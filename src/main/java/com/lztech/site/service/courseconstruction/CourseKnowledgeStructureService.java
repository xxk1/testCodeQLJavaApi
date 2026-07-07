package com.lztech.site.service.courseconstruction;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseKnowledgeStructure;
import com.lztech.domain.model.course.CourseResourceFile;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.KnowledgeStructureType;
import com.lztech.domain.model.course.enums.StructureStatus;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.persistence.repositories.course.CourseCompletionRepository;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.CourseResourceFileRepository;
import com.lztech.persistence.repositories.course.CourseResourceKnowledgePointRepository;
import com.lztech.persistence.repositories.courseknowledgestructure.CourseKnowledgeStructureRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.until.Util;
import com.lztech.site.viewmodel.courseconstruction.CourseKnowledgeStructureResource;
import com.lztech.site.viewmodel.coursemanagement.*;
import com.lztech.site.viewmodel.event.KnowledgeStructureTopicVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.ConstantDataVisual.DATA_VISUAL_SOURCE_CLOUD_CLASSROOM;

@Service
public class CourseKnowledgeStructureService {

    private static double trillion = 1048576.0;
    private static double zeroPointOne = 0.1;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseKnowledgeStructureRepository courseKnowledgeStructureRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseResourceKnowledgePointRepository courseResourceKnowledgePointRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private CourseCompletionRepository courseCompletionRepository;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;
    @Autowired
    private Neo4jUtil neo4jUtil;

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;

    public Result createCourseKnowledgeStructure(CourseKnowledgeStructureVo structureVo) {
        Date now = new Date();
        Course course = courseRepository.findByIdAndUseStateIsTrue(structureVo.getCourseId()).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(structureVo.getVersionId(), CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        if (StringUtils.isNotBlank(structureVo.getParentId())) {
            int parentStructureNum = courseKnowledgeStructureRepository.countByIdAndStructureStatus(structureVo.getParentId(),
                    StructureStatus.NORMAL);
            if (parentStructureNum <= 0) {
                return Result.error("保存失败，父级知识结构已被删除");
            }
        }
        List<CourseKnowledgeStructure> courseKnowledgeStructureList =
                courseKnowledgeStructureRepository.findByKnowledgeStructureTypeAndStructureStatusAndAndCourseAndCourseVersion
                        (KnowledgeStructureType.KNOWLEDGE_POINT, StructureStatus.NORMAL, course, courseVersion);
        KnowledgeStructureType knowledgeStructureType = KnowledgeStructureType.getKnowledgeStructureType(structureVo.getStructureType());
        List<CourseKnowledgeStructure> resultList = new ArrayList<>();
        Integer showOrder;
        if (StringUtils.isBlank(structureVo.getParentId())) {
            Integer maxShowOrder = courseKnowledgeStructureRepository.getMaxShowOrderWithoutParentId(course.getId(), courseVersion.getId());
            showOrder = Objects.isNull(maxShowOrder) ? 1 : ++maxShowOrder;
        } else {
            Integer maxShowOrder = courseKnowledgeStructureRepository.getMaxShowOrderWithParentId(course.getId(), courseVersion.getId(),
                    structureVo.getParentId());
            showOrder = Objects.isNull(maxShowOrder) ? 1 : ++maxShowOrder;
        }
        boolean isPointInsert = Objects.nonNull(knowledgeStructureType) && KnowledgeStructureType.KNOWLEDGE_POINT.equals(knowledgeStructureType);
        if (isPointInsert) {
            if (CollectionUtils.isNotEmpty(structureVo.getKnowledgePointList())) {
                List<String> pointList = structureVo.getKnowledgePointList();
                for (int i = 0; i < pointList.size(); i++) {
                    if (StringUtils.isBlank(pointList.get(i))) {
                        continue;
                    }
                    CourseKnowledgeStructure courseKnowledgeStructure = buildCourseKnowledgeStructure(pointList.get(i), course, structureVo,
                            courseVersion, showOrder + i, now);

                    courseKnowledgeStructure.setKnowledgeStructureType(knowledgeStructureType);
                    resultList.add(courseKnowledgeStructure);
                }
            }
        } else {
            CourseKnowledgeStructure courseKnowledgeStructure = buildCourseKnowledgeStructure(structureVo.getContent(), course, structureVo,
                    courseVersion, showOrder, now);
            courseKnowledgeStructure.setKnowledgeStructureType(knowledgeStructureType);
            resultList.add(courseKnowledgeStructure);
        }

        List<CourseKnowledgeStructure> courseKnowledgeStructures = courseKnowledgeStructureRepository.saveAll(resultList);
        if (isPointInsert) {
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.KNOWLEDGE_STRUCTURE, true, structureVo.getOperatorId(),
                    structureVo.getOperatorName());
            if (dataVisualEnable) {
                List<String> parentIds =
                        courseKnowledgeStructures.stream().map(CourseKnowledgeStructure::getParentId).collect(Collectors.toList());
                List<CourseKnowledgeStructure> knowledgeStructures = courseKnowledgeStructureRepository.findByIdIn(parentIds);
                Map<String, List<CourseKnowledgeStructure>> listMap =
                        knowledgeStructures.stream().collect(Collectors.groupingBy(CourseKnowledgeStructure::getId));
                List<KnowledgeStructureTopicVo> knowledgeStructureTopicVos =
                        this.buildKnowledgeStructureTopicVo(courseKnowledgeStructures, listMap, course, courseVersion);
                eventService.sendAddKnowledgeStructureList(knowledgeStructureTopicVos);

            }
        }

        return Result.success();
    }

    private List<KnowledgeStructureTopicVo> buildKnowledgeStructureTopicVo
            (List<CourseKnowledgeStructure> courseKnowledgeStructures, Map<String, List<CourseKnowledgeStructure>> listMap,
             Course course, CourseVersion courseVersion) {
        List<KnowledgeStructureTopicVo> knowledgeStructureTopicVos = new ArrayList<>();
        courseKnowledgeStructures.forEach(courseKnowledgeStructure -> {
            KnowledgeStructureTopicVo knowledgeStructureTopicVo = new KnowledgeStructureTopicVo();
            knowledgeStructureTopicVo.setId(courseKnowledgeStructure.getId());
            knowledgeStructureTopicVo.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
            knowledgeStructureTopicVo.setKnowledgeStructureType(courseKnowledgeStructure.getKnowledgeStructureType().getValue());
            knowledgeStructureTopicVo.setCourseCode(course.getCourseCode());
            knowledgeStructureTopicVo.setCourseId(course.getId());
            knowledgeStructureTopicVo.setCourseName(course.getCourseName());
            knowledgeStructureTopicVo.setParentId(courseKnowledgeStructure.getParentId());
            knowledgeStructureTopicVo.setCourseVersionId(courseVersion.getId());
            knowledgeStructureTopicVo.setContent(courseKnowledgeStructure.getContent());
            knowledgeStructureTopicVo.setModifierName(courseKnowledgeStructure.getCreatorName());
            knowledgeStructureTopicVo.setModifierId(courseKnowledgeStructure.getCreatorId());
            knowledgeStructureTopicVo.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
            knowledgeStructureTopicVo.setParentName(listMap.get(courseKnowledgeStructure.getParentId()).get(0).getContent());
            knowledgeStructureTopicVos.add(knowledgeStructureTopicVo);
        });
        return knowledgeStructureTopicVos;
    }

    public void sendKnowledgeStructureTopicVo
            (CourseKnowledgeStructure courseKnowledgeStructure, CourseKnowledgeStructure parentCourseKnowledgeStructure) {
        KnowledgeStructureTopicVo knowledgeStructureTopicVo = new KnowledgeStructureTopicVo();
        knowledgeStructureTopicVo.setId(courseKnowledgeStructure.getId());
        knowledgeStructureTopicVo.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        knowledgeStructureTopicVo.setKnowledgeStructureType(courseKnowledgeStructure.getKnowledgeStructureType().getValue());
        knowledgeStructureTopicVo.setCourseCode(courseKnowledgeStructure.getCourse().getCourseCode());
        knowledgeStructureTopicVo.setCourseId(courseKnowledgeStructure.getCourse().getId());
        knowledgeStructureTopicVo.setCourseName(courseKnowledgeStructure.getCourse().getCourseName());
        knowledgeStructureTopicVo.setParentId(parentCourseKnowledgeStructure.getId());
        knowledgeStructureTopicVo.setCourseVersionId(courseKnowledgeStructure.getCourseVersion().getId());
        knowledgeStructureTopicVo.setContent(courseKnowledgeStructure.getContent());
        knowledgeStructureTopicVo.setModifierName(courseKnowledgeStructure.getCreatorName());
        knowledgeStructureTopicVo.setModifierId(courseKnowledgeStructure.getCreatorId());
        knowledgeStructureTopicVo.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
        knowledgeStructureTopicVo.setParentName(parentCourseKnowledgeStructure.getContent());

        eventService.sendAddKnowledgeStructure(knowledgeStructureTopicVo);
    }


    private CourseKnowledgeStructure buildCourseKnowledgeStructure(String content,
                                                                   Course course,
                                                                   CourseKnowledgeStructureVo structureVo,
                                                                   CourseVersion courseVersion,
                                                                   Integer showOrder,
                                                                   Date now) {
        CourseKnowledgeStructure courseKnowledgeStructure = new CourseKnowledgeStructure();
        courseKnowledgeStructure.setContent(content.trim());

        courseKnowledgeStructure.setStructureStatus(StructureStatus.NORMAL);
        courseKnowledgeStructure.setParentId(StringUtils.isBlank(structureVo.getParentId()) ? null : structureVo.getParentId());
        courseKnowledgeStructure.setShowOrder(showOrder);
        courseKnowledgeStructure.setCourse(course);
        courseKnowledgeStructure.setCourseVersion(courseVersion);
        courseKnowledgeStructure.setModifierId(structureVo.getOperatorId());
        courseKnowledgeStructure.setModifierName(structureVo.getOperatorName());
        courseKnowledgeStructure.setModifyTime(now);
        courseKnowledgeStructure.setCreatorId(structureVo.getOperatorId());
        courseKnowledgeStructure.setCreatorName(structureVo.getOperatorName());
        courseKnowledgeStructure.setCreateTime(now);

        return courseKnowledgeStructure;

    }

    public List<CourseKnowledgeStructureResource> getCourseKnowledgeStructure(String courseId, String versionId) {
        List<CourseKnowledgeStructure> courseKnowledgeStructureList =
                courseKnowledgeStructureRepository.findByCourseIdAndVersionId(courseId, versionId);

        List<CourseKnowledgeStructure> topFloorCourseKnowledgeStructureList = courseKnowledgeStructureList
                .stream()
                .filter(c -> StringUtils.isBlank(c.getParentId()))
                .sorted(Comparator.comparing(CourseKnowledgeStructure::getShowOrder))
                .collect(Collectors.toList());

        List<CourseKnowledgeStructureResource> resourceList = buildCourseKnowledgeStructureTree(topFloorCourseKnowledgeStructureList,
                courseKnowledgeStructureList);
        return resourceList;
    }

    private List<CourseKnowledgeStructureResource> buildCourseKnowledgeStructureTree(List<CourseKnowledgeStructure>
                                                                                             topFloorCourseKnowledgeStructureList,
                                                                                     List<CourseKnowledgeStructure> courseKnowledgeStructureList) {
        List<CourseKnowledgeStructureResource> resourceList = new ArrayList<>();
        topFloorCourseKnowledgeStructureList.forEach(c -> {
            CourseKnowledgeStructureResource courseKnowledgeStructureResource = new CourseKnowledgeStructureResource();
            courseKnowledgeStructureResource.setId(c.getId());
            courseKnowledgeStructureResource.setContent(c.getContent());
            courseKnowledgeStructureResource.setStructureType(c.getKnowledgeStructureType().getValue());
            courseKnowledgeStructureResource.setShowOrder(c.getShowOrder());
            courseKnowledgeStructureResource.setChildList(buildCourseKnowledgeStructureChildList(c, courseKnowledgeStructureList));
            resourceList.add(courseKnowledgeStructureResource);


        });
        return resourceList;
    }

    private List<CourseKnowledgeStructureResource> buildCourseKnowledgeStructureChildList(CourseKnowledgeStructure courseKnowledgeStructure,
                                                                                          List<CourseKnowledgeStructure>
                                                                                                  courseKnowledgeStructureList) {
        List<CourseKnowledgeStructureResource> returnList = new ArrayList<>();
        List<CourseKnowledgeStructure> childList = courseKnowledgeStructureList
                .stream()
                .filter(child -> StringUtils.isNotBlank(child.getParentId()) &&
                        child.getParentId().equals(courseKnowledgeStructure.getId()))
                .sorted(Comparator.comparing(CourseKnowledgeStructure::getShowOrder))
                .collect(Collectors.toList());
        childList.forEach(c -> {
            CourseKnowledgeStructureResource courseKnowledgeStructureResource = new CourseKnowledgeStructureResource();
            courseKnowledgeStructureResource.setId(c.getId());
            courseKnowledgeStructureResource.setContent(c.getContent());
            courseKnowledgeStructureResource.setStructureType(c.getKnowledgeStructureType().getValue());
            courseKnowledgeStructureResource.setShowOrder(c.getShowOrder());
            courseKnowledgeStructureResource.setChildList(buildCourseKnowledgeStructureChildList(c, courseKnowledgeStructureList));
            returnList.add(courseKnowledgeStructureResource);
        });
        return returnList;
    }

    public Result deleteCourseKnowledgeStructure(String id, CourseKnowledgeStructureVo structureVo) {
        Course course = courseRepository.findByIdAndUseStateIsTrue(structureVo.getCourseId()).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(structureVo.getVersionId(), CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        int count = courseKnowledgeStructureRepository.countByParentIdAndStructureStatus(id, StructureStatus.NORMAL);
        if (count > 0) {
            return Result.error("该章节下已存在内容，不可删除");
        }
        Date now = new Date();
        courseKnowledgeStructureRepository.updateCourseKnowledgeStructureStatus(id, structureVo.getOperatorId(), structureVo.getOperatorName(), now);
        if (Objects.nonNull(structureVo.getStructureType()) && structureVo.getStructureType() == KnowledgeStructureType.KNOWLEDGE_POINT.getValue()) {
            int knowledgePointCount = courseKnowledgeStructureRepository.countByCourseIdAndVersionIdAndType(structureVo.getCourseId(),
                    structureVo.getVersionId(), structureVo.getStructureType());
            if (knowledgePointCount == 0) {
                courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.KNOWLEDGE_STRUCTURE, false, structureVo.getOperatorId(),
                        structureVo.getOperatorName());
            }
            if (dataVisualEnable) {
                eventService.sendDeleteKnowledgeStructure(id);
            }
            courseResourceKnowledgePointRepository.deleteByPointId(id);

        }
        return Result.success();
    }

    public Result updateCourseKnowledgeStructure(String id, CourseKnowledgeStructureVo structureVo) {
        Course course = courseRepository.findByIdAndUseStateIsTrue(structureVo.getCourseId()).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(structureVo.getVersionId(), CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        Date now = new Date();
        courseKnowledgeStructureRepository.updateCourseKnowledgeStructureContent(id, structureVo.getContent(), structureVo.getOperatorId(),
                structureVo.getOperatorName(), now);
        return Result.success();
    }

    public Result updateCourseKnowledgeStructureSort(String courseId,
                                                     String versionId,
                                                     String operatorId,
                                                     String operatorName,
                                                     List<CourseKnowledgeStructureSortVo> structureVoList) {
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(versionId,
                CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        Date now = new Date();
        List<CourseKnowledgeStructure> structureList = courseKnowledgeStructureRepository.findByCourseIdAndVersionId(courseId, versionId);
        recursionUpdateSort(operatorId, operatorName, structureVoList, now, structureList);
        courseKnowledgeStructureRepository.saveAll(structureList);
        return Result.success();
    }

    private void recursionUpdateSort(String operatorId, String operatorName,
                                     List<CourseKnowledgeStructureSortVo> structureVoList,
                                     Date now,
                                     List<CourseKnowledgeStructure> structureList) {
        for (int i = 0; i < structureVoList.size(); i++) {
            CourseKnowledgeStructureSortVo sortVo = structureVoList.get(i);
            CourseKnowledgeStructure courseKnowledgeStructure = structureList
                    .stream()
                    .filter(s -> s.getId().equals(sortVo.getId()))
                    .findFirst()
                    .orElse(null);
            if (Objects.nonNull(courseKnowledgeStructure)) {
                courseKnowledgeStructure.setShowOrder(i + 1);
                courseKnowledgeStructure.setModifierId(operatorId);
                courseKnowledgeStructure.setModifierName(operatorName);
                courseKnowledgeStructure.setModifyTime(now);
                courseKnowledgeStructure.setParentId(null);
            }
            if (CollectionUtils.isNotEmpty(sortVo.getChildList())) {
                recursionUpdateChildListSort(operatorId, operatorName, sortVo.getChildList(),
                        now, structureList, sortVo.getId());
            }
        }
    }

    private void recursionUpdateChildListSort(String operatorId,
                                              String operatorName,
                                              List<CourseKnowledgeStructureSortVo> structureVoList,
                                              Date now,
                                              List<CourseKnowledgeStructure> structureList,
                                              String parentId) {
        for (int i = 0; i < structureVoList.size(); i++) {
            CourseKnowledgeStructureSortVo sortVo = structureVoList.get(i);
            CourseKnowledgeStructure courseKnowledgeStructure = structureList
                    .stream()
                    .filter(s -> s.getId().equals(sortVo.getId()))
                    .findFirst()
                    .orElse(null);
            if (Objects.nonNull(courseKnowledgeStructure)) {
                courseKnowledgeStructure.setShowOrder(i + 1);
                courseKnowledgeStructure.setModifierId(operatorId);
                courseKnowledgeStructure.setModifierName(operatorName);
                courseKnowledgeStructure.setModifyTime(now);
                courseKnowledgeStructure.setParentId(parentId);
            }
            if (CollectionUtils.isNotEmpty(sortVo.getChildList())) {
                recursionUpdateChildListSort(operatorId, operatorName, sortVo.getChildList(),
                        now, structureList, sortVo.getId());
            }
        }
    }

    public List<CourseKnowledgePointResource> getCourseKnowledgePointResource(String courseId, String versionId, String pointId) {
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                courseKnowledgeGraphService.getCourseKnowledgeGraphDomain(courseId);
        List<CourseKnowledgePointResourceSqlVo> sqlVoList = getCourseKnowledgePointResourceSqlVo(courseId, versionId, pointId);
        if (CollectionUtils.isEmpty(sqlVoList)) {
            return new ArrayList<>();
        }
        List<String> pointIdList = sqlVoList.stream().map(CourseKnowledgePointResourceSqlVo::getPointId).distinct().collect(Collectors.toList());
        List<CourseResourceKnowledgePointSqlVo> pointSqlVoList = neo4jUtil
                .getCourseResourceKnowledgePointList(courseKnowledgeGraphDomain.getId(), pointIdList);
        sqlVoList.forEach(pointSqlVo -> {
            CourseResourceKnowledgePointSqlVo knowledgePointResourceVo = pointSqlVoList
                    .stream().filter(sqlVo -> sqlVo.getPointId().equals(pointSqlVo.getPointId())).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(knowledgePointResourceVo)) {
                pointSqlVo.setPointContent(knowledgePointResourceVo.getPointContent());
            }
        });
        sqlVoList = sqlVoList
                .stream()
                .sorted(Comparator.comparing(CourseKnowledgePointResourceSqlVo::getPointId, Comparator.naturalOrder()))
                .collect(Collectors.toList());
        List<String> resourceDetailIdList = sqlVoList
                .stream()
                .map(CourseKnowledgePointResourceSqlVo::getResourceDetailId)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
        List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(resourceDetailIdList);
        List<CourseKnowledgePointResource> resourceList = new ArrayList<>();
        LinkedHashMap<String, List<CourseKnowledgePointResourceSqlVo>> pointMap =
                sqlVoList.stream().collect(Collectors.groupingBy(CourseKnowledgePointResourceSqlVo::getPointId, LinkedHashMap::new,
                        Collectors.toList()));
        List<CourseKnowledgePointResourceSqlVo> finalSqlVoList = sqlVoList;
        pointMap.forEach((key, value) -> {
            CourseKnowledgePointResourceSqlVo sqlVo = value.get(0);
            CourseKnowledgePointResource courseKnowledgePointResource = new CourseKnowledgePointResource();
            courseKnowledgePointResource.setPointId(sqlVo.getPointId());
            courseKnowledgePointResource.setPointContent(sqlVo.getPointContent());
            courseKnowledgePointResource.setResourceList(buildPointResourceList(value, courseResourceFiles, finalSqlVoList));
            resourceList.add(courseKnowledgePointResource);
        });

        return resourceList;
    }

    private List<CourseResourceVo> buildPointResourceList(List<CourseKnowledgePointResourceSqlVo> resourceList,
                                                          List<CourseResourceFile> courseResourceFiles,
                                                          List<CourseKnowledgePointResourceSqlVo> finalSqlVoList) {
        List<CourseResourceVo> courseResourceVoList = new ArrayList<>();
        for (CourseKnowledgePointResourceSqlVo courseKnowledgePointResourceSqlVo : resourceList) {
            CourseResourceVo courseResourceVo = new CourseResourceVo();
            courseResourceVo.setResourceId(courseKnowledgePointResourceSqlVo.getResourceId());

            courseResourceVo.setResourceName(courseKnowledgePointResourceSqlVo.getResourceName());
            courseResourceVo.setResourceType(courseKnowledgePointResourceSqlVo.getResourceType());

            courseResourceVo.setModifyTime(courseKnowledgePointResourceSqlVo.getModifierTime());
            courseResourceVo.setModifierName(courseKnowledgePointResourceSqlVo.getModifierName());
            courseResourceVo.setModifierId(courseKnowledgePointResourceSqlVo.getModifierId());
            courseResourceVo.setIsPublic(courseKnowledgePointResourceSqlVo.isPublic() ? 1 : 0);
            courseResourceVo.setResourceNum(courseKnowledgePointResourceSqlVo.getResourceContentNum());

            courseResourceVo.setQuestionNum(courseKnowledgePointResourceSqlVo.getResourceContentNum());
            courseResourceVo.setResourceDetailId(courseKnowledgePointResourceSqlVo.getResourceDetailId());
            courseResourceVo.setResourceReferences(Objects.isNull(courseKnowledgePointResourceSqlVo.getResourceReferenceNum()) ? 0 :
                    courseKnowledgePointResourceSqlVo.getResourceReferenceNum());
            courseResourceVo.setResourceOtherReferences(Objects.isNull(courseKnowledgePointResourceSqlVo.getResourceOtherReferenceNum()) ? 0 :
                    courseKnowledgePointResourceSqlVo.getResourceOtherReferenceNum());
            courseResourceVo.setCourseId(courseKnowledgePointResourceSqlVo.getCourseId());
            List<CourseKnowledgePointResourceSqlVo> resourcePointList = finalSqlVoList
                    .stream()
                    .filter(r -> r.getResourceId().equals(courseKnowledgePointResourceSqlVo.getResourceId()))
                    .collect(Collectors.toList());
            courseResourceVo.setKnowledgePointList(buildResourcePointList(resourcePointList));

            CourseResourceFile file = courseResourceFiles
                    .stream()
                    .filter(courseResourceFile -> courseResourceFile.getId().equals(courseKnowledgePointResourceSqlVo.getResourceDetailId()))
                    .findFirst()
                    .orElse(null);
            if (ObjectUtils.isNotEmpty(file)) {
                courseResourceVo.setInnerIp(file.getInnerIp());
                courseResourceVo.setOuterIp(file.getOuterIp());
                courseResourceVo.setFilePath(file.getFilePath());
                courseResourceVo.setFileType(file.getFileType());
                String sizeStr = "";
                if (file.getFileSize() != null) {
                    double size = file.getFileSize() / trillion;
                    if (size <= zeroPointOne) {
                        sizeStr += "0.1M";
                    } else {
                        sizeStr += Util.initDecimalFormat(size) + "M";
                    }
                }
                courseResourceVo.setResourceSize(sizeStr);
            } else {
                courseResourceVo.setInnerIp("");
                courseResourceVo.setOuterIp("");
                courseResourceVo.setFilePath("");
                courseResourceVo.setResourceSize("");
                courseResourceVo.setFileType("");
            }
            courseResourceVoList.add(courseResourceVo);
        }
        return courseResourceVoList;
    }

    private List<KnowledgePointResource> buildResourcePointList(List<CourseKnowledgePointResourceSqlVo> resourcePointList) {
        List<KnowledgePointResource> knowledgePointList = new ArrayList<>();
        resourcePointList.forEach(r -> {
            KnowledgePointResource knowledgePointResource = new KnowledgePointResource();
            knowledgePointResource.setPointId(r.getPointId());
            knowledgePointResource.setPointName(r.getPointContent());
            knowledgePointList.add(knowledgePointResource);
        });
        return knowledgePointList;
    }

    private void setCourseKnowledgePointResourceSqlVoShowOrder(List<CourseKnowledgeStructure> courseKnowledgeStructureList,
                                                               List<CourseKnowledgePointResourceSqlVo> sqlVoList) {

        for (CourseKnowledgePointResourceSqlVo sqlVo : sqlVoList) {
            if (StringUtils.isBlank(sqlVo.getPointParentId())) {
                continue;
            }
            CourseKnowledgeStructure pointParent =
                    courseKnowledgeStructureList.stream().filter(c -> c.getId().equals(sqlVo.getPointParentId())).findFirst().orElse(null);
            if (Objects.isNull(pointParent)) {
                continue;
            }
            if (KnowledgeStructureType.SECTION.equals(pointParent.getKnowledgeStructureType())) {
                sqlVo.setSectionShowOrder(pointParent.getShowOrder());
                CourseKnowledgeStructure sectionParent =
                        courseKnowledgeStructureList.stream().filter(c -> c.getId().equals(pointParent.getParentId())).findFirst().orElse(null);
                if (Objects.nonNull(sectionParent)) {
                    sqlVo.setChapterShowOrder(sectionParent.getShowOrder());
                }
            } else if (KnowledgeStructureType.CHAPTER.equals(pointParent.getKnowledgeStructureType())) {
                sqlVo.setChapterShowOrder(pointParent.getShowOrder());
            }
        }
    }

    private List<CourseKnowledgePointResourceSqlVo> getCourseKnowledgePointResourceSqlVo(String courseId, String versionId, String pointId) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "select crkp.knowledge_point_id pointId," +
                "cs.course_id courseId, " +
                "cs.course_version_id versionId, " +
                "cr.id resourceId, " +
                "cr.resource_type resourceType, " +
                "cr.resource_content_num resourceContentNum, " +
                "cr.resource_references resourceReferenceNum, " +
                "cr.resource_other_references resourceOtherReferenceNum, " +
                "cr.resource_name resourceName, " +
                "cr.resource_detail_id resourceDetailId, " +
                "cr.is_public isPublic, " +
                "cr.modifier_id modifierId, " +
                "cr.modifier_name modifierName, " +
                "date_format(cr.modify_time,'%Y-%m-%d %H:%i:%s') modifierTime " +
                "from tb_course_structure cs " +
                "inner join tb_course_resource cr on  cs.id = cr.course_structure_id " +
                "inner join tb_course_resource_knowledge_point crkp on crkp.resource_id = cr.id " +
                "where cs.structure_status = 0 " +
                "and cr.resource_status = 0 " +
                "and cr.is_public = 1 ";

        if (StringUtils.isNotBlank(courseId)) {
            sql += " and cs.course_id =:courseId ";
            paramMap.put("courseId", courseId);
        }
        if (StringUtils.isNotBlank(versionId)) {
            sql += " and cs.course_version_id =:versionId ";
            paramMap.put("versionId", versionId);
        }
        if (StringUtils.isNotBlank(pointId)) {
            sql += " and crkp.knowledge_point_id =:pointId ";
            paramMap.put("pointId", pointId);
        }
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseKnowledgePointResourceSqlVo.class));
        return query.getResultList();
    }
}

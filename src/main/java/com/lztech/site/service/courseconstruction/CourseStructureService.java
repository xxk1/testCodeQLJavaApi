package com.lztech.site.service.courseconstruction;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.CourseResourceFileRepository;
import com.lztech.persistence.repositories.course.CourseStructureRepository;
import com.lztech.persistence.repositories.course.CourseTeachingTeamRepository;
import com.lztech.persistence.repositories.courseknowledgestructure.CourseKnowledgeStructureRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.until.Util;
import com.lztech.site.viewmodel.coursemanagement.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseStructureService {

    private static double trillion = 1048576.0;
    private static double zeroPointOne = 0.1;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Autowired
    private CourseKnowledgeStructureRepository courseKnowledgeStructureRepository;
    @Autowired
    private CourseVersionService courseVersionService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;
    @Autowired
    private Neo4jUtil neo4jUtil;

    @Transactional
    public Result createCourseStructure(StructureParam param) {
        Date now = new Date();
        Boolean flag = false;
        Course course = courseRepository.findByIdAndUseStateIsTrue(param.getCourseId()).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(param.getVersionId(), CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        if (StringUtils.isNotBlank(param.getParentId())) {
            int parentStructureNum = courseStructureRepository.countByIdAndStructureStatus(param.getParentId(),
                    StructureStatus.NORMAL);
            if (parentStructureNum <= 0) {
                return Result.error("保存失败，父级知识结构已被删除");
            }
        }
        Integer showOrder;
        if (StringUtils.isBlank(param.getParentId())) {
            Integer maxShowOrder = courseStructureRepository.getMaxShowOrderWithoutParentId(course.getId(), courseVersion.getId());
            showOrder = Objects.isNull(maxShowOrder) ? 0 : ++maxShowOrder;
        } else {
            Integer maxShowOrder = courseStructureRepository.getMaxShowOrderWithParentId(course.getId(), courseVersion.getId(),
                    param.getParentId());
            showOrder = Objects.isNull(maxShowOrder) ? 0 : ++maxShowOrder;
        }
        CourseStructure courseStructureSon = new CourseStructure();
        courseStructureSon.setCreateTime(now);
        courseStructureSon.setCreatorId(param.getUserId());
        courseStructureSon.setCreatorName(param.getUserName());
        courseStructureSon.setModifyTime(now);
        courseStructureSon.setModifierId(param.getUserId());
        courseStructureSon.setModifierName(param.getUserName());
        courseStructureSon.setCourse(course);
        courseStructureSon.setStructureStatus(StructureStatus.NORMAL);
        courseStructureSon.setCourseResources(new ArrayList<>());
        courseStructureSon.setCourseStructureName(param.getStructureName());
        courseStructureSon.setParentId(StringUtils.isBlank(param.getParentId()) ? null : param.getParentId());
        courseStructureSon.setCourseVersion(courseVersion);
        courseStructureSon.setShowOrder(showOrder);
        courseStructureRepository.save(courseStructureSon);
        courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_STRUCTURE, true, param.getUserId(),
                param.getUserName());
        return Result.success();
    }

    public Result getCourseResources(String courseId, String versionId, String userId) {
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(versionId, CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        int courseTeachingTeamTeacherNum = courseTeachingTeamRepository.countByCourseIdAndVersionIdAndUserId(courseId, versionId, userId);
        if (courseTeachingTeamTeacherNum == 0) {
            return Result.error("请先加入教学团队");
        }
        List<CourseStructureResource> courseStructureResources = new ArrayList<>();
        List<CourseStructure> courseStructures = courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus(courseId, versionId,
                StructureStatus.NORMAL);
        if (CollectionUtils.isEmpty(courseStructures)) {
            return Result.error("当前课程无课程结构");
        }
        List<CourseResource> courseResources = new ArrayList<>();
        //获取最上层课程结构
        List<CourseStructure> topStructures = courseStructures
                .stream()
                .filter(courseStructure -> StringUtils.isBlank(courseStructure.getParentId()))
                .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                .collect(Collectors.toList());
        courseStructures.forEach(structure -> {
            courseResources.addAll(structure.getCourseResources()
                    .stream()
                    .filter(courseResource -> (courseResource.getIsPublic()
                            && courseResource.getResourceStatus().equals(ResourceStatus.NORMAL)
                            && !courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) ||
                            (!courseResource.getIsPublic() && userId.equals(courseResource.getCreatorId())
                                    && courseResource.getResourceStatus().equals(ResourceStatus.NORMAL))
                                    && !courseResource.getResourceType().equals(ResourceType.CLASS_TEST))
                    .collect(Collectors.toList()));
        });
        List<String> detailIds = courseResources
                .stream()
                .map(CourseResource::getResourceDetailId)
                .collect(Collectors.toList());
        List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
        courseStructureResources = buildCourseStructureResourceList(topStructures, courseStructures, courseResources, courseResourceFiles);
        return Result.success().setData(courseStructureResources);
    }

    private List<CourseStructureResource> buildCourseStructureResourceList(List<CourseStructure> topStructures,
                                                                           List<CourseStructure> courseStructures,
                                                                           List<CourseResource> courseResources,
                                                                           List<CourseResourceFile> courseResourceFiles) {
        List<CourseStructureResource> courseStructureResources = new ArrayList<>();
        topStructures.forEach(courseStructure -> {
            CourseStructureResource courseStructureResource = new CourseStructureResource();
            courseStructureResource.setStructureId(courseStructure.getId());
            courseStructureResource.setParentId(courseStructure.getParentId() == null ? "" : courseStructure.getParentId());
            courseStructureResource.setStructureName(courseStructure.getCourseStructureName());
            courseStructureResource.setShowOrder(courseStructure.getShowOrder());
            List<CourseStructure> structures = courseStructures
                    .stream()
                    .filter(structure -> courseStructure.getId().equals(structure.getParentId()))
                    .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                    .collect(Collectors.toList());
            courseStructureResource.setChildStructureList
                    (buildCourseStructureResourceList(structures, courseStructures, courseResources, courseResourceFiles));
            List<CourseResource> resources = courseResources
                    .stream()
                    .filter(courseResource -> courseResource.getCourseStructure().getId().equals(courseStructure.getId()))
                    .sorted(Comparator.comparing(CourseResource::getModifyTime))
                    .collect(Collectors.toList());
            List<CourseResourceFile> resourceFiles = new ArrayList<>();
            resources.forEach(courseResource -> {
                CourseResourceFile file = courseResourceFiles
                        .stream()
                        .filter(courseResourceFile -> courseResource.getResourceDetailId().equals(courseResourceFile.getId()))
                        .findFirst()
                        .orElse(null);
                if (ObjectUtils.isNotEmpty(file)) {
                    resourceFiles.add(file);
                }
            });
            courseStructureResource.setCourseResourceList(getCourseResourceInfos(resources, resourceFiles));
            courseStructureResources.add(courseStructureResource);
        });
        return courseStructureResources;
    }

    private List<CourseResourceInfo> getCourseResourceInfos(List<CourseResource> resources, List<CourseResourceFile> resourceFiles) {
        return resources.stream().map(courseResource -> new CourseResourceInfo() {{
            this.setResourceId(courseResource.getId());
            this.setResourceName(courseResource.getResourceName());
            CourseResourceFile file = resourceFiles
                    .stream()
                    .filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                    .findFirst()
                    .orElse(null);
            if (ObjectUtils.isNotEmpty(file)) {
                this.setFileSavedName(file.getFileSavedName());
                this.setInnerIp(file.getInnerIp());
                this.setOuterIp(file.getOuterIp());
                this.setFilePath(file.getFilePath());
                this.setFileType(file.getFileType());
                this.setFileSize(String.valueOf(file.getFileSize()));
            } else {
                this.setFileSavedName("");
                this.setInnerIp("");
                this.setOuterIp("");
                this.setFilePath("");
                this.setFileSize("");
                this.setFileType("");
            }
        }}).collect(Collectors.toList());
    }

    public Result getCoursesContent(String courseId, String versionId, Integer type, String needFilterUserId, String needUserId) {
        List<CourseStructureVo> courseStructureVos;

        List<CourseVersion> courseVersionList = new ArrayList<>();

        if (StringUtils.isNotBlank(versionId)) {
            Optional<CourseVersion> courseVersionOptional = courseVersionRepository.findById(versionId);
            courseVersionOptional.ifPresent(courseVersionList::add);
        } else {
            Course course = courseRepository.findByIdAndUseState(courseId, true);
            CourseVersion courseVersion = courseVersionService.generateAndUpdateCourseVersion(course);
            courseVersionList.add(courseVersion);
        }

        List<CourseStructure> courseStructures = courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus
                (courseId, CollectionUtils.isNotEmpty(courseVersionList) ? courseVersionList.get(0).getId() : versionId, StructureStatus.NORMAL);


        //获取最上层课程结构
        List<CourseStructure> topStructures = courseStructures
                .stream()
                .filter(courseStructure -> StringUtils.isBlank(courseStructure.getParentId()))
                .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                .collect(Collectors.toList());
        //获取课程下正常、公开的资源及资源详情
        if (type == 0) {
            List<CourseResource> courseResources = new ArrayList<>();
            for (CourseStructure courseStructure : courseStructures) {
                if (StringUtils.isNotBlank(needUserId)) {
                    courseResources.addAll(courseStructure.getCourseResources()
                            .stream()
                            .filter(courseResource -> courseResource.getResourceStatus().equals(ResourceStatus.NORMAL))
                            .collect(Collectors.toList()));
                } else {
                    courseResources.addAll(courseStructure.getCourseResources()
                            .stream()
                            .filter(courseResource -> courseResource.getIsPublic()
                                    && courseResource.getResourceStatus().equals(ResourceStatus.NORMAL))
                            .collect(Collectors.toList()));
                }

            }
            courseResources = courseResources.stream()
                    .sorted(Comparator.comparing(
                            CourseResource::getAuxiliarySort,
                            (a, b) -> {
                                int intA = a != null ? a : 0;
                                int intB = b != null ? b : 0;
                                return Integer.compare(intB, intA);
                            }
                    ).thenComparing(CourseResource::getModifyTime,Comparator.reverseOrder()).thenComparing(CourseResource::getId))
                    .collect(Collectors.toList());
            if (StringUtils.isNotBlank(needFilterUserId)) {
                courseResources = courseResources.stream().filter(cr -> !needFilterUserId.equals(cr.getCreatorId())).collect(Collectors.toList());
            }
            if (StringUtils.isNotBlank(needUserId)) {
                courseResources = courseResources.stream().filter(cr -> needUserId.equals(cr.getCreatorId())).collect(Collectors.toList());
            }
            List<String> detailIds = courseResources
                    .stream()
                    .map(CourseResource::getResourceDetailId)
                    .collect(Collectors.toList());
            List<String> resourceIds = courseResources.stream().map(CourseResource::getId).collect(Collectors.toList());
            List<CourseResourceKnowledgePointSqlVo> sqlVoList = new ArrayList<>();
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomain(courseId);
            if (ObjectUtils.isNotEmpty(courseKnowledgeGraphDomain)) {
                sqlVoList = getCourseResourceKnowledgePointSqlVo(courseKnowledgeGraphDomain.getId(), courseResources);

            }
            List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
            courseStructureVos = buildCourseStructureVoList(topStructures, courseStructures, courseResources, courseResourceFiles, sqlVoList);
        } else {
            courseStructureVos = buildCourseStructureVoList(topStructures, courseStructures);
        }
        return Result.success().setData(courseStructureVos);
    }

    public Result getCoursesContentPack(String courseId, String versionId, Integer type) {
        List<CourseStructureVo> courseStructureVos;

        List<CourseVersion> courseVersionList = new ArrayList<>();

        if (StringUtils.isNotBlank(versionId)) {
            Optional<CourseVersion> courseVersionOptional = courseVersionRepository.findById(versionId);

            courseVersionOptional.ifPresent(courseVersionList::add);
        }

        List<CourseStructure> courseStructures = courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus
                (courseId, CollectionUtils.isNotEmpty(courseVersionList) ? courseVersionList.get(0).getId() : versionId, StructureStatus.NORMAL);

        //获取最上层课程结构
        List<CourseStructure> topStructures = courseStructures
                .stream()
                .filter(courseStructure -> StringUtils.isBlank(courseStructure.getParentId()))
                .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                .collect(Collectors.toList());
        //获取课程下正常、公开的资源及资源详情
        if (type == 0) {
            List<CourseResource> courseResources = new ArrayList<>();
            courseStructures.forEach(courseStructure -> {
                courseResources.addAll(courseStructure.getCourseResources()
                        .stream()
                        .filter(courseResource -> courseResource.getIsPublic()
                                && courseResource.getResourceStatus().equals(ResourceStatus.NORMAL))
                        .collect(Collectors.toList()));
            });
            List<String> detailIds = courseResources
                    .stream()
                    .map(CourseResource::getResourceDetailId)
                    .collect(Collectors.toList());
            List<String> resourceIds = courseResources.stream().map(CourseResource::getId).collect(Collectors.toList());
            List<CourseResourceKnowledgePointSqlVo> sqlVoList = new ArrayList<>();
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomain(courseId);
            if (ObjectUtils.isNotEmpty(courseKnowledgeGraphDomain)) {
                sqlVoList = getCourseResourceKnowledgePointSqlVo(courseKnowledgeGraphDomain.getId(), courseResources);

            }
            List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
            courseStructureVos = buildCourseStructureVoList(topStructures, courseStructures, courseResources, courseResourceFiles, sqlVoList);
        } else {
            courseStructureVos = buildCourseStructureVoList(topStructures, courseStructures);
        }
        return Result.success().setData(courseStructureVos);
    }

    public List<CourseResourceKnowledgePointSqlVo> getCourseResourceKnowledgePointSqlVo(String courseKnowledgeGraphDomainId,
                                                                                         List<CourseResource> courseResourceList) {
        List<String> resourceIds = courseResourceList.stream().map(CourseResource::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resourceIds)) {
            return new ArrayList<>();
        }
        String sql = "select resource_id as resourceId, knowledge_point_id as pointId " +
                " from  tb_course_resource_knowledge_point ";
        StringBuilder suffer = new StringBuilder();
        for (String id : resourceIds) {
            suffer.append("\'");
            suffer.append(id);
            suffer.append("\',");
        }
        sql += "where resource_id  in (" + suffer.substring(0, suffer.length() - 1) + ")";

        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseResourceKnowledgePointSqlVo.class));
        List<CourseResourceKnowledgePointSqlVo> courseResourceKnowledgePointSqlVos = query.getResultList();
        List<String> detailIds = courseResourceKnowledgePointSqlVos
                .stream().map(CourseResourceKnowledgePointSqlVo::getPointId).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(detailIds)) {
            return new ArrayList<>();
        }
        List<CourseResourceKnowledgePointSqlVo> sqlVoList = neo4jUtil.getCourseResourceKnowledgePointList(courseKnowledgeGraphDomainId, detailIds);
        courseResourceKnowledgePointSqlVos.forEach(pointSqlVo -> {
            CourseResourceKnowledgePointSqlVo courseResourceKnowledgePointSqlVo = sqlVoList
                    .stream().filter(sqlVo -> sqlVo.getPointId().equals(pointSqlVo.getPointId())).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(courseResourceKnowledgePointSqlVo)) {
                pointSqlVo.setPointContent(courseResourceKnowledgePointSqlVo.getPointContent());
            }
        });
        return courseResourceKnowledgePointSqlVos;
    }

    public List<CourseStructureVo> buildCourseStructureVoList(List<CourseStructure> topCourseStructure, List<CourseStructure> courseStructures,
                                                               List<CourseResource> courseResources,
                                                               List<CourseResourceFile> courseResourceFiles,
                                                               List<CourseResourceKnowledgePointSqlVo> pointSqlVoList) {
        List<CourseStructureVo> courseStructureVos = new ArrayList<>();
        topCourseStructure.forEach(courseStructure -> {
            CourseStructureVo courseStructureVo = new CourseStructureVo();
            courseStructureVo.setStructureId(courseStructure.getId());
            courseStructureVo.setParentId(courseStructure.getParentId() == null ? "" : courseStructure.getParentId());
            courseStructureVo.setStructureName(courseStructure.getCourseStructureName());
            courseStructureVo.setShowOrder(courseStructure.getShowOrder());
            List<CourseStructure> structures = courseStructures
                    .stream()
                    .filter(structure -> courseStructure.getId().equals(structure.getParentId()))
                    .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                    .collect(Collectors.toList());
            courseStructureVo.setChildStructureList(buildCourseStructureVoList(structures, courseStructures, courseResources, courseResourceFiles,
                    pointSqlVoList));
            List<CourseResource> resources = courseResources
                    .stream()
                    .filter(courseResource -> courseResource.getCourseStructure().getId().equals(courseStructure.getId()))
                    .collect(Collectors.toList());
            List<CourseResourceFile> resourceFiles = new ArrayList<>();
            resources.forEach(courseResource -> {
                CourseResourceFile file = courseResourceFiles
                        .stream()
                        .filter(courseResourceFile -> StringUtils.isNotBlank(courseResource.getResourceDetailId()) &&
                                courseResource.getResourceDetailId().equals(courseResourceFile.getId()))
                        .findFirst()
                        .orElse(null);
                if (ObjectUtils.isNotEmpty(file)) {
                    resourceFiles.add(file);
                }
            });
            courseStructureVo.setCourseResourceList(getCourseResourceList(resources, resourceFiles, pointSqlVoList));
            courseStructureVos.add(courseStructureVo);
        });
        return courseStructureVos;
    }

    private List<CourseStructureVo> buildCourseStructureVoList(List<CourseStructure> topCourseStructure, List<CourseStructure> courseStructures) {
        List<CourseStructureVo> courseStructureVos = new ArrayList<>();
        topCourseStructure.forEach(courseStructure -> {
            CourseStructureVo courseStructureVo = new CourseStructureVo();
            courseStructureVo.setStructureId(courseStructure.getId());
            courseStructureVo.setParentId(courseStructure.getParentId() == null ? "" : courseStructure.getParentId());
            courseStructureVo.setStructureName(courseStructure.getCourseStructureName());
            courseStructureVo.setShowOrder(courseStructure.getShowOrder());
            List<CourseStructure> structures = courseStructures
                    .stream()
                    .filter(structure -> courseStructure.getId().equals(structure.getParentId()))
                    .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                    .collect(Collectors.toList());
            courseStructureVo.setChildStructureList(buildCourseStructureVoList(structures, courseStructures));
            courseStructureVo.setCourseResourceList(new ArrayList<>());
            courseStructureVos.add(courseStructureVo);
        });
        return courseStructureVos;
    }

    private List<CourseResourceVo> getCourseResourceList(List<CourseResource> courseResources, List<CourseResourceFile> courseResourceFiles,
                                                         List<CourseResourceKnowledgePointSqlVo> pointSqlVoList) {
        return courseResources.stream().map(courseResource -> new CourseResourceVo() {{
                    this.setResourceId(courseResource.getId());
                    this.setResourceName(courseResource.getResourceName());
                    this.setResourceNum(courseResource.getResourceContentNum());
                    if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) {
                        this.setQuestionNum(courseResource.getResourceContentNum());
                    }
                    this.setResourceDetailId(courseResource.getResourceDetailId());
                    this.setIsPublic(courseResource.getIsPublic() ? 1 : 0);
                    this.setResourceType(courseResource.getResourceType().getIndex());
                    this.setModifierId(courseResource.getModifierId());
                    this.setModifyTime(DateUtils.formatDate(DateUtils.DATE_TIME, courseResource.getModifyTime()));
                    this.setModifierName(courseResource.getModifierName());
                    this.setFileSize(String.valueOf(courseResource.getResourceSize()));
                    this.setResourceReferences(courseResource.getResourceReferences() == null ? 0 :
                            (courseResource.getResourceReferences()));
                    this.setResourceOtherReferences(courseResource.getResourceOtherReferences() == null ? 0 :
                            (courseResource.getResourceOtherReferences()));
                    CourseResourceFile file = courseResourceFiles.stream()
                            .filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                            .findFirst().orElse(null);
                    if (ObjectUtils.isNotEmpty(file)) {
                        AIIdentifyStatus aiIdentifyStatus = file.getAiIdentifyStatus();
                        if (ObjectUtils.isNotEmpty(aiIdentifyStatus)){
                            this.setAiIdentifyStatus(aiIdentifyStatus.getValue());
                        }
                        if (TranscodeStatus.TRANSCODE_SUCCESS.equals(file.getTranscodeStatus())) {
                            this.setInnerIp(file.getTranscodeInnerIp());
                            this.setOuterIp(file.getTranscodeOuterIp());
                            this.setFilePath(file.getTranscodeFilePath());
                            this.setFileType(file.getTranscodeFileType());
                            String sizeStr = "";
                            if (file.getTranscodeFileSize() != null) {
                                double size = file.getTranscodeFileSize() / trillion;
                                if (size <= zeroPointOne) {
                                    sizeStr += "0.1M";
                                } else {
                                    sizeStr += Util.initDecimalFormat(size) + "M";
                                }
                            }
                            this.setResourceSize(sizeStr);
                        } else {
                            this.setInnerIp(file.getInnerIp());
                            this.setOuterIp(file.getOuterIp());
                            this.setFilePath(file.getFilePath());
                            this.setFileType(file.getFileType());
                            String sizeStr = "";
                            if (file.getFileSize() != null) {
                                double size = file.getFileSize() / trillion;
                                if (size <= zeroPointOne) {
                                    sizeStr += "0.1M";
                                } else {
                                    sizeStr += Util.initDecimalFormat(size) + "M";
                                }
                            }
                            this.setResourceSize(sizeStr);
                        }
                        this.setTranscodeStatus(file.getTranscodeStatus().getValue());
                    } else {
                        this.setInnerIp("");
                        this.setOuterIp("");
                        this.setFilePath("");
                        this.setResourceSize("");
                        this.setFileType("");
                    }
                    List<CourseResourceKnowledgePointSqlVo> resourcePointList =
                            pointSqlVoList.stream().filter(p -> courseResource.getId().equals(p.getResourceId())).collect(Collectors.toList());
                    List<KnowledgePointResource> knowledgePointResourceList = new ArrayList<>();
                    if (CollectionUtils.isNotEmpty(resourcePointList)) {
                        resourcePointList.forEach(p -> {
                            KnowledgePointResource knowledgePointResource = new KnowledgePointResource();
                            knowledgePointResource.setPointId(p.getPointId());
                            knowledgePointResource.setPointName(p.getPointContent());
                            knowledgePointResourceList.add(knowledgePointResource);
                        });
                    }
                    this.setKnowledgePointList(knowledgePointResourceList);
                }}).collect(Collectors.toList());
    }

    //region 接口-重命名课程结构
    @Transactional
    public Result updateCourseStructureName(String courseStructureId, String courseStructureName, String userId, String userName) {

        Date now = new Date();
        CourseStructure courseStructure = courseStructureRepository.findByIdAndStructureStatus(courseStructureId, StructureStatus.NORMAL);
        if (ObjectUtils.isEmpty(courseStructure)) {
            return Result.error("保存失败，课程结构已被删除");
        }
        if (Objects.isNull(courseStructure.getCourseVersion()) || !CourseVersionStatus.IN_USE
                .equals(courseStructure.getCourseVersion().getCourseVersionStatus())) {
            return Result.error("无法生成，当前版本已归档，请刷新页面");
        }

        courseStructure.setModifyTime(now);
        courseStructure.setModifierId(userId);
        courseStructure.setModifierName(userName);
        courseStructure.setCourseStructureName(courseStructureName);
        courseStructureRepository.save(courseStructure);
        return Result.success();
    }
    //endregion

    public Result generateCourseStructure(String courseId, String versionId, String userId, String userName) {
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(versionId, CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        List<CourseKnowledgeStructure> structureList = courseKnowledgeStructureRepository.findByCourseIdAndVersionIdWithoutPoint(courseId, versionId);
        List<CourseKnowledgeStructure> topStructureList =
                structureList.stream().filter(s -> StringUtils.isBlank(s.getParentId())).collect(Collectors.toList());
        List<CourseStructure> courseStructureList = buildCourseStructureList(structureList, topStructureList, course, courseVersion, userId,
                userName);
        if (CollectionUtils.isNotEmpty(courseStructureList)) {
            courseStructureRepository.saveAll(courseStructureList);
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_STRUCTURE, true, userId, userName);
        }
        return Result.success();
    }

    private List<CourseStructure> buildCourseStructureList(List<CourseKnowledgeStructure> structureList,
                                                           List<CourseKnowledgeStructure> topStructureList,
                                                           Course course,
                                                           CourseVersion courseVersion,
                                                           String userId,
                                                           String userName) {
        Date now = new Date();
        List<CourseStructure> courseStructureList = new ArrayList<>();
        topStructureList.forEach(s -> {
            CourseStructure courseStructure = setBaseCourseStructureInfo(course, courseVersion, userId, userName, now, s);
            courseStructure.setParentId(null);
            CourseStructure savedCourseStructure = courseStructureRepository.save(courseStructure);
            List<CourseStructure> childrenList = buildChildCourseStructureList(structureList,
                    s.getId(), course, courseVersion, savedCourseStructure);
            courseStructureList.add(savedCourseStructure);
            if (CollectionUtils.isNotEmpty(childrenList)) {
                courseStructureList.addAll(childrenList);
            }
        });
        return courseStructureList;
    }

    private List<CourseStructure> buildChildCourseStructureList(List<CourseKnowledgeStructure> structureList,
                                                                String parentId,
                                                                Course course,
                                                                CourseVersion courseVersion,
                                                                CourseStructure courseStructure) {
        Date now = new Date();
        List<CourseKnowledgeStructure> childCourseList =
                structureList.stream().filter(s -> StringUtils.isNotBlank(s.getParentId()) &&
                        s.getParentId().equals(parentId)).collect(Collectors.toList());
        List<CourseStructure> childList = new ArrayList<>();
        if (CollectionUtils.isEmpty(childCourseList)) {
            return childList;
        }
        childCourseList.forEach(s -> {
            CourseStructure childCourseStructure = setBaseCourseStructureInfo(course, courseVersion, courseStructure.getCreatorId(),
                    courseStructure.getCreatorName(), now, s);
            childCourseStructure.setParentId(courseStructure.getId());

            CourseStructure savedCourseStructure = courseStructureRepository.save(childCourseStructure);
            List<CourseStructure> childCourseStructureList =
                    buildChildCourseStructureList(structureList, savedCourseStructure.getId(),
                            course, courseVersion, savedCourseStructure);
            childList.add(savedCourseStructure);
            if (CollectionUtils.isNotEmpty(childCourseStructureList)) {
                childList.addAll(childCourseStructureList);
            }
        });
        return childList;

    }

    private CourseStructure setBaseCourseStructureInfo(Course course, CourseVersion courseVersion, String userId, String userName, Date now,
                                                       CourseKnowledgeStructure s) {
        CourseStructure courseStructure = new CourseStructure();
        courseStructure.setCourseStructureName(s.getContent());
        courseStructure.setStructureStatus(StructureStatus.NORMAL);
        courseStructure.setShowOrder(s.getShowOrder());
        courseStructure.setCourseResources(new ArrayList<>());
        courseStructure.setCourse(course);
        courseStructure.setCourseVersion(courseVersion);
        courseStructure.setModifierId(userId);
        courseStructure.setModifierName(userName);
        courseStructure.setModifyTime(now);
        courseStructure.setCreatorId(userId);
        courseStructure.setCreatorName(userName);
        courseStructure.setCreateTime(now);
        return courseStructure;
    }

}

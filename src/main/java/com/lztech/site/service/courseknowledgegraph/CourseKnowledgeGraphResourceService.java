package com.lztech.site.service.courseknowledgegraph;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.courseknowledgestructure.CourseKnowledgeStructureRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.service.courseconstruction.CourseStructureService;
import com.lztech.site.service.courseconstruction.CourseVersionService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.until.Util;
import com.lztech.site.viewmodel.courseknowledgegraph.KnowledgePointCourseResourceVo;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceKnowledgePointSqlVo;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceVo;
import com.lztech.site.viewmodel.coursemanagement.CourseStructureVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseKnowledgeGraphResourceService {
    private static double zeroPointOne = 0.1;
    private static double trillion = 1048576.0;

    @Autowired
    private CourseResourceKnowledgePointRepository courseResourceKnowledgePointRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseVersionService courseVersionService;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;

    @Autowired
    private CourseKnowledgeStructureRepository courseKnowledgeStructureRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;
    @Autowired
    private CourseStructureService courseStructureService;
    @Autowired
    private Neo4jUtil neo4jUtil;
    @Value("${coursewareFileTypes}")
    private String coursewareFileTypes;


    public Result addKnowledgePointCourseResource(KnowledgePointCourseResourceVo resourcePointVo) {
        List<CourseResourceKnowledgePoint> resultList = new ArrayList<>();
        List<CourseResourceKnowledgePoint> knowledgePointList =
                courseResourceKnowledgePointRepository.findByKnowledgePointId(resourcePointVo.getKnowledgePointId());
        List<String> resouceIdList = resourcePointVo.getCourseResourceIdList().stream().distinct().collect(Collectors.toList());
        resouceIdList.forEach(resourceId -> {
            if (StringUtils.isBlank(resourceId)) {
                return;
            }
            CourseResourceKnowledgePoint courseResourceKnowledgePoint = knowledgePointList
                    .stream()
                    .filter(s -> s.getResourceId().equals(resourceId))
                    .findFirst()
                    .orElse(null);


            if (courseResourceKnowledgePoint != null) {
                return;
            }
            Date now = new Date();
            courseResourceKnowledgePoint = new CourseResourceKnowledgePoint();
            courseResourceKnowledgePoint.setKnowledgePointId(resourcePointVo.getKnowledgePointId());
            courseResourceKnowledgePoint.setResourceId(resourceId);
            courseResourceKnowledgePoint.setModifierId(resourcePointVo.getUserId());
            courseResourceKnowledgePoint.setModifierName(resourcePointVo.getUserName());
            courseResourceKnowledgePoint.setModifyTime(now);
            courseResourceKnowledgePoint.setCreatorId(resourcePointVo.getUserId());
            courseResourceKnowledgePoint.setCreatorName(resourcePointVo.getUserName());
            courseResourceKnowledgePoint.setCreateTime(now);
            courseResourceKnowledgePoint.setCourseKnowledgeGraphDomainId(resourcePointVo.getCourseKnowledgeGraphId());
            resultList.add(courseResourceKnowledgePoint);
        });
        courseResourceKnowledgePointRepository.saveAll(resultList);

        return Result.success();
    }

    public List<CourseResourceVo> getCourseKnowledgePointCourseResourceList(String knowledgePointId) {
        List<CourseResourceKnowledgePoint> courseResourceKnowledgePointList =
                courseResourceKnowledgePointRepository.findByKnowledgePointId(knowledgePointId);
        List<String> resourceIdList =
                courseResourceKnowledgePointList.stream().map(CourseResourceKnowledgePoint::getResourceId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resourceIdList)) {
            return Collections.emptyList();
        }
        List<CourseResource> courseResources = courseResourceRepository.findByIdIn(resourceIdList);
        courseResources = courseResources
                .stream()
                .filter(courseResource ->
                        courseResource.getResourceStatus().equals(ResourceStatus.NORMAL))
                .sorted(Comparator.comparing(
                        CourseResource::getAuxiliarySort,
                        (a, b) -> {
                            int intA = a != null ? a : 0;
                            int intB = b != null ? b : 0;
                            return Integer.compare(intB, intA);
                        }
                ))
                .collect(Collectors.toList());
        List<String> detailIds = courseResources
                .stream()
                .map(CourseResource::getResourceDetailId)
                .collect(Collectors.toList());
        List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
        return buildCourseResourceList(courseResources, courseResourceFiles);
    }

    private List<CourseResourceVo> buildCourseResourceList(List<CourseResource> courseResources,
                                                           List<CourseResourceFile> courseResourceFiles) {
        List<CourseResourceVo> courseResourceVos = new ArrayList<>();
        for (CourseResource courseResource : courseResources) {
            CourseResourceVo courseResourceVo = new CourseResourceVo();
            courseResourceVo.setResourceId(courseResource.getId());
            courseResourceVo.setResourceName(courseResource.getResourceName());
            if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) {
                courseResourceVo.setQuestionNum(courseResource.getResourceContentNum());
            }
            courseResourceVo.setResourceDetailId(courseResource.getResourceDetailId());
            courseResourceVo.setIsPublic(courseResource.getIsPublic() ? 1 : 0);
            courseResourceVo.setResourceType(courseResource.getResourceType().getIndex());
            courseResourceVo.setModifierId(courseResource.getModifierId());
            courseResourceVo.setModifyTime(DateUtils.formatDate(DateUtils.DATE_TIME, courseResource.getModifyTime()));
            courseResourceVo.setModifierName(courseResource.getModifierName());
            courseResourceVo.setFileSize(String.valueOf(courseResource.getResourceSize()));
            courseResourceVo.setResourceReferences(courseResource.getResourceReferences() == null ? 0 :
                    (courseResource.getResourceReferences()));
            courseResourceVo.setResourceOtherReferences(courseResource.getResourceOtherReferences() == null ? 0 :
                    (courseResource.getResourceOtherReferences()));

            if (StringUtils.isNotEmpty(courseResource.getCitedUserId())) {
                courseResourceVo.setInitialCreatorId(courseResource.getCitedUserId());
                courseResourceVo.setInitialCreatorName(courseResource.getCitedUserName());
            } else {
                courseResourceVo.setInitialCreatorId(courseResource.getCreatorId());
                courseResourceVo.setInitialCreatorName(courseResource.getCreatorName());
            }
            CourseResourceFile file = courseResourceFiles
                    .stream()
                    .filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                    .findFirst()
                    .orElse(null);
            if (ObjectUtils.isNotEmpty(file)) {
                if (TranscodeStatus.TRANSCODE_SUCCESS.equals(file.getTranscodeStatus())) {
                    courseResourceVo.setInnerIp(file.getTranscodeInnerIp());
                    courseResourceVo.setOuterIp(file.getTranscodeOuterIp());
                    courseResourceVo.setFilePath(file.getTranscodeFilePath());
                    courseResourceVo.setFileType(file.getTranscodeFileType());
                    String sizeStr = "";
                    if (file.getTranscodeFileSize() != null) {
                        double size = file.getTranscodeFileSize() / trillion;
                        if (size <= zeroPointOne) {
                            sizeStr += "0.1M";
                        } else {
                            sizeStr += Util.initDecimalFormat(size) + "M";
                        }
                    }
                    courseResourceVo.setResourceSize(sizeStr);
                } else {
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
                }
                courseResourceVo.setTranscodeStatus(file.getTranscodeStatus().getValue());
            } else {
                courseResourceVo.setInnerIp("");
                courseResourceVo.setOuterIp("");
                courseResourceVo.setFilePath("");
                courseResourceVo.setResourceSize("");
                courseResourceVo.setFileType("");
            }
            courseResourceVos.add(courseResourceVo);
        }
        return courseResourceVos;
    }

    public Integer cancelCourseKnowledgePointCourseResource(String knowledgePointId, String resourceId) {
        return courseResourceKnowledgePointRepository.deleteByKnowledgePointIdAndResourceId(knowledgePointId, resourceId);
    }

    public Result getCoursesContent(String courseId, String versionId, String needUserId,Integer coursewareStatus) {
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
                (courseId, org.apache.commons.collections.CollectionUtils.isNotEmpty(courseVersionList) ? courseVersionList.get(0).getId() :
                        versionId, StructureStatus.NORMAL);


        //获取最上层课程结构
        List<CourseStructure> topStructures = courseStructures
                .stream()
                .filter(courseStructure -> StringUtils.isBlank(courseStructure.getParentId()))
                .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                .collect(Collectors.toList());
        List<CourseResource> courseResources = new ArrayList<>();
        for (CourseStructure courseStructure : courseStructures) {
            courseResources.addAll(courseStructure.getCourseResources()
                    .stream()
                    .filter(cr ->ResourceStatus.NORMAL.equals(cr.getResourceStatus())
                            &&  ResourceSourceType.USER_ADDED.equals(cr.getSourceType())
                            && (cr.getIsPublic() || cr.getCreatorId().equals(needUserId)))
                    .collect(Collectors.toList()));
        }

        courseResources = courseResources.stream().sorted(Comparator.comparing(CourseResource::getAuxiliarySort, (a, b) -> {
                    int intA = a != null ? a : 0;
                    int intB = b != null ? b : 0;
                    return Integer.compare(intB, intA);
                }))
                .collect(Collectors.toList());

        List<String> detailIds = courseResources
                .stream()
                .map(CourseResource::getResourceDetailId)
                .collect(Collectors.toList());
        List<CourseResourceKnowledgePointSqlVo> sqlVoList = new ArrayList<>();
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                courseKnowledgeGraphService.getCourseKnowledgeGraphDomain(courseId);
        if (ObjectUtils.isNotEmpty(courseKnowledgeGraphDomain)) {
            sqlVoList = courseStructureService.getCourseResourceKnowledgePointSqlVo(courseKnowledgeGraphDomain.getId(), courseResources);

        }
        List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
        courseStructureVos = courseStructureService.buildCourseStructureVoList(topStructures, courseStructures, courseResources,
                courseResourceFiles, sqlVoList);
        if (coursewareStatus != null && coursewareStatus == 1){
            handleCourseStructureCourseResourceList(courseStructureVos);
        }
        return Result.success().setData(courseStructureVos);
    }
    public void handleCourseStructureCourseResourceList(List<CourseStructureVo> courseStructureVos){
        List<String> coursewareFileTypeList = Arrays.asList(coursewareFileTypes.split(","));
        coursewareFileTypeList = coursewareFileTypeList.stream().map(String::toLowerCase).collect(Collectors.toList());
        for (CourseStructureVo courseStructureVo : courseStructureVos) {
            List<CourseResourceVo> courseResourceList = courseStructureVo.getCourseResourceList();
            List<String> finalCoursewareFileTypeList = coursewareFileTypeList;
            List<CourseResourceVo> effectiveCourseResourceList =
                    courseResourceList.stream().filter( courseResourceVo ->
                            ObjectUtils.isNotEmpty(courseResourceVo.getFileType()) &&
                                    finalCoursewareFileTypeList.contains(courseResourceVo.getFileType().toLowerCase()))
                            .collect(Collectors.toList());
            courseStructureVo.setCourseResourceList(effectiveCourseResourceList);
            List<CourseStructureVo> childStructureList =  courseStructureVo.getChildStructureList();
            if (CollectionUtils.isNotEmpty(childStructureList)){
                handleCourseStructureCourseResourceList(childStructureList);
            }
        }
    }
}

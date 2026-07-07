package com.lztech.site.service.courseconstruction;

import cn.hutool.core.bean.BeanUtil;
import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.KnowledgeStructureType;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.courseknowledgestructure.CourseKnowledgeStructureRepository;
import com.lztech.persistence.repositories.coursetheme.CourseThemeDetailRepository;
import com.lztech.persistence.repositories.coursetheme.CourseThemeRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.courseconstruction.CourseVersionResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.ONE;

@Service
public class CourseVersionService {

    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private CourseIntroductionRepository courseIntroductionRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseTeachingTeamService courseTeachingTeamService;
    @Autowired
    private CourseCompletionRepository courseCompletionRepository;
    @Autowired
    private CourseKnowledgeStructureRepository courseKnowledgeStructureRepository;
    @Autowired
    private CourseChapterGoalRepository courseChapterGoalRepository;
    @Autowired
    private CourseResourceKnowledgePointRepository courseResourceKnowledgePointRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Autowired
    private CourseThemeRepository courseThemeRepository;
    @Autowired
    private CourseThemeDetailRepository courseThemeDetailRepository;

    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private CourseKnowledgeStructureService courseKnowledgeStructureService;

    @Autowired
    private CourseService courseService;

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;

    public List<CourseVersionResource> getCourseVersion(String id, Integer versionStatus) {
        Course course = courseRepository.findByIdAndUseState(id, true);
        if (Objects.isNull(course)) {
            return new ArrayList<>();
        }
        List<CourseVersion> courseVersionList = new ArrayList<>();

        if (Objects.isNull(versionStatus)) {
            courseVersionList = courseVersionRepository.findAll();
        } else {
            courseVersionList = courseVersionRepository.findByCourseIdAndVersionStatus(id, versionStatus);
        }
        List<CourseVersionResource> resourceList = new ArrayList<>();
        if (Objects.nonNull(versionStatus) && versionStatus == CourseVersionStatus.IN_USE.getValue() && CollectionUtils.isEmpty(courseVersionList)) {
            CourseVersion courseVersion = generateAndUpdateCourseVersion(course);
            courseVersionList.add(courseVersion);
        }
        List<CourseVersionResource> finalResourceList = resourceList;
        courseVersionList.forEach(version -> {
            CourseVersionResource courseVersionResource = new CourseVersionResource();
            courseVersionResource.setVersionId(version.getId());
            courseVersionResource.setVersionNumber("版本" + version.getVersionSerialNumber());
            courseVersionResource.setFilingTime(Objects.isNull(version.getFilingTime()) ? null : DateUtils.formatDate(DateUtils.DATE_TIME,
                    version.getFilingTime()));
            courseVersionResource.setFilingUserId(version.getFilingUserId());
            courseVersionResource.setFilingUserNo(version.getFilingUserNo());
            courseVersionResource.setFilingUserName(version.getFilingUserName());
            courseVersionResource.setVersionCompletion(Objects.isNull(version.getVersionCompletion()) ? null :
                    BigDecimal.valueOf(version.getVersionCompletion()));
            courseVersionResource.setSortTime(Objects.isNull(version.getFilingTime()) ? DateUtils.formatDate(DateUtils.DATE_TIME, new Date()) :
                    DateUtils.formatDate(DateUtils.DATE_TIME, version.getFilingTime()));
            finalResourceList.add(courseVersionResource);
        });

        resourceList = finalResourceList.stream().sorted(Comparator.comparing(
                CourseVersionResource::getSortTime).reversed()).collect(Collectors.toList());
        return resourceList;
    }

    public synchronized CourseVersion generateAndUpdateCourseVersion(Course course) {
        List<CourseVersion> courseVersionList = courseVersionRepository.findByCourseIdAndVersionStatus(course.getId(),
                CourseVersionStatus.IN_USE.getValue());
        if (CollectionUtils.isNotEmpty(courseVersionList)) {
            return courseVersionList.get(0);
        }
        CourseVersion courseVersion = new CourseVersion();
        courseVersion.setVersionSerialNumber(ONE);
        courseVersion.setCourseVersionStatus(CourseVersionStatus.IN_USE);
        courseVersion.setCourse(course);
        courseVersion.setCreateTime(new Date());
        courseVersion.setCreatorId(course.getCreatorId());
        courseVersion.setCreatorName(course.getCreatorName());
        CourseVersion savedCourseVersion = courseVersionRepository.save(courseVersion);
        courseMaterialRepository.updateCourseVersion(course.getId(), savedCourseVersion.getId());
        courseIntroductionRepository.updateCourseVersion(course.getId(), savedCourseVersion.getId());
        courseStructureRepository.updateCourseVersion(course.getId(), savedCourseVersion.getId());
        courseTeachingTeamRepository.updateCourseVersion(course.getId(), savedCourseVersion.getId());
        courseCompletionRepository.updateCourseVersion(course.getId(), savedCourseVersion.getId());
        return savedCourseVersion;
    }

    public synchronized void saveCourseVersion(String courseId, CourseVersionResource courseVersionResource) {
        List<CourseVersion> courseVersions = courseVersionRepository.findByCourseIdAndCourseVersionStatusIsNot(
                courseId, CourseVersionStatus.DELETE);
        List<Integer> nowVisionNumberList = courseVersions.stream()
                .map(CourseVersion::getVersionSerialNumber)
                .distinct()
                .collect(Collectors.toList());
        int maxNowVisionNumber = nowVisionNumberList.stream().max(Integer::compareTo).orElse(0);
        Date nowDate = new Date();
        final Optional<Course> optionalCourse = courseRepository.findById(courseId);
        List<CourseVersion> inUseCourseVersions = courseVersionRepository.findByCourseIdAndVersionStatus(
                optionalCourse.orElse(null).getId(), CourseVersionStatus.IN_USE.getValue());
        CourseVersion inUseCourseVersion = null;
        if (CollectionUtils.isNotEmpty(inUseCourseVersions)) {
            inUseCourseVersion = inUseCourseVersions.get(0);
            inUseCourseVersion.setVersionSerialNumber(maxNowVisionNumber);
            inUseCourseVersion.setCourseVersionStatus(CourseVersionStatus.ARCHIVED);
            inUseCourseVersion.setFilingTime(nowDate);
            inUseCourseVersion.setFilingUserId(courseVersionResource.getFilingUserId());
            inUseCourseVersion.setFilingUserNo(courseVersionResource.getFilingUserNo());
            inUseCourseVersion.setFilingUserName(courseVersionResource.getFilingUserName());
            inUseCourseVersion.setVersionCompletion(courseVersionResource.getVersionCompletion().doubleValue());
            inUseCourseVersion.setModifierId(courseVersionResource.getFilingUserId());
            inUseCourseVersion.setModifierName(courseVersionResource.getFilingUserName());
            inUseCourseVersion.setModifyTime(new Date());
            inUseCourseVersion = courseVersionRepository.save(inUseCourseVersion);
            if (dataVisualEnable) {
                courseCompletionService.createCourseVersionEvent(inUseCourseVersion);
            }
        }
        /**版本**/
        CourseVersion courseVersion = new CourseVersion();
        CourseVersion saveCourseVersion = saveCourseVersion(courseVersionResource, ++maxNowVisionNumber, nowDate, courseVersion, optionalCourse);
        /**全新版本**/
        if (dataVisualEnable) {
            courseCompletionService.createCourseVersionEvent(saveCourseVersion);
        }
        /**课程完成度**/
        List<CourseCompletion> courseCompletionList = courseCompletionRepository.findByCourseIdList(Collections.singletonList(courseId));
        updateCourseCompletion(courseCompletionList, saveCourseVersion);
        /**基本信息**/
        CourseMaterial courseMaterialByCourseId = courseMaterialRepository.findByCourseIdAndVersionId(courseId, courseVersionResource.getVersionId());
        CourseMaterial saveCourseMaterial = new CourseMaterial();
        BeanUtil.copyProperties(courseMaterialByCourseId, saveCourseMaterial, "id", "courseVersion");
        saveCourseMaterial.setCourseVersion(saveCourseVersion);
        courseMaterialRepository.save(saveCourseMaterial);
        /**课程介绍表**/
        List<CourseIntroduction> courseIntroductionList =
                courseIntroductionRepository.findByCourseIdAndCourseVersionId(courseId, courseVersionResource.getVersionId());
        List<CourseIntroduction> saveCourseIntroductionList = new ArrayList<>();
        courseIntroductionList.forEach(courseIntroduction -> {
            CourseIntroduction saveCourseIntroduction = new CourseIntroduction();
            BeanUtil.copyProperties(courseIntroduction, saveCourseIntroduction, "id", "courseVersion");
            saveCourseIntroduction.setCourseVersion(saveCourseVersion);
            saveCourseIntroductionList.add(saveCourseIntroduction);
        });
        courseIntroductionRepository.saveAll(saveCourseIntroductionList);
        /**教师团队**/
        List<CourseTeachingTeam> courseTeachingTeamList =
                courseTeachingTeamRepository.findByCourseIdAndCourseVersionId(courseId, courseVersionResource.getVersionId());
        List<CourseTeachingTeam> saveCourseTeachingTeamList = new ArrayList<>();
        courseTeachingTeamList.forEach(courseTeachingTeam -> {
            CourseTeachingTeam saveCourseTeachingTeam = new CourseTeachingTeam();
            BeanUtil.copyProperties(courseTeachingTeam, saveCourseTeachingTeam, "id", "courseVersion");
            saveCourseTeachingTeam.setModifierId(courseVersionResource.getFilingUserId());
            saveCourseTeachingTeam.setModifierName(courseVersionResource.getFilingUserName());
            saveCourseTeachingTeam.setModifyTime(nowDate);
            saveCourseTeachingTeam.setCourseVersion(saveCourseVersion);
            saveCourseTeachingTeamList.add(saveCourseTeachingTeam);
        });
        List<CourseTeachingTeam> finalSaveCourseTeachingTeamList = courseTeachingTeamRepository.saveAll(saveCourseTeachingTeamList);
        /**课程知识点**/
        Map map = saveCourseKnowledgeStructure(courseId, courseVersionResource, courseVersion);
        /**课程结构和资源**/
        saveCourseStructure(courseId, courseVersionResource, saveCourseVersion, inUseCourseVersion, map);
        /**归档：教师团队重新推送**/
        if (dataVisualEnable) {
            courseTeachingTeamService.sendCourseTeachingTeamList(finalSaveCourseTeachingTeamList);
        }
    }

    private void updateCourseCompletion(List<CourseCompletion> courseCompletionList, CourseVersion saveCourseVersion) {
        courseCompletionList.forEach(courseCompletion -> courseCompletion.setCourseVersion(saveCourseVersion));
        courseCompletionRepository.saveAll(courseCompletionList);
    }


    private void saveCourseStructure(String courseId, CourseVersionResource courseVersionResource, CourseVersion saveCourseVersion,
                                     CourseVersion inUseCourseVersion,
                                     Map<String, String> map) {
        List<CourseResource> courseResourceList = new ArrayList<>();
        List<CourseStructure> courseStructureList =
                courseStructureRepository.findByCourseIdAndCourseVersionId(courseId, courseVersionResource.getVersionId());
        List<CourseStructure> parentCourseStructures = courseStructureList
                .stream()
                .filter(filter -> filter.getParentId() == null)
                .collect(Collectors.toList());
        parentCourseStructures.forEach(parentCourseStructure -> {
            CourseStructure structure = new CourseStructure();
            List<CourseResource> courseResources = parentCourseStructure.getCourseResources();
            BeanUtil.copyProperties(parentCourseStructure, structure, "id", "courseVersion", "courseResources");
            structure.setCourseVersion(inUseCourseVersion);
            structure.setModifierId(courseVersionResource.getFilingUserId());
            structure.setModifierName(courseVersionResource.getFilingUserName());
            structure.setModifyTime(new Date());
            CourseStructure saveCourseStructure = courseStructureRepository.save(structure);
            List<CourseChapterGoal> courseChapterGoals =
                    courseChapterGoalRepository.findByCourseStructureId(parentCourseStructure.getId());
            List<CourseChapterGoal> saveCourseChapterGoals = new ArrayList<>();
            courseChapterGoals.forEach(courseChapterGoal -> {
                CourseChapterGoal chapterGoal = new CourseChapterGoal();
                BeanUtil.copyProperties(courseChapterGoal, chapterGoal, "id", "courseStructure");
                saveCourseChapterGoals.add(chapterGoal);
            });
            saveCourseChapterGoals.forEach(courseChapterGoal -> courseChapterGoal.setCourseStructure(saveCourseStructure));
            courseChapterGoalRepository.saveAll(saveCourseChapterGoals);
            courseResources.forEach(courseResource -> {
                CourseResource resource = new CourseResource();
                BeanUtil.copyProperties(courseResource, resource, "id", "courseStructure");
                saveCourseResourceDetail(courseResource, resource);
                resource.setCourseStructure(saveCourseStructure);
                resource.setModifierId(courseVersionResource.getFilingUserId());
                resource.setModifierName(courseVersionResource.getFilingUserName());
                resource.setModifyTime(new Date());
                CourseResource saveCourseResource = courseResourceRepository.save(resource);
                courseResourceList.add(saveCourseResource);
                List<CourseResourceKnowledgePoint> courseResourceKnowledgePoints =
                        courseResourceKnowledgePointRepository.findByResourceId(courseResource.getId());
                List<CourseResourceKnowledgePoint> saveCourseResourceKnowledgePoints = new ArrayList<>();
                courseResourceKnowledgePoints.forEach(courseResourceKnowledgePoint -> {
                    CourseResourceKnowledgePoint resourceKnowledgePoint = new CourseResourceKnowledgePoint();
                    String knowledgePointId = map.get(courseResourceKnowledgePoint.getKnowledgePointId());
                    BeanUtil.copyProperties(courseResourceKnowledgePoint, resourceKnowledgePoint, "id", "resourceId", "knowledgePointId");
                    resourceKnowledgePoint.setKnowledgePointId(knowledgePointId);
                    resourceKnowledgePoint.setResourceId(saveCourseResource.getId());
                    saveCourseResourceKnowledgePoints.add(resourceKnowledgePoint);
                });
                courseResourceKnowledgePointRepository.saveAll(saveCourseResourceKnowledgePoints);
            });
            List<CourseStructure> subsetCourseStructures = courseStructureList
                    .stream()
                    .filter(filter -> parentCourseStructure.getId().equals(filter.getParentId()))
                    .collect(Collectors.toList());
            subsetCourseStructures.forEach(subsetCourseStructure -> {
                CourseStructure courseStructure = new CourseStructure();
                List<CourseResource> subsetCourseResources = subsetCourseStructure.getCourseResources();
                BeanUtil.copyProperties(subsetCourseStructure, courseStructure, "id", "courseVersion", "courseResources");
                courseStructure.setCourseVersion(inUseCourseVersion);
                courseStructure.setParentId(saveCourseStructure.getId());
                CourseStructure saveSubsetCourseStructure = courseStructureRepository.save(courseStructure);
                saveSubsetCourseResources(map, subsetCourseResources, saveSubsetCourseStructure);
            });
        });
        courseStructureList.forEach(courseStructure -> courseStructure.setCourseVersion(saveCourseVersion));
        List<CourseStructure> courseStructures = courseStructureRepository.saveAll(courseStructureList);
        /**推送课程结构相关**/
        courseStructures.forEach(courseStructure -> {
            List<CourseResource> courseResources = courseStructure.getCourseResources();
            if (CollectionUtils.isNotEmpty(courseStructures)) {
                courseResources.forEach(courseResource -> {
                    if (dataVisualEnable) {
                        courseService.sendCourseResourceEvent(courseResource);
                    }
                });
            }
        });
    }

    private void saveCourseResourceDetail(CourseResource courseResource, CourseResource resource) {
        if (StringUtils.isNotEmpty(courseResource.getResourceDetailId())) {
            Optional<CourseResourceFile> optionalCourseResourceFile =
                    courseResourceFileRepository.findById(courseResource.getResourceDetailId());
            if (optionalCourseResourceFile.isPresent()) {
                CourseResourceFile courseResourceFile = optionalCourseResourceFile.get();
                CourseResourceFile saveCourseResourceFile = new CourseResourceFile();
                BeanUtil.copyProperties(courseResourceFile, saveCourseResourceFile, "id");
                CourseResourceFile resourceFile = courseResourceFileRepository.save(saveCourseResourceFile);
                resource.setResourceDetailId(resourceFile.getId());
            }
            CourseTheme courseTheme =
                    courseThemeRepository.findByIdAndThemeStatus(courseResource.getResourceDetailId(), ResourceStatus.NORMAL);
            if (ObjectUtils.isNotEmpty(courseTheme)) {
                List<CourseThemeDetail> courseThemeDetailList = courseTheme.getCourseThemeDetailList();
                List<CourseThemeDetail> saveCourseThemeDetail = new ArrayList<>();
                courseThemeDetailList.forEach(courseThemeDetail -> {
                    CourseThemeDetail detail = new CourseThemeDetail();
                    BeanUtil.copyProperties(courseThemeDetail, detail, "id", "courseTheme");
                    saveCourseThemeDetail.add(detail);
                });
                CourseTheme toCourseTheme = new CourseTheme();
                BeanUtil.copyProperties(courseTheme, toCourseTheme, "id");
                toCourseTheme.setCourseThemeDetailList(saveCourseThemeDetail);
                CourseTheme saveCourseTheme = courseThemeRepository.save(toCourseTheme);
                System.out.println(saveCourseTheme);
                saveCourseThemeDetail.forEach(courseThemeDetail -> {
                    courseThemeDetail.setCourseTheme(saveCourseTheme);
                });
                courseThemeDetailRepository.saveAll(saveCourseThemeDetail);
                resource.setResourceDetailId(saveCourseTheme.getId());
            }
        }
    }

    private void saveSubsetCourseResources(Map<String, String> map, List<CourseResource> subsetCourseResources,
                                           CourseStructure saveSubsetCourseStructure) {
        subsetCourseResources.forEach(courseResource -> {
            CourseResource resource = new CourseResource();
            Optional<CourseResourceFile> optionalCourseResourceFile = courseResourceFileRepository.findById(courseResource.getId());
            if (optionalCourseResourceFile.isPresent()) {
                CourseResourceFile courseResourceFile = optionalCourseResourceFile.get();
                CourseResourceFile saveCourseResourceFile = new CourseResourceFile();
                BeanUtil.copyProperties(courseResourceFile, saveCourseResourceFile, "id");
                CourseResourceFile resourceFile = courseResourceFileRepository.save(saveCourseResourceFile);
                resource.setResourceDetailId(resourceFile.getId());
            }
            BeanUtil.copyProperties(courseResource, resource, "id", "courseStructure");
            resource.setCourseStructure(saveSubsetCourseStructure);
            CourseResource saveCourseResource = courseResourceRepository.save(resource);
            List<CourseResourceKnowledgePoint> courseResourceKnowledgePoints =
                    courseResourceKnowledgePointRepository.findByResourceId(courseResource.getId());
            List<CourseResourceKnowledgePoint> saveCourseResourceKnowledgePoints = new ArrayList<>();
            courseResourceKnowledgePoints.forEach(courseResourceKnowledgePoint -> {
                CourseResourceKnowledgePoint resourceKnowledgePoint = new CourseResourceKnowledgePoint();
                String knowledgePointId = map.get(courseResourceKnowledgePoint.getKnowledgePointId());
                BeanUtil.copyProperties(courseResourceKnowledgePoint, resourceKnowledgePoint, "id", "resourceId");
                resourceKnowledgePoint.setResourceId(saveCourseResource.getId());
                resourceKnowledgePoint.setKnowledgePointId(knowledgePointId);
                saveCourseResourceKnowledgePoints.add(resourceKnowledgePoint);
            });
            courseResourceKnowledgePointRepository.saveAll(saveCourseResourceKnowledgePoints);
        });
    }

    private Map saveCourseKnowledgeStructure(String courseId, CourseVersionResource courseVersionResource, CourseVersion courseVersion) {
        List<CourseKnowledgeStructure> courseKnowledgeStructureList =
                courseKnowledgeStructureRepository.findByCourseIdAndVersionId(courseId, courseVersionResource.getVersionId());

        List<CourseKnowledgeStructure> chapterList = courseKnowledgeStructureList
                .stream()
                .filter(f -> f.getParentId() == null || f.getParentId().equals(""))
                .collect(Collectors.toList());
        Map<String, String> idsMap = new HashMap<>();
        chapterList.forEach(chapterCourseKnowledgeStructure -> {
            CourseKnowledgeStructure newCourseKnowledgeStructure = new CourseKnowledgeStructure();
            BeanUtil.copyProperties(chapterCourseKnowledgeStructure, newCourseKnowledgeStructure, "id", "courseVersion");
            newCourseKnowledgeStructure.setCourseVersion(courseVersion);

            List<CourseKnowledgeStructure> chapterSubset = courseKnowledgeStructureList
                    .stream()
                    .filter(f -> chapterCourseKnowledgeStructure.getId().equals(f.getParentId()))
                    .collect(Collectors.toList());
            //保存新章节
            CourseKnowledgeStructure saveNewCourseKnowledgeStructure = courseKnowledgeStructureRepository.saveAndFlush(newCourseKnowledgeStructure);
            //处理章 下的所有子集
            chapterSubset.forEach(courseKnowledgeStructure -> {
                CourseKnowledgeStructure knowledgeStructure = new CourseKnowledgeStructure();
                BeanUtil.copyProperties(courseKnowledgeStructure, knowledgeStructure, "id", "parentId", "courseVersion");
                knowledgeStructure.setCourseVersion(courseVersion);
                knowledgeStructure.setParentId(saveNewCourseKnowledgeStructure.getId());
                CourseKnowledgeStructure structure = courseKnowledgeStructureRepository.saveAndFlush(knowledgeStructure);

                if (courseKnowledgeStructure.getKnowledgeStructureType() == KnowledgeStructureType.KNOWLEDGE_POINT) {
                    idsMap.put(courseKnowledgeStructure.getId(), structure.getId());
                    if (dataVisualEnable) {
                        courseKnowledgeStructureService.sendKnowledgeStructureTopicVo(structure, saveNewCourseKnowledgeStructure);
                    }
                }
                List<CourseKnowledgeStructure> knowledgePointCourseKnowledgeStructureList = courseKnowledgeStructureList
                        .stream()
                        .filter(f -> courseKnowledgeStructure.getId().equals(f.getParentId()))
                        .collect(Collectors.toList());
                knowledgePointCourseKnowledgeStructureList.forEach(knowledgePointCourseKnowledgeStructure -> {
                    CourseKnowledgeStructure toSaveCourseKnowledgeStructure = new CourseKnowledgeStructure();
                    BeanUtil.copyProperties(knowledgePointCourseKnowledgeStructure,
                            toSaveCourseKnowledgeStructure, "id", "parentId", "courseVersion");
                    toSaveCourseKnowledgeStructure.setParentId(structure.getId());
                    toSaveCourseKnowledgeStructure.setCourseVersion(courseVersion);
                    CourseKnowledgeStructure save = courseKnowledgeStructureRepository.saveAndFlush(toSaveCourseKnowledgeStructure);
                    if (knowledgePointCourseKnowledgeStructure.getKnowledgeStructureType() == KnowledgeStructureType.KNOWLEDGE_POINT) {
                        idsMap.put(knowledgePointCourseKnowledgeStructure.getId(), save.getId());
                        if (dataVisualEnable) {
                            courseKnowledgeStructureService.sendKnowledgeStructureTopicVo(save, structure);
                        }
                    }
                });
            });
        });
        return idsMap;
    }

    private CourseVersion saveCourseVersion(CourseVersionResource courseVersionResource, int maxNowVisionNumber, Date nowDate,
                                            CourseVersion courseVersion, Optional<Course> optionalCourse) {
        courseVersion.setCourse(optionalCourse.orElse(null));
        courseVersion.setCreateTime(nowDate);
        courseVersion.setCreatorId(courseVersionResource.getFilingUserId());
        courseVersion.setCreatorName(courseVersionResource.getFilingUserName());
        courseVersion.setModifierId(courseVersionResource.getFilingUserId());
        courseVersion.setModifierName(courseVersionResource.getFilingUserName());
        courseVersion.setModifyTime(nowDate);
        courseVersion.setCourseVersionStatus(CourseVersionStatus.IN_USE);
        courseVersion.setVersionSerialNumber(maxNowVisionNumber);
        CourseVersion saveCourseVersion = courseVersionRepository.save(courseVersion);
        return saveCourseVersion;
    }
}

package com.lztech.site.service.coursechaptergoal;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseChapterGoal;
import com.lztech.domain.model.course.CourseStructure;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.CourseChapterGoalType;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.StructureStatus;
import com.lztech.persistence.repositories.course.CourseChapterGoalRepository;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.CourseStructureRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.viewmodel.coursechaptergoal.CourseChapterGoalResource;
import com.lztech.site.viewmodel.coursechaptergoal.CourseChapterGoalVo;
import com.lztech.site.viewmodel.coursechaptergoal.CourseStructureBaseResource;
import com.lztech.site.viewmodel.courseconstruction.CourseChapterGoalTypeResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseChapterGoalService {
    @Autowired
    private CourseChapterGoalRepository courseChapterGoalRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;


    public List<CourseChapterGoalTypeResource> getCourseChapterGoalType() {
        List<CourseChapterGoalTypeResource> resourceList = new ArrayList<>();
        CourseChapterGoalType[] courseChapterGoalTypeList = CourseChapterGoalType.values();

        for (CourseChapterGoalType courseChapterGoalType : courseChapterGoalTypeList) {
            CourseChapterGoalTypeResource courseChapterGoalTypeResource = new CourseChapterGoalTypeResource();
            courseChapterGoalTypeResource.setTypeName(courseChapterGoalType.getName());
            courseChapterGoalTypeResource.setTypeValue(courseChapterGoalType.getValue());
            courseChapterGoalTypeResource.setParentName(courseChapterGoalType.getParentName());
            courseChapterGoalTypeResource.setIsShow(courseChapterGoalType.isShow());
            courseChapterGoalTypeResource.setShowOrder(courseChapterGoalType.getShowOrder());

            resourceList.add(courseChapterGoalTypeResource);
        }
        return resourceList;
    }

    public List<CourseStructureBaseResource> getCourseChapterGoal(String structureId, String courseId, String versionId) {
        List<CourseStructureBaseResource> resourceList = new ArrayList<>();
        List<CourseChapterGoal> goalList = new ArrayList<>();
        if (StringUtils.isNotBlank(structureId)) {
            goalList = courseChapterGoalRepository.findByCourseStructureId(structureId);
        } else {
            goalList = courseChapterGoalRepository.findByCourseIdAndVersionId(courseId, versionId);
        }
        Map<String, List<CourseChapterGoal>> courseGoalMap = goalList.stream().collect(Collectors.groupingBy(c -> c.getCourseStructure().getId()));
        CourseChapterGoalType[] courseChapterGoalTypeList = CourseChapterGoalType.values();

        if (courseGoalMap == null || courseGoalMap.isEmpty()) {
            if (StringUtils.isNotBlank(structureId)) {
                CourseStructure courseStructure = courseStructureRepository.findById(structureId).orElse(null);
                if (Objects.nonNull(courseStructure)) {
                    CourseStructureBaseResource baseResource = new CourseStructureBaseResource();
                    baseResource.setStructureId(courseStructure.getId());
                    baseResource.setStructureName(courseStructure.getCourseStructureName());
                    baseResource.setGoalList(buildGoalList(new ArrayList<>(), courseChapterGoalTypeList));
                    resourceList.add(baseResource);
                }
            }
            return resourceList;
        }

        courseGoalMap.forEach((k, v) -> {
            CourseStructure courseStructure = v.get(0).getCourseStructure();
            CourseStructureBaseResource baseResource = new CourseStructureBaseResource();
            baseResource.setStructureId(courseStructure.getId());
            baseResource.setStructureName(courseStructure.getCourseStructureName());
            baseResource.setStructureShowOrder(Objects.isNull(courseStructure.getShowOrder()) ? 0 : courseStructure.getShowOrder());
            baseResource.setGoalList(buildGoalList(v, courseChapterGoalTypeList));
            resourceList.add(baseResource);
        });
        return resourceList.stream().sorted(Comparator.comparing(CourseStructureBaseResource::getStructureShowOrder)).collect(Collectors.toList());
    }

    private List<CourseChapterGoalResource> buildGoalList(List<CourseChapterGoal> v, CourseChapterGoalType[] courseChapterGoalTypeList) {
        List<CourseChapterGoalResource> resourceList = new ArrayList<>();
        for (CourseChapterGoalType courseChapterGoalType : courseChapterGoalTypeList) {
            CourseChapterGoalResource goalResource = new CourseChapterGoalResource();
            goalResource.setTypeName(courseChapterGoalType.getName());
            goalResource.setTypeValue(courseChapterGoalType.getValue());
            goalResource.setParentName(courseChapterGoalType.getParentName());
            goalResource.setIsShow(courseChapterGoalType.isShow());
            goalResource.setShowOrder(courseChapterGoalType.getShowOrder());
            CourseChapterGoal goal = v.stream().filter(c -> c.getChapterGoalType().equals(courseChapterGoalType)).findFirst().orElse(null);
            if (Objects.nonNull(goal)) {
                goalResource.setContent(goal.getContent());
            }
            resourceList.add(goalResource);

        }
        return resourceList;
    }

    public Result addCourseChapterGoal(CourseChapterGoalVo courseChapterGoalVo) {

        Course course = courseRepository.findByIdAndUseStateIsTrue(courseChapterGoalVo.getCourseId()).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(courseChapterGoalVo.getVersionId(),
                CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        CourseStructure courseStructure = courseStructureRepository.findByIdAndStructureStatus(courseChapterGoalVo.getStructureId(),
                StructureStatus.NORMAL);
        if (Objects.isNull(courseStructure)) {
            return Result.error("当前章节不存在，请刷新页面");
        }
        List<CourseChapterGoal> courseChapterGoalList = courseChapterGoalRepository.findByCourseStructureId(courseStructure.getId());
        if (CollectionUtils.isEmpty(courseChapterGoalList)) {
            courseChapterGoalList = new ArrayList<>();
        }
        List<CourseChapterGoal> resultList = new ArrayList<>();
        Date now = new Date();
        for (CourseChapterGoalResource g : courseChapterGoalVo.getGoalList()) {
            CourseChapterGoalType courseChapterGoalType = CourseChapterGoalType.getCourseChapterGoalType(g.getTypeValue());
            if (ObjectUtils.isEmpty(courseChapterGoalType)) {
                continue;
            }
            CourseChapterGoal courseChapterGoal = courseChapterGoalList
                    .stream()
                    .filter(cg -> courseChapterGoalType.equals(cg.getChapterGoalType()))
                    .findFirst()
                    .orElse(null);
            if (Objects.isNull(courseChapterGoal)) {
                courseChapterGoal = new CourseChapterGoal();
                courseChapterGoal.setChapterGoalType(courseChapterGoalType);
                courseChapterGoal.setCourseStructure(courseStructure);
                courseChapterGoal.setCreatorId(courseChapterGoalVo.getUserId());
                courseChapterGoal.setCreatorName(courseChapterGoalVo.getUserName());
            }
            courseChapterGoal.setContent(g.getContent());
            courseChapterGoal.setModifierId(courseChapterGoalVo.getUserId());
            courseChapterGoal.setModifierName(courseChapterGoalVo.getUserName());
            resultList.add(courseChapterGoal);
        }
        courseChapterGoalRepository.saveAll(resultList);
        if (CollectionUtils.isEmpty(courseChapterGoalList) && CollectionUtils.isNotEmpty(resultList)) {
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_OBJECTIVES, true, courseChapterGoalVo.getUserId(),
                    courseChapterGoalVo.getUserName());
        }
        return Result.success();


    }
}

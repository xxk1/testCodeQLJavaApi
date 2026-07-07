package com.lztech.site.service.teachingactivity;

import cn.hutool.core.bean.BeanUtil;
import com.lztech.domain.model.course.CourseResource;
import com.lztech.domain.model.course.CourseStructure;
import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.domain.model.course.enums.StructureStatus;
import com.lztech.domain.model.course.enums.TeacherDataSource;
import com.lztech.persistence.repositories.course.CourseResourceRepository;
import com.lztech.persistence.repositories.course.CourseStructureRepository;
import com.lztech.persistence.repositories.course.CourseTeachingTeamRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.viewmodel.coursematerial.CourseStatisticEntity;
import com.lztech.site.viewmodel.teachingactivity.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@Log4j2
public class TeachingActivityService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;

    @Autowired
    private CourseResourceRepository courseResourceRepository;

    @Autowired
    private CourseStructureRepository courseStructureRepository;

    public TeachingActivityStatisticResult teachingActivityStatistic(TeachingActivityStatisticQueryParam teachingActivityStatisticQueryParam) {
        TeachingActivityStatisticResult teachingActivityStatisticResult = new TeachingActivityStatisticResult();
        Map<String, Object> paramMap = new HashMap<>();
        String nativeSql = buildQuerySql(teachingActivityStatisticQueryParam, paramMap);
        Query nativeQuery = entityManager.createNativeQuery(nativeSql);
        paramMap.forEach(nativeQuery::setParameter);
        List<TeachingActivityStatisticVo> teachingActivityStatisticVoList = nativeQuery.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(TeachingActivityStatisticVo.class))
                .getResultList();
        teachingActivityStatisticVoList = teachingActivityStatisticVoList.stream().filter(
                teachingActivityStatisticVo ->
                        teachingActivityStatisticVo.getAllCount().intValue() > 0
        ).collect(toList());
        int size = teachingActivityStatisticVoList.size();
        teachingActivityStatisticVoList = teachingActivityStatisticVoList
                .stream()
                .skip((long) (teachingActivityStatisticQueryParam.getPageNum() - 1) * teachingActivityStatisticQueryParam.getPageSize())
                .limit(teachingActivityStatisticQueryParam.getPageSize())
                .collect(toList());
        if (ObjectUtils.isNotEmpty(teachingActivityStatisticVoList)) {
            teachingActivityStatisticResult.setPageNum(teachingActivityStatisticQueryParam.getPageNum());
            teachingActivityStatisticResult.setPageSize(teachingActivityStatisticQueryParam.getPageSize());
            teachingActivityStatisticResult.setTeachingActivityStatisticList(teachingActivityStatisticVoList);
            teachingActivityStatisticResult.setTotalCount(size);
        }else{
            teachingActivityStatisticResult.setPageNum(teachingActivityStatisticQueryParam.getPageNum());
            teachingActivityStatisticResult.setPageSize(teachingActivityStatisticQueryParam.getPageSize());
            teachingActivityStatisticResult.setTeachingActivityStatisticList(new ArrayList<>());
            teachingActivityStatisticResult.setTotalCount(size);
        }
        return teachingActivityStatisticResult;
    }

    private String buildQuerySql(TeachingActivityStatisticQueryParam teachingActivityStatisticQueryParam,
                                 Map<String, Object> paramMap) {
        StringBuilder baseSql = new StringBuilder("SELECT " +
                "  tc.id AS courseId, " +
                "  tc.course_name AS courseName, " +
                "  tc.college_name AS courseCollege, " +
                "  tc.course_code AS courseCode, " +
                "  tc.college_id AS collegeId, " +
                "  SUM( CASE WHEN tcr.resource_type = 2 AND tcr.resource_status = 0 THEN 1 ELSE 0 END ) 'testCount', " +
                "  SUM( CASE WHEN tcr.resource_type = 4 AND tcr.resource_status = 0 THEN 1 ELSE 0 END ) 'themeCount', " +
                "  SUM( CASE WHEN (tcr.resource_type = 2 OR tcr.resource_type = 4) AND tcr.resource_status = 0 THEN 1 ELSE 0 END ) 'allCount' " +
                "  FROM " +
                "  tb_course tc  LEFT JOIN tb_course_structure tcs ON tcs.course_id = tc.id " +
                "  LEFT JOIN tb_course_resource tcr ON tcr.course_structure_id = tcs.id  " +
                "  WHERE  tc.use_state = TRUE  ");
        if (StringUtils.isNotEmpty(teachingActivityStatisticQueryParam.getCourseNameOrNo())) {
            baseSql.append(" AND (tc.course_name LIKE :courseNameOrNo OR tc.course_code LIKE :courseNameOrNo ) ");
            paramMap.put("courseNameOrNo", "%" + teachingActivityStatisticQueryParam.getCourseNameOrNo() + "%");
        }
        if (StringUtils.isNotEmpty(teachingActivityStatisticQueryParam.getCollegeId())) {
            baseSql.append(" AND tc.college_id= :collegeId ");
            paramMap.put("collegeId", teachingActivityStatisticQueryParam.getCollegeId());
        }
        baseSql.append(" AND tcs.structure_status=0 " +
                " AND tcr.resource_status=0 " +
                " AND (tcr.resource_type=2 or tcr.resource_type=4) ");
        baseSql.append("GROUP BY tc.id  ");
        if (StringUtils.isNotEmpty(teachingActivityStatisticQueryParam.getSort())
                && ObjectUtils.isNotEmpty(teachingActivityStatisticQueryParam.getSortType())) {
            baseSql.append("  ORDER BY  ")
                    .append(teachingActivityStatisticQueryParam.getSortType() == Constant.ZREO ? "courseCode" :
                            teachingActivityStatisticQueryParam.getSortType() == Constant.ONE ? "allCount" :
                                    teachingActivityStatisticQueryParam.getSortType() == Constant.TWO ? "testCount" :
                                            "themeCount")
                    .append(" ").append(teachingActivityStatisticQueryParam.getSort());
        }
        baseSql.append(" , courseCode ASC ");
        return baseSql.toString();
    }

    public TeacherActivityStatistic teacherActivityStatistic(String courseId) {
        TeacherActivityStatistic teacherActivityStatistic = new TeacherActivityStatistic();
        List<CourseStatisticEntity> courseStatisticEntities = new ArrayList<>();
        List<CourseTeachingTeam> courseTeachingTeamList = courseTeachingTeamRepository
                .findByCourseIdAndTeacherDataSource(courseId, TeacherDataSource.CAMPUS_USER);
        if (courseTeachingTeamList.size() > 0) {
            List<String> courseStructureIds = courseStructureRepository
                    .findByCourseIdAndStructureStatus(courseId, StructureStatus.NORMAL)
                    .stream()
                    .map(CourseStructure::getId)
                    .collect(toList());
            List<CourseResource> courseResourceList = courseResourceRepository
                    .findAllByCourseStructureIdInAndResourceStatus(courseStructureIds, ResourceStatus.NORMAL)
                    .stream()
                    .filter(courseResource -> courseResource.getResourceType().equals(ResourceType.CLASS_TEST)
                            || courseResource.getResourceType().equals(ResourceType.THEME)).collect(toList());
            Map<String, List<CourseResource>> teacherIdAndCourseResourceGroup = courseResourceList.stream()
                    .collect(groupingBy(CourseResource::getCreatorId));
            teacherIdAndCourseResourceGroup.forEach((key, value) -> {
                CourseStatisticEntity courseStatisticEntity = new CourseStatisticEntity();
                courseStatisticEntity.setTeacherName(value.get(0).getCreatorName());
                courseTeachingTeamRepository.findFirstByTeacherId(value.get(0).getCreatorId())
                        .ifPresent(teacher -> courseStatisticEntity.setTeacherNo(teacher.getTeacherNo()));
                courseStatisticEntity.setMateriaNum(value.size());
                courseStatisticEntities.add(courseStatisticEntity);
            });
            List<String> sortedNameList = new LinkedList<>(), sortedNumList = new LinkedList<>();
            courseStatisticEntities.stream()
                    .filter(courseStatistic -> StringUtils.isNotEmpty(courseStatistic.getTeacherName()))
                    .sorted(
                            Comparator.comparing(CourseStatisticEntity::getMateriaNum).reversed()
                                    .thenComparing(CourseStatisticEntity::getTeacherNo))
                    .forEach(courseStatistic -> {
                        sortedNameList.add(courseStatistic.getTeacherName());
                        sortedNumList.add(String.valueOf(courseStatistic.getMateriaNum()));
                    });
            teacherActivityStatistic.setTeacherNameList(sortedNameList);
            teacherActivityStatistic.setResourceNumList(sortedNumList);
        }
        return teacherActivityStatistic;
    }

    public ActivityTypeStatistic activityTypeStatistic(String courseId) {
        ActivityTypeStatistic activityTypeStatistic = new ActivityTypeStatistic();
        Map<ResourceType, List<CourseResource>> resourceTypeListMap = courseResourceRepository.findAllByCourseStructureIdInAndResourceStatus(
                        courseStructureRepository.findByCourseIdAndStructureStatus(courseId, StructureStatus.NORMAL)
                                .stream()
                                .map(CourseStructure::getId)
                                .collect(toList()),
                        ResourceStatus.NORMAL)
                .stream().filter(
                        courseResource -> courseResource.getResourceType().equals(ResourceType.CLASS_TEST)
                                || courseResource.getResourceType().equals(ResourceType.THEME)
                ).collect(groupingBy(CourseResource::getResourceType));
        resourceTypeListMap.forEach((key, value) -> {
            ActivityTypeStatisticVO activityTypeStatisticVO = new ActivityTypeStatisticVO();
            activityTypeStatisticVO.setName(key.name().equals(ResourceType.CLASS_TEST.name()) ? "考核测验" : "讨论主题");
            activityTypeStatisticVO.setValue(value.size());
            activityTypeStatistic.add(activityTypeStatisticVO);
        });
        List<ActivityTypeStatisticVO> collect = activityTypeStatistic
                .stream()
                .sorted(Comparator.comparing(ActivityTypeStatisticVO::getName))
                .collect(toList());
        for (int i = 0; i < collect.size(); i++) {
            activityTypeStatistic.set(i, collect.get(i));
        }
        return activityTypeStatistic;
    }

    public CourseActivityList courseActivityList(String courseId, ActivityListQueryParam activityListQueryParam) {
        CourseActivityList courseActivityList = new CourseActivityList();
        List<CourseActivityVo> courseActivityVoList = new ArrayList<>();
        List<CourseResource> courseResources = courseResourceRepository.findAllByCourseStructureIdInAndResourceStatus(
                        courseStructureRepository.findByCourseIdAndStructureStatus(courseId, StructureStatus.NORMAL)
                                .stream()
                                .map(CourseStructure::getId)
                                .collect(toList()),
                        ResourceStatus.NORMAL)
                .stream().filter(
                        courseResource -> courseResource.getResourceType().equals(ResourceType.CLASS_TEST)
                                || courseResource.getResourceType().equals(ResourceType.THEME)
                ).collect(toList());
        if (ObjectUtils.isNotEmpty(activityListQueryParam.getResourceType())) {
            courseResources = courseResources
                    .stream()
                    .filter(courseResource -> Objects.equals(courseResource.getResourceType().getIndex(), activityListQueryParam.getResourceType()))
                    .collect(toList());
        }
        if (StringUtils.isNotBlank(activityListQueryParam.getResourceName())) {
            courseResources = courseResources
                    .stream()
                    .filter(courseResource -> courseResource.getResourceName().contains(activityListQueryParam.getResourceName()))
                    .collect(toList());
        }
        if (StringUtils.isNotBlank(activityListQueryParam.getModifierName())) {
            courseResources = courseResources
                    .stream()
                    .filter(courseResource -> courseResource.getModifierName().contains(activityListQueryParam.getModifierName()))
                    .collect(toList());
        }
        getActivityList(courseResources
                .stream()
                .sorted(Comparator.comparing(CourseResource::getModifyTime))
                .collect(toList()), courseActivityVoList);
        int allCount = courseActivityVoList.size();
        if (ObjectUtils.allNotNull(activityListQueryParam.getPageNum(), activityListQueryParam.getPageSize())) {
            courseActivityVoList = courseActivityVoList.stream()
                    .skip((long) (activityListQueryParam.getPageNum() - 1) * activityListQueryParam.getPageSize())
                    .limit(activityListQueryParam.getPageSize()).collect(toList());
        }
        courseActivityList.setCourseResourceList(courseActivityVoList);
        courseActivityList.setTotalCount(allCount);
        courseActivityList.setPageNum(activityListQueryParam.getPageNum());
        courseActivityList.setPageSize(activityListQueryParam.getPageSize());
        return courseActivityList;
    }

    private void getActivityList(List<CourseResource> collect, List<CourseActivityVo> courseActivityVoList) {
        for (CourseResource courseResource : collect) {
            CourseActivityVo courseActivityVo = new CourseActivityVo();
            BeanUtil.copyProperties(courseResource, courseActivityVo, "resourceId", "resourceType", "isPublic");
            courseActivityVo.setResourceId(courseResource.getId());
            courseActivityVo.setResourceType(courseResource.getResourceType().getIndex());
            courseActivityVo.setIsPublic(courseResource.getIsPublic() ? 1 : 0);
            courseActivityVoList.add(courseActivityVo);
        }
    }

    public TeachingActivityTypes getActivityTypes() {
        TeachingActivityTypes teachingActivityTypes = new TeachingActivityTypes();
        for (ResourceType value : ResourceType.values()) {
            if (value.getIndex() == Constant.TWO || value.getIndex() == Constant.FOUR) {
                TeachingActivityType teachingActivityType = new TeachingActivityType();
                teachingActivityType.setIndex(value.getIndex());
                teachingActivityType.setName(value.name().equals("CLASS_TEST") ? "考核测验" : "讨论主题");
                teachingActivityTypes.add(teachingActivityType);
            }
        }
        return teachingActivityTypes;
    }
}

package com.lztech.site.service.courseconstruction;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseManagementOldDataService {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseCompletionRepository courseCompletionRepository;
    @Autowired
    private CourseTeachingTeamService courseTeachingTeamService;
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;

    @Transactional
    public void updateOldData() {
        List<CourseMaterial> courseMaterialList = courseMaterialRepository.findAll();
        List<CourseTeachingTeam> courseTeachingTeamList = courseTeachingTeamRepository.findAll();
        List<CourseStructure> courseStructureList = courseStructureRepository.findByStructureStatus(StructureStatus.NORMAL);
        List<CourseResource> courseResourceList = courseResourceRepository.findByResourceStatus(ResourceStatus.NORMAL);
        List<CourseVersion> courseVersionList = courseVersionRepository.findByCourseVersionStatus(CourseVersionStatus.IN_USE);
        List<CourseCompletion> courseCompletionList = courseCompletionRepository.findAll();
        Date now = new Date();
        List<CourseTeachingTeam> resultCourseTeachingTeamList = new ArrayList<>();
        List<CourseStructure> resultCourseStructureList = new ArrayList<>();
        List<CourseCompletion> resultCourseCompletionList = new ArrayList<>();
        courseMaterialList.forEach(c -> {
            Course course = c.getCourse();
            System.out.println(c.getId());
            CourseVersion savedCourseVersion = saveCourseCourseVersion(now, c, course, courseVersionList);
            List<CourseTeachingTeam> thisCourseTeachingTeamList =
                    courseTeachingTeamList.stream().filter(ctt -> ctt.getCourse().getId().equals(course.getId())).collect(Collectors.toList());
            c.setCourseVersion(savedCourseVersion);
            boolean teachingTeamUserCompletedFlag = false;
            boolean teachingTeamLeaderCompletedFlag = false;
            if (CollectionUtils.isNotEmpty(thisCourseTeachingTeamList)) {
                for (CourseTeachingTeam teachingTeam : thisCourseTeachingTeamList) {
                    teachingTeam.setCourseVersion(savedCourseVersion);
                    teachingTeam.setTeacherDataSource(TeacherDataSource.CAMPUS_USER);
                    if (!teachingTeamUserCompletedFlag && TeacherType.TEACHER.equals(teachingTeam.getTeacherType())) {
                        teachingTeamUserCompletedFlag = true;
                    }
                    if (!teachingTeamLeaderCompletedFlag && TeacherType.COURSE_LEADER.equals(teachingTeam.getTeacherType())) {
                        teachingTeamLeaderCompletedFlag = true;
                    }
                    resultCourseTeachingTeamList.add(teachingTeam);
                }
            }
            List<CourseStructure> thisCourseStructureList = courseStructureList
                    .stream()
                    .filter(cs -> cs.getCourse().getId().equals(course.getId()))
                    .collect(Collectors.toList());
            boolean courseStructureCompletedFlag = false;
            if (CollectionUtils.isNotEmpty(thisCourseStructureList)) {
                courseStructureCompletedFlag = true;
                for (CourseStructure courseStructure : thisCourseStructureList) {
                    courseStructure.setCourseVersion(savedCourseVersion);
                    resultCourseStructureList.add(courseStructure);
                }
            }
            boolean assessmentMethodCompletedFlag = StringUtils.isNotBlank(c.getAssessmentMethod());
            List<String> thisCourseStructureIdList = courseStructureList.stream().map(CourseStructure::getId).collect(Collectors.toList());
            boolean courseResourceCompletedFlag = courseResourceList
                    .stream()
                    .anyMatch(cr -> (ResourceType.TEACHING_COURSE_WARE.equals(cr.getResourceType())
                            || ResourceType.MICRO_VIDEO.equals(cr.getResourceType())
                            || ResourceType.IMAGE.equals(cr.getResourceType())
                            || ResourceType.AUDIO.equals(cr.getResourceType()))
                            && thisCourseStructureIdList.contains(cr.getCourseStructure().getId()));

            boolean courseActivityCompletedFlag = courseResourceList
                    .stream()
                    .anyMatch(cr -> (ResourceType.CLASS_TEST.equals(cr.getResourceType())
                            || ResourceType.THEME.equals(cr.getResourceType()))
                            && thisCourseStructureIdList.contains(cr.getCourseStructure().getId()));
            CourseCompletionFlagVo courseCompletionFlagVo = new CourseCompletionFlagVo();
            courseCompletionFlagVo.setTeachingTeamUserCompletedFlag(teachingTeamUserCompletedFlag);
            courseCompletionFlagVo.setTeachingTeamLeaderCompletedFlag(teachingTeamLeaderCompletedFlag);
            courseCompletionFlagVo.setCourseStructureCompletedFlag(courseStructureCompletedFlag);
            courseCompletionFlagVo.setAssessmentMethodCompletedFlag(assessmentMethodCompletedFlag);
            courseCompletionFlagVo.setCourseResourceCompletedFlag(courseResourceCompletedFlag);
            courseCompletionFlagVo.setCourseActivityCompletedFlag(courseActivityCompletedFlag);
            List<CourseCompletion> thisCourseCompletionList =
                    courseCompletionList.stream().filter(cc -> course.getId().equals(cc.getCourse().getId())).collect(Collectors.toList());
            List<CourseCompletion> thisResultCourseCompletionList = generateThisCourseCompletion(courseCompletionFlagVo, course, savedCourseVersion,
                    thisCourseCompletionList);
            resultCourseCompletionList.addAll(thisResultCourseCompletionList);
        });
        courseMaterialRepository.saveAll(courseMaterialList);
        List<CourseTeachingTeam> saveCourseTeachingTeamList = courseTeachingTeamRepository.saveAll(resultCourseTeachingTeamList);
        courseStructureRepository.saveAll(resultCourseStructureList);
        courseCompletionRepository.saveAll(resultCourseCompletionList);
        if (dataVisualEnable) {
            courseTeachingTeamService.sendCourseTeachingTeamList(saveCourseTeachingTeamList);
        }
    }

    private List<CourseCompletion> generateThisCourseCompletion(CourseCompletionFlagVo courseCompletionFlagVo,
                                                                Course course,
                                                                CourseVersion savedCourseVersion,
                                                                List<CourseCompletion> courseCompletionList) {
        List<CourseCompletion> returnCourseCompletionList = new ArrayList<>();
        CourseCompletion teachingTeamUserCourseCompletion = getCourseCompletion(CourseContentTypeEnum.COURSE_TEAM_USER,
                courseCompletionFlagVo.isTeachingTeamUserCompletedFlag(),
                course, savedCourseVersion, courseCompletionList);
        returnCourseCompletionList.add(teachingTeamUserCourseCompletion);

        CourseCompletion teachingTeamLeaderCourseCompletion = getCourseCompletion(CourseContentTypeEnum.COURSE_TEAM_LEADER,
                courseCompletionFlagVo.isTeachingTeamLeaderCompletedFlag(), course,
                savedCourseVersion, courseCompletionList);
        returnCourseCompletionList.add(teachingTeamLeaderCourseCompletion);

        CourseCompletion courseStructureCompletion = getCourseCompletion(CourseContentTypeEnum.COURSE_STRUCTURE,
                courseCompletionFlagVo.isCourseStructureCompletedFlag(), course, savedCourseVersion, courseCompletionList);

        returnCourseCompletionList.add(courseStructureCompletion);

        CourseCompletion assessmentMethodCompletion = getCourseCompletion(CourseContentTypeEnum.ASSESSMENT_SCHEME,
                courseCompletionFlagVo.isAssessmentMethodCompletedFlag(), course,
                savedCourseVersion, courseCompletionList);

        returnCourseCompletionList.add(assessmentMethodCompletion);


        CourseCompletion courseResourceCompletion = getCourseCompletion(CourseContentTypeEnum.TEACHING_RESOURCES,
                courseCompletionFlagVo.isCourseResourceCompletedFlag(),
                course, savedCourseVersion, courseCompletionList);

        returnCourseCompletionList.add(courseResourceCompletion);

        CourseCompletion courseActivityCompletion = getCourseCompletion(CourseContentTypeEnum.PREPARATORY_ACTIVITIES,
                courseCompletionFlagVo.isCourseActivityCompletedFlag(), course,
                savedCourseVersion, courseCompletionList);

        returnCourseCompletionList.add(courseActivityCompletion);
        return returnCourseCompletionList;
    }

    private CourseCompletion getCourseCompletion(CourseContentTypeEnum contentTypeEnum,
                                                 boolean completedFlag,
                                                 Course course,
                                                 CourseVersion savedCourseVersion,
                                                 List<CourseCompletion> courseCompletionList) {

        CourseCompletion courseCompletion =
                courseCompletionList.stream().filter(cc -> contentTypeEnum.equals(cc.getCourseContentEnum())).findFirst().orElse(null);
        if (Objects.isNull(courseCompletion)) {
            courseCompletion = new CourseCompletion();
            courseCompletion.setCreatorId(savedCourseVersion.getCreatorId());
            courseCompletion.setCreatorName(savedCourseVersion.getCreatorName());
            courseCompletion.setCreateTime(savedCourseVersion.getModifyTime());
        }
        courseCompletion.setCourseContentEnum(contentTypeEnum);
        courseCompletion.setCompleted(completedFlag);
        courseCompletion.setCourse(course);
        courseCompletion.setCourseVersion(savedCourseVersion);
        courseCompletion.setModifierId(savedCourseVersion.getModifierId());
        courseCompletion.setModifierName(savedCourseVersion.getModifierName());
        courseCompletion.setModifyTime(savedCourseVersion.getModifyTime());
        return courseCompletion;
    }

    private CourseVersion saveCourseCourseVersion(Date now, CourseMaterial c, Course course, List<CourseVersion> courseVersionList) {
        try {
            CourseVersion courseVersion =
                    courseVersionList.stream().filter(cv -> course.getId().equals(cv.getCourse().getId())).findFirst().orElse(null);
            if (Objects.isNull(courseVersion)) {
                courseVersion = new CourseVersion();
                courseVersion.setCreatorId(c.getCreatorId());
                courseVersion.setCreatorName(c.getCreatorName());
                courseVersion.setCreateTime(now);
            }
            courseVersion.setVersionSerialNumber(1);
            courseVersion.setCourseVersionStatus(CourseVersionStatus.IN_USE);
            courseVersion.setCourse(course);
            courseVersion.setModifierId(c.getModifierId());
            courseVersion.setModifierName(c.getModifierName());
            courseVersion.setModifyTime(now);
            return courseVersionRepository.save(courseVersion);

        } catch (Exception e) {
            System.out.println("courseId->" + course.getId() + "CourseMaterialId->" + c.getId());
            throw new RuntimeException();
        }

    }

    class CourseCompletionFlagVo {
        private boolean teachingTeamUserCompletedFlag = false;
        private boolean teachingTeamLeaderCompletedFlag = false;
        private boolean courseStructureCompletedFlag = false;
        private boolean assessmentMethodCompletedFlag = false;
        private boolean courseResourceCompletedFlag = false;
        private boolean courseActivityCompletedFlag = false;

        public boolean isTeachingTeamUserCompletedFlag() {
            return teachingTeamUserCompletedFlag;
        }

        public void setTeachingTeamUserCompletedFlag(boolean teachingTeamUserCompletedFlag) {
            this.teachingTeamUserCompletedFlag = teachingTeamUserCompletedFlag;
        }

        public boolean isTeachingTeamLeaderCompletedFlag() {
            return teachingTeamLeaderCompletedFlag;
        }

        public void setTeachingTeamLeaderCompletedFlag(boolean teachingTeamLeaderCompletedFlag) {
            this.teachingTeamLeaderCompletedFlag = teachingTeamLeaderCompletedFlag;
        }

        public boolean isCourseStructureCompletedFlag() {
            return courseStructureCompletedFlag;
        }

        public void setCourseStructureCompletedFlag(boolean courseStructureCompletedFlag) {
            this.courseStructureCompletedFlag = courseStructureCompletedFlag;
        }

        public boolean isAssessmentMethodCompletedFlag() {
            return assessmentMethodCompletedFlag;
        }

        public void setAssessmentMethodCompletedFlag(boolean assessmentMethodCompletedFlag) {
            this.assessmentMethodCompletedFlag = assessmentMethodCompletedFlag;
        }

        public boolean isCourseResourceCompletedFlag() {
            return courseResourceCompletedFlag;
        }

        public void setCourseResourceCompletedFlag(boolean courseResourceCompletedFlag) {
            this.courseResourceCompletedFlag = courseResourceCompletedFlag;
        }

        public boolean isCourseActivityCompletedFlag() {
            return courseActivityCompletedFlag;
        }

        public void setCourseActivityCompletedFlag(boolean courseActivityCompletedFlag) {
            this.courseActivityCompletedFlag = courseActivityCompletedFlag;
        }
    }
}

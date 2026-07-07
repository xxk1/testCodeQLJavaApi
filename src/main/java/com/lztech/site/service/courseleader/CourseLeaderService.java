package com.lztech.site.service.courseleader;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.TeacherDataSource;
import com.lztech.domain.model.course.enums.TeacherType;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.CourseTeachingTeamRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.resource.course.CourseInfoResource;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.service.courseconstruction.CourseTeachingTeamService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.viewmodel.courseleader.CourseLeaderVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Service
public class CourseLeaderService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private CourseVersionRepository courseVersionRepository;

    private Integer courseTeamActionType = 0;
    @Autowired
    private CourseTeachingTeamService courseTeachingTeamService;

    @Autowired
    private EventService eventService;

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;

    @Transactional
    public Result updateCourseLeader(CourseLeaderVo courseLeaderVo) {
        Date nowDate = new Date() ;
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseLeaderVo.getCourseId()).orElse(null);
        if (course == null) {
            return Result.error("课程不存在");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(courseLeaderVo.getVersionId(),
                CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository.findByCourseIdAndCourseVersionId(courseLeaderVo.getCourseId(),
                courseLeaderVo.getVersionId());
        if (!courseLeaderVo.isManagementPort()) {
            long count = courseTeachingTeams.stream().filter(c -> courseLeaderVo.getCourseLeaderId().equals(c.getTeacherId()) &&
                            TeacherType.COURSE_LEADER.equals(c.getTeacherType())).count();
            if (count == 0) {
                return Result.error("该老师不是课程负责人");
            }
        }
        String teacherJobTitle  = null;
        CourseTeachingTeam otherCourseTeachingTeam = courseTeachingTeamRepository
                .getCourseTeachingTeamByTeacherId(courseLeaderVo.getTeacherId());
        if (Objects.nonNull(otherCourseTeachingTeam)) {
            teacherJobTitle = otherCourseTeachingTeam.getJobTitle();
        }
        CourseTeachingTeam newCourseLeader = courseTeachingTeams.stream()
                .filter(c -> courseLeaderVo.getTeacherId().equals(c.getTeacherId())).findFirst().orElse(null);
        if (Objects.isNull(newCourseLeader)) {
            newCourseLeader = new CourseTeachingTeam();
            createNewCourseLeader(courseLeaderVo, nowDate, course, courseVersion, teacherJobTitle, newCourseLeader);
            courseTeachingTeams.add(newCourseLeader);
        } else {
            newCourseLeader.setModifyTime(nowDate);
            newCourseLeader.setModifierId(courseLeaderVo.getCourseLeaderId());
            newCourseLeader.setModifierName(courseLeaderVo.getCourseLeaderName());
            newCourseLeader.setTeacherType(TeacherType.COURSE_LEADER);
        }
        List<CourseTeachingTeam> resultList = courseTeachingTeams;
        if (StringUtils.isNotBlank(courseLeaderVo.getCourseLeaderId())) {
            CourseTeachingTeam oldTeamLeader = courseTeachingTeams.stream()
                    .filter(c -> courseLeaderVo.getCourseLeaderId().equals(c.getTeacherId())).findFirst().orElse(null);
            if (Objects.nonNull(oldTeamLeader)) {
                oldTeamLeader.setTeacherType(TeacherType.TEACHER);
                oldTeamLeader.setModifyTime(nowDate);
                oldTeamLeader.setModifierId(courseLeaderVo.getCourseLeaderId());
                oldTeamLeader.setModifierName(courseLeaderVo.getCourseLeaderName());
                if (courseTeamActionType.equals(courseLeaderVo.getActionType())) {
                    resultList =
                            resultList.stream().filter(c -> TeacherDataSource.CAMPUS_USER.equals(c.getTeacherDataSource())
                                    && !c.getTeacherId().equals(oldTeamLeader.getTeacherId())).collect(Collectors.toList());
                    courseTeachingTeamRepository.delete(oldTeamLeader);
                    if (dataVisualEnable){
                        eventService.sendDeleteCourseTeachingTeamEvent(oldTeamLeader.getId());
                    }
                }
            }
        }
        courseTeachingTeamRepository.saveAll(resultList);
        if (dataVisualEnable){
            sendCourseTeachingTeamEvent(courseLeaderVo, nowDate, resultList);
        }
        course.setCourseLeaderId(courseLeaderVo.getTeacherId());
        course.setCourseLeaderName(courseLeaderVo.getTeacherName());
        courseRepository.save(course);
        courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_TEAM_USER,
                resultList.stream().anyMatch(c -> TeacherType.TEACHER.equals(c.getTeacherType())), courseLeaderVo.getCourseLeaderId(),
                courseLeaderVo.getCourseLeaderName());
        courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_TEAM_LEADER,
                resultList.stream().anyMatch(c -> TeacherType.COURSE_LEADER.equals(c.getTeacherType())), courseLeaderVo.getCourseLeaderId(),
                courseLeaderVo.getCourseLeaderName());
        return Result.success();
    }

    private void createNewCourseLeader(CourseLeaderVo courseLeaderVo, Date nowDate,
                                       Course course, CourseVersion courseVersion,
                                       String teacherJobTitle, CourseTeachingTeam newCourseLeader) {
        newCourseLeader.setTeacherType(TeacherType.COURSE_LEADER);
        if (ObjectUtils.isNotEmpty(teacherJobTitle)){
            newCourseLeader.setJobTitle(teacherJobTitle);
        }
        newCourseLeader.setCreateTime(nowDate);
        newCourseLeader.setCreatorId(courseLeaderVo.getCourseLeaderId());
        newCourseLeader.setCreatorName(courseLeaderVo.getCourseLeaderName());
        newCourseLeader.setModifyTime(nowDate);
        newCourseLeader.setModifierId(courseLeaderVo.getCourseLeaderId());
        newCourseLeader.setModifierName(courseLeaderVo.getCourseLeaderName());
        newCourseLeader.setCourse(course);
        newCourseLeader.setCourseVersion(courseVersion);
        newCourseLeader.setTeacherId(courseLeaderVo.getTeacherId());
        newCourseLeader.setTeacherName(courseLeaderVo.getTeacherName());
        newCourseLeader.setTeacherNo(courseLeaderVo.getTeacherNo());
        newCourseLeader.setTeacherDataSource(TeacherDataSource.CAMPUS_USER);
    }

    private void sendCourseTeachingTeamEvent(CourseLeaderVo courseLeaderVo, Date nowDate, List<CourseTeachingTeam> resultList) {
        List<CourseTeachingTeam> sendCourseTeachingTeamList = resultList.stream().filter(
                courseTeachingTeam ->
                        courseTeachingTeam.getModifyTime().compareTo(nowDate) ==0 &&
                        courseTeachingTeam.getCourse().getId().equals(courseLeaderVo.getCourseId())&&
                        courseTeachingTeam.getCourseVersion().getId().equals(courseLeaderVo.getVersionId()))
                .collect(Collectors.toList());
        courseTeachingTeamService.sendCourseTeachingTeamList(sendCourseTeachingTeamList);
    }

    public List<CourseInfoResource> getUserLeaderCourseList(String id) {
        List<Course> courseList = courseRepository.findByCourseLeaderIdAndUseStateIsTrue(id);
        List<CourseInfoResource> courseInfoResourceList = new ArrayList<>();
        courseList.forEach(c -> {
            CourseInfoResource courseInfoResource = new CourseInfoResource();
            courseInfoResource.setCourseId(c.getId());
            courseInfoResource.setCourseName(c.getCourseName());
            courseInfoResource.setCollegeCode(c.getCollegeCode());
            courseInfoResource.setCourseCode(c.getCourseCode());
            courseInfoResource.setCollegeName(c.getCollegeName());
            courseInfoResource.setCourseLeaderId(c.getCourseLeaderId());
            courseInfoResource.setCourseLeaderName(c.getCourseLeaderName());
            courseInfoResource.setCourseSource(c.getCourseSource().getIndex());
            courseInfoResourceList.add(courseInfoResource);
        });
        return courseInfoResourceList;
    }
}

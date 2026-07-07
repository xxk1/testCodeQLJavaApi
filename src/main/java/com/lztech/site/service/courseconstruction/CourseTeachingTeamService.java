package com.lztech.site.service.courseconstruction;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.TeachingTeamTeacher;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.TeacherDataSource;
import com.lztech.domain.model.course.enums.TeacherType;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.CourseTeachingTeamRepository;
import com.lztech.persistence.repositories.course.TeachingTeamTeacherRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.viewmodel.authorityapi.UsersInfoResource;
import com.lztech.site.viewmodel.coursemanagement.*;
import com.lztech.site.viewmodel.event.CourseTeachingTeamEvent;
import com.lztech.site.viewmodel.userinfo.Users;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseTeachingTeamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseTeachingTeamService.class);

    @Value("${request.address.authorityApi}")
    private String authorityApi;
    @Value("${signKey}")
    private String signKey;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private AuthorityApiService authorityApiService;
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Autowired
    private EventService eventService;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TeachingTeamTeacherRepository teachingTeamTeacherRepository;

    //region 接口-老师加入教学团队
    @Transactional
    public Result createCourseTeachingTeam(CourseTeachingTeamVo courseTeachingTeamVo) {
        CourseTeachingTeam courseTeachingTeam;
        Date date = new Date();
        boolean isAdd = false;
        if (Objects.isNull(courseTeachingTeamVo.getTeachingTeamId())) {
            Course course = courseRepository.findByIdAndUseStateIsTrue(courseTeachingTeamVo.getCourseId()).orElse(null);
            if (ObjectUtils.isEmpty(course)) {
                return Result.error("当前系统不存在该课程");
            }
            CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(courseTeachingTeamVo.getVersionId(),
                    CourseVersionStatus.IN_USE);
            if (ObjectUtils.isEmpty(courseVersion)) {
                return Result.error("无法保存，当前版本已归档，请刷新页面");
            }
            if (TeacherDataSource.CAMPUS_USER.getValue() == courseTeachingTeamVo.getDataSource()) {
                courseTeachingTeam = courseTeachingTeamRepository
                        .findByTeacherIdAndCourseId(courseTeachingTeamVo.getTeacherId(), courseTeachingTeamVo.getCourseId());
                if (ObjectUtils.isNotEmpty(courseTeachingTeam)) {
                    return Result.error("老师已加入该教学团队");
                }
            }

            courseTeachingTeam = new CourseTeachingTeam();
            courseTeachingTeam.setCreateTime(date);
            courseTeachingTeam.setCreatorId(courseTeachingTeamVo.getOperatorId());
            courseTeachingTeam.setCreatorName(courseTeachingTeamVo.getOperatorName());
            courseTeachingTeam.setCourseVersion(courseVersion);
            courseTeachingTeam.setCourse(course);
            courseTeachingTeam.setTeacherDataSource(TeacherDataSource.getTeacherDataSource(courseTeachingTeamVo.getDataSource()));
            courseTeachingTeam.setTeacherType(TeacherType.TEACHER);
            isAdd = true;
        } else {
            courseTeachingTeam = courseTeachingTeamRepository.findById(courseTeachingTeamVo.getTeachingTeamId()).orElse(null);
            if (ObjectUtils.isEmpty(courseTeachingTeam)) {
                return Result.error("该教学团队成员不存在");
            }
        }
        String teacherJobTitle = courseTeachingTeamVo.getJobTitle();
        if (StringUtils.isBlank(teacherJobTitle)) {
            CourseTeachingTeam otherCourseTeachingTeam = courseTeachingTeamRepository
                    .getCourseTeachingTeamByTeacherId(courseTeachingTeamVo.getTeacherId());
            if (Objects.nonNull(otherCourseTeachingTeam)) {
                teacherJobTitle = otherCourseTeachingTeam.getJobTitle();
            }
        }
        courseTeachingTeam.setModifierId(courseTeachingTeamVo.getOperatorId());
        courseTeachingTeam.setModifierName(courseTeachingTeamVo.getOperatorName());
        courseTeachingTeam.setModifyTime(date);

        courseTeachingTeam.setTeacherId(courseTeachingTeamVo.getTeacherId());
        courseTeachingTeam.setTeacherNo(courseTeachingTeamVo.getTeacherNo());
        courseTeachingTeam.setTeacherName(courseTeachingTeamVo.getTeacherName());

        courseTeachingTeam.setJobTitle(teacherJobTitle);
        courseTeachingTeam.setSchoolName(Objects.isNull(courseTeachingTeamVo.getSchoolName()) ? null :
                courseTeachingTeamVo.getSchoolName().trim());
        courseTeachingTeam.setTeacherProfile(courseTeachingTeamVo.getTeacherProfile());
        courseTeachingTeam.setTeacherDataSource(TeacherDataSource.getTeacherDataSource(courseTeachingTeamVo.getDataSource()));
        courseTeachingTeam = courseTeachingTeamRepository.save(courseTeachingTeam);

        updateOtherTeachingTeamTeacherJobTitle(
                courseTeachingTeam.getJobTitle(), courseTeachingTeamVo.getTeacherId(),
                courseTeachingTeamVo, date);

        updateTeachingTeamTeacher(courseTeachingTeamVo,courseTeachingTeam,isAdd);

        if (Objects.isNull(courseTeachingTeamVo.getTeachingTeamId())) {
            courseCompletionService.saveCourseCompletion(courseTeachingTeam.getCourse(), CourseContentTypeEnum.COURSE_TEAM_USER, true,
                    courseTeachingTeamVo.getOperatorId(), courseTeachingTeamVo.getOperatorName());
        }
        if (dataVisualEnable) {
            createCourseTeachingTeamEvent(courseTeachingTeam);
        }
        return Result.success();
    }

    private void updateTeachingTeamTeacher(CourseTeachingTeamVo courseTeachingTeamVo,
                                           CourseTeachingTeam courseTeachingTeam,
                                           boolean isAdd) {
        if (isAdd) {
            return;
        }

        TeachingTeamTeacher teachingTeamTeacher;

        String teacherId;
        if (courseTeachingTeam.getTeacherDataSource().getValue() == TeacherDataSource.CAMPUS_USER.getValue()) {
            teacherId = courseTeachingTeamVo.getTeacherId();
        } else {
            teacherId = courseTeachingTeam.getId();
        }

        Optional<TeachingTeamTeacher>  existingOpt =
                teachingTeamTeacherRepository.findByTeacherIdForUpdate(teacherId);

        teachingTeamTeacher = existingOpt.orElseGet(TeachingTeamTeacher::new);

        if (StringUtils.isNotBlank(teachingTeamTeacher.getTeacherId())) {
            if (StringUtils.isEmpty(courseTeachingTeamVo.getPhotoInnerUrl())
                    || StringUtils.isEmpty(courseTeachingTeamVo.getPhotoFilePath())) {
                teachingTeamTeacherRepository.delete(teachingTeamTeacher);
                return;
            } else if (Objects.equals(teachingTeamTeacher.getPhotoInnerUrl(), courseTeachingTeamVo.getPhotoInnerUrl())
                && Objects.equals(teachingTeamTeacher.getPhotoFilePath(), courseTeachingTeamVo.getPhotoFilePath())) {
                return;
            }
        }

        Date now = new Date();
        if (StringUtils.isBlank(teachingTeamTeacher.getTeacherId())) {
            teachingTeamTeacher.setCreatorId(courseTeachingTeamVo.getOperatorId());
            teachingTeamTeacher.setCreateTime(now);
            teachingTeamTeacher.setCreatorName(courseTeachingTeamVo.getOperatorName());
        }
        teachingTeamTeacher.setTeacherId(teacherId);
        teachingTeamTeacher.setTeacherName(courseTeachingTeamVo.getTeacherName());
        teachingTeamTeacher.setTeacherNo(courseTeachingTeamVo.getTeacherNo());
        teachingTeamTeacher.setPhotoFileDisplayName(courseTeachingTeamVo.getPhotoFileDisplayName());
        teachingTeamTeacher.setPhotoFileRealName(courseTeachingTeamVo.getPhotoFileRealName());
        teachingTeamTeacher.setPhotoFileType(courseTeachingTeamVo.getPhotoFileType());
        teachingTeamTeacher.setPhotoInnerUrl(courseTeachingTeamVo.getPhotoInnerUrl());
        teachingTeamTeacher.setPhotoOuterUrl(courseTeachingTeamVo.getPhotoOuterUrl());
        teachingTeamTeacher.setPhotoFilePath(courseTeachingTeamVo.getPhotoFilePath());
        teachingTeamTeacher.setPhotoFileSize(StringUtils.isAnyBlank(courseTeachingTeamVo.getPhotoFileSize())
                ? null : Long.parseLong(courseTeachingTeamVo.getPhotoFileSize()));
        teachingTeamTeacher.setModifierId(courseTeachingTeamVo.getOperatorId());
        teachingTeamTeacher.setModifierName(courseTeachingTeamVo.getOperatorName());
        teachingTeamTeacher.setModifyTime(now);

        teachingTeamTeacherRepository.save(teachingTeamTeacher);
    }

    private void updateOtherTeachingTeamTeacherJobTitle(String jobTitle, String teacherId, CourseTeachingTeamVo courseTeachingTeamVo,
                                                        Date date) {
        List<CourseTeachingTeam> courseTeachingTeamList = courseTeachingTeamRepository.findByTeacherIdOrderByCourseId(teacherId);
        courseTeachingTeamList = courseTeachingTeamList.stream().filter(courseTeachingTeam ->
                        courseTeachingTeam.getCourseVersion().getCourseVersionStatus() == CourseVersionStatus.IN_USE
                                && (StringUtils.isNotBlank(jobTitle) && !jobTitle.equals(courseTeachingTeam.getJobTitle())))
                .peek(courseTeachingTeam -> {
                    courseTeachingTeam.setJobTitle(jobTitle);
                    courseTeachingTeam.setModifierId(courseTeachingTeamVo.getOperatorId());
                    courseTeachingTeam.setModifierName(courseTeachingTeamVo.getOperatorName());
                    courseTeachingTeam.setModifyTime(date);
                }).collect(Collectors.toList());

        courseTeachingTeamRepository.saveAll(courseTeachingTeamList);

        if (dataVisualEnable && courseTeachingTeamList.size() > 0) {
            sendCourseTeachingTeamList(courseTeachingTeamList);
        }

    }

    public CourseTeachingTeamResource getCourseTeachingTeams(String courseId,
                                                             String versionId,
                                                             Boolean isStatistics) {
        CourseTeachingTeamResource courseTeachingTeamResource = new CourseTeachingTeamResource();
        courseTeachingTeamResource.setCampusTeacherNum(0);
        courseTeachingTeamResource.setOffCampusTeacherNum(0);
        courseTeachingTeamResource.setJobTitleStatisticsList(new ArrayList<>());
        courseTeachingTeamResource.setOrgStatisticsList(new ArrayList<>());
        courseTeachingTeamResource.setTeacherList(new ArrayList<>());

        List<CourseTeachingTeam> courseTeachingTeamList = courseTeachingTeamRepository.findByCourseIdAndCourseVersionId(courseId, versionId);
        if (CollectionUtils.isEmpty(courseTeachingTeamList)) {
            return courseTeachingTeamResource;
        }
        List<UsersInfoResource> usersInfos = getCourseTeachingTeamTeacherInfo(courseTeachingTeamList);

        List<String> teacherIds = courseTeachingTeamList.stream().map(o->{
            if (o.getTeacherDataSource().equals(TeacherDataSource.CAMPUS_USER)){
                return o.getTeacherId();
            } else {
                return o.getId();
            }
        }).collect(Collectors.toList());

        List<TeachingTeamTeacher> teachingTeamTeachers = teachingTeamTeacherRepository.findAllByTeacherIdIn(teacherIds);
        List<TeacherInfoVo> teacherInfoVoList = new ArrayList<>();
        for (CourseTeachingTeam courseTeachingTeam : courseTeachingTeamList) {
            TeacherInfoVo teacherInfoVo = new TeacherInfoVo();
            teacherInfoVo.setTeacherId(courseTeachingTeam.getTeacherId());
            teacherInfoVo.setTeachingTeamId(courseTeachingTeam.getId());
            teacherInfoVo.setTeacherNo(courseTeachingTeam.getTeacherNo());
            teacherInfoVo.setTeacherName(courseTeachingTeam.getTeacherName());
            teacherInfoVo.setIsLeader(TeacherType.COURSE_LEADER.equals(courseTeachingTeam.getTeacherType()));
            teacherInfoVo.setJobTitle(courseTeachingTeam.getJobTitle());
            teacherInfoVo.setTeacherProfile(courseTeachingTeam.getTeacherProfile());
            teacherInfoVo.setDataSource(courseTeachingTeam.getTeacherDataSource().getValue());
            teacherInfoVo.setSchoolName(courseTeachingTeam.getSchoolName());
            if (TeacherDataSource.CAMPUS_USER.equals(courseTeachingTeam.getTeacherDataSource())) {
                UsersInfoResource usersInfoResource = usersInfos
                        .stream()
                        .filter(usersInfo -> StringUtils.isNotBlank(courseTeachingTeam.getTeacherId()) &&
                                courseTeachingTeam.getTeacherId().equals(usersInfo.getId()))
                        .findFirst()
                        .orElse(null);
                if (Objects.nonNull(usersInfoResource)) {
                    teacherInfoVo.setAvatarPath(usersInfoResource.getAvatarPath());
                    teacherInfoVo.setAvatarInnerUrl(usersInfoResource.getAvatarInnerUrl());
                    teacherInfoVo.setAvatarOuterUrl(usersInfoResource.getAvatarOuterUrl());
                    teacherInfoVo.setCollegeId(usersInfoResource.getCollegeId());
                    teacherInfoVo.setCollegeName(usersInfoResource.getCollegeName());
                }
            }

            setTeacherPhotoInfo(courseTeachingTeam, teachingTeamTeachers, teacherInfoVo);

            teacherInfoVoList.add(teacherInfoVo);
        }
        courseTeachingTeamResource.setTeacherList(teacherInfoVoList);
        if (CollectionUtils.isNotEmpty(teacherInfoVoList) && Objects.nonNull(isStatistics) && isStatistics) {
            getCourseTeachingTeamStatistics(courseTeachingTeamResource, teacherInfoVoList);
        }
        return courseTeachingTeamResource;
    }

    private static void setTeacherPhotoInfo(CourseTeachingTeam courseTeachingTeam,
                                            List<TeachingTeamTeacher> teachingTeamTeachers,
                                            TeacherInfoVo teacherInfoVo) {

        String teacherId;
        if (courseTeachingTeam.getTeacherDataSource().getValue() == TeacherDataSource.OFF_CAMPUS_USER.getValue()) {
            teacherId = courseTeachingTeam.getId();
        } else {
            teacherId = courseTeachingTeam.getTeacherId();
        }

        TeachingTeamTeacher filter = teachingTeamTeachers.stream()
                .filter(o-> teacherId.equals(o.getTeacherId()))
                .findFirst().orElse(null);
        if (Objects.nonNull(filter)) {
            teacherInfoVo.setPhotoFileDisplayName(filter.getPhotoFileDisplayName());
            teacherInfoVo.setPhotoFileRealName(filter.getPhotoFileRealName());
            teacherInfoVo.setPhotoFileType(filter.getPhotoFileType());
            teacherInfoVo.setPhotoInnerUrl(filter.getPhotoInnerUrl());
            teacherInfoVo.setPhotoOuterUrl(filter.getPhotoOuterUrl());
            teacherInfoVo.setPhotoFilePath(filter.getPhotoFilePath());
            teacherInfoVo.setPhotoFileSize("");
            teacherInfoVo.setPhotoFileSize(ObjectUtils.isEmpty(filter.getPhotoFileSize())
                    ?"": filter.getPhotoFileSize() + "");
        } else {
            teacherInfoVo.setPhotoFileDisplayName("");
            teacherInfoVo.setPhotoFileRealName("");
            teacherInfoVo.setPhotoFileType("");
            teacherInfoVo.setPhotoInnerUrl("");
            teacherInfoVo.setPhotoOuterUrl("");
            teacherInfoVo.setPhotoFilePath("");
            teacherInfoVo.setPhotoFileSize("");
        }
    }

    private void getCourseTeachingTeamStatistics(CourseTeachingTeamResource courseTeachingTeamResource,
                                                 List<TeacherInfoVo> teacherInfoVoList) {
        courseTeachingTeamResource.setCampusTeacherNum((int) teacherInfoVoList
                .stream()
                .filter(t -> TeacherDataSource.CAMPUS_USER.getValue() == t.getDataSource())
                .count());
        courseTeachingTeamResource.setOffCampusTeacherNum((int) teacherInfoVoList
                .stream()
                .filter(t -> TeacherDataSource.OFF_CAMPUS_USER.getValue() == t.getDataSource())
                .count());
        Map<String, List<TeacherInfoVo>> jobTitleGroupMap = teacherInfoVoList
                .stream()
                .filter(t -> StringUtils.isNotBlank(t.getJobTitle()))
                .collect(Collectors.groupingBy(TeacherInfoVo::getJobTitle));
        List<JobTitleStatisticsResource> jobTitleStatisticsResourceList = new ArrayList<>();
        jobTitleGroupMap.forEach((k, v) -> {
            JobTitleStatisticsResource jobTitleStatisticsResource = new JobTitleStatisticsResource();
            jobTitleStatisticsResource.setJobTitle(k);
            jobTitleStatisticsResource.setTeacherNum(v.size());
            jobTitleStatisticsResourceList.add(jobTitleStatisticsResource);
        });
        courseTeachingTeamResource.setJobTitleStatisticsList(jobTitleStatisticsResourceList);
        List<OrgStatisticsResource> orgStatisticsResourceList = new ArrayList<>();
        Map<String, List<TeacherInfoVo>> collegeGroupMap = teacherInfoVoList
                .stream()
                .filter(t -> StringUtils.isNotBlank(t.getCollegeId()))
                .collect(Collectors.groupingBy(TeacherInfoVo::getCollegeId));
        collegeGroupMap.forEach((k, v) -> {
            OrgStatisticsResource orgStatisticsResource = new OrgStatisticsResource();
            orgStatisticsResource.setOrgName(v.get(0).getCollegeName());
            orgStatisticsResource.setTeacherNum(v.size());
            orgStatisticsResourceList.add(orgStatisticsResource);
        });
        Map<String, List<TeacherInfoVo>> schoolGroupMap = teacherInfoVoList
                .stream()
                .filter(t -> StringUtils.isNotBlank(t.getSchoolName()))
                .collect(Collectors.groupingBy(TeacherInfoVo::getSchoolName));

        schoolGroupMap.forEach((k, v) -> {
            OrgStatisticsResource orgStatisticsResource = new OrgStatisticsResource();
            orgStatisticsResource.setOrgName(k);
            orgStatisticsResource.setTeacherNum(v.size());
            orgStatisticsResourceList.add(orgStatisticsResource);
        });
        courseTeachingTeamResource.setOrgStatisticsList(orgStatisticsResourceList);
    }

    public List<UsersInfoResource> getCourseTeachingTeamTeacherInfo(List<CourseTeachingTeam> courseTeachingTeamList) {
        List<Users> users = courseTeachingTeamList
                .stream()
                .filter(c -> Objects.nonNull(c.getTeacherId()))
                .map(courseTeachingTeam -> new Users() {{
                    this.setUserId(courseTeachingTeam.getTeacherId());
                    this.setOpenId("");
                }}).collect(Collectors.toList());
        return authorityApiService.getUsersInfo(users);
    }

    public boolean judgeTeacherInCourseTeachingTeam(String courseId, String teacherId) {
        return courseTeachingTeamRepository.countByCourseIdAndTeacherId(courseId, teacherId) > 0;
    }

    @Async
    public void createCourseTeachingTeamEvent(CourseTeachingTeam courseTeachingTeam) {
        CourseTeachingTeamEvent courseTeachingTeamEvent = eventService.buildCourseTeachingTeamEvent(courseTeachingTeam);
        eventService.sendCourseTeachingTeamEvent(courseTeachingTeamEvent);
    }

    public void sendCourseTeachingTeamList(List<CourseTeachingTeam> resultCourseTeachingTeamList) {
        resultCourseTeachingTeamList.forEach(courseTeachingTeam -> {
            createCourseTeachingTeamEvent(courseTeachingTeam);
        });
    }

    /**
     * 职称接口
     */
    public List<Userbaseinfoandcollegevo> coursemanagementTeacherinfosGetService(String teacherNoOrName) {
        List<Userbaseinfoandcollegevo> teacherInfoVoByTeacherNoOrName = getTeacherInfoVoByTeacherNoOrName(teacherNoOrName);
        if (Objects.nonNull(teacherInfoVoByTeacherNoOrName)) {
            teacherInfoVoByTeacherNoOrName.forEach(p -> {
                ArrayList<String> jobTitleArr = new ArrayList<>();
                List<Map<String, String>> courseTeachingTeamList = findDistinctByTeacherNoAndCollegId(p.getCollegeId(), p.getUserNo());
                List<String> aArray = new ArrayList<>();
                courseTeachingTeamList.forEach(item -> {
                    aArray.add(item.get("a"));
                });
                aArray.forEach(aitem -> {
                    if (!"-1".equals(aitem)) {
                        jobTitleArr.add(aitem);
                    }
                });
                p.setJobTitleOptions(jobTitleArr);
            });
        }

        return teacherInfoVoByTeacherNoOrName;
    }


    public List<Userbaseinfoandcollegevo> getTeacherInfoVoByTeacherNoOrName(String teacherNoOrName) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuffer sb = new StringBuffer();
        String key = Md5Utils.md5(signKey);
        try {
            String url = sb
                    .append(authorityApi)
                    .append("api/v1/")
                    .append("users/teacherinfos?teacherNoOrName=" + teacherNoOrName + "&validCode=" + key)
                    .toString();

            ResponseEntity<List<Userbaseinfoandcollegevo>> exchange =
                    restTemplate.exchange(url, HttpMethod.GET, null,
                            new ParameterizedTypeReference<List<Userbaseinfoandcollegevo>>() {
                            });
            return exchange.getBody();
        } catch (Exception e) {
            LOGGER.error("getTeacherInfoVoByTeacherNoOrName->", e);
            return null;
        }
    }


    List<Map<String, String>> findDistinctByTeacherNoAndCollegId(String collegeId, String teacherNo) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT IFNULL(t.job_title,'-1') as a FROM `tb_course_teaching_team` t " +
                "INNER JOIN tb_course c ON t.course_id = c.id " +
                "INNER JOIN tb_college g ON c.college_id = g.id " +
                "WHERE g.id =:collegeId AND t.teacher_no=:teacherNo " +
                "GROUP BY job_title ORDER BY t.modify_time DESC LIMIT 1";
        paramMap.put("collegeId", collegeId);
        paramMap.put("teacherNo", teacherNo);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        List<Map<String, String>> resultList = query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
        return resultList;

    }


}

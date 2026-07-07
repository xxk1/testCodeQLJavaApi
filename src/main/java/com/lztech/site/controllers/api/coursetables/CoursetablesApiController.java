package com.lztech.site.controllers.api.coursetables;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseParms;
import com.lztech.domain.model.coursetabledetailteacher.CourseAndMembers;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.terms.TermRepository;
import com.lztech.site.resource.course.StudentHomepageCourse;
import com.lztech.site.resource.coursetable.CourseTableResource;
import com.lztech.site.resource.coursetable.CourseTableRunningTerm;
import com.lztech.site.resource.term.TermResource;
import com.lztech.site.service.coursetable.*;
import com.lztech.site.service.preparelessonplugin.PrepareLessonPluginService;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.CourseResource;
import com.lztech.site.viewmodel.assistantcourse.AssistantCourseParam;
import com.lztech.site.viewmodel.assistantcourse.AssistantCourseVo;
import com.lztech.site.viewmodel.coursetable.*;
import com.lztech.site.viewmodel.coursetabledetail.RealTimeSchedule;
import com.lztech.site.viewmodel.coursetableinfo.CourseTableInfoByRoomIdAndNowResource;
import com.lztech.site.viewmodel.coursetableinfo.CourseTableInfoResource;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.group.TeachingClassVo;
import com.lztech.site.viewmodel.preparelessonplugin.TeachingCourseInfo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static com.lztech.domain.model.term.enums.TermType.getTermType;
import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.RequestUtils.checkValidCode;
import static com.lztech.site.until.ValidUtils.checkAuthCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-11T05:53:37.381Z")
@Controller
public class CoursetablesApiController implements CoursetablesApi {
    private final Logger log = LoggerFactory.getLogger(CoursetablesApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    @Value("${studentType}")
    private Integer defaultStudentType;
    @Autowired
    private CourseTableService courseTableService;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private AssistantCourseService assistantCourseService;
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private PrepareLessonPluginService prepareLessonPluginService;
    @Autowired
    private TeacherCourseTableService teacherCourseTableService;
    @Autowired
    private TermService termService;

    @Autowired
    public CoursetablesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<CourseResource>> coursetablesGet(
            @NotNull @ApiParam(value = "课程ID,多个用“,”隔开", required = true) @Valid @RequestParam(value = "courseIds", required = true) String courseIds,
            @NotNull @ApiParam(value = "老师Id", required = true) @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "学年", required = true) @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true) @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "加密验证码courseIds=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {


        try {
            if (StringUtils.isAnyBlank(courseIds, schoolYear, validCode) || term == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            String validKey = "courseIds=" + courseIds + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<CourseResource> courseResourceList = courseTableService.getCourseTableResource(courseIds, schoolYear, term, teacherId);

            return new ResponseEntity<List<CourseResource>>(courseResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("coursetablesIdsGet" , e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CourseResource>> courseTablesTeacherCourseListGet(
            @NotNull @ApiParam(value = "老师Id", required = true) @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "学年", required = true) @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true) @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "加密验证码userId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "允许的授课学生类型的开课") @Valid
            @RequestParam(value = "courseStudentTypes", required = false) String courseStudentTypes,
            @ApiParam(value = "是否需要区分授课学生类型") @Valid @RequestParam(value = "isNeedDistinguish", required = false)
            Boolean isNeedDistinguish,
            @ApiParam(value = "关联的授课类型") @Valid
            @RequestParam(value = "relatedScheduleTypes", required = false) String relatedScheduleTypes) {
        try {
            if (StringUtils.isAnyBlank(userId, schoolYear, validCode) || term == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            String validKey = "userId=" + userId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (StringUtils.isBlank(courseStudentTypes)) {
                courseStudentTypes = String.valueOf(defaultStudentType);
            }

            List<CourseResource> courseResourceList = courseTableService.getTeacherIdCourseTableList(userId, schoolYear, term, courseStudentTypes,
                    isNeedDistinguish,relatedScheduleTypes);

            return new ResponseEntity<List<CourseResource>>(courseResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("coursetablesIdsGet" , e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<StudentHomepageCourse>> coursetablesUserIdGet(
            @ApiParam(value = "学生Id", required = true)
            @PathVariable("userId") String userId,
            @NotNull @ApiParam(value = "课程ID,多个用“,”隔开", required = true)
            @Valid @RequestParam(value = "courseIds", required = true) String courseIds,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "加密验证码userId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(userId, courseIds, schoolYear, validCode) || term == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String validKey = "userId=" + userId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            List<StudentHomepageCourse> courseResourceList = courseTableService.getStudentCourseTableResource(
                    userId, courseIds, schoolYear, term);
            return new ResponseEntity<List<StudentHomepageCourse>>(courseResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("coursetablesIdsGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> coursetablesPost(@NotNull @ApiParam(value = "加密验证码courseIds=?&signKey=123123", required = true)
                                                 @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                 @ApiParam(value = "课表列表", required = true)
                                                 @Valid @RequestBody List<CourseTableResource> courseTables) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (courseTables.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            courseTableService.saveCourseTables(courseTables);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("coursesPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private StudentScheduleService studentScheduleService;

    public ResponseEntity<List<CourseTableDate>> getCourseTablesDayOrWeek(
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "查询课表条件集合", required = true)
            @Valid @RequestBody CourseParms courseparms) {
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<CourseTableDate> courseTableDate = courseTableService.getCourseTableByRoomId(courseparms);
//            List<CourseTableDate> courseTableDate =  studentScheduleService.getCourseTableByRoomId(courseparms);
            if (courseTableDate == null || courseTableDate.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(courseTableDate, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("getCourseTablesDayOrWeek -> ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTableBaseInfo> coursetablesIdBaseinfoGet(
            @ApiParam(value = "课表id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "验证码：id={id}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "请求的开课类型(0:本科，1：研究生)", required = false)
            @Valid @RequestParam(value = "type", required = false) Integer type) {

        try {
            if (StringUtils.isAnyEmpty(id, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return courseTableService.getCourseTableBaseInfo(id, type);
        } catch (Exception e) {
            log.error("coursetablesIdGet:{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableInfoResource>> getCourseTableInfoListResource(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "0:数据对接；1:系统录入；2:外部导入；3:自主开班；4:备课夹新建课程；5:二次排课（注：参数为空则查询全部）")
            @Valid @RequestParam(value = "courseInfoSource", required = false) Integer courseInfoSource) {

        try {

            if (StringUtils.isAnyEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return courseTableService.getCourseTableInfoListResource(courseInfoSource);
        } catch (Exception e) {
            log.error("getCourseTableInfoListResource:{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableInfoByRoomIdAndNowResource>> getCourseTableByRoomIdAndNowTime(
            @NotNull @ApiParam(value = "教室Id", required = true)
            @Valid @RequestParam(value = "roomId", required = true) String roomId,
            @NotNull @ApiParam(value = "验证码（roomId=?&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "0:数据对接；1:系统录入；2:外部导入；3:自主开班；4:备课夹新建课程；5:二次排课（注：参数为空则查询全部）")
            @Valid @RequestParam(value = "courseInfoSource", required = false) Integer courseInfoSource) {
        try {

            if (StringUtils.isAnyEmpty(validCode, roomId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("roomId", roomId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return courseTableService.getCourseTableByRoomIdAndNowTime(courseInfoSource, roomId);
        } catch (Exception e) {
            log.error("getCourseTableInfoListResource:{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableBasicInfo>> coursetablesBasicinfosGet(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {

            if (StringUtils.isAnyEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return courseTableService.getCourseTablesBasicInfos();
        } catch (Exception e) {
            log.error("coursetablesBasicinfosGet:{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableTeacherResource>> coursetableTeachersGet(@NotNull
                                                                                   @ApiParam(value = "课表id集合，多个使用逗号分割", required = true)
                                                                                   @Valid @RequestParam(value = "courseTableIds", required = true)
                                                                                   String courseTableIds,
                                                                                   @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)",
                                                                                           required = true)
                                                                                   @Valid @RequestParam(value = "validCode", required = true)
                                                                                   String validCode) {

        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);

        }
        if (!checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return courseTableService.getCourseTableTeacherInfo(courseTableIds);
        } catch (Exception e) {
            log.error("coursetableTeachersGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<StatisticsResource>> coursetableStatisticsGet(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);

        }
        if (!checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return courseTableService.getCourseTableStatistics();
        } catch (Exception e) {
            log.error("coursetableStatisticsGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<StatisticsResource>> coursetableTypeStatisticsGet(
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyEmpty(schoolYear, validCode) || term == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<StatisticsResource> statisticsResources = courseTableService.getCourseTableTypeStatistics(schoolYear, term);
            return new ResponseEntity<>(statisticsResources, HttpStatus.OK);
        } catch (Exception e) {
            log.error("coursetableTypeStatisticsGet->{}", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseCategoryResource>> coursetablesCoursecategorysGet(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);

        }
        if (!checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return courseTableService.getCourseCategorys();
        } catch (Exception e) {
            log.error("coursetablesCoursecategorysGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<RealTimeSchedule> realtimescheduleGet(
            @NotNull @ApiParam(value = "教室id", required = true)
            @Valid @RequestParam(value = "roomId", required = true) String roomId,
            @NotNull @ApiParam(value = "加密验证码roomId={xxx}&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, roomId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);

        }
        if (!ValidUtils.checkValidCode("roomId", roomId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return courseTableService.getRealtimeScheduleGet(roomId);
        } catch (Exception e) {
            log.error("realtimescheduleGet->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeachingDetail>> courseTablesTeachingschedulesPost(
            @ApiParam(value = "教学日历查询条件", required = true)
            @Valid @RequestBody CourseTableSearch courseTableSearch,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(courseTableSearch.getSchoolYear(), courseTableSearch.getTerm(), validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            if (ObjectUtils.isEmpty(courseTableSearch.getStudentType())) {
                courseTableSearch.setStudentType(defaultStudentType);
            }
            List<TeachingDetail> teachingDetailList = courseTableService.getTeachingDetailList(courseTableSearch);
            return new ResponseEntity<>(teachingDetailList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("courseTablesTeachingschedulesPost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeachingClassVo>> teacherClassListGet(
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "老师Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "班级名称")
            @Valid @RequestParam(value = "groupName", required = false) String groupName,
            @ApiParam(value = "允许的授课学生类型的开课")
            @Valid @RequestParam(value = "courseStudentTypes", required = false) String courseStudentTypes,
            @ApiParam(value = "是否需要区分授课学生类型") @Valid @RequestParam(value = "isNeedDistinguish", required = false)
            Boolean isNeedDistinguish) {
        try {
            if (StringUtils.isAnyEmpty(schoolYear, userId, validCode) || term == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            if (StringUtils.isBlank(courseStudentTypes)) {
                courseStudentTypes = String.valueOf(defaultStudentType);
            }
            List<TeachingClassVo> teachingClassVos = courseTableService.getTeachingClassVos(schoolYear, term, userId,
                    groupName, courseStudentTypes, isNeedDistinguish);
            return new ResponseEntity<>(teachingClassVos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("teacherClassListGet:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ExperimentalCoursePage> getExperimentalCourseList(
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "当前页数", required = true)
            @Valid @RequestParam(value = "page", required = true) Integer page,
            @NotNull @ApiParam(value = "每页条数", required = true)
            @Valid @RequestParam(value = "row", required = true) Integer row,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程名称")
            @Valid @RequestParam(value = "courseName", required = false) String courseName) {
        try {
            if (StringUtils.isAnyBlank(schoolYear, validCode) || term == null || term == 0
                    || page == null || page == 0 || row == null || row == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            ExperimentalCoursePage experimentalCoursePage = courseTableService.getExperimentalCourseList
                    (schoolYear, term, page, row, courseName);
            return new ResponseEntity<>(experimentalCoursePage, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getExperimentalCourseList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<ExperimentalCourseProject>> getExperimentalCourseProjectList(
            @NotNull @ApiParam(value = "开课课表id", required = true)
            @Valid @RequestParam(value = "courseTableId", required = true) String courseTableId,
            @NotNull @ApiParam(value = "验证码：courseTableId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {

            if (StringUtils.isAnyBlank(courseTableId, validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode("courseTableId", courseTableId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<ExperimentalCourseProject> experimentalCourseProjectList = courseTableService
                    .getExperimentalCourseProjectList(courseTableId);
            return new ResponseEntity<>(experimentalCourseProjectList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getExperimentalCourseProjectList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseTableTermResource> getCourseTableTermInfo(
            @NotNull @ApiParam(value = "courseTableId={xxx}&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "开课信息Id", required = true)
            @Valid @RequestParam(value = "courseTableId", required = true) String courseTableId) {

        try {

            if (StringUtils.isAnyBlank(courseTableId, validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode("courseTableId", courseTableId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            Optional<CourseTable> optionalCourseTable = courseTableRepository.findById(courseTableId);

            if (optionalCourseTable.isPresent()) {

                CourseTable courseTable = optionalCourseTable.get();
                Term term = termRepository.findBySchoolYearAndTerm(courseTable.getSchoolYear(), getTermType(courseTable.getTerm()));
                if (term == null) {
                    return new ResponseEntity(ErrorResult.dataNotExistError("学期"), HttpStatus.NOT_FOUND);
                }
                CourseTableTermResource courseTableTermInfo = courseTableService
                        .getCourseTableTermInfo(term);
                return new ResponseEntity<>(courseTableTermInfo, HttpStatus.OK);

            } else {
                return new ResponseEntity(ErrorResult.dataNotExistError("开课"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("getCourseTableTermInfo->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseAndMembers>> getTeacherCourseAndMembers(
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "老师Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "加密验证码userId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(schoolYear, userId, validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String validKey = "userId=" + userId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            List<CourseAndMembers> courseAndMembersList = courseTableService.getTeacherIdCourseAndMembers(userId, schoolYear, term);
            return new ResponseEntity<>(courseAndMembersList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getTeacherCourseAndMembers->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TermCourseStatisticsResource>> coursetableTermscoursestatisticsGet(
            @NotNull @ApiParam(value = "取前多少条", required = true)
            @Valid @RequestParam(value = "topNum", required = true) Integer topNum,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyEmpty(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        List<TermCourseStatisticsResource> termCourseStatisticsResources =
                courseTableService.getTermCourseStatisticsResourceList(topNum);
        return new ResponseEntity<List<TermCourseStatisticsResource>>(termCourseStatisticsResources, HttpStatus.OK);
    }


    public ResponseEntity<List<CourseInfoModel>> getCourseInfosBySchoolYearAndTerm(
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学年") @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @ApiParam(value = "学期") @Valid @RequestParam(value = "term", required = false) String term) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<CourseInfoModel> courseTableList = courseTableService.getCourseInfosBySchoolYearAndTerm(schoolYear, term);
            return new ResponseEntity(courseTableList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseInfosBySchoolYearAndTerm->{}", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTableRunningTerm> getCourseTableStatisticsByStartClass(
            @NotNull @ApiParam(value = "课程ID", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(courseId, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            CourseTableRunningTerm courseTableRunningTerm = courseTableService.getCourseTableStatisticsByStartClass(courseId);
            return new ResponseEntity(courseTableRunningTerm, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseTableStatisticsByStartClass->{}", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableCollegeResource>> coursetableCollegesGet(
            @NotNull @ApiParam(value = "课程id集合，多个使用逗号分割", required = true)
            @Valid @RequestParam(value = "courseIds", required = true) String courseIds,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, courseIds)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseTableCollegeResource> courseTableCollegeResources = courseTableService.getCourseTableColleges(courseIds);
            return new ResponseEntity(courseTableCollegeResources, HttpStatus.OK);
        } catch (Exception e) {
            log.error("coursetableCollegesGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TermResource>> getCourseTableOperationTerms(
            @NotNull @ApiParam(value = "课程ID", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(courseId, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<TermResource> termResourceList = courseTableService.getCourseTableOperationTerms(courseId);
            return new ResponseEntity(termResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseTableOperationTerms->{}", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<AssistantCourseVo>> getAssistantTeacherCourseTableList(
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "助教课程入参", required = true)
            @Valid @RequestBody AssistantCourseParam assistantCourseParam) {
        try {
            if (ObjectUtils.isEmpty(assistantCourseParam)
                    || StringUtils.isAnyBlank(assistantCourseParam.getSchoolYear(), validCode)
                    || assistantCourseParam.getTerm() == null || CollectionUtils.isEmpty(assistantCourseParam.getTeacherAndCourseList())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<AssistantCourseVo> assistantCourseVoList = assistantCourseService.getAssistantTeacherCourseTableList(assistantCourseParam);

            return new ResponseEntity<List<AssistantCourseVo>>(assistantCourseVoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getAssistantTeacherCourseTableList" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeachingCourseInfo>> getTeacherCourseTableCourseStructures(
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "老师Id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(teacherId, schoolYear, validCode) || term == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            String validKey = "teacherId=" + teacherId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            List<TeachingCourseInfo> teachingCourseInfoList =
                    courseTableService.getTeacherCourseTableCourseStructures(teacherId, schoolYear, term);
            if (teachingCourseInfoList.size() > 0) {
                return new ResponseEntity(teachingCourseInfoList, HttpStatus.OK);
            } else {
                return new ResponseEntity(ErrorResult.dataNotExistError("教师所属课程"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("getTeacherCourseTableCourseStructures->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeacherCourseResource>> getTeacherCourseInfos(
            @NotNull @ApiParam(value = "老师Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "加密验证码userId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "允许的授课学生类型的开课")
            @Valid @RequestParam(value = "courseStudentTypes", required = false) String courseStudentTypes,
            @ApiParam(value = "是否需要区分授课学生类型")
            @Valid @RequestParam(value = "isNeedDistinguish", required = false) Boolean isNeedDistinguish) {
        try {
            if (StringUtils.isAnyBlank(userId, validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String validKey = "userId=" + userId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (StringUtils.isBlank(courseStudentTypes)) {
                courseStudentTypes = String.valueOf(defaultStudentType);
            }
            Term term = termService.getNowTerm();
            if (ObjectUtils.isEmpty(term)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<TeacherCourseResource> teacherCourseResourceList =
                    teacherCourseTableService.getTeacherCourseInfos(userId, term.getSchoolYear(), term.getTerm().getIndex(), courseStudentTypes,
                            isNeedDistinguish);

            return new ResponseEntity<>(teacherCourseResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getTeacherCourseInfos " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private TeacherScheduleService teacherScheduleService;

    public ResponseEntity<List<CourseTableDate>> getTeacherCourseTablesDayOrWeek(@NotNull
                                                                                 @ApiParam(value = "加密验证码&signKey=123123", required = true)
                                                                                 @Valid @RequestParam(value = "validCode", required = true)
                                                                                 String validCode,
                                                                                 @ApiParam(value = "查询课表条件集合", required = true)
                                                                                 @Valid @RequestBody
                                                                                 CourseParms courseparms) {
        if (!checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseTableDate> courseTableDateList = teacherScheduleService.getTeacherCourseTablesDayOrWeek(courseparms);
            return new ResponseEntity<List<CourseTableDate>>(courseTableDateList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getTeacherCourseTablesDayOrWeek ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CourseTableDate>> getStudentCourseTablesDayOrWeek(@NotNull
                                                                                 @ApiParam(value = "加密验证码&signKey=123123", required = true)
                                                                                 @Valid @RequestParam(value = "validCode", required = true)
                                                                                 String validCode,
                                                                                 @ApiParam(value = "查询课表条件集合", required = true)
                                                                                 @Valid @RequestBody
                                                                                 CourseParms courseparms) {
        if (!checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseTableDate> courseTableDateList = studentScheduleService.getCourseTableByRoomId(courseparms);
            return new ResponseEntity<List<CourseTableDate>>(courseTableDateList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getStudentCourseTablesDayOrWeek ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseInfoCode>> getCourseInfoCodes(
            @NotNull @ApiParam(value = "课次数", required = true)
            @Valid @RequestParam(value = "classTime", required = true) Integer classTime,
            @NotNull @ApiParam(value = "课次结课状态", required = true)
            @Valid @RequestParam(value = "courseCompletionStatus", required = true) Boolean courseCompletionStatus,
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode, @ApiParam(value = "学年")
            @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @ApiParam(value = "学期") @Valid @RequestParam(value = "term", required = false) String term) {

        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<CourseInfoCode> courseInfoCodeList = courseTableService.getCourseInfoCodes(schoolYear, term, classTime, courseCompletionStatus);
            return new ResponseEntity(courseInfoCodeList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseInfoCodes->{}", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<String>> getCourseTableIdsByCollegeIds(
            @ApiParam(value = "学院列表" ,required=true )  @Valid @RequestBody List<String> collegeIds,
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode) || ObjectUtils.isEmpty(collegeIds)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<String> courseTableIds = courseTableService.getCourseTableIdsByCollegeIds(collegeIds);
            return new ResponseEntity(courseTableIds, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseTableIdsByCollegeIds->{}", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CourseTableIdAndNameInfo>> getCourseTableInfoByCollegeIds(
            @ApiParam(value = "学院ID列表" ,required=true )  @Valid @RequestBody List<String> collegeIds,
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode) || ObjectUtils.isEmpty(collegeIds)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<CourseTableIdAndNameInfo> courseTableIds = courseTableService.getCourseTableInfoByCollegeIds(collegeIds);
            return new ResponseEntity(courseTableIds, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseTableInfoByCollegeIds->{}", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

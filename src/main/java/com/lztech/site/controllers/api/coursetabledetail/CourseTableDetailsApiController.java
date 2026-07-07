package com.lztech.site.controllers.api.coursetabledetail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.term.Term;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailResource;
import com.lztech.site.resource.coursetabledetail.*;
import com.lztech.site.service.coursetabledetail.CourseTableDetailExtendService;
import com.lztech.site.service.coursetabledetail.CourseTableDetailLogicService;
import com.lztech.site.service.coursetabledetail.CourseTableDetailService;
import com.lztech.site.service.teachingcalendarcoursetable.TeachingCalendarCourseTableService;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.CourseTableSimpleVo;
import com.lztech.site.viewmodel.coursetable.*;
import com.lztech.site.viewmodel.coursetabledetail.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingcalendarcoursetabledetail.LabResource;
import com.lztech.site.viewmodel.teachingcalendarcoursetabledetail.TeachingCalendarCourseTableDetail;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.DateUtils.DATE_TIME_MINUTES;
import static com.lztech.site.until.DateUtils.checkDateTimeFormatter;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-15T08:34:51.679Z")

@Controller
public class CourseTableDetailsApiController implements CourseTableDetailsApi {

    private final Logger logger = LoggerFactory.getLogger(CourseTableDetailsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Value("${studentType}")
    private Integer defaultStudentType;

    @Autowired
    private CourseTableDetailService courseTableDetailService;
    @Autowired
    private TeachingCalendarCourseTableService teachingCalendarCourseTableService;
    @Autowired
    private TermService termService;
    @Autowired
    private CourseTableDetailLogicService courseTableDetailLogicService;
    @Autowired
    private CourseTableDetailExtendService courseTableDetailExtendService;

    @org.springframework.beans.factory.annotation.Autowired
    public CourseTableDetailsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> coursetabledetailsIdCoursestatusPost(@ApiParam(value = "课表明细id", required = true)
                                                                     @PathVariable("id") String id,
                                                                     @NotNull @ApiParam(value = "验证码：id=?&signKey=123123(md5加密)", required = true)
                                                                     @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                                     @ApiParam(value = "更新课表的课次状态信息", required = true)
                                                                     @Valid @RequestBody UpdateCourseStatusInfo updateCourseStatusInfo) {

        try {
            if (StringUtils.isAnyBlank(id, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
            }

            return courseTableDetailService.updateCourseTableDetailCourseStatus(id, updateCourseStatusInfo);
        } catch (Exception e) {
            logger.error("coursetabledetailsIdCoursestatusPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTableInfo> courseTableDetailsIdGet(@ApiParam(value = "课表明细id", required = true)
                                                                   @PathVariable("id") String id,
                                                                   @NotNull @ApiParam(value = "验证码：id=?&signKey=123123(md5加密)", required = true)
                                                                   @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (StringUtils.isAnyBlank(id, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
            }

            return courseTableDetailService.getCourseTableInfoByCourseTableDetailId(id);
        } catch (Exception e) {
            logger.error("teachersIdClassinformationGet:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailTimeVo>> getCourseTableDetailTimeVoList(
            @ApiParam(value = "课表列表", required = true)
            @Valid @RequestBody List<String> courseTableDetailIdList,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (CollectionUtils.isEmpty(courseTableDetailIdList)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.UNAUTHORIZED);
            }
            List<CourseTableDetailTimeVo> courseTableDetailTimeVoList =
                    courseTableDetailService.getCourseTableDetailTimeVoList(courseTableDetailIdList);
            return new ResponseEntity<>(courseTableDetailTimeVoList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCourseTableDetailTimeVoList:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> coursetabledetailsPost(@NotNull @ApiParam(value = "&signKey=123123", required = true)
                                                       @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                       @ApiParam(value = "课表列表", required = true)
                                                       @Valid @RequestBody List<CourseTableDetailResource> courseTableDetails) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (courseTableDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (courseTableDetailService.getCourseTableDetailCount() > 0) {
                courseTableDetailService.deleteNowDateAndAfterCourse();
            }
            courseTableDetailService.saveCourseTableDetails(courseTableDetails);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("coursetabledetailsPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTableSimpleVo> coursetabledetailsTeachingcalendarPost(@NotNull @ApiParam(value = "&signKey=123123", required = true)
                                                                                      @Valid @RequestParam(value = "validCode", required = true)
                                                                                      String validCode,
                                                                                      @ApiParam(value = "课表详情", required = true) @Valid
                                                                                      @RequestBody TeachingCalendarCourseTableDetail
                                                                                              courseTableDetails) {

        if (StringUtils.isAnyBlank(validCode, courseTableDetails.getCourseDate(), courseTableDetails.getGroupId(), courseTableDetails.getSegments(),
                courseTableDetails.getWeek(), courseTableDetails.getSectionMaxEndTime(), courseTableDetails.getSectionMaxEndTime()) ||
                CollectionUtils.isEmpty(courseTableDetails.getLabList()) ||
                CollectionUtils.isEmpty(courseTableDetails.getTeacherList())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.UNAUTHORIZED);
        }
        try {
            return teachingCalendarCourseTableService.saveTeachingCalendarCourseTable(courseTableDetails);
        } catch (Exception e) {
            logger.error("coursetabledetailsTeachingcalendarPost->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTableDetailVo> courseTableDetailsFindcoursetablePost(
            @ApiParam(value = "课表查询条件", required = true)
            @Valid @RequestBody CourseTableDetailData courseTableDetailData,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(courseTableDetailData.getStartTime(), courseTableDetailData.getEndTime())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (courseTableDetailData.getPage() == null || courseTableDetailData.getPageSize() == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            if (ObjectUtils.isEmpty(courseTableDetailData.getStudentType())) {
                courseTableDetailData.setStudentType(defaultStudentType);
            }
            return courseTableDetailService.getCourseTableDetailByDate(courseTableDetailData);
        } catch (Exception e) {
            logger.error("courseTableDetailsFindcoursetablePost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> coursetabledetailsCourseTableDetailIdRoomIdItemteacherPost(@NotNull
                                                                                           @ApiParam(value = "id={}&signKey=123123", required = true)
                                                                                           @Valid @RequestParam(value = "validCode", required = true)
                                                                                           String validCode,
                                                                                           @ApiParam(value = "课表详情id", required = true)
                                                                                           @PathVariable("courseTableDetailId")
                                                                                           String courseTableDetailId,
                                                                                           @ApiParam(value = "教室id", required = true)
                                                                                           @PathVariable("id") String id,
                                                                                           @ApiParam(value = "", required = true) @Valid @RequestBody
                                                                                           LabResource labResource) {

        if (StringUtils.isAnyBlank(validCode, courseTableDetailId, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }
        try {
            return teachingCalendarCourseTableService.updateCourseTableItems(courseTableDetailId, id, labResource);
        } catch (Exception e) {
            logger.error("coursetabledetailsCourseTableDetailIdRoomIdItemteacherPost->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<RoomScheduleInformation>> getRoomScheduleInformationList(
            @NotNull @ApiParam(value = "房间Ids（多个用“,”隔开）", required = true)
            @Valid @RequestParam(value = "roomIds", required = true) String roomIds,
            @NotNull @ApiParam(value = "加密验证码roomIds=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, roomIds)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("roomIds", roomIds, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return teachingCalendarCourseTableService.getRoomScheduleInformationList(roomIds);
        } catch (Exception e) {
            logger.error("getRoomScheduleInformationList->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getTimeFrameCourseTableDetailList(
            @NotNull @ApiParam(value = "开始时间（格式：yyyy-MM-dd HH:mm）", required = true)
            @Valid @RequestParam(value = "startTime", required = true) String startTime,
            @NotNull @ApiParam(value = "结束时间（格式：yyyy-MM-dd HH:mm）", required = true)
            @Valid @RequestParam(value = "endTime", required = true) String endTime,
            @NotNull @ApiParam(value = "验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {

            if (StringUtils.isAnyBlank(startTime, endTime, validCode)
                    || !checkDateTimeFormatter(startTime, DATE_TIME_MINUTES)
                    || !checkDateTimeFormatter(endTime, DATE_TIME_MINUTES)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<RoomScheduleInformation> scheduleInformationList = teachingCalendarCourseTableService
                    .getTimeFrameCourseTableDetailList(startTime, endTime);
            return new ResponseEntity<>(scheduleInformationList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getTimeFrameCourseTableDetailList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailSegmentModel>> getCourseTableDetailSegments(
            @NotNull @ApiParam(value = "组id", required = true)
            @Valid @RequestParam(value = "groupId", required = true) String groupId,
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, groupId, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseTableDetailSegmentModel> courseTableDetailSegmentModels =
                    courseTableDetailService.getCourseTableDetailSegments(groupId, teacherId);
            return new ResponseEntity<>(courseTableDetailSegmentModels, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCourseTableDetailSegments：", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<RoomSegmentUsageModel>> getCourseTableDetailRoomSegmentUsages(
            @NotNull @ApiParam(value = "教室id", required = true)
            @Valid @RequestParam(value = "roomId", required = true) String roomId,
            @NotNull @ApiParam(value = "上课日期", required = true)
            @Valid @RequestParam(value = "courseDate", required = true) String courseDate,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, roomId, courseDate)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<RoomSegmentUsageModel> roomSegmentUsageModelList =
                    courseTableDetailService.getCourseTableDetailRoomSegmentUsages(roomId, courseDate);
            return new ResponseEntity<>(roomSegmentUsageModelList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCourseTableDetailRoomSegmentUsages：", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeachingCalendarResource>> getTeachingCalendarResourceList(
            @NotNull @ApiParam(value = "年月(yyyy-MM)", required = true)
            @Valid @RequestParam(value = "yearMonth", required = true) String yearMonth,
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(teacherId, yearMonth, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        if (!courseTableDetailService.isValidDate(yearMonth, "yyyy-MM")) {
            return new ResponseEntity(ErrorResult.customError("年月(yyyy-MM)格式不正确"), HttpStatus.BAD_REQUEST);
        }
        Term nowTerm = termService.getNowTerm();
        if (ObjectUtils.isEmpty(nowTerm)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            List<TeachingCalendarResource> teachingCalendarResourceList =
                    courseTableDetailService.getTeachingCalendarResourceList(nowTerm, yearMonth, teacherId);
            return new ResponseEntity<>(teachingCalendarResourceList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getTeachingCalendarResourceList：", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeachingCalendarResource>> getStudentTeachingCalendarResourceList(
            @NotNull @ApiParam(value = "年月(yyyy-MM)", required = true)
            @Valid @RequestParam(value = "yearMonth", required = true) String yearMonth,
            @NotNull @ApiParam(value = "学生id", required = true)
            @Valid @RequestParam(value = "studentId", required = true) String studentId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(studentId, yearMonth, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        if (!courseTableDetailService.isValidDate(yearMonth, "yyyy-MM")) {
            return new ResponseEntity(ErrorResult.customError("年月(yyyy-MM)格式不正确"), HttpStatus.BAD_REQUEST);
        }
        Term nowTerm = termService.getNowTerm();
        if (ObjectUtils.isEmpty(nowTerm)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            List<TeachingCalendarResource> teachingCalendarResourceList =
                    courseTableDetailService.getStudentTeachingCalendarResourceList(nowTerm, yearMonth, studentId);
            return new ResponseEntity<>(teachingCalendarResourceList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getStudentTeachingCalendarResourceList：", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<WeekCourseTableDetailPage> getCourseTableWeekDetailInfo(
            @NotNull @ApiParam(value = "课程日期", required = true)
            @Valid @RequestParam(value = "courseDate", required = true) String courseDate,
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(courseDate, teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        if (!courseTableDetailService.isValidDate(courseDate, "yyyy-MM-dd")) {
            return new ResponseEntity(ErrorResult.customError("年月(yyyy-MM-dd)格式不正确"), HttpStatus.BAD_REQUEST);
        }
        Term nowTerm = termService.getNowTerm();
        if (ObjectUtils.isEmpty(nowTerm)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            WeekCourseTableDetailPage weekCourseTableDetailPage =
                    courseTableDetailService.getCourseTableWeekDetailInfo(nowTerm, courseDate, teacherId);
            return new ResponseEntity<>(weekCourseTableDetailPage, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCourseTableWeekDetailInfo：", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailResourceVo>> getCourseTableDetailResourceVoList(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "", required = true) @Valid @RequestBody ParamResource paramResource) {

        if (ObjectUtils.isEmpty(paramResource) || StringUtils.isAnyBlank(validCode, paramResource.getRoomIds(),
                paramResource.getSources(), paramResource.getCourseDate())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseTableDetailResourceVo> resourceVoList =
                    courseTableDetailLogicService.getCourseTableDetailResourceVoList(paramResource.getRoomIds(),
                            paramResource.getSources(), paramResource.getCourseDate());
            return new ResponseEntity<>(resourceVoList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCourseTableDetailResourceVoList：", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CurrentTimeStatisticsModel> getCurrentTimeStatistics(
            @NotNull @ApiParam(value = "&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "查询条件", required = true)
            @Valid @RequestBody CurrentTimeStatisticsParam currentTimeStatisticsParam) {

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CurrentTimeStatisticsModel currentTimeStatisticsModel =
                    courseTableDetailLogicService.getCurrentTimeStatistics(currentTimeStatisticsParam);
            return new ResponseEntity<>(currentTimeStatisticsModel, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCurrentTimeStatistics：", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTableDetailStatusResource> getCourseTableDetailStatusById(
            @ApiParam(value = "课程表详情ID", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "验证码：id={}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "教室Id", required = false)
            @Valid @RequestParam(value = "roomId", required = false) String roomId) {
        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }
        try {
            CourseTableDetailStatusResource courseTableDetailStatusResource =
                    courseTableDetailExtendService.getCourseTableDetailStatusById(id, roomId);
            return new ResponseEntity<>(courseTableDetailStatusResource, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCourseTableDetailStatusById：", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailResourceModel>> getWaitAttendClassCourseTableDetails(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "", required = true)
            @Valid @RequestBody WaitAttendClassCourseTableDetailParam waitAttendClassCourseTableDetailParam
    ) {

        if (StringUtils.isAnyBlank(waitAttendClassCourseTableDetailParam.getCourseDate())
                || CollectionUtils.isEmpty(waitAttendClassCourseTableDetailParam.getCollegeIdList())
        ) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseTableDetailResourceModel> resourceVoList =
                    courseTableDetailLogicService.getWaitAttendClassCourseTableDetails(
                            waitAttendClassCourseTableDetailParam
                    );
            return new ResponseEntity<>(resourceVoList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getWaitAttendClassCourseTableDetails：", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CourseTableTerm>> getCourseTableTermList(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "用户id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId
    ) {
        if (StringUtils.isAnyBlank(validCode, courseId, userId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseTableTerm> courseTableTermList =
                    courseTableDetailLogicService.getCourseTableTermList(courseId, userId);
            return new ResponseEntity<>(courseTableTermList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCourseTableTermList：", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailInfoVo>> getCourseTableDetailListByCourseTableId(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "开课id", required = true)
            @Valid @RequestParam(value = "courseTableId", required = true) String courseTableId) {

        if (StringUtils.isAnyBlank(courseTableId, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            List<CourseTableDetailInfoVo> coursetableResourceVoList =
                    courseTableDetailService.getCourseTableDetailListByCourseTableId(courseTableId);
            return new ResponseEntity<>(coursetableResourceVoList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCourseTableDetailListByCourseTableId：", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.lztech.site.controllers.api.teachingactivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.teachingactivity.TeachingActivityService;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingactivity.*;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-28T08:23:44.244Z")

@Controller
public class TeachingsactivityApiController implements TeachingsactivityApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingsactivityApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TeachingsactivityApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private TeachingActivityService teachingActivityService;

    public ResponseEntity<TeachingActivityStatisticResult> teachingActivityStatistic(
            @ApiParam(value = "教学活动素材统计查询参数", required = true)
            @Valid @RequestBody TeachingActivityStatisticQueryParam teachingActivityStatisticQueryParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            TeachingActivityStatisticResult teachingActivityStatisticResult = teachingActivityService
                    .teachingActivityStatistic(teachingActivityStatisticQueryParam);
            return new ResponseEntity<TeachingActivityStatisticResult>(teachingActivityStatisticResult, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("teachingActivityStatistic:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TeacherActivityStatistic> teacherActivityStatistic(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId
    ) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            TeacherActivityStatistic teacherActivityStatistic = teachingActivityService.teacherActivityStatistic(courseId);
            return new ResponseEntity<TeacherActivityStatistic>(teacherActivityStatistic, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("teacherActivityStatistic:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ActivityTypeStatistic> activityTypeStatistic(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId
    ) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            ActivityTypeStatistic activityTypeStatistic = teachingActivityService.activityTypeStatistic(courseId);
            return new ResponseEntity<ActivityTypeStatistic>(activityTypeStatistic, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("activityTypeStatistic:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseActivityList> courseActivityList(
            @ApiParam(value = "统计查询参数" ,required=true )
            @Valid @RequestBody ActivityListQueryParam activityListQueryParam,
            @ApiParam(value = "课程id",required=true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseActivityList courseActivityList = teachingActivityService.courseActivityList(courseId,activityListQueryParam);
            return new ResponseEntity<CourseActivityList>(courseActivityList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("courseActivityList:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TeachingActivityTypes> getActivityTypes(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            TeachingActivityTypes teachingActivityTypes = teachingActivityService.getActivityTypes();
            return new ResponseEntity<TeachingActivityTypes>(teachingActivityTypes, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getActivityTypes:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.lztech.site.controllers.api.teachingcenter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.coursetabledetail.CourseTableDetailService;
import com.lztech.site.service.teachingcenterstatistics.TeachingCenterStatisticsService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingcenterstatistics.TeachingCenterJobTitleStatistics;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-15T06:15:36.817Z")

@Controller
public class TeachingcenterApiController implements TeachingcenterApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingcenterApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CourseTableDetailService courseTableDetailService;
    @Autowired
    private TeachingCenterStatisticsService teachingCenterStatisticsService;

    @org.springframework.beans.factory.annotation.Autowired
    public TeachingcenterApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Integer> getTeachingCenterTeachingQuantity(
            @NotNull @ApiParam(value = "加密验证码: &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "实验中心id(不传值则查询全部实验中心数据)")
            @Valid @RequestParam(value = "teachingCenterId", required = false) String teachingCenterId) {

        try {

            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            Integer teachingQuantity = courseTableDetailService
                    .getTeachingCenterTeachingQuantity(teachingCenterId);
            return new ResponseEntity<>(teachingQuantity, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getTeachingCenterTeachingQuantity->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getTeacherInClassQuantity(
            @NotNull @ApiParam(value = "加密验证码: &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "实验中心id(不传值则查询全部实验中心数据)")
            @Valid @RequestParam(value = "teachingCenterId", required = false) String teachingCenterId) {
        try {

            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            Integer teacherInClassQuantity = courseTableDetailService
                    .getTeacherInClassQuantity(teachingCenterId);
            return new ResponseEntity<>(teacherInClassQuantity, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getTeacherInClassQuantity->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<TeachingCenterJobTitleStatistics>> courseJobTitleTeacherStatistics(@NotNull
                                                                                                  @ApiParam(value = "学年", required = true)
                                                                                                  @Valid
                                                                                                  @RequestParam(value = "schoolYear",
                                                                                                          required = true)
                                                                                                          String schoolYear,
                                                                                                  @NotNull @ApiParam(value = "学期",
                                                                                                          required = true)
                                                                                                  @Valid @RequestParam(value = "term",
                                                                                                          required = true)
                                                                                                          Integer term,
                                                                                                  @NotNull
                                                                                                  @ApiParam(value = "验证码（&signKey=123123）",
                                                                                                          required = true)
                                                                                                  @Valid @RequestParam(value = "validCode",
                                                                                                          required = true)
                                                                                                          String validCode,
                                                                                                  @ApiParam(value = "实验中心id") @Valid
                                                                                                  @RequestParam(value = "teachingCenterId",
                                                                                                          required = false)
                                                                                                          String teachingCenterId) {

        if (StringUtils.isAnyBlank(validCode, schoolYear) || Objects.isNull(term)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            List<TeachingCenterJobTitleStatistics> teachingCenterJobTitleStatisticsList =
                    teachingCenterStatisticsService.getCourseJobTitleTeacherStatistics(teachingCenterId, schoolYear, term);

            return new ResponseEntity<>(teachingCenterJobTitleStatisticsList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("courseJobTitleTeacherStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.lztech.site.controllers.api.coursetablestatistics;

import com.lztech.site.service.coursetablestatistics.CourseTableStatisticsService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursetable.StudentTypeCourseTableStatisticsResource;
import com.lztech.site.viewmodel.coursetablestatistics.CourseTableStatisticsResource;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-18T04:29:54.572Z")

@Controller
public class CourseTableStatisticsApiController implements CourseTableStatisticsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseTableStatisticsApiController.class);

    @Autowired
    private CourseTableStatisticsService courseTableStatisticsService;

    public ResponseEntity<CourseTableStatisticsResource> getCourseTableStatistics(@NotNull
                                                                                  @ApiParam(value = "加密验证码&signKey=123123",
                                                                                          required = true)
                                                                                  @Valid @RequestParam(value = "validCode", required = true)
                                                                                          String validCode) {

        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseTableStatisticsResource courseTableStatisticsResource = courseTableStatisticsService.getCourseTableStatistics();
            return new ResponseEntity<>(courseTableStatisticsResource, HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("getCourseTableStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<StudentTypeCourseTableStatisticsResource> statisticsCourseTableByStudentType(@NotNull
                                                                                                       @ApiParam(value = "验证码：&signKey=123123" +
                                                                                                               "(md5加密)", required = true)
                                                                                                       @Valid @RequestParam(value = "validCode",
            required = true) String validCode,
                                                                                                       @NotNull @ApiParam(value = "学年", required =
                                                                                                               true) @Valid
                                                                                                       @RequestParam(value = "schoolYear",
                                                                                                               required = true) String schoolYear,
                                                                                                       @NotNull @ApiParam(value = "学期", required =
                                                                                                               true) @Valid
                                                                                                       @RequestParam(value = "term", required =
                                                                                                               true) Integer term) {

        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            StudentTypeCourseTableStatisticsResource resource = courseTableStatisticsService.statisticsCourseTableByStudentType(schoolYear, term);

            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("statisticsCourseTableByStudentType->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

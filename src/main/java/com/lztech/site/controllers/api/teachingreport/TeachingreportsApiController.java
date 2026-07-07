package com.lztech.site.controllers.api.teachingreport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.teachingreport.TeachingReportService;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingreport.*;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-11-29T09:45:52.037Z")

@Controller
public class TeachingreportsApiController implements TeachingreportsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingreportsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private TeachingReportService teachingReportService;

    @org.springframework.beans.factory.annotation.Autowired
    public TeachingreportsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<OfferCourseInfoVo> getTeachingReportOfferingCourseInfo(
            @ApiParam(value = "查询参数" ,required=true )
            @Valid @RequestBody OfferingCourseRequest queryParam,
            @ApiParam(value = "验证码：&signKey=123123(md5加密)")
            @Valid @RequestParam(value = "validCode", required = false) String validCode) {
        try {
            if (ObjectUtils.isEmpty(queryParam)
                    || StringUtils.isAnyBlank(validCode,queryParam.getSchoolYear(),
                    queryParam.getStartDate(),queryParam.getEndDate())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (ObjectUtils.isEmpty(queryParam.getTerm()) || CollectionUtils.isEmpty(queryParam.getColleges())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            OfferCourseInfoVo offerCourseInfoVo = teachingReportService.getTeachingReportOfferingCourseInfo(queryParam);
            return new ResponseEntity<>(offerCourseInfoVo,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getTeachingReportOfferingCourseInfo->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity<TeachingReportCourseResourceStatisticsVo> getCourseResourceStatistics(
            @ApiParam(value = "查询参数" ,required=true )  @Valid @RequestBody OfferingCourseRequest queryParam,
            @ApiParam(value = "验证码：&signKey=123123(md5加密)")
            @Valid @RequestParam(value = "validCode", required = false) String validCode) {
        try {
            if (ObjectUtils.isEmpty(queryParam)
                    || StringUtils.isAnyBlank(validCode,queryParam.getSchoolYear(),
                    queryParam.getStartDate(),queryParam.getEndDate())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (ObjectUtils.isEmpty(queryParam.getTerm()) || CollectionUtils.isEmpty(queryParam.getColleges())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            TeachingReportCourseResourceStatisticsVo teachingReportCourseResourceStatisticsVo
                    = teachingReportService.getTeachingReportCourseResourceStatistics(queryParam);
            return new ResponseEntity<>(teachingReportCourseResourceStatisticsVo,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseResourceStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseResourceCollegeStatisticsModel>> getCourseResourceCollegeStatisticsList(
            @ApiParam(value = "查询参数" ,required=true )
            @Valid @RequestBody CollegeTeacherResourceStatisticsRequest queryParam,
            @ApiParam(value = "验证码：&signKey=123123(md5加密)")
            @Valid @RequestParam(value = "validCode", required = false) String validCode) {
        try {
            if (ObjectUtils.isEmpty(queryParam)
                    || StringUtils.isAnyBlank(validCode,queryParam.getSchoolYear(),
                    queryParam.getStartDate(),queryParam.getEndDate())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (ObjectUtils.isEmpty(queryParam.getTerm())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            List<CourseResourceCollegeStatisticsModel> courseResourceCollegeStatisticsModelList
                    = teachingReportService.getCourseResourceCollegeStatisticsList(queryParam);
            return new ResponseEntity<>(courseResourceCollegeStatisticsModelList,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseResourceCollegeStatisticsList->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

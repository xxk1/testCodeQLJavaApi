package com.lztech.site.controllers.api.coursestatistics;

import com.lztech.site.service.coursestatistics.CourseStatisticsService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursestatistics.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-09-27T02:57:40.200Z")

@Controller
public class CoursestatisticsApiController implements CoursestatisticsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursestatisticsApiController.class);

    @Autowired
    private CourseStatisticsService courseStatisticsService;

    public ResponseEntity<CourseStatisticsResource> getCourseStatistics(@NotNull
                                                                        @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
                                                                        @Valid @RequestParam(value = "validCode", required = true)
                                                                                String validCode,
                                                                        @NotNull
                                                                        @ApiParam(value = "统计维度 0:课程属性（必修；选修）；1:学生类别（本科；研究）",
                                                                                required = true) @Valid @RequestParam(value = "dimension",
                                                                                required = true)
                                                                                Integer dimension,
                                                                        @ApiParam(value = "学年") @Valid @RequestParam(value = "schoolYear",
                                                                                required = false)
                                                                                String schoolYear,
                                                                        @ApiParam(value = "学期") @Valid @RequestParam(value = "term", required =
                                                                                false)
                                                                                String term) {
        if (StringUtils.isBlank(validCode) || Objects.isNull(dimension)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            CourseStatisticsResource courseStatisticsResource = courseStatisticsService.getCourseStatistics(dimension, schoolYear, term);

            return new ResponseEntity<>(courseStatisticsResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<CourseResourceStatisticsResource> getCourseResourceStatistics(@NotNull
                                                                                        @ApiParam(value = "验证码：&signKey=123123(md5加密)", required =
                                                                                                true)
                                                                                        @Valid @RequestParam(value = "validCode", required = true)
                                                                                                String validCode) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            CourseResourceStatisticsResource courseStatisticsResource = courseStatisticsService.getCourseResourceStatistics();

            return new ResponseEntity<>(courseStatisticsResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseResourceDetailStatisticsVo> getCourseResourceDetailStatistics(@NotNull
                                                                                              @ApiParam(value = "验证码&signKey=123123(md5加密)",
                                                                                                      required = true)
                                                                                              @Valid
                                                                                              @RequestParam(value = "validCode", required = true)
                                                                                                      String validCode) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            CourseResourceDetailStatisticsVo courseResourceDetailStatisticsVo = courseStatisticsService.getCourseResourceDetailStatistics();

            return new ResponseEntity<>(courseResourceDetailStatisticsVo, HttpStatus.OK);

        } catch (Exception e) {

            LOGGER.error("getCourseResourceDetailStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseResourceUseStatisticsResource> getCourseResourceUseStatistics(@NotNull
                                                                                              @ApiParam(value = "验证码&signKey=123123(md5加密)",
                                                                                                      required = true)
                                                                                              @Valid
                                                                                              @RequestParam(value = "validCode", required = true)
                                                                                                      String validCode) {

        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseResourceUseStatisticsResource useStatisticsResource = courseStatisticsService.getCourseResourceUseStatistics();

            return new ResponseEntity<>(useStatisticsResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseResourceUseStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseBuildStatisticsResource> getCourseBuildStatistics(@NotNull
                                                                                  @ApiParam(value = "验证码&signKey=123123(md5加密)",
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
            CourseBuildStatisticsResource courseBuildStatisticsResource = courseStatisticsService.getCourseBuildStatistics();

            return new ResponseEntity<>(courseBuildStatisticsResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseBuildStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getCourseCountStatistics(@NotNull
                                                            @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
                                                            @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                            @NotNull
                                                            @ApiParam(value = "0:理论课，1：实验课", required = true)
                                                            @Valid
                                                            @RequestParam(value = "courseType", required = true) Integer courseType,
                                                            @NotNull
                                                            @ApiParam(value = "学年", required = true)
                                                            @Valid
                                                            @RequestParam(value = "schoolYear", required = true) String schoolYear,
                                                            @NotNull
                                                            @ApiParam(value = "学期", required = true)
                                                            @Valid @RequestParam(value = "term", required = true) Integer term) {
        if (StringUtils.isBlank(validCode) || StringUtils.isBlank(schoolYear)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            int courseCount = courseStatisticsService.getCourseCountByCourseTypeSchoolYearTerm(courseType, schoolYear, term);
            return new ResponseEntity<Integer>(courseCount, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseCountStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseStatisticsPeopleNumberVo> getCoursePeopleNumber(
            @NotNull @ApiParam(value = "验证码courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程ID", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) String term) {
        try {
            if (StringUtils.isBlank(validCode) || StringUtils.isBlank(courseId) || StringUtils.isBlank(schoolYear)
                    || StringUtils.isBlank(term)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseStatisticsPeopleNumberVo courseStatisticsPeopleNumberVo =
                    courseStatisticsService.getCoursePeopleNumber(courseId, schoolYear, term);
            return new ResponseEntity<>(courseStatisticsPeopleNumberVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseBuildStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseStatisticsVersionVo>> getCourseStatisticsVersion(
            @NotNull @ApiParam(value = "课程ID", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(courseId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CourseStatisticsVersionVo> courseStatisticsVersionVos = courseStatisticsService.getCourseStatisticByCourseId(courseId);
            return new ResponseEntity<>(courseStatisticsVersionVos, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseStatisticsVersion->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseStatisticsTeachResourceVo>> getCourseStatisticsTeachResource(
            @NotNull @ApiParam(value = "课程ID", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(courseId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CourseStatisticsTeachResourceVo> courseStatisticsTeachResourceVos = courseStatisticsService
                    .getCourseStatisticResourceByCourseId(courseId);
            return new ResponseEntity<>(courseStatisticsTeachResourceVos, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseStatisticsTeachResource->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseStatisticsResourceVo> getCourseStatisticsTeachResourceNum(
            @NotNull @ApiParam(value = "课程ID", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本ID", required = true)
            @Valid @RequestParam(value = "versionId", required = true) String versionId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(versionId, courseId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseStatisticsResourceVo courseStatisticsResourceVo =
                    courseStatisticsService.getResourceStatisticNum(courseId, versionId);
            return new ResponseEntity<>(courseStatisticsResourceVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseStatisticsTeachResourceNum->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseStatisticsKnowledgeVo> getCourseStatisticsKnowledge(
            @NotNull @ApiParam(value = "课程ID", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本ID", required = true)
            @Valid @RequestParam(value = "versionId", required = true) String versionId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(versionId, courseId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseStatisticsKnowledgeVo courseStatisticsKnowledgeVo =
                    courseStatisticsService.getCourseStatisticsKnowledge(courseId, versionId);
            return new ResponseEntity<>(courseStatisticsKnowledgeVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseStatisticsKnowledge->", e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getCourseProjectStatistics(@NotNull
                                                              @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
                                                              @Valid
                                                              @RequestParam(value = "validCode", required = true) String validCode,
                                                              @NotNull
                                                              @ApiParam(value = "学年", required = true)
                                                              @Valid
                                                              @RequestParam(value = "schoolYear", required = true) String schoolYear,
                                                              @NotNull
                                                              @ApiParam(value = "学期", required = true)
                                                              @Valid
                                                              @RequestParam(value = "term", required = true) Integer term) {
        if (StringUtils.isBlank(validCode) ||StringUtils.isBlank(schoolYear)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            int courseCount = courseStatisticsService.getCourseProjectBySchoolYearTerm(schoolYear,term);
            return new ResponseEntity<Integer>(courseCount,HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("getCourseProjectStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

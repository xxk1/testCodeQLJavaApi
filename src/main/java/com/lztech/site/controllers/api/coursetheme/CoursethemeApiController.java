package com.lztech.site.controllers.api.coursetheme;

import com.lztech.domain.model.course.CourseTheme;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.coursetheme.CourseThemeService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursetheme.CourseThemeResource;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-07T07:12:16.723Z")

@Controller
public class CoursethemeApiController implements CoursethemeApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursethemeApiController.class);
    @Autowired
    private CourseThemeService courseThemeService;

    public ResponseEntity<Void> insertOrUpdateCourseTheme(@NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
                                                          @Valid @RequestParam(value = "validCode", required = true)
                                                                  String validCode,
                                                          @ApiParam(value = "", required = true) @Valid @RequestBody
                                                                  CourseThemeResource courseThemeResource) {
        if (StringUtils.isAnyBlank(validCode, courseThemeResource.getCourseStructureId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseThemeService.insertOrUpdateCourseTheme(courseThemeResource);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("insertOrUpdateCourseTheme->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseThemeResource> getCourseTheme(@NotNull @ApiParam(value = "验证码：id={}&signKey=123123(md5加密)", required = true)
                                                              @Valid @RequestParam(value = "validCode", required = true)
                                                                      String validCode,
                                                              @ApiParam(value = "主题信息id", required = true)
                                                              @PathVariable("id")
                                                                      String id) {

        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseTheme courseTheme = courseThemeService.findByIdAndStatus(id, ResourceStatus.NORMAL);
            if (Objects.isNull(courseTheme)) {
                return new ResponseEntity(ErrorResult.customError("主题信息不存在"), HttpStatus.NOT_FOUND);
            }
            CourseThemeResource courseThemeResource = courseThemeService.transformCourseTheme(courseTheme);
            return new ResponseEntity<>(courseThemeResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseTheme->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CourseThemeResource>> getTeacherCourseTheme(@NotNull @ApiParam(value = "课程id", required = true)
                                                                           @Valid @RequestParam(value = "courseId", required = true)
                                                                                   String courseId,
                                                                           @NotNull @ApiParam(value = "教师id", required = true)
                                                                           @Valid @RequestParam(value = "teacherId", required = true)
                                                                                   String teacherId,
                                                                           @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)",
                                                                                   required = true)
                                                                           @Valid @RequestParam(value = "validCode", required = true)
                                                                                   String validCode) {

        if (StringUtils.isAnyBlank(validCode, teacherId, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseThemeResource> courseThemeResourceList = courseThemeService.getTeacherCourseTheme(courseId, teacherId);
            return new ResponseEntity<>(courseThemeResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getTeacherCourseTheme->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

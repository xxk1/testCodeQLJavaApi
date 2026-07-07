package com.lztech.site.controllers.api.courseteachingpackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.courseteachingpackage.CourseTeachingPackage;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.teachingpackage.TeachingPackageService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingpackage.CourseTeachingPackagePageResource;
import com.lztech.site.viewmodel.teachingpackage.CourseTeachingPackageVo;
import com.lztech.site.viewmodel.teachingpackage.QuoteTeachingPackageVo;
import com.lztech.site.viewmodel.teachingpackage.TeachingPackageCourseVersionResource;
import com.lztech.site.viewmodel.teachingpackage.TeachingPackageVo;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-24T05:48:54.282Z")

@Controller
public class TeachingpackageApiController implements TeachingpackageApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingpackageApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private TeachingPackageService teachingPackageService;

    @org.springframework.beans.factory.annotation.Autowired
    public TeachingpackageApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addTeachingPackage(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "" ,required=true )  @Valid @RequestBody CourseTeachingPackageVo teachingPackageVo) {
        try {
            if (ObjectUtils.isEmpty(teachingPackageVo) || StringUtils.isBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Result result = teachingPackageService.addTeachingPackage(teachingPackageVo);
            if(result.getStatus().equals(ResultStatus.ERROR)){
                return new ResponseEntity(result.getMsg(),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("addTeachingPackage" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeachingPackageVo>> getTeachingPackageByName(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程名称", required = true)
            @Valid @RequestParam(value = "courseName", required = true) String courseName,
            @NotNull @ApiParam(value = "课程Id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "用户Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId) {
        try {
            if ( StringUtils.isAnyBlank(courseName,courseId,userId,validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<TeachingPackageVo> teachingPackageVos = teachingPackageService.getTeachingPackageByName(courseName,courseId,userId);
            return new ResponseEntity<>(teachingPackageVos,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("addTeachingPackage" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeachingPackageCourseVersionResource>> getTeachingPackageVersion(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程ID", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId) {
        try {
            if ( StringUtils.isAnyBlank(courseId,userId,validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<TeachingPackageCourseVersionResource> teachingPackageVos = teachingPackageService.getPackageVersion(courseId,userId);
            return new ResponseEntity<>(teachingPackageVos,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("addTeachingPackage" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseTeachingPackagePageResource> getTeachingPackageList(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程Ids" ,required=true )
            @Valid @RequestBody List<String> courseIds,
            @NotNull @ApiParam(value = "当前页", required = true)
            @Valid @RequestParam(value = "page", required = true) Integer page,
            @NotNull @ApiParam(value = "每页个数", required = true)
            @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize) {
        try {
            if ( StringUtils.isAnyBlank(validCode)&&ObjectUtils.isEmpty(page)&&ObjectUtils.isEmpty(pageSize)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            CourseTeachingPackagePageResource packagePageResource =
                    teachingPackageService.getTeachingPackageList(courseIds, page, pageSize);
            return new ResponseEntity<>(packagePageResource,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getTeachingPackageList" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<TeachingPackageVo> getTeachingPackageDetail(
            @NotNull @ApiParam(value = "验证码（courseId=?&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @ApiParam(value = "课程Id",required=true)
            @PathVariable("courseId") String courseId) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CourseTeachingPackage> teachingPackages = teachingPackageService.findByCourseId(courseId, userId);
            if (CollectionUtils.isEmpty(teachingPackages)) {
                return new ResponseEntity(ErrorResult.dataNotExistError("教学包"), HttpStatus.NOT_FOUND);
            }
            TeachingPackageVo teachingPackageVo = teachingPackageService.getTeachingPackageDetail(teachingPackages,courseId,userId);

            return new ResponseEntity<>(teachingPackageVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getTeachingPackageDetail" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    public ResponseEntity<Void> quoteTeachingPackage(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "" ,required=true )  @Valid @RequestBody QuoteTeachingPackageVo quoteTeachingPackage) {
        try {
            if (ObjectUtils.isEmpty(quoteTeachingPackage)|| StringUtils.isAnyBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Result result = teachingPackageService.quoteTeachingPackage(quoteTeachingPackage);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("addTeachingPackage" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseTeachingPackagePageResource> getTeachingPackageMyList(
            @NotNull @ApiParam(value = "验证码（userId=?&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "用户Id",required=true)
            @PathVariable("userId") String userId,
            @NotNull @ApiParam(value = "当前页", required = true)
            @Valid @RequestParam(value = "page", required = true) Integer page,
            @NotNull @ApiParam(value = "每页个数", required = true)
            @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize) {
        try {
            if (StringUtils.isAnyEmpty(userId, validCode) || ObjectUtils.isEmpty(page) || ObjectUtils.isEmpty(pageSize)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("userId", userId, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            CourseTeachingPackagePageResource packagePageResource =
                    teachingPackageService.getTeachingPackageMyList(userId, page, pageSize);
            return new ResponseEntity<>(packagePageResource,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getTeachingPackageMyList" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TeachingPackageVo> getTeachingPackageMyDetail(
            @NotNull @ApiParam(value = "验证码（courseId=?&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @ApiParam(value = "课程Id", required = true)
            @PathVariable("courseId") String courseId) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CourseTeachingPackage> teachingPackages = teachingPackageService.findByCourseIdAndUserId(courseId, userId);
            if (CollectionUtils.isEmpty(teachingPackages)) {
                return new ResponseEntity(ErrorResult.dataNotExistError("教学包"), HttpStatus.NOT_FOUND);
            }
            TeachingPackageVo teachingPackageVo =
                    teachingPackageService.getTeachingPackageMyDetail(teachingPackages,courseId,userId);

            return new ResponseEntity<>(teachingPackageVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getTeachingPackageDetail" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

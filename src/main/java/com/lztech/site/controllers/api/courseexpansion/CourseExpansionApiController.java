package com.lztech.site.controllers.api.courseexpansion;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.courseexpansion.CourseListeningTypeService;
import com.lztech.site.service.courseexpansion.CourseExpansionService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionCreateResource;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionQuery;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionResource;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionResourcePage;
import com.lztech.site.viewmodel.courseexpansion.CourseListeningTypeResource;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-08T06:03:14.171Z")

@Controller
public class CourseExpansionApiController implements CourseExpansionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseExpansionApiController.class);


    @Autowired
    private CourseExpansionService courseExpansionService;
    @Autowired
    private CourseListeningTypeService courseListeningTypeService;

    public ResponseEntity<List<CourseExpansionResource>> getCourseExpansionResource(
            @NotNull @ApiParam(value = "验证码：expansionKey={}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "扩展属性key", required = true)
            @Valid @RequestParam(value = "expansionKey", required = true) String expansionKey,
            @NotNull @ApiParam(value = "排序字段", required = true)
            @Valid @RequestParam(value = "sortName", required = true) String sortName,
            @ApiParam(value = "课程id") @Valid @RequestParam(value = "courseId", required = false)
            String courseId) {

        if (StringUtils.isAnyBlank(expansionKey, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("expansionKey", expansionKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseExpansionResource> courseExpansionResourceList = courseExpansionService.
                    getCourseExpansionResource(expansionKey, sortName,courseId);
            return new ResponseEntity<>(courseExpansionResourceList, HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("getCourseExpansionResource->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteCourseExpansionResource(@NotNull
                                                              @ApiParam(value = "验证码：id={}&signKey=123123(md5加密)", required = true)
                                                              @Valid @RequestParam(value = "validCode", required = true)
                                                              String validCode,
                                                              @ApiParam(value = "扩展属性id", required = true)
                                                              @PathVariable("id")
                                                              String id) {

        if (StringUtils.isAnyBlank(id, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            courseExpansionService.deleteCourseExpansionById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("deleteCourseExpansionResource->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> createCourseexpansion(@NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
                                                      @Valid @RequestParam(value = "validCode", required = true)
                                                      String validCode,
                                                      @ApiParam(value = "添加思政课程信息", required = true)
                                                      @Valid @RequestBody
                                                      CourseExpansionCreateResource expansionInfo) {

        if (StringUtils.isAnyBlank(validCode, expansionInfo.getExpansionKey(), expansionInfo.getCourseId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {

            Result result = courseExpansionService.createCourseExpansion(expansionInfo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("createCourseexpansion->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseExpansionResourcePage> getCourseExpansionPage(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "分页查询参数" ,required=true )  @Valid @RequestBody
            CourseExpansionQuery courseExpansionQuery) {
        if (StringUtils.isAnyBlank(validCode)|| Objects.isNull(courseExpansionQuery)|| Objects.isNull(courseExpansionQuery.getPage())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseExpansionResourcePage courseExpansionResourcePage = courseExpansionService.getCourseExpansionPage(courseExpansionQuery);
            return new ResponseEntity<>(courseExpansionResourcePage, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseExpansionPage->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<CourseListeningTypeResource> getCourseListeningType(
            @NotNull @ApiParam(value = "验证码：courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId) {
        if (StringUtils.isAnyBlank(validCode, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseListeningTypeResource resource = courseListeningTypeService.getCourseListeningTypeResource(courseId);
            if (resource == null) {
                return new ResponseEntity(ErrorResult.dataNotExistError("课程"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseListeningType->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> importCourseExpansion(
            @NotNull @ApiParam(value = "创建人id", required = true)
            @Valid @RequestParam(value = "creatorId", required = true) String creatorId,
            @NotNull @ApiParam(value = "创建人姓名", required = true)
            @Valid @RequestParam(value = "creatorName", required = true) String creatorName,
            @NotNull @ApiParam(value = "听课类型模式(0:固定,1:灵活)", required = true)
            @Valid @RequestParam(value = "superviseModel", required = true) Integer superviseModel,
            @ApiParam(value = "听课类型接口参数usageRange") @Valid
            @RequestParam(value = "usageRange", required = false) Integer usageRange,
            @ApiParam(value = "听课类型接口参数courseStudentTypeIds") @Valid
            @RequestParam(value = "courseStudentTypeIds", required = false) String courseStudentTypeIds,
            @ApiParam(value = "听课类型接口参数isDistinguishCourseStudentType") @Valid
            @RequestParam(value = "isDistinguishCourseStudentType", required = false) Integer isDistinguishCourseStudentType,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "导入文件（xlsx）", required = true)
            @Valid @org.springframework.web.bind.annotation.RequestPart(value = "importFile", required = true) MultipartFile importFile
    ) {
        if (StringUtils.isAnyBlank(creatorId, creatorName, validCode) || Objects.isNull(importFile) || Objects.isNull(superviseModel)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseExpansionService.importCourseExpansion(creatorId, creatorName, superviseModel, usageRange,
                    courseStudentTypeIds, isDistinguishCourseStudentType, importFile);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(String.valueOf(result.getMsg())), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("importCourseExpansion->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

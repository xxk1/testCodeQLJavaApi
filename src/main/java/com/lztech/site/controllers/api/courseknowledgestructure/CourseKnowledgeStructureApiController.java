package com.lztech.site.controllers.api.courseknowledgestructure;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.courseconstruction.CourseKnowledgeStructureService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseconstruction.CourseKnowledgeStructureResource;
import com.lztech.site.viewmodel.coursemanagement.CourseKnowledgePointResource;
import com.lztech.site.viewmodel.coursemanagement.CourseKnowledgeStructureSortVo;
import com.lztech.site.viewmodel.coursemanagement.CourseKnowledgeStructureVo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-22T07:41:01.018Z")

@Controller
public class CourseKnowledgeStructureApiController implements CourseKnowledgeStructureApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseKnowledgeStructureApiController.class);

    @Autowired
    private CourseKnowledgeStructureService courseKnowledgeStructureService;

    public ResponseEntity<Void> insertCourseKnowledgestructure(@NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                               @Valid @RequestParam(value = "validCode", required = true)
                                                                       String validCode,
                                                               @ApiParam(value = "", required = true) @Valid
                                                               @RequestBody CourseKnowledgeStructureVo structureVo) {
        if (Objects.isNull(structureVo) || StringUtils.isAnyBlank(validCode, structureVo.getCourseId(), structureVo.getVersionId(),
                structureVo.getContent()) || Objects.isNull(structureVo.getStructureType())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseKnowledgeStructureService.createCourseKnowledgeStructure(structureVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("insertCourseKnowledgestructure->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseKnowledgeStructureResource>> getCourseKnowledgeStructure(@NotNull
                                                                                              @ApiParam(value = "验证码（courseId={}&signKey=123123）",
                                                                                                      required = true)
                                                                                              @Valid
                                                                                              @RequestParam(value = "validCode", required = true)
                                                                                                      String validCode,
                                                                                              @NotNull @ApiParam(value = "课程id", required = true)
                                                                                              @Valid
                                                                                              @RequestParam(value = "courseId", required = true)
                                                                                                      String courseId,
                                                                                              @NotNull @ApiParam(value = "版本id", required = true)
                                                                                              @Valid @RequestParam(value = "versionId",
                                                                                                      required = true)
                                                                                                      String versionId) {

        if (StringUtils.isAnyBlank(validCode, courseId, versionId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseKnowledgeStructureResource> resourceList = courseKnowledgeStructureService.getCourseKnowledgeStructure(courseId, versionId);

            return new ResponseEntity(resourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseKnowledgeStructure->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteCourseKnowledgestructure(@NotNull @ApiParam(value = "验证码（id={}&signKey=123123）", required = true)
                                                               @Valid @RequestParam(value = "validCode", required = true)
                                                                       String validCode,
                                                               @ApiParam(value = "知识结构id", required = true)
                                                               @PathVariable("id")
                                                                       String id,
                                                               @ApiParam(value = "", required = true) @Valid
                                                               @RequestBody CourseKnowledgeStructureVo structureVo) {
        if (Objects.isNull(structureVo) || StringUtils.isAnyBlank(validCode, id, structureVo.getCourseId(), structureVo.getVersionId()
                , structureVo.getOperatorId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseKnowledgeStructureService.deleteCourseKnowledgeStructure(id, structureVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("deleteCourseKnowledgestructure->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateCourseKnowledgestructure(@NotNull @ApiParam(value = "验证码（id={}&signKey=123123）", required = true)
                                                               @Valid @RequestParam(value = "validCode", required = true)
                                                                       String validCode,
                                                               @ApiParam(value = "知识结构id", required = true)
                                                               @PathVariable("id")
                                                                       String id,
                                                               @ApiParam(value = "", required = true) @Valid
                                                               @RequestBody CourseKnowledgeStructureVo structureVo) {
        if (Objects.isNull(structureVo) || StringUtils.isAnyBlank(validCode, id, structureVo.getCourseId(), structureVo.getVersionId()
                , structureVo.getOperatorId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseKnowledgeStructureService.updateCourseKnowledgeStructure(id, structureVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("deleteCourseKnowledgestructure->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateCourseKnowledgestructureSort(@NotNull @ApiParam(value = "课程id", required = true)
                                                                   @Valid @RequestParam(value = "courseId", required = true)
                                                                           String courseId,
                                                                   @NotNull @ApiParam(value = "版本id", required = true)
                                                                   @Valid @RequestParam(value = "versionId", required = true)
                                                                           String versionId,
                                                                   @NotNull @ApiParam(value = "验证码（courseId={}&signKey" +
                                                                           "=123123）", required = true)
                                                                   @Valid @RequestParam(value = "validCode", required = true)
                                                                           String validCode,
                                                                   @NotNull @ApiParam(value = "操作人id", required = true)
                                                                   @Valid @RequestParam(value = "operatorId", required = true)
                                                                           String operatorId,
                                                                   @NotNull @ApiParam(value = "操作人姓名", required = true)
                                                                   @Valid @RequestParam(value = "operatorName", required = true)
                                                                           String operatorName,
                                                                   @ApiParam(value = "") @Valid @RequestBody
                                                                           List<CourseKnowledgeStructureSortVo> structureVoList) {
        if (StringUtils.isAnyBlank(validCode, courseId, versionId, operatorId, operatorName) || CollectionUtils.isEmpty(structureVoList)) {

            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseKnowledgeStructureService.updateCourseKnowledgeStructureSort(courseId, versionId, operatorId, operatorName,
                    structureVoList);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("updateCourseKnowledgestructureSort->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<CourseKnowledgePointResource>> getCourseKnowledgePointResource(@NotNull
                                                                                              @ApiParam(value = "验证码（&signKey=123123）",
                                                                                                      required = true)
                                                                                              @Valid
                                                                                              @RequestParam(value = "validCode", required = true)
                                                                                                      String validCode,
                                                                                              @ApiParam(value = "课程id") @Valid
                                                                                              @RequestParam(value = "courseId", required = false)
                                                                                                      String courseId,
                                                                                              @ApiParam(value = "版本id") @Valid
                                                                                              @RequestParam(value = "versionId", required = false)
                                                                                                      String versionId,
                                                                                              @ApiParam(value = "知识点id") @Valid
                                                                                              @RequestParam(value = "pointId", required = false)
                                                                                                      String pointId) {
        if (StringUtils.isAnyBlank(validCode) || StringUtils.isAllBlank(pointId, courseId, versionId)
                || (StringUtils.isBlank(pointId) && StringUtils.isAnyBlank(courseId, versionId))) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            List<CourseKnowledgePointResource> courseKnowledgePointResourceList =
                    courseKnowledgeStructureService.getCourseKnowledgePointResource(courseId, versionId, pointId);

            return new ResponseEntity<>(courseKnowledgePointResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseKnowledgePointResource->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

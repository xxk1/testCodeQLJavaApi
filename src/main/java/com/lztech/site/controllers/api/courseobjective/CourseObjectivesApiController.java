package com.lztech.site.controllers.api.courseobjective;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.courseobjective.CourseObjectivesService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseobjective.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
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
import java.util.Objects;

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-06T15:46:11.737+08:00")

@Controller
public class CourseObjectivesApiController implements CourseObjectivesApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseObjectivesApiController.class);

    @Autowired
    private CourseObjectivesService courseObjectivesService;

    public ResponseEntity<CourseObjectiveResponse> createCourseObjective(
            @ApiParam(value = "", required = true) @Valid @RequestBody CourseObjectiveCreateRequest param,
            @ApiParam(value = "验证码  &signKey=123123(md5加密)") @Valid
            @RequestParam(value = "validCode", required = false) String validCode) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(param.getCourseId(), param.getName(),
                param.getOperatorId(), param.getOperatorNo(), validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseObjectivesService.createCourseObjective(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(result.getData(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("createCourseObjective->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteCourseObjective(
            @ApiParam(value = "课程目标ID", required = true) @PathVariable("id") String id,
            @ApiParam(value = "", required = true) @Valid @RequestBody CourseObjectiveUpdateRequest body,
            @ApiParam(value = "验证码 id={}&signKey=123123(md5加密)") @Valid
            @RequestParam(value = "validCode", required = false) String validCode) {
        if (Objects.isNull(body) || StringUtils.isAnyBlank(id, body.getId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseObjectivesService.deleteCourseObjective(id, body);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("deleteCourseObjective->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> modifyCourseObjective(
            @ApiParam(value = "课程目标ID", required = true) @PathVariable("id") String id,
            @ApiParam(value = "", required = true) @Valid @RequestBody CourseObjectiveUpdateRequest body,
            @ApiParam(value = "验证码 id={}&signKey=123123(md5加密)") @Valid
            @RequestParam(value = "validCode", required = false) String validCode) {
        if (Objects.isNull(body) || StringUtils.isAnyBlank(id, body.getId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseObjectivesService.modifyCourseObjective(id, body);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("modifyCourseObjective->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseObjectiveMainInfo> queryCourseObjective(
            @NotNull @ApiParam(value = "课程ID", required = true) @Valid
            @RequestParam(value = "courseId", required = true) String courseId,
            @ApiParam(value = "验证码 courseId={}&signKey=123123(md5加密)") @Valid
            @RequestParam(value = "validCode", required = false) String validCode) {
        if (StringUtils.isAnyBlank(courseId, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseObjectiveMainInfo courseObjectiveMainInfo = courseObjectivesService.queryCourseObjective(courseId);
            return new ResponseEntity<CourseObjectiveMainInfo>(courseObjectiveMainInfo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("queryCourseObjective->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> sortCourseObjective(
            @ApiParam(value = "", required = true) @Valid @RequestBody CourseObjectiveSortRequest param,
            @NotNull @ApiParam(value = "验证码 courseId={}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(param.getCourseId(), validCode)
                || CollectionUtils.isEmpty(param.getSortList())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", param.getCourseId(), validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseObjectivesService.sortCourseObjective(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("sortCourseObjective->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> relatedCourseObjectiveKnowledgeGraphNode(
            @ApiParam(value = "", required = true) @Valid @RequestBody
            CourseObjectiveRelatedKnowledgeGraphNodeRequest param,
            @NotNull @ApiParam(value = "验证码 id={}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程目标ID", required = true) @PathVariable("id") String id) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(id, validCode, param.getCourseKnowledgeGraphId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            Result result = courseObjectivesService.relatedCourseObjectiveKnowledgeGraphNode(param, id);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("relatedCourseObjectiveKnowledgeGraphNode->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseObjectiveRelatedKnowledgeGraphNodeResult> getCourseObjectiveRelatedKnowledgeGraphNode(
            @ApiParam(value = "课程目标ID", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "验证码 id={}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(id, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseObjectiveRelatedKnowledgeGraphNodeResult courseObjectiveRelatedKnowledgeGraphNodeResult =
                    courseObjectivesService.getCourseObjectiveRelatedKnowledgeGraphNode(id);
            return new ResponseEntity<CourseObjectiveRelatedKnowledgeGraphNodeResult>(
                    courseObjectiveRelatedKnowledgeGraphNodeResult, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseObjectiveRelatedKnowledgeGraphNode->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

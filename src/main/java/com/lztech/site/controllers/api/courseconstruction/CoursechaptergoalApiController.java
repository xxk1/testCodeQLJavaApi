package com.lztech.site.controllers.api.courseconstruction;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.coursechaptergoal.CourseChapterGoalService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursechaptergoal.CourseChapterGoalVo;
import com.lztech.site.viewmodel.coursechaptergoal.CourseStructureBaseResource;
import com.lztech.site.viewmodel.courseconstruction.CourseChapterGoalTypeResource;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-24T09:15:35.278Z")

@Controller
public class CoursechaptergoalApiController implements CoursechaptergoalApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursechaptergoalApiController.class);

    @Autowired
    private CourseChapterGoalService courseChapterGoalService;

    public ResponseEntity<List<CourseChapterGoalTypeResource>> coursechaptergoalTypeGet(@NotNull
                                                                                        @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                                                        @Valid @RequestParam(value = "validCode", required = true)
                                                                                                String validCode) {

        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseChapterGoalTypeResource> courseChapterGoalTypeResourceList = courseChapterGoalService.getCourseChapterGoalType();
            return new ResponseEntity<>(courseChapterGoalTypeResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursechaptergoalTypeGet->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<List<CourseStructureBaseResource>> getCourseChapterGoal(@NotNull
                                                                                  @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                                                  @Valid @RequestParam(value = "validCode", required = true)
                                                                                          String validCode,
                                                                                  @ApiParam(value = "章节id") @Valid
                                                                                  @RequestParam(value = "structureId", required = false)
                                                                                          String structureId,
                                                                                  @ApiParam(value = "课程id") @Valid
                                                                                  @RequestParam(value = "courseId", required = false)
                                                                                          String courseId,
                                                                                  @ApiParam(value = "版本id") @Valid
                                                                                  @RequestParam(value = "versionId", required = false)
                                                                                          String versionId) {
        if (StringUtils.isAnyBlank(validCode) || StringUtils.isAllBlank(structureId, courseId, versionId)
                || (StringUtils.isBlank(structureId) && StringUtils.isAnyBlank(courseId, versionId))) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseStructureBaseResource> baseResourceList = courseChapterGoalService.getCourseChapterGoal(structureId, courseId, versionId);
            return new ResponseEntity(baseResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursechaptergoalTypeGet->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> addCourseChapterGoal(@NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                     @Valid @RequestParam(value = "validCode", required = true)
                                                             String validCode,
                                                     @ApiParam(value = "课程章节目标信息", required = true)
                                                     @Valid @RequestBody CourseChapterGoalVo courseChapterGoalVo) {
        if (Objects.isNull(courseChapterGoalVo) || StringUtils.isAnyBlank(courseChapterGoalVo.getCourseId(), courseChapterGoalVo.getStructureId(),
                courseChapterGoalVo.getVersionId()) || CollectionUtils.isEmpty(courseChapterGoalVo.getGoalList())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseChapterGoalService.addCourseChapterGoal(courseChapterGoalVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("addCourseChapterGoal->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

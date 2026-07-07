package com.lztech.site.controllers.api.coursestructure;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.courseconstruction.CourseStructureService;
import com.lztech.site.until.ValidUtils;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-24T06:54:22.330Z")

@Controller
public class CoursestructureApiController implements CoursestructureApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursestructureApiController.class);

    @Autowired
    private CourseStructureService courseStructureService;


    public ResponseEntity<Void> generateCourseStructureByCourseknowledgeStructure(@NotNull
                                                                                  @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                                                  @Valid @RequestParam(value = "validCode", required = true)
                                                                                          String validCode,
                                                                                  @NotNull @ApiParam(value = "课程id", required = true)
                                                                                  @Valid @RequestParam(value = "courseId", required = true)
                                                                                          String courseId,
                                                                                  @NotNull @ApiParam(value = "版本id", required = true)
                                                                                  @Valid @RequestParam(value = "versionId", required = true)
                                                                                          String versionId,
                                                                                  @NotNull @ApiParam(value = "用户id", required = true)
                                                                                  @Valid @RequestParam(value = "userId", required = true)
                                                                                          String userId,
                                                                                  @NotNull @ApiParam(value = "用户名称", required = true)
                                                                                  @Valid @RequestParam(value = "userName", required = true)
                                                                                          String userName) {

        if (StringUtils.isAnyBlank(courseId, versionId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseStructureService.generateCourseStructure(courseId, versionId, userId, userName);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("generateCourseStructureByCourseknowledgeStructure:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.lztech.site.controllers.api.courseconstruction;

import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursemanagement.CourseCompletionDetailResource;
import com.lztech.site.viewmodel.coursemanagement.CourseCompletionResource;
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
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-28T09:21:24.689Z")

@Controller
public class CourseCompletionApiController implements CourseCompletionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseCompletionApiController.class);

    @Autowired
    private CourseCompletionService courseCompletionService;

    public ResponseEntity<List<CourseCompletionResource>> getCourseCompletion(@NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                                              @Valid @RequestParam(value = "validCode", required = true)
                                                                                      String validCode,
                                                                              @NotNull @ApiParam(value = "课程ids", required = true)
                                                                              @Valid @RequestParam(value = "courseIds", required = true)
                                                                                      String courseIds) {
        if (StringUtils.isAnyBlank(courseIds, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            List<CourseCompletionResource> completionResourceList = courseCompletionService.getCourseCompletion(courseIds);

            return new ResponseEntity(completionResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseCompletion->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<CourseCompletionDetailResource>> getCourseCompletionDetail(@NotNull
                                                                                          @ApiParam(value = "验证码（courseId={}&signKey=123123）",
                                                                                                  required = true)
                                                                                          @Valid @RequestParam(value = "validCode", required = true)
                                                                                                  String validCode,
                                                                                          @NotNull @ApiParam(value = "课程id", required = true)
                                                                                          @Valid @RequestParam(value = "courseId", required = true)
                                                                                                  String courseId) {
        if (StringUtils.isAnyBlank(validCode, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }
        try {
            List<CourseCompletionDetailResource> detailResourceList = courseCompletionService.getCourseCompletionDetail(courseId);

            return new ResponseEntity<>(detailResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseCompletionDetail->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

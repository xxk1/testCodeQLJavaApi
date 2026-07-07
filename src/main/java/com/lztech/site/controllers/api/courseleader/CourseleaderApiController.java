package com.lztech.site.controllers.api.courseleader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.resource.course.CourseInfoResource;
import com.lztech.site.service.courseleader.CourseLeaderService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseleader.CourseLeaderVo;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-02T06:08:10.691Z")

@Controller
public class CourseleaderApiController implements CourseleaderApi {

    private final Logger log = LoggerFactory.getLogger(CourseleaderApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CourseleaderApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private CourseLeaderService courseLeaderService;

    public ResponseEntity<Void> courseleaderPost(
            @ApiParam(value = "", required = true)
            @Valid @RequestBody CourseLeaderVo courseLeaderVo,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, courseLeaderVo.getCourseId(), courseLeaderVo.getTeacherId(),
                courseLeaderVo.getTeacherNo(), courseLeaderVo.getTeacherName())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (courseLeaderVo.getActionType() == null || courseLeaderVo.isManagementPort() == null) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (courseLeaderVo.getTeacherId().equals(courseLeaderVo.getCourseLeaderId())) {
            return new ResponseEntity(ErrorResult.customError("课程负责人与要转让的老师Id一致"), HttpStatus.CONFLICT);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            Result result = courseLeaderService.updateCourseLeader(courseLeaderVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(result.getData(), HttpStatus.OK);

        } catch (Exception e) {
            log.error("courseleaderPost", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CourseInfoResource>> getUserLeaderCourse(@NotNull
                                                                        @ApiParam(value = "验证码：id={}&signKey=123123(md5加密)", required = true)
                                                                        @Valid @RequestParam(value = "validCode", required = true)
                                                                                String validCode,
                                                                        @ApiParam(value = "直播讲座id", required = true)
                                                                        @PathVariable("id") String id) {

        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseInfoResource> courseInfoResourceList = courseLeaderService.getUserLeaderCourseList(id);

            return new ResponseEntity<>(courseInfoResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getUserLeaderCourse->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

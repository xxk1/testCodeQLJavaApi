package com.lztech.site.controllers.api.teachers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.coursetable.TeacherCourseTableService;
import com.lztech.site.service.coursetabledetail.CourseTableDetailService;
import com.lztech.site.service.coursetabledetail.TeacherCourseTableDetailService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.CourseTableSimpleVo;
import com.lztech.site.viewmodel.TeacherCourseTableSimpleResource;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.peerreview.TeacherPeerReviewCourseResource;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-12T05:40:01.236Z")

@Controller
public class TeachersApiController implements TeachersApi {

    private final Logger log = LoggerFactory.getLogger(TeachersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CourseTableDetailService courseTableDetailService;
    @Autowired
    private TeacherCourseTableService teacherCourseTableService;
    @Autowired
    private TeacherCourseTableDetailService teacherCourseTableDetailService;

    @org.springframework.beans.factory.annotation.Autowired
    public TeachersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CourseTableSimpleVo> teachersCurrentcoursetablesGet(
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码：teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(teacherId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
            }

            return courseTableDetailService.getCourseTableSimpleVoByCurrentAndTeacherId(teacherId);
        } catch (Exception e) {
            log.error("teachersCurrentcoursetablesGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<TeacherPeerReviewCourseResource>> getTeacherCourseTableGroupAndProject(
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "老师Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull
            @ApiParam(value = "加密验证码userId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {

        if (StringUtils.isAnyBlank(userId, schoolYear, validCode) || term == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String validKey = "userId=" + userId + signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<TeacherPeerReviewCourseResource> resourceList = teacherCourseTableService.getTeacherCourseTableGroupAndProject(userId, schoolYear,
                    term);

            return new ResponseEntity<>(resourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getTeacherCourseTableGroupAndProject:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeacherCourseTableSimpleResource>> getTeachersCurrentCourseTableList(
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码：teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }

        try {
            List<TeacherCourseTableSimpleResource> courseTableSimpleVoList =
                    teacherCourseTableDetailService.getTeacherCurrentCourseTableList(teacherId);
            return new ResponseEntity<>(courseTableSimpleVoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getTeacherCurrentCourseTableList:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

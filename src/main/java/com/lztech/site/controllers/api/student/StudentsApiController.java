package com.lztech.site.controllers.api.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.coursetable.CourseTableService;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseNameFirstSpell;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-06-23T11:52:08.788+08:00")

@Controller
public class StudentsApiController implements StudentsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CourseTableService courseTableService;

    @org.springframework.beans.factory.annotation.Autowired
    public StudentsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<StudentCourseNameFirstSpell>> getCourseAndCourseTableIdsByStudentId(
            @NotNull @ApiParam(value = "学生Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "openId") @Valid @RequestParam(value = "openId", required = false) String openId,
            @ApiParam(value = "学期") @Valid @RequestParam(value = "term", required = false) Integer term,
            @ApiParam(value = "学年") @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear) {

        try {
            if (StringUtils.isAnyBlank(validCode, userId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<StudentCourseNameFirstSpell> result =
                    courseTableService.getCourseAndCourseTableIdsByStudentId(userId, schoolYear, term, openId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseAndCourseTableIdsByStudentId:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

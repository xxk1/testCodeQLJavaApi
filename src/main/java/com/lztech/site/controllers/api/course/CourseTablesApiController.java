package com.lztech.site.controllers.api.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.course.CourseStudentResource;
import com.lztech.site.service.coursetable.CourseTableService;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-13T02:22:11.814Z")

@Controller
public class CourseTablesApiController implements CourseTablesApi {

    private final Logger log = LoggerFactory.getLogger(CourseTablesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CourseTableService courseTableService;

    @org.springframework.beans.factory.annotation.Autowired
    public CourseTablesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<CourseStudentResource>> courseTablesStudentCourseListGet(
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学生Id")
            @Valid @RequestParam(value = "userId", required = false) String userId,
            @ApiParam(value = "openId")
            @Valid @RequestParam(value = "openId", required = false) String openId) {
        try {
            if (StringUtils.isAnyBlank(schoolYear, validCode) || term == null || StringUtils.isAllEmpty(userId, openId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return courseTableService.convertToCourseTableList(userId, schoolYear, term, openId);
        } catch (Exception e) {
            log.error("courseTablesStudentCourseListGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

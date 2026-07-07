package com.lztech.site.controllers.api.courseteacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.courseteacher.CourseTeacher;
import com.lztech.site.service.courseteacher.CourseTeacherService;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-10T08:03:24.890Z")

@Controller
public class CourseteacherApiController implements CourseteacherApi {

    private final Logger log = LoggerFactory.getLogger(CourseteacherApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private CourseTeacherService courseTeacherService;

    @org.springframework.beans.factory.annotation.Autowired
    public CourseteacherApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<CourseTeacher>> courseteacherUserIdGet(
            @ApiParam(value = "老师Id", required = true)
            @PathVariable("userId") String userId,
            @NotNull @ApiParam(value = "验证码（userId=?&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isBlank(userId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String validKey = "userId=" + userId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return courseTeacherService.getCourseList(userId);
        } catch (Exception e) {
            log.error("courseteacherUserIdGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

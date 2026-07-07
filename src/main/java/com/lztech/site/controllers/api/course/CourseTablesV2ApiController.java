package com.lztech.site.controllers.api.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.course.CourseStudentResource;
import com.lztech.site.service.coursetable.StudentCourseListV2Service;
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

/**
 * 课程表 V2 API 控制器
 * 性能优化版本，使用 Native Query 查询
 */
@Controller
public class CourseTablesV2ApiController implements CourseTablesV2Api {

    private final Logger log = LoggerFactory.getLogger(CourseTablesV2ApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private StudentCourseListV2Service studentCourseListV2Service;

    @org.springframework.beans.factory.annotation.Autowired
    public CourseTablesV2ApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<List<CourseStudentResource>> courseTablesStudentCourseListGetV2(
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            
            @NotNull @ApiParam(value = "验证码（?&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            
            @ApiParam(value = "学生Id")
            @Valid @RequestParam(value = "userId", required = false) String userId,
            
            @ApiParam(value = "openId")
            @Valid @RequestParam(value = "openId", required = false) String openId) {
        
        try {
            // 参数校验
            if (StringUtils.isAnyBlank(schoolYear, validCode) || term == null || StringUtils.isAllEmpty(userId, openId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            // 验证码校验
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            // 调用 V2 服务（性能优化版本）
            return studentCourseListV2Service.getStudentCourseList(userId, schoolYear, term, openId);
        } catch (Exception e) {
            log.error("courseTablesStudentCourseListGetV2 error: userId={}, schoolYear={}, term={}, openId={}", 
                    userId, schoolYear, term, openId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

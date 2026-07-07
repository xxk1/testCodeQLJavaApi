package com.lztech.site.controllers.api.courseinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.course.Course;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.resource.course.CourseInfo;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.course.CourseCoverVo;
import com.lztech.site.viewmodel.course.CourseData;
import com.lztech.site.viewmodel.course.CourseImageInfoQueryParam;
import com.lztech.site.viewmodel.course.CourseImageInfoVo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-07T09:16:12.156Z")

@Controller
public class CoursesApiController implements CoursesApi {

    private final Logger log = LoggerFactory.getLogger(CoursesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private CourseService courseService;

    @org.springframework.beans.factory.annotation.Autowired
    public CoursesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> coursesPost(@NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
                                            @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                            @ApiParam(value = "课程列表", required = true) @Valid @RequestBody List<CourseInfo> courses) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Course> courseList = courseService.saveCourses(courses);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("coursesPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteCourse(@NotNull @ApiParam(value = "验证码：id=?&signKey=123123(md5加密)", required = true)
                                             @Valid @RequestParam(value = "validCode", required = true)
                                                     String validCode,
                                             @ApiParam(value = "课程id", required = true)
                                             @PathVariable("id") String id) {

        if (StringUtils.isAnyBlank(id, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseService.deleteCourse(id);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("deleteCourse->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateCourseName(@NotNull @ApiParam(value = "验证码：id=?&signKey=123123(md5加密)", required = true)
                                                 @Valid @RequestParam(value = "validCode", required = true)
                                                         String validCode,
                                                 @ApiParam(value = "课程id", required = true) @PathVariable("id")
                                                         String id,
                                                 @NotNull @ApiParam(value = "新课程名称", required = true)
                                                 @Valid @RequestParam(value = "newCourseName", required = true)
                                                         String newCourseName) {
        if (StringUtils.isAnyBlank(id, validCode, newCourseName)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseService.updateCourseName(id, newCourseName);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("deleteCourse->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseData>> getAllCourseList(@NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                             @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<CourseData> courseList = courseService.getAllCourseList();
            return new ResponseEntity(courseList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getAllCourseList->{}", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> addOrUpdateCourseCover(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "封面信息") @Valid @RequestBody CourseCoverVo courseCoverVo) {

        if (ObjectUtils.isEmpty(courseCoverVo) || StringUtils.isAnyEmpty(validCode, courseCoverVo.getCourseId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Course course = courseService.findById(courseCoverVo.getCourseId());
            if (ObjectUtils.isEmpty(course)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.NOT_FOUND);
            }
            courseService.addOrUpdateCourseCover(course, courseCoverVo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("addOrUpdateCourseCover:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseImageInfoVo>> getCourseImageInfos(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "查询参数") @Valid @RequestBody CourseImageInfoQueryParam courseImageInfoQueryParam) {

        if (StringUtils.isAnyEmpty(validCode, courseImageInfoQueryParam.getCourseIds())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseImageInfoVo> courseImageInfoVos = courseService.getCourseImageInfos(courseImageInfoQueryParam.getCourseIds());
            return new ResponseEntity<>(courseImageInfoVos,HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseImageInfos:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.lztech.site.controllers.api.courseresources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.Course;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.resource.college.CollegeCourseResource;
import com.lztech.site.resource.course.*;
import com.lztech.site.service.college.CollegeService;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseresources.CourseResourcesService;
import com.lztech.site.until.CustomRuntimeException;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseconstruction.CourseResourceKnowledgePointVo;
import com.lztech.site.viewmodel.courseresource.CourseInfoResourceVo;
import com.lztech.site.viewmodel.courseresource.FoundationCoursePageVo;
import com.lztech.site.viewmodel.courseresource.TestPaperResource;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-10T01:59:13.968Z")

@Controller
public class CourseResourcesApiController implements CourseResourcesApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseResourcesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;
    @Autowired
    CourseResourcesService courseResourcesService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CollegeService collegeService;

    @org.springframework.beans.factory.annotation.Autowired
    public CourseResourcesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CourseResourcesPageVo> getCourseResourcePage(@NotNull @ApiParam(value = "MD5加密验证字符串", required = true)
                                                                       @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                                       @NotNull @ApiParam(value = "当前页", required = true)
                                                                       @Valid @RequestParam(value = "page", required = true) Integer page,
                                                                       @NotNull @ApiParam(value = "每页个数", required = true)
                                                                       @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                                       @ApiParam(value = "学院Id")
                                                                       @Valid @RequestParam(value = "collegeId", required = false) String collegeId,
                                                                       @ApiParam(value = "课程名称")
                                                                       @Valid @RequestParam(value = "courseName", required = false) String courseName,
                                                                       @ApiParam(value = "课程来源 0：数据对接；1：课程管理老师新建") @Valid
                                                                       @RequestParam(value = "courseSource", required = false) Integer courseSource) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            CourseResourcesPageVo courseResourcesPageVo = courseResourcesService.getCourseList(collegeId, courseName, page, pageSize, courseSource);
            return new ResponseEntity<>(courseResourcesPageVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseResourcePage->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //region 根据课程名称查询课程信息
    public ResponseEntity<List<CourseInfoResource>> courseresourcesCourseinforesourcesGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串（&signKey=123123）", required = true) @Valid
            @RequestParam(value = "validCode", required = true)
            String validCode,
            @NotNull @ApiParam(value = "课程名称", required = true) @Valid
            @RequestParam(value = "courseName", required = true)
            String courseName) {
        if (StringUtils.isAnyBlank(validCode, courseName)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return courseResourcesService.getCourseInfoResources(courseName);
        } catch (Exception e) {
            LOGGER.error("courseresourcesCourseinforesourcesGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //endregion
    public ResponseEntity<List<CourseResourceVo>> allcourseresourceGet(@NotNull @ApiParam(value = "MD5加密验证字符串（&signKey=123123）", required = true)
                                                                       @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return courseResourcesService.getAllCourseList();
        } catch (Exception e) {
            LOGGER.error("allcourseresourceGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TestPaperResource>> getTestPapers(
            @NotNull @ApiParam(value = "课程Id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "教师Id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码（courseId={}&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "测验名称")
            @Valid @RequestParam(value = "testName", required = false) String testName) {
        if (StringUtils.isAnyBlank(validCode, courseId, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = "courseId=" + courseId + signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<TestPaperResource> testPaperResources =
                    courseResourcesService.getTestPapers(courseId, teacherId, testName);
            return new ResponseEntity(testPaperResources, HttpStatus.OK);
        } catch (CustomRuntimeException cre) {
            return new ResponseEntity(cre.getErrorResult(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            LOGGER.error("testPapersGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseInfoPageVo> getCourseInfo(
            @NotNull @ApiParam(value = "MD5加密验证字符串（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程名称/课程编号", required = true)
            @Valid @RequestParam(value = "courseNameOrNo", required = true) String courseNameOrNo,
            @NotNull @ApiParam(value = "当前页", required = true)
            @Valid @RequestParam(value = "page", required = true) Integer page,
            @NotNull @ApiParam(value = "每页个数", required = true)
            @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize) {
        try {
            if (StringUtils.isAnyEmpty(validCode, courseNameOrNo) || page == null || page == 0
                    || pageSize == null || pageSize == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseInfoPageVo courseInfoPageVo = courseResourcesService.getCourseInfoList(courseNameOrNo, page, pageSize);
            return new ResponseEntity<>(courseInfoPageVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseInfo" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<FoundationCoursePageVo> getFoundationCourseList(
            @NotNull @ApiParam(value = "MD5加密验证字符串", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "当前页", required = true)
            @Valid @RequestParam(value = "page", required = true) Integer page,
            @NotNull @ApiParam(value = "每页个数", required = true)
            @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @ApiParam(value = "课程名称") @Valid @RequestParam(value = "courseName", required = false) String courseName,
            @ApiParam(value = "教师工号") @Valid @RequestParam(value = "teacherNo", required = false) String teacherNo,
            @ApiParam(value = "时间范围-开始时间") @Valid @RequestParam(value = "startTime", required = false) String startTime,
            @ApiParam(value = "时间范围-结束时间") @Valid @RequestParam(value = "endTime", required = false) String endTime) {
        try {

            if (StringUtils.isBlank(validCode) || page == null || page == 0 || pageSize == null || pageSize == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            FoundationCoursePageVo foundationCoursePageVo = courseResourcesService
                    .getFoundationCourseList(courseName, page, pageSize, teacherNo, startTime, endTime);
            return new ResponseEntity<>(foundationCoursePageVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getFoundationCourseList -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Boolean> getCourseDeleteState(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：courseId={xxx}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (StringUtils.isAnyBlank(courseId, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        String validKey = "courseId=" + courseId + signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {

            boolean courseDeleteState = courseResourcesService.getCourseDeleteState(courseId);
            return new ResponseEntity<>(courseDeleteState, HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("getCourseDeleteState -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<CourseInfosTO>> getAvailableCourseInfo(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程名称或编号") @Valid @RequestParam(value = "courseNameOrNo", required = false) String courseNameOrNo,
            @ApiParam(value = "学院id") @Valid @RequestParam(value = "collegeId", required = false) String collegeId
    ) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            List<CourseInfosTO> courseInfosTOS = courseResourcesService.getAvailableCourseInfo(collegeId, courseNameOrNo);
            return new ResponseEntity<List<CourseInfosTO>>(courseInfosTOS, HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("getAvailableCourseInfo -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> addCourseResourceKnowledgePoint(@NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                                @Valid @RequestParam(value = "validCode", required = true)
                                                                String validCode,
                                                                @ApiParam(value = "", required = true) @Valid
                                                                @RequestBody CourseResourceKnowledgePointVo resourcePointVo) {
        if (StringUtils.isAnyBlank(validCode) || Objects.isNull(resourcePointVo) || StringUtils.isAnyBlank(resourcePointVo.getResourceId(),
                resourcePointVo.getUserId(), resourcePointVo.getUserName())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseResourcesService.addCourseResourceKnowledgePoint(resourcePointVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("addCourseResourceKnowledgePoint -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseInfoVo> getCourseInfoByCourseNo(
            @NotNull @ApiParam(value = "MD5加密验证字符串（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程编号", required = true)
            @Valid @RequestParam(value = "courseNo", required = true) String courseNo) {
        try {
            if (StringUtils.isAnyEmpty(validCode, courseNo)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseInfoVo courseInfoVo = courseResourcesService.getCourseInfoByCourseNo(courseNo);
            if (ObjectUtils.isNotEmpty(courseInfoVo)) {
                return new ResponseEntity<>(courseInfoVo, HttpStatus.OK);
            } else {
                return new ResponseEntity("查询课程信息为空", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("getCourseInfoByCourseNo->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseInfoResourceVo>> getCourseInfos(
            @NotNull @ApiParam(value = "MD5加密验证字符串（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程名称/课程编号", required = true)
            @Valid @RequestParam(value = "courseNameOrNo", required = true) String courseNameOrNo) {
        try {
            if (StringUtils.isAnyEmpty(validCode, courseNameOrNo)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CourseInfoResourceVo> courseInfoResourceVos = courseResourcesService.getCourseInfos(courseNameOrNo);
            return new ResponseEntity<>(courseInfoResourceVos, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseInfos->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> addCourse(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程列表", required = true)
            @Valid @RequestBody CourseResourceInfoVo courseResourceInfoVo) {
        try {
            if (StringUtils.isAnyEmpty(validCode) || ObjectUtils.isEmpty(courseResourceInfoVo)
                    || StringUtils.isEmpty(courseResourceInfoVo.getCourseCode())
                    || StringUtils.isEmpty(courseResourceInfoVo.getCourseName())
                    || StringUtils.isEmpty(courseResourceInfoVo.getCollegeId())
                    || StringUtils.isEmpty(courseResourceInfoVo.getOperatorId())
                    || StringUtils.isEmpty(courseResourceInfoVo.getOperatorName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            Course course = courseService.getCourseByCourseCode(courseResourceInfoVo.getCourseCode());
            if (ObjectUtils.isNotEmpty(course)) {
                return new ResponseEntity(ErrorResult.customError("课程编码为" + courseResourceInfoVo.getCourseCode() + "的课程已经存在"), HttpStatus.CONFLICT);
            }

            College college = collegeService.getCollegeById(courseResourceInfoVo.getCollegeId());
            if (ObjectUtils.isEmpty(college)) {
                return new ResponseEntity(ErrorResult.customError("所属学院不存在"), HttpStatus.CONFLICT);
            }

            courseService.addCourse(courseResourceInfoVo, college);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("addCourse->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CollegeCourseResource>> getCourseInfosByColegeIds(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学院Id列表", required = true)
            @Valid @RequestBody List<String> collegeIds) {
        try {
            if (StringUtils.isAnyEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CollegeCourseResource> collegeCourseResources = courseService.getCourseInfosByColegeIds(collegeIds);
            return new ResponseEntity<>(collegeCourseResources, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseInfosByColegeIds->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseInfoVo>> queryCourseList(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程名称或课程编号") @Valid @RequestParam(value = "courseNameOrNo", required = false)
            String courseNameOrNo) {
        if (StringUtils.isAnyEmpty(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseInfoVo> courseInfoVos = courseResourcesService.queryCourseList( courseNameOrNo);
            return new ResponseEntity<>(courseInfoVos, HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("queryCourseList" , e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

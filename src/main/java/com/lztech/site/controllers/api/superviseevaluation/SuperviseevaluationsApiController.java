package com.lztech.site.controllers.api.superviseevaluation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailResource;
import com.lztech.site.service.superviseevaluation.SuperviseEvaluationService;
import com.lztech.site.service.superviseevaluation.SupervisorRecommendService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.superviseevaluation.*;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-12T04:24:19.239Z")

@Controller
public class SuperviseevaluationsApiController implements SuperviseevaluationsApi {

    private final Logger logger = LoggerFactory.getLogger(SuperviseevaluationsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Value("${studentType}")
    private Integer defaultStudentType;
    @Autowired
    private SuperviseEvaluationService superviseEvaluationService;
    @Resource
    private SupervisorRecommendService supervisorRecommendService;

    @org.springframework.beans.factory.annotation.Autowired
    public SuperviseevaluationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<SupervisionCourseListPage> superviseevaluationsSupervisioncoursesPost(
            @ApiParam(value = "督导听课老师课程列表查询条件", required = true)
            @Valid @RequestBody SupervisionCourseQueryParam supervisorListenCourseQueryParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (supervisorListenCourseQueryParam == null || supervisorListenCourseQueryParam.getPage() == null
                    || supervisorListenCourseQueryParam.getPageSize() == null
                    || supervisorListenCourseQueryParam.getNowTime() == null
                    || supervisorListenCourseQueryParam.getPage() == 0 || supervisorListenCourseQueryParam.getPageSize() == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return superviseEvaluationService.getSuperviseCoursePage(supervisorListenCourseQueryParam);
        } catch (Exception e) {
            logger.error("superviseevaluationsSupervisioncoursesPost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SupervisionCourseVo>> superviseevaluationsCoursetablesGet(
            @NotNull @ApiParam(value = "课表id组合（逗号分割）", required = true)
            @Valid @RequestParam(value = "courseTableIds", required = true) String courseTableIds,
            @NotNull @ApiParam(value = "现在的时间（yyyy-MM-hh HH:mm:ss）", required = true)
            @Valid @RequestParam(value = "nowTime", required = true) String nowTime,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学年")
            @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @ApiParam(value = "学期") @Valid @RequestParam(value = "term", required = false) Integer term,
            @ApiParam(value = "开课类型")
            @Valid @RequestParam(value = "studentType", required = false) Integer studentType) {
        try {
            if (StringUtils.isAnyEmpty(validCode, nowTime, courseTableIds)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return superviseEvaluationService.getCourseTablesByIdsAndNowTime(courseTableIds, nowTime, schoolYear, term, String.valueOf(studentType)
                    , null);
        } catch (Exception e) {
            logger.error("superviseevaluationsCoursetablesGet:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<SupervisionCourseResource> superviseevaluationsCoursetablesCourseTableIdGet(
            @ApiParam(value = "课表id", required = true) @PathVariable("courseTableId") String courseTableId,
            @NotNull @ApiParam(value = "现在的时间（yyyy-MM-hh HH:mm:ss）", required = true)
            @Valid @RequestParam(value = "nowTime", required = true) String nowTime,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(validCode, nowTime, courseTableId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return superviseEvaluationService.getCourseTableByIdAndNowTime(courseTableId, nowTime);
        } catch (Exception e) {
            logger.error("superviseevaluationsCoursetablesGet:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<SuperviseCourseTablePage> coursetablePagePost(
            @ApiParam(value = "督导听课信息", required = true)
            @Valid @RequestBody SuperviseEvaluationResource superviseEvaluationResource,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode) || superviseEvaluationResource.getPage() == null
                    || superviseEvaluationResource.getPageSize() == null || superviseEvaluationResource.getPage() == 0
                    || superviseEvaluationResource.getPageSize() == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return superviseEvaluationService.getCourseTablePage(superviseEvaluationResource);
        } catch (Exception e) {
            logger.error("coursetablePagePost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<SuperviseCourseTablePage> politicalcourseCoursetablePagePost(
            @ApiParam(value = "督导听课信息", required = true)
            @Valid @RequestBody SuperviseEvaluationResource superviseEvaluationResource,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode) || superviseEvaluationResource.getPage() == null
                    || superviseEvaluationResource.getPageSize() == null || superviseEvaluationResource.getPage() == 0
                    || superviseEvaluationResource.getPageSize() == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            SuperviseCourseTablePage superviseCourseTablePage =
                    superviseEvaluationService.getPoliticalCourseCourseTablePage(superviseEvaluationResource);

            return new ResponseEntity<>(superviseCourseTablePage, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("politicalcourseCoursetablePagePost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailResource>> getSupervisorRecommendCourseTable(@ApiParam(value = "督导听课推荐课程表查询条件", required = true)
                                                                                             @Valid @RequestBody
                                                                                             SupervisorRecommendQueryParam
                                                                                                     supervisorListenCourseQueryParam,
                                                                                             @NotNull @ApiParam(value = "验证码" +
                                                                                                     "：&signKey=123123(md5加密)", required = true)
                                                                                             @Valid @RequestParam(value = "validCode",
                                                                                                     required = true)
                                                                                             String validCode) {
        if (Objects.isNull(supervisorListenCourseQueryParam) ||
                CollectionUtils.isEmpty(supervisorListenCourseQueryParam.getSupervisorRecommendVoList())
                || StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            List<CourseTableDetailResource> courseTableDetailResourceList =
                    supervisorRecommendService.getSupervisorRecommendCourseTable(supervisorListenCourseQueryParam);
            return new ResponseEntity(courseTableDetailResourceList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getSupervisorRecommendCourseTable:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailResource>> getNotSupervisedCourse(@ApiParam(value = "查询条件", required = true)
                                                                                  @Valid @RequestBody
                                                                                  NotSupervisionCourseQueryParam supervisionCourseQueryParam,
                                                                                  @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)",
                                                                                          required = true)
                                                                                  @Valid @RequestParam(value = "validCode", required = true)
                                                                                  String validCode) {
        if (Objects.isNull(supervisionCourseQueryParam) || StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseTableDetailResource> courseTableDetailResourceList =
                    supervisorRecommendService.getNotSupervisedCourse(supervisionCourseQueryParam);
            return new ResponseEntity(courseTableDetailResourceList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getNotSupervisedCourse:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.lztech.site.controllers.api.superviseevaluation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.superviseevaluation.SuperviseEvaluationV2Service;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.superviseevaluation.CanListenCourseTableDetailResourceVo;
import com.lztech.site.viewmodel.superviseevaluation.v2.*;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-07-05T03:44:28.658Z")

@Controller
public class SuperviseevaluationsApiV2Controller implements SuperviseevaluationsApiV2 {

    private final Logger logger = LoggerFactory.getLogger(SuperviseevaluationsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private SuperviseEvaluationV2Service superviseEvaluationV2Service;

    @org.springframework.beans.factory.annotation.Autowired
    public SuperviseevaluationsApiV2Controller(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<SupervisionCourseAndCourseTableDetailVo>> getSupervisionCourseAndCourseTableDetail(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode, @ApiParam(value = "查询条件", required = true)
            @Valid @RequestBody SupervisionCourseTableDetialQueryParam queryParam) {
        try {
            if (StringUtils.isAnyBlank(validCode) || queryParam == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (ObjectUtils.isEmpty(ObjectUtils.isEmpty(queryParam.getTerm())
                    || StringUtils.isAnyBlank(queryParam.getNowTime(), queryParam.getSchoolYear()))) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<SupervisionCourseAndCourseTableDetailVo> result =
                    superviseEvaluationV2Service.getSupervisionCourseAndCourseTableDetail(queryParam);

            return new ResponseEntity<List<SupervisionCourseAndCourseTableDetailVo>>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getSupervisionCourseAndCourseTableDetail:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<SupervisionCourseListPageV2> getSupervisionCourseAndCourseTableDetailList(
            @ApiParam(value = "督导听课老师课程列表查询条件", required = true)
            @Valid @RequestBody SupervisionCourseQueryParamV2 queryParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (queryParam == null || queryParam.getPage() == null
                    || queryParam.getPageSize() == null
                    || queryParam.getNowTime() == null
                    || queryParam.getPage() == 0 || queryParam.getPageSize() == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            SupervisionCourseListPageV2 result =
                    superviseEvaluationV2Service.getSupervisionCourseAndCourseTableDetailList(queryParam);

            return new ResponseEntity<SupervisionCourseListPageV2>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getSupervisionCourseAndCourseTableDetailList:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SupervisionCourseTableAndDetailVo>> getSupervisionCourseTableAndDetailList(
            @ApiParam(value = "查询条件", required = true)
            @Valid @RequestBody SupervisionCourseAndDetailQueryParam queryParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (ObjectUtils.isEmpty(queryParam) || ObjectUtils.isEmpty(queryParam.getCollegeType())
                    || ObjectUtils.isEmpty(queryParam.getTerm())
                    || StringUtils.isAnyBlank(queryParam.getCourseId(), queryParam.getSchoolYear(),
                    queryParam.getTeachers(), queryParam.getNowTime())
                    || StringUtils.isAllBlank(queryParam.getCollegeId(), queryParam.getCollegeIds())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<SupervisionCourseTableAndDetailVo> courseTableAndDetailVos =
                    superviseEvaluationV2Service.getSupervisionCourseTableAndDetailList(queryParam);

            return new ResponseEntity<>(courseTableAndDetailVos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getSupervisionCourseTableAndDetailList:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ListenCourseInfoVo> getListenCourseInfoByCourseTableDetailId(
            @NotNull @ApiParam(value = "课表明细id", required = true)
            @Valid @RequestParam(value = "courseTableDetailId", required = true) String courseTableDetailId,
            @NotNull @ApiParam(value = "开课学院类型（0：授课教师学院；1：开课学院）", required = true)
            @Valid @RequestParam(value = "collegeType", required = true) Integer collegeType,
            @NotNull @ApiParam(value = "学院id", required = true)
            @Valid @RequestParam(value = "collegeId", required = true) String collegeId,
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @ApiParam(value = "是否直播：null或true：只查询直播;false：不区分直播")
            @Valid @RequestParam(value = "hasLive", required = false) Boolean hasLive,
            @NotNull @ApiParam(value = "验证码：courseDetailId={}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (ObjectUtils.isEmpty(collegeType)
                    || StringUtils.isAnyBlank(courseTableDetailId, validCode, collegeId, teacherId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode("courseTableDetailId", courseTableDetailId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            ListenCourseInfoVo listenCourseInfoVo =
                    superviseEvaluationV2Service.getListenCourseInfoByCourseDetailId(
                            courseTableDetailId, collegeType, collegeId, teacherId, hasLive);

            return new ResponseEntity<>(listenCourseInfoVo, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getListenCourseInfoByCourseDetailId:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CanListenCourseTableDetailResourceVo> getCanListenCourseTableDetailResourceVo(
            @ApiParam(value = "课表详情ID", required = true)
            @PathVariable("courseTableDetailId") String courseTableDetailId,
            @NotNull @ApiParam(value = "验证码：courseTableDetailId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(courseTableDetailId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode("courseTableDetailId", courseTableDetailId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            CanListenCourseTableDetailResourceVo canListenCourseTableDetailResourceVo =
                    superviseEvaluationV2Service.getCanListenCourseTableDetailResourceVo(courseTableDetailId);

            return new ResponseEntity<>(canListenCourseTableDetailResourceVo, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getCanListenCourseTableDetailResourceVo:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


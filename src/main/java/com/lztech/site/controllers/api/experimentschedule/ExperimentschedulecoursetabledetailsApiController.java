package com.lztech.site.controllers.api.experimentschedule;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetail;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.ScheduleStatus;
import com.lztech.site.service.experimentschedule.ExperimentScheduleCourseTableDetailExtendService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.experimentschedule.ExperimentScheduleCourseTableDetailExportQueryVo;
import com.lztech.site.viewmodel.experimentschedule.ExperimentScheduleCourseTableDetailPageVo;
import com.lztech.site.viewmodel.experimentschedule.ExperimentScheduleCourseTableDetailQueryVo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-12T10:03:02.836Z")

@Controller
public class ExperimentschedulecoursetabledetailsApiController implements ExperimentschedulecoursetabledetailsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentschedulecoursetabledetailsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ExperimentScheduleCourseTableDetailExtendService courseTableDetailService;


    @org.springframework.beans.factory.annotation.Autowired
    public ExperimentschedulecoursetabledetailsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ExperimentScheduleCourseTableDetailPageVo> getExperimentScheduleCourseTableDetails(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "", required = true) @Valid @RequestBody ExperimentScheduleCourseTableDetailQueryVo queryParam) {
        if (StringUtils.isAnyBlank(validCode) || ObjectUtils.isEmpty(queryParam)
                || ObjectUtils.isEmpty(queryParam.getPage()) || queryParam.getPage() < 1
                || ObjectUtils.isEmpty(queryParam.getPageSize()) || queryParam.getPageSize() < 1
                || ObjectUtils.isEmpty(queryParam.getStartDate()) || ObjectUtils.isEmpty(queryParam.getEndDate())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        try {
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            ExperimentScheduleCourseTableDetailPageVo pageVo =
                    courseTableDetailService.getExperimentScheduleCourseTableDetails(queryParam);

            return new ResponseEntity(pageVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getExperimentScheduleCourseTableDetails->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteExperimentScheduleCourseTableDetails(
            @ApiParam(value = "排课id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "验证码id={}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        try {
            if (!ValidUtils.checkAuthCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            ExperimentScheduleCourseTableDetail courseTableDetail = courseTableDetailService.findExperimentScheduleCourseTableDetailById(id);
            if (ObjectUtils.isEmpty(courseTableDetail)) {
                return new ResponseEntity(ErrorResult.dataNotExistError("排课课表"), HttpStatus.NOT_FOUND);
            }

            if (courseTableDetail.getScheduleStatus() != ScheduleStatus.WAIT_SCHEDULE
                    && courseTableDetail.getScheduleStatus() != ScheduleStatus.EDUCATIONAL_ADMINISTRATION_DELETE) {
                return new ResponseEntity(ErrorResult.dataNotExistError("只有排课状态是待排课或教务删除课表才能删除"), HttpStatus.CONFLICT);
            }

            courseTableDetailService.deleteExperimentScheduleCourseTableDetails(courseTableDetail);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("deleteExperimentScheduleCourseTableDetails->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> exportExperimentScheduleCourseTableDetailsExcel(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "", required = true) @Valid @RequestBody ExperimentScheduleCourseTableDetailExportQueryVo queryParam,
            HttpServletResponse response) {
        if (StringUtils.isAnyBlank(validCode) || ObjectUtils.isEmpty(queryParam)
                || ObjectUtils.isEmpty(queryParam.getStartDate()) || ObjectUtils.isEmpty(queryParam.getEndDate())
                || ObjectUtils.isEmpty(queryParam.getTerm()) || StringUtils.isAnyBlank(queryParam.getTerm())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        try {
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<Map<String, Object>> exportData = courseTableDetailService.getExportExperimentScheduleCourseTableDetails(queryParam);
            if (CollectionUtils.isEmpty(exportData)) {
                return new ResponseEntity(ErrorResult.customError("数据为空，无法导出Excel！"),HttpStatus.CONFLICT);
            }

            if (!courseTableDetailService.exportExperimentScheduleCourseTableDetailsExcel(
                    response, exportData, queryParam.getTerm())) {
                return new ResponseEntity(ErrorResult.customError("导出失败"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("exportExperimentScheduleCourseTableDetailsExcel->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
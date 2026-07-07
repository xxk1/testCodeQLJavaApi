package com.lztech.site.controllers.api.guidancereminderrecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.guidancereminderrecord.GuidanceReminderRecordService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.guidancereminderrecord.GuidanceReminderRecordResource;
import com.lztech.site.viewmodel.guidancereminderrecord.GuidanceReminderRecordVo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-03-17T12:38:57.390Z")

@Controller
public class GuidancereminderrecordApiController implements GuidancereminderrecordApi {

    private static final Logger LOG = LoggerFactory.getLogger(GuidancereminderrecordApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    private GuidanceReminderRecordService guidanceReminderRecordService;

    @org.springframework.beans.factory.annotation.Autowired
    public GuidancereminderrecordApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addGuidanceReminderRecord(
            @ApiParam(value = "新增班级管理记录教师查看记录信息", required = true)
            @Valid @RequestBody GuidanceReminderRecordResource guidanceReminderRecordResource) {
        if (ObjectUtils.isEmpty(guidanceReminderRecordResource) ||
                StringUtils.isAnyBlank(guidanceReminderRecordResource.getUserId(),
                        guidanceReminderRecordResource.getUserNo(), guidanceReminderRecordResource.getUserName()
                        , guidanceReminderRecordResource.getFunctionCode(), guidanceReminderRecordResource.getFunctionDescription()
                        , guidanceReminderRecordResource.getValidCode())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("userId",
                guidanceReminderRecordResource.getUserId(), guidanceReminderRecordResource.getValidCode())) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            guidanceReminderRecordService.addGuidanceReminderRecord(guidanceReminderRecordResource);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("addGuidanceReminderRecord->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<GuidanceReminderRecordVo> getGuidanceReminderRecord(
            @NotNull @ApiParam(value = "用户id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "功能编码", required = true)
            @Valid @RequestParam(value = "functionCode", required = true) String functionCode,
            @NotNull @ApiParam(value = "加密验证码userId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {


        if (StringUtils.isAnyBlank(userId, functionCode, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("userId", userId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            GuidanceReminderRecordVo guidanceReminderRecordVo = guidanceReminderRecordService.getGuidanceReminderRecord(userId, functionCode);
            if (ObjectUtils.isNotEmpty(guidanceReminderRecordVo)) {
                return new ResponseEntity(guidanceReminderRecordVo, HttpStatus.OK);
            } else {
                return new ResponseEntity(ErrorResult.dataNotExistError("未找到教师查看引导记录"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.error("getGuidanceReminderRecord->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

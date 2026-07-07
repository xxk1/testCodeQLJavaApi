package com.lztech.site.controllers.api.experimentschedulecoursetabledetail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetail;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.ScheduleStatus;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.experimentschedulecoursetabledetail.ExperimentScheduleCourseLogService;
import com.lztech.site.service.experimentschedulecoursetabledetail.ExperimentScheduleCourseTableDetailService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.experimentschedulecoursetabledetail.*;
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

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-12T16:34:51.679Z")

@Controller
public class ExperimentScheduleCourseTableDetailsApiController implements ExperimentScheduleCourseTableDetailsApi {

    private final Logger logger = LoggerFactory.getLogger(ExperimentScheduleCourseTableDetailsApiController.class);

    @Autowired
    private ExperimentScheduleCourseTableDetailService experimentScheduleCourseTableDetailService;
    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private ExperimentScheduleCourseLogService experimentScheduleCourseLogService;
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public ExperimentScheduleCourseTableDetailsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<DataDockingCourseTableDetailPageResource> getDataDockingCourseTableDetailPageResource(
            @ApiParam(value = "查询参数", required = true)
            @Valid @RequestBody ExperimentScheduleObtainDataParam experimentScheduleObtainDataParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(experimentScheduleObtainDataParam.getStartDate(), experimentScheduleObtainDataParam.getEndDate()
                    , validCode) || experimentScheduleObtainDataParam.getPageNum() == null || experimentScheduleObtainDataParam.getPageNum() == 0 ||
                    experimentScheduleObtainDataParam.getPageSize() == null || experimentScheduleObtainDataParam.getPageSize() == 0
            ) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (ExperimentScheduleCourseTableDetailService.validateDates(
                    experimentScheduleObtainDataParam.getStartDate(), experimentScheduleObtainDataParam.getEndDate())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            DataDockingCourseTableDetailPageResource dataDockingCourseTableDetailPageResource =
                    experimentScheduleCourseTableDetailService.getDataDockingCourseTableDetailPageResource(experimentScheduleObtainDataParam);
            return new ResponseEntity<>(dataDockingCourseTableDetailPageResource, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getDataDockingCourseTableDetailPageResource:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> importExperimentScheduleCourseTableDetails(
            @ApiParam(value = "查询参数", required = true)
            @Valid @RequestBody ExperimentScheduleObtainDataParam experimentScheduleObtainDataParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(experimentScheduleObtainDataParam.getStartDate(),
                    experimentScheduleObtainDataParam.getEndDate(),
                    experimentScheduleObtainDataParam.getCreatorId(),
                    experimentScheduleObtainDataParam.getCreatorName(), validCode)
            ) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (ExperimentScheduleCourseTableDetailService.validateDates(
                    experimentScheduleObtainDataParam.getStartDate(), experimentScheduleObtainDataParam.getEndDate())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            experimentScheduleCourseTableDetailService.importExperimentScheduleCourseTableDetails(experimentScheduleObtainDataParam);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("importExperimentScheduleCourseTableDetails:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ExperimentScheduleCourseTableDetailRelevantResource> pushExperimentScheduleCourseTableDetail(
            @NotNull @ApiParam(value = "MD5加密验证字符串(id=?&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "发布实验课排课参数", required = true)
            @Valid @RequestBody ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailPushParam) {
        try {
            if (StringUtils.isAnyBlank(experimentScheduleCourseTableDetailPushParam.getId(),
                    experimentScheduleCourseTableDetailPushParam.getCourseDate(), validCode) ||
                    experimentScheduleCourseTableDetailPushParam.getStartSegment() == null ||
                    experimentScheduleCourseTableDetailPushParam.getStartSegment() == 0 ||
                    experimentScheduleCourseTableDetailPushParam.getEndSegment() == null ||
                    experimentScheduleCourseTableDetailPushParam.getEndSegment() == 0 ||
                    experimentScheduleCourseTableDetailPushParam.getStartSegment() >
                            experimentScheduleCourseTableDetailPushParam.getEndSegment()
            ) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (ExperimentScheduleCourseTableDetailService.validateDate(
                    experimentScheduleCourseTableDetailPushParam.getCourseDate())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("id", experimentScheduleCourseTableDetailPushParam.getId(), validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail =
                    experimentScheduleCourseTableDetailService.getExperimentScheduleCourseTableDetail(
                            experimentScheduleCourseTableDetailPushParam.getId());
            ExperimentScheduleCourseTableDetailRelevantResource experimentScheduleCourseTableDetailRelevantResource =
                    new ExperimentScheduleCourseTableDetailRelevantResource();
            if (ObjectUtils.isEmpty(experimentScheduleCourseTableDetail)) {
                experimentScheduleCourseTableDetailRelevantResource.setResult(false);
                experimentScheduleCourseTableDetailRelevantResource.setRemark("当前实验排课信息已被删除");
                return new ResponseEntity<>(experimentScheduleCourseTableDetailRelevantResource, HttpStatus.OK);
            }
            experimentScheduleCourseTableDetailRelevantResource
                    = experimentScheduleCourseTableDetailService.pushExperimentScheduleCourseTableDetail(
                    experimentScheduleCourseTableDetail, experimentScheduleCourseTableDetailPushParam);

            return new ResponseEntity<>(experimentScheduleCourseTableDetailRelevantResource, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("pushExperimentScheduleCourseTableDetail:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateEntranceGuardGeneratedStatus(
            @ApiParam(value = "实验课表id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "MD5加密验证字符串(id=?&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "门禁生成状态(0:没有生成;1:生成失败;2:生成成功;3:生成失败，没有门禁设备)", required = true)
            @Valid @RequestParam(value = "entranceGuardGeneratedStatus", required = true) Integer entranceGuardGeneratedStatus
    ) {
        try {
            if (StringUtils.isAnyBlank(id, validCode) || entranceGuardGeneratedStatus == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            experimentScheduleCourseTableDetailService.updateEntranceGuardGeneratedStatus(id, entranceGuardGeneratedStatus);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("updateEntranceGuardGeneratedStatus:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ExperimentScheduleCourseTableDetailInfoVo> getExperimentScheduleCourseTableDetailById(
            @ApiParam(value = "实验课表id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "操作人id", required = true)
            @Valid @RequestParam(value = "operatorId", required = true) String operatorId,
            @NotNull @ApiParam(value = "操作人名称", required = true)
            @Valid @RequestParam(value = "operatorName", required = true) String operatorName,
            @NotNull @ApiParam(value = "MD5加密验证字符串(id=?&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(id, operatorId, operatorName, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail =
                    experimentScheduleCourseTableDetailService.getExperimentScheduleCourseTableDetail(id);
            if (ObjectUtils.isEmpty(experimentScheduleCourseTableDetail)) {
                return new ResponseEntity(ErrorResult.customError("当前实验排课信息已被删除"), HttpStatus.CONFLICT);
            }
            String courseDate = DateUtils.formatDate(DateUtils.DATE, experimentScheduleCourseTableDetail.getCourseDate());
            String courseTableDetailId = experimentScheduleCourseTableDetailService.getCourseTableDetailId(experimentScheduleCourseTableDetail);
            if (ObjectUtils.isEmpty(courseTableDetailId)) {
                experimentScheduleCourseTableDetailService.updateScheduleStatus(experimentScheduleCourseTableDetail,
                        ScheduleStatus.EDUCATIONAL_ADMINISTRATION_DELETE, operatorId, operatorName);
                return new ResponseEntity(ErrorResult.customError("教务课表不存在"), HttpStatus.CONFLICT);
            }
            if (experimentScheduleCourseTableDetail.getScheduleStatus().equals(ScheduleStatus.EDUCATIONAL_ADMINISTRATION_DELETE)) {
                return new ResponseEntity(ErrorResult.customError("教务课表不存在"), HttpStatus.CONFLICT);
            }
            ExperimentScheduleCourseTableDetailInfoVo experimentScheduleCourseTableDetailInfoVo =
                    experimentScheduleCourseTableDetailService.getExperimentScheduleCourseTableDetailById(experimentScheduleCourseTableDetail);
            return new ResponseEntity<>(experimentScheduleCourseTableDetailInfoVo, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getExperimentScheduleCourseTableDetailById:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CancelExperimentScheduleCourseTableDetailModel> cancelExperimentScheduleCourseTableDetailById(
            @ApiParam(value = "排课id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "操作人id", required = true)
            @Valid @RequestParam(value = "operatorId", required = true) String operatorId,
            @NotNull @ApiParam(value = "操作人名称", required = true)
            @Valid @RequestParam(value = "operatorName", required = true) String operatorName,
            @ApiParam(value = "操作类型（0：仅取消、1：修改前取消）")
            @Valid @RequestParam(value = "operatorType", required = false) Integer operatorType,
            @NotNull @ApiParam(value = "MD5加密验证字符串(id=?&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(id, operatorId, operatorName, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail =
                    experimentScheduleCourseTableDetailService.getExperimentScheduleCourseTableDetail(id);
            CancelExperimentScheduleCourseTableDetailModel cancelExperimentScheduleCourseTableDetailModel =
                    new CancelExperimentScheduleCourseTableDetailModel();
            if (ObjectUtils.isEmpty(experimentScheduleCourseTableDetail)) {
                cancelExperimentScheduleCourseTableDetailModel.setCancelStatus("001");
                cancelExperimentScheduleCourseTableDetailModel.setCancelStatusRemark("实验课表不存在");
                return new ResponseEntity<>(cancelExperimentScheduleCourseTableDetailModel, HttpStatus.OK);
            }
            if (!experimentScheduleCourseTableDetail.getScheduleStatus().equals(ScheduleStatus.ALREADY_SCHEDULE)) {
                cancelExperimentScheduleCourseTableDetailModel.setCancelStatus("002");
                cancelExperimentScheduleCourseTableDetailModel.setCancelStatusRemark("实验课表还没有排课");
                return new ResponseEntity<>(cancelExperimentScheduleCourseTableDetailModel, HttpStatus.OK);
            }
            String courseTableDetailId = experimentScheduleCourseTableDetailService.getCourseTableDetailId(
                    experimentScheduleCourseTableDetail);
            if (ObjectUtils.isEmpty(courseTableDetailId)) {
                experimentScheduleCourseTableDetailService.updateScheduleStatus(experimentScheduleCourseTableDetail,
                        ScheduleStatus.EDUCATIONAL_ADMINISTRATION_DELETE, operatorId, operatorName);
                cancelExperimentScheduleCourseTableDetailModel.setCancelStatus("003");
                cancelExperimentScheduleCourseTableDetailModel.setCancelStatusRemark("教务课表不存在");
                return new ResponseEntity<>(cancelExperimentScheduleCourseTableDetailModel, HttpStatus.OK);
            }
            CourseTableDetail courseTableDetail = courseTableDetailRepository.findById(courseTableDetailId).get();
            experimentScheduleCourseTableDetailService.cancelExperimentScheduleCourseTableDetailById(
                    courseTableDetail, experimentScheduleCourseTableDetail, operatorId, operatorName,
                    cancelExperimentScheduleCourseTableDetailModel, operatorType);
            return new ResponseEntity<>(cancelExperimentScheduleCourseTableDetailModel, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("cancelExperimentScheduleCourseTableDetailById:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CheckExperimentScheduleCourseTableDetailModel> checkExperimentScheduleCourseTableDetailById(
            @ApiParam(value = "排课id", required = true) @PathVariable("id") String id,
            @ApiParam(value = "校验实验课排课参数", required = true) @Valid @RequestBody
            CheckExperimentScheduleCourseTableDetailParam checkExperimentScheduleCourseTableDetailParam,
            @NotNull @ApiParam(value = "MD5加密验证字符串(id=?&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(id, checkExperimentScheduleCourseTableDetailParam.getCourseDate(), validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<RoomSegmentTimeVo> roomSegmentTimeVoList = experimentScheduleCourseTableDetailService.getRoomSegmentTimeVoList(
                    checkExperimentScheduleCourseTableDetailParam.getStartSegment(),
                    checkExperimentScheduleCourseTableDetailParam.getEndSegment(),
                    checkExperimentScheduleCourseTableDetailParam.getRoomIdList());
            CheckExperimentScheduleCourseTableDetailModel checkExperimentScheduleCourseTableDetailModel =
                    new CheckExperimentScheduleCourseTableDetailModel();
            checkExperimentScheduleCourseTableDetailModel.setRoomSegmentTimeList(roomSegmentTimeVoList);

            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail =
                    experimentScheduleCourseTableDetailService.getExperimentScheduleCourseTableDetail(id);
            if (ObjectUtils.isEmpty(experimentScheduleCourseTableDetail)) {
                checkExperimentScheduleCourseTableDetailModel.setCheckStatus(Constant.ONE);
                checkExperimentScheduleCourseTableDetailModel.setCheckRemark("没有教室被排课");
                return new ResponseEntity<>(checkExperimentScheduleCourseTableDetailModel, HttpStatus.OK);
            }
            String courseTableDetailId = experimentScheduleCourseTableDetailService.getCourseTableDetailId(
                    experimentScheduleCourseTableDetail);
            if (StringUtils.isEmpty(courseTableDetailId)) {
                checkExperimentScheduleCourseTableDetailModel.setCheckStatus(Constant.ONE);
                checkExperimentScheduleCourseTableDetailModel.setCheckRemark("没有教室被排课");
                return new ResponseEntity<>(checkExperimentScheduleCourseTableDetailModel, HttpStatus.OK);
            }
            experimentScheduleCourseTableDetailService.checkExperimentScheduleCourseTableDetailById(
                    courseTableDetailId, checkExperimentScheduleCourseTableDetailParam, checkExperimentScheduleCourseTableDetailModel);
            if (ObjectUtils.isEmpty(checkExperimentScheduleCourseTableDetailModel)) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(checkExperimentScheduleCourseTableDetailModel, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("checkExperimentScheduleCourseTableDetailById:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ExperimentScheduleCourseLogVo>> getExperimentScheduleCourseLog(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "实验排课待排课表id", required = true)
            @Valid @RequestParam(value = "experimentScheduleId", required = true) String experimentScheduleId) {
        try {
            if (StringUtils.isAnyBlank(experimentScheduleId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<ExperimentScheduleCourseLogVo> experimentScheduleCourseLogVoList = experimentScheduleCourseLogService
                    .getExperimentScheduleCourseLogList(experimentScheduleId);
            return new ResponseEntity<>(experimentScheduleCourseLogVoList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getExperimentScheduleCourseLog:->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

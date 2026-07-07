package com.lztech.site.controllers.api.wisdomcourse;

import com.lztech.domain.model.wisdomcourse.enums.WisdomCourseTaskStatus;
import com.lztech.domain.model.wisdomcourse.enums.WisdomCourseTaskType;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.wisdomcourse.WisdomCourseService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.wisdomcourse.*;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

@Controller
public class WisdomCourseApiController implements WisdomCourseApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(WisdomCourseApiController.class);

    @Autowired
    private WisdomCourseService wisdomCourseService;
    public ResponseEntity<List<WisdomCourseResult>> getWisdomCourses(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程id") @Valid
            @RequestParam(value = "课程id列表，多个逗号分割", required = false) String courseIds) {


        return new ResponseEntity<List<WisdomCourseResult>>(HttpStatus.NOT_IMPLEMENTED);
    }
    public ResponseEntity<Void> addWisdomCourse(
            @ApiParam(value = "智慧课程信息", required = true) @Valid @RequestBody
            WisdomCourseParam wisdomCourseParam,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode) {
        if (Objects.isNull(wisdomCourseParam) || StringUtils.isAnyBlank(validCode, wisdomCourseParam.getCourseId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = wisdomCourseService.addWisdomCourse(wisdomCourseParam);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("addWisdomCourse->", e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Void> deleteWisdomCourse(
            @NotNull @ApiParam(value = "智慧课程id", required = true) @Valid @RequestParam(value =
                    "wisdomCourseId", required = true) String wisdomCourseId,
            @NotNull @ApiParam(value = "验证码 （wisdomCourseId={}&signKey=123123）",
                    required = true) @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "操作人id", required = true) @Valid
            @RequestParam(value = "operatorId", required = true) String operatorId,
            @NotNull @ApiParam(value = "操作人名称", required = true) @Valid
            @RequestParam(value = "operatorName", required = true) String operatorName,
            @NotNull @ApiParam(value = "操作人编号", required = true) @Valid
            @RequestParam(value = "operatorNo", required = true) String operatorNo) {
        if (Objects.isNull(wisdomCourseId) || StringUtils.isAnyBlank(validCode, wisdomCourseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = "wisdomCourseId=" + wisdomCourseId + signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = wisdomCourseService.deleteWisdomCourse(wisdomCourseId, operatorId, operatorName, operatorNo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("deleteWisdomCourse->", e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<WisdomCourseResult>> queryWisdomCourse(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程id,多个逗号分割") @Valid @RequestParam(value = "courseId", required = false)
            String courseId) {if (Objects.isNull(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<WisdomCourseResult> resultList = wisdomCourseService.queryWisdomCourse(courseId);
            return new ResponseEntity(resultList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("queryWisdomCourse->", e);
            return new ResponseEntity<List<WisdomCourseResult>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<WisdomCoursePageResult> queryWisdomCoursePage(
            @ApiParam(value = "分页查询智慧课程参数", required = true) @Valid @RequestBody
            WisdomCoursePageParam wisdomCoursePageParam,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (Objects.isNull(wisdomCoursePageParam) || StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            WisdomCoursePageResult wisdomCoursePageResult =
                    wisdomCourseService.queryWisdomCoursePage(wisdomCoursePageParam);
            return new ResponseEntity(wisdomCoursePageResult, HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("queryWisdomCoursePage->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<WisdomCourseTaskPageResult> queryWisdomCourseTaskPage(
            @ApiParam(value = "分页查询智慧课程任务参数", required = true) @Valid @RequestBody
            WisdomCourseTaskPageParam wisdomCourseTaskPageParam,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode) {
        if (Objects.isNull(wisdomCourseTaskPageParam)
                || StringUtils.isAnyBlank(validCode, wisdomCourseTaskPageParam.getWisdomCourseId())
                || Objects.isNull(wisdomCourseTaskPageParam.getPageNum())){
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            WisdomCourseTaskPageResult wisdomCourseTaskPageResult =
                    wisdomCourseService.queryWisdomCourseTaskPage(wisdomCourseTaskPageParam);
            return new ResponseEntity(wisdomCourseTaskPageResult, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("queryWisdomCourseTaskPage->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> retryWisdomCourseTask(
            @ApiParam(value = "智慧课程任务重试参数", required = true) @Valid @RequestBody
            WisdomCourseTaskRetryParam wisdomCourseTaskRetryParam,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (Objects.isNull(wisdomCourseTaskRetryParam) || StringUtils.isAnyBlank(validCode, wisdomCourseTaskRetryParam.getTaskIds())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = wisdomCourseService.retryWisdomCourseTask(wisdomCourseTaskRetryParam);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            LOGGER.error("retryWisdomCourseTask->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<WisdomCourseTaskResult>> queryWisdomCourseTaskBy(
            @ApiParam(value = "课程id", required = false)
            @Valid @RequestParam(value = "courseId", required = false) String courseId,
            @ApiParam(value = "任务类型（多个逗号分割）", required = false)
            @Valid @RequestParam(value = "taskType", required = false) String taskTypes,
            @ApiParam(value = "任务结果（多个逗号分割）", required = false)
            @Valid @RequestParam(value = "taskStatus", required = false) String taskStatus,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        List<WisdomCourseTaskType> taskTypesEnum = new ArrayList<>();
        List<WisdomCourseTaskStatus> taskStatusEnum = new ArrayList<>();
        try {
            if (!StringUtils.isEmpty(taskTypes)) {
                taskTypesEnum = Arrays.stream(taskTypes.trim().split(","))
                        .map(t -> WisdomCourseTaskType.getByCode(Integer.parseInt(t)))
                        .collect(Collectors.toList());
            }

            if(!StringUtils.isEmpty(taskStatus)){
                taskStatusEnum = Arrays.stream(taskStatus.trim().split(","))
                        .map(t -> WisdomCourseTaskStatus.getByCode(Integer.parseInt(t)))
                        .collect(Collectors.toList());
            }
            List<WisdomCourseTaskResult> result = wisdomCourseService.findWisdomCourseTaskResults(courseId, taskTypesEnum,taskStatusEnum);
            return new ResponseEntity<List<WisdomCourseTaskResult>>(result, HttpStatus.OK);
        }catch (Exception e ){
            LOGGER.error("queryWisdomCourseTaskBy->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<WisdomCourseStatisticsResult>> getWisdomCourseTaskStatistics(
            @NotNull @ApiParam(value = "课程id",required=true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "智慧课程任务编号，多个逗号分割",required=true)
            @Valid @RequestParam(value = "taskTypes", required = true) String taskTypes,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        if (StringUtils.isAnyBlank(courseId,taskTypes)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        try {
            List<Integer> tasks = Arrays.asList(taskTypes.split(",")).stream().map(t -> Integer.parseInt(t)).collect(Collectors.toList());
            List<WisdomCourseStatisticsResult> results = wisdomCourseService.getWisdomCourseStatisticsByCourse(courseId, tasks);
            return new ResponseEntity<List<WisdomCourseStatisticsResult>>(results,HttpStatus.OK);

        }catch (Exception e){
            LOGGER.error("根据课程Id获取AI知识库任务情况报错：->", e);

            return new ResponseEntity<List<WisdomCourseStatisticsResult>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

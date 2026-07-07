package com.lztech.site.controllers.api.courseknowledgegraphaigeneratetask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.knowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTask;
import com.lztech.domain.model.knowledgegraphaigeneratetask.enums.GraphAiGenerateStatus;
import com.lztech.site.controllers.api.courseknowledgegraph.CourseKnowledgeGraphApiController;
import com.lztech.site.service.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskCreateStatusModel;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskData;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskTaskStatusModel;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseknowledgegraphaigeneratetaskCreateParam;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-24T07:25:45.794Z")

@Controller
public class CourseKnowledgeGraphAiGenerateTaskController implements CourseKnowledgeGraphAiGenerateTaskApi {

    private static final Logger LOG = LoggerFactory.getLogger(CourseKnowledgeGraphApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CourseKnowledgeGraphAiGenerateTaskService courseKnowledgeGraphAiGenerateTaskService;

    @org.springframework.beans.factory.annotation.Autowired
    public CourseKnowledgeGraphAiGenerateTaskController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CourseKnowledgeGraphAiGenerateTaskCreateStatusModel> createCourseKnowledgeGraphAiGenerateTask(
            @ApiParam(value = "知识图谱智能生成任务生成参数", required = true)
            @Valid @RequestBody CourseknowledgegraphaigeneratetaskCreateParam courseknowledgegraphaigeneratetaskCreateParam,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {

            if (StringUtils.isAnyBlank(validCode, courseknowledgegraphaigeneratetaskCreateParam.getCourseId(),
                    courseknowledgegraphaigeneratetaskCreateParam.getSchoolYear(),
                    courseknowledgegraphaigeneratetaskCreateParam.getGroupId(),
                    courseknowledgegraphaigeneratetaskCreateParam.getAccessUserId(),
                    courseknowledgegraphaigeneratetaskCreateParam.getAccessUserName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (ObjectUtils.isEmpty(courseknowledgegraphaigeneratetaskCreateParam.getTerm())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode("courseId",
                    courseknowledgegraphaigeneratetaskCreateParam.getCourseId(), validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphAiGenerateTask courseKnowledgeGraphAiGenerateTask =
                    courseKnowledgeGraphAiGenerateTaskService.getCourseKnowledgeGraphAiGenerateTask(
                            courseknowledgegraphaigeneratetaskCreateParam.getCourseId());
            if (ObjectUtils.isNotEmpty(courseKnowledgeGraphAiGenerateTask) &&
                    (!courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus().equals(GraphAiGenerateStatus.FAIL))) {
                CourseKnowledgeGraphAiGenerateTaskCreateStatusModel courseKnowledgeGraphAiGenerateTaskCreateStatusModel =
                        new CourseKnowledgeGraphAiGenerateTaskCreateStatusModel();
                courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setStatus(0);
                String remark =
                        "课程id:【" + courseknowledgegraphaigeneratetaskCreateParam.getCourseId() + "】" + "课程知识图谱智能生成任务已存在";
                courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setRemark(remark);
                return new ResponseEntity<>(courseKnowledgeGraphAiGenerateTaskCreateStatusModel, HttpStatus.OK);
            }
            if (ObjectUtils.isNotEmpty(courseKnowledgeGraphAiGenerateTask)){
                courseKnowledgeGraphAiGenerateTaskService.deleteCourseKnowledgeGraphAiGenerateTask(courseKnowledgeGraphAiGenerateTask);
            }
            courseKnowledgeGraphAiGenerateTaskService
                    .createCourseKnowledgeGraphAiGenerateTask(courseknowledgegraphaigeneratetaskCreateParam);
            CourseKnowledgeGraphAiGenerateTaskCreateStatusModel courseKnowledgeGraphAiGenerateTaskCreateStatusModel =
                    new CourseKnowledgeGraphAiGenerateTaskCreateStatusModel();
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setStatus(1);
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setRemark("课程知识图谱智能生成任务创建成功");
            return new ResponseEntity<>(courseKnowledgeGraphAiGenerateTaskCreateStatusModel, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("createCourseKnowledgeGraphAiGenerateTask:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseKnowledgeGraphAiGenerateTaskTaskStatusModel> getCourseKnowledgeGraphAiGenerateTaskTaskStatus(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphAiGenerateTaskTaskStatusModel courseKnowledgeGraphAiGenerateTaskCreateStatusModel =
                    courseKnowledgeGraphAiGenerateTaskService.getCourseKnowledgeGraphAiGenerateTaskTaskStatusModel(courseId);

            return new ResponseEntity<>(courseKnowledgeGraphAiGenerateTaskCreateStatusModel, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphAiGenerateTaskTaskStatus:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseKnowledgeGraphAiGenerateTaskData> getCourseKnowledgeGraphAiGenerateTaskTaskData(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphAiGenerateTask courseKnowledgeGraphAiGenerateTask =
                    courseKnowledgeGraphAiGenerateTaskService.getCourseKnowledgeGraphAiGenerateTask(courseId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphAiGenerateTask)) {
                return new ResponseEntity(ErrorResult.customError("该课程还没课程知识图谱智能生成任务"), HttpStatus.CONFLICT);
            }
            if (courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus().equals(GraphAiGenerateStatus.WAITING) &&
                    courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus().equals(GraphAiGenerateStatus.GENERATING)) {
                return new ResponseEntity(ErrorResult.customError("该课程课程知识图谱智能生成任务还在进行中"), HttpStatus.CONFLICT);
            }
            if (courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus().equals(GraphAiGenerateStatus.FAIL)) {
                return new ResponseEntity(ErrorResult.customError("该课程课程知识图谱智能生成任务生成失败"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphAiGenerateTaskData courseKnowledgeGraphAiGenerateTaskData =
                    new CourseKnowledgeGraphAiGenerateTaskData();
            courseKnowledgeGraphAiGenerateTaskData.setId(courseKnowledgeGraphAiGenerateTask.getId());
            courseKnowledgeGraphAiGenerateTaskData.setGraphContent(courseKnowledgeGraphAiGenerateTask.getGraphContent());

            return new ResponseEntity<>(courseKnowledgeGraphAiGenerateTaskData, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphAiGenerateTaskTaskData:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> immediateUseCourseKnowledgeGraphAiGenerateTask(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphAiGenerateTask courseKnowledgeGraphAiGenerateTask =
                    courseKnowledgeGraphAiGenerateTaskService.getCourseKnowledgeGraphAiGenerateTask(courseId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphAiGenerateTask)) {
                return new ResponseEntity(ErrorResult.customError("该课程还没课程知识图谱智能生成任务"), HttpStatus.CONFLICT);
            }
            if (courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus().equals(GraphAiGenerateStatus.WAITING) &&
                    courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus().equals(GraphAiGenerateStatus.GENERATING)) {
                return new ResponseEntity(ErrorResult.customError("该课程课程知识图谱智能生成任务还在进行中"), HttpStatus.CONFLICT);
            }
            if (courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus().equals(GraphAiGenerateStatus.FAIL)) {
                return new ResponseEntity(ErrorResult.customError("该课程课程知识图谱智能生成任务生成失败"), HttpStatus.CONFLICT);
            }
            courseKnowledgeGraphAiGenerateTaskService
                    .immediateUseCourseKnowledgeGraphAiGenerateTask(courseKnowledgeGraphAiGenerateTask, accessUserId, accessUserName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("immediateUseCourseKnowledgeGraphAiGenerateTask:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> anomalousOperateCourseKnowledgeGraphAiGenerateTask(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "异常操作状态:(0:放弃|丢弃|取消;1:重新生成)", required = true)
            @Valid @RequestParam(value = "operateStatus", required = true) Integer anomalousOperateStatus,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId, accessUserId, accessUserName) || anomalousOperateStatus == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphAiGenerateTask courseKnowledgeGraphAiGenerateTask =
                    courseKnowledgeGraphAiGenerateTaskService.getCourseKnowledgeGraphAiGenerateTask(courseId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphAiGenerateTask)) {
                return new ResponseEntity(ErrorResult.customError("该课程还没课程知识图谱智能生成任务"), HttpStatus.CONFLICT);
            }
            if ((courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus().equals(GraphAiGenerateStatus.WAITING) ||
                    courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus().equals(GraphAiGenerateStatus.GENERATING)) &&
                    anomalousOperateStatus == 1
            ) {
                return new ResponseEntity(ErrorResult.customError("该课程课程知识图谱智能生成任务还在进行中，无法重新生成"), HttpStatus.CONFLICT);
            }

            courseKnowledgeGraphAiGenerateTaskService
                    .anomalousOperateCourseKnowledgeGraphAiGenerateTask(
                            courseKnowledgeGraphAiGenerateTask, anomalousOperateStatus, accessUserId, accessUserName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("anomalousOperateCourseKnowledgeGraphAiGenerateTask:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

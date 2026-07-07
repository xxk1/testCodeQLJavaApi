package com.lztech.site.controllers.api.courseknowledgegraphaigeneratetask;

import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskCreateStatusModel;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskData;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskTaskStatusModel;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseknowledgegraphaigeneratetaskCreateParam;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-24T07:25:45.794Z")

@Validated
@Api(value = "courseknowledgegraphaigeneratetask", description = "the courseknowledgegraphaigeneratetask API")
@RequestMapping(value = "/v1")
public interface CourseKnowledgeGraphAiGenerateTaskApi {

    @ApiOperation(value = "接口-根据课程id创建知识图谱智能生成任务",
            nickname = "createCourseKnowledgeGraphAiGenerateTask", notes = "调用方：1、teachingApi内部调用"
            , response = CourseKnowledgeGraphAiGenerateTaskCreateStatusModel.class, tags = {"CourseKnowledgeGraphAiGenerateTask",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = CourseKnowledgeGraphAiGenerateTaskCreateStatusModel.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/courseknowledgegraphaigeneratetask",
            method = RequestMethod.POST)
    ResponseEntity<CourseKnowledgeGraphAiGenerateTaskCreateStatusModel> createCourseKnowledgeGraphAiGenerateTask(
            @ApiParam(value = "知识图谱智能生成任务生成参数", required = true)
            @Valid @RequestBody CourseknowledgegraphaigeneratetaskCreateParam courseknowledgegraphaigeneratetaskCreateParam,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    );

    @ApiOperation(value = "接口-根据课程id获取知识图谱智能生成任务相关状态",
            nickname = "getCourseKnowledgeGraphAiGenerateTaskTaskStatus", notes = "调用方：1、老师端"
            , response = CourseKnowledgeGraphAiGenerateTaskTaskStatusModel.class, tags = {"CourseKnowledgeGraphAiGenerateTask",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = CourseKnowledgeGraphAiGenerateTaskTaskStatusModel.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/courseknowledgegraphaigeneratetask/{courseId}/taskstatus",
            method = RequestMethod.GET)
    ResponseEntity<CourseKnowledgeGraphAiGenerateTaskTaskStatusModel> getCourseKnowledgeGraphAiGenerateTaskTaskStatus(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    );

    @ApiOperation(value = "接口-根据课程id获取已成功知识图谱智能生成任务的数据",
            nickname = "getCourseKnowledgeGraphAiGenerateTaskTaskData", notes = "调用方：1、老师端"
            , response = CourseKnowledgeGraphAiGenerateTaskData.class, tags = {"CourseKnowledgeGraphAiGenerateTask",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = CourseKnowledgeGraphAiGenerateTaskTaskStatusModel.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/courseknowledgegraphaigeneratetask/{courseId}",
            method = RequestMethod.GET)
    ResponseEntity<CourseKnowledgeGraphAiGenerateTaskData> getCourseKnowledgeGraphAiGenerateTaskTaskData(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    );

    @ApiOperation(value = "接口-根据课程id立即使用智能生成的知识图谱",
            nickname = "immediateUseCourseKnowledgeGraphAiGenerateTask", notes = "调用方：1、老师端"
            ,  tags = {"CourseKnowledgeGraphAiGenerateTask",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/courseknowledgegraphaigeneratetask/{courseId}/immediateuse",
            method = RequestMethod.POST)
    ResponseEntity<Void> immediateUseCourseKnowledgeGraphAiGenerateTask(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    );

    @ApiOperation(value = "接口-对courseId的知识图谱智能生成任务进行异常操作(放弃或重新生成)",
            nickname = "anomalousOperateCourseKnowledgeGraphAiGenerateTask", notes = "调用方：1、老师端"
            ,  tags = {"CourseKnowledgeGraphAiGenerateTask",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/courseknowledgegraphaigeneratetask/{courseId}/anomalousoperate",
            method = RequestMethod.POST)
    ResponseEntity<Void> anomalousOperateCourseKnowledgeGraphAiGenerateTask(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "异常操作状态:(0:放弃|丢弃|取消;1:重新生成)", required = true)
            @Valid @RequestParam(value = "operateStatus", required = true) Integer anomalousOperateStatus,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    );
}

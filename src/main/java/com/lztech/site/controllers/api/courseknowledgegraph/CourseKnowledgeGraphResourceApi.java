package com.lztech.site.controllers.api.courseknowledgegraph;


import com.lztech.site.viewmodel.courseknowledgegraph.KnowledgePointCourseResourceVo;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceVo;
import com.lztech.site.viewmodel.coursemanagement.CourseStructureVo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-24T07:25:45.794Z")

@Validated
@Api(value = "courseknowledgegraph", description = "the courseknowledgegraph API")
@RequestMapping(value = "/v1")
public interface CourseKnowledgeGraphResourceApi {

    @ApiOperation(value = "接口-根据知识点关联课程资源", nickname = "addCourseKnowledgePointCourseResource", notes = "调用方：1、老师端", tags = {"CourseKnowledgeGraph",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到资源信息", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务校验数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/courseknowledgegraph/courseresource",
            method = RequestMethod.POST)
    ResponseEntity<Void> addCourseKnowledgePointCourseResource(@NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                               @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                               @ApiParam(value = "", required = true)
                                                               @Valid @RequestBody KnowledgePointCourseResourceVo resourcePointVo);

    @ApiOperation(value = "接口-根据知识点id获取课程资源列表", nickname = "getCourseKnowledgePointCourseResourceList", notes = "调用方：1、老师端", response =
            CourseResourceVo.class, responseContainer = "List", tags = {"CourseKnowledgeGraph",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = CourseResourceVo.class, responseContainer = "List"),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到资源信息", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务校验数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/courseknowledgegraph/courseresource",
            method = RequestMethod.GET)
    ResponseEntity<List<CourseResourceVo>> getCourseKnowledgePointCourseResourceList(@NotNull @ApiParam(value = "知识点id", required = true)
                                                                                     @Valid @RequestParam(value = "knowledgePointId", required = true)
                                                                                     String knowledgePointId,
                                                                                     @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)",
                                                                                             required = true)
                                                                                     @Valid @RequestParam(value = "validCode", required = true)
                                                                                     String validCode);

    @ApiOperation(value = "接口-取消备课资源关联知识点", nickname = "cancelCourseKnowledgePointCourseResource",
            notes = "调用方：1、老师端-知识图谱", tags={ "CourseKnowledgeGraph", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到资源信息", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常") })
    @RequestMapping(value = "/courseknowledgegraph/courseresource/cancellink",
            method = RequestMethod.POST)
    ResponseEntity<Void> cancelCourseKnowledgePointCourseResource(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "知识点id", required = true)
            @Valid @RequestParam(value = "knowledgePointId", required = true) String knowledgePointId,
            @NotNull @ApiParam(value = "资源id", required = true)
            @Valid @RequestParam(value = "resourceId", required = true) String resourceId);

    @ApiOperation(value = "获取允许被关联的所有课程资源",
            nickname = "getCourseKnowledgeGraphCanAssociationCourseResource",
            notes = "1.云课堂老师端-课程建设使用", response = CourseStructureVo.class,
            responseContainer = "List", tags={ "CourseKnowledgeGraphCourseResource", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = CourseStructureVo.class,
                    responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到资源信息", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常") })
    @RequestMapping(value = "/courseknowledgegraph/courseresource/canassociation",
            method = RequestMethod.GET)
    ResponseEntity<List<CourseStructureVo>> getCourseKnowledgeGraphCanAssociationCourseResource(
            @NotNull @ApiParam(value = "课程id", required = true) @Valid
            @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程id") @Valid @RequestParam(value = "versionId", required = false) String versionId,
            @ApiParam(value = "需要的用户ID，即用户ID创建的资源展示")
            @Valid @RequestParam(value = "needUserId", required = false) String needUserId,
            @ApiParam(value = "课件查询状态（null或0：没有限制，1：只查询课件）")
            @Valid @RequestParam(value = "coursewareStatus", required = false) Integer coursewareStatus
            );



}

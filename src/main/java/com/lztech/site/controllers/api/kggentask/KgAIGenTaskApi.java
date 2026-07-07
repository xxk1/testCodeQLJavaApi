package com.lztech.site.controllers.api.kggentask;

import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeVideoInfoTextModel;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.kggentask.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-22T10:17:27.444+08:00")

@Api(value = "knowledgeGraph", description = "the knowledgeGraph API")
@RequestMapping(value = "/api/v1")
public interface KgAIGenTaskApi {

    @ApiOperation(value = "根据教材AI生成知识图谱", nickname = "knowledgeGraphAIGenByTeachingMaterial", notes = "1.云课堂老师端-课程建设知识图谱使用", tags = {
            "AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务校验数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/courseTeachingMaterialFile/aiGen",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> knowledgeGraphAIGenByTeachingMaterial(
            @ApiParam(value = "教材信息", required = true) @Valid @RequestBody
            KgAIGenTeachingMaterialParam param,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode);


    @ApiOperation(value = "根据课件AI生成知识图谱", nickname = "knowledgeGraphAIGenByCourseware",
            notes = "1.云课堂老师端-课程建设知识图谱使用", tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务校验数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/knowledgeGraphAIGenByCourseware/aiGen",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> knowledgeGraphAIGenByCourseware(
            @ApiParam(value = "保存条件", required = true)
            @Valid @RequestBody KgAIGenCoursewareParam param,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    );


    @ApiOperation(value = "根据课堂实录AI生成知识图谱", nickname = "knowledgeGraphAIGenByCourseVideo",
            notes = "1.云课堂老师端-课程建设知识图谱使用", tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务校验数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/knowledgeGraphAIGenByCourseVideo/aiGen",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> knowledgeGraphAIGenByCourseVideo(
            @ApiParam(value = "保存条件", required = true) @Valid @RequestBody
            KgAIGenCourseVideoParam param,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode);


    @ApiOperation(value = "根据教材AI生成知识图谱状态查询", nickname = "knowledgeGraphAIGenByTeachingMaterialStatus",
            notes = "1.云课堂老师端-课程建设知识图谱使用", response = KgAIGenTeachingMaterialStatusResource.class, tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = KgAIGenTeachingMaterialStatusResource.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到进行中的任务", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/courseTeachingMaterialFile/aiGen/status",
            method = RequestMethod.GET)
    ResponseEntity<KgAIGenTeachingMaterialStatusResource> knowledgeGraphAIGenByTeachingMaterialStatus(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true)
            String validCode,
            @ApiParam(value = "课程id", required = false)
            @Valid @RequestParam(value = "courseId", required = false)
            String courseId,
            @ApiParam(value = "步骤类型 0:节点生成；1：节点详情生成；2：资源关联")
            @Valid @RequestParam(value = "stepIndex", required = false) Integer stepIndex,
            @ApiParam(value = "子任务id") @Valid @RequestParam(value = "subTaskId", required = false)
            String subTaskId, @ApiParam(value = "任务id")
            @Valid @RequestParam(value = "masterTaskId", required = false)
            String masterTaskId);

    @ApiOperation(value = "根据教材AI生成知识图谱节点查询",
            nickname = "getKnowledgeGraphAIGenByTeachingMaterialNode",
            notes = "1.云课堂老师端-课程建设知识图谱使用", response = KgAIGenTeachingMaterialNodeResource.class, tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = KgAIGenTeachingMaterialNodeResource.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到进行中的任务", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/courseTeachingMaterialFile/node",
            method = RequestMethod.GET)
    ResponseEntity<KgAIGenTeachingMaterialNodeResource> getKnowledgeGraphAIGenByTeachingMaterialNode(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true) @Valid
            @RequestParam(value = "courseId", required = true) String courseId,
            @ApiParam(value = "相似性分数")
            @Valid @RequestParam(value = "similarityScore", required = false) Double similarityScore
    );


    @ApiOperation(value = "根据教材AI生成知识图谱状态修改",
            nickname = "modifyKnowledgeGraphAIGenByTeaching",
            notes = "1.云课堂老师端-课程建设知识图谱使用", tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到进行中的任务", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/courseTeachingMaterialFile/aiGen/status/modify",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<KgAIGenTeachingMaterialAIGenTaskBaseResource> modifyKnowledgeGraphAIGenByTeaching(
            @ApiParam(value = "修改参数", required = true)
            @Valid @RequestBody KgAIGenTeachingMaterialStatusModifyParam param,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode);

    @ApiOperation(value = "根据教材AI生成知识图谱子任务创建", nickname = "knowledgeGraphAIGenByTeachingMaterialNodeDetailTask", notes = "1.云课堂老师端-课程建设知识图谱使用",
            response = KgAIGenTeachingMaterialAIGenTaskBaseResource.class, tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = KgAIGenTeachingMaterialAIGenTaskBaseResource.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "任务已存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/courseMaterialFile/aiGen/subTaskBuild",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<KgAIGenTeachingMaterialAIGenTaskBaseResource> knowledgeGraphAIGenByTeachingMaterialNodeDetailTask(
            @ApiParam(value = "节点任务详情参数", required = true) @Valid @RequestBody
            KgAIGenTeachingMaterialNodeDetailTaskParam param,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode);

    @ApiOperation(value = "根据教材AI生成知识图谱节点详情查询",
            nickname = "getKnowledgeGraphAIGenByTeachingMaterialNodeDetail",
            notes = "1.云课堂老师端-课程建设知识图谱使用", response = KgAIGenTeachingMaterialNodeResource.class, tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = KgAIGenTeachingMaterialNodeResource.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "任务不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/courseMaterialFile/aiGen/nodeDetail",
            method = RequestMethod.GET)
    ResponseEntity<KgAIGenTeachingMaterialNodeResource> getKnowledgeGraphAIGenByTeachingMaterialNodeDetail(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "任务id", required = true) @Valid
            @RequestParam(value = "masterTaskId", required = true) String masterTaskId,
            @ApiParam(value = "重要标签： 0：次要；1：一般；2：重要;3：极其重要") @Valid
            @RequestParam(value = "importantTag", required = false) Integer importantTag,
            @ApiParam(value = "层级 1：一级节点；2：二级节点;3：三级节点;4：四级节点;5：五级节点;6：六级节点;7：七级节点;8：八级节点....")
            @Valid @RequestParam(value = "level", required = false) Integer level,
            @ApiParam(value = "内容") @Valid @RequestParam(value = "content", required = false) String content,
            @ApiParam(value = "页码") @Valid @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(value = "每页数量")
            @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize);

    @ApiOperation(value = "根据教材AI生成知识图谱资源相关统计", nickname = "getKnowledgeGraphAIGenByTeachingMaterialResourceRelatedStatistics", notes = "1" +
            ".云课堂老师端-课程建设知识图谱使用", response = KgAIGenRelatedStatisticsResource.class, tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = KgAIGenRelatedStatisticsResource.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "任务不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/courseMaterialFile/aiGen/resourceRelatedStatistics",
            method = RequestMethod.GET)
    ResponseEntity<KgAIGenRelatedStatisticsResource> getKnowledgeGraphAIGenByTeachingMaterialResourceRelatedStatistics(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "任务id", required = true)
            @Valid @RequestParam(value = "masterTaskId", required = true) String masterTaskId,
            @NotNull @ApiParam(value = "子任务id", required = true)
            @Valid @RequestParam(value = "subTaskId", required = true) String subTaskId);


    @ApiOperation(value = "根据教材AI生成知识图谱节点详情查询", nickname = "getKnowledgeGraphAIGenByTeachingMaterialNodeDetail", notes = "1.云课堂老师端-课程建设知识图谱使用",
            response = KgAIGenTeachingMaterialNodeResourceItem.class, tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = KgAIGenTeachingMaterialNodeResourceItem.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "任务不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/aiGen/node/nodeDetail",
            method = RequestMethod.GET)
    ResponseEntity<KgAIGenTeachingMaterialNodeResourceItem> getKnowledgeGraphAIGenNodeDetail(@NotNull @ApiParam(value = "验证码 （&signKey=123123）",
            required = true) @Valid @RequestParam(value = "validCode", required = true) String validCode, @NotNull @ApiParam(value = "节点id",
            required = true) @Valid @RequestParam(value = "nodeId", required = true) String nodeId);


    @ApiOperation(value = "根据教材AI生成知识图谱节点关联题目查询", nickname = "getKnowledgeGraphAIGenNodeQuestion", notes = "1.云课堂老师端-课程建设知识图谱使用",
            response = KgAIGenNodeQuestionPageResource.class, tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = KgAIGenNodeQuestionPageResource.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "任务不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/aiGen/node/question",
            method = RequestMethod.POST)
    ResponseEntity<KgAIGenNodeQuestionPageResource> getKnowledgeGraphAIGenNodeQuestion(@NotNull @ApiParam(value = "验证码 （&signKey" +
            "=123123）", required = true) @Valid @RequestParam(value = "validCode", required = true) String validCode, @ApiParam(value = "参数",
            required = true) @Valid @RequestBody KgAIGenNodeQuestionQueryParam param);


    @ApiOperation(value = "根据教材AI生成知识图谱节点关联视频查询", nickname = "getKnowledgeGraphAIGenNodeVideo", notes = "1.云课堂老师端-课程建设知识图谱使用",
            response = KgAIGenNodeVideoPageResource.class, tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = KgAIGenNodeVideoPageResource.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "任务不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/aiGen/node/video",
            method = RequestMethod.POST)
    ResponseEntity<KgAIGenNodeVideoPageResource> getKnowledgeGraphAIGenNodeVideo(@NotNull @ApiParam(value = "验证码 （&signKey=123123" +
            "）", required = true) @Valid @RequestParam(value = "validCode", required = true) String validCode, @ApiParam(value = "参数", required =
            true) @Valid @RequestBody KgAIGenNodeVideoQueryParam param);

    @ApiOperation(value = "接口-根据视频id和知识点id获取智能资源列表", nickname = "getFilterAIGenNodeVideoInfoTextList", notes = "1.云课堂老师端-课程建设知识图谱使用", response =
            CourseKnowledgeGraphNodeVideoInfoTextModel.class, responseContainer = "List", tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = CourseKnowledgeGraphNodeVideoInfoTextModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/aiGen/{nodeId}/videoinfotexts",
            method = RequestMethod.GET)
    ResponseEntity<List<CourseKnowledgeGraphNodeVideoInfoTextModel>> getFilterAIGenNodeVideoInfoTextList(
            @ApiParam(value = "知识点id", required = true) @PathVariable("nodeId") String nodeId,
            @NotNull @ApiParam(value = "视频信息id", required = true) @Valid
            @RequestParam(value = "videoInfoId", required = true) String videoInfoId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "相似性分数")
            @Valid @RequestParam(value = "similarityScore", required = false) Double similarityScore
    );

    @ApiOperation(value = "根据教材AI生成知识图谱导入", nickname = "importKnowledgeGraphAIGen",
            notes = "1.云课堂老师端-课程建设知识图谱使用", tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "任务不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/aiGen/import",
            method = RequestMethod.POST)
    ResponseEntity<Void> importKnowledgeGraphAIGen(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "参数", required = true) @Valid @RequestBody KgAIGenImportParam param);

    @ApiOperation(value = "根据教材AI生成知识图谱节点添加", nickname = "addKnowledgeGraphAIGenNode", notes = "1.云课堂老师端-课程建设知识图谱使用", tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "任务不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/aiGen/node/add",
            method = RequestMethod.POST)
    ResponseEntity<Void> addKnowledgeGraphAIGenNode(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "参数", required = true) @Valid @RequestBody KgAIGenNodeAddParam param);


    @ApiOperation(value = "根据教材AI生成知识图谱节点修改", nickname = "modifyKnowledgeGraphAIGenNode", notes = "1.云课堂老师端-课程建设知识图谱使用", tags = {
            "AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "任务不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/aiGen/node/modifify",
            method = RequestMethod.POST)
    ResponseEntity<Void> modifyKnowledgeGraphAIGenNode(@NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
                                                       @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                       @ApiParam(value = "参数", required = true) @Valid @RequestBody KgAIGenNodeModifyParam param);

    @ApiOperation(value = "根据教材AI生成知识图谱节点排序", nickname = "sortedKnowledgeGraphAIGenNode", notes = "1.云课堂老师端-课程建设知识图谱使用", tags = {
            "AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "任务不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/aiGen/node/sorted",
            method = RequestMethod.POST)
    ResponseEntity<Void> sortedKnowledgeGraphAIGenNode(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "参数", required = true) @Valid @RequestBody KgAIGenNodeSortedParam param);

    @ApiOperation(value = "根据id删除一站式AI生成知识图谱的节点",
            nickname = "deleteKgGenNodeById",
            notes = "1.云课堂老师端-课程建设知识图谱使用", tags = {"AIKnowledgeGraphGen",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到进行中的任务", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/knowledgeGraph/aiGen/node/{kgGenNodeId}/delete", method = RequestMethod.POST)
    ResponseEntity<Void> deleteKgGenNodeById(
            @ApiParam(value = "知识点id", required = true) @PathVariable("kgGenNodeId") String kgGenNodeId,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    );

}

package com.lztech.site.controllers.api.kggentask;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.exception.NodeLevelExceededException;
import com.lztech.site.service.kggentask.KgAIGenTaskResultImportService;
import com.lztech.site.service.kggentask.KgAIGenTaskService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeVideoInfoTextModel;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.kggentask.*;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import static com.lztech.site.config.Access.signKey;

@Controller
public class KgAIGenTaskApiController implements KgAIGenTaskApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(KgAIGenTaskApiController.class);
    @Autowired
    private KgAIGenTaskService kgAIGenTaskService;
    @Autowired
    private KgAIGenTaskResultImportService kgAIGenTaskResultImportService;

    public ResponseEntity<Void> knowledgeGraphAIGenByTeachingMaterial(
            @ApiParam(value = "教材信息", required = true) @Valid @RequestBody
            KgAIGenTeachingMaterialParam param,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true)
            String validCode) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode,
                param.getTeachingMaterialId(), param.getCourseId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = kgAIGenTaskService.createCourseVideoKgAIGenTask(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("knowledgeGraphAIGenByTeachingMaterial", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Void> knowledgeGraphAIGenByCourseware(
            @ApiParam(value = "保存条件", required = true)
            @Valid @RequestBody KgAIGenCoursewareParam param,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode, param.getCourseId())
                || CollectionUtils.isEmpty(param.getCoursewareFileList())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = kgAIGenTaskService.createCoursewareKgAIGenTask(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("knowledgeGraphAIGenByCourseware", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> knowledgeGraphAIGenByCourseVideo(
            @ApiParam(value = "保存条件", required = true) @Valid @RequestBody
            KgAIGenCourseVideoParam param,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode){
        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode,
                param.getGroupId(),param.getSchoolYear(), param.getCourseId()) ||param.getTerm()==null)  {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = kgAIGenTaskService.createCourseVideoKgAIGenTask(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("knowledgeGraphAIGenByCourseVideo", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<KgAIGenTeachingMaterialStatusResource> knowledgeGraphAIGenByTeachingMaterialStatus(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true)
            String validCode,
            @ApiParam(value = "课程id", required = false)
            @Valid @RequestParam(value = "courseId", required = false)
            String courseId,
            @ApiParam(value = "步骤类型 0:节点生成；1：节点详情生成；2：资源关联")
            @Valid @RequestParam(value = "stepIndex", required = false) Integer stepIndex,
            @ApiParam(value = "子任务id") @Valid @RequestParam(value = "subTaskId", required = false)
            String subTaskId,
            @ApiParam(value = "任务id") @Valid @RequestParam(value = "masterTaskId", required = false)
            String masterTaskId) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            KgAIGenTeachingMaterialStatusResource kgAIGenTeachingMaterialStatusResource = kgAIGenTaskService.getKgAIGenTeachingMaterialStatus(
                    courseId, stepIndex, subTaskId, masterTaskId);
            if (Objects.isNull(kgAIGenTeachingMaterialStatusResource)) {
                return new ResponseEntity(ErrorResult.customError("未找到进行中的任务"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(kgAIGenTeachingMaterialStatusResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("knowledgeGraphAIGenByTeachingMaterialStatus->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<KgAIGenTeachingMaterialNodeResource> getKnowledgeGraphAIGenByTeachingMaterialNode(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @ApiParam(value = "相似性分数")
            @Valid @RequestParam(value = "similarityScore", required = false) Double similarityScore
    ) {

        if (StringUtils.isAnyBlank(validCode, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            KgAIGenTeachingMaterialNodeResource kgAIGenTeachingMaterialNodeResource =
                    kgAIGenTaskService.getKgAIGenTeachingMaterialNode(courseId,similarityScore);
            if (Objects.isNull(kgAIGenTeachingMaterialNodeResource)) {
                return new ResponseEntity(ErrorResult.customError("未找到课程节点信息"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(kgAIGenTeachingMaterialNodeResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getKnowledgeGraphAIGenByTeachingMaterialNode->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<KgAIGenTeachingMaterialAIGenTaskBaseResource> modifyKnowledgeGraphAIGenByTeaching(
            @ApiParam(value = "修改参数", required = true) @Valid @RequestBody
            KgAIGenTeachingMaterialStatusModifyParam param,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true)
            String validCode) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode, param.getCourseId(), param.getMasterTaskId(), param.getSubTaskId(),
                param.getOperatorId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            Result result = kgAIGenTaskService.modifyKnowledgeGraphAIGenByTeaching(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(result.getData(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("modifyKnowledgeGraphAIGenByTeaching->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<KgAIGenTeachingMaterialAIGenTaskBaseResource> knowledgeGraphAIGenByTeachingMaterialNodeDetailTask(
            @ApiParam(value = "节点任务详情参数", required = true) @Valid @RequestBody
            KgAIGenTeachingMaterialNodeDetailTaskParam param,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode, param.getCourseId(),
                param.getMasterTaskId(), param.getOperatorId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = kgAIGenTaskService.knowledgeGraphAIGenByTeachingMaterialNodeDetailTask(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(result.getData(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("knowledgeGraphAIGenByTeachingMaterialNodeDetailTask->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<KgAIGenTeachingMaterialNodeResource> getKnowledgeGraphAIGenByTeachingMaterialNodeDetail(
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
            @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (StringUtils.isAnyBlank(validCode, masterTaskId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            KgAIGenTeachingMaterialNodeResource kgAIGenTeachingMaterialNodeResource =
                    kgAIGenTaskService.getKgAIGenTeachingMaterialNodeDetail(masterTaskId, importantTag, level, content, page, pageSize);
            if (Objects.isNull(kgAIGenTeachingMaterialNodeResource)) {
                return new ResponseEntity(ErrorResult.customError("未找到课程节点信息"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(kgAIGenTeachingMaterialNodeResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getKnowledgeGraphAIGenByTeachingMaterialNodeDetail->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<KgAIGenRelatedStatisticsResource> getKnowledgeGraphAIGenByTeachingMaterialResourceRelatedStatistics(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "任务id", required = true) @Valid @RequestParam(value = "masterTaskId", required = true) String masterTaskId,
            @NotNull @ApiParam(value = "子任务id", required = true) @Valid @RequestParam(value = "subTaskId", required = true) String subTaskId) {
        if (StringUtils.isAnyBlank(validCode, masterTaskId, subTaskId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            KgAIGenRelatedStatisticsResource resource =
                    kgAIGenTaskService.getKnowledgeGraphAIGenResourceRelatedStatistics(masterTaskId, subTaskId);
            if (Objects.isNull(resource)) {
                return new ResponseEntity(ErrorResult.customError("任务不存在"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(resource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getKnowledgeGraphAIGenByTeachingMaterialResourceRelatedStatistics->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<KgAIGenTeachingMaterialNodeResourceItem> getKnowledgeGraphAIGenNodeDetail(
            @NotNull @ApiParam(value = "验证码 （&signKey" +
                    "=123123）", required = true) @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value =
                    "节点id", required = true) @Valid @RequestParam(value = "nodeId", required = true) String nodeId) {
        if (StringUtils.isAnyBlank(validCode, nodeId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            KgAIGenTeachingMaterialNodeResourceItem kgAIGenTeachingMaterialNodeResourceItem = kgAIGenTaskService.
                    getKgAIGenNodeDetail(nodeId);
            if (Objects.isNull(kgAIGenTeachingMaterialNodeResourceItem)) {
                return new ResponseEntity(ErrorResult.customError("节点不存在"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(kgAIGenTeachingMaterialNodeResourceItem, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getKnowledgeGraphAIGenNodeDetail->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<KgAIGenNodeQuestionPageResource> getKnowledgeGraphAIGenNodeQuestion(@NotNull @ApiParam(value = "验证码 " +
            "（&signKey=123123）", required = true) @Valid @RequestParam(value = "validCode", required = true) String validCode, @ApiParam(value =
            "参数", required = true) @Valid @RequestBody KgAIGenNodeQuestionQueryParam param) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode, param.getKgNodeId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            KgAIGenNodeQuestionPageResource kgAIGenNodeQuestionPageResource = kgAIGenTaskService.getKgAIGenNodeQuestion(param);
            return new ResponseEntity(kgAIGenNodeQuestionPageResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getKnowledgeGraphAIGenNodeQuestion->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<KgAIGenNodeVideoPageResource> getKnowledgeGraphAIGenNodeVideo(
            @NotNull @ApiParam(value = "验证码 （&signKey" +
                    "=123123）", required = true) @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "参数",
                    required = true) @Valid @RequestBody KgAIGenNodeVideoQueryParam param) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode, param.getKgNodeId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            KgAIGenNodeVideoPageResource kgAIGenNodeVideoPageResource = kgAIGenTaskService.getKnowledgeGraphAIGenNodeVideo(param);
            return new ResponseEntity<>(kgAIGenNodeVideoPageResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getKnowledgeGraphAIGenNodeVideo:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseKnowledgeGraphNodeVideoInfoTextModel>> getFilterAIGenNodeVideoInfoTextList(
            @ApiParam(value = "知识点id", required = true) @PathVariable("nodeId") String nodeId,
            @NotNull @ApiParam(value = "视频信息id", required = true)
            @Valid @RequestParam(value = "videoInfoId", required = true) String videoInfoId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "相似性分数")
            @Valid @RequestParam(value = "similarityScore", required = false) Double similarityScore
    ) {
        if (StringUtils.isAnyBlank(nodeId, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseKnowledgeGraphNodeVideoInfoTextModel> resultList =
                    kgAIGenTaskService.getFilterAIGenNodeVideoInfoTextList(nodeId, videoInfoId,similarityScore);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getFilterAIGenNodeVideoInfoTextList:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Void> importKnowledgeGraphAIGen(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "参数", required = true) @Valid @RequestBody KgAIGenImportParam param) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode, param.getMasterTaskId(), param.getSubTaskId(), param.getCourseId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            kgAIGenTaskResultImportService.importKnowledgeGraphAIGen(param);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("importKnowledgeGraphAIGen:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> addKnowledgeGraphAIGenNode(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "参数", required = true) @Valid @RequestBody KgAIGenNodeAddParam param) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode, param.getNodeName(), param.getParentNodeId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = kgAIGenTaskService.addKnowledgeGraphAIGenNode(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("addKnowledgeGraphAIGenNode:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> modifyKnowledgeGraphAIGenNode(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "参数", required = true) @Valid @RequestBody KgAIGenNodeModifyParam param) {
        if (Objects.isNull(param) || Objects.isNull(param.getModifyType()) || StringUtils.isAnyBlank(validCode, param.getNodeId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = kgAIGenTaskService.modifyKnowledgeGraphAIGenNode(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("modifyKnowledgeGraphAIGenNode:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Void> sortedKnowledgeGraphAIGenNode(
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "参数", required = true) @Valid @RequestBody KgAIGenNodeSortedParam param) {
        if (Objects.isNull(param) || StringUtils.isAnyBlank(validCode, param.getCourseId()) || CollectionUtils.isEmpty(param.getNodeList())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = kgAIGenTaskService.sortedKnowledgeGraphAIGenNode(param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NodeLevelExceededException e) {
            return new ResponseEntity(ErrorResult.customError("该知识图谱最多允许创建十级知识点"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            LOGGER.error("sortedKnowledgeGraphAIGenNode:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteKgGenNodeById(
            @ApiParam(value = "节点id",required=true) @PathVariable("kgGenNodeId") String kgGenNodeId,
            @NotNull @ApiParam(value = "验证码 （&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        if (StringUtils.isAnyBlank(validCode, kgGenNodeId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            kgAIGenTaskService.deleteKgGenNodeById(kgGenNodeId);
            return new ResponseEntity<>(HttpStatus.OK);
        }  catch (Exception e) {
            LOGGER.error("deleteKgGenNodeById:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

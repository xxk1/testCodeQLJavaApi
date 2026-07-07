package com.lztech.site.controllers.api.courseknowledgegraph;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.knowledgegraph.*;
import com.lztech.domain.model.knowledgegraph.enums.KnowledgeNodeLevel;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseknowledgegraph.*;
import com.lztech.site.until.IpUtil;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseknowledgegraph.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.questionknowledgepointvo.QuestionKnowledgePointVo;
import com.lztech.site.viewmodel.questionlibrary.QuestionLibraryResource;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import static com.lztech.site.constants.Constant.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-24T07:25:45.794Z")
@Controller
public class CourseKnowledgeGraphApiController implements CourseKnowledgeGraphApi {

    private static final Logger LOG = LoggerFactory.getLogger(CourseKnowledgeGraphApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseKnowledgeGraphExportService courseKnowledgeGraphExportService;
    @Autowired
    private Neo4jUtil neo4jUtil;
    @Autowired
    private CourseKnowledgeGraphExtendService courseKnowledgeGraphExtendService;
    @Autowired
    private CourseKnowledgeGraphLogicService courseKnowledgeGraphLogicService;
    @Autowired
    private QuestionCourseKnowledgeGraphService questionCourseKnowledgeGraphService;

    @org.springframework.beans.factory.annotation.Autowired
    public CourseKnowledgeGraphApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CourseKnowledgeGraphNodeTreeModel> getCourseKnowledgeGraphTree(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            Course course = courseService.findById(courseId);
            if (ObjectUtils.isEmpty(course)) {
                return new ResponseEntity(ErrorResult.customError(courseId + "课程不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphTree(course, accessUserId, accessUserName);
            return new ResponseEntity<>(courseKnowledgeGraphNodeTreeModel, HttpStatus.OK);

        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphTree:", e );
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> createCourseKnowledgeGraphNode(
            @ApiParam(value = "新增知识图谱节点参数", required = true)
            @Valid @RequestBody CourseKnowledgeGraphNodeData courseKnowledgeGraphNodeData,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(validCode, courseKnowledgeGraphNodeData.getCourseId(),
                    courseKnowledgeGraphNodeData.getAccessUserId(), courseKnowledgeGraphNodeData.getAccessUserName(),
                    courseKnowledgeGraphNodeData.getParentCourseKnowledgeGraphNodeId(),
                    courseKnowledgeGraphNodeData.getCourseKnowledgeGraphId(),
                    courseKnowledgeGraphNodeData.getKnowledgeNodeName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (courseKnowledgeGraphNodeData.getKnowledgeNodeName().trim().length() > FIVE_HUNDRED) {
                return new ResponseEntity(ErrorResult.customError("知识点名称不能超过500个字"), HttpStatus.CONFLICT);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphNodeData.getCourseKnowledgeGraphId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphNodeData.getCourseKnowledgeGraphId()
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseKnowledgeGraphNodeData.getCourseId());
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphNodeData.getCourseKnowledgeGraphId(), false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode parentCourseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeData.getParentCourseKnowledgeGraphNodeId());
            if (ObjectUtils.isEmpty(parentCourseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(courseKnowledgeGraphNodeData.getParentCourseKnowledgeGraphNodeId()
                        + "父知识点不存在"), HttpStatus.CONFLICT);
            }
            if (parentCourseKnowledgeGraphNode.getKnowledgeNodeLevel() >= KNOWLEDGE_NODE_MAX_LEVEL) {
                return new ResponseEntity(ErrorResult.customError("该知识图谱最多允许创建十级知识点"), HttpStatus.CONFLICT);
            }
            /**判断同父级下同层级知识点名称是否存在相同**/
//            boolean checkKnowledgeNodeName = courseKnowledgeGraphService.checkChildCourseKnowledgeGraphNode(courseKnowledgeGraphDomain,
//                    parentCourseKnowledgeGraphNode.getId(), courseKnowledgeGraphNodeData.getKnowledgeNodeName().trim());
//            if (checkKnowledgeNodeName) {
//                return new ResponseEntity(ErrorResult.customError("同父级下同层级知识点名称不能重复"), HttpStatus.CONFLICT);
//            }
            courseKnowledgeGraphService.createCourseKnowledgeGraphNode(courseKnowledgeGraphDomain,
                    parentCourseKnowledgeGraphNode, courseKnowledgeGraphNodeData, ip);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            LOG.error("createCourseKnowledgeGraphNode:{}",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteCourseKnowledgeGraphNode(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @ApiParam(value = "课程知识图谱知识点id", required = true)
            @PathVariable("courseKnowledgeGraphNodeId") String courseKnowledgeGraphNodeId,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeId,
                    accessUserId, accessUserName, courseKnowledgeGraphId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNodeId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(courseKnowledgeGraphNodeId + "知识点不存在"), HttpStatus.CONFLICT);
            }
            courseKnowledgeGraphService.deleteCourseKnowledgeGraphNode(courseKnowledgeGraphDomain,
                    courseKnowledgeGraphNode, accessUserId, accessUserName, courseId, ip);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("deleteCourseKnowledgeGraphNode:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseKnowledgeGraphNodeModel> getCourseKnowledgeGraphNodeInfo(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @NotNull @ApiParam(value = "课程知识图谱知识点id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphNodeId", required = true) String courseKnowledgeGraphNodeId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {

            if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeId, courseKnowledgeGraphId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNodeId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(courseKnowledgeGraphNodeId + "知识点不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNodeModel courseKnowledgeGraphNodeModel =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNodeInfo(courseKnowledgeGraphNode);
            return new ResponseEntity<>(courseKnowledgeGraphNodeModel, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphNodeInfo:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseKnowledgeGraphNodeModel> updateCourseKnowledgeGraphNode(
            @ApiParam(value = "新增知识图谱节点参数", required = true)
            @Valid @RequestBody CourseKnowledgeGraphNodeParam courseKnowledgeGraphNodeParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(validCode, courseKnowledgeGraphNodeParam.getCourseId(),
                    courseKnowledgeGraphNodeParam.getCourseKnowledgeGraphId(),
                    courseKnowledgeGraphNodeParam.getCourseKnowledgeGraphNodeId(),
                    courseKnowledgeGraphNodeParam.getAccessUserId(), courseKnowledgeGraphNodeParam.getAccessUserName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isEmpty(courseKnowledgeGraphNodeParam.getKnowledgeNodeName().trim()) &&
                    StringUtils.isEmpty(courseKnowledgeGraphNodeParam.getContentDetail()) &&
                    courseKnowledgeGraphNodeParam.getLevelTypeIndex() == null
            ) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphNodeParam.getCourseKnowledgeGraphId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphNodeParam.getCourseKnowledgeGraphId()
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseKnowledgeGraphNodeParam.getCourseId());
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphNodeParam.getCourseKnowledgeGraphId(), false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeParam.getCourseKnowledgeGraphNodeId());

            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeParam.getCourseKnowledgeGraphNodeId() + "知识点不存在"), HttpStatus.CONFLICT);
            }
            /**根据子节点id获取父节点**/
            CourseKnowledgeGraphNode parentCourseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getParentCourseKnowledgeGraphNode(
                            courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            /**判断同父级下同层级知识点名称是否存在相同**/
//            boolean checkKnowledgeNodeName = courseKnowledgeGraphService.checkChildCourseKnowledgeGraphNode(courseKnowledgeGraphDomain,
//                    parentCourseKnowledgeGraphNode.getId(), courseKnowledgeGraphNode.getId(),
//                    courseKnowledgeGraphNodeParam.getKnowledgeNodeName().trim());
//            if (checkKnowledgeNodeName) {
//                return new ResponseEntity(ErrorResult.customError("同父级下同层级知识点名称不能重复"), HttpStatus.CONFLICT);
//            }
            ErrorResult result = courseKnowledgeGraphService.updateCourseKnowledgeGraphNode(courseKnowledgeGraphDomain,
                    courseKnowledgeGraphNodeParam, courseKnowledgeGraphNode, ip);
            if (ObjectUtils.isNotEmpty(result)) {
                return new ResponseEntity(result, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("updateCourseKnowledgeGraphNode:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseKnowledgeGraphNodeRelationshipResource> getCourseKnowledgeGraphNodeRelationshipList(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @NotNull @ApiParam(value = "课程知识图谱知识点id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphNodeId", required = true) String courseKnowledgeGraphNodeId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeId, courseKnowledgeGraphId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeId + "知识点不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNodeRelationshipResource courseKnowledgeGraphNodeRelationshipResource =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNodeRelationshipList(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeId);
            return new ResponseEntity<>(courseKnowledgeGraphNodeRelationshipResource, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphNodeRelationshipList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteCourseKnowledgeGraphNodeRelationship(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @ApiParam(value = "课程知识点id", required = true)
            @PathVariable("courseKnowledgeGraphNodeId") String courseKnowledgeGraphNodeId,
            @ApiParam(value = "关系id", required = true) @PathVariable("relationshipId") String relationshipId,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：relationshipId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeId, courseKnowledgeGraphId,
                    relationshipId, accessUserId, accessUserName)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeId + "知识点不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship =
                    neo4jUtil.getCourseKnowledgeGraphNodeRelationship(courseKnowledgeGraphDomain.getId(), relationshipId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeRelationship)) {
                return new ResponseEntity(ErrorResult.customError(
                        relationshipId + "该知识节点关系不存在"), HttpStatus.CONFLICT);
            }
            if (!ValidUtils.checkValidCode("relationshipId", relationshipId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            courseKnowledgeGraphService.deleteCourseKnowledgeGraphNodeRelationship(
                    courseKnowledgeGraphDomain, courseKnowledgeGraphNode, courseKnowledgeGraphNodeRelationship,
                    accessUserId, accessUserName, ip);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("deleteCourseKnowledgeGraphNodeRelationship:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> createCourseKnowledgeGraphNodeRelationship(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @ApiParam(value = "新增知识图谱节点关系参数", required = true)
            @Valid @RequestBody CourseKnowledgeGraphNodeRelationshipParam courseKnowledgeGraphNodeRelationshipParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeRelationshipParam.getKnowledgeGraphNodeId(),
                    courseKnowledgeGraphNodeRelationshipParam.getCourseKnowledgeGraphId(),
                    courseKnowledgeGraphNodeRelationshipParam.getAccessUserId(),
                    courseKnowledgeGraphNodeRelationshipParam.getAccessUserName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!courseKnowledgeGraphService.checkParameter(courseKnowledgeGraphNodeRelationshipParam)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain = courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(
                    courseKnowledgeGraphNodeRelationshipParam.getCourseKnowledgeGraphId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" +
                        courseKnowledgeGraphNodeRelationshipParam.getCourseKnowledgeGraphId() + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(
                        courseKnowledgeGraphNodeRelationshipParam.getCourseKnowledgeGraphId(), false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeRelationshipParam.getKnowledgeGraphNodeId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeRelationshipParam.getKnowledgeGraphNodeId() + "知识点不存在"), HttpStatus.CONFLICT);
            }
            if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId()) ||
                    StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getPostKnowledgeGraphNodeId())
            ) {
                String secondKnowledgeGraphNodeId = StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId())
                        ? courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId() :
                        courseKnowledgeGraphNodeRelationshipParam.getPostKnowledgeGraphNodeId();
                CourseKnowledgeGraphNode secondKnowledgeGraphNode =
                        courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(), secondKnowledgeGraphNodeId);
                if (ObjectUtils.isEmpty(secondKnowledgeGraphNode)) {
                    return new ResponseEntity(ErrorResult.customError(
                            courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId() + "知识点不存在"), HttpStatus.CONFLICT);
                }
                boolean tag = neo4jUtil.checkFrontRearRelationship(
                        courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId(), secondKnowledgeGraphNodeId);
                if (tag) {
                    return new ResponseEntity(ErrorResult.customError("当前两个节点前后置关系已存在"), HttpStatus.CONFLICT);
                }
            }
            if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getRelevanceKnowledgeGraphNodeId())) {
                String secondKnowledgeGraphNodeId = courseKnowledgeGraphNodeRelationshipParam.getRelevanceKnowledgeGraphNodeId();
                CourseKnowledgeGraphNode secondKnowledgeGraphNode =
                        courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(), secondKnowledgeGraphNodeId);
                if (ObjectUtils.isEmpty(secondKnowledgeGraphNode)) {
                    return new ResponseEntity(ErrorResult.customError(
                            courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId() + "知识点不存在"), HttpStatus.CONFLICT);
                }
                boolean tag = neo4jUtil.checkRelevanceRelationship(
                        courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId(), secondKnowledgeGraphNodeId);
                if (tag) {
                    return new ResponseEntity(ErrorResult.customError("当前两个节点关联关系已存在"), HttpStatus.CONFLICT);
                }
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            courseKnowledgeGraphService.createCourseKnowledgeGraphNodeRelationship(courseKnowledgeGraphDomain, courseKnowledgeGraphNode,
                    courseKnowledgeGraphNodeRelationshipParam, ip);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("createCourseKnowledgeGraphNodeRelationship:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseKnowledgeGraphNodeFileModel>> getCourseKnowledgeGraphNodeFileList(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "课程知识图谱知识点id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphNodeId", required = true) String courseKnowledgeGraphNodeId,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {

            if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeId, courseKnowledgeGraphId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(
                            courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" +
                        courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }


            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeId + "知识点不存在"), HttpStatus.CONFLICT);
            }
            List<CourseKnowledgeGraphNodeFileModel> courseKnowledgeGraphNodeFileModelList =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNodeFileList(courseKnowledgeGraphDomain, courseKnowledgeGraphNode);
            return new ResponseEntity<>(courseKnowledgeGraphNodeFileModelList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphNodeFileList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> createCourseKnowledgeGraphNodeFileList(
            @ApiParam(value = "新增知识图谱节点学习材料参数", required = true)
            @Valid @RequestBody CourseKnowledgeGraphNodeFileParam courseKnowledgeGraphNodeFileParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(validCode, courseKnowledgeGraphNodeFileParam.getCourseId(),
                    courseKnowledgeGraphNodeFileParam.getKnowledgeGraphNodeId(),
                    courseKnowledgeGraphNodeFileParam.getCourseKnowledgeGraphId())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (CollectionUtils.isEmpty(courseKnowledgeGraphNodeFileParam.getCourseKnowledgeGraphNodeFileResourceList())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(
                            courseKnowledgeGraphNodeFileParam.getCourseKnowledgeGraphId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" +
                        courseKnowledgeGraphNodeFileParam.getCourseKnowledgeGraphId()
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }


            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }

            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseKnowledgeGraphNodeFileParam.getCourseId());
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(
                        courseKnowledgeGraphNodeFileParam.getCourseKnowledgeGraphId(), false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeFileParam.getKnowledgeGraphNodeId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeFileParam.getKnowledgeGraphNodeId() + "知识点不存在"), HttpStatus.CONFLICT);
            }
            courseKnowledgeGraphService.createCourseKnowledgeGraphNodeFileList(courseKnowledgeGraphDomain, courseKnowledgeGraphNode,
                    courseKnowledgeGraphNodeFileParam, ip);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("createCourseKnowledgeGraphNodeFileList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteCourseKnowledgeGraphNodeFile(
            @ApiParam(value = "节点学习材料id", required = true) @PathVariable("nodeFileId") String nodeFileId,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：nodeFileId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(nodeFileId, validCode, accessUserId, accessUserName)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("nodeFileId", nodeFileId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNodeResource(nodeFileId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeResource)) {
                return new ResponseEntity(ErrorResult.customError(
                        nodeFileId + "学习材料不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphNodeResource.getCourseKnowledgeGraphDomain();
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError(
                        nodeFileId + "学习材料所在课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseKnowledgeGraphDomain.getCourse().getId());
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }

            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeResource.getKnowledgeNodeId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(nodeFileId + "学习材料所在知识点不存在"), HttpStatus.CONFLICT);
            }
            courseKnowledgeGraphService.deleteCourseKnowledgeGraphNodeFile(
                    courseKnowledgeGraphDomain, courseKnowledgeGraphNode,
                    courseKnowledgeGraphNodeResource, accessUserId, accessUserName, ip);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("deleteCourseKnowledgeGraphNodeFile:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> reorderCourseKnowledgeGraphNodeResource(
            @ApiParam(value = "节点文件重新排序参数", required = true)
            @Valid @RequestBody NodeFileReorderParam nodeFileReorderParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {

            if (StringUtils.isAnyBlank(nodeFileReorderParam.getCourseId(),
                    nodeFileReorderParam.getKnowledgeGraphNodeId(), nodeFileReorderParam.getCourseKnowledgeGraphId(),
                    validCode) || ObjectUtils.isEmpty(nodeFileReorderParam)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isAllBlank(nodeFileReorderParam.getTargetLocationAfterNodeFileId(),
                    nodeFileReorderParam.getTargetLocationBeforeNodeFileId())
                    || StringUtils.isEmpty(nodeFileReorderParam.getNeedToBeMovedNodeFileId())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(
                            nodeFileReorderParam.getCourseKnowledgeGraphId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" +
                        nodeFileReorderParam.getCourseKnowledgeGraphId()
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("id为【" + nodeFileReorderParam.getCourseKnowledgeGraphId()
                        + "】的课程知识图谱已归档"), HttpStatus.CONFLICT);
            }
            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(nodeFileReorderParam.getCourseId());
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(
                        nodeFileReorderParam.getCourseKnowledgeGraphId(), false);
                return new ResponseEntity(ErrorResult.customError("id为【" + nodeFileReorderParam.getCourseKnowledgeGraphId()
                        + "】的课程知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            nodeFileReorderParam.getKnowledgeGraphNodeId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        nodeFileReorderParam.getKnowledgeGraphNodeId() + "知识点不存在"), HttpStatus.CONFLICT);
            }
            ErrorResult errorResult = courseKnowledgeGraphService.reorderCourseKnowledgeGraphNodeResource(
                    courseKnowledgeGraphDomain, courseKnowledgeGraphNode, nodeFileReorderParam);
            if (ObjectUtils.isNotEmpty(errorResult)) {
                return new ResponseEntity(errorResult, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("reorderCourseKnowledgeGraphNodeResource:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseKnowledgeGraphClassifyResource> getCourseKnowledgeGraphClassifyResource(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {

            if (StringUtils.isAnyBlank(courseId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomain(courseId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError(courseId + "课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphClassifyResource courseKnowledgeGraphClassifyResource =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphClassifyResource(courseKnowledgeGraphDomain);

            return new ResponseEntity<>(courseKnowledgeGraphClassifyResource, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphClassifyResource:" , e );
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> importKnowledgeStructure(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            courseKnowledgeGraphService.importKnowledgeStructure();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("importKnowledgeStructure:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> courseKnowledgeGraphSort(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @ApiParam(value = "课程知识图谱树形结构参数", required = true)
            @Valid @RequestBody CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel) {
        try {
            if (StringUtils.isAnyBlank(validCode, accessUserId, accessUserName, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }

            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }

            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            if (courseKnowledgeGraphNodeTreeModel.getLevelCount() > KnowledgeNodeLevel.values().length) {
                return new ResponseEntity(ErrorResult.customError("知识点层级不能超过10级"), HttpStatus.CONFLICT);
            }
            ErrorResult errorResult = courseKnowledgeGraphService.courseKnowledgeGraphSort(
                    courseKnowledgeGraphNodeTreeModel, courseKnowledgeGraphDomain, accessUserId, accessUserName);
            if (ObjectUtils.isNotEmpty(errorResult)) {
                return new ResponseEntity(errorResult, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("courseKnowledgeGraphSort:{}",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<ImportCourseKnowledgeGraphNodeResource> importCourseKnowledgeGraphNode(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户姓名", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @ApiParam(value = "谱知识节点信息")
            @Valid @RequestPart(value = "courseKnowledgeGraphNodeInfo", required = true) MultipartFile courseKnowledgeGraphNodeInfo) {
        if (StringUtils.isAnyBlank(validCode, userId, userName, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);

        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        Course course = courseService.findById(courseId);
        if (ObjectUtils.isEmpty(course)) {
            return new ResponseEntity(ErrorResult.customError(courseId + "课程不存在"), HttpStatus.CONFLICT);
        }
        try {
            ImportCourseKnowledgeGraphNodeResource result =
                    courseKnowledgeGraphExportService.importCourseKnowledgeGraphNode(request, userId, userName, courseKnowledgeGraphNodeInfo, course);
            if (result != null) {
                if (result.getMessage().contains("error-")) {
                    final int cutPrefix = 6;
                    String errorMesg = result.getMessage().substring(cutPrefix);
                    result.setMessage(errorMesg);
                    return new ResponseEntity<>(result, HttpStatus.CONFLICT);
                }
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("importCourseKnowledgeGraphNode->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> addOrUpdateCourseKnowledgeGraphClass(
            @NotNull @ApiParam(value = "验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "退回信息", required = true)
            @Valid @RequestBody CourseKnowledgeGraphInfo courseKnowledgeGraphInfo) {
        try {
            if (ObjectUtils.isEmpty(courseKnowledgeGraphInfo)
                    || CollectionUtils.isEmpty(courseKnowledgeGraphInfo.getCourseKnowledgeGraphClassVoList())
                    || StringUtils.isAnyBlank(validCode, courseKnowledgeGraphInfo.getCourseId())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            courseKnowledgeGraphService.addOrUpdateCourseKnowledgeGraphClass(courseKnowledgeGraphInfo);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            LOG.error("addOrUpdateCourseKnowledgeGraphClass:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ClassVo>> getCourseKnowledgeGraphClassList(
            @NotNull @ApiParam(value = "验证码courseId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "开课学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "开课学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term) {
        try {

            if (StringUtils.isAnyBlank(validCode, courseId, schoolYear) || term == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<ClassVo> classVoList =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphClassList(courseId, schoolYear, term);
            return new ResponseEntity<>(classVoList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphClassList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Boolean> checkCourseKnowledgeGraphPublicStatus(
            @NotNull @ApiParam(value = "验证码courseId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "开课学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "开课学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "班级id，多个用“,”隔开", required = true)
            @Valid @RequestParam(value = "groupIds", required = true) String groupIds) {
        try {

            if (StringUtils.isAnyBlank(validCode, courseId, schoolYear, groupIds) || term == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            Boolean publicStatus =
                    courseKnowledgeGraphService.checkCourseKnowledgeGraphPublicStatus(courseId, schoolYear, term, groupIds);
            return new ResponseEntity<>(publicStatus, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("checkCourseKnowledgeGraphPublicStatus:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> createCourseKnowledgeGraphNodeQuestionList(
            @ApiParam(value = "新增知识图谱节点题目参数", required = true)
            @Valid @RequestBody CourseKnowledgeGraphNodeQuestionParam courseKnowledgeGraphNodeQuestionParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(validCode, courseKnowledgeGraphNodeQuestionParam.getCourseId(),
                    courseKnowledgeGraphNodeQuestionParam.getKnowledgeGraphNodeId(),
                    courseKnowledgeGraphNodeQuestionParam.getCourseKnowledgeGraphId()) &&
                    CollectionUtils.isEmpty(courseKnowledgeGraphNodeQuestionParam.getQuestionIdList())
            ) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            String courseKnowledgeGraphId = courseKnowledgeGraphNodeQuestionParam.getCourseKnowledgeGraphId();
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }

            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }

            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseKnowledgeGraphNodeQuestionParam.getCourseId());
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeQuestionParam.getKnowledgeGraphNodeId());
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeQuestionParam.getKnowledgeGraphNodeId() + "知识点不存在"), HttpStatus.CONFLICT);
            }
            courseKnowledgeGraphService.createCourseKnowledgeGraphNodeQuestionList(courseKnowledgeGraphDomain, courseKnowledgeGraphNode,
                    courseKnowledgeGraphNodeQuestionParam, ip);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("createCourseKnowledgeGraphNodeQuestionList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionLibraryResource>> getCourseKnowledgeGraphNodeQuestionList(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "课程知识图谱知识点id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphNodeId", required = true) String courseKnowledgeGraphNodeId,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {

            if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeId, courseKnowledgeGraphId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }

            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }

            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeId + "知识点不存在"), HttpStatus.CONFLICT);
            }
            List<QuestionLibraryResource> questionLibraryResourceList =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNodeQuestionList(
                            courseKnowledgeGraphDomain, courseKnowledgeGraphNode);
            return new ResponseEntity<>(questionLibraryResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphNodeQuestionList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteCourseKnowledgeGraphNodeQuestion(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @ApiParam(value = "课程知识点id", required = true)
            @PathVariable("courseKnowledgeGraphNodeId") String courseKnowledgeGraphNodeId,
            @ApiParam(value = "题目id", required = true)
            @PathVariable("questionId") String questionId,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @NotNull @ApiParam(value = "验证码：questionId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeId,
                    questionId, accessUserId, accessUserName, courseKnowledgeGraphId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }

            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }

            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeId + "知识点不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNodeQuestion(courseKnowledgeGraphNodeId, questionId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeQuestion)) {
                return new ResponseEntity(ErrorResult.customError(
                        questionId + "该题目与知识点没有建立关系"), HttpStatus.CONFLICT);
            }
            if (!ValidUtils.checkValidCode("questionId", questionId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            courseKnowledgeGraphService.deleteCourseKnowledgeGraphNodeQuestion(
                    courseKnowledgeGraphDomain, courseKnowledgeGraphNode,
                    courseKnowledgeGraphNodeQuestion, accessUserId, accessUserName, ip);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("deleteCourseKnowledgeGraphNodeQuestion:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseKnowledgeGraphNodeBasicInfo>> getNodeBasicInfoListByQuestionId(
            @ApiParam(value = "题目id", required = true) @PathVariable("questionId") String questionId,
            @NotNull @ApiParam(value = "验证码：questionId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, questionId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("questionId", questionId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CourseKnowledgeGraphNodeBasicInfo> courseKnowledgeGraphNodeBasicInfoList =
                    courseKnowledgeGraphService.getNodeBasicInfoListByQuestionId(questionId);
            return new ResponseEntity<>(courseKnowledgeGraphNodeBasicInfoList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getNodeBasicInfoListByQuestionId:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteNodeQuestionByQuestionIdList(
            @ApiParam(value = "题目id列表", required = true)
            @Valid @RequestBody List<String> questionIdList,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            String ip = IpUtil.getIpAddr(request);
            if (StringUtils.isAnyBlank(validCode) && CollectionUtils.isEmpty(questionIdList)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            courseKnowledgeGraphService.deleteNodeQuestionByQuestionIdList(
                    questionIdList, accessUserId, accessUserName, ip);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("deleteNodeQuestionByQuestionIdList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> historyDataPushKafka(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            courseKnowledgeGraphService.historyDataPushKafka();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("historyDataPushKafka:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseKnowledgeGraphCorrelationNodeInfo>> getCourseKnowledgeGraphCorrelationNodeInfoList(
            @ApiParam(value = "知识图谱节点查询条件", required = true)
            @Valid @RequestBody CourseKnowledgeGraphNodeQueryInfoParam courseKnowledgeGraphNodeQueryInfoParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(courseKnowledgeGraphNodeQueryInfoParam.getCourseId(), validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (courseKnowledgeGraphNodeQueryInfoParam.getQueryType() == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(
                            courseKnowledgeGraphNodeQueryInfoParam.getCourseKnowledgeGraphId());

            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" +
                        courseKnowledgeGraphNodeQueryInfoParam.getCourseKnowledgeGraphId()
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }

            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }

            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(
                    courseKnowledgeGraphNodeQueryInfoParam.getCourseId());
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(
                        courseKnowledgeGraphNodeQueryInfoParam.getCourseKnowledgeGraphId(), false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            List<CourseKnowledgeGraphCorrelationNodeInfo> courseKnowledgeGraphCorrelationNodeInfoList =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphCorrelationNodeInfoList(
                            courseKnowledgeGraphDomain, courseKnowledgeGraphNodeQueryInfoParam);
            return new ResponseEntity<>(courseKnowledgeGraphCorrelationNodeInfoList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("queryCourseKnowledgeGraphCorrelationNodeInfo:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> batchCopyQuestionKnowledgeGraphNodeToNewQuestion(
            @ApiParam(value = "保存参数", required = true)
            @Valid @RequestBody CourseQuestionToNewQuestionKnowledgeGraphParam saveParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode) || ObjectUtils.isEmpty(saveParam)
                || StringUtils.isAnyBlank(saveParam.getUserId(), saveParam.getUserName(),
                saveParam.getCourseId()) || CollectionUtils.isEmpty(saveParam.getQuestionIdList())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        try {
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            courseKnowledgeGraphExtendService.batchCopyQuestionKnowledgeGraphNodeToNewQuestion(saveParam);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("batchCopyQuestionKnowledgeGraphNodeToNewQuestion->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseKnowledgeGraphNodeVideoInfoTextModel>> getCourseKnowledgeGraphNodeVideoInfoTextList(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "班级id", required = true)
            @Valid @RequestParam(value = "groupId", required = true) String groupId,
            @NotNull @ApiParam(value = "课程知识图谱知识点id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphNodeId", required = true) String courseKnowledgeGraphNodeId,
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphId", required = true) String courseKnowledgeGraphId,
            @ApiParam(value = "相似性分数")
            @Valid @RequestParam(value = "similarityScore", required = false) Double similarityScore,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId, courseKnowledgeGraphNodeId, courseKnowledgeGraphId, schoolYear, groupId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (term == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(
                            courseKnowledgeGraphId);

            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" +
                        courseKnowledgeGraphId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }

            if (!courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }

            String lastVersionLabel = courseKnowledgeGraphService.getLastVersionLabel(courseId);
            if (StringUtils.isNotBlank(lastVersionLabel) && !lastVersionLabel.equals(courseKnowledgeGraphDomain.getVersionLabel())) {
                courseKnowledgeGraphService.updateCourseKnowledgeGraphDomainStatus(courseKnowledgeGraphId, false);
                return new ResponseEntity(ErrorResult.customError("当前页面知识图谱已归档"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeId);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode)) {
                return new ResponseEntity(ErrorResult.customError(
                        courseKnowledgeGraphNodeId + "知识点不存在"), HttpStatus.CONFLICT);
            }
            List<CourseKnowledgeGraphNodeVideoInfoTextModel> courseKnowledgeGraphNodeVideoInfoTextList =
                    courseKnowledgeGraphLogicService.getCourseKnowledgeGraphNodeVideoInfoTextList(
                            courseKnowledgeGraphDomain, courseKnowledgeGraphNode,
                            schoolYear, term, groupId,similarityScore);
            return new ResponseEntity<>(courseKnowledgeGraphNodeVideoInfoTextList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCourseKnowledgeGraphNodeVideoInfoTextList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseKnowledgeGraphNodeVideoInfoTextModel>> getFilterCourseKnowledgeGraphNodeVideoInfoTextList(
            @ApiParam(value = "课程知识图谱知识点id", required = true)
            @PathVariable("courseKnowledgeGraphNodeId") String courseKnowledgeGraphNodeId,
            @NotNull @ApiParam(value = "视频信息id", required = true)
            @Valid @RequestParam(value = "videoInfoId", required = true) String videoInfoId,
            @ApiParam(value = "相似性分数")
            @Valid @RequestParam(value = "similarityScore", required = false) Double similarityScore,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseKnowledgeGraphNodeId, videoInfoId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<CourseKnowledgeGraphNodeVideoInfoTextModel> courseKnowledgeGraphNodeVideoInfoTextList =
                    courseKnowledgeGraphLogicService.getFilterCourseKnowledgeGraphNodeVideoInfoTextList(
                            courseKnowledgeGraphNodeId, videoInfoId,similarityScore);
            return new ResponseEntity<>(courseKnowledgeGraphNodeVideoInfoTextList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getFilterCourseKnowledgeGraphNodeVideoInfoTextList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CourseKnowledgeGraphVersionModel>> getHistoryVersionCourseKnowledgeGraphList(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<CourseKnowledgeGraphVersionModel> courseKnowledgeGraphVersionModelList =
                    courseKnowledgeGraphLogicService.getHistoryVersionCourseKnowledgeGraphList(courseId);
            return new ResponseEntity<>(courseKnowledgeGraphVersionModelList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getHistoryVersionCourseKnowledgeGraphList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseKnowledgeGraphNodeTreeModel> getHistoryCourseKnowledgeGraphNodeTreeModel(
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphDomainId", required = true) String courseKnowledgeGraphDomainId,
            @NotNull @ApiParam(value = "验证码：courseKnowledgeGraphDomainId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseKnowledgeGraphDomainId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("courseKnowledgeGraphDomainId", courseKnowledgeGraphDomainId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphDomainId);

            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphDomainId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel =
                    courseKnowledgeGraphService.getHistoryCourseKnowledgeGraphNodeTreeModel(courseKnowledgeGraphDomain);

            return new ResponseEntity<>(courseKnowledgeGraphNodeTreeModel, HttpStatus.OK);

        } catch (Exception e) {
            LOG.error("getHistoryCourseKnowledgeGraphNodeTreeModel:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> replyHistoryCourseKnowledgeGraphDomain(
            @NotNull @ApiParam(value = "课程知识图谱id", required = true)
            @Valid @RequestParam(value = "courseKnowledgeGraphDomainId", required = true) String courseKnowledgeGraphDomainId,
            @NotNull @ApiParam(value = "访问用户id", required = true)
            @Valid @RequestParam(value = "accessUserId", required = true) String accessUserId,
            @NotNull @ApiParam(value = "访问用户名称", required = true)
            @Valid @RequestParam(value = "accessUserName", required = true) String accessUserName,
            @NotNull @ApiParam(value = "验证码：courseKnowledgeGraphDomainId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseKnowledgeGraphDomainId, accessUserId, accessUserName)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("courseKnowledgeGraphDomainId", courseKnowledgeGraphDomainId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                    courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(courseKnowledgeGraphDomainId);

            if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
                return new ResponseEntity(ErrorResult.customError("id为【" + courseKnowledgeGraphDomainId
                        + "】的课程知识图谱不存在"), HttpStatus.CONFLICT);
            }
            if (courseKnowledgeGraphDomain.getStatus()) {
                return new ResponseEntity(ErrorResult.customError("该知识图谱正在使用中,无需恢复"), HttpStatus.CONFLICT);
            }

            courseKnowledgeGraphLogicService.replyHistoryCourseKnowledgeGraphDomain(
                    courseKnowledgeGraphDomain, accessUserId, accessUserName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("replyHistoryCourseKnowledgeGraphDomain:" ,e );
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> createCourseKnowledgeGraphNodeQuestionList(
            @ApiParam(value = "新增知识点和题目参数" ,required=true )
            @Valid @RequestBody QuestionKnowledgePointVo questionKnowledgePointVo,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (Objects.isNull(questionKnowledgePointVo)|| StringUtils.isAnyBlank(validCode, questionKnowledgePointVo.getQuestionId())){
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = questionCourseKnowledgeGraphService.createCourseKnowledgeGraphNodeQuestionList(questionKnowledgePointVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            LOG.error("createCourseKnowledgeGraphNodeQuestionList:" ,e );
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }



}

package com.lztech.site.service.courseknowledgegraph;

import com.lztech.domain.model.knowledgegraph.*;
import com.lztech.domain.model.knowledgegraph.enums.BusinessType;
import com.lztech.domain.model.knowledgegraph.enums.KnowledgeNodeLevel;
import com.lztech.domain.model.knowledgegraph.enums.LevelType;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphActionLogRepository;
import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.viewmodel.authorityapi.UsersInfoResource;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeRelationshipParam;
import com.lztech.site.viewmodel.questionlibrary.QuestionLibraryResource;
import com.lztech.site.viewmodel.userinfo.UserIdAndOpenIdVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.lztech.site.config.Access.signKey;

@Service
public class CourseKnowledgeGraphActionLogService {

    @Autowired
    private CourseKnowledgeGraphActionLogRepository courseKnowledgeGraphActionLogRepository;
    @Autowired
    private AuthorityApiService authorityApiService;
    @Autowired
    private Neo4jUtil neo4jUtil;

    @Value("${request.address.teachingApi}")
    private String teachingApi;

    @Async
    public void createAddCourseKnowledgeGraphNodeActionLog(CourseKnowledgeGraphNode courseKnowledgeGraphNode,
                                                           CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                           String ip) {
        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE.getName());
        courseKnowledgeGraphActionLog.setBusinessType(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE);
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        courseKnowledgeGraphActionLog.setOperatorId(courseKnowledgeGraphNode.getCreatorId());
        courseKnowledgeGraphActionLog.setOperatorNo(getUserNoById(courseKnowledgeGraphNode.getCreatorId()));
        courseKnowledgeGraphActionLog.setOperatorName(courseKnowledgeGraphNode.getCreatorName());
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        courseKnowledgeGraphActionLog.setOperatorTime(new Date());
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphActionLog.getOperatorTime());
        courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                + "老师新增" + courseKnowledgeGraphActionLog.getNowKnowledgeNodeLevelName() + "【"
                + courseKnowledgeGraphActionLog.getNowKnowledgeNodeName() + "】");
        createCourseKnowledgeGraphActionLog(courseKnowledgeGraphActionLog);
    }

    @Async
    public void createDeleteCourseKnowledgeGraphNodeActionLog(CourseKnowledgeGraphNode courseKnowledgeGraphNode,
                                                              CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                              String accessUserId,
                                                              String accessUserName,
                                                              String ip
    ) {
        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE.getName());
        courseKnowledgeGraphActionLog.setBusinessType(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE);
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        courseKnowledgeGraphActionLog.setOperatorId(accessUserId);
        courseKnowledgeGraphActionLog.setOperatorNo(getUserNoById(accessUserId));
        courseKnowledgeGraphActionLog.setOperatorName(accessUserName);
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        courseKnowledgeGraphActionLog.setOperatorTime(new Date());
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphActionLog.getOperatorTime());
        courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                + "老师删除" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeLevelName() + "【"
                + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName() + "】");
        createCourseKnowledgeGraphActionLog(courseKnowledgeGraphActionLog);
    }

    @Async
    public void createUpdateCourseKnowledgeGraphNodeContentActionLog(
            CourseKnowledgeGraphNode courseKnowledgeGraphNode, CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            String ip, BusinessType businessType, String knowledgeNodeDataId, String knowledgeNodeDataContent) {
        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(businessType.getName());
        courseKnowledgeGraphActionLog.setBusinessType(businessType);
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        courseKnowledgeGraphActionLog.setOperatorId(courseKnowledgeGraphNode.getModifierId());
        courseKnowledgeGraphActionLog.setOperatorNo(getUserNoById(courseKnowledgeGraphNode.getModifierId()));
        courseKnowledgeGraphActionLog.setOperatorName(courseKnowledgeGraphNode.getModifierName());
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        courseKnowledgeGraphActionLog.setOperatorTime(new Date());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataId(knowledgeNodeDataId);
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataContent(knowledgeNodeDataContent);
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphActionLog.getOperatorTime());
        if (BusinessType.UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_NAME.equals(businessType)) {
            courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataId(null);
            courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataContent(courseKnowledgeGraphNode.getKnowledgeNodeName());
            courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                    + "老师将知识点【" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName()
                    + "】修改为【" + courseKnowledgeGraphActionLog.getNowKnowledgeNodeDataContent() + "】");
        } else if (BusinessType.UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE.equals(businessType)) {
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNode.getLevelType())) {
                courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataId(null);
                courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataContent(null);
            } else {
                courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataId(String.valueOf(courseKnowledgeGraphNode.getLevelType()));
                courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataContent(
                        LevelType.getLevelType(courseKnowledgeGraphNode.getLevelType()).getName());
            }
            if (ObjectUtils.isEmpty(courseKnowledgeGraphActionLog.getOldKnowledgeNodeDataContent())) {
                courseKnowledgeGraphActionLog.setTitle(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE.getName());
                courseKnowledgeGraphActionLog.setBusinessType(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE);
                courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                        + "老师新增知识点【" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName()
                        + "】重要层级“" + courseKnowledgeGraphActionLog.getNowKnowledgeNodeDataContent() + "”");
            } else if (!ObjectUtils.isEmpty(courseKnowledgeGraphActionLog.getNowKnowledgeNodeDataContent())) {
                courseKnowledgeGraphActionLog.setTitle(BusinessType.UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE.getName());
                courseKnowledgeGraphActionLog.setBusinessType(BusinessType.UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE);
                courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                        + "老师将知识点【" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName()
                        + "】重要层级“" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeDataContent() + "”修改为“"
                        + courseKnowledgeGraphActionLog.getNowKnowledgeNodeDataContent() + "”");
            } else {
                courseKnowledgeGraphActionLog.setTitle(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE.getName());
                courseKnowledgeGraphActionLog.setBusinessType(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE);
                courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                        + "老师删除知识点【" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName()
                        + "】重要层级“" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeDataContent() + "”");
            }
        } else if (BusinessType.UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_CONTENT.equals(businessType)) {
            courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataId(null);
            courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataContent(courseKnowledgeGraphNode.getContentDetail());
            courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                    + "老师修改知识点【" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName() + "】的内容");
        }
        createCourseKnowledgeGraphActionLog(courseKnowledgeGraphActionLog);
    }

    @Async
    public void createAddCourseKnowledgeGraphNodeRelationshipActionLog(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeRelationshipParam courseKnowledgeGraphNodeRelationshipParam,
            String relationshipId, String ip) {
        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_RELATIONSHIP.getName());
        courseKnowledgeGraphActionLog.setBusinessType(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_RELATIONSHIP);
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId())) {
            //前置关系
            CourseKnowledgeGraphNode preCourseKnowledgeGraphNode =
                    getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeId(preCourseKnowledgeGraphNode.getId());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeName(preCourseKnowledgeGraphNode.getKnowledgeNodeName());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelIndex(preCourseKnowledgeGraphNode.getKnowledgeNodeLevel());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelName(
                    KnowledgeNodeLevel.getKnowledgeNodeLevel(preCourseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
            courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataId(relationshipId);
            courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataContent("前置关系");
        } else if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getPostKnowledgeGraphNodeId())) {
            //后置关系
            CourseKnowledgeGraphNode postCourseKnowledgeGraphNode =
                    getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeRelationshipParam.getPostKnowledgeGraphNodeId());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeId(postCourseKnowledgeGraphNode.getId());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeName(postCourseKnowledgeGraphNode.getKnowledgeNodeName());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelIndex(postCourseKnowledgeGraphNode.getKnowledgeNodeLevel());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelName(
                    KnowledgeNodeLevel.getKnowledgeNodeLevel(postCourseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
            courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataId(relationshipId);
            courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataContent("后置关系");
        } else {
            //关联关系
            CourseKnowledgeGraphNode relevanceCourseKnowledgeGraphNode =
                    getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                            courseKnowledgeGraphNodeRelationshipParam.getRelevanceKnowledgeGraphNodeId());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeId(relevanceCourseKnowledgeGraphNode.getId());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeName(relevanceCourseKnowledgeGraphNode.getKnowledgeNodeName());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelIndex(relevanceCourseKnowledgeGraphNode.getKnowledgeNodeLevel());
            courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelName(
                    KnowledgeNodeLevel.getKnowledgeNodeLevel(relevanceCourseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
            courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataId(relationshipId);
            courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataContent("关联关系");
        }
        courseKnowledgeGraphActionLog.setOperatorId(courseKnowledgeGraphNodeRelationshipParam.getAccessUserId());
        courseKnowledgeGraphActionLog.setOperatorNo(getUserNoById(courseKnowledgeGraphNodeRelationshipParam.getAccessUserId()));
        courseKnowledgeGraphActionLog.setOperatorName(courseKnowledgeGraphNodeRelationshipParam.getAccessUserName());
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        courseKnowledgeGraphActionLog.setOperatorTime(new Date());
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphActionLog.getOperatorTime());
        courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                + "老师新增知识点" + "【" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName() + "】的“"
                + courseKnowledgeGraphActionLog.getNowKnowledgeNodeDataContent()
                + "”为知识点【" + courseKnowledgeGraphActionLog.getOldRelationshipKnowledgeNodeName() + "】");
        createCourseKnowledgeGraphActionLog(courseKnowledgeGraphActionLog);
    }

    public CourseKnowledgeGraphNode getCourseKnowledgeGraphNode(
            String courseKnowledgeGraphDomainId, String parentCourseKnowledgeGraphNodeId) {
        return neo4jUtil.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomainId, parentCourseKnowledgeGraphNodeId);
    }

    @Async
    public void createDeleteCourseKnowledgeGraphNodeRelationshipActionLog(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship,
            String accessUserId, String accessUserName, String ip) {
        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_RELATIONSHIP.getName());
        courseKnowledgeGraphActionLog.setBusinessType(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_RELATIONSHIP);
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("FRONT_REAR")) {
            if (courseKnowledgeGraphNodeRelationship.getStartNodeId().equals(courseKnowledgeGraphNode.getId())) {
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeId(courseKnowledgeGraphNodeRelationship.getEndNodeId());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeName(courseKnowledgeGraphNodeRelationship.getEndNodeName());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelIndex(
                        courseKnowledgeGraphNodeRelationship.getEndKnowledgeNodeLevel());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelName(
                        KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNodeRelationship.getEndKnowledgeNodeLevel()).getName());
                courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataId(String.valueOf(courseKnowledgeGraphNodeRelationship.getRelationshipId()));
                courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataContent("后置关系");
            } else {
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeId(courseKnowledgeGraphNodeRelationship.getStartNodeId());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeName(courseKnowledgeGraphNodeRelationship.getStartNodeName());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelIndex(
                        courseKnowledgeGraphNodeRelationship.getStartKnowledgeNodeLevel());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelName(
                        KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNodeRelationship.getStartKnowledgeNodeLevel()).getName());
                courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataId(String.valueOf(courseKnowledgeGraphNodeRelationship.getRelationshipId()));
                courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataContent("前置关系");
            }
        } else {
            if (courseKnowledgeGraphNodeRelationship.getStartNodeId().equals(courseKnowledgeGraphNode.getId())) {
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeId(courseKnowledgeGraphNodeRelationship.getEndNodeId());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeName(courseKnowledgeGraphNodeRelationship.getEndNodeName());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelIndex(
                        courseKnowledgeGraphNodeRelationship.getEndKnowledgeNodeLevel());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelName(
                        KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNodeRelationship.getEndKnowledgeNodeLevel()).getName());
            } else {
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeId(courseKnowledgeGraphNodeRelationship.getStartNodeId());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeName(courseKnowledgeGraphNodeRelationship.getStartNodeName());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelIndex(
                        courseKnowledgeGraphNodeRelationship.getStartKnowledgeNodeLevel());
                courseKnowledgeGraphActionLog.setOldRelationshipKnowledgeNodeLevelName(
                        KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNodeRelationship.getStartKnowledgeNodeLevel()).getName());
            }
            courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataId(String.valueOf(courseKnowledgeGraphNodeRelationship.getRelationshipId()));
            courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataContent("关联关系");
        }
        courseKnowledgeGraphActionLog.setOperatorId(accessUserId);
        courseKnowledgeGraphActionLog.setOperatorNo(getUserNoById(accessUserId));
        courseKnowledgeGraphActionLog.setOperatorName(accessUserName);
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        courseKnowledgeGraphActionLog.setOperatorTime(new Date());
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphActionLog.getOperatorTime());
        courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                + "老师删除知识点" + "【" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName() + "】" +
                "与知识点【" + courseKnowledgeGraphActionLog.getOldRelationshipKnowledgeNodeName() + "】之间的“" +
                courseKnowledgeGraphActionLog.getOldKnowledgeNodeDataContent() + "”");
        createCourseKnowledgeGraphActionLog(courseKnowledgeGraphActionLog);
    }

    @Async
    public void createAddCourseKnowledgeGraphNodeResourceActionLog(CourseKnowledgeGraphNode courseKnowledgeGraphNode,
                                                                   CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                                   CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource,
                                                                   String ip) {
        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEARNING_RESOURCE.getName());
        courseKnowledgeGraphActionLog.setBusinessType(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEARNING_RESOURCE);
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataId(courseKnowledgeGraphNodeResource.getId());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataContent(courseKnowledgeGraphNodeResource.getResourceName());
        courseKnowledgeGraphActionLog.setOperatorId(courseKnowledgeGraphNodeResource.getCreatorId());
        courseKnowledgeGraphActionLog.setOperatorNo(getUserNoById(courseKnowledgeGraphNodeResource.getCreatorId()));
        courseKnowledgeGraphActionLog.setOperatorName(courseKnowledgeGraphNodeResource.getCreatorName());
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        courseKnowledgeGraphActionLog.setOperatorTime(new Date());
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphActionLog.getOperatorTime());
        courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                + "老师在" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeLevelName() + "【"
                + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName() + "】新增学习材料“"
                + courseKnowledgeGraphActionLog.getNowKnowledgeNodeDataContent() + "”");
        createCourseKnowledgeGraphActionLog(courseKnowledgeGraphActionLog);
    }

    @Async
    public void createDeleteCourseKnowledgeGraphNodeResourceActionLog(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource, String accessUserId, String accessUserName,
            String ip) {
        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_LEARNING_RESOURCE.getName());
        courseKnowledgeGraphActionLog.setBusinessType(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_LEARNING_RESOURCE);
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataId(courseKnowledgeGraphNodeResource.getId());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataContent(courseKnowledgeGraphNodeResource.getResourceName());
        courseKnowledgeGraphActionLog.setOperatorId(accessUserId);
        courseKnowledgeGraphActionLog.setOperatorNo(getUserNoById(accessUserId));
        courseKnowledgeGraphActionLog.setOperatorName(accessUserName);
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        courseKnowledgeGraphActionLog.setOperatorTime(new Date());
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphActionLog.getOperatorTime());
        courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                + "老师在" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeLevelName() + "【"
                + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName() + "】删除学习材料“"
                + courseKnowledgeGraphActionLog.getOldKnowledgeNodeDataContent() + "”");
        createCourseKnowledgeGraphActionLog(courseKnowledgeGraphActionLog);
    }


    @Async
    public void createAddCourseKnowledgeGraphNodeQuestionActionLog(CourseKnowledgeGraphNode courseKnowledgeGraphNode,
                                                                   CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                                   CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion,
                                                                   String ip) {
        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_QUESTION.getName());
        courseKnowledgeGraphActionLog.setBusinessType(BusinessType.CREATE_COURSE_KNOWLEDGE_GRAPH_NODE_QUESTION);
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataId(courseKnowledgeGraphNodeQuestion.getQuestionId());
        QuestionLibraryResource questionLibraryResource = getQuestionLibraryById(courseKnowledgeGraphNodeQuestion.getQuestionId());
        if (ObjectUtils.isNotEmpty(questionLibraryResource)) {
            courseKnowledgeGraphActionLog.setNowKnowledgeNodeDataContent(convertQuestionHtml(questionLibraryResource.getQuestionStem()));
        }
        courseKnowledgeGraphActionLog.setOperatorId(courseKnowledgeGraphNodeQuestion.getCreatorId());
        courseKnowledgeGraphActionLog.setOperatorNo(getUserNoById(courseKnowledgeGraphNodeQuestion.getCreatorId()));
        courseKnowledgeGraphActionLog.setOperatorName(courseKnowledgeGraphNodeQuestion.getCreatorName());
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        courseKnowledgeGraphActionLog.setOperatorTime(new Date());
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphActionLog.getOperatorTime());
        courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                + "老师在" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeLevelName() + "【"
                + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName() + "】关联题目“"
                + convertQuestionHtml(courseKnowledgeGraphActionLog.getNowKnowledgeNodeDataContent()) + "”");
        createCourseKnowledgeGraphActionLog(courseKnowledgeGraphActionLog);
    }

    @Async
    public void createDeleteCourseKnowledgeGraphNodeQuestionActionLog(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion,
            String accessUserId, String accessUserName, String ip) {
        CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog = new CourseKnowledgeGraphActionLog();
        courseKnowledgeGraphActionLog.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphActionLog.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphActionLog.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphActionLog.setTitle(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_QUESTION.getName());
        courseKnowledgeGraphActionLog.setBusinessType(BusinessType.DELETE_COURSE_KNOWLEDGE_GRAPH_NODE_QUESTION);
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelIndex(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeLevelName(
                KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
        courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataId(courseKnowledgeGraphNodeQuestion.getQuestionId());
        QuestionLibraryResource questionLibraryResource = getQuestionLibraryById(courseKnowledgeGraphNodeQuestion.getQuestionId());
        if (ObjectUtils.isNotEmpty(questionLibraryResource)) {
            courseKnowledgeGraphActionLog.setOldKnowledgeNodeDataContent(convertQuestionHtml(questionLibraryResource.getQuestionStem()));
        }
        courseKnowledgeGraphActionLog.setOperatorId(accessUserId);
        courseKnowledgeGraphActionLog.setOperatorNo(getUserNoById(accessUserId));
        courseKnowledgeGraphActionLog.setOperatorName(accessUserName);
        courseKnowledgeGraphActionLog.setOperatorIp(ip);
        courseKnowledgeGraphActionLog.setOperatorTime(new Date());
        String operatorTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphActionLog.getOperatorTime());
        courseKnowledgeGraphActionLog.setDetailContent(operatorTime + " " + courseKnowledgeGraphActionLog.getOperatorName()
                + "老师删除知识点" + "【" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeName() + "】" +
                "与题目“" + courseKnowledgeGraphActionLog.getOldKnowledgeNodeDataContent() + "”之间的关系");
        createCourseKnowledgeGraphActionLog(courseKnowledgeGraphActionLog);
    }

    private String getUserNoById(String creatorId) {
        List<UserIdAndOpenIdVo> userIdAndOpenIdVos = new ArrayList<>();
        UserIdAndOpenIdVo userIdAndOpenIdVo = new UserIdAndOpenIdVo();
        userIdAndOpenIdVo.setUserId(creatorId);
        userIdAndOpenIdVos.add(userIdAndOpenIdVo);
        List<UsersInfoResource> usersInfos = authorityApiService.getUsersInfoResource(userIdAndOpenIdVos);
        return !usersInfos.isEmpty() ?
                usersInfos.get(0).getUserNo() : "";
    }

    public void createCourseKnowledgeGraphActionLog(CourseKnowledgeGraphActionLog courseKnowledgeGraphActionLog) {
        courseKnowledgeGraphActionLogRepository.save(courseKnowledgeGraphActionLog);
    }

    public QuestionLibraryResource getQuestionLibraryById(String questionId) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5("id=" + questionId + signKey);
        QuestionLibraryResource questionLibraryResource = new QuestionLibraryResource();
        String url = teachingApi + ConstantWebApi.GET_QUESTION_LIBRARY + "/" + questionId + "?validCode=" + key;
        try {
            ResponseEntity<QuestionLibraryResource> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<QuestionLibraryResource>() {
                    });
            questionLibraryResource = responseEntity.getBody();
            return questionLibraryResource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<QuestionLibraryResource> getQuestionLibraryList(List<String> questionIdList) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5(signKey);
        String url = teachingApi + ConstantWebApi.POST_QUESTION_LIBRARY_LIST + "?validCode=" + key;
        //设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<List<String>> httpEntity = new HttpEntity<>(questionIdList, headers);
        try {
            ResponseEntity<List<QuestionLibraryResource>> responseEntity = restTemplate
                    .exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<QuestionLibraryResource>>() {
                    });
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            }
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String convertQuestionHtml(String htmlContent) {
        Document document = Jsoup.parse(htmlContent);
        Elements elementList = document.getElementsByTag("img");
        int imgNum = 0;
        int formulaNum = 0;
        for (Element element : elementList) {
            String className = element.attr("class");
            if (StringUtils.isNotBlank(className)) {
                formulaNum = formulaNum + 1;
                String formulaName = "[公式" + formulaNum + "]";
                // 创建一个新的Element
                Element newElement = document.createElement("span");
                newElement.text(formulaName);
                newElement.attr("class", "formula_" + formulaNum);
                element.replaceWith(newElement);
            } else {
                imgNum = imgNum + 1;
                String imgName = "[图" + imgNum + "]";
                // 创建一个新的Element
                Element newElement = document.createElement("span");
                newElement.text(imgName);
                newElement.attr("class", "img_" + imgName);
                element.replaceWith(newElement);
            }
        }
        htmlContent = Jsoup.clean(document.outerHtml(), Whitelist.none()).replace("&nbsp;", "");
        return StringEscapeUtils.unescapeHtml4(htmlContent);
    }

}

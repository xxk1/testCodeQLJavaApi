package com.lztech.site.service.courseknowledgegraph;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.KnowledgeStructureType;
import com.lztech.domain.model.course.enums.TranscodeStatus;
import com.lztech.domain.model.knowledgegraph.*;
import com.lztech.domain.model.knowledgegraph.enums.*;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.CourseResourceKnowledgePointRepository;
import com.lztech.persistence.repositories.courseknowledgegraph.*;
import com.lztech.persistence.repositories.courseknowledgestructure.CourseKnowledgeStructureRepository;
import com.lztech.persistence.repositories.courseobjective.CourseObjectiveRelatedKgNodeRepository;
import com.lztech.persistence.repositories.courseobjective.CourseObjectivesRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.service.coursemanagement.TranscodeService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.viewmodel.courseknowledgegraph.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.event.KnowledgeGraphDomainTopicVo;
import com.lztech.site.viewmodel.event.KnowledgeStructureTopicVo;
import com.lztech.site.viewmodel.questionlibrary.QuestionLibraryResource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.neo4j.driver.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.TWO;
import static com.lztech.site.constants.ConstantDataVisual.DATA_VISUAL_SOURCE_CLOUD_CLASSROOM;
import static org.neo4j.driver.Values.parameters;

@Service
public class CourseKnowledgeGraphService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseKnowledgeGraphService.class);
    @Autowired
    private CourseKnowledgeGraphDomainRepository courseKnowledgeGraphDomainRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private Neo4jUtil neo4jUtil;
    @Autowired
    private CourseResourceKnowledgePointRepository courseResourceKnowledgePointRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseKnowledgeStructureRepository courseKnowledgeStructureRepository;
    @Autowired
    private CourseKnowledgeGraphNodeResourceRepository courseKnowledgeGraphNodeResourceRepository;
    @Autowired
    private CourseKnowledgeGraphNodeResourceFileRepository courseKnowledgeGraphNodeResourceFileRepository;
    @Autowired
    private TranscodeService transcodeService;
    @Autowired
    private CourseKnowledgeGraphActionLogService courseKnowledgeGraphActionLogService;
    @Autowired
    private CourseKnowledgeGraphClassRepository classRepository;
    @Autowired
    private CourseKnowledgeGraphNodeQuestionRepository courseKnowledgeGraphNodeQuestionRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private CourseKnowledgeGraphNodeVideoInfoTextRepository courseKnowledgeGraphNodeVideoInfoTextRepository;
    @Autowired
    private CourseObjectiveRelatedKgNodeRepository relatedKgNodeRepository;
    @Autowired
    private CourseObjectivesRepository courseObjectivesRepository;

    @org.springframework.beans.factory.annotation.Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;

    public CourseKnowledgeGraphNodeTreeModel getCourseKnowledgeGraphTree(Course course, String accessUserId, String accessUserName) {

        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                courseKnowledgeGraphDomainRepository.getEffectiveCourseKnowledgeGraphDomainByCourseId(course.getId());

        if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
            List<CourseVersion> courseVersions = courseVersionRepository.findByCourseInAndCourseVersionStatus(
                    Collections.singletonList(course), CourseVersionStatus.IN_USE);
            CourseVersion courseVersion = courseVersions.get(0);
            CourseKnowledgeGraphDomain createCourseKnowledgeGraphDomain =
                    createCourseKnowledgeGraphDomain(course, courseVersion, accessUserId, accessUserName);
            courseKnowledgeGraphDomain = courseKnowledgeGraphDomainRepository.saveAndFlush(createCourseKnowledgeGraphDomain);
            /*kafka知识图谱数据推送*/
            if (dataVisualEnable) {
                KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo = getKnowledgeGraphDomainTopicVo(courseKnowledgeGraphDomain);
                eventService.sendKnowledgeGraphDomainTopicVo(knowledgeGraphDomainTopicVo);
            }
            CourseKnowledgeGraphNode rootCourseKnowledgeGraphNode = createRootCourseKnowledgeGraphNode(courseKnowledgeGraphDomain);
            neo4jUtil.createRootCourseKnowledgeGraphNode(rootCourseKnowledgeGraphNode, courseKnowledgeGraphDomain.getId());
        }
        CourseKnowledgeGraphNode courseKnowledgeGraphNode = neo4jUtil.getRootCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId());
        if (neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId()) > 1) {
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel =
                    neo4jUtil.getNodeTree(courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            packCourseKnowledgeGraphNodeTreeModel(courseKnowledgeGraphNodeTreeModel, courseKnowledgeGraphDomain.getId());
            return courseKnowledgeGraphNodeTreeModel;
        } else {
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel = new CourseKnowledgeGraphNodeTreeModel();
            courseKnowledgeGraphNodeTreeModel.setId(courseKnowledgeGraphNode.getId());
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelIndex(
                    KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getIndex());
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelName(
                    KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
            courseKnowledgeGraphNodeTreeModel.setSort(courseKnowledgeGraphNode.getSort());
            courseKnowledgeGraphNodeTreeModel.setCourseKnowledgeGraphId(courseKnowledgeGraphDomain.getId());
            return courseKnowledgeGraphNodeTreeModel;
        }
    }

    public void packCourseKnowledgeGraphNodeTreeModel(CourseKnowledgeGraphNodeTreeModel
                                                              courseKnowledgeGraphNodeTreeModel, String courseKnowledgeGraphId) {
        courseKnowledgeGraphNodeTreeModel.setCourseKnowledgeGraphId(courseKnowledgeGraphId);
        List<CourseKnowledgeGraphNodeTreeModel> subsetCourseKnowledgeGraphNodeTreeList =
                courseKnowledgeGraphNodeTreeModel.getSubsetCourseKnowledgeGraphNodeTreeList();
        if (!CollectionUtils.isEmpty(subsetCourseKnowledgeGraphNodeTreeList)) {
            for (CourseKnowledgeGraphNodeTreeModel childCourseKnowledgeGraphNodeTreeModel : subsetCourseKnowledgeGraphNodeTreeList) {
                packCourseKnowledgeGraphNodeTreeModel(childCourseKnowledgeGraphNodeTreeModel, courseKnowledgeGraphId);
            }
        }
    }

    public CourseKnowledgeGraphNode createRootCourseKnowledgeGraphNode(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
        courseKnowledgeGraphNode.setId(UUID.randomUUID().toString().replace("-", ""));
        courseKnowledgeGraphNode.setKnowledgeNodeLevel(KnowledgeNodeLevel.ZERO_LEVEL.getIndex());
        courseKnowledgeGraphNode.setKnowledgeNodeName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphNode.setName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphNode.setSort(1);
        courseKnowledgeGraphNode.setCreatorId(courseKnowledgeGraphDomain.getCreatorId());
        courseKnowledgeGraphNode.setCreatorName(courseKnowledgeGraphDomain.getCreatorName());
        courseKnowledgeGraphNode.setCreateTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseKnowledgeGraphDomain.getCreateTime()));
        return courseKnowledgeGraphNode;
    }

    public CourseKnowledgeGraphDomain getCourseKnowledgeGraphDomain(String courseId) {
        return courseKnowledgeGraphDomainRepository.getEffectiveCourseKnowledgeGraphDomainByCourseId(courseId);
    }

    public CourseKnowledgeGraphDomain getCourseKnowledgeGraphDomainById(String id) {
        return courseKnowledgeGraphDomainRepository.getCourseKnowledgeGraphDomainById(id);
    }

    public CourseKnowledgeGraphNode getCourseKnowledgeGraphNode(
            String courseKnowledgeGraphDomainId, String parentCourseKnowledgeGraphNodeId) {
        return neo4jUtil.getCourseKnowledgeGraphNode(courseKnowledgeGraphDomainId, parentCourseKnowledgeGraphNodeId);
    }

    public void createCourseKnowledgeGraphNode(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode parentCourseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeData courseKnowledgeGraphNodeData, String ip) {
        CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
        courseKnowledgeGraphNode.setId(UUID.randomUUID().toString().replace("-", ""));
        courseKnowledgeGraphNode.setCreateTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
        int childNodeCounts = neo4jUtil.getDomainChildNodeCount(courseKnowledgeGraphDomain.getId(), parentCourseKnowledgeGraphNode.getId());
        courseKnowledgeGraphNode.setSort(childNodeCounts + 1);
        courseKnowledgeGraphNode.setName(courseKnowledgeGraphNodeData.getKnowledgeNodeName().trim());
        courseKnowledgeGraphNode.setKnowledgeNodeName(courseKnowledgeGraphNodeData.getKnowledgeNodeName().trim());
        courseKnowledgeGraphNode.setCreatorId(courseKnowledgeGraphNodeData.getAccessUserId());
        courseKnowledgeGraphNode.setCreatorName(courseKnowledgeGraphNodeData.getAccessUserName());
        courseKnowledgeGraphNode.setKnowledgeNodeLevel(parentCourseKnowledgeGraphNode.getKnowledgeNodeLevel() + 1);
        neo4jUtil.createChildCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(), parentCourseKnowledgeGraphNode.getId(),
                courseKnowledgeGraphNode);
        int nodeCounts = neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphDomain.setNodeCount(nodeCounts);
        updateCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain, courseKnowledgeGraphNodeData.getAccessUserId(),
                courseKnowledgeGraphNodeData.getAccessUserName());
        courseCompletionService.saveCourseCompletion(courseKnowledgeGraphDomain.getCourse(), CourseContentTypeEnum.KNOWLEDGE_STRUCTURE,
                true, courseKnowledgeGraphNodeData.getAccessUserId(), courseKnowledgeGraphNodeData.getAccessUserName());
        courseKnowledgeGraphActionLogService.createAddCourseKnowledgeGraphNodeActionLog(
                courseKnowledgeGraphNode, courseKnowledgeGraphDomain, ip);
        if (dataVisualEnable) {
            sendKnowledgeStructureTopicVo(courseKnowledgeGraphDomain, courseKnowledgeGraphNode, parentCourseKnowledgeGraphNode,
                    courseKnowledgeGraphNodeData.getAccessUserId(), courseKnowledgeGraphNodeData.getAccessUserName());
        }
    }

    @Transactional
    public synchronized void deleteCourseKnowledgeGraphNode(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                            CourseKnowledgeGraphNode courseKnowledgeGraphNode,
                                                            String accessUserId, String accessUserName, String courseId,
                                                            String ip) {
        List<String> knowledgeGraphNodeIdList = neo4jUtil.getDeleteChildKnowledgeGraphNodeIdList(
                courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphNodeResourceRepository.deleteByKnowledgeGraphNodeIdList(knowledgeGraphNodeIdList, accessUserId, accessUserName);
        /* 删除对应知识图谱与题目的关系*/
        courseKnowledgeGraphNodeQuestionRepository.deleteByKnowledgeNodeIdIn(knowledgeGraphNodeIdList);
        courseResourceKnowledgePointRepository.deleteByPointIdIn(knowledgeGraphNodeIdList);
        neo4jUtil.deleteKnowledgeGraphNodeAndChildKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode);
        int nodeCounts = neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphDomain.setNodeCount(nodeCounts);
        if (nodeCounts == 1) {
            Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.KNOWLEDGE_STRUCTURE, false, accessUserId,
                    accessUserName);
        }
        courseKnowledgeGraphActionLogService.createDeleteCourseKnowledgeGraphNodeActionLog(
                courseKnowledgeGraphNode, courseKnowledgeGraphDomain, accessUserId, accessUserName, ip);
        updateCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain, accessUserId, accessUserName);
        deleteCourseObjectiveRelatedKgNode(knowledgeGraphNodeIdList, courseKnowledgeGraphDomain.getId());
        if (dataVisualEnable) {
            if (!CollectionUtils.isEmpty(knowledgeGraphNodeIdList)) {
                for (String knowledgeGraphNodeId : knowledgeGraphNodeIdList) {
                    eventService.sendDeleteKnowledgeStructure(knowledgeGraphNodeId);
                }
            }
        }
    }

    private void deleteCourseObjectiveRelatedKgNode(List<String> nodeIdList,
                                                    String courseKnowledgeGraphDomainId) {
        List<CourseObjectiveRelatedKgNode> relatedRecords =
                relatedKgNodeRepository.findByNodeIdListAndCourseKnowledgeGraphId(nodeIdList,
                        courseKnowledgeGraphDomainId);
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(relatedRecords)) {
            return;
        }
        Set<String> objectiveIds = relatedRecords.stream()
                .map(CourseObjectiveRelatedKgNode::getCourseObjectiveId)
                .collect(Collectors.toSet());

        // ② 一次性删除所有关联记录（SQL 2）
        relatedKgNodeRepository.deleteByRelatedKnowledgePointIdInAndCourseKnowledgeGraphId(nodeIdList,
                courseKnowledgeGraphDomainId);

        // ③ 一次性统计所有课程目标剩余的知识点数量（SQL 3）
        List<Map<String, Object>> countResult =
                relatedKgNodeRepository.countGroupByObjectiveIds(objectiveIds);
        Map<String, Integer> countMap = new HashMap<>();
        for (Map<String, Object> row : countResult) {
            String objId = (String) row.get("courseObjectiveId");
            Long cnt = (Long) row.get("cnt");
            countMap.put(objId, cnt.intValue());
        }
        // ④ 一次性查出所有课程目标（SQL 4）
        List<CourseObjective> objectives = courseObjectivesRepository.findAllById(objectiveIds);

        // 设置统计结果
        for (CourseObjective obj : objectives) {
            obj.setRelatedKnowledgePointNum(countMap.get(obj.getId()));
        }
        courseObjectivesRepository.saveAll(objectives);


    }

    public CourseKnowledgeGraphNodeModel getCourseKnowledgeGraphNodeInfo(CourseKnowledgeGraphNode courseKnowledgeGraphNode) {
        CourseKnowledgeGraphNodeModel courseKnowledgeGraphNodeModel = new CourseKnowledgeGraphNodeModel();
        courseKnowledgeGraphNodeModel.setId(courseKnowledgeGraphNode.getId());
        courseKnowledgeGraphNodeModel.setKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        courseKnowledgeGraphNodeModel.setContentDetail(courseKnowledgeGraphNode.getContentDetail());
        courseKnowledgeGraphNodeModel.setContentGenerationModeIndex(Objects.isNull(courseKnowledgeGraphNode.getContentGenerationModeIndex()) ?
                ContentGenerationMode.MANUAL_MAINTENANCE.getIndex() :
                courseKnowledgeGraphNode.getContentGenerationModeIndex());
        List<LevelTypeModel> levelTypeModelList = new ArrayList<>();
        for (LevelType levelType : LevelType.values()) {
            LevelTypeModel levelTypeModel = new LevelTypeModel();
            levelTypeModel.setLevelTypeIndex(levelType.getIndex());
            levelTypeModel.setKnowledgeNodeName(levelType.getName());
            levelTypeModel.setSelect(!ObjectUtils.isEmpty(courseKnowledgeGraphNode.getLevelType())
                    && (courseKnowledgeGraphNode.getLevelType().equals(levelType.getIndex())));
            levelTypeModelList.add(levelTypeModel);

        }
        courseKnowledgeGraphNodeModel.setLevelTypeList(levelTypeModelList);
        return courseKnowledgeGraphNodeModel;
    }

    public ErrorResult updateCourseKnowledgeGraphNode(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                      CourseKnowledgeGraphNodeParam courseKnowledgeGraphNodeParam,
                                                      CourseKnowledgeGraphNode courseKnowledgeGraphNode, String ip) {
        String modifyTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date());
        if (StringUtils.isNotBlank(courseKnowledgeGraphNodeParam.getKnowledgeNodeName()) &&
                !courseKnowledgeGraphNodeParam.getKnowledgeNodeName().equals(courseKnowledgeGraphNode.getKnowledgeNodeName())) {
            if (courseKnowledgeGraphNodeParam.getKnowledgeNodeName().trim().length() > Constant.FIVE_HUNDRED) {
                return ErrorResult.customError("知识点名称不能超过500个字");
            }
            String cypherSql = String.format("MATCH (n:`%s`{id:'%s'}) set n.knowledgeNodeName=$knowledgeNodeName,n.name=$name," +
                    "n.modifierId=$modifierId,n.modifierName=$modifierName,n.modifyTime=$modifyTime" +
                    " return n ", courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            Value value = parameters("knowledgeNodeName", courseKnowledgeGraphNodeParam.getKnowledgeNodeName().trim(),
                    "name", courseKnowledgeGraphNodeParam.getKnowledgeNodeName().trim(), "modifierId",
                    courseKnowledgeGraphNodeParam.getAccessUserId(),
                    "modifierName", courseKnowledgeGraphNodeParam.getAccessUserName(), "modifyTime", modifyTime);
            neo4jUtil.updateCourseKnowledgeGraphNode(cypherSql, value);
            courseKnowledgeGraphNode.setModifierId(courseKnowledgeGraphNodeParam.getAccessUserId());
            courseKnowledgeGraphNode.setModifierName(courseKnowledgeGraphNodeParam.getAccessUserName());
            courseKnowledgeGraphActionLogService.createUpdateCourseKnowledgeGraphNodeContentActionLog(
                    courseKnowledgeGraphNode, courseKnowledgeGraphDomain, ip, BusinessType.UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_NAME,
                    null, courseKnowledgeGraphNodeParam.getKnowledgeNodeName().trim());
            if (dataVisualEnable) {
                CourseKnowledgeGraphNode parentCourseKnowledgeGraphNode = getParentCourseKnowledgeGraphNode(
                        courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
                courseKnowledgeGraphNode.setKnowledgeNodeName(
                        courseKnowledgeGraphNodeParam.getKnowledgeNodeName().trim());
                sendKnowledgeStructureTopicVo(courseKnowledgeGraphDomain, courseKnowledgeGraphNode, parentCourseKnowledgeGraphNode,
                        courseKnowledgeGraphNodeParam.getAccessUserId(), courseKnowledgeGraphNodeParam.getAccessUserName());
            }
        } else if (courseKnowledgeGraphNodeParam.getLevelTypeIndex() != null &&
                !Objects.equals(courseKnowledgeGraphNodeParam.getLevelTypeIndex(), courseKnowledgeGraphNode.getLevelType())) {
            String cypherSql = String.format("MATCH (n:`%s`{id:'%s'}) set n.levelType=$levelType ," +
                            "n.modifierId=$modifierId,n.modifierName=$modifierName,n.modifyTime=$modifyTime return n ",
                    courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            Value value = parameters("levelType", courseKnowledgeGraphNodeParam.getLevelTypeIndex(),
                    "modifierId", courseKnowledgeGraphNodeParam.getAccessUserId(),
                    "modifierName", courseKnowledgeGraphNodeParam.getAccessUserName(), "modifyTime", modifyTime);
            neo4jUtil.updateCourseKnowledgeGraphNode(cypherSql, value);
            courseKnowledgeGraphNode.setModifierId(courseKnowledgeGraphNodeParam.getAccessUserId());
            courseKnowledgeGraphNode.setModifierName(courseKnowledgeGraphNodeParam.getAccessUserName());
            courseKnowledgeGraphActionLogService.createUpdateCourseKnowledgeGraphNodeContentActionLog(
                    courseKnowledgeGraphNode, courseKnowledgeGraphDomain,
                    ip, BusinessType.UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE,
                    String.valueOf(courseKnowledgeGraphNodeParam.getLevelTypeIndex()),
                    LevelType.getLevelType(courseKnowledgeGraphNodeParam.getLevelTypeIndex()).getName());
        } else if (StringUtils.isNotBlank(courseKnowledgeGraphNodeParam.getContentDetail()) &&
                !courseKnowledgeGraphNodeParam.getContentDetail().equals(courseKnowledgeGraphNode.getContentDetail())) {
//            if (courseKnowledgeGraphNodeParam.getContentDetail().length() > Constant.MAGIC_SIX_HUNDRED) {
//                return ErrorResult.customError("知识点内容不能超过600个字");
//            }
            String cypherSql = String.format("MATCH (n:`%s`{id:'%s'}) set n.contentDetail=$contentDetail," +
                            "n.modifierId=$modifierId,n.modifierName=$modifierName,n.modifyTime=$modifyTime," +
                            "n.contentGenerationModeIndex=$contentGenerationModeIndex return n ",
                    courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            Value value = parameters("contentDetail", courseKnowledgeGraphNodeParam.getContentDetail(),
                    "modifierId", courseKnowledgeGraphNodeParam.getAccessUserId(),
                    "modifierName", courseKnowledgeGraphNodeParam.getAccessUserName(), "modifyTime", modifyTime, "contentGenerationModeIndex",
                    courseKnowledgeGraphNodeParam.getContentGenerationModeIndex());
            neo4jUtil.updateCourseKnowledgeGraphNode(cypherSql, value);
            courseKnowledgeGraphNode.setModifierId(courseKnowledgeGraphNodeParam.getAccessUserId());
            courseKnowledgeGraphNode.setModifierName(courseKnowledgeGraphNodeParam.getAccessUserName());
            courseKnowledgeGraphActionLogService.createUpdateCourseKnowledgeGraphNodeContentActionLog(
                    courseKnowledgeGraphNode, courseKnowledgeGraphDomain, ip, BusinessType.UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_CONTENT,
                    null, courseKnowledgeGraphNodeParam.getContentDetail());
        } else if (courseKnowledgeGraphNodeParam.getLevelTypeIndex() == null
                && ObjectUtils.isNotEmpty(courseKnowledgeGraphNode.getLevelType())) {
            String cypherSql = String.format("MATCH (n:`%s`{id:'%s'}) set n.levelType=$levelType ," +
                            "n.modifierId=$modifierId,n.modifierName=$modifierName,n.modifyTime=$modifyTime return n ",
                    courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            Value value = parameters("levelType", null,
                    "modifierId", courseKnowledgeGraphNodeParam.getAccessUserId(), "modifierName", courseKnowledgeGraphNodeParam.getAccessUserName(),
                    "modifyTime", modifyTime);
            neo4jUtil.updateCourseKnowledgeGraphNode(cypherSql, value);
            courseKnowledgeGraphNode.setModifierId(courseKnowledgeGraphNodeParam.getAccessUserId());
            courseKnowledgeGraphNode.setModifierName(courseKnowledgeGraphNodeParam.getAccessUserName());
            courseKnowledgeGraphActionLogService.createUpdateCourseKnowledgeGraphNodeContentActionLog(
                    courseKnowledgeGraphNode, courseKnowledgeGraphDomain, ip, BusinessType.UPDATE_COURSE_KNOWLEDGE_GRAPH_NODE_LEVEL_TYPE,
                    null, null);
        }
        return null;
    }

    public CourseKnowledgeGraphNodeRelationshipResource getCourseKnowledgeGraphNodeRelationshipList(
            String courseKnowledgeGraphDomainId, String courseKnowledgeGraphNodeId) {
        CourseKnowledgeGraphNodeRelationshipResource courseKnowledgeGraphNodeRelationshipResource =
                new CourseKnowledgeGraphNodeRelationshipResource();
        //前置关系
        List<CourseKnowledgeGraphNodeVo> preposeKnowledgeGraphNodeList = new ArrayList<>();
        //后置关系
        List<CourseKnowledgeGraphNodeVo> postposeKnowledgeGraphNodeList = new ArrayList<>();
        //关联关系
        List<CourseKnowledgeGraphNodeVo> relevanceKnowledgeGraphNodeList = new ArrayList<>();
        List<CourseKnowledgeGraphNodeRelationship> courseKnowledgeGraphNodeRelationshipList =
                neo4jUtil.getCourseKnowledgeGraphNodeRelationshipList(courseKnowledgeGraphDomainId, courseKnowledgeGraphNodeId);
        for (CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship : courseKnowledgeGraphNodeRelationshipList) {
            if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("FRONT_REAR")) {
                //前后置关系
                getCourseKnowledgeGraphNodeVoList(courseKnowledgeGraphNodeId, preposeKnowledgeGraphNodeList,
                        postposeKnowledgeGraphNodeList, courseKnowledgeGraphNodeRelationship);
            } else if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("RELEVANCE")) {
                //关联关系
                getCourseKnowledgeGraphNodeVoList(courseKnowledgeGraphNodeId, relevanceKnowledgeGraphNodeList,
                        relevanceKnowledgeGraphNodeList, courseKnowledgeGraphNodeRelationship);
            }
        }
        courseKnowledgeGraphNodeRelationshipResource.setPreposeKnowledgeGraphNodeList(preposeKnowledgeGraphNodeList.
                stream().sorted(Comparator.comparing(CourseKnowledgeGraphNodeVo::getRelationshipCreateTimestamp,
                        Comparator.nullsFirst(Comparator.naturalOrder())))
                .collect(Collectors.toList()));
        courseKnowledgeGraphNodeRelationshipResource.setPostposeKnowledgeGraphNodeList(postposeKnowledgeGraphNodeList.
                stream().sorted(Comparator.comparing(CourseKnowledgeGraphNodeVo::getRelationshipCreateTimestamp,
                        Comparator.nullsFirst(Comparator.naturalOrder())))
                .collect(Collectors.toList()));
        courseKnowledgeGraphNodeRelationshipResource.setRelevanceKnowledgeGraphNodeList(relevanceKnowledgeGraphNodeList.
                stream().sorted(Comparator.comparing(CourseKnowledgeGraphNodeVo::getRelationshipCreateTimestamp,
                        Comparator.nullsFirst(Comparator.naturalOrder())))
                .collect(Collectors.toList()));
        return courseKnowledgeGraphNodeRelationshipResource;
    }

    private void getCourseKnowledgeGraphNodeVoList(
            String courseKnowledgeGraphNodeId, List<CourseKnowledgeGraphNodeVo> preposeKnowledgeGraphNodeList,
            List<CourseKnowledgeGraphNodeVo> postposeKnowledgeGraphNodeList,
            CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship) {
        CourseKnowledgeGraphNodeVo courseKnowledgeGraphNodeVo = new CourseKnowledgeGraphNodeVo();
        if (courseKnowledgeGraphNodeRelationship.getEndNodeId().equals(courseKnowledgeGraphNodeId)) {
            courseKnowledgeGraphNodeVo.setKnowledgeGraphNodeId(courseKnowledgeGraphNodeRelationship.getStartNodeId());
            courseKnowledgeGraphNodeVo.setKnowledgeNodeName(courseKnowledgeGraphNodeRelationship.getStartNodeName());
            courseKnowledgeGraphNodeVo.setRelationshipId(courseKnowledgeGraphNodeRelationship.getRelationshipId());
            courseKnowledgeGraphNodeVo.setRelationshipCreateTimestamp(courseKnowledgeGraphNodeRelationship.getRelationshipCreateTimestamp());
            preposeKnowledgeGraphNodeList.add(courseKnowledgeGraphNodeVo);
        } else {
            courseKnowledgeGraphNodeVo.setKnowledgeGraphNodeId(courseKnowledgeGraphNodeRelationship.getEndNodeId());
            courseKnowledgeGraphNodeVo.setKnowledgeNodeName(courseKnowledgeGraphNodeRelationship.getEndNodeName());
            courseKnowledgeGraphNodeVo.setRelationshipId(courseKnowledgeGraphNodeRelationship.getRelationshipId());
            courseKnowledgeGraphNodeVo.setRelationshipCreateTimestamp(courseKnowledgeGraphNodeRelationship.getRelationshipCreateTimestamp());
            postposeKnowledgeGraphNodeList.add(courseKnowledgeGraphNodeVo);
        }
    }

    public void deleteCourseKnowledgeGraphNodeRelationship(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship,
            String accessUserId, String accessUserName, String ip) {
        neo4jUtil.deleteNodeRelationship(courseKnowledgeGraphDomain.getId(),
                String.valueOf(courseKnowledgeGraphNodeRelationship.getRelationshipId()));
        int nodeCounts = neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphDomain.setNodeCount(nodeCounts);
        updateCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain, accessUserId, accessUserName);
        courseKnowledgeGraphActionLogService.createDeleteCourseKnowledgeGraphNodeRelationshipActionLog(
                courseKnowledgeGraphDomain, courseKnowledgeGraphNode, courseKnowledgeGraphNodeRelationship,
                accessUserId, accessUserName, ip);
    }

    public void updateCourseKnowledgeGraphDomain(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                 String accessUserId, String accessUserName) {
        int relationShipCounts = neo4jUtil.getDomainRelationShipCount(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphDomain.setShipCount(relationShipCounts);
        courseKnowledgeGraphDomain.setModifyTime(new Date());
        courseKnowledgeGraphDomain.setModifierId(accessUserId);
        courseKnowledgeGraphDomain.setModifierName(accessUserName);
        courseKnowledgeGraphDomainRepository.saveAndFlush(courseKnowledgeGraphDomain);
    }

    public void createCourseKnowledgeGraphNodeRelationship(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeRelationshipParam courseKnowledgeGraphNodeRelationshipParam,
            String ip
    ) {
        String relationshipId = "";
        if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId())) {
            relationshipId = neo4jUtil.createFrontRearRelationship(courseKnowledgeGraphDomain.getId(),
                    courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId(),
                    courseKnowledgeGraphNode.getId());
        } else if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getPostKnowledgeGraphNodeId())) {
            relationshipId = neo4jUtil.createFrontRearRelationship(courseKnowledgeGraphDomain.getId(),
                    courseKnowledgeGraphNode.getId(),
                    courseKnowledgeGraphNodeRelationshipParam.getPostKnowledgeGraphNodeId());
        } else if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getRelevanceKnowledgeGraphNodeId())) {
            relationshipId = neo4jUtil.createRelevanceRelationship(courseKnowledgeGraphDomain.getId(),
                    courseKnowledgeGraphNode.getId(),
                    courseKnowledgeGraphNodeRelationshipParam.getRelevanceKnowledgeGraphNodeId());
        }
        int nodeCounts = neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphDomain.setNodeCount(nodeCounts);
        updateCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain, courseKnowledgeGraphNodeRelationshipParam.getAccessUserId(),
                courseKnowledgeGraphNodeRelationshipParam.getAccessUserName());
        courseKnowledgeGraphActionLogService.createAddCourseKnowledgeGraphNodeRelationshipActionLog(
                courseKnowledgeGraphDomain, courseKnowledgeGraphNode, courseKnowledgeGraphNodeRelationshipParam,
                relationshipId, ip);
    }

    public boolean checkParameter(CourseKnowledgeGraphNodeRelationshipParam courseKnowledgeGraphNodeRelationshipParam) {
        int i = 0;
        if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getPreKnowledgeGraphNodeId())) {
            i++;
        }
        if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getPostKnowledgeGraphNodeId())) {
            i++;
        }
        if (StringUtils.isNotBlank(courseKnowledgeGraphNodeRelationshipParam.getRelevanceKnowledgeGraphNodeId())) {
            i++;
        }
        return i == 1;
    }

    public List<CourseKnowledgeGraphNodeFileModel> getCourseKnowledgeGraphNodeFileList(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode courseKnowledgeGraphNode) {
        List<CourseKnowledgeGraphNodeFileModel> courseKnowledgeGraphNodeFileModelList = new ArrayList<>();
        List<CourseKnowledgeGraphNodeResource> courseKnowledgeGraphNodeResourceList =
                courseKnowledgeGraphNodeResourceRepository.findByCourseKnowledgeGraphDomainAndKnowledgeNodeIdAndGraphNodeResourceStatus(
                        courseKnowledgeGraphDomain, courseKnowledgeGraphNode.getId(), GraphNodeResourceStatus.NORMAL);
        List<String> resourceDetailIdList = courseKnowledgeGraphNodeResourceList.stream().map(CourseKnowledgeGraphNodeResource::getResourceDetailId)
                .filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List<CourseKnowledgeGraphNodeResourceFile> courseKnowledgeGraphNodeResourceFileList =
                courseKnowledgeGraphNodeResourceFileRepository.findByIdIn(resourceDetailIdList);

        for (CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource : courseKnowledgeGraphNodeResourceList) {
            CourseKnowledgeGraphNodeResourceFile courseKnowledgeGraphNodeResourceFile =
                    courseKnowledgeGraphNodeResourceFileList.stream().filter(courseKnowledgeGraphNodeResourceFile1 ->
                                    courseKnowledgeGraphNodeResourceFile1.getId().equals(courseKnowledgeGraphNodeResource.getResourceDetailId()))
                            .findFirst().orElse(null);
            CourseKnowledgeGraphNodeFileModel courseKnowledgeGraphNodeFileModel =
                    getCourseKnowledgeGraphNodeFileModel(courseKnowledgeGraphNodeResource, courseKnowledgeGraphNodeResourceFile);
            courseKnowledgeGraphNodeFileModelList.add(courseKnowledgeGraphNodeFileModel);
        }
        return courseKnowledgeGraphNodeFileModelList.stream().sorted(
                Comparator.comparingInt(CourseKnowledgeGraphNodeFileModel::getSort).reversed()).collect(Collectors.toList());
    }

    private static CourseKnowledgeGraphNodeFileModel getCourseKnowledgeGraphNodeFileModel(
            CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource,
            CourseKnowledgeGraphNodeResourceFile courseKnowledgeGraphNodeResourceFile) {
        CourseKnowledgeGraphNodeFileModel courseKnowledgeGraphNodeFileModel = new CourseKnowledgeGraphNodeFileModel();
        courseKnowledgeGraphNodeFileModel.setId(courseKnowledgeGraphNodeResource.getId());
        courseKnowledgeGraphNodeFileModel.setResourceName(courseKnowledgeGraphNodeResource.getResourceName());
        courseKnowledgeGraphNodeFileModel.setResourceTypeIndex(courseKnowledgeGraphNodeResource.getResourceType().getIndex());
        courseKnowledgeGraphNodeFileModel.setResourceTypeName(courseKnowledgeGraphNodeResource.getResourceType().getValue());
        courseKnowledgeGraphNodeFileModel.setSort(courseKnowledgeGraphNodeResource.getSort());
        courseKnowledgeGraphNodeFileModel.setInitialCreatorId(courseKnowledgeGraphNodeResource.getCreatorId());
        courseKnowledgeGraphNodeFileModel.setInitialCreatorName(courseKnowledgeGraphNodeResource.getCreatorName());
        if (ObjectUtils.isNotEmpty(courseKnowledgeGraphNodeResourceFile)) {
            courseKnowledgeGraphNodeFileModel.setTranscodeStatus(courseKnowledgeGraphNodeResourceFile.getTranscodeStatus().getValue());
            if (courseKnowledgeGraphNodeResourceFile.getTranscodeStatus().equals(TranscodeStatus.TRANSCODE_SUCCESS)) {
                courseKnowledgeGraphNodeFileModel.setFileRealName(courseKnowledgeGraphNodeResourceFile.getTranscodeFileSavedName());
                courseKnowledgeGraphNodeFileModel.setFileType(courseKnowledgeGraphNodeResourceFile.getTranscodeFileType());
                courseKnowledgeGraphNodeFileModel.setInnerUrl(courseKnowledgeGraphNodeResourceFile.getTranscodeInnerIp());
                courseKnowledgeGraphNodeFileModel.setOuterUrl(courseKnowledgeGraphNodeResourceFile.getTranscodeOuterIp());
                courseKnowledgeGraphNodeFileModel.setFilePath(courseKnowledgeGraphNodeResourceFile.getTranscodeFilePath());
                courseKnowledgeGraphNodeFileModel.setFileSize(String.valueOf(courseKnowledgeGraphNodeResourceFile.getTranscodeFileSize()));
            } else {
                courseKnowledgeGraphNodeFileModel.setFileRealName(courseKnowledgeGraphNodeResourceFile.getFileRealName());
                courseKnowledgeGraphNodeFileModel.setFileType(courseKnowledgeGraphNodeResourceFile.getFileType());
                courseKnowledgeGraphNodeFileModel.setInnerUrl(courseKnowledgeGraphNodeResourceFile.getInnerUrl());
                courseKnowledgeGraphNodeFileModel.setOuterUrl(courseKnowledgeGraphNodeResourceFile.getOuterUrl());
                courseKnowledgeGraphNodeFileModel.setFilePath(courseKnowledgeGraphNodeResourceFile.getFilePath());
                courseKnowledgeGraphNodeFileModel.setFileSize(String.valueOf(courseKnowledgeGraphNodeResourceFile.getFileSize()));
            }
        }
        return courseKnowledgeGraphNodeFileModel;
    }

    public void createCourseKnowledgeGraphNodeFileList(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                       CourseKnowledgeGraphNode courseKnowledgeGraphNode,
                                                       CourseKnowledgeGraphNodeFileParam courseKnowledgeGraphNodeFileParam,
                                                       String ip
    ) {
        List<CourseKnowledgeGraphNodeResourceFile> transcodeCourseKnowledgeGraphNodeResourceFiles = new ArrayList<>();
        int sort = courseKnowledgeGraphNodeResourceRepository.countByCourseKnowledgeGraphDomainAndKnowledgeNodeIdAndGraphNodeResourceStatus(
                courseKnowledgeGraphDomain, courseKnowledgeGraphNode.getId(), GraphNodeResourceStatus.NORMAL);
        List<CourseKnowledgeGraphNodeFileResource> courseKnowledgeGraphNodeFileResourceList =
                courseKnowledgeGraphNodeFileParam.getCourseKnowledgeGraphNodeFileResourceList();
        for (CourseKnowledgeGraphNodeFileResource courseKnowledgeGraphNodeFileResource : courseKnowledgeGraphNodeFileResourceList) {
            sort = sort + 1;
            CourseKnowledgeGraphNodeResourceFile courseKnowledgeGraphNodeResourceFile = new CourseKnowledgeGraphNodeResourceFile();
            courseKnowledgeGraphNodeResourceFile.setFileDisplayName(courseKnowledgeGraphNodeFileResource.getResourceName());
            courseKnowledgeGraphNodeResourceFile.setFileRealName(courseKnowledgeGraphNodeFileResource.getFileRealName());
            courseKnowledgeGraphNodeResourceFile.setFileType(courseKnowledgeGraphNodeFileResource.getFileType());
            courseKnowledgeGraphNodeResourceFile.setInnerUrl(courseKnowledgeGraphNodeFileResource.getInnerUrl());
            courseKnowledgeGraphNodeResourceFile.setOuterUrl(courseKnowledgeGraphNodeFileResource.getOuterUrl());
            courseKnowledgeGraphNodeResourceFile.setFilePath(courseKnowledgeGraphNodeFileResource.getFilePath());
            courseKnowledgeGraphNodeResourceFile.setFileSize(Long.valueOf(courseKnowledgeGraphNodeFileResource.getFileSize()));
            courseKnowledgeGraphNodeResourceFile.setCreatorId(courseKnowledgeGraphNodeFileParam.getAccessUserId());
            courseKnowledgeGraphNodeResourceFile.setCreatorName(courseKnowledgeGraphNodeFileParam.getAccessUserName());
            courseKnowledgeGraphNodeResourceFile.setCreateTime(new Date());
            Boolean isTransCodeVideoType = transcodeService.isTransCodeVideoType(courseKnowledgeGraphNodeFileResource.getFileType());
            if (isTransCodeVideoType) {
                courseKnowledgeGraphNodeResourceFile.setTranscodeStatus(TranscodeStatus.TRANS_CODING);
            } else {
                courseKnowledgeGraphNodeResourceFile.setTranscodeStatus(TranscodeStatus.NOT_TRANSCODE);
            }
            courseKnowledgeGraphNodeResourceFile = courseKnowledgeGraphNodeResourceFileRepository.saveAndFlush(courseKnowledgeGraphNodeResourceFile);
            if (isTransCodeVideoType) {
                transcodeCourseKnowledgeGraphNodeResourceFiles.add(courseKnowledgeGraphNodeResourceFile);
            }
            CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource = new CourseKnowledgeGraphNodeResource();
            courseKnowledgeGraphNodeResource.setCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
            courseKnowledgeGraphNodeResource.setKnowledgeNodeId(courseKnowledgeGraphNode.getId());
            courseKnowledgeGraphNodeResource.setGraphNodeResourceStatus(GraphNodeResourceStatus.NORMAL);
            courseKnowledgeGraphNodeResource.setResourceType(GraphNodeResourceType.FILE);
            courseKnowledgeGraphNodeResource.setResourceDetailId(courseKnowledgeGraphNodeResourceFile.getId());
            courseKnowledgeGraphNodeResource.setResourceName(courseKnowledgeGraphNodeFileResource.getResourceName());
            courseKnowledgeGraphNodeResource.setResourceSize(Long.valueOf(courseKnowledgeGraphNodeFileResource.getFileSize()));
            courseKnowledgeGraphNodeResource.setResourceContentNum(1);
            courseKnowledgeGraphNodeResource.setSort(sort);
            courseKnowledgeGraphNodeResource.setCreatorId(courseKnowledgeGraphNodeFileParam.getAccessUserId());
            courseKnowledgeGraphNodeResource.setCreatorName(courseKnowledgeGraphNodeFileParam.getAccessUserName());
            courseKnowledgeGraphNodeResource.setCreateTime(new Date());
            courseKnowledgeGraphNodeResource = courseKnowledgeGraphNodeResourceRepository.saveAndFlush(courseKnowledgeGraphNodeResource);
            courseKnowledgeGraphActionLogService.createAddCourseKnowledgeGraphNodeResourceActionLog(
                    courseKnowledgeGraphNode, courseKnowledgeGraphDomain, courseKnowledgeGraphNodeResource, ip);
        }
        if (!transcodeCourseKnowledgeGraphNodeResourceFiles.isEmpty()) {
            transcodeService.sendKnowledgeGraphTranscodeMqRequestMessage(transcodeCourseKnowledgeGraphNodeResourceFiles);
        }

    }

    public CourseKnowledgeGraphNodeResource getCourseKnowledgeGraphNodeResource(String nodeFileId) {
        return courseKnowledgeGraphNodeResourceRepository.findById(nodeFileId).orElse(null);
    }

    @Transactional
    public synchronized void deleteCourseKnowledgeGraphNodeFile(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource,
            String accessUserId, String accessUserName, String ip) {
        courseKnowledgeGraphNodeResource.setGraphNodeResourceStatus(GraphNodeResourceStatus.DELETE);
        courseKnowledgeGraphNodeResource.setModifierId(accessUserId);
        courseKnowledgeGraphNodeResource.setModifierName(accessUserName);
        courseKnowledgeGraphNodeResource.setModifyTime(new Date());
        courseKnowledgeGraphNodeResourceRepository.saveAndFlush(courseKnowledgeGraphNodeResource);
        courseKnowledgeGraphNodeResourceRepository.updateByKnowledgeGraphNodeIdAndFileSort(
                courseKnowledgeGraphNode.getId(), courseKnowledgeGraphNodeResource.getSort());
        courseKnowledgeGraphActionLogService.createDeleteCourseKnowledgeGraphNodeResourceActionLog(
                courseKnowledgeGraphDomain, courseKnowledgeGraphNode,
                courseKnowledgeGraphNodeResource, accessUserId, accessUserName, ip);
    }


    public ErrorResult reorderCourseKnowledgeGraphNodeResource(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            NodeFileReorderParam nodeFileReorderParam) {
        String needToBeMovedNodeFileId = nodeFileReorderParam.getNeedToBeMovedNodeFileId();
        CourseKnowledgeGraphNodeResource needToBeMovedNodeResource =
                getCourseKnowledgeGraphNodeResource(needToBeMovedNodeFileId);
        if (ObjectUtils.isEmpty(needToBeMovedNodeResource)) {
            return ErrorResult.customError(needToBeMovedNodeFileId + "学习材料不存在");
        }
        Integer currentNodeFileSort = needToBeMovedNodeResource.getSort();
        // 判断之前位置的ID是否为空
        if (ObjectUtils.isEmpty(nodeFileReorderParam.getTargetLocationBeforeNodeFileId())) {
            int maxIndex = courseKnowledgeGraphNodeResourceRepository.
                    countByCourseKnowledgeGraphDomainAndKnowledgeNodeIdAndGraphNodeResourceStatus(
                            courseKnowledgeGraphDomain, courseKnowledgeGraphNode.getId(), GraphNodeResourceStatus.NORMAL
                    );
            courseKnowledgeGraphNodeResourceRepository.forwardByDomainIdAndNodeIdWithIndexBetween(
                    courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId(),
                    currentNodeFileSort, maxIndex + 1);
            needToBeMovedNodeResource.setSort(maxIndex);
        } else {
            CourseKnowledgeGraphNodeResource targetLocationBeforeNodeResource =
                    getCourseKnowledgeGraphNodeResource(nodeFileReorderParam.getTargetLocationBeforeNodeFileId());
            if (ObjectUtils.isEmpty(targetLocationBeforeNodeResource)) {
                return ErrorResult.customError(nodeFileReorderParam.getTargetLocationBeforeNodeFileId() + "学习材料不存在");
            }
            Integer targetLocationBeforeSort = targetLocationBeforeNodeResource.getSort();
            int directionOfMovement = targetLocationBeforeSort - currentNodeFileSort;
            if (directionOfMovement < 0) {
                // 后移一位
                if (directionOfMovement == -1) {
                    int tmpLocation = targetLocationBeforeSort;
                    targetLocationBeforeNodeResource.setSort(currentNodeFileSort);
                    courseKnowledgeGraphNodeResourceRepository.save(targetLocationBeforeNodeResource);
                    needToBeMovedNodeResource.setSort(tmpLocation);
                } else {
                    // 后移
                    courseKnowledgeGraphNodeResourceRepository.backwardByDomainIdAndNodeIdWithIndexBetween(
                            courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId(),
                            targetLocationBeforeSort - 1, currentNodeFileSort
                    );
                    needToBeMovedNodeResource.setSort(targetLocationBeforeSort);
                }
            } else {
                // 前移一位
                if (directionOfMovement == TWO) {
                    int tmpLocation = targetLocationBeforeSort;
                    CourseKnowledgeGraphNodeResource nextCourseKnowledgeGraphNodeResource =
                            getNextCourseKnowledgeGraphNodeResource(needToBeMovedNodeResource);
                    nextCourseKnowledgeGraphNodeResource.setSort(currentNodeFileSort);
                    courseKnowledgeGraphNodeResourceRepository.save(nextCourseKnowledgeGraphNodeResource);
                    needToBeMovedNodeResource.setSort(tmpLocation - 1);
                } else {
                    // 前移
                    courseKnowledgeGraphNodeResourceRepository.forwardByDomainIdAndNodeIdWithIndexBetween(
                            courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId(),
                            currentNodeFileSort, targetLocationBeforeSort
                    );
                    needToBeMovedNodeResource.setSort(targetLocationBeforeSort - 1);
                }
            }
        }
        courseKnowledgeGraphNodeResourceRepository.save(needToBeMovedNodeResource);
        return null;
    }

    private CourseKnowledgeGraphNodeResource getNextCourseKnowledgeGraphNodeResource(
            CourseKnowledgeGraphNodeResource needToBeMovedNodeResource) {
        return courseKnowledgeGraphNodeResourceRepository.
                findByCourseKnowledgeGraphDomainAndKnowledgeNodeIdAndGraphNodeResourceStatusAndSort(
                        needToBeMovedNodeResource.getCourseKnowledgeGraphDomain(), needToBeMovedNodeResource.getKnowledgeNodeId(),
                        GraphNodeResourceStatus.NORMAL, needToBeMovedNodeResource.getSort() + 1);
    }

    public CourseKnowledgeGraphClassifyResource getCourseKnowledgeGraphClassifyResource(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        CourseKnowledgeGraphClassifyResource courseKnowledgeGraphClassifyResource = new CourseKnowledgeGraphClassifyResource();
        /**获取图谱所有节点**/
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList =
                neo4jUtil.getCourseKnowledgeGraphDomainAllNodeList(courseKnowledgeGraphDomain.getId());
        /**获取图谱所有关系**/
        List<CourseKnowledgeGraphNodeRelationship> courseKnowledgeGraphNodeRelationshipList =
                neo4jUtil.getCourseKnowledgeGraphDomainAllRelationshipList(courseKnowledgeGraphDomain.getId());
        List<CourseKnowledgeGraphNodeBaseModel> courseKnowledgeGraphNodeBaseModelList = new ArrayList<>();
        for (CourseKnowledgeGraphNode courseKnowledgeGraphNode : courseKnowledgeGraphNodeList) {
            CourseKnowledgeGraphNodeBaseModel courseKnowledgeGraphNodeBaseModel = new CourseKnowledgeGraphNodeBaseModel();
            courseKnowledgeGraphNodeBaseModel.setId(courseKnowledgeGraphNode.getId());
            courseKnowledgeGraphNodeBaseModel.setKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
            KnowledgeNodeLevel knowledgeNodeLevel =
                    KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
            if (ObjectUtils.isNotEmpty(knowledgeNodeLevel)) {
                courseKnowledgeGraphNodeBaseModel.setKnowledgeNodeLevelIndex(knowledgeNodeLevel.getIndex());
                courseKnowledgeGraphNodeBaseModel.setKnowledgeNodeLevelName(knowledgeNodeLevel.getName());
            }
            courseKnowledgeGraphNodeBaseModel.setCourseKnowledgeGraphId(courseKnowledgeGraphDomain.getId());
            courseKnowledgeGraphNodeBaseModel.setSort(courseKnowledgeGraphNode.getSort());
            courseKnowledgeGraphNodeBaseModelList.add(courseKnowledgeGraphNodeBaseModel);
        }
        courseKnowledgeGraphClassifyResource.setCourseKnowledgeGraphNodeList(
                courseKnowledgeGraphNodeBaseModelList.stream().sorted(
                        Comparator.comparing(CourseKnowledgeGraphNodeBaseModel::getKnowledgeNodeLevelIndex,
                                        Comparator.nullsLast(Integer::compareTo))
                                .thenComparing(CourseKnowledgeGraphNodeBaseModel::getSort, Comparator.naturalOrder())
                ).collect(Collectors.toList()));
        List<CourseKnowledgeGraphRelationshipModel> parentChildKnowledgeGraphRelationshipList = new ArrayList<>();

        List<CourseKnowledgeGraphRelationshipModel> frontRearKnowledgeGraphRelationshipList = new ArrayList<>();

        List<CourseKnowledgeGraphRelationshipModel> relevanceKnowledgeGraphRelationshipList = new ArrayList<>();
        for (CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship : courseKnowledgeGraphNodeRelationshipList) {
            CourseKnowledgeGraphRelationshipModel courseKnowledgeGraphRelationshipModel =
                    getCourseKnowledgeGraphRelationshipModel(courseKnowledgeGraphNodeRelationship, courseKnowledgeGraphDomain);
            if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("PARENT_CHILD")) {
                parentChildKnowledgeGraphRelationshipList.add(courseKnowledgeGraphRelationshipModel);
            } else if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("FRONT_REAR")) {
                frontRearKnowledgeGraphRelationshipList.add(courseKnowledgeGraphRelationshipModel);
            } else if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("RELEVANCE")) {
                relevanceKnowledgeGraphRelationshipList.add(courseKnowledgeGraphRelationshipModel);
            }
        }
        courseKnowledgeGraphClassifyResource.setParentChildKnowledgeGraphRelationshipList(
                parentChildKnowledgeGraphRelationshipList.stream().sorted(Comparator.comparingInt(
                                CourseKnowledgeGraphRelationshipModel::getRelationshipId))
                        .collect(Collectors.toList()));
        courseKnowledgeGraphClassifyResource.setFrontRearKnowledgeGraphRelationshipList(
                frontRearKnowledgeGraphRelationshipList.stream().sorted(Comparator.comparingInt(
                                CourseKnowledgeGraphRelationshipModel::getRelationshipId))
                        .collect(Collectors.toList()));
        courseKnowledgeGraphClassifyResource.setRelevanceKnowledgeGraphRelationshipList(
                relevanceKnowledgeGraphRelationshipList.stream().sorted(Comparator.comparingInt(
                                CourseKnowledgeGraphRelationshipModel::getRelationshipId))
                        .collect(Collectors.toList()));
        return courseKnowledgeGraphClassifyResource;
    }

    private CourseKnowledgeGraphRelationshipModel getCourseKnowledgeGraphRelationshipModel(
            CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship,
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        CourseKnowledgeGraphRelationshipModel courseKnowledgeGraphRelationshipModel =
                new CourseKnowledgeGraphRelationshipModel();
        courseKnowledgeGraphRelationshipModel.setStartNodeId(courseKnowledgeGraphNodeRelationship.getStartNodeId());
        courseKnowledgeGraphRelationshipModel.setEndNodeId(courseKnowledgeGraphNodeRelationship.getEndNodeId());
        courseKnowledgeGraphRelationshipModel.setRelationshipId(courseKnowledgeGraphNodeRelationship.getRelationshipId());
        courseKnowledgeGraphRelationshipModel.setCourseKnowledgeGraphId(courseKnowledgeGraphDomain.getId());
        return courseKnowledgeGraphRelationshipModel;
    }


    public CourseKnowledgeGraphDomain createCourseKnowledgeGraphDomain(
            Course course, CourseVersion courseVersion, String accessUserId, String accessUserName) {
        updateOldCourseKnowledgeGraphDomainList(course.getId(), courseVersion.getId(), accessUserId, accessUserName);
        List<CourseKnowledgeGraphDomain> courseKnowledgeGraphDomainList =
                courseKnowledgeGraphDomainRepository.findByCourseAndCourseVersionOrderByShowOrderAsc(course, courseVersion);
        CourseKnowledgeGraphDomain createCourseKnowledgeGraphDomain = new CourseKnowledgeGraphDomain();
        createCourseKnowledgeGraphDomain.setCourse(course);
        createCourseKnowledgeGraphDomain.setCourseVersion(courseVersion);
        createCourseKnowledgeGraphDomain.setCourseCode(course.getCourseCode());
        createCourseKnowledgeGraphDomain.setCourseName(course.getCourseName());
        createCourseKnowledgeGraphDomain.setStatus(true);
        createCourseKnowledgeGraphDomain.setNodeCount(1);
        createCourseKnowledgeGraphDomain.setShipCount(0);
        createCourseKnowledgeGraphDomain.setCreatorId(accessUserId);
        createCourseKnowledgeGraphDomain.setCreatorName(accessUserName);
        createCourseKnowledgeGraphDomain.setCreateTime(new Date());
        createCourseKnowledgeGraphDomain.setShowOrder(courseKnowledgeGraphDomainList.size() + 1);
        String versionLabel = "V1." + courseKnowledgeGraphDomainList.size();
        createCourseKnowledgeGraphDomain.setVersionLabel(versionLabel);
        return createCourseKnowledgeGraphDomain;
    }

    private void updateOldCourseKnowledgeGraphDomainList(
            String courseId, String courseVersionId, String accessUserId, String accessUserName) {
        List<CourseKnowledgeGraphDomain> courseKnowledgeGraphDomainList =
                courseKnowledgeGraphDomainRepository
                        .getCourseKnowledgeGraphDomainByCourseIdAndCourseVersionId(courseId, courseVersionId);
        for (CourseKnowledgeGraphDomain courseKnowledgeGraphDomain : courseKnowledgeGraphDomainList) {
            courseKnowledgeGraphDomain.setStatus(false);
            courseKnowledgeGraphDomain.setModifierId(accessUserId);
            courseKnowledgeGraphDomain.setModifierName(accessUserName);
            courseKnowledgeGraphDomain.setModifyTime(new Date());
        }
        courseKnowledgeGraphDomainRepository.saveAllAndFlush(courseKnowledgeGraphDomainList);
        if (dataVisualEnable) {
            for (CourseKnowledgeGraphDomain courseKnowledgeGraphDomain : courseKnowledgeGraphDomainList) {
                /*kafka知识图谱数据推送*/
                KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo = getKnowledgeGraphDomainTopicVo(courseKnowledgeGraphDomain);
                eventService.sendKnowledgeGraphDomainTopicVo(knowledgeGraphDomainTopicVo);

            }
        }
    }

    public void importKnowledgeStructure() {
        List<CourseKnowledgeStructure> courseKnowledgeStructureList = courseKnowledgeStructureRepository.findAllDataList();
        List<CourseKnowledgeGraphDomain> courseKnowledgeGraphDomainList = courseKnowledgeGraphDomainRepository.getAllEffectiveDataList();
        Map<Course, List<CourseKnowledgeStructure>> courseKnowledgeStructureListMap =
                courseKnowledgeStructureList.stream()
                        .collect(Collectors.groupingBy(CourseKnowledgeStructure::getCourse));
        courseKnowledgeStructureListMap.forEach((course, groupCourseKnowledgeStructureList) -> {
            boolean existKnowledgeGraph;
            LOGGER.info("课程->{}[{}]历史课程知识结构开始并生成更新知识图谱", course.getCourseName(), course.getCourseCode());
            CourseVersion courseVersion = groupCourseKnowledgeStructureList.get(0).getCourseVersion();
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain;
            CourseKnowledgeGraphNode rootCourseKnowledgeGraphNode;
            if (courseKnowledgeGraphDomainList.stream().noneMatch(courseKnowledgeGraphDomain1 ->
                    courseKnowledgeGraphDomain1.getCourseVersion().getId().equals(courseVersion.getId()))) {
                CourseKnowledgeGraphDomain createCourseKnowledgeGraphDomain =
                        createCourseKnowledgeGraphDomain(course, courseVersion, "1", "系统管理员");
                courseKnowledgeGraphDomain =
                        courseKnowledgeGraphDomainRepository.saveAndFlush(createCourseKnowledgeGraphDomain);
                /*kafka知识图谱数据推送*/
                if (dataVisualEnable) {
                    KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo = getKnowledgeGraphDomainTopicVo(courseKnowledgeGraphDomain);
                    eventService.sendKnowledgeGraphDomainTopicVo(knowledgeGraphDomainTopicVo);
                }
                rootCourseKnowledgeGraphNode = createRootCourseKnowledgeGraphNode(createCourseKnowledgeGraphDomain);
                neo4jUtil.createRootCourseKnowledgeGraphNode(rootCourseKnowledgeGraphNode, createCourseKnowledgeGraphDomain.getId());
                existKnowledgeGraph = false;
            } else {
                courseKnowledgeGraphDomain = courseKnowledgeGraphDomainList.stream().filter(courseKnowledgeGraphDomain1 ->
                                courseKnowledgeGraphDomain1.getCourseVersion().getId().equals(courseVersion.getId()))
                        .findFirst().orElse(null);
                rootCourseKnowledgeGraphNode = neo4jUtil.getRootCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId());
                existKnowledgeGraph = true;
            }
            List<Map<String, Object>> courseKnowledgeGraphNodeMapList =
                    composeCourseKnowledgeGraphNodeList(rootCourseKnowledgeGraphNode, groupCourseKnowledgeStructureList);
            courseKnowledgeGraphNodeMapList.sort((o1, o2) -> {
                Integer str1 = Integer.valueOf(o1.get("knowledgeNodeLevel").toString());
                Integer str2 = Integer.valueOf(o2.get("knowledgeNodeLevel").toString());
                //str1在前，str2在后，默认升序，这里Integer类型的也可以
                return str1.compareTo(str2);
            });
            courseKnowledgeGraphNodeMapList.forEach(stringObjectMap -> {
                String parentCourseKnowledgeGraphNodeId = stringObjectMap.get("parentCourseKnowledgeGraphNodeId").toString();
                CourseKnowledgeGraphNode newCourseKnowledgeGraphNode = (CourseKnowledgeGraphNode) stringObjectMap.get("courseKnowledgeGraphNode");
                CourseKnowledgeGraphNode neo4jCourseKnowledgeGraphNode =
                        getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(), newCourseKnowledgeGraphNode.getId());
                if (existKnowledgeGraph) {
                    if (ObjectUtils.isNotEmpty(neo4jCourseKnowledgeGraphNode) &&
                            (!Objects.equals(newCourseKnowledgeGraphNode.getCreatorId(), neo4jCourseKnowledgeGraphNode.getCreatorId())
                                    || !Objects.equals(newCourseKnowledgeGraphNode.getCreateTime(), neo4jCourseKnowledgeGraphNode.getCreateTime())
                            )) {
                        String cypherSql = String.format("MATCH (n:`%s`{id:'%s'}) set " +
                                        "n.creatorId=$creatorId,n.creatorName=$creatorName,n.createTime=$createTime  return n ",
                                courseKnowledgeGraphDomain.getId(), newCourseKnowledgeGraphNode.getId());
                        Value value = parameters("creatorId", newCourseKnowledgeGraphNode.getCreatorId(),
                                "creatorName", newCourseKnowledgeGraphNode.getCreatorName(),
                                "createTime", newCourseKnowledgeGraphNode.getCreateTime());
                        neo4jUtil.updateCourseKnowledgeGraphNode(cypherSql, value);
                    }
                } else {
                    neo4jUtil.createChildCourseKnowledgeGraphNode(
                            courseKnowledgeGraphDomain.getId(), parentCourseKnowledgeGraphNodeId, newCourseKnowledgeGraphNode);
                }
            });
        });
        //批量更新知识图谱中的总节点个数和关系个数
        List<CourseKnowledgeGraphDomain> systemCourseKnowledgeGraphDomainList =
                courseKnowledgeGraphDomainRepository.findAll();
        for (CourseKnowledgeGraphDomain courseKnowledgeGraphDomain : systemCourseKnowledgeGraphDomainList) {
            //更新节点数量和关系数量
            int nodeCounts = neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId());
            courseKnowledgeGraphDomain.setNodeCount(nodeCounts);
            updateCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain,
                    courseKnowledgeGraphDomain.getCreatorId(), courseKnowledgeGraphDomain.getCreatorName());
        }
    }

    private List<Map<String, Object>> composeCourseKnowledgeGraphNodeList(
            CourseKnowledgeGraphNode rootCourseKnowledgeGraphNode, List<CourseKnowledgeStructure> groupCourseKnowledgeStructureList) {
        groupCourseKnowledgeStructureList.forEach(courseKnowledgeStructure -> {
            if (ObjectUtils.isEmpty(courseKnowledgeStructure.getParentId())) {
                courseKnowledgeStructure.setParentId(rootCourseKnowledgeGraphNode.getId());
            } else {
                List<CourseKnowledgeStructure> courseKnowledgeStructureList = groupCourseKnowledgeStructureList
                        .stream().filter(gorupCourseKnowledgeStructure ->
                                gorupCourseKnowledgeStructure.getId().equals(courseKnowledgeStructure.getParentId()))
                        .collect(Collectors.toList());
                if (CollectionUtils.isEmpty(courseKnowledgeStructureList)) {
                    courseKnowledgeStructure.setParentId(rootCourseKnowledgeGraphNode.getId());
                }
            }
        });
        List<Map<String, Object>> courseKnowledgeGraphNodeMapList = new ArrayList<>();
        Map<String, List<CourseKnowledgeStructure>> courseKnowledgeStructureListMap =
                groupCourseKnowledgeStructureList.stream()
                        .collect(Collectors.groupingBy(courseKnowledgeStructure ->
                                courseKnowledgeStructure.getKnowledgeStructureType().getValue() + "_" + courseKnowledgeStructure.getParentId()));
        courseKnowledgeStructureListMap.forEach((key, groupTypeCourseKnowledgeStructureList) -> {
            List<CourseKnowledgeStructure> sortCourseKnowledgeStructureList =
                    groupTypeCourseKnowledgeStructureList.stream().sorted(Comparator.comparing(CourseKnowledgeStructure::getShowOrder,
                            Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
            int i = 1;
            for (CourseKnowledgeStructure groupTypeCourseKnowledgeStructure : sortCourseKnowledgeStructureList) {
                CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
                courseKnowledgeGraphNode.setId(groupTypeCourseKnowledgeStructure.getId());
                if (groupTypeCourseKnowledgeStructure.getKnowledgeStructureType().equals(KnowledgeStructureType.KNOWLEDGE_POINT)) {
                    String parentId = groupTypeCourseKnowledgeStructure.getParentId();
                    CourseKnowledgeStructure parentCourseKnowledgeStructure = groupCourseKnowledgeStructureList.stream()
                            .filter(courseKnowledgeStructure -> courseKnowledgeStructure.getId().equals(parentId))
                            .findFirst().orElse(null);
                    courseKnowledgeGraphNode.setKnowledgeNodeLevel(
                            parentCourseKnowledgeStructure.getKnowledgeStructureType().getValue() + TWO);
                } else {
                    courseKnowledgeGraphNode.setKnowledgeNodeLevel(
                            groupTypeCourseKnowledgeStructure.getKnowledgeStructureType().getValue() + 1);
                }
                courseKnowledgeGraphNode.setKnowledgeNodeName(groupTypeCourseKnowledgeStructure.getContent());
                courseKnowledgeGraphNode.setName(groupTypeCourseKnowledgeStructure.getContent());
                courseKnowledgeGraphNode.setSort(i);
                courseKnowledgeGraphNode.setCreatorId(groupTypeCourseKnowledgeStructure.getCreatorId());
                courseKnowledgeGraphNode.setCreatorName(groupTypeCourseKnowledgeStructure.getCreatorName());
                String createTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", groupTypeCourseKnowledgeStructure.getCreateTime());
                courseKnowledgeGraphNode.setCreateTime(createTime);
                Map<String, Object> map = new HashMap<>();
                map.put("courseKnowledgeGraphNode", courseKnowledgeGraphNode);
                map.put("knowledgeNodeLevel", courseKnowledgeGraphNode.getKnowledgeNodeLevel());
                if (ObjectUtils.isEmpty(groupTypeCourseKnowledgeStructure.getParentId())) {
                    map.put("parentCourseKnowledgeGraphNodeId", rootCourseKnowledgeGraphNode.getId());
                } else {
                    map.put("parentCourseKnowledgeGraphNodeId", groupTypeCourseKnowledgeStructure.getParentId());
                }
                courseKnowledgeGraphNodeMapList.add(map);
                i++;
            }
        });
        return courseKnowledgeGraphNodeMapList;
    }

    public ErrorResult courseKnowledgeGraphSort(
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel,
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            String accessUserId, String accessUserName) {
        //节点列表
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        //父子级别关系列表
        List<CourseKnowledgeGraphRelationshipVo> courseKnowledgeGraphRelationshipVoList = new ArrayList<>();
        /**根据图谱id获取对应节点列表**/
        List<CourseKnowledgeGraphNode> allCourseKnowledgeGraphNodeList =
                neo4jUtil.getCourseKnowledgeGraphDomainAllNodeList(courseKnowledgeGraphDomain.getId());
        /**
         * 树状图内容填充
         */
        fillCourseKnowledgeGraphNodeTreeContent(courseKnowledgeGraphNodeTreeModel, allCourseKnowledgeGraphNodeList, 0, 1);
        /**
         * 树状图数据转列表
         */
        organizeCourseKnowledgeGraphNodeList(
                courseKnowledgeGraphNodeTreeModel, courseKnowledgeGraphNodeList,
                courseKnowledgeGraphRelationshipVoList, null);

        if (courseKnowledgeGraphRelationshipVoList.stream().anyMatch(courseKnowledgeGraphRelationshipVo ->
                ObjectUtils.isEmpty(courseKnowledgeGraphRelationshipVo.getEndNodeName()))) {
            return ErrorResult.customError("已有其他教师新增或删除知识点，当前设置排序失效，请稍后重试");
        }
        int nodeCount = neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId());
        if (nodeCount > courseKnowledgeGraphNodeList.size()) {
            return ErrorResult.customError("已有其他教师新增或删除知识点，当前设置排序失效，请稍后重试");
        }
        //判断相关
//        ErrorResult errorResult = checkCourseKnowledgeGraphNodeName(courseKnowledgeGraphRelationshipVoList);
//        if (ObjectUtils.isNotEmpty(errorResult)) {
//            return errorResult;
//        }
        String modifyTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date());
        //修改所有节点相关参数
        for (CourseKnowledgeGraphNode courseKnowledgeGraphNode : courseKnowledgeGraphNodeList) {
            String cypherSql = String.format("MATCH (n:`%s`{id:'%s'}) set n.knowledgeNodeLevel=$knowledgeNodeLevel," +
                            "n.sort=$sort,n.modifierId=$modifierId,n.modifierName=$modifierName,n.modifyTime=$modifyTime return n ",
                    courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            Value value = parameters("knowledgeNodeLevel", courseKnowledgeGraphNode.getKnowledgeNodeLevel(),
                    "sort", courseKnowledgeGraphNode.getSort(),
                    "modifierId", accessUserId, "modifierName", accessUserName, "modifyTime", modifyTime);
            neo4jUtil.updateCourseKnowledgeGraphNode(cypherSql, value);
        }
        // 删除图谱所有父子关系
        neo4jUtil.deleteCourseKnowledgeGraphDomainParentChildRelationship(courseKnowledgeGraphDomain);
        // 创建图谱所有父子关系
        for (CourseKnowledgeGraphRelationshipVo courseKnowledgeGraphRelationshipVo : courseKnowledgeGraphRelationshipVoList) {
            neo4jUtil.createParentChildRelationship(courseKnowledgeGraphDomain.getId(),
                    courseKnowledgeGraphRelationshipVo.getStartNodeId(),
                    courseKnowledgeGraphRelationshipVo.getEndNodeId());
        }
        return null;
    }

    private void fillCourseKnowledgeGraphNodeTreeContent(
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel,
            List<CourseKnowledgeGraphNode> allCourseKnowledgeGraphNodeList, int knowledgeNodeLevelIndex, int sort) {
        CourseKnowledgeGraphNode oneCourseKnowledgeGraphNode =
                allCourseKnowledgeGraphNodeList.stream().filter(courseKnowledgeGraphNode ->
                        courseKnowledgeGraphNode.getId().equals(courseKnowledgeGraphNodeTreeModel.getId())).findFirst().orElse(null);
        if (ObjectUtils.isNotEmpty(oneCourseKnowledgeGraphNode)) {
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeName(oneCourseKnowledgeGraphNode.getKnowledgeNodeName());
        } else {
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeName(null);
        }
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelIndex(KnowledgeNodeLevel.getKnowledgeNodeLevel(knowledgeNodeLevelIndex).getIndex());
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelName(KnowledgeNodeLevel.getKnowledgeNodeLevel(knowledgeNodeLevelIndex).getName());
        courseKnowledgeGraphNodeTreeModel.setSort(sort);
        if (!CollectionUtils.isEmpty(courseKnowledgeGraphNodeTreeModel.getSubsetCourseKnowledgeGraphNodeTreeList())) {
            List<CourseKnowledgeGraphNodeTreeModel> subsetCourseKnowledgeGraphNodeTreeList =
                    courseKnowledgeGraphNodeTreeModel.getSubsetCourseKnowledgeGraphNodeTreeList();
            for (int i = 0; i < subsetCourseKnowledgeGraphNodeTreeList.size(); i++) {
                fillCourseKnowledgeGraphNodeTreeContent(subsetCourseKnowledgeGraphNodeTreeList.get(i),
                        allCourseKnowledgeGraphNodeList,
                        knowledgeNodeLevelIndex + 1, i + 1);
            }
        }
    }


    private void organizeCourseKnowledgeGraphNodeList(
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel,
            List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList,
            List<CourseKnowledgeGraphRelationshipVo> courseKnowledgeGraphRelationshipVoList, String parentCourseKnowledgeGraphNodeId) {
        CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
        courseKnowledgeGraphNode.setId(courseKnowledgeGraphNodeTreeModel.getId());
        courseKnowledgeGraphNode.setKnowledgeNodeName(courseKnowledgeGraphNodeTreeModel.getKnowledgeNodeName());
        courseKnowledgeGraphNode.setKnowledgeNodeLevel(courseKnowledgeGraphNodeTreeModel.getKnowledgeNodeLevelIndex());
        courseKnowledgeGraphNode.setSort(courseKnowledgeGraphNodeTreeModel.getSort());
        courseKnowledgeGraphNodeList.add(courseKnowledgeGraphNode);
        if (StringUtils.isNotBlank(parentCourseKnowledgeGraphNodeId)) {
            CourseKnowledgeGraphRelationshipVo courseKnowledgeGraphRelationshipVo =
                    CourseKnowledgeGraphRelationshipVo.builder().startNodeId(parentCourseKnowledgeGraphNodeId)
                            .endNodeId(courseKnowledgeGraphNodeTreeModel.getId())
                            .endNodeName(courseKnowledgeGraphNodeTreeModel.getKnowledgeNodeName()).build();
            courseKnowledgeGraphRelationshipVoList.add(courseKnowledgeGraphRelationshipVo);
        }
        if (!CollectionUtils.isEmpty(courseKnowledgeGraphNodeTreeModel.getSubsetCourseKnowledgeGraphNodeTreeList())) {
            List<CourseKnowledgeGraphNodeTreeModel> subsetCourseKnowledgeGraphNodeTreeList =
                    courseKnowledgeGraphNodeTreeModel.getSubsetCourseKnowledgeGraphNodeTreeList();
            for (CourseKnowledgeGraphNodeTreeModel childCourseKnowledgeGraphNodeTreeModel :
                    subsetCourseKnowledgeGraphNodeTreeList) {
                organizeCourseKnowledgeGraphNodeList(childCourseKnowledgeGraphNodeTreeModel,
                        courseKnowledgeGraphNodeList,
                        courseKnowledgeGraphRelationshipVoList, courseKnowledgeGraphNodeTreeModel.getId());
            }
        }
    }

    private ErrorResult checkCourseKnowledgeGraphNodeName(List<CourseKnowledgeGraphRelationshipVo> courseKnowledgeGraphRelationshipVoList) {
        Map<String, List<CourseKnowledgeGraphRelationshipVo>> courseKnowledgeGraphRelationshipVoListMap =
                courseKnowledgeGraphRelationshipVoList.stream().collect(Collectors.groupingBy(CourseKnowledgeGraphRelationshipVo::getStartNodeId));
        Set<String> startNodeIdSet = courseKnowledgeGraphRelationshipVoListMap.keySet();
        for (String startNodeId : startNodeIdSet) {
            List<CourseKnowledgeGraphRelationshipVo> courseKnowledgeGraphRelationshipVos =
                    courseKnowledgeGraphRelationshipVoListMap.get(startNodeId);
            List<String> endNodeNameList = courseKnowledgeGraphRelationshipVos.stream().map(CourseKnowledgeGraphRelationshipVo::getEndNodeName)
                    .distinct().collect(Collectors.toList());
            if (endNodeNameList.size() != courseKnowledgeGraphRelationshipVos.size()) {
                return ErrorResult.customError("同父级下同层级知识点名称不能重复");
            }
        }
        return null;
    }

    public boolean checkChildCourseKnowledgeGraphNode(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, String parentCourseKnowledgeGraphNodeId, String knowledgeNodeName) {
        List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList =
                neo4jUtil.childCourseKnowledgeGraphNodeList(courseKnowledgeGraphDomain.getId(), parentCourseKnowledgeGraphNodeId);
        return childCourseKnowledgeGraphNodeList.stream().anyMatch(courseKnowledgeGraphNode ->
                courseKnowledgeGraphNode.getKnowledgeNodeName().equals(knowledgeNodeName));
    }

    public boolean checkChildCourseKnowledgeGraphNode(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            String parentCourseKnowledgeGraphNodeId, String childCourseKnowledgeGraphNodeId, String knowledgeNodeName) {
        List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList =
                neo4jUtil.childCourseKnowledgeGraphNodeList(courseKnowledgeGraphDomain.getId(), parentCourseKnowledgeGraphNodeId);
        return childCourseKnowledgeGraphNodeList.stream()
                .filter(courseKnowledgeGraphNode -> !courseKnowledgeGraphNode.getId().equals(childCourseKnowledgeGraphNodeId))
                .anyMatch(courseKnowledgeGraphNode ->
                        courseKnowledgeGraphNode.getKnowledgeNodeName().equals(knowledgeNodeName));
    }

    public CourseKnowledgeGraphNode getParentCourseKnowledgeGraphNode(String courseKnowledgeGraphDomainId, String courseKnowledgeGraphNodeId) {
        return neo4jUtil.getParentCourseKnowledgeGraphNode(courseKnowledgeGraphDomainId, courseKnowledgeGraphNodeId);
    }

    public void addOrUpdateCourseKnowledgeGraphClass(CourseKnowledgeGraphInfo courseKnowledgeGraphInfo) {
        List<CourseKnowledgeGraphClass> classList = classRepository
                .findByCourseIdAndSchoolYearAndTerm(courseKnowledgeGraphInfo.getCourseId(),
                        courseKnowledgeGraphInfo.getSchoolYear(), courseKnowledgeGraphInfo.getTerm());
        List<CourseKnowledgeGraphClass> courseKnowledgeGraphClassList = new ArrayList<>();
        Date now = new Date();

        List<String> unPublicIds = courseKnowledgeGraphInfo.getCourseKnowledgeGraphClassVoList().stream()
                .filter(classVo -> !classVo.isPublicStatus()).map(ClassVo::getGroupId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(unPublicIds)) {
            List<CourseKnowledgeGraphClass> classes = classList.stream()
                    .filter(graphClass -> unPublicIds.contains(graphClass.getGroupId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(classes)) {
                classRepository.deleteAll(classes);
            }
        }
        courseKnowledgeGraphInfo.getCourseKnowledgeGraphClassVoList().stream()
                .filter(classVo -> classVo.isPublicStatus()).forEach(classVo -> {
                    CourseKnowledgeGraphClass oldCourseKnowledgeGraphClass = classList.stream()
                            .filter(graphClass -> graphClass.getGroupId().equals(classVo.getGroupId())).findFirst().orElse(null);
                    if (ObjectUtils.isEmpty(oldCourseKnowledgeGraphClass)) {
                        CourseKnowledgeGraphClass courseKnowledgeGraphClass = new CourseKnowledgeGraphClass();
                        courseKnowledgeGraphClass.setGroupId(classVo.getGroupId());
                        courseKnowledgeGraphClass.setGroupName(classVo.getGroupName());
                        courseKnowledgeGraphClass.setCourseId(courseKnowledgeGraphInfo.getCourseId());
                        courseKnowledgeGraphClass.setCourseName(courseKnowledgeGraphInfo.getCourseName());
                        courseKnowledgeGraphClass.setSchoolYear(courseKnowledgeGraphInfo.getSchoolYear());
                        courseKnowledgeGraphClass.setTerm(courseKnowledgeGraphInfo.getTerm());
                        courseKnowledgeGraphClass.setCreatorId(courseKnowledgeGraphInfo.getCreatorId());
                        courseKnowledgeGraphClass.setCreatorName(courseKnowledgeGraphInfo.getCreatorName());
                        courseKnowledgeGraphClass.setCreateTime(now);
                        courseKnowledgeGraphClassList.add(courseKnowledgeGraphClass);
                    }
                });

        classRepository.saveAll(courseKnowledgeGraphClassList);
    }

    public List<ClassVo> getCourseKnowledgeGraphClassList(String courseId, String schoolYear, Integer term) {
        List<CourseKnowledgeGraphClass> classList = classRepository
                .findByCourseIdAndSchoolYearAndTerm(courseId,
                        schoolYear, term);
        List<ClassVo> classVoList = new ArrayList<>();
        classList.forEach(graphClass -> {
            ClassVo classVo = new ClassVo();
            classVo.setGroupId(graphClass.getGroupId());
            classVoList.add(classVo);
        });
        return classVoList;

    }

    public Boolean checkCourseKnowledgeGraphPublicStatus(String courseId, String schoolYear, Integer term, String groupIds) {
        List<String> groups = Arrays.asList(groupIds.split(","));
        List<CourseKnowledgeGraphClass> classList = classRepository
                .findByCourseIdAndSchoolYearAndTermAndGroupIdIn(courseId,
                        schoolYear, term, groups);
        return !CollectionUtils.isEmpty(classList);
    }

    public void createCourseKnowledgeGraphNodeQuestionList(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeQuestionParam courseKnowledgeGraphNodeQuestionParam,
            String ip) {
        List<String> questionIdList = courseKnowledgeGraphNodeQuestionParam.getQuestionIdList();
        List<CourseKnowledgeGraphNodeQuestion> courseKnowledgeGraphNodeQuestionList =
                courseKnowledgeGraphNodeQuestionRepository.findByCourseKnowledgeGraphDomainAndKnowledgeNodeIdOrderBySort(
                        courseKnowledgeGraphDomain, courseKnowledgeGraphNode.getId());
        AtomicInteger sort = new AtomicInteger(courseKnowledgeGraphNodeQuestionList.size());
        Collections.reverse(questionIdList);
        questionIdList.forEach(questionId -> {
            if (courseKnowledgeGraphNodeQuestionList.stream().noneMatch(courseKnowledgeGraphNodeQuestion ->
                    courseKnowledgeGraphNodeQuestion.getQuestionId().equals(questionId))) {
                CourseKnowledgeGraphNodeQuestion nowCourseKnowledgeGraphNodeQuestion = new CourseKnowledgeGraphNodeQuestion();
                nowCourseKnowledgeGraphNodeQuestion.setQuestionId(questionId);
                nowCourseKnowledgeGraphNodeQuestion.setCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
                nowCourseKnowledgeGraphNodeQuestion.setKnowledgeNodeId(courseKnowledgeGraphNode.getId());
                nowCourseKnowledgeGraphNodeQuestion.setSort(sort.getAndIncrement());
                nowCourseKnowledgeGraphNodeQuestion.setCreatorId(courseKnowledgeGraphNodeQuestionParam.getAccessUserId());
                nowCourseKnowledgeGraphNodeQuestion.setCreatorName(courseKnowledgeGraphNodeQuestionParam.getAccessUserName());
                nowCourseKnowledgeGraphNodeQuestion.setCreateTime(new Date());
                courseKnowledgeGraphNodeQuestionRepository.saveAndFlush(nowCourseKnowledgeGraphNodeQuestion);
                courseKnowledgeGraphActionLogService.createAddCourseKnowledgeGraphNodeQuestionActionLog(
                        courseKnowledgeGraphNode, courseKnowledgeGraphDomain, nowCourseKnowledgeGraphNodeQuestion, ip);
            }
        });


    }

    public List<QuestionLibraryResource> getCourseKnowledgeGraphNodeQuestionList(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode courseKnowledgeGraphNode) {
        List<QuestionLibraryResource> effectiveQuestionLibraryResourceList = new ArrayList<>();
        List<CourseKnowledgeGraphNodeQuestion> courseKnowledgeGraphNodeQuestionList =
                courseKnowledgeGraphNodeQuestionRepository.findByCourseKnowledgeGraphDomainAndKnowledgeNodeIdOrderBySort(
                        courseKnowledgeGraphDomain, courseKnowledgeGraphNode.getId());
        if (!CollectionUtils.isEmpty(courseKnowledgeGraphNodeQuestionList)) {
            List<String> questionIdList = courseKnowledgeGraphNodeQuestionList
                    .stream().map(CourseKnowledgeGraphNodeQuestion::getQuestionId).distinct().collect(Collectors.toList());
            List<QuestionLibraryResource> questionLibraryResourceList =
                    courseKnowledgeGraphActionLogService.getQuestionLibraryList(questionIdList);
            for (CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion : courseKnowledgeGraphNodeQuestionList) {
                QuestionLibraryResource questionLibraryResource =
                        questionLibraryResourceList.stream().filter(questionLibraryResource1 ->
                                        questionLibraryResource1.getId().equals(courseKnowledgeGraphNodeQuestion.getQuestionId()))
                                .findFirst().orElse(null);
                if (ObjectUtils.isNotEmpty(questionLibraryResource)) {
                    questionLibraryResource.setQuestionStemText(convertQuestionHtml(questionLibraryResource.getQuestionStem()));
                    questionLibraryResource.setShowOrder(courseKnowledgeGraphNodeQuestion.getSort());
                    effectiveQuestionLibraryResourceList.add(questionLibraryResource);
                }
            }
        }
        return effectiveQuestionLibraryResourceList.stream()
                .sorted(Comparator.comparingInt(QuestionLibraryResource::getShowOrder).reversed())
                .collect(Collectors.toList());
    }

    public String convertQuestionHtml(String htmlContent) {
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

    public CourseKnowledgeGraphNodeQuestion getCourseKnowledgeGraphNodeQuestion(String courseKnowledgeGraphNodeId, String questionId) {
        return courseKnowledgeGraphNodeQuestionRepository.findFirstByKnowledgeNodeIdAndQuestionId(courseKnowledgeGraphNodeId, questionId);
    }

    public void deleteCourseKnowledgeGraphNodeQuestion(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion,
            String accessUserId, String accessUserName, String ip) {
        courseKnowledgeGraphNodeQuestionRepository.delete(courseKnowledgeGraphNodeQuestion);
        courseKnowledgeGraphNodeQuestionRepository.updateByKnowledgeGraphNodeIdAndQuestionSort(
                courseKnowledgeGraphNode.getId(), courseKnowledgeGraphNodeQuestion.getSort());
        courseKnowledgeGraphActionLogService.createDeleteCourseKnowledgeGraphNodeQuestionActionLog(
                courseKnowledgeGraphDomain, courseKnowledgeGraphNode, courseKnowledgeGraphNodeQuestion,
                accessUserId, accessUserName, ip);

    }

    public List<CourseKnowledgeGraphNodeBasicInfo> getNodeBasicInfoListByQuestionId(String questionId) {
        List<CourseKnowledgeGraphNodeBasicInfo> courseKnowledgeGraphNodeBasicInfoList = new ArrayList<>();

        List<CourseKnowledgeGraphNodeQuestion> courseKnowledgeGraphNodeQuestionList =
                courseKnowledgeGraphNodeQuestionRepository.getEffectiveCourseKnowledgeGraphNodeQuestionList(questionId);

        if (!CollectionUtils.isEmpty(courseKnowledgeGraphNodeQuestionList)) {
            for (CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion : courseKnowledgeGraphNodeQuestionList) {
                if (courseKnowledgeGraphNodeBasicInfoList.stream().noneMatch(courseKnowledgeGraphNodeBasicInfo ->
                        courseKnowledgeGraphNodeBasicInfo.getKnowledgeNodeId()
                                .equals(courseKnowledgeGraphNodeQuestion.getKnowledgeNodeId()))) {
                    CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                            getCourseKnowledgeGraphNode(courseKnowledgeGraphNodeQuestion.getCourseKnowledgeGraphDomain().getId(),
                                    courseKnowledgeGraphNodeQuestion.getKnowledgeNodeId());
                    if (ObjectUtils.isNotEmpty(courseKnowledgeGraphNode)) {
                        CourseKnowledgeGraphNodeBasicInfo courseKnowledgeGraphNodeBasicInfo = new CourseKnowledgeGraphNodeBasicInfo();
                        courseKnowledgeGraphNodeBasicInfo.setKnowledgeNodeId(courseKnowledgeGraphNode.getId());
                        courseKnowledgeGraphNodeBasicInfo.setKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
                        courseKnowledgeGraphNodeBasicInfo.setKnowledgeNodeLevelIndex(
                                Objects.requireNonNull(KnowledgeNodeLevel.getKnowledgeNodeLevel(
                                        courseKnowledgeGraphNode.getKnowledgeNodeLevel())).getIndex());
                        courseKnowledgeGraphNodeBasicInfo.setKnowledgeNodeLevelName(
                                Objects.requireNonNull(KnowledgeNodeLevel.getKnowledgeNodeLevel(
                                        courseKnowledgeGraphNode.getKnowledgeNodeLevel())).getName()
                        );
                        courseKnowledgeGraphNodeBasicInfoList.add(courseKnowledgeGraphNodeBasicInfo);
                    }
                }
            }
        }
        return courseKnowledgeGraphNodeBasicInfoList;
    }

    public void deleteNodeQuestionByQuestionIdList(
            List<String> questionIdList, String accessUserId, String accessUserName, String ip) {
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        List<CourseKnowledgeGraphNodeQuestion> courseKnowledgeGraphNodeQuestionList =
                courseKnowledgeGraphNodeQuestionRepository.findByQuestionIdInOrderByCreateTimeAsc(questionIdList);
        if (!CollectionUtils.isEmpty(courseKnowledgeGraphNodeQuestionList)) {
            for (CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion : courseKnowledgeGraphNodeQuestionList) {
                courseKnowledgeGraphNodeQuestionRepository.delete(courseKnowledgeGraphNodeQuestion);
                courseKnowledgeGraphNodeQuestionRepository.updateByKnowledgeGraphNodeIdAndQuestionSort(
                        courseKnowledgeGraphNodeQuestion.getKnowledgeNodeId(), courseKnowledgeGraphNodeQuestion.getSort());
                CourseKnowledgeGraphDomain courseKnowledgeGraphDomain = courseKnowledgeGraphNodeQuestion.getCourseKnowledgeGraphDomain();
                String courseKnowledgeGraphNodeId = courseKnowledgeGraphNodeQuestion.getKnowledgeNodeId();
                if (courseKnowledgeGraphNodeList.stream().anyMatch(courseKnowledgeGraphNode ->
                        courseKnowledgeGraphNode.getId().equals(courseKnowledgeGraphNodeId))) {
                    CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                            courseKnowledgeGraphNodeList.stream().filter(oneCourseKnowledgeGraphNode ->
                                            oneCourseKnowledgeGraphNode.getId().equals(courseKnowledgeGraphNodeId))
                                    .findFirst().orElse(null);
                    if (ObjectUtils.isNotEmpty(courseKnowledgeGraphNode)) {
                        courseKnowledgeGraphActionLogService.createDeleteCourseKnowledgeGraphNodeQuestionActionLog(
                                courseKnowledgeGraphDomain, courseKnowledgeGraphNode, courseKnowledgeGraphNodeQuestion,
                                accessUserId, accessUserName, ip
                        );
                    }
                } else {
                    CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                            getCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNodeId);
                    if (ObjectUtils.isNotEmpty(courseKnowledgeGraphNode)) {
                        courseKnowledgeGraphNodeList.add(courseKnowledgeGraphNode);
                        courseKnowledgeGraphActionLogService.createDeleteCourseKnowledgeGraphNodeQuestionActionLog(
                                courseKnowledgeGraphDomain, courseKnowledgeGraphNode, courseKnowledgeGraphNodeQuestion,
                                accessUserId, accessUserName, ip
                        );
                    }
                }
            }
        }
    }

    public void sendKnowledgeStructureTopicVo(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode courseKnowledgeGraphNode, CourseKnowledgeGraphNode parentCourseKnowledgeGraphNode,
            String accessUserId, String accessUserName) {
        KnowledgeStructureTopicVo knowledgeStructureTopicVo = new KnowledgeStructureTopicVo();
        knowledgeStructureTopicVo.setId(courseKnowledgeGraphNode.getId());
        knowledgeStructureTopicVo.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        knowledgeStructureTopicVo.setKnowledgeStructureType(TWO);
        knowledgeStructureTopicVo.setCourseCode(courseKnowledgeGraphDomain.getCourse().getCourseCode());
        knowledgeStructureTopicVo.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        knowledgeStructureTopicVo.setCourseName(courseKnowledgeGraphDomain.getCourse().getCourseName());
        knowledgeStructureTopicVo.setParentId(parentCourseKnowledgeGraphNode.getId());
        knowledgeStructureTopicVo.setCourseVersionId(courseKnowledgeGraphDomain.getCourseVersion().getId());
        knowledgeStructureTopicVo.setContent(courseKnowledgeGraphNode.getKnowledgeNodeName());
        knowledgeStructureTopicVo.setModifierId(accessUserId);
        knowledgeStructureTopicVo.setModifierName(accessUserName);
        knowledgeStructureTopicVo.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
        knowledgeStructureTopicVo.setParentName(parentCourseKnowledgeGraphNode.getKnowledgeNodeName());
        knowledgeStructureTopicVo.setKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        eventService.sendAddKnowledgeStructure(knowledgeStructureTopicVo);
    }

    public List<CourseKnowledgeGraphNode> getCourseKnowledgeGraphNodeList(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        return neo4jUtil.getCourseKnowledgeGraphNodeList(courseKnowledgeGraphDomain);
    }

    public void historyDataPushKafka() {
        List<CourseKnowledgeGraphDomain> courseKnowledgeGraphDomainList = courseKnowledgeGraphDomainRepository.findAll();
        Map<CourseKnowledgeGraphDomain, List<CourseKnowledgeGraphNode>> courseKnowledgeGraphDomainListMap =
                new HashMap<>();
        for (CourseKnowledgeGraphDomain courseKnowledgeGraphDomain : courseKnowledgeGraphDomainList) {
            List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = getCourseKnowledgeGraphNodeList(courseKnowledgeGraphDomain);
            courseKnowledgeGraphDomainListMap.put(courseKnowledgeGraphDomain, courseKnowledgeGraphNodeList);
        }
        courseKnowledgeGraphDomainListMap
                .forEach((courseKnowledgeGraphDomain,
                          courseKnowledgeGraphNodeList) -> {
                            KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo =
                                    getKnowledgeGraphDomainTopicVo(courseKnowledgeGraphDomain);
                            eventService.sendKnowledgeGraphDomainTopicVo(knowledgeGraphDomainTopicVo);

                            courseKnowledgeGraphNodeList.forEach(courseKnowledgeGraphNode -> {
                                CourseKnowledgeGraphNode parentCourseKnowledgeGraphNode = getParentCourseKnowledgeGraphNode(
                                        courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
                                KnowledgeStructureTopicVo knowledgeStructureTopicVo = new KnowledgeStructureTopicVo();
                                knowledgeStructureTopicVo.setId(courseKnowledgeGraphNode.getId());
                                knowledgeStructureTopicVo.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
                                knowledgeStructureTopicVo.setKnowledgeStructureType(TWO);
                                knowledgeStructureTopicVo.setCourseCode(courseKnowledgeGraphDomain.getCourse().getCourseCode());
                                knowledgeStructureTopicVo.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
                                knowledgeStructureTopicVo.setCourseName(courseKnowledgeGraphDomain.getCourse().getCourseName());
                                knowledgeStructureTopicVo.setParentId(parentCourseKnowledgeGraphNode.getId());
                                knowledgeStructureTopicVo.setCourseVersionId(courseKnowledgeGraphDomain.getCourseVersion().getId());
                                knowledgeStructureTopicVo.setContent(courseKnowledgeGraphNode.getKnowledgeNodeName());
                                knowledgeStructureTopicVo.setModifierId(courseKnowledgeGraphNode.getCreatorId());
                                knowledgeStructureTopicVo.setModifierName(courseKnowledgeGraphNode.getCreatorName());
                                knowledgeStructureTopicVo.setModifyTime(courseKnowledgeGraphNode.getCreateTime());
                                knowledgeStructureTopicVo.setParentName(parentCourseKnowledgeGraphNode.getKnowledgeNodeName());
                                knowledgeStructureTopicVo.setKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
                                eventService.sendAddKnowledgeStructure(knowledgeStructureTopicVo);
                            });

                        }
                );
    }

    public KnowledgeGraphDomainTopicVo getKnowledgeGraphDomainTopicVo(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo = new KnowledgeGraphDomainTopicVo();
        knowledgeGraphDomainTopicVo.setId(courseKnowledgeGraphDomain.getId());
        knowledgeGraphDomainTopicVo.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        knowledgeGraphDomainTopicVo.setCourseCode(courseKnowledgeGraphDomain.getCourse().getCourseCode());
        knowledgeGraphDomainTopicVo.setCourseName(courseKnowledgeGraphDomain.getCourse().getCourseName());
        knowledgeGraphDomainTopicVo.setShowOrder(courseKnowledgeGraphDomain.getShowOrder());
        knowledgeGraphDomainTopicVo.setVersionLabel(courseKnowledgeGraphDomain.getVersionLabel());
        knowledgeGraphDomainTopicVo.setStatus(courseKnowledgeGraphDomain.getStatus());
        return knowledgeGraphDomainTopicVo;
    }

    public List<CourseKnowledgeGraphCorrelationNodeInfo> getCourseKnowledgeGraphCorrelationNodeInfoList(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNodeQueryInfoParam courseKnowledgeGraphNodeQueryInfoParam
    ) {
        String searchParams = StringUtils.trimToEmpty(courseKnowledgeGraphNodeQueryInfoParam.getSearchParams()).toLowerCase();
        boolean hasSearchParams = StringUtils.isNotEmpty(searchParams);
        int queryType = courseKnowledgeGraphNodeQueryInfoParam.getQueryType();

        String cypherTemplate = "MATCH (child:`%s`) WHERE child.knowledgeNodeLevel <> 0 %s " +
                "OPTIONAL MATCH (parent)-[:PARENT_CHILD]->(child) " +
                "OPTIONAL MATCH (grandparent)-[:PARENT_CHILD]->(parent) " +
                "OPTIONAL MATCH (greatGrandParent)-[:PARENT_CHILD]->(grandparent) " +
                "RETURN " +
                "CASE WHEN greatGrandParent IS NULL OR greatGrandParent.knowledgeNodeLevel = 0 THEN NULL " +
                "ELSE greatGrandParent.id END as greatGrandParentKnowledgeNodeId, " +
                "CASE WHEN greatGrandParent IS NULL OR greatGrandParent.knowledgeNodeLevel = 0 THEN NULL " +
                "ELSE greatGrandParent.knowledgeNodeName END as greatGrandParentKnowledgeNodeName, " +
                "CASE WHEN grandparent IS NULL OR grandparent.knowledgeNodeLevel = 0 THEN NULL " +
                "ELSE grandparent.id END as grandParentKnowledgeNodeId, " +
                "CASE WHEN grandparent IS NULL OR grandparent.knowledgeNodeLevel = 0 THEN NULL " +
                "ELSE grandparent.knowledgeNodeName END as grandParentKnowledgeNodeName, " +
                "CASE WHEN parent IS NULL OR parent.knowledgeNodeLevel = 0 THEN NULL " +
                "ELSE parent.id END as parentKnowledgeNodeId, " +
                "CASE WHEN parent IS NULL OR parent.knowledgeNodeLevel = 0 THEN NULL " +
                "ELSE parent.knowledgeNodeName END as parentKnowledgeNodeName, " +
                "child.id as childKnowledgeNodeId, " +
                "child.knowledgeNodeName as childKnowledgeNodeName, " +
                "child.knowledgeNodeLevel as childKnowledgeNodeLevel, " +
                "child.sort as childKnowledgeNodeSort " +
                "ORDER BY child.knowledgeNodeLevel, child.sort";

        String whereClause = "";
        Value parameters = null;

        if (queryType == 0 && hasSearchParams) {
            whereClause = "AND toLower(child.knowledgeNodeName) = $searchParams";
            parameters = parameters("searchParams", searchParams);
        } else if (queryType == 1 && hasSearchParams) {
            whereClause = "AND toLower(child.knowledgeNodeName) CONTAINS $searchParams";
            parameters = parameters("searchParams", searchParams);
        }

        String cypherSql = String.format(cypherTemplate, courseKnowledgeGraphDomain.getId(), whereClause);

        return parameters != null ?
                neo4jUtil.getCourseKnowledgeGraphCorrelationNodeInfoList(cypherSql, parameters) :
                neo4jUtil.getCourseKnowledgeGraphCorrelationNodeInfoList(cypherSql);
    }


    public String getLastVersionLabel(String courseId) {
        return courseKnowledgeGraphDomainRepository.getLastVersionLabel(courseId);
    }

    public void updateCourseKnowledgeGraphDomainStatus(String id, boolean status) {
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain = courseKnowledgeGraphDomainRepository.getCourseKnowledgeGraphDomainById(id);
        if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
            return;
        }
        courseKnowledgeGraphDomain.setStatus(status);
        courseKnowledgeGraphDomainRepository.saveAndFlush(courseKnowledgeGraphDomain);
        /*kafka知识图谱数据推送*/
        if (dataVisualEnable) {
            KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo = getKnowledgeGraphDomainTopicVo(courseKnowledgeGraphDomain);
            eventService.sendKnowledgeGraphDomainTopicVo(knowledgeGraphDomainTopicVo);
        }
    }

    public CourseKnowledgeGraphNodeTreeModel getHistoryCourseKnowledgeGraphNodeTreeModel(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {

        CourseKnowledgeGraphNode courseKnowledgeGraphNode = neo4jUtil.getRootCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId());
        if (neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId()) > 1) {
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel =
                    neo4jUtil.getNodeTree(courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
            packCourseKnowledgeGraphNodeTreeModel(courseKnowledgeGraphNodeTreeModel, courseKnowledgeGraphDomain.getId());
            return courseKnowledgeGraphNodeTreeModel;
        } else {
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel = new CourseKnowledgeGraphNodeTreeModel();
            courseKnowledgeGraphNodeTreeModel.setId(courseKnowledgeGraphNode.getId());
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelIndex(
                    KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getIndex());
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelName(
                    KnowledgeNodeLevel.getKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel()).getName());
            courseKnowledgeGraphNodeTreeModel.setSort(courseKnowledgeGraphNode.getSort());
            courseKnowledgeGraphNodeTreeModel.setCourseKnowledgeGraphId(courseKnowledgeGraphDomain.getId());
            return courseKnowledgeGraphNodeTreeModel;
        }
    }
}

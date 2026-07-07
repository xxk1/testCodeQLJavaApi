package com.lztech.site.service.kggentask;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.kggentask.*;
import com.lztech.domain.model.kggentask.enums.KgGenTaskMasterStatus;
import com.lztech.domain.model.kggentask.enums.KgGenTaskStepCode;
import com.lztech.domain.model.kggentask.enums.KgGenTaskSubStatus;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNode;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeQuestion;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeVideoInfoText;
import com.lztech.domain.model.knowledgegraph.enums.ContentGenerationMode;
import com.lztech.domain.model.knowledgegraph.enums.KnowledgeNodeLevel;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphDomainRepository;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphNodeVideoInfoTextRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.persistence.repositories.kggentask.KgGenNodeRepository;
import com.lztech.persistence.repositories.kggentask.KgGenNodeVideoInfoRepository;
import com.lztech.persistence.repositories.kggentask.KgGenTaskMasterRepository;
import com.lztech.persistence.repositories.kggentask.KgGenTaskSubRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphService;
import com.lztech.site.service.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphRelationshipVo;
import com.lztech.site.viewmodel.event.KnowledgeGraphDomainTopicVo;
import com.lztech.site.viewmodel.event.KnowledgeStructureTopicVo;
import com.lztech.site.viewmodel.kggentask.KgAIGenImportParam;
import com.lztech.site.viewmodel.kggentask.KgAIGenTeachingMaterialNodeResourceItem;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.TWO;
import static com.lztech.site.constants.ConstantDataVisual.DATA_VISUAL_SOURCE_CLOUD_CLASSROOM;
import static com.lztech.site.until.UUIDGenerator.generateUUIDWithoutHyphens;

@Service
public class KgAIGenTaskResultImportService {
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Autowired
    private EventService eventService;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;
    @Autowired
    private KgGenTaskMasterRepository kgGenTaskMasterRepository;
    @Autowired
    private KgGenTaskSubRepository kgGenTaskSubRepository;

    @Autowired
    private KgGenNodeRepository kgGenNodeRepository;
    @Autowired
    private KgGenNodeVideoInfoRepository kgGenNodeVideoInfoRepository;
    @Autowired
    private KgAIGenTaskService kgAIGenTaskService;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private Neo4jUtil neo4jUtil;
    @Autowired
    private CourseKnowledgeGraphDomainRepository courseKnowledgeGraphDomainRepository;
    @Autowired
    private CourseKnowledgeGraphAiGenerateTaskService courseKnowledgeGraphAiGenerateTaskService;

    @Autowired
    private CourseKnowledgeGraphNodeVideoInfoTextRepository courseKnowledgeGraphNodeVideoInfoTextRepository;


    @Transactional
    public Result importKnowledgeGraphAIGen(KgAIGenImportParam param) {
        KgGenTaskMaster kgGenTaskMaster = kgGenTaskMasterRepository.findById(param.getMasterTaskId()).orElse(null);
        if (Objects.isNull(kgGenTaskMaster)) {
            return Result.error("任务不存在");
        }
        List<KgGenTaskSub> kgGenTaskSubList = kgGenTaskSubRepository.findLatestByMasterIdAndStepCode(kgGenTaskMaster.getId(),
                KgGenTaskStepCode.STEP_NODE_GEN);
        if (CollectionUtils.isEmpty(kgGenTaskSubList)) {
            return Result.error("任务不存在");
        }
        KgGenTaskSub endKgGenTaskSub = kgGenTaskSubRepository.findById(param.getSubTaskId()).orElse(null);
        if (Objects.isNull(endKgGenTaskSub)) {
            return Result.error("任务不存在");
        }
        KgGenTaskSub kgGenTaskSub = kgGenTaskSubList.get(0);
        List<KgGenNode> kgGenNodeList = kgGenNodeRepository.findBySubTaskId(kgGenTaskSub.getId());
        Course course = courseService.findById(param.getCourseId());
        List<CourseVersion> courseVersions = courseVersionRepository.findByCourseInAndCourseVersionStatus(
                Collections.singletonList(course), CourseVersionStatus.IN_USE);
        CourseVersion courseVersion = courseVersions.get(0);
        /*判断当前版本图谱知识点个数*/
        CourseKnowledgeGraphDomain effectiveCourseKnowledgeGraphDomain =
                courseKnowledgeGraphService.getCourseKnowledgeGraphDomain(param.getCourseId());
        int nodeCounts = neo4jUtil.getDomainNodeCount(effectiveCourseKnowledgeGraphDomain.getId());
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain = null;
        if (nodeCounts == 1) {
            courseKnowledgeGraphDomain = effectiveCourseKnowledgeGraphDomain;
        } else {
            CourseKnowledgeGraphDomain createCourseKnowledgeGraphDomain =
                    courseKnowledgeGraphAiGenerateTaskService.createCourseKnowledgeGraphDomain(course, courseVersion, param.getOperatorId(),
                            param.getOperatorName());
            courseKnowledgeGraphDomain = courseKnowledgeGraphDomainRepository.saveAndFlush(createCourseKnowledgeGraphDomain);
            if (dataVisualEnable) {
                KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo =
                        courseKnowledgeGraphAiGenerateTaskService.getKnowledgeGraphDomainTopicVo(courseKnowledgeGraphDomain);
                eventService.sendKnowledgeGraphDomainTopicVo(knowledgeGraphDomainTopicVo);
            }
        }

        // 构造成一个树
        KgAIGenTeachingMaterialNodeResourceItem nodeResourceItem = kgAIGenTaskService.buildNodeList(kgGenNodeList, param.getCourseId(),
                kgGenTaskMaster.getCourseName());
        List<KgGenNodeVideoInfo> kgGenNodeVideoInfoList = kgGenNodeVideoInfoRepository.findByMasterTaskId(kgGenTaskMaster.getId());
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        List<CourseKnowledgeGraphRelationshipVo> courseKnowledgeGraphRelationshipVoList = new ArrayList<>();
        List<CourseKnowledgeGraphNodeVideoInfoText> courseKnowledgeGraphNodeVideoInfoTextList = new ArrayList<>();
        buildGraphNodeAndRelationship(nodeResourceItem, courseKnowledgeGraphNodeList, courseKnowledgeGraphRelationshipVoList, null,
                param.getOperatorId(), param.getOperatorName(), kgGenNodeVideoInfoList, courseKnowledgeGraphNodeVideoInfoTextList,
                courseKnowledgeGraphDomain);
        courseKnowledgeGraphNodeList =
                courseKnowledgeGraphNodeList.stream().sorted(Comparator.comparingInt(CourseKnowledgeGraphNode::getKnowledgeNodeLevel)
                        .thenComparingInt(CourseKnowledgeGraphNode::getSort)).collect(Collectors.toList());


        List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList = new ArrayList<>();

        if (nodeCounts == 1) {
            CourseKnowledgeGraphNode rootCourseKnowledgeGraphNode =
                    neo4jUtil.getRootCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId());
            CourseKnowledgeGraphNode newRootCourseKnowledgeGraphNode = courseKnowledgeGraphNodeList.stream().filter(
                    courseKnowledgeGraphNode ->
                            courseKnowledgeGraphNode.getKnowledgeNodeLevel() == KnowledgeNodeLevel.ZERO_LEVEL.getIndex()
            ).findFirst().orElse(null);
            courseKnowledgeGraphNodeList.add(rootCourseKnowledgeGraphNode);
            courseKnowledgeGraphNodeList.remove(newRootCourseKnowledgeGraphNode);
            for (CourseKnowledgeGraphRelationshipVo courseKnowledgeGraphRelationshipVo : courseKnowledgeGraphRelationshipVoList) {
                if (courseKnowledgeGraphRelationshipVo.getStartNodeId().equals(newRootCourseKnowledgeGraphNode.getId())) {
                    courseKnowledgeGraphRelationshipVo.setStartNodeId(rootCourseKnowledgeGraphNode.getId());
                }
            }
            childCourseKnowledgeGraphNodeList =
                    courseKnowledgeGraphNodeList.stream().filter(courseKnowledgeGraphNode ->
                                    courseKnowledgeGraphNode.getKnowledgeNodeLevel() != KnowledgeNodeLevel.ZERO_LEVEL.getIndex())
                            .collect(Collectors.toList());
        } else {
            CourseKnowledgeGraphNode rootCourseKnowledgeGraphNode = courseKnowledgeGraphNodeList.stream().filter(
                    courseKnowledgeGraphNode ->
                            courseKnowledgeGraphNode.getKnowledgeNodeLevel() == KnowledgeNodeLevel.ZERO_LEVEL.getIndex()
            ).findFirst().orElse(null);
            neo4jUtil.createRootCourseKnowledgeGraphNode(rootCourseKnowledgeGraphNode, courseKnowledgeGraphDomain.getId());
            childCourseKnowledgeGraphNodeList =
                    courseKnowledgeGraphNodeList.stream().filter(courseKnowledgeGraphNode ->
                            !courseKnowledgeGraphNode.getId().equals(rootCourseKnowledgeGraphNode.getId())).collect(Collectors.toList());
        }
        for (CourseKnowledgeGraphNode courseKnowledgeGraphNode : childCourseKnowledgeGraphNodeList) {
            CourseKnowledgeGraphRelationshipVo courseKnowledgeGraphRelationshipVo = courseKnowledgeGraphRelationshipVoList.stream()
                    .filter(oneCourseKnowledgeGraphRelationshipVo ->
                            oneCourseKnowledgeGraphRelationshipVo.getEndNodeId().equals(courseKnowledgeGraphNode.getId()))
                    .findFirst().get();
            neo4jUtil.createChildCourseKnowledgeGraphNode(courseKnowledgeGraphDomain.getId(),
                    courseKnowledgeGraphRelationshipVo.getStartNodeId(), courseKnowledgeGraphNode);
        }
        courseKnowledgeGraphAiGenerateTaskService.updateCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain, param.getOperatorId(),
                param.getOperatorName());
        updateTaskEnd(param, kgGenTaskMaster, endKgGenTaskSub);
        if (CollectionUtils.isNotEmpty(courseKnowledgeGraphNodeVideoInfoTextList)) {
            courseKnowledgeGraphNodeVideoInfoTextRepository.saveAll(courseKnowledgeGraphNodeVideoInfoTextList);
        }
        if (dataVisualEnable) {
            sendChildCourseKnowledgeGraphNodeList(
                    param.getOperatorId(), param.getOperatorName(), childCourseKnowledgeGraphNodeList,
                    courseKnowledgeGraphRelationshipVoList, courseKnowledgeGraphNodeList, courseKnowledgeGraphDomain);
        }

        return Result.success();
    }

    private void updateTaskEnd(KgAIGenImportParam param, KgGenTaskMaster kgGenTaskMaster, KgGenTaskSub endKgGenTaskSub) {
        kgGenTaskMaster.setFinishTime(new Date());
        kgGenTaskMaster.setModifierId(param.getOperatorId());
        kgGenTaskMaster.setModifierName(param.getOperatorName());
        kgGenTaskMaster.setModifyTime(new Date());
        kgGenTaskMaster.setStatus(KgGenTaskMasterStatus.SUCCESS);
        kgGenTaskMasterRepository.saveAndFlush(kgGenTaskMaster);
        endKgGenTaskSub.setFinishTime(new Date());
        endKgGenTaskSub.setModifierId(param.getOperatorId());
        endKgGenTaskSub.setModifierName(param.getOperatorName());
        endKgGenTaskSub.setModifyTime(new Date());
        endKgGenTaskSub.setStatus(KgGenTaskSubStatus.SUCCESS);
        kgGenTaskSubRepository.saveAndFlush(endKgGenTaskSub);
    }

    private void sendChildCourseKnowledgeGraphNodeList(
            String accessUserId, String accessUserName, List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList,
            List<CourseKnowledgeGraphRelationshipVo> courseKnowledgeGraphRelationshipVoList,
            List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList, CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        for (CourseKnowledgeGraphNode courseKnowledgeGraphNode : childCourseKnowledgeGraphNodeList) {
            CourseKnowledgeGraphRelationshipVo courseKnowledgeGraphRelationshipVo = courseKnowledgeGraphRelationshipVoList.stream()
                    .filter(oneCourseKnowledgeGraphRelationshipVo ->
                            oneCourseKnowledgeGraphRelationshipVo.getEndNodeId().equals(courseKnowledgeGraphNode.getId()))
                    .findFirst().get();
            CourseKnowledgeGraphNode parentCourseKnowledgeGraphNode =
                    courseKnowledgeGraphNodeList.stream().filter(oneCourseKnowledgeGraphNode ->
                                    oneCourseKnowledgeGraphNode.getId().equals(courseKnowledgeGraphRelationshipVo.getStartNodeId()))
                            .findFirst().get();
            sendKnowledgeStructureTopicVo(courseKnowledgeGraphDomain, courseKnowledgeGraphNode, parentCourseKnowledgeGraphNode,
                    accessUserId, accessUserName);
        }
    }

    private void sendKnowledgeStructureTopicVo(
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

    private void buildGraphNodeAndRelationship(KgAIGenTeachingMaterialNodeResourceItem nodeResourceItem,
                                               List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList,
                                               List<CourseKnowledgeGraphRelationshipVo> knowledgeGraphRelationshipVoList,
                                               String parentNodeId,
                                               String operatorId, String operatorName,
                                               List<KgGenNodeVideoInfo> kgGenNodeVideoInfoList,
                                               List<CourseKnowledgeGraphNodeVideoInfoText> courseKnowledgeGraphNodeVideoInfoTextList,
                                               CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
        courseKnowledgeGraphNode.setId(generateUUIDWithoutHyphens());
        courseKnowledgeGraphNode.setKnowledgeNodeName(nodeResourceItem.getKgNodeName());
        courseKnowledgeGraphNode.setLevelType(nodeResourceItem.getKgNodeImportantTag());
        courseKnowledgeGraphNode.setContentDetail(nodeResourceItem.getKgNodeContent());
        courseKnowledgeGraphNode.setKnowledgeNodeLevel(nodeResourceItem.getLevel());
        courseKnowledgeGraphNode.setSort(nodeResourceItem.getShowOrder());
        courseKnowledgeGraphNode.setCreateTime(TimeUtils.getNowDateTime());
        courseKnowledgeGraphNode.setCreatorId(operatorId);
        courseKnowledgeGraphNode.setCreatorName(operatorName);
        courseKnowledgeGraphNode.setModifyTime(TimeUtils.getNowDateTime());
        courseKnowledgeGraphNode.setModifierId(operatorId);
        courseKnowledgeGraphNode.setModifierName(operatorName);
        courseKnowledgeGraphNode.setName(nodeResourceItem.getKgNodeName());
        courseKnowledgeGraphNode.setContentGenerationModeIndex(ContentGenerationMode.SMART_GENERATION.getIndex());
        courseKnowledgeGraphNode.setNodeGenerationModeIndex(ContentGenerationMode.SMART_GENERATION.getIndex());
        courseKnowledgeGraphNodeList.add(courseKnowledgeGraphNode);
        addRelatedVideoInfo(kgGenNodeVideoInfoList, courseKnowledgeGraphNodeVideoInfoTextList, nodeResourceItem,
                courseKnowledgeGraphDomain, courseKnowledgeGraphNode);
        if (StringUtils.isNotBlank(parentNodeId)) {
            CourseKnowledgeGraphRelationshipVo courseKnowledgeGraphRelationshipVo =
                    CourseKnowledgeGraphRelationshipVo.builder().startNodeId(parentNodeId)
                            .endNodeId(courseKnowledgeGraphNode.getId())
                            .endNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName()).build();
            knowledgeGraphRelationshipVoList.add(courseKnowledgeGraphRelationshipVo);
        }
        if (CollectionUtils.isNotEmpty(nodeResourceItem.getChildList())) {
            for (KgAIGenTeachingMaterialNodeResourceItem childNodeResourceItem : nodeResourceItem.getChildList()) {
                buildGraphNodeAndRelationship(childNodeResourceItem, courseKnowledgeGraphNodeList,
                        knowledgeGraphRelationshipVoList, courseKnowledgeGraphNode.getId(),
                        operatorId, operatorName, kgGenNodeVideoInfoList,
                        courseKnowledgeGraphNodeVideoInfoTextList, courseKnowledgeGraphDomain);
            }
        }

    }

    private void addRelatedVideoInfo(List<KgGenNodeVideoInfo> kgGenNodeVideoInfoList,
                                     List<CourseKnowledgeGraphNodeVideoInfoText> courseKnowledgeGraphNodeVideoInfoTextList,
                                     KgAIGenTeachingMaterialNodeResourceItem nodeResourceItem,
                                     CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                     CourseKnowledgeGraphNode courseKnowledgeGraphNode) {
        List<KgGenNodeVideoInfo> nodeVideoInfoList = kgGenNodeVideoInfoList.stream().filter(v ->
                        nodeResourceItem.getKgNodeId().equals(v.getKnowledgeNodeId()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(nodeVideoInfoList)) {
            return;
        }
        for (KgGenNodeVideoInfo kgGenNodeVideoInfo : nodeVideoInfoList) {
            CourseKnowledgeGraphNodeVideoInfoText videoInfoText = new CourseKnowledgeGraphNodeVideoInfoText();
            videoInfoText.setCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
            videoInfoText.setTextDataSource(kgGenNodeVideoInfo.getTextDataSource());
            videoInfoText.setVideoInfoId(kgGenNodeVideoInfo.getVideoInfoId());
            videoInfoText.setSchoolYear(kgGenNodeVideoInfo.getSchoolYear());
            videoInfoText.setTerm(kgGenNodeVideoInfo.getTerm());
            videoInfoText.setTeacherIds(kgGenNodeVideoInfo.getTeacherIds());
            videoInfoText.setTeacherNos(kgGenNodeVideoInfo.getTeacherNos());
            videoInfoText.setTeacherNames(kgGenNodeVideoInfo.getTeacherNames());
            videoInfoText.setVideoDate(kgGenNodeVideoInfo.getVideoDate());
            videoInfoText.setCourseTableDetailId(kgGenNodeVideoInfo.getCourseTableDetailId());
            videoInfoText.setSegment(kgGenNodeVideoInfo.getSegment());
            videoInfoText.setCourseTableId(kgGenNodeVideoInfo.getCourseTableId());
            videoInfoText.setGroupId(kgGenNodeVideoInfo.getGroupId());
            videoInfoText.setCourseId(kgGenNodeVideoInfo.getCourseId());
            videoInfoText.setInnerIp(kgGenNodeVideoInfo.getInnerIp());
            videoInfoText.setOuterIp(kgGenNodeVideoInfo.getOuterIp());
            videoInfoText.setThumbnailPath(kgGenNodeVideoInfo.getThumbnailPath());
            videoInfoText.setKnowledgeNodeId(courseKnowledgeGraphNode.getId());
            videoInfoText.setKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
            videoInfoText.setTextContent(kgGenNodeVideoInfo.getTextContent());
            videoInfoText.setHighLightTextContent(kgGenNodeVideoInfo.getHighLightTextContent());
            videoInfoText.setStartTimestamp(kgGenNodeVideoInfo.getStartTimestamp());
            videoInfoText.setEndTimestamp(kgGenNodeVideoInfo.getEndTimestamp());
            videoInfoText.setCreatorId(courseKnowledgeGraphNode.getCreatorId());
            videoInfoText.setCreatorName(courseKnowledgeGraphNode.getCreatorName());
            videoInfoText.setCreateTime(new Date());
            videoInfoText.setSimilarityScore(kgGenNodeVideoInfo.getSimilarityScore());
            courseKnowledgeGraphNodeVideoInfoTextList.add(videoInfoText);
        }
    }

    private void addRelatedQuestion(List<KgGenNodeQuestion> kgGenNodeQuestionList,
                                    List<CourseKnowledgeGraphNodeQuestion> courseKnowledgeGraphNodeQuestionList,
                                    KgAIGenTeachingMaterialNodeResourceItem nodeResourceItem,
                                    CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                    CourseKnowledgeGraphNode courseKnowledgeGraphNode) {
        List<KgGenNodeQuestion> nodeQuestionList = kgGenNodeQuestionList.stream().filter(q ->
                        nodeResourceItem.getKgNodeId().equals(q.getNodeId()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(nodeQuestionList)) {
            return;
        }
        for (KgGenNodeQuestion kgGenNodeQuestion : nodeQuestionList) {
            CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion = new CourseKnowledgeGraphNodeQuestion();
            courseKnowledgeGraphNodeQuestion.setCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
            courseKnowledgeGraphNodeQuestion.setKnowledgeNodeId(courseKnowledgeGraphNode.getId());
            courseKnowledgeGraphNodeQuestion.setQuestionId(kgGenNodeQuestion.getQuestionId());
            courseKnowledgeGraphNodeQuestion.setSort(kgGenNodeQuestion.getSort());
            courseKnowledgeGraphNodeQuestion.setCreatorId(courseKnowledgeGraphNode.getCreatorId());
            courseKnowledgeGraphNodeQuestion.setCreatorName(courseKnowledgeGraphNode.getCreatorName());
            courseKnowledgeGraphNodeQuestion.setCreateTime(new Date());
            courseKnowledgeGraphNodeQuestionList.add(courseKnowledgeGraphNodeQuestion);
        }
    }
}

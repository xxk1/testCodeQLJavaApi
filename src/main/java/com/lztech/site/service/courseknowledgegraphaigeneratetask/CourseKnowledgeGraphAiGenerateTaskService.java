package com.lztech.site.service.courseknowledgegraphaigeneratetask;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNode;
import com.lztech.domain.model.knowledgegraph.enums.KnowledgeNodeLevel;
import com.lztech.domain.model.knowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTask;
import com.lztech.domain.model.knowledgegraphaigeneratetask.enums.GraphAiGenerateStatus;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphDomainRepository;
import com.lztech.persistence.repositories.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphRelationshipVo;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskTaskStatusModel;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseknowledgegraphaigeneratetaskCreateParam;
import com.lztech.site.viewmodel.event.KnowledgeGraphDomainTopicVo;
import com.lztech.site.viewmodel.event.KnowledgeStructureTopicVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.TWO;
import static com.lztech.site.constants.ConstantDataVisual.DATA_VISUAL_SOURCE_CLOUD_CLASSROOM;

@Service
public class CourseKnowledgeGraphAiGenerateTaskService {

    @Autowired
    private CourseKnowledgeGraphAiGenerateTaskRepository courseKnowledgeGraphAiGenerateTaskRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;

    @Autowired
    private CourseService courseService;

    public CourseKnowledgeGraphAiGenerateTask getCourseKnowledgeGraphAiGenerateTask(String courseId) {
        return courseKnowledgeGraphAiGenerateTaskRepository.findFirstByCourseId(courseId);
    }

    @Autowired
    private CourseKnowledgeGraphDomainRepository courseKnowledgeGraphDomainRepository;

    @Autowired
    private Neo4jUtil neo4jUtil;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Autowired
    private EventService eventService;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;

    public void createCourseKnowledgeGraphAiGenerateTask(
            CourseknowledgegraphaigeneratetaskCreateParam courseknowledgegraphaigeneratetaskCreateParam) {
        CourseKnowledgeGraphAiGenerateTask courseKnowledgeGraphAiGenerateTask = new CourseKnowledgeGraphAiGenerateTask();
        courseKnowledgeGraphAiGenerateTask.setCourseId(courseknowledgegraphaigeneratetaskCreateParam.getCourseId());
        courseKnowledgeGraphAiGenerateTask.setSchoolYear(courseknowledgegraphaigeneratetaskCreateParam.getSchoolYear());
        courseKnowledgeGraphAiGenerateTask.setTerm(courseknowledgegraphaigeneratetaskCreateParam.getTerm());
        courseKnowledgeGraphAiGenerateTask.setGroupId(courseknowledgegraphaigeneratetaskCreateParam.getGroupId());
        courseKnowledgeGraphAiGenerateTask.setGraphAiGenerateStatus(GraphAiGenerateStatus.WAITING);
        courseKnowledgeGraphAiGenerateTask.setCreatorId(courseknowledgegraphaigeneratetaskCreateParam.getAccessUserId());
        courseKnowledgeGraphAiGenerateTask.setCreatorName(courseknowledgegraphaigeneratetaskCreateParam.getAccessUserName());
        courseKnowledgeGraphAiGenerateTask.setCreateTime(new Date());
        courseKnowledgeGraphAiGenerateTaskRepository.saveAndFlush(courseKnowledgeGraphAiGenerateTask);

    }

    public CourseKnowledgeGraphAiGenerateTaskTaskStatusModel getCourseKnowledgeGraphAiGenerateTaskTaskStatusModel(String courseId) {
        CourseKnowledgeGraphAiGenerateTask courseKnowledgeGraphAiGenerateTask = getCourseKnowledgeGraphAiGenerateTask(courseId);
        CourseKnowledgeGraphAiGenerateTaskTaskStatusModel courseKnowledgeGraphAiGenerateTaskCreateStatusModel =
                new CourseKnowledgeGraphAiGenerateTaskTaskStatusModel();
        if (ObjectUtils.isEmpty(courseKnowledgeGraphAiGenerateTask)) {
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setStatus(0);
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setRemark("课程【" + courseId + "】没有知识图谱智能生成任务");

        } else if (courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus() == GraphAiGenerateStatus.SUCCESS) {
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setStatus(Constant.TWO);
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setRemark("课程【" + courseId + "】知识图谱智能生成完成");

        } else if (courseKnowledgeGraphAiGenerateTask.getGraphAiGenerateStatus() == GraphAiGenerateStatus.FAIL) {
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setStatus(Constant.THREE);
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setRemark("课程【" + courseId + "】知识图谱智能生成失败");

        } else {
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setStatus(1);
            courseKnowledgeGraphAiGenerateTaskCreateStatusModel.setRemark("课程【" + courseId + "】知识图谱智能生成任务正在进行中");

        }

        return courseKnowledgeGraphAiGenerateTaskCreateStatusModel;
    }

    public void immediateUseCourseKnowledgeGraphAiGenerateTask(
            CourseKnowledgeGraphAiGenerateTask courseKnowledgeGraphAiGenerateTask, String accessUserId, String accessUserName) {
        String graphContent = courseKnowledgeGraphAiGenerateTask.getGraphContent();
        JSONObject jsonObject = JSONObject.parseObject(graphContent);
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        List<CourseKnowledgeGraphRelationshipVo> courseKnowledgeGraphRelationshipVoList = new ArrayList<>();
        composeCourseKnowledgeGraphData(courseKnowledgeGraphNodeList, courseKnowledgeGraphRelationshipVoList,
                jsonObject, null, 0, 1);
        for (CourseKnowledgeGraphNode courseKnowledgeGraphNode : courseKnowledgeGraphNodeList) {
            courseKnowledgeGraphNode.setCreatorId(accessUserId);
            courseKnowledgeGraphNode.setCreatorName(accessUserName);
        }
        courseKnowledgeGraphNodeList =
                courseKnowledgeGraphNodeList.stream().sorted(Comparator.comparingInt(CourseKnowledgeGraphNode::getKnowledgeNodeLevel)
                        .thenComparingInt(CourseKnowledgeGraphNode::getSort)).collect(Collectors.toList());
        System.out.println("知识点个数:" + courseKnowledgeGraphNodeList.size());
        System.out.println("知识点关系个数:" + courseKnowledgeGraphRelationshipVoList.size());
        /*保存图谱信息*/
        Course course = courseService.findById(courseKnowledgeGraphAiGenerateTask.getCourseId());
        List<CourseVersion> courseVersions = courseVersionRepository.findByCourseInAndCourseVersionStatus(
                Collections.singletonList(course), CourseVersionStatus.IN_USE);
        CourseVersion courseVersion = courseVersions.get(0);
        /*判断当前版本图谱知识点个数*/
        CourseKnowledgeGraphDomain effectiveCourseKnowledgeGraphDomain =
                courseKnowledgeGraphService.getCourseKnowledgeGraphDomain(courseKnowledgeGraphAiGenerateTask.getCourseId());
        int nodeCounts = neo4jUtil.getDomainNodeCount(effectiveCourseKnowledgeGraphDomain.getId());
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain = null;
        List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList = new ArrayList<>();
        if (nodeCounts == 1) {
            courseKnowledgeGraphDomain = effectiveCourseKnowledgeGraphDomain;
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
            CourseKnowledgeGraphDomain createCourseKnowledgeGraphDomain =
                    createCourseKnowledgeGraphDomain(course, courseVersion, accessUserId, accessUserName);
            courseKnowledgeGraphDomain = courseKnowledgeGraphDomainRepository.saveAndFlush(createCourseKnowledgeGraphDomain);
            if (dataVisualEnable) {
                KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo = getKnowledgeGraphDomainTopicVo(courseKnowledgeGraphDomain);
                eventService.sendKnowledgeGraphDomainTopicVo(knowledgeGraphDomainTopicVo);
            }
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
        updateCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain, accessUserId, accessUserName);
        courseKnowledgeGraphAiGenerateTaskRepository.delete(courseKnowledgeGraphAiGenerateTask);
        if (dataVisualEnable) {
            sendChildCourseKnowledgeGraphNodeList(
                    accessUserId, accessUserName, childCourseKnowledgeGraphNodeList,
                    courseKnowledgeGraphRelationshipVoList, courseKnowledgeGraphNodeList, courseKnowledgeGraphDomain);
        }
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

    public void updateCourseKnowledgeGraphDomain(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
                                                 String accessUserId, String accessUserName) {
        int nodeCounts = neo4jUtil.getDomainNodeCount(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphDomain.setNodeCount(nodeCounts);
        int relationShipCounts = neo4jUtil.getDomainRelationShipCount(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphDomain.setShipCount(relationShipCounts);
        courseKnowledgeGraphDomain.setModifyTime(new Date());
        courseKnowledgeGraphDomain.setModifierId(accessUserId);
        courseKnowledgeGraphDomain.setModifierName(accessUserName);
        courseKnowledgeGraphDomainRepository.saveAndFlush(courseKnowledgeGraphDomain);
        Course course = courseKnowledgeGraphDomain.getCourse();
        if (nodeCounts > 1) {
            courseCompletionService.saveCourseCompletion(courseKnowledgeGraphDomain.getCourse(), CourseContentTypeEnum.KNOWLEDGE_STRUCTURE,
                    true, accessUserId, accessUserName);
        } else {
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.KNOWLEDGE_STRUCTURE, false, accessUserId,
                    accessUserName);
        }
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

    private void composeCourseKnowledgeGraphData(
            List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList,
            List<CourseKnowledgeGraphRelationshipVo> courseKnowledgeGraphRelationshipVoList,
            JSONObject jsonObject,
            String parentCourseKnowledgeGraphNodeId,
            Integer knowledgeNodeLevelIndex,
            Integer sort) {
        CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
        String knowledgeNodeName = jsonObject.getString("topic");
        courseKnowledgeGraphNode.setId(UUID.randomUUID().toString().replace("-", ""));
        courseKnowledgeGraphNode.setKnowledgeNodeName(knowledgeNodeName);
        courseKnowledgeGraphNode.setKnowledgeNodeLevel(knowledgeNodeLevelIndex);
        courseKnowledgeGraphNode.setSort(sort);
        courseKnowledgeGraphNode.setCreateTime(TimeUtils.getNowDateTime());
        courseKnowledgeGraphNode.setName(knowledgeNodeName);
        courseKnowledgeGraphNodeList.add(courseKnowledgeGraphNode);
        if (StringUtils.isNotBlank(parentCourseKnowledgeGraphNodeId)) {
            CourseKnowledgeGraphRelationshipVo courseKnowledgeGraphRelationshipVo =
                    CourseKnowledgeGraphRelationshipVo.builder().startNodeId(parentCourseKnowledgeGraphNodeId)
                            .endNodeId(courseKnowledgeGraphNode.getId())
                            .endNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName()).build();
            courseKnowledgeGraphRelationshipVoList.add(courseKnowledgeGraphRelationshipVo);
        }
        if (jsonObject.containsKey("children") && jsonObject.getJSONArray("children") != null) {
            JSONArray children = jsonObject.getJSONArray("children");
            for (int i = 0; i < children.size(); i++) {
                Integer childSort = i + 1;
                Integer childKnowledgeNodeLevel = courseKnowledgeGraphNode.getKnowledgeNodeLevel() + 1;
                JSONObject element = children.getJSONObject(i);
                composeCourseKnowledgeGraphData(
                        courseKnowledgeGraphNodeList,
                        courseKnowledgeGraphRelationshipVoList,
                        element,
                        courseKnowledgeGraphNode.getId(),
                        childKnowledgeNodeLevel,
                        childSort);
            }
        }

    }

    public void anomalousOperateCourseKnowledgeGraphAiGenerateTask(
            CourseKnowledgeGraphAiGenerateTask courseKnowledgeGraphAiGenerateTask,
            Integer anomalousOperateStatus, String accessUserId, String accessUserName) {
        if (anomalousOperateStatus == 0) {
            courseKnowledgeGraphAiGenerateTaskRepository.delete(courseKnowledgeGraphAiGenerateTask);
        } else if (anomalousOperateStatus == 1) {
            courseKnowledgeGraphAiGenerateTask.setGraphAiGenerateStatus(GraphAiGenerateStatus.WAITING);
            courseKnowledgeGraphAiGenerateTask.setModifierId(accessUserId);
            courseKnowledgeGraphAiGenerateTask.setModifierName(accessUserName);
            courseKnowledgeGraphAiGenerateTask.setModifyTime(new Date());
            courseKnowledgeGraphAiGenerateTask.setRemark("");
            courseKnowledgeGraphAiGenerateTask.setGraphContent("");
            courseKnowledgeGraphAiGenerateTaskRepository.saveAndFlush(courseKnowledgeGraphAiGenerateTask);
        }
    }

    public void deleteCourseKnowledgeGraphAiGenerateTask(CourseKnowledgeGraphAiGenerateTask courseKnowledgeGraphAiGenerateTask) {
        courseKnowledgeGraphAiGenerateTaskRepository.delete(courseKnowledgeGraphAiGenerateTask);
        courseKnowledgeGraphAiGenerateTaskRepository.flush();
    }
}

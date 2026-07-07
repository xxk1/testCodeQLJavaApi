package com.lztech.site.service.courseknowledgegraph;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.knowledgegraph.*;
import com.lztech.domain.model.knowledgegraph.enums.KnowledgeNodeLevel;
import com.lztech.persistence.repositories.course.CourseResourceKnowledgePointRepository;
import com.lztech.persistence.repositories.courseknowledgegraph.*;
import com.lztech.persistence.repositories.courseobjective.CourseObjectiveRelatedKgNodeRepository;
import com.lztech.persistence.repositories.courseobjective.CourseObjectivesRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeVideoInfoTextModel;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphVersionModel;
import com.lztech.site.viewmodel.event.KnowledgeGraphDomainTopicVo;
import com.lztech.site.viewmodel.event.KnowledgeStructureTopicVo;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.TWO;
import static com.lztech.site.constants.ConstantDataVisual.DATA_VISUAL_SOURCE_CLOUD_CLASSROOM;

@Service
public class CourseKnowledgeGraphLogicService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseKnowledgeGraphLogicService.class);

    @Autowired
    private Neo4jUtil neo4jUtil;

    @Autowired
    private CourseService courseService;

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;

    @Autowired
    private EventService eventService;

    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;
    @Autowired
    private CourseKnowledgeGraphDomainRepository courseKnowledgeGraphDomainRepository;

    @Autowired
    private CourseKnowledgeGraphNodeQuestionRepository courseKnowledgeGraphNodeQuestionRepository;

    @Autowired
    private CourseKnowledgeGraphNodeResourceRepository courseKnowledgeGraphNodeResourceRepository;

    @Autowired
    private CourseKnowledgeGraphNodeResourceFileRepository courseKnowledgeGraphNodeResourceFileRepository;

    @Autowired
    private CourseKnowledgeGraphNodeVideoInfoTextRepository courseKnowledgeGraphNodeVideoInfoTextRepository;
    @Autowired
    private CourseResourceKnowledgePointRepository courseResourceKnowledgePointRepository;
    @Autowired
    private CourseObjectiveRelatedKgNodeRepository courseObjectiveRelatedKgNodeRepository;
    @Autowired
    private CourseObjectivesRepository courseObjectivesRepository;

    public List<CourseKnowledgeGraphNodeVideoInfoTextModel> getCourseKnowledgeGraphNodeVideoInfoTextList(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            String schoolYear, Integer term, String groupId, Double similarityScore) {
        List<CourseKnowledgeGraphNodeVideoInfoTextModel> courseKnowledgeGraphNodeVideoInfoTextModelList = new ArrayList<>();
        List<CourseKnowledgeGraphNodeVideoInfoText> courseKnowledgeGraphNodeVideoInfoTextList = new ArrayList<>();
        if (ObjectUtils.isEmpty(similarityScore)) {
            courseKnowledgeGraphNodeVideoInfoTextList = courseKnowledgeGraphNodeVideoInfoTextRepository.findByData(
                    courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId(),
                    schoolYear, term, groupId
            );
        } else {
            courseKnowledgeGraphNodeVideoInfoTextList = courseKnowledgeGraphNodeVideoInfoTextRepository.findByData(
                    courseKnowledgeGraphDomain.getId(),
                    courseKnowledgeGraphNode.getId(),
                    schoolYear,
                    term,
                    groupId,
                    similarityScore
            );
        }
        List<CourseKnowledgeGraphNodeVideoInfoText> groupTextList = courseKnowledgeGraphNodeVideoInfoTextList
                .stream()
                .filter(t -> t.getGroupId().equals(groupId))
                .sorted(Comparator.comparingDouble(CourseKnowledgeGraphNodeVideoInfoText::getSimilarityScore).reversed())
                .collect(Collectors.toList());
        List<CourseKnowledgeGraphNodeVideoInfoText> distinctList = groupTextList.stream()
                // 按复合键分组 (courseTableDetailId + segment)
                .collect(Collectors.groupingBy(
                        text -> text.getCourseTableDetailId() + "_" + text.getSegment(),
                        // 保留最小 startTimestamp 的记录 (因列表已按 startTimestamp 排序)
                        Collectors.reducing((t1, t2) -> t1)
                ))
                .values().stream()       // 获取分组结果的值
                .filter(Optional::isPresent)  // 过滤空 Optional
                .map(Optional::get)          // 解包 Optional
                .collect(Collectors.toList());

        for (CourseKnowledgeGraphNodeVideoInfoText courseKnowledgeGraphNodeVideoInfoText : distinctList) {
            CourseKnowledgeGraphNodeVideoInfoTextModel courseKnowledgeGraphNodeVideoInfoTextModel =
                    getCourseKnowledgeGraphNodeVideoInfoTextModel(courseKnowledgeGraphNodeVideoInfoText);
            courseKnowledgeGraphNodeVideoInfoTextModelList.add(courseKnowledgeGraphNodeVideoInfoTextModel);
        }
        courseKnowledgeGraphNodeVideoInfoTextModelList = courseKnowledgeGraphNodeVideoInfoTextModelList.stream()
                .sorted(Comparator.comparingDouble(CourseKnowledgeGraphNodeVideoInfoTextModel::getSimilarityScore).reversed())
                .collect(Collectors.toList());
        List<CourseKnowledgeGraphNodeVideoInfoTextModel> handleCourseKnowledgeGraphNodeVideoInfoTextModelList = new ArrayList<>();
        Map<Integer, List<CourseKnowledgeGraphNodeVideoInfoTextModel>> groupCourseKnowledgeGraphNodeVideoInfoTextModelListMap =
                courseKnowledgeGraphNodeVideoInfoTextModelList.stream()
                        .collect(Collectors.groupingBy(CourseKnowledgeGraphNodeVideoInfoTextModel::getIntegerStartTimestamp));
        groupCourseKnowledgeGraphNodeVideoInfoTextModelListMap
                .forEach((integerStartTimestamp, groupCourseKnowledgeGraphNodeVideoInfoTextModelList) -> {
                    if (groupCourseKnowledgeGraphNodeVideoInfoTextModelList.size() > Constant.ONE) {
                        CourseKnowledgeGraphNodeVideoInfoTextModel handleCourseKnowledgeGraphNodeVideoInfoTextModel =
                                groupCourseKnowledgeGraphNodeVideoInfoTextModelList.stream()
                                        .filter(courseKnowledgeGraphNodeVideoInfoTextModel ->
                                                courseKnowledgeGraphNodeVideoInfoTextModel.getTextDataSourceIndex().equals(Constant.ONE))
                                        .findFirst().orElse(null);
                        if (ObjectUtils.isNotEmpty(handleCourseKnowledgeGraphNodeVideoInfoTextModel)) {
                            handleCourseKnowledgeGraphNodeVideoInfoTextModelList.add(handleCourseKnowledgeGraphNodeVideoInfoTextModel);
                        } else {
                            handleCourseKnowledgeGraphNodeVideoInfoTextModelList.add(groupCourseKnowledgeGraphNodeVideoInfoTextModelList.get(0));
                        }
                    } else {
                        handleCourseKnowledgeGraphNodeVideoInfoTextModelList.add(groupCourseKnowledgeGraphNodeVideoInfoTextModelList.get(0));
                    }
                });
        return handleCourseKnowledgeGraphNodeVideoInfoTextModelList
                .stream()
                .sorted(Comparator.comparingDouble(CourseKnowledgeGraphNodeVideoInfoTextModel::getSimilarityScore).reversed())
                .collect(Collectors.toList());
    }

    public List<CourseKnowledgeGraphNodeVideoInfoTextModel> getFilterCourseKnowledgeGraphNodeVideoInfoTextList(
            String courseKnowledgeGraphNodeId, String videoInfoId, Double similarityScore) {

        List<CourseKnowledgeGraphNodeVideoInfoText> courseKnowledgeGraphNodeVideoInfoTextList = new ArrayList<>();
        if (ObjectUtils.isEmpty(similarityScore)) {
            courseKnowledgeGraphNodeVideoInfoTextList =
                    courseKnowledgeGraphNodeVideoInfoTextRepository
                            .findByKnowledgeNodeIdAndVideoInfoId(
                                    courseKnowledgeGraphNodeId,
                                    videoInfoId
                            );
        } else {
            courseKnowledgeGraphNodeVideoInfoTextList =
                    courseKnowledgeGraphNodeVideoInfoTextRepository
                            .findByKnowledgeNodeIdAndVideoInfoIdAndSimilarityScore(
                                    courseKnowledgeGraphNodeId,
                                    videoInfoId,
                                    similarityScore
                            );
        }

        List<CourseKnowledgeGraphNodeVideoInfoTextModel> courseKnowledgeGraphNodeVideoInfoTextModelList = new ArrayList<>();

        for (CourseKnowledgeGraphNodeVideoInfoText courseKnowledgeGraphNodeVideoInfoText : courseKnowledgeGraphNodeVideoInfoTextList) {
            CourseKnowledgeGraphNodeVideoInfoTextModel courseKnowledgeGraphNodeVideoInfoTextModel =
                    getCourseKnowledgeGraphNodeVideoInfoTextModel(courseKnowledgeGraphNodeVideoInfoText);
            courseKnowledgeGraphNodeVideoInfoTextModelList.add(courseKnowledgeGraphNodeVideoInfoTextModel);
        }
        courseKnowledgeGraphNodeVideoInfoTextModelList = courseKnowledgeGraphNodeVideoInfoTextModelList.stream()
                .sorted(Comparator.comparingDouble(CourseKnowledgeGraphNodeVideoInfoTextModel::getStartTimestamp))
                .collect(Collectors.toList());
        List<CourseKnowledgeGraphNodeVideoInfoTextModel> handleCourseKnowledgeGraphNodeVideoInfoTextModelList = new ArrayList<>();
        Map<Integer, List<CourseKnowledgeGraphNodeVideoInfoTextModel>> groupCourseKnowledgeGraphNodeVideoInfoTextModelListMap =
                courseKnowledgeGraphNodeVideoInfoTextModelList.stream()
                        .collect(Collectors.groupingBy(CourseKnowledgeGraphNodeVideoInfoTextModel::getIntegerStartTimestamp));
        groupCourseKnowledgeGraphNodeVideoInfoTextModelListMap
                .forEach((integerStartTimestamp, groupCourseKnowledgeGraphNodeVideoInfoTextModelList) -> {
                    if (groupCourseKnowledgeGraphNodeVideoInfoTextModelList.size() > Constant.ONE) {
                        CourseKnowledgeGraphNodeVideoInfoTextModel handleCourseKnowledgeGraphNodeVideoInfoTextModel =
                                groupCourseKnowledgeGraphNodeVideoInfoTextModelList.stream()
                                        .filter(courseKnowledgeGraphNodeVideoInfoTextModel ->
                                                courseKnowledgeGraphNodeVideoInfoTextModel.getTextDataSourceIndex().equals(Constant.ONE))
                                        .findFirst().orElse(null);
                        if (ObjectUtils.isNotEmpty(handleCourseKnowledgeGraphNodeVideoInfoTextModel)) {
                            handleCourseKnowledgeGraphNodeVideoInfoTextModelList.add(handleCourseKnowledgeGraphNodeVideoInfoTextModel);
                        } else {
                            handleCourseKnowledgeGraphNodeVideoInfoTextModelList.add(groupCourseKnowledgeGraphNodeVideoInfoTextModelList.get(0));
                        }
                    } else {
                        handleCourseKnowledgeGraphNodeVideoInfoTextModelList.add(groupCourseKnowledgeGraphNodeVideoInfoTextModelList.get(0));
                    }
                });
        return handleCourseKnowledgeGraphNodeVideoInfoTextModelList.stream()
                .sorted(Comparator.comparingDouble(CourseKnowledgeGraphNodeVideoInfoTextModel::getStartTimestamp))
                .collect(Collectors.toList());
    }

    public List<CourseKnowledgeGraphVersionModel> getHistoryVersionCourseKnowledgeGraphList(String courseId) {
        List<CourseKnowledgeGraphVersionModel> courseKnowledgeGraphVersionModelList = new ArrayList<>();
        Course course = courseService.findById(courseId);
        CourseVersion courseVersion = courseVersionRepository.findFirstByCourseInAndCourseVersionStatus(
                Collections.singletonList(course), CourseVersionStatus.IN_USE);
        if (ObjectUtils.isNotEmpty(course) && ObjectUtils.isNotEmpty(courseVersion)) {
            String courseVersionId = courseVersion.getId();
            List<CourseKnowledgeGraphDomain> courseKnowledgeGraphDomainList =
                    courseKnowledgeGraphDomainRepository.getOldCourseKnowledgeGraphDomainByCourseIdAndCourseVersionId(courseId, courseVersionId);
            for (CourseKnowledgeGraphDomain courseKnowledgeGraphDomain : courseKnowledgeGraphDomainList) {
                CourseKnowledgeGraphVersionModel courseKnowledgeGraphVersionModel = getCourseKnowledgeGraphVersionModel(courseKnowledgeGraphDomain);
                courseKnowledgeGraphVersionModelList.add(courseKnowledgeGraphVersionModel);
            }
            return courseKnowledgeGraphVersionModelList;

        } else {
            return new ArrayList<>();
        }
    }

    private CourseKnowledgeGraphVersionModel getCourseKnowledgeGraphVersionModel(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        CourseKnowledgeGraphVersionModel courseKnowledgeGraphVersionModel = new CourseKnowledgeGraphVersionModel();
        courseKnowledgeGraphVersionModel.setCourseKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        courseKnowledgeGraphVersionModel.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        courseKnowledgeGraphVersionModel.setCourseCode(courseKnowledgeGraphDomain.getCourseCode());
        courseKnowledgeGraphVersionModel.setCourseName(courseKnowledgeGraphDomain.getCourseName());
        courseKnowledgeGraphVersionModel.setNodeCount(courseKnowledgeGraphDomain.getNodeCount());
        courseKnowledgeGraphVersionModel.setShipCount(courseKnowledgeGraphDomain.getShipCount());
        courseKnowledgeGraphVersionModel.setShowOrder(courseKnowledgeGraphDomain.getShowOrder());
        courseKnowledgeGraphVersionModel.setVersionLabel(courseKnowledgeGraphDomain.getVersionLabel());
        courseKnowledgeGraphVersionModel.setModifierId(courseKnowledgeGraphDomain.getModifierId());
        courseKnowledgeGraphVersionModel.setModifierName(courseKnowledgeGraphDomain.getModifierName());
        courseKnowledgeGraphVersionModel.setModifyTime(DateUtils.formatDate(DateUtils.DATE_TIME_MINUTES,
                Objects.isNull(courseKnowledgeGraphDomain.getModifyTime()) ? courseKnowledgeGraphDomain.getCreateTime() :
                        courseKnowledgeGraphDomain.getModifyTime()));
        return courseKnowledgeGraphVersionModel;
    }

    private static CourseKnowledgeGraphNodeVideoInfoTextModel getCourseKnowledgeGraphNodeVideoInfoTextModel(
            CourseKnowledgeGraphNodeVideoInfoText courseKnowledgeGraphNodeVideoInfoText) {
        CourseKnowledgeGraphNodeVideoInfoTextModel courseKnowledgeGraphNodeVideoInfoTextModel = new CourseKnowledgeGraphNodeVideoInfoTextModel();
        courseKnowledgeGraphNodeVideoInfoTextModel.setId(courseKnowledgeGraphNodeVideoInfoText.getId());
        courseKnowledgeGraphNodeVideoInfoTextModel.setVideoInfoId(courseKnowledgeGraphNodeVideoInfoText.getVideoInfoId());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTextDataSourceIndex(courseKnowledgeGraphNodeVideoInfoText.getTextDataSource().getIndex());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTextDataSourceName(courseKnowledgeGraphNodeVideoInfoText.getTextDataSource().getName());
        courseKnowledgeGraphNodeVideoInfoTextModel.setSchoolYear(courseKnowledgeGraphNodeVideoInfoText.getSchoolYear());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTerm(courseKnowledgeGraphNodeVideoInfoText.getTerm());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTeacherIds(courseKnowledgeGraphNodeVideoInfoText.getTeacherIds());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTeacherNos(courseKnowledgeGraphNodeVideoInfoText.getTeacherNos());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTeacherNames(courseKnowledgeGraphNodeVideoInfoText.getTeacherNames());
        courseKnowledgeGraphNodeVideoInfoTextModel.setVideoDate(courseKnowledgeGraphNodeVideoInfoText.getVideoDate());
        courseKnowledgeGraphNodeVideoInfoTextModel.setSegment(courseKnowledgeGraphNodeVideoInfoText.getSegment());
        courseKnowledgeGraphNodeVideoInfoTextModel.setInnerIp(courseKnowledgeGraphNodeVideoInfoText.getInnerIp());
        courseKnowledgeGraphNodeVideoInfoTextModel.setOuterIp(courseKnowledgeGraphNodeVideoInfoText.getOuterIp());
        courseKnowledgeGraphNodeVideoInfoTextModel.setThumbnailPath(courseKnowledgeGraphNodeVideoInfoText.getThumbnailPath());
        courseKnowledgeGraphNodeVideoInfoTextModel.setKnowledgeNodeId(courseKnowledgeGraphNodeVideoInfoText.getKnowledgeNodeId());
        courseKnowledgeGraphNodeVideoInfoTextModel.setKnowledgeNodeName(courseKnowledgeGraphNodeVideoInfoText.getKnowledgeNodeName());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTextContent(courseKnowledgeGraphNodeVideoInfoText.getTextContent());
        courseKnowledgeGraphNodeVideoInfoTextModel.setHighLightTextContent(courseKnowledgeGraphNodeVideoInfoText.getHighLightTextContent());
        courseKnowledgeGraphNodeVideoInfoTextModel.setStartTimestamp(courseKnowledgeGraphNodeVideoInfoText.getStartTimestamp());
        courseKnowledgeGraphNodeVideoInfoTextModel.setEndTimestamp(courseKnowledgeGraphNodeVideoInfoText.getEndTimestamp());
        courseKnowledgeGraphNodeVideoInfoTextModel.setSimilarityScore(courseKnowledgeGraphNodeVideoInfoText.getSimilarityScore());
        return courseKnowledgeGraphNodeVideoInfoTextModel;
    }


    @Transactional
    public void replyHistoryCourseKnowledgeGraphDomain(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, String accessUserId, String accessUserName) {
        List<CourseKnowledgeGraphNode> oldCourseKnowledgeGraphNodeList =
                neo4jUtil.getCourseKnowledgeGraphDomainAllNodeList(courseKnowledgeGraphDomain.getId());
        List<CourseKnowledgeGraphNodeRelationship> oldCourseKnowledgeGraphNodeRelationshipList =
                neo4jUtil.getCourseKnowledgeGraphDomainAllRelationshipList(courseKnowledgeGraphDomain.getId());
        List<CourseKnowledgeGraphNodeQuestion> oldCourseKnowledgeGraphNodeQuestionList =
                courseKnowledgeGraphNodeQuestionRepository.findByCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
        List<CourseKnowledgeGraphNodeResource> oldCourseKnowledgeGraphNodeResourceList =
                courseKnowledgeGraphNodeResourceRepository.findByCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
        List<CourseKnowledgeGraphNodeVideoInfoText> oldCourseKnowledgeGraphNodeVideoInfoTextList =
                courseKnowledgeGraphNodeVideoInfoTextRepository.findByCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
        List<CourseObjectiveRelatedKgNode> oldCourseObjectiveRelatedKgNodeList =
                courseObjectiveRelatedKgNodeRepository.findByCourseKnowledgeGraphId(courseKnowledgeGraphDomain.getId());
        List<String> nodeIdList = oldCourseKnowledgeGraphNodeList.stream().map(CourseKnowledgeGraphNode::getId).collect(Collectors.toList());
        List<CourseResourceKnowledgePoint> oldCourseResourceKnowledgePointList =
                courseResourceKnowledgePointRepository.findByKnowledgePointIdIn(nodeIdList);
        Course course = courseKnowledgeGraphDomain.getCourse();
        LOGGER.info(">>>courseId->【{}】新知识图谱开始保存", course.getId());
        CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain = courseKnowledgeGraphService.createCourseKnowledgeGraphDomain(
                courseKnowledgeGraphDomain.getCourse(), courseKnowledgeGraphDomain.getCourseVersion(), accessUserId, accessUserName);
        newCourseKnowledgeGraphDomain = courseKnowledgeGraphDomainRepository.saveAndFlush(newCourseKnowledgeGraphDomain);
        if (dataVisualEnable) {
            KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo =
                    courseKnowledgeGraphService.getKnowledgeGraphDomainTopicVo(newCourseKnowledgeGraphDomain);
            eventService.sendKnowledgeGraphDomainTopicVo(knowledgeGraphDomainTopicVo);
        }
        LOGGER.info(">>>courseId->【{}】新知识图谱保存结束", course.getId());
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点开始保存", course.getId());
        Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap = new HashMap<>();
        List<CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeList = new ArrayList<>();
        for (CourseKnowledgeGraphNode courseKnowledgeGraphNode : oldCourseKnowledgeGraphNodeList) {
            CourseKnowledgeGraphNode newCourseKnowledgeGraphNode = getCourseKnowledgeGraphNode(courseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeMap.put(courseKnowledgeGraphNode.getId(), newCourseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeList.add(newCourseKnowledgeGraphNode);
        }
        List<CourseKnowledgeGraphNodeRelationship> newCourseKnowledgeGraphNodeRelationshipList = new ArrayList<>();
        createNewCourseKnowledgeGraphNodeRelationshipList(
                oldCourseKnowledgeGraphNodeRelationshipList, newCourseKnowledgeGraphNodeMap, newCourseKnowledgeGraphNodeRelationshipList);
        CourseKnowledgeGraphNode rootCourseKnowledgeGraphNode = newCourseKnowledgeGraphNodeList.stream().filter(courseKnowledgeGraphNode ->
                courseKnowledgeGraphNode.getKnowledgeNodeLevel() == KnowledgeNodeLevel.ZERO_LEVEL.getIndex()).findFirst().get();
        List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList = newCourseKnowledgeGraphNodeList.stream().filter(courseKnowledgeGraphNode ->
                !courseKnowledgeGraphNode.getId().equals(rootCourseKnowledgeGraphNode.getId())).collect(Collectors.toList());
        /*保存根知识点*/
        neo4jUtil.createRootCourseKnowledgeGraphNode(rootCourseKnowledgeGraphNode, newCourseKnowledgeGraphDomain.getId());

        List<CourseKnowledgeGraphNodeRelationship> parentChildCourseKnowledgeGraphNodeRelationshipList = new ArrayList<>();
        List<CourseKnowledgeGraphNodeRelationship> frontRearKnowledgeGraphRelationshipList = new ArrayList<>();
        List<CourseKnowledgeGraphNodeRelationship> relevanceKnowledgeGraphRelationshipList = new ArrayList<>();
        for (CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship : newCourseKnowledgeGraphNodeRelationshipList) {
            if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("PARENT_CHILD")) {
                parentChildCourseKnowledgeGraphNodeRelationshipList.add(courseKnowledgeGraphNodeRelationship);
            } else if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("FRONT_REAR")) {
                frontRearKnowledgeGraphRelationshipList.add(courseKnowledgeGraphNodeRelationship);
            } else if (courseKnowledgeGraphNodeRelationship.getRelationshipType().equals("RELEVANCE")) {
                relevanceKnowledgeGraphRelationshipList.add(courseKnowledgeGraphNodeRelationship);
            }
        }
        childCourseKnowledgeGraphNodeList =
                childCourseKnowledgeGraphNodeList.stream().sorted(Comparator.comparingInt(CourseKnowledgeGraphNode::getKnowledgeNodeLevel)
                        .thenComparingInt(CourseKnowledgeGraphNode::getSort)).collect(Collectors.toList());
        saveChildCourseKnowledgeGraphNodeList(childCourseKnowledgeGraphNodeList,
                parentChildCourseKnowledgeGraphNodeRelationshipList, newCourseKnowledgeGraphDomain);
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点保存结束", course.getId());
        LOGGER.info(">>>courseId->【{}】新知识图谱相关关系开始保存", course.getId());
        saveNewCourseKnowledgeGraphNodeRelationshipList(
                newCourseKnowledgeGraphDomain, frontRearKnowledgeGraphRelationshipList, relevanceKnowledgeGraphRelationshipList);
        updateCourseKnowledgeGraphDomainData(accessUserId, accessUserName, newCourseKnowledgeGraphDomain);
        LOGGER.info(">>>courseId->【{}】新知识图谱相关关系保存结束", course.getId());
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关题目关系开始保存", course.getId());
        saveNewCourseKnowledgeGraphNodeQuestionList(
                oldCourseKnowledgeGraphNodeQuestionList, newCourseKnowledgeGraphNodeMap, newCourseKnowledgeGraphDomain);
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关题目关系保存结束", course.getId());
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关资源关系开始保存", course.getId());
        saveNewCourseKnowledgeGraphNodeResourceList(
                oldCourseKnowledgeGraphNodeResourceList, newCourseKnowledgeGraphNodeMap, newCourseKnowledgeGraphDomain);
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关资源关系保存结束", course.getId());
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关视频关联信息开始保存", course.getId());
        saveNewCourseKnowledgeGraphNodeVideoInfoTextList(
                oldCourseKnowledgeGraphNodeVideoInfoTextList, newCourseKnowledgeGraphNodeMap, newCourseKnowledgeGraphDomain);
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关视频关联信息保存结束", course.getId());

        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关关联资源开始保存", course.getId());
        saveNewCourseResourceKnowledgePointList(
                oldCourseResourceKnowledgePointList, newCourseKnowledgeGraphNodeMap, newCourseKnowledgeGraphDomain, accessUserId, accessUserName);
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关关联资源保存结束", course.getId());

        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关关联课程目标开始保存", course.getId());
        saveNewCourseObjectiveRelatedKgNodeList(
                oldCourseObjectiveRelatedKgNodeList, newCourseKnowledgeGraphNodeMap, newCourseKnowledgeGraphDomain, course.getId());
        LOGGER.info(">>>courseId->【{}】新知识图谱知识点相关相关关联课程目标保存结束", course.getId());

    }

    private void saveNewCourseObjectiveRelatedKgNodeList(List<CourseObjectiveRelatedKgNode> oldCourseObjectiveRelatedKgNodeList,
                                                         Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap,
                                                         CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
                                                         String courseId) {
        List<CourseObjectiveRelatedKgNode> newCourseObjectiveRelatedKgNodeList = new ArrayList<>();
        for (CourseObjectiveRelatedKgNode courseObjectiveRelatedKgNode : oldCourseObjectiveRelatedKgNodeList) {
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseObjectiveRelatedKgNode.getRelatedKnowledgePointId());
            if (ObjectUtils.isEmpty(nowCourseKnowledgeGraphNode)) {
                continue;
            }
            CourseObjectiveRelatedKgNode newCourseObjectiveRelatedKgNode = new CourseObjectiveRelatedKgNode();
            newCourseObjectiveRelatedKgNode.setCourseObjectiveId(courseObjectiveRelatedKgNode.getCourseObjectiveId());
            newCourseObjectiveRelatedKgNode.setCourseKnowledgeGraphId(newCourseKnowledgeGraphDomain.getId());
            newCourseObjectiveRelatedKgNode.setRelatedKnowledgePointId(nowCourseKnowledgeGraphNode.getId());
            newCourseObjectiveRelatedKgNode.setCreatorId(courseObjectiveRelatedKgNode.getCreatorId());
            newCourseObjectiveRelatedKgNode.setCreatorName(courseObjectiveRelatedKgNode.getCreatorName());
            newCourseObjectiveRelatedKgNode.setCreateTime(courseObjectiveRelatedKgNode.getCreateTime());
            newCourseObjectiveRelatedKgNode.setModifierId(courseObjectiveRelatedKgNode.getModifierId());
            newCourseObjectiveRelatedKgNode.setModifierName(courseObjectiveRelatedKgNode.getModifierName());
            newCourseObjectiveRelatedKgNode.setModifyTime(courseObjectiveRelatedKgNode.getModifyTime());
            newCourseObjectiveRelatedKgNodeList.add(newCourseObjectiveRelatedKgNode);
        }
        courseObjectiveRelatedKgNodeRepository.saveAll(newCourseObjectiveRelatedKgNodeList);

        List<CourseObjective> courseObjectiveList = courseObjectivesRepository.findByCourseIdAndUseState(courseId, true);
        for (CourseObjective courseObjective : courseObjectiveList) {
            int relatedKnowledgePointNum = Math.toIntExact(newCourseObjectiveRelatedKgNodeList.stream()
                    .filter(relatedKgNode -> relatedKgNode.getCourseObjectiveId()
                            .equals(courseObjective.getId()))
                    .count());
            courseObjective.setRelatedKnowledgePointNum(newCourseObjectiveRelatedKgNodeList.size());
            courseObjectivesRepository.save(courseObjective);
        }
        courseObjectivesRepository.saveAll(courseObjectiveList);


    }

    private void saveNewCourseResourceKnowledgePointList(List<CourseResourceKnowledgePoint> oldCourseResourceKnowledgePointList,
                                                         Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap,
                                                         CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
                                                         String accessUserId, String accessUserName) {

        List<CourseResourceKnowledgePoint> newCourseResourceKnowledgePointList = new ArrayList<>();
        for (CourseResourceKnowledgePoint courseResourceKnowledgePoint : oldCourseResourceKnowledgePointList) {
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseResourceKnowledgePoint.getKnowledgePointId());
            if (ObjectUtils.isEmpty(nowCourseKnowledgeGraphNode)) {
                continue;
            }
            CourseResourceKnowledgePoint newCourseKnowledgeGraphNodeVideoInfoText =
                    getCourseResourceKnowledgePoint(courseResourceKnowledgePoint,
                            newCourseKnowledgeGraphDomain, nowCourseKnowledgeGraphNode, accessUserId, accessUserName);
            newCourseResourceKnowledgePointList.add(newCourseKnowledgeGraphNodeVideoInfoText);
        }
        courseResourceKnowledgePointRepository.saveAllAndFlush(newCourseResourceKnowledgePointList);

    }

    private CourseResourceKnowledgePoint getCourseResourceKnowledgePoint(CourseResourceKnowledgePoint oldCourseResourceKnowledgePoint,
                                                                         CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
                                                                         CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode,
                                                                         String accessUserId,
                                                                         String accessUserName) {
        CourseResourceKnowledgePoint newCourseResourceKnowledgePoint = new CourseResourceKnowledgePoint();
        newCourseResourceKnowledgePoint.setKnowledgePointId(nowCourseKnowledgeGraphNode.getId());
        newCourseResourceKnowledgePoint.setResourceId(oldCourseResourceKnowledgePoint.getResourceId());
        newCourseResourceKnowledgePoint.setCourseKnowledgeGraphDomainId(newCourseKnowledgeGraphDomain.getId());
        newCourseResourceKnowledgePoint.setModifierId(accessUserId);
        newCourseResourceKnowledgePoint.setModifierName(accessUserName);
        newCourseResourceKnowledgePoint.setModifyTime(new Date());
        newCourseResourceKnowledgePoint.setCreatorId(accessUserId);
        newCourseResourceKnowledgePoint.setCreatorName(accessUserName);
        newCourseResourceKnowledgePoint.setCreateTime(new Date());
        return newCourseResourceKnowledgePoint;
    }

    private void saveChildCourseKnowledgeGraphNodeList(
            List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList,
            List<CourseKnowledgeGraphNodeRelationship> parentChildCourseKnowledgeGraphNodeRelationshipList,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        for (CourseKnowledgeGraphNode childCourseKnowledgeGraphNode : childCourseKnowledgeGraphNodeList) {
            //查询父子关系
            CourseKnowledgeGraphNodeRelationship parentCourseKnowledgeGraphNodeRelationship =
                    parentChildCourseKnowledgeGraphNodeRelationshipList.stream().filter(filterCourseKnowledgeGraphNodeRelationship ->
                            filterCourseKnowledgeGraphNodeRelationship.getEndNodeId()
                                    .equals(childCourseKnowledgeGraphNode.getId())).findFirst().get();
            neo4jUtil.createChildCourseKnowledgeGraphNode(newCourseKnowledgeGraphDomain.getId(),
                    parentCourseKnowledgeGraphNodeRelationship.getStartNodeId(), childCourseKnowledgeGraphNode);
            if (dataVisualEnable) {
                sendKnowledgeStructureTopicVo(newCourseKnowledgeGraphDomain, childCourseKnowledgeGraphNode,
                        parentCourseKnowledgeGraphNodeRelationship);
            }
        }
    }

    private static void createNewCourseKnowledgeGraphNodeRelationshipList(
            List<CourseKnowledgeGraphNodeRelationship> oldCourseKnowledgeGraphNodeRelationshipList,
            Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap,
            List<CourseKnowledgeGraphNodeRelationship> newCourseKnowledgeGraphNodeRelationshipList) {
        for (CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship : oldCourseKnowledgeGraphNodeRelationshipList) {
            CourseKnowledgeGraphNode startCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseKnowledgeGraphNodeRelationship.getStartNodeId());
            CourseKnowledgeGraphNode endCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseKnowledgeGraphNodeRelationship.getEndNodeId());
            CourseKnowledgeGraphNodeRelationship newCourseKnowledgeGraphNodeRelationship =
                    getCourseKnowledgeGraphNodeRelationship(courseKnowledgeGraphNodeRelationship,
                            startCourseKnowledgeGraphNode, endCourseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeRelationshipList.add(newCourseKnowledgeGraphNodeRelationship);
        }
    }

    private void updateCourseKnowledgeGraphDomainData(
            String accessUserId, String accessUserName, CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        int nodeCounts = neo4jUtil.getDomainNodeCount(newCourseKnowledgeGraphDomain.getId());
        newCourseKnowledgeGraphDomain.setNodeCount(nodeCounts);
        int relationShipCounts = neo4jUtil.getDomainRelationShipCount(newCourseKnowledgeGraphDomain.getId());
        newCourseKnowledgeGraphDomain.setShipCount(relationShipCounts);
        newCourseKnowledgeGraphDomain.setModifyTime(new Date());
        newCourseKnowledgeGraphDomain.setModifierId(accessUserId);
        newCourseKnowledgeGraphDomain.setModifierName(accessUserName);
        courseKnowledgeGraphDomainRepository.saveAndFlush(newCourseKnowledgeGraphDomain);
    }

    private void saveNewCourseKnowledgeGraphNodeRelationshipList(
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
            List<CourseKnowledgeGraphNodeRelationship> frontRearKnowledgeGraphRelationshipList,
            List<CourseKnowledgeGraphNodeRelationship> relevanceKnowledgeGraphRelationshipList) {
        for (CourseKnowledgeGraphNodeRelationship frontRearKnowledgeGraphRelationship : frontRearKnowledgeGraphRelationshipList) {
            neo4jUtil.createFrontRearRelationship(newCourseKnowledgeGraphDomain.getId(),
                    frontRearKnowledgeGraphRelationship.getStartNodeId(),
                    frontRearKnowledgeGraphRelationship.getEndNodeId());
        }
        for (CourseKnowledgeGraphNodeRelationship relevanceKnowledgeGraphRelationship : relevanceKnowledgeGraphRelationshipList) {
            neo4jUtil.createRelevanceRelationship(newCourseKnowledgeGraphDomain.getId(),
                    relevanceKnowledgeGraphRelationship.getStartNodeId(),
                    relevanceKnowledgeGraphRelationship.getEndNodeId());
        }
    }

    private void saveNewCourseKnowledgeGraphNodeQuestionList(
            List<CourseKnowledgeGraphNodeQuestion> oldCourseKnowledgeGraphNodeQuestionList,
            Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap, CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        List<CourseKnowledgeGraphNodeQuestion> newCourseKnowledgeGraphNodeQuestionList = new ArrayList<>();
        for (CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion : oldCourseKnowledgeGraphNodeQuestionList) {
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseKnowledgeGraphNodeQuestion.getKnowledgeNodeId());
            if (ObjectUtils.isEmpty(nowCourseKnowledgeGraphNode)) {
                continue;
            }
            CourseKnowledgeGraphNodeQuestion newCourseKnowledgeGraphNodeQuestion =
                    getCourseKnowledgeGraphNodeQuestion(courseKnowledgeGraphNodeQuestion,
                            newCourseKnowledgeGraphDomain, nowCourseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeQuestionList.add(newCourseKnowledgeGraphNodeQuestion);
        }
        courseKnowledgeGraphNodeQuestionRepository.saveAllAndFlush(newCourseKnowledgeGraphNodeQuestionList);
    }

    private void saveNewCourseKnowledgeGraphNodeResourceList(
            List<CourseKnowledgeGraphNodeResource> oldCourseKnowledgeGraphNodeResourceList,
            Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap, CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        List<CourseKnowledgeGraphNodeResource> newCourseKnowledgeGraphNodeResourceList = new ArrayList<>();
        for (CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource : oldCourseKnowledgeGraphNodeResourceList) {
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(courseKnowledgeGraphNodeResource.getKnowledgeNodeId());
            if (ObjectUtils.isEmpty(nowCourseKnowledgeGraphNode)) {
                continue;
            }
            CourseKnowledgeGraphNodeResourceFile oldCourseKnowledgeGraphNodeResourceFile =
                    courseKnowledgeGraphNodeResourceFileRepository.getCourseKnowledgeGraphNodeResourceFileById(
                            courseKnowledgeGraphNodeResource.getResourceDetailId());
            CourseKnowledgeGraphNodeResourceFile newCourseKnowledgeGraphNodeResourceFile =
                    getCourseKnowledgeGraphNodeResourceFile(oldCourseKnowledgeGraphNodeResourceFile);
            newCourseKnowledgeGraphNodeResourceFile =
                    courseKnowledgeGraphNodeResourceFileRepository.saveAndFlush(newCourseKnowledgeGraphNodeResourceFile);

            CourseKnowledgeGraphNodeResource newCourseKnowledgeGraphNodeResource =
                    getCourseKnowledgeGraphNodeResource(courseKnowledgeGraphNodeResource, newCourseKnowledgeGraphNodeResourceFile,
                            newCourseKnowledgeGraphDomain, nowCourseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeResourceList.add(newCourseKnowledgeGraphNodeResource);
        }
        courseKnowledgeGraphNodeResourceRepository.saveAllAndFlush(newCourseKnowledgeGraphNodeResourceList);
    }

    private void saveNewCourseKnowledgeGraphNodeVideoInfoTextList(
            List<CourseKnowledgeGraphNodeVideoInfoText> oldCourseKnowledgeGraphNodeVideoInfoTextList,
            Map<String, CourseKnowledgeGraphNode> newCourseKnowledgeGraphNodeMap,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain) {
        List<CourseKnowledgeGraphNodeVideoInfoText> newCourseKnowledgeGraphNodeVideoInfoTextList = new ArrayList<>();
        for (CourseKnowledgeGraphNodeVideoInfoText oldCourseKnowledgeGraphNodeVideoInfoText : oldCourseKnowledgeGraphNodeVideoInfoTextList) {
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode =
                    newCourseKnowledgeGraphNodeMap.get(oldCourseKnowledgeGraphNodeVideoInfoText.getKnowledgeNodeId());
            if (ObjectUtils.isEmpty(nowCourseKnowledgeGraphNode)) {
                continue;
            }
            CourseKnowledgeGraphNodeVideoInfoText newCourseKnowledgeGraphNodeVideoInfoText =
                    getCourseKnowledgeGraphNodeVideoInfoText(oldCourseKnowledgeGraphNodeVideoInfoText,
                            newCourseKnowledgeGraphDomain, nowCourseKnowledgeGraphNode);
            newCourseKnowledgeGraphNodeVideoInfoTextList.add(newCourseKnowledgeGraphNodeVideoInfoText);

        }
        courseKnowledgeGraphNodeVideoInfoTextRepository.saveAllAndFlush(newCourseKnowledgeGraphNodeVideoInfoTextList);
    }

    private void sendKnowledgeStructureTopicVo(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, CourseKnowledgeGraphNode childCourseKnowledgeGraphNode,
            CourseKnowledgeGraphNodeRelationship parentCourseKnowledgeGraphNodeRelationship) {
        KnowledgeStructureTopicVo knowledgeStructureTopicVo = new KnowledgeStructureTopicVo();
        knowledgeStructureTopicVo.setId(childCourseKnowledgeGraphNode.getId());
        knowledgeStructureTopicVo.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        knowledgeStructureTopicVo.setKnowledgeStructureType(TWO);
        knowledgeStructureTopicVo.setCourseCode(courseKnowledgeGraphDomain.getCourse().getCourseCode());
        knowledgeStructureTopicVo.setCourseId(courseKnowledgeGraphDomain.getCourse().getId());
        knowledgeStructureTopicVo.setCourseName(courseKnowledgeGraphDomain.getCourse().getCourseName());
        knowledgeStructureTopicVo.setParentId(parentCourseKnowledgeGraphNodeRelationship.getStartNodeId());
        knowledgeStructureTopicVo.setCourseVersionId(courseKnowledgeGraphDomain.getCourseVersion().getId());
        knowledgeStructureTopicVo.setContent(childCourseKnowledgeGraphNode.getKnowledgeNodeName());
        knowledgeStructureTopicVo.setModifierId(childCourseKnowledgeGraphNode.getCreatorId());
        knowledgeStructureTopicVo.setModifierName(childCourseKnowledgeGraphNode.getCreatorName());
        knowledgeStructureTopicVo.setModifyTime(childCourseKnowledgeGraphNode.getCreateTime());
        knowledgeStructureTopicVo.setParentName(parentCourseKnowledgeGraphNodeRelationship.getStartNodeName());
        knowledgeStructureTopicVo.setKnowledgeGraphDomainId(courseKnowledgeGraphDomain.getId());
        eventService.sendAddKnowledgeStructure(knowledgeStructureTopicVo);
    }

    private static CourseKnowledgeGraphNodeVideoInfoText getCourseKnowledgeGraphNodeVideoInfoText(
            CourseKnowledgeGraphNodeVideoInfoText oldCourseKnowledgeGraphNodeVideoInfoText,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain, CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode) {
        CourseKnowledgeGraphNodeVideoInfoText newCourseKnowledgeGraphNodeVideoInfoText = new CourseKnowledgeGraphNodeVideoInfoText();
        newCourseKnowledgeGraphNodeVideoInfoText.setCourseKnowledgeGraphDomain(newCourseKnowledgeGraphDomain);
        newCourseKnowledgeGraphNodeVideoInfoText.setTextDataSource(oldCourseKnowledgeGraphNodeVideoInfoText.getTextDataSource());
        newCourseKnowledgeGraphNodeVideoInfoText.setVideoInfoId(oldCourseKnowledgeGraphNodeVideoInfoText.getVideoInfoId());
        newCourseKnowledgeGraphNodeVideoInfoText.setSchoolYear(oldCourseKnowledgeGraphNodeVideoInfoText.getSchoolYear());
        newCourseKnowledgeGraphNodeVideoInfoText.setTerm(oldCourseKnowledgeGraphNodeVideoInfoText.getTerm());
        newCourseKnowledgeGraphNodeVideoInfoText.setTeacherIds(oldCourseKnowledgeGraphNodeVideoInfoText.getTeacherIds());
        newCourseKnowledgeGraphNodeVideoInfoText.setTeacherNos(oldCourseKnowledgeGraphNodeVideoInfoText.getTeacherNos());
        newCourseKnowledgeGraphNodeVideoInfoText.setTeacherNames(oldCourseKnowledgeGraphNodeVideoInfoText.getTeacherNames());
        newCourseKnowledgeGraphNodeVideoInfoText.setVideoDate(oldCourseKnowledgeGraphNodeVideoInfoText.getVideoDate());
        newCourseKnowledgeGraphNodeVideoInfoText.setCourseTableDetailId(oldCourseKnowledgeGraphNodeVideoInfoText.getCourseTableDetailId());
        newCourseKnowledgeGraphNodeVideoInfoText.setSegment(oldCourseKnowledgeGraphNodeVideoInfoText.getSegment());
        newCourseKnowledgeGraphNodeVideoInfoText.setCourseTableId(oldCourseKnowledgeGraphNodeVideoInfoText.getCourseTableId());
        newCourseKnowledgeGraphNodeVideoInfoText.setGroupId(oldCourseKnowledgeGraphNodeVideoInfoText.getGroupId());
        newCourseKnowledgeGraphNodeVideoInfoText.setCourseId(oldCourseKnowledgeGraphNodeVideoInfoText.getCourseId());
        newCourseKnowledgeGraphNodeVideoInfoText.setInnerIp(oldCourseKnowledgeGraphNodeVideoInfoText.getInnerIp());
        newCourseKnowledgeGraphNodeVideoInfoText.setOuterIp(oldCourseKnowledgeGraphNodeVideoInfoText.getOuterIp());
        newCourseKnowledgeGraphNodeVideoInfoText.setThumbnailPath(oldCourseKnowledgeGraphNodeVideoInfoText.getThumbnailPath());
        newCourseKnowledgeGraphNodeVideoInfoText.setKnowledgeNodeId(nowCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeVideoInfoText.setKnowledgeNodeName(nowCourseKnowledgeGraphNode.getKnowledgeNodeName());
        newCourseKnowledgeGraphNodeVideoInfoText.setTextContent(oldCourseKnowledgeGraphNodeVideoInfoText.getTextContent());
        newCourseKnowledgeGraphNodeVideoInfoText.setHighLightTextContent(oldCourseKnowledgeGraphNodeVideoInfoText.getHighLightTextContent());
        newCourseKnowledgeGraphNodeVideoInfoText.setStartTimestamp(oldCourseKnowledgeGraphNodeVideoInfoText.getStartTimestamp());
        newCourseKnowledgeGraphNodeVideoInfoText.setEndTimestamp(oldCourseKnowledgeGraphNodeVideoInfoText.getEndTimestamp());
        newCourseKnowledgeGraphNodeVideoInfoText.setCreatorId(oldCourseKnowledgeGraphNodeVideoInfoText.getCreatorId());
        newCourseKnowledgeGraphNodeVideoInfoText.setCreatorName(oldCourseKnowledgeGraphNodeVideoInfoText.getCreatorName());
        newCourseKnowledgeGraphNodeVideoInfoText.setCreateTime(oldCourseKnowledgeGraphNodeVideoInfoText.getCreateTime());
        newCourseKnowledgeGraphNodeVideoInfoText.setSimilarityScore(oldCourseKnowledgeGraphNodeVideoInfoText.getSimilarityScore());
        return newCourseKnowledgeGraphNodeVideoInfoText;
    }

    private static CourseKnowledgeGraphNodeResource getCourseKnowledgeGraphNodeResource(
            CourseKnowledgeGraphNodeResource courseKnowledgeGraphNodeResource,
            CourseKnowledgeGraphNodeResourceFile newCourseKnowledgeGraphNodeResourceFile,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode) {
        CourseKnowledgeGraphNodeResource newCourseKnowledgeGraphNodeResource = new CourseKnowledgeGraphNodeResource();
        newCourseKnowledgeGraphNodeResource.setGraphNodeResourceStatus(courseKnowledgeGraphNodeResource.getGraphNodeResourceStatus());
        newCourseKnowledgeGraphNodeResource.setResourceType(courseKnowledgeGraphNodeResource.getResourceType());
        newCourseKnowledgeGraphNodeResource.setResourceDetailId(newCourseKnowledgeGraphNodeResourceFile.getId());
        newCourseKnowledgeGraphNodeResource.setResourceName(courseKnowledgeGraphNodeResource.getResourceName());
        newCourseKnowledgeGraphNodeResource.setResourceSize(courseKnowledgeGraphNodeResource.getResourceSize());
        newCourseKnowledgeGraphNodeResource.setResourceContentNum(courseKnowledgeGraphNodeResource.getResourceContentNum());
        newCourseKnowledgeGraphNodeResource.setCourseKnowledgeGraphDomain(newCourseKnowledgeGraphDomain);
        newCourseKnowledgeGraphNodeResource.setKnowledgeNodeId(nowCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeResource.setSort(courseKnowledgeGraphNodeResource.getSort());
        newCourseKnowledgeGraphNodeResource.setModifierId(courseKnowledgeGraphNodeResource.getModifierId());
        newCourseKnowledgeGraphNodeResource.setModifierName(courseKnowledgeGraphNodeResource.getModifierName());
        newCourseKnowledgeGraphNodeResource.setModifyTime(courseKnowledgeGraphNodeResource.getModifyTime());
        newCourseKnowledgeGraphNodeResource.setCreatorId(courseKnowledgeGraphNodeResource.getCreatorId());
        newCourseKnowledgeGraphNodeResource.setCreatorName(courseKnowledgeGraphNodeResource.getCreatorName());
        newCourseKnowledgeGraphNodeResource.setCreateTime(courseKnowledgeGraphNodeResource.getCreateTime());
        return newCourseKnowledgeGraphNodeResource;
    }

    private static CourseKnowledgeGraphNodeResourceFile getCourseKnowledgeGraphNodeResourceFile(
            CourseKnowledgeGraphNodeResourceFile oldCourseKnowledgeGraphNodeResourceFile) {
        CourseKnowledgeGraphNodeResourceFile newCourseKnowledgeGraphNodeResourceFile = new CourseKnowledgeGraphNodeResourceFile();
        newCourseKnowledgeGraphNodeResourceFile.setFileDisplayName(oldCourseKnowledgeGraphNodeResourceFile.getFileDisplayName());
        newCourseKnowledgeGraphNodeResourceFile.setFileRealName(oldCourseKnowledgeGraphNodeResourceFile.getFileRealName());
        newCourseKnowledgeGraphNodeResourceFile.setFileType(oldCourseKnowledgeGraphNodeResourceFile.getFileType());
        newCourseKnowledgeGraphNodeResourceFile.setInnerUrl(oldCourseKnowledgeGraphNodeResourceFile.getInnerUrl());
        newCourseKnowledgeGraphNodeResourceFile.setOuterUrl(oldCourseKnowledgeGraphNodeResourceFile.getOuterUrl());
        newCourseKnowledgeGraphNodeResourceFile.setFilePath(oldCourseKnowledgeGraphNodeResourceFile.getFilePath());
        newCourseKnowledgeGraphNodeResourceFile.setFileSize(oldCourseKnowledgeGraphNodeResourceFile.getFileSize());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeStatus(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeStatus());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeMessage(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeMessage());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeInnerIp(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeInnerIp());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeOuterIp(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeOuterIp());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeFileType(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeFileType());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeFilePath(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeFilePath());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeFileSavedName(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeFileSavedName());
        newCourseKnowledgeGraphNodeResourceFile.setTranscodeFileSize(oldCourseKnowledgeGraphNodeResourceFile.getTranscodeFileSize());
        newCourseKnowledgeGraphNodeResourceFile.setModifierId(oldCourseKnowledgeGraphNodeResourceFile.getModifierId());
        newCourseKnowledgeGraphNodeResourceFile.setModifierName(oldCourseKnowledgeGraphNodeResourceFile.getModifierName());
        newCourseKnowledgeGraphNodeResourceFile.setModifyTime(oldCourseKnowledgeGraphNodeResourceFile.getModifyTime());
        newCourseKnowledgeGraphNodeResourceFile.setCreatorId(oldCourseKnowledgeGraphNodeResourceFile.getCreatorId());
        newCourseKnowledgeGraphNodeResourceFile.setCreatorName(oldCourseKnowledgeGraphNodeResourceFile.getCreatorName());
        newCourseKnowledgeGraphNodeResourceFile.setCreateTime(oldCourseKnowledgeGraphNodeResourceFile.getCreateTime());
        return newCourseKnowledgeGraphNodeResourceFile;
    }

    private static CourseKnowledgeGraphNodeQuestion getCourseKnowledgeGraphNodeQuestion(
            CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion,
            CourseKnowledgeGraphDomain newCourseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode nowCourseKnowledgeGraphNode) {
        CourseKnowledgeGraphNodeQuestion newCourseKnowledgeGraphNodeQuestion = new CourseKnowledgeGraphNodeQuestion();
        newCourseKnowledgeGraphNodeQuestion.setCourseKnowledgeGraphDomain(newCourseKnowledgeGraphDomain);
        newCourseKnowledgeGraphNodeQuestion.setKnowledgeNodeId(nowCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeQuestion.setQuestionId(courseKnowledgeGraphNodeQuestion.getQuestionId());
        newCourseKnowledgeGraphNodeQuestion.setSort(courseKnowledgeGraphNodeQuestion.getSort());
        newCourseKnowledgeGraphNodeQuestion.setCreatorId(courseKnowledgeGraphNodeQuestion.getCreatorId());
        newCourseKnowledgeGraphNodeQuestion.setCreatorName(courseKnowledgeGraphNodeQuestion.getCreatorName());
        newCourseKnowledgeGraphNodeQuestion.setCreateTime(courseKnowledgeGraphNodeQuestion.getCreateTime());
        return newCourseKnowledgeGraphNodeQuestion;
    }

    private static CourseKnowledgeGraphNode getCourseKnowledgeGraphNode(CourseKnowledgeGraphNode courseKnowledgeGraphNode) {
        CourseKnowledgeGraphNode newCourseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
        newCourseKnowledgeGraphNode.setId(UUID.randomUUID().toString().replace("-", ""));
        newCourseKnowledgeGraphNode.setKnowledgeNodeName(courseKnowledgeGraphNode.getKnowledgeNodeName());
        newCourseKnowledgeGraphNode.setLevelType(courseKnowledgeGraphNode.getLevelType());
        newCourseKnowledgeGraphNode.setContentDetail(courseKnowledgeGraphNode.getContentDetail());
        newCourseKnowledgeGraphNode.setKnowledgeNodeLevel(courseKnowledgeGraphNode.getKnowledgeNodeLevel());
        newCourseKnowledgeGraphNode.setSort(courseKnowledgeGraphNode.getSort());
        newCourseKnowledgeGraphNode.setCreateTime(courseKnowledgeGraphNode.getCreateTime());
        newCourseKnowledgeGraphNode.setCreatorId(courseKnowledgeGraphNode.getCreatorId());
        newCourseKnowledgeGraphNode.setCreatorName(courseKnowledgeGraphNode.getCreatorName());
        newCourseKnowledgeGraphNode.setModifyTime(courseKnowledgeGraphNode.getModifyTime());
        newCourseKnowledgeGraphNode.setModifierId(courseKnowledgeGraphNode.getModifierId());
        newCourseKnowledgeGraphNode.setModifierName(courseKnowledgeGraphNode.getModifierName());
        newCourseKnowledgeGraphNode.setName(courseKnowledgeGraphNode.getName());
        newCourseKnowledgeGraphNode.setContentGenerationModeIndex(courseKnowledgeGraphNode.getContentGenerationModeIndex());
        newCourseKnowledgeGraphNode.setNodeGenerationModeIndex(courseKnowledgeGraphNode.getNodeGenerationModeIndex());
        return newCourseKnowledgeGraphNode;
    }

    private static CourseKnowledgeGraphNodeRelationship getCourseKnowledgeGraphNodeRelationship(
            CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship,
            CourseKnowledgeGraphNode startCourseKnowledgeGraphNode,
            CourseKnowledgeGraphNode endCourseKnowledgeGraphNode) {
        CourseKnowledgeGraphNodeRelationship newCourseKnowledgeGraphNodeRelationship = new CourseKnowledgeGraphNodeRelationship();
        newCourseKnowledgeGraphNodeRelationship.setStartNodeId(startCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeRelationship.setStartNodeName(startCourseKnowledgeGraphNode.getKnowledgeNodeName());
        newCourseKnowledgeGraphNodeRelationship.setStartKnowledgeNodeLevel(courseKnowledgeGraphNodeRelationship.getStartKnowledgeNodeLevel());
        newCourseKnowledgeGraphNodeRelationship.setEndNodeId(endCourseKnowledgeGraphNode.getId());
        newCourseKnowledgeGraphNodeRelationship.setEndNodeName(endCourseKnowledgeGraphNode.getKnowledgeNodeName());
        newCourseKnowledgeGraphNodeRelationship.setEndKnowledgeNodeLevel(courseKnowledgeGraphNodeRelationship.getEndKnowledgeNodeLevel());
        newCourseKnowledgeGraphNodeRelationship.setRelationshipId(courseKnowledgeGraphNodeRelationship.getRelationshipId());
        newCourseKnowledgeGraphNodeRelationship.setRelationshipType(courseKnowledgeGraphNodeRelationship.getRelationshipType());
        newCourseKnowledgeGraphNodeRelationship.setRelationshipName(courseKnowledgeGraphNodeRelationship.getRelationshipName());
        newCourseKnowledgeGraphNodeRelationship.setRelationshipCreateTimestamp(courseKnowledgeGraphNodeRelationship.getRelationshipCreateTimestamp());
        return newCourseKnowledgeGraphNodeRelationship;
    }
}

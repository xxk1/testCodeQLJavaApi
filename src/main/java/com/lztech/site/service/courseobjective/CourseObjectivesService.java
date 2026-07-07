package com.lztech.site.service.courseobjective;

import com.lztech.domain.model.course.CourseObjective;
import com.lztech.domain.model.course.CourseObjectiveRelatedKgNode;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeRelationship;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphDomainRepository;
import com.lztech.persistence.repositories.courseobjective.CourseObjectiveRelatedKgNodeRepository;
import com.lztech.persistence.repositories.courseobjective.CourseObjectivesRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeTreeModel;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphRelationshipModel;
import com.lztech.site.viewmodel.courseobjective.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseObjectivesService {

    @Autowired
    private CourseObjectivesRepository courseObjectivesRepository;
    @Autowired
    private CourseObjectiveRelatedKgNodeRepository courseObjectiveRelatedKgNodeRepository;
    @Autowired
    private CourseKnowledgeGraphDomainRepository courseKnowledgeGraphDomainRepository;
    @Autowired
    private Neo4jUtil neo4jUtil;

    public Result createCourseObjective(CourseObjectiveCreateRequest param) {
        int maxShowOrder = courseObjectivesRepository.findMaxShowOrder(param.getCourseId());
        Date now = new Date();
        CourseObjective courseObjective = new CourseObjective();
        courseObjective.setName(param.getName());
        courseObjective.setContent(param.getContent());
        courseObjective.setShowOrder(maxShowOrder + 1);
        courseObjective.setCourseId(param.getCourseId());
        courseObjective.setCreatorId(param.getOperatorId());
        courseObjective.setCreatorName(param.getOperatorName());
        courseObjective.setCreatorNo(param.getOperatorNo());
        courseObjective.setUseState(true);
        courseObjective.setCreateTime(now);
        courseObjective.setModifierId(param.getOperatorId());
        courseObjective.setModifierName(param.getOperatorName());
        courseObjective.setModifierNo(param.getOperatorNo());
        courseObjective.setModifyTime(now);
        courseObjective = courseObjectivesRepository.saveAndFlush(courseObjective);
        CourseObjectiveResponse courseObjectiveResponse = new CourseObjectiveResponse();
        courseObjectiveResponse.setId(courseObjective.getId());
        return Result.success().setData(courseObjectiveResponse);
    }

    public CourseObjectiveMainInfo queryCourseObjective(String courseId) {
        List<CourseObjective> courseObjectiveList = courseObjectivesRepository.findByCourseIdAndUseState(courseId,
                true);

        CourseObjectiveMainInfo courseObjectiveMainInfo = new CourseObjectiveMainInfo();
        courseObjectiveMainInfo.setCourseId(courseId);
        courseObjectiveMainInfo.setObjectiveCount(0);
        courseObjectiveMainInfo.setTotalRelatedKnowledgePointNum(0);
        courseObjectiveMainInfo.setObjectiveList(new ArrayList<>());
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                courseKnowledgeGraphDomainRepository.getEffectiveCourseKnowledgeGraphDomainByCourseId(courseId);
        if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
            return courseObjectiveMainInfo;
        }
        List<CourseObjectiveRelatedKgNode> relatedKgNodeList = courseObjectiveRelatedKgNodeRepository
                .findByCourseKnowledgeGraphId(courseKnowledgeGraphDomain.getId());
        if (CollectionUtils.isNotEmpty(courseObjectiveList)) {
            courseObjectiveList = courseObjectiveList.stream()
                    .sorted(Comparator.comparing(CourseObjective::getShowOrder, Comparator.naturalOrder())
                            .thenComparing(CourseObjective::getCreateTime, Comparator.reverseOrder()))
                    .collect(Collectors.toList());
            courseObjectiveMainInfo.setObjectiveCount(courseObjectiveList.size());
            List<CourseObjectiveResponse> courseObjectiveResponseList = new ArrayList<>();
            for (CourseObjective courseObjective : courseObjectiveList) {
                int relatedKnowledgePointNum = (int) relatedKgNodeList.stream()
                        .filter(relatedKgNode -> relatedKgNode.getCourseObjectiveId()
                                .equals(courseObjective.getId()))
                        .count();
                CourseObjectiveResponse courseObjectiveResponse = new CourseObjectiveResponse();
                courseObjectiveResponse.setId(courseObjective.getId());
                courseObjectiveResponse.setName(courseObjective.getName());
                courseObjectiveResponse.setContent(courseObjective.getContent());
                courseObjectiveResponse.setShowOrder(courseObjective.getShowOrder());
                courseObjectiveResponse.setRelatedKnowledgePointNum(relatedKnowledgePointNum);
                courseObjectiveResponseList.add(courseObjectiveResponse);
            }
            courseObjectiveMainInfo.setObjectiveList(courseObjectiveResponseList);
            courseObjectiveMainInfo.setTotalRelatedKnowledgePointNum((int) relatedKgNodeList
                    .stream().map(CourseObjectiveRelatedKgNode::getRelatedKnowledgePointId).distinct().count());
        }
        return courseObjectiveMainInfo;
    }

    public Result modifyCourseObjective(String id, CourseObjectiveUpdateRequest body) {
        CourseObjective courseObjective = courseObjectivesRepository.findById(id).orElse(null);
        if (Objects.isNull(courseObjective)) {
            return Result.error("课程目标不存在");
        }
        courseObjective.setName(body.getName());
        courseObjective.setContent(body.getContent());
        courseObjective.setModifierId(body.getOperatorId());
        courseObjective.setModifierName(body.getOperatorName());
        courseObjective.setModifierNo(body.getOperatorNo());
        courseObjective.setModifyTime(new Date());
        courseObjectivesRepository.save(courseObjective);
        return Result.success();
    }

    @Transactional
    public Result deleteCourseObjective(String id, CourseObjectiveUpdateRequest body) {
        CourseObjective courseObjective = courseObjectivesRepository.findById(id).orElse(null);
        if (Objects.isNull(courseObjective)) {
            return Result.error("课程目标不存在");
        }
        courseObjective.setUseState(false);
        courseObjective.setModifierId(body.getOperatorId());
        courseObjective.setModifierName(body.getOperatorName());
        courseObjective.setModifierNo(body.getOperatorNo());
        courseObjective.setModifyTime(new Date());
        courseObjectivesRepository.save(courseObjective);
        // 删除关联的知识点
        courseObjectiveRelatedKgNodeRepository.deleteByCourseObjectiveId(id);
        return Result.success();
    }

    public Result sortCourseObjective(CourseObjectiveSortRequest param) {
        List<CourseObjective> courseObjectiveList = courseObjectivesRepository.findByCourseIdAndUseState(
                param.getCourseId(), true);
        if (CollectionUtils.isEmpty(courseObjectiveList)) {
            return Result.error("课程目标不存在");
        }

        courseObjectiveList.forEach(courseObjective -> {
            CourseObjectiveSortRequestSortList sort = param.getSortList()
                    .stream().filter(s -> s.getId().equals(courseObjective.getId()))
                    .findFirst().orElse(null);
            if (Objects.nonNull(sort)) {
                courseObjective.setShowOrder(sort.getShowOrder());
                courseObjective.setModifierId(param.getOperatorId());
                courseObjective.setModifierName(param.getOperatorName());
                courseObjective.setModifyTime(new Date());
                courseObjective.setModifierNo(param.getOperatorNo());
            }
        });
        courseObjectivesRepository.saveAll(courseObjectiveList);
        return Result.success();
    }

    @Transactional
    public Result relatedCourseObjectiveKnowledgeGraphNode(CourseObjectiveRelatedKnowledgeGraphNodeRequest param,
                                                           String id) {
        CourseObjective courseObjective = courseObjectivesRepository.findById(id).orElse(null);
        if (Objects.isNull(courseObjective) || !courseObjective.getUseState()) {
            return Result.error("课程目标不存在");
        }
        List<CourseObjectiveRelatedKnowledgePoint> relatedKnowledgePointList =
                Objects.isNull(param.getRelatedKnowledgePointList()) ? new ArrayList<>()
                        : param.getRelatedKnowledgePointList();


        // 3. 获取现有关联关系
        List<CourseObjectiveRelatedKgNode> existingRelations = courseObjectiveRelatedKgNodeRepository
                .findByCourseObjectivesIdAndKnowledgeGraphId(id, param.getCourseKnowledgeGraphId());

        // 4. 提取传入的知识点ID
        Set<String> incomingKnowledgePointIds = relatedKnowledgePointList.stream()
                .map(CourseObjectiveRelatedKnowledgePoint::getKnowledgePointId)
                .collect(Collectors.toSet());

        // 5. 提取现有的知识点ID
        Set<String> existingKnowledgePointIds = existingRelations.stream()
                .map(CourseObjectiveRelatedKgNode::getRelatedKnowledgePointId)
                .collect(Collectors.toSet());

        // 6. 找出需要新增的关联
        List<CourseObjectiveRelatedKgNode> relationsToAdd = incomingKnowledgePointIds.stream()
                .filter(kpId -> !existingKnowledgePointIds.contains(kpId))
                .map(kpId -> createNewRelation(param, id, kpId))
                .collect(Collectors.toList());

        // 7. 找出需要删除的关联
        List<CourseObjectiveRelatedKgNode> relationsToRemove = existingRelations.stream()
                .filter(relation -> !incomingKnowledgePointIds.contains(relation.getRelatedKnowledgePointId()))
                .collect(Collectors.toList());

        // 8. 执行数据库操作
        if (!relationsToRemove.isEmpty()) {
            courseObjectiveRelatedKgNodeRepository.deleteAll(relationsToRemove);
        }

        if (!relationsToAdd.isEmpty()) {
            courseObjectiveRelatedKgNodeRepository.saveAll(relationsToAdd);
        }

        // 9. 更新关联数量（重新查询确保准确性）
        long currentRelationCount = courseObjectiveRelatedKgNodeRepository
                .countByCourseObjectiveIdAndCourseKnowledgeGraphId(id, param.getCourseKnowledgeGraphId());
        courseObjective.setRelatedKnowledgePointNum((int) currentRelationCount);
        courseObjectivesRepository.save(courseObjective);
        return Result.success();
    }

    // 提取创建新关联的公共方法
    private CourseObjectiveRelatedKgNode createNewRelation(CourseObjectiveRelatedKnowledgeGraphNodeRequest param,
                                                           String courseObjectiveId, String knowledgePointId) {
        CourseObjectiveRelatedKgNode relation = new CourseObjectiveRelatedKgNode();
        relation.setCourseKnowledgeGraphId(param.getCourseKnowledgeGraphId());
        relation.setCourseObjectiveId(courseObjectiveId);
        relation.setRelatedKnowledgePointId(knowledgePointId);
        relation.setCreatorId(param.getOperatorId());
        relation.setCreatorName(param.getOperatorName());
        relation.setCreatorNo(param.getOperatorNo());
        relation.setCreateTime(new Date());
        relation.setModifierId(param.getOperatorId());
        relation.setModifierName(param.getOperatorName());
        relation.setModifierNo(param.getOperatorNo());
        relation.setModifyTime(new Date());
        return relation;
    }

    public CourseObjectiveRelatedKnowledgeGraphNodeResult getCourseObjectiveRelatedKnowledgeGraphNode(String id) {
        CourseObjectiveRelatedKnowledgeGraphNodeResult result = new CourseObjectiveRelatedKnowledgeGraphNodeResult();
        result.setCourseKnowledgeGraphId(id);
        result.setRelatedKnowledgePointNum(0);
        result.setNodeList(new ArrayList<>());
        result.setRelatedKnowledgePointList(new ArrayList<>());
        result.setParentChildKnowledgeGraphRelationshipList(new ArrayList<>());
        CourseObjective courseObjective = courseObjectivesRepository.findById(id).orElse(null);
        if (Objects.isNull(courseObjective) || !courseObjective.getUseState()) {
            return result;
        }
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                courseKnowledgeGraphDomainRepository.getEffectiveCourseKnowledgeGraphDomainByCourseId(courseObjective.getCourseId());
        if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
            return result;
        }
        result.setCourseKnowledgeGraphId(courseKnowledgeGraphDomain.getId());
        List<CourseObjectiveRelatedKgNode> relatedKgNodeList = courseObjectiveRelatedKgNodeRepository
                .findByCourseObjectivesIdAndKnowledgeGraphId(id, courseKnowledgeGraphDomain.getId());
        if (CollectionUtils.isEmpty(relatedKgNodeList)) {
            return result;
        }
        result.setRelatedKnowledgePointNum(relatedKgNodeList.size());
        List<String> relatedKnowledgePointIdList = relatedKgNodeList.stream()
                .map(CourseObjectiveRelatedKgNode::getRelatedKnowledgePointId)
                .distinct()
                .collect(Collectors.toList());

        List<CourseKnowledgeGraphNodeTreeModel> nodeTreeList = neo4jUtil
                .getCourseKnowledgeGraphNodeTreeList(courseKnowledgeGraphDomain.getId(), relatedKnowledgePointIdList);

        List<CourseKnowledgeGraphNodeTreeModel> nodeList = neo4jUtil
                .getCourseKnowledgeGraphNodeListByIdList(courseKnowledgeGraphDomain.getId(), relatedKnowledgePointIdList);
        List<CourseObjectiveRelatedKnowledgePoint> relatedKnowledgePointList = new ArrayList<>();


        List<CourseKnowledgeGraphRelationshipModel> parentChildKnowledgeGraphRelationshipList = new ArrayList<>();
        List<CourseKnowledgeGraphNodeRelationship> nodeRelationshipList = neo4jUtil.
                getCourseKnowledgeGraphRelationshipListByIdList(courseKnowledgeGraphDomain.getId(),
                        relatedKnowledgePointIdList);
        nodeRelationshipList.forEach(relationship -> {
            CourseKnowledgeGraphRelationshipModel courseKnowledgeGraphRelationshipModel =
                    getCourseKnowledgeGraphRelationshipModel(relationship, courseKnowledgeGraphDomain);
            if ("PARENT_CHILD".equals(relationship.getRelationshipType()) && relatedKnowledgePointIdList.contains(relationship.getStartNodeId())) {
                parentChildKnowledgeGraphRelationshipList.add(courseKnowledgeGraphRelationshipModel);
            }
        });
        relatedKgNodeList.forEach(relatedKgNode -> {
            CourseObjectiveRelatedKnowledgePoint relatedKnowledgePoint = new CourseObjectiveRelatedKnowledgePoint();
            relatedKnowledgePoint.setKnowledgePointId(relatedKgNode.getRelatedKnowledgePointId());
            CourseKnowledgeGraphNodeTreeModel treeModel = nodeList.stream()
                    .filter(node -> node.getId().equals(relatedKgNode.getRelatedKnowledgePointId()))
                    .findFirst()
                    .orElse(null);
            relatedKnowledgePoint.setKnowledgePointName(Objects.isNull(treeModel) ? "" : treeModel.getKnowledgeNodeName());
            relatedKnowledgePoint.setKnowledgeNodeLevelIndex(Objects.isNull(treeModel) ? 0 : treeModel.getKnowledgeNodeLevelIndex());
            relatedKnowledgePoint.setKnowledgeNodeLevelName(Objects.isNull(treeModel) ? "" : treeModel.getKnowledgeNodeLevelName());
            relatedKnowledgePoint.setCreateTime(DateUtils.formatDate(DateUtils.DATE_TIME,
                    Objects.isNull(relatedKgNode.getCreateTime()) ? new Date() : relatedKgNode.getCreateTime()));
            relatedKnowledgePoint.setCourseKnowledgeGraphId(courseKnowledgeGraphDomain.getId());
            relatedKnowledgePoint.setParentId("root");
            if (nodeRelationshipList.stream().anyMatch(
                    n -> n.getEndNodeId().equals(relatedKgNode.getRelatedKnowledgePointId()))) {
                CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphRelationshipModel =
                        nodeRelationshipList.stream()
                                .filter(n -> n.getEndNodeId().equals(relatedKgNode.getRelatedKnowledgePointId()))
                                .findFirst()
                                .orElse(null);
                relatedKnowledgePoint.setParentId(Objects.isNull(courseKnowledgeGraphRelationshipModel) ||
                        !relatedKnowledgePointIdList.contains(courseKnowledgeGraphRelationshipModel.getStartNodeId()) ? "root" :
                        courseKnowledgeGraphRelationshipModel.getStartNodeId());
            }
            relatedKnowledgePointList.add(relatedKnowledgePoint);

        });
        result.setParentChildKnowledgeGraphRelationshipList(parentChildKnowledgeGraphRelationshipList);
        result.setRelatedKnowledgePointList(relatedKnowledgePointList
                .stream()
                .sorted(Comparator.comparing(CourseObjectiveRelatedKnowledgePoint::getCreateTime))
                .collect(Collectors.toList()));
        result.setCourseObjectiveId(id);
        result.setNodeList(nodeTreeList);
        return result;
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

}

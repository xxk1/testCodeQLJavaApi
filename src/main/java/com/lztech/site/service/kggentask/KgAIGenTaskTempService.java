package com.lztech.site.service.kggentask;

import com.lztech.domain.model.course.TeachingMaterialFile;
import com.lztech.domain.model.kggentask.*;
import com.lztech.domain.model.kggentask.enums.KgGenTaskMasterStatus;
import com.lztech.domain.model.kggentask.enums.KgGenTaskStepCode;
import com.lztech.domain.model.kggentask.enums.KgGenTaskSubStatus;
import com.lztech.domain.model.kggentask.enums.KgGenTaskType;
import com.lztech.domain.model.knowledgegraph.enums.KnowledgeNodeLevel;
import com.lztech.domain.model.knowledgegraph.enums.LevelType;
import com.lztech.persistence.repositories.course.TeachingMaterialFileRepository;
import com.lztech.persistence.repositories.kggentask.*;
import com.lztech.persistence.repositories.kggentask.specification.KgGenNodeVideoInfoSpecification;
import com.lztech.site.constants.Constant;
import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.constants.Result;
import com.lztech.site.exception.NodeLevelExceededException;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphActionLogService;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphService;
import com.lztech.site.until.ListPageHelper;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.until.UUIDGenerator;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeVideoInfoTextModel;
import com.lztech.site.viewmodel.questionlibrary.QuestionLibraryResource;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTaskCreateStatusModel;
import com.lztech.site.viewmodel.courseknowledgegraphaigeneratetask.CourseknowledgegraphaigeneratetaskCreateParam;
import com.lztech.site.viewmodel.kggentask.*;
import com.lztech.site.viewmodel.kggentask.enums.KgAIGenSubTaskOperationType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.constants.Constant.KNOWLEDGE_NODE_MAX_LEVEL;
import static com.lztech.site.until.UUIDGenerator.generateUUIDWithoutHyphens;

@Service
public class KgAIGenTaskTempService {

    @Autowired
    private KgGenTaskMasterRepository kgGenTaskMasterRepository;
    @Autowired
    private KgGenTaskSubRepository kgGenTaskSubRepository;
    @Autowired
    private TeachingMaterialFileRepository teachingMaterialFileRepository;
    @Autowired
    private KgGenNodeRepository kgGenNodeRepository;
    @Autowired
    private KgGenNodeResourceRelatedStatisticsRepository kgGenNodeResourceRelatedStatisticsRepository;
    @Autowired
    private KgGenNodeVideoInfoRepository kgGenNodeVideoInfoRepository;
    @Autowired
    private KgGenNodeQuestionRepository kgGenNodeQuestionRepository;
    @Autowired
    private CourseKnowledgeGraphActionLogService courseKnowledgeGraphActionLogService;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;
    @Autowired
    private KgGenNodeRelationRepository kgGenNodeRelationRepository;
    @Value("${request.address.teachingApi}")
    private String teachingApi;
    @Value("${copyKgGenMasterTaskId:}")
    private String copyKgGenMasterTaskId;

    @Transactional
    public Result createCourseVideoKgAIGenTask(KgAIGenTeachingMaterialParam param) {
        List<KgGenTaskMasterStatus> statuses = new ArrayList<>();
        statuses.add(KgGenTaskMasterStatus.WAITING);
        statuses.add(KgGenTaskMasterStatus.GENERATING);
        if (Objects.nonNull(param.isIsReGen()) && param.isIsReGen()) {
            // 重新生成的，先把之前的取消了
            List<KgGenTaskMaster> kgGenTaskMasters = kgGenTaskMasterRepository.findByCourseIdAndStatusIn(param.getCourseId(), statuses);
            kgGenTaskMasters.forEach(model -> {
                model.setStatus(KgGenTaskMasterStatus.CANCEL);
                model.setModifierId(param.getOperatorId());
                model.setModifierName(param.getOperatorName());
                model.setModifyTime(new Date());
                kgGenTaskMasterRepository.saveAndFlush(model);
            });
            kgGenTaskMasters.forEach(model -> {
                List<KgGenTaskSub> kgGenTaskSubs = kgGenTaskSubRepository.findByMasterId(model.getId());
                kgGenTaskSubs.forEach(sub -> {
                    sub.setStatus(KgGenTaskSubStatus.GIVE_UP);
                    sub.setModifierId(param.getOperatorId());
                    sub.setModifierName(param.getOperatorName());
                    sub.setModifyTime(new Date());
                    kgGenTaskSubRepository.saveAndFlush(sub);
                });
            });
        }


        List<KgGenTaskMaster> kgGenTaskMasters = kgGenTaskMasterRepository.findByCourseIdAndStatusIn(param.getCourseId(), statuses);
        if (CollectionUtils.isNotEmpty(kgGenTaskMasters)) {
            return Result.error("课程正在生成中");
        }
        TeachingMaterialFile teachingMaterialFile = teachingMaterialFileRepository.findById(param.getTeachingMaterialId()).orElse(null);
        if (ObjectUtils.isEmpty(teachingMaterialFile)) {
            return Result.error("未找到教材信息");
        }
        KgGenTaskMaster kgGenTaskMaster = buildKgGenTaskMaster(param);
        kgGenTaskMaster = kgGenTaskMasterRepository.saveAndFlush(kgGenTaskMaster);
        KgGenTaskSub kgGenTaskSub = buildKgGenTaskSub(kgGenTaskMaster, param.getOperatorId(), param.getOperatorName());
        return Result.success();
    }

    @Transactional
    public Result createCourseVideoKgAIGenTask(KgAIGenCourseVideoParam param) {
        List<KgGenTaskMasterStatus> statuses = new ArrayList<>();
        statuses.add(KgGenTaskMasterStatus.WAITING);
        statuses.add(KgGenTaskMasterStatus.GENERATING);
        if (Objects.nonNull(param.isIsReGen()) && param.isIsReGen()) {
            // 重新生成的，先把之前的取消了
            List<KgGenTaskMaster> kgGenTaskMasters = kgGenTaskMasterRepository.findByCourseIdAndStatusIn(param.getCourseId(), statuses);
            kgGenTaskMasters.forEach(model -> {
                model.setStatus(KgGenTaskMasterStatus.CANCEL);
                model.setModifierId(param.getOperatorId());
                model.setModifierName(param.getOperatorName());
                model.setModifyTime(new Date());
                kgGenTaskMasterRepository.saveAndFlush(model);
            });
            kgGenTaskMasters.forEach(model -> {
                List<KgGenTaskSub> kgGenTaskSubs = kgGenTaskSubRepository.findByMasterId(model.getId());
                kgGenTaskSubs.forEach(sub -> {
                    sub.setStatus(KgGenTaskSubStatus.GIVE_UP);
                    sub.setModifierId(param.getOperatorId());
                    sub.setModifierName(param.getOperatorName());
                    sub.setModifyTime(new Date());
                    kgGenTaskSubRepository.saveAndFlush(sub);
                });
            });
        }


        List<KgGenTaskMaster> kgGenTaskMasters = kgGenTaskMasterRepository.findByCourseIdAndStatusIn(param.getCourseId(), statuses);
        if (CollectionUtils.isNotEmpty(kgGenTaskMasters)) {
            return Result.error("课程正在生成中");
        }
        //判断课堂实录是否存在图谱
        CourseKnowledgeGraphAiGenerateTaskCreateStatusModel courseKnowledgeGraphAiGenerateTaskCreateStatusModel =
                verifyCourseKnowledgeGraphAiGenerateTask(param);
        if (ObjectUtils.isEmpty(courseKnowledgeGraphAiGenerateTaskCreateStatusModel) ||
                courseKnowledgeGraphAiGenerateTaskCreateStatusModel.getStatus() == 0) {
            return Result.error("所选教学班的课堂实录中不存在知识导图");
        }
        KgGenTaskMaster kgGenTaskMaster = buildKgGenTaskMaster(param);
        kgGenTaskMaster = kgGenTaskMasterRepository.saveAndFlush(kgGenTaskMaster);
        KgGenTaskSub kgGenTaskSub = buildKgGenTaskSub(kgGenTaskMaster, param.getOperatorId(), param.getOperatorName());
        kgGenTaskSubRepository.save(kgGenTaskSub);
        return Result.success();
    }

    public CourseKnowledgeGraphAiGenerateTaskCreateStatusModel verifyCourseKnowledgeGraphAiGenerateTask(KgAIGenCourseVideoParam param) {
        CourseknowledgegraphaigeneratetaskCreateParam courseknowledgegraphaigeneratetaskCreateParam =
                new CourseknowledgegraphaigeneratetaskCreateParam();
        courseknowledgegraphaigeneratetaskCreateParam.setCourseId(param.getCourseId());
        courseknowledgegraphaigeneratetaskCreateParam.setSchoolYear(param.getSchoolYear());
        courseknowledgegraphaigeneratetaskCreateParam.setTerm(param.getTerm());
        courseknowledgegraphaigeneratetaskCreateParam.setGroupId(param.getGroupId());
        courseknowledgegraphaigeneratetaskCreateParam.setAccessUserId(param.getOperatorId());
        courseknowledgegraphaigeneratetaskCreateParam.setAccessUserName(param.getOperatorName());

        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5("courseId=" + param.getCourseId() + signKey);
        String url = teachingApi + ConstantWebApi.POST_COURSE_KNOWLEDGE_GRAPH_AI_GENERATE_TASK + "?validCode=" + key;
        //设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<CourseknowledgegraphaigeneratetaskCreateParam> httpEntity = new HttpEntity<>(courseknowledgegraphaigeneratetaskCreateParam,
                headers);
        try {
            ResponseEntity<CourseKnowledgeGraphAiGenerateTaskCreateStatusModel> responseEntity = restTemplate
                    .exchange(url, HttpMethod.POST, httpEntity,
                            new ParameterizedTypeReference<CourseKnowledgeGraphAiGenerateTaskCreateStatusModel>() {
                            });
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private KgGenTaskSub buildKgGenTaskSub(KgGenTaskMaster kgGenTaskMaster, String operatorId, String operatorName) {
        KgGenTaskSub kgGenTaskSub = new KgGenTaskSub();
        kgGenTaskSub.setMasterId(kgGenTaskMaster.getId());
        kgGenTaskSub.setStepCode(KgGenTaskStepCode.STEP_NODE_GEN);
        kgGenTaskSub.setStatus(KgGenTaskSubStatus.SUCCESS);
        kgGenTaskSub.setModifierId(operatorId);
        kgGenTaskSub.setModifierName(operatorName);
        kgGenTaskSub.setModifyTime(new Date());
        kgGenTaskSub.setCreatorId(operatorId);
        kgGenTaskSub.setCreatorName(operatorName);
        kgGenTaskSub.setCreateTime(new Date());
        if (StringUtils.isBlank(copyKgGenMasterTaskId)) {
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.FAIL);
            kgGenTaskSub.setRemark("未找到");
            return kgGenTaskSub;
        }
        kgGenTaskSub = kgGenTaskSubRepository.saveAndFlush(kgGenTaskSub);
        createCopyKgGenNode(kgGenTaskSub, copyKgGenMasterTaskId, kgGenTaskMaster.getId());
        return kgGenTaskSub;
    }

    private void createCopyKgGenNode(KgGenTaskSub kgGenTaskSub,
                                     String copyKgGenMasterTaskId,
                                     String newMasterTaskId) {
        List<KgGenTaskSub> kgGenTaskSubList = kgGenTaskSubRepository.findLatestByMasterId(copyKgGenMasterTaskId);
        KgGenTaskSub copyKgGenNodeTaskSub = kgGenTaskSubList.stream()
                .filter(model -> Objects.equals(model.getStepCode(), KgGenTaskStepCode.STEP_NODE_GEN))
                .min(Comparator.comparing(KgGenTaskSub::getCreateTime))
                .orElse(null);
        if (ObjectUtils.isEmpty(copyKgGenNodeTaskSub)) {
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.FAIL);
            kgGenTaskSubRepository.saveAndFlush(kgGenTaskSub);
            return;
        }
        List<KgGenNode> copyKgGenNodeList = kgGenNodeRepository.findBySubTaskId(copyKgGenNodeTaskSub.getId());
        Map<String, String> idMapping = new HashMap<>();
        // 3. 首先复制所有节点，暂不设置parentNodeId
        List<KgGenNode> newNodes = new ArrayList<>();
        for (KgGenNode originalNode : copyKgGenNodeList) {
            KgGenNode newNode = copyNode(originalNode, kgGenTaskSub.getId(), newMasterTaskId);
            newNodes.add(newNode);
            // 保存ID映射关系
            idMapping.put(originalNode.getId(), newNode.getId());
        }
        // 4. 更新父节点关系
        for (KgGenNode newNode : newNodes) {
            if (StringUtils.isNotBlank(newNode.getParentNodeId()) &&
                    idMapping.containsKey(newNode.getParentNodeId())) {
                // 设置新的父节点ID
                newNode.setParentNodeId(idMapping.get(newNode.getParentNodeId()));
            }
        }
        // 5. 保存所有新节点
        kgGenNodeRepository.saveAll(newNodes);
        // 6. 保存关联关系表
        saveNodeRelations(idMapping, newMasterTaskId);
    }

    private void saveNodeRelations(Map<String, String> idMapping, String newMasterTaskId) {
        List<KgGenNodeRelation> relations = new ArrayList<>();
        for (Map.Entry<String, String> entry : idMapping.entrySet()) {
            KgGenNodeRelation relation = new KgGenNodeRelation();
            relation.setOriginalNodeId(entry.getKey());
            relation.setNewNodeId(entry.getValue());
            relation.setCreateTime(new Date());
            relation.setNewMasterTaskId(newMasterTaskId);
            relation.setId(UUIDGenerator.generateUUIDWithoutHyphens());
            relations.add(relation);
        }
        kgGenNodeRelationRepository.saveAll(relations);
    }

    private KgGenNode copyNode(KgGenNode original,
                               String newSubTaskId, String newMasterTaskId) {
        KgGenNode newNode = new KgGenNode();
        // 生成新ID
        newNode.setId(UUIDGenerator.generateUUIDWithoutHyphens()); // 使用UUID或其他ID生成策略
        // 设置新的任务关联ID
        newNode.setSubTaskId(newSubTaskId);
        newNode.setMasterTaskId(newMasterTaskId);
        // 复制其他属性
        newNode.setNodeName(original.getNodeName());
        newNode.setLevel(original.getLevel());
        newNode.setParentNodeId(original.getParentNodeId()); // 暂时保留原父节点ID，后面会更新
        newNode.setContent(original.getContent());
        newNode.setChapterContentId(original.getChapterContentId());
        newNode.setNodeOrder(original.getNodeOrder());
        newNode.setLevelType(original.getLevelType());

        // 设置审计信息
        newNode.setCreatorId("copy"); // 设置当前用户
        newNode.setCreatorName("copy");
        newNode.setCreateTime(new Date());
        newNode.setModifierId("copy");
        newNode.setModifierName("copy");
        newNode.setModifyTime(new Date());

        return newNode;
    }

    private KgGenTaskMaster buildKgGenTaskMaster(KgAIGenCourseVideoParam param) {
        KgGenTaskMaster kgGenTaskMaster = new KgGenTaskMaster();
        kgGenTaskMaster.setCourseId(param.getCourseId());
        kgGenTaskMaster.setCourseName(param.getCourseName());
        kgGenTaskMaster.setType(KgGenTaskType.COURSE_RECORD_KNOWLEDGE_GRAPH_AI_GENERATE_TASK);
        kgGenTaskMaster.setGroupId(param.getGroupId());
        kgGenTaskMaster.setSchoolYear(param.getSchoolYear());
        kgGenTaskMaster.setTerm(param.getTerm());
        kgGenTaskMaster.setStatus(KgGenTaskMasterStatus.WAITING);
        kgGenTaskMaster.setModifierId(param.getOperatorId());
        kgGenTaskMaster.setModifierName(param.getOperatorName());
        kgGenTaskMaster.setModifyTime(new Date());
        kgGenTaskMaster.setCreatorId(param.getOperatorId());
        kgGenTaskMaster.setCreatorName(param.getOperatorName());
        kgGenTaskMaster.setCreateTime(new Date());
        return kgGenTaskMaster;
    }

    private KgGenTaskMaster buildKgGenTaskMaster(KgAIGenTeachingMaterialParam param) {
        KgGenTaskMaster kgGenTaskMaster = new KgGenTaskMaster();
        kgGenTaskMaster.setCourseId(param.getCourseId());
        kgGenTaskMaster.setCourseName(param.getCourseName());
        kgGenTaskMaster.setMaterialFileId(param.getTeachingMaterialId());
        kgGenTaskMaster.setType(KgGenTaskType.COURSE_MATERIAL_KNOWLEDGE_GRAPH_AI_GENERATE_TASK);
        kgGenTaskMaster.setStatus(KgGenTaskMasterStatus.GENERATING);
        kgGenTaskMaster.setModifierId(param.getOperatorId());
        kgGenTaskMaster.setModifierName(param.getOperatorName());
        kgGenTaskMaster.setModifyTime(new Date());
        kgGenTaskMaster.setCreatorId(param.getOperatorId());
        kgGenTaskMaster.setCreatorName(param.getOperatorName());
        kgGenTaskMaster.setCreateTime(new Date());
        return kgGenTaskMaster;
    }

    public KgAIGenTeachingMaterialStatusResource getKgAIGenTeachingMaterialStatus(String courseId,
                                                                                  Integer stepIndex,
                                                                                  String subTaskId,
                                                                                  String masterTaskId) {
        if (StringUtils.isNoneBlank(masterTaskId, subTaskId)) {
            KgGenTaskMaster kgGenTaskMaster = kgGenTaskMasterRepository.findById(masterTaskId).orElse(null);
            if (ObjectUtils.isEmpty(kgGenTaskMaster)) {
                return null;
            }
            List<KgGenTaskSub> kgGenTaskSubList = kgGenTaskSubRepository.findLatestByMasterId(kgGenTaskMaster.getId());
            if (StringUtils.isNotBlank(subTaskId)) {
                kgGenTaskSubList = kgGenTaskSubList.stream()
                        .filter(model -> model.getId().equals(subTaskId))
                        .collect(Collectors.toList());
            }
            if (CollectionUtils.isEmpty(kgGenTaskSubList)) {
                return null;
            }
            return buildKgAIGenTeachingMaterialStatusResource(kgGenTaskMaster, kgGenTaskSubList);
        } else {
            List<KgGenTaskMaster> kgGenTaskMasters = kgGenTaskMasterRepository.findByCourseIdAndStatusIn(courseId,
                    Arrays.asList(KgGenTaskMasterStatus.WAITING, KgGenTaskMasterStatus.GENERATING));
            if (CollectionUtils.isEmpty(kgGenTaskMasters)) {
                return null;
            }
            KgGenTaskMaster kgGenTaskMaster = kgGenTaskMasters.get(0);
            List<KgGenTaskSub> kgGenTaskSubList = kgGenTaskSubRepository.findLatestByMasterId(kgGenTaskMaster.getId());
            if (Objects.nonNull(stepIndex)) {
                kgGenTaskSubList = kgGenTaskSubList.stream()
                        .filter(model -> Objects.equals(model.getStepCode().getIndex(), stepIndex))
                        .sorted(Comparator.comparing(KgGenTaskSub::getCreateTime))
                        .collect(Collectors.toList());
            }
            if (CollectionUtils.isEmpty(kgGenTaskSubList)) {
                return null;
            }
            return buildKgAIGenTeachingMaterialStatusResource(kgGenTaskMaster, kgGenTaskSubList);
        }
    }

    private KgAIGenTeachingMaterialStatusResource buildKgAIGenTeachingMaterialStatusResource(KgGenTaskMaster kgGenTaskMaster,
                                                                                             List<KgGenTaskSub> kgGenTaskSubList) {
        KgAIGenTeachingMaterialStatusResource kgAIGenTeachingMaterialStatusResource = new KgAIGenTeachingMaterialStatusResource();
        kgAIGenTeachingMaterialStatusResource.setMasterTaskId(kgGenTaskMaster.getId());
        kgAIGenTeachingMaterialStatusResource.setMasterTaskStatus(kgGenTaskMaster.getStatus().getIndex());
        kgAIGenTeachingMaterialStatusResource.setSubTaskStatusList(kgGenTaskSubList.stream()
                .map(model -> {
                    KgAIGenTeachingMaterialSubTaskStatusResource kgAIGenTeachingMaterialSubTaskStatusResource
                            = new KgAIGenTeachingMaterialSubTaskStatusResource();
                    kgAIGenTeachingMaterialSubTaskStatusResource.setSubTaskId(model.getId());
                    kgAIGenTeachingMaterialSubTaskStatusResource.setKgGenTaskStepCode(model.getStepCode().getIndex());
                    kgAIGenTeachingMaterialSubTaskStatusResource.setSubTaskStatus(model.getStatus().getIndex());
                    String messageModel = getGeneratingMessageModel(model);
                    if (Objects.isNull(model.getFinishTime())) {
                        Date now = new Date();
                        kgAIGenTeachingMaterialSubTaskStatusResource.setRemark(buildGeneratingMessage(model.getCreateTime(), now, messageModel));
                    } else {
                        kgAIGenTeachingMaterialSubTaskStatusResource.setRemark(buildGeneratingMessage(model.getCreateTime(), model.getFinishTime(),
                                messageModel));
                    }
                    return kgAIGenTeachingMaterialSubTaskStatusResource;
                })
                .sorted(Comparator.comparing(KgAIGenTeachingMaterialSubTaskStatusResource::getKgGenTaskStepCode))
                .collect(Collectors.toList()));
        return kgAIGenTeachingMaterialStatusResource;
    }

    private String getGeneratingMessageModel(KgGenTaskSub model) {
        if (KgGenTaskStepCode.STEP_NODE_GEN.equals(model.getStepCode())) {
            return "“知识树”正在生长中，已花费%d分钟";
        } else if (KgGenTaskStepCode.STEP_NODE_DETAIL_GEN.equals(model.getStepCode())) {
            return "“知识树”正在生成详情内容，已花费%d分钟";
        } else if (KgGenTaskStepCode.STEP_RESOURCE_RELATED.equals(model.getStepCode())) {
            return "“知识树”正在编织资源脉络，已花费%d分钟";
        } else {
            return "“知识树”正在生成，已花费%d分钟";
        }
    }

    private String buildGeneratingMessage(Date createTime, Date finishTime, String messageModel) {
        // 获取当前时间
        Date now = finishTime;
        // 计算时间差（毫秒）
        long durationInMillis = now.getTime() - createTime.getTime();

        // 处理负值时间（创建时间晚于当前时间）
        if (durationInMillis < 0) {
            return String.format(messageModel, 0);
        }

        // 转换为分钟
        long totalMinutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis);

        // 判断是否超过1分钟（包括完整分钟）
        if (durationInMillis >= TimeUnit.MINUTES.toMillis(1)) {
            return String.format(messageModel, totalMinutes);
        }
        // 不足1分钟显示秒
        else {
            // 转换为秒（向上取整，避免显示0秒）
            long seconds = TimeUnit.MILLISECONDS.toSeconds(durationInMillis);
            if (seconds == 0 && durationInMillis > 0) {
                seconds = 0; // 大于0毫秒但不足1秒时显示1秒
            }
            return String.format(messageModel, 0);
        }
    }

    public KgAIGenTeachingMaterialNodeResource getKgAIGenTeachingMaterialNode(String courseId) {
        List<KgGenTaskMaster> kgGenTaskMasters = kgGenTaskMasterRepository.findByCourseIdAndStatusIn(courseId,
                Arrays.asList(KgGenTaskMasterStatus.GENERATING));
        if (CollectionUtils.isEmpty(kgGenTaskMasters)) {
            return null;
        }
        KgGenTaskMaster kgGenTaskMaster = kgGenTaskMasters.get(0);
        List<KgGenTaskSub> kgGenTaskSubList = kgGenTaskSubRepository.findLatestByMasterIdAndStepCode(kgGenTaskMaster.getId(),
                KgGenTaskStepCode.STEP_NODE_GEN);
        if (CollectionUtils.isEmpty(kgGenTaskSubList)) {
            return null;
        }
        KgGenTaskSub kgGenTaskSub = kgGenTaskSubList.get(0);
        List<KgGenNode> kgGenNodeList = kgGenNodeRepository.findBySubTaskId(kgGenTaskSub.getId());

        KgAIGenTeachingMaterialNodeResource kgAIGenTeachingMaterialNodeResource =
                new KgAIGenTeachingMaterialNodeResource();
        kgAIGenTeachingMaterialNodeResource.setCourseName(kgGenTaskMaster.getCourseName());
        kgAIGenTeachingMaterialNodeResource.setCourseId(kgGenTaskMaster.getCourseId());
        kgAIGenTeachingMaterialNodeResource.setNodeCount(0);
        kgAIGenTeachingMaterialNodeResource.setRelatedQuestionNum(0);
        kgAIGenTeachingMaterialNodeResource.setRelatedVideoClipNum(0);
        kgAIGenTeachingMaterialNodeResource.setKgNode(null);
        if (CollectionUtils.isNotEmpty(kgGenNodeList)) {
            kgAIGenTeachingMaterialNodeResource.setNodeCount(kgGenNodeList.size());
            kgAIGenTeachingMaterialNodeResource.setKgNode(buildNodeList(kgGenNodeList, courseId,
                    kgGenTaskMaster.getCourseName()));
        }
        List<KgGenNodeResourceRelatedStatistics> statisticsList = kgGenNodeResourceRelatedStatisticsRepository.
                findByMasterTaskId(kgGenTaskMaster.getId());
        if (CollectionUtils.isNotEmpty(statisticsList)) {
            KgGenNodeResourceRelatedStatistics statistics = statisticsList.get(0);
            kgAIGenTeachingMaterialNodeResource.setRelatedQuestionNum(statistics.getRelatedQuestionNum());
            kgAIGenTeachingMaterialNodeResource.setRelatedVideoClipNum(statistics.getRelatedVideoClipNum());
        }
        return kgAIGenTeachingMaterialNodeResource;

    }

    public KgAIGenTeachingMaterialNodeResourceItem buildNodeList(List<KgGenNode> kgGenNodeList,
                                                                 String courseId,
                                                                 String courseName) {
        // 最上级
        KgAIGenTeachingMaterialNodeResourceItem root = new KgAIGenTeachingMaterialNodeResourceItem();
        root.setLevel(0);
        root.setKgNodeId(courseId);
        root.setKgNodeName(courseName);
        root.setShowOrder(0);
        // 步骤 1: 按父节点ID分组（使用 computeIfAbsent 优化分组效率）
        Map<String, List<KgGenNode>> nodeMap = new HashMap<>();
        for (KgGenNode node : kgGenNodeList) {
            String parentId = node.getParentNodeId();
            nodeMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(node);
        }
        root.setChildList(buildChildList(nodeMap.get(null), nodeMap));

        return root;
    }

    // 递归构建子树结构
    private List<KgAIGenTeachingMaterialNodeResourceItem> buildChildList(List<KgGenNode> nodes,
                                                                         Map<String, List<KgGenNode>> nodeMap) {
        if (nodes == null || nodes.isEmpty()) {
            return Collections.emptyList();
        }

        List<KgAIGenTeachingMaterialNodeResourceItem> result = new ArrayList<>();
        nodes = nodes.stream()
                .sorted(Comparator.comparingInt(KgGenNode::getNodeOrder))
                .collect(Collectors.toList());
        for (KgGenNode node : nodes) {
            KgAIGenTeachingMaterialNodeResourceItem item = new KgAIGenTeachingMaterialNodeResourceItem();
            item.setKgNodeId(node.getId());
            item.setKgNodeName(node.getNodeName());
            item.setLevel(node.getLevel());
            item.setShowOrder(node.getNodeOrder());
            item.setKgNodeContent(node.getContent());
            item.setKgNodeImportantTag(Objects.nonNull(node.getLevelType()) ? node.getLevelType().getIndex() : null);
            // 递归构建子节点（使用当前节点ID查找子节点）
            List<KgGenNode> childNodes = nodeMap.get(node.getId());
            item.setChildList(buildChildList(childNodes, nodeMap));

            result.add(item);
        }
        return result;
    }

    public Result modifyKnowledgeGraphAIGenByTeaching(KgAIGenTeachingMaterialStatusModifyParam param) {
        KgGenTaskMaster kgGenTaskMaster = kgGenTaskMasterRepository.findById(param.getMasterTaskId())
                .orElse(null);
        if (Objects.isNull(kgGenTaskMaster)) {
            return Result.error("未找到该任务");
        }
        KgGenTaskSub kgGenTaskSub = kgGenTaskSubRepository.findById(param.getSubTaskId())
                .orElse(null);
        if (Objects.isNull(kgGenTaskSub)) {
            return Result.error("未找到该任务");
        }
        KgAIGenTeachingMaterialAIGenTaskBaseResource kgAIGenTeachingMaterialAIGenTaskBaseResource =
                new KgAIGenTeachingMaterialAIGenTaskBaseResource();
        kgAIGenTeachingMaterialAIGenTaskBaseResource.setCourseId(kgGenTaskMaster.getCourseId());
        kgAIGenTeachingMaterialAIGenTaskBaseResource.setMasterTaskId(kgGenTaskMaster.getId());
        if (KgGenTaskStepCode.STEP_NODE_GEN.equals(kgGenTaskSub.getStepCode())) {
            return handleNodeGenStatus(param, kgGenTaskMaster, kgGenTaskSub, kgAIGenTeachingMaterialAIGenTaskBaseResource);
        }
        if (KgGenTaskStepCode.STEP_NODE_DETAIL_GEN.equals(kgGenTaskSub.getStepCode())) {
            return handleNodeDetailGenStatus(param, kgGenTaskMaster, kgGenTaskSub, kgAIGenTeachingMaterialAIGenTaskBaseResource);
        }
        if (KgGenTaskStepCode.STEP_RESOURCE_RELATED.equals(kgGenTaskSub.getStepCode())) {
            return handleNodeRelatedResourceGenStatus(param, kgGenTaskMaster, kgGenTaskSub, kgAIGenTeachingMaterialAIGenTaskBaseResource);
        }
        if (KgGenTaskStepCode.STEP_IMPORT_DATABASE.equals(kgGenTaskSub.getStepCode())) {
            return handleImportDatabaseStatus(param, kgGenTaskMaster, kgGenTaskSub, kgAIGenTeachingMaterialAIGenTaskBaseResource);
        }
        return Result.success();
    }

    private Result handleImportDatabaseStatus(KgAIGenTeachingMaterialStatusModifyParam param,
                                              KgGenTaskMaster kgGenTaskMaster,
                                              KgGenTaskSub kgGenTaskSub,
                                              KgAIGenTeachingMaterialAIGenTaskBaseResource kgAIGenTeachingMaterialAIGenTaskBaseResource) {
        if (KgAIGenSubTaskOperationType.CANCEL.getValue() == param.getOperationType()) {
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.GIVE_UP);
            kgGenTaskSub.setModifierId(param.getOperatorId());
            kgGenTaskSub.setModifierName(param.getOperatorName());
            kgGenTaskSub.setModifyTime(new Date());
            kgGenTaskSubRepository.save(kgGenTaskSub);
            kgGenTaskMaster.setStatus(KgGenTaskMasterStatus.CANCEL);
            kgGenTaskMaster.setModifierId(param.getOperatorId());
            kgGenTaskMaster.setModifierName(param.getOperatorName());
            kgGenTaskMaster.setModifyTime(new Date());
            kgGenTaskMasterRepository.save(kgGenTaskMaster);
            kgAIGenTeachingMaterialAIGenTaskBaseResource.setSubTaskId(kgGenTaskSub.getId());
            kgAIGenTeachingMaterialAIGenTaskBaseResource.setMasterTaskId(kgGenTaskMaster.getId());
        }
        return Result.success().setData(kgAIGenTeachingMaterialAIGenTaskBaseResource);
    }

    private Result handleNodeRelatedResourceGenStatus(KgAIGenTeachingMaterialStatusModifyParam param,
                                                      KgGenTaskMaster kgGenTaskMaster,
                                                      KgGenTaskSub kgGenTaskSub,
                                                      KgAIGenTeachingMaterialAIGenTaskBaseResource kgAIGenTeachingMaterialAIGenTaskBaseResource) {
        kgGenTaskSub.setModifierId(param.getOperatorId());
        kgGenTaskSub.setModifierName(param.getOperatorName());
        kgGenTaskSub.setModifyTime(new Date());
        if (KgAIGenSubTaskOperationType.CANCEL.getValue() == param.getOperationType()) {
            // 取消任务
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.GIVE_UP);
            kgGenTaskSubRepository.save(kgGenTaskSub);
            kgAIGenTeachingMaterialAIGenTaskBaseResource.setSubTaskId(kgGenTaskSub.getId());
        } else if (KgAIGenSubTaskOperationType.REGENERATE.getValue() == param.getOperationType()) {
//            kgGenTaskSub.setStatus(KgGenTaskSubStatus.GIVE_UP);
//            kgGenTaskSubRepository.save(kgGenTaskSub);
//            KgGenTaskSub newKgGenTaskSub = new KgGenTaskSub();
//            newKgGenTaskSub.setMasterId(kgGenTaskMaster.getId());
//            newKgGenTaskSub.setStepCode(KgGenTaskStepCode.STEP_RESOURCE_RELATED);
//            newKgGenTaskSub.setStatus(KgGenTaskSubStatus.WAITING);
//            newKgGenTaskSub.setModifierId(param.getOperatorId());
//            newKgGenTaskSub.setModifierName(param.getOperatorId());
//            newKgGenTaskSub.setModifyTime(new Date());
//            newKgGenTaskSub.setCreatorId(param.getOperatorId());
//            newKgGenTaskSub.setCreatorName(param.getOperatorName());
//            newKgGenTaskSub.setCreateTime(new Date());
//            newKgGenTaskSub = kgGenTaskSubRepository.saveAndFlush(newKgGenTaskSub);
            kgAIGenTeachingMaterialAIGenTaskBaseResource.setSubTaskId(kgGenTaskSub.getId());
        }
        return Result.success().setData(kgAIGenTeachingMaterialAIGenTaskBaseResource);
    }

    private Result handleNodeDetailGenStatus(KgAIGenTeachingMaterialStatusModifyParam param,
                                             KgGenTaskMaster kgGenTaskMaster,
                                             KgGenTaskSub kgGenTaskSub,
                                             KgAIGenTeachingMaterialAIGenTaskBaseResource kgAIGenTeachingMaterialAIGenTaskBaseResource) {
        kgGenTaskSub.setModifierId(param.getOperatorId());
        kgGenTaskSub.setModifierName(param.getOperatorName());
        kgGenTaskSub.setModifyTime(new Date());
        if (KgAIGenSubTaskOperationType.CANCEL.getValue() == param.getOperationType()) {
            // 取消任务
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.GIVE_UP);
            kgGenTaskSubRepository.save(kgGenTaskSub);
            kgAIGenTeachingMaterialAIGenTaskBaseResource.setSubTaskId(kgGenTaskSub.getId());
        } else if (KgAIGenSubTaskOperationType.REGENERATE.getValue() == param.getOperationType()) {
//            kgGenTaskSub.setStatus(KgGenTaskSubStatus.GIVE_UP);
//            kgGenTaskSubRepository.save(kgGenTaskSub);
//            kgGenNodeResourceRelatedStatisticsRepository.deleteByMasterTaskId(kgGenTaskMaster.getId());
//            kgGenNodeVideoInfoRepository.deleteByMasterTaskId(kgGenTaskMaster.getId());
//            kgGenNodeQuestionRepository.deleteByMasterTaskId(kgGenTaskMaster.getId());
//            KgGenTaskSub newKgGenTaskSub = new KgGenTaskSub();
//            newKgGenTaskSub.setMasterId(kgGenTaskMaster.getId());
//            newKgGenTaskSub.setStepCode(KgGenTaskStepCode.STEP_NODE_DETAIL_GEN);
//            newKgGenTaskSub.setStatus(KgGenTaskSubStatus.WAITING);
//            newKgGenTaskSub.setModifierId(param.getOperatorId());
//            newKgGenTaskSub.setModifierName(param.getOperatorId());
//            newKgGenTaskSub.setModifyTime(new Date());
//            newKgGenTaskSub.setCreatorId(param.getOperatorId());
//            newKgGenTaskSub.setCreatorName(param.getOperatorName());
//            newKgGenTaskSub.setCreateTime(new Date());
//            newKgGenTaskSub = kgGenTaskSubRepository.saveAndFlush(newKgGenTaskSub);
            kgAIGenTeachingMaterialAIGenTaskBaseResource.setSubTaskId(kgGenTaskSub.getId());
        }
        return Result.success().setData(kgAIGenTeachingMaterialAIGenTaskBaseResource);
    }

    private Result handleNodeGenStatus(KgAIGenTeachingMaterialStatusModifyParam param,
                                       KgGenTaskMaster kgGenTaskMaster,
                                       KgGenTaskSub kgGenTaskSub,
                                       KgAIGenTeachingMaterialAIGenTaskBaseResource kgAIGenTeachingMaterialAIGenTaskBaseResource) {
        if (KgAIGenSubTaskOperationType.CANCEL.getValue() == param.getOperationType()) {
            // 取消任务
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.GIVE_UP);
            kgGenTaskSubRepository.save(kgGenTaskSub);
            kgGenTaskMaster.setStatus(KgGenTaskMasterStatus.CANCEL);
            kgGenTaskMasterRepository.save(kgGenTaskMaster);
            kgAIGenTeachingMaterialAIGenTaskBaseResource.setSubTaskId(kgGenTaskSub.getId());
        } else if (KgAIGenSubTaskOperationType.REGENERATE.getValue() == param.getOperationType()) {
            if (KgGenTaskMasterStatus.FAIL.equals(kgGenTaskMaster.getStatus())) {
                // 主任务已经失败了，先把主任务改为等待中
                kgGenTaskMaster.setStatus(KgGenTaskMasterStatus.WAITING);
                kgGenTaskMaster.setModifierId(param.getOperatorId());
                kgGenTaskMaster.setModifierName(param.getOperatorName());
                kgGenTaskMaster.setModifyTime(new Date());
                kgGenTaskMasterRepository.save(kgGenTaskMaster);
            }
//            kgGenTaskSub.setStatus(KgGenTaskSubStatus.GIVE_UP);
//            kgGenTaskSubRepository.save(kgGenTaskSub);
//            KgGenTaskSub newKgGenTaskSub = new KgGenTaskSub();
//            newKgGenTaskSub.setMasterId(kgGenTaskMaster.getId());
//            newKgGenTaskSub.setStepCode(KgGenTaskStepCode.STEP_NODE_GEN);
//            newKgGenTaskSub.setStatus(KgGenTaskSubStatus.WAITING);
//            newKgGenTaskSub.setModifierId(param.getOperatorId());
//            newKgGenTaskSub.setModifierName(param.getOperatorId());
//            newKgGenTaskSub.setModifyTime(new Date());
//            newKgGenTaskSub.setCreatorId(param.getOperatorId());
//            newKgGenTaskSub.setCreatorName(param.getOperatorName());
//            newKgGenTaskSub.setCreateTime(new Date());
//            newKgGenTaskSub = kgGenTaskSubRepository.saveAndFlush(newKgGenTaskSub);
            kgAIGenTeachingMaterialAIGenTaskBaseResource.setSubTaskId(kgGenTaskSub.getId());
        }

        return Result.success().setData(kgAIGenTeachingMaterialAIGenTaskBaseResource);
    }

    public Result knowledgeGraphAIGenByTeachingMaterialNodeDetailTask(KgAIGenTeachingMaterialNodeDetailTaskParam param) {
        KgGenTaskMaster kgGenTaskMaster = kgGenTaskMasterRepository.findById(param.getMasterTaskId())
                .orElse(null);
        if (Objects.isNull(kgGenTaskMaster)) {
            return Result.error("未找到该任务");
        }
        KgGenTaskStepCode stepCode = KgGenTaskStepCode.getByIndex(param.getStepCode());
        if (Objects.isNull(stepCode)) {
            return Result.error("参数不正确");
        }
        List<KgGenTaskSub> kgGenTaskSubList = kgGenTaskSubRepository.findLatestByMasterIdAndStepCode(kgGenTaskMaster.getId(),
                KgGenTaskStepCode.STEP_NODE_GEN);
        if (CollectionUtils.isNotEmpty(kgGenTaskSubList) && kgGenTaskSubList.stream().anyMatch(t -> KgGenTaskSubStatus.WAITING.equals(t.getStatus())
                || KgGenTaskSubStatus.GENERATING.equals(t.getStatus()))) {
            return Result.error("请等待当前任务完成");
        }
        KgGenTaskSub kgGenTaskSub = new KgGenTaskSub();
        kgGenTaskSub.setMasterId(kgGenTaskMaster.getId());
        kgGenTaskSub.setStepCode(stepCode);
        if (param.getOperationType() == 0) {
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.SKIP);
        } else if (param.getOperationType() == 1) {
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.WAITING);
        }
        kgGenTaskSub.setModifierId(param.getOperatorId());
        kgGenTaskSub.setModifierName(param.getOperatorName());
        kgGenTaskSub.setModifyTime(new Date());
        kgGenTaskSub.setCreatorId(param.getOperatorId());
        kgGenTaskSub.setCreatorName(param.getOperatorName());
        kgGenTaskSub.setCreateTime(new Date());
        if (KgGenTaskStepCode.STEP_NODE_DETAIL_GEN.equals(stepCode)
                && KgGenTaskSubStatus.WAITING.equals(kgGenTaskSub.getStatus())) {
            // 详情直接成功
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.SUCCESS);
        }

        kgGenTaskSub = kgGenTaskSubRepository.saveAndFlush(kgGenTaskSub);
        if (KgGenTaskStepCode.STEP_RESOURCE_RELATED.equals(stepCode)
                && KgGenTaskSubStatus.WAITING.equals(kgGenTaskSub.getStatus())) {
            //存储视频和题库关联关系
            saveQuestionAndVideoRelated(kgGenTaskMaster.getId(), kgGenTaskSub.getId());
            kgGenTaskSub.setStatus(KgGenTaskSubStatus.SUCCESS);
            kgGenTaskSub = kgGenTaskSubRepository.saveAndFlush(kgGenTaskSub);
        }
        KgAIGenTeachingMaterialAIGenTaskBaseResource kgAIGenTeachingMaterialAIGenTaskBaseResource =
                new KgAIGenTeachingMaterialAIGenTaskBaseResource();
        kgAIGenTeachingMaterialAIGenTaskBaseResource.setCourseId(kgGenTaskMaster.getCourseId());
        kgAIGenTeachingMaterialAIGenTaskBaseResource.setMasterTaskId(kgGenTaskMaster.getId());
        kgAIGenTeachingMaterialAIGenTaskBaseResource.setSubTaskId(kgGenTaskSub.getId());
        return Result.success().setData(kgAIGenTeachingMaterialAIGenTaskBaseResource);
    }

    private void saveQuestionAndVideoRelated(String kgGenTaskMasterId, String kgGenTaskSubTaskId) {
        List<KgGenTaskSub> kgGenTaskSubList = kgGenTaskSubRepository.findLatestByMasterIdAndStepCode(kgGenTaskMasterId,
                KgGenTaskStepCode.STEP_NODE_GEN);
        if (CollectionUtils.isEmpty(kgGenTaskSubList)) {
            return;
        }
        // 找老的关联视频
        List<KgGenTaskSub> copyKgGenTaskSubList = kgGenTaskSubRepository.findLatestByMasterIdAndStepCode(copyKgGenMasterTaskId,
                KgGenTaskStepCode.STEP_RESOURCE_RELATED);
        if (CollectionUtils.isEmpty(copyKgGenTaskSubList)) {
            return;
        }
        KgGenTaskSub copyKgGenTaskSub = copyKgGenTaskSubList.get(0);
        KgGenTaskSub kgGenTaskSub = kgGenTaskSubList.get(0);
        List<KgGenNode> kgGenNodeList = kgGenNodeRepository.findBySubTaskId(kgGenTaskSub.getId());
        List<KgGenNodeRelation> kgGenNodeRelationList = kgGenNodeRelationRepository
                .findByNewMasterTaskId(kgGenTaskMasterId);
        List<KgGenNodeVideoInfo> copyKgGenNodeVideoInfoList = kgGenNodeVideoInfoRepository.findBySubTaskId(copyKgGenTaskSub.getId());
        List<KgGenNodeQuestion> copyKgGenNodeQuestionList = kgGenNodeQuestionRepository.findBySubTaskId(copyKgGenTaskSub.getId());
        List<KgGenNodeResourceRelatedStatistics> copyRelatedStatisticsList = kgGenNodeResourceRelatedStatisticsRepository
                .findBySubTaskId(copyKgGenTaskSub.getId());

        List<String> newNodeIdList = kgGenNodeList.stream().map(KgGenNode::getId).collect(Collectors.toList());
        List<KgGenNodeVideoInfo> kgGenNodeVideoInfoList = genNewTaskNodeVideoInfoList(newNodeIdList, kgGenNodeRelationList,
                copyKgGenNodeVideoInfoList, kgGenTaskMasterId, kgGenTaskSubTaskId);
        List<KgGenNodeQuestion> kgGenNodeQuestionList = genNewTaskNodeQuestionList(newNodeIdList, kgGenNodeRelationList,
                copyKgGenNodeQuestionList, kgGenTaskMasterId, kgGenTaskSubTaskId);
        List<KgGenNodeResourceRelatedStatistics> kgGenNodeResourceRelatedStatisticsList = genNewTaskNodeResourceRelatedStatisticsList(
                copyRelatedStatisticsList, kgGenTaskMasterId, kgGenTaskSubTaskId);

        kgGenNodeVideoInfoRepository.saveAll(kgGenNodeVideoInfoList);
        kgGenNodeQuestionRepository.saveAll(kgGenNodeQuestionList);
        kgGenNodeResourceRelatedStatisticsRepository.saveAll(kgGenNodeResourceRelatedStatisticsList);
    }

    private List<KgGenNodeResourceRelatedStatistics> genNewTaskNodeResourceRelatedStatisticsList(
            List<KgGenNodeResourceRelatedStatistics> copyRelatedStatisticsList,
            String kgGenTaskMasterId,
            String kgGenTaskSubTaskId) {
        List<KgGenNodeResourceRelatedStatistics> kgGenNodeResourceRelatedStatisticsList = new ArrayList<>();
        copyRelatedStatisticsList.forEach(copyRelatedStatistics -> {
            KgGenNodeResourceRelatedStatistics kgGenNodeResourceRelatedStatistics = new KgGenNodeResourceRelatedStatistics();
            kgGenNodeResourceRelatedStatistics.setTotalVideoNum(copyRelatedStatistics.getTotalVideoNum());
            kgGenNodeResourceRelatedStatistics.setSupportVoiceTranVideoNum(copyRelatedStatistics.getSupportVoiceTranVideoNum());
            kgGenNodeResourceRelatedStatistics.setSupportVoiceTranVideoTeacherNames(copyRelatedStatistics.getSupportVoiceTranVideoTeacherNames());
            kgGenNodeResourceRelatedStatistics.setRelatedVideoClipNum(copyRelatedStatistics.getRelatedVideoClipNum());
            kgGenNodeResourceRelatedStatistics.setCourseQuestionNum(copyRelatedStatistics.getCourseQuestionNum());
            kgGenNodeResourceRelatedStatistics.setCourseQuestionTeacherNames(copyRelatedStatistics.getCourseQuestionTeacherNames());
            kgGenNodeResourceRelatedStatistics.setRelatedQuestionNum(copyRelatedStatistics.getRelatedQuestionNum());
            kgGenNodeResourceRelatedStatistics.setMasterTaskId(kgGenTaskMasterId);
            kgGenNodeResourceRelatedStatistics.setSubTaskId(kgGenTaskSubTaskId);
            kgGenNodeResourceRelatedStatistics.setModifierId("admin");
            kgGenNodeResourceRelatedStatistics.setModifierName("admin");
            kgGenNodeResourceRelatedStatistics.setModifyTime(new Date());
            kgGenNodeResourceRelatedStatistics.setCreatorId("admin");
            kgGenNodeResourceRelatedStatistics.setCreatorName("admin");
            kgGenNodeResourceRelatedStatistics.setCreateTime(new Date());

            kgGenNodeResourceRelatedStatisticsList.add(kgGenNodeResourceRelatedStatistics);
        });
        return kgGenNodeResourceRelatedStatisticsList;
    }


    private List<KgGenNodeQuestion> genNewTaskNodeQuestionList(List<String> newNodeIdList,
                                                               List<KgGenNodeRelation> kgGenNodeRelationList,
                                                               List<KgGenNodeQuestion> copyKgGenNodeQuestionList,
                                                               String kgGenTaskMasterId,
                                                               String kgGenTaskSubTaskId) {
        List<KgGenNodeQuestion> kgGenNodeQuestionList = new ArrayList<>();
        copyKgGenNodeQuestionList.forEach(copyQuestion -> {
            KgGenNodeRelation relation = kgGenNodeRelationList.stream()
                    .filter(kgGenNodeRelation ->
                            kgGenNodeRelation.getOriginalNodeId().equals(copyQuestion.getNodeId())
                                    && newNodeIdList.contains(kgGenNodeRelation.getNewNodeId()))
                    .findFirst().orElse(null);
            if (relation == null) {
                return;
            }
            String newNodeId = relation.getNewNodeId();
            KgGenNodeQuestion kgGenNodeQuestion = new KgGenNodeQuestion();
            kgGenNodeQuestion.setNodeId(newNodeId);
            kgGenNodeQuestion.setQuestionId(copyQuestion.getQuestionId());
            kgGenNodeQuestion.setSort(copyQuestion.getSort());
            kgGenNodeQuestion.setQuestionCreatorId(copyQuestion.getQuestionCreatorId());
            kgGenNodeQuestion.setQuestionCreatorName(copyQuestion.getQuestionCreatorName());
            kgGenNodeQuestion.setMasterTaskId(kgGenTaskMasterId);
            kgGenNodeQuestion.setSubTaskId(kgGenTaskSubTaskId);
            kgGenNodeQuestion.setModifierId("admin");
            kgGenNodeQuestion.setModifierName("admin");
            kgGenNodeQuestion.setModifyTime(new Date());
            kgGenNodeQuestion.setCreatorId("admin");
            kgGenNodeQuestion.setCreatorName("admin");
            kgGenNodeQuestion.setCreateTime(new Date());
            kgGenNodeQuestionList.add(kgGenNodeQuestion);
        });
        return kgGenNodeQuestionList;
    }

    private List<KgGenNodeVideoInfo> genNewTaskNodeVideoInfoList(List<String> kgGenNodeIdList,
                                                                 List<KgGenNodeRelation> kgGenNodeRelationList,
                                                                 List<KgGenNodeVideoInfo> copyKgGenNodeVideoInfoList,
                                                                 String kgGenTaskMasterId,
                                                                 String kgGenTaskSubTaskId) {
        List<KgGenNodeVideoInfo> kgGenNodeVideoInfoList = new ArrayList<>();
        copyKgGenNodeVideoInfoList.forEach(copyVideoInfo -> {
            KgGenNodeRelation relation = kgGenNodeRelationList.stream()
                    .filter(kgGenNodeRelation ->
                            kgGenNodeRelation.getOriginalNodeId().equals(copyVideoInfo.getKnowledgeNodeId())
                                    && kgGenNodeIdList.contains(kgGenNodeRelation.getNewNodeId()))
                    .findFirst().orElse(null);
            if (relation == null) {
                return;
            }
            String newNodeId = relation.getNewNodeId();
            KgGenNodeVideoInfo kgGenNodeVideoInfo = new KgGenNodeVideoInfo();
            kgGenNodeVideoInfo.setTextDataSource(copyVideoInfo.getTextDataSource());
            kgGenNodeVideoInfo.setVideoInfoId(copyVideoInfo.getVideoInfoId());
            kgGenNodeVideoInfo.setSchoolYear(copyVideoInfo.getSchoolYear());
            kgGenNodeVideoInfo.setTerm(copyVideoInfo.getTerm());
            kgGenNodeVideoInfo.setTeacherIds(copyVideoInfo.getTeacherIds());
            kgGenNodeVideoInfo.setTeacherNos(copyVideoInfo.getTeacherNos());
            kgGenNodeVideoInfo.setTeacherNames(copyVideoInfo.getTeacherNames());
            kgGenNodeVideoInfo.setVideoDate(copyVideoInfo.getVideoDate());
            kgGenNodeVideoInfo.setCourseTableDetailId(copyVideoInfo.getCourseTableDetailId());
            kgGenNodeVideoInfo.setSegment(copyVideoInfo.getSegment());
            kgGenNodeVideoInfo.setCourseTableId(copyVideoInfo.getCourseTableId());
            kgGenNodeVideoInfo.setGroupId(copyVideoInfo.getGroupId());
            kgGenNodeVideoInfo.setCourseId(copyVideoInfo.getCourseId());
            kgGenNodeVideoInfo.setInnerIp(copyVideoInfo.getInnerIp());
            kgGenNodeVideoInfo.setOuterIp(copyVideoInfo.getOuterIp());
            kgGenNodeVideoInfo.setThumbnailPath(copyVideoInfo.getThumbnailPath());
            kgGenNodeVideoInfo.setKnowledgeNodeId(newNodeId);
            kgGenNodeVideoInfo.setKnowledgeNodeName(copyVideoInfo.getKnowledgeNodeName());
            kgGenNodeVideoInfo.setTextContent(copyVideoInfo.getTextContent());
            kgGenNodeVideoInfo.setStartTimestamp(copyVideoInfo.getStartTimestamp());
            kgGenNodeVideoInfo.setEndTimestamp(copyVideoInfo.getEndTimestamp());
            kgGenNodeVideoInfo.setMasterTaskId(kgGenTaskMasterId);
            kgGenNodeVideoInfo.setSubTaskId(kgGenTaskSubTaskId);
            kgGenNodeVideoInfo.setModifierId("admin");
            kgGenNodeVideoInfo.setModifierName("admin");
            kgGenNodeVideoInfo.setModifyTime(new Date());
            kgGenNodeVideoInfo.setCreatorId("admin");
            kgGenNodeVideoInfo.setCreatorName("admin");
            kgGenNodeVideoInfo.setCreateTime(new Date());
            kgGenNodeVideoInfo.setSimilarityScore(copyVideoInfo.getSimilarityScore());
            kgGenNodeVideoInfoList.add(kgGenNodeVideoInfo);
        });
        return kgGenNodeVideoInfoList;
    }

    public KgAIGenTeachingMaterialNodeResource getKgAIGenTeachingMaterialNodeDetail(String masterTaskId,
                                                                                    Integer importantTag,
                                                                                    Integer level,
                                                                                    String content, Integer page,
                                                                                    Integer pageSize) {
        KgGenTaskMaster kgGenTaskMaster = kgGenTaskMasterRepository.findById(masterTaskId)
                .orElse(null);
        if (Objects.isNull(kgGenTaskMaster)) {
            return null;
        }
        List<KgGenTaskSub> kgGenTaskSubList = kgGenTaskSubRepository.findLatestByMasterIdAndStepCode(kgGenTaskMaster.getId(),
                KgGenTaskStepCode.STEP_NODE_GEN);
        if (CollectionUtils.isEmpty(kgGenTaskSubList)) {
            return null;
        }
        KgGenTaskSub kgGenTaskSub = kgGenTaskSubList.get(0);
        List<KgGenNode> kgGenNodeList = kgGenNodeRepository.findBySubTaskId(kgGenTaskSub.getId());
        List<KgGenNode> sortedNodes = sortHierarchically(kgGenNodeList);
        if (Objects.nonNull(importantTag)) {
            sortedNodes =
                    sortedNodes.stream().filter(t ->
                                    Objects.nonNull(t.getLevelType()) && t.getLevelType().getIndex() == importantTag)
                            .collect(Collectors.toList());
        }
        if (Objects.nonNull(level)) {
            sortedNodes = sortedNodes.stream().filter(t -> Objects.equals(t.getLevel(), level))
                    .collect(Collectors.toList());
        }
        if (Objects.nonNull(content)) {
            sortedNodes = sortedNodes.stream()
                    .filter(t -> (t.getContent() != null && t.getContent().contains(content)) ||
                            (t.getNodeName() != null && t.getNodeName().contains(content)))
                    .collect(Collectors.toList());
        }
        KgAIGenTeachingMaterialNodeResource kgAIGenTeachingMaterialNodeResource = new KgAIGenTeachingMaterialNodeResource();

        if (page != null && pageSize != null) {
            kgAIGenTeachingMaterialNodeResource.setPageCount((int) Math.ceil(sortedNodes.size() / (double) pageSize));
            kgAIGenTeachingMaterialNodeResource.setTotalCount(sortedNodes.size());
            sortedNodes = ListPageHelper.splitList(sortedNodes, page, pageSize);
        }
        kgAIGenTeachingMaterialNodeResource.setCourseId(kgGenTaskMaster.getCourseId());
        kgAIGenTeachingMaterialNodeResource.setCourseName(kgGenTaskMaster.getCourseName());
        kgAIGenTeachingMaterialNodeResource.setNodeCount(kgGenNodeList.size());
        kgAIGenTeachingMaterialNodeResource.setPage(page);
        kgAIGenTeachingMaterialNodeResource.setPageSize(pageSize);
        List<KgAIGenTeachingMaterialNodeResourceItem> kgNodeList = buildKgNodeList(sortedNodes);
        kgAIGenTeachingMaterialNodeResource.setKgNodeList(kgNodeList);
        return kgAIGenTeachingMaterialNodeResource;
    }

    private List<KgAIGenTeachingMaterialNodeResourceItem> buildKgNodeList(List<KgGenNode> sortedNodes) {
        List<KgAIGenTeachingMaterialNodeResourceItem> kgNodeList = new ArrayList<>();
        for (KgGenNode kgGenNode : sortedNodes) {
            KgAIGenTeachingMaterialNodeResourceItem kgAIGenTeachingMaterialNodeResourceItem = new KgAIGenTeachingMaterialNodeResourceItem();
            KnowledgeNodeLevel knowledgeNodeLevel = KnowledgeNodeLevel.getKnowledgeNodeLevel(kgGenNode.getLevel());
            kgAIGenTeachingMaterialNodeResourceItem.setLevelName(Objects.isNull(knowledgeNodeLevel) ? ""
                    : knowledgeNodeLevel.getName());
            LevelType levelType = kgGenNode.getLevelType();
            if (Objects.nonNull(levelType)) {
                kgAIGenTeachingMaterialNodeResourceItem.setKgNodeImportantTagName(kgGenNode.getLevelType().getName());
                kgAIGenTeachingMaterialNodeResourceItem.setKgNodeImportantTag(kgGenNode.getLevelType().getIndex());
            }
            kgAIGenTeachingMaterialNodeResourceItem.setKgNodeId(kgGenNode.getId());
            kgAIGenTeachingMaterialNodeResourceItem.setKgNodeName(kgGenNode.getNodeName());
            kgAIGenTeachingMaterialNodeResourceItem.setLevel(kgGenNode.getLevel());
            kgAIGenTeachingMaterialNodeResourceItem.setKgNodeContent(kgGenNode.getContent());
            kgAIGenTeachingMaterialNodeResourceItem.setChildList(new ArrayList<>());
            kgNodeList.add(kgAIGenTeachingMaterialNodeResourceItem);
        }

        return kgNodeList;
    }

    /**
     * 层级排序主方法 (处理根节点parentNodeId=null的情况)
     *
     * @param allNodes 所有节点集合
     * @return 按层级排序的平铺列表
     */
    public List<KgGenNode> sortHierarchically(List<KgGenNode> allNodes) {
        // 1. 构建快速访问索引
        Map<String, KgGenNode> nodeMap = allNodes.stream()
                .collect(Collectors.toMap(KgGenNode::getId, node -> node));

        // 2. 构建层级关系映射
        Map<String, List<KgGenNode>> childrenMap = new HashMap<>();
        for (KgGenNode node : allNodes) {
            String parentId = node.getParentNodeId();

            // 处理根节点 (parentNodeId为null)
            if (parentId == null) {
                childrenMap.computeIfAbsent("ROOT", k -> new ArrayList<>()).add(node);
            }
            // 处理子节点
            else {
                childrenMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(node);
            }
        }

        // 3. 对每个父节点下的子节点进行排序
        for (List<KgGenNode> children : childrenMap.values()) {
            children.sort(Comparator.comparingInt(child ->
                    child.getNodeOrder() != null ? child.getNodeOrder() : 0
            ));
        }

        // 4. 获取根节点 (parentNodeId为null的节点)
        List<KgGenNode> roots = childrenMap.getOrDefault("ROOT", Collections.emptyList())
                .stream()
                .sorted(Comparator.comparingInt(root ->
                        root.getNodeOrder() != null ? root.getNodeOrder() : 0
                ))
                .collect(Collectors.toList());

        // 5. 深度优先遍历 (非递归)
        return dfsTraversal(roots, childrenMap);
    }

    /**
     * 深度优先遍历生成排序列表
     *
     * @param roots       根节点集合
     * @param childrenMap 层级关系映射
     * @return 平铺的排序列表
     */
    private List<KgGenNode> dfsTraversal(List<KgGenNode> roots,
                                         Map<String, List<KgGenNode>> childrenMap) {
        List<KgGenNode> result = new ArrayList<>();
        Stack<KgGenNode> stack = new Stack<>();
        // 将根节点逆序压入栈 (保证弹出顺序正确)
        for (int i = roots.size() - 1; i >= 0; i--) {
            stack.push(roots.get(i));
        }

        while (!stack.isEmpty()) {
            KgGenNode current = stack.pop();
            result.add(current);

            // 获取当前节点的子节点
            List<KgGenNode> children = childrenMap.get(current.getId());
            if (children != null && !children.isEmpty()) {
                // 逆序压入栈 (确保顺序正确)
                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            }
        }
        return result;
    }

    public KgAIGenRelatedStatisticsResource getKnowledgeGraphAIGenResourceRelatedStatistics(String masterTaskId,
                                                                                            String subTaskId) {
        KgGenNodeResourceRelatedStatistics kgGenNodeResourceRelatedStatistics = kgGenNodeResourceRelatedStatisticsRepository.
                findByMasterTaskIdAndSubTaskId(masterTaskId, subTaskId);
        if (Objects.isNull(kgGenNodeResourceRelatedStatistics)) {
            return null;
        }
        KgAIGenRelatedStatisticsResource kgAIGenRelatedStatisticsResource = new KgAIGenRelatedStatisticsResource();
        kgAIGenRelatedStatisticsResource.setTotalVideoNum(kgGenNodeResourceRelatedStatistics.getTotalVideoNum());
        kgAIGenRelatedStatisticsResource.setSupportVoiceTranVideoNum(kgGenNodeResourceRelatedStatistics.getSupportVoiceTranVideoNum());
        kgAIGenRelatedStatisticsResource.setSupportVoiceTranVideoTeacherNames(
                kgGenNodeResourceRelatedStatistics.getSupportVoiceTranVideoTeacherNames());
        kgAIGenRelatedStatisticsResource.setRelatedVideoClipNum(kgGenNodeResourceRelatedStatistics.getRelatedVideoClipNum());
        kgAIGenRelatedStatisticsResource.setCourseQuestionNum(kgGenNodeResourceRelatedStatistics.getCourseQuestionNum());
        kgAIGenRelatedStatisticsResource.setCourseQuestionTeacherNames(kgGenNodeResourceRelatedStatistics.getCourseQuestionTeacherNames());
        kgAIGenRelatedStatisticsResource.setRelatedQuestionNum(kgGenNodeResourceRelatedStatistics.getRelatedQuestionNum());
        return kgAIGenRelatedStatisticsResource;
    }

    public KgAIGenTeachingMaterialNodeResourceItem getKgAIGenNodeDetail(String nodeId) {
        KgGenNode kgGenNode = kgGenNodeRepository.findById(nodeId).orElse(null);
        if (Objects.isNull(kgGenNode)) {
            return null;
        }
        KgAIGenTeachingMaterialNodeResourceItem kgAIGenTeachingMaterialNodeResourceItem = new KgAIGenTeachingMaterialNodeResourceItem();
        KnowledgeNodeLevel knowledgeNodeLevel = KnowledgeNodeLevel.getKnowledgeNodeLevel(kgGenNode.getLevel());
        kgAIGenTeachingMaterialNodeResourceItem.setLevelName(Objects.isNull(knowledgeNodeLevel) ? ""
                : knowledgeNodeLevel.getName());
        LevelType levelType = kgGenNode.getLevelType();
        if (Objects.nonNull(levelType)) {
            kgAIGenTeachingMaterialNodeResourceItem.setKgNodeImportantTagName(kgGenNode.getLevelType().getName());
            kgAIGenTeachingMaterialNodeResourceItem.setKgNodeImportantTag(kgGenNode.getLevelType().getIndex());
        }
        kgAIGenTeachingMaterialNodeResourceItem.setKgNodeId(kgGenNode.getId());
        kgAIGenTeachingMaterialNodeResourceItem.setKgNodeName(kgGenNode.getNodeName());
        kgAIGenTeachingMaterialNodeResourceItem.setLevel(kgGenNode.getLevel());
        kgAIGenTeachingMaterialNodeResourceItem.setKgNodeContent(kgGenNode.getContent());
        kgAIGenTeachingMaterialNodeResourceItem.setChildList(new ArrayList<>());
        return kgAIGenTeachingMaterialNodeResourceItem;
    }

    public KgAIGenNodeQuestionPageResource getKgAIGenNodeQuestion(KgAIGenNodeQuestionQueryParam param) {
        KgAIGenNodeQuestionPageResource kgAIGenNodeQuestionPageResource = new KgAIGenNodeQuestionPageResource();
        kgAIGenNodeQuestionPageResource.setPage(param.getPage());
        kgAIGenNodeQuestionPageResource.setPageSize(param.getPageSize());
        kgAIGenNodeQuestionPageResource.setTotalCount(0);
        kgAIGenNodeQuestionPageResource.setPageCount(0);
        kgAIGenNodeQuestionPageResource.setQuestionList(new ArrayList<>());

        List<KgGenNodeQuestion> kgGenNodeQuestionList = kgGenNodeQuestionRepository.findByNodeId(param.getKgNodeId());
        if (CollectionUtils.isEmpty(kgGenNodeQuestionList)) {
            return kgAIGenNodeQuestionPageResource;
        }
        List<String> questionIdList = kgGenNodeQuestionList.stream().map(KgGenNodeQuestion::getQuestionId).distinct().collect(Collectors.toList());
        List<QuestionLibraryResource> questionLibraryResourceList =
                courseKnowledgeGraphActionLogService.getQuestionLibraryList(questionIdList);
        List<QuestionLibraryResource> resourceList = new ArrayList<>();

        for (KgGenNodeQuestion kgGenNodeQuestion : kgGenNodeQuestionList) {
            QuestionLibraryResource questionLibraryResource =
                    questionLibraryResourceList.stream().filter(questionLibraryResource1 ->
                                    questionLibraryResource1.getId().equals(kgGenNodeQuestion.getQuestionId()))
                            .findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(questionLibraryResource)) {
                questionLibraryResource.setQuestionStemText(courseKnowledgeGraphService
                        .convertQuestionHtml(questionLibraryResource.getQuestionStem()));
                questionLibraryResource.setShowOrder(kgGenNodeQuestion.getSort());
                resourceList.add(questionLibraryResource);
            }
        }
        resourceList = resourceList.stream()
                .sorted(Comparator.comparingInt(QuestionLibraryResource::getShowOrder).reversed())
                .collect(Collectors.toList());
        if (param.getPage() != null && param.getPageSize() != null) {
            kgAIGenNodeQuestionPageResource.setPageCount((int) Math.ceil(resourceList.size() / (double) param.getPageSize()));
            kgAIGenNodeQuestionPageResource.setTotalCount(resourceList.size());
            resourceList = ListPageHelper.splitList(resourceList, param.getPage(), param.getPageSize());
        }
        kgAIGenNodeQuestionPageResource.setQuestionList(resourceList);
        return kgAIGenNodeQuestionPageResource;
    }

    public KgAIGenNodeVideoPageResource getKnowledgeGraphAIGenNodeVideo(KgAIGenNodeVideoQueryParam param) {
        KgAIGenNodeVideoPageResource kgAIGenNodeVideoPageResource = new KgAIGenNodeVideoPageResource();
        kgAIGenNodeVideoPageResource.setPage(param.getPage());
        kgAIGenNodeVideoPageResource.setPageSize(param.getPageSize());
        kgAIGenNodeVideoPageResource.setTotalCount(0);
        kgAIGenNodeVideoPageResource.setPageCount(0);
        kgAIGenNodeVideoPageResource.setVideoTextList(new ArrayList<>());

        List<KgGenNodeVideoInfo> kgGenNodeVideoInfoList = kgGenNodeVideoInfoRepository.findAll(
                KgGenNodeVideoInfoSpecification.specification(param));

        List<String> teacherIdList = kgGenNodeVideoInfoList.stream()
                .map(KgGenNodeVideoInfo::getTeacherIds)
                .flatMap(teacherIds ->
                        Arrays.stream(teacherIds.split(",")))
                .distinct().collect(Collectors.toList());
        List<KgGenNodeVideoInfo> resultList = new ArrayList<>();

        teacherIdList.forEach(teacherId -> {
            // 过滤包含的教师
            List<KgGenNodeVideoInfo> teacherTextList = kgGenNodeVideoInfoList
                    .stream()
                    .filter(t -> t.getTeacherIds().contains(teacherId))
                    .sorted(Comparator.comparing(KgGenNodeVideoInfo::getGroupId))
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(teacherTextList)) {
                return;
            }
            // 根据班级分组，优先取班级教师只有一个的
            List<KgGenNodeVideoInfo> notContainsManyTeacherTextList = teacherTextList
                    .stream()
                    .filter(t -> {
                        String teacherIds = t.getTeacherIds();
                        return teacherIds.split(",").length == 1;
                    })
                    .collect(Collectors.toList());
            String groupId = teacherTextList.get(0).getGroupId();
            if (CollectionUtils.isNotEmpty(notContainsManyTeacherTextList)) {
                groupId = notContainsManyTeacherTextList.get(0).getGroupId();
            }
            String finalGroupId = groupId;
            List<KgGenNodeVideoInfo> groupTextList = teacherTextList
                    .stream()
                    .filter(t -> t.getGroupId().equals(finalGroupId))
                    .sorted(Comparator.comparing(KgGenNodeVideoInfo::getStartTimestamp))
                    .collect(Collectors.toList());
            List<KgGenNodeVideoInfo> distinctList = groupTextList.stream()
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
            resultList.addAll(distinctList);
        });
        List<CourseKnowledgeGraphNodeVideoInfoTextModel> returnList = getKgAIGenNodeVideoTextList(resultList);
        if (CollectionUtils.isEmpty(returnList)) {
            return kgAIGenNodeVideoPageResource;
        }
        returnList = returnList.stream()
                .sorted(Comparator.comparing(CourseKnowledgeGraphNodeVideoInfoTextModel::getVideoDate))
                .collect(Collectors.toList());

        kgAIGenNodeVideoPageResource.setPageCount((int) Math.ceil(returnList.size() / (double) param.getPageSize()));
        kgAIGenNodeVideoPageResource.setTotalCount(returnList.size());
        returnList = ListPageHelper.splitList(returnList, param.getPage(), param.getPageSize());

        kgAIGenNodeVideoPageResource.setVideoTextList(returnList);
        return kgAIGenNodeVideoPageResource;
    }

    private List<CourseKnowledgeGraphNodeVideoInfoTextModel> getKgAIGenNodeVideoTextList(List<KgGenNodeVideoInfo> content) {
        List<CourseKnowledgeGraphNodeVideoInfoTextModel> kgAIGenNodeVideoTextList = new ArrayList<>();
        for (KgGenNodeVideoInfo kgGenNodeVideoInfo : content) {
            CourseKnowledgeGraphNodeVideoInfoTextModel courseKnowledgeGraphNodeVideoInfoTextModel =
                    getCourseKnowledgeGraphNodeVideoInfoTextModel(kgGenNodeVideoInfo);
            kgAIGenNodeVideoTextList.add(courseKnowledgeGraphNodeVideoInfoTextModel);
        }
        return kgAIGenNodeVideoTextList;
    }

    public List<CourseKnowledgeGraphNodeVideoInfoTextModel> getFilterAIGenNodeVideoInfoTextList(String nodeId, String videoInfoId) {
        List<KgGenNodeVideoInfo> kgGenNodeVideoInfoList = kgGenNodeVideoInfoRepository.findByKnowledgeNodeIdAndVideoInfoId(nodeId, videoInfoId);
        List<CourseKnowledgeGraphNodeVideoInfoTextModel> courseKnowledgeGraphNodeVideoInfoTextModelList = new ArrayList<>();
        for (KgGenNodeVideoInfo nodeVideoInfo : kgGenNodeVideoInfoList) {
            CourseKnowledgeGraphNodeVideoInfoTextModel courseKnowledgeGraphNodeVideoInfoTextModel =
                    getCourseKnowledgeGraphNodeVideoInfoTextModel(nodeVideoInfo);
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

    private CourseKnowledgeGraphNodeVideoInfoTextModel getCourseKnowledgeGraphNodeVideoInfoTextModel(KgGenNodeVideoInfo kgGenNodeVideoInfo) {
        CourseKnowledgeGraphNodeVideoInfoTextModel courseKnowledgeGraphNodeVideoInfoTextModel = new CourseKnowledgeGraphNodeVideoInfoTextModel();
        courseKnowledgeGraphNodeVideoInfoTextModel.setId(kgGenNodeVideoInfo.getId());
        courseKnowledgeGraphNodeVideoInfoTextModel.setVideoInfoId(kgGenNodeVideoInfo.getVideoInfoId());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTextDataSourceIndex(kgGenNodeVideoInfo.getTextDataSource().getIndex());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTextDataSourceName(kgGenNodeVideoInfo.getTextDataSource().getName());
        courseKnowledgeGraphNodeVideoInfoTextModel.setSchoolYear(kgGenNodeVideoInfo.getSchoolYear());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTerm(kgGenNodeVideoInfo.getTerm());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTeacherIds(kgGenNodeVideoInfo.getTeacherIds());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTeacherNos(kgGenNodeVideoInfo.getTeacherNos());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTeacherNames(kgGenNodeVideoInfo.getTeacherNames());
        courseKnowledgeGraphNodeVideoInfoTextModel.setVideoDate(kgGenNodeVideoInfo.getVideoDate());
        courseKnowledgeGraphNodeVideoInfoTextModel.setSegment(kgGenNodeVideoInfo.getSegment());
        courseKnowledgeGraphNodeVideoInfoTextModel.setInnerIp(kgGenNodeVideoInfo.getInnerIp());
        courseKnowledgeGraphNodeVideoInfoTextModel.setOuterIp(kgGenNodeVideoInfo.getOuterIp());
        courseKnowledgeGraphNodeVideoInfoTextModel.setThumbnailPath(kgGenNodeVideoInfo.getThumbnailPath());
        courseKnowledgeGraphNodeVideoInfoTextModel.setKnowledgeNodeId(kgGenNodeVideoInfo.getKnowledgeNodeId());
        courseKnowledgeGraphNodeVideoInfoTextModel.setKnowledgeNodeName(kgGenNodeVideoInfo.getKnowledgeNodeName());
        courseKnowledgeGraphNodeVideoInfoTextModel.setTextContent(kgGenNodeVideoInfo.getTextContent());
        courseKnowledgeGraphNodeVideoInfoTextModel.setHighLightTextContent(kgGenNodeVideoInfo.getHighLightTextContent());
        courseKnowledgeGraphNodeVideoInfoTextModel.setStartTimestamp(kgGenNodeVideoInfo.getStartTimestamp());
        courseKnowledgeGraphNodeVideoInfoTextModel.setEndTimestamp(kgGenNodeVideoInfo.getEndTimestamp());
        courseKnowledgeGraphNodeVideoInfoTextModel.setSimilarityScore(kgGenNodeVideoInfo.getSimilarityScore());

        return courseKnowledgeGraphNodeVideoInfoTextModel;
    }

    public Result modifyKnowledgeGraphAIGenNode(KgAIGenNodeModifyParam param) {
        KgGenNode kgGenNode = kgGenNodeRepository.findById(param.getNodeId()).orElse(null);
        if (ObjectUtils.isEmpty(kgGenNode)) {
            return Result.error("节点不存在");
        }
        kgGenNode.setModifierId(param.getOperatorId());
        kgGenNode.setModifierName(param.getOperatorName());
        kgGenNode.setModifyTime(new Date());
        if (param.getModifyType() == 0) {
            kgGenNode.setNodeName(param.getNodeName());
        } else if (param.getModifyType() == 1) {
            kgGenNode.setContent(param.getNodeContent());
        } else if (param.getModifyType() == 2) {
            LevelType levelType = Objects.isNull(param.getImportantTag()) ? null : LevelType.getLevelType(param.getImportantTag());
            kgGenNode.setLevelType(levelType);
        }
        kgGenNodeRepository.save(kgGenNode);
        return Result.success();
    }

    public Result addKnowledgeGraphAIGenNode(KgAIGenNodeAddParam param) {
        String parentId = param.getParentNodeId();
        KgGenNode kgGenNode = new KgGenNode();
        kgGenNode.setId(generateUUIDWithoutHyphens());
        kgGenNode.setCreatorId(param.getOperatorId());
        kgGenNode.setCreatorName(param.getOperatorName());
        kgGenNode.setCreateTime(new Date());
        kgGenNode.setModifierId(param.getOperatorId());
        kgGenNode.setModifierName(param.getOperatorName());
        kgGenNode.setModifyTime(new Date());
        kgGenNode.setNodeName(param.getNodeName());
        kgGenNode.setMasterTaskId(param.getMasterTaskId());
        kgGenNode.setSubTaskId(param.getSubTaskId());
        if (parentId.equals(param.getCourseId())) {
            // 创建课程根节点
            kgGenNode.setLevel(1);
            kgGenNode.setParentNodeId(null);
            Integer maxOrder = kgGenNodeRepository.findMaxOrderByParentNodeIdAndTaskId(param.getSubTaskId());
            kgGenNode.setNodeOrder(maxOrder == null ? 1 : maxOrder + 1);
        } else {
            KgGenNode kgGenNodeParent = kgGenNodeRepository.findById(parentId).orElse(null);
            if (ObjectUtils.isEmpty(kgGenNodeParent)) {
                return Result.error("父节点不存在");
            }
            int level = kgGenNodeParent.getLevel() + 1;
            if (level > KNOWLEDGE_NODE_MAX_LEVEL) {
                return Result.error("该知识图谱最多允许创建十级知识点");
            }
            kgGenNode.setLevel(level);
            kgGenNode.setParentNodeId(parentId);
            kgGenNode.setChapterContentId(kgGenNodeParent.getChapterContentId());
            Integer maxOrder = kgGenNodeRepository.findMaxOrderByParentNodeId(parentId);
            kgGenNode.setNodeOrder(maxOrder == null ? 1 : maxOrder + 1);
        }
        kgGenNodeRepository.save(kgGenNode);
        return Result.success();
    }

    @Transactional
    public Result sortedKnowledgeGraphAIGenNode(KgAIGenNodeSortedParam param) {
        List<KgAIGenNodeSortedParamNode> nodeList = param.getNodeList();
        List<String> nodeIdList = nodeList.stream().map(KgAIGenNodeSortedParamNode::getNodeId).collect(Collectors.toList());
        List<KgGenNode> kgGenNodeList = kgGenNodeRepository.findAllById(nodeIdList);
        int i = 1;
        List<KgGenNode> resultList = new ArrayList<>();
        for (KgAIGenNodeSortedParamNode sortedParamNode : nodeList) {
            KgGenNode kgGenNode = kgGenNodeList
                    .stream().filter(node -> node.getId().equals(sortedParamNode.getNodeId()))
                    .findFirst().orElse(null);
            if (Objects.isNull(kgGenNode)) {
                return Result.error("知识点不存在");
            }
            kgGenNode.setModifierId(param.getOperatorId());
            kgGenNode.setModifierName(param.getOperatorName());
            kgGenNode.setModifyTime(new Date());
            kgGenNode.setNodeOrder(i);
            if (sortedParamNode.isIsSortedNode()) {
                if (param.getCourseId().equals(sortedParamNode.getParentNodeId())
                        || "root".equals(sortedParamNode.getParentNodeId())) {
                    kgGenNode.setLevel(1);
                    kgGenNode.setParentNodeId(null);
                } else {
                    KgGenNode parentNode = kgGenNodeRepository.findById(sortedParamNode.getParentNodeId()).orElse(null);
                    if (Objects.isNull(parentNode)) {
                        return Result.error("父节点不存在");
                    }
                    if (parentNode.getLevel() >= KNOWLEDGE_NODE_MAX_LEVEL) {
                        throw new NodeLevelExceededException("层级不能超过 10 级，父节点：" + parentNode.getId());
                    }
                    kgGenNode.setParentNodeId(parentNode.getId());
                    kgGenNode.setLevel(parentNode.getLevel() + 1);
                }
                // 排序后需要对子集的层级重新修改
                updateChildNodeLevels(kgGenNode);
            }

            resultList.add(kgGenNode);
            i++;
        }
        kgGenNodeRepository.saveAll(resultList);
        return Result.success();
    }

    /**
     * 递归更新子节点层级
     *
     * @param parentNode 父节点
     */
    private void updateChildNodeLevels(KgGenNode parentNode) {
        // 查找所有直接子节点
        List<KgGenNode> childNodes = kgGenNodeRepository.findByParentNodeId(parentNode.getId());

        if (CollectionUtils.isNotEmpty(childNodes)) {
            for (KgGenNode childNode : childNodes) {
                int newLevel = parentNode.getLevel() + 1;
                if (newLevel > KNOWLEDGE_NODE_MAX_LEVEL) {
                    throw new NodeLevelExceededException("层级不能超过 10 级，违规节点：" + childNode.getId());
                }
                // 更新子节点层级
                childNode.setLevel(newLevel);
                // 递归更新孙子节点层级
                updateChildNodeLevels(childNode);
            }
            // 批量保存子节点
            kgGenNodeRepository.saveAll(childNodes);
        }
    }

    public void deleteKgGenNodeById(String kgGenNodeId) {
        KgGenNode parentKgGenNode = kgGenNodeRepository.getKgGenNodeById(kgGenNodeId);
        if (ObjectUtils.isEmpty(parentKgGenNode)) {
            return;
        }
        String masterTaskId = parentKgGenNode.getMasterTaskId();

        List<KgGenNode> kgGenNodeList = kgGenNodeRepository.findByMasterTaskId(masterTaskId);
        List<KgGenNode> effectiveKgGenNodeList = kgGenNodeList.stream()
                .filter(kgGenNode -> !kgGenNode.getId().equals(kgGenNodeId))
                .collect(Collectors.toList());
        // 构建父节点ID → 子节点列表的映射（核心优化）
        Map<String, List<KgGenNode>> parentChildMap = new HashMap<>();
        for (KgGenNode node : effectiveKgGenNodeList) {
            String parentId = node.getParentNodeId();
            parentChildMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(node);
        }
        List<KgGenNode> descendants = new ArrayList<>();
        descendants.add(parentKgGenNode);
        Deque<KgGenNode> stack = new ArrayDeque<>();
        stack.push(parentKgGenNode);
        while (!stack.isEmpty()) {
            KgGenNode current = stack.pop();
            // 直接通过Map获取当前节点的子节点列表（O(1)复杂度）
            List<KgGenNode> children = parentChildMap.get(current.getId());
            if (children != null) {
                for (KgGenNode child : children) {
                    descendants.add(child); // 添加子节点到结果
                    stack.push(child);       // 子节点入栈继续遍历
                }
            }
        }
        kgGenNodeRepository.deleteAll(descendants);
    }
}

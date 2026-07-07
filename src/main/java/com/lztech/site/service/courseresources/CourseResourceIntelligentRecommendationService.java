package com.lztech.site.service.courseresources;

import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNode;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeVideoInfoText;
import com.lztech.domain.model.term.enums.TermType;
import com.lztech.persistence.repositories.course.CourseTeachingTeamRepository;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphNodeVideoInfoTextRepository;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeVideoInfoTextModel;
import com.lztech.site.viewmodel.courseresourceintelligentrecommendation.CourseResourceIntelligentRecommendationQueryParam;
import com.lztech.site.viewmodel.courseresourceintelligentrecommendation.CourseResourceIntelligentRecommendationTermResource;
import com.lztech.site.viewmodel.courseresourceintelligentrecommendation.CourseResourceIntelligentRecommendationTermTeacherResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.courseknowledgegraph.specification.CourseKnowledgeGraphNodeVideoInfoTextSpecification.specification;

@Service
public class CourseResourceIntelligentRecommendationService {
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;

    @Autowired
    private CourseKnowledgeGraphNodeVideoInfoTextRepository courseKnowledgeGraphNodeVideoInfoTextRepository;

    public List<CourseResourceIntelligentRecommendationTermResource> getTermList(
            String courseId, String courseVersionId,
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,
            CourseKnowledgeGraphNode courseKnowledgeGraphNode,
            Double similarityScore
    ) {
        List<CourseTeachingTeam> courseTeachingTeamList = courseTeachingTeamRepository.findByCourseIdAndCourseVersionId(courseId, courseVersionId);
        if (CollectionUtils.isEmpty(courseTeachingTeamList)) {
            return new ArrayList<>();
        }
        // 构建教师ID集合和教师信息映射
        Set<String> teacherIdSet = new HashSet<>();
        Map<String, CourseTeachingTeam> teacherInfoMap = new HashMap<>();
        for (CourseTeachingTeam team : courseTeachingTeamList) {
            if (StringUtils.isNotBlank(team.getTeacherId())) {
                teacherIdSet.add(team.getTeacherId().trim());
                teacherInfoMap.put(team.getTeacherId().trim(), team);
            }
        }
        List<CourseKnowledgeGraphNodeVideoInfoText> courseKnowledgeGraphNodeVideoInfoTextList = new ArrayList<>();
        if (ObjectUtils.isEmpty(similarityScore)){
            courseKnowledgeGraphNodeVideoInfoTextList =
                    courseKnowledgeGraphNodeVideoInfoTextRepository.findByCourseKnowledgeGraphDomainIdAndNodeId(
                    courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId());
        }else{
            courseKnowledgeGraphNodeVideoInfoTextList =
                    courseKnowledgeGraphNodeVideoInfoTextRepository.findByCourseKnowledgeGraphDomainIdAndNodeIdAndSimilarityScore(
                            courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphNode.getId(),similarityScore);
        }


        // 筛选包含任意teacherId的数据
        List<CourseKnowledgeGraphNodeVideoInfoText> filteredList = courseKnowledgeGraphNodeVideoInfoTextList.stream()
                .filter(item -> {
                    String teacherIds = item.getTeacherIds();  // 假设有getTeacherIds()方法
                    if (teacherIds == null || teacherIds.trim().isEmpty()) {
                        return false;
                    }
                    // 分割teacherIds并转换为Set
                    Set<String> ids = Arrays.stream(teacherIds.split(",")).map(String::trim).collect(Collectors.toSet());
                    // 检查两个集合是否有交集
                    return !Collections.disjoint(ids, teacherIdSet);
                }).collect(Collectors.toList());
        // 4. 按学年学期分组，并收集教师信息
        Map<String, Map<String, Object>> termMap = new LinkedHashMap<>();
        for (CourseKnowledgeGraphNodeVideoInfoText text : filteredList) {
            // 检查学年学期是否有效
            String schoolYear = text.getSchoolYear();
            Integer term = text.getTerm();
            if (StringUtils.isBlank(schoolYear) || term == null) {
                continue;
            }
            schoolYear = schoolYear.trim();
            String termKey = schoolYear + "|" + term;
            // 初始化学期条目
            String finalSchoolYear = schoolYear;
            Map<String, Object> termData = termMap.computeIfAbsent(termKey, k -> {
                Map<String, Object> map = new HashMap<>();
                map.put("schoolYear", finalSchoolYear);
                map.put("term", term);
                map.put("teachers", new HashMap<String, CourseResourceIntelligentRecommendationTermTeacherResource>());
                return map;
            });
            // 处理教师信息
            Map<String, CourseResourceIntelligentRecommendationTermTeacherResource> teachersMap =
                    (Map<String, CourseResourceIntelligentRecommendationTermTeacherResource>) termData.get("teachers");
            // 解析教师IDs
            if (StringUtils.isNotBlank(text.getTeacherIds())) {
                Arrays.stream(text.getTeacherIds().split(",")).map(String::trim).filter(teacherIdSet::contains) // 只保留在教师集合中的ID
                        .forEach(teacherId -> {
                            // 如果尚未添加此教师，创建资源对象
                            teachersMap.computeIfAbsent(teacherId, id -> {
                                CourseTeachingTeam team = teacherInfoMap.get(id);
                                CourseResourceIntelligentRecommendationTermTeacherResource teacherResource =
                                        new CourseResourceIntelligentRecommendationTermTeacherResource();
                                teacherResource.setTeacherId(id);
                                teacherResource.setTeacherName(team != null ? team.getTeacherName() : "");
                                teacherResource.setTeacherNo(team != null ? team.getTeacherNo() : "");
                                return teacherResource;
                            });
                        });
            }
        }
        List<CourseResourceIntelligentRecommendationTermResource> result = new ArrayList<>();
        for (Map.Entry<String, Map<String, Object>> entry : termMap.entrySet()) {
            Map<String, Object> termData = entry.getValue();
            // 获取教师资源列表（从map的values中获取）
            Map<String, CourseResourceIntelligentRecommendationTermTeacherResource> teachersMap =
                    (Map<String, CourseResourceIntelligentRecommendationTermTeacherResource>) termData.get("teachers");
            List<CourseResourceIntelligentRecommendationTermTeacherResource> teacherResources =
                    new ArrayList<>(teachersMap.values());
            // 创建学期资源对象
            CourseResourceIntelligentRecommendationTermResource termResource =
                    new CourseResourceIntelligentRecommendationTermResource();
            termResource.setSchoolYear((String) termData.get("schoolYear"));
            termResource.setTerm((Integer) termData.get("term"));
            termResource.setTeacherList(teacherResources);
            TermType termType = TermType.getTermType(termResource.getTerm());
            if (Objects.nonNull(termType)) {
                termResource.setTermName(termResource.getSchoolYear() + termType.getName());
            } else {
                termResource.setTermName(termResource.getSchoolYear() + "-" + termResource.getTerm());
            }
            result.add(termResource);
        }
        // 6. 按学年学期倒序排列
        result.sort((term1, term2) -> {
            // 创建可排序的复合键
            String key1 = term1.getSchoolYear() + "-" + String.format("%02d", term1.getTerm());
            String key2 = term2.getSchoolYear() + "-" + String.format("%02d", term2.getTerm());
            // 倒序排序：较新的学年学期排在前面
            return key2.compareTo(key1);
        });
        return result;
    }

    public List<CourseKnowledgeGraphNodeVideoInfoTextModel> getCourseKnowledgeGraphNodeVideoInfoTexts(
            CourseResourceIntelligentRecommendationQueryParam queryParams) {
        List<CourseKnowledgeGraphNodeVideoInfoText> courseKnowledgeGraphNodeVideoInfoTexts =
                courseKnowledgeGraphNodeVideoInfoTextRepository.findAll(specification(queryParams));

        List<String> teacherIdList = Arrays.asList(queryParams.getTeacherIds().split(","));
        List<CourseKnowledgeGraphNodeVideoInfoText> resultList = new ArrayList<>();
        teacherIdList.forEach(teacherId -> {
            // 过滤包含的教师
            List<CourseKnowledgeGraphNodeVideoInfoText> teacherTextList = courseKnowledgeGraphNodeVideoInfoTexts
                    .stream()
                    .filter(t -> t.getTeacherIds().contains(teacherId))
                    .sorted(Comparator.comparing(CourseKnowledgeGraphNodeVideoInfoText::getGroupId))
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(teacherTextList)) {
                return;
            }
            // 根据班级分组，优先取班级教师只有一个的
            List<CourseKnowledgeGraphNodeVideoInfoText> notContainsManyTeacherTextList = teacherTextList
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
            List<CourseKnowledgeGraphNodeVideoInfoText> groupTextList = teacherTextList
                    .stream()
                    .filter(t -> t.getGroupId().equals(finalGroupId))
                    .sorted(Comparator.comparingDouble(CourseKnowledgeGraphNodeVideoInfoText::getSimilarityScore).reversed())
                    .collect(Collectors.toList());
            // 按照courseTableDetailId去重

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
            resultList.addAll(distinctList);
        });
        List<CourseKnowledgeGraphNodeVideoInfoTextModel> returnList = new ArrayList<>();
        resultList.forEach(courseKnowledgeGraphNodeVideoInfoText -> {
            CourseKnowledgeGraphNodeVideoInfoTextModel model = new CourseKnowledgeGraphNodeVideoInfoTextModel();
            CourseKnowledgeGraphNodeVideoInfoTextModel courseKnowledgeGraphNodeVideoInfoTextModel =
                    new CourseKnowledgeGraphNodeVideoInfoTextModel();
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
            courseKnowledgeGraphNodeVideoInfoTextModel.setHighLightTextContent(
                    courseKnowledgeGraphNodeVideoInfoText.getHighLightTextContent());
            courseKnowledgeGraphNodeVideoInfoTextModel.setStartTimestamp(courseKnowledgeGraphNodeVideoInfoText.getStartTimestamp());
            courseKnowledgeGraphNodeVideoInfoTextModel.setEndTimestamp(courseKnowledgeGraphNodeVideoInfoText.getEndTimestamp());
            courseKnowledgeGraphNodeVideoInfoTextModel.setSimilarityScore(courseKnowledgeGraphNodeVideoInfoText.getSimilarityScore());
            returnList.add(courseKnowledgeGraphNodeVideoInfoTextModel);
        });
        return returnList
                .stream()
                .sorted(Comparator.comparingDouble(CourseKnowledgeGraphNodeVideoInfoTextModel::getSimilarityScore).reversed())
                .collect(Collectors.toList());
    }
}

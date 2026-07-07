package com.lztech.site.service.courseknowledgegraph;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeQuestion;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphNodeQuestionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.questionknowledgepointvo.QuestionKnowledgePointVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class QuestionCourseKnowledgeGraphService {

    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;
    @Autowired
    private CourseKnowledgeGraphNodeQuestionRepository courseKnowledgeGraphNodeQuestionRepository;

    @Transactional
    public Result createCourseKnowledgeGraphNodeQuestionList(QuestionKnowledgePointVo questionKnowledgePointVo) {
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                courseKnowledgeGraphService.getCourseKnowledgeGraphDomainById(questionKnowledgePointVo.getCourseKnowledgeGraphId());
        if (ObjectUtils.isEmpty(courseKnowledgeGraphDomain)) {
            return Result.error(ErrorResult.customError(
                    "id为【" + questionKnowledgePointVo.getCourseKnowledgeGraphId() + "】的课程知识图谱不存在"));
        }
        if (CollectionUtils.isEmpty(questionKnowledgePointVo.getPointIdList())){
            List<CourseKnowledgeGraphNodeQuestion> courseKnowledgeGraphNodeQuestionList =
                    courseKnowledgeGraphNodeQuestionRepository.findByQuestionIdInOrderByCreateTimeAsc(
                            Collections.singletonList(questionKnowledgePointVo.getQuestionId()));
            // 取消关联所有知识点
            courseKnowledgeGraphNodeQuestionRepository.deleteByQuestionId(questionKnowledgePointVo.getQuestionId());
            // 修改排序字段
            for (CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion : courseKnowledgeGraphNodeQuestionList) {
                courseKnowledgeGraphNodeQuestionRepository.updateByKnowledgeGraphNodeIdAndQuestionSort(
                        courseKnowledgeGraphNodeQuestion.getKnowledgeNodeId(), courseKnowledgeGraphNodeQuestion.getSort());
            }
            return Result.success();
        }
        List<CourseKnowledgeGraphNodeQuestion> courseKnowledgeGraphNodeQuestionList =
                courseKnowledgeGraphNodeQuestionRepository.findByCourseKnowledgeGraphDomainAndQuestionId(courseKnowledgeGraphDomain,
                        questionKnowledgePointVo.getQuestionId());
        Date now = new Date();
        List<CourseKnowledgeGraphNodeQuestion> resultList = new ArrayList<>();
        courseKnowledgeGraphNodeQuestionRepository.deleteNotConcatPoint(questionKnowledgePointVo.getQuestionId(),
                questionKnowledgePointVo.getPointIdList());
        //修改无需关联的顺序
        // 将 pointIdList 转换为 HashSet 以提高查找效率
        Set<String> pointIdSet = new HashSet<>(questionKnowledgePointVo.getPointIdList());
        // 使用 Stream 进行过滤
        List<CourseKnowledgeGraphNodeQuestion> filteredList =
                courseKnowledgeGraphNodeQuestionList.stream()
                        .filter(question -> !pointIdSet.contains(question.getKnowledgeNodeId()))
                        .collect(Collectors.toList());
        for (CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion : filteredList) {
            courseKnowledgeGraphNodeQuestionRepository.updateByKnowledgeGraphNodeIdAndQuestionSort(
                    courseKnowledgeGraphNodeQuestion.getKnowledgeNodeId(), courseKnowledgeGraphNodeQuestion.getSort());
        }
        questionKnowledgePointVo.getPointIdList().forEach(pointId -> {

            CourseKnowledgeGraphNodeQuestion courseKnowledgeGraphNodeQuestion = courseKnowledgeGraphNodeQuestionList.stream()
                    .filter(q -> q.getKnowledgeNodeId().equals(pointId)).findFirst().orElse(null);
            if (ObjectUtils.isEmpty(courseKnowledgeGraphNodeQuestion)) {
                List<CourseKnowledgeGraphNodeQuestion> pointIdCourseKnowledgeGraphNodeQuestionList =
                        courseKnowledgeGraphNodeQuestionRepository.findByCourseKnowledgeGraphDomainAndKnowledgeNodeIdOrderBySort(
                                courseKnowledgeGraphDomain, pointId);
                AtomicInteger sort = new AtomicInteger(pointIdCourseKnowledgeGraphNodeQuestionList.size());
                courseKnowledgeGraphNodeQuestion = new CourseKnowledgeGraphNodeQuestion();
                courseKnowledgeGraphNodeQuestion.setCourseKnowledgeGraphDomain(courseKnowledgeGraphDomain);
                courseKnowledgeGraphNodeQuestion.setKnowledgeNodeId(pointId);
                courseKnowledgeGraphNodeQuestion.setQuestionId(questionKnowledgePointVo.getQuestionId());
                courseKnowledgeGraphNodeQuestion.setSort(sort.getAndIncrement());
                courseKnowledgeGraphNodeQuestion.setCreatorId(questionKnowledgePointVo.getOperatorId());
                courseKnowledgeGraphNodeQuestion.setCreatorName(questionKnowledgePointVo.getOperatorName());
                courseKnowledgeGraphNodeQuestion.setCreateTime(now);
                resultList.add(courseKnowledgeGraphNodeQuestion);
            }
        });
        courseKnowledgeGraphNodeQuestionRepository.saveAll(resultList);
        return Result.success();
    }
}

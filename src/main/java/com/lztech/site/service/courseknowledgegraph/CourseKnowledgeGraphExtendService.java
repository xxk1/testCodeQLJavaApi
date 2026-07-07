package com.lztech.site.service.courseknowledgegraph;

import cn.hutool.core.bean.BeanUtil;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeQuestion;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphNodeQuestionRepository;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseQuestionToNewQuestionKnowledgeGraphParam;
import com.lztech.site.viewmodel.courseknowledgegraph.NodeQuestionCountVo;
import com.lztech.site.viewmodel.courseknowledgegraph.QuestionMapVo;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseKnowledgeGraphExtendService {

    @Autowired
    private CourseKnowledgeGraphNodeQuestionRepository nodeQuestionRepository;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void batchCopyQuestionKnowledgeGraphNodeToNewQuestion(
            CourseQuestionToNewQuestionKnowledgeGraphParam saveParam) {
        List<QuestionMapVo> questionMapVos = saveParam.getQuestionIdList();
        List<String> sourceQuestionIds = saveParam.getQuestionIdList().stream()
                .map(QuestionMapVo::getSourceQuestionId).collect(Collectors.toList());

        List<CourseKnowledgeGraphNodeQuestion> sourceNodeQuestions =
                nodeQuestionRepository.findByQuestionIdInOrderByCreateTimeAsc(sourceQuestionIds);

        List<String> sourceNodeIds = sourceNodeQuestions.stream().map(CourseKnowledgeGraphNodeQuestion::getKnowledgeNodeId)
                .distinct().collect(Collectors.toList());

        List<NodeQuestionCountVo> sourceNodeQuestionCountVos = getNodesQuestionCount(sourceNodeIds);

        Date now = new Date();
        List<CourseKnowledgeGraphNodeQuestion> saveNodeQuestions = new ArrayList<>();
        sourceNodeQuestionCountVos.forEach(o -> {
            final Integer[] number = {o.getNumber().intValue()};
            List<CourseKnowledgeGraphNodeQuestion> sourceNodeQuestionFilter = sourceNodeQuestions.stream()
                    .filter(nodeQuestion -> nodeQuestion.getKnowledgeNodeId().equals(o.getKnowledgeNodeId()))
                    .collect(Collectors.toList());

            sourceNodeQuestionFilter.forEach(nodeQuestion -> {
                QuestionMapVo questionMapVo = questionMapVos.stream()
                        .filter(obj -> nodeQuestion.getQuestionId().equals(obj.getSourceQuestionId())).findFirst().get();

                CourseKnowledgeGraphNodeQuestion saveNodeQuestion = new CourseKnowledgeGraphNodeQuestion();
                BeanUtil.copyProperties(nodeQuestion, saveNodeQuestion,
                        "id", "sort", "creatorId", "creatorName", "createTime", "questionId");
                saveNodeQuestion.setCreatorId(saveParam.getUserId());
                saveNodeQuestion.setCreatorName(saveParam.getUserName());
                saveNodeQuestion.setCreateTime(now);
                saveNodeQuestion.setQuestionId(questionMapVo.getNewQuestionId());
                saveNodeQuestion.setSort(number[0]);
                number[0]++;
                saveNodeQuestions.add(saveNodeQuestion);
            });
        });

        if (CollectionUtils.isNotEmpty(saveNodeQuestions)) {
            nodeQuestionRepository.saveAll(saveNodeQuestions);
        }
    }

    private List<NodeQuestionCountVo> getNodesQuestionCount(List<String> nodeIds) {
        Map<String, Object> paramMap = new HashMap<>();

        String querySql = "select knowledge_node_id as knowledgeNodeId,count(1) as number from tb_course_knowledge_graph_node_question" +
                " where knowledge_node_id in (:nodeIds)" +
                " group by knowledge_node_id";
        paramMap.put("nodeIds", nodeIds);

        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(NodeQuestionCountVo.class));
        return query.getResultList();
    }

}

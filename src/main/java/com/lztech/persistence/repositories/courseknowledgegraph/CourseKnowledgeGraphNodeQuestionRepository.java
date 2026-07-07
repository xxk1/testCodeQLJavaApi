package com.lztech.persistence.repositories.courseknowledgegraph;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseKnowledgeGraphNodeQuestionRepository extends JpaRepository<CourseKnowledgeGraphNodeQuestion, String> {

    List<CourseKnowledgeGraphNodeQuestion> findByCourseKnowledgeGraphDomainAndKnowledgeNodeIdOrderBySort(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, String knowledgeNodeId);

    CourseKnowledgeGraphNodeQuestion findFirstByKnowledgeNodeIdAndQuestionId(String knowledgeNodeId, String questionId);

    @Query(nativeQuery = true, value = "update tb_course_knowledge_graph_node_question " +
            " set sort = (sort - 1) where  sort>:sort")
    @Modifying
    @Transactional
    void updateByQuestionSort(@Param("sort") Integer sort);

    List<CourseKnowledgeGraphNodeQuestion> findByQuestionIdOrderByCreateTimeAsc(String questionId);

    @Transactional
    @Modifying
    void deleteByKnowledgeNodeIdIn(List<String> knowledgeNodeIdList);

    @Query(nativeQuery = true, value = "update tb_course_knowledge_graph_node_question " +
            " set sort = (sort - 1) where knowledge_node_id=:knowledgeNodeId and  sort>:sort")
    @Modifying
    @Transactional
    void updateByKnowledgeGraphNodeIdAndQuestionSort(
            @Param("knowledgeNodeId") String knowledgeNodeId, @Param("sort") Integer sort);

    List<CourseKnowledgeGraphNodeQuestion> findByQuestionIdInOrderByCreateTimeAsc(List<String> questionIdList);

    @Query(nativeQuery = true, value = " SELECT n.*  FROM tb_course_knowledge_graph_node_question n " +
            " inner join  tb_course_knowledge_graph_domain d ON n.course_knowledge_graph_domain_id = d.id " +
            " WHERE d.status = 1 " +
            "  AND d.version_label = ( " +
            "      SELECT MAX(version_label) " +
            "      FROM tb_course_knowledge_graph_domain " +
            "      WHERE id = d.id AND status = 1 " +
            "  )  AND n.question_id =:questionId ")
    List<CourseKnowledgeGraphNodeQuestion> getEffectiveCourseKnowledgeGraphNodeQuestionList(@Param("questionId") String questionId);

    List<CourseKnowledgeGraphNodeQuestion> findByCourseKnowledgeGraphDomain(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain);


    List<CourseKnowledgeGraphNodeQuestion> findByCourseKnowledgeGraphDomainAndQuestionId(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, String questionId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from tb_course_knowledge_graph_node_question " +
            " where question_id =:questionId and knowledge_node_id not in (:pointIdList) ")
    void deleteNotConcatPoint(@Param("questionId") String questionId,
                              @Param("pointIdList") List<String> pointIdList);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from tb_course_knowledge_graph_node_question where question_id =:questionId")
    void deleteByQuestionId(@Param("questionId") String questionId);
}

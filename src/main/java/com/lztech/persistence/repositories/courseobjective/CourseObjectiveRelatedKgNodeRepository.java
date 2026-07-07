package com.lztech.persistence.repositories.courseobjective;

import com.lztech.domain.model.course.CourseObjectiveRelatedKgNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface CourseObjectiveRelatedKgNodeRepository extends JpaRepository<CourseObjectiveRelatedKgNode, String> {

    @Query("SELECT c FROM CourseObjectiveRelatedKgNode c WHERE c.courseObjectiveId = ?1 AND c.courseKnowledgeGraphId = ?2")
    List<CourseObjectiveRelatedKgNode> findByCourseObjectivesIdAndKnowledgeGraphId(String courseObjectivesId,
                                                                                   String courseKnowledgeGraphId);

    long countByCourseObjectiveIdAndCourseKnowledgeGraphId(String courseObjectivesId,
                                                           String courseKnowledgeGraphId);

    List<CourseObjectiveRelatedKgNode> findByCourseKnowledgeGraphId(String courseKnowledgeGraphId);

    @Modifying
    @Query("DELETE FROM CourseObjectiveRelatedKgNode c WHERE c.courseObjectiveId = ?1")
    void deleteByCourseObjectiveId(String courseObjectiveId);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_course_objective_kg_node" +
            " WHERE course_knowledge_graph_id = ?2 and related_knowledge_point_id in ( ?1) ")
    List<CourseObjectiveRelatedKgNode> findByNodeIdListAndCourseKnowledgeGraphId(List<String> nodeIdList,
                                                                                 String courseKnowledgeGraphId);

    @Modifying
    void deleteByRelatedKnowledgePointIdInAndCourseKnowledgeGraphId(List<String> nodeIdList,
                                                                    String courseKnowledgeGraphDomainId);

    // 一次统计多个课程目标的知识点数量
    @Query("SELECT c.courseObjectiveId AS courseObjectiveId, COUNT(c.id) AS cnt " +
            "FROM CourseObjectiveRelatedKgNode c " +
            "WHERE c.courseObjectiveId IN :objectiveIds " +
            "GROUP BY c.courseObjectiveId")
    List<Map<String, Object>> countGroupByObjectiveIds(@Param("objectiveIds") Set<String> objectiveIds);
}

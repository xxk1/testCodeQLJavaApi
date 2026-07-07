package com.lztech.persistence.repositories.courseknowledgegraph;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeResource;
import com.lztech.domain.model.knowledgegraph.enums.GraphNodeResourceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseKnowledgeGraphNodeResourceRepository extends JpaRepository<CourseKnowledgeGraphNodeResource, String> {

    List<CourseKnowledgeGraphNodeResource> findByCourseKnowledgeGraphDomainAndKnowledgeNodeIdAndGraphNodeResourceStatus(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, String knowledgeNodeId, GraphNodeResourceStatus graphNodeResourceStatus
            );

    int countByCourseKnowledgeGraphDomainAndKnowledgeNodeIdAndGraphNodeResourceStatus(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,String knowledgeNodeId,GraphNodeResourceStatus graphNodeResourceStatus);

    @Query(nativeQuery = true, value = "update tb_course_knowledge_graph_node_resource " +
            " set sort = (sort - 1) where  graph_node_resource_status = 0 and sort>:sort")
    @Modifying
    @Transactional
    void  updateByFileSort(@Param("sort") Integer sort);

    @Query(nativeQuery = true, value = "UPDATE tb_course_knowledge_graph_node_resource SET sort = (sort - 1)  " +
            "WHERE course_knowledge_graph_domain_id = :courseKnowledgeGraphDomainId " +
            " AND knowledge_node_id=:knowledgeNodeId and graph_node_resource_status = 0   And sort >:from and sort<:to")
    @Modifying
    @Transactional
    void forwardByDomainIdAndNodeIdWithIndexBetween(
            @Param("courseKnowledgeGraphDomainId") String courseKnowledgeGraphDomainId,
            @Param("knowledgeNodeId") String knowledgeNodeId,
            @Param("from") Integer from,
            @Param("to") Integer to
    );

    @Query(nativeQuery = true, value = "UPDATE tb_course_knowledge_graph_node_resource SET sort = (sort + 1)  " +
            " WHERE course_knowledge_graph_domain_id = :courseKnowledgeGraphDomainId " +
            " AND knowledge_node_id=:knowledgeNodeId and graph_node_resource_status = 0  And  sort >:from and sort<:to")
    @Modifying
    @Transactional
    void backwardByDomainIdAndNodeIdWithIndexBetween(
            @Param("courseKnowledgeGraphDomainId") String courseKnowledgeGraphDomainId,
            @Param("knowledgeNodeId") String knowledgeNodeId,
            @Param("from") Integer from,
            @Param("to") Integer to
    );

    CourseKnowledgeGraphNodeResource findByCourseKnowledgeGraphDomainAndKnowledgeNodeIdAndGraphNodeResourceStatusAndSort(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain,String knowledgeNodeId,
            GraphNodeResourceStatus graphNodeResourceStatus, int sort);
    @Query(nativeQuery = true, value = "UPDATE tb_course_knowledge_graph_node_resource SET  " +
            " graph_node_resource_status=1 ,modifier_id=:accessUserId,modifier_name=:accessUserName," +
            " modify_time=now() where graph_node_resource_status=0 and knowledge_node_id in (:knowledgeGraphNodeIdList)")
    @Modifying
    @Transactional
    void deleteByKnowledgeGraphNodeIdList(
            @Param("knowledgeGraphNodeIdList") List<String> knowledgeGraphNodeIdList,
            @Param("accessUserId") String accessUserId,
            @Param("accessUserName") String accessUserName);
    @Query(nativeQuery = true, value = "update tb_course_knowledge_graph_node_resource " +
            " set sort = (sort - 1) where knowledge_node_id=:knowledgeGraphNodeId and" +
            "  graph_node_resource_status = 0 and sort>:sort")
    @Modifying
    @Transactional
    void updateByKnowledgeGraphNodeIdAndFileSort(
            @Param("knowledgeGraphNodeId") String knowledgeGraphNodeId,
            @Param("sort") Integer sort);

    List<CourseKnowledgeGraphNodeResource> findByCourseKnowledgeGraphDomain(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain);
}

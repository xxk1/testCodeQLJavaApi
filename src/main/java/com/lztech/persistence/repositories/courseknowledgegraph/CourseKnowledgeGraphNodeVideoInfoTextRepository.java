package com.lztech.persistence.repositories.courseknowledgegraph;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeVideoInfoText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseKnowledgeGraphNodeVideoInfoTextRepository extends JpaRepository<CourseKnowledgeGraphNodeVideoInfoText, String>,
        JpaSpecificationExecutor<CourseKnowledgeGraphNodeVideoInfoText> {
    List<CourseKnowledgeGraphNodeVideoInfoText> findByCourseKnowledgeGraphDomainAndKnowledgeNodeIdOrderByVideoDateDescSegmentDesc(
            CourseKnowledgeGraphDomain courseKnowledgeGraphDomain, String knowledgeNodeId);

    @Query(nativeQuery = true, value = " select * from tb_course_knowledge_graph_node_video_info_text where" +
            " course_knowledge_graph_domain_id= :courseKnowledgeGraphDomainId and knowledge_node_id= :knowledgeNodeId  and school_year= :schoolYear" +
            " and term= :term and group_id= :groupId  ")
    List<CourseKnowledgeGraphNodeVideoInfoText>  findByData(
            @Param("courseKnowledgeGraphDomainId") String courseKnowledgeGraphDomainId,
            @Param("knowledgeNodeId") String knowledgeNodeId,
            @Param("schoolYear") String schoolYear,
            @Param("term") Integer term,
            @Param("groupId") String groupId
    );

    @Query(nativeQuery = true, value = " select * from tb_course_knowledge_graph_node_video_info_text where" +
            " course_knowledge_graph_domain_id= :courseKnowledgeGraphDomainId and knowledge_node_id= :knowledgeNodeId  and school_year= :schoolYear" +
            " and term= :term and group_id= :groupId and (similarity_score >=:similarityScore or similarity_score is null ) ")
    List<CourseKnowledgeGraphNodeVideoInfoText>  findByData(
            @Param("courseKnowledgeGraphDomainId") String courseKnowledgeGraphDomainId,
            @Param("knowledgeNodeId") String knowledgeNodeId,
            @Param("schoolYear") String schoolYear,
            @Param("term") Integer term,
            @Param("groupId") String groupId,
            @Param("similarityScore")  Double similarityScore
    );

    List<CourseKnowledgeGraphNodeVideoInfoText> findByKnowledgeNodeIdAndVideoInfoId(String knowledgeNodeId, String videoInfoId);

    @Query(nativeQuery = true, value = " select * from tb_course_knowledge_graph_node_video_info_text where " +
            " knowledge_node_id= :knowledgeNodeId and video_info_id= :videoInfoId " +
            " and (similarity_score >=:similarityScore or similarity_score is null) ")
    List<CourseKnowledgeGraphNodeVideoInfoText> findByKnowledgeNodeIdAndVideoInfoIdAndSimilarityScore(
            @Param("knowledgeNodeId")  String knowledgeNodeId,
            @Param("videoInfoId")  String videoInfoId,
            @Param("similarityScore")  Double similarityScore
    );


    List<CourseKnowledgeGraphNodeVideoInfoText> findByCourseKnowledgeGraphDomain(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain);

    @Query(nativeQuery = true, value = " select * from tb_course_knowledge_graph_node_video_info_text where" +
            " course_knowledge_graph_domain_id= :courseKnowledgeGraphDomainId and knowledge_node_id= :knowledgeNodeId ")
    List<CourseKnowledgeGraphNodeVideoInfoText> findByCourseKnowledgeGraphDomainIdAndNodeId(@Param("courseKnowledgeGraphDomainId")
                                                                                            String courseKnowledgeGraphDomainId,
                                                                                            @Param("knowledgeNodeId") String knowledgeNodeId);

    @Query(nativeQuery = true, value = " select * from tb_course_knowledge_graph_node_video_info_text where " +
            " course_knowledge_graph_domain_id= :courseKnowledgeGraphDomainId and knowledge_node_id= :knowledgeNodeId " +
            " and similarity_score >=:similarityScore ")
    List<CourseKnowledgeGraphNodeVideoInfoText> findByCourseKnowledgeGraphDomainIdAndNodeIdAndSimilarityScore(
            @Param("courseKnowledgeGraphDomainId") String courseKnowledgeGraphDomainId,
            @Param("knowledgeNodeId") String knowledgeNodeId,
            @Param("similarityScore") Double similarityScore
    );
}

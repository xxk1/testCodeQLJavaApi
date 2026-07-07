package com.lztech.persistence.repositories.kggentask;

import com.lztech.domain.model.kggentask.KgGenNodeVideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface KgGenNodeVideoInfoRepository extends JpaRepository<KgGenNodeVideoInfo, String>, JpaSpecificationExecutor<KgGenNodeVideoInfo> {
    @Transactional
    @Modifying
    void deleteByMasterTaskId(String id);

    List<KgGenNodeVideoInfo> findByKnowledgeNodeIdAndVideoInfoId(String courseKnowledgeGraphNodeId, String videoInfoId);



    @Query(nativeQuery = true, value = " select * from tb_kg_gen_node_video_info where " +
            " knowledge_node_id= :knowledgeNodeId and video_info_id= :videoInfoId " +
            " and (similarity_score >=:similarityScore or similarity_score is null) ")
    List<KgGenNodeVideoInfo> findByKnowledgeNodeIdAndVideoInfoIdAndSimilarityScore(
            @Param("knowledgeNodeId")  String knowledgeNodeId,
            @Param("videoInfoId")  String videoInfoId,
            @Param("similarityScore")  Double similarityScore);

    List<KgGenNodeVideoInfo> findByMasterTaskId(String masterTaskId);

    @Query(nativeQuery = true, value = "select count(1) from tb_kg_gen_node_video_info " +
            " where master_task_id=:masterTaskId and (similarity_score>=:similarityScore or similarity_score is null ) ")
    int countByMasterTaskIdAndSimilarityScore(
            @Param("masterTaskId")  String masterTaskId,
            @Param("similarityScore")  Double similarityScore
    );

    List<KgGenNodeVideoInfo> findBySubTaskId(String subTaskId);
}

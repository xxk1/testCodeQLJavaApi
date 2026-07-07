package com.lztech.persistence.repositories.kggentask;

import com.lztech.domain.model.kggentask.KgGenNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KgGenNodeRepository extends JpaRepository<KgGenNode, String> {

    List<KgGenNode> findByMasterTaskId(String masterTaskId);

    List<KgGenNode> findBySubTaskId(String subTaskId);

    @Query("select max(nodeOrder) from KgGenNode where parentNodeId = ?1")
    Integer findMaxOrderByParentNodeId(String parentId);


    @Query("select max(nodeOrder) from KgGenNode where parentNodeId is null and subTaskId=?1")
    Integer findMaxOrderByParentNodeIdAndTaskId(String subTaskId);

    List<KgGenNode> findByParentNodeId(String id);


    @Query(nativeQuery = true, value = "SELECT * FROM tb_kg_gen_node k WHERE k.id =:kgGenNodeId limit 1 ")
    KgGenNode getKgGenNodeById(@Param("kgGenNodeId") String kgGenNodeId);
}

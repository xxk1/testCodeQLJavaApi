package com.lztech.persistence.repositories.kggentask;

import com.lztech.domain.model.kggentask.KgGenNodeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface KgGenNodeQuestionRepository extends JpaRepository<KgGenNodeQuestion, String> {
    @Transactional
    @Modifying
    void deleteByMasterTaskId(String id);

    List<KgGenNodeQuestion> findByNodeId(String nodeId);

    List<KgGenNodeQuestion> findByMasterTaskId(String masterTaskId);

    List<KgGenNodeQuestion> findBySubTaskId(String subTaskId);
}

package com.lztech.persistence.repositories.kggentask;

import com.lztech.domain.model.kggentask.KgGenNodeResourceRelatedStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface KgGenNodeResourceRelatedStatisticsRepository extends JpaRepository<KgGenNodeResourceRelatedStatistics,String> {
    KgGenNodeResourceRelatedStatistics findByMasterTaskIdAndSubTaskId(String masterTaskId, String subTaskId);

    @Transactional
    @Modifying
    void deleteByMasterTaskId(String id);

    List<KgGenNodeResourceRelatedStatistics> findByMasterTaskId(String masterTaskId);


    List<KgGenNodeResourceRelatedStatistics> findBySubTaskId(String subTaskId);
}

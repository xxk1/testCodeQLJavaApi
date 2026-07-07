package com.lztech.persistence.repositories.kggentask;

import com.lztech.domain.model.kggentask.KgGenTaskSub;
import com.lztech.domain.model.kggentask.enums.KgGenTaskStepCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KgGenTaskSubRepository extends JpaRepository<KgGenTaskSub, String> {
    List<KgGenTaskSub> findByMasterId(String id);

    @Query("SELECT k FROM KgGenTaskSub k WHERE k.masterId = :masterId AND k.createTime = " +
            "(SELECT MAX(k2.createTime) FROM KgGenTaskSub k2 WHERE " +
            " k2.masterId = :masterId AND k2.stepCode = k.stepCode )")
    List<KgGenTaskSub> findLatestByMasterId(@Param("masterId") String masterId);


    @Query("SELECT k FROM KgGenTaskSub k WHERE k.masterId = :masterId AND k.createTime = " +
            "(SELECT MAX(k2.createTime) FROM KgGenTaskSub k2 WHERE " +
            " k2.masterId = :masterId AND k2.stepCode = :kgGenTaskStepCode ) and k.stepCode=:kgGenTaskStepCode ")
    List<KgGenTaskSub> findLatestByMasterIdAndStepCode(@Param("masterId") String masterId,
                                                 @Param("kgGenTaskStepCode") KgGenTaskStepCode kgGenTaskStepCode);
}

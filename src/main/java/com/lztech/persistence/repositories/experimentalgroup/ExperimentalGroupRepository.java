package com.lztech.persistence.repositories.experimentalgroup;

import com.lztech.domain.model.experimentalgroup.ExperimentalGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ExperimentalGroupRepository extends JpaRepository<ExperimentalGroup, String> {

    List<ExperimentalGroup> findByGroupIdAndUseStateIsTrue(String groupId);

    int countByGroupIdAndUseStateIsTrue(String groupId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_experimental_group " +
            " set use_state=0,modifier_id=?2,modifier_name=?3,modify_time=?4 " +
            " where id=?1")
    void updateExperimentalGroupState(String id, String modifierId, String modifierName, Date date);
}

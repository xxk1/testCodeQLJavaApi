package com.lztech.persistence.repositories.experimentalgroup;

import com.lztech.domain.model.experimentalgroup.ExperimentalGroup;
import com.lztech.domain.model.experimentalgroup.ExperimentalGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentalGroupMemberRepository extends JpaRepository<ExperimentalGroupMember, String> {

    void deleteByExperimentalGroup(ExperimentalGroup experimentalGroup);
}

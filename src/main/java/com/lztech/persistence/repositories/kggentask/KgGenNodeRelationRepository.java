package com.lztech.persistence.repositories.kggentask;

import com.lztech.domain.model.kggentask.KgGenNodeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KgGenNodeRelationRepository extends JpaRepository<KgGenNodeRelation, String> {

    List<KgGenNodeRelation> findByNewMasterTaskId(String newMasterTaskId);
}

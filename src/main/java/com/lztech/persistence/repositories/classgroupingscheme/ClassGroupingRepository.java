package com.lztech.persistence.repositories.classgroupingscheme;

import com.lztech.domain.model.classgroupingscheme.ClassGrouping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ClassGroupingRepository extends JpaRepository<ClassGrouping, String> , JpaSpecificationExecutor<ClassGrouping> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = " delete from tb_class_grouping where class_grouping_scheme_id = ?1 ")
    void deleteClassGroupingByClassGroupingSchemeId(String id);
}

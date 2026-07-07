package com.lztech.persistence.repositories.classgroupingscheme;

import com.lztech.domain.model.classgroupingscheme.ClassGroupingMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ClassGroupingMemberRepository extends JpaRepository<ClassGroupingMember, String> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = " delete from tb_class_grouping_member where class_grouping_id in " +
            " (select id from tb_class_grouping where class_grouping_scheme_id= ?1 )")
    void deleteClassGroupingByClassGroupingSchemeId(String id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = " delete from tb_class_grouping_member where student_id=?1 and class_grouping_id in (" +
            " select id from tb_class_grouping where class_grouping_scheme_id in (select id from tb_class_grouping_scheme where group_id=?2))")
    void deleteByStudentIdAndGroupId(String studentId, String groupId);
}

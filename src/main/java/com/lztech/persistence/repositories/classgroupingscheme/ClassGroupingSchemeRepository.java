package com.lztech.persistence.repositories.classgroupingscheme;

import com.lztech.domain.model.classgroupingscheme.ClassGroupingScheme;
import com.lztech.domain.model.classgroupingscheme.enums.SchemeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ClassGroupingSchemeRepository extends JpaRepository<ClassGroupingScheme, String> {

    List<ClassGroupingScheme> findByGroupId(String groupId);

    List<ClassGroupingScheme> findByGroupIdAndSchemeType(String groupId, SchemeType schemeType);


    int countByGroupIdAndSchemeName(String groupId, String schemeName);

    int countByGroupId(String groupId);

    int countByGroupIdAndSchemeType(String groupId, SchemeType schemeType);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_class_grouping_scheme " +
            " set grouped_head_count = grouped_head_count-1, modifier_id= :operatorId,modifier_name=:operatorName,modify_time=:date " +
            " WHERE " +
            " id IN ( SELECT class_grouping_scheme_id FROM tb_class_grouping WHERE id in  ( SELECT class_grouping_id FROM tb_class_grouping_member " +
            " WHERE student_id = :studentId ) ) " +
            " and grouped_head_count>0 and group_id= :groupId ")
    void updateClassGroupingMemberNum(@Param("studentId") String studentId,
                                      @Param("operatorId") String operatorId,
                                      @Param("operatorName") String operatorName,
                                      @Param("date") Date date,
                                      @Param("groupId") String groupId);

    @Transactional
    @Modifying
    void deleteByGroupIdAndSchemeType(String groupId, SchemeType schemeType);

    List<ClassGroupingScheme> findByGroupIdIn(List<String> groupIds);
}

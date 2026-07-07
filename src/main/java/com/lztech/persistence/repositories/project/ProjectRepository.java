package com.lztech.persistence.repositories.project;

import com.lztech.domain.model.project.Project;
import com.lztech.domain.model.project.enums.WhetherAssociateCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String>, JpaSpecificationExecutor {

    List<Project> findAllByIdIn(List<String> ids);

    List<Project> findByProjectClassificationId(String classificationId);

    List<Project> findByWhetherAssociateCourses(WhetherAssociateCourses whetherAssociateCourses);

    Project findByProjectCode(String projectCode);

    @Query(nativeQuery = true, value = "select * from tb_project where project_code like  CONCAT(?1,'%')" +
            " and LENGTH(project_code)= ?2 ")
    List<Project> findByProjectCodeLike(String projectCodePrefix,
                                        int projectCodeLength);

    @Query(nativeQuery = true, value = "select * from tb_project where project_code=:projectCode limit 1")
    Project findByProjectCodeLimitOne(@Param("projectCode") String projectCode);

    int countByProjectCode(String projectCode);

    @Query(nativeQuery = true, value = "select * from tb_project p " +
            " inner join tb_project_attribute tpa on p.id = tpa.project_id " +
            " where tpa.data_id in (?1) and tpa.attribute_kind=2")
    List<Project> findByCourseIdIn(List<String> courseIds);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_project " +
            " set standard_duration=?2,modifier_id=?3,modifier_name=?4 " +
            " where id =?1")
    void updateProjectStandardDuration(String projectId, double resultStandardDuration, String modifierId, String modifierName);

    @Query(nativeQuery = true, value = "select count(1)  from tb_project a " +
            "where a.project_code >=:minProjectCode and a.project_code<=:maxProjectCode and LENGTH(a.project_code) = 12 ")
    int countByProjectCodeData(
            @Param("minProjectCode") String minProjectCode,
            @Param("maxProjectCode") String maxProjectCode
    );
}

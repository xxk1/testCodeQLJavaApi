package com.lztech.persistence.repositories.experimentschedulelcoursetabledetail;

import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetail;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetailTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExperimentScheduleCourseTableDetailTeacherRepository extends JpaRepository<ExperimentScheduleCourseTableDetailTeacher, String>,
        JpaSpecificationExecutor<ExperimentScheduleCourseTableDetailTeacher> {
    List<ExperimentScheduleCourseTableDetailTeacher> findAllByExperimentScheduleCourseTableDetailIdIn(List<String> ids);
    @Transactional
    @Modifying
    void deleteByExperimentScheduleCourseTableDetail(ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail);

}

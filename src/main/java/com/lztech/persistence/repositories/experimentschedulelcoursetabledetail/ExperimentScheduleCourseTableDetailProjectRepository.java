package com.lztech.persistence.repositories.experimentschedulelcoursetabledetail;

import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetailProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentScheduleCourseTableDetailProjectRepository extends JpaRepository<ExperimentScheduleCourseTableDetailProject, String>,
        JpaSpecificationExecutor<ExperimentScheduleCourseTableDetailProject> {
    List<ExperimentScheduleCourseTableDetailProject> findAllByExperimentScheduleCourseTableDetailIdIn(List<String> ids);
}

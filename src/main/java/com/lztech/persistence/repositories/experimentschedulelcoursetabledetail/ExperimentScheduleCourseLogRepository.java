package com.lztech.persistence.repositories.experimentschedulelcoursetabledetail;

import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentScheduleCourseLogRepository extends JpaRepository<ExperimentScheduleCourseLog, String>,
        JpaSpecificationExecutor<ExperimentScheduleCourseLog> {

    List<ExperimentScheduleCourseLog> findByExperimentScheduleId(String experimentScheduleId);
}

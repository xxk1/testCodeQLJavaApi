package com.lztech.persistence.repositories.experimentschedulelcoursetabledetail;

import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentScheduleCourseTableDetailRepository extends JpaRepository<ExperimentScheduleCourseTableDetail, String>,
        JpaSpecificationExecutor<ExperimentScheduleCourseTableDetail> {

}

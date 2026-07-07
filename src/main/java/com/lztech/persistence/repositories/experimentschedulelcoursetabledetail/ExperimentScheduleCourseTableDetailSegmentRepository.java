package com.lztech.persistence.repositories.experimentschedulelcoursetabledetail;

import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetail;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetailSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ExperimentScheduleCourseTableDetailSegmentRepository extends JpaRepository<ExperimentScheduleCourseTableDetailSegment, String>,
        JpaSpecificationExecutor<ExperimentScheduleCourseTableDetailSegment> {

    @Transactional
    @Modifying
    void deleteByExperimentScheduleCourseTableDetail(ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail);
}

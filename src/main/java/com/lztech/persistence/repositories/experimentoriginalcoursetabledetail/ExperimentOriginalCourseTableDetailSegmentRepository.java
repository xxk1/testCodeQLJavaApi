package com.lztech.persistence.repositories.experimentoriginalcoursetabledetail;

import com.lztech.domain.model.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetailSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentOriginalCourseTableDetailSegmentRepository extends JpaRepository<ExperimentOriginalCourseTableDetailSegment, String>,
        JpaSpecificationExecutor<ExperimentOriginalCourseTableDetailSegment> {
}

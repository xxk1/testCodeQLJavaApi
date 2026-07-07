package com.lztech.persistence.repositories.experimentoriginalcoursetabledetail;

import com.lztech.domain.model.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentOriginalCourseTableDetailRepository extends JpaRepository<ExperimentOriginalCourseTableDetail, String>,
        JpaSpecificationExecutor<ExperimentOriginalCourseTableDetail> {
}

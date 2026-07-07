package com.lztech.persistence.repositories.experimentoriginalcoursetabledetail;

import com.lztech.domain.model.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetailProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentOriginalCourseTableDetailProjectRepository extends JpaRepository<ExperimentOriginalCourseTableDetailProject, String>,
        JpaSpecificationExecutor<ExperimentOriginalCourseTableDetailProject> {
}

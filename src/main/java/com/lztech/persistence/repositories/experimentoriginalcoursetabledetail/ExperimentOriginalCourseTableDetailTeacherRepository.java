package com.lztech.persistence.repositories.experimentoriginalcoursetabledetail;

import com.lztech.domain.model.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetailTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentOriginalCourseTableDetailTeacherRepository extends JpaRepository<ExperimentOriginalCourseTableDetailTeacher, String>,
        JpaSpecificationExecutor<ExperimentOriginalCourseTableDetailTeacher> {
}

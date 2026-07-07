package com.lztech.persistence.repositories.projectsuite;

import com.lztech.domain.model.projectsuite.ProjectSuite;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteStatus;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectSuiteRepository extends JpaRepository<ProjectSuite, String> {
    List<ProjectSuite> findByProjectSuiteNameAndSuiteStatusAndSuiteType(String projectSuiteName,
                                                                        ProjectSuiteStatus projectSuiteStatus,
                                                                        ProjectSuiteType projectSuiteType);

    ProjectSuite findByIdAndSuiteStatus(String projectSuiteId, ProjectSuiteStatus projectSuiteStatus);

    Page<ProjectSuite> findAll(Specification<ProjectSuite> specification, Pageable pageable);

    List<ProjectSuite> findByIdIsIn(List<String> ids);
}

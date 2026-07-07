package com.lztech.persistence.repositories.projectcardprojectsuite;

import com.lztech.domain.model.projectcardprojectsuite.ProjectCardProjectSuite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectCardProjectSuiteRepository extends JpaRepository<ProjectCardProjectSuite, String> {
}

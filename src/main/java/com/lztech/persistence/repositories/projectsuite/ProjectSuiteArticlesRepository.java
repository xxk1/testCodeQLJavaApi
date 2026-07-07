package com.lztech.persistence.repositories.projectsuite;

import com.lztech.domain.model.projectsuite.ProjectSuiteArticles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSuiteArticlesRepository extends JpaRepository<ProjectSuiteArticles, String> {
}

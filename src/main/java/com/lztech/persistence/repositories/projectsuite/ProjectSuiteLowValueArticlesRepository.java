package com.lztech.persistence.repositories.projectsuite;

import com.lztech.domain.model.projectsuite.ProjectSuiteLowValueArticles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSuiteLowValueArticlesRepository extends JpaRepository<ProjectSuiteLowValueArticles, String> {
}

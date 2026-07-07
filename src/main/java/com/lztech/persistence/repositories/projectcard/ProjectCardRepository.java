package com.lztech.persistence.repositories.projectcard;

import com.lztech.domain.model.projectcard.ProjectCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectCardRepository extends JpaRepository<ProjectCard, String> {

    List<ProjectCard> findByProjectIdInAndCourseId(List<String> projectIdList, String courseId);

    Page<ProjectCard> findAll(Specification<ProjectCard> specification, Pageable pageable);
    List<ProjectCard> findByProjectId(String projectId);

    List<ProjectCard> findByProjectIdIn(List<String> projectIdList);
}

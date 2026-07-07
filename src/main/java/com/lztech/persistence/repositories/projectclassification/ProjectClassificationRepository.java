package com.lztech.persistence.repositories.projectclassification;

import com.lztech.domain.model.projectclassification.ProjectClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectClassificationRepository extends JpaRepository<ProjectClassification, String>{
    ProjectClassification findByClassificationName(String classificationName);

    List<ProjectClassification> findByClassificationNameIn(List<String> classificationNameList);
}

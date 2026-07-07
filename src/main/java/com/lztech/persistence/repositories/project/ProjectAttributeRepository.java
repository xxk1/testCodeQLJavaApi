package com.lztech.persistence.repositories.project;

import com.lztech.domain.model.projectattribute.ProjectAttribute;
import com.lztech.domain.model.projectattribute.enums.ProjectAttributeKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface ProjectAttributeRepository extends JpaRepository<ProjectAttribute, String>, JpaSpecificationExecutor {
    List<ProjectAttribute> findByDataIdAndAttributeKind(String courseId, ProjectAttributeKind attributeKind);
}

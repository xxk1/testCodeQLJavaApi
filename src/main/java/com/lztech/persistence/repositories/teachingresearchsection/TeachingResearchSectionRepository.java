package com.lztech.persistence.repositories.teachingresearchsection;

import com.lztech.domain.model.teachingresearchsection.TeachingResearchSection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachingResearchSectionRepository extends JpaRepository<TeachingResearchSection, String>, JpaSpecificationExecutor {

    TeachingResearchSection findByTeachingResearchName(String teachingResearchName);

    TeachingResearchSection findByIdAndTeachingResearchName(String id, String teachingResearchName);

    Page<TeachingResearchSection> findByOrderByModifyTimeDesc(Pageable pageable);
}

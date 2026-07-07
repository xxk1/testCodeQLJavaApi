package com.lztech.persistence.repositories.teachingresearchsection;

import com.lztech.domain.model.teachingresearchcourse.TeachingResearchCourse;
import com.lztech.domain.model.teachingresearchsection.TeachingResearchSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeachingResearchCourseRepository extends JpaRepository<TeachingResearchCourse, String>, JpaSpecificationExecutor {
    @Transactional
    void deleteAllByTeachingResearchSection(TeachingResearchSection teachingResearchSection);
}

package com.lztech.persistence.repositories.coursestructuremapping;

import com.lztech.domain.model.course.CourseStructureMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStructureMappingRepository extends JpaRepository<CourseStructureMapping, String> {

    List<CourseStructureMapping> findByCourseIdAndCourseVersionId(String courseId, String versionId);

    void deleteByCourseIdAndCourseVersionId(String courseId, String versionId);
}

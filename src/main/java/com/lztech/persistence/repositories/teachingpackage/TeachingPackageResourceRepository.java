package com.lztech.persistence.repositories.teachingpackage;

import com.lztech.domain.model.courseteachingpackage.CourseTeachingPackageResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingPackageResourceRepository extends JpaRepository<CourseTeachingPackageResource, String>,
        JpaSpecificationExecutor<CourseTeachingPackageResource>{

    List<CourseTeachingPackageResource> findByCourseIdAndCourseTeachingPackageId(String courseId,
                                                                                          String courseTeachingPackageId);
}

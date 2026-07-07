package com.lztech.persistence.repositories.teachingpackage;

import com.lztech.domain.model.courseteachingpackage.CourseTeachingPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingPackageRepository extends JpaRepository<CourseTeachingPackage, String>, JpaSpecificationExecutor<CourseTeachingPackage>{
    List<CourseTeachingPackage> findByCourseNameLikeOrderByCreateTime(String courseName);

    List<CourseTeachingPackage> findByCourseIdAndCreatorId(String courseId,String userId);

    List<CourseTeachingPackage> findByCreatorId(String userId);

    List<CourseTeachingPackage> findByCourseId(String courseId);
    List<CourseTeachingPackage> findByCourseIdIn(List<String> courseIds);
}

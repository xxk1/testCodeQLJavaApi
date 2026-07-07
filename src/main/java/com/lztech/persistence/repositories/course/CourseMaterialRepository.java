package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseMaterial;
import com.lztech.domain.model.course.CourseVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, String>, JpaSpecificationExecutor {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_course_material cm set course_version_id=?2 " +
            " where course_id =?1 and course_version_id is null")
    void updateCourseVersion(String courseId, String courseVersionId);

    @Query(nativeQuery = true, value = "select * from tb_course_material " +
            " where course_id = ?1 and course_version_id=?2 limit 1")
    CourseMaterial findByCourseIdAndVersionId(String courseId, String versionId);
    List<CourseMaterial> findByCourseInAndCourseVersionIn(List<Course> courses, List<CourseVersion> courseVersions);
    List<CourseMaterial> findByCourseIn(List<Course> courses);
}

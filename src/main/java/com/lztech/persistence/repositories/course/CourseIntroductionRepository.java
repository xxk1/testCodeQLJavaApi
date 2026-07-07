package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseIntroduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseIntroductionRepository extends JpaRepository<CourseIntroduction, String> {

    void deleteByCourse(Course course);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_course_introduction set course_version_id=?2 " +
            " where course_id =?1 and course_version_id is null")
    void updateCourseVersion(String courseId, String courseVersionId);

    @Query(nativeQuery = true, value = "select * from tb_course_introduction " +
            " where course_id = ?1 and course_version_id=?2 ")
    List<CourseIntroduction> findByCourseIdAndCourseVersionId(String courseId, String courseVersionId);
}

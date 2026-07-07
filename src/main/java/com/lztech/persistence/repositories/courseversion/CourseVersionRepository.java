package com.lztech.persistence.repositories.courseversion;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseVersionRepository extends JpaRepository<CourseVersion, String> {

    @Query(nativeQuery = true, value = "select * from tb_course_version " +
            " where course_id =?1 and course_Version_Status=?2")
    List<CourseVersion> findByCourseIdAndVersionStatus(String id, Integer versionStatus);

    @Query(nativeQuery = true, value = "select * from tb_course_version " +
            " where course_id =?1 and course_Version_Status=?2")
    List<CourseVersion> findByCourseIdAndVersionStatusIsArchived(String id,Integer versionStatus);
    CourseVersion findByIdAndCourseVersionStatus(String id, CourseVersionStatus courseVersionStatus);

    List<CourseVersion> findByCourseVersionStatus(CourseVersionStatus courseVersionStatus);

    List<CourseVersion> findByCourseId(String courseId);

    List<CourseVersion> findByCourseIdAndCourseVersionStatusIsNot(String courseId,CourseVersionStatus courseVersionStatus);

    List<CourseVersion> findByCourseInAndCourseVersionStatus(List<Course> courses,CourseVersionStatus courseVersionStatus);

    CourseVersion findFirstByCourseInAndCourseVersionStatus(List<Course> courses, CourseVersionStatus courseVersionStatus);



    @Query(nativeQuery = true, value = "select * from tb_course_version v where v.course_id in(?1) and v.course_version_status = 1")
    List<CourseVersion> findCourseVersionByCourseIds(List<String> courseIdList);

}

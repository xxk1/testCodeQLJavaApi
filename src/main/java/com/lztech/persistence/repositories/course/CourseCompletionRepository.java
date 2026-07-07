package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseCompletion;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseCompletionRepository extends JpaRepository<CourseCompletion, String> {

    Optional<CourseCompletion> findByCourseAndCourseContentEnum(Course course, CourseContentTypeEnum contentTypeEnum);

    @Query(nativeQuery = true, value = "select  * from tb_course_completion cc " +
            " where cc.course_id in (?1) and is_completed=1 ")
    List<CourseCompletion> findByCourseIdList(List<String> courseIdList);

    List<CourseCompletion> findByCourseId(String courseId);

    @Query(nativeQuery = true, value = "select  distinct (cc.course_id) from tb_course_completion cc ")
    int countCountBuildNum();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_course_completion  set course_version_id=?2 " +
            " where course_id =?1 and course_version_id is null")
    void updateCourseVersion(String courseId, String courseVersionId);

    List<CourseCompletion> findByCourseIdAndCourseVersionId(String courseId, String courseVersionId);

    @Query(nativeQuery = true, value = "select * from tb_course_completion cc " +
            " where cc.course_id in (?1) and cc.course_version_id in (?2) and is_completed = 1 ")
    List<CourseCompletion> findByCourseIdListAndCourseVersionIdList(List<String> courseIdList, List<String> courseVersionIds);
}

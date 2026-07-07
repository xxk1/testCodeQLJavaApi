package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseMaterial;
import com.lztech.domain.model.course.enums.CourseSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {

    Course findByCourseCodeAndUseState(String courseCode, Boolean useState);

    Course findByCourseCode(String courseCode);

    List<Course> findByCourseCodeIn(List<String> courseCodeList);

    List<Course> findByCourseCodeInAndUseState(List<String> courseCodeList, Boolean useState);

    List<Course> findByIdIn(List<String> courseIds);


    List<Course> findByCourseNameLikeAndUseState(String courseName, Boolean useState);

    List<Course> findByCollegeNotNullAndUseStateIsTrue();

    @Transactional
    @Modifying
    @Query(value = "update Course set courseMaterial=?1 " +
            "where id = ?2 and courseMaterial is null  ")
    int updateCourse(CourseMaterial courseMaterial, String id);

    List<Course> findByCourseSourceAndCourseCodeLikeAndUseStateIsTrueOrderByCourseCodeDesc(CourseSource courseSource,
                                                                                           String courseCode);

    List<Course> findByCourseSourceAndCourseCodeLikeOrderByCreateTimeDesc(CourseSource courseSource,
                                                                          String courseCode);

    List<Course> findByIdIsInAndUseStateIsTrueOrderById(List<String> ids);

    Course findByIdAndUseState(String courseId, Boolean state);

    int countByCourseNameAndIdIsNotAndUseStateIsTrue(String courseName, String courseId);

    Optional<Course> findByIdAndUseStateIsTrue(String id);

    int countByCourseLeaderIdIsNotNull();

    List<Course> findByCourseLeaderIdAndUseStateIsTrue(String leaderId);

    @Query(nativeQuery = true,value = "SELECT id  FROM tb_course  WHERE  " +
            "  use_state = 1  " +
            "  AND ( course_name LIKE :courseName OR course_code LIKE  :courseCode )")
    List<String> findByCourseNameLikeOrCourseCodeLikeBySql(@Param("courseName") String courseName,

                                                           @Param("courseCode") String courseCode);

    List<Course> findAllByCollegeIdInAndUseState(List<String> collegeIds,Boolean b);

    List<Course> findByUseState(boolean useState);

    @Query(value = "SELECT * FROM tb_course WHERE " +
            "  use_state = 1  " +
            "  AND ( course_name LIKE :courseNameOrCode OR course_code LIKE  :courseNameOrCode )",
            nativeQuery = true)
    List<Course> findByCourseNameCodeLike(@Param("courseNameOrCode") String courseNameOrCode);

    @Query(value = "SELECT * FROM tb_course WHERE " +
            "  use_state = 1  " +
            "  AND ( course_name LIKE :courseName )",
            nativeQuery = true)
    List<Course> findByCourseNameLike(@Param("courseName") String courseName);
}

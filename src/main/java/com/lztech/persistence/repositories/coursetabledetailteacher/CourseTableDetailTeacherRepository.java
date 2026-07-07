package com.lztech.persistence.repositories.coursetabledetailteacher;

import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseTableDetailTeacherRepository extends JpaRepository<CourseTableDetailTeacher, String>, JpaSpecificationExecutor {
    List<CourseTableDetailTeacher> findByTeacherId(String userId);

    @Query(nativeQuery = true, value = "SELECT  * FROM tb_course_table_detail_teacher WHERE course_table_detail_id in " +
            "(SELECT id FROM tb_course_table_detail where course_table_id =(" +
            "SELECT id from tb_course_table WHERE group_id =:groupId and course_id = :courseId))  GROUP BY teacher_id;")
    List<CourseTableDetailTeacher> findAllByGroupIdAndCourseIdOrderByTeacherNo(@Param("groupId") String groupId, @Param("courseId") String courseId);

    List<CourseTableDetailTeacher> findByCourseTableDetail(CourseTableDetail courseTableDetail);


    @Query(nativeQuery = true, value = " SELECT * from tb_course_table_detail_teacher where course_table_detail_id in " +
            " (SELECT id from tb_course_table_detail where course_table_id =:courseTableId ) and teacher_id in ( :deleteTeacherIdList) and" +
            " (SELECT COUNT(*) from tb_course_table_detail_teacher where course_table_detail_id in (SELECT id from tb_course_table_detail " +
            " where course_table_id =:courseTableId ) and teacher_id not in  (:deleteTeacherIdList ))>=1 ")
    List<CourseTableDetailTeacher> getCourseTableDetailTeacher(@Param("deleteTeacherIdList") List<String> deleteTeacherIdList
            , @Param("courseTableId") String courseTableId);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = " delete from tb_course_table_detail_teacher where course_table_detail_id in " +
            " (SELECT id from tb_course_table_detail where course_table_id =:courseTableId ) and teacher_id in (:deleteTeacherIdList )  ")
    void deleteCourseTableDetailTeacher(@Param("deleteTeacherIdList") List<String> deleteTeacherIdList
            , @Param("courseTableId") String courseTableId);

    List<CourseTableDetailTeacher> findByCourseTableDetailIsIn(List<CourseTableDetail> courseTableDetailList);

    @Query(nativeQuery = true, value = "select * from " +
            " tb_course_table_detail_teacher where course_table_detail_id in (?1) ")
    List<CourseTableDetailTeacher> findByCourseTableDetailIdIn(List<String> courseTableDetailIdList);

    @Transactional
    @Modifying
    void deleteByCourseTableDetail(CourseTableDetail courseTableDetail);

}

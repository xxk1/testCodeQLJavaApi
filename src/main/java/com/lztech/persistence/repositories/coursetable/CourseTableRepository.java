package com.lztech.persistence.repositories.coursetable;

import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseTableRepository extends JpaRepository<CourseTable, String>, JpaSpecificationExecutor<CourseTable> {
    List<CourseTable> findByGroup(Group group);

    List<CourseTable> findByCourseIdAndTermAndSchoolYear(String courseId, Integer term, String schoolYear);

    @Query(nativeQuery = true, value = "select * from tb_course_table where group_id = :groupId")
    List<CourseTable> findByGroupId(@Param("groupId") String groupId);

    List<CourseTable> findByCourseIdIn(List<String> courseIdList);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = " update tb_course_table set course_name= ?1 where course_id=?2")
    void updateCourseNameByCourseId(String newCourseName, String id);

    @Query(nativeQuery = true, value = "select count(DISTINCT course_id) " +
            " from tb_course_table ct INNER JOIN tb_term t " +
            " on ct.school_year=t.school_year and ct.term=t.term " +
            " where t.start_date<=?1 and t.end_date>=?1 and course_id is not null")
    int getCourseInfoCourseNum(String nowDate);


    @Query(nativeQuery = true, value = "select count(DISTINCT college_id) " +
            " from tb_course_table ct INNER JOIN tb_term t " +
            " on ct.school_year=t.school_year and ct.term=t.term " +
            " where t.start_date<=?1 and t.end_date>=?1 and college_id is not null")
    int getCourseInfoCollegeNum(String nowDate);

    List<CourseTable> findByIdIn(List<String> ids);

    @Query(nativeQuery = true, value = "select * from tb_course_table where course_id in " +
            " (:courseIdList) and term = :term and school_year = :schoolYear")
    List<CourseTable> findByCourseIdInAndTermAndSchoolYear(@Param("courseIdList") List<String> courseIdList,
                                                           @Param("term") Integer term, @Param("schoolYear") String schoolYear);

    List<CourseTable> findByGroupIdInAndSchoolYearAndTerm(List<String> groupIdList, String schoolYear, Integer term);

    @Query(nativeQuery = true, value = "select DISTINCT id from tb_course_table where college_id in " +
            " (:collegeIds)")
    List<String> getCourseTableIdsByCollegeIds(@Param("collegeIds") List<String> collegeIds);
}

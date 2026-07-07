package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.CourseHiddenRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseHiddenRecordRepository extends JpaRepository<CourseHiddenRecord, String>, JpaSpecificationExecutor<CourseHiddenRecord> {
    List<CourseHiddenRecord> findByCourseIdAndTeacherIdAndSchoolYearAndTerm(String courseId, String teacherId, String schoolYear, Integer term);

    List<CourseHiddenRecord> findByCourseIdAndTeacherId(String courseId,String teacherId);

    List<CourseHiddenRecord> findByTeacherId(String teacherId);
}

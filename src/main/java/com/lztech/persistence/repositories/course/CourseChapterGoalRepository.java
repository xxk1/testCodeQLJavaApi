package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.CourseChapterGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseChapterGoalRepository extends JpaRepository<CourseChapterGoal, String> {

    List<CourseChapterGoal> findByCourseStructureId(String structureId);

    @Query(nativeQuery = true, value = "select * from tb_course_chapter_goal ccg " +
            " inner join tb_course_structure cs " +
            " on ccg.course_structure_id = cs.id " +
            " where cs.course_id=?1 and cs.course_version_id=?2 and cs.structure_status=0 " +
            " order by cs.show_order")
    List<CourseChapterGoal> findByCourseIdAndVersionId(String courseId, String versionId);
}

package com.lztech.persistence.repositories.courseobjective;

import com.lztech.domain.model.course.CourseObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseObjectivesRepository extends JpaRepository<CourseObjective, String> {

    @Query("SELECT COALESCE(MAX(o.showOrder), 0) FROM CourseObjective o WHERE o.courseId = ?1 AND o.useState = true")
    Integer findMaxShowOrder(String courseId);

    List<CourseObjective> findByCourseIdAndUseState(String courseId, boolean useState);
}

package com.lztech.persistence.repositories.wisdomcourse;

import com.lztech.domain.model.wisdomcourse.WisdomCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WisdomCourseRepository extends JpaRepository<WisdomCourse, String> {
    List<WisdomCourse> findByCourseIdAndUseState(String courseId, boolean useState);


    List<WisdomCourse> findByCourseIdInAndUseState(List<String> courseId, boolean useState);
    List<WisdomCourse> findByUseState(  boolean useState);
}

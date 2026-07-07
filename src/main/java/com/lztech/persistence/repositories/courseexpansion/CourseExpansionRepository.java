package com.lztech.persistence.repositories.courseexpansion;

import com.lztech.domain.model.course.CourseExpansion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseExpansionRepository extends JpaRepository<CourseExpansion, String> {

    int countByCourseIdAndExpansionKey(String courseId, String expansionKey);

    @Query(nativeQuery = true, value = "delete  from tb_course_expansion where id=?1")
    @Modifying
    @Transactional
    void deleteCourseExpansionById(String id);


    int countByCourseId(String courseId);

    List<CourseExpansion> findByCourseIdIn(List<String> courseIdList);

    CourseExpansion findByCourseId(String courseId);

}

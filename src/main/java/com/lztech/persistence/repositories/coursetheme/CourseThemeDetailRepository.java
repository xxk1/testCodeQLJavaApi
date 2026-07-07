package com.lztech.persistence.repositories.coursetheme;

import com.lztech.domain.model.course.CourseThemeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CourseThemeDetailRepository extends JpaRepository<CourseThemeDetail, String> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = " delete from tb_course_theme_detail where course_theme_id =?1 ")
    void deleteThemeDetailByThemeId(String id);
}

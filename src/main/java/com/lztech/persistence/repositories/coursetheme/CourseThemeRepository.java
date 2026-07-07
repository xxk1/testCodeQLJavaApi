package com.lztech.persistence.repositories.coursetheme;

import com.lztech.domain.model.course.CourseTheme;
import com.lztech.domain.model.course.enums.ResourceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseThemeRepository extends JpaRepository<CourseTheme, String> {

    CourseTheme findByIdAndThemeStatus(String id, ResourceStatus status);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = " update tb_course_theme set theme_status = 1 where id in (?1) ")
    void deleteCourseThemeByIds(List<String> themeIdList);

    @Query(nativeQuery = true, value = "select ch.* from  tb_course c " +
            " INNER JOIN tb_course_version cv        ON c.id = cv.course_id " +
            " INNER JOIN tb_course_teaching_team ctt on c.id = ctt.course_id and cv.id = ctt.course_version_id " +
            " INNER JOIN tb_course_structure cs      on cs.course_id = c.id  and cv.id = cs.course_version_id " +
            " INNER JOIN tb_course_resource cr       on cs.id = cr.course_structure_id " +
            " INNER JOIN tb_course_theme ch on cr.resource_detail_id = ch.id and cr.resource_type='4' " +
            " where c.use_state=1 and cs.structure_Status='0' " +
            " and theme_Status='0' and cv.course_version_status = '1' and ctt.teacher_id = ?1 and c.id=?2 " +
            " and cr.creator_id = ?1 ")
    List<CourseTheme> findByTeacherIdAndCourseId(String teacherId, String courseId);

    List<CourseTheme> findByIdIn(List<String> idList);
}

package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.TeachingTeamTeacher;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeachingTeamTeacherRepository extends JpaRepository<TeachingTeamTeacher, String>,
        JpaSpecificationExecutor<TeachingTeamTeacher> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM TeachingTeamTeacher t WHERE t.teacherId = :teacherId")
    Optional<TeachingTeamTeacher> findByTeacherIdForUpdate(@Param("teacherId") String teacherId);

    List<TeachingTeamTeacher> findAllByTeacherIdIn(List<String> teacherIds);

    @Modifying
    @Transactional
    @Query(value = "delete from tb_teaching_team_teacher where teacher_id=?1 ", nativeQuery = true)
    void deleteByTeacherId(String teacherId);
}

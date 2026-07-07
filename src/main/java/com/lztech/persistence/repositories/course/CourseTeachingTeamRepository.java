package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.TeacherDataSource;
import com.lztech.domain.model.course.enums.TeacherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseTeachingTeamRepository extends JpaRepository<CourseTeachingTeam, String>, JpaSpecificationExecutor {

    @Query(nativeQuery = true, value = "select * from tb_course_teaching_team a inner join tb_course b " +
            " on a.course_id = b.id inner join tb_course_version cv on a.course_version_id=cv.id" +
            " where a.teacher_id= ?1 and b.use_state=1 and cv.course_version_status=1 order by a.course_id ")
    List<CourseTeachingTeam> findByTeacherIdOrderByCourseId(String teacherId);
    @Query(nativeQuery = true, value = "select * from tb_course_teaching_team a inner join tb_course b " +
            " on a.course_id = b.id inner join tb_course_version cv on a.course_version_id=cv.id" +
            " where a.teacher_id= ?1 and b.use_state=1 and cv.course_version_status=1 " +
            " and a.course_id= ?2 order by a.course_id ")
    List<CourseTeachingTeam> findByTeacherIdAndCourseIdOrderByCourseId(String teacherId,String courseId);

    @Query(nativeQuery = true, value = "select * from tb_course_teaching_team a inner join tb_course b " +
            " on a.course_id = b.id inner join " +
            " tb_course_version cv on a.course_version_id=cv.id " +
            "  where a.teacher_id=?1 and b.use_state=1 and b.id=?2 and cv.course_version_status=1")
    CourseTeachingTeam findByTeacherIdAndCourseId(String teacherId, String courseId);

    List<CourseTeachingTeam> findByTeacherTypeAndCourseAndCourseVersion(TeacherType teacherType,
                                                                        Course course,
                                                                        CourseVersion courseVersion);

    @Query(nativeQuery = true, value = "select * from tb_course_teaching_team a inner join tb_course b " +
            " on a.course_id = b.id where b.use_state=1 and a.course_id=?1 and a.teacher_type=?2 ")
    List<CourseTeachingTeam> findByCourseIdAndTeacherType(String courseId, Integer teacherType);

    @Query(nativeQuery = true, value = "select count(distinct  teacher_id ) " +
            " from (select course_leader_id as teacher_id from tb_course " +
            " union select teacher_id from tb_course_teaching_team ) a where teacher_id" +
            " is not null")
    int countCourseTeachingTeacherNum();

    List<CourseTeachingTeam> findByCourseId(String courseId);

    List<CourseTeachingTeam> findByCourseIdAndTeacherDataSource(String courseId, TeacherDataSource teacherDataSource);

    List<CourseTeachingTeam> findByCourseIdAndTeacherId(String courseId, String teacherId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_course_teaching_team set course_version_id=?2,teacher_data_source=0 " +
            " where course_id =?1 and course_version_id is null ")
    void updateCourseVersion(String courseId, String courseVersionId);

    @Query(nativeQuery = true, value = "select * from tb_course_teaching_team " +
            " where course_id=?1 and course_version_id=?2 order by create_time desc")
    List<CourseTeachingTeam> findByCourseIdAndCourseVersionId(String courseId, String courseVersionId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_course_teaching_team set job_title=?1 " +
            " where teacher_id =?2 and course_version_id " +
            " in (select id from tb_course_version where course_version_status = 1)")
    void updateOtherTeachingTeamTeacherJobTitle(String jobTitle, String teacherId);

    @Query(nativeQuery = true, value = "select count(1) from tb_course_teaching_team " +
            " where course_id=?1 and course_version_id=?2 and teacher_id=?3")
    int countByCourseIdAndVersionIdAndUserId(String courseId, String versionId, String userId);

    @Query(nativeQuery = true, value = "select count(1) from tb_course_teaching_team ctt " +
            " inner join tb_course_version cv on ctt.course_version_id = cv.id " +
            " where ctt.course_id=?1  " +
            " and teacher_id=?2 and cv.course_version_status=1")
    int countByCourseIdAndTeacherId(String courseId, String teacherId);

    @Query(nativeQuery = true, value = "select * from tb_course_teaching_team " +
            " where teacher_id=?1 order by modify_time desc limit 1")
    CourseTeachingTeam getCourseTeachingTeamByTeacherId(String teacherId);

    Optional<CourseTeachingTeam> findFirstByTeacherId(String teacherId);

    CourseTeachingTeam findByCourseIdAndTeacherIdAndCourseVersionId(String courseId,String teacherId,String courseVersionId);

    @Query(nativeQuery = true, value = "select * from tb_course_teaching_team " +
            " where course_version_id in (?1)")
    List<CourseTeachingTeam> findByCourseVersionIdList(List<String> courseVersionIdList);

}

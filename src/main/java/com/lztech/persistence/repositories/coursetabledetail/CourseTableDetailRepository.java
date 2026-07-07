package com.lztech.persistence.repositories.coursetabledetail;

import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseTableDetailRepository extends JpaRepository<CourseTableDetail, String>, JpaSpecificationExecutor<CourseTableDetail> {

    @Query("select cbd" +
            "    from  CourseTableDetail cbd " +
            "    inner join cbd.courseTableDetailTeacherList tls" +
            "    inner join cbd.courseSegmentList ls " +
            "    inner join ls.segment sg " +
            "    where cbd.courseDate = :courseDate " +
            "    and tls.teacherId = :teacherId  " +
            "    group by cbd.id having max(sg.endTime) >= :endTime and min(sg.startTime) <= :startTime")
    List<CourseTableDetail> findCourseTableDetail(@Param("teacherId") String teacherId,
                                                  @Param("endTime") String endTime,
                                                  @Param("startTime") String startTime,
                                                  @Param("courseDate") Date courseDate);

    long countBySource(Source source);

    @Modifying
    @Transactional
    @Query("delete from CourseTableDetail a where a.source=:source and a.courseDate>:nowDate")
    void deleteNowDateAndAfterCourse(@Param("nowDate") Date nowDate, @Param("source") Source source);

    @Query("select a from CourseTableDetail a where a.source=:source and a.courseDate>:nowDate")
    List<CourseTableDetail> findNowDateAndAfterCourse(@Param("nowDate") Date nowDate, @Param("source") Source source);

    @Query(nativeQuery = true, value = "select * from tb_course_table_detail where " +
            " course_table_id = :courseTableId and course_date= :courseDate and source=5")
    List<CourseTableDetail> findByCourseTableAndCourseDate(@Param("courseTableId") String courseTableId,
                                                           @Param("courseDate") String courseDate);

    @Query(nativeQuery = true, value = "select * from tb_course_table_detail where " +
            "course_date= :courseDate ")
    List<CourseTableDetail> findByCourseDate(@Param("courseDate") String courseDate);

    @Query(" select DISTINCT ctd from CourseTableDetail ctd " +
            " inner join ctd.courseTableDetailRoomUserList ctdru " +
            " where ctdru.roomId in (:roomIdList)  and  ctd.segmentEndTime is not null and ctd.courseDate >=:nowDate " +
            "  and ctd.courseDate is not null and ctd.segmentStartTime is not null ")
    List<CourseTableDetail> getRoomScheduleInformationList(@Param("roomIdList") List<String> roomIdList,
                                                           @Param("nowDate") Date nowDate);
    @Query(" select DISTINCT ctd from CourseTableDetail ctd " +
            " inner join ctd.courseTableDetailRoomUserList ctdru " +
            " where ctdru.roomId= ?1  and  ctd.segmentEndTime is not null   and ctd.courseDate= ?2" +
            "  and ctd.courseDate is not null and ctd.segmentStartTime is not null ")
    List<CourseTableDetail> getRoomCourseDateList(String roomId,Date courseDate);

    List<CourseTableDetail> findByCourseTableId(String courseTableId);

    @Query(nativeQuery = true, value = " select * from tb_course_table_detail where id in " +
            " (select course_table_detail_id from tb_course_table_detail_teacher " +
            " where teacher_id=:teacherId)")
    List<CourseTableDetail> findByTeacherId(@Param("teacherId") String teacherId);

    List<CourseTableDetail> findByIdIn(List<String> ids);
}

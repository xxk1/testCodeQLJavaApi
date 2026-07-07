package com.lztech.persistence.repositories.coursetabledetailroomuser;

import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseTableDetailRoomUserRepository extends JpaRepository<CourseTableDetailRoomUser, String>
        , JpaSpecificationExecutor<CourseTableDetailRoomUser> {

    List<CourseTableDetailRoomUser> findByCourseTableDetail(CourseTableDetail courseTableDetail);

    @Query(nativeQuery = true, value = "select * from tb_course_table_detail_room_user where course_table_detail_id = :courseTableDetailId " +
            " and room_id = :roomId limit 1")
    CourseTableDetailRoomUser findByCourseTableDetailIdAndRoomId(@Param("courseTableDetailId") String courseTableDetailId,
                                                                 @Param("roomId") String roomId);

    List<CourseTableDetailRoomUser> findByCourseTableDetailIdIn(List<String> courseTableDetailIdList);

    @Query(nativeQuery = true,value="SELECT count(DISTINCT ctdru.room_id) " +
            " FROM tb_course_table_detail ctd INNER JOIN " +
            " tb_course_table_detail_room_user ctdru " +
            " ON ctd.id = ctdru.course_table_detail_id  and ctd.course_date=CURRENT_DATE()" +
            " WHERE CONCAT(ctd.course_date,' ',ctd.segment_start_time) <= ?1 AND " +
            " CONCAT(ctd.course_date,' ',ctd.segment_end_time) >= ?1 AND ctdru.room_id IS NOT NULL ")
    int getInClassRoomNum(String nowDateTime);

    Integer countAllByCourseTableDetailIdIn(List<String> ids);
    @Transactional
    @Modifying
    void deleteByCourseTableDetail(CourseTableDetail courseTableDetail);
}

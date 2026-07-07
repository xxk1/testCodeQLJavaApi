package com.lztech.site.service.roomcoursetabledetail;

import com.lztech.site.viewmodel.coursetabledetail.RoomCourseTableDetailResource;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomCourseTableDetailService {

    @Autowired
    private EntityManager entityManager;
    @Value("${studentType}")
    private String studentType;

    public List<RoomCourseTableDetailResource> getCourseTableDetailList(String roomId, String startTime, String endTime) {

        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT ct.course_name as courseName,GROUP_CONCAT(DISTINCT ctdt.teacher_name) as teacherNames," +
                "GROUP_CONCAT( DISTINCT ctdt.teacher_no ) AS teacherNos, " +
                "g.group_name as groupName,ctd.segment as segments,ct.college_name as collegeName, " +
                "(SELECT  count(1) from tb_group_member where group_id = g.id and group_Member_Status=0) as studentNum," +
                "  CONCAT(ctd.course_date,' ',ctd.segment_start_time) as segmentStartTime," +
                " CONCAT(ctd.course_date,' ',ctd.segment_end_time) as segmentEndTime ," +
                " ctdru.room_id as roomId, ctdru.room_name as roomName " +
                "FROM `tb_course_table_detail`  ctd " +
                "INNER JOIN tb_course_table_detail_room_user  ctdru on ctd.id=ctdru.course_table_detail_id " +
                "INNER JOIN tb_course_table ct on ct.id=ctd.course_table_id " +
                "INNER JOIN tb_course_table_detail_teacher ctdt on ctd.id=ctdt.course_table_detail_id " +
                "INNER JOIN tb_group g on ct.group_id=g.id  " +
                "where g.group_status='0' and ctdru.room_id=:roomId" +
                " and CONCAT(ctd.course_date,' ',ctd.segment_start_time) <= :endTime " +
                " and CONCAT(ctd.course_date,' ',ctd.segment_end_time) >=  :startTime group by ctd.id\n";
        paramMap.put("roomId", roomId);
        paramMap.put("startTime", startTime+ ":00" );
        paramMap.put("endTime", endTime+ ":59" );
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(RoomCourseTableDetailResource.class));
        return query.getResultList();
    }
}

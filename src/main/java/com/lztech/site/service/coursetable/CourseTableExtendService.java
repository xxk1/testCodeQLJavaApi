package com.lztech.site.service.coursetable;

import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.coursetable.RealtimeSegment;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseTableExtendService {

    @Autowired
    private EntityManager entityManager;

    public List<RealtimeSegment> getRealtimeSegmentsByRoomId(String roomId) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "select ctd.id as courseTableDetailId,cs.id as courseSegmentId,s.start_time as startTime,s.end_time as endTime" +
                " from tb_course_table ct" +
                " join tb_course_table_detail ctd on ctd.course_table_id = ct.id" +
                " join tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = ctd.id" +
                " join tb_course_segment cs on cs.course_table_detail_id = ctd.id" +
                " join tb_segment s on s.buildid=cs.build_id and cs.segment=s.segment" +
                " where ctdru.room_id = :roomId" +
                " and ctd.course_date = :courseDate" +
                " and s.start_time <= :nowTime and s.end_time>= :nowTime";

        Date nowDate = new Date();
        String day = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String time = DateUtils.formatDate(DateUtils.TIME, nowDate);

        paramMap.put("roomId", roomId);
        paramMap.put("courseDate", day);
        paramMap.put("nowTime", time);

        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }
}

package com.lztech.site.service.myuniversity;

import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.myuniversity.CourseTableIdVo;
import com.lztech.site.viewmodel.myuniversity.MyUniversityAttendClassStatisticsInfoVo;
import com.lztech.site.viewmodel.myuniversity.MyUniversityHighestScoreCourseInfoInfoVo;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyUniversityService {
    @Autowired
    private EntityManager entityManager;

    public MyUniversityHighestScoreCourseInfoInfoVo getMyUniversityHighestScoreCourseInfos(String studentNo) {
        MyUniversityHighestScoreCourseInfoInfoVo highestScoreCourseInfoInfoVo = null;

        String sql = "select c.course_code as courseCode,c.course_name as courseName,ss.total_score as totalScore" +
                " from tb_student_score ss join tb_course c on ss.course_code=c.course_code" +
                " where student_no=:studentNo order by total_score desc limit 1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("studentNo", studentNo);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> resMapList = query.getResultList();

        if (!resMapList.isEmpty()) {
            Map<String, Object> resMap = resMapList.get(0);
            highestScoreCourseInfoInfoVo = new MyUniversityHighestScoreCourseInfoInfoVo();
            highestScoreCourseInfoInfoVo.setCourseCode(resMap.get("courseCode").toString());
            highestScoreCourseInfoInfoVo.setCourseName(resMap.get("courseName").toString());

            BigDecimal totalScore = new BigDecimal(resMap.get("totalScore").toString());
            highestScoreCourseInfoInfoVo.setScore(String.valueOf(totalScore.intValue()));
        }

        return highestScoreCourseInfoInfoVo;
    }

    public MyUniversityAttendClassStatisticsInfoVo getMyUniversityAttendClassStatisticsInfos(String studentId) {
        MyUniversityAttendClassStatisticsInfoVo statisticsInfoVo = null;
        String sql = "select a.courseName as courseName,a.firstAttendClassTime as firstAttendClassTime,b.courseNumber,b.segmentNumber" +
                " from" +
                " (" +
                " select gm.student_id as student_id," +
                " ct.course_name as courseName,concat(ctd.course_date,' ',ctd.segment_start_time) as firstAttendClassTime" +
                " from tb_course_table ct" +
                " join tb_course_table_detail ctd on ct.id=ctd.course_table_id" +
                " join tb_group g on ct.group_id=g.id and g.group_status=0" +
                " join tb_group_member gm on g.id=gm.group_id and gm.group_member_status = 0" +
                " where ct.source=0 and gm.student_id=:studentId" +
                " and concat(ctd.course_date,' ',ctd.segment_start_time)<=:now" +
                " order by ctd.course_date,ctd.segment_start_time" +
                " limit 1" +
                " ) a" +
                " join (" +
                " select gm.student_id as student_id," +
                " count(distinct ct.course_id) as courseNumber," +
                " sum(LENGTH(ctd.segment)-LENGTH(REPLACE(ctd.segment, ',', '')) + 1)  as segmentNumber" +
                " from tb_course_table ct" +
                " join tb_course_table_detail ctd on ct.id=ctd.course_table_id " +
                " join tb_group g on ct.group_id=g.id and g.group_status=0" +
                " join tb_group_member gm on g.id=gm.group_id and gm.group_member_status = 0" +
                " where ct.source=0 and gm.student_id=:studentId" +
                " and concat(ctd.course_date,' ',ctd.segment_start_time)<=:now" +
                " ) b on a.student_id = b.student_id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("studentId", studentId);
        paramMap.put("now", DateUtils.formatDate(DateUtils.DATE_TIME,new Date()));
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>)query.getResultList().get(0);

        int courseNumber = Integer.parseInt(resMap.get("courseNumber").toString());
        if (courseNumber > 0) {
            statisticsInfoVo = new MyUniversityAttendClassStatisticsInfoVo();

            Date firstAttendClassDate = DateUtils.stringToDate(DateUtils.DATE_TIME,resMap.get("firstAttendClassTime").toString());

            LocalDateTime firstAttendClassTime = LocalDateTime.parse(
                    DateUtils.formatDate(DateUtils.DATE_TIME,firstAttendClassDate),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            statisticsInfoVo.setFirstClassYear(String.valueOf(firstAttendClassTime.getYear()));
            statisticsInfoVo.setFirstClassMonth(String.valueOf(firstAttendClassTime.getMonthValue()));
            statisticsInfoVo.setFirstClassDay(String.valueOf(firstAttendClassTime.getDayOfMonth()));
            statisticsInfoVo.setFirstClassHour(DateUtils.formatDate(DateUtils.HOUR,firstAttendClassDate));
            statisticsInfoVo.setFirstClassMinute(DateUtils.formatDate(DateUtils.MINUTES,firstAttendClassDate));
            statisticsInfoVo.setFirstClassCourseName(resMap.get("courseName").toString());
            statisticsInfoVo.setTotalCourseNumber(String.valueOf(courseNumber));
            statisticsInfoVo.setTotalSegmentNumber(resMap.get("segmentNumber").toString());
        }

        return statisticsInfoVo;
    }

    public List<CourseTableIdVo> getStudentIdByCourseTableIds(String studentId) {
        String sql = "select distinct ct.id as courseTableId  from tb_course_table ct " +
                "join tb_group g on ct.group_id=g.id and g.group_status=0 " +
                "join tb_group_member gm on g.id=gm.group_id and gm.group_member_status = 0 " +
                "where ct.source=0 and gm.student_id=:studentId ;";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("studentId", studentId);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableIdVo.class));
        return query.getResultList();
    }
}

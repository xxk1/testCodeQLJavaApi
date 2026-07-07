package com.lztech.site.service.classfirstcoursetable;

import com.lztech.domain.model.term.Term;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.firstcoursetable.ClassFirstCourseTableInfo;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClassFirstCourseTableService {

    @Autowired
    private TermService termService;

    @Autowired
    private EntityManager entityManager;



    public List<ClassFirstCourseTableInfo> getClassTermFirstCourseTable() {
        Term term = termService.getNowTerm();
        if (Objects.isNull(term)) {
            return new ArrayList<>();
        }
        String sql = getQuerySql(term);
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ClassFirstCourseTableInfo.class));
        return query.getResultList();
    }

    private String getQuerySql(Term term) {

        StringBuffer sql = new StringBuffer("select \n" +
                " g.id as groupId,\n" +
                " g.group_name as groupName,\n" +
                " ct.course_name as courseName,\n" +
                " c.course_code as courseCode,\n" +
                " ct.id as courseTableId,\n" +
                " ctd.id as courseTableDetailId,\n" +
                " DATE_FORMAT( ctd.course_date,'%Y-%m-%d') as courseDate,\n" +
                " ctd.segment as segments,\n" +
                " DATE_FORMAT(ctd.segment_start_time,'%H:%i:%s') as segmentStartTime,\n" +
                " DATE_FORMAT(ctd.segment_end_time,'%H:%i:%s')  as segmentEndTime,\n" +
                " ctdru.room_id as roomId,\n" +
                " ctdru.room_code as roomCode ,\n" +
                " ctdru.room_name as roomName,\n" +
                " GROUP_CONCAT(ctdt.teacher_id ORDER BY ctdt.teacher_id) teacherIds,\n" +
                " GROUP_CONCAT(ctdt.teacher_no ORDER BY ctdt.teacher_id) as teacherNos,\n" +
                " GROUP_CONCAT(ctdt.teacher_name ORDER BY ctdt.teacher_id) as teacherNames,\n" +
                " GROUP_CONCAT(ctdt.teacher_college_id ORDER BY ctdt.teacher_id) as teacherCollegeIds,\n" +
                " GROUP_CONCAT(ctdt.teacher_college_name ORDER BY ctdt.teacher_id) as teacherCollegeNames,\n" +
                " ct.college_id as courseTableCollegeId,\n" +
                " ct.college_name as courseTableCollegeName," +
                " ct.school_year as schoolYear, ct.term as term," +
                " (select count(1) from tb_group_member where group_id = g.id and group_member_status= '0' )  as groupMember " +
                " from tb_course_table_detail ctd \n" +
                " INNER JOIN  \n" +
                " (select course_table_id,MIN(CONCAT(course_date,' ',segment_start_time)) as min_course_time\n" +
                "   from tb_course_table_detail \n" +
                "   where course_date>='" + DateUtils.formatDate(DateUtils.DATE, term.getStartDate()) + "' and " +
                " course_date<='" + DateUtils.formatDate(DateUtils.DATE, term.getEndDate()) + "' and source=0 group by course_table_id )\n" +
                "\t temp on CONCAT(course_date,' ',segment_start_time) = temp.min_course_time \n" +
                "\t and ctd.course_table_id = temp.course_table_id\n" +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id\n" +
                " INNER JOIN tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id\n" +
                " INNER JOIN tb_course_table ct on ct.id = ctd.course_table_id\n" +
                " INNER JOIN tb_group g on ct.group_id = g.id\n" +
                " INNER JOIN tb_course c on c.id = ct.course_id\n" +
                " where ctdru.room_id is not null and ctdru.has_live=1 and ctd.source=0 "

        );

        sql.append("  and ctd.course_date>= ' "+ DateUtils.formatDate(DateUtils.DATE, term.getStartDate()) + " ' " +
                "   and ctd.course_date<= ' "+ DateUtils.formatDate(DateUtils.DATE, term.getEndDate()) + " ' " +
                "   GROUP BY ctd.id");
        return sql.toString();
    }
}

package com.lztech.site.service.coursetabledetail;

import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.TeacherCourseTableSimpleResource;
import com.lztech.site.viewmodel.coursetabledetail.TeacherCourseTableDetailBaseResource;
import com.lztech.site.viewmodel.coursetabledetail.TeacherCourseTableDetailParam;
import com.lztech.site.viewmodel.group.GroupMemberSqlVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeacherCourseTableDetailService {
    @Value("${attendClassAdvanceTime}")
    private Integer attendClassAdvanceTime;
    @Value("${attendClassAdvanceEarliestTime}")
    private Integer attendClassAdvanceEarliestTime;

    @Autowired
    private EntityManager entityManager;

    public List<TeacherCourseTableSimpleResource> getTeacherCurrentCourseTableList(String teacherId) {
        List<TeacherCourseTableSimpleResource> courseTableSimpleVoList = getCourseTableDetailByCurrentAndTeacherId(teacherId, attendClassAdvanceTime);
        if (CollectionUtils.isEmpty(courseTableSimpleVoList)) {
            courseTableSimpleVoList = getCourseTableDetailByCurrentAndTeacherId(teacherId, attendClassAdvanceEarliestTime);
        }
        if (CollectionUtils.isNotEmpty(courseTableSimpleVoList)) {
            List<String> groupIdList = courseTableSimpleVoList
                    .stream()
                    .map(TeacherCourseTableSimpleResource::getGroupId)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());
            List<GroupMemberSqlVo> groupMemberSqlVoList = getGroupMemberNum(groupIdList);
            for (TeacherCourseTableSimpleResource simpleResource : courseTableSimpleVoList) {
                GroupMemberSqlVo groupMemberSqlVo =
                        groupMemberSqlVoList.stream().filter(g -> g.getGroupId().equals(simpleResource.getGroupId())).findFirst().orElse(null);
                if (Objects.nonNull(groupMemberSqlVo)) {
                    simpleResource.setGroupMemberNum(groupMemberSqlVo.getGroupMemberCount().intValue());
                }
            }
        }

        return courseTableSimpleVoList;
    }

    private List<GroupMemberSqlVo> getGroupMemberNum(List<String> groupIdList) {
        if (CollectionUtils.isEmpty(groupIdList)) {
            return new ArrayList<>();
        }
        StringBuilder groupIdBuilder = new StringBuilder();
        for (String groupId : groupIdList) {
            groupIdBuilder.append("\'");
            groupIdBuilder.append(groupId);
            groupIdBuilder.append("\',");
        }
        String sql = "select group_id as groupId,count(1) as groupMemberCount " +
                " from tb_group_member where group_member_status= '0' and  group_id in (" + groupIdBuilder.substring(0, groupIdBuilder.length() - 1) +
                ") group by group_id";

        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GroupMemberSqlVo.class));
        return query.getResultList();
    }

    private List<TeacherCourseTableSimpleResource> getCourseTableDetailByCurrentAndTeacherId(String teacherId, Integer attendClassAdvanceTime) {
        Date date = new Date();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime nowTime = LocalDateTime.now();
        String startTime = nowTime.plusMinutes(attendClassAdvanceTime).format(formatter);
        String nowDate = DateUtils.formatDate("yyyy-MM-dd", date);
        String querySql = "select ctd.id   as courseTableDetailId, \n " +
                "       ct.course_name     as courseName, \n " +
                "       ct.id              as courseTableId, \n " +
                "       g.id               as groupId, \n " +
                "       g.group_no         as groupNo, \n " +
                "       g.group_name       as groupName, \n " +
                "       ct.college_id      as collegeId, \n " +
                "       (select college_code from tb_college where id = ct.college_id LIMIT 1 ) as collegeCode, \n " +
                "       ct.college_name    as collegeName, \n " +
                "       c.id               as courseId, \n " +
                "       c.course_code      as courseCode, \n " +
                "       c.college_id       as courseCollegeId, \n " +
                "       c.college_code     as courseCollegeCode, \n " +
                "       c.college_name     as courseCollegeName, \n " +
                "    group_concat(distinct ctdru.room_id)    as roomIds \n," +
                "  DATE_FORMAT( ctd.segment_start_time, '%H:%i:%s' ) AS segmentStartTime," +
                " DATE_FORMAT( ctd.segment_end_time, '%H:%i:%s' ) AS segmentEndTime, " +
                "       ct.student_type                      as studentType \n " +
                " from tb_course_table_detail ctd \n " +
                "         inner join tb_course_table ct on ctd.course_table_id = ct.id \n " +
                "         inner join tb_course c on c.id= ct.course_id \n " +
                "         inner join tb_group g on ct.group_id = g.id \n " +
                "         inner join tb_course_segment cs on ctd.id = cs.course_table_detail_id \n " +
                "         inner join tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id \n " +
                "         inner join tb_segment s on cs.build_id = s.buildid and s.segment = cs.segment \n " +
                "         left join tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id \n " +
                "        where ctd.course_date= :nowDate and ctdt.teacher_id= :teacherId group by ctd.id " +
                " having max(s.end_time)>= :endTime  and min(s.start_time)<= :startTime ";
        Query query = entityManager.createNativeQuery(querySql);
        query.setParameter("startTime", startTime);
        query.setParameter("endTime", nowTime.format(formatter));
        query.setParameter("nowDate", nowDate);
        query.setParameter("teacherId", teacherId);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TeacherCourseTableSimpleResource.class));
        return query.getResultList();
    }


    public List<TeacherCourseTableDetailBaseResource> getTeacherCourseTableDetail(String teacherId,
                                                                                  TeacherCourseTableDetailParam param) {
        String querySql = "select ctd.id as courseTableDetailId,ct.course_name as courseName,group_concat(ctdru.room_name) AS roomName,group_concat" +
                "(ctdru.room_id) AS roomId ,DATE_FORMAT(ctd.course_date,'%Y-%m-%d') as courseDate,DATE_FORMAT(ctd.segment_start_time,'%H:%i') " +
                " segmentStartTime,DATE_FORMAT(ctd.segment_end_time,'%H:%i') segmentEndTime,ctd.segment as segments,ct.school_year as schoolYear," +
                " ct.term as term" +
                " from tb_course_table_detail ctd\n" +
                "INNER JOIN tb_course_table_detail_teacher ctdt on ctd.id=ctdt.course_table_detail_id\n" +
                "INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id=ctdru.course_table_detail_id\n" +
                "INNER JOIN tb_course_table ct on ct.id=ctd.course_table_id\n" +
                "where ctdt.teacher_id= :teacherId ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("teacherId", teacherId);
        String now = DateUtils.formatDate(DateUtils.DATE_TIME, new Date());
        if (StringUtils.isNotBlank(param.getSchoolYear())) {
            querySql += " and ct.school_year= :schoolYear ";
            paramMap.put("schoolYear", param.getSchoolYear());
        }
        if (Objects.nonNull(param.getTerm())) {
            querySql += " and ct.term= :term ";
            paramMap.put("term", param.getTerm());
        }
        if (Objects.nonNull(param.getType()) && param.getType() == 1) {
            // 获取当前时间之前的课程表详情
            querySql += " and CONCAT(ctd.course_date,' ',ctd.segment_start_time)> '"+now+"' ";
        }
        querySql += " group by ctd.id ";
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TeacherCourseTableDetailBaseResource.class));
        return query.getResultList();
    }
}

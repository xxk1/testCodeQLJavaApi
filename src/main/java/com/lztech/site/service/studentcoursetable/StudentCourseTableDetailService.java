package com.lztech.site.service.studentcoursetable;

import com.alibaba.excel.util.StringUtils;
import com.lztech.domain.model.term.Term;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailInfoModel;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailSqlVo;
import com.lztech.site.resource.coursetabledetail.WeekCourseTableDetailPage;
import com.lztech.site.resource.coursetabledetail.WeekDateCourseTableDetailResource;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseTableDetailBaseResource;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseTableDetailParam;
import com.lztech.site.viewmodel.studentschedule.StudentCourseTableSqlVo;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.service.coursetabledetail.CourseTableDetailService.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class StudentCourseTableDetailService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TermService termService;


    public List<StudentCourseTableDetailBaseResource> getStudentCourseTableDetail(String studentId,
                                                                                  StudentCourseTableDetailParam param) {
        List<StudentCourseTableSqlVo> studentGroupBaseInfoList = getStudentGroupBaseInfo(studentId, param.getSchoolYear(), param.getTerm());
        if (CollectionUtils.isEmpty(studentGroupBaseInfoList)) {
            return new ArrayList<>();
        }

        return getStudentCourseTableDetailBaseResource(studentGroupBaseInfoList, param);

    }

    private List<StudentCourseTableDetailBaseResource> getStudentCourseTableDetailBaseResource(List<StudentCourseTableSqlVo>
                                                                                                       studentGroupBaseInfoList,
                                                                                               StudentCourseTableDetailParam param) {
        List<String> courseTableIdList = studentGroupBaseInfoList.stream().map(StudentCourseTableSqlVo::getCourseTableId)
                .distinct().collect(Collectors.toList());
        String querySql = "select ctd.id as courseTableDetailId,ct.course_name as courseName,group_concat(ctdru.room_name) AS roomName,group_concat" +
                "(ctdru.room_id) AS roomId ,DATE_FORMAT(ctd.course_date,'%Y-%m-%d') as courseDate,DATE_FORMAT(ctd.segment_start_time,'%H:%i') " +
                " segmentStartTime,DATE_FORMAT(ctd.segment_end_time,'%H:%i') segmentEndTime,ctd.segment as segments," +
                "ct.school_year as schoolYear," +
                " ct.term as term " +
                " from tb_course_table ct inner join tb_course_table_detail ctd on ct.id = ctd.course_table_id " +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id=ctdru.course_table_detail_id\n" +
                " where ctd.course_table_id in ( :courseTableIdList )";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseTableIdList", courseTableIdList);
        String now = DateUtils.formatDate(DateUtils.DATE_TIME, new Date());
        if (Objects.nonNull(param.getType()) && param.getType() == 1) {
            // 获取当前时间之前的课程表详情
            querySql += " and CONCAT(ctd.course_date,' ',ctd.segment_start_time)> '" + now + "' ";
        }
        querySql += " group by ctd.id ";
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(StudentCourseTableDetailBaseResource.class));
        return query.getResultList();
    }

    private List<StudentCourseTableSqlVo> getStudentGroupBaseInfo(String studentId,
                                                                  String schoolYear, Integer term) {
        String sql = "SELECT ct.id as courseTableId, ct.course_id as courseId,ct.course_name as courseName" +
                " from tb_course_table ct" +
                " INNER JOIN tb_group_member gm on gm.group_id=ct.group_id " +
                " where gm.student_id= :studentId and gm.group_member_status=0 ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("studentId", studentId);
        if (StringUtils.isBlank(schoolYear)) {
            sql += " and ct.school_year = :schoolYear ";
            paramMap.put("schoolYear", schoolYear);
        }
        if (Objects.nonNull(term)) {
            sql += " AND ct.term= :term";
            paramMap.put("term", term);
        }
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(StudentCourseTableSqlVo.class));
        return query.getResultList();
    }

    public WeekCourseTableDetailPage getStudentCourseTableDetailWeekDetailInfo(String studentId,
                                                                               String courseDate, String schoolYear, Integer term) {
        WeekCourseTableDetailPage weekCourseTableDetailPage = new WeekCourseTableDetailPage();
        weekCourseTableDetailPage.setWeek(null);
        weekCourseTableDetailPage.setWeekDateCourseTableDetailList(new ArrayList<>());
        Term nowTerm = termService.getNowTerm();
        if (Objects.isNull(nowTerm)) {
            return weekCourseTableDetailPage;
        }
        Date courseDateTime = DateUtils.stringToDate("yyyy-MM-dd", courseDate);
        String newDate = TimeUtils.getNowDate();
        int weeks = getWeek(courseDateTime, nowTerm.getStartDate());
        weekCourseTableDetailPage.setWeek("第" + weeks + "周次");
        List<StudentCourseTableSqlVo> studentGroupBaseInfoList = getStudentGroupBaseInfo(studentId, schoolYear, term);
        if (CollectionUtils.isEmpty(studentGroupBaseInfoList)) {
            return weekCourseTableDetailPage;
        }
        List<String> weekCourseDateList = new ArrayList<>();
        getWeekCourseDateList(courseDateTime, weekCourseDateList);
        List<String> courseTableIdList = studentGroupBaseInfoList.stream().map(StudentCourseTableSqlVo::getCourseTableId)
                .distinct().collect(Collectors.toList());
        List<CourseTableDetailSqlVo> courseTableDetailSqlVoList = getCourseTableDetailSqlVo(nowTerm, courseTableIdList, weekCourseDateList);
        if (CollectionUtils.isNotEmpty(courseTableDetailSqlVoList)) {
            Map<String, List<CourseTableDetailSqlVo>> courseTableDetailSqlVoGroupByCourseDateMap = courseTableDetailSqlVoList.stream()
                    .collect(Collectors.groupingBy(CourseTableDetailSqlVo::getCourseDate));
            List<WeekDateCourseTableDetailResource> weekDateCourseTableDetailList = new ArrayList<>();
            weekCourseDateList.forEach(weekCourseDate -> {
                List<CourseTableDetailSqlVo> groupCourseTableDetailSqlVoList = courseTableDetailSqlVoGroupByCourseDateMap.get(weekCourseDate);
                WeekDateCourseTableDetailResource weekDateCourseTableDetailResource = new WeekDateCourseTableDetailResource();
                weekDateCourseTableDetailResource.setCourseDate(weekCourseDate);
                weekDateCourseTableDetailResource.setWeekNum(dateToWeek(weekCourseDate));
                weekDateCourseTableDetailResource.setHasToday(newDate.equals(weekCourseDate));
                List<CourseTableDetailInfoModel> courseTableDetailInfoModelList = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(groupCourseTableDetailSqlVoList)) {
                    groupCourseTableDetailSqlVoList.forEach(courseTableDetailSqlVo -> {
                        CourseTableDetailInfoModel courseTableDetailInfoModel = new CourseTableDetailInfoModel();
                        courseTableDetailInfoModel.setCourseId(courseTableDetailSqlVo.getCourseId());
                        courseTableDetailInfoModel.setCourseName(courseTableDetailSqlVo.getCourseName());
                        courseTableDetailInfoModel.segmentStartTime(courseTableDetailSqlVo.getSegmentStartTime());
                        courseTableDetailInfoModel.setSegmentEndTime(courseTableDetailSqlVo.getSegmentEndTime());
                        courseTableDetailInfoModel.setRoomNames(Arrays.stream(courseTableDetailSqlVo.getRoomNames().split(",")).collect(toList())
                                .stream().filter(roomName -> !roomName.isEmpty()).collect(joining("、")));
                        courseTableDetailInfoModel.setRoomCodes(Arrays.stream(courseTableDetailSqlVo.getRoomCodes().split(",")).collect(toList())
                                .stream().filter(roomCode -> !roomCode.isEmpty()).collect(joining("、")));
                        courseTableDetailInfoModelList.add(courseTableDetailInfoModel);
                    });
                }
                weekDateCourseTableDetailResource.setCourseTableDetailList(courseTableDetailInfoModelList.stream().sorted(
                        Comparator.comparing(CourseTableDetailInfoModel::getSegmentStartTime)).collect(toList()));
                weekDateCourseTableDetailList.add(weekDateCourseTableDetailResource);
            });
            weekCourseTableDetailPage.setWeekDateCourseTableDetailList(weekDateCourseTableDetailList.stream().sorted(
                    Comparator.comparing(WeekDateCourseTableDetailResource::getCourseDate)).collect(toList()));
        }
        return weekCourseTableDetailPage;

    }


    private List<CourseTableDetailSqlVo> getCourseTableDetailSqlVo(Term nowTerm,
                                                                   List<String> courseTableIdList,
                                                                   List<String> weekCourseDateList) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = " SELECT distinct  b.course_id as courseId, " +
                " b.course_name as courseName,date_format( a.course_date, '%Y-%m-%d') as courseDate, " +
                " DATE_FORMAT(a.segment_start_time,'%H:%i') segmentStartTime,DATE_FORMAT(a.segment_end_time,'%H:%i') segmentEndTime, " +
                "group_concat(if(room_name is null,'',room_name) order by ctdru.id SEPARATOR ',') as roomNames," +
                "group_concat(if(room_code is null,'',room_code) order by ctdru.id SEPARATOR ',') as roomCodes" +
                " FROM tb_course_table_detail a INNER JOIN tb_course_table b on a.course_table_id = b.id " +
                " join tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = a.id" +
                " where b.id in (:courseTableIdList) and a.course_date in (:weekCourseDateList) " +
                " group by ctdru.course_table_detail_id";
        paramMap.put("weekCourseDateList", weekCourseDateList);
        paramMap.put("courseTableIdList", courseTableIdList);
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailSqlVo.class));
        return (List<CourseTableDetailSqlVo>) query.getResultList();
    }
}

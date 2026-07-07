package com.lztech.site.service.coursetable;

import com.lztech.domain.model.coursetabledetail.CourseParms;
import com.lztech.domain.model.coursetabledetail.CourseTableDetailProject;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.coursetype.CourseType;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailProjectRepository;
import com.lztech.persistence.repositories.coursetabledetailroomuser.CourseTableDetailRoomUserRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.coursetype.CourseTypeRepository;
import com.lztech.site.viewmodel.coursetable.CourseTableDate;
import com.lztech.site.viewmodel.studentschedule.GroupMemberStatisticsSqlVo;
import com.lztech.site.viewmodel.studentschedule.StudentCourseTableSqlVo;
import com.lztech.site.viewmodel.studentschedule.StudentScheduleSqlVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeacherScheduleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherScheduleService.class);
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseTableDetailRoomUserRepository courseTableDetailRoomUserRepository;
    @Autowired
    private CourseTableDetailProjectRepository courseTableDetailProjectRepository;
    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private CourseTypeRepository courseTypeRepository;

    public List<CourseTableDate> getTeacherCourseTablesDayOrWeek(CourseParms courseparms) {
        long startTime = System.currentTimeMillis();
        List<StudentScheduleSqlVo> studentScheduleSqlVoList = getTeacherCourseTableDetailDateSqlList(courseparms);
        long endTime = System.currentTimeMillis();
        LOGGER.info("获取教师课程表详细日期SQL列表耗时: {}ms", endTime - startTime);
        if (CollectionUtils.isEmpty(studentScheduleSqlVoList)) {
            return Collections.emptyList();
        }
        List<String> needCourseTableIdList =
                studentScheduleSqlVoList.stream().map(StudentScheduleSqlVo::getCourseTableId).distinct().collect(Collectors.toList());
        startTime = System.currentTimeMillis();
        List<StudentCourseTableSqlVo> needCourseTableList = getCourseTableSqlList(needCourseTableIdList, courseparms);
        endTime = System.currentTimeMillis();
        LOGGER.info("获取课程表SQL列表耗时: {}ms", endTime - startTime);
        List<String> needGroupIdList = needCourseTableList.stream().map(StudentCourseTableSqlVo::getGroupId).distinct().collect(Collectors.toList());
        startTime = System.currentTimeMillis();
        List<GroupMemberStatisticsSqlVo> groupMemberSqlVoList = getGroupMemberStatisticsSqlVoList(needGroupIdList);
        endTime = System.currentTimeMillis();
        LOGGER.info("获取组成员统计信息列表耗时: {}ms", endTime - startTime);
        List<String> courseTableDetailIdList =
                studentScheduleSqlVoList.stream().map(StudentScheduleSqlVo::getCourseTableDetailId).collect(Collectors.toList());
        startTime = System.currentTimeMillis();
        List<CourseTableDetailRoomUser> courseTableDetailRoomUserList =
                courseTableDetailRoomUserRepository.findByCourseTableDetailIdIn(courseTableDetailIdList);
        endTime = System.currentTimeMillis();
        LOGGER.info("获取课程表详细房间列表耗时: {}ms", endTime - startTime);
        startTime = System.currentTimeMillis();
        List<CourseTableDetailTeacher> courseTableDetailTeacherList =
                courseTableDetailTeacherRepository.findByCourseTableDetailIdIn(courseTableDetailIdList);
        endTime = System.currentTimeMillis();
        LOGGER.info("获取课程表详细房间列表耗时: {}ms", endTime - startTime);
        List<CourseTableDetailProject> courseTableDetailProjectList =
                courseTableDetailProjectRepository.findByCourseTableDetailIdIn(courseTableDetailIdList);
        List<CourseTableDate> courseTableDateList = new ArrayList<>();
        List<CourseType> courseTypeList = courseTypeRepository.findAll();
        for (StudentScheduleSqlVo studentScheduleSqlVo : studentScheduleSqlVoList) {
            StudentCourseTableSqlVo studentCourseTableSqlVo = needCourseTableList.stream().filter(c ->
                            c.getCourseTableId().equals(studentScheduleSqlVo.getCourseTableId())).findFirst().orElse(null);
            if (Objects.isNull(studentCourseTableSqlVo)) {
                continue;
            }
            List<CourseTableDetailTeacher> thisCourseTableDetailTeacherList =
                    courseTableDetailTeacherList.stream().filter(c ->
                            c.getCourseTableDetail().getId().equals(studentScheduleSqlVo.getCourseTableDetailId())).collect(Collectors.toList());
            List<CourseTableDetailProject> thisCourseTableDetailProjectList =
                    courseTableDetailProjectList.stream().filter(c ->
                            c.getCourseTableDetail().getId().equals(studentScheduleSqlVo.getCourseTableDetailId())).collect(Collectors.toList());
            List<CourseTableDetailRoomUser> thisCourseTableDetailRoomUserList =
                    courseTableDetailRoomUserList.stream().filter(c ->
                            c.getCourseTableDetail().getId().equals(studentScheduleSqlVo.getCourseTableDetailId())).collect(Collectors.toList());
            GroupMemberStatisticsSqlVo groupMemberStatisticsSqlVo =
                    groupMemberSqlVoList.stream().filter(g ->
                            g.getGroupId().equals(studentCourseTableSqlVo.getGroupId())).findFirst().orElse(null);
            CourseTableDate courseTableDate = new CourseTableDate();
            getCourseTableDateModel(studentScheduleSqlVo, courseTableDate, thisCourseTableDetailProjectList, studentCourseTableSqlVo);
            if (CollectionUtils.isNotEmpty(thisCourseTableDetailTeacherList)) {
                courseTableDate.setTeacherId(thisCourseTableDetailTeacherList.stream()
                        .map(CourseTableDetailTeacher::getTeacherId).collect(Collectors.joining(",")));
                courseTableDate.setTeacherName(thisCourseTableDetailTeacherList.stream()
                        .map(CourseTableDetailTeacher::getTeacherName).collect(Collectors.joining(",")));
            }
            if (CollectionUtils.isNotEmpty(thisCourseTableDetailRoomUserList)) {
                courseTableDate.setRoomId(thisCourseTableDetailRoomUserList.stream()
                        .map(CourseTableDetailRoomUser::getRoomId).collect(Collectors.joining(",")));
                courseTableDate.setRoomName(thisCourseTableDetailRoomUserList.stream()
                        .map(CourseTableDetailRoomUser::getRoomName).collect(Collectors.joining(",")));
            }
            courseTableDate.setStudentCount(Objects.isNull(groupMemberStatisticsSqlVo) ? "0" :
                    String.valueOf(groupMemberStatisticsSqlVo.getStudentCount()));
            CourseType courseType =
                    courseTypeList.stream().filter(c -> c.getId().equals(studentScheduleSqlVo.getCourseType())).findFirst().orElse(null);
            if (Objects.nonNull(courseType)) {
                courseTableDate.setCourseType(courseType.getId());
                courseTableDate.setCourseTypeName(courseType.getCourseTypeName());
            }
            courseTableDateList.add(courseTableDate);
        }
        return courseTableDateList.stream().sorted(Comparator.comparing(CourseTableDate::getCourseDate)
                .thenComparing(CourseTableDate::getStartTime)).collect(Collectors.toList());
    }

    private static void getCourseTableDateModel(
            StudentScheduleSqlVo studentScheduleSqlVo, CourseTableDate courseTableDate,
            List<CourseTableDetailProject> thisCourseTableDetailProjectList, StudentCourseTableSqlVo studentCourseTableSqlVo) {
        courseTableDate.setProjectNames(CollectionUtils.isEmpty(thisCourseTableDetailProjectList) ? null :
                thisCourseTableDetailProjectList.stream().map(CourseTableDetailProject::getProjectName).collect(Collectors.joining(",")));
        courseTableDate.setCourseTableId(studentScheduleSqlVo.getCourseTableId());
        courseTableDate.setStartTime(studentScheduleSqlVo.getSegmentStartTime());
        courseTableDate.setEndTime(studentScheduleSqlVo.getSegmentEndTime());
        courseTableDate.setCourseDate(studentScheduleSqlVo.getCourseDate());
        courseTableDate.setWeekNum(studentScheduleSqlVo.getWeekNum());
        courseTableDate.setCourseName(studentCourseTableSqlVo.getCourseName());
        courseTableDate.setTeachingClassName(studentCourseTableSqlVo.getGroupName());
        courseTableDate.setTeachingClassId(studentCourseTableSqlVo.getGroupId());
        courseTableDate.setCourseAttr(0);
        courseTableDate.setCollegeId(studentCourseTableSqlVo.getCollegeId());
        courseTableDate.setCollegeName(studentCourseTableSqlVo.getCollegeName());
        courseTableDate.setNowWeek(studentScheduleSqlVo.getNowWeek());
        courseTableDate.setWeekType(String.valueOf(studentCourseTableSqlVo.getWeekType()));
        courseTableDate.setClassName(studentCourseTableSqlVo.getGroupName());
        courseTableDate.setCourseCategory(studentCourseTableSqlVo.getCourseCategoryId());
        courseTableDate.setCourseCategoryName(studentCourseTableSqlVo.getCourseCategoryName());
    }

    private List<GroupMemberStatisticsSqlVo> getGroupMemberStatisticsSqlVoList(List<String> needGroupIdList) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "select group_id as groupId,count(1) as studentCount from tb_group_member where group_member_status=0 ";
        if (CollectionUtils.isNotEmpty(needGroupIdList)) {
            sql += " and group_id in ( :groupIdList )";
            paramMap.put("groupIdList", needGroupIdList);
        }
        sql += " GROUP BY group_id ";
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GroupMemberStatisticsSqlVo.class));

        return query.getResultList();

    }

    private List<StudentCourseTableSqlVo> getCourseTableSqlList(List<String> needCourseTableIdList, CourseParms courseParms) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = " SELECT\n" +
                "\tct.id as courseTableId,\n" +
                "\tct.course_Id as courseId,\n" +
                "\tct.course_Name as courseName,\n" +
                "\tct.week_type as weekType,\n" +
                "\tct.college_id as collegeId,\n" +
                "\tct.college_name as collegeName,\n" +
                "\tg.group_name as groupName,\n" +
                "\tg.id as groupId,\n" +
                "\tct.course_category_id as courseCategoryId,\n" +
                "\tcc.course_category_name as courseCategoryName " +
                " FROM tb_course_category cc \n" +
                "  INNER JOIN\ttb_course_table ct on cc.id=ct.course_category_id " +
                "\tINNER JOIN tb_group g ON ct.group_id = g.id " +
                " WHERE g.group_status=0  ";
        if (StringUtils.isNotBlank(courseParms.getSchoolYear())) {
            sql += " and ct.school_year = :schoolYear ";
            paramMap.put("schoolYear", courseParms.getSchoolYear());
        }
        if (Objects.nonNull(courseParms.getTerm())) {
            sql += " and ct.term = :term ";
            paramMap.put("term", courseParms.getTerm());
        }
        if (CollectionUtils.isNotEmpty(needCourseTableIdList)) {
            sql += " and ct.id in (:courseTableIdList) ";
            paramMap.put("courseTableIdList", needCourseTableIdList);
        }
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(StudentCourseTableSqlVo.class));

        return query.getResultList();
    }

    private List<StudentScheduleSqlVo> getTeacherCourseTableDetailDateSqlList(CourseParms courseParms) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = " SELECT\n" +
                "  ctd.id AS courseTableDetailId,\n" +
                "  DATE_FORMAT( ctd.course_date, '%Y-%m-%d' ) AS courseDate,\n" +
                "  DATE_FORMAT( ctd.segment_start_time, '%H:%i:%s' ) AS segmentStartTime,\n" +
                "  DATE_FORMAT( ctd.segment_end_time, '%H:%i:%s' ) AS segmentEndTime,\n" +
                "  0 AS 'courseAttr',\n" +
                "  ctd.course_type_id AS courseType,\n" +
                "  ctd.`week` AS nowWeek,\n" +
                "  ctd.segment AS 'segments',\n" +
                "  ctd.week_num AS 'weekNum',\n" +
                "  ctd.course_table_id AS courseTableId \n" +
                "FROM\n" +
                "  tb_course_table_detail ctd \n" +
                "  INNER JOIN \n" +
                "  tb_course_table_detail_teacher ctdt on ctd.id=ctdt.course_table_detail_id\n" +
                " WHERE\n" +
                "  1 = 1";
        if (StringUtils.isNotBlank(courseParms.getTeacherId())) {
            sql += " and ctdt.teacher_id = :teacherId ";
            paramMap.put("teacherId", courseParms.getTeacherId());
        }
        if (StringUtils.isNotBlank(courseParms.getCourseDate())) {
            sql += " and ctd.course_date= :courseDate";
            paramMap.put("courseDate", courseParms.getCourseDate());
        }
        if (Objects.nonNull(courseParms.getWeek())) {
            sql += " and ctd.week= :week ";
            paramMap.put("week", courseParms.getWeek());
        }
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(StudentScheduleSqlVo.class));

        return query.getResultList();
    }
}

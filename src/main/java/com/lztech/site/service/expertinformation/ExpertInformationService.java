package com.lztech.site.service.expertinformation;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.coursecategory.CourseCategoryRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.coursetabledetail.LiveCourseParam;
import com.lztech.site.viewmodel.expertinformation.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.until.DateUtils.DATE;
import static com.lztech.site.until.DateUtils.formatDate;

@Service
public class ExpertInformationService {
    @Value("${expertListeningEndTime}")
    private String expertListeningEndTime;
    @Resource
    private EntityManager entityManager;
    @Resource
    private CourseCategoryRepository courseCategoryRepository;
    @Resource
    private GroupRepository groupRepository;
    @Resource
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Resource
    private CourseRepository courseRepository;

    public ExpertListenCurriculumPageVo getExpertListenCurriculumPage(LiveCourseParam liveCourseParam) {
        Map<String, Object> paramMap = new HashMap<>();
        String baseSql = getBaseSql(liveCourseParam, paramMap);
        ExpertListenCurriculumPageVo pageVo = new ExpertListenCurriculumPageVo();
        pageVo.setPage(liveCourseParam.getPage());
        pageVo.setPageSize(liveCourseParam.getPageSize());
        pageVo.setPageCount(0);
        pageVo.setTotalCount(0);
        pageVo.setCurriculumList(new ArrayList<>());
        if (StringUtils.isBlank(baseSql)) {
            return pageVo;
        }
        int count = getExpertListenCurriculumCount(baseSql, paramMap);
        if (count != 0) {
            pageVo.setPageCount((int) Math.ceil((double) count /
                    (double) liveCourseParam.getPageSize()));
            pageVo.setTotalCount(count);
            pageVo.setCurriculumList(buildCurriculumList(getExpertListenCurriculumList(baseSql,
                    paramMap, liveCourseParam)));
        }
        return pageVo;

    }

    private List<ExpertListenCurriculumVo> buildCurriculumList(List<ExpertListenCurriculumSqlVo>
                                                                       expertListenCurriculumVoList) {
        List<String> groupIdList = expertListenCurriculumVoList.stream()
                .map(ExpertListenCurriculumSqlVo::getGroupId)
                .distinct()
                .collect(Collectors.toList());
        List<String> courseTableDetailIdList = expertListenCurriculumVoList.stream()
                .map(ExpertListenCurriculumSqlVo::getCourseTableDetailId)
                .distinct()
                .collect(Collectors.toList());
        List<CourseCategory> courseCategoryList = courseCategoryRepository.findAll();
        List<Group> groupList = groupRepository.findByIdIn(groupIdList);
        List<CourseTableDetailTeacher> teacherList = courseTableDetailTeacherRepository
                .findByCourseTableDetailIdIn(courseTableDetailIdList);
        List<String> courseIdList = expertListenCurriculumVoList.stream()
                .map(ExpertListenCurriculumSqlVo::getCourseId)
                .distinct()
                .collect(Collectors.toList());
        List<Course> courseList = courseRepository.findByIdIn(courseIdList);
        List<ExpertListenCurriculumVo> resultList = new ArrayList<>();
        for (ExpertListenCurriculumSqlVo expertListenCurriculumSqlVo : expertListenCurriculumVoList) {
            Course course = courseList.stream().filter(c -> c.getId().equals(expertListenCurriculumSqlVo.getCourseId())).findFirst().orElse(null);
            if (ObjectUtils.isEmpty(course)) {
                continue;
            }
            Group group = groupList.stream().filter(g -> g.getId().equals(expertListenCurriculumSqlVo.getGroupId())).findFirst().orElse(null);
            if (ObjectUtils.isEmpty(group)) {
                continue;
            }
            CourseCategory courseCategory = courseCategoryList.stream()
                    .filter(c -> c.getId().equals(expertListenCurriculumSqlVo.getCourseCategoryId()))
                    .findFirst()
                    .orElse(null);

            ExpertListenCurriculumVo expertListenCurriculumVo = new ExpertListenCurriculumVo();
            expertListenCurriculumVo.setCourseTableDetailId(expertListenCurriculumSqlVo.getCourseTableDetailId());
            expertListenCurriculumVo.setCourseTableId(expertListenCurriculumSqlVo.getCourseTableId());
            expertListenCurriculumVo.setRoomId(expertListenCurriculumSqlVo.getRoomId());
            expertListenCurriculumVo.setCourseTableCollegeName(expertListenCurriculumSqlVo.getCourseTableCollegeName());
            expertListenCurriculumVo.setCourseTableCollegeId(expertListenCurriculumSqlVo.getCourseTableCollegeId());
            expertListenCurriculumVo.setMajorName(group.getMajorComposition());
            expertListenCurriculumVo.setTeacherInfoList(buildCurriculumTeacherList(teacherList,
                    expertListenCurriculumSqlVo.getCourseTableDetailId()));
            expertListenCurriculumVo.setCourseName(expertListenCurriculumSqlVo.getCourseName());
            expertListenCurriculumVo.setCourseId(expertListenCurriculumSqlVo.getCourseId());
            expertListenCurriculumVo.setCourseCode(course.getCourseCode());
            expertListenCurriculumVo.setCourseAttrName(Objects.nonNull(courseCategory) ? courseCategory.getCourseCategoryName() : "其他");
            expertListenCurriculumVo.setCourseDate(expertListenCurriculumSqlVo.getCourseDate());
            expertListenCurriculumVo.setSegmentStartTime(expertListenCurriculumSqlVo.getStartTime());
            expertListenCurriculumVo.setSegmentEndTime(expertListenCurriculumSqlVo.getEndTime());
            expertListenCurriculumVo.setSegment(String.valueOf(expertListenCurriculumSqlVo.getSegment()));
            expertListenCurriculumVo.setHasLiveBroadcast(expertListenCurriculumSqlVo.getHasLiveBroadcast());
            resultList.add(expertListenCurriculumVo);
        }
        return resultList;
    }

    private List<TeacherInfo> buildCurriculumTeacherList(List<CourseTableDetailTeacher> teacherList,
                                                         String courseTableDetailId) {
        List<CourseTableDetailTeacher> thisTeacherList =
                teacherList.stream().filter(t -> t.getCourseTableDetail().getId().equals(courseTableDetailId)).collect(Collectors.toList());
        return thisTeacherList.stream().map(t -> {
            TeacherInfo teacherInfo = new TeacherInfo();
            teacherInfo.setTeacherId(t.getTeacherId());
            teacherInfo.setTeacherName(t.getTeacherName());
            teacherInfo.setTeacherNo(t.getTeacherNo());
            teacherInfo.setTeacherTitle(t.getTeacherTitle());
            return teacherInfo;
        }).collect(Collectors.toList());
    }

    private List<ExpertListenCurriculumSqlVo> getExpertListenCurriculumList(String baseSql, Map<String, Object> paramMap,
                                                                            LiveCourseParam liveCourseParam) {
        String sql = "select * from (" + baseSql + ") t order by courseDate desc,startTime desc, courseTableId";
        int start = (liveCourseParam.getPage() - 1) * liveCourseParam.getPageSize();
        int size = liveCourseParam.getPageSize();
        sql += " limit " + start + "," + size;
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ExpertListenCurriculumSqlVo.class));
        return query.getResultList();
    }

    private int getExpertListenCurriculumCount(String baseSql, Map<String, Object> paramMap) {
        String countSql = "select count(1) as allNumber from ( " + baseSql + " ) t";
        Query queryCount = entityManager.createNativeQuery(countSql);
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        return ((BigInteger) resMap.get("allNumber")).intValue();
    }

    private String getBaseSql(LiveCourseParam liveCourseParam,
                              Map<String, Object> paramMap) {
        String sql = " ";
        Date nowDate = new Date();
        String startTime = DateUtils.formatDate(DateUtils.TIME, nowDate);
        LocalDateTime segmentEndTime = LocalDateTime.parse(formatDate(DATE, new Date()) + " "
                + startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        startTime = segmentEndTime.plusMinutes(Long.parseLong(expertListeningEndTime)).format(df);
        String endTime = DateUtils.formatDate(DateUtils.TIME, nowDate);
        String date = DateUtils.formatDate(DateUtils.DATE, nowDate);
        if (AuthorizationScopeType.LIVE_COURSES.getValue().equals(liveCourseParam.getAuthorizationScopeType())) {
            sql = getLivingCourseTableSql(liveCourseParam, paramMap, date, startTime, endTime);
        } else if (AuthorizationScopeType.ON_DEMAND_COURSES.getValue().equals(liveCourseParam.getAuthorizationScopeType())) {
            sql = getRecordCourseTableSql(liveCourseParam, paramMap, date);
        } else if (AuthorizationScopeType.All_COURSES.getValue().equals(liveCourseParam.getAuthorizationScopeType())) {
            sql = "(" + getLivingCourseTableSql(liveCourseParam, paramMap, date, startTime, endTime) + ") union (" +
                    getRecordCourseTableSql(liveCourseParam, paramMap, date) + ")";
        }
        return sql;
    }

    private String getRecordCourseTableSql(LiveCourseParam liveCourseParam,
                                           Map<String, Object> paramMap, String date) {
        String recordSql = "select ctd.id  as courseTableDetailId, ct.id as courseTableId,ct.course_id as courseId,ct.course_name as courseName,\n" +
                " ct.college_id as courseTableCollegeId,ct.college_name as courseTableCollegeName,ct.course_category_id  as courseCategoryId,ct" +
                ".group_id as groupId,CAST( ctd.course_date AS CHAR )   as courseDate,\n" +
                "   cs.segment   as segment,group_concat(ctdru.room_id order by ctdru.room_id)   as roomId,\n" +
                "   group_concat(ctdru.room_name order by ctdru.room_id) as roomName,\n" +
                "   CAST( s.start_time AS CHAR )  as startTime,CAST( s.end_time AS CHAR ) as endTime,'0' as hasLiveBroadcast\n" +
                " from  " +
                "  tb_course_table ct inner join tb_term t on ct.school_year=t.school_year and ct.term = t.term  " +
                " INNER JOIN tb_course_table_detail ctd on ctd.course_table_id = ct.id\n" +
                " inner join tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id" +

                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id\n" +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id\n" +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment\n" +
                " where ctd.source = 0  and ctd.course_date < :courseDate " +
                " and ctdru.has_live = 1 and (ctdru.room_id is not null or ctdru.room_id != '')";
        paramMap.put("courseDate", date);
        if (StringUtils.isNotBlank(liveCourseParam.getTermIds())) {
            List<String> termIds = Arrays.asList(liveCourseParam.getTermIds().split(","));
            recordSql += " and t.id in (:termIds) ";
            paramMap.put("termIds", termIds);
        }
        if (StringUtils.isNotBlank(liveCourseParam.getSchoolYear()) && Objects.nonNull(liveCourseParam.getTerm())) {
            recordSql += " and ct.school_year=:schoolYear and term= :term ";
            paramMap.put("schoolYear", liveCourseParam.getSchoolYear());
            paramMap.put("term", liveCourseParam.getTerm());

        }
        if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getCourseName())) {
            recordSql += " and ( ct.course_name like :queryParam or ctdt.teacher_name like :queryParam or ctdt.teacher_no like :queryParam )";
            paramMap.put("queryParam", "%" + liveCourseParam.getCourseName() + "%");
        }
        if (CollectionUtils.isNotEmpty(liveCourseParam.getCourseTableCustomIdList())) {
            recordSql += " and CONCAT(ctd.course_table_id,ctd.course_date,ctd.segment,cs.segment) in (:courseTableCustomIdList) ";
            paramMap.put("courseTableCustomIdList", liveCourseParam.getCourseTableCustomIdList());
        }
        if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getStudentType())) {
            recordSql += " and ct.student_type = :studentType ";
            paramMap.put("studentType", liveCourseParam.getStudentType());
        }
        recordSql += " group by cs.id order by ctd.course_date desc, s.start_time desc, ct.id";
        return recordSql;

    }

    private String getLivingCourseTableSql(LiveCourseParam liveCourseParam,
                                           Map<String, Object> paramMap,
                                           String date,
                                           String segmentStartTime,
                                           String segmentEndTime) {
        String livingSql = "select ctd.id  as courseTableDetailId, ct.id as courseTableId,ct.course_id as courseId,ct.course_name as courseName," +
                " ct.college_id as courseTableCollegeId,ct.college_name as courseTableCollegeName,ct.course_category_id  as courseCategoryId,ct" +
                ".group_id as groupId,CAST( ctd.course_date AS CHAR )   as courseDate,\n" +
                "   cs.segment as segment,group_concat(ctdru.room_id order by ctdru.room_id)   as roomId,\n" +
                "   group_concat(ctdru.room_name order by ctdru.room_id) as roomName,\n" +
                "   CAST( s.start_time AS CHAR )  as startTime,CAST( s.end_time AS CHAR ) as endTime,'1' as hasLiveBroadcast\n" +
                " from  tb_course_table ct inner join tb_term t on ct.school_year=t.school_year and ct.term = t.term " +
                " INNER JOIN tb_course_table_detail ctd on ctd.course_table_id = ct.id\n" +
                " inner join tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id" +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id\n" +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id\n" +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment\n" +
                " where ctd.source = 0 and ctdru.has_live = 1 " +
                " and ctd.course_date = :courseDate  and (ctdru.room_id is not null or ctdru.room_id != '')\n" +
                " and s.start_time <= :startTime and s.end_time > :endTime ";

        paramMap.put("courseDate", date);
        paramMap.put("startTime", segmentStartTime);
        paramMap.put("endTime", segmentEndTime);

        if (StringUtils.isNotBlank(liveCourseParam.getTermIds())) {
            List<String> termIds = Arrays.asList(liveCourseParam.getTermIds().split(","));
            livingSql += " and t.id in (:termIds) ";
            paramMap.put("termIds", termIds);
        }

        if (StringUtils.isNotBlank(liveCourseParam.getSchoolYear()) && Objects.nonNull(liveCourseParam.getTerm())) {
            livingSql += " and ct.school_year=:schoolYear and term= :term ";
            paramMap.put("schoolYear", liveCourseParam.getSchoolYear());
            paramMap.put("term", liveCourseParam.getTerm());

        }
        if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getCourseName())) {
            livingSql += " and ( ct.course_name like :queryParam or ctdt.teacher_name like :queryParam or ctdt.teacher_no like :queryParam )";
            paramMap.put("queryParam", "%" + liveCourseParam.getCourseName() + "%");
        }
        if (CollectionUtils.isNotEmpty(liveCourseParam.getCourseTableCustomIdList())) {
            livingSql += " and CONCAT(ctd.course_table_id,ctd.course_date,ctd.segment,cs.segment) in (:courseTableCustomIdList) ";
            paramMap.put("courseTableCustomIdList", liveCourseParam.getCourseTableCustomIdList());
        }
        if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getStudentType())) {
            livingSql += " and ct.student_type = :studentType ";
            paramMap.put("studentType", liveCourseParam.getStudentType());
        }
        livingSql += " group by cs.id order by ctd.course_date desc, s.start_time desc, ct.id";
        return livingSql;
    }
}

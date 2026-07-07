package com.lztech.site.service.coursetabledetail;

import cn.hutool.core.date.DateUtil;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.domain.model.segment.Segment;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.persistence.repositories.segment.SegmentRepository;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.coursetable.CourseTableDetailResourceVo;
import com.lztech.site.viewmodel.coursetable.StudentResourceVo;
import com.lztech.site.viewmodel.coursetable.TeacherResourceVo;
import com.lztech.site.viewmodel.coursetabledetail.*;
import com.lztech.site.viewmodel.evaluation.PolymerizeCourseInfoModel;
import com.lztech.site.viewmodel.evaluation.PolymerizeCourseInfoParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.coursetabledetail.specification.CourseTableDetailSpecification.getWaitAttendClassCourseTableDetailSpecification;
import static com.lztech.persistence.repositories.coursetabledetail.specification.CourseTableDetailSpecification.specification;
import static com.lztech.site.until.DateUtils.TIME_SECOND;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class CourseTableDetailLogicService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    public CurrentTimeStatisticsModel getCurrentTimeStatistics(CurrentTimeStatisticsParam currentTimeStatisticsParam) {
        CurrentTimeStatisticsModel currentTimeStatisticsModel = new CurrentTimeStatisticsModel();
        Date nowDate = new Date();
        List<String> roomIdList = currentTimeStatisticsParam.getRoomIdList();
        if (CollectionUtils.isNotEmpty(currentTimeStatisticsParam.getRoomIdList())) {
            roomIdList = roomIdList.stream().filter(ObjectUtils::isNotEmpty).distinct().collect(toList());
        }
        currentTimeStatisticsModel.setCourseDate(DateUtil.format(nowDate, "yyyy-MM-dd"));
        List<Segment> segmentList = segmentRepository.findByBuildName("0");
        Segment segment = segmentList.stream().filter(filterSegment -> DateUtil.compare(nowDate, DateUtil.parse(
                currentTimeStatisticsModel.getCourseDate() + " " +
                        filterSegment.getStartTime(), "yyyy-MM-dd HH:mm")) >= 0 &&
                DateUtil.compare(nowDate, DateUtil.parse(currentTimeStatisticsModel.getCourseDate() + " " +
                        filterSegment.getEndTime(), "yyyy-MM-dd HH:mm")) <= 0).findFirst().orElse(null);
        if (ObjectUtils.isNotEmpty(segment)) {
            String querySql = "select a.id from tb_course_table_detail a  " +
                    " INNER JOIN tb_course_table b on a.course_table_id = b.id ";
            if (CollectionUtils.isNotEmpty(roomIdList)) {
                querySql = querySql + " INNER JOIN tb_course_table_detail_room_user c on c.course_table_detail_id = a.id ";
            }
            querySql = querySql +
                    " where  a.course_date = :courseDate and " +
                    " a.segment_start_time<= :nowTime " +
                    " and  a.segment_end_time>= :nowTime  ";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("courseDate", currentTimeStatisticsModel.getCourseDate());
            paramMap.put("nowTime", DateUtils.formatDate(TIME_SECOND, nowDate));
            querySql = getQuerySql(currentTimeStatisticsParam, querySql, paramMap);
            Query query = entityManager.createNativeQuery(querySql);
            paramMap.forEach(query::setParameter);
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List<Map<String, Object>> resultList = query.getResultList();
            if (CollectionUtils.isNotEmpty(resultList)) {
                currentTimeStatisticsModel.setIsInClassCourseTable(1);
                currentTimeStatisticsModel.setInClassCourseTableSegment(segment.getSegment());
                currentTimeStatisticsModel.setInClassCourseTableSegmentStartTime(
                        currentTimeStatisticsModel.getCourseDate() + " " + segment.getStartTime() + ":00");
                currentTimeStatisticsModel.setInClassCourseTableSegmentEndTime(
                        currentTimeStatisticsModel.getCourseDate() + " " + segment.getEndTime() + ":00");
                currentTimeStatisticsModel.setInClassCourseTableDetailCount(resultList.size());
                List<String> courseTableDetailIdList = resultList.stream().map(map -> map.get("id").toString()).collect(toList());
                if (CollectionUtils.isNotEmpty(courseTableDetailIdList)) {
                    currentTimeStatisticsModel.setCourseTableDetailIdList(courseTableDetailIdList);
                } else {
                    currentTimeStatisticsModel.setCourseTableDetailIdList(new ArrayList<>());
                }
            } else {
                saveCurrentTimeStatisticsModel(currentTimeStatisticsModel, nowDate, currentTimeStatisticsParam,
                        roomIdList);
            }
        } else {
            saveCurrentTimeStatisticsModel(currentTimeStatisticsModel, nowDate, currentTimeStatisticsParam, roomIdList);
        }
        return currentTimeStatisticsModel;
    }

    private static String getQuerySql(
            CurrentTimeStatisticsParam currentTimeStatisticsParam, String querySql, Map<String, Object> paramMap) {
        if (Objects.equals(currentTimeStatisticsParam.getIsDistinguishCourseStudentType(), "1")) {
            List<Integer> studentTypeIds = new ArrayList<>();
            if (!StringUtils.isBlank(currentTimeStatisticsParam.getCourseStudentTypeIds())) {
                studentTypeIds = Arrays.stream(currentTimeStatisticsParam.getCourseStudentTypeIds().split(","))
                        .map(Integer::valueOf).collect(toList());
            } else {
                studentTypeIds.add(-1);
            }
            querySql += " and b.student_type in (:studentTypeIds) ";
            paramMap.put("studentTypeIds", studentTypeIds);
        }
        List<String> courseTableCollegeIdList = currentTimeStatisticsParam.getCourseTableCollegeIdList();
        List<String> courseIdList = currentTimeStatisticsParam.getCourseIdList();
        List<String> roomIdList = currentTimeStatisticsParam.getRoomIdList();
        if (CollectionUtils.isNotEmpty(courseTableCollegeIdList) || CollectionUtils.isNotEmpty(courseIdList) ||
                CollectionUtils.isNotEmpty(roomIdList)) {
            querySql = querySql + " and (";
            int index = 0;
            if (CollectionUtils.isNotEmpty(courseTableCollegeIdList)) {
                querySql = querySql + "b.college_id in(:courseTableCollegeIdList) ";
                paramMap.put("courseTableCollegeIdList", courseTableCollegeIdList);
                index = index + 1;
            }
            if (CollectionUtils.isNotEmpty(courseIdList) && index == 0) {
                querySql = querySql + "  b.course_id in(:courseIdList) ";
                paramMap.put("courseIdList", courseIdList);
                index = index + 1;
            } else if (CollectionUtils.isNotEmpty(courseIdList) && index != 0) {
                querySql = querySql + " or b.course_id in(:courseIdList) ";
                paramMap.put("courseIdList", courseIdList);
                index = index + 1;
            }
            if (CollectionUtils.isNotEmpty(roomIdList) && index == 0) {
                querySql = querySql + "  c.room_id in(:roomIdList) ";
                paramMap.put("roomIdList", roomIdList);
            } else if (CollectionUtils.isNotEmpty(roomIdList) && index != 0) {
                querySql = querySql + " or  c.room_id in(:roomIdList) ";
                paramMap.put("roomIdList", roomIdList);
            }
            querySql = querySql + ") ";
        }
        return querySql;
    }

    private void saveCurrentTimeStatisticsModel(
            CurrentTimeStatisticsModel currentTimeStatisticsModel,
            Date nowDate,
            CurrentTimeStatisticsParam currentTimeStatisticsParam,
            List<String> roomIdList
    ) {
        currentTimeStatisticsModel.setIsInClassCourseTable(0);
        String twoQuerySql = "select a.id  from tb_course_table_detail a  " +
                " INNER JOIN tb_course_table b on a.course_table_id = b.id ";
        if (CollectionUtils.isNotEmpty(roomIdList)) {
            twoQuerySql = twoQuerySql + " INNER JOIN tb_course_table_detail_room_user c " +
                    " on c.course_table_detail_id = a.id ";
        }
        twoQuerySql = twoQuerySql + " where  a.course_date = :courseDate and " +
                " a.segment_start_time <= :nowTime  ";
        Map<String, Object> twoParamMap = new HashMap<>();
        twoParamMap.put("courseDate", currentTimeStatisticsModel.getCourseDate());
        twoParamMap.put("nowTime", DateUtils.formatDate(TIME_SECOND, nowDate));
        twoQuerySql = getQuerySql(currentTimeStatisticsParam, twoQuerySql, twoParamMap);
        Query twoQuery = entityManager.createNativeQuery(twoQuerySql);
        twoParamMap.forEach(twoQuery::setParameter);
        twoQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> twoResultList = twoQuery.getResultList();
        currentTimeStatisticsModel.setHistoryCourseTableDetailCount(twoResultList.size());
        List<String> courseTableDetailIdList = twoResultList.stream().map(map -> map.get("id").toString()).collect(toList());
        if (CollectionUtils.isNotEmpty(courseTableDetailIdList)) {
            currentTimeStatisticsModel.setCourseTableDetailIdList(courseTableDetailIdList);
        } else {
            currentTimeStatisticsModel.setCourseTableDetailIdList(new ArrayList<>());
        }
    }

    public List<CourseTableDetailResourceVo> getCourseTableDetailResourceVoList(String roomIds, String sources, String courseDate) {
        List<CourseTableDetailResourceVo> resourceVoList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository.findAll(specification(roomIds, sources, courseDate));
        List<Group> groupList = courseTableDetailList.stream()
                .map(CourseTableDetail::getCourseTable).map(CourseTable::getGroup).collect(toList());
        List<GroupMember> groupMembers = groupMemberRepository.findByGroupIsIn(groupList);
        courseTableDetailList.forEach(detail -> {
            CourseTableDetailResourceVo courseTableDetailResourceVo = new CourseTableDetailResourceVo();
            courseTableDetailResourceVo.setId(detail.getId());
            courseTableDetailResourceVo.setCourseDate(courseDate);
            courseTableDetailResourceVo.setRoomIds(
                    detail.getCourseTableDetailRoomUserList().stream().map(CourseTableDetailRoomUser::getRoomId).collect(joining()));
            courseTableDetailResourceVo.setSegments(detail.getSegment());
            courseTableDetailResourceVo.setSegmentStartTime(simpleDateFormat.format(detail.getSegmentStartTime()));
            courseTableDetailResourceVo.setSegmentEndTime(simpleDateFormat.format(detail.getSegmentEndTime()));
            CourseTable courseTable = detail.getCourseTable();
            List<TeacherResourceVo> teacherResourceVos = new ArrayList<>();
            detail.getCourseTableDetailTeacherList().forEach(teacher -> {
                TeacherResourceVo teacherResourceVo = new TeacherResourceVo();
                teacherResourceVo.setTeacherId(teacher.getTeacherId());
                teacherResourceVo.setTeacherNo(teacher.getTeacherNo());
                teacherResourceVo.setTeacherName(teacher.getTeacherName());
                teacherResourceVos.add(teacherResourceVo);
            });
            courseTableDetailResourceVo.setTeacherResourceList(teacherResourceVos);
            List<StudentResourceVo> studentResourceList = new ArrayList<>();
            List<GroupMember> groupMemberList = groupMembers.stream().filter(groupMember ->
                    groupMember.getGroup().equals(courseTable.getGroup())).collect(toList());
            groupMemberList.forEach(student -> {
                StudentResourceVo studentResourceVo = new StudentResourceVo();
                studentResourceVo.setStudentId(student.getStudentId());
                studentResourceVo.setStudentNo(student.getStudentNo());
                studentResourceVo.setStudentName(student.getStudentName());
                studentResourceList.add(studentResourceVo);
            });
            courseTableDetailResourceVo.setStudentResourceList(studentResourceList);
            resourceVoList.add(courseTableDetailResourceVo);
        });
        return resourceVoList;
    }

    public List<PolymerizeCourseInfoModel> getPolymerizeCourseInfoList(PolymerizeCourseInfoParam polymerizeCourseInfoParam) {
        List<String> collegeIdList = Arrays.stream(polymerizeCourseInfoParam.getCollegeIds().split(","))
                .filter(collegeId -> !StringUtils.isEmpty(collegeId)).collect(toList());
        List<String> termIdList = new ArrayList<>();
        polymerizeCourseInfoParam.getTermBaseModelList().forEach(termBaseModel ->
                termIdList.add(termBaseModel.getSchoolYear() + "-" + termBaseModel.getTerm()));
        List<String> studentTypeList = Arrays.stream(polymerizeCourseInfoParam.getStudentTypes().split(","))
                .filter(studentType -> !StringUtils.isEmpty(studentType)).collect(toList());
        Map<String, Object> paramMap = new HashMap<>();

        String querySql = "SELECT CONCAT(a.school_year,'-',a.term) as termId, a.school_year AS schoolYear, a.term AS term, b.id AS courseId, " +
                " b.course_code AS courseCode, b.course_name AS courseName, " +
                " c.id AS collegeId, c.college_code AS collegeCode, c.college_name AS collegeName " +
                " FROM tb_course_table a INNER JOIN tb_course b ON a.course_id = b.id " +
                " INNER JOIN tb_college c ON a.college_id = c.id " +
                " where CONCAT(a.school_year,'-',a.term) in ( :termIdList) and a.college_id in (:collegeIdList) ";

        if (CollectionUtils.isNotEmpty(studentTypeList)) {
            querySql = querySql + " and a.student_type in (:studentTypeList) ";
            paramMap.put("studentTypeList", studentTypeList);
        }

        querySql = querySql + " group by CONCAT(a.school_year,'-',a.term),a.school_year,a.term,a.course_id," +
                "a.college_id,c.college_name,b.course_name " +
                " order by CONCAT(a.school_year,'-',a.term) DESC ,CONVERT( c.college_name USING gbk ) COLLATE gbk_chinese_ci ASC," +
                "CONVERT( b.course_name USING gbk ) COLLATE gbk_chinese_ci ASC,b.course_code ASC;";

        paramMap.put("termIdList", termIdList);
        paramMap.put("collegeIdList", collegeIdList);
        Query query = entityManager.createNativeQuery(querySql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(PolymerizeCourseInfoModel.class));
        paramMap.forEach(query::setParameter);
        return (List<PolymerizeCourseInfoModel>) query.getResultList();
    }

    public List<CourseTableDetailResourceModel> getWaitAttendClassCourseTableDetails(
            WaitAttendClassCourseTableDetailParam waitAttendClassCourseTableDetailParam) {
        List<CourseTableDetailResourceModel> resourceVoList = new ArrayList<>();
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository.findAll(
                getWaitAttendClassCourseTableDetailSpecification(waitAttendClassCourseTableDetailParam));
        courseTableDetailList.forEach(detail -> {
            CourseTableDetailResourceModel courseTableDetailResourceModel = new CourseTableDetailResourceModel();
            courseTableDetailResourceModel.setCourseTableDetailId(detail.getId());
            courseTableDetailResourceModel.setSegments(detail.getSegment());
            Course course = detail.getCourseTable().getCourse();
            courseTableDetailResourceModel.setCourseId(course.getId());
            courseTableDetailResourceModel.setCourseCode(course.getCourseCode());
            courseTableDetailResourceModel.setCourseName(course.getCourseName());
            courseTableDetailResourceModel.setCourseDate(waitAttendClassCourseTableDetailParam.getCourseDate());

            List<CourseTableDetailRoomUser> courseTableDetailRoomUserList = detail.getCourseTableDetailRoomUserList();

            String courseTableDetailRoomIds = courseTableDetailRoomUserList.stream().map(CourseTableDetailRoomUser::getRoomId)
                    .distinct().collect(Collectors.joining(","));

            String courseTableDetailRoomNames = courseTableDetailRoomUserList.stream().map(CourseTableDetailRoomUser::getRoomName)
                    .distinct().collect(Collectors.joining(","));

            courseTableDetailResourceModel.setRoomIds(courseTableDetailRoomIds);
            courseTableDetailResourceModel.setRoomNames(courseTableDetailRoomNames);

            String segmentStartTimeCharacter = DateUtils.formatDate(DateUtils.DATE, detail.getCourseDate())
                    + " " + DateUtils.formatDate(DateUtils.TIME_SECOND, detail.getSegmentStartTime());

            String segmentEndTimeCharacter = DateUtils.formatDate(DateUtils.DATE, detail.getCourseDate())
                    + " " + DateUtils.formatDate(DateUtils.TIME_SECOND, detail.getSegmentEndTime());
            courseTableDetailResourceModel.setSegmentStartTime(segmentStartTimeCharacter);
            courseTableDetailResourceModel.setSegmentEndTime(segmentEndTimeCharacter);
            CourseTable courseTable = detail.getCourseTable();
            courseTableDetailResourceModel.setSchoolYear(courseTable.getSchoolYear());
            courseTableDetailResourceModel.setTerm(courseTable.getTerm());
            courseTableDetailResourceModel.setCourseTableCollegeId(courseTable.getCollegeId());
            courseTableDetailResourceModel.setCourseTableCollegeName(courseTable.getCollegeName());
            Group group = courseTable.getGroup();
            courseTableDetailResourceModel.setGroupId(group.getId());
            courseTableDetailResourceModel.setGroupName(group.getGroupName());
            List<GroupMember> groupMemberList = group.getGroupMemberList().stream().filter(
                    groupMember -> groupMember.getGroupMemberStatus().equals(GroupMemberStatus.NORMAL)
            ).collect(toList());

            courseTableDetailResourceModel.setGroupStudentCount(CollectionUtils.isEmpty(groupMemberList) ? 0 : groupMemberList.size());
            List<CourseTableDetailTeacher> courseTableDetailTeacherList = detail.getCourseTableDetailTeacherList();
            String teacherIds = courseTableDetailTeacherList.stream()
                    .map(CourseTableDetailTeacher::getTeacherId).distinct().collect(joining(","));
            String teacherNos = courseTableDetailTeacherList.stream()
                    .map(CourseTableDetailTeacher::getTeacherNo).distinct().collect(joining(","));
            String teacherNames = courseTableDetailTeacherList.stream()
                    .map(CourseTableDetailTeacher::getTeacherName).distinct().collect(joining(","));
            courseTableDetailResourceModel.setTeacherIds(teacherIds);
            courseTableDetailResourceModel.setTeacherNos(teacherNos);
            courseTableDetailResourceModel.setTeacherNames(teacherNames);
            resourceVoList.add(courseTableDetailResourceModel);
        });
        Date newDate = new Date();
        List<CourseTableDetailResourceModel> handleCourseTableDetailResourceModelList = new ArrayList<>();
        for (CourseTableDetailResourceModel courseTableDetailResourceModel : resourceVoList) {
            Date segmentStartTime =
                    DateUtils.stringToDate(DateUtils.DATE_TIME, courseTableDetailResourceModel.getSegmentStartTime());
            if (segmentStartTime.after(newDate)) {
                handleCourseTableDetailResourceModelList.add(courseTableDetailResourceModel);
            }
        }
        return handleCourseTableDetailResourceModelList.stream()
                .sorted(Comparator.comparing(CourseTableDetailResourceModel::getSegmentStartTime)
                        .thenComparing(CourseTableDetailResourceModel::getRoomIds)).collect(Collectors.toList());
    }

    public List<CourseTableTerm> getCourseTableTermList(String courseId, String userId) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = "SELECT DISTINCT d.id as id, d.school_year as schoolYear, CONVERT(d.term,CHAR) as term, " +
                "  date_format(d.start_date, '%Y-%m-%d') as startDate, date_format(d.end_date, '%Y-%m-%d')  as endDate, " +
                "  d.description as description,d.nick_name as schoolYearTermNickName  FROM tb_course_table_detail_teacher a " +
                "  INNER JOIN tb_course_table_detail b ON a.course_table_detail_id = b.id " +
                "  INNER JOIN tb_course_table c ON b.course_table_id = c.id " +
                "  INNER JOIN tb_term d ON d.school_year = c.school_year  " +
                "  AND d.term = c.term  " +
                "WHERE (a.teacher_id =:userId or c.course_table_teacher_id like :userIdLike)  AND c.course_id =:courseId order by d.start_date ";
        paramMap.put("userId", userId);
        paramMap.put("courseId", courseId);
        paramMap.put("userIdLike", "%" + userId + "%");
        Query query = entityManager.createNativeQuery(querySql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableTerm.class));
        paramMap.forEach(query::setParameter);
        return (List<CourseTableTerm>) query.getResultList();
    }
}

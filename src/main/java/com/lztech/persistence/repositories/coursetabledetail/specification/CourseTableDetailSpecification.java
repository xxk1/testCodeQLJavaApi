package com.lztech.persistence.repositories.coursetabledetail.specification;

import com.lztech.domain.model.course.CourseExpansion;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetail.enums.CourseKind;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.segment.Segment;
import com.lztech.domain.model.segment.SegmentPK;
import com.lztech.site.constants.Constant;
import com.lztech.site.resource.administrativeclass.AdministrativeVo;
import com.lztech.site.resource.coursetable.CourseTableDetailParam;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailData;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.coursetabledetail.WaitAttendClassCourseTableDetailParam;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.lztech.site.constants.Constant.NEED_DISTINGUISH_COURSE_STUDENT_TYPE;
import static com.lztech.site.constants.Constant.SUPERVISE_COLLEGE_TYPE_COURSE_TABLE_COLLEGE;
import static com.lztech.site.until.CharactersReplace.replaceCharacters;
import static com.lztech.site.until.TimeUtils.getNowDate;

public class CourseTableDetailSpecification {
    public static Specification<CourseTableDetail> getTeacherClassTimeSpecification(String teacherId, String courseId,
                                                                                    String schoolYear, Integer term) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> join = root.join("courseTable", JoinType.LEFT);

            predicateList.add(cb.and(cb.equal(join.get("schoolYear").as(String.class), schoolYear),
                    cb.equal(join.get("term").as(Integer.class), term)));


            if (!StringUtils.isEmpty(teacherId)) {
                predicateList.add(cb.equal(root.join("courseTableDetailTeacherList", JoinType.LEFT).get("teacherId").as(String.class), teacherId));
            }

            if (!StringUtils.isEmpty(courseId)) {
                predicateList.add(cb.equal(join.join("course", JoinType.LEFT).get("id").as(String.class), courseId));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseTableDetail> getTeacherClassIdClassTimeSpecification(String teacherId, String courseId,
                                                                                           String classId, String schoolYear, Integer term) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> join = root.join("courseTable", JoinType.LEFT);

            if (!StringUtils.isEmpty(courseId)) {
                predicateList.add(cb.equal(join.join("course", JoinType.LEFT).get("id").as(String.class), courseId));
            }

            if (!StringUtils.isEmpty(teacherId)) {
                predicateList.add(cb.equal(root.join("courseTableDetailTeacherList", JoinType.LEFT).get("teacherId").as(String.class), teacherId));
            }

            predicateList.add(cb.and(cb.equal(join.get("schoolYear").as(String.class), schoolYear),
                    cb.equal(join.get("term").as(Integer.class), term)));

            if (!StringUtils.isEmpty(classId)) {
                predicateList.add(cb.equal(join.join("group", JoinType.LEFT).get("id"), classId));
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }


    public static Specification<CourseTableDetail> getTeacherCourseSpecification(String teacherId, String courseId) {
        return (root, query, cb) -> {

            Join<Object, Object> join = root.join("courseTable", JoinType.LEFT);
            List<Predicate> predicateList = new ArrayList<>();


            if (!StringUtils.isEmpty(teacherId)) {
                predicateList.add(cb.equal(root.join("courseTableDetailTeacherList", JoinType.LEFT).get("teacherId").as(String.class), teacherId));
            }

            if (!StringUtils.isEmpty(courseId)) {
                predicateList.add(cb.equal(join.join("course", JoinType.LEFT).get("id").as(String.class), courseId));
            }


            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.desc(root.get("courseDate")));
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseTableDetail> getStudentClassTimes(String courseId, String studentId,
                                                                        String schoolYear, Integer term, String openId) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> join = root.join("courseTable", JoinType.LEFT);

            predicateList.add(cb.and(cb.equal(join.get("schoolYear").as(String.class), schoolYear),
                    cb.equal(join.get("term").as(Integer.class), term)));


            if (org.apache.commons.lang3.StringUtils.isNotBlank(studentId)) {
                predicateList.add(cb.equal(join.join("group", JoinType.INNER).join("groupMemberList", JoinType.INNER).
                        get("studentId").as(String.class), studentId));
            } else if (org.apache.commons.lang3.StringUtils.isNotBlank(openId)) {
                predicateList.add(cb.equal(join.join("group", JoinType.INNER).join("groupMemberList", JoinType.INNER).
                        get("openId").as(String.class), openId));
            }

            if (!StringUtils.isEmpty(courseId)) {
                predicateList.add(cb.equal(join.join("course", JoinType.LEFT).get("id").as(String.class), courseId));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }


    public static Specification<CourseTableDetail> courseSegmentSpecification(CourseTableDetailParam courseTableDetailParam) {

        return (Root<CourseTableDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> courseTableJoin = root.join("courseTable", JoinType.LEFT);
            Join<Object, Object> courseSegmentJoin = root.join("courseSegmentList", JoinType.LEFT);
            Join<Object, Object> roomUserJoin = root.join("courseTableDetailRoomUserList", JoinType.LEFT);
            if (!StringUtils.isEmpty(courseTableDetailParam.getSchoolYear())) { // 学年学期
                predicateList.add(
                        cb.and(cb.equal(courseTableJoin.get("schoolYear").as(String.class), courseTableDetailParam.getSchoolYear()),
                                cb.equal(courseTableJoin.get("term").as(Integer.class), courseTableDetailParam.getTerm()))
                );
            }
            if (!StringUtils.isEmpty(courseTableDetailParam.getCollegeId())) { // 学院  0 全部
                predicateList.add(cb.equal(courseTableJoin.get("collegeId").as(String.class), courseTableDetailParam.getCollegeId()));
            }
            if (!StringUtils.isEmpty(courseTableDetailParam.getTeacherNameOrCourseName())) {  // 老师名称/课程名称
                String teacherNameOrCourseName = replaceCharacters(courseTableDetailParam.getTeacherNameOrCourseName()).trim();
                predicateList.add(cb.or(cb.like(courseTableJoin.get("courseName").as(String.class),
                                "%" + teacherNameOrCourseName + "%"),
                        cb.like(root.get("teacherNames").as(String.class),
                                "%" + teacherNameOrCourseName + "%")));
            }
            if (Objects.nonNull(courseTableDetailParam.getStudentType()) && !courseTableDetailParam.getStudentType().equals(-1)) {
                predicateList.add(cb.equal(courseTableJoin.get("studentType").as(Integer.class), courseTableDetailParam.getStudentType()));
            }
            if (courseTableDetailParam.getClassRooms().size() > 0) { // 教室
                predicateList.add(roomUserJoin.get("roomId").as(String.class)
                        .in(courseTableDetailParam.getClassRooms()));
            }
            if (courseTableDetailParam.getWeekNum() != 0) { // 星期几 0 全部
                predicateList.add(cb.equal(root.get("weekNum").as(Integer.class), courseTableDetailParam.getWeekNum()));
            }
            if (courseTableDetailParam.getWeek() != 0) { // 周次 0 全部
                predicateList.add(cb.equal(root.get("week").as(String.class), courseTableDetailParam.getWeek()));
            }
            Join<Object, Object> join = courseSegmentJoin.join("segment", JoinType.LEFT);
            if (StringUtils.isEmpty(courseTableDetailParam.getBuildingId()) && courseTableDetailParam.getSegment() != 0) { // 节次 0 全部
                predicateList.add(cb.equal(join.get("segmentPK").get("segment").as(Integer.class), courseTableDetailParam.getSegment()));
            }
            // 教学楼
            if (!StringUtils.isEmpty(courseTableDetailParam.getBuildingId()) && courseTableDetailParam.getSegment() == 0) {
                predicateList.add(cb.equal(join.get("segmentPK").get("buildID").as(String.class), courseTableDetailParam.getBuildingId()));
            }
            if (!StringUtils.isEmpty(courseTableDetailParam.getBuildingId()) && courseTableDetailParam.getSegment() != 0) {
                predicateList.add(cb.equal(join.get("segmentPK").as(SegmentPK.class),
                        new SegmentPK(courseTableDetailParam.getBuildingId(), courseTableDetailParam.getSegment())));
            }
            predicateList.add(cb.isNotNull(root.get("segmentStartTime")));
            Predicate[] predicates = new Predicate[predicateList.size()];
            query.distinct(true);
            List<Order> orderList = getOrders(courseTableDetailParam, root, cb);
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseTableDetail> searchGreaterNowTimeCourseTables() {
        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Segment, CourseTableDetail> join = root.join("courseSegmentList", JoinType.LEFT)
                    .join("segment", JoinType.LEFT);
            predicateList.add(cb.and(
                    cb.equal(root.get("courseDate").as(String.class), nowDate),
                    cb.greaterThanOrEqualTo(join.get("startTime").as(String.class), nowTime)
            ));
            predicateList.add(cb.equal(root.get("source").as(Source.class), Source.DATA_DOCKING));
            Predicate[] predicates = new Predicate[predicateList.size()];

            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    private static List<Order> getOrders(CourseTableDetailParam param, Root<CourseTableDetail> root, CriteriaBuilder cb) {
        List<Order> orderList = new ArrayList<>();
        Join<Object, Object> roomUserJoin = root.join("courseTableDetailRoomUserList", JoinType.LEFT);
        Join<Object, Object> courseTypeJoin = root.join("courseType", JoinType.LEFT);
        Join<Object, Object> courseSegmentJoin = root.join("courseSegmentList", JoinType.LEFT);
        if (param.getSegment() != Constant.ZREO && param.getWeek() != Constant.ZREO && param.getWeekNum() != Constant.ZREO) {
            orderList.add(cb.asc(courseTypeJoin.get("sortName")));
            orderList.add(cb.asc(roomUserJoin.get("roomName").as(String.class)));
            orderList.add(cb.asc(roomUserJoin.get("roomId").as(String.class)));
        } else {
            orderList.add(cb.asc(root.get("courseDate")));
            orderList.add(cb.asc(courseSegmentJoin.join("segment", JoinType.LEFT).get("startTime")));
            orderList.add(cb.asc(courseTypeJoin.get("sortName")));
            orderList.add(cb.asc(roomUserJoin.get("roomName").as(String.class)));
            orderList.add(cb.asc(roomUserJoin.get("roomId").as(String.class)));
        }
        return orderList;
    }

    public static Specification<CourseTableDetail> courseTableDetailByTeacherId(
            String teacherId, String studentType, String schoolYear, Integer term) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> courseTableJoin = root.join("courseTable", JoinType.LEFT);
            Join<Object, Object> teacherList = root.joinList("courseTableDetailTeacherList", JoinType.LEFT);
            Join<Object, Object> courseTableDetailRoomUser = root.
                    joinList("courseTableDetailRoomUserList", JoinType.LEFT);
            if (!StringUtils.isEmpty(teacherId)) {
                predicateList.add(
                        cb.and(cb.equal(teacherList.get("teacherId").as(String.class), teacherId),
                                cb.equal(courseTableJoin.get("schoolYear").as(String.class), schoolYear),
                                cb.equal(courseTableJoin.get("term").as(Integer.class), term))
                );
                if (!studentType.equals("-1")) {
                    Integer studentTypeQuery = Integer.parseInt(studentType);
                    predicateList.add(cb.equal(courseTableJoin.get("studentType").as(Integer.class), studentTypeQuery));
                }

            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseTableDetail> courseTableDetailSpecificationByClass(AdministrativeVo administrative,
                                                                                         String schoolYear, Integer term) {

        return (Root<CourseTableDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> courseTableJoin = root.join("courseTable", JoinType.LEFT);
            Join<Object, Object> courseSegmentJoin = root.join("courseSegmentList", JoinType.LEFT);
            Join<Object, Object> administrativeJoin = courseTableJoin.join("group", JoinType.LEFT)
                    .join("administrativeClasses", JoinType.LEFT);

            if (!StringUtils.isEmpty(schoolYear)) { // 学年学期
                predicateList.add(
                        cb.and(cb.equal(courseTableJoin.get("schoolYear").as(String.class), schoolYear),
                                cb.equal(courseTableJoin.get("term").as(Integer.class), term))
                );
            }
            if (Objects.nonNull(administrative.getStudentType()) && !administrative.getStudentType().equals(-1)) {
                predicateList.add(cb.equal(courseTableJoin.get("studentType").as(Integer.class), administrative.getStudentType()));
            }
            if (!StringUtils.isEmpty(administrative.getCollegeId())) { // 学院  0 全部
                predicateList.add(cb.equal(administrativeJoin.join("college", JoinType.LEFT)
                        .get("id").as(String.class), administrative.getCollegeId()));
            }
            if (!StringUtils.isEmpty(administrative.getYear())) {
                predicateList.add(cb.equal(administrativeJoin.get("year").as(String.class), administrative.getYear()));
            }
            if (!StringUtils.isEmpty(administrative.getMajorId())) {
                predicateList.add(cb.equal(administrativeJoin.join("major", JoinType.LEFT)
                        .get("id").as(String.class), administrative.getMajorId()));
            }
            if (!StringUtils.isEmpty(administrative.getAdministrativeClass())) {
                predicateList.add(cb.equal(administrativeJoin.get("className").as(String.class), administrative.getAdministrativeClass()));
            }
            if (!StringUtils.isEmpty(administrative.getWeekNum()) && administrative.getWeekNum() != 0) { // 星期几 0 全部
                predicateList.add(cb.equal(root.get("weekNum").as(Integer.class), administrative.getWeekNum()));
            }
            if (!StringUtils.isEmpty(administrative.getWeek()) && administrative.getWeek() != 0) { // 周次 0 全部
                predicateList.add(cb.equal(root.get("week").as(String.class), administrative.getWeek()));
            }
            Join<Object, Object> join = courseSegmentJoin.join("segment", JoinType.LEFT);
            if (!StringUtils.isEmpty(administrative.getSegment()) && administrative.getSegment() != 0) { // 节次 0 全部
                predicateList.add(cb.equal(join.get("segmentPK").get("segment").as(Integer.class), administrative.getSegment()));
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            query.groupBy(root.get("id"));
            CourseTableDetailParam courseTableDetailParam = new CourseTableDetailParam();
            courseTableDetailParam.setWeek(administrative.getWeek());
            courseTableDetailParam.setWeekNum(administrative.getWeekNum());
            courseTableDetailParam.setSegment(administrative.getSegment());
            List<Order> orderList = getOrders(courseTableDetailParam, root, cb);
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseTableDetail> courseTableDetailSpecificationByDate(CourseTableDetailData courseTableDetailData) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> courseTableJoin = root.join("courseTable");
            Join<Object, Object> teacherJoin = root.join("courseTableDetailTeacherList");
            Join<Object, Object> roomUserJoin = root.join("courseTableDetailRoomUserList", JoinType.LEFT);
            if (!StringUtils.isEmpty(courseTableDetailData.getSearchName())) {
                String searchNames = replaceCharacters(courseTableDetailData.getSearchName().trim());
                predicateList.add(cb.or(cb.like(roomUserJoin.get("roomName").as(String.class), "%" + searchNames + "%"),
                        cb.like(teacherJoin.get("teacherName").as(String.class), "%" + searchNames + "%"),
                        cb.like(courseTableJoin.get("courseName").as(String.class), "%" + searchNames + "%"),
                        cb.like(roomUserJoin.get("itemTeacherName").as(String.class), "%" + searchNames + "%")
                ));
            }

            if (!StringUtils.isEmpty(courseTableDetailData.getStartTime())) {
                Date startTime = DateUtils.stringToDate(DateUtils.DATE, courseTableDetailData.getStartTime());
                predicateList.add(cb.greaterThanOrEqualTo(root.get("courseDate").as(Date.class), startTime));
            }
            if (!StringUtils.isEmpty(courseTableDetailData.getEndTime())) {
                Date endTime = DateUtils.stringToDate(DateUtils.DATE, courseTableDetailData.getEndTime());
                predicateList.add(cb.lessThanOrEqualTo(root.get("courseDate").as(Date.class), endTime));
            }
            if (!StringUtils.isEmpty(courseTableDetailData.getType())) {
                predicateList.add(cb.equal(root.get("courseKind").as(CourseKind.class),
                        Integer.valueOf(courseTableDetailData.getType())));
            }
            if (!courseTableDetailData.getStudentType().equals(-1)) {
                predicateList.add(cb.equal(courseTableJoin.get("studentType").as(Integer.class), courseTableDetailData.getStudentType()));
            }
            predicateList.add(cb.isNotNull(root.get("segment")));
            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            query.distinct(true);
            orderList.add(cb.desc(root.get("courseDate")));
            orderList.add(cb.desc(root.get("segmentStartTime")));
            orderList.add(cb.desc(root.get("segment")));
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }


    public static Specification<CourseTableDetail> getRoomScheduleInformationList(Date nowDate, Integer source) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();


            if (nowDate != null) {
                predicateList.add(cb.equal(root.get("courseDate").as(Date.class), nowDate));
            }

            if (source != null) {
                predicateList.add(cb.equal(root.get("source").as(Integer.class), source));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            query.distinct(true);
            orderList.add(cb.asc(root.get("courseDate")));
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseTableDetail> getCourseTableByRoomIdNowTime(Integer source, String roomId) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            Join<Segment, CourseTableDetail> join = root.join("courseSegmentList", JoinType.INNER)
                    .join("segment", JoinType.INNER);
            predicateList.add(cb.and(
                    cb.equal(root.get("courseDate").as(String.class), nowDate),
                    cb.and(cb.lessThanOrEqualTo(join.get("startTime").as(String.class), nowTime),
                            cb.greaterThanOrEqualTo(join.get("endTime").as(String.class), nowTime))
            ));

            if (!StringUtils.isEmpty(roomId)) {
                predicateList.add(cb.equal(root.join("courseTableDetailRoomUserList", JoinType.INNER)
                        .get("roomId").as(String.class), roomId));
            }

            if (source != null) {
                predicateList.add(cb.equal(root.get("source").as(Integer.class), source));
            }

            predicateList.add(cb.and(cb.isNotNull(root.get("segmentStartTime")), cb.isNotNull(root.get("segmentEndTime"))));

            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            query.distinct(true);
            orderList.add(cb.asc(root.get("courseDate")));
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }


    public static Specification<CourseTableDetail> searchCourseTablePage(List<String> collegeIds, List<String> courseTableIds,
                                                                         String roomId, String searchStr, String courseStudentTypes,
                                                                         String isDistinguishCourseStudentType,
                                                                         Integer superviseCollegeType, String courseDate,
                                                                         Boolean isNeedFilterPoliticalCourse,
                                                                         String relatedScheduleTypes) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            String nowDate = org.apache.commons.lang3.StringUtils.isNotBlank(courseDate) ? courseDate : getNowDate();
            String nowTime = getNowTime();

            predicateList.add(cb.equal(root.get("courseDate").as(Date.class), DateUtils.stringToDate(DateUtils.DATE, nowDate)));
//            predicateList.add(cb.lessThanOrEqualTo(root.get("segmentStartTime")
//                    .as(Date.class), DateUtils.stringToDate(DateUtils.DATE_TIME, nowTime)));
            Join<CourseTableDetail, CourseTableDetailRoomUser> joinRoomUser = root.join("courseTableDetailRoomUserList", JoinType.INNER);
            Join<CourseTableDetail, CourseTableDetailTeacher> joinTeacher = root.join("courseTableDetailTeacherList", JoinType.INNER);
            Join<CourseTableDetail, CourseTable> joinCourseTable = root.join("courseTable", JoinType.INNER);
            if (!StringUtils.isEmpty(roomId)) {
                predicateList.add(cb.equal(joinRoomUser.get("roomId").as(String.class), roomId));
            }
            if (!StringUtils.isEmpty(searchStr)) {
                predicateList.add(cb.or(cb.like(joinTeacher.get("teacherName").as(String.class),
                                "%" + replaceCharacters(searchStr.trim()) + "%"),
                        cb.like(root.get("courseName").as(String.class),
                                "%" + replaceCharacters(searchStr.trim()) + "%")));
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(isDistinguishCourseStudentType)
                    && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(isDistinguishCourseStudentType)) {
                if (org.apache.commons.lang3.StringUtils.isNotBlank(courseStudentTypes)) {
                    String[] courseStudentTypeList = courseStudentTypes.split(",");
                    List<StudentType> studentTypeList = new ArrayList<>();
                    for (String courseStudentType : courseStudentTypeList) {
                        if (org.apache.commons.lang3.StringUtils.isNumeric(courseStudentType)) {
                            StudentType studentType = StudentType.getStudentType(Integer.parseInt(courseStudentType));
                            if (Objects.nonNull(studentType)) {
                                studentTypeList.add(studentType);
                            }
                        }
                    }
                    if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(studentTypeList)) {
                        predicateList.add(cb.in(joinCourseTable.get("studentType")).value(studentTypeList));
                    } else {
                        predicateList.add(cb.equal(joinCourseTable.get("studentType"), "-1"));

                    }

                }
            }
            if (!CollectionUtils.isEmpty(collegeIds)) {
                if (SUPERVISE_COLLEGE_TYPE_COURSE_TABLE_COLLEGE.equals(superviseCollegeType)) {
                    predicateList.add(joinCourseTable.get("collegeId").as(String.class).in(collegeIds));
                } else {
                    predicateList.add(joinTeacher.get("teacherCollegeId").as(String.class).in(collegeIds));
                }
            }
            if (!CollectionUtils.isEmpty(courseTableIds)) {
                predicateList.add(joinCourseTable.get("id").as(String.class).in(courseTableIds).not());
            }
            if (Objects.nonNull(isNeedFilterPoliticalCourse) && isNeedFilterPoliticalCourse) {
                Subquery<String> subquery = query.subquery(String.class);
                Root<CourseExpansion> subRoot = subquery.from(CourseExpansion.class);
                subquery.select(subRoot.get("courseId")).distinct(true)
                        .where(cb.equal(subRoot.get("expansionKey"), "politicalCourse"));
                predicateList.add(cb.not(joinCourseTable.get("course").get("id").as(String.class).in(subquery)));
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(relatedScheduleTypes)) {
                String[] relatedScheduleTypeList = relatedScheduleTypes.split(",");

                // 1. 获取 Course ID 的路径 (提取变量以避免在 OR 条件中重复 join)
                // 假设 joinCourseTable 已经在上下文定义
                javax.persistence.criteria.Path<String> courseIdPath = joinCourseTable.join("course").get("id");

                // 2. 子查询 A：查找符合 relatedScheduleTypes 的 courseId
                Subquery<String> matchSubquery = query.subquery(String.class);
                Root<CourseExpansion> matchRoot = matchSubquery.from(CourseExpansion.class);
                matchSubquery.select(matchRoot.get("courseId"));

                CriteriaBuilder.In<String> inClause = cb.in(matchRoot.get("expansionKey"));
                for (String type : relatedScheduleTypeList) {
                    inClause.value(type);
                }
                matchSubquery.where(inClause);

                //以此构建条件 A：课程 ID 存在于匹配列表中
                Predicate isMatched = cb.in(courseIdPath).value(matchSubquery);

                // 3. 子查询 B：查找 CourseExpansion 表中所有的 courseId (用于判断是否配置过)
                Subquery<String> existSubquery = query.subquery(String.class);
                Root<CourseExpansion> existRoot = existSubquery.from(CourseExpansion.class);
                existSubquery.select(existRoot.get("courseId"));
                // 查全部，无需 where

                // 以此构建条件 B：课程 ID 不在扩展表中 (即未配置)
                Predicate isNotConfigured = cb.not(cb.in(courseIdPath).value(existSubquery));

                // 4. 将两个条件用 OR 连接并加入 predicateList
                predicateList.add(cb.or(isMatched, isNotConfigured));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("segmentStartTime")));
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            query.distinct(true);

            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    private static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static Specification<CourseTableDetail> specification(String roomIds, String sources, String nowDate) {

        return (Root<CourseTableDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> roomUserJoin = root.join("courseTableDetailRoomUserList", JoinType.LEFT);
            if (!StringUtils.isEmpty(sources)) {
                List<String> sourceList = Arrays.asList(sources.split(","));
                predicateList.add(root.get("source").as(Integer.class)
                        .in(sourceList));
            }
            if (!StringUtils.isEmpty(roomIds)) { // 教室
                List<String> roomIdList = Arrays.asList(roomIds.split(","));
                predicateList.add(roomUserJoin.get("roomId").as(String.class)
                        .in(roomIdList));
            }
            predicateList.add(cb.equal(root.get("courseDate").as(String.class), nowDate));
            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("segment")));
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseTableDetail> getWaitAttendClassCourseTableDetailSpecification(
            WaitAttendClassCourseTableDetailParam waitAttendClassCourseTableDetailParam
    ) {
        return (Root<CourseTableDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            String courseDate = waitAttendClassCourseTableDetailParam.getCourseDate();
            predicateList.add(cb.equal(root.get("courseDate").as(String.class), courseDate));

            Join<CourseTableDetail, CourseTableDetailRoomUser> joinRoomUser = root.join("courseTableDetailRoomUserList", JoinType.INNER);
            Join<CourseTableDetail, CourseTable> joinCourseTable = root.join("courseTable", JoinType.INNER);
            Join<CourseTableDetail, CourseTableDetailTeacher> joinTeacher = root.join("courseTableDetailTeacherList", JoinType.INNER);

            if (!CollectionUtils.isEmpty(waitAttendClassCourseTableDetailParam.getRoomIdList())) { // 教室
                List<String> roomIdList = waitAttendClassCourseTableDetailParam.getRoomIdList();
                predicateList.add(joinRoomUser.get("roomId").as(String.class).in(roomIdList));
            }
            String isDistinguishCourseStudentType = waitAttendClassCourseTableDetailParam.getIsDistinguishCourseStudentType();
            if ((org.apache.commons.lang3.StringUtils.isNotBlank(isDistinguishCourseStudentType)
                    && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(isDistinguishCourseStudentType)) &&
                    (
                            ObjectUtils.isEmpty(waitAttendClassCourseTableDetailParam.getOnlyGraduateCourse()) ||
                                    waitAttendClassCourseTableDetailParam.getOnlyGraduateCourse() == 0)
            ) {
                String courseStudentTypes = waitAttendClassCourseTableDetailParam.getCourseStudentTypes();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(courseStudentTypes)) {
                    String[] courseStudentTypeList = courseStudentTypes.split(",");
                    List<StudentType> studentTypeList = new ArrayList<>();
                    for (String courseStudentType : courseStudentTypeList) {
                        if (org.apache.commons.lang3.StringUtils.isNumeric(courseStudentType)) {
                            StudentType studentType = StudentType.getStudentType(Integer.parseInt(courseStudentType));
                            if (Objects.nonNull(studentType)) {
                                studentTypeList.add(studentType);
                            }
                        }
                    }
                    if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(studentTypeList)) {
                        predicateList.add(cb.in(joinCourseTable.get("studentType")).value(studentTypeList));
                    } else {
                        predicateList.add(cb.equal(joinCourseTable.get("studentType"), "-1"));

                    }

                }
            }
            if (Objects.nonNull(waitAttendClassCourseTableDetailParam.getOnlyGraduateCourse())
                    && waitAttendClassCourseTableDetailParam.getOnlyGraduateCourse() == 1) {
                predicateList.add(cb.equal(joinCourseTable.get("studentType"), "1"));
            }
            List<String> collegeIdList = waitAttendClassCourseTableDetailParam.getCollegeIdList();
            if (!CollectionUtils.isEmpty(collegeIdList)) {
                Integer superviseCollegeType = waitAttendClassCourseTableDetailParam.getSuperviseCollegeType();
                if (SUPERVISE_COLLEGE_TYPE_COURSE_TABLE_COLLEGE.equals(superviseCollegeType)) {
                    predicateList.add(joinCourseTable.get("collegeId").as(String.class).in(collegeIdList));
                } else {
                    predicateList.add(joinTeacher.get("teacherCollegeId").as(String.class).in(collegeIdList));
                }
            }

            Subquery<String> subquery = query.subquery(String.class);
            Root<CourseExpansion> subRoot = subquery.from(CourseExpansion.class);
            subquery.select(subRoot.get("courseId")).distinct(true)
                    .where(cb.equal(subRoot.get("expansionKey"), "politicalCourse"));
            predicateList.add(cb.not(joinCourseTable.get("course").get("id").as(String.class).in(subquery)));

            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }


    public static Specification<CourseTableDetail> getCourseTableDetailSpecificationByCourseTableId(String courseTableId) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> courseTableJoin = root.join("courseTable");

            if (!StringUtils.isEmpty(courseTableId)) {
                predicateList.add(cb.equal(courseTableJoin.get("id").as(String.class), courseTableId));
            }
            predicateList.add(cb.isNotNull(root.get("segment")));
            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            query.distinct(true);
            orderList.add(cb.desc(root.get("courseDate")));
            orderList.add(cb.desc(root.get("segmentStartTime")));
            orderList.add(cb.desc(root.get("segment")));
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

}

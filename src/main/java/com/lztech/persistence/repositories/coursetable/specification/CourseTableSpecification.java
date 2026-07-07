package com.lztech.persistence.repositories.coursetable.specification;

import com.lztech.domain.model.course.CourseExpansion;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.site.viewmodel.assistantcourse.AssistantCourseParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CourseTableSpecification {

    public static Specification<CourseTable> specification(String studentType, List<String> courseIds, String schoolYear,
                                                           Integer term, String teacherId) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (courseIds.size() > 0) {
                predicateList.add(root.join("course", JoinType.LEFT).get("id").as(String.class).in(courseIds));
            }

            if (!StringUtils.isEmpty(teacherId)) {

                Predicate joinTablePredicate = cb.equal(root.join("courseTableDetailList", JoinType.LEFT)
                        .join("courseTableDetailTeacherList", JoinType.LEFT)
                        .get("teacherId").as(String.class), teacherId);
                // 2. CourseTable自身teacherId字段包含userId（使用like查询）
                Predicate directTeacherIdPredicate = cb.like(root.get("courseTableTeacherId").as(String.class),
                        "%" + teacherId + "%");
                // 使用OR连接两个条件
                predicateList.add(cb.or(joinTablePredicate, directTeacherIdPredicate));
            }

            predicateList.add(cb.equal(root.join("group", JoinType.INNER).get("groupStatus")
                    .as(GroupStatus.class), GroupStatus.NORMAL));
            return getPredicate(schoolYear, term, root, query, cb, predicateList);
        };
    }


    public static Specification<CourseTable> getTeacherCourseList(String userId, String schoolYear, Integer term,
                                                                  String courseStudentTypes, Boolean isNeedDistinguish,
                                                                  String relatedScheduleTypes) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (org.apache.commons.lang3.StringUtils.isNotBlank(userId)) {
                Predicate joinTablePredicate = cb.equal(root.join("courseTableDetailList", JoinType.INNER)
                        .join("courseTableDetailTeacherList", JoinType.INNER)
                        .get("teacherId").as(String.class), userId);
                // 2. CourseTable自身teacherId字段包含userId（使用like查询）
                Predicate directTeacherIdPredicate = cb.like(root.get("courseTableTeacherId").as(String.class),
                        "%" + userId + "%");
                // 使用OR连接两个条件
                predicateList.add(cb.or(joinTablePredicate, directTeacherIdPredicate));
            }
            if (Objects.nonNull(isNeedDistinguish) && isNeedDistinguish) {
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
                    if (CollectionUtils.isNotEmpty(studentTypeList)) {
                        predicateList.add(cb.in(root.get("studentType")).value(studentTypeList));
                    } else {
                        predicateList.add(cb.equal(root.get("studentType"), "-1"));

                    }

                }
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(relatedScheduleTypes)) {
                String[] relatedScheduleTypeList = relatedScheduleTypes.split(",");
                // 0. 获取 Course ID 的路径 (避免多次 join)
                // 注意：如果 root 是 CourseTableDetail，这里可能是 root.join("courseTable").join("course").get("id")
                // 这里沿用你代码中的 root.join("course").get("id")
                javax.persistence.criteria.Path<String> courseIdPath = root.join("course").get("id");

                // 1. 条件一：匹配指定的 expansionKey
                Subquery<String> matchSubquery = query.subquery(String.class);
                Root<CourseExpansion> matchRoot = matchSubquery.from(CourseExpansion.class);
                matchSubquery.select(matchRoot.get("courseId"));

                CriteriaBuilder.In<String> inClause = cb.in(matchRoot.get("expansionKey"));
                for (String type : relatedScheduleTypeList) {
                    inClause.value(type);
                }
                matchSubquery.where(inClause);

                // 生成匹配 Predicate: id IN (select courseId from ... where key in (...))
                Predicate isMatched = cb.in(courseIdPath).value(matchSubquery);

                // 2. 条件二：没有配置 (即在 CourseExpansion 表中找不到该 courseId)
                Subquery<String> existSubquery = query.subquery(String.class);
                Root<CourseExpansion> existRoot = existSubquery.from(CourseExpansion.class);
                existSubquery.select(existRoot.get("courseId"));
                // 这里不需要 where 条件，我们需要查出所有存在于扩展表中的 ID

                // 生成未配置 Predicate: id NOT IN (select courseId from CourseExpansion)
                Predicate isNotConfigured = cb.not(cb.in(courseIdPath).value(existSubquery));

                // 3. 组合条件：(匹配指定类型 OR 没有配置)
                predicateList.add(cb.or(isMatched, isNotConfigured));
            }


            predicateList.add(cb.equal(root.join("group", JoinType.INNER).get("groupStatus")
                    .as(GroupStatus.class), GroupStatus.NORMAL));
            return getPredicate(schoolYear, term, root, query, cb, predicateList);
        };
    }

    public static Specification<CourseTable> getAITeacherCourseList(String userId, String schoolYear, Integer term,
                                                                    List<String> courseIdList) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (org.apache.commons.lang3.StringUtils.isNotBlank(userId)) {
                Predicate joinTablePredicate = cb.equal(root.join("courseTableDetailList", JoinType.INNER)
                        .join("courseTableDetailTeacherList", JoinType.INNER)
                        .get("teacherId").as(String.class), userId);
                // 2. CourseTable自身teacherId字段包含userId（使用like查询）
                Predicate directTeacherIdPredicate = cb.like(root.get("courseTableTeacherId").as(String.class),
                        "%" + userId + "%");
                // 使用OR连接两个条件
                predicateList.add(cb.or(joinTablePredicate, directTeacherIdPredicate));
            }
            if (CollectionUtils.isNotEmpty(courseIdList)) {
                predicateList.add(root.join("course", JoinType.INNER).get("id").as(String.class).in(courseIdList));
            }

            predicateList.add(cb.equal(root.join("group", JoinType.INNER).get("groupStatus")
                    .as(GroupStatus.class), GroupStatus.NORMAL));
            return getPredicate(schoolYear, term, root, query, cb, predicateList);
        };
    }

    private static Predicate getPredicate(String schoolYear, Integer term, Root<CourseTable> root, CriteriaQuery<?> query,
                                          CriteriaBuilder cb, List<Predicate> predicateList) {
        if (!StringUtils.isEmpty(schoolYear)) {
            predicateList.add(cb.equal(root.get("schoolYear").as(String.class), schoolYear));
        }

        if (!StringUtils.isEmpty(term)) {
            predicateList.add(cb.equal(root.get("term").as(Integer.class), term));
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        return query.where(predicateList.toArray(predicates)).getRestriction();
    }

    public static Specification<CourseTable> getStudentCourseList(String userId, String schoolYear,
                                                                  Integer term, String openId) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinGroup = root.join("group", JoinType.LEFT);
            Join<Object, Object> joinMemberGroup = joinGroup.join("groupMemberList", JoinType.LEFT);
            if (!StringUtils.isEmpty(userId)) {
                predicateList.add(cb.equal(joinMemberGroup.get("studentId").as(String.class), userId));
            } else {
                predicateList.add(cb.equal(joinMemberGroup.get("openId").as(String.class), openId));
            }


            predicateList.add(cb.equal(joinMemberGroup.get("groupMemberStatus").as(Integer.class), GroupMemberStatus.NORMAL.getIndex()));
            predicateList.add(cb.equal(joinGroup.get("groupStatus").as(GroupStatus.class), GroupStatus.NORMAL));
            return getPredicate(schoolYear, term, root, query, cb, predicateList);
        };
    }

    public static Specification<CourseTable> studentSpecification(String userId, List<String> courseIds,
                                                                  String schoolYear, Integer term) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinGroup = root.join("group", JoinType.LEFT)
                    .join("groupMemberList", JoinType.LEFT);
            if (!StringUtils.isEmpty(userId)) {
                predicateList.add(cb.equal(joinGroup.get("studentId").as(String.class), userId));
            }
            if (courseIds.size() > 0) {
                predicateList.add(root.join("course", JoinType.LEFT).get("id").as(String.class).in(courseIds));
            }
            predicateList.add(cb.equal(root.join("group", JoinType.INNER).get("groupStatus")
                    .as(GroupStatus.class), GroupStatus.NORMAL));
            predicateList.add(cb.equal(joinGroup.get("groupMemberStatus").as(Integer.class), GroupMemberStatus.NORMAL.getIndex()));
            return getPredicate(schoolYear, term, root, query, cb, predicateList);
        };
    }

    public static Specification<CourseTable> getCourseTableSpecification(String userId, String courseId, String schoolYear,
                                                                         Integer term, String groupId) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!StringUtils.isEmpty(userId)) {
                predicateList.add(cb.equal(root.join("courseTableDetailList", JoinType.LEFT)
                        .join("courseTableDetailTeacherList", JoinType.INNER).get("teacherId").as(String.class), userId));
            }

            if (!StringUtils.isEmpty(courseId)) {
                predicateList.add(cb.equal(root.join("course", JoinType.INNER).get("id").as(String.class), courseId));
            }

            if (!StringUtils.isEmpty(groupId)) {
                predicateList.add(cb.equal(root.join("group", JoinType.INNER).get("id").as(String.class), groupId));
            }


            query.distinct(true);
            return getPredicate(schoolYear, term, root, query, cb, predicateList);
        };
    }

    public static Specification<CourseTableDetail> getRealtimeScheduleSpecification(
            String studentType, String roomId, String nowDate, String nowTime) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> join = root.join("courseTable", JoinType.LEFT);

            if (!StringUtils.isEmpty(nowDate)) {
                predicateList.add(cb.equal(root.get("courseDate").as(String.class), nowDate));
            }

            if (!StringUtils.isEmpty(roomId)) {
                predicateList.add(root.join("courseTableDetailRoomUserList", JoinType.LEFT)
                        .get("roomId").as(String.class).in(roomId));
            }
            if (!studentType.equals("-1")) {
                Integer studentTypeQuery = Integer.parseInt(studentType);
                predicateList.add(cb.equal(join.get("studentType").as(Integer.class),
                        studentTypeQuery));
            }
            if (!StringUtils.isEmpty(nowTime)) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get("segmentEndTime").as(String.class), nowTime));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("segmentStartTime")));
            query.orderBy(orderList);
            query.distinct(true);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseTable> getAssistantTeacherCourseList(
            AssistantCourseParam assistantCourseParam, List<String> courseIdList, List<String> teacherIdList) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            Join<Object, Object> courseTableDetailTeacherJoin = root.join("courseTableDetailList", JoinType.INNER)
                    .join("courseTableDetailTeacherList", JoinType.INNER);
            Join<Object, Object> courseJoin = root.join("course", JoinType.INNER);
            if (CollectionUtils.isNotEmpty(teacherIdList)) {
                predicateList.add(cb.in(courseTableDetailTeacherJoin.get("teacherId")).value(teacherIdList));
            }
            if (CollectionUtils.isNotEmpty(courseIdList)) {
                predicateList.add(cb.in(courseJoin.get("id")).value(courseIdList));
            }

            if (org.apache.commons.lang3.StringUtils.isNotBlank(assistantCourseParam.getCourseStudentTypes())) {
                String[] courseStudentTypeList = assistantCourseParam.getCourseStudentTypes().split(",");
                List<StudentType> studentTypeList = new ArrayList<>();
                for (String courseStudentType : courseStudentTypeList) {
                    if (org.apache.commons.lang3.StringUtils.isNumeric(courseStudentType)) {
                        StudentType studentType = StudentType.getStudentType(Integer.parseInt(courseStudentType));
                        if (Objects.nonNull(studentType)) {
                            studentTypeList.add(studentType);
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(studentTypeList)) {
                    predicateList.add(cb.in(root.get("studentType")).value(studentTypeList));
                }
            }

            predicateList.add(cb.equal(root.join("group", JoinType.INNER).get("groupStatus")
                    .as(GroupStatus.class), GroupStatus.NORMAL));
            return getPredicate(assistantCourseParam.getSchoolYear(), assistantCourseParam.getTerm(), root, query, cb, predicateList);
        };
    }

    public static Specification<CourseTable> getTeacherCourseList
            (String teacherNo, String courseName, String startTime, String endTime) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();


            Join<Object, Object> courseJoin = root.join("course", JoinType.INNER);
            if (org.apache.commons.lang3.StringUtils.isNotBlank(teacherNo)) {
                Join<Object, Object> courseTableDetailJoin = root.join("courseTableDetailList", JoinType.INNER);
                predicateList.add(cb.between(courseTableDetailJoin.get("courseDate").as(String.class), startTime, endTime));

                Join<Object, Object> courseTableDetailTeacherJoin = courseTableDetailJoin
                        .join("courseTableDetailTeacherList", JoinType.INNER);
                predicateList.add(cb.equal(courseTableDetailTeacherJoin.get("teacherNo").as(String.class), teacherNo));
            }

            if (org.apache.commons.lang3.StringUtils.isNotBlank(courseName)) {
                predicateList.add(cb.equal(courseJoin.get("courseName").as(String.class), courseName));
            }

            predicateList.add(cb.equal(root.get("studentType").as(StudentType.class), StudentType.UNDERGRADUATE));

            query.groupBy(courseJoin.get("id"));
            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.desc(root.get("createTime")));
            query.orderBy(orderList);
            query.distinct(true);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }
}

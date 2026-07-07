package com.lztech.persistence.repositories.course.specification;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseStructure;
import com.lztech.domain.model.course.enums.CourseSource;
import com.lztech.site.until.CharactersReplace;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseSpecification {

    public static Specification<Course> specification(String collegeId,
                                                      String courseName,
                                                      Integer courseSource) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(collegeId)) {
                predicateList.add(cb.equal(root.join("college").get("id").as(String.class), collegeId));
            }
            if (!StringUtils.isEmpty(courseName)) {
                String name = CharactersReplace.replaceCharacters(courseName).trim();
                predicateList.add(cb.like(root.get("courseName").as(String.class), "%" + name + "%"));
            }
            if (Objects.nonNull(courseSource)){
                predicateList.add(cb.equal(root.get("courseSource").as(CourseSource.class), CourseSource.getCourseSourceByIndex(courseSource)));
            }
            predicateList.add(cb.isTrue(root.get("useState")));
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<Course> courseNameOrCodeSpecification(String courseNameOrCode, List<String> courseIds) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(courseNameOrCode)) {
                String name = CharactersReplace.replaceCharacters(courseNameOrCode).trim();
                predicateList.add(cb.or(
                        cb.like(root.get("courseName").as(String.class), "%" + name + "%"),
                        cb.like(root.get("courseCode").as(String.class), "%" + name + "%")
                ));
            }
            if (CollectionUtils.isNotEmpty(courseIds)) {
                predicateList.add(cb.not(cb.in(root.get("id")).value(courseIds)));
            }
            predicateList.add(cb.isTrue(root.get("useState")));

            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }


    public static Specification<Course> courseNameOrCodeAndCollegeSpecification(String courseNameOrCode, List<String> collegeIds) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object,Object> collegeJoin = root.join("college", JoinType.INNER);
            if (!StringUtils.isEmpty(courseNameOrCode)) {
                String name = CharactersReplace.replaceCharacters(courseNameOrCode).trim();
                predicateList.add(cb.or(
                        cb.like(root.get("courseName").as(String.class), "%" + name + "%"),
                        cb.like(root.get("courseCode").as(String.class), "%" + name + "%")
                ));
            }
            if (CollectionUtils.isNotEmpty(collegeIds)) {
                predicateList.add(cb.in(collegeJoin.get("id")).value(collegeIds));
            }
            predicateList.add(cb.isTrue(root.get("useState")));

            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseStructure> courseNameOrCodeSpecification(String courseNameOrCode, String collegeId) {
        return (root, query, cb) -> {
            Join<Object, Object> joinCourse = root.join("course", JoinType.INNER);
            Join<Object, Object> joinCollege = joinCourse.join("college", JoinType.INNER);
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(courseNameOrCode)) {
                String name = CharactersReplace.replaceCharacters(courseNameOrCode).trim();
                predicateList.add(cb.or(
                        cb.like(joinCourse.get("courseName").as(String.class), "%" + name + "%"),
                        cb.like(joinCourse.get("courseCode").as(String.class), "%" + name + "%")
                ));
            }
            if (StringUtils.isNotEmpty(collegeId)) {
                predicateList.add(cb.equal(joinCollege.get("id").as(String.class),collegeId));
            }
            predicateList.add(cb.isTrue(joinCourse.get("useState")));
            predicateList.add(cb.equal(root.get("structureStatus").as(String.class),0));
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<Course> getFoundationCourseSpecification(String courseName) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!StringUtils.isEmpty(courseName)) {
                String name = CharactersReplace.replaceCharacters(courseName).trim();
                predicateList.add(cb.like(root.get("courseName").as(String.class), "%" + name + "%"));
            }
            predicateList.add(cb.isTrue(root.get("useState")));

            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("courseCode")));
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

}

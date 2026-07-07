package com.lztech.persistence.repositories.group.specification;

import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class GroupSpecification {

    public static Specification<Group> getTeacherGroupSpecification(String teacherId, String schoolYear,
                                                                    Integer term, String courseId) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> join = root.join("courseTableList", JoinType.INNER);
            Join<Object, Object> courseTableDetailJoin = join.join("courseTableDetailList", JoinType.LEFT);
            if (!StringUtils.isEmpty(teacherId)) {
                predicateList.add(cb.equal(courseTableDetailJoin
                        .join("courseTableDetailTeacherList", JoinType.LEFT).get("teacherId").as(String.class), teacherId));
            }

            if (!StringUtils.isEmpty(courseId)) {
                predicateList.add(cb.equal(join.join("course", JoinType.LEFT).get("id").as(String.class), courseId));
            }

            if (!StringUtils.isEmpty(schoolYear)) {
                predicateList.add(cb.equal(join.get("schoolYear").as(String.class), schoolYear));
            }

            if (!StringUtils.isEmpty(term)) {
                predicateList.add(cb.equal(join.get("term").as(Integer.class), term));
            }

            predicateList.add(cb.equal(root.get("groupStatus").as(GroupStatus.class), GroupStatus.NORMAL));
            Predicate[] predicates = new Predicate[predicateList.size()];
            query.groupBy(root.get("id"));
            query.distinct(true);
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.desc(root.get("createTime")));
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<Group> getGroupInfoSpecification(String groupNo, String groupName, String creatorName) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(groupNo)) {
                predicateList.add(cb.like(root.get("groupNo"), "%" + groupNo.trim() + "%"));
            }
            if (!StringUtils.isEmpty(groupName)) {
                predicateList.add(cb.like(root.get("groupName"), "%" + groupName.trim() + "%"));
            }
            if (!StringUtils.isEmpty(creatorName)) {
                predicateList.add(cb.like(root.get("creatorName"), "%" + creatorName.trim() + "%"));
            }
            predicateList.add(cb.equal(root.get("groupStatus").as(GroupStatus.class), GroupStatus.NORMAL));
            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.desc(root.get("createTime")));
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }


}

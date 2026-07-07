package com.lztech.persistence.repositories.classgroupingscheme.specification;

import com.lztech.domain.model.classgroupingscheme.ClassGrouping;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassGroupingSpecification {
    public static Specification<ClassGrouping> specification(String teacherId,
                                                             String className) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (StringUtils.isNotBlank(teacherId)) {
                predicateList.add(cb.equal(root.get("responsibleTeacherId").as(String.class), teacherId));
            }
            if (StringUtils.isNotBlank(className)) {
                predicateList.add(cb.like(root.get("classGroupingName").as(String.class), "%" + className + "%"));
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<ClassGrouping> responsibleSpecification(String groupIds, String responsibleTeacherId,
                                                                        String classGroupId) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> join = root.join("classGroupingScheme", JoinType.INNER);
            if (StringUtils.isNotBlank(groupIds)) {
                String[] groupIdLIst = groupIds.split(",");
                predicateList.add(cb.in(join.get("groupId")).value(Arrays.asList(groupIdLIst)));
            }
            if (StringUtils.isNotBlank(responsibleTeacherId)) {
                predicateList.add(cb.equal(root.get("responsibleTeacherId").as(String.class), responsibleTeacherId));
            }
            if (StringUtils.isNotBlank(classGroupId)) {
                predicateList.add(cb.equal(root.get("id").as(String.class), classGroupId));
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

}

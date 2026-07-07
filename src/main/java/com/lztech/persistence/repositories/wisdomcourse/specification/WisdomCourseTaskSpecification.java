package com.lztech.persistence.repositories.wisdomcourse.specification;

import com.lztech.domain.model.wisdomcourse.WisdomCourseTask;
import com.lztech.domain.model.wisdomcourse.enums.WisdomCourseTaskStatus;
import com.lztech.domain.model.wisdomcourse.enums.WisdomCourseTaskType;
import com.lztech.site.viewmodel.wisdomcourse.WisdomCourseTaskPageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WisdomCourseTaskSpecification {

    public static Specification<WisdomCourseTask> specification(WisdomCourseTaskPageParam wisdomCourseTaskPageParam) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(wisdomCourseTaskPageParam.getWisdomCourseId())) {
                predicateList.add(cb.equal(root.get("wisdomCourse").get("id").as(String.class), wisdomCourseTaskPageParam.getWisdomCourseId()));
            }
            if (Objects.nonNull(wisdomCourseTaskPageParam.getTaskType())) {
                WisdomCourseTaskType wisdomCourseTaskType = WisdomCourseTaskType.getByCode(wisdomCourseTaskPageParam.getTaskType());
                if (Objects.nonNull(wisdomCourseTaskType)) {
                    predicateList.add(cb.equal(root.get("taskType").as(WisdomCourseTaskType.class), wisdomCourseTaskType));
                }
            }
            if (Objects.nonNull(wisdomCourseTaskPageParam.getTaskStatus())) {
                WisdomCourseTaskStatus wisdomCourseTaskStatus = WisdomCourseTaskStatus.getByCode(wisdomCourseTaskPageParam.getTaskStatus());
                if (Objects.nonNull(wisdomCourseTaskStatus)) {
                    predicateList.add(cb.equal(root.get("taskStatus").as(WisdomCourseTaskStatus.class), wisdomCourseTaskStatus));
                }
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.desc(root.get("createTime")));
            query.orderBy(orderList);
            query.distinct(true);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };

    }

    public static Specification<WisdomCourseTask> specification(Map<String,Object> conditions) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Object courseId = conditions.get("courseId");
            if (!ObjectUtils.isEmpty(courseId)){
                predicateList.add(cb.equal(root.join("wisdomCourse", JoinType.INNER).get("courseId")
                        .as(String.class), courseId));
            }
            Object taskType = conditions.get("taskType");
            if (!ObjectUtils.isEmpty(taskType)){
                predicateList.add(root.<WisdomCourseTaskType>get("taskType").in(taskType));
            }
            Object taskStatus = conditions.get("taskStatus");
            if (!ObjectUtils.isEmpty(taskStatus)){
                predicateList.add(root.<WisdomCourseTaskStatus>get("taskStatus").in(taskStatus));
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.desc(root.get("createTime")));
            query.orderBy(orderList);
            query.distinct(true);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };

    }

}

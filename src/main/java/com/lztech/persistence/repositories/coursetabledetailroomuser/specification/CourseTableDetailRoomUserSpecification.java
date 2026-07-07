package com.lztech.persistence.repositories.coursetabledetailroomuser.specification;

import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CourseTableDetailRoomUserSpecification {
    public static Specification<CourseTableDetailRoomUser> courseTableDetailRoomUserSpecification
            (List<String> courseDateList, List<String> segmentIdList) {

        return (Root<CourseTableDetailRoomUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> courseTableDetailJoin = root.join("courseTableDetail", JoinType.INNER);
            Join<Object, Object> courseSegmentJoin = courseTableDetailJoin.join("courseSegmentList", JoinType.INNER)
                    .join("segment", JoinType.LEFT);
            if (courseDateList.size() > 0) {
                predicateList.add(courseTableDetailJoin.get("courseDate").as(String.class).in(courseDateList));
            }

            if (segmentIdList.size() > 0) {
                predicateList.add(courseSegmentJoin.get("segmentPK")
                        .get("segment").as(String.class).in(segmentIdList));
            }

            query.distinct(true);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }
}

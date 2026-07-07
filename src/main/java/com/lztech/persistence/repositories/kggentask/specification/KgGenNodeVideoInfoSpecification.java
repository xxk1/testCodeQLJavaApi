package com.lztech.persistence.repositories.kggentask.specification;

import com.lztech.domain.model.kggentask.KgGenNodeVideoInfo;
import com.lztech.site.viewmodel.kggentask.KgAIGenNodeVideoQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KgGenNodeVideoInfoSpecification {

    public static Specification<KgGenNodeVideoInfo> specification(KgAIGenNodeVideoQueryParam param) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(param.getKgNodeId())) {
                predicateList.add(cb.equal(root.get("knowledgeNodeId").as(String.class), param.getKgNodeId()));
            }
            if (StringUtils.isNotBlank(param.getTeacherId())){
                // 模糊搜索
                predicateList.add(cb.like(root.get("teacherIds").as(String.class), "%" + param.getTeacherId() + "%"));
            }
            if (StringUtils.isNotBlank(param.getSchoolYear())) {
                predicateList.add(cb.equal(root.get("schoolYear").as(String.class), param.getSchoolYear()));
            }
            if (Objects.nonNull(param.getTerm())) {
                predicateList.add(cb.equal(root.get("term").as(Integer.class), param.getTerm()));
            }
            if (Objects.nonNull(param.getSimilarityScore())){
                predicateList.add(cb.or(
                        cb.greaterThanOrEqualTo(root.get("similarityScore"), param.getSimilarityScore()),
                        cb.isNull(root.get("similarityScore"))
                ));
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            query.distinct(true);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };

    }
}

package com.lztech.persistence.repositories.courseknowledgegraph.specification;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeVideoInfoText;
import com.lztech.site.viewmodel.courseresourceintelligentrecommendation.CourseResourceIntelligentRecommendationQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseKnowledgeGraphNodeVideoInfoTextSpecification {

    public static Specification<CourseKnowledgeGraphNodeVideoInfoText> specification(CourseResourceIntelligentRecommendationQueryParam
                                                                                             queryParams){
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (StringUtils.isNotBlank(queryParams.getCourseId())){
                predicateList.add(cb.equal(root.get("courseId").as(String.class), queryParams.getCourseId()));
            }
            if (StringUtils.isNotBlank(queryParams.getCourseKnowledgeGraphNodeId())){
                predicateList.add(cb.equal(root.get("knowledgeNodeId").as(String.class), queryParams.getCourseKnowledgeGraphNodeId()));
            }
            if (StringUtils.isNotBlank(queryParams.getCourseKnowledgeGraphId())){
                predicateList.add(cb.equal(root.join("courseKnowledgeGraphDomain").get("id").as(String.class),
                        queryParams.getCourseKnowledgeGraphId()));
            }
            if (StringUtils.isNotBlank(queryParams.getSchoolYear())){
                predicateList.add(cb.equal(root.get("schoolYear").as(String.class), queryParams.getSchoolYear()));
            }
            if (Objects.nonNull(queryParams.getTerm())){
                predicateList.add(cb.equal(root.get("term").as(Integer.class), queryParams.getTerm()));
            }
            if (Objects.nonNull(queryParams.getSimilarityScore())){
                predicateList.add(cb.or(
                        cb.greaterThanOrEqualTo(root.get("similarityScore"), queryParams.getSimilarityScore()),
                        cb.isNull(root.get("similarityScore"))
                ));
            }

            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }
}

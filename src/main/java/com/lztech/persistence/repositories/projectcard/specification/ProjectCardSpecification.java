package com.lztech.persistence.repositories.projectcard.specification;

import com.lztech.domain.model.projectcard.ProjectCard;
import com.lztech.site.until.CharactersReplace;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProjectCardSpecification {
    public static Specification<ProjectCard> specification(String projectName, String courseId) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(projectName)) {
                String projectNameResult = CharactersReplace.replaceCharacters(projectName);
                predicateList.add(cb.like(root.get("projectName").as(String.class), "%" + projectNameResult.trim() + "%"));
            }
            if (StringUtils.isNotBlank(courseId)) {
                predicateList.add(cb.equal(root.get("courseId").as(String.class), courseId));
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.desc(root.get("modifyTime")));
            query.orderBy(orderList);
            query.distinct(true);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }
}

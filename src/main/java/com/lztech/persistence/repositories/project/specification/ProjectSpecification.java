package com.lztech.persistence.repositories.project.specification;

import com.lztech.domain.model.project.Project;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.viewmodel.project.ProjectQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProjectSpecification {

    public static Specification<Project> specification(ProjectQueryParam param) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!StringUtils.isEmpty(param.getProjectName())) {
                predicateList.add(cb.like(root.get("projectName").as(String.class)
                        , "%" + CharactersReplace.replaceCharacters(param.getProjectName()).trim() + "%"));
            }

            if (param.getProjectTypeValue() != null) {
                predicateList.add(cb.equal(root.get("projectType").as(Integer.class)
                        , param.getProjectTypeValue()));
            }

            if (!StringUtils.isEmpty(param.getCourseId())) {
                predicateList.add(cb.equal(root.join("projectAttributeList", JoinType.LEFT)
                        .get("dataId").as(String.class), param.getCourseId()));
            }

            if (!StringUtils.isEmpty(param.getProjectClassificationId())) {
                predicateList.add(cb.equal(root.get("projectClassificationId").as(String.class)
                        , param.getProjectClassificationId()));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("projectCode")));
            query.orderBy(orderList);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }


}

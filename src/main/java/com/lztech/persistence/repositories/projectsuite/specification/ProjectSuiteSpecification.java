package com.lztech.persistence.repositories.projectsuite.specification;

import com.lztech.domain.model.projectsuite.ProjectSuite;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteStatus;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteType;
import com.lztech.site.until.CharactersReplace;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProjectSuiteSpecification {
    public static Specification<ProjectSuite> specification(String projectSuiteName, String projectSuiteType) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(projectSuiteName)) {
                String projectSuiteNameResult = CharactersReplace.replaceCharacters(projectSuiteName);
                predicateList.add(cb.like(root.get("projectSuiteName").as(String.class), "%" + projectSuiteNameResult.trim() + "%"));
            }
            predicateList.add(cb.equal(root.get("suiteStatus").as(ProjectSuiteStatus.class), ProjectSuiteStatus.NORMAL));
            if (StringUtils.isNotBlank(projectSuiteType)) {
                ProjectSuiteType suiteType = ProjectSuiteType.getProjectSuiteType(Integer.valueOf(projectSuiteType));
                predicateList.add(cb.equal(root.get("suiteType").as(ProjectSuiteType.class), suiteType));
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

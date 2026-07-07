package com.lztech.persistence.repositories.groupmember.specification;

import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.site.until.CharactersReplace;
import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class GroupMemberSpecification {
    public static Specification<GroupMember> specification(List<String> groupIds, String userNoOrUserName) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!CollectionUtils.isEmpty(groupIds)) {
                predicateList.add(root.join("group", JoinType.LEFT).get("id").as(String.class).in(groupIds));
            }
            if (StringUtils.isNotBlank(userNoOrUserName)) {
                String userNoOrUserNameResult = CharactersReplace.replaceCharacters(userNoOrUserName);
                predicateList.add(cb.or(cb.like(root.get("studentNo").as(String.class), "%" + userNoOrUserNameResult.trim() + "%"),
                        cb.like(root.get("studentName").as(String.class), "%" + userNoOrUserNameResult.trim() + "%")));
            }
            predicateList.add(cb.equal(root.join("group", JoinType.INNER)
                    .get("groupStatus").as(GroupStatus.class), GroupStatus.NORMAL));
            predicateList.add(cb.equal(root.get("groupMemberStatus").as(GroupMemberStatus.class), GroupMemberStatus.NORMAL));

            Predicate[] predicates = new Predicate[predicateList.size()];
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            orderList.add(cb.asc(root.get("studentNo")));
            query.orderBy(orderList);
            query.distinct(true);
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }
}

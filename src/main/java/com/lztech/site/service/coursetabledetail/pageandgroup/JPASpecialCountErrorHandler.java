package com.lztech.site.service.coursetabledetail.pageandgroup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public abstract class JPASpecialCountErrorHandler {
    @Value("${attendClassAdvanceTime}")
    public Integer attendClassAdvanceTime;
    @Value("${englishCollegeName}")
    public String englishCollege;
    @Value("${peCollegeName}")
    public String peCollege;
    @Value("${schoolName}")
    public String schoolName;
    @Value("${request.address.classRoom}")
    public String classRoom;
    @Value("${validKey}")
    public String validKey;
    @Value("${signKey}")
    public String signKey;
    /**
     * 获取EntityManager实现
     *
     * @return
     */
    protected abstract EntityManager getEm();


    /**
     * 获取分页数据
     *
     * @param content  当前页面条目数
     * @param pageable 分页信息
     * @param count    真实总数
     * @return
     */
    protected <S> PageImpl getPage(List<S> content, Pageable pageable, int count) {
        return new PageImpl(content, pageable, (long) count);
    }

    /**
     * 获取正确的count计数
     *
     * @param spec
     * @param domainClass
     * @param <S>
     * @return
     */
    protected <S> int getCount(org.springframework.data.jpa.domain.Specification<S> spec, Class<S> domainClass) {
        return getCountQuery(spec, domainClass).getResultList().size();
    }

    /**
     * 来自源码，获取TypedQuery
     * org\springframework\data\jpa\repository\support\SimpleJpaRepository.class
     *
     * @param spec
     * @param domainClass
     * @param <S>
     * @return
     */
    protected <S> TypedQuery<Long> getCountQuery(org.springframework.data.jpa.domain.Specification<S> spec, Class<S> domainClass) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<S> root = this.applySpecificationToCriteria(spec, domainClass, (CriteriaQuery<S>) query);
        if (query.isDistinct()) {
            query.select(builder.countDistinct(root));
        } else {
            query.select(builder.count(root));
        }
        query.orderBy(Collections.emptyList());
        return getEm().createQuery(query);
    }

    /**
     * 来自源码，翻译spec
     * org\springframework\data\jpa\repository\support\SimpleJpaRepository.class
     *
     * @param spec
     * @param domainClass
     * @param query
     * @param <S>
     * @return
     */
    private <S> Root<S> applySpecificationToCriteria(org.springframework.data.jpa.domain.Specification<S> spec, Class<S> domainClass,
                                                     CriteriaQuery<S> query) {
        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(query, "CriteriaQuery must not be null!");
        Root<S> root = query.from(domainClass);
        if (spec == null) {
            return root;
        } else {
            CriteriaBuilder builder = getEm().getCriteriaBuilder();
            Predicate predicate = spec.toPredicate(root, query, builder);
            if (predicate != null) {
                query.where(predicate);
            }
            return root;
        }
    }

}

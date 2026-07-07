package com.lztech.persistence.repositories.course.specification;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseResource;
import com.lztech.domain.model.course.CourseStructure;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.domain.model.course.enums.StructureStatus;
import com.lztech.site.until.CharactersReplace;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseResourceSpecification {
    public static Specification<CourseResource> specification(String creatorId,
                                                              String courseId,
                                                              String name, boolean isIsPublic,
                                                              CourseVersion courseVersion, CourseStructure courseStructure) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinCourseStructure = root.join("courseStructure", JoinType.INNER);

            if (StringUtils.isNotBlank(name)) {
                predicateList.add(cb.equal(root.get("resourceName").as(String.class), name));
            }
            predicateList.add(cb.equal(root.get("courseStructure").as(CourseStructure.class), courseStructure));
            predicateList.add(cb.equal(joinCourseStructure.get("course").get("id").as(String.class), courseId));
            if (Objects.nonNull(courseVersion)) {
                predicateList.add(cb.equal(joinCourseStructure.get("courseVersion").as(CourseVersion.class), courseVersion));
            }
            if (isIsPublic) {
                predicateList.add(cb.equal(root.get("isPublic").as(Boolean.class), true));
            } else {
                predicateList.add(cb.equal(root.get("creatorId").as(String.class), creatorId));
                predicateList.add(cb.equal(root.get("isPublic").as(Boolean.class), false));
            }
            predicateList.add(cb.or(cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.MICRO_VIDEO),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.IMAGE),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.TEACHING_COURSE_WARE),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.CLASS_TEST),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.AUDIO)));
            predicateList.add(cb.equal(root.get("resourceStatus").as(ResourceStatus.class), ResourceStatus.NORMAL));
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }
    public static Specification<CourseResource> specificationOwnResources(String creatorId,
                                                              String courseId,
                                                              String name,
                                                              CourseVersion courseVersion, CourseStructure courseStructure) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinCourseStructure = root.join("courseStructure", JoinType.INNER);

            if (StringUtils.isNotBlank(name)) {
                predicateList.add(cb.equal(root.get("resourceName").as(String.class), name));
            }
            predicateList.add(cb.equal(root.get("courseStructure").as(CourseStructure.class), courseStructure));
            predicateList.add(cb.equal(joinCourseStructure.get("course").get("id").as(String.class), courseId));
            if (Objects.nonNull(courseVersion)) {
                predicateList.add(cb.equal(joinCourseStructure.get("courseVersion").as(CourseVersion.class), courseVersion));
            }
            predicateList.add(cb.equal(root.get("creatorId").as(String.class), creatorId));
            predicateList.add(cb.or(cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.MICRO_VIDEO),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.IMAGE),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.TEACHING_COURSE_WARE),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.CLASS_TEST)));
            predicateList.add(cb.equal(root.get("resourceStatus").as(ResourceStatus.class), ResourceStatus.NORMAL));
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }


    public static Specification<CourseResource> specificationAllResources(String creatorId,
                                                              String courseId,
                                                              String name,
                                                              CourseVersion courseVersion, List<CourseStructure> courseStructures) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinCourseStructure = root.join("courseStructure", JoinType.INNER);

            if(StringUtils.isNotBlank(creatorId)){
                predicateList.add(cb.equal(root.get("creatorId").as(String.class), creatorId));
            }
            if (StringUtils.isNotBlank(name)) {
                predicateList.add(cb.equal(root.get("resourceName").as(String.class), name));
            }
            if(CollectionUtils.isNotEmpty(courseStructures)){
                predicateList.add(cb.in(root.get("courseStructure")).value(courseStructures));
            }

            predicateList.add(cb.equal(joinCourseStructure.get("course").get("id").as(String.class), courseId));
            if (Objects.nonNull(courseVersion)) {
                predicateList.add(cb.equal(joinCourseStructure.get("courseVersion").as(CourseVersion.class), courseVersion));
            }
            predicateList.add(cb.or(cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.MICRO_VIDEO),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.IMAGE),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.TEACHING_COURSE_WARE),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.CLASS_TEST),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.AUDIO)));
            predicateList.add(cb.equal(root.get("resourceStatus").as(ResourceStatus.class), ResourceStatus.NORMAL));
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseResource> specificationlike(String creatorId,
                                                              String courseId,
                                                              String name, boolean isIsPublic,
                                                              CourseVersion courseVersion,CourseStructure courseStructure) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinCourseStructure = root.join("courseStructure", JoinType.INNER);

            if (StringUtils.isNotBlank(name)) {
                String projectName = CharactersReplace.replaceCharacters(name);
                predicateList.add(cb.like(root.get("resourceName").as(String.class), "%" + projectName.trim() + "%"));
            }
            predicateList.add(cb.equal(root.get("courseStructure").as(CourseStructure.class), courseStructure));
            predicateList.add(cb.equal(joinCourseStructure.get("course").get("id").as(String.class), courseId));
            if (Objects.nonNull(courseVersion)) {
                predicateList.add(cb.equal(joinCourseStructure.get("courseVersion").as(CourseVersion.class), courseVersion));
            }
            if (isIsPublic) {
                predicateList.add(cb.equal(root.get("isPublic").as(Boolean.class), true));
            } else {
                predicateList.add(cb.equal(root.get("creatorId").as(String.class), creatorId));
                predicateList.add(cb.equal(root.get("isPublic").as(Boolean.class), false));
            }
            predicateList.add(cb.or(cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.MICRO_VIDEO),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.IMAGE),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.TEACHING_COURSE_WARE),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.CLASS_TEST),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.AUDIO)));
            predicateList.add(cb.equal(root.get("resourceStatus").as(ResourceStatus.class), ResourceStatus.NORMAL));
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseResource> testPaperSpecification(String creatorId, Course course,
                                                                       String name, Boolean isPublic,
                                                                       CourseVersion courseVersion) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinCourseStructure = root.join("courseStructure", JoinType.INNER);

            if (StringUtils.isNotBlank(name)) {
                predicateList.add(cb.equal(root.get("resourceName").as(String.class), name));
            }
            if (isPublic) {
                predicateList.add(cb.equal(root.get("isPublic").as(Boolean.class), true));
            } else {
                predicateList.add(cb.equal(root.get("isPublic").as(Boolean.class), false));
                if (StringUtils.isNotBlank(creatorId)) {
                    predicateList.add(cb.equal(root.get("creatorId").as(String.class), creatorId));
                }
            }
            predicateList.add(cb.equal(joinCourseStructure.get("course").as(Course.class), course));
            if (ObjectUtils.isNotEmpty(courseVersion)) {
                predicateList.add(cb.equal(joinCourseStructure.get("courseVersion").as(CourseVersion.class), courseVersion));
            }
            predicateList.add(cb.equal(root.get("resourceStatus").as(ResourceStatus.class), ResourceStatus.NORMAL));
            predicateList.add(cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.CLASS_TEST));
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseResource> testPaperSpecificationlike(String creatorId, Course course,
                                                                       String name, Boolean isPublic,
                                                                       CourseVersion courseVersion) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinCourseStructure = root.join("courseStructure", JoinType.INNER);

            if (StringUtils.isNotBlank(name)) {
                String projectName = CharactersReplace.replaceCharacters(name);
                predicateList.add(cb.like(root.get("resourceName").as(String.class), "%" + projectName.trim() + "%"));
            }
            if (isPublic) {
                predicateList.add(cb.equal(root.get("isPublic").as(Boolean.class), true));
            } else {
                predicateList.add(cb.equal(root.get("isPublic").as(Boolean.class), false));
                if (StringUtils.isNotBlank(creatorId)) {
                    predicateList.add(cb.equal(root.get("creatorId").as(String.class), creatorId));
                }
            }
            predicateList.add(cb.equal(joinCourseStructure.get("course").as(Course.class), course));
            if (ObjectUtils.isNotEmpty(courseVersion)) {
                predicateList.add(cb.equal(joinCourseStructure.get("courseVersion").as(CourseVersion.class), courseVersion));
            }
            predicateList.add(cb.equal(root.get("resourceStatus").as(ResourceStatus.class), ResourceStatus.NORMAL));
            predicateList.add(cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.CLASS_TEST));
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseResource> getTestPaperSpecification(String creatorId, String courseId,CourseVersion courseVersion,
                                                                          String testName) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinCourseStructure = root.join("courseStructure", JoinType.INNER);
            predicateList.add(cb.equal(root.get("creatorId").as(String.class), creatorId));
            predicateList.add(cb.equal(joinCourseStructure.get("course").get("id").as(String.class), courseId));
            predicateList.add(cb.equal(root.get("resourceStatus").as(ResourceStatus.class), ResourceStatus.NORMAL));
            predicateList.add(cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.CLASS_TEST));
            if (!StringUtils.isEmpty(testName)) {
                String name = CharactersReplace.replaceCharacters(testName).trim();
                predicateList.add(cb.like(root.get("resourceName").as(String.class), "%" + name + "%"));
            }
            if (ObjectUtils.isNotEmpty(courseVersion)) {
                predicateList.add(cb.equal(joinCourseStructure.get("courseVersion").as(CourseVersion.class), courseVersion));
            }
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }

    public static Specification<CourseResource> getSpecification(String creatorId, String courseId) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            Join<Object, Object> joinCourseStructure = root.join("courseStructure", JoinType.INNER);
            predicateList.add(cb.equal(joinCourseStructure.get("course").get("id").as(String.class), courseId));
            predicateList.add(cb.equal(joinCourseStructure.get("structureStatus").as(StructureStatus.class), StructureStatus.NORMAL));
            predicateList.add(cb.or(cb.equal(root.get("creatorId").as(String.class), creatorId),
                    cb.equal(root.get("isPublic").as(Boolean.class), false)));
            predicateList.add(cb.equal(root.get("resourceStatus").as(ResourceStatus.class), ResourceStatus.NORMAL));
            predicateList.add(cb.or(cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.MICRO_VIDEO),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.IMAGE),
                    cb.equal(root.get("resourceType").as(ResourceType.class), ResourceType.TEACHING_COURSE_WARE)));
            List<Order> orderList = new ArrayList<>();
            orderList.add(cb.asc(root.get("id")));
            query.orderBy(orderList);
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
    }
}

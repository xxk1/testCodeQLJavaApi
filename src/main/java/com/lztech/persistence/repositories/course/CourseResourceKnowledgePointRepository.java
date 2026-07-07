package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.CourseResourceKnowledgePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseResourceKnowledgePointRepository extends JpaRepository<CourseResourceKnowledgePoint, String> {


    @Modifying
    @Transactional
    @Query(value = "delete from tb_course_resource_knowledge_point where resource_id=?1 ", nativeQuery = true)
    void deleteByResourceId(String resourceId);

    @Modifying
    @Transactional
    @Query(value = "delete from tb_course_resource_knowledge_point where knowledge_point_id=?1 ", nativeQuery = true)
    void deleteByPointId(String pointId);

    @Modifying
    @Transactional
    @Query(value = "delete from tb_course_resource_knowledge_point where resource_id in(?1)", nativeQuery = true)
    void deleteByResourceIdList(List<String> resourceIdList);

    List<CourseResourceKnowledgePoint> findByResourceId(String resourceId);

    @Modifying
    @Transactional
    @Query(value = "delete from tb_course_resource_knowledge_point where knowledge_point_id in (?1) ", nativeQuery = true)
    void deleteByPointIdIn(List<String> pointIdList);

    List<CourseResourceKnowledgePoint> findByKnowledgePointId(String pointId);

    @Modifying
    @Transactional
    @Query(value = "delete from tb_course_resource_knowledge_point where knowledge_point_id in (?1) and resource_id in(?2)", nativeQuery = true)
    Integer deleteByKnowledgePointIdAndResourceId(String knowledgePointId,String resourceId);

    List<CourseResourceKnowledgePoint> findByKnowledgePointIdIn(List<String> nodeIdList);
}

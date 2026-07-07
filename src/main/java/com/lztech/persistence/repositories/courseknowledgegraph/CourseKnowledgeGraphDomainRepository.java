package com.lztech.persistence.repositories.courseknowledgegraph;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseKnowledgeGraphDomainRepository extends JpaRepository<CourseKnowledgeGraphDomain, String> {

    List<CourseKnowledgeGraphDomain> findByCourse(Course course);

    @Query(nativeQuery = true, value = "SELECT a.* FROM tb_course_knowledge_graph_domain a INNER JOIN " +
            " tb_course_version b on a.course_version_id = b.id where b.course_id=:courseId " +
            "AND b.course_version_status = 1 AND a.`status` = 1 limit 1")
    CourseKnowledgeGraphDomain getCourseKnowledgeGraphDomainByCourseId(@Param("courseId") String courseId);

    @Query(nativeQuery = true, value = "SELECT a.* FROM tb_course_knowledge_graph_domain a  INNER JOIN " +
            " tb_course_version b on a.course_version_id = b.id  " +
            " WHERE b.course_version_status = 1 and a.`status` = 1 AND a.version_label = (   SELECT MAX(version_label) " +
            " FROM tb_course_knowledge_graph_domain WHERE id = a.id AND status = 1  ) and a.course_id =:courseId limit 1 ")
    CourseKnowledgeGraphDomain getEffectiveCourseKnowledgeGraphDomainByCourseId(@Param("courseId") String courseId);


    @Query(nativeQuery = true, value = "SELECT a.* FROM tb_course_knowledge_graph_domain a  INNER JOIN " +
            " tb_course_version b on a.course_version_id = b.id  " +
            " WHERE b.course_version_status = 1 and a.`status` = 1 AND a.version_label = (   SELECT MAX(version_label) " +
            " FROM tb_course_knowledge_graph_domain WHERE id = a.id AND status = 1  ) " +
            " and a.id =:courseKnowledgeGraphDomainId limit 1 ")
    CourseKnowledgeGraphDomain getEffectiveCourseKnowledgeGraphDomainByCourseKnowledgeGraphDomainId(
            @Param("courseKnowledgeGraphDomainId") String courseKnowledgeGraphDomainId);



    @Query(nativeQuery = true, value = "SELECT a.* FROM tb_course_knowledge_graph_domain a INNER JOIN " +
            " tb_course_version b on a.course_version_id = b.id where " +
            " b.course_version_status = 1 AND a.`status` = 1 ")
    List<CourseKnowledgeGraphDomain> getAllEffectiveDataList();

    @Query(nativeQuery = true, value = "SELECT * FROM tb_course_knowledge_graph_domain a where id =:id limit 1 ")
    CourseKnowledgeGraphDomain getCourseKnowledgeGraphDomainById(@Param("id") String id);

    @Query(nativeQuery = true, value = "SELECT a.version_label FROM tb_course_knowledge_graph_domain a INNER JOIN " +
            " tb_course_version b on a.course_version_id = b.id where b.course_id=:courseId " +
            "AND b.course_version_status = 1 order by a.show_order desc limit 1 ")
    String getLastVersionLabel(String courseId);

    List<CourseKnowledgeGraphDomain> findByCourseAndCourseVersionOrderByShowOrderAsc(Course course, CourseVersion courseVersion);

    @Query(nativeQuery = true, value = "select * from  tb_course_knowledge_graph_domain" +
            " where  course_id =:courseId and course_version_id =:courseVersionId and status = 1 ")
    List<CourseKnowledgeGraphDomain> getCourseKnowledgeGraphDomainByCourseIdAndCourseVersionId(
            @Param("courseId") String courseId,@Param("courseVersionId") String courseVersionId);

    @Query(nativeQuery = true, value = "select * from  tb_course_knowledge_graph_domain" +
            " where  course_id =:courseId and course_version_id =:courseVersionId and status = 0 order by show_order")
    List<CourseKnowledgeGraphDomain> getOldCourseKnowledgeGraphDomainByCourseIdAndCourseVersionId(
            @Param("courseId") String courseId,@Param("courseVersionId") String courseVersionId);
}

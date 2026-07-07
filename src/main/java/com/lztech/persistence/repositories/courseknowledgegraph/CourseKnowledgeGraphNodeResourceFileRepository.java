package com.lztech.persistence.repositories.courseknowledgegraph;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeResourceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseKnowledgeGraphNodeResourceFileRepository extends JpaRepository<CourseKnowledgeGraphNodeResourceFile, String> {

    List<CourseKnowledgeGraphNodeResourceFile> findByIdIn(List<String> ids);


    @Query(nativeQuery = true, value = "SELECT * FROM tb_course_knowledge_graph_node_resource_file a where id =:id limit 1 ")
    CourseKnowledgeGraphNodeResourceFile getCourseKnowledgeGraphNodeResourceFileById(@Param("id") String id);
}

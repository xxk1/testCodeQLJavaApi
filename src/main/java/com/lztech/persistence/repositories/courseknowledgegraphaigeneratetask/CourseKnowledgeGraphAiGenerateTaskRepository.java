package com.lztech.persistence.repositories.courseknowledgegraphaigeneratetask;

import com.lztech.domain.model.knowledgegraphaigeneratetask.CourseKnowledgeGraphAiGenerateTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseKnowledgeGraphAiGenerateTaskRepository extends
        JpaRepository<CourseKnowledgeGraphAiGenerateTask, String>, JpaSpecificationExecutor<CourseKnowledgeGraphAiGenerateTask> {

    CourseKnowledgeGraphAiGenerateTask findFirstByCourseId(String courseId);
}

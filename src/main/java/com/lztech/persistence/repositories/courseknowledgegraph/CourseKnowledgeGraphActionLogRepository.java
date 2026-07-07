package com.lztech.persistence.repositories.courseknowledgegraph;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseKnowledgeGraphActionLogRepository extends JpaRepository<CourseKnowledgeGraphActionLog, String> {

}

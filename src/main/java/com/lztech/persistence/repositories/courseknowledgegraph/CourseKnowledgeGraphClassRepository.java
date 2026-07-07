package com.lztech.persistence.repositories.courseknowledgegraph;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseKnowledgeGraphClassRepository extends JpaRepository<CourseKnowledgeGraphClass, String> {
    List<CourseKnowledgeGraphClass> findByCourseIdAndSchoolYearAndTerm(String courseId, String schoolYear, Integer term);
    List<CourseKnowledgeGraphClass> findByCourseIdAndSchoolYearAndTermAndGroupIdIn(
            String courseId, String schoolYear, Integer term,List<String> groupIds);

}

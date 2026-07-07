package com.lztech.persistence.repositories.wisdomcourse;

import com.lztech.domain.model.wisdomcourse.WisdomCourseTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WisdomCourseTaskRepository extends JpaRepository<WisdomCourseTask, String>, JpaSpecificationExecutor {

    @Query(nativeQuery = true, value = "SELECT * FROM tb_wisdom_course_task WHERE wisdom_course_id IN (:wisdomCourseIdList)")
    List<WisdomCourseTask> findByWisdomCourseIdIn(@Param("wisdomCourseIdList") List<String> wisdomCourseIdList);

    List<WisdomCourseTask> findByIdIn(List<String> idList);
}

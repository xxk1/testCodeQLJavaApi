package com.lztech.persistence.repositories.wisdomcourse;

import com.lztech.domain.model.wisdomcourse.WisdomCourseHistoryTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WisdomCourseHistoryTermRepository extends JpaRepository<WisdomCourseHistoryTerm, String> {
}

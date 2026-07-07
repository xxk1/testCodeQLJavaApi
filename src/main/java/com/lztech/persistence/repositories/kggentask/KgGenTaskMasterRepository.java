package com.lztech.persistence.repositories.kggentask;

import com.lztech.domain.model.kggentask.KgGenTaskMaster;
import com.lztech.domain.model.kggentask.enums.KgGenTaskMasterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KgGenTaskMasterRepository extends JpaRepository<KgGenTaskMaster, String> {

    List<KgGenTaskMaster> findByCourseIdAndStatusIn(String courseId,
                                                    List<KgGenTaskMasterStatus> statuses);

    @Query(nativeQuery = true, value = "SELECT * FROM kg_gen_task_master WHERE course_id = ?1 AND status = ?2 LIMIT 1 ")
    List<KgGenTaskMaster> findByCourseIdAndStatusLimitOne(String courseId, int status);
}

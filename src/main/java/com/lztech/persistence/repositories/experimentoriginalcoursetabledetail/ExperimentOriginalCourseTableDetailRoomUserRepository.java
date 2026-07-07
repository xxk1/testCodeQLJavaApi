package com.lztech.persistence.repositories.experimentoriginalcoursetabledetail;

import com.lztech.domain.model.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetailRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentOriginalCourseTableDetailRoomUserRepository extends JpaRepository<ExperimentOriginalCourseTableDetailRoomUser, String>,
        JpaSpecificationExecutor<ExperimentOriginalCourseTableDetailRoomUser> {
}

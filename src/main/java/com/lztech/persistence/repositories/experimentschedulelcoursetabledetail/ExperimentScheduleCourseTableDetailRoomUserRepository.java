package com.lztech.persistence.repositories.experimentschedulelcoursetabledetail;

import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetail;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetailRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExperimentScheduleCourseTableDetailRoomUserRepository extends JpaRepository<ExperimentScheduleCourseTableDetailRoomUser, String>,
        JpaSpecificationExecutor<ExperimentScheduleCourseTableDetailRoomUser> {
    List<ExperimentScheduleCourseTableDetailRoomUser> findAllByExperimentScheduleCourseTableDetailIdIn(List<String> ids);

    @Transactional
    @Modifying
    void deleteByExperimentScheduleCourseTableDetail(ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail);
}

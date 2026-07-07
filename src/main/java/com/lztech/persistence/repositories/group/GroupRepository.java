package com.lztech.persistence.repositories.group;

import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group, String>, JpaSpecificationExecutor {
    Group findByIdAndGroupStatus(String id, GroupStatus groupStatus);

    Group findByGroupNo(String groupNo);

    List<Group> findByIdIn(List<String> ids);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = " update tb_group set group_status= 1 " +
            " where id in (select group_id from tb_course_table where course_id=?1)")
    void updateGroupStatusByCourseId(String courseId);

    @Query(nativeQuery = true, value = "select * from tb_group " +
            " where group_no like CONCAT('%',?1,'%')  order by group_no desc limit 1 ")
    Group findByGroupNoLike(String groupNo);

    List<Group> findByIdInAndGroupStatus(List<String> ids, GroupStatus groupStatus);

    @Query(nativeQuery = true, value = "select * from tb_group")
    List<Group> findAllData();

    List<Group> findByGroupNoInAndGroupStatus(List<String> groupNoList, GroupStatus groupStatus);
}

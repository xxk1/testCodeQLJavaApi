package com.lztech.persistence.repositories.groupmember;

import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, String>, JpaSpecificationExecutor {

    @Query(nativeQuery = true, value = "select * from tb_group_member where group_id=:groupId and group_member_status = '0' ")
    List<GroupMember> findByGroupId(@Param("groupId") String groupId);

    @Query(nativeQuery = true, value = "select count(1) from tb_group_member where group_id=:groupId and group_member_status = '0' ")
    int countByGroupId(@Param("groupId") String groupId);

    List<GroupMember> findByGroup(Group group);

    @Query(nativeQuery = true, value = "select * from tb_group_member where group_id=:groupId and group_member_status = '1' and student_id =:userId ")
    GroupMember findByGroupIdAndUserId(@Param("groupId") String groupId, @Param("userId") String userId);

    @Query(nativeQuery = true, value = "select * from tb_group_member where group_id=:groupId and group_member_status = '1' and open_id =:openId ")
    GroupMember findByGroupIdAndOpenId(@Param("groupId") String groupId, @Param("openId") String openId);

    @Query(nativeQuery = true, value = "select * from tb_group_member where group_id=:groupId and group_member_status = '0' ORDER BY student_no")
    List<GroupMember> findByGroupIdOrderByStudentNo(@Param("groupId") String groupId);

    @Query(nativeQuery = true, value = "SELECT * from tb_group_member where group_id in " +
            "(SELECT id from tb_group where group_name = :groupName) and group_member_status = '0' GROUP BY student_no ORDER BY student_no ")
    List<GroupMember> findByGroupName(@Param("groupName") String groupName);

    GroupMember findByOpenIdAndGroup(String openId, Group group);

    GroupMember findByOpenIdAndGroupId(String openId, String groupId);
    GroupMember findByOpenIdAndGroupIdAndGroupMemberStatus(String openId, String groupId,GroupMemberStatus groupMemberStatus);

    GroupMember findByStudentIdAndGroupId(String studentId, String groupId);

    GroupMember findByStudentIdAndGroupIdAndGroupMemberStatus(String studentId,String groupId,GroupMemberStatus groupMemberStatus);

    GroupMember findByStudentNoAndGroup(String studentNo, Group group);

    GroupMember findByStudentIdAndGroup(String studentNo, Group group);

    List<GroupMember> findByGroupAndGroupMemberStatus(Group group, GroupMemberStatus groupMemberStatus);

    List<GroupMember> findByGroupIdInAndGroupMemberStatus(List<String> groupId, GroupMemberStatus groupMemberStatus);


    @Query(nativeQuery = true, value = "select * from tb_group_member where group_id = :groupId and group_member_status ='0'")
    List<GroupMember> findByStudentByGroupId(@Param("groupId") String groupId);

    int countByGroupIdAndGroupMemberStatus(String groupId, GroupMemberStatus groupMemberStatus);

    @Query(nativeQuery = true, value = "SELECT count(member.id) " +
            " FROM tb_group g INNER JOIN tb_group_member member ON g.id = member.group_id " +
            " INNER JOIN tb_course_table ct ON ct.group_id = g.id " +
            " INNER JOIN tb_course_table_detail ctd on ctd.course_table_id = ct.id and ctd.course_date=CURRENT_DATE() " +
            " inner join tb_course_table_detail_room_user tctdru on ctd.id = tctdru.course_table_detail_id" +
            " WHERE g.group_status=0 AND  CONCAT(ctd.course_date,' ',ctd.segment_start_time) <= ?1 " +
            " AND CONCAT(ctd.course_date,' ',ctd.segment_end_time) >= ?1 and tctdru.room_id is not null ")
    int getInClassStudentNum(String nowDateTime);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_group_member WHERE group_id IN :groupIdList and group_member_status= '0' " +
            " AND (student_no LIKE %:studentNoOrName% OR student_name LIKE %:studentNoOrName%);")
    List<GroupMember> getGroupMemberByStudentNoOrNameIsLike(List<String> groupIdList, String studentNoOrName);

    GroupMember findByStudentIdAndGroupAndGroupMemberStatus(String studentNo, Group group, GroupMemberStatus groupMemberStatus);

    @Transactional
    @Modifying
    @Query(value = "update tb_group_member" +
            " set group_member_status= 1," +
            " modifier_id = ?3, " +
            " modifier_name = ?4, " +
            " modify_time = now() " +
            " where student_id in ?1" +
            " and group_id = ?2" +
            " and group_member_status = 0", nativeQuery = true)
    void deleteByStudentIdInAndGroupId(List<String> studentNos, String groupId, String teacherId, String teacherName);

    @Query(nativeQuery = true, value = "select * from tb_group_member where group_id=?1 and group_member_status=0 " +
            " and student_id not in (" +
            " select student_id from tb_experimental_group_member where experimental_group_id  in (" +
            " select id from tb_experimental_group a where a.group_id=?1 and use_state=1))")
    List<GroupMember> getGroupUnassignedExperimentalGroupList(String groupId);

    List<GroupMember> findByGroupAndStudentIdIn(Group group,List<String> studentIds);

    List<GroupMember> findByGroupIsIn(List<Group> group);
    @Query(nativeQuery = true, value = "select * from tb_group_member where group_id=:groupId and group_member_status = '0' and student_id =:userId ")
    GroupMember findValidByGroupIdAndUserId(@Param("groupId") String groupId, @Param("userId") String userId);


    @Transactional
    @Modifying
    @Query(value = "update tb_group_member" +
            " set group_member_status= 1," +
            " modifier_id = ?2, " +
            " modifier_name = ?3, " +
            " modify_time = now() " +
            " where id in ?1" +
            " and group_member_status = 0", nativeQuery = true)
    void deleteByIds(List<String> ids, String operatorId, String operatorName);

    @Query(nativeQuery = true, value = "select distinct group_id from tb_group_member where group_member_status = '0' and " +
            " student_id = :studentId ")
    List<String> findGroupIdByStudentId(String studentId);

    @Query(nativeQuery = true, value = "select student_id from tb_group_member where group_id=:groupId and group_member_status = '0' ")
    List<String> findUserIdsByGroupId(@Param("groupId") String groupId);
}

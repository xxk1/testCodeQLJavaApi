package com.lztech.site.service.groupcard;

import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberSource;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.site.service.event.EventService;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.event.GroupMemberEvent;
import com.lztech.site.viewmodel.group.GroupCardData;
import com.lztech.site.viewmodel.group.GroupMemberVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupCardService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private EventService eventService;

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${groupMqConfig.exchange}")
    private String exchange;

    public ResponseEntity<Void> joinGroup(GroupCardData groupCardData) {
        Group group = this.groupRepository.findById(groupCardData.getGroupId()).orElse(null);
        if (group == null) {
            return new ResponseEntity(ErrorResult.customError("未找到班级信息"), HttpStatus.NOT_FOUND);
        }
        if (this.getBoolByTeacherId(group, groupCardData)) {
            return new ResponseEntity(ErrorResult.customError("您为班级教师，无法加入该班"), HttpStatus.CONFLICT);
        }

        List<GroupMember> groupMembers = group.getGroupMemberList();
        int groupMemberCount = 0;
        GroupMember existGroupMember = null;
        for (GroupMember groupMember : groupMembers) {
            if (groupMember.getGroupMemberStatus().equals(GroupMemberStatus.NORMAL)) {
                groupMemberCount = groupMemberCount + 1;
            }
            if (groupMember.getStudentId().equals(groupCardData.getUserId())) {
                existGroupMember = groupMember;
            }
        }

        Date date = new Date();
        if (existGroupMember != null) {
            if (existGroupMember.getGroupMemberStatus().equals(GroupMemberStatus.NORMAL)) {
                return new ResponseEntity(ErrorResult.customError("您已加入班级，无法再次加入"), HttpStatus.CONFLICT);
            } else {
                existGroupMember.setGroupMemberStatus(GroupMemberStatus.NORMAL);
                existGroupMember.setModifyTime(date);
                GroupMember member = this.groupMemberRepository.save(existGroupMember);
                groupMemberCount = groupMemberCount + 1;
                this.sendStudentCountMessage(groupCardData.getGroupId(), groupMemberCount);
                if (dataVisualEnable) {
                    GroupMemberEvent groupMemberEvent = eventService.buildGroupMemberEvent(member);
                    eventService.sendGroupMemberEvent(member.getId(), groupMemberEvent);
                }
            }
        } else {
            GroupMember groupMember = this.getGroupMember(groupCardData, group, date);
            GroupMember member = this.groupMemberRepository.save(groupMember);
            groupMemberCount = groupMemberCount + 1;
            this.sendStudentCountMessage(groupCardData.getGroupId(), groupMemberCount);
            if (dataVisualEnable) {
                GroupMemberEvent groupMemberEvent = eventService.buildGroupMemberEvent(member);
                eventService.sendGroupMemberEvent(member.getId(), groupMemberEvent);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private GroupMember getGroupMember(GroupCardData groupCardData, Group group, Date date) {
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupMemberStatus(GroupMemberStatus.NORMAL);
        groupMember.setStudentId(groupCardData.getUserId());
        groupMember.setStudentName(groupCardData.getUserName());
        groupMember.setStudentNo(groupCardData.getUserNo());
        groupMember.setGroup(group);
        groupMember.setAdministrativeClassName(groupCardData.getAdministrativeClassName());
        groupMember.setCreateTime(date);
        groupMember.setModifyTime(date);
        groupMember.setCreatorId(groupCardData.getUserId());
        groupMember.setCreatorName(groupCardData.getUserName());
        groupMember.setModifierId(groupCardData.getUserId());
        groupMember.setModifierName(groupCardData.getUserName());
        groupMember.setSource(GroupMemberSource.SCAN_CLASS_CODE);
        groupMember.setStudentCollegeId(groupCardData.getCollegeId());
        groupMember.setStudentCollegeName(groupCardData.getCollegeName());
        return groupMember;
    }


    private Boolean getBoolByTeacherId(Group group, GroupCardData groupCardData) {
        CourseTable courseTable = courseTableRepository.findByGroup(group).get(0);
        if (courseTable.getCourseTableDetailList().size() > 0) {
            for (CourseTableDetail courseTableDetail : courseTable.getCourseTableDetailList()) {
                for (CourseTableDetailTeacher courseTableDetailTeacher : courseTableDetail.getCourseTableDetailTeacherList()) {
                    if (groupCardData.getUserId().equals(courseTableDetailTeacher.getTeacherId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void sendStudentCountMessage(String groupId, int currentGroupMemberCount) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("studentCount", currentGroupMemberCount);
        rabbitTemplate.convertAndSend(exchange, groupId, dataMap);
    }

    public ResponseEntity<GroupMemberVo> getStudentCount(String groupId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            return new ResponseEntity(ErrorResult.customError("未找到班级信息"), HttpStatus.NOT_FOUND);
        }
        GroupMemberVo groupMemberVo = new GroupMemberVo();
        groupMemberVo.setStudentCount(group.getGroupMemberList() == null ? 0 :
                (int) group.getGroupMemberList()
                        .stream()
                        .filter(g -> GroupMemberStatus.NORMAL.equals(g.getGroupMemberStatus())).count());
        return new ResponseEntity<>(groupMemberVo, HttpStatus.OK);
    }
}


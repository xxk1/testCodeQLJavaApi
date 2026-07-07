package com.lztech.site.service.event;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.JsonUtils;
import com.lztech.site.viewmodel.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static com.lztech.site.constants.ConstantDataVisual.*;


@Service
public class EventService {
    private final Logger log = LoggerFactory.getLogger(EventService.class);

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    private List<KnowledgeStructureTopicVo> knowledgeStructureTopicVos;

    public void sendTermEvent(TermEvent termEvent) {
        String message = "";

        try {
            message = JsonUtils.getJson(termEvent);
        } catch (JsonProcessingException je) {
            log.error("sendLoginUserEvent对象转换Json错误：" + termEvent);
            return;
        }

        try {
            kafkaTemplate.send(TERM_TOPIC, termEvent.getId(), message);
        } catch (Exception e) {
            log.info("sendTermEvent data:" + message);
            log.error("sendTermEvent:", e);
        }
    }
    public void sendCourseEvent(CourseEvent courseEvent) {
        String message = "";

        try {
            message = JsonUtils.getJson(courseEvent);
        } catch (JsonProcessingException je) {
            log.error("sendCourseEvent对象转换Json错误：" + courseEvent);
            return;
        }

        try {
            kafkaTemplate.send(COURSE_TOPIC, courseEvent.getId(), message);
        } catch (Exception e) {
            log.info("sendCourseEvent data:" + message);
            log.error("sendCourseEvent:", e);
        }
    }
    @Async
    public void sendDeleteCourseEvent(String courseEventId) {

        try {
            kafkaTemplate.send(COURSE_TOPIC, courseEventId, null);

        } catch (Exception e) {
            log.error("sendDeleteCourseEvent:", e);
        }
    }

    public void sendCourseResourceEvent(CourseResourceEvent courseResourceEvent) {
        String message = "";

        try {
            message = JsonUtils.getJson(courseResourceEvent);
        } catch (JsonProcessingException je) {
            log.error("sendCourseResourceEvent对象转换Json错误：" + courseResourceEvent);
            return;
        }

        try {
            kafkaTemplate.send(COURSE_RESOURCE_TOPIC, courseResourceEvent.getId(), message);
        } catch (Exception e) {
            log.info("sendCourseResourceEvent data:" + message);
            log.error("sendCourseResourceEvent:", e);
        }
    }
    @Async
    public void sendDeleteCourseResourceEvent(String courseResourceEventId) {

        try {
            kafkaTemplate.send(COURSE_RESOURCE_TOPIC, courseResourceEventId, null);

        } catch (Exception e) {
            log.error("sendDeleteCourseResourceEvent:", e);
        }
    }


    public void sendGroupEvent(String id,GroupEvent groupEvent) {
        String message = "";

        try {
            message = JsonUtils.getJson(groupEvent);
        } catch (JsonProcessingException je) {
            log.error("sendGroupEvent对象转换Json错误：" + groupEvent);
            return;
        }

        try {
            kafkaTemplate.send(GROUP_TOPIC, id, message);
        } catch (Exception e) {
            log.info("sendGroupEvent data:" + message);
            log.error("sendGroupEvent:", e);
        }
    }
    public void sendDeleteGroupEvent(String groupEventId) {

        try {
            kafkaTemplate.send(GROUP_TOPIC, groupEventId, null);

        } catch (Exception e) {
            log.error("sendDeleteGroupEvent:", e);
        }
    }

    public GroupEvent buildGroupEvent(Group group){
        GroupEvent groupEvent =new GroupEvent();
        groupEvent.setId(group.getId());
        groupEvent.setGroupNo(group.getGroupNo());
        groupEvent.setGroupSource(group.getSource().getIndex());
        groupEvent.setGroupName(group.getGroupName());
        groupEvent.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        groupEvent.setClassNickName(group.getClassNickName());
        groupEvent.setClassCompositionName(group.getClassCompositionName());
        groupEvent.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
        return groupEvent;
    }

    public void sendGroupMemberEvent(String id, GroupMemberEvent groupMemberEvent) {
        String message = "";
        try {
            message = JsonUtils.getJson(groupMemberEvent);
        } catch (JsonProcessingException je) {
            log.error("sendGroupMemberEvent对象转换Json错误：" + groupMemberEvent);
            return;
        }
        try {
            kafkaTemplate.send(GROUP_MEMBER_TOPIC, id, message);
        } catch (Exception e) {
            log.info("sendGroupMemberEvent data:" + message);
            log.error("sendGroupMemberEvent:", e);
        }
    }

    public void sendDeleteGroupMemberEvent(String groupMemberEventId) {
        try {
            kafkaTemplate.send(GROUP_MEMBER_TOPIC, groupMemberEventId, null);
        } catch (Exception e) {
            log.error("sendDeleteGroupMemberEvent:", e);
        }
    }

    public GroupMemberEvent buildGroupMemberEvent(GroupMember groupMember){
        GroupMemberEvent groupMemberEvent =new GroupMemberEvent();
        groupMemberEvent.setId(groupMember.getId());
        groupMemberEvent.setGroupId(groupMember.getGroup().getId());
        groupMemberEvent.setStudentId(groupMember.getStudentId());
        groupMemberEvent.setStudentNo(groupMember.getStudentNo());
        groupMemberEvent.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        groupMemberEvent.setStudentName(groupMember.getStudentName());
        groupMemberEvent.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
        return groupMemberEvent;
    }

    public void sendCourseTeachingTeamEvent(CourseTeachingTeamEvent courseTeachingTeamEvent) {
        String message = "";

        try {
            message = JsonUtils.getJson(courseTeachingTeamEvent);
        } catch (JsonProcessingException je) {
            log.error("sendCourseTeachingTeamEvent：" + courseTeachingTeamEvent);
            return;
        }

        try {
            kafkaTemplate.send(COURSE_TEACHING_TEAM_TOPIC, courseTeachingTeamEvent.getId(), message);
        } catch (Exception e) {
            log.info("sendCourseTeachingTeamEvent data:" + message);
            log.error("sendCourseTeachingTeamEvent:", e);
        }
    }
    public CourseTeachingTeamEvent buildCourseTeachingTeamEvent(CourseTeachingTeam courseTeachingTeam){

        CourseTeachingTeamEvent courseTeachingTeamEvent =new CourseTeachingTeamEvent();
        courseTeachingTeamEvent.setId(courseTeachingTeam.getId());
        courseTeachingTeamEvent.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        courseTeachingTeamEvent.setCourseId(courseTeachingTeam.getCourse().getId());
        courseTeachingTeamEvent.setCourseCode(courseTeachingTeam.getCourse().getCourseCode());
        courseTeachingTeamEvent.setCourseName(courseTeachingTeam.getCourse().getCourseName());
        courseTeachingTeamEvent.setCourseVersionId(courseTeachingTeam.getCourseVersion().getId());
        courseTeachingTeamEvent.setTeacherId(courseTeachingTeam.getTeacherId());
        courseTeachingTeamEvent.setTeacherNo(courseTeachingTeam.getTeacherNo());
        courseTeachingTeamEvent.setTeacherName(courseTeachingTeam.getTeacherName());
        courseTeachingTeamEvent.setJobTitle(courseTeachingTeam.getJobTitle());
        courseTeachingTeamEvent.setSchoolName(courseTeachingTeam.getSchoolName());
        courseTeachingTeamEvent.setTeacherType(courseTeachingTeam.getTeacherType().getIndex());
        courseTeachingTeamEvent.setTeacherDataSource(courseTeachingTeam.getTeacherDataSource().getValue());
        courseTeachingTeamEvent.setModifierId(courseTeachingTeam.getModifierId());
        courseTeachingTeamEvent.setModifierName(courseTeachingTeam.getModifierName());
        courseTeachingTeamEvent.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", new Date()));
        return courseTeachingTeamEvent;
    }
    @Async
    public void sendDeleteCourseTeachingTeamEvent(String courseTeachingTeamId) {
        try {
            kafkaTemplate.send(COURSE_TEACHING_TEAM_TOPIC, courseTeachingTeamId, null);
        } catch (Exception e) {
            log.error("sendDeleteCourseTeachingTeamEvent:", e);
        }
    }

    //课程版本完成度事件
    @Async
    public void sendCourseVersionEvent(CourseVersionEvent courseVersionEvent) {
        String message = "";

        try {
            message = JsonUtils.getJson(courseVersionEvent);
        } catch (JsonProcessingException je) {
            log.error("sendCourseVersionEvent对象转换Json错误：" + courseVersionEvent);
            return;
        }

        try {
            kafkaTemplate.send(COURSE_VERSION_TOPIC, courseVersionEvent.getId(), message);
        } catch (Exception e) {
            log.info("sendCourseVersionEvent data:" + message);
            log.error("sendCourseVersionEvent:", e);
        }
    }

    @Async
    public void sendAddKnowledgeStructureList( List<KnowledgeStructureTopicVo> knowledgeStructureTopicVos) {
        knowledgeStructureTopicVos.forEach(knowledgeStructureTopicVo -> {
            this.sendAddKnowledgeStructure(knowledgeStructureTopicVo);
        });
    }

    public void sendAddKnowledgeStructure( KnowledgeStructureTopicVo knowledgeStructureTopicVo) {
        String message = "";

        try {
            message = JsonUtils.getJson(knowledgeStructureTopicVo);
        } catch (JsonProcessingException je) {
            log.error("sendAddKnowledgeStructure：{}", knowledgeStructureTopicVo);
            return;
        }

        try {
            kafkaTemplate.send(KNOWLEDGE_STRUCTURE_TOPIC, knowledgeStructureTopicVo.getId(), message);
        } catch (Exception e) {
            log.info("sendAddKnowledgeStructure data:" + message);
            log.error("sendAddKnowledgeStructure:", e);
        }
    }

    @Async
    public void sendDeleteKnowledgeStructure( String id) {
        try {
            kafkaTemplate.send(KNOWLEDGE_STRUCTURE_TOPIC, id, null);
        } catch (Exception e) {
            log.error("sendDeleteKnowledgeStructure:", e);
        }
    }

    public void sendKnowledgeGraphDomainTopicVo(KnowledgeGraphDomainTopicVo knowledgeGraphDomainTopicVo) {
        String message = "";

        try {
            message = JsonUtils.getJson(knowledgeGraphDomainTopicVo);
        } catch (JsonProcessingException je) {
            log.error("sendKnowledgeGraphDomainTopicVo：{}", knowledgeGraphDomainTopicVo);
            return;
        }

        try {
            kafkaTemplate.send(KNOWLEDGE_GRAPH_DOMAIN_TOPIC, knowledgeGraphDomainTopicVo.getId(), message);
        } catch (Exception e) {
            log.info("sendKnowledgeGraphDomainTopicVo data:{}" , message);
            log.error("sendKnowledgeGraphDomainTopicVo:{}", e.toString());
        }
    }
}

package com.lztech.site.service.experimentalgroup;

import com.lztech.domain.model.experimentalgroup.ExperimentalGroup;
import com.lztech.domain.model.experimentalgroup.ExperimentalGroupMember;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.persistence.repositories.experimentalgroup.ExperimentalGroupMemberRepository;
import com.lztech.persistence.repositories.experimentalgroup.ExperimentalGroupRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.constants.Result;
import com.lztech.site.viewmodel.experimentalgroup.ExperimentalGroupCreateResource;
import com.lztech.site.viewmodel.experimentalgroup.ExperimentalGroupMemberResource;
import com.lztech.site.viewmodel.experimentalgroup.ExperimentalGroupNum;
import com.lztech.site.viewmodel.experimentalgroup.ExperimentalGroupResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ExperimentalGroupService {

    @Autowired
    private ExperimentalGroupRepository experimentalGroupRepository;
    @Autowired
    private ExperimentalGroupMemberRepository experimentalGroupMemberRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;


    public List<ExperimentalGroupResource> getGroupExperimentalGroupList(String id) {
        List<ExperimentalGroup> groupList = experimentalGroupRepository.findByGroupIdAndUseStateIsTrue(id);
        if (CollectionUtils.isEmpty(groupList)) {
            return new ArrayList<>();
        }
        List<ExperimentalGroupResource> resourceList = new ArrayList<>();
        groupList.forEach(group -> {
            ExperimentalGroupResource resource = new ExperimentalGroupResource();
            setExperimentalGroupResource(group, resource);
            resourceList.add(resource);
        });
        return resourceList;
    }

    private void setExperimentalGroupResource(ExperimentalGroup group, ExperimentalGroupResource resource) {
        resource.setId(group.getId());
        resource.setGroupName(group.getGroupName());
        resource.setResponsibleTeacherId(group.getResponsibleTeacherId());
        resource.setResponsibleTeacherName(group.getResponsibleTeacherName());
        resource.setResponsibleTeacherNo(group.getResponsibleTeacherNo());
        resource.setGroupLeaderId(group.getGroupLeaderId());
        resource.setGroupLeaderName(group.getGroupLeaderName());
        resource.setGroupLeaderNo(group.getGroupLeaderNo());
        resource.setMemberNum(group.getMemberNum());
    }


    public Result insertOrUpdateExperimentalGroup(ExperimentalGroupCreateResource experimentalGroupCreateVo) {
        Date now = new Date();
        ExperimentalGroup experimentalGroup = null;
        if (StringUtils.isBlank(experimentalGroupCreateVo.getId())) {
            // 新增
            int classGroupNum = experimentalGroupRepository.countByGroupIdAndUseStateIsTrue(experimentalGroupCreateVo.getClassId());
            if (classGroupNum > Constant.CLASS_EXPERIMENTAL_GROUP_MAX_NUM) {
                return Result.error("小组数量已达上限");
            }
            experimentalGroup = buildNewExperimentalGroup(experimentalGroupCreateVo, now);
        } else {
            experimentalGroup = experimentalGroupRepository.findById(experimentalGroupCreateVo.getId()).orElse(null);
            if (Objects.isNull(experimentalGroup)) {
                int classGroupNum = experimentalGroupRepository.countByGroupIdAndUseStateIsTrue(experimentalGroupCreateVo.getClassId());
                if (classGroupNum > Constant.CLASS_EXPERIMENTAL_GROUP_MAX_NUM) {
                    return Result.error("小组数量已达上限");
                }
                experimentalGroup = buildNewExperimentalGroup(experimentalGroupCreateVo, now);
            } else {
                experimentalGroupMemberRepository.deleteByExperimentalGroup(experimentalGroup);
                experimentalGroup.setGroupName(experimentalGroupCreateVo.getGroupName());
                experimentalGroup.setResponsibleTeacherId(experimentalGroupCreateVo.getResponsibleTeacherId());
                experimentalGroup.setResponsibleTeacherNo(experimentalGroupCreateVo.getResponsibleTeacherNo());
                experimentalGroup.setResponsibleTeacherName(experimentalGroupCreateVo.getResponsibleTeacherName());
                experimentalGroup.setUseState(true);
                experimentalGroup.setModifierId(experimentalGroupCreateVo.getCreatorId());
                experimentalGroup.setModifierName(experimentalGroupCreateVo.getCreatorName());
                experimentalGroup.setModifyTime(now);
                experimentalGroup.setGroupMemberList(createExperimentalGroupMemberList(experimentalGroup, experimentalGroupCreateVo, now));
            }
        }
        if (Objects.nonNull(experimentalGroup)) {
            experimentalGroupRepository.save(experimentalGroup);
        }
        return Result.success();
    }

    private ExperimentalGroup buildNewExperimentalGroup(ExperimentalGroupCreateResource experimentalGroupCreateVo, Date now) {

        ExperimentalGroup experimentalGroup = new ExperimentalGroup();
        experimentalGroup.setGroupName(experimentalGroupCreateVo.getGroupName());
        experimentalGroup.setResponsibleTeacherId(experimentalGroupCreateVo.getResponsibleTeacherId());
        experimentalGroup.setResponsibleTeacherNo(experimentalGroupCreateVo.getResponsibleTeacherNo());
        experimentalGroup.setResponsibleTeacherName(experimentalGroupCreateVo.getResponsibleTeacherName());
        experimentalGroup.setGroupId(experimentalGroupCreateVo.getClassId());
        experimentalGroup.setUseState(true);
        experimentalGroup.setModifierId(experimentalGroupCreateVo.getCreatorId());
        experimentalGroup.setModifierName(experimentalGroupCreateVo.getCreatorName());
        experimentalGroup.setModifyTime(now);
        experimentalGroup.setCreatorId(experimentalGroupCreateVo.getCreatorId());
        experimentalGroup.setCreatorName(experimentalGroupCreateVo.getCreatorName());
        experimentalGroup.setCreateTime(now);
        experimentalGroup.setGroupMemberList(createExperimentalGroupMemberList(experimentalGroup, experimentalGroupCreateVo, now));
        return experimentalGroup;
    }

    private List<ExperimentalGroupMember> createExperimentalGroupMemberList(ExperimentalGroup experimentalGroup,
                                                                            ExperimentalGroupCreateResource experimentalGroupCreateVo,
                                                                            Date now) {
        List<ExperimentalGroupMember> memberList = new ArrayList<>();
        experimentalGroupCreateVo.getMemberList().forEach(m -> {
            ExperimentalGroupMember experimentalGroupMember = new ExperimentalGroupMember();
            experimentalGroupMember.setStudentId(m.getStudentId());
            experimentalGroupMember.setStudentNo(m.getStudentNo());
            experimentalGroupMember.setStudentName(m.getStudentName());
            experimentalGroupMember.setGroupLeader(m.isIsGroupLeader());
            if (m.isIsGroupLeader()) {
                experimentalGroup.setGroupLeaderId(m.getStudentId());
                experimentalGroup.setGroupLeaderNo(m.getStudentNo());
                experimentalGroup.setGroupLeaderName(m.getStudentName());
            }
            experimentalGroupMember.setExperimentalGroup(experimentalGroup);
            experimentalGroupMember.setCreatorId(experimentalGroupCreateVo.getCreatorId());
            experimentalGroupMember.setCreatorName(experimentalGroupCreateVo.getCreatorName());
            experimentalGroupMember.setCreateTime(now);
            memberList.add(experimentalGroupMember);
        });
        experimentalGroup.setMemberNum(memberList.size());
        return memberList;
    }

    public List<ExperimentalGroupMemberResource> getGroupUnassignedExperimentalGroupList(String groupId) {
        List<ExperimentalGroupMemberResource> resourceList = new ArrayList<>();

        List<GroupMember> groupMemberList = groupMemberRepository.getGroupUnassignedExperimentalGroupList(groupId);
        groupMemberList.forEach(m -> {
            ExperimentalGroupMemberResource memberResource = new ExperimentalGroupMemberResource();
            memberResource.setStudentId(m.getStudentId());
            memberResource.setStudentName(m.getStudentName());
            memberResource.setStudentNo(m.getStudentNo());
            memberResource.setIsGroupLeader(false);
            resourceList.add(memberResource);
        });
        return resourceList;
    }

    public ExperimentalGroupResource getExperimentalGroupDetail(String id, Boolean isGetMemberList) {
        ExperimentalGroup experimentalGroup = experimentalGroupRepository.findById(id).orElse(null);
        if (Objects.isNull(experimentalGroup)) {
            return null;
        }
        ExperimentalGroupResource experimentalGroupResource = new ExperimentalGroupResource();
        setExperimentalGroupResource(experimentalGroup, experimentalGroupResource);
        if (Objects.nonNull(isGetMemberList) && isGetMemberList) {
            experimentalGroupResource.setMemberList(buildMemberList(experimentalGroup.getGroupMemberList()));
        }
        return experimentalGroupResource;
    }

    private List<ExperimentalGroupMemberResource> buildMemberList(List<ExperimentalGroupMember> groupMemberList) {
        List<ExperimentalGroupMemberResource> memberResourceList = new ArrayList<>();
        groupMemberList.forEach(m -> {
            ExperimentalGroupMemberResource resource = new ExperimentalGroupMemberResource();
            resource.setStudentId(m.getStudentId());
            resource.setStudentName(m.getStudentName());
            resource.setStudentNo(m.getStudentNo());
            resource.setIsGroupLeader(m.isGroupLeader());
            memberResourceList.add(resource);
        });
        return memberResourceList;
    }

    public void deleteExperimentalGroup(String id, String modifierId, String modifierName) {
        experimentalGroupRepository.updateExperimentalGroupState(id, modifierId, modifierName, new Date());
    }

    public ExperimentalGroupNum getGroupExperimentalGroupNum(String id) {
        int num = experimentalGroupRepository.countByGroupIdAndUseStateIsTrue(id);
        ExperimentalGroupNum experimentalGroupNum = new ExperimentalGroupNum();
        experimentalGroupNum.setExperimentalGroupNum(num);
        return experimentalGroupNum;
    }
}

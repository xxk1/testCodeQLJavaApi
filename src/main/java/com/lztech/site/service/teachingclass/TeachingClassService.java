package com.lztech.site.service.teachingclass;

import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.site.resource.teachingclass.TeachingClassResource;
import com.lztech.site.resource.term.TermResource;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.NumUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TeachingClassService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TermService termService;


    public List<TeachingClassResource> getTeachingClassResource(String teachingClassIds) {

        String[] teachingClassIdList = teachingClassIds.split(",");
        List<TeachingClassResource> teachingClassResources = new ArrayList<>();
        for (String teachingClassId : teachingClassIdList) {
            Group group = groupRepository.findByIdAndGroupStatus(teachingClassId, GroupStatus.NORMAL);
            if (group != null&& CollectionUtils.isNotEmpty(group.getCourseTableList())) {
                TeachingClassResource teachingClassResource = new TeachingClassResource();
                teachingClassResource.setCourseId(group.getCourseTableList().get(0).getCourse().getId());
                teachingClassResource.setCourseName(group.getCourseTableList().get(0).getCourse().getCourseName());
                teachingClassResource.setTeachingClassId(group.getId());
                teachingClassResource.setTeachingClassName(group.getGroupName());
                teachingClassResource.setTeachingClassCode(group.getGroupNo());
                Integer sort = ObjectUtils.isEmpty(group.getSort()) ? 0 : group.getSort();
                teachingClassResource.setTeachingClassSort(sort);
                teachingClassResource.setClassCompositionName(group.getClassCompositionName());
                teachingClassResource.setClassNickName(group.getClassNickName());
                teachingClassResource.setCollegeId(group.getCourseTableList().get(0).getCourse().getCollege().getId());
                teachingClassResource.setCollegeName(group.getCourseTableList().get(0).getCourse().getCollege().getCollegeName());
                teachingClassResource.setCourseTableCollegeId(group.getCourseTableList().get(0).getCollegeId());
                teachingClassResource.setCourseTableCollegeName(group.getCourseTableList().get(0).getCollegeName());
                teachingClassResource.setSchoolYear(group.getCourseTableList().get(0).getSchoolYear());
                teachingClassResource.setTerm(group.getCourseTableList().get(0).getTerm());
                teachingClassResource.setClassAttribute(Objects.isNull(group.getGroupAttribute()) ? null : group.getGroupAttribute().getValue());
                teachingClassResource.setDescription("第" + NumUtil.int2chineseNum(group.getCourseTableList().get(0).getTerm()) + "学期");
                TermResource termResource = termService.getTerm(teachingClassResource.getSchoolYear(),teachingClassResource.getTerm()+"");
                if (StringUtils.isEmpty(termResource.getSchoolYearTermNickName())){
                    teachingClassResource.setSchoolYearTermNickName(teachingClassResource.getSchoolYear()+"-"+teachingClassResource.getTerm());
                }else {
                    teachingClassResource.setSchoolYearTermNickName(termResource.getSchoolYearTermNickName());
                }

                if (group.getGroupMemberList() != null) {
                    List<GroupMember> groupMembers = group.getGroupMemberList()
                            .stream()
                            .filter(g -> GroupMemberStatus.NORMAL.equals(g.getGroupMemberStatus()))
                            .collect(Collectors.toList());
                    teachingClassResource.setTeachingClassMembers(groupMembers.size());
                }else {
                    teachingClassResource.setTeachingClassMembers(0);
                }
                teachingClassResource.setTeachingClassType(group.getSource().getIndex());
                teachingClassResources.add(teachingClassResource);
            }
        }
        return teachingClassResources.stream()
                .sorted(Comparator.comparingInt(TeachingClassResource::getTeachingClassSort)
                        .thenComparing((TeachingClassResource obj) ->
                                (obj.getTeachingClassCode() != null && obj.getTeachingClassCode().startsWith("2")) ? 0 : 1)
                        .thenComparing(TeachingClassResource::getTeachingClassCode))
                .collect(Collectors.toList());
    }
}

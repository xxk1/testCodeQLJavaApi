package com.lztech.site.service.classgroupingscheme;

import com.lztech.domain.model.classgroupingscheme.ClassGrouping;
import com.lztech.domain.model.classgroupingscheme.ClassGroupingMember;
import com.lztech.domain.model.classgroupingscheme.ClassGroupingScheme;
import com.lztech.domain.model.classgroupingscheme.enums.SchemeType;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupAttribute;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.persistence.repositories.classgroupingscheme.ClassGroupingMemberRepository;
import com.lztech.persistence.repositories.classgroupingscheme.ClassGroupingRepository;
import com.lztech.persistence.repositories.classgroupingscheme.ClassGroupingSchemeRepository;
import com.lztech.persistence.repositories.classgroupingscheme.specification.ClassGroupingSpecification;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.viewmodel.authorityapi.UsersInfoResource;
import com.lztech.site.viewmodel.classgroupingscheme.*;
import com.lztech.site.viewmodel.groupmember.DeleteGroupMemberInfoVo;
import com.lztech.site.viewmodel.userinfo.Users;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.*;

@Service
public class ClassGroupingSchemeService {

    @Autowired
    private ClassGroupingSchemeRepository classGroupingSchemeRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ClassGroupingRepository classGroupingRepository;
    @Autowired
    private ClassGroupingMemberRepository classGroupingMemberRepository;
    @Autowired
    private AuthorityApiService authorityApiService;

    public List<ClassGroupingSchemeVo> getClassGroupingSchemeList(String groupId, Integer schemaTypeValue) {

        int groupMemberCount = groupMemberRepository.countByGroupIdAndGroupMemberStatus(groupId, GroupMemberStatus.NORMAL);
        SchemeType schemeType = Objects.isNull(schemaTypeValue) ? null : SchemeType.getSchemeType(schemaTypeValue);
        List<ClassGroupingScheme> classGroupingSchemeList = new ArrayList<>();
        if (Objects.nonNull(schemeType)) {
            classGroupingSchemeList = classGroupingSchemeRepository.findByGroupIdAndSchemeType(groupId, schemeType);
        } else {
            classGroupingSchemeList = classGroupingSchemeRepository.findByGroupId(groupId);
        }
        return classGroupingSchemeList
                .stream()
                .sorted(Comparator.comparing(ClassGroupingScheme::getCreateTime).reversed())
                .map(classGroupingScheme
                        -> new ClassGroupingSchemeVo() {{
                    this.setId(classGroupingScheme.getId());
                    this.setSchemeName(classGroupingScheme.getSchemeName());
                    this.setSchemePresentation(classGroupingScheme.getSchemePresentation());
                    this.setClassGroupingCount(classGroupingScheme.getClassGroupingList().size());
                    int groupedHeadCount = classGroupingScheme.getGroupedHeadCount() == null ? 0 : classGroupingScheme.getGroupedHeadCount();
                    this.setGroupedHeadCount(groupedHeadCount);
                    int ungroupedHeadCount = Math.max((groupMemberCount - groupedHeadCount), 0);
                    this.setUngroupedHeadCount(ungroupedHeadCount);
                    this.setSchemeType(Objects.isNull(classGroupingScheme.getSchemeType()) ? SchemeType.GENERAL_GROUP_SCHEME.getValue() :
                            classGroupingScheme.getSchemeType().getValue());
                }}).collect(Collectors.toList());
    }

    @Transactional
    public void deleteClassGroupingScheme(String classGroupingSchemeId) {

        Optional<ClassGroupingScheme> optionalClassGroupingScheme = classGroupingSchemeRepository.findById(classGroupingSchemeId);

        if (optionalClassGroupingScheme.isPresent()) {

            classGroupingSchemeRepository.deleteById(classGroupingSchemeId);
        }
    }

    @org.springframework.transaction.annotation.Transactional
    public Result insertClassGroupingScheme(String groupId,
                                            ClassGroupingSchemeInsertResource schemeResource) {

        Group group = groupRepository.findByIdAndGroupStatus(groupId, GroupStatus.NORMAL);
        if (Objects.isNull(group)) {
            return Result.error("此班级不存在");
        }
        ClassGroupingScheme classGroupingScheme = null;
        if (StringUtils.isNotBlank(schemeResource.getSchemeId())) {
            classGroupingScheme = classGroupingSchemeRepository.findById(schemeResource.getSchemeId()).orElse(null);
        }
        Date now = new Date();
        if (Objects.nonNull(classGroupingScheme)) {
            if (!schemeResource.getSchemeName().equals(classGroupingScheme.getSchemeName())) {
                boolean existSameName = existSameSchemeName(groupId, schemeResource.getSchemeName());
                if (existSameName) {
                    return Result.error("当前班级小组方案名称已存在");
                }
            }
            classGroupingMemberRepository.deleteClassGroupingByClassGroupingSchemeId(classGroupingScheme.getId());
            classGroupingRepository.deleteClassGroupingByClassGroupingSchemeId(classGroupingScheme.getId());

        } else {
            boolean existSameName = existSameSchemeName(groupId, schemeResource.getSchemeName());
            if (existSameName) {
                return Result.error("当前班级小组方案名称已存在");
            }
//            boolean checkClassSchemeNum = checkGroupSchemeNum(group);
//            if (checkClassSchemeNum) {
//                return Result.error("最多只能添加8个小组");
//            }
//            boolean checkExperimentalSchemeNum = checkExperimentalSchemeNum(group);
//            if (checkClassSchemeNum) {
//                return Result.error("最多只能添加1个实验小组方案");
//            }
            classGroupingScheme = new ClassGroupingScheme();
            classGroupingScheme.setCreatorId(schemeResource.getOperatorId());
            classGroupingScheme.setCreatorName(schemeResource.getOperatorName());
            classGroupingScheme.setCreateTime(now);
        }
        classGroupingScheme.setModifierId(schemeResource.getOperatorId());
        classGroupingScheme.setModifierName(schemeResource.getOperatorName());
        classGroupingScheme.setModifyTime(now);
        classGroupingScheme.setGroupId(groupId);
        classGroupingScheme.setSchemeName(schemeResource.getSchemeName());
        classGroupingScheme.setSchemeType(Objects.isNull(schemeResource.getSchemaType()) ? SchemeType.GENERAL_GROUP_SCHEME :
                SchemeType.getSchemeType(schemeResource.getSchemaType()));
        classGroupingScheme.setSchemePresentation(schemeResource.getSchemeDescription());
        Integer groupedHeadCount = schemeResource.getClassGroupList().stream().mapToInt(c -> c.getMemberList().size()).sum();
        classGroupingScheme.setClassGroupingList(createClassGroupList(schemeResource, now, classGroupingScheme));
        classGroupingScheme.setGroupedHeadCount(groupedHeadCount);
        classGroupingSchemeRepository.save(classGroupingScheme);
        return Result.success();
    }

    private boolean checkExperimentalSchemeNum(Group group) {
        return classGroupingSchemeRepository.countByGroupIdAndSchemeType(group.getId(), SchemeType.EXPERIMENTAL_GROUP_SCHEME) >= 1;
    }

    private boolean checkGroupSchemeNum(Group group) {
        return classGroupingSchemeRepository.countByGroupId(group.getId()) >= CLASS_MAX_SCHEME_NUM;
    }

    private Boolean existSameSchemeName(String groupId, String schemeName) {
        int sameSchemeNameNum = classGroupingSchemeRepository.countByGroupIdAndSchemeName(groupId, schemeName);
        return sameSchemeNameNum > 0;

    }

    private List<ClassGrouping> createClassGroupList(ClassGroupingSchemeInsertResource schemeResource,
                                                     Date now,
                                                     ClassGroupingScheme classGroupingScheme) {
        List<ClassGrouping> classGroupingList = new ArrayList<>();
        for (ClassGroupingInsertResource classGroupingInsertResource : schemeResource.getClassGroupList()) {
            ClassGrouping classGrouping = new ClassGrouping();
            classGrouping.setClassGroupingName(classGroupingInsertResource.getName());
            classGrouping.setCreatorId(schemeResource.getOperatorId());
            classGrouping.setCreateTime(now);
            classGrouping.setCreatorName(schemeResource.getOperatorName());
            classGrouping.setClassGroupingScheme(classGroupingScheme);
            classGrouping.setResponsibleTeacherId(classGroupingInsertResource.getResponsibleTeacherId());
            classGrouping.setResponsibleTeacherName(classGroupingInsertResource.getResponsibleTeacherName());
            classGrouping.setResponsibleTeacherNo(classGroupingInsertResource.getResponsibleTeacherNo());
            classGrouping.setClassGroupingMemberList(createClassGroupMemberList(classGrouping, schemeResource.getOperatorId(),
                    schemeResource.getOperatorName(), classGroupingInsertResource.getMemberList(), now));
            classGroupingList.add(classGrouping);
        }
        return classGroupingList;
    }

    private List<ClassGroupingMember> createClassGroupMemberList(ClassGrouping classGrouping,
                                                                 String operatorId,
                                                                 String operatorName,
                                                                 List<ClassGroupingMemberInsertResource> memberList,
                                                                 Date now) {
        List<ClassGroupingMember> classGroupingMemberList = new ArrayList<>();
        for (ClassGroupingMemberInsertResource classGroupingMemberInsertResource : memberList) {
            ClassGroupingMember classGroupingMember = new ClassGroupingMember();
            classGroupingMember.setAdministrativeClassName(classGroupingMemberInsertResource.getAdministrativeClassName());
            classGroupingMember.setClassGrouping(classGrouping);
            classGroupingMember.setStudentName(classGroupingMemberInsertResource.getStudentName());
            classGroupingMember.setMajorName(classGroupingMemberInsertResource.getMajorName());
            classGroupingMember.setStudentNo(classGroupingMemberInsertResource.getStudentNo());
            classGroupingMember.setCollegeName(classGroupingMemberInsertResource.getCollegeName());
            classGroupingMember.setWhetherGroupLeader(classGroupingMemberInsertResource.isWhetherGroupLeader());
            classGroupingMember.setStudentId(classGroupingMemberInsertResource.getStudentId());
            classGroupingMember.setCreateTime(now);
            classGroupingMember.setCreatorId(operatorId);
            classGroupingMember.setCreatorName(operatorName);
            classGroupingMemberList.add(classGroupingMember);
        }
        return classGroupingMemberList;
    }

    public ClassGroupingScheme findById(String id) {
        return classGroupingSchemeRepository.findById(id).orElse(null);
    }

    public ClassGroupingSchemeResource buildClassGroupingSchemeDetail(ClassGroupingScheme classGroupingScheme) {
        ClassGroupingSchemeResource classGroupingSchemeResource = new ClassGroupingSchemeResource();
        classGroupingSchemeResource.setId(classGroupingScheme.getId());
        classGroupingSchemeResource.setSchemeName(classGroupingScheme.getSchemeName());
        classGroupingSchemeResource.setSchemaType(Objects.isNull(classGroupingScheme.getSchemeType()) ? null :
                classGroupingScheme.getSchemeType().getValue());
        classGroupingSchemeResource.setSchemeDescription(classGroupingScheme.getSchemePresentation());
        classGroupingSchemeResource.setClassGroupingList(buildClassGroupingList(classGroupingScheme));
        return classGroupingSchemeResource;
    }

    private List<ClassGroupingResource> buildClassGroupingList(ClassGroupingScheme classGroupingScheme) {
        List<ClassGroupingResource> classGroupingResources = new ArrayList<>();
        List<GroupMember> groupMembers = groupMemberRepository.findByGroupIdOrderByStudentNo(classGroupingScheme.getGroupId());
        List<UsersInfoResource> groupMemberUserInfoList = getGroupMemberUserInfo(groupMembers);
        List<String> assignedStudentIdList = new ArrayList<>();
        classGroupingScheme.getClassGroupingList().forEach(classGrouping -> {
            ClassGroupingResource classGroupingResource = new ClassGroupingResource();
            classGroupingResource.setClassGroupingId(classGrouping.getId());
            classGroupingResource.setClassGroupingName(classGrouping.getClassGroupingName());
            classGroupingResource.setMemberNum(classGrouping.getClassGroupingMemberList().size());
            classGroupingResource.setResponsibleTeacherId(classGrouping.getResponsibleTeacherId());
            classGroupingResource.setResponsibleTeacherName(classGrouping.getResponsibleTeacherName());
            classGroupingResource.setResponsibleTeacherNo(classGrouping.getResponsibleTeacherNo());
            classGroupingResource.setClassGroupingType(CLASS_GROUPING_ASSIGNED);
            classGroupingResource.setMemberList(buildClassGroupingMemberList(classGrouping.getClassGroupingMemberList(),
                    groupMemberUserInfoList, assignedStudentIdList));
            classGroupingResources.add(classGroupingResource);
        });

        ClassGroupingResource unassignedClassGroup = buildUnassignedClassGrouping(groupMembers, groupMemberUserInfoList,
                assignedStudentIdList);
        classGroupingResources.add(unassignedClassGroup);
        return classGroupingResources;
    }

    private ClassGroupingResource buildUnassignedClassGrouping(List<GroupMember> groupMembers,
                                                               List<UsersInfoResource> groupMemberUserInfoList,
                                                               List<String> assignedStudentIdList) {
        ClassGroupingResource classGroupingResource = new ClassGroupingResource();
        classGroupingResource.setClassGroupingType(CLASS_GROUPING_UNASSIGNED);
        classGroupingResource.setClassGroupingName("未分配");
        List<ClassGroupingMemberResource> classGroupingMemberResourceList = new ArrayList<>();
        groupMembers.forEach(groupMember -> {
            if (!assignedStudentIdList.contains(groupMember.getStudentId())) {
                ClassGroupingMemberResource classGroupingMemberResource = buildUnassignedClassGroupingMemberInfo(groupMember,
                        groupMemberUserInfoList);
                classGroupingMemberResourceList.add(classGroupingMemberResource);
            }

        });

        classGroupingResource.setMemberList(classGroupingMemberResourceList);
        classGroupingResource.setMemberNum(classGroupingMemberResourceList.size());
        return classGroupingResource;
    }

    private ClassGroupingMemberResource buildUnassignedClassGroupingMemberInfo(GroupMember groupMember,
                                                                               List<UsersInfoResource> groupMemberUserInfoList) {
        ClassGroupingMemberResource classGroupingMemberResource = new ClassGroupingMemberResource();
        classGroupingMemberResource.setId(groupMember.getStudentId());
        classGroupingMemberResource.setUserNo(groupMember.getStudentNo());
        classGroupingMemberResource.setClassName(groupMember.getAdministrativeClassName());
        classGroupingMemberResource.setUserName(groupMember.getStudentName());
        classGroupingMemberResource.setWhetherGroupLeader(false);
        UsersInfoResource usersInfo = groupMemberUserInfoList
                .stream()
                .filter(user -> user.getId().equals(groupMember.getStudentId()))
                .findFirst()
                .orElse(null);
        if (Objects.nonNull(usersInfo)) {
            classGroupingMemberResource.setAvatarPath(usersInfo.getAvatarPath());
            classGroupingMemberResource.setEamil(usersInfo.getEmail());
            classGroupingMemberResource.setGender(usersInfo.getGender());
            classGroupingMemberResource.setMajorName(usersInfo.getMajorName());
            classGroupingMemberResource.setAvatarInnerUrl(usersInfo.getAvatarInnerUrl());
            classGroupingMemberResource.setAvatarOuterUrl(usersInfo.getAvatarOuterUrl());
            classGroupingMemberResource.setCollege(usersInfo.getCollegeName());
        }
        return classGroupingMemberResource;
    }

    private List<ClassGroupingMemberResource> buildClassGroupingMemberList(List<ClassGroupingMember> classGroupingMemberList,
                                                                           List<UsersInfoResource> groupMemberUserInfoList,
                                                                           List<String> assignedStudentIdList) {

        List<ClassGroupingMemberResource> classGroupingMemberResourceList = new ArrayList<>();
        classGroupingMemberList.forEach(classGroupingMember -> {
            ClassGroupingMemberResource classGroupingMemberResource = new ClassGroupingMemberResource();
            classGroupingMemberResource.setId(classGroupingMember.getStudentId());
            classGroupingMemberResource.setUserName(classGroupingMember.getStudentName());
            classGroupingMemberResource.setMajorName(classGroupingMember.getMajorName());
            classGroupingMemberResource.setCollege(classGroupingMember.getCollegeName());
            classGroupingMemberResource.setClassName(classGroupingMember.getAdministrativeClassName());
            classGroupingMemberResource.setUserNo(classGroupingMember.getStudentNo());
            classGroupingMemberResource.setWhetherGroupLeader(classGroupingMember.getWhetherGroupLeader());
            UsersInfoResource usersInfo = groupMemberUserInfoList
                    .stream()
                    .filter(user -> user.getId().equals(classGroupingMember.getStudentId()))
                    .findFirst()
                    .orElse(null);
            if (Objects.nonNull(usersInfo)) {
                classGroupingMemberResource.setAvatarPath(usersInfo.getAvatarPath());
                classGroupingMemberResource.setAvatarInnerUrl(usersInfo.getAvatarInnerUrl());
                classGroupingMemberResource.setAvatarOuterUrl(usersInfo.getAvatarOuterUrl());
                classGroupingMemberResource.setEamil(usersInfo.getEmail());
                classGroupingMemberResource.setGender(usersInfo.getGender());
            }
            assignedStudentIdList.add(classGroupingMember.getStudentId());
            classGroupingMemberResourceList.add(classGroupingMemberResource);
        });
        return classGroupingMemberResourceList;
    }

    public List<UsersInfoResource> getGroupMemberUserInfo(List<GroupMember> groupMemberList) {
        List<Users> usersList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(groupMemberList)) {
            groupMemberList.forEach(groupMember -> {
                Users users = new Users();
                users.setUserId(groupMember.getStudentId());
                users.setOpenId(groupMember.getOpenId());
                usersList.add(users);
            });
            return authorityApiService.getUsersInfo(usersList);
        }
        return new ArrayList<>();
    }

    public void updateClassGroupingMemberNum(String studentId, String operatorId, String operatorName, String groupId) {
        classGroupingSchemeRepository.updateClassGroupingMemberNum(studentId, operatorId, operatorName, new Date(), groupId);
    }

    public void deleteClassGroupingStudent(String studentId, String groupId) {
        classGroupingMemberRepository.deleteByStudentIdAndGroupId(studentId, groupId);
    }

    public GroupingSchemeAddCheckedResource checkCanAddExperimentalGroupingScheme(String groupId) {
        GroupingSchemeAddCheckedResource resource = new GroupingSchemeAddCheckedResource();
        Group group = groupRepository.findByIdAndGroupStatus(groupId, GroupStatus.NORMAL);
        if (Objects.isNull(group)) {
            resource.setCheckedStatus(CLASS_SCHEME_ADD_NULL);
        }
        List<ClassGroupingScheme> schemeList = classGroupingSchemeRepository.findByGroupId(groupId);
        if (CollectionUtils.isNotEmpty(schemeList) && schemeList.size() >= CLASS_MAX_SCHEME_NUM) {
            resource.setCheckedStatus(CLASS_SCHEME_ADD_NULL);
        } else if ((GroupAttribute.PRACTICE.equals(group.getGroupAttribute())
                || GroupAttribute.THEORY_AND_PRACTICE.equals(group.getGroupAttribute()))
                && schemeList.stream().noneMatch(s -> SchemeType.EXPERIMENTAL_GROUP_SCHEME.equals(s.getSchemeType()))) {
            resource.setCheckedStatus(CLASS_SCHEME_ADD_PRACTICE);
        } else {
            resource.setCheckedStatus(CLASS_SCHEME_ADD_ORDINARY);
        }
        return resource;
    }

    public List<GroupingSchemeTeacher> getResponsibleTeacher(String groupIds, String teacherName) {
        String[] ids = groupIds.split(",");
        List<ClassGroupingScheme> classGroupingSchemes = classGroupingSchemeRepository.findByGroupIdIn(Arrays.asList(ids));
        Set<GroupingSchemeTeacher> groupingSchemeTeachers = new HashSet<>();
        classGroupingSchemes.forEach(classGroupingScheme -> {
            classGroupingScheme.getClassGroupingList().forEach(classGrouping -> {
                if (classGrouping.getResponsibleTeacherName() != null && classGrouping.getResponsibleTeacherName().equals(teacherName)) {
                    GroupingSchemeTeacher groupingSchemeTeacher = new GroupingSchemeTeacher();
                    groupingSchemeTeacher.setTeacherId(classGrouping.getResponsibleTeacherId());
                    groupingSchemeTeacher.setTeacherName(classGrouping.getResponsibleTeacherName());
                    groupingSchemeTeachers.add(groupingSchemeTeacher);
                }
            });
        });
        return new ArrayList<>(groupingSchemeTeachers);
    }

    public List<ClassGroupingVO> getResponsibleClassGroup(String teacherId, String className) {
        List<ClassGrouping> classGroupings = classGroupingRepository.findAll(ClassGroupingSpecification.specification(teacherId, className));
        Set<ClassGroupingVO> classGroupingVOS = new HashSet<>();
        classGroupings.forEach(classGrouping -> {
            ClassGroupingVO classGroupingVO = new ClassGroupingVO();
            classGroupingVO.setClassGroupId(classGrouping.getId());
            classGroupingVO.setClassGroupName(classGrouping.getClassGroupingName());
            classGroupingVOS.add(classGroupingVO);
        });
        return new ArrayList<>(classGroupingVOS);
    }

    public List<GroupingSchemeStudent> getResponsibleStudent(String groupIds, String responsibleTeacherId, String classGroupId) {
        List<GroupingSchemeStudent> groupingSchemeStudents = new ArrayList<>();
        if (StringUtils.isNotBlank(responsibleTeacherId)) {
            List<ClassGrouping> classGroupings = classGroupingRepository.findAll(ClassGroupingSpecification
                    .responsibleSpecification(groupIds, responsibleTeacherId, classGroupId));
            classGroupings.stream().forEach(classGrouping -> {
                classGrouping.getClassGroupingMemberList().stream().forEach(groupMember -> {
                    GroupingSchemeStudent groupingSchemeStudent = new GroupingSchemeStudent();
                    groupingSchemeStudent.setStudentId(groupMember.getStudentId());
                    groupingSchemeStudent.setStudentName(groupMember.getStudentName());
                    groupingSchemeStudents.add(groupingSchemeStudent);
                });
            });
        } else {
            String[] groupList = groupIds.split(",");
            List<Group> groups = groupRepository.findByIdIn(Arrays.asList(groupList));
            groups.stream().forEach(group -> {
                group.getGroupMemberList().stream().filter(groupMember -> groupMember.getGroupMemberStatus().equals(GroupMemberStatus.NORMAL))
                        .forEach(groupMember -> {
                            GroupingSchemeStudent groupingSchemeStudent = new GroupingSchemeStudent();
                            groupingSchemeStudent.setStudentId(groupMember.getStudentId());
                            groupingSchemeStudent.setStudentName(groupMember.getStudentName());
                            groupingSchemeStudents.add(groupingSchemeStudent);
                        });
            });
        }
        return groupingSchemeStudents;
    }

    public void updateClassGroupingInfo(String groupId, List<DeleteGroupMemberInfoVo> deleteGroupMemberInfoVos,
                                        String operatorId, String operatorName) {

        List<ClassGroupingScheme> classGroupingSchemes = classGroupingSchemeRepository.findByGroupId(groupId);
        classGroupingSchemes.forEach(classGroupingScheme -> {
            List<ClassGroupingMember> deleteGroupMembers = new ArrayList<>();

            classGroupingScheme.getClassGroupingList().forEach(classGrouping -> {
                List<ClassGroupingMember> classGroupingMembers = classGrouping.getClassGroupingMemberList();

                deleteGroupMemberInfoVos.forEach(deleteGroupMemberInfoVo -> {
                    ClassGroupingMember filter = classGroupingMembers.stream()
                            .filter(classGroupingMember ->
                                    classGroupingMember.getStudentId().equals(deleteGroupMemberInfoVo.getStudentId())).findFirst().orElse(null);

                    if (ObjectUtils.isNotEmpty(filter)) {
                        deleteGroupMembers.add(filter);
                    }
                });
            });

            if (CollectionUtils.isNotEmpty(deleteGroupMembers)) {
                classGroupingMemberRepository.deleteAll(deleteGroupMembers);

                Map<String, List<ClassGroupingMember>> map
                        = deleteGroupMembers.stream().collect(Collectors.groupingBy(ClassGroupingMember::getStudentId));
                classGroupingScheme.setGroupedHeadCount(classGroupingScheme.getGroupedHeadCount() - map.size());
                classGroupingScheme.setModifierId(operatorId);
                classGroupingScheme.setModifierName(operatorName);
                classGroupingScheme.setModifyTime(new Date());

                classGroupingSchemeRepository.save(classGroupingScheme);
            }
        });

    }
}

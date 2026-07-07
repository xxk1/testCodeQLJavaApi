package com.lztech.site.service.groupmember;

import com.alibaba.excel.EasyExcel;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberSource;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.domain.model.groupmember.enums.StudentIdentity;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.resource.group.ImportGroupMemberResource;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.service.classgroupingscheme.ClassGroupingSchemeService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.service.group.CacheUtil;
import com.lztech.site.service.group.GroupService;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.viewmodel.UserParam;
import com.lztech.site.viewmodel.authorityapi.UserResource;
import com.lztech.site.viewmodel.authorityapi.UsersInfoResource;
import com.lztech.site.viewmodel.event.GroupMemberEvent;
import com.lztech.site.viewmodel.group.*;
import com.lztech.site.viewmodel.groupmember.*;
import com.lztech.site.viewmodel.groupmember.GroupMemberResourceVo;
import com.lztech.site.viewmodel.userinfo.UserNoOrUserIdVo;
import com.lztech.site.viewmodel.userinfo.Users;
import com.lztech.site.viewmodel.userinfo.UsersInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ClassGroupingSchemeService classGroupingSchemeService;
    @Autowired
    private AuthorityApiService authorityApiService;
    @Autowired
    private EventService eventService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private CourseTableRepository courseTableRepository;

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Value("${request.address.authorityApi}")
    private String authorityApi;
    @Value("${signKey}")
    private String signKey;

    @Autowired
    private EntityManager entityManager;

    public ResponseEntity<Void> addGroupMembers(String id, String userName, String groupId,
                                                List<GroupMembersResource> groupMembersResources, String courseId) {
        List<GroupMembersResource> groupMembersResourceList = new ArrayList<>();
        Group group = groupRepository.findById(groupId).orElse(null);
        List<GroupMember> groupMembers = groupMemberRepository.findByGroupId(groupId);
        List<GroupMember> groupMemberList = new ArrayList<>();
        if (groupMembers.size() > 0) {
            for (GroupMembersResource groupMembersResource : groupMembersResources) {
                boolean flag = true;
                for (GroupMember groupMember : groupMembers) {
                    //如果存在同样studentId,绕行数据
                    if (StringUtils.isEmpty(groupMembersResource.getStudentId())) {
                        //如果studentId不存在，但是存在同样openId,绕行数据
                        if (groupMembersResource.getOpenId().equals(groupMember.getOpenId())) {
                            flag = false;
                            break;
                        }
                    } else {
                        if (groupMembersResource.getStudentId().equals(groupMember.getStudentId())) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    groupMembersResourceList.add(groupMembersResource);
                }
            }
            groupMembersResources = groupMembersResourceList;
        }
        groupMembersResources.stream().map(groupMembersResource -> {
            GroupMember groupMember;
            if (StringUtils.isEmpty(groupMembersResource.getStudentId())) {
                groupMember = groupMemberRepository.findByGroupIdAndOpenId(groupId, groupMembersResource.getOpenId());
            } else {
                groupMember = groupMemberRepository.findByGroupIdAndUserId(groupId, groupMembersResource.getStudentId());
            }
            if (ObjectUtils.isEmpty(groupMember)) {
                groupMember = new GroupMember();
            }
            groupMember.setGroup(group);
            groupMember.setStudentId(groupMembersResource.getStudentId());
            groupMember.setStudentName(groupMembersResource.getStudentName());
            groupMember.setStudentNo(groupMembersResource.getStudentNo());
            groupMember.setCreateTime(new Date());
            groupMember.setCreatorId(id);
            groupMember.setCreatorName(userName);
            groupMember.setModifyTime(new Date());
            groupMember.setModifierId(id);
            groupMember.setModifierName(userName);
            groupMember.setOpenId(groupMembersResource.getOpenId());
            groupMember.setAdministrativeClassName(groupMembersResource.getAdministrativeClassName());
            groupMember.setGroupMemberStatus(GroupMemberStatus.NORMAL);
            groupMember.setSource(GroupMemberSource.MANUALLY_ADD);
            groupMember.setStudentCollegeId(groupMembersResource.getCollegeId());
            groupMember.setStudentCollegeName(groupMembersResource.getCollegeName());
            groupMemberList.add(groupMember);
            return groupMemberList;
        }).collect(Collectors.toList());
        List<GroupMember> memberList = groupMemberRepository.saveAll(groupMemberList);
        if (dataVisualEnable) {
            memberList.forEach(groupMember -> {
                GroupMemberEvent groupMemberEvent = eventService.buildGroupMemberEvent(groupMember);
                eventService.sendGroupMemberEvent(groupMember.getId(), groupMemberEvent);
            });
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<UsersInfo>> getGroupMembers(String groupId, String userNoOrName) {
        List<GroupMember> groupMembers = groupMemberRepository.findByGroupIdOrderByStudentNo(groupId);
        Map<String, GroupMember> groupMemberMap = new HashMap<>();
        String userNoResult = CharactersReplace.replaceCharacters(userNoOrName);
        List<Users> usersList = new ArrayList<>();
        if (groupMembers.size() > 0) {
            groupMembers.stream().map(groupMember -> {
                Users users = new Users();
                users.setUserId(groupMember.getStudentId());
                users.setOpenId(groupMember.getOpenId());
                usersList.add(users);
                if (!groupMemberMap.containsKey(groupMember.getStudentId())) {
                    groupMemberMap.put(groupMember.getStudentId(), groupMember);
                }
                return usersList;
            }).collect(Collectors.toList());
        }

        List<UsersInfoResource> usersInfoList = authorityApiService.getUsersInfo(usersList);
        List<UsersInfo> usersInfos = new ArrayList<>();
        for (UsersInfoResource usersInfoResource : usersInfoList) {
            UsersInfo usersInfo = new UsersInfo();
            setBaseUserInfo(usersInfo, usersInfoResource);
            GroupMember groupMember = null;
            if (groupMemberMap.containsKey(usersInfo.getId())) {
                groupMember = groupMemberMap.get(usersInfo.getId());
                usersInfo.setClassNickName(groupMember.getGroup().getClassNickName());
                usersInfo.setGroupNo(groupMember.getGroup().getGroupNo());
                if (!ObjectUtils.isEmpty(groupMember.getStudentIdentity())) {
                    usersInfo.setStudentIdentityCode(String.valueOf(groupMember.getStudentIdentity().getIndex()));
                    usersInfo.setStudentIdentityName(groupMember.getStudentIdentity().getName());
                } else {
                    usersInfo.setStudentIdentityCode(String.valueOf(StudentIdentity.STUDENT.getIndex()));
                    usersInfo.setStudentIdentityName(StudentIdentity.STUDENT.getName());
                }
            }
            if (!ObjectUtils.isEmpty(groupMember)) {
                if (StringUtils.isEmpty(groupMember.getModifyTime())) {
                    usersInfo.setUpdateTime("");
                } else {
                    usersInfo.setUpdateTime(DateUtils.formatDate(DateUtils.DATE_TIME, groupMember.getModifyTime()));
                }
            }
            usersInfos.add(usersInfo);
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(userNoResult)) {
            usersInfos = usersInfos
                    .stream()
                    .filter(usersInfo -> usersInfo.getUserNo().contains(userNoResult)
                            || usersInfo.getUserName().contains(userNoResult))
                    .collect(Collectors.toList());
        }

        usersInfos = usersInfos.stream().sorted(
                Comparator.comparing(UsersInfo::getUpdateTime).reversed()
                        .thenComparing(UsersInfo::getUserNo)).collect(Collectors.toList());
        return new ResponseEntity<>(usersInfos, HttpStatus.OK);
    }

    private void setBaseUserInfo(UsersInfo usersInfo, UsersInfoResource usersInfoResource) {
        usersInfo.setId(usersInfoResource.getId());
        usersInfo.setClassId(usersInfoResource.getClassId());
        usersInfo.setClassName(usersInfoResource.getClassName());
        usersInfo.setOpenId(usersInfoResource.getOpenId());
        usersInfo.setUserName(usersInfoResource.getUserRealName());
        usersInfo.setUserNo(usersInfoResource.getUserNo());
        usersInfo.setAvatarPath(usersInfoResource.getAvatarPath());
        usersInfo.setCollege(usersInfoResource.getCollegeName());
        usersInfo.setCollegeCode(usersInfoResource.getCollegeCode());
        usersInfo.setCollegeId(usersInfoResource.getCollegeId());
        usersInfo.setEamil(usersInfoResource.getEmail());
        usersInfo.setGender(usersInfoResource.getGender());
        usersInfo.setMajorName(usersInfoResource.getMajorName());
        usersInfo.setAvatarInnerUrl(usersInfoResource.getAvatarInnerUrl());
        usersInfo.setAvatarOuterUrl(usersInfoResource.getAvatarOuterUrl());
        usersInfo.setGrade(usersInfoResource.getGrade());
    }


    public ResponseEntity<List<UsersInfo>> getGroupTeachers(String groupId, String courseId) {
        List<CourseTableDetailTeacher> courseTableDetailTeachers = courseTableDetailTeacherRepository
                .findAllByGroupIdAndCourseIdOrderByTeacherNo(groupId, courseId);
        List<Users> usersList = new ArrayList<>();
        Set<String> teacherIdSet = new HashSet<>();
        if (CollectionUtils.isNotEmpty(courseTableDetailTeachers)) {
            courseTableDetailTeachers.forEach(courseTableDetailTeacher -> {
                teacherIdSet.add(courseTableDetailTeacher.getTeacherId());
            });
        }
        List<CourseTable> courseTableList = courseTableRepository.findByGroupId(groupId);
        if (CollectionUtils.isNotEmpty(courseTableList)) {
            courseTableList.forEach(courseTable -> {
                if (org.apache.commons.lang3.StringUtils.isNotBlank(courseTable.getCourseTableTeacherId())){
                    List<String> teacherIdList = Arrays.asList(courseTable.getCourseTableTeacherId().split(","));
                    teacherIdSet.addAll(teacherIdList);
                }
            });
        }
        usersList = teacherIdSet.stream().map(teacherId -> {
            Users users = new Users();
            users.setUserId(teacherId);
            return users;
        }).collect(Collectors.toList());

        List<UsersInfoResource> usersInfoList = authorityApiService.getUsersInfo(usersList);
        List<UsersInfo> resultList = new ArrayList<>();
        usersInfoList.forEach(usersInfoResource -> {
            UsersInfo usersInfo = new UsersInfo();
            setBaseUserInfo(usersInfo, usersInfoResource);
            resultList.add(usersInfo);
        });
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }


    public ResponseEntity<List<GroupMembersResource>> getGroupMembersByGroupName(String groupName, String userNo) {
        List<GroupMember> groupMembers = groupMemberRepository.findByGroupName(groupName);
        List<GroupMembersResource> groupMembersResourceList = new ArrayList<>();
        if (groupMembers.size() > 0) {
            if (!StringUtils.isEmpty(userNo)) {
                groupMembers.stream().filter(groupMember -> groupMember.getStudentNo().equals(userNo))
                        .forEach(groupMember -> {
                            GroupMembersResource groupMembersResource = new GroupMembersResource();
                            groupMembersResource.setStudentId(groupMember.getStudentId());
                            groupMembersResource.setStudentName(groupMember.getStudentName());
                            groupMembersResource.setStudentNo(groupMember.getStudentNo());
                            groupMembersResource.setOpenId(groupMember.getOpenId());
                            groupMembersResource.setAdministrativeClassName(groupMember.getAdministrativeClassName());
                            groupMembersResource.setCollegeId(groupMember.getStudentCollegeId());
                            groupMembersResource.setCollegeName(groupMember.getStudentCollegeName());
                            groupMembersResourceList.add(groupMembersResource);
                        });
            } else {
                groupMembers.stream().map(groupMember -> {
                    GroupMembersResource groupMembersResource = new GroupMembersResource();
                    groupMembersResource.setStudentId(groupMember.getStudentId());
                    groupMembersResource.setStudentName(groupMember.getStudentName());
                    groupMembersResource.setStudentNo(groupMember.getStudentNo());
                    groupMembersResource.setOpenId(groupMember.getOpenId());
                    groupMembersResource.setAdministrativeClassName(groupMember.getAdministrativeClassName());
                    groupMembersResource.setCollegeId(groupMember.getStudentCollegeId());
                    groupMembersResource.setCollegeName(groupMember.getStudentCollegeName());
                    groupMembersResourceList.add(groupMembersResource);
                    return groupMembersResourceList;
                }).collect(Collectors.toList());
            }
        }
        return new ResponseEntity<>(groupMembersResourceList, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Void> deleteGroupMember(String teacherId, String studentId, String groupId, String teacherName) {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GroupMember groupMember = groupMemberRepository
                .findByStudentIdAndGroupAndGroupMemberStatus(studentId, group, GroupMemberStatus.NORMAL);
        if (groupMember == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        groupMember.setModifyTime(new Date());
        groupMember.setModifierId(teacherId);
        groupMember.setModifierName(teacherName);
        groupMember.setGroupMemberStatus(GroupMemberStatus.REMOVE);
        groupMemberRepository.save(groupMember);
        updateClassGroupingInfo(groupMember.getStudentId(), teacherId, teacherName, groupId);
        if (dataVisualEnable) {
            eventService.sendDeleteGroupMemberEvent(groupMember.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateClassGroupingInfo(String studentId, String operatorId, String operatorName, String groupId) {
        // 更新总人数，删除学生
        classGroupingSchemeService.updateClassGroupingMemberNum(studentId, operatorId, operatorName, groupId);
        classGroupingSchemeService.deleteClassGroupingStudent(studentId, groupId);
    }

    public ResponseEntity<ImportGroupMemberResource> importGroupMembers(
            String userId, String groupId, String courseId, MultipartFile file, HttpServletRequest request)
            throws IOException, InterruptedException, InvalidFormatException {
        List<CourseTableDetailTeacher> courseTableDetailTeachers = courseTableDetailTeacherRepository
                .findAllByGroupIdAndCourseIdOrderByTeacherNo(groupId, courseId);
        ImportGroupMemberResource importGroupMemberResource = new ImportGroupMemberResource();
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (fileName.length() > Constant.IMPORT_FILE_NAME_LIMIT_LENGTH) {
                importGroupMemberResource.setMessage("Excel文件名称不能超过" + Constant.IMPORT_FILE_NAME_LIMIT_LENGTH + "个字符");
                return new ResponseEntity<>(importGroupMemberResource, HttpStatus.OK);
            }
            String uploadUrl = request.getSession().getServletContext().getRealPath("\\upload\\");
            String stringUrl = uploadUrl + "\\" + fileName;
            File fileUrl = new File(stringUrl);
            File createFile = new File(uploadUrl);
            if (!createFile.exists()) {
                createFile.mkdir();
            }
            file.transferTo(fileUrl);
            String temporarys, temporary = importExcel(importGroupMemberResource, stringUrl, userId,
                    group, courseTableDetailTeachers);
            if (temporary.contains("success")) {
                String num = temporary.substring(temporary.lastIndexOf("-") + 1);
                importGroupMemberResource.setMessage("导入成功，共计导入" + num + "条");
                return new ResponseEntity<>(importGroupMemberResource, HttpStatus.OK);
            } else {
                if (temporary.contains("modelError")) {
                    importGroupMemberResource.setMessage("导入的学生信息模板不正确");
                    return new ResponseEntity<>(importGroupMemberResource, HttpStatus.OK);
                } else if (temporary.contains("isNull")) {
                    temporarys = temporary.substring(Constant.SIX);
                    importGroupMemberResource.setMessage("导入失败，第" + temporarys + "行有未填写项");
                    return new ResponseEntity<>(importGroupMemberResource, HttpStatus.OK);
                } else if (temporary.contains("noData")) {
                    importGroupMemberResource.setMessage("导入的学生信息模板信息不能为空");
                    return new ResponseEntity<>(importGroupMemberResource, HttpStatus.OK);
                } else if (temporary.contains("getUserInfoFailed")) {
                    importGroupMemberResource.setMessage("获取用户信息失败");
                    return new ResponseEntity<>(importGroupMemberResource, HttpStatus.OK);
                } else {
                    temporarys = temporary;
                    importGroupMemberResource.setMessage(temporarys);
                    return new ResponseEntity<>(importGroupMemberResource, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public String importExcel(ImportGroupMemberResource importGroupMemberResource,
                              String xlsPath, String userId, Group group,
                              List<CourseTableDetailTeacher> courseTableDetailTeachers) throws IOException, InvalidFormatException {
        String[] titleArr = new String[]{"工号/学号", "姓名"};
        List<UserParam> userParams = new ArrayList<>();
        if (xlsPath != null) {
            Workbook workBook;
            FileInputStream fileIn = new FileInputStream(xlsPath);
            workBook = WorkbookFactory.create(fileIn);
            fileIn.close();
            Sheet sheet = workBook.getSheetAt(0);
            Row row = sheet.getRow(0);

            if (verifyModelEttot(titleArr, row)) {
                return "modelError";
            }
            if (sheet.getRow(1) == null) {
                return "noData";
            }
            List<UserResource> userInfoList = authorityApiService.getAllUser();
            if (Objects.isNull(userInfoList)) {
                return "getUserInfoFailed";
            }
            String rowNum = getSheet(titleArr, userParams, sheet, group, userInfoList, courseTableDetailTeachers);
            if (rowNum != null) {
                return rowNum;
            }
            if (userParams.size() > 0) {
                List<GroupMember> groupMembers = new ArrayList<>();
                List<GroupMembersResource> groupMembersResources = new ArrayList<>();
                for (UserParam userParam : userParams) {
                    GroupMember groupMember = groupMemberRepository.findByStudentNoAndGroup(userParam.getStudentNo(), group);
                    if (groupMember == null) {
                        groupMember = new GroupMember();
                        groupMember.setCreateTime(new Date());
                        groupMember.setCreatorId(userId);
                        List<CourseTableDetailTeacher> courseTableDetailTeacherList =
                                courseTableDetailTeachers.stream().filter(t -> t.getTeacherId().equals(userId)).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(courseTableDetailTeacherList)) {
                            groupMember.setCreatorName(courseTableDetailTeacherList.get(0).getTeacherName());
                            groupMember.setModifierName(courseTableDetailTeacherList.get(0).getTeacherName());
                        }
                        groupMember.setModifyTime(new Date());
                        groupMember.setModifierId(userId);

                        groupMember.setStudentId(userParam.getStudentId());
                        groupMember.setStudentNo(userParam.getStudentNo());
                        groupMember.setStudentName(userParam.getStudentName());
                        groupMember.setGroup(group);
                        groupMember.setSource(GroupMemberSource.MANUALLY_ADD);
                        groupMember.setStudentCollegeId(userParam.getCollegeId());
                        groupMember.setStudentCollegeName(userParam.getCollegeName());
                        groupMember.setAdministrativeClassName(userParam.getAdministrativeClassName());
                    }

                    groupMember.setGroupMemberStatus(GroupMemberStatus.NORMAL);
                    groupMembers.add(groupMember);
                    GroupMembersResource groupMembersResource = new GroupMembersResource();
                    groupMembersResource.setStudentId(groupMember.getStudentId());
                    groupMembersResource.setStudentName(groupMember.getStudentName());
                    groupMembersResource.setStudentNo(groupMember.getStudentNo());
                    groupMembersResources.add(groupMembersResource);
                }
                List<GroupMember> members = groupMemberRepository.saveAll(groupMembers);
                importGroupMemberResource.setGroupMembers(groupMembersResources);
                if (dataVisualEnable) {
                    members.forEach(groupMember -> {
                        GroupMemberEvent groupMemberEvent = eventService.buildGroupMemberEvent(groupMember);
                        eventService.sendGroupMemberEvent(groupMember.getId(), groupMemberEvent);
                    });
                }
                return "success-" + groupMembers.size();
            } else {
                return "noData";
            }
        }
        return "null";
    }

    private boolean verifyModelEttot(String[] titleArr, Row row) {
        for (short i = 0; i < titleArr.length; i++) {
            if (row.getCell(i) != null) {
                row.getCell(i).setCellType(CellType.STRING);
                if (!row.getCell(i).getStringCellValue().trim().equals(titleArr[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getSheet(String[] titleArr,
                            List<UserParam> userParams,
                            Sheet sheet,
                            Group group,
                            List<UserResource> userInfoList,
                            List<CourseTableDetailTeacher> courseTableDetailTeachers) {
        for (Row rows : sheet) {
            if (rows.getRowNum() < 1) {
                continue;
            }
            int checkNum = 0;
            checkNum = getCheckNum(titleArr, rows, checkNum);
            if (checkNum > titleArr.length) {
                break;
            }
            int rowNum = rows.getRowNum() + 1;
            if (verifyIsNull(titleArr, rows)) {
                return "isNull" + rowNum;
            }
            String userNo = rows.getCell(0).getStringCellValue().trim();
            UserResource userMap = userInfoList
                    .stream()
                    .filter(userResource -> userNo.equals(userResource.getUserNo()))
                    .findFirst().orElse(null);
            long count0 = userParams
                    .stream()
                    .filter(p -> p.getStudentNo().equals(userNo))
                    .count();
            if (count0 > 0) {
                return "导入失败，“工号/学号：" + userNo + "”存在重复";
            }

            if (Objects.isNull(userMap)) {
                return "导入失败，第" + rowNum + "行“工号/学号：" + userNo + "”不存在";
            }

            List<GroupMember> groupMembers = groupMemberRepository
                    .findByGroupAndGroupMemberStatus(group, GroupMemberStatus.NORMAL);
            long count = groupMembers
                    .stream()
                    .filter(p -> p.getStudentNo().equals(userNo))
                    .count();
            if (count > 0) {
                return "导入失败，第" + rowNum + "行“工号/学号：" + userNo + "”已存在当前班级";
            }
            long count1 = courseTableDetailTeachers
                    .stream()
                    .filter(p -> p.getTeacherNo().equals(userNo))
                    .count();
            if (count1 > 0) {
                return "导入失败，第" + rowNum + "行“工号/学号：" + userNo + "”已存在当前班级";
            }
            addUserParams(userParams, userMap, group.getId());
        }
        return null;
    }

    private int getCheckNum(String[] titleArr, Row rows, int checkNum) {
        for (short i = 0; i < titleArr.length; i++) {
            if (rows.getCell(i) == null) {
                checkNum++;
            }
        }
        return checkNum;
    }

    private boolean verifyIsNull(String[] titleArr, Row rows) {
        for (short i = 0; i < titleArr.length; i++) {
            if (rows.getCell(i) != null) {
                rows.getCell(i).setCellType(CellType.STRING);
                if (StringUtils.isEmpty(rows.getCell(i).getStringCellValue()) || "".equals(rows.getCell(i).getStringCellValue().trim())) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private void addUserParams(List<UserParam> userParams, UserResource userMap, String groupId) {
        UserParam userParam = new UserParam();
        userParam.setStudentId(userMap.getUserId());
        userParam.setStudentNo(userMap.getUserNo());
        userParam.setStudentName(userMap.getUserName());
        userParam.setTeachingClassId(groupId);
        userParam.setCollegeId(userMap.getCollegeId());
        userParam.setCollegeName(userMap.getCollegeName());
        userParam.setAdministrativeClassName(userMap.getAdministrativeClassName());
        userParams.add(userParam);
    }

    public ResponseEntity<UsersInfo> getGroupMembersUserIdOrOpenId(String userId, String openId, String groupId, Integer excludeDelete) {
        if (excludeDelete == null || excludeDelete == 0) {
            if (!StringUtils.isEmpty(userId)) {
                GroupMember groupMember = groupMemberRepository.findByStudentIdAndGroupId(userId, groupId);
                if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(groupMember)) {
                    return getUsersInfoResponseEntity(groupMember);
                }

            }

            if (!StringUtils.isEmpty(openId)) {
                GroupMember groupMember = groupMemberRepository.findByOpenIdAndGroupId(openId, groupId);
                if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(groupMember)) {
                    return getUsersInfoResponseEntity(groupMember);
                }
            }
        } else {
            if (!StringUtils.isEmpty(userId)) {
                GroupMember groupMember =
                        groupMemberRepository.findByStudentIdAndGroupIdAndGroupMemberStatus(userId, groupId, GroupMemberStatus.NORMAL);
                if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(groupMember)) {
                    return getUsersInfoResponseEntity(groupMember);
                }

            }

            if (!StringUtils.isEmpty(openId)) {
                GroupMember groupMember =
                        groupMemberRepository.findByOpenIdAndGroupIdAndGroupMemberStatus(openId, groupId, GroupMemberStatus.NORMAL);
                if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(groupMember)) {
                    return getUsersInfoResponseEntity(groupMember);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<UsersInfo> getUsersInfoResponseEntity(GroupMember groupMember) {
        UsersInfo usersInfo = new UsersInfo();
        usersInfo.setClassName(groupMember.getGroup().getGroupName());
        usersInfo.setOpenId(groupMember.getOpenId());
        usersInfo.setUserName(groupMember.getStudentName());
        usersInfo.setUserNo(groupMember.getStudentNo());
        usersInfo.setId(groupMember.getStudentId());
        usersInfo.setCreateTime(DateUtils.formatDate(DateUtils.DATE_TIME, groupMember.getCreateTime()));
        return new ResponseEntity<>(usersInfo, HttpStatus.OK);
    }

    public List<StudentVo> getGroupMemberByStudentNoOrNameIsLike(GroupMemberByStudentNoOrNameVo userNoOrUserIdVoList) {
        List<StudentVo> list = new ArrayList<>();
        String s = CharactersReplace.replaceCharacters(userNoOrUserIdVoList.getStudentNoOrName());
        try {
            List<GroupMember> groupMemberList = groupMemberRepository.getGroupMemberByStudentNoOrNameIsLike(
                    userNoOrUserIdVoList.getGroupIdList(),
                    s);
            groupMemberList.forEach(item -> {
                StudentVo studentVo = new StudentVo();
                studentVo.setGroupId(item.getGroup().getId());
                studentVo.setGroupName(item.getGroup().getGroupName());
                studentVo.setStudentId(item.getStudentId());
                studentVo.setStudentName(item.getStudentName());
                studentVo.setStudentNo(item.getStudentNo());
                list.add(studentVo);
            });
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    @Transactional
    public void batchDeleteGroupMembers(String teacherId, String studentIds, String groupId, String teacherName) {
        List<String> studentIdList = new ArrayList<>(1);
        if (studentIds.contains(",")) {
            studentIdList.addAll(Arrays.asList(studentIds.split(",")));
        } else {
            studentIdList.add(studentIds);
        }
        groupMemberRepository.deleteByStudentIdInAndGroupId(studentIdList, groupId, teacherId, teacherName);
        for (String studentNo : studentIdList) {
            updateClassGroupingInfo(studentNo, teacherId, teacherName, groupId);
        }
        Optional<Group> optionalGroup = this.groupRepository.findById(groupId);
        if (!ObjectUtils.isEmpty(optionalGroup.get()) && dataVisualEnable) {
            List<GroupMember> memberList = groupMemberRepository.findByGroupAndStudentIdIn(optionalGroup.get(), studentIdList);
            memberList.forEach(groupMember -> {
                eventService.sendDeleteGroupMemberEvent(groupMember.getId());
            });
        }
    }

    public List<UsersInfo> getGroupTeachersByGroupIds(List<String> groupIds, String courseId) {
        List<CourseTableDetailTeacher> courseTableDetailTeachers = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(groupIds)) {
            groupIds.forEach(groupId -> {
                courseTableDetailTeachers.addAll(courseTableDetailTeacherRepository
                        .findAllByGroupIdAndCourseIdOrderByTeacherNo(groupId, courseId));
            });

        }
        List<Users> usersList = new ArrayList<>();
        if (courseTableDetailTeachers.size() > 0) {
            courseTableDetailTeachers.stream().map(courseTableDetailTeacher -> {
                Users users = new Users();
                users.setUserId(courseTableDetailTeacher.getTeacherId());
                users.setOpenId("");
                usersList.add(users);
                return usersList;
            }).collect(Collectors.toList());

        }
        List<UsersInfoResource> usersInfoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(usersList)) {
            usersInfoList = authorityApiService.getUsersInfo(usersList);
        }
        List<UsersInfo> resultList = new ArrayList<>();
        usersInfoList.forEach(usersInfoResource -> {
            UsersInfo usersInfo = new UsersInfo();
            setBaseUserInfo(usersInfo, usersInfoResource);
            resultList.add(usersInfo);
        });
        return resultList;
    }

    public List<AssistantResource> getGroupSchoolTermUserAssistants(String schoolYear, Integer term, String id) {
        String sql = "select DISTINCT  a.student_id as studentId, a.student_no as studentNo ," +
                " a.student_name as studentName from tb_group_member a " +
                "INNER JOIN tb_group b on a.group_id = b.id " +
                "INNER JOIN tb_course_table c on  c.group_id = b.id " +
                "INNER JOIN tb_course_table_detail d on d.course_table_id = c.id " +
                "INNER JOIN tb_course_table_detail_teacher e on e.course_table_detail_id = d.id " +
                "where c.school_year = :schoolYear and c.term = :term and e.teacher_id = :id " +
                "and a.student_identity = 1 and a.group_member_status = 0 ";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(AssistantResource.class));
        query.setParameter("schoolYear", schoolYear);
        query.setParameter("term", term);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public void updateGroupMemberStudentIdentity(String studentId, String groupId, Integer studentIdentityCode) {
        GroupMember groupMember = groupMemberRepository.findValidByGroupIdAndUserId(groupId, studentId);
        groupMember.setStudentIdentity(StudentIdentity.getStudentIdentity(studentIdentityCode));
        groupMemberRepository.save(groupMember);
    }

    public GroupMemberResourcePage getGroupMemberResourceList(GroupMemberQueryParamVo queryParam) {
        GroupMemberResourcePage memberResourcePage = new GroupMemberResourcePage();
        int count = this.queryGroupMemberResourceCount(queryParam);
        memberResourcePage.setPage(queryParam.getPage());
        memberResourcePage.setPageSize(queryParam.getPageSize());
        memberResourcePage.setTotal(count);
        if (count == 0) {
            memberResourcePage.setTotal(0);
            memberResourcePage.setTotalPage(0);
            memberResourcePage.setGroupMemberResourceList(new ArrayList<>());
            return memberResourcePage;
        }

        memberResourcePage.setTotalPage((int) Math.ceil(count * 1.0 / queryParam.getPageSize()));
        List<GroupMemberResourceVo> groupMemberResources = this.queryGroupMemberResource(queryParam);
        memberResourcePage.setGroupMemberResourceList(groupMemberResources);

        return memberResourcePage;
    }

    private Integer queryGroupMemberResourceCount(GroupMemberQueryParamVo queryParam) {

        String querySql = "SELECT COUNT(1) AS allNumber" +
                " FROM tb_group_member gm" +
                " INNER JOIN tb_group g on g.id = gm.group_id" +
                " INNER JOIN tb_course_table ct on ct.group_id = g.id" +
                " INNER JOIN tb_course c on ct.course_id = c.id" +
                " WHERE gm.group_member_status = 0";

        Map<String, Object> paramMap = new HashMap<>();
        querySql += " and ct.school_year = :schoolYear ";
        paramMap.put("schoolYear", queryParam.getSchoolYear());
        querySql += " and ct.term = :term ";
        paramMap.put("term", queryParam.getTerm());

        if (!StringUtils.isEmpty(queryParam.getCourseName())) {
            querySql += " and ct.course_name like :courseName ";
            paramMap.put("courseName", "%" + CharactersReplace.replaceCharacters(queryParam.getCourseName()) + "%");
        }

        if (!StringUtils.isEmpty(queryParam.getClassName())) {
            querySql += "and g.group_name like  :groupName ";
            paramMap.put("groupName", "%" + CharactersReplace.replaceCharacters(queryParam.getClassName()) + "%");
        }

        if (!StringUtils.isEmpty(queryParam.getStudentNoOrName())) {
            querySql += " and (gm.student_no like :student or gm.student_name like :student) ";
            paramMap.put("student", "%" + CharactersReplace.replaceCharacters(queryParam.getStudentNoOrName()) + "%");
        }
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);

        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) query.getResultList().get(0);
        int count = ((BigInteger) resMap.get("allNumber")).intValue();
        return count;
    }


    private List<GroupMemberResourceVo> queryGroupMemberResource(GroupMemberQueryParamVo queryParam) {
        String querySql = "SELECT gm.id AS groupMemberId,ct.school_year AS schoolYear,ct.term AS term,t.nick_name as schoolYearTermNickName," +
                "c.course_code AS courseCode,ct.course_name AS courseName," +
                "g.group_no AS groupNo,g.group_name AS groupName," +
                "gm.student_no AS studentNo,gm.student_name AS studentName,IF(ct.source=0,'数据对接','手动添加') AS source" +
                " FROM tb_group_member gm" +
                " INNER JOIN tb_group g on g.id = gm.group_id" +
                " INNER JOIN tb_course_table ct on ct.group_id = g.id" +
                " INNER JOIN tb_course c on ct.course_id = c.id" +
                " INNER JOIN tb_term t on t.school_year = ct.school_year and t.term = ct.term" +
                " WHERE gm.group_member_status = 0";

        Map<String, Object> paramMap = new HashMap<>();
        querySql += " and ct.school_year = :schoolYear ";
        paramMap.put("schoolYear", queryParam.getSchoolYear());
        querySql += " and ct.term = :term ";
        paramMap.put("term", queryParam.getTerm());

        if (!StringUtils.isEmpty(queryParam.getCourseName())) {
            querySql += " and ct.course_name like :courseName ";
            paramMap.put("courseName", "%" + CharactersReplace.replaceCharacters(queryParam.getCourseName()) + "%");
        }

        if (!StringUtils.isEmpty(queryParam.getClassName())) {
            querySql += "and g.group_name like  :groupName ";
            paramMap.put("groupName", "%" + CharactersReplace.replaceCharacters(queryParam.getClassName()) + "%");
        }

        if (!StringUtils.isEmpty(queryParam.getStudentNoOrName())) {
            querySql += " and (gm.student_no like :student or gm.student_name like :student) ";
            paramMap.put("student", "%" + CharactersReplace.replaceCharacters(queryParam.getStudentNoOrName()) + "%");
        }

        querySql += " ORDER BY gm.modify_time desc,gm.student_no asc";

        querySql += " LIMIT :start,:pageSize";
        paramMap.put("start", (queryParam.getPage() - 1) * queryParam.getPageSize());
        paramMap.put("pageSize", queryParam.getPageSize());

        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);

        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GroupMemberResourceVo.class));
        List<GroupMemberResourceVo> voList = (List<GroupMemberResourceVo>) query.getResultList();
        return voList;
    }

    public List<DeleteGroupMemberInfoVo> queryGroupMembersByIds(List<DeleteGroupMemberIdVo> deleteGroupMembers) {
        String querySql = "SELECT ct.source AS source,gm.student_no AS studentNo,gm.student_name AS studentName," +
                "gm.group_id AS groupId,gm.id AS groupMemberId,gm.student_id AS studentId" +
                " FROM tb_group_member gm" +
                " INNER JOIN tb_group g on g.id = gm.group_id" +
                " INNER JOIN tb_course_table ct on ct.group_id = g.id" +
                " where gm.group_member_status = 0" +
                " and gm.id in (:ids)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ids", deleteGroupMembers.stream().map(DeleteGroupMemberIdVo::getGroupMemberId).collect(Collectors.toList()));

        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);

        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(DeleteGroupMemberInfoVo.class));
        List<DeleteGroupMemberInfoVo> voList = (List<DeleteGroupMemberInfoVo>) query.getResultList();
        return voList;
    }

    @Transactional
    public void deleteGroupMembersByGroupMembers(List<DeleteGroupMemberInfoVo> deleteGroupMemberInfoVos,
                                                 String operatorId, String operatorName) {
        List<String> ids = deleteGroupMemberInfoVos.stream()
                .map(obj -> obj.getGroupMemberId()).collect(Collectors.toList());

        groupMemberRepository.deleteByIds(ids, operatorId, operatorName);

        Map<String, List<DeleteGroupMemberInfoVo>> mapGroup =
                deleteGroupMemberInfoVos.stream().collect(Collectors.groupingBy(DeleteGroupMemberInfoVo::getGroupId));

        mapGroup.forEach((k, v) -> {
            classGroupingSchemeService.updateClassGroupingInfo(k, v, operatorId, operatorName);
        });

        if (dataVisualEnable) {
            deleteGroupMemberInfoVos.forEach(groupMember -> {
                eventService.sendDeleteGroupMemberEvent(groupMember.getGroupMemberId());
            });
        }
    }

    public ImportStudentsVo multipartFileImportStudents(MultipartFile importFile, String operatorId, String operatorName) throws Exception {
        CacheUtil.put(Constant.OPERATOR_ID, operatorId);
        CacheUtil.put(Constant.OPERATOR_NAME, operatorName);

        // 查询数据库中所有的班级编号
        List groupNoList = groupService.getAllGroupNo();
        if (CollectionUtils.isNotEmpty(groupNoList)) {
            CacheUtil.put(Constant.GROUP_NO_LIST, groupNoList);
        }

        //获取所有学生信息并过滤启用的
        List<com.lztech.site.viewmodel.authorityapi.StudentVo> authorityApiServiceAllStudent = authorityApiService.getAllStudent();
        if (CollectionUtils.isNotEmpty(authorityApiServiceAllStudent)) {
            List<String> allStudentNo = authorityApiServiceAllStudent.stream().map(com.lztech.site.viewmodel.authorityapi.StudentVo::getStudentNo)
                    .collect(Collectors.toList());
            CacheUtil.put(Constant.ALL_STUDENT_NO, allStudentNo);
        }

        EasyExcel
                .read(importFile.getInputStream(),
                        ImportStudentExcelObject.class,
                        new ImportStudentListener(groupRepository, this, groupMemberRepository))
                .sheet().doRead();
        return (ImportStudentsVo) CacheUtil.get(Constant.IMPORT_STUDENT_VO);
    }

    public List<UserResource> getUserListByUserNoOrUserId(UserNoOrUserIdVo userNoOrUserIdVo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = authorityApi + ConstantWebApi.GET_USER_RESOURCE + "?validCode=" + Md5Utils.md5(signKey);

        //设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<UserNoOrUserIdVo> httpEntity = new HttpEntity<>(userNoOrUserIdVo, headers);

        ResponseEntity<List<UserResource>> responseEntity = restTemplate
                .exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<UserResource>>() {
                });
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return new ArrayList<>();
    }


    public List<GroupMemberBranch> getGroupMemberBranch(String groupId) {
        String querySql = "SELECT cast(count(group_id) as char) as studentNum,learning_method_name as learningMethodName " +
                "FROM `tb_group_member` " +
                "WHERE group_member_status=0 and group_id=:groupId GROUP BY learning_method_name  ";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", groupId);

        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);

        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GroupMemberBranch.class));
        List<GroupMemberBranch> voList = (List<GroupMemberBranch>) query.getResultList();
        return voList;
    }

    public List<GroupResourceVo> getGroupByGroupMember(String studentId, String schoolYear, Integer term) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = getQueryStudentSql(studentId, schoolYear, term, paramMap);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GroupResourceVo.class));

        return query.getResultList();
    }

    private String getQueryStudentSql(String studentId,
                                      String schoolYear,
                                      Integer term, Map<String, Object> paramMap) {
        String sql = "select b.id as groupId   from tb_course_table a " +
                " inner join tb_group b on a.group_id=b.id " +
                " inner join tb_group_member c on b.id=c.group_id " +
                " where b.group_status=0 and c.group_member_status=0 ";

        if (org.apache.commons.lang3.StringUtils.isNotBlank(studentId)) {
            sql += "and c.student_id=:studentId ";
            paramMap.put("studentId", studentId);
        }

        if (org.apache.commons.lang3.StringUtils.isNotBlank(schoolYear)) {
            sql += "and a.school_year=:schoolYear ";
            paramMap.put("schoolYear", schoolYear);
        }
        if (term != null) {
            sql += "and a.term=:term ";
            paramMap.put("term", term);
        }
        sql += " group by b.id";

        return sql;

    }
}

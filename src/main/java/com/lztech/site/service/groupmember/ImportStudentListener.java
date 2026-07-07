package com.lztech.site.service.groupmember;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberSource;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.group.CacheUtil;
import com.lztech.site.service.group.annotation.AnnotationValidator;
import com.lztech.site.viewmodel.authorityapi.UserResource;
import com.lztech.site.viewmodel.group.ImportStudentExcelObject;
import com.lztech.site.viewmodel.group.ImportStudentsVo;
import com.lztech.site.viewmodel.userinfo.UserNoOrUserIdVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.EMPTY_TABLE_ERROR_CODE;
import static com.lztech.site.constants.Constant.TABLE_UPPER_LIMIT_ERROR_CODE;

@Slf4j
@Component
public class ImportStudentListener implements ReadListener<ImportStudentExcelObject> {


    private final GroupRepository groupRepository;

    private final GroupMemberService groupMemberService;

    private final GroupMemberRepository groupMemberRepository;
    private List<ImportStudentExcelObject> cachedDataList = ListUtils.newArrayListWithExpectedSize(Constant.BATCH_COUNT);

    public ImportStudentListener(GroupRepository groupRepository,
                                 GroupMemberService groupMemberService,
                                 GroupMemberRepository groupMemberRepository) {

        this.groupRepository = groupRepository;
        this.groupMemberService = groupMemberService;
        this.groupMemberRepository = groupMemberRepository;
    }

    @Override
    public void invoke(ImportStudentExcelObject data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        ArrayList<String> everyRowErrorMessages = AnnotationValidator.valid(data, context.readRowHolder().getRowIndex());

        // 跳过空行
        if (StrUtil.isAllEmpty(
                data.getStudentCode(),
                data.getClassNo(),
                data.getClassName(),
                data.getStudentName())) {
            return;
        }

        if (!CacheUtil.isContainKey(Constant.ROW_INDEX)) {
            CacheUtil.put(Constant.ROW_INDEX, String.valueOf(Constant.ROW_INDEX_VALUE_INIT + 1));
        } else {
            CacheUtil.put(Constant.ROW_INDEX, String.valueOf(Integer.parseInt((String) CacheUtil.get(Constant.ROW_INDEX)) + 1));
        }

        if (!CacheUtil.isContainKey(Constant.EVERY_ROW_ERROR_MESSAGES)) {
            CacheUtil.put(Constant.EVERY_ROW_ERROR_MESSAGES, everyRowErrorMessages);
        } else {
            ((ArrayList<String>) CacheUtil.get(Constant.EVERY_ROW_ERROR_MESSAGES)).addAll(everyRowErrorMessages);
        }
        if (ObjectUtils.isEmpty(CacheUtil.get(Constant.EVERY_ROW_ERROR_MESSAGES))) {
            cachedDataList.add(data);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        ImportStudentsVo importStudentsVo = new ImportStudentsVo();

        if (!CacheUtil.isContainKey(Constant.ROW_INDEX)) {
            importStudentsVo.setSuccessRows(Constant.ROW_INDEX_VALUE_INIT);
        } else {
            importStudentsVo.setSuccessRows(Integer.parseInt((String) CacheUtil.get(Constant.ROW_INDEX)));
        }

        ArrayList<String> errorMessagesList = (ArrayList<String>) CacheUtil.get(Constant.EVERY_ROW_ERROR_MESSAGES);
        if (ObjectUtils.isEmpty(errorMessagesList)) {
            CacheUtil.put(Constant.EVERY_ROW_ERROR_MESSAGES, new ArrayList<String>());
        }
        errorMessagesList = (ArrayList<String>) CacheUtil.get(Constant.EVERY_ROW_ERROR_MESSAGES);

        if (ObjectUtils.isEmpty(CacheUtil.get(Constant.ROW_INDEX))) {
            errorMessagesList.clear();
            errorMessagesList.add(EMPTY_TABLE_ERROR_CODE);
            importStudentsVo.setErrorInfo(errorMessagesList);
            CacheUtil.put(Constant.IMPORT_STUDENT_VO, importStudentsVo);
            return;
        }

        int count = Integer.parseInt((String) CacheUtil.get(Constant.ROW_INDEX));
        if (count >= Constant.ROW_INDEX_MAX_VALUE) {
            errorMessagesList.clear();
            errorMessagesList.add(TABLE_UPPER_LIMIT_ERROR_CODE);
            importStudentsVo.setErrorInfo(errorMessagesList);
            CacheUtil.put(Constant.IMPORT_STUDENT_VO, importStudentsVo);
            return;
        }

        if (CollectionUtils.isNotEmpty(errorMessagesList)) {
            importStudentsVo.setErrorInfo(errorMessagesList);
            importStudentsVo.setErrorRows(errorMessagesList.size());
        }
        CacheUtil.put(Constant.IMPORT_STUDENT_VO, importStudentsVo);
        if (CollectionUtils.isEmpty(errorMessagesList)) {
            savaData(cachedDataList);
        }
    }

    public void savaData(List<ImportStudentExcelObject> cachedDataList) {
        String operatorId = (String) CacheUtil.get(Constant.OPERATOR_ID);
        String operatorName = (String) CacheUtil.get(Constant.OPERATOR_NAME);
        Date nowDate = new Date();
        List<String> classNoList = cachedDataList
                .stream()
                .map(ImportStudentExcelObject::getClassNo)
                .collect(Collectors.toList());
        List<Group> groupList = groupRepository.findByGroupNoInAndGroupStatus(classNoList, GroupStatus.NORMAL);
        if (groupList.size() > 0) {
            List<String> groupIdList = groupList.stream().map(Group::getId).collect(Collectors.toList());
            List<GroupMember> groupMemberList = groupMemberRepository.findByGroupIdInAndGroupMemberStatus(groupIdList, GroupMemberStatus.NORMAL);
            Map<String, List<GroupMember>> groupMemberByGroupNoListMap = groupMemberList
                    .stream()
                    .collect(Collectors
                            .groupingBy(groupMember -> groupMember.getGroup().getGroupNo()));
            Map<String, List<ImportStudentExcelObject>> groupMemberToBeSavedByGroupNoListMap = cachedDataList
                    .stream()
                    .collect(Collectors
                            .groupingBy(ImportStudentExcelObject::getClassNo));
//            List<String> studentNoList = cachedDataList
//                    .stream()
//                    .filter(importStudentExcelObject
//                            -> groupList.stream().anyMatch(group -> group.getGroupNo().equals(importStudentExcelObject.getClassNo())))
//                    .map(ImportStudentExcelObject::getStudentCode)
//                    .collect(Collectors.toList());

            List<GroupMember> saveGroupMemberList = new ArrayList<>();
            groupList.forEach(group -> {
                List<String> studentNoList = cachedDataList
                        .stream()
                        .filter(data -> group.getGroupNo().equals(data.getClassNo()))
                        .map(ImportStudentExcelObject::getStudentCode)
                        .collect(Collectors.toList());
                UserNoOrUserIdVo userNoOrUserIdVo = new UserNoOrUserIdVo();
                userNoOrUserIdVo.setWhetherExistEmail(false);
                userNoOrUserIdVo.setUserNoList(studentNoList);
                userNoOrUserIdVo.setUserIdList(new ArrayList<>());
                List<UserResource> userResourceList = groupMemberService.getUserListByUserNoOrUserId(userNoOrUserIdVo);
                List<GroupMember> groupMemberSavedList = groupMemberByGroupNoListMap.get(group.getGroupNo());
                List<ImportStudentExcelObject> groupMemberToBeSavedList = groupMemberToBeSavedByGroupNoListMap.get(group.getGroupNo());
                List<ImportStudentExcelObject> groupMemberNeedToSaveExcelList = groupMemberToBeSavedList
                        .stream()
                        .filter(importStudentExcelObject
                                -> (groupMemberSavedList == null
                                || groupMemberSavedList.stream().noneMatch(groupMember
                                -> groupMember.getStudentNo().equals(importStudentExcelObject.getStudentCode()))))
                        .collect(Collectors.toList());
                List<UserResource> groupMemberNeedToSaveList = userResourceList
                        .stream()
                        .filter(userResource
                                -> (groupMemberSavedList == null ||
                                groupMemberNeedToSaveExcelList.stream().anyMatch(importStudentExcelObject
                                        -> importStudentExcelObject.getStudentCode().equals(userResource.getUserNo()))
                        ))
                        .collect(Collectors.toList());
                groupMemberNeedToSaveList.forEach(userResource -> {
                    GroupMember groupMember = new GroupMember();
                    groupMember.setGroup(group);
                    groupMember.setAdministrativeClassName(userResource.getAdministrativeClassName());
                    groupMember.setGroupMemberStatus(GroupMemberStatus.NORMAL);
                    groupMember.setSource(GroupMemberSource.MANUALLY_ADD);
                    groupMember.setStudentId(userResource.getUserId());
                    groupMember.setStudentNo(userResource.getUserNo());
                    groupMember.setStudentName(userResource.getUserName());
                    groupMember.setCreateTime(nowDate);
                    groupMember.setCreatorId(operatorId);
                    groupMember.setCreatorName(operatorName);
                    groupMember.setModifierId(operatorId);
                    groupMember.setModifierName(operatorName);
                    groupMember.setModifyTime(nowDate);
                    saveGroupMemberList.add(groupMember);
                });
            });
            if (saveGroupMemberList.size() > 0) {
                groupMemberRepository.saveAll(saveGroupMemberList);
            }
        }
    }

}

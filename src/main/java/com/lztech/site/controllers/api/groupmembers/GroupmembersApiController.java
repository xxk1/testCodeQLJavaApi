package com.lztech.site.controllers.api.groupmembers;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.group.ImportGroupMemberResource;
import com.lztech.site.service.group.CacheUtil;
import com.lztech.site.service.group.GroupService;
import com.lztech.site.service.groupmember.GroupMemberService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.StudentInfo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.group.*;
import com.lztech.site.viewmodel.groupmember.GroupMemberBranch;
import com.lztech.site.viewmodel.userinfo.UsersInfo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URLDecoder;
import java.util.List;

import static com.lztech.site.constants.Constant.EMPTY_TABLE_ERROR_CODE;
import static com.lztech.site.constants.Constant.TABLE_UPPER_LIMIT_ERROR_CODE;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-19T08:59:41.236Z")

@Controller
public class GroupmembersApiController implements GroupmembersApi {

    private static final Logger LOG = LoggerFactory.getLogger(GroupmembersApiController.class);


    @Autowired
    private GroupMemberService groupMemberService;
    @Autowired
    private GroupService groupService;

    @Value("${signKey}")
    private String signKey;
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GroupmembersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<UsersInfo>> groupmembersGroupIdGet(
            @ApiParam(value = "组Id", required = true) @PathVariable("groupId") String groupId,
            @NotNull @ApiParam(value = "验证码：groupId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学号/工号或者姓名")
            @Valid @RequestParam(value = "userNoOrName", required = false) String userNoOrName) {

        try {
            if (StringUtils.isAllBlank(groupId, userNoOrName)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String validKey = "groupId=" + groupId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupMemberService.getGroupMembers(groupId, userNoOrName);
        } catch (Exception e) {
            LOG.error("groupmembersGroupIdGet：", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<UsersInfo>> groupteachersGet(@NotNull @ApiParam(value = "组Id", required = true)
                                                            @Valid @RequestParam(value = "groupId", required = true)
                                                            String groupId,
                                                            @NotNull @ApiParam(value = "课程Id", required = true)
                                                            @Valid @RequestParam(value = "courseId", required = true) String courseId,
                                                            @NotNull @ApiParam(value = "验证码：groupId=?&signKey=123123(md5加密)", required = true)
                                                            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (StringUtils.isAnyBlank(groupId, courseId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String validKey = "groupId=" + groupId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupMemberService.getGroupTeachers(groupId, courseId);
        } catch (Exception e) {
            LOG.error("groupGet", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<GroupMembersResource>> groupmembersGet(@NotNull @ApiParam(value = "组名称", required = true)
                                                                      @Valid @RequestParam(value = "groupName", required = true)
                                                                      String groupName,
                                                                      @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)",
                                                                              required = true)
                                                                      @Valid @RequestParam(value = "validCode", required = true)
                                                                      String validCode,
                                                                      @ApiParam(value = "学号/工号")
                                                                      @Valid @RequestParam(value = "userNo", required = false) String userNo) {
        try {
            if (StringUtils.isAnyBlank(groupName)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            groupName = URLDecoder.decode(groupName, "UTF-8");
            return groupMemberService.getGroupMembersByGroupName(groupName, userNo);

        } catch (Exception e) {
            LOG.error("groupGet", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ImportGroupMemberResource> groupImportgroupmembersPost(
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "课程Id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "组Id", required = true)
            @Valid @RequestParam(value = "groupId", required = true) String groupId,
            @ApiParam(value = "成员信息")
            @Valid @RequestPart(value = "groupMember", required = true) MultipartFile groupMember,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(courseId, groupId, userId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupMemberService.importGroupMembers(userId, groupId, courseId, groupMember, request);
        } catch (Exception e) {
            LOG.error("groupImportgroupmembersPost", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> groupDeletegroupmembersDelete(
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "老师姓名", required = true)
            @Valid @RequestParam(value = "teacherName", required = true) String teacherName,
            @NotNull @ApiParam(value = "组Id", required = true)
            @Valid @RequestParam(value = "groupId", required = true) String groupId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学生id")
            @Valid @RequestParam(value = "studentId", required = false) String studentId) {
        try {
            if (StringUtils.isAnyEmpty(teacherId, groupId, teacherName, studentId)
                    || StringUtils.isAllBlank(teacherId, groupId, teacherName, studentId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupMemberService.deleteGroupMember(teacherId, studentId, groupId, teacherName);
        } catch (Exception e) {
            LOG.error("groupDeletegroupmembersDelete" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> batchDeleteGroupMembers(
            @NotNull @ApiParam(value = "组Id", required = true)
            @Valid @RequestParam(value = "groupId", required = true) String groupId,
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "学生Id 使用,分割的字符串", required = true)
            @Valid @RequestParam(value = "studentIds", required = true) String studentIds,
            @NotNull @ApiParam(value = "教师姓名", required = true)
            @Valid @RequestParam(value = "teacherName", required = true) String teacherName,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyEmpty(teacherId, groupId, teacherName, studentIds)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            groupMemberService.batchDeleteGroupMembers(teacherId, studentIds, groupId, teacherName);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("batchDeleteGroupMembers->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UsersInfo> groupmembersUserIdOrOpenIdGet(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "用户Id") @Valid @RequestParam(value = "userId", required = false) String userId,
            @ApiParam(value = "微信openId") @Valid @RequestParam(value = "openId", required = false) String openId,
            @ApiParam(value = "分组Id") @Valid @RequestParam(value = "groupId", required = false) String groupId,
            @ApiParam(value = "排除删除") @Valid @RequestParam(value = "excludeDelete", required = false) Integer excludeDelete) {
        try {

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupMemberService.getGroupMembersUserIdOrOpenId(userId, openId, groupId, excludeDelete);
        } catch (Exception e) {
            LOG.error("groupmembersUserIdOrOpenIdGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<StudentInfo>> groupGroupIdMembersGet(@ApiParam(value = "组Id", required = true)
                                                                    @PathVariable("groupId") String groupId,
                                                                    @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
                                                                    @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (StringUtils.isAnyBlank(groupId, validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupService.getGroupMember(groupId);
        } catch (Exception e) {
            LOG.error("groupGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<StudentVo>> getGroupMemberByStudentNoOrNameIsLike(@NotNull
                                                                                 @ApiParam(value = "验证码&signKey=123123(md5加密)",
                                                                                         required = true)
                                                                                 @Valid
                                                                                 @RequestParam(value = "validCode", required = true)
                                                                                 String validCode, @ApiParam(value = "", required = true)
                                                                                 @Valid
                                                                                 @RequestBody
                                                                                 GroupMemberByStudentNoOrNameVo userNoOrUserIdVoList) {

        if (StringUtils.isAnyEmpty(userNoOrUserIdVoList.getStudentNoOrName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (CollectionUtils.isEmpty(userNoOrUserIdVoList.getGroupIdList())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<StudentVo> list = groupMemberService.getGroupMemberByStudentNoOrNameIsLike(userNoOrUserIdVoList);

        return new ResponseEntity<List<StudentVo>>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<UsersInfo>> getGroupteachers(
            @ApiParam(value = "", required = true) @Valid @RequestBody GroupsTeachersVo groupId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(groupId.getCourseId(), validCode) || CollectionUtils.isEmpty(groupId.getGroupIds())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<UsersInfo> usersInfos = groupMemberService.getGroupTeachersByGroupIds(
                    groupId.getGroupIds(), groupId.getCourseId());
            return new ResponseEntity<>(usersInfos, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("groupGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<AssistantResource>> groupIdAssistantsGet(
            @ApiParam(value = "老师id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "验证码：id=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, id, schoolYear)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            String validKey = "id=" + id + signKey;
            if (!RequestUtils.checkValidCode(validKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<AssistantResource> assistantResourceList = groupMemberService.getGroupSchoolTermUserAssistants(schoolYear, term, id);
            return new ResponseEntity<>(assistantResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("groupIdAssistantsGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> groupmembersStudentidentityPost(
            @NotNull @ApiParam(value = "学生id", required = true)
            @Valid @RequestParam(value = "studentId", required = true) String studentId,
            @NotNull @ApiParam(value = "教学班id", required = true)
            @Valid @RequestParam(value = "groupId", required = true) String groupId,
            @NotNull @ApiParam(value = "学生身份编码(0:学生；1：助教)", required = true)
            @Valid @RequestParam(value = "studentIdentityCode", required = true) Integer studentIdentityCode,
            @NotNull @ApiParam(value = "验证码：studentId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, groupId, studentId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            String validKey = "studentId=" + studentId + signKey;
            if (!RequestUtils.checkValidCode(validKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            groupMemberService.updateGroupMemberStudentIdentity(studentId, groupId, studentIdentityCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("groupmembersStudentidentityPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<ImportStudentsVo> importStudents(
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "导入的excel文件") @Valid @RequestPart(value = "importFile", required = true) MultipartFile importFile,
            @NotNull @ApiParam(value = "操作人id", required = true)
            @Valid @RequestParam(value = "operatorid", required = true) String operatorid,
            @NotNull @ApiParam(value = "操作人姓名", required = true)
            @Valid @RequestParam(value = "operatorname", required = true) String operatorName) {
        if (StringUtils.isBlank(validCode) || ObjectUtil.isEmpty(importFile)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            CacheUtil.clearAll();
            ImportStudentsVo importStudentsVo = groupMemberService.multipartFileImportStudents(importFile, operatorid, operatorName);

            if (ObjectUtils.isEmpty(importStudentsVo.getErrorInfo())) {
                return new ResponseEntity(importStudentsVo, HttpStatus.OK);
            } else {
                if (importStudentsVo.getErrorInfo().get(0).equals(EMPTY_TABLE_ERROR_CODE)) {
                    return new ResponseEntity(ErrorResult.customError("导入失败，导入模板内无有效数据！"), HttpStatus.CONFLICT);
                }

                if (importStudentsVo.getErrorInfo().get(0).equals(TABLE_UPPER_LIMIT_ERROR_CODE)) {
                    return new ResponseEntity(ErrorResult.customError("导入失败，超过数据上限（1000），请分批上传！"), HttpStatus.CONFLICT);
                }
                CacheUtil.clearAll();
                return new ResponseEntity(importStudentsVo, HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            LOG.error("importStudent->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<GroupMemberBranch>> getGroupMemberBranch(
            @ApiParam(value = "组Id", required = true) @PathVariable("groupId") String groupId,
            @NotNull @ApiParam(value = "验证码：groupId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (StringUtils.isAnyBlank(groupId, validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String validKey = "groupId=" + groupId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<GroupMemberBranch> groupMemberBranchList = groupMemberService.getGroupMemberBranch(groupId);
            return new ResponseEntity<>(groupMemberBranchList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getGroupMemberBranch：", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<GroupResourceVo>> getGroupByGroupMember(
            @NotNull @ApiParam(value = "学生ID", required = true) @Valid
            @RequestParam(value = "studentId", required = true) String studentId,
            @ApiParam(value = "MD5加密验证字符串 &signKey=123123") @Valid
            @RequestParam(value = "validCode", required = false) String validCode,
            @ApiParam(value = "学年") @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @ApiParam(value = "学期") @Valid @RequestParam(value = "term", required = false) Integer term) {
        if (StringUtils.isAnyBlank(studentId, validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            List<GroupResourceVo> groupResourceList = groupMemberService.getGroupByGroupMember(studentId, schoolYear, term);
            return new ResponseEntity<>(groupResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getGroupByGroupMember：", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.lztech.site.controllers.api.group;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.group.Group;
import com.lztech.site.resource.classresource.ClassResource;
import com.lztech.site.resource.classresource.ClassTeacherResource;
import com.lztech.site.resource.group.GroupResource;
import com.lztech.site.resource.group.GroupSimpleInfo;
import com.lztech.site.resource.group.GroupStudentPageResource;
import com.lztech.site.resource.teachingclass.TeachingClassResource;
import com.lztech.site.service.group.CacheUtil;
import com.lztech.site.service.group.GroupService;
import com.lztech.site.service.groupmember.GroupMemberService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.group.*;
import com.lztech.site.viewmodel.groupmember.DeleteGroupMemberInfoVo;
import com.lztech.site.viewmodel.groupmember.DeleteGroupMembersParam;
import com.lztech.site.viewmodel.groupmember.GroupMemberQueryParamVo;
import com.lztech.site.viewmodel.groupmember.GroupMemberResourcePage;
import com.lztech.site.viewmodel.term.TermResource;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.*;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-11T08:42:20.485Z")

@Controller
public class GroupApiController implements GroupApi {

    private final Logger log = LoggerFactory.getLogger(GroupApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Value("${studentType}")
    private Integer defaultStudentType;

    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupMemberService groupMemberService;

    @org.springframework.beans.factory.annotation.Autowired
    public GroupApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ClassResource> groupPost(@ApiParam(value = "", required = true)
                                                   @Valid @RequestBody ClassTeacherResource classTeacherResource,
                                                   @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                   @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(classTeacherResource.getUserId(), classTeacherResource.getUserName(),
                    classTeacherResource.getGroupId())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupService.updateGroupBaseInfo(classTeacherResource.getUserId(), classTeacherResource.getUserName(),
                    classTeacherResource.getGroupId(), classTeacherResource.getGroupName(), classTeacherResource.getClassNickName(),
                    classTeacherResource.getClassAttribute());
        } catch (Exception e) {
            log.error("groupPost", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> groupIdDelete(@ApiParam(value = "班级id", required = true)
                                              @PathVariable("id") String id,
                                              @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                              @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isBlank(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupService.deleteGroup(id);
        } catch (Exception e) {
            log.error("groupIdDelete" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> groupIdGroupmembersPost(@ApiParam(value = "老师id", required = true)
                                                        @PathVariable("id") String id,
                                                        @NotNull @ApiParam(value = "课程id", required = true)
                                                        @Valid @RequestParam(value = "courseId", required = true)
                                                        String courseId,
                                                        @NotNull @ApiParam(value = "老师姓名", required = true)
                                                        @Valid @RequestParam(value = "userName", required = true)
                                                        String userName,
                                                        @NotNull @ApiParam(value = "组Id", required = true)
                                                        @Valid @RequestParam(value = "groupId", required = true)
                                                        String groupId,
                                                        @NotNull @ApiParam(value = "验证码：id=?&signKey=123123(md5加密)", required = true)
                                                        @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                        @ApiParam(value = "班级成员信息集合", required = true)
                                                        @Valid @RequestBody List<GroupMembersResource> groupMembers) {

        try {
            if (org.apache.commons.lang3.StringUtils.isAnyEmpty(id, userName, groupId, courseId) || groupMembers.size() == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
            }
            return groupMemberService.addGroupMembers(id, userName, groupId, groupMembers, courseId);
        } catch (Exception e) {
            log.error("groupIdGroupmembersPost:", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<GroupStudentPageResource> groupGroupmemberGet(
            @NotNull @ApiParam(value = "班级ID,多个用“,”隔开", required = true)
            @Valid @RequestParam(value = "groupIds", required = true) String groupIds,
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "工号/学号、姓名")
            @Valid @RequestParam(value = "userNoOrUserName", required = false) String userNoOrUserName,
            @ApiParam(value = "当前页")
            @Valid @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(value = "每页个数")
            @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        try {
            if (StringUtils.isBlank(groupIds)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupService.getGroupMembers(groupIds, userNoOrUserName, page, pageSize);
        } catch (Exception e) {
            log.error("groupGroupmemberGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<GroupDetails> groupCreategroupPost(
            @ApiParam(value = "", required = true) @Valid @RequestBody CreateGroup createGroup,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (createGroup == null || StringUtils.isAllEmpty(createGroup.getClassName())
                    || StringUtils.isAllEmpty(createGroup.getCourseId()) || StringUtils.isAllEmpty(createGroup.getTeacherId())
                    || StringUtils.isAllEmpty(createGroup.getSchoolYear()) || StringUtils.isAllEmpty(createGroup.getTerm())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            if (createGroup.getClassName().length() > TWENTY) {
                return new ResponseEntity(ErrorResult.customError("班级名称超过20个字符"), HttpStatus.FORBIDDEN);
            }

            return groupService.createGroup(createGroup);
        } catch (Exception e) {
            log.error("groupDelete" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> groupsGroupmembersPost(@ApiParam(value = "", required = true)
                                                       @Valid @RequestBody List<GroupResource> groups,
                                                       @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                       @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (groups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            groupService.saveGroupsAndMembers(groups);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("groupsGroupmembersPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<GroupResource>> groupsGet(@NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                         @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<GroupResource> groupResourceList = groupService.getAllGroup();
            return new ResponseEntity<List<GroupResource>>(groupResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("groupsGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<GroupStatisticsResource> groupsStatisticsGet(@NotNull
                                                                       @ApiParam(value = "学期", required = true)
                                                                       @Valid
                                                                       @RequestParam(value = "term", required = true)
                                                                       Integer term,
                                                                       @NotNull
                                                                       @ApiParam(value = "学年", required = true)
                                                                       @Valid
                                                                       @RequestParam(value = "schoolYear", required = true)
                                                                       String schoolYear,
                                                                       @NotNull
                                                                       @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                                       @Valid
                                                                       @RequestParam(value = "validCode", required = true)
                                                                       String validCode) {
        try {
            if (StringUtils.isAnyBlank(schoolYear, validCode) || term == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            int groupCount = groupService.getGroupCountBySchoolYearTerm(schoolYear, term);
            return new ResponseEntity<>(new GroupStatisticsResource().groupCount(groupCount), HttpStatus.OK);
        } catch (Exception e) {
            log.error("groupsStatisticsGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<GroupVo> groupInfoGet(@NotNull @ApiParam(value = "每页个数", required = true)
                                                @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
                                                @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                @ApiParam(value = "分组编号") @Valid @RequestParam(value = "groupNo", required = false)
                                                String groupNo,
                                                @ApiParam(value = "分组名称") @Valid @RequestParam(value = "groupName", required = false)
                                                String groupName,
                                                @ApiParam(value = "创建人名称") @Valid @RequestParam(value = "creatorName", required = false)
                                                String creatorName,
                                                @ApiParam(value = "当前页", required = true) @NotNull @RequestParam(value = "page", required = true)
                                                @Valid Integer page) {
        String accept = request.getHeader("Accept");
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return groupService.getGroupVos(groupNo, groupName, creatorName, page, pageSize);
        } catch (Exception e) {
            log.error("groupsGroupmembersPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<TeachingClassResource>> groupGet(
            @NotNull @ApiParam(value = "老师Id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId
    ) {
        if (StringUtils.isAnyBlank(courseId, schoolYear, validCode) || term == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            String validKey = signKey;
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return groupService.findGroupsByTeacher(teacherId, schoolYear, term, courseId);
        } catch (Exception e) {
            log.error("groupGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //region 根据课程Id查询班级信息
    public ResponseEntity<List<GroupSimpleInfo>> getGroupInfosByCourseId(
            @ApiParam(value = "课程id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "验证码（courseId={}&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "开课类型")
            @Valid @RequestParam(value = "studentType", required = false) Integer studentType) {
        if (StringUtils.isAnyBlank(validCode, id, schoolYear)
                || StringUtils.isAnyEmpty(validCode, id, schoolYear)
                || term == null) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            if (ObjectUtils.isEmpty(studentType)) {
                studentType = defaultStudentType;
            }
            return groupService.findGroupsByCourseId(id, term, schoolYear, studentType);
        } catch (Exception e) {
            log.error("getGroupInfosByCourseId->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    public ResponseEntity<GroupVoResourcePage> getGroupResourcePage(
            @ApiParam(value = "查询条件", required = true)
            @Valid @RequestBody GroupParam groupParam,
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (ObjectUtils.isEmpty(groupParam)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isEmpty(validCode)
                || ObjectUtils.isEmpty(groupParam)
                || StringUtils.isEmpty(groupParam.getSchoolYear())
                || ObjectUtils.isEmpty(groupParam.getPage()) || groupParam.getPage() < 1
                || ObjectUtils.isEmpty(groupParam.getPageSize()) || groupParam.getPageSize() < 1
                || groupParam.getTerm() == null || groupParam.getTerm() <= 0) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            return groupService.getGroups(groupParam);
        } catch (Exception e) {
            log.error("getGroupResourcePage->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //region 根据班级信息Id查询班级详情
    public ResponseEntity<List<GroupSimpleVo>> getGroupSimpleInfos(
            @NotNull @ApiParam(value = "班级Id，多个用逗号分隔", required = true) @Valid
            @RequestParam(value = "groupIds", required = true)
            String groupIds,
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true) @Valid
            @RequestParam(value = "validCode", required = true)
            String validCode) {
        if (StringUtils.isAnyEmpty(validCode, groupIds)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            return this.groupService.getGroupSimpleInfos(groupIds);
        } catch (Exception e) {
            log.error("getGroupSimpleInfos->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion
    public ResponseEntity<GroupUserStatisticsResource> groupsUserstatisticsGet(
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @ApiParam(value = "开课学院id")
            @Valid @RequestParam(value = "collegeId", required = false) String collegeId,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(schoolYear, validCode) || term == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            GroupUserStatisticsResource groupUserStatisticsResource = groupService.getGroupUserCountBySchoolYearTerm(schoolYear, term, collegeId);
            return new ResponseEntity<>(groupUserStatisticsResource, HttpStatus.OK);
        } catch (Exception e) {
            log.error("groupsUserstatisticsGet->{}" + e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<GroupMemberResourceVo>> getGroupMemberResourceList(
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) String term) {

        try {
            if (StringUtils.isAnyBlank(validCode, schoolYear, term)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            List<GroupMemberResourceVo> groupMemberResourceVoList = groupService.getGroupMemberList(schoolYear, term);
            return new ResponseEntity<>(groupMemberResourceVoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getGroupMemberResourceList->{}" + e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<MemberNumVo> getStudentMemberNum(
            @NotNull @ApiParam(value = "课程ID", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "班级ID", required = true)
            @Valid @RequestParam(value = "groupId", required = true) String groupId,
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学年") @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @ApiParam(value = "学期") @Valid @RequestParam(value = "term", required = false) String term) {
        if (StringUtils.isAnyBlank(courseId, validCode, courseId, groupId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            MemberNumVo memberNumVo =
                    groupService.getGroupMemberCount(schoolYear, term, courseId, groupId);
            return new ResponseEntity(memberNumVo, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getStudentMemberNum->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TermRunningModel> getTermRunningNums(
            @ApiParam(value = "学期列表", required = true)
            @Valid @RequestBody List<TermResource> termResources,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (CollectionUtils.isEmpty(termResources)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isAnyBlank(courseId, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            TermRunningModel termRunningModel = groupService.getTermRunningNums(termResources, courseId);
            return new ResponseEntity(termRunningModel, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getTermRunningNums->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<GroupMemberResourcePage> getGroupMemberResourceList(
            @ApiParam(value = "查询条件", required = true)
            @Valid @RequestBody GroupMemberQueryParamVo queryParam,
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (StringUtils.isEmpty(validCode)
                    || ObjectUtils.isEmpty(queryParam)
                    || ObjectUtils.isEmpty(queryParam.getPage()) || queryParam.getPage() < 1
                    || ObjectUtils.isEmpty(queryParam.getPageSize()) || queryParam.getPageSize() < 1
                    || StringUtils.isEmpty(queryParam.getSchoolYear())
                    || ObjectUtils.isEmpty(queryParam.getTerm())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            GroupMemberResourcePage groupMemberResourcePage
                    = groupMemberService.getGroupMemberResourceList(queryParam);
            return new ResponseEntity<GroupMemberResourcePage>(groupMemberResourcePage, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getGroupMemberResourceList->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteGroupMembersByGroupMembers(
            @ApiParam(value = "删除参数", required = true)
            @Valid @RequestBody DeleteGroupMembersParam deleteParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isEmpty(validCode)
                    || ObjectUtils.isEmpty(deleteParam)
                    || StringUtils.isEmpty(deleteParam.getOperatorId()) || StringUtils.isEmpty(deleteParam.getOperatorName())
                    || CollectionUtils.isEmpty(deleteParam.getDeleteGroupMembers())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<DeleteGroupMemberInfoVo> deleteGroupMemberInfoVos
                    = groupMemberService.queryGroupMembersByIds(deleteParam.getDeleteGroupMembers());

            if (deleteGroupMemberInfoVos.size() < deleteParam.getDeleteGroupMembers().size()) {
                return new ResponseEntity(ErrorResult.customError("有未找到的班级学生信息"), HttpStatus.NOT_FOUND);
            }

            List<DeleteGroupMemberInfoVo> dockingGroupMembers = deleteGroupMemberInfoVos
                    .stream()
                    .filter(deleteGroupMemberInfoVo ->
                            deleteGroupMemberInfoVo.getSource().equals(Source.DATA_DOCKING.getIndex())).collect(Collectors.toList());

            if (dockingGroupMembers.size() > 0) {
                String errorMessage = "";
                for (DeleteGroupMemberInfoVo groupMemberInfoVo : dockingGroupMembers) {
                    errorMessage += groupMemberInfoVo.getStudentName() + "(" + groupMemberInfoVo.getStudentNo() + "),";
                }
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
                errorMessage += "不是手动添加的学生，不能删除";

                return new ResponseEntity(ErrorResult.customError(errorMessage), HttpStatus.CONFLICT);
            }

            groupMemberService.deleteGroupMembersByGroupMembers(deleteGroupMemberInfoVos
                    , deleteParam.getOperatorId(), deleteParam.getOperatorName());

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("deleteGroupMembersByGroupMembers->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteGroupGroupIds(
            @ApiParam(value = "删除参数", required = true)
            @Valid @RequestBody DeleteGroupParam deleteParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (StringUtils.isEmpty(validCode)
                    || ObjectUtils.isEmpty(deleteParam)
                    || StringUtils.isEmpty(deleteParam.getOperatorId()) || StringUtils.isEmpty(deleteParam.getOperatorName())
                    || CollectionUtils.isEmpty(deleteParam.getDeleteGroupIds())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<Group> groups = groupService.getGroupsByIds(deleteParam.getDeleteGroupIds()
                    .stream().map(DeleteGroupIdVo::getGroupId).collect(Collectors.toList()));

            if (groups.size() < deleteParam.getDeleteGroupIds().size()) {
                return new ResponseEntity(ErrorResult.customError("有未找到的班级信息"), HttpStatus.NOT_FOUND);
            }

            List<Group> dockingGroups = groups
                    .stream()
                    .filter(group ->
                            group.getSource().equals(Source.DATA_DOCKING)).collect(Collectors.toList());

            String errorMessage = "";
            if (dockingGroups.size() > 0) {
                for (Group group : dockingGroups) {
                    errorMessage += group.getGroupName() + "(" + group.getGroupNo() + "),";
                }
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
                errorMessage += "不是手动添加的班级，不能删除";

                return new ResponseEntity(ErrorResult.customError(errorMessage), HttpStatus.CONFLICT);
            }

            errorMessage = "";
            for (Group group : groups) {
                List<CourseTable> courseTables = group.getCourseTableList();

                for (CourseTable courseTable : courseTables) {
                    Integer count = (int) courseTable.getCourseTableDetailList().stream()
                            .filter(courseTableDetail -> ObjectUtils.isNotEmpty(courseTableDetail.getCourseDate())).count();
                    if (count > 0) {
                        errorMessage += group.getGroupName() + "(" + group.getGroupNo() + "),";
                        break;
                    }
                }
            }

            if (errorMessage.length() > 0) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
                errorMessage += "班级已经排课，不能删除";
                return new ResponseEntity(ErrorResult.customError(errorMessage), HttpStatus.CONFLICT);
            }

            groupService.deleteGroupsByIds(groups, deleteParam.getOperatorId(), deleteParam.getOperatorName());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("deleteGroupGroupIds->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ImportClassVo> importClass(
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "操作人id", required = true)
            @Valid @RequestParam(value = "operatorid", required = true) String operatorId,
            @NotNull @ApiParam(value = "操作人姓名", required = true)
            @Valid @RequestParam(value = "operatorname", required = true) String operatorName,
            @ApiParam(value = "导入的excel文件") @RequestPart(value = "importFile", required = true) @Valid MultipartFile importFile) {
        if (StringUtils.isBlank(validCode) || ObjectUtil.isEmpty(importFile)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            CacheUtil.clearAll();
            ImportClassVo importClassVo = groupService.multipartFile(importFile, operatorId, operatorName);

            if (ObjectUtils.isEmpty(importClassVo.getErrorInfo())) {
                return new ResponseEntity(importClassVo, HttpStatus.OK);
            } else {
                if (importClassVo.getErrorInfo().get(0).equals(EMPTY_TABLE_ERROR_CODE)) {
                    return new ResponseEntity(ErrorResult.customError("导入失败，导入模板内无有效数据！"), HttpStatus.CONFLICT);
                }

                if (importClassVo.getErrorInfo().get(0).equals(TABLE_UPPER_LIMIT_ERROR_CODE)) {
                    return new ResponseEntity(ErrorResult.customError("导入失败，超过数据上限（1000），请分批上传！"), HttpStatus.CONFLICT);
                }
                CacheUtil.clearAll();
                return new ResponseEntity(importClassVo, HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            log.error("importClass->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<GroupResourceVo>> getGroupListByStudentId(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学生Id") @Valid @RequestParam(value = "studentId", required = false) String studentId,
            @ApiParam(value = "学年") @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @ApiParam(value = "学期") @Valid @RequestParam(value = "term", required = false) Integer term) {
        try {
            if (StringUtils.isAnyBlank(studentId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<GroupResourceVo> groupResourceList = groupService.getGroupListByStudentId(studentId, schoolYear, term);
            return new ResponseEntity(groupResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getGroupListByStudentId->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<GroupBaseInfo>> getCollegeGroupList(
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) Integer term,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "开课学院id(多个学院逗号拼接)", required = true)
            @Valid @RequestParam(value = "collegeIds", required = true) String collegeIds,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(schoolYear, collegeIds, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (term == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<GroupBaseInfo> groupResourceList = groupService.getCollegeGroupList(schoolYear, term, collegeIds);
            return new ResponseEntity(groupResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getGroupListByStudentId->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

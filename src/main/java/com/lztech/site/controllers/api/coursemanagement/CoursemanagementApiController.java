package com.lztech.site.controllers.api.coursemanagement;

import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.resource.course.CourseInfoResource;
import com.lztech.site.service.courseconstruction.*;
import com.lztech.site.service.coursemanagement.CourseManagementLogicService;
import com.lztech.site.service.coursemanagement.CourseManagementService;
import com.lztech.site.service.coursemanagement.ExtendCourseManagementService;
import com.lztech.site.service.coursemanagementportal.InTeachingCoursesService;
import com.lztech.site.service.coursemanagementportal.LatestConstructionCoursesService;
import com.lztech.site.service.coursemanagementportal.TourCoursesService;
import com.lztech.site.until.ConflictRuntimeException;
import com.lztech.site.until.CustomRuntimeException;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseconstruction.CourseVersionResource;
import com.lztech.site.viewmodel.coursemanagement.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.portal.NewCourseResource;
import com.lztech.site.viewmodel.portal.TeachingCourseResource;
import com.lztech.site.viewmodel.portal.TourCoursesInfoVo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-11T02:30:31.464Z")


@Controller
public class CoursemanagementApiController implements CoursemanagementApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursemanagementApiController.class);

    @Autowired
    private CourseManagementService courseManagementService;
    @Autowired
    private CourseMaterialService courseMaterialService;
    @Autowired
    private CourseTeachingTeamService courseTeachingTeamService;
    @Autowired
    private CourseStructureService courseStructureService;
    @Autowired
    private CourseManagementOldDataService oldDataService;
    @Autowired
    private ExtendCourseManagementService extendCourseManagementService;
    @Autowired
    private CourseVersionService courseVersionService;
    @Autowired
    private LatestConstructionCoursesService latestConstructionCoursesService;
    @Autowired
    private InTeachingCoursesService inTeachingCoursesService;
    @Autowired
    private TourCoursesService tourCoursesService;
    @Autowired
    private CourseManagementLogicService courseManagementLogicService;

    //region 接口-新建课程结构
    public ResponseEntity<Void> coursemanagementCoursestructurePost(
            @ApiParam(value = "课程结构相关参数", required = true)
            @Valid @RequestBody StructureParam structureParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (ObjectUtils.isEmpty(structureParam)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isAnyEmpty(validCode, structureParam.getCourseId(), structureParam.getStructureName(),
                structureParam.getUserId(), structureParam.getUserName())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseStructureService.createCourseStructure(structureParam);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCoursestructurePost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-老师查看加入的课程
    public ResponseEntity<List<CourseVo>> coursemanagementTeacherIdCoursesGet(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "验证码teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {

            List<CourseVo> courseVos = courseManagementService.getCourseList(teacherId);
            return new ResponseEntity<>(courseVos, HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("coursemanagementTeacherIdCoursesGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion
    //region 接口-根据老师id和课程id查询课程建设相关课程信息
    public ResponseEntity<CourseVo> getCourseManagementByCourseIdAndTeacherId(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @ApiParam(value = "课程id", required = true)
            @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseVo courseVo = courseManagementService.getCourseManagementByCourseIdAndTeacherId(teacherId, courseId);
            if (ObjectUtils.isNotEmpty(courseVo)) {
                return new ResponseEntity<>(courseVo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("getCourseManagementByCourseIdAndTeacherId->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion
    //region 接口-老师查看加入的课程
    public ResponseEntity<AiIntelligentCourseSummaryResource> getAiIntelligentCourseSummaryResource(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "验证码teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        if (StringUtils.isAnyBlank(validCode, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            AiIntelligentCourseSummaryResource aiIntelligentCourseSummaryResource =
                    courseManagementLogicService.getAiIntelligentCourseSummaryResource(teacherId);
            return new ResponseEntity<>(aiIntelligentCourseSummaryResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getAiIntelligentCourseSummaryResource->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion
    //region 接口-根据课程id和老师id查询智慧课程详细信息
    public ResponseEntity<AiIntelligentCourseVo> getAiIntelligentCourseByCourseIdAndTeacherId(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @ApiParam(value = "课程id", required = true)
            @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        if (StringUtils.isAnyBlank(validCode, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            AiIntelligentCourseVo aiIntelligentCourseVo =
                    courseManagementLogicService.getAiIntelligentCourseByCourseIdAndTeacherId(teacherId, courseId);
            if (ObjectUtils.isNotEmpty(aiIntelligentCourseVo)) {
                return new ResponseEntity<>(aiIntelligentCourseVo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("getAiIntelligentCourseByCourseIdAndTeacherId->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-老师加入教学团队
    public ResponseEntity<Void> coursemanagementCourseteachingteamPost(
            @ApiParam(value = "加入教师团队信息", required = true)
            @Valid @RequestBody CourseTeachingTeamVo courseTeachingTeamVo,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, courseTeachingTeamVo.getCourseId(),
                courseTeachingTeamVo.getVersionId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseTeachingTeamService.createCourseTeachingTeam(courseTeachingTeamVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementTeacherIdCourseteachingteamPost", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-上传资源
    public ResponseEntity<Void> coursemanagementUploadresourcesPost(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程教学内容资源信息")
            @Valid @RequestBody TeachingContentResource teachingContentResource) {
        if (StringUtils.isAnyEmpty(validCode, teachingContentResource.getCourseStructureId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        List<Integer> allowResourceTypes = new ArrayList<>();
        allowResourceTypes.add(ResourceType.TEACHING_COURSE_WARE.getIndex());
        allowResourceTypes.add(ResourceType.MICRO_VIDEO.getIndex());
        allowResourceTypes.add(ResourceType.CLASS_TEST.getIndex());
        allowResourceTypes.add(ResourceType.IMAGE.getIndex());
        allowResourceTypes.add(ResourceType.AUDIO.getIndex());

        if (!allowResourceTypes.contains(teachingContentResource.getResourceType())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return courseManagementService.createTeachingContentResource(teachingContentResource);
        } catch (Exception e) {
            LOGGER.error("coursemanagementUploadresourcesPost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-资源重命名
    public ResponseEntity<CourseResourceData> coursemanagementFilenamePost(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "用户Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "资源Id", required = true)
            @Valid @RequestParam(value = "fileId", required = true) String fileId,
            @NotNull @ApiParam(value = "资源名称", required = true)
            @Valid @RequestParam(value = "fileName", required = true) String fileName) {
        try {
            if (StringUtils.isAnyEmpty(validCode, userId, fileId, fileName)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return courseManagementService.updateCourseResourceFile(userId, fileId, fileName);
        } catch (Exception e) {
            LOGGER.error("coursemanagementFilenamePost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-重命名课程结构
    public ResponseEntity<Void> coursemanagementCoursestructurenamePost(
            @NotNull @ApiParam(value = "课程结构id", required = true)
            @Valid @RequestParam(value = "courseStructureId", required = true) String courseStructureId,
            @NotNull @ApiParam(value = "课程结构名称", required = true)
            @Valid @RequestParam(value = "courseStructureName", required = true) String courseStructureName,
            @NotNull @ApiParam(value = "用户id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyEmpty(validCode, courseStructureId, courseStructureName, userId, userName)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseStructureService.updateCourseStructureName(courseStructureId, courseStructureName, userId, userName);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCoursestructurenamePost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion

    //region 接口-老师退出教学团队
    public ResponseEntity<Void> coursemanagementCourseteachingteamDelete(
            @NotNull @ApiParam(value = "课程教学团队Id", required = true)
            @Valid @RequestParam(value = "courseTeachingTeamId", required = true) String courseTeachingTeamId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "操作人Id")
            @Valid @RequestParam(value = "userId", required = false) String userId,
            @ApiParam(value = "操作人名称")
            @Valid @RequestParam(value = "userName", required = false) String userName) {
        if (StringUtils.isAnyBlank(validCode, courseTeachingTeamId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return courseManagementService.deleteCourseTeachingTeam(courseTeachingTeamId, userId, userName);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCourseteachingteamDelete", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-资源移动
    public ResponseEntity<Void> coursemanagementCourseStructureIdMovedPost(
            @ApiParam(value = "课程结构Id", required = true)
            @PathVariable("courseStructureId") String courseStructureId,
            @NotNull @ApiParam(value = "资源Id,多个以‘，’分割", required = true)
            @Valid @RequestParam(value = "courseResourceIds", required = true) String courseResourceIds,
            @NotNull @ApiParam(value = "用户Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "验证码：courseStructureId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseResourceIds, courseStructureId, userId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseStructureId", courseStructureId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return courseManagementService.moveToCourseStructure(courseResourceIds, courseStructureId, userId);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCourseStructureIdMovedPost" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-查询课程列表
    public ResponseEntity<List<CourseInfoResource>> coursemanagementTeacherIdCourseListGet(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "验证码teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程名称或编号")
            @Valid @RequestParam(value = "courseNameOrCode", required = false) String courseNameOrCode) {
        if (StringUtils.isAnyBlank(validCode, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return courseManagementService.getCourseInfoResources(teacherId, courseNameOrCode);
        } catch (Exception e) {
            LOGGER.error("coursemanagementTeacherIdCourseListGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //endregion
    //region 接口-老师新增课程
    public ResponseEntity<Void> coursemanagementCoursePost(
            @ApiParam(value = "新增课程相关信息", required = true)
            @Valid @RequestBody CourseModule courseModule,
            @NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, courseModule.getTeacherId(), courseModule.getTeacherNo(), courseModule.getTeacherName(),
                courseModule.getCollegeCode(), courseModule.getCollegeName(), courseModule.getCourseName())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return extendCourseManagementService.createCourse(courseModule);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCoursePost", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-查看课程教学内容详情
    public ResponseEntity<List<CourseStructureVo>> coursemanagementCoursescontentGet(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本id", required = false)
            @Valid @RequestParam(value = "versionId", required = false) String versionId,
            @NotNull @ApiParam(value = "（0-全部 1-只显示课程结构）", required = true)
            @Valid @RequestParam(value = "type", required = true) Integer type,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "需要过滤的用户ID，即用户ID创建的资源不展示") @Valid
            @RequestParam(value = "needFilterUserId", required = false) String needFilterUserId,
            @ApiParam(value = "需要的用户ID，即用户ID创建的资源展示") @Valid
            @RequestParam(value = "needUserId", required = false) String needUserId) {
        if (StringUtils.isAnyEmpty(validCode, courseId) || type == null || (type != 0 && type != 1)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            Result result = courseStructureService.getCoursesContent(courseId, versionId, type, needFilterUserId, needUserId);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(result.getData(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCoursescontentGet:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //region 接口-删除课程资源
    public ResponseEntity<Void> deleteCourseManagementCourseResources(
            @NotNull @ApiParam(value = "资源Id,多个以‘，’分割", required = true)
            @Valid @RequestParam(value = "courseResourceIds", required = true) String courseResourceIds,
            @NotNull @ApiParam(value = "用户Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程Id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本id", required = true)
            @Valid @RequestParam(value = "versionId", required = true) String versionId) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseResourceIds, userId, courseId, versionId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            extendCourseManagementService.deleteCourseResources(courseResourceIds, userId, courseId, versionId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomRuntimeException mre) {
            return new ResponseEntity(mre.getErrorResult(), HttpStatus.NOT_FOUND);
        } catch (ConflictRuntimeException tre) {
            return new ResponseEntity(tre.getErrorResult(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCourseresourceDelete" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-删除课程结构
    public ResponseEntity<Void> coursemanagementCoursestructureIdPost(
            @ApiParam(value = "课程结构id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "用户Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName,
            @NotNull @ApiParam(value = "验证码：id=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, userId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return extendCourseManagementService.deleteCourseStructure(id, userId, userName);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCoursestructureIdPost", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-查看教师团队
    public ResponseEntity<CourseTeachingTeamResource> coursemanagementCourseteachingteamGet(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本id", required = true) @Valid
            @RequestParam(value = "versionId", required = true) String versionId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "是否获取统计") @Valid @RequestParam(value = "isStatistics", required = false)
            Boolean isStatistics) {
        if (StringUtils.isAnyBlank(validCode, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            CourseTeachingTeamResource courseTeachingTeamResource = courseTeachingTeamService.getCourseTeachingTeams(courseId, versionId,
                    isStatistics);
            return new ResponseEntity(courseTeachingTeamResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCourseteachingteamGet->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion


    //region 接口-课程结构排序
    public ResponseEntity<Void> coursemanagementCoursestructuresortPost(
            @NotNull @ApiParam(value = "用户Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户姓名", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName,
            @ApiParam(value = "课程结构信息", required = true)
            @Valid @RequestBody List<CourseStructureData> courseStructureDatas,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, userId, userName)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (courseStructureDatas.size() <= 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return extendCourseManagementService.updateCourseStructureOrder(userId, userName, courseStructureDatas);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCoursestructuresortPost" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion
    //region 接口-课程课程介绍、课程大纲、考核办法信息
    public ResponseEntity<CourseMaterialResource> coursemanagementCourseIdMaterialmoduleinfoGet(
            @ApiParam(value = "课程id", required = true)
            @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "资料模块类型：0 课程介绍，1课程大纲，2考核办法", required = true)
            @Valid @RequestParam(value = "materialModuleType", required = true) Integer materialModuleType,
            @NotNull @ApiParam(value = "验证码：courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "版本id", required = true) @Valid @RequestParam(value = "versionId", required = true) String versionId) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId, versionId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
            }
            Result result = courseMaterialService.getCourseIdMaterialModuleInfo(courseId, materialModuleType, versionId);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(result.getData(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCourseIdMaterialmoduleGet", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-维护课程介绍、课程大纲、考核办法
    public ResponseEntity<Void> coursemanagementCourseIdMaterialmoduleinfoPost(
            @ApiParam(value = "", required = true)
            @Valid @RequestBody MaterialParam param,
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (ObjectUtils.isEmpty(param) || param.getMaterialType() == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isAnyBlank(validCode, courseId, param.getUserId(), param.getUserName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
            }
            Result result = courseMaterialService.updateCourseMaterial(courseId, param);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCourseIdMaterialmoduleinfoPost", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion

    public ResponseEntity<List<CourseStructureResource>> coursemanagementCourseresourceGet(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本id", required = true)
            @Valid @RequestParam(value = "versionId", required = true) String versionId,
            @NotNull @ApiParam(value = "用户Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (StringUtils.isAnyBlank(validCode, courseId, versionId, userId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }
        try {
            Result result = courseStructureService.getCourseResources(courseId, versionId, userId);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(result.getData(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCourseresourceGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //region 接口-获取当前课程一级结构列表
    public ResponseEntity<List<PrimaryStructure>> coursemanagementCourseIdPrimarystructureGet(
            @ApiParam(value = "课程id", required = true)
            @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "版本id", required = false)
            @Valid @RequestParam(value = "versionId", required = false) String versionId,
            @ApiParam(value = "展示删除的章节结构", required = false)
            @Valid @RequestParam(value = "showDeleted", required = false) Boolean showDeleted,
            @NotNull @ApiParam(value = "验证码courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(validCode, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return extendCourseManagementService.getPrimaryStructure(courseId, versionId, showDeleted);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCoursescontentGet:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 接口-判断当前课程老师是否加入教师团队

    public ResponseEntity<IsJoinTeachingTeam> coursemanagementCourseIdCourseteachingteamGet(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程版本id")
            @Valid @RequestParam(value = "courseVisionId", required = false) String courseVisionId) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId, teacherId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            if (StringUtils.isAnyEmpty(courseVisionId)) {
                List<CourseVersionResource> courseVersions =
                        courseVersionService.getCourseVersion(courseId, CourseVersionStatus.IN_USE.getValue());
                courseVisionId = courseVersions.get(0).getVersionId();
            }
            return extendCourseManagementService.getIfCourseIdCourseTeachingTeam(courseId, teacherId, courseVisionId);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCourseIdCourseteachingteamGet", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion
    //region 接口-查询课程列表(题库添加题目使用)
    public ResponseEntity<List<CourseInfoResource>> coursemanagementTeacherIdJoincourseListGet(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "验证码teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return extendCourseManagementService.getJoinCourseInfoResources(teacherId);
        } catch (Exception e) {
            LOGGER.error("coursemanagementTeacherIdJoincourseListGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion


    public ResponseEntity coursemanagementUploadresourceListPost(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "", required = true)
            @Valid @RequestBody UploadResourceParam param) {
        try {
            if (ObjectUtils.isEmpty(param)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isAnyBlank(validCode, param.getCourseStructureId(),
                    param.getUserId(), param.getUserName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            return courseManagementService.uploadResourceList(param);
        } catch (Exception e) {
            LOGGER.error("coursemanagementUploadresourceListPost" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Boolean> coursemanagementCourseStructureIdIsdeleteGet(
            @ApiParam(value = "课程结构id", required = true)
            @PathVariable("courseStructureId") String courseStructureId,
            @NotNull @ApiParam(value = "courseStructureId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseStructureId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseStructureId", courseStructureId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return extendCourseManagementService.getCourseStructureIdIsDelete(courseStructureId);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCourseStructureIdIsdeleteGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> adminDeleteCourseResource(
            @NotNull @ApiParam(value = "课程Id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "资源Id,多个以‘，’分割", required = true)
            @Valid @RequestParam(value = "courseResourceIds", required = true) String courseResourceIds,
            @NotNull @ApiParam(value = "用户Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseResourceIds, userId, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return extendCourseManagementService.adminDeleteCourseResources(courseResourceIds, userId, courseId);
        } catch (Exception e) {
            LOGGER.error("adminDeleteCourseResource" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> coursemanagementOlddataupdateGet(@NotNull @ApiParam(value = "验证码:&signKey=123123(md5加密)", required = true)
                                                                 @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            oldDataService.updateOldData();
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("coursemanagementOlddataupdateGet", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<Userbaseinfoandcollegevo>> coursemanagementTeacherinfosGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "教师工号或姓名", required = true)
            @Valid @RequestParam(value = "teacherNoOrName", required = true) String teacherNoOrName) {
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return new ResponseEntity<List<Userbaseinfoandcollegevo>>
                    (courseTeachingTeamService.coursemanagementTeacherinfosGetService(teacherNoOrName), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementTeacherinfosGet->", e);
            return new ResponseEntity<List<Userbaseinfoandcollegevo>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<NewCourseResource>> getLatestConstructionCourses(
            @NotNull @ApiParam(value = "&signKey=123123(md5加密)",
                    required = true) @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "展示数量", required = true)
            @Valid @RequestParam(value = "displayNum", required = true) Integer displayNum) {
        if (displayNum == null) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        } else if (displayNum == 0) {
            return new ResponseEntity<List<NewCourseResource>>(new ArrayList<>(), HttpStatus.OK);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<NewCourseResource> newCourses = latestConstructionCoursesService.getLatestCourses(displayNum);
            return new ResponseEntity<List<NewCourseResource>>(newCourses, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getLatestConstructionCourses ->", e);
            return new ResponseEntity<List<NewCourseResource>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<TeachingCourseResource>> getInTeachingCourses(
            @NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "展示数量", required = true)
            @Valid @RequestParam(value = "displayNum", required = true) Integer displayNum) {
        if (displayNum == null) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        } else if (displayNum == 0) {
            return new ResponseEntity<List<TeachingCourseResource>>(new ArrayList<>(), HttpStatus.OK);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<TeachingCourseResource> inTeachingCourses = inTeachingCoursesService.getInTeachingCourses(displayNum);
            return new ResponseEntity<List<TeachingCourseResource>>(inTeachingCourses, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getInTeachingCourses ->", e);
            return new ResponseEntity<List<TeachingCourseResource>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TourCoursesInfoVo>> getCoursesTeacherAndCollegeNameByDetailId(
            @NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课表详情id(多个用逗号分割)", required = true)
            @Valid @RequestParam(value = "detailIds", required = true) String detailIds) {
        if (StringUtils.isAnyBlank(validCode, detailIds)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<TourCoursesInfoVo> tourCoursesInfoVos = tourCoursesService.getCoursesTeacherAndCollegeNameByDetailId(detailIds);
            return new ResponseEntity<List<TourCoursesInfoVo>>(tourCoursesInfoVos, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCoursesTeacherAndCollegeNameByDetailId ->", e);
            return new ResponseEntity<List<TourCoursesInfoVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> reorderCourseResource(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "重新排序参数", required = true)
            @Valid @RequestBody ReorderParams reorderParams) {
        try {
            if (StringUtils.isAnyEmpty(validCode) || ObjectUtils.isEmpty(reorderParams)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            courseManagementService.reorderCourseResource(reorderParams);
            return new ResponseEntity<Void>(HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("reorderCourseResource->", e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

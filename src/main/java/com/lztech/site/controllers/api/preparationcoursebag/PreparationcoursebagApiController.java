package com.lztech.site.controllers.api.preparationcoursebag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.ResourceSourceType;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.persistence.repositories.course.CourseResourceRepository;
import com.lztech.site.resource.courseteacher.CourseTeacherNewVo;
import com.lztech.site.resource.courseteacher.CourseTeacherVo;
import com.lztech.site.service.courseconstruction.CourseVersionService;
import com.lztech.site.service.preparationcoursebag.ExtendPreparationCourseBagService;
import com.lztech.site.service.preparationcoursebag.PreparationCourseBagService;
import com.lztech.site.until.CustomRuntimeException;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.CourseResource;
import com.lztech.site.viewmodel.courseconstruction.CourseVersionResource;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceVo;
import com.lztech.site.viewmodel.coursemanagement.ReorderParams;
import com.lztech.site.viewmodel.coursematerial.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.preparationcoursebag.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-14T02:27:52.715Z")


@Controller
public class PreparationcoursebagApiController implements PreparationcoursebagApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreparationcoursebagApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    PreparationCourseBagService preparationCourseBagService;

    @Autowired
    private CourseResourceRepository courseResourceRepository;

    @Autowired
    private ExtendPreparationCourseBagService extendPreparationCourseBagService;
    @Autowired
    private CourseVersionService courseVersionService;

    @org.springframework.beans.factory.annotation.Autowired
    public PreparationcoursebagApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> preparationcoursebagCourseIdHidePost(
            @NotNull @ApiParam(value = "用户id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName,
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, userId, userName, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return preparationCourseBagService.preparationCourseHide(userId, userName, courseId);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagCourseIdHidePost" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> preparationcoursebagCourseIdShowDelete(
            @NotNull @ApiParam(value = "用户id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, userId, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return preparationCourseBagService.preparationCourseShow(userId, courseId);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagCourseIdShowDelete" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TestPaperParmVo> preparationcoursebagClasstestpaperPost(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "", required = true)
            @Valid @RequestBody TestPaperParmVo testPaperParmVo) {
        try {
            if (StringUtils.isAnyEmpty(validCode, testPaperParmVo.getCourseStructureId(), testPaperParmVo.getPaperId(),
                    testPaperParmVo.getPaperName(), testPaperParmVo.getUserId(), testPaperParmVo.getUserName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (testPaperParmVo.getQuestionNum() <= 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return preparationCourseBagService.createClassTestPaper(testPaperParmVo);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagClasstestpaperPost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<PreparationCourse>> preparationcoursebagTeacherIdCoursesGet(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "验证码：teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return preparationCourseBagService.getPreparationCourses(teacherId);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagTeacherIdCoursesGet", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CourseResourceVo>> preparationcoursebagTeacherIdCourseresourcesGet(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "课程结构id", required = true)
            @Valid @RequestParam(value = "courseStructureId", required = true) String courseStructureId,
            @NotNull @ApiParam(value = "验证码：teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "资源名称")
            @Valid @RequestParam(value = "resourceName", required = false) String resourceName) {
        if (StringUtils.isAnyBlank(validCode, courseStructureId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return preparationCourseBagService.getCourseStructureIdCourseResources(courseStructureId, teacherId, resourceName);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagTeacherIdCourseresourcesGet->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //region 接口-获取发布资源信息
    public ResponseEntity<CourseResourceModule> preparationcoursebagCourseresourceIdGet(
            @ApiParam(value = "资源id", required = true)
            @PathVariable("id") String id,
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：id=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, id, teacherId, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return preparationCourseBagService.getIdCourseIdTeacherIdCourseResource(id, teacherId, courseId);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagCourseresourceIdGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    public ResponseEntity<List<TeachingFileVo>> getPreparationCourseBagTeachingFiles(
            @NotNull @ApiParam(value = "教师Id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "课程Id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, teacherId, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            List<TeachingFileVo> teachingFiles = preparationCourseBagService.getTeachingFiles(teacherId, courseId);
            return new ResponseEntity(teachingFiles, HttpStatus.OK);
        } catch (CustomRuntimeException cre) {
            return new ResponseEntity(cre.getErrorResult(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagTeachingfilesGet", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseResource> preparationcoursebagCourseIdTeacherCourseGet(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码：courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, teacherId, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return preparationCourseBagService.getCourseIdTeacherCourse(teacherId, courseId);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagCourseresourceIdGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<ClassTestPaperVo>> getPreparationCourseBagClassTestPapers(

            @NotNull @ApiParam(value = "教师ID", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "课程ID", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程版本id")
            @Valid @RequestParam(value = "courseVisionId", required = false) String courseVisionId) {
        if (StringUtils.isAnyBlank(validCode, teacherId, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            if (StringUtils.isAnyEmpty(courseVisionId)) {
                List<CourseVersionResource> courseVersions =
                        courseVersionService.getCourseVersion(courseId, CourseVersionStatus.IN_USE.getValue());
                courseVisionId = courseVersions.get(0).getVersionId();
            }
            List<ClassTestPaperVo> classTestPaperVos = preparationCourseBagService.getClassTestPapers(teacherId, courseId, courseVisionId);
            return new ResponseEntity<>(classTestPaperVos, HttpStatus.OK);
        } catch (CustomRuntimeException cre) {
            return new ResponseEntity(cre.getErrorResult(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagClasstestpapersGet", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CourseTeacherVo>> preparationcoursebagTeacherIdCourseTeacherGet(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "验证码：teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (StringUtils.isAnyBlank(validCode, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseTeacherVo> courseTeachers = preparationCourseBagService.getTeacherIdTeacherCourses(teacherId);
            return new ResponseEntity<>(courseTeachers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagTeacherIdCoursesGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseResourceVo>> getPreparationCourseBagImages(
            @ApiParam(value = "课程id", required = true) @PathVariable("courseId") String courseId,
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码：courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, courseId, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseResourceVo> courseResourceVos =
                    preparationCourseBagService.getCourseIdTeacherIdImages(courseId, teacherId);
            return new ResponseEntity<>(courseResourceVos, HttpStatus.OK);
        } catch (CustomRuntimeException cre) {
            return new ResponseEntity(cre.getErrorResult(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagCourseIdImagesGet", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> preparationcoursebagClasstestpaperNumPost(
            @ApiParam(value = "", required = true)
            @Valid @RequestBody List<String> classTestPaperIds,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }
        return extendPreparationCourseBagService.getUpdateClassTestPaperNums(classTestPaperIds);
    }

    public ResponseEntity<Void> updateCourseResourceListIsPublic(
            @NotNull @ApiParam(value = "课程资源Id", required = true)
            @Valid @RequestParam(value = "courseResourceId", required = true) String courseResourceId,
            @NotNull @ApiParam(value = "课程Id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "是否为公共资源(true:公开；false:隐藏)", required = true)
            @Valid @RequestParam(value = "isPublic", required = true) Boolean isPublic,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, courseResourceId, courseId) || isPublic == null) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            com.lztech.domain.model.course.CourseResource courseResource = courseResourceRepository
                    .findByIdAndResourceStatus(courseResourceId, ResourceStatus.NORMAL);
            if (courseResource != null) {
                if (ResourceSourceType.USER_COPIED.equals(courseResource.getSourceType())) {
                    return new ResponseEntity(ErrorResult.customError("引用的资源无法公开"), HttpStatus.CONFLICT);
                }
                preparationCourseBagService.updateCourseResourceListIsPublic(courseResourceId, isPublic, courseResource);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity(ErrorResult.customError("资源信息不存在"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("updateCourseResourceListIsPublic->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTeacherNewVo>> preparationcourseTeacherIdCourseTeacher(
            @ApiParam(value = "老师id", required = true)
            @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "验证码：teacherId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (StringUtils.isAnyBlank(validCode, teacherId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkValidCode("teacherId", teacherId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {

            List<CourseTeacherNewVo> courseTeachers = extendPreparationCourseBagService.getTeacherCoursesByTeacherId(teacherId);
            return new ResponseEntity<>(courseTeachers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("preparationcourseTeacherIdCourseTeacher->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<StatisticResult> preparationcoursebagMaterialStatistic(
            @ApiParam(value = "统计查询参数", required = true) @Valid @RequestBody StatisticQueryParam queryParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            StatisticResult statisticResult = extendPreparationCourseBagService.materialStatistic(queryParam);
            return new ResponseEntity<StatisticResult>(statisticResult, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagMaterialStatistic->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> coursebagMaterialDownloadStatistic(
            @ApiParam(value = "统计查询参数", required = true)
            @Valid @RequestBody StatisticQueryParam queryParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            HttpServletResponse response
    ) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            if (!extendPreparationCourseBagService
                    .exportStatistic(queryParam, response)) {
                return new ResponseEntity(ErrorResult.customError("导出失败"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagMaterialStatistic->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TeacherMateriaStatistic> teacherMateriaStatistic(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程结构id", required = true)
            @Valid @RequestParam(value = "courseStructureId", required = true) String courseStructureId
    ) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            TeacherMateriaStatistic teacherMateriaStatistic = extendPreparationCourseBagService
                    .teacherMateriaStatistic(courseStructureId);
            return new ResponseEntity<TeacherMateriaStatistic>(teacherMateriaStatistic, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("teacherMateriaStatistic->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseMateriaStatistic> courseMateriaStatistic(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程结构id", required = true)
            @Valid @RequestParam(value = "courseStructureId", required = true) String courseStructureId
    ) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseMateriaStatistic courseMateriaStatistic = extendPreparationCourseBagService
                    .courseMateriaStatistic(courseStructureId);
            return new ResponseEntity<CourseMateriaStatistic>(courseMateriaStatistic, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("courseMateriaStatistic->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseMateriaList> courseMateriaList(
            @ApiParam(value = "统计查询参数", required = true)
            @Valid @RequestBody MateriaListQueryParam queryParam,
            @ApiParam(value = "课程结构ID", required = true)
            @PathVariable("courseStructureId") String courseStructureId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseMateriaList courseMateriaList = extendPreparationCourseBagService
                    .courseMateriaList(courseStructureId, queryParam);
            return new ResponseEntity<CourseMateriaList>(courseMateriaList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("courseMateriaList->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> preparationcoursebagMaterialExport(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "学院ID(传0为全部)", required = true)
            @Valid @RequestParam(value = "collageId", required = true) String collageId,
            @ApiParam(value = "课程编号或名称")
            @Valid @RequestParam(value = "courseCodeOrName", required = false) String courseCodeOrName,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName) {
        if (StringUtils.isAnyBlank(validCode, userId, userName)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            extendPreparationCourseBagService
                    .exportPreparationCourseBag(collageId, courseCodeOrName, userId, userName);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagMaterialExport->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<PreparationPackageListVo>> preparationcoursebagMaterialExportList(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName) {
        if (StringUtils.isAnyBlank(validCode, userId, userName)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<PreparationPackageListVo> preparationPackageListVos = extendPreparationCourseBagService
                    .getPreparationList(userId, userName);
            return new ResponseEntity(preparationPackageListVos, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("preparationcoursebagMaterialExport->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> batchUpdateClassTestPaperNum(
            @ApiParam(value = "测验内容列表", required = true) @Valid @RequestBody List<ClassTestPaperResult> classTestPaperResultList,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            extendPreparationCourseBagService.batchUpdateClassTestPaperNum(classTestPaperResultList);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("batchUpdateClassTestPaperNum->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeachingFileVo>> getTeacherPreparationCourseBag(
            @ApiParam(value = "老师id", required = true) @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "课程Id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode, teacherId, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            List<TeachingFileVo> teachingFiles = preparationCourseBagService.getTeacherPreparationCourseBag(teacherId, courseId);
            return new ResponseEntity(teachingFiles, HttpStatus.OK);
        } catch (CustomRuntimeException cre) {
            return new ResponseEntity(cre.getErrorResult(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("getTeacherPreparationCourseBag", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeacherCourseResourceStatisticVo>> getPreparationCourseBagTeacherStatistics(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            List<TeacherCourseResourceStatisticVo> teacherCourseResourceStatisticVoList =
                    preparationCourseBagService.getPreparationCourseBagTeacherStatistic();
            return new ResponseEntity(teacherCourseResourceStatisticVoList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getPreparationCourseBagTeacherStatistics", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TeacherCourseResourceReferenceStatisticVo>> getPreparationCourseBagTeacherReferenceStatistics(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<TeacherCourseResourceReferenceStatisticVo> teacherCourseResourceReferenceStatisticVoList =
                    preparationCourseBagService.getPreparationCourseBagTeacherReferenceStatistics();
            return new ResponseEntity(teacherCourseResourceReferenceStatisticVoList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getPreparationCourseBagTeacherReferenceStatistics", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> courseResourceReorder(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "重新排序参数", required = true) @Valid @RequestBody ReorderParams reorderParams) {
        try {
            if (StringUtils.isAnyEmpty(validCode) || ObjectUtils.isEmpty(reorderParams)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            preparationCourseBagService.courseResourceReorder(reorderParams);
            return new ResponseEntity<Void>(HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("courseResourceReorder->", e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

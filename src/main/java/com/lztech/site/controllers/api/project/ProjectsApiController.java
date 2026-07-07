package com.lztech.site.controllers.api.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.project.enums.WhetherAssociateCourses;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.project.ProjectService;
import com.lztech.site.service.project.ProjectVerifyService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.project.*;
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
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-30T15:47:49.857Z")

@Controller
public class ProjectsApiController implements ProjectsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ProjectVerifyService projectVerifyService;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    public ProjectsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<ProjectVo>> projectsGet(@NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
                                                       @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (!DigestUtils.md5DigestAsHex(signKey.getBytes()).equals(validCode)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<ProjectVo> projectVos = projectService.getAllProjects();
        return new ResponseEntity(projectVos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProjectVo>> getInitialSortProjects(@NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
                                                       @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (!DigestUtils.md5DigestAsHex(signKey.getBytes()).equals(validCode)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<ProjectVo> projectVos = projectService.getInitialSortProjects();
        return new ResponseEntity(projectVos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProjectVo>> projectsProjectIdsGet(@NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
                                                                 @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                                 @NotNull @ApiParam(value = "项目id,多个用逗号隔开", required = true)
                                                                 @Valid @RequestParam(value = "projectIds", required = true) String projectIds) {
        try {
            if (StringUtils.isAllEmpty(projectIds, validCode)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
            String[] projectId = projectIds.split(",");
            List<String> projectIdList = Arrays.asList(projectId);
            List<ProjectVo> projectVos = projectService.getProjectByIds(projectIdList);
            return new ResponseEntity<List<ProjectVo>>(projectVos, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<List<ProjectVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //region 根据课程id获取项目信息
    public ResponseEntity<List<ProjectVo>> getProjectsByCourseId(
            @NotNull @ApiParam(value = "courseId不为空：MD5加密验证字符串(courseId={}&signKey=123123)；courseId为空：MD5加密验证字符串(&signKey=123123)",
                    required = true) @Valid
            @RequestParam(value = "validCode", required = true)
            String validCode,
            @NotNull @ApiParam(value = "课程Id", required = false) @Valid
            @RequestParam(value = "courseId", required = false)
            String courseId) {
        try {
            if (StringUtils.isAnyBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if ((StringUtils.isNotEmpty(courseId) && !ValidUtils.checkValidCode("courseId", courseId, validCode))
                    || (StringUtils.isBlank(courseId) && !RequestUtils.checkValidCode(signKey, validCode))) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<ProjectVo> projectVos = this.projectService.getProjectByCourseId(courseId);
            if (projectVos != null && projectVos.size() > 0) {
                return new ResponseEntity<List<ProjectVo>>(projectVos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
        } catch (Exception e) {
            LOGGER.error("getProjectsByCourseId:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    public ResponseEntity<List<ProjectTypeVo>> projectsProjecttypesGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return projectService.getProjectTypes();
        } catch (Exception e) {
            LOGGER.error("projectsProjecttypesGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ProjectCategoryVo>> projectsProjectcategoriesGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return projectService.getProjectCategories();
        } catch (Exception e) {
            LOGGER.error("projectsProjectcategoriesGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ProjectClaimVo>> projectsProjectclaimsGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return projectService.getProjectClaims();
        } catch (Exception e) {
            LOGGER.error("projectsProjectclaimsGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ProjectGenreVo>> projectsProjectgenresGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return projectService.getProjectGenres();
        } catch (Exception e) {
            LOGGER.error("projectsProjectgenresGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ProjectPageResource> projectsPagelistPost(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "项目信息列表查询条件", required = true)
            @Valid @RequestBody ProjectQueryParam param) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (param == null || param.getPage() == null || param.getPageSize() == null
                    || param.getPage() == 0 || param.getPageSize() == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return projectService.getProjectsPageList(param);
        } catch (Exception e) {
            LOGGER.error("projectsPagelistPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Void> deleteProjectList(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "项目信息批量删除条件", required = true)
            @Valid @RequestBody ProjectDeleteParam projectDeleteParam
    ) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (ObjectUtils.isEmpty(projectDeleteParam)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (CollectionUtils.isEmpty(projectDeleteParam.getProjectIdList())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            projectService.deleteProjectList(projectDeleteParam);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("deleteProjectList:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> importProjectList(
            @NotNull @ApiParam(value = "用户id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "项目信息")
            @Valid @RequestPart(value = "projectFile", required = true) MultipartFile projectFile
    ) {
        try {
            if (StringUtils.isAnyEmpty(userId, userName, validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            ProjectValidateResource projectValidateResource = projectVerifyService.validateAndParseExcel(projectFile);

            if (projectValidateResource.getStatus() == 0){
                return new ResponseEntity(ErrorResult.customError(projectValidateResource.getMessage()), HttpStatus.CONFLICT);
            }
            if (CollectionUtils.isEmpty(projectValidateResource.getProjectFileModelList())){
                return new ResponseEntity(ErrorResult.customError("导入失败，导入模板内无有效数据！"), HttpStatus.CONFLICT);
            }

            ProjectValidateResource filterProjectValidateResource =
                    projectService.importProjectList(userId, userName, projectValidateResource.getProjectFileModelList());

            if (filterProjectValidateResource.getStatus() == 0){
                return new ResponseEntity(ErrorResult.customError(filterProjectValidateResource.getMessage()), HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("importProjectList" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ProjectResource> projectsPost(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "新增项目信息", required = true) @Valid @RequestBody ProjectInfoResource projectInfoResource) {
        if (projectInfoResource == null) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isAnyBlank(validCode, projectInfoResource.getProjectName())
                || projectInfoResource.getWhetherAssociateCourses() == null
                || projectInfoResource.getWhetherAssociateCourses() > 1) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            if (StringUtils.isNotBlank(projectInfoResource.getProjectCode())) {
                // 如果输入了项目编码，判断项目编码是否已存在
                int sameProjectCodeNum = projectService.countSameProjectCode(projectInfoResource.getProjectCode());
                if (sameProjectCodeNum > 0) {
                    return new ResponseEntity(ErrorResult.customError("项目编号不能重复"), HttpStatus.CONFLICT);
                }
            }

            if (projectInfoResource.getWhetherAssociateCourses().equals(WhetherAssociateCourses.YES.getValue())) {
                Course course = courseService.findById(projectInfoResource.getCourseId());
                if (Objects.isNull(course)) {
                    return new ResponseEntity(ErrorResult.customError("此课程不存在"), HttpStatus.CONFLICT);
                }
                int sameProjectCount = projectService.checkProjectName(projectInfoResource.getProjectName(), projectInfoResource.getCourseId());
                if (sameProjectCount > 0) {
                    return new ResponseEntity(ErrorResult.customError("所属课程下该项目名称已存在"), HttpStatus.CONFLICT);
                }
            }

            ProjectResource projectResource = projectService.createProject(projectInfoResource);
            return new ResponseEntity<>(projectResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("projectsPost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> projectsIdPost(
            @NotNull @ApiParam(value = "验证码id=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "项目id", required = true) @PathVariable("id") String id,
            @ApiParam(value = "修改项目信息", required = true) @Valid @RequestBody ProjectInfoResource projectInfoResource) {
        try {
            if (projectInfoResource == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isAnyBlank(validCode, id, projectInfoResource.getProjectName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("id", id, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return projectService.updateProject(projectInfoResource, id);
        } catch (Exception e) {
            LOGGER.error("projectsIdPost:" , e );
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ProjectResource>> getCourseProjectList(@NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
                                                                      @Valid @RequestParam(value = "validCode", required = true)
                                                                      String validCode,
                                                                      @NotNull @ApiParam(value = "课程ids", required = true)
                                                                      @Valid @RequestParam(value = "courseIds", required = true)
                                                                      String courseIds,
                                                                      @ApiParam(value = "项目名称") @Valid
                                                                      @RequestParam(value = "projectName", required = false)
                                                                      String projectName) {

        if (StringUtils.isAnyBlank(courseIds, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<ProjectResource> projectResourceList = projectService.getCourseProjectList(courseIds, projectName);

            return new ResponseEntity<>(projectResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseProjectList->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateProjectStandardDuration(@NotNull @ApiParam(value = "验证码：id={}&signKey=123123(md5加密)", required = true)
                                                              @Valid @RequestParam(value = "validCode", required = true)
                                                              String validCode,
                                                              @ApiParam(value = "项目id", required = true)
                                                              @PathVariable("id")
                                                              String id,
                                                              @NotNull @ApiParam(value = "项目标准时长 单位：h", required = true)
                                                              @Valid @RequestParam(value = "standardDuration", required = true)
                                                              BigDecimal standardDuration,
                                                              @ApiParam(value = "修改人id") @Valid
                                                              @RequestParam(value = "modifierId", required = false)
                                                              String modifierId,
                                                              @ApiParam(value = "修改人姓名") @Valid
                                                              @RequestParam(value = "modifierName", required = false)
                                                              String modifierName) {
        if (StringUtils.isAnyBlank(validCode, id, modifierId, modifierName) || Objects.isNull(standardDuration)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            projectService.updateProjectStandardDuration(id, standardDuration, modifierId, modifierName);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("updateProjectStandardDuration->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ProjectVo>> getBookableProjects(
            @NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "开始时间", required = true)
            @Valid @RequestParam(value = "startTime", required = true) String startTime,
            @NotNull @ApiParam(value = "结束时间", required = true)
            @Valid @RequestParam(value = "endTime", required = true) String endTime) {
        if (StringUtils.isAnyBlank(validCode,startTime,endTime)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<ProjectVo> projectVos = projectService.getBookableProjects(startTime,endTime);
            return new ResponseEntity(projectVos, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getBookableProjects->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

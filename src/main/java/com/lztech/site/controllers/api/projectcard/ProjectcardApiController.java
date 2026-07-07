package com.lztech.site.controllers.api.projectcard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.projectcard.ProjectCardService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.projectcard.ProjectCardPageVo;
import com.lztech.site.viewmodel.projectcard.ProjectCardVo;
import com.lztech.site.viewmodel.projectsuite.ProjectSuiteResource;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-19T10:55:50.034Z")

@Controller
public class ProjectcardApiController implements ProjectcardApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectcardApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ProjectCardService projectCardService;

    @org.springframework.beans.factory.annotation.Autowired
    public ProjectcardApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<ProjectSuiteResource>> getProjectSuiteByProjectIds(
            @NotNull @ApiParam(value = "课程Id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "项目id（多个用逗号“,”隔开）", required = true)
            @Valid @RequestParam(value = "projectIds", required = true) String projectIds,
            @NotNull @ApiParam(value = "验证码（courseId=XXX&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (StringUtils.isAnyBlank(validCode, courseId, projectIds)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return projectCardService.getProjectSuiteByProjectIds(courseId, projectIds);
        } catch (Exception e) {
            LOGGER.error("getProjectSuiteByProjectIds:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ProjectCardPageVo> projectCardGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "当前页", required = true)
            @Valid @RequestParam(value = "page", required = true) Integer page,
            @NotNull @ApiParam(value = "每页个数", required = true)
            @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @ApiParam(value = "项目卡名称") @Valid @RequestParam(value = "projectName", required = false) String projectName,
            @ApiParam(value = "所属课程id") @Valid @RequestParam(value = "courseId", required = false) String courseId) {

        try {
            if (StringUtils.isBlank(validCode) || page == null || pageSize == null
                    || page == 0 || pageSize == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return projectCardService.getProjectCards(page, pageSize, projectName, courseId);
        } catch (Exception e) {
            LOGGER.error("projectCardGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> projectCardPost(
            @NotNull @ApiParam(value = "MD5加密验证字符串&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "", required = true) @Valid @RequestBody ProjectCardVo projectCardVo) {

        try {
            if (StringUtils.isAllBlank(projectCardVo.getCourseId(),projectCardVo.getCourseCode(),
                    projectCardVo.getCourseName(), projectCardVo.getProjectId(), projectCardVo.getProjectName())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (CollectionUtils.isEmpty(projectCardVo.getProjectSuiteIds())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return projectCardService.saveProjectCard(projectCardVo);
        } catch (Exception e) {
            LOGGER.error("projectCardPost" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Void> projectCardDelete(
            @NotNull @ApiParam(value = "projectCardIds=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "项目卡id(逗号分割)", required = true)
            @Valid @RequestParam(value = "projectCardIds", required = true) String projectCardIds) {
        try {
            if (StringUtils.isAnyEmpty(validCode, projectCardIds)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("projectCardIds", projectCardIds, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return projectCardService.deleteProjectCard(projectCardIds);
        } catch (Exception e) {
            LOGGER.error("projectCardDelete" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

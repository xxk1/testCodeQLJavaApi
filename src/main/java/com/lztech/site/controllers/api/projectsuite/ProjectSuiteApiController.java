package com.lztech.site.controllers.api.projectsuite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteType;
import com.lztech.site.service.projectsuite.ProjectSuiteService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.projectsuite.*;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-21T05:33:12.625Z")

@Controller
public class ProjectSuiteApiController implements ProjectSuiteApi {

    @Autowired
    private ProjectSuiteService projectSuiteService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectSuiteApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProjectSuiteApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> projectSuitePost(@NotNull @ApiParam(value = "MD5加密验证字符串", required = true)
                                                 @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                 @ApiParam(value = "低值易耗品", required = true)
                                                 @Valid @RequestBody ProjectSuiteVo projectSuiteVo) {
        try {
            if (StringUtils.isEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (CollectionUtils.isEmpty(projectSuiteVo.getArticlesList())){
                return new ResponseEntity(ErrorResult.customError("套件物品不能为空"), HttpStatus.CONFLICT);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            if (ObjectUtils.isEmpty(ProjectSuiteType.getProjectSuiteType(projectSuiteVo.getProjectSuiteType()))) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            return projectSuiteService.operationProjectSuite(projectSuiteVo);
        } catch (Exception e) {
            LOGGER.error("projectSuiteAddProjectSuitePost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ProjectSuitePageVo> projectSuiteGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "当前页", required = true)
            @Valid @RequestParam(value = "page", required = true) Integer page,
            @NotNull @ApiParam(value = "每页个数", required = true)
            @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @ApiParam(value = "套件名称")
            @Valid @RequestParam(value = "projectSuiteName", required = false) String projectSuiteName,
            @ApiParam(value = "套件名称:0 设备仪器、1 低值易耗 ")
            @Valid @RequestParam(value = "projectSuiteType", required = false) String projectSuiteType) {
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
            return projectSuiteService.getProjectSuites(page, pageSize,projectSuiteName,projectSuiteType);
        } catch (Exception e) {
            LOGGER.error("projectSuiteGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ProjectSuiteInfo> projectSuiteProjectSuiteIdGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串projectSuiteId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "套件id",required=true) @PathVariable("projectSuiteId") String projectSuiteId) {
        try {
            if (StringUtils.isAnyEmpty(validCode,projectSuiteId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("projectSuiteId", projectSuiteId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return projectSuiteService.getProjectSuiteInfo(projectSuiteId);
        } catch (Exception e) {
            LOGGER.error("projectSuiteProjectSuiteIdGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> projectSuiteDelete(
            @NotNull @ApiParam(value = "MD5加密验证字符串projectSuiteIds=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "套件id(逗号分割)", required = true)
            @Valid @RequestParam(value = "projectSuiteIds", required = true) String projectSuiteIds) {
        try {
            if (StringUtils.isAnyEmpty(validCode, projectSuiteIds)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("projectSuiteIds", projectSuiteIds, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return projectSuiteService.deleteProjectSuite(projectSuiteIds);
        } catch (Exception e) {
            LOGGER.error("projectSuiteDelete" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ArticlesModel>> projectSuiteLowValueArticlesPost(
            @NotNull @ApiParam(value = "MD5加密验证字符串&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "", required = true) @RequestBody @Valid List<ProjectSuiteResourceVo> projectSuiteResourceVos) {
        try {
            if (CollectionUtils.isEmpty(projectSuiteResourceVos) || StringUtils.isAllBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return  projectSuiteService.getSuiteProjectSuiteIdLowValueArticles(projectSuiteResourceVos);
        } catch (Exception e) {
            LOGGER.error("projectSuiteDelete" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

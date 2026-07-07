package com.lztech.site.controllers.api.projectclassification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.projectclassification.ProjectClassificationService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.projectclassification.ClassificationResource;
import com.lztech.site.viewmodel.projectclassification.ProjectClassificationVo;
import io.swagger.annotations.ApiParam;
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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-15T09:02:09.480Z")

@Controller
public class ProjectclassificationsApiController implements ProjectclassificationsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectclassificationsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ProjectClassificationService projectClassificationService;

    @org.springframework.beans.factory.annotation.Autowired
    public ProjectclassificationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<ProjectClassificationVo>> projectclassificationsGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return projectClassificationService.getProjectClassifications();
        } catch (Exception e) {
            LOGGER.error("projectclassificationsGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> projectclassificationsPost(
            @NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "新增项目分类信息") @Valid @RequestBody ClassificationResource classificationResource) {
        try {
            if (StringUtils.isAnyBlank(validCode, classificationResource.getClassificationName()) || classificationResource == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return projectClassificationService.createProjectClassification(classificationResource);
        } catch (Exception e) {
            LOGGER.error("projectclassificationsPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> projectclassificationsIdDelete(
            @NotNull @ApiParam(value = "id=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "项目分类id",required=true) @PathVariable("id") String id) {
        try {
            if (StringUtils.isAnyBlank(validCode, id)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            String validKey = "id=" + id + signKey;
            if (!RequestUtils.checkValidCode(validKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return projectClassificationService.deleteProjectClassification(id);
        } catch (Exception e) {
            LOGGER.error("projectclassificationsIdDelete:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

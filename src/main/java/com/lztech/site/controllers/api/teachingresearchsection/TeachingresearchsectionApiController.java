package com.lztech.site.controllers.api.teachingresearchsection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.teachingresearchsection.TeachingResearchSectionService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingresearchsection.TeachingResearchSectionPage;
import com.lztech.site.viewmodel.teachingresearchsection.TeachingResearchSectionVo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-24T06:53:58.920Z")

@Controller
public class TeachingresearchsectionApiController implements TeachingresearchsectionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingresearchsectionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private TeachingResearchSectionService teachingResearchSectionService;

    @org.springframework.beans.factory.annotation.Autowired
    public TeachingresearchsectionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> teachingresearchsectionPost(
            @ApiParam(value = "新增/修改的教研室信息", required = true) @Valid @RequestBody TeachingResearchSectionVo teachingResearchSectionVo,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (ObjectUtils.isEmpty(teachingResearchSectionVo) || ObjectUtils.isEmpty(teachingResearchSectionVo.getTeachingResearchCourseVoList())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isAnyBlank(validCode, teachingResearchSectionVo.getCollegeId(), teachingResearchSectionVo.getTeachingResearchName())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return teachingResearchSectionService.teachingResearchSectionAddOrUpdate(teachingResearchSectionVo);
        } catch (Exception e) {
            LOGGER.error("teachingresearchsectionPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TeachingResearchSectionPage> teachingresearchsectionGet(
            @NotNull @ApiParam(value = "当前页", required = true) @Valid @RequestParam(value = "page", required = true) Integer page,
            @NotNull @ApiParam(value = "每页个数", required = true) @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isEmpty(validCode) || page == null || page == 0 || pageSize == null || pageSize == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return teachingResearchSectionService.getTeachingResearchSectionList(page, pageSize);
        } catch (Exception e) {
            LOGGER.error("teachingresearchsectionGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> teachingresearchsectionDelete(
            @NotNull @ApiParam(value = "教研室id", required = true) @Valid @RequestParam(value = "id", required = true) String id,
            @NotNull @ApiParam(value = "id=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, id)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            String validKey = "id=" + id + signKey;
            if (!RequestUtils.checkValidCode(validKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return teachingResearchSectionService.deleteTeachingResearchSection(id);
        } catch (Exception e) {
            LOGGER.error("teachingresearchsectionDelete:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

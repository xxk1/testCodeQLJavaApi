package com.lztech.site.controllers.api.college;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.college.College;
import com.lztech.site.resource.college.CollegeCourseResource;
import com.lztech.site.resource.college.CollegeResource;
import com.lztech.site.service.college.CollegeService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.college.CollegeIdsParam;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-07T02:41:59.747Z")

@Controller
public class CollegesApiController implements CollegesApi {

    private final Logger log = LoggerFactory.getLogger(CollegesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private CollegeService collegeService;

    @org.springframework.beans.factory.annotation.Autowired
    public CollegesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> collegesPost(@NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
                                             @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                             @ApiParam(value = "学院列表" ,required=true )  @Valid @RequestBody List<CollegeResource> colleges) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if(colleges.isEmpty()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<College> collegeList = collegeService.saveColleges(colleges);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("collegesPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CollegeResource>> allcollegesGet(@NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
                                                                @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            List<CollegeResource> collegeList = collegeService.getColleges();
            return new ResponseEntity<List<CollegeResource>>(collegeList,HttpStatus.OK);
        } catch (Exception e) {
            log.error("allcollegesGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CollegeResource> datadockCollegePost(@NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
                                                               @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                               @ApiParam(value = "学院信息" ,required=true )
                                                               @Valid @RequestBody CollegeResource collegeResource) {


        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if(StringUtils.isEmpty(collegeResource.getCollegeName())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            College college = collegeService.findByName(collegeResource.getCollegeName());
            if(college != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            college = collegeService.saveCollege(collegeResource);
            if(college == null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            collegeResource.setId(college.getId());
            collegeResource.setCollegeCode(college.getCollegeCode());
            return new ResponseEntity<CollegeResource>(collegeResource,HttpStatus.OK);
        } catch (Exception e) {
            log.error("allcollegesGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CollegeResource>> collegesGet(
            @NotNull @ApiParam(value = "学院ids（学院id逗号分割）", required = true)
            @Valid @RequestParam(value = "collegeIds", required = true) String collegeIds,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (org.apache.commons.lang3.StringUtils.isAnyEmpty(validCode,collegeIds)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return collegeService.getCollegesByIds(collegeIds);
        } catch (Exception e) {
            log.error("collegesGet:" , e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CollegeCourseResource>> findCollegeInfoByCourseIds(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程Id列表" ,required=true )
            @Valid @RequestBody List<String> courseIds) {

        try {
            if (org.apache.commons.lang3.StringUtils.isAnyEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (CollectionUtils.isEmpty(courseIds)){
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CollegeCourseResource> collegeCourseResources = collegeService.getCollegeInfoByCourseId(courseIds);
            return new ResponseEntity<List<CollegeCourseResource>>(collegeCourseResources, HttpStatus.OK);
        } catch (Exception e) {
            log.error("findCollegeInfoByCourseIds:" + e);
            return new ResponseEntity<List<CollegeCourseResource>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Void> updateCollegeCourseInfoCollege(
            @ApiParam(value = "学院id",required=true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "MD5加密验证字符串(id={id}&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "是否开课学院", required = true)
            @Valid @RequestParam(value = "courseInfoCollegeStatus", required = true) Boolean courseInfoCollegeStatus) {

        if (org.apache.commons.lang3.StringUtils.isAnyBlank(id,validCode)
                || ObjectUtils.isEmpty(courseInfoCollegeStatus)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        try {
            if (!ValidUtils.checkAuthCode("id",id,validCode)){
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            College college = collegeService.getCollegeById(id);
            if (ObjectUtils.isEmpty(college)) {
                return new ResponseEntity(ErrorResult.dataNotExistError("学院信息"), HttpStatus.NOT_FOUND);
            }

            collegeService.updateCollegeCourseInfoCollege(college,courseInfoCollegeStatus);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            log.error("updateCollegeCourseInfoCollege：", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CollegeResource>> getCourseInfoOrAllCollegeByIds(
            @ApiParam(value = "查询参数" ,required=true )  @Valid @RequestBody CollegeIdsParam queryParam,
            @ApiParam(value = "验证码：&signKey=123123(md5加密)")
            @Valid @RequestParam(value = "validCode", required = false) String validCode) {
        try {
            if (org.apache.commons.lang3.StringUtils.isAnyBlank(validCode)
                    || ObjectUtils.isEmpty(queryParam)
                    || org.apache.commons.lang3.StringUtils.isAnyBlank(queryParam.getIds())
                    || ObjectUtils.isEmpty(queryParam.isCourseInfoCollegeStatus())) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return collegeService.getCourseInfoOrAllCollegeByIds(queryParam.getIds(), queryParam.isCourseInfoCollegeStatus());

        } catch (Exception e) {
            log.error("getCourseInfoOrAllCollegeByIds:",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CollegeCourseResource>> findAllByCourseCode(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程Id列表", required = true) @Valid @RequestBody List<String> courseCodes
    ) {
        try {
            if (org.apache.commons.lang3.StringUtils.isAnyEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (CollectionUtils.isEmpty(courseCodes)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CollegeCourseResource> collegeCourseResources = collegeService.getCollegeInfoByCourseCode(courseCodes);
            return new ResponseEntity<List<CollegeCourseResource>>(collegeCourseResources, HttpStatus.OK);
        } catch (Exception e) {
            log.error("findAllByCourseCode:" + e);
            return new ResponseEntity<List<CollegeCourseResource>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

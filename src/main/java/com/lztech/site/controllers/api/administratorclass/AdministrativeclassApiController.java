package com.lztech.site.controllers.api.administratorclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.administratorclass.AdministrativeClassGroupInfo;
import com.lztech.site.resource.administratorclass.AdministrativeClassInfo;
import com.lztech.site.resource.administratorclass.AdministrativeClassInfoVo;
import com.lztech.site.resource.administratorclass.AdministrativeClassVo;
import com.lztech.site.service.administratorclass.AdministratorClassService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.administratorclass.AdministrativeClassResourceVo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T08:30:00.298Z")

@Controller
public class AdministrativeclassApiController implements AdministrativeclassApi {

    private final Logger log = LoggerFactory.getLogger(AdministrativeclassApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private AdministratorClassService administratorClassService;

    @org.springframework.beans.factory.annotation.Autowired
    public AdministrativeclassApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> administrativeclassGroupsPost(@NotNull @ApiParam(value = "&signKey=123123", required = true)
                                                              @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                              @ApiParam(value = "行政班教学班关系列表", required = true)
                                                              @Valid @RequestBody List<AdministrativeClassGroupInfo> administrativeClassGroups) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (administrativeClassGroups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            administratorClassService.saveAdministrativeClassAndGroup(administrativeClassGroups);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("groupsGroupmembersPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> administrativeclassPost(@NotNull @ApiParam(value = "&signKey=123123", required = true)
                                                        @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                        @ApiParam(value = "行政班列表", required = true)
                                                        @Valid @RequestBody List<AdministrativeClassInfo> administrativeClasses) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (administrativeClasses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            administratorClassService.saveAdministratorClasses(administrativeClasses);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("groupsGroupmembersPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<AdministrativeClassVo>> administrativeClassGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "老师Id") @Valid @RequestParam(value = "teacherId", required = false) String teacherId) {

        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<AdministrativeClassVo> administrativeClassVoList = administratorClassService.getAdministrativeClassVoList(teacherId);
            return new ResponseEntity<>(administrativeClassVoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("administrativeClassGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<AdministrativeClassInfoVo>> getAdministrativeClassVoListByTeacherId(
            @ApiParam(value = "老师id",required=true) @PathVariable("teacherId") String teacherId,
            @NotNull @ApiParam(value = "MD5加密验证字符串 teacherId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(teacherId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode("teacherId",teacherId,validCode)){
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<AdministrativeClassInfoVo> administrativeClassInfoVoList =
                    administratorClassService.getAdministrativeClassVoListByTeacherId(teacherId);
            return new ResponseEntity<>(administrativeClassInfoVoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getAdministrativeClassVoListByTeacherId->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<AdministrativeClassResourceVo>> getAdministrativeClassList(
            @NotNull @ApiParam(value = "MD5加密验证字符串 collegeId=?&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "学院id", required = true)
            @Valid @RequestParam(value = "collegeId", required = true) String collegeId,
            @NotNull @ApiParam(value = "行政班名称", required = true)
            @Valid @RequestParam(value = "className", required = true) String className) {
        try {
            if (StringUtils.isAnyBlank(collegeId, validCode, className)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            String validKey = "collegeId=" + collegeId + signKey;
            if (!RequestUtils.checkValidCode(validKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.UNAUTHORIZED);
            }
            List<AdministrativeClassResourceVo> administrativeClassList = administratorClassService
                    .getAdministrativeClassList(collegeId, className);
            return new ResponseEntity<>(administrativeClassList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getAdministrativeClassList->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.lztech.site.controllers.api.coursebag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.coursebag.CoursePackageListVo;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.coursebag.CourseBagService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-20T03:08:00.445Z")

@Controller
public class CoursebagApiController implements CoursebagApi {
    @Autowired
    private CourseBagService courseBagService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursebagApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final HttpServletResponse response;
    @org.springframework.beans.factory.annotation.Autowired
    public CoursebagApiController(ObjectMapper objectMapper, HttpServletRequest request, HttpServletResponse response) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.response = response;
    }

    public ResponseEntity<Void> exportCourseBag(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本id", required = true)
            @Valid @RequestParam(value = "versionId", required = true) String versionId,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId) {
        if (StringUtils.isAnyBlank(userId,courseId,versionId,validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            courseBagService.exportCourseBag(userId,courseId,versionId);
            return new ResponseEntity<>( HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("exportCourseBag->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> importCourseBag(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程包信息（只支持zip文件）") @Valid @RequestPart(value="courseBag", required=false) MultipartFile courseBag,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName,
            @NotNull @ApiParam(value = "上传类型(0:上传课程包，1:生成课程)", required = true)
            @Valid @RequestParam(value = "uploadType", required = true) String uploadType,
            @ApiParam(value = "版本ID")
            @Valid @RequestParam(value = "versionId", required = false) String versionId) {
        if (StringUtils.isAnyBlank(uploadType,userId,userName,validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseBagService.importCourseBag(versionId,userName,userId,courseBag,uploadType);
            if(result.getStatus().equals(ResultStatus.ERROR)){
                return new ResponseEntity(result.getMsg(), HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity( HttpStatus.OK);
            }
        } catch (Exception e) {
            LOGGER.error("exportCourseBag->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CoursePackageListVo>> packCourseBag(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId) {
        if (StringUtils.isAnyBlank(userId,validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CoursePackageListVo> coursePackageListVos = courseBagService.getPackCourseBag(userId);
            return new ResponseEntity<>(coursePackageListVos, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("packCourseBag->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> recordCourseBag(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本id", required = true)
            @Valid @RequestParam(value = "versionId", required = true) String versionId,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName) {
        if (StringUtils.isAnyBlank(userId,courseId,versionId,validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseBagService.ecordCourseBag(userId,userName,courseId,versionId);
            if(result.getStatus().equals(ResultStatus.SUCCESS)){
                return new ResponseEntity<>( HttpStatus.OK);
            }else{
                return new ResponseEntity(result.getMsg(), HttpStatus.CONFLICT);
            }


        } catch (Exception e) {
            LOGGER.error("exportCourseBag->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

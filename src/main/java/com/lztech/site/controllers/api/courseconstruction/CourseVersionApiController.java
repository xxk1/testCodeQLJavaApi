package com.lztech.site.controllers.api.courseconstruction;

import com.lztech.site.service.courseconstruction.CourseVersionService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseconstruction.CourseVersionResource;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-18T07:05:46.115Z")

@Controller
public class CourseVersionApiController implements CourseVersionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseVersionApiController.class);

    @Autowired
    private CourseVersionService courseVersionService;

    public ResponseEntity<List<CourseVersionResource>> getCourseVersion(@NotNull @ApiParam(value = "验证码（id={}&signKey=123123）", required = true)
                                                                        @Valid @RequestParam(value = "validCode", required = true)
                                                                                String validCode,
                                                                        @ApiParam(value = "课程id", required = true) @PathVariable("id")
                                                                                String id,
                                                                        @ApiParam(value = "版本状态 0：已归档；1：使用中") @Valid
                                                                        @RequestParam(value = "versionStatus", required = false)
                                                                                Integer versionStatus) {
        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseVersionResource> courseVersionResourceList = courseVersionService.getCourseVersion(id, versionStatus);

            return new ResponseEntity<>(courseVersionResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseVersion->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<Void> saveCourseVersion(
            @NotNull @ApiParam(value = "验证码（id={}&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程id",required=true) @PathVariable("id") String id,
            @ApiParam(value = "课程建设归档信息" ,required=true )
            @Valid @RequestBody CourseVersionResource courseVersionResource) {
        if (StringUtils.isAnyBlank(validCode, id,courseVersionResource.getVersionId())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            courseVersionService.saveCourseVersion(id,courseVersionResource);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("saveCourseVersion->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

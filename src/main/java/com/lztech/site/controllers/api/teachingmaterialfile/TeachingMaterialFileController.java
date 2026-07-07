package com.lztech.site.controllers.api.teachingmaterialfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.teachingmaterialfile.TeachingMaterialFileService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingmaterialfile.TeachingMaterialFileResource;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-17T03:10:25.361Z")

@Controller
public class TeachingMaterialFileController implements TeachingMaterialFileApi {

    private final Logger log = LoggerFactory.getLogger(TeachingMaterialFileController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private TeachingMaterialFileService teachingMaterialFileService;

    @org.springframework.beans.factory.annotation.Autowired
    public TeachingMaterialFileController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<TeachingMaterialFileResource>> getTeachingMaterialFileResourceList(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseId)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseId", courseId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<TeachingMaterialFileResource> teachingMaterialFileResourceList =
                    teachingMaterialFileService.getTeachingMaterialFileResourceList(courseId);
            return new ResponseEntity<>(teachingMaterialFileResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getTeachingMaterialFileResourceList:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

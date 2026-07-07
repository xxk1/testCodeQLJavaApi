package com.lztech.site.controllers.api.collegeresources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.collegeresources.CollegeResources;
import com.lztech.site.service.collegeresources.CollegeResourcesService;
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

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-08T09:26:24.794Z")

@Controller
public class CollegeResourcesApiController implements CollegeResourcesApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollegeResourcesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    private CollegeResourcesService collegeResourcesService;

    @org.springframework.beans.factory.annotation.Autowired
    public CollegeResourcesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<CollegeResources>> collegeResourcesGet(
            @ApiParam(value = "学院名称")
            @RequestParam(value = "collegeName",required = false) String collegeName,
            @ApiParam(value = "是否是开课学院")
            @RequestParam(value = "courseInfoCollege",required = false) Integer courseInfoCollege,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return collegeResourcesService.getCollegeResources(collegeName,courseInfoCollege);
        } catch (Exception e) {
            LOGGER.error("collegeResourcesGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

package com.lztech.site.controllers.api.courseresources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.courseresources.CourseResourcesService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.courseresource.CourseResourceDetailVo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-22T09:43:51.375Z")

@Controller
public class CourseresourcedetailApiController implements CourseresourcedetailApi {

    private final Logger log = LoggerFactory.getLogger(CourseresourcedetailApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Value("${signKey}")
    private String signKey;
    @Autowired
    private CourseResourcesService courseResourcesService;

    @org.springframework.beans.factory.annotation.Autowired
    public CourseresourcedetailApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<CourseResourceDetailVo>> courseresourcedetailGet(
            @NotNull @ApiParam(value = "试卷Id（逗号分割）", required = true)
            @Valid @RequestParam(value = "resourceIds", required = true) String resourceIds,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return courseResourcesService.getCourseResourceDetail(resourceIds);
        } catch (Exception e) {
            log.error("courseresourcedetailGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

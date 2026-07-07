package com.lztech.site.controllers.api.administrativeclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.administrativeclass.AdministrativeVo;
import com.lztech.site.resource.administrativeclass.ClassCourseTablePage;
import com.lztech.site.resource.coursetable.CourseTypeCountResource;
import com.lztech.site.service.coursetabledetail.CourseTableDetailService;
import com.lztech.site.until.RequestUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-22T01:42:27.475Z")

@Controller
public class ClassesApiController implements ClassesApi {

    private final Logger log = LoggerFactory.getLogger(ClassesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Value("${signKey}")
    private String signKey;
    @Value("${studentType}")
    private Integer defaultStudentType;
    @Autowired
    private CourseTableDetailService courseTableDetailService;

    @org.springframework.beans.factory.annotation.Autowired
    public ClassesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ClassCourseTablePage> classesCoursetablesPagePost(
            @ApiParam(value = "", required = true)
            @Valid @RequestBody AdministrativeVo administrative,
            @NotNull @ApiParam(value = "MD5加密验证字符串 (&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isEmpty(administrative.getPage()) || StringUtils.isEmpty(administrative.getRows())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (administrative.getPage() <= 0 || administrative.getRows() <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            return courseTableDetailService.classesCourseTablesPagePost(administrative);
        } catch (Exception e) {
            log.error("classesCoursetablesPagePost->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTypeCountResource> classesCoursetablesStatisticsPost(
            @ApiParam(value = "", required = true)
            @Valid @RequestBody AdministrativeVo administrative,
            @NotNull @ApiParam(value = "MD5加密验证字符串 (&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            return courseTableDetailService.classesCourseTablesStatisticsPost(administrative);
        } catch (Exception e) {
            log.error("classesCoursetablesStatisticsPost->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

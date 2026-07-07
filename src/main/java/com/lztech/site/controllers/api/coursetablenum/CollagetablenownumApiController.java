package com.lztech.site.controllers.api.coursetablenum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.coursetable.CourseTableService;
import io.swagger.annotations.ApiParam;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-16T13:14:38.739Z")

@Controller
public class CollagetablenownumApiController implements CollagetablenownumApi {
    @Autowired
    private CourseTableService courseTableService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CollagetablenownumApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public CollagetablenownumApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Integer> collagetablenownumGet(@NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
                                                         @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            ResponseEntity<Integer> collegeINum = courseTableService.getCollegeINum();
            return collegeINum;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity("未找到资源", HttpStatus.NOT_FOUND);
        }
    }

}

package com.lztech.site.controllers.api.dibblingvideo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.coursetable.CourseTableService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.coursetable.CourseTableVo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
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

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-28T06:15:56.376Z")

@Controller
public class DibblingvideoApiController implements DibblingvideoApi {

    private final Logger log = LoggerFactory.getLogger(DibblingvideoApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    private CourseTableService courseTableService;
    @org.springframework.beans.factory.annotation.Autowired
    public DibblingvideoApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CourseTableVo> dibblingvideoCoursetableGet(
            @NotNull @ApiParam(value = "课表Id", required = true)
            @Valid @RequestParam(value = "id", required = true) String id,
            @NotNull @ApiParam(value = "&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(id)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return courseTableService.getCourseTableById(id);
        } catch (Exception e) {
            log.error("dibblingvideoCoursetableGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

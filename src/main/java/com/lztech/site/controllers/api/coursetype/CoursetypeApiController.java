package com.lztech.site.controllers.api.coursetype;

import com.lztech.site.service.coursetype.CourseTypeService;
import com.lztech.site.viewmodel.coursetype.CourseTypeResource;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-21T05:45:39.862Z")

@Controller
public class CoursetypeApiController implements CoursetypeApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursetypeApiController.class);
    @Value("${signKey}")
    private String signKey;
    @Autowired
    private CourseTypeService courseTypeService;

    public ResponseEntity<List<CourseTypeResource>> coursetypeGet(@NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
                                                                  @Valid @RequestParam(value = "validCode", required = true)
                                                                          String validCode) {

        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey,validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return courseTypeService.findAllCourseTypeResource();
        } catch (Exception e) {
            LOGGER.error("coursetypeGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

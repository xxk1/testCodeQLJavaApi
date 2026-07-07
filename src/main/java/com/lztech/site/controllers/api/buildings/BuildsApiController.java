package com.lztech.site.controllers.api.buildings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.segment.SegmentService;
import com.lztech.site.viewmodel.buildings.Buildings;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-08T07:23:58.366Z")

@Controller
public class BuildsApiController implements BuildsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuildsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Value("${signKey}")
    private String signKey;
    @Autowired
    private SegmentService segmentService;

    @org.springframework.beans.factory.annotation.Autowired
    public BuildsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Buildings>> buildsGet(@ApiParam(value = "验证码&signKey=123123(md5加密)")
                                                     @Valid @RequestParam(value = "validCode", required = false)
                                                             String validCode) {
        if (StringUtils.isEmpty(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!checkValidCode(signKey, validCode)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            return segmentService.getBuildings();
        } catch (Exception e) {
            LOGGER.error("buildsGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

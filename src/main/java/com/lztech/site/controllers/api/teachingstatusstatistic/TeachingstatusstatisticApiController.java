package com.lztech.site.controllers.api.teachingstatusstatistic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.teachingstatusstatistic.TeachingStatusStatisticService;
import com.lztech.site.until.RequestUtils;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-09-17T08:37:25.285Z")

@Controller
public class TeachingstatusstatisticApiController implements TeachingstatusstatisticApi {

    private static final Logger LOG = LoggerFactory.getLogger(TeachingstatusstatisticApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private TeachingStatusStatisticService teachingStatusStatisticService;

    @org.springframework.beans.factory.annotation.Autowired
    public TeachingstatusstatisticApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Integer> getAllCollegeNum(
            @NotNull @ApiParam(value = "加密验证码: &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Integer allCollegeNum = teachingStatusStatisticService.getAllCollegeNum();
            return new ResponseEntity<Integer>(allCollegeNum, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getAllCollegeNum -->");
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getAllCourseNum(
            @NotNull @ApiParam(value = "加密验证码: &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Integer allCourseNum = teachingStatusStatisticService.getAllCourseNum();
            return new ResponseEntity<Integer>(allCourseNum, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCurrentUsingClassroom -->");
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getCurrentInClassStudentNum(
            @NotNull @ApiParam(value = "加密验证码: &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Integer currentInClassStudemtNum = teachingStatusStatisticService.getCurrentInClassStudentNum();
            return new ResponseEntity<Integer>(currentInClassStudemtNum, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCurrentInClassStudemtNum -->");
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getCurrentInClassTeacherNum(
            @NotNull @ApiParam(value = "加密验证码: &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Integer currentInClassStudemtNum = teachingStatusStatisticService.getCurrentInClassTeacherNum();
            return new ResponseEntity<Integer>(currentInClassStudemtNum, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCurrentInClassTeacherNum -->");
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getCurrentUsingClassroom(
            @NotNull @ApiParam(value = "加密验证码: &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Integer currentUsingClassroom = teachingStatusStatisticService.getCurrentUsingClassroom();
            return new ResponseEntity<Integer>(currentUsingClassroom, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("getCurrentUsingClassroom -->");
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.lztech.site.controllers.api.myuniversity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.myuniversity.MyUniversityService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.myuniversity.CourseTableIdVo;
import com.lztech.site.viewmodel.myuniversity.MyUniversityAttendClassStatisticsInfoVo;
import com.lztech.site.viewmodel.myuniversity.MyUniversityHighestScoreCourseInfoInfoVo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-02T08:51:14.631Z")

@Controller
public class MyuniversityApiController implements MyuniversityApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyuniversityApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private MyUniversityService myUniversityService;

    @org.springframework.beans.factory.annotation.Autowired
    public MyuniversityApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<MyUniversityHighestScoreCourseInfoInfoVo> getMyUniversityHighestScoreCourseInfos(
            @NotNull @ApiParam(value = "学生学号", required = true)
            @Valid @RequestParam(value = "studentNo", required = true) String studentNo,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode,studentNo)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        try {
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            MyUniversityHighestScoreCourseInfoInfoVo highestScoreCourseInfoInfoVo =
                    myUniversityService.getMyUniversityHighestScoreCourseInfos(studentNo);
            if (ObjectUtils.isEmpty(highestScoreCourseInfoInfoVo)) {
                return new ResponseEntity(ErrorResult.dataNotExistError("最高分数课程"), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(highestScoreCourseInfoInfoVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getMyUniversityHighestScoreCourseInfos->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<MyUniversityAttendClassStatisticsInfoVo> getMyUniversityAttendClassStatisticsInfos(
            @NotNull @ApiParam(value = "学生id", required = true)
            @Valid @RequestParam(value = "studentId", required = true) String studentId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(validCode,studentId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        try {
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            MyUniversityAttendClassStatisticsInfoVo statisticsInfoVo =
                    myUniversityService.getMyUniversityAttendClassStatisticsInfos(studentId);
            if (ObjectUtils.isEmpty(statisticsInfoVo)) {
                return new ResponseEntity(ErrorResult.dataNotExistError("第一堂课程、总课程数、总学时"), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(statisticsInfoVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getMyUniversityAttendClassStatisticsInfos->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableIdVo>> getStudentIdByCourseTableIds(
            @ApiParam(value = "学生id",required=true) @PathVariable("studentId") String studentId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {
        if (StringUtils.isAnyBlank(validCode,studentId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        try {
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<CourseTableIdVo> courseTableIdVoList = myUniversityService.getStudentIdByCourseTableIds(studentId);
            return new ResponseEntity(courseTableIdVoList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getStudentIdByCourseTableIds->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

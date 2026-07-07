package com.lztech.site.controllers.api.student;

import com.lztech.site.resource.coursetabledetail.WeekCourseTableDetailPage;
import com.lztech.site.service.studentcoursetable.StudentCourseTableDetailService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseTableDetailBaseResource;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseTableDetailParam;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-04-29T14:36:17.223+08:00")

@Controller
public class StudentCourseTableDetailApiController implements StudentCourseTableDetailApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentCourseTableDetailApiController.class);

    @Autowired
    private StudentCourseTableDetailService studentCourseTableDetailService;


    public ResponseEntity<List<StudentCourseTableDetailBaseResource>> getStudentCourseTableDetail(
            @ApiParam(value = "学生Id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "加密验证码id={}&signKey=123123", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "请求参数", required = true) @Valid @RequestBody StudentCourseTableDetailParam param) {
        if (StringUtils.isAnyBlank(id, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }
        try {
            List<StudentCourseTableDetailBaseResource> studentCourseTableDetailBaseResourceList =
                    studentCourseTableDetailService.getStudentCourseTableDetail(id, param);
            return new ResponseEntity<>(studentCourseTableDetailBaseResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getStudentCourseTableDetail:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<WeekCourseTableDetailPage> getStudentCourseTableDetailWeekDetailInfo(
            @ApiParam(value = "学生Id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "加密验证码id={}&signKey=123123", required = true) @Valid
            @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "上课日期", required = true) @Valid @RequestParam(value = "courseDate", required = true)
            String courseDate,
            @NotNull @ApiParam(value = "学年", required = true) @Valid @RequestParam(value = "schoolYear", required = true)
            String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true) @Valid @RequestParam(value = "term", required = true)
            Integer term) {
        if (StringUtils.isAnyBlank(id, validCode, schoolYear) || Objects.isNull(term)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }
        try {
            WeekCourseTableDetailPage weekCourseTableDetailPage =
                    studentCourseTableDetailService.getStudentCourseTableDetailWeekDetailInfo(id, courseDate, schoolYear, term);
            return new ResponseEntity<>(weekCourseTableDetailPage, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getStudentCourseTableDetailWeekDetailInfo:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

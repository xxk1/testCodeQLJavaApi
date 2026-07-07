package com.lztech.site.controllers.api.student;

import com.lztech.site.service.studentcoursetable.StudentCourseTableService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseTableResource;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-11-15T08:46:21.704Z")

@Controller
public class StudentApiController implements StudentApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentApiController.class);

    @Autowired
    private StudentCourseTableService studentCourseTableService;

    public ResponseEntity<List<StudentCourseTableResource>> getStudentNowTimeCourseTable(@NotNull
                                                                                   @ApiParam(value = "验证码（id={}&signKey=123123）",
                                                                                           required = true)
                                                                                   @Valid @RequestParam(value = "validCode", required = true)
                                                                                           String validCode,
                                                                                   @ApiParam(value = "学生id", required = true) @PathVariable("id")
                                                                                           String id) {


        if (StringUtils.isAnyBlank(id, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }

        try {

            List<StudentCourseTableResource> studentCourseTableResourceList = studentCourseTableService.getStudentNowTimeCourseTable(id);
            if (Objects.isNull(studentCourseTableResourceList)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(studentCourseTableResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getStudentNowTimeCourseTable->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

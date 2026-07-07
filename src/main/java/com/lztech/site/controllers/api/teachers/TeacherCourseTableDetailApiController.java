package com.lztech.site.controllers.api.teachers;

import com.lztech.site.service.coursetabledetail.TeacherCourseTableDetailService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursetabledetail.TeacherCourseTableDetailBaseResource;
import com.lztech.site.viewmodel.coursetabledetail.TeacherCourseTableDetailParam;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
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

@Controller
public class TeacherCourseTableDetailApiController implements TeacherCourseTableDetailApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherCourseTableDetailApiController.class);

    @Autowired
    private TeacherCourseTableDetailService teacherCourseTableDetailService;

    public ResponseEntity<List<TeacherCourseTableDetailBaseResource>> getTeacherCourseTableDetail(
            @ApiParam(value = "教师Id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "加密验证码id={}&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "请求参数", required = true) @Valid @RequestBody TeacherCourseTableDetailParam param) {
        if (StringUtils.isAnyBlank(id, validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }

        try {
            List<TeacherCourseTableDetailBaseResource> teacherCourseTableDetailBaseResourceList =
                    teacherCourseTableDetailService.getTeacherCourseTableDetail(id, param);
            return new ResponseEntity<>(teacherCourseTableDetailBaseResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getTeacherCourseTableDetail:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

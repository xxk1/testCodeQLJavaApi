package com.lztech.site.controllers.api.coursetabledetailteacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.coursetabledetailteacher.CourseTableDetailTeacherService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursetabledetailteacher.AutonomousClassTeacher;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-24T07:27:55.359Z")

@Controller
public class AutonomousClassTeacherApiController implements AutonomousClassTeacherApi {

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    private Logger log = LoggerFactory.getLogger(AutonomousClassTeacherApiController.class);
    @Autowired
    private CourseTableDetailTeacherService courseTableDetailTeacherService;

    @org.springframework.beans.factory.annotation.Autowired
    public AutonomousClassTeacherApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> autonomousclassteacherAddteacherPost(
            @ApiParam(value = "", required = true)
            @Valid @RequestBody List<AutonomousClassTeacher> addTeacherList,
            @NotNull @ApiParam(value = "主课表id", required = true)
            @Valid @RequestParam(value = "courseTableId", required = true) String courseTableId,
            @NotNull @ApiParam(value = "操作人Id", required = true)
            @Valid @RequestParam(value = "operatorId", required = true) String operatorId,
            @NotNull @ApiParam(value = "操作人名称", required = true)
            @Valid @RequestParam(value = "operatorName", required = true) String operatorName,
            @NotNull @ApiParam(value = "courseTableId=XX&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {

            if (StringUtils.isAnyBlank(validCode, courseTableId, operatorId, operatorName) || addTeacherList == null
                    || addTeacherList.size() == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            boolean falg = addTeacherList.stream().anyMatch(teacher -> StringUtils
                    .isAnyBlank(teacher.getTeacherId(), teacher.getTeacherNo()
                            , teacher.getTeacherName()));

            if (falg) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkValidCode("courseTableId", courseTableId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return courseTableDetailTeacherService.autonomousClassTeacherAddTeacherPost(addTeacherList, courseTableId, operatorId, operatorName);
        } catch (Exception e) {
            log.error("autonomousClassTeacherAddteacherPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> autonomousclassteacherDeleteteacherDelete(
            @ApiParam(value = "", required = true)
            @Valid @RequestBody List<AutonomousClassTeacher> deleteTeacherIdList,
            @NotNull @ApiParam(value = "主课表id", required = true)
            @Valid @RequestParam(value = "courseTableId", required = true) String courseTableId,
            @NotNull @ApiParam(value = "操作人Id", required = true)
            @Valid @RequestParam(value = "operatorId", required = true) String operatorId,
            @NotNull @ApiParam(value = "操作人名称", required = true)
            @Valid @RequestParam(value = "operatorName", required = true) String operatorName,
            @NotNull @ApiParam(value = "courseTableId=XX&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {

            if (StringUtils.isAnyBlank(validCode, courseTableId, operatorId, operatorName) || deleteTeacherIdList == null
                    || deleteTeacherIdList.size() == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            boolean falg = deleteTeacherIdList.stream().anyMatch(teacher -> StringUtils
                    .isAnyBlank(teacher.getTeacherId()));

            if (falg) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            boolean containYourself = deleteTeacherIdList.stream().anyMatch(autonomousClassTeacher
                    -> autonomousClassTeacher.getTeacherId().equals(operatorId));

            if (containYourself) {
                return new ResponseEntity(ErrorResult.customError("删除人不能包含自己"), HttpStatus.CONFLICT);
            }

            if (!ValidUtils.checkValidCode("courseTableId", courseTableId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return courseTableDetailTeacherService.autonomousClassTeacherDeleteTeacherDelete(deleteTeacherIdList, courseTableId);
        } catch (Exception e) {
            log.error("autonomousclassteacherDeleteteacherDelete:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

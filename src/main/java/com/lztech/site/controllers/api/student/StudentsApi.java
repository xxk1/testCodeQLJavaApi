package com.lztech.site.controllers.api.student;

import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseAndCourseTableId;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseNameFirstSpell;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-06-23T11:52:08.788+08:00")

@Api(value = "students", description = "the students API")
@RequestMapping(value = "/api/v1")
public interface StudentsApi {

    @ApiOperation(value = "获取学生课程和开课列表信息", nickname = "getCourseAndCourseTableIdsByStudentId",
            notes = "使用方：云课堂学生端AI伴学-一站式搜索", response = StudentCourseAndCourseTableId.class, responseContainer = "List", tags={ "Students", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = StudentCourseAndCourseTableId.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证信息错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常") })
    @RequestMapping(value = "/students/courseandcoursetableids",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<StudentCourseNameFirstSpell>> getCourseAndCourseTableIdsByStudentId(
            @NotNull @ApiParam(value = "学生Id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "openId") @Valid @RequestParam(value = "openId", required = false) String openId,
            @ApiParam(value = "学期") @Valid @RequestParam(value = "term", required = false) Integer term,
            @ApiParam(value = "学年") @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear);


}

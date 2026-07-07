package com.lztech.site.controllers.api.teachers;

import com.lztech.site.viewmodel.coursetabledetail.TeacherCourseTableDetailBaseResource;
import com.lztech.site.viewmodel.coursetabledetail.TeacherCourseTableDetailParam;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api(value = "teachers", description = "the teachers API")
@RequestMapping(value = "/api/v1")
public interface TeacherCourseTableDetailApi {

    @ApiOperation(value = "获取教师课表详情", nickname = "getTeacherCourseTableDetail", notes = "教师端使用",
            response = TeacherCourseTableDetailBaseResource.class, responseContainer = "List",
            tags = {"TeacherCourseTableDetail",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = TeacherCourseTableDetailBaseResource.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class)})
    @RequestMapping(value = "/teacher/{id}/courseTableDetail",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<List<TeacherCourseTableDetailBaseResource>> getTeacherCourseTableDetail(
            @ApiParam(value = "教师Id", required = true) @PathVariable("id") String id,
            @NotNull @ApiParam(value = "加密验证码id={}&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "请求参数", required = true) @Valid @RequestBody TeacherCourseTableDetailParam param);
}

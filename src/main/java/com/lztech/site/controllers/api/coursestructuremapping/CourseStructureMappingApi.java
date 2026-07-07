package com.lztech.site.controllers.api.coursestructuremapping;

import com.lztech.site.viewmodel.coursestructuremapping.CourseStructureMappingDetailResource;
import com.lztech.site.viewmodel.coursestructuremapping.CourseStructureMappingParam;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-01T15:05:53.522+08:00")


@Api(value = "CourseStructureMapping", description = "the CourseStructureMapping API")
@RequestMapping(value = "/api/v1")
public interface CourseStructureMappingApi {


    @ApiOperation(value = "课程章节对应相关接口", nickname = "createCourseStructureMapping",
            notes = "1.云课堂教师端使用", tags = {"CourseStructureMapping",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/course/structure/mapping",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> createCourseStructureMapping(@ApiParam(value = "课程章节对应关系", required = true)
                                                      @Valid @RequestBody
                                                      CourseStructureMappingParam param,
                                                      @NotNull @ApiParam(value = "验证码 &signKey=123123",
                                                              required = true)
                                                      @Valid @RequestParam(value = "validCode",
                                                              required = true)
                                                      String validCode);


    @ApiOperation(value = "课程章节对应关系列表", nickname = "getCourseStructureMappingList",
            notes = "1.云课堂教师端使用", response = CourseStructureMappingParam.class,
            responseContainer = "List", tags = {"CourseStructureMapping",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = CourseStructureMappingDetailResource.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/course/structure/mapping/list",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<CourseStructureMappingDetailResource>> getCourseStructureMappingList(
            @NotNull @ApiParam(value = "课程id", required = true) @Valid
            @RequestParam(value = "courseId", required = true)
            String courseId,
            @NotNull @ApiParam(value = "版本id", required = true)
            @Valid @RequestParam(value = "versionId", required = true)
            String versionId,
            @NotNull @ApiParam(value = "验证码 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode);


}

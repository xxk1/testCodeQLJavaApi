package com.lztech.site.controllers.api.coursebag;

import com.lztech.site.resource.coursebag.CoursePackageListVo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-20T03:08:00.445Z")

@Validated
@Api(value = "coursebag", description = "the coursebag API")
@RequestMapping(value = "/v1")
public interface CoursebagApi {

    @ApiOperation(value = "课程包导出", nickname = "exportCourseBag", notes = "1.云课堂教师端-课程建设使用", tags={ "CourseBagExport", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
        @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
        @ApiResponse(code = 404, message = "未找到资源信息", response = ErrorResult.class),
        @ApiResponse(code = 409, message = "业务校验数据错误", response = ErrorResult.class),
        @ApiResponse(code = 500, message = "服务器异常") })
    @RequestMapping(value = "/coursebag/export",
        method = RequestMethod.POST)
    ResponseEntity<Void> exportCourseBag(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本id", required = true)
            @Valid @RequestParam(value = "versionId", required = true) String versionId,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId);


    @ApiOperation(value = "课程包导入", nickname = "importCourseBag", notes = "1.云课堂教师端-课程建设使用", tags={ "CourseBagExport", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到资源信息", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务校验数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常") })
    @RequestMapping(value = "/coursebag/import",
            produces = { "application/json" },
            consumes = { "multipart/form-data" },
            method = RequestMethod.POST)
    ResponseEntity<Void> importCourseBag(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程包信息（只支持zip文件）") @Valid @RequestPart(value="courseBag", required=false) MultipartFile courseBag,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName,
            @NotNull @ApiParam(value = "上传类型(0:上传课程包，1:生成课程)", required = true)
            @Valid @RequestParam(value = "uploadType", required = true) String uploadType,
            @ApiParam(value = "版本ID")
            @Valid @RequestParam(value = "versionId", required = false) String versionId);

    @ApiOperation(value = "课程包打包列表", nickname = "packCourseBag",
            notes = "1.云课堂教师端-课程建设使用", response = CoursePackageListVo.class,
            responseContainer = "List", tags={ "CourseBagExport", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = CoursePackageListVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到资源信息", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务校验数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常") })
        @RequestMapping(value = "/coursebag/list",
            method = RequestMethod.GET)
    ResponseEntity<List<CoursePackageListVo>> packCourseBag(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId);

    @ApiOperation(value = "课程包生成导出记录", nickname = "recordCourseBag",
            notes = "1.云课堂教师端-课程建设-导出使用", tags={ "CourseBagExport", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务校验数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常") })
    @RequestMapping(value = "/coursebag/list/record",
            method = RequestMethod.GET)
    ResponseEntity<Void> recordCourseBag(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本id", required = true)
            @Valid @RequestParam(value = "versionId", required = true) String versionId,
            @NotNull @ApiParam(value = "用户ID", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName);
}

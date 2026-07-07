package com.lztech.site.controllers.api.courseconstruction;

import com.lztech.site.viewmodel.courseconstruction.CourseTeachingMaterialFileInfo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-21T16:36:23.222+08:00")

@Api(value = "course", description = "the course API")
@RequestMapping("/api/v1")
public interface CourseTeachingMaterialApi {

    @ApiOperation(value = "上传课程教材", nickname = "uploadTeachingMaterial", notes = "1.云课堂教师端-课程建设使用", tags = {"CourseTeachingMaterial",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "上传成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到资源信息", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/course/teachingMaterial/upload",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> uploadTeachingMaterial(
            @ApiParam(value = "上传文件信息", required = true) @Valid @RequestBody
            CourseTeachingMaterialFileInfo fileInfo,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true)
            String validCode);

}

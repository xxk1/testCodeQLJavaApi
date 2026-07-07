package com.lztech.site.controllers.api.preparelessonplugin;

import com.lztech.site.viewmodel.preparelessonplugin.CheckResourceResult;
import com.lztech.site.viewmodel.preparelessonplugin.TeachingCourseInfo;
import com.lztech.site.viewmodel.preparelessonplugin.PptTeachingContentResource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-12T07:07:37.075Z")

@Validated

@Api(value = "PrepareLessonPlugInApi", description = "the preparelessonPlugin API")
@RequestMapping(value = "/v1")
public interface PrepareLessonPlugInApi {

    @ApiOperation(value = "保存老师课件信息到备课包", nickname = "savePptFileInfo", notes = "备课版PPT插件使用",
            tags={ "PrepareLessonPlugin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数异常，接受传入的参数类型不正确、必填参数未填写"),
            @ApiResponse(code = 403, message = "传入的加密参数未通过验证"),
            @ApiResponse(code = 404, message = "资源路径参数对应的资源未找到") })
    @RequestMapping(value = "/preparelesson/savepptfileinfo",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Void> savePptFileInfo(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true) @Valid
            @RequestParam(value = "validCode", required = true)
                    String validCode,
            @ApiParam(value = "ppt上传备课包信息" ,required=true )  @Valid @RequestBody
                    PptTeachingContentResource pptTeachingContentResource);

    @ApiOperation(value = "获取老师教学团队下课程，结构", nickname = "getCourseStructures", notes = "备课版PPT插件使用",
            response = TeachingCourseInfo.class, responseContainer = "List", tags={ "PrepareLessonPlugin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TeachingCourseInfo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数异常，接受传入的参数类型不正确、必填参数未填写"),
            @ApiResponse(code = 403, message = "传入的加密参数未通过验证"),
            @ApiResponse(code = 404, message = "未找到课程结构") })
    @RequestMapping(value = "/preparelesson/getcoursestructures",
            method = RequestMethod.GET)
    ResponseEntity<List<TeachingCourseInfo>> getCourseStructures(
            @NotNull @ApiParam(value = "用户id", required = true) @Valid
            @RequestParam(value = "userId", required = true)
                    String userId,
            @NotNull @ApiParam(value = "验证码userId=&signKey=123123(md5加密)", required = true) @Valid
            @RequestParam(value = "validCode", required = true)
                    String validCode);

    @ApiOperation(value = "检查课程结构下该老师是否存在重名的PPT（私有）", nickname = "checkSameNameResource",
            notes = "备课版PPT插件使用", response = CheckResourceResult.class, tags={ "PrepareLessonPlugin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CheckResourceResult.class),
            @ApiResponse(code = 400, message = "参数异常，接受传入的参数类型不正确、必填参数未填写"),
            @ApiResponse(code = 403, message = "传入的加密参数未通过验证"),
            @ApiResponse(code = 404, message = "课程结构未找到") })
    @RequestMapping(value = "/preparelesson/checksamenameresource",
            method = RequestMethod.GET)
    ResponseEntity<CheckResourceResult> checkSameNameResource(
            @NotNull @ApiParam(value = "用户Id", required = true) @Valid
            @RequestParam(value = "userId", required = true)
                    String userId,
            @NotNull @ApiParam(value = "文件名称", required = true) @Valid
            @RequestParam(value = "fileRealName", required = true)
                    String fileRealName,
            @NotNull @ApiParam(value = "文件类型", required = true) @Valid
            @RequestParam(value = "fileType", required = true)
                    String fileType,
            @NotNull @ApiParam(value = "课程结构Id", required = true) @Valid
            @RequestParam(value = "courseStructureId", required = true)
                    String courseStructureId,
            @NotNull @ApiParam(value = "验证码userId=&signKey=123123(md5加密)", required = true) @Valid
            @RequestParam(value = "validCode", required = true)
                    String validCode);
}

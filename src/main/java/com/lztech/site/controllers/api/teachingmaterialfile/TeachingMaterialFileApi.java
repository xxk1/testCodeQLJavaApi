package com.lztech.site.controllers.api.teachingmaterialfile;

import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingmaterialfile.TeachingMaterialFileResource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-17T03:10:25.361Z")

@Api(value = "TeachingMaterialFile", description = "the TeachingMaterialFile API")
@RequestMapping(value = "/v1")
public interface TeachingMaterialFileApi {

    @ApiOperation(value = "接口-根据courseId获取课程材料及材料内容列表数据", nickname = "getTeachingMaterialFileResourceList",
            notes = "调用方：1、老师端",
            response = TeachingMaterialFileResource.class, responseContainer = "List", tags = {"TeachingMaterialFile",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = TeachingMaterialFileResource.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "解密验证失败", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "数据不存在", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常" )})
    @RequestMapping(value = "/teachingmaterialfile/detaillist",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<TeachingMaterialFileResource>> getTeachingMaterialFileResourceList(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    );

}

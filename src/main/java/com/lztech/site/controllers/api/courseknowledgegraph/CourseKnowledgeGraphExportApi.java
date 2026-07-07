package com.lztech.site.controllers.api.courseknowledgegraph;


import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RequestMapping(value = "/api/v1")
public interface CourseKnowledgeGraphExportApi {


    @ApiOperation(value = "接口-导出课程知识图谱", nickname = "exportCourseKnowledgeGraphToExcel", notes = "调用方：1、老师端-知识图谱", tags={ "CourseKnowledgeGraph", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到资源信息", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常") })
    @RequestMapping(value = "/courseknowledgegraph/export/toexcel",
            method = RequestMethod.POST)
    ResponseEntity<Void> exportCourseKnowledgeGraphToExcel(
            @NotNull @ApiParam(value = "课程id", required = true) @Valid
            @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码：courseId=xxx&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode, HttpServletResponse response);

}

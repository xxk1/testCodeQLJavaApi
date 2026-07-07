package com.lztech.site.controllers.api.classfirstcoursetable;

import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.firstcoursetable.ClassFirstCourseTableInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-26T09:37:56.824Z")

@Validated
@Api(value = "coursetable", description = "the coursetable API")
@RequestMapping(value = "/v1")
public interface ClassFirstCourseTableApi {


    @ApiOperation(value = "获取班级开学第一课课程课表信息", nickname = "getClassTermFirstCourseTable",
            notes = "云课堂服务-同步班级开学第一课信息", response = ClassFirstCourseTableInfo.class,
            responseContainer = "List", tags = {"CourseTableDetail",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ClassFirstCourseTableInfo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到此班级方案", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/coursetable/firstcoursetabledetail",
            method = RequestMethod.GET)
    ResponseEntity<List<ClassFirstCourseTableInfo>> getClassTermFirstCourseTable(@NotNull
                                                                                 @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                                                 @Valid @RequestParam(value = "validCode", required = true)
                                                                                         String validCode);

}

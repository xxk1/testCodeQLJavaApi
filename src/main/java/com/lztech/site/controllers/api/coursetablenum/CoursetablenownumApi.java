package com.lztech.site.controllers.api.coursetablenum;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-16T13:14:38.739Z")

@Validated
@Api(value = "coursetablenownum", description = "the coursetablenownum API")
@RequestMapping(value = "/v1")
public interface CoursetablenownumApi {

    @ApiOperation(value = "获取开课课程数数量", nickname = "coursetablenownumGet", notes = "矿大门户数量统计使用", tags = {"CourseTableNowNum",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确"),
            @ApiResponse(code = 403, message = "解密验证失败"),
            @ApiResponse(code = 404, message = "未找到数量信息"),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/coursetablenownum",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Integer> coursetablenownumGet(
            @ApiParam(value = "学年")
            @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @ApiParam(value = "学期")
            @Valid @RequestParam(value = "term" +
                    "", required = false) Integer term,
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
                                                 @Valid @RequestParam(value = "validCode", required = true)
                                                         String validCode);

}

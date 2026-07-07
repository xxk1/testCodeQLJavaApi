package com.lztech.site.controllers.api.project;

import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.project.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-30T15:47:49.857Z")

@Api(value = "projects", description = "the projects API")
@RequestMapping(value = "/v1")
public interface ProjectsApi {

    @ApiOperation(value = "获取所有项目", nickname = "projectsGet", notes = "云课堂web",
            response = ProjectVo.class, responseContainer = "List", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ProjectVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "客户端错误"),
            @ApiResponse(code = 500, message = "服务器异常"),
            @ApiResponse(code = 403, message = "验证码错误")})
    @RequestMapping(value = "/projects", produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectVo>> projectsGet(@NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
                                                @Valid @RequestParam(value = "validCode", required = true)
                                                String validCode);

    @ApiOperation(value = "获取所有项目(中文首字母排序)", nickname = "projectsGet", notes = "1、云课堂管理端",
            response = ProjectVo.class, responseContainer = "List", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ProjectVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "客户端错误"),
            @ApiResponse(code = 500, message = "服务器异常"),
            @ApiResponse(code = 403, message = "验证码错误")})
    @RequestMapping(value = "/initialsortprojects", produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectVo>> getInitialSortProjects(@NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
                                                @Valid @RequestParam(value = "validCode", required = true)
                                                String validCode);

    @ApiOperation(value = "根据项目id获取项目信息", nickname = "projectsProjectIdsGet", notes = "云课堂web",
            response = ProjectVo.class, responseContainer = "List", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = ProjectVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数异常"),
            @ApiResponse(code = 403, message = "验证码错误")})
    @RequestMapping(value = "/projects/projectIds",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectVo>> projectsProjectIdsGet(@NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)z`", required = true)
                                                          @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                          @NotNull @ApiParam(value = "项目id,多个用逗号隔开", required = true)
                                                          @Valid @RequestParam(value = "projectIds", required = true) String projectIds);

    //region 根据课程id获取项目信息
    @ApiOperation(value = "根据课程id获取项目信息", nickname = "getProjectsByCourseId", notes = "云课堂web",
            response = ProjectVo.class, responseContainer = "List", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = ProjectVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数异常，接受传入的参数类型不正确、必填参数未填写"),
            @ApiResponse(code = 403, message = "传入的加密参数未通过验证"),
            @ApiResponse(code = 404, message = "资源路径参数对应的资源未找到"),
            @ApiResponse(code = 500, message = "文件操作IO异常等")})
    @RequestMapping(value = "/projects/courseid",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectVo>> getProjectsByCourseId(
            @NotNull @ApiParam(value = "courseId不为空：MD5加密验证字符串(courseId={}&signKey=123123)；courseId为空：MD5加密验证字符串(&signKey=123123)",
                    required = true) @Valid
            @RequestParam(value = "validCode", required = true)
            String validCode,
            @NotNull @ApiParam(value = "课程Id", required = false) @Valid
            @RequestParam(value = "courseId", required = false)
            String courseId);
    //endregion

    @ApiOperation(value = "接口-获取项目种类", nickname = "projectsProjecttypesGet", notes = "1、云课堂管理端项目管理使用",
            response = ProjectTypeVo.class, responseContainer = "List", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ProjectTypeVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/projects/projecttypes",
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectTypeVo>> projectsProjecttypesGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode);

    @ApiOperation(value = "接口-获取项目类别", nickname = "projectsProjectcategoriesGet", notes = "1、云课堂管理端项目管理使用",
            response = ProjectCategoryVo.class, responseContainer = "List", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ProjectCategoryVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/projects/projectcategories",
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectCategoryVo>> projectsProjectcategoriesGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode);

    @ApiOperation(value = "接口-获取项目要求", nickname = "projectsProjectclaimsGet", notes = "1、云课堂管理端项目管理使用",
            response = ProjectClaimVo.class, responseContainer = "List", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ProjectClaimVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/projects/projectclaims",
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectClaimVo>> projectsProjectclaimsGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode);

    @ApiOperation(value = "接口-获取项目类型", nickname = "projectsProjectgenresGet", notes = "1、云课堂管理端项目管理使用",
            response = ProjectGenreVo.class, responseContainer = "List", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ProjectGenreVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/projects/projectgenres",
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectGenreVo>> projectsProjectgenresGet(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode);

    @ApiOperation(value = "接口-项目信息列表", nickname = "projectsPagelistPost", notes = "1、云课堂管理端项目管理使用",
            response = ProjectPageResource.class, tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ProjectPageResource.class),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/projects/pagelist",
            method = RequestMethod.POST)
    ResponseEntity<ProjectPageResource> projectsPagelistPost(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "项目信息列表查询条件", required = true)
            @Valid @RequestBody ProjectQueryParam projectQueryParam);

    @ApiOperation(value = "接口-项目批量删除", nickname = "deleteProjectList", notes = "1、云课堂管理端项目管理使用", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/projects/deletelist",
            method = RequestMethod.POST)
    ResponseEntity<Void> deleteProjectList(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "项目信息批量删除条件", required = true)
            @Valid @RequestBody ProjectDeleteParam projectDeleteParam
    );
    @ApiOperation(value = "Excel导入项目", nickname = "importProjectList", notes = "1、云课堂管理端", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确"),
            @ApiResponse(code = 401, message = "解密验证失败"),
            @ApiResponse(code = 404, message = "未找到导入的Excel信息"),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/projects/importprojectlist",
            produces = {"application/json"},
            consumes = {"multipart/form-data"},
            method = RequestMethod.POST)
    ResponseEntity<Void> importProjectList(
            @NotNull @ApiParam(value = "用户id", required = true)
            @Valid @RequestParam(value = "userId", required = true) String userId,
            @NotNull @ApiParam(value = "用户名称", required = true)
            @Valid @RequestParam(value = "userName", required = true) String userName,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "项目信息")
            @Valid @RequestPart(value = "projectFile", required = true) MultipartFile projectFile
    );

    @ApiOperation(value = "接口-新增项目", nickname = "projectsPost", notes = "1、云课堂管理端项目管理使用;2、teachingApi新增实验报告时调用",
            tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/projects",
            method = RequestMethod.POST)
    ResponseEntity<ProjectResource> projectsPost(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "新增项目信息", required = true) @Valid @RequestBody ProjectInfoResource projectInfoResource);

    @ApiOperation(value = "接口-修改项目", nickname = "projectsIdPost", notes = "1、云课堂管理端项目管理使用", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 404, message = "未找到项目信息", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务数据错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/projects/{id}",
            method = RequestMethod.POST)
    ResponseEntity<Void> projectsIdPost(
            @NotNull @ApiParam(value = "验证码id=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "项目id", required = true) @PathVariable("id") String id,
            @ApiParam(value = "修改项目信息", required = true) @Valid @RequestBody ProjectInfoResource projectInfoResource);


    @ApiOperation(value = "获取课程下的项目接口", nickname = "getCourseProjectList", notes = "1.云课堂老师端-项目量表配置使用", response = ProjectResource.class,
            responseContainer = "List", tags = {"Project",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ProjectResource.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/course/project",
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectResource>> getCourseProjectList(@NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
                                                               @Valid @RequestParam(value = "validCode", required = true)
                                                               String validCode,
                                                               @NotNull @ApiParam(value = "课程ids", required = true)
                                                               @Valid @RequestParam(value = "courseIds", required = true)
                                                               String courseIds,
                                                               @ApiParam(value = "项目名称") @Valid
                                                               @RequestParam(value = "projectName", required = false)
                                                               String projectName);


    @ApiOperation(value = "修改项目标准时长接口", nickname = "updateProjectStandardDuration",
            notes = "1.云课堂教师端-项目配置管理使用", tags = {"Project",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "参数不正确", response = ErrorResult.class),
            @ApiResponse(code = 403, message = "验证码错误", response = ErrorResult.class),
            @ApiResponse(code = 409, message = "业务校验错误信息", response = ErrorResult.class),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/project/{id}/standardduration",
            method = RequestMethod.POST)
    ResponseEntity<Void> updateProjectStandardDuration(@NotNull @ApiParam(value = "验证码：id={}&signKey=123123(md5加密)", required = true)
                                                       @Valid @RequestParam(value = "validCode", required = true)
                                                       String validCode,
                                                       @ApiParam(value = "项目id", required = true) @PathVariable("id")
                                                       String id,
                                                       @NotNull @ApiParam(value = "项目标准时长 单位：h", required = true)
                                                       @Valid @RequestParam(value = "standardDuration", required = true)
                                                       BigDecimal standardDuration,
                                                       @ApiParam(value = "修改人id") @Valid
                                                       @RequestParam(value = "modifierId", required = false)
                                                       String modifierId,
                                                       @ApiParam(value = "修改人姓名") @Valid @RequestParam(value = "modifierName", required = false)
                                                       String modifierName);

    @ApiOperation(value = "获取所有可预约项目", nickname = "getBookableprojects", notes = "云课堂web", response = ProjectVo.class,
            responseContainer = "List", tags = {"projects",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = ProjectVo.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "客户端错误"),
            @ApiResponse(code = 403, message = "验证码错误"),
            @ApiResponse(code = 500, message = "服务器异常")})
    @RequestMapping(value = "/project/bookableprojects",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<ProjectVo>> getBookableProjects(
            @NotNull @ApiParam(value = "&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "开始时间", required = true)
            @Valid @RequestParam(value = "startTime", required = true) String startTime,
            @NotNull @ApiParam(value = "结束时间", required = true)
            @Valid @RequestParam(value = "endTime", required = true) String endTime);

}

package com.lztech.site.controllers.api.courseknowledgegraph;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphResourceService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseknowledgegraph.KnowledgePointCourseResourceVo;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceVo;
import com.lztech.site.viewmodel.coursemanagement.CourseStructureVo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-24T07:25:45.794Z")

@Controller
public class CourseKnowledgeGraphResourceApiController implements CourseKnowledgeGraphResourceApi {


    private static final Logger LOGGER = LoggerFactory.getLogger(CourseKnowledgeGraphResourceApiController.class);

    @Autowired
    private CourseKnowledgeGraphResourceService courseResourcesService;

    public ResponseEntity<Void> addCourseKnowledgePointCourseResource(@NotNull @ApiParam(value = "验证码（ &signKey=123123）", required = true)
                                                                      @Valid @RequestParam(value = "validCode", required = true)
                                                                      String validCode,
                                                                      @ApiParam(value = "", required = true) @Valid
                                                                      @RequestBody KnowledgePointCourseResourceVo resourcePointVo) {

        if (StringUtils.isAnyBlank(validCode) || Objects.isNull(resourcePointVo) || StringUtils.isAnyBlank(resourcePointVo.getKnowledgePointId(),
                resourcePointVo.getUserId(), resourcePointVo.getUserName())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseResourcesService.addKnowledgePointCourseResource(resourcePointVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("addCourseResourceKnowledgePoint -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseResourceVo>> getCourseKnowledgePointCourseResourceList(@NotNull @ApiParam(value = "知识点id", required = true)
                                                                                            @Valid
                                                                                            @RequestParam(value = "knowledgePointId", required = true)
                                                                                            String knowledgePointId,
                                                                                            @NotNull @ApiParam(value = "验证码：" +
                                                                                                    "&signKey=123123(md5加密)", required = true)
                                                                                            @Valid @RequestParam(value = "validCode", required = true)
                                                                                            String validCode) {
        if (StringUtils.isAnyBlank(validCode) || StringUtils.isAnyBlank(knowledgePointId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<CourseResourceVo> resultList = courseResourcesService.getCourseKnowledgePointCourseResourceList(knowledgePointId);

            return new ResponseEntity(resultList, HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("getCourseKnowledgePointCourseResourceList -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Void> cancelCourseKnowledgePointCourseResource(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "知识点id", required = true)
            @Valid @RequestParam(value = "knowledgePointId", required = true) String knowledgePointId,
            @NotNull @ApiParam(value = "资源id", required = true)
            @Valid @RequestParam(value = "resourceId", required = true) String resourceId) {

        if (StringUtils.isAnyBlank(validCode,knowledgePointId,resourceId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            Integer count = courseResourcesService.cancelCourseKnowledgePointCourseResource(knowledgePointId,resourceId);
            if (count == 0) {
                return new ResponseEntity(ErrorResult.dataNotExistError("关联"),HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("cancelCourseKnowledgePointCourseResource -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseStructureVo>> getCourseKnowledgeGraphCanAssociationCourseResource(
            @NotNull @ApiParam(value = "课程id", required = true) @Valid
            @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课程id") @Valid
            @RequestParam(value = "versionId", required = false) String versionId,
            @ApiParam(value = "需要的用户ID，即用户ID创建的资源展示") @Valid
            @RequestParam(value = "needUserId", required = false) String needUserId,
            @ApiParam(value = "课件查询状态（null或0：没有限制，1：只查询课件）")
            @Valid @RequestParam(value = "coursewareStatus", required = false) Integer coursewareStatus
    ) {
        if (StringUtils.isAnyEmpty(validCode, courseId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            Result result = courseResourcesService.getCoursesContent(courseId, versionId,needUserId,coursewareStatus);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(result.getData(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("coursemanagementCoursescontentGet:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

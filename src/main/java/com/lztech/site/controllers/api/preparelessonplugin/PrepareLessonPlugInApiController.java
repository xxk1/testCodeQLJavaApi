package com.lztech.site.controllers.api.preparelessonplugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.course.CourseResource;
import com.lztech.domain.model.course.CourseResourceFile;
import com.lztech.domain.model.course.CourseStructure;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.site.service.preparelessonplugin.PrepareLessonPluginService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.preparelessonplugin.CheckResourceResult;
import com.lztech.site.viewmodel.preparelessonplugin.PptTeachingContentResource;
import com.lztech.site.viewmodel.preparelessonplugin.TeachingCourseInfo;
import com.lztech.site.viewmodel.preparelessonplugin.enums.ResourceIsSameName;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-12T07:07:37.075Z")

@Controller
public class PrepareLessonPlugInApiController implements PrepareLessonPlugInApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrepareLessonPlugInApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private PrepareLessonPluginService prepareLessonPluginService;

    @org.springframework.beans.factory.annotation.Autowired
    public PrepareLessonPlugInApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    //region 保存教学资源信息
    public ResponseEntity<Void> savePptFileInfo(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true) @Valid
            @RequestParam(value = "validCode", required = true)
                    String validCode,
            @ApiParam(value = "ppt上传备课包信息", required = true) @Valid @RequestBody
                    PptTeachingContentResource pptTeachingContentResource) {
        try {
            if (!this.prepareLessonPluginService.isNotEmptyPptTeachingContentResource(pptTeachingContentResource)
                    || StringUtils.isEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            ResourceType resourceType
                    = ResourceType.getResourceType(pptTeachingContentResource.getResourceType().intValue());
            if (resourceType == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            CourseStructure courseStructure
                    = this.prepareLessonPluginService.getCourseStructure(
                    pptTeachingContentResource.getCourseStructureId());
            if (StringUtils.isEmpty(courseStructure.getId())) {
                return new ResponseEntity(ErrorResult.dataNotExistError("课程结构"), HttpStatus.NOT_FOUND);
            } else {
                this.prepareLessonPluginService.createPptTeachingContentResource(pptTeachingContentResource,
                        courseStructure);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            LOGGER.error("savePptFileInfo" , e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 获取老师教学团队下课程，结构
    public ResponseEntity<List<TeachingCourseInfo>> getCourseStructures(
            @NotNull @ApiParam(value = "用户id", required = true) @Valid
            @RequestParam(value = "userId", required = true)
                    String userId,
            @NotNull @ApiParam(value = "验证码userId=&signKey=123123(md5加密)", required = true) @Valid
            @RequestParam(value = "validCode", required = true)
                    String validCode) {
        try {
            if (StringUtils.isAnyEmpty(userId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkAuthCode("userId", userId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<TeachingCourseInfo> teachingCourseInfos
                    = prepareLessonPluginService.getCourseResourceInfos(userId);
            if (teachingCourseInfos.size() > 0) {
                prepareLessonPluginService.setCourseStructuresAndResources(teachingCourseInfos);
                return new ResponseEntity(teachingCourseInfos, HttpStatus.OK);
            } else {
                return new ResponseEntity(ErrorResult.dataNotExistError("教师所属课程"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("getCourseStructures", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region 检查课程结构下该老师是否存在重名的PPT（只查私有资源）
    public ResponseEntity<CheckResourceResult> checkSameNameResource(
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
                    String validCode) {
        try {
            if (StringUtils.isAnyEmpty(userId, fileRealName, courseStructureId, fileType, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode("userId", userId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CourseStructure courseStructure
                    = this.prepareLessonPluginService.getCourseStructure(courseStructureId);
            if (StringUtils.isEmpty(courseStructure.getId())) {
                return new ResponseEntity(ErrorResult.dataNotExistError("课程结构"), HttpStatus.NOT_FOUND);
            } else {

                List<CourseResource> courseResources =
                        this.prepareLessonPluginService.getSameCourseResources(userId, fileRealName,courseStructure);
                List<CourseResourceFile> courseResourceFiles
                        = this.prepareLessonPluginService.getSameNamePpt(courseResources, userId, fileRealName,
                        fileType);
                List<CourseResourceFile> list=new ArrayList<>();
                CheckResourceResult checkResourceResult = new CheckResourceResult();
                if (courseResourceFiles.size() > 0) {
                    checkResourceResult.setHasSameNameFile(ResourceIsSameName.SAME.getIndex());
                    return new ResponseEntity(checkResourceResult, HttpStatus.OK);
                } else {
                    checkResourceResult.setHasSameNameFile(ResourceIsSameName.NOT_SAME.getIndex());
                    return new ResponseEntity(checkResourceResult, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOGGER.error("checkSameNameResource" , e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion
}


package com.lztech.site.controllers.api.classgroupingscheme;

import com.lztech.domain.model.classgroupingscheme.ClassGroupingScheme;
import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.classgroupingscheme.ClassGroupingSchemeService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.classgroupingscheme.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-23T05:58:18.497Z")

@Controller
public class ClassGroupingSchemeApiController implements ClassGroupingSchemeApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassGroupingSchemeApiController.class);

    @Autowired
    private ClassGroupingSchemeService classGroupingSchemeService;

    public ResponseEntity<List<ClassGroupingSchemeVo>> getClassGroupingSchemeList(
            @NotNull @ApiParam(value = "班级id", required = true)
            @Valid @RequestParam(value = "groupId", required = true) String groupId,
            @NotNull @ApiParam(value = "验证码：groupId={XXX}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "小组方案类型 0:普通小组；1：实验小组")
            @Valid @RequestParam(value = "schemaType", required = false) Integer schemaType) {

        if (StringUtils.isAnyBlank(validCode, groupId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode("groupId", groupId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {

            List<ClassGroupingSchemeVo> classGroupingSchemeVoList = classGroupingSchemeService
                    .getClassGroupingSchemeList(groupId, schemaType);
            return new ResponseEntity<>(classGroupingSchemeVoList, HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("getClassGroupingSchemeList->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> deleteClassGroupingScheme(
            @NotNull @ApiParam(value = "班级分组方案id", required = true)
            @Valid @RequestParam(value = "classGroupingSchemeId", required = true) String classGroupingSchemeId,
            @NotNull @ApiParam(value = "验证码：classGroupingSchemeId={xxx}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (StringUtils.isAnyBlank(validCode, classGroupingSchemeId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode("classGroupingSchemeId", classGroupingSchemeId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {

            classGroupingSchemeService.deleteClassGroupingScheme(classGroupingSchemeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("deleteClassGroupingScheme->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> insertClassGroupingScheme(@NotNull @ApiParam(value = "验证码（groupId={}&signKey=123123）", required = true)
                                                          @Valid @RequestParam(value = "validCode", required = true)
                                                                  String validCode,
                                                          @NotNull @ApiParam(value = "班级id", required = true)
                                                          @Valid @RequestParam(value = "groupId", required = true)
                                                                  String groupId,
                                                          @ApiParam(value = "", required = true) @Valid
                                                          @RequestBody ClassGroupingSchemeInsertResource schemeResource) {
        if (StringUtils.isAnyBlank(validCode, groupId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("groupId", groupId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = classGroupingSchemeService.insertClassGroupingScheme(groupId, schemeResource);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("insertClassGroupingScheme->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ClassGroupingSchemeResource> getClassGroupingSchemeDetail(@NotNull
                                                                                    @ApiParam(value = "验证码（id={}&signKey=123123）", required = true)
                                                                                    @Valid @RequestParam(value = "validCode", required = true)
                                                                                            String validCode,
                                                                                    @ApiParam(value = "班级分组方案id", required = true)
                                                                                    @PathVariable("id") String id) {
        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            ClassGroupingScheme classGroupingScheme = classGroupingSchemeService.findById(id);
            if (Objects.isNull(classGroupingScheme)) {
                return new ResponseEntity(ErrorResult.customError("未找到此班级分组方案"), HttpStatus.NOT_FOUND);
            }
            ClassGroupingSchemeResource classGroupingSchemeResource = classGroupingSchemeService.buildClassGroupingSchemeDetail(classGroupingScheme);
            return new ResponseEntity<>(classGroupingSchemeResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getClassGroupingSchemeDetail->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<GroupingSchemeAddCheckedResource> checkCanAddExperimentalGroupingScheme(@NotNull
                                                                                                  @ApiParam(value = "验证码（groupId" +
                                                                                                          "={}&signKey=123123）", required = true)
                                                                                                  @Valid
                                                                                                  @RequestParam(value = "validCode",
                                                                                                          required = true)
                                                                                                          String validCode,
                                                                                                  @NotNull @ApiParam(value = "班级id", required = true)
                                                                                                  @Valid @RequestParam(value = "groupId", required
                                                                                                          = true)
                                                                                                          String groupId) {
        if (StringUtils.isAnyBlank(validCode, groupId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("groupId", groupId, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            GroupingSchemeAddCheckedResource checkedResource = classGroupingSchemeService.checkCanAddExperimentalGroupingScheme(groupId);

            return new ResponseEntity<>(checkedResource, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("checkCanAddExperimentalGroupingScheme->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<GroupingSchemeTeacher>> getResponsibleTeacher(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "班级id(多个班级用“，”隔开)", required = true)
            @Valid @RequestParam(value = "groupIds", required = true) String groupIds,
            @NotNull @ApiParam(value = "老师名称", required = true)
            @Valid @RequestParam(value = "teacherName", required = true) String teacherName) {
        if (StringUtils.isAnyBlank(validCode, groupIds,teacherName)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            List<GroupingSchemeTeacher> groupingSchemeTeachers = classGroupingSchemeService.getResponsibleTeacher(groupIds,teacherName);
            return new ResponseEntity(groupingSchemeTeachers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getResponsibleTeacher->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ClassGroupingVO>> getResponsibleClassGroup(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "老师Id") @Valid @RequestParam(value = "teacherId", required = false) String teacherId,
            @ApiParam(value = "小组名称") @Valid @RequestParam(value = "className", required = false) String className) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            List<ClassGroupingVO> classGroupingVOS = classGroupingSchemeService.getResponsibleClassGroup(teacherId,className);
            return new ResponseEntity(classGroupingVOS, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getResponsibleClassGroup->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<GroupingSchemeStudent>> getResponsibleStudent(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "班级id(多个班级用“，”隔开)", required = true)
            @Valid @RequestParam(value = "groupIds", required = true) String groupIds,
            @ApiParam(value = "负责老师ID") @Valid @RequestParam(value = "responsibleTeacherId", required = false) String responsibleTeacherId,
            @ApiParam(value = "小组ID") @Valid @RequestParam(value = "classGroupId", required = false) String classGroupId) {
        if (StringUtils.isAnyBlank(validCode,groupIds)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<GroupingSchemeStudent> groupingSchemeStudents = classGroupingSchemeService.
                    getResponsibleStudent(groupIds,responsibleTeacherId,classGroupId);
            return new ResponseEntity(groupingSchemeStudents, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getResponsibleStudent->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

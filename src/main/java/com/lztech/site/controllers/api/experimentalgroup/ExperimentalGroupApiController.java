package com.lztech.site.controllers.api.experimentalgroup;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.experimentalgroup.ExperimentalGroupService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.experimentalgroup.ExperimentalGroupCreateResource;
import com.lztech.site.viewmodel.experimentalgroup.ExperimentalGroupMemberResource;
import com.lztech.site.viewmodel.experimentalgroup.ExperimentalGroupNum;
import com.lztech.site.viewmodel.experimentalgroup.ExperimentalGroupResource;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
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

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-01-19T10:09:54.312Z")

@Controller
public class ExperimentalGroupApiController implements ExperimentalGroupApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentalGroupApiController.class);

    @Autowired
    private ExperimentalGroupService experimentalGroupService;

    public ResponseEntity<List<ExperimentalGroupResource>> getGroupExperimentalGroupList(@NotNull
                                                                                         @ApiParam(value = "验证码（id={}&signKey=123123）",
                                                                                                 required = true)
                                                                                         @Valid @RequestParam(value = "validCode", required = true)
                                                                                                 String validCode,
                                                                                         @ApiParam(value = "班级id", required = true)
                                                                                         @PathVariable("id") String id) {
        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            List<ExperimentalGroupResource> experimentalGroupResourceList = experimentalGroupService.getGroupExperimentalGroupList(id);

            return new ResponseEntity(experimentalGroupResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getGroupExperimentalGroupList->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Void> insertOrUpdateExperimentalGroup(@NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
                                                                @Valid @RequestParam(value = "validCode", required = true)
                                                                        String validCode,
                                                                @ApiParam(value = "", required = true) @Valid
                                                                @RequestBody ExperimentalGroupCreateResource experimentalGroupCreateVo) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (Objects.isNull(experimentalGroupCreateVo) || StringUtils.isAnyBlank(experimentalGroupCreateVo.getClassId(),
                experimentalGroupCreateVo.getGroupName()) || CollectionUtils.isEmpty(experimentalGroupCreateVo.getMemberList())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = experimentalGroupService.insertOrUpdateExperimentalGroup(experimentalGroupCreateVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(String.valueOf(result.getMsg())), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("insertOrUpdateExperimentalGroup->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<List<ExperimentalGroupMemberResource>> getGroupUnassignedExperimentalGroupList(@NotNull
                                                                                                         @ApiParam(value =
                                                                                                                 "验证码（id={}&signKey=123123）",
                                                                                                                 required = true)
                                                                                                         @Valid
                                                                                                         @RequestParam(value = "validCode",
                                                                                                                 required = true)
                                                                                                                 String validCode,
                                                                                                         @ApiParam(value = "组id", required = true)
                                                                                                         @PathVariable("id")
                                                                                                                 String id) {
        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<ExperimentalGroupMemberResource> memberResourceList = experimentalGroupService.getGroupUnassignedExperimentalGroupList(id);

            return new ResponseEntity<>(memberResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getGroupUnassignedExperimentalGroupList->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteExperimentalGroup(@NotNull @ApiParam(value = "验证码（id={}&signKey=123123）", required = true)
                                                        @Valid @RequestParam(value = "validCode", required = true)
                                                                String validCode,
                                                        @ApiParam(value = "实验小组组id", required = true)
                                                        @PathVariable("id")
                                                                String id,
                                                        @NotNull @ApiParam(value = "操作人id", required = true)
                                                        @Valid @RequestParam(value = "modifierId", required = true)
                                                                String modifierId,
                                                        @NotNull @ApiParam(value = "操作人姓名", required = true)
                                                        @Valid @RequestParam(value = "modifierName", required = true)
                                                                String modifierName) {
        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            experimentalGroupService.deleteExperimentalGroup(id, modifierId, modifierName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("deleteExperimentalGroup->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<ExperimentalGroupResource> getExperimentalGroupDetail(@NotNull
                                                                                @ApiParam(value = "验证码（id={}&signKey=123123）", required = true)
                                                                                @Valid @RequestParam(value = "validCode", required = true)
                                                                                        String validCode,
                                                                                @ApiParam(value = "实验小组组id", required = true)
                                                                                @PathVariable("id")
                                                                                        String id,
                                                                                @ApiParam(value = "是否获取组员详情 默认不获取", defaultValue = "fale")
                                                                                @Valid @RequestParam(value = "isGetMemberList", required = false,
                                                                                        defaultValue = "fale")
                                                                                        Boolean isGetMemberList) {
        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            ExperimentalGroupResource experimentalGroupResource = experimentalGroupService.getExperimentalGroupDetail(id, isGetMemberList);

            return new ResponseEntity<ExperimentalGroupResource>(experimentalGroupResource, HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("getExperimentalGroupDetail->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ExperimentalGroupNum> getGroupExperimentalGroupNum(@NotNull
                                                                             @ApiParam(value = "验证码（id={}&signKey=123123）", required = true)
                                                                             @Valid @RequestParam(value = "validCode", required = true)
                                                                                     String validCode,
                                                                             @ApiParam(value = "组id", required = true) @PathVariable("id")
                                                                                     String id) {

        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkValidCode("id", id, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            ExperimentalGroupNum experimentalGroupNum = experimentalGroupService.getGroupExperimentalGroupNum(id);
            return new ResponseEntity<>(experimentalGroupNum, HttpStatus.OK);
        } catch (Exception e) {

            LOGGER.error("getGroupExperimentalGroupNum->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

package com.lztech.site.controllers.api.groupcard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.groupcard.GroupCardService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.group.GroupCardData;
import com.lztech.site.viewmodel.group.GroupMemberVo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-17T08:00:47.801Z")

@Controller
public class GroupcardApiController implements GroupcardApi {

    private final Logger log = LoggerFactory.getLogger(GroupcardApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Value("${signKey}")
    private String signKey;
    @Autowired
    private GroupCardService groupCardService;

    @org.springframework.beans.factory.annotation.Autowired
    public GroupcardApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> groupcardPost(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学生信息") @Valid @RequestBody GroupCardData groupCardData) {

        try {
            if (StringUtils.isAnyEmpty(groupCardData.getGroupId(), groupCardData.getUserId(),
                    groupCardData.getUserName(), validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return groupCardService.joinGroup(groupCardData);
        } catch (Exception e) {
            log.error("groupcardPost:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<GroupMemberVo> groupcardGroupIdStudentcountGet(
            @ApiParam(value = "班级Id", required = true)
            @PathVariable("groupId") String groupId,
            @NotNull @ApiParam(value = "验证码：groupId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(groupId, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("groupId", groupId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return groupCardService.getStudentCount(groupId);
        } catch (Exception e) {
            log.error("groupcardGroupIdStudentcountGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.lztech.site.controllers.api.teachingclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.teachingclass.TeachingClassResource;
import com.lztech.site.service.teachingclass.TeachingClassService;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-17T03:10:25.361Z")

@Controller
public class TeachingclassesApiController implements TeachingclassesApi {

    private final Logger log = LoggerFactory.getLogger(TeachingclassesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private TeachingClassService teachingClassService;

    @org.springframework.beans.factory.annotation.Autowired
    public TeachingclassesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<TeachingClassResource>> teachingclassesGet(
            @NotNull @ApiParam(value = "班级ID,多个用“,”隔开", required = true)
            @Valid @RequestParam(value = "teachingClassIds", required = true) String teachingClassIds,
            @NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(teachingClassIds, validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            List<TeachingClassResource> teachingClassResources = teachingClassService.getTeachingClassResource(teachingClassIds);
            return new ResponseEntity<List<TeachingClassResource>>(teachingClassResources, HttpStatus.OK);
        } catch (Exception e) {
            log.error("teachingclassesGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

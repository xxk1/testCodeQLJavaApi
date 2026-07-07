package com.lztech.site.controllers.api.preparation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.preparation.PreparationService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.preparation.PreparationResource;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-25T03:07:00.312Z")

@Controller
public class PreparationApiController implements PreparationApi {

    private final Logger log = LoggerFactory.getLogger(PreparationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PreparationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Value("${signKey}")
    private String signKey;
    @Autowired
    private PreparationService preparationService;

    public ResponseEntity<List<PreparationResource>> preparationGet(
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return preparationService.getPreparation(teacherId);
        } catch (Exception e) {
            log.error("preparationTeacherIdGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

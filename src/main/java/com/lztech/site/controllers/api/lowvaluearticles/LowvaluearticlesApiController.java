package com.lztech.site.controllers.api.lowvaluearticles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.lowvaluearticles.LowValueArticlesService;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.lowvaluearticles.LowValueArticlesResource;
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
import java.util.Arrays;
import java.util.List;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-26T12:20:14.639Z")

@Controller
public class LowvaluearticlesApiController implements LowvaluearticlesApi {

    private final Logger log = LoggerFactory.getLogger(LowvaluearticlesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private LowValueArticlesService lowValueArticlesService;

    @org.springframework.beans.factory.annotation.Autowired
    public LowvaluearticlesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<LowValueArticlesResource>> lowvaluearticlesGet(
            @NotNull @ApiParam(value = "项目Id,多个使用‘,’分割", required = true)
            @Valid @RequestParam(value = "appointmentProjectIds", required = true) String appointmentProjectIds,
            @NotNull @ApiParam(value = "预约人员数据", required = true)
            @Valid @RequestParam(value = "appointmentPeopleNumber", required = true) Integer appointmentPeopleNumber,
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (appointmentPeopleNumber == null || appointmentPeopleNumber == 0) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isAllEmpty(appointmentProjectIds, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<String> projectIds = Arrays.asList(appointmentProjectIds.split(","));
            return lowValueArticlesService.getProjectByIds(projectIds,appointmentPeopleNumber);
        } catch (Exception e) {
            log.error("lowvaluearticlesGet" + e.getMessage());
            return new ResponseEntity<List<LowValueArticlesResource>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

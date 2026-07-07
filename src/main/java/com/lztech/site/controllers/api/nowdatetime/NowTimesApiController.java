package com.lztech.site.controllers.api.nowdatetime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.datetime.Times;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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

@Controller
public class NowTimesApiController implements NowTimesApi {

    private final Logger log = LoggerFactory.getLogger(NowTimesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;
    @Autowired
    private TermService termService;

    @org.springframework.beans.factory.annotation.Autowired
    public NowTimesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<Times> getNowTime(@NotNull @ApiParam(value = "MD5加密验证字符串", required = true)
                                                @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Times dateTimeInfo = termService.getNowDateTime();
            return new ResponseEntity<Times>(dateTimeInfo, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<Times>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

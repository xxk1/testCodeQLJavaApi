package com.lztech.site.controllers.api.major;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.major.MajorService;
import com.lztech.site.viewmodel.major.MajorVo;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T07:09:56.790Z")

@Controller
public class MajorApiController implements MajorApi {

    private static final Logger LOG = LoggerFactory.getLogger(MajorApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Value("${signKey}")
    private String signKey;
    @Autowired
    private MajorService majorService;
    @org.springframework.beans.factory.annotation.Autowired
    public MajorApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<MajorVo> majorGet(@NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
                                            @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                            @NotNull @ApiParam(value = "当前页", required = true)
                                            @Valid @RequestParam(value = "page", required = true) Integer page,
                                            @NotNull @ApiParam(value = "每页个数", required = true)
                                            @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize, @ApiParam(value = "专业名称")
                                            @Valid @RequestParam(value = "majorName", required = false) String majorName, @ApiParam(value = "学院名称")
                                            @Valid @RequestParam(value = "collegeId", required = false) String collegeName) {

        String accept = request.getHeader("Accept");
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return    majorService.getMajorsList(majorName,collegeName,page,pageSize);

        } catch (Exception e) {
            LOG.error("groupGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

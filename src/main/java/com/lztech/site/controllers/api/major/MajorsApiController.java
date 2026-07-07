package com.lztech.site.controllers.api.major;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.major.MajorInfo;
import com.lztech.site.service.major.MajorService;
import com.lztech.site.until.RequestUtils;
import io.swagger.annotations.ApiParam;
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
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T08:30:00.298Z")

@Controller
public class MajorsApiController implements MajorsApi {

    private final Logger log = LoggerFactory.getLogger(MajorsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private MajorService majorService;

    @org.springframework.beans.factory.annotation.Autowired
    public MajorsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> majorsPost(@NotNull @ApiParam(value = "&signKey=123123", required = true)
                                           @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                           @ApiParam(value = "专业列表" ,required=true )  @Valid @RequestBody List<MajorInfo> majors) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if(majors.isEmpty()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            majorService.saveMajorList(majors);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("groupsGroupmembersPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

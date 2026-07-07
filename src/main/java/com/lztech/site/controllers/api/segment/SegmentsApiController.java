package com.lztech.site.controllers.api.segment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.segment.SegmentService;
import com.lztech.site.until.Util;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.segment.BuildIdVo;
import com.lztech.site.viewmodel.segment.BuildingVo;
import com.lztech.site.viewmodel.segment.CourseTableDetailSegment;
import com.lztech.site.viewmodel.segment.SegmentTaskResource;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T12:16:00.895Z")

@Controller
public class SegmentsApiController implements SegmentsApi {

    private final Logger logger = LoggerFactory.getLogger(SegmentsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private SegmentService segmentService;
    @Value("${studentType}")
    private Integer defaultStudentType;

    @org.springframework.beans.factory.annotation.Autowired
    public SegmentsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<SegmentTaskResource>> segmentsTasksGet(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "指定查询日期（yyyy-MM-dd）,可以为空,如果为空则查询当天的")
            @Valid @RequestParam(value = "date", required = false) String date,
            @ApiParam(value = "开课类型")
            @Valid @RequestParam(value = "studentType", required = false) Integer studentType) {
        try {
            if (StringUtils.isAnyBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (StringUtils.isAnyBlank(date)) {
                date = Util.nowDate();
            }
            return segmentService.getSegmentTaskList(date, studentType);
        } catch (Exception e) {
            logger.error("segmentsTasksGet:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailSegment>> getSegmentTaskList(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "指定查询日期（yyyy-MM-dd）,可以为空,如果为空则查询当天的")
            @Valid @RequestParam(value = "date", required = false) String date,
            @ApiParam(value = "开课类型")
            @Valid @RequestParam(value = "studentType", required = false) Integer studentType) {
        try {
            if (StringUtils.isAnyBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!ValidUtils.checkAuthCode(validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (StringUtils.isAnyBlank(date)) {
                date = Util.nowDate();
            }
            return segmentService.getSegmentTasks(date, studentType);
        } catch (Exception e) {
            logger.error("segmentsTasksGet:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<BuildingVo>> getSegmentsByBuildIds(
            @NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "楼栋列表") @Valid @RequestBody List<BuildIdVo> buildIdList) {

        if (StringUtils.isAnyBlank(validCode) || CollectionUtils.isEmpty(buildIdList)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(),HttpStatus.BAD_REQUEST);
        }
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(),HttpStatus.FORBIDDEN);
            }

            List<BuildingVo> buildingVos = segmentService.getSegmentListByBuildIdList(buildIdList);

            return new ResponseEntity<>(buildingVos,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getSegmentsByBuildIds->{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

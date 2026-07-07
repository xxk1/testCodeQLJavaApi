package com.lztech.site.controllers.api.evaluation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.coursetabledetail.CourseTableDetailLogicService;
import com.lztech.site.service.coursetabledetail.CourseTableDetailService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.evaluation.EvaluationCourseTableVo;
import com.lztech.site.viewmodel.evaluation.PolymerizeCourseInfoModel;
import com.lztech.site.viewmodel.evaluation.PolymerizeCourseInfoParam;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-09T06:47:52.911Z")

@Controller
public class EvaluationApiController implements EvaluationApi {

    private final Logger log = LoggerFactory.getLogger(EvaluationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EvaluationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private CourseTableDetailService courseTableDetailService;

    @Autowired
    private CourseTableDetailLogicService courseTableDetailLogicService;

    public ResponseEntity<List<EvaluationCourseTableVo>> evaluationCurrentcoursetableGet(
            @NotNull @ApiParam(value = "老师id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @NotNull @ApiParam(value = "课后延长时间", required = true)
            @Valid @RequestParam(value = "delayTime", required = true) String delayTime,
            @NotNull @ApiParam(value = "延时类型：0:课后延迟时间；1:当天24点前", required = true)
            @Valid @RequestParam(value = "delayType", required = true) Integer delayType,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课前提前时间") @Valid @RequestParam(value = "advanceTime", required = false) String advanceTime) {


        try {
            if (StringUtils.isAnyEmpty(teacherId,advanceTime)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return courseTableDetailService.getEvaluationCourseTable(teacherId, advanceTime,delayTime,delayType);
        } catch (Exception e) {
            log.error("teachersCurrentcoursetablesGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<PolymerizeCourseInfoModel>> getPolymerizeCourseInfoList(
            @ApiParam(value = "查询参数" ,required=true )
            @Valid @RequestBody PolymerizeCourseInfoParam polymerizeCourseInfoParam,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(polymerizeCourseInfoParam.getCollegeIds(),validCode) ||
                CollectionUtils.isEmpty(polymerizeCourseInfoParam.getTermBaseModelList())
        ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey,validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<PolymerizeCourseInfoModel> polymerizeCourseInfoModelList =
                    courseTableDetailLogicService.getPolymerizeCourseInfoList(polymerizeCourseInfoParam);
            return new ResponseEntity<>(polymerizeCourseInfoModelList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getPolymerizeCourseInfoList:" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

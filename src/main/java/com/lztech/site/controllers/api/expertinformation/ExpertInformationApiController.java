package com.lztech.site.controllers.api.expertinformation;

import com.lztech.site.service.expertinformation.ExpertInformationService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursetabledetail.LiveCourseParam;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.expertinformation.ExpertListenCurriculumPageVo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-04-15T08:04:04.064Z")

@Controller
public class ExpertInformationApiController implements ExpertInformationApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpertInformationApiController.class);

    @Resource
    private ExpertInformationService expertInformationService;


    public ResponseEntity<ExpertListenCurriculumPageVo> getExpertListenCurriculumPage(@NotNull
                                                                                      @ApiParam(value = "验证码：&signKey=123123(md5加密)",
                                                                                              required = true)
                                                                                      @Valid @RequestParam(value = "validCode", required = true)
                                                                                      String validCode,
                                                                                      @ApiParam(value = "获取正在直播课程信息入参",
                                                                                              required = true)
                                                                                      @Valid @RequestBody
                                                                                      LiveCourseParam liveCourseParam) {
        if (StringUtils.isAnyBlank(validCode) || ObjectUtils.isEmpty(liveCourseParam) || liveCourseParam.getPage() == null ||
                ObjectUtils.isEmpty(liveCourseParam.getPageSize())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            ExpertListenCurriculumPageVo pageVo = expertInformationService.getExpertListenCurriculumPage(liveCourseParam);

            return new ResponseEntity(pageVo, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getExpertListenCurriculumPage->", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

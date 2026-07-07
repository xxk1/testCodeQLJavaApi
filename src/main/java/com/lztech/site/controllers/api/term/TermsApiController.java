package com.lztech.site.controllers.api.term;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.domain.model.term.Term;
import com.lztech.domain.model.term.enums.TermType;
import com.lztech.site.resource.term.TermModel;
import com.lztech.site.resource.term.TermResource;
import com.lztech.site.resource.term.TermWeekResource;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.term.TermTypeResource;
import com.lztech.site.viewmodel.term.TermVo;
import com.lztech.site.viewmodel.term.TermWeekVo;
import com.lztech.site.viewmodel.term.WeeksResources;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-12T03:00:28.278Z")

@Controller
public class TermsApiController implements TermsApi {

    private final Logger log = LoggerFactory.getLogger(TermsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private TermService termService;

    @org.springframework.beans.factory.annotation.Autowired
    public TermsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<TermResource>> termsTermResourceGet(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "请求来源(1:老师:前2个学期、当前学期、后1个学期,0:学生:当前学期,不传:当前学期与下一个学期)",
                    required = false)
            @Valid @RequestParam(value = "source", required = false) String source) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<TermResource> termResourceList = termService.getTermResource(source);
            return new ResponseEntity<>(termResourceList,HttpStatus.OK);
        } catch (Exception e) {
            log.error("termsTermResourceGet:" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TermResource>> getFiveTermsTermResource(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            List<TermResource> termResourceList =  termService.getNowTermAndBeforFiveTerm();
            if(termResourceList.size()>0){
                return new ResponseEntity<>(termResourceList,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("getFiveTermsTermResource:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> termsPost(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学期列表", required = true) @Valid @RequestBody List<TermResource> terms) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (terms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Term> termList = termService.saveTerms(terms);
            if (termList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }

        } catch (Exception e) {
            log.error("termsPost:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TermResource> termsNowtermGet(@NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
                                                        @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Term term = termService.getNowTerm();
            if (term != null) {
                TermResource termResource = new TermResource();
                termResource.setId(term.getId());
                termResource.setSchoolYear(term.getSchoolYear());
                termResource.setTerm(String.valueOf(term.getTerm().getIndex()));
                termResource.setStartDate(DateUtils.formatDate(DateUtils.DATE, term.getStartDate()));
                termResource.setEndDate(DateUtils.formatDate(DateUtils.DATE, term.getEndDate()));
                termResource.setDescription(term.getDescription());
                termResource.setSchoolYearTermNickName(term.getNickName());
                return new ResponseEntity<TermResource>(termResource, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("termsNowtermGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TermResource>> termsTermListGet(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return termService.getTermList();
        } catch (Exception e) {
            log.error("termsTermListGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<WeeksResources> futureWeeksGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "节次id")
            @Valid @RequestParam(value = "buildingId", required = false) String buildingId) {
        try {
            if (StringUtils.isAnyBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return termService.futureWeeksGet(buildingId);
        } catch (Exception e) {
            log.error("futureWeeksGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<TermWeekResource> termWeeksGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "buildingId") @Valid @RequestParam(value = "buildingId", required = false) String buildingId) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return termService.getTermWeeks(buildingId);
        } catch (Exception e) {
            log.error("termWeeksGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<WeeksResources> futureSegmentsGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "节次id")
            @Valid @RequestParam(value = "buildingId", required = false) String buildingId) {
        try {
            if (StringUtils.isAnyBlank(validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return termService.futureSegments(buildingId);
        } catch (Exception e) {
            log.error("futureSegmentsGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<TermWeekVo>> termsWeeksGet(@NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
                                                          @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                          @ApiParam(value = "周次数")
                                                          @Valid @RequestParam(value = "weekNum", required = false) Integer week,
                                                          @ApiParam(value = "学年")
                                                          @Valid @RequestParam(value = "schoolYear", required = false) String schoolYear,
                                                          @ApiParam(value = "学期")
                                                          @Valid @RequestParam(value = "term" +
                                                                  "", required = false) Integer term) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return termService.getWeeks(week, schoolYear, term);
        } catch (Exception e) {
            log.error("termsWeeksGet ->", e);
            e.printStackTrace();
            return new ResponseEntity<List<TermWeekVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TermResource> termsNowornexttermGet(@NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
                                                              @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Term term = termService.getNowTermOrNextTerm();
            if (term != null) {
                TermResource termResource = new TermResource();
                termResource.setId(term.getId());
                termResource.setSchoolYear(term.getSchoolYear());
                termResource.setTerm(String.valueOf(term.getTerm().getIndex()));
                termResource.setStartDate(DateUtils.formatDate(DateUtils.DATE, term.getStartDate()));
                termResource.setEndDate(DateUtils.formatDate(DateUtils.DATE, term.getEndDate()));
                termResource.setDescription(term.getDescription());
                termResource.setSchoolYearTermNickName(term.getNickName());
                return new ResponseEntity<TermResource>(termResource, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("termsNowornexttermGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TermResource>> termsNowandlasttermGet(@NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
                                                                     @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return termService.getNowTermAndLastTerm();
        } catch (Exception e) {
            log.error("termsNowAndLastTermGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TermResource>> termsNowandlastthreetermGet(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return termService.getNowTermAndLastThreeTerm();
        } catch (Exception e) {
            log.error("termsNowandlastthreetermGet:" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TermModel>> termsGet(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<TermModel> termModelList = termService.getAllTermList();
            return new ResponseEntity(termModelList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("termsGet -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TermResource> getTerm(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "学年", required = true)
            @Valid @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @NotNull @ApiParam(value = "学期", required = true)
            @Valid @RequestParam(value = "term", required = true) String term) {

        try {
            if (StringUtils.isAnyEmpty(schoolYear,term, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            TermResource termResource = termService.getTerm(schoolYear,term);
            return new ResponseEntity(termResource, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getTerm -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<TermVo>> getRecentTerm(@NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
                                                      @Valid @RequestParam(value = "validCode", required = true)
                                                              String validCode,
                                                      @ApiParam(value = "之前学期数量 查询当前日期所在学期之前的学期数量 为null时则为0")
                                                      @Valid @RequestParam(value = "previousTermNum", required = false)
                                                              Integer previousTermNum,
                                                      @ApiParam(value = "之后学期数量 查询当前日期所在学期之后的学期数量 为null时则为0")
                                                      @Valid @RequestParam(value = "futureTermNum", required = false)
                                                              Integer futureTermNum) {
        if (StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            List<TermVo> termVoList = termService.getRecentTerm(previousTermNum, futureTermNum);
            return new ResponseEntity<>(termVoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getRecentTerm -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Void> termPost(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学期参数", required = true) @Valid @RequestBody TermResource termResource) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            Term term =
                    termService.getTermsBySchoolYearAndTerm(termResource.getSchoolYear(),
                            TermType.getTermType(Integer.valueOf(termResource.getTerm())));
            if (StringUtils.isEmpty(termResource.getId()) && !ObjectUtils.isEmpty(term)) {
                return new ResponseEntity(ErrorResult.customError("当前学期已存在，请修改后再提交"), HttpStatus.CONFLICT);
            }
            if (!ObjectUtils.isEmpty(term)&&StringUtils.isNotEmpty(termResource.getId())
                    &&!termResource.getId().equals(term.getId())) {
                return new ResponseEntity(ErrorResult.customError("该学年学期已存在学期管理列表中"), HttpStatus.CONFLICT);
            }
            List<Term> termList = termService.getStartTimeAndEndTimeTerm(termResource.getStartDate(), termResource.getEndDate());
            if (ObjectUtils.isNotEmpty(termResource.getId())) {
                termList = termList.stream()
                        .filter(term1 -> !term1.getId().equals(termResource.getId())).collect(Collectors.toList());
            }
            if (CollectionUtils.isNotEmpty(termList)) {
                return new ResponseEntity(ErrorResult.customError("当前时间段已存在学年学期，请修改后再提交"), HttpStatus.CONFLICT);
            }
            term = termService.saveAndUpdateTerm(termResource);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("termPost -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TermTypeResource>> termsTermtypesGet(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<TermTypeResource> termTypeResources = termService.getTermTypes();
            return new ResponseEntity<>(termTypeResources, HttpStatus.OK);
        } catch (Exception e) {
            log.error("termsTermtypesGet -> {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


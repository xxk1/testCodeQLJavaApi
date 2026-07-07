package com.lztech.site.controllers.api.classresource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.classresource.AdministrativeClassResource;
import com.lztech.site.resource.classresource.ClassSortResource;
import com.lztech.site.resource.classresource.ClassTeacher;
import com.lztech.site.resource.group.ClassYear;
import com.lztech.site.resource.major.MajorResource;
import com.lztech.site.service.classresource.ClassResourceService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.until.ValidUtils;
import io.swagger.annotations.ApiParam;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-10T09:11:00.019Z")

@Controller
public class ClassApiController implements ClassApi {

    private final Logger log = LoggerFactory.getLogger(ClassApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private ClassResourceService classResourceService;

    @org.springframework.beans.factory.annotation.Autowired
    public ClassApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> classPost(@ApiParam(value = "", required = true)
                                          @Valid @RequestBody ClassTeacher classTeacher) {
        try {
            if (StringUtils.isAnyEmpty(classTeacher.getUserId(), classTeacher.getUserNo(), classTeacher.getCourseId(), classTeacher.getCourseName()
                    , classTeacher.getSchoolYear()) || classTeacher.getTerm() == null
                    || classTeacher.getIsRecommend() == null || classTeacher.getClasses().size() == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!RequestUtils.checkValidCode(signKey, classTeacher.getValidCode())) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return classResourceService.classSavePost(classTeacher);
        } catch (Exception e) {
            log.error("classPost" , e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ClassYear>> classesYearGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return classResourceService.getClassYear();
        } catch (Exception e) {
            log.error("classesYearGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<MajorResource>> classesMajorGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学院id")
            @Valid @RequestParam(value = "collegeId", required = false) String collegeId,
            @ApiParam(value = "年级")
            @Valid @RequestParam(value = "year", required = false) String year) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return classResourceService.getClassMajor(year, collegeId);
        } catch (Exception e) {
            log.error("classesMajorGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<AdministrativeClassResource>> classesGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串 (&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "年级")
            @Valid @RequestParam(value = "year", required = false) String year,
            @ApiParam(value = "学院id")
            @Valid @RequestParam(value = "collegeId", required = false) String collegeId,
            @ApiParam(value = "专业id")
            @Valid @RequestParam(value = "majorId", required = false) String majorId) {
        try {
            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return classResourceService.getAdministrativeClass(year, collegeId, majorId);
        } catch (Exception e) {
            log.error("classesGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> classsortPost(@ApiParam(value = "", required = true) @Valid @RequestBody ClassSortResource classSort) {
        try {
            if (!ValidUtils.checkAuthCode(classSort.getValidCode())) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            classResourceService.changeSort(classSort);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("classsortPost->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

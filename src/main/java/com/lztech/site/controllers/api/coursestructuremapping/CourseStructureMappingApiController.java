package com.lztech.site.controllers.api.coursestructuremapping;

import com.lztech.site.service.coursestructuremapping.CourseStructureMappingService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.coursestructuremapping.CourseStructureMappingDetailResource;
import com.lztech.site.viewmodel.coursestructuremapping.CourseStructureMappingParam;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import static com.lztech.site.config.Access.signKey;

@Controller
public class CourseStructureMappingApiController implements CourseStructureMappingApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseStructureMappingApiController.class);

    @Autowired
    private CourseStructureMappingService courseStructureMappingService;

    public ResponseEntity<Void> createCourseStructureMapping(
            @ApiParam(value = "课程章节对应关系", required = true) @Valid @RequestBody
            CourseStructureMappingParam param,
            @NotNull @ApiParam(value = "验证码 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (Objects.isNull(param) ||
                StringUtils.isAnyBlank(validCode, param.getCourseId(), param.getVersionId()) ||
                CollectionUtils.isEmpty(param.getMappingList())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.UNAUTHORIZED);
        }
        try {
            courseStructureMappingService.createCourseStructureMapping(param);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("createCourseStructureMapping:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseStructureMappingDetailResource>> getCourseStructureMappingList(
            @NotNull @ApiParam(value = "课程id", required = true)
            @Valid @RequestParam(value = "courseId", required = true) String courseId,
            @NotNull @ApiParam(value = "版本id", required = true)
            @Valid @RequestParam(value = "versionId", required = true) String versionId,
            @NotNull @ApiParam(value = "验证码 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (StringUtils.isAnyBlank(courseId, versionId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!RequestUtils.checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.UNAUTHORIZED);
        }
        try {
            List<CourseStructureMappingDetailResource> courseStructureMappingParamList =
                    courseStructureMappingService.getCourseStructureMappingList(courseId, versionId);
            return new ResponseEntity<>(courseStructureMappingParamList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseStructureMappingList:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

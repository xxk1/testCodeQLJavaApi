package com.lztech.site.controllers.api.courseconstruction;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.courseconstruction.CourseMaterialService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.courseconstruction.CourseTeachingMaterialFileInfo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import io.swagger.annotations.ApiParam;
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
import java.util.Objects;


@Controller
public class CourseTeachingMaterialApiController implements CourseTeachingMaterialApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseTeachingMaterialApiController.class);

    @Autowired
    private CourseMaterialService courseTeachingMaterialService;

    public ResponseEntity<Void> uploadTeachingMaterial(
            @ApiParam(value = "上传文件信息", required = true) @Valid @RequestBody
            CourseTeachingMaterialFileInfo fileInfo,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        if (Objects.isNull(fileInfo) ||
                StringUtils.isAnyBlank(fileInfo.getCourseId(),fileInfo.getVersionId(), validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = courseTeachingMaterialService.uploadTeachingMaterial(fileInfo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(result.getMsg().toString()), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("uploadTeachingMaterial->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

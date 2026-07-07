package com.lztech.site.controllers.api.teacherpreparelessons;

import com.lztech.site.constants.Result;
import com.lztech.site.constants.ResultStatus;
import com.lztech.site.service.teacherpreparelessons.TeacherPrepareLessonsService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teacherpreparelessons.CopiedCourseResourceVo;
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
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-03-02T06:18:18.885Z")

@Controller
public class TeacherPrepareLessonsApiController implements TeacherPrepareLessonsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherPrepareLessonsApiController.class);

    @Autowired
    private TeacherPrepareLessonsService teacherPrepareLessonsService;


    public ResponseEntity<Void> copyTeacherCourseResource(@NotNull @ApiParam(value = "验证码&signKey=123123(md5加密)", required = true)
                                                          @Valid @RequestParam(value = "validCode", required = true)
                                                                  String validCode,
                                                          @ApiParam(value = "复制的教学资源信息", required = true) @Valid
                                                          @RequestBody CopiedCourseResourceVo copiedCourseResourceVo) {

        if (Objects.isNull(copiedCourseResourceVo) || StringUtils.isAnyBlank(validCode, copiedCourseResourceVo.getCourseId(),
                copiedCourseResourceVo.getOperatorId()) || CollectionUtils.isEmpty(copiedCourseResourceVo.getResourceList())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            Result result = teacherPrepareLessonsService.copyTeacherCourseResource(copiedCourseResourceVo);
            if (ResultStatus.ERROR.equals(result.getStatus())) {
                return new ResponseEntity(ErrorResult.customError(String.valueOf(result.getMsg())), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("copyTeacherCourseResource", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

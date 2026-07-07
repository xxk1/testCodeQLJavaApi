package com.lztech.site.controllers.api.coursetabledetail.imports;

import com.lztech.site.service.coursetabledetail.CourseTableDetailImportService;
import com.lztech.site.until.RequestUtils;
import com.lztech.site.viewmodel.coursetabledetail.GroupCourseTableInfo;
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

import static com.lztech.site.config.Access.signKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-02-22T05:44:45.129Z")

@Controller
public class CoursetabledetailImportApiController implements CoursetabledetailImportApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursetabledetailImportApiController.class);


    @Autowired
    private CourseTableDetailImportService courseTableDetailImportService;

    public ResponseEntity<Void> coursetabledetailImportPost(@NotNull @ApiParam(value = "加密验证码&signKey=123123", required = true)
                                                            @Valid @RequestParam(value = "validCode", required = true)
                                                                    String validCode,
                                                            @ApiParam(value = "导入课表的明细", required = true)
                                                            @Valid @RequestBody GroupCourseTableInfo groupCourseTableDetail) {

        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!RequestUtils.checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            return courseTableDetailImportService.importCourseTableDetail(groupCourseTableDetail);
        } catch (Exception e) {
            LOGGER.error("coursetabledetailImportPost:" ,e );
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

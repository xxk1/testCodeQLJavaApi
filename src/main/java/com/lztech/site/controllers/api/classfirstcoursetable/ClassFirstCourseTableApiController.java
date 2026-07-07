package com.lztech.site.controllers.api.classfirstcoursetable;

import com.lztech.site.service.classfirstcoursetable.ClassFirstCourseTableService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.firstcoursetable.ClassFirstCourseTableInfo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2021-08-26T09:37:56.824Z")

@Controller
public class ClassFirstCourseTableApiController implements ClassFirstCourseTableApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassFirstCourseTableApiController.class);

    @Autowired
    private ClassFirstCourseTableService classFirstCourseTableService;

    public ResponseEntity<List<ClassFirstCourseTableInfo>> getClassTermFirstCourseTable(@NotNull
                                                                                        @ApiParam(value = "验证码（&signKey=123123）",
                                                                                                required = true)
                                                                                        @Valid @RequestParam(value = "validCode", required = true)
                                                                                                String validCode) {

        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<ClassFirstCourseTableInfo> classFirstCourseTableInfoList = classFirstCourseTableService.getClassTermFirstCourseTable();
            return new ResponseEntity<>(classFirstCourseTableInfoList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getClassTermFirstCourseTable->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

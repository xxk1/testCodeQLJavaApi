package com.lztech.site.controllers.api.roomcoursetabledetail;

import com.lztech.site.service.roomcoursetabledetail.RoomCourseTableDetailService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursetabledetail.RoomCourseTableDetailResource;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-02T11:09:50.065Z")

@Controller
public class RoomCourseTableDetailApiController implements RoomCourseTableDetailApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomCourseTableDetailApiController.class);

    @Autowired
    private RoomCourseTableDetailService roomCourseTableDetailService;

    public ResponseEntity<List<RoomCourseTableDetailResource>> getCourseTableDetailByRoomAndTime(@NotNull
                                                                                                 @ApiParam(value = "验证码：&signKey=123123(md5加密)",
                                                                                                         required = true)
                                                                                                 @Valid
                                                                                                 @RequestParam(value = "validCode", required = true)
                                                                                                         String validCode,
                                                                                                 @NotNull @ApiParam(value = "教室id", required = true)
                                                                                                 @Valid @RequestParam(value = "roomId",
                                                                                                         required = true)
                                                                                                         String roomId,
                                                                                                 @NotNull @ApiParam(value = "开始时间", required = true)
                                                                                                 @Valid @RequestParam(value = "startTime",
                                                                                                         required = true)
                                                                                                         String startTime,
                                                                                                 @NotNull @ApiParam(value = "结束时间",
                                                                                                         required = true)
                                                                                                 @Valid @RequestParam(value = "endTime", required =
                                                                                                         true) String endTime) {

        if (StringUtils.isAnyBlank(roomId, validCode, startTime, endTime)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkAuthCode(validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
        }
        try {

            List<RoomCourseTableDetailResource> roomCourseTableDetailResourceList = roomCourseTableDetailService.getCourseTableDetailList(roomId,
                    startTime, endTime);

            return new ResponseEntity<>(roomCourseTableDetailResourceList, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("getCourseTableDetailByRoomAndTime->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

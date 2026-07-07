package com.lztech.site.controllers.api.coursetables;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.service.coursetable.CourseTableExtendService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursetable.RealtimeSegment;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-10-10T14:19:44.442+08:00")

@Controller
public class RoomrealtimesegmentApiController implements RoomrealtimesegmentApi {

    private final Logger log = LoggerFactory.getLogger(RoomrealtimesegmentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CourseTableExtendService courseTableExtendService;

    @org.springframework.beans.factory.annotation.Autowired
    public RoomrealtimesegmentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<RealtimeSegment>> getRealtimeSegmentsByRoomId(
            @NotNull @ApiParam(value = "教室id", required = true)
            @Valid @RequestParam(value = "roomId", required = true) String roomId,
            @NotNull @ApiParam(value = "加密验证码roomId={xxx}&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (StringUtils.isAnyBlank(validCode, roomId)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        try {
            if (!ValidUtils.checkValidCode("roomId", roomId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<RealtimeSegment> realtimeSegments = courseTableExtendService.getRealtimeSegmentsByRoomId(roomId);
            return new ResponseEntity<List<RealtimeSegment>>(realtimeSegments, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getRealtimeSegmentsByRoomId->", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.lztech.site.controllers.api.segment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.segment.SegmentTimeResource;
import com.lztech.site.resource.segment.WeekIndexResource;
import com.lztech.site.service.segment.SegmentService;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.SegmentResource;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.segment.*;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-08T02:36:10.542Z")

@Controller
public class SegmentApiController implements SegmentApi {

    private final Logger logger = LoggerFactory.getLogger(SegmentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${signKey}")
    private String signKey;

    @Autowired
    private SegmentService segmentService;

    @org.springframework.beans.factory.annotation.Autowired
    public SegmentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<SegmentResource>> segmentGet(
            @NotNull @ApiParam(value = "&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "楼栋Id")
            @Valid @RequestParam(value = "buildId", required = false) String buildId) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return segmentService.getSegmentList(buildId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("segmentGet->{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseDate> datebyweekPost(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "星期", required = true)
            @Valid @RequestParam(value = "weekDay", required = true) Integer weekDay,
            @NotNull @ApiParam(value = "周次", required = true)
            @Valid @RequestParam(value = "weekNum", required = true) Integer weekNum,
            @ApiParam(value = "节次id")
            @Valid @RequestParam(value = "buildingId", required = false) String buildingId) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return segmentService.dateByWeek(buildingId, weekNum, weekDay);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("datebyweekPost", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<SegmentTimeResource> segmenttimeGet(
            @NotNull @ApiParam(value = "节次", required = true)
            @Valid @RequestParam(value = "segment", required = true) Integer segment,
            @NotNull @ApiParam(value = "周次", required = true)
            @Valid @RequestParam(value = "weekNum", required = true) Integer weekNum,
            @NotNull @ApiParam(value = "星期", required = true)
            @Valid @RequestParam(value = "weekDay", required = true) Integer weekDay,
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "buildingId") @Valid @RequestParam(value = "buildingId", required = false) String buildingId) {

        try {
            if (weekNum == null || weekDay == null || segment == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return segmentService.getSegmentTime(buildingId, weekNum, weekDay, segment);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("segmenttimeGet->{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<WeekIndexResource> segmentNowdateGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "buildingId")
            @Valid @RequestParam(value = "buildingId", required = false) String buildingId) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return segmentService.getNowSegmentByBuildId(buildingId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("segmenttimeGet->{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<CourseDate> timebysegmentPost(
            @NotNull @ApiParam(value = "MD5加密验证字符串(&signKey=123123)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "开始节次", required = true)
            @Valid @RequestParam(value = "sSegment", required = true) Integer sSegment,
            @NotNull @ApiParam(value = "结束节次", required = true)
            @Valid @RequestParam(value = "eSegment", required = true) Integer eSegment,
            @ApiParam(value = "节次id")
            @Valid @RequestParam(value = "buildingId", required = false) String buildingId) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            return segmentService.timeBySegment(buildingId, sSegment, eSegment);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("timebysegmentPost", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<NowSegment> getNowSegmentInfo(@NotNull @ApiParam(value = "MD5加密验证字符串buildId=?&signKey=123123", required = true)
                                                        @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                        @NotNull @ApiParam(value = "楼栋Id", required = true)
                                                        @Valid @RequestParam(value = "buildId", required = true) String buildId) {

        try {
            if (StringUtils.isAnyBlank(buildId, validCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            String validKey = "buildId=" + buildId + signKey;
            if (!checkValidCode(validKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            NowSegment nowSegment = segmentService.getNowSegmentInfo(buildId);
            if (Objects.isNull(nowSegment)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(nowSegment, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getNowSegmentInfo" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<BuildingVo>> segmentBuildingIdsGet(@NotNull @ApiParam(value = "&signKey=123123", required = true)
                                                                  @Valid @RequestParam(value = "validCode", required = true) String validCode,
                                                                  @ApiParam(value = "楼栋Ids")
                                                                  @Valid @RequestParam(value = "buildIds", required = false) String buildIds) {
        if (StringUtils.isAnyBlank(validCode, buildIds)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return segmentService.getSegmentListByBuildIds(buildIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("segmentGet->{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ClassroomSegmentCourseVo>> segmentClassroomSegmentCourseDetailsPost(
            @ApiParam(value = "", required = true) @Valid @RequestBody SegmentQueryParam segmentQueryParam) {
        try {
            if (ObjectUtils.isEmpty(segmentQueryParam) || ObjectUtils.isEmpty(segmentQueryParam.getSegmentList())
                    || StringUtils.isAnyEmpty(segmentQueryParam.getClassRoomId()
                    , segmentQueryParam.getBuildingId(), segmentQueryParam.getValidecode())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!checkValidCode(signKey, segmentQueryParam.getValidecode())) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return segmentService.getSegmentsClassroomSegmentCourseDetails(segmentQueryParam);
        } catch (Exception e) {
            logger.error("segmentClassroomSegmentCourseDetailsPost" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ClassroomSegmentCoursePage> segmentCourseScheduleDetailsPost(
            @ApiParam(value = "", required = true) @Valid @RequestBody CourseQueryParam courseQueryParam) {
        try {
            if (ObjectUtils.isEmpty(courseQueryParam)
                    || StringUtils.isAnyEmpty(courseQueryParam.getBuildingId(),
                    courseQueryParam.getValidecode()) || courseQueryParam.getSegment() == null
                    || courseQueryParam.getPage() == null || courseQueryParam.getPageSize() == null
                    || courseQueryParam.getPage() == 0 || courseQueryParam.getPageSize() == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, courseQueryParam.getValidecode())) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return segmentService.getSegmentCourseScheduleDetails(courseQueryParam);
        } catch (Exception e) {
            logger.error("segmentCourseScheduleDetailsPost" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SegmentDataResource>> coursetableSegmentGet(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<SegmentDataResource> segmentDataResources = segmentService.getSegmentDataResource();
            if (segmentDataResources == null || segmentDataResources.isEmpty()) {

                SegmentDataResource segmentDataResource = new SegmentDataResource();
                segmentDataResource.setSegment(null);
                segmentDataResource.setCounts(null);
                segmentDataResources.add(segmentDataResource);
            }
            return new ResponseEntity<>(segmentDataResources, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("coursetableSegmentGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<NowSegment>> segmentDefaultbuildingsegmentGet(
            @NotNull @ApiParam(value = "验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            List<NowSegment> nowSegments = segmentService.getDefaultBuildingSegment();
            if (nowSegments.size() <= 0) {
                return new ResponseEntity(ErrorResult.dataNotExistError("默认楼栋节次"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(nowSegments, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("segmentDefaultbuildingsegmentGet" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SegmentVo>> segmentAllGet(
            @NotNull @ApiParam(value = "MD5加密验证字符串 &signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isEmpty(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return segmentService.getAllSegmentsDistinct();
        } catch (Exception e) {
            logger.error("segmentAllGet->{}" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BuildingSegmentResource> getBuildingSegmentResource(
            @ApiParam(value = "教室id", required = true) @PathVariable("roomId") String roomId,
            @NotNull @ApiParam(value = "验证码：roomId=?&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(validCode, roomId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("roomId", roomId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            BuildingSegmentResource buildingSegmentResource = segmentService.getBuildingSegmentResource(roomId);
            return new ResponseEntity<>(buildingSegmentResource, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("getBuildingSegmentResource->{}" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SegmentResourceVo>> getBuildAndSegmentList(
            @NotNull @ApiParam(value = "&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "楼栋Id")
            @Valid @RequestParam(value = "buildId", required = false) String buildId) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            return segmentService.getBuildAndSegmentList(buildId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("segmentGet->{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

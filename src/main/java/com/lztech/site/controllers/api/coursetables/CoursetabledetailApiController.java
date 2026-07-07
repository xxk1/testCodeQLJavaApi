package com.lztech.site.controllers.api.coursetables;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lztech.site.resource.coursetable.CourseSegmentResource;
import com.lztech.site.resource.coursetable.CourseTableDetailParam;
import com.lztech.site.resource.coursetable.CourseTypeCountResource;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailResource;
import com.lztech.site.service.coursetable.CourseTableService;
import com.lztech.site.service.coursetabledetail.CourseTableDetailExtendService;
import com.lztech.site.service.coursetabledetail.CourseTableDetailService;
import com.lztech.site.service.online.OnlineTourService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.LineType;
import com.lztech.site.until.ValidUtils;
import com.lztech.site.viewmodel.coursetable.OccupancyLabResource;
import com.lztech.site.viewmodel.coursetable.RoomResource;
import com.lztech.site.viewmodel.coursetabledetail.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.lztech.site.until.RequestUtils.checkValidCode;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-17T08:59:38.735Z")

@Controller
public class CoursetabledetailApiController implements CoursetabledetailApi {
    private final Logger log = LoggerFactory.getLogger(CoursetabledetailApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${studentType}")
    private Integer defaultStudentType;
    @Value("${signKey}")
    private String signKey;
    @Autowired
    private CourseTableService courseTableService;
    @Autowired
    private OnlineTourService onlineTourService;
    @Autowired
    private CourseTableDetailService courseTableDetailService;
    @Autowired
    private CourseTableDetailExtendService courseTableDetailExtendService;

    @org.springframework.beans.factory.annotation.Autowired
    public CoursetabledetailApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CourseSegmentResource> getCourseTableDetail(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课表综合查询信息", required = true)
            @Valid @RequestBody CourseTableDetailParam courseTableDetailParam) {
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            if (Objects.isNull(courseTableDetailParam.getStudentType())) {
                courseTableDetailParam.setStudentType(defaultStudentType);
            }
            CourseSegmentResource courseSegmentResource = courseTableService.queryCourseTableDetail(courseTableDetailParam);
            return new ResponseEntity<>(courseSegmentResource, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseTableDetail->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTypeCountResource> coursetableCoursetypecountPost(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "获取课表总数综合查询", required = true)
            @Valid @RequestBody CourseTableDetailParam courseTableDetailParam) {
        try {
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            if (ObjectUtils.isEmpty(courseTableDetailParam.getStudentType())) {
                courseTableDetailParam.setStudentType(defaultStudentType);
            }
            return courseTableService.queryCourseTypeIdCount(courseTableDetailParam);
        } catch (Exception e) {
            log.error("coursetabledetailPost->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseCourseTableDetailResource>> coursetabledetailCourseInclassGet(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "开课类型")
            @Valid @RequestParam(value = "courseStudentTypeIds", required = false) String courseStudentTypeIds,
            @ApiParam(value = "查询条件 教室/课程/教师 多个使用空格隔开")
            @Valid @RequestParam(value = "searchParams", required = false) String searchParams,
            @ApiParam(value = "课程Id,多个以‘，’分割")
            @Valid @RequestParam(value = "courseIds", required = false) String courseIds,
            @ApiParam(value = "学院id,多个以‘，’分割")
            @Valid @RequestParam(value = "collegeIds", required = false) String collegeIds,
            @ApiParam(value = "是否区分本硕信息")
            @Valid @RequestParam(value = "isDistinguishCourseStudentType", required = false) String isDistinguishCourseStudentType) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            return onlineTourService.statisticsCourseCount(searchParams, courseIds, collegeIds, courseStudentTypeIds, isDistinguishCourseStudentType);
        } catch (Exception e) {
            log.error("coursetabledetailCourseInclassGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseCourseTableDetailResource>> coursetabledetailCourseInclassPost(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "查询条件 教室/课程/教师 多个使用空格隔开")
            @Valid @RequestParam(value = "searchParams", required = false) String searchParams,
            @ApiParam(value = "课程Id,多个以‘，’分割")
            String courseIds,
            @ApiParam(value = "学院id,多个以‘，’分割")
            String collegeIds) {
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return onlineTourService.statisticsCourseCountPost(searchParams, courseIds, collegeIds);
        } catch (Exception e) {
            log.error("coursetabledetailCourseInclassGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTableDetailPageResource> coursetabledetailInclassPagePost(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课表巡课搜索信息", required = true)
            @Valid @RequestBody InclassPageVo inclassPageVo) {

        if (StringUtils.isBlank(validCode) || Objects.isNull(inclassPageVo.getPage()) || Objects.isNull(inclassPageVo.getPageSize())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return onlineTourService.findInClassCourseTableDetails(inclassPageVo);
        } catch (Exception e) {
            log.error("coursetabledetailInclassPagePost->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseTableDetailResource> roomIdCoursetabledetailGet(@NotNull
                                                                                @ApiParam(value = "id={}加密验证码（&signKey=123123）",
                                                                                        required = true) @Valid
                                                                                @RequestParam(value = "validCode", required = true)
                                                                                String validCode,
                                                                                @ApiParam(value = "教室id", required = true)
                                                                                @PathVariable("id")
                                                                                String id,
                                                                                @ApiParam(value = "是否区分本硕信息") @Valid
                                                                                @RequestParam(value = "isDistinguishCourseStudentType",
                                                                                        required = false)
                                                                                String isDistinguishCourseStudentType,
                                                                                @ApiParam(value = "授课学生类型") @Valid
                                                                                @RequestParam(value = "courseStudentTypeIds", required = false)
                                                                                String courseStudentTypeIds) {

        if (StringUtils.isAnyBlank(validCode, id)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = "id=" + id + signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            List<CourseTableDetailResource> courseTableDetailResourceList = onlineTourService.findInClassCourseTableDetailByRoomId(id,
                    isDistinguishCourseStudentType, courseStudentTypeIds);

            if (CollectionUtils.isEmpty(courseTableDetailResourceList) || (courseTableDetailResourceList.size() == 1 &&
                    StringUtils.isBlank(courseTableDetailResourceList.get(0).getId()))) {
                return new ResponseEntity(ErrorResult.customError("未找到正在上课的课表信息"), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(courseTableDetailResourceList.get(0), HttpStatus.OK);

        } catch (Exception e) {
            log.error("roomIdCoursetabledetailGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<RoomResource>> getCourseOccupancyLabList(
            @NotNull @ApiParam(value = "课程日期（格式：yyyy-MM-dd；多个用“,”隔开，如：1,2,3）", required = true)
            @Valid @RequestParam(value = "courseDates", required = true) String courseDates,
            @NotNull @ApiParam(value = "课程节次（多个用“,”隔开，如：1,2,3）", required = true)
            @Valid @RequestParam(value = "courseSegments", required = true) String courseSegments,
            @NotNull @ApiParam(value = "courseSegments=XXX&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {
        try {
            if (StringUtils.isAnyBlank(courseDates, courseSegments, validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            List<String> courseDateList = Arrays.asList(courseDates.split(","));
            for (String courseDate : courseDateList) {
                if (!DateUtils.checkDateStringFormat(courseDate, LineType.HORIZONTAL_LINE)) {
                    return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
                }
            }
            if (!ValidUtils.checkValidCode("courseSegments", courseSegments, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return courseTableService.getCourseOccupancyLabList(courseDateList, courseSegments);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getCourseOccupancyLabList->{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //region 获取当天课表明细信息
    public ResponseEntity<List<NowDayCourseTableDetail>> coursetabledetailNowdaycoursetabledetailsGet(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true) @Valid
            @RequestParam(value = "validCode", required = true)
            String validCode) {
        if (StringUtils.isBlank(validCode) || StringUtils.isEmpty(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!checkValidCode(this.signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }

        try {
            return this.courseTableDetailService.getNowDayCourseTableDetails();
        } catch (Exception e) {
            log.error("coursetabledetailNowdaycoursetabledetailsGet->{}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    public ResponseEntity<OccupancyResource> coursetabledetailOccupancycoursetabledetailGet(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "节次", required = true)
            @Valid @RequestParam(value = "segment", required = true) Integer segment,
            @NotNull @ApiParam(value = "上课日期", required = true)
            @Valid @RequestParam(value = "courseDate", required = true) String courseDate,
            @NotNull @ApiParam(value = "教室id", required = true)
            @Valid @RequestParam(value = "roomId", required = true) String roomId) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseDate, roomId) || segment == null) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return courseTableDetailService.getOccupancyCourseTableDetail(segment, courseDate, roomId);
        } catch (Exception e) {
            log.error("coursetabledetailOccupancycoursetabledetailGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SegmentCourseTableResource>> coursetabledetailSegmentcoursetableresourceGet(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "上课日期", required = true)
            @Valid @RequestParam(value = "courseDate", required = true) String courseDate) {
        try {
            if (StringUtils.isAnyBlank(validCode, courseDate)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            return courseTableDetailService.getSegmentCourseTableResource(courseDate);
        } catch (Exception e) {
            log.error("coursetabledetailSegmentcoursetableresourceGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailInClassResource>> getCourseTableDetailInClassList(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "查询字段——课程名称或老师名称 模糊查询")
            @Valid @RequestParam(value = "courseNameOrTeacherName", required = false) String courseNameOrTeacherName,
            @ApiParam(value = "课程Id和学院Id集合")
            @Valid @RequestBody CourseIdAndCollegeIdQuery courseIdAndCollegeIdQuery) {

        try {

            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<CourseTableDetailInClassResource> courseTableDetailInClassResourceList =
                    courseTableDetailService.getCourseTableDetailInClassList(courseNameOrTeacherName, courseIdAndCollegeIdQuery);

            return new ResponseEntity<>(courseTableDetailInClassResourceList, HttpStatus.OK);

        } catch (Exception e) {
            log.error("getCourseTableDetailInClassList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableSuperviseVo>> getCourseandgroupByTeacherId(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @NotNull @ApiParam(value = "老师Id", required = true)
            @Valid @RequestParam(value = "teacherId", required = true) String teacherId,
            @ApiParam(value = "开课类型")
            @Valid @RequestParam(value = "courseStudentTypeIds", required = false) String courseStudentTypeIds,
            @ApiParam(value = "是否区分本硕信息")
            @Valid @RequestParam(value = "isDistinguishCourseStudentType", required = false) String isDistinguishCourseStudentType,
            @ApiParam(value = "是否需要过滤思政课")
            @Valid @RequestParam(value = "isNeedFilterPoliticalCourse", required = false) Boolean isNeedFilterPoliticalCourse) {
        try {
            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }
            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            teacherId = teacherId.trim();
            return new ResponseEntity(courseTableDetailService.getTeacherGroupInfos(teacherId, isDistinguishCourseStudentType,
                    courseStudentTypeIds, isNeedFilterPoliticalCourse), HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCoursedetailandgroupByTeacherId->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CourseTableDetailPageResource> coursetabledetailLivePost(
            @ApiParam(value = "在线巡课查询条件", required = true)
            @Valid @RequestBody CourseTableDetailPageRequest courseTableDetailPageRequest,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "学院Id", required = false)
            @Valid @RequestParam(value = "collegeId", required = false) String collegeId,
            @ApiParam(value = "学院名称", required = false)
            @Valid @RequestParam(value = "collegeName", required = false) String collegeName) {

        if (courseTableDetailPageRequest.getPage() == null || courseTableDetailPageRequest.getPageSize() == null) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isEmpty(courseTableDetailPageRequest.getCourseDate())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseTableDetailPageResource pageResource =
                    courseTableDetailService.getLiveCourseTableDetailPage(courseTableDetailPageRequest, collegeName, collegeId);
            return new ResponseEntity<>(pageResource, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseTableDetailInClassList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<CourseTableDetailPageResource> getLiveCourseTableDetailListByAdministrativeClassIdList(
            @ApiParam(value = "辅导员巡课查询条件", required = true)
            @Valid @RequestBody CourseTableDetailAdministrativeClassPageRequest pageRequest,
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode
    ) {

        if (pageRequest.getPage() == null || pageRequest.getPageSize() == null) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isEmpty(pageRequest.getCourseDate())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        if (!checkValidCode(signKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            CourseTableDetailPageResource pageResource =
                    courseTableDetailService.getAdministrativeClassLiveCourseTableDetailPage(pageRequest);
            return new ResponseEntity<>(pageResource, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getLiveCourseTableDetailListByAdministrativeClassIdList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<CoursetableResourceVo> getCourseByCourseTableDetailId(
            @NotNull @ApiParam(value = "验证码：courseTableDetailId={}&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课表详情ID", required = true)
            @PathVariable("courseTableDetailId") String courseTableDetailId) {
        try {
            if (StringUtils.isAnyEmpty(validCode, courseTableDetailId)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.BAD_REQUEST);
            }
            if (!ValidUtils.checkValidCode("courseTableDetailId", courseTableDetailId, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }
            CoursetableResourceVo coursetableResourceVo =
                    courseTableDetailService.getCourseByCourseTableDetailId(courseTableDetailId);
            return new ResponseEntity<CoursetableResourceVo>(coursetableResourceVo, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseByCourseTableDetailId--->", e);
            return new ResponseEntity<CoursetableResourceVo>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CourseTableDetailPageModel> coursetabledetailInclassDetailsGet(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课表巡课搜索信息", required = true)
            @Valid @RequestBody InclassModelRequest inclassModelRequest) {

        if (StringUtils.isBlank(validCode) || Objects.isNull(inclassModelRequest.getPage()) || Objects.isNull(inclassModelRequest.getPageSize())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            return onlineTourService.findCourseTableDetailInClassDetails(inclassModelRequest);
        } catch (Exception e) {
            log.error("coursetabledetailInclassPagePost->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<CourseTableDetailResource> roomIdCoursetabledetailinfoGet(@NotNull
                                                                                    @ApiParam(value = "id={}加密验证码（&signKey=123123）",
                                                                                            required = true) @Valid
                                                                                    @RequestParam(value = "validCode", required = true)
                                                                                    String validCode,
                                                                                    @NotNull
                                                                                    @ApiParam(value = "数据来源，多个使用逗号隔开", required = true)
                                                                                    @Valid @RequestParam(value = "sources", required = true)
                                                                                    String sources,
                                                                                    @ApiParam(value = "教室id", required = true)
                                                                                    @PathVariable("id")
                                                                                    String id) {
        if (StringUtils.isAnyBlank(validCode, id, sources)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = "id=" + id + signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {

            List<CourseTableDetailResource> courseTableDetailResourceList = onlineTourService.findInClassCourseTableDetailByRoomId(id, null, null
                    , null);

            if (CollectionUtils.isEmpty(courseTableDetailResourceList) || (courseTableDetailResourceList.size() == 1 &&
                    StringUtils.isBlank(courseTableDetailResourceList.get(0).getId()))) {
                return new ResponseEntity(ErrorResult.customError("未找到正在上课的课表信息"), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(courseTableDetailResourceList.get(0), HttpStatus.OK);

        } catch (Exception e) {
            log.error("roomIdCoursetabledetailGet->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<List<ExpertAuthorizationChildListOne>> getExpertAuthorizationScheduleResource(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "课表巡课搜索信息", required = true)
            @Valid @RequestBody ExpertAuthorizationScheduleParam expertAuthorizationScheduleParam) {
        if (ObjectUtils.isEmpty(expertAuthorizationScheduleParam)
                || StringUtils.isAnyBlank(validCode, expertAuthorizationScheduleParam.getCourseDate())) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<ExpertAuthorizationChildListOne> scheduleResourceList =
                    onlineTourService.getExpertAuthorizationScheduleResource(expertAuthorizationScheduleParam);
            return new ResponseEntity<>(scheduleResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("coursetabledetailInclassPagePost->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<LiveScheduleResource>> getLiveCourseTableDetailList(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "获取正在直播或点播课表信息入参", required = true)
            @Valid @RequestBody LiveCourseTableDetailParam liveCourseTableDetailParam) {

        if (ObjectUtils.isEmpty(liveCourseTableDetailParam) || StringUtils.isAnyBlank(validCode)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<LiveScheduleResource> liveScheduleResourceList =
                    onlineTourService.getLiveCourseTableDetailList(liveCourseTableDetailParam);
            return new ResponseEntity(liveScheduleResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getLiveCourseTableDetailList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<LiveScheduleResourcePageVo> getLiveCourseList(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "获取正在直播课程信息入参", required = true)
            @Valid @RequestBody LiveCourseParam liveCourseParam) {
        if (ObjectUtils.isEmpty(liveCourseParam) || StringUtils.isAnyBlank(validCode)
                || liveCourseParam.getPage() == null || liveCourseParam.getPage() == 0
                || liveCourseParam.getPageSize() == null || liveCourseParam.getPageSize() == 0) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            LiveScheduleResourcePageVo liveScheduleResourcePageVo =
                    onlineTourService.getLiveCourseList(liveCourseParam);
            return new ResponseEntity(liveScheduleResourcePageVo, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getLiveCourseList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<LessonPreviewPage> getLiveCourseTablePage(
            @NotNull @ApiParam(value = "验证码：&signKey=123123(md5加密)", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "获取信息入参", required = true)
            @Valid @RequestBody LessonPreviewParam lessonPreviewParam) {
        if (ObjectUtils.isEmpty(lessonPreviewParam) || StringUtils.isAnyBlank(validCode)
                || lessonPreviewParam.getPage() == null || lessonPreviewParam.getPage() == 0
                || lessonPreviewParam.getPageSize() == null || lessonPreviewParam.getPageSize() == 0) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        String validKey = signKey;
        if (!checkValidCode(validKey, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            LessonPreviewPage lessonPreviewPage =
                    onlineTourService.getLiveCourseTablePage(lessonPreviewParam);
            return new ResponseEntity(lessonPreviewPage, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getLiveCourseTablePage->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<OccupancyLabResource>> getCourseTableDetailOccupancyLabList(
            @NotNull @ApiParam(value = "上课日期（格式：yyyy-MM-dd）", required = true)
            @Valid @RequestParam(value = "courseDate", required = true) String courseDate,
            @NotNull @ApiParam(value = "课程节次（多个用“,”隔开，如：1,2,3）", required = true)
            @Valid @RequestParam(value = "courseSegments", required = true) String courseSegments,
            @NotNull @ApiParam(value = "courseDate={}&signKey=123123", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode) {

        if (StringUtils.isAnyBlank(validCode, courseDate, courseSegments)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }

        if (!ValidUtils.checkValidCode("courseDate", courseDate, validCode)) {
            return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
        }
        try {
            List<OccupancyLabResource>  occupancyLabResources =
                    courseTableDetailExtendService.getCourseTableDetailOccupancyLabList(courseDate, courseSegments);
            occupancyLabResources = occupancyLabResources.stream()
                    .filter(o -> !StringUtils.isBlank(o.getRoomId())).collect(Collectors.toList());
            return new ResponseEntity(occupancyLabResources, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseTableDetailOccupancyLabList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CourseTableDetailInClassResource>> getCourseTableDetailInClassDetailList(
            @NotNull @ApiParam(value = "加密验证码（&signKey=123123）", required = true)
            @Valid @RequestParam(value = "validCode", required = true) String validCode,
            @ApiParam(value = "查询字段——课程名称或老师名称 模糊查询")
            @Valid @RequestParam(value = "courseNameOrTeacherName", required = false) String courseNameOrTeacherName,
            @ApiParam(value = "课程Id和学院Id集合")
            @Valid @RequestBody CourseIdAndCollegeIdQuery courseIdAndCollegeIdQuery) {

        try {

            if (StringUtils.isBlank(validCode)) {
                return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
            }

            if (!checkValidCode(signKey, validCode)) {
                return new ResponseEntity(ErrorResult.validCodeError(), HttpStatus.FORBIDDEN);
            }

            List<CourseTableDetailInClassResource> courseTableDetailInClassResourceList =
                    courseTableDetailExtendService.getCourseTableDetailInClassDetailList(courseNameOrTeacherName, courseIdAndCollegeIdQuery);

            return new ResponseEntity<>(courseTableDetailInClassResourceList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("getCourseTableDetailInClassDetailList->{}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
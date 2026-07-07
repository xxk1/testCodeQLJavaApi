package com.lztech.site.service.coursetabledetail;

import com.lztech.domain.model.coursesegment.CourseSegment;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetail.enums.CourseKind;
import com.lztech.domain.model.coursetabledetail.enums.CourseStatus;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.coursetype.CourseType;
import com.lztech.domain.model.segment.Segment;
import com.lztech.persistence.repositories.coursesegment.CourseSegmentRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetailroomuser.CourseTableDetailRoomUserRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.coursetype.CourseTypeRepository;
import com.lztech.persistence.repositories.segment.SegmentRepository;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.service.classroom.ClassRoomService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.authorityapi.UserBaseInfoAndCollege;
import com.lztech.site.viewmodel.authorityapi.UserBaseInfoAndCollegeBase;
import com.lztech.site.viewmodel.classroom.ClassRoomResource;
import com.lztech.site.viewmodel.coursetabledetail.CourseTableDetailResource;
import com.lztech.site.viewmodel.coursetabledetail.GroupCourseTableInfo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Service
public class CourseTableDetailImportService {

    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private SegmentRepository segmentRepository;
    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private CourseSegmentRepository courseSegmentRepository;
    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private CourseTableDetailRoomUserRepository courseTableDetailRoomUserRepository;
    @Autowired
    private AuthorityApiService authorityApiService;

    public ResponseEntity<Void> importCourseTableDetail(GroupCourseTableInfo groupCourseTableDetail) {
        List<CourseTable> courseTableList = courseTableRepository.findByGroupId(groupCourseTableDetail.getGroupId());
        if (CollectionUtils.isEmpty(courseTableList)) {
            return new ResponseEntity(ErrorResult.customError("未找到此班级"), HttpStatus.CONFLICT);
        }
        List<String> teacherNoList = new ArrayList<>();
        groupCourseTableDetail.getCourseTableDetailList().forEach(courseTableDetailResource -> {
            teacherNoList.add(courseTableDetailResource.getTeacherNo());
        });
        List<UserBaseInfoAndCollege> teacherInfoVoList = authorityApiService.getTeacherInfoListByTeacherNo(teacherNoList);
        List<ClassRoomResource> classRoomResourceList = classRoomService.findClassRoomResourceList("");
        List<CourseType> courseTypeList = courseTypeRepository.findAll();
        Date now = new Date();
        List<Segment> tempSegments = new ArrayList<>();
        List<CourseTableDetail> courseTableDetailList = new ArrayList<>();
        List<CourseSegment> courseSegmentList = new ArrayList<>();
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();
        List<CourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();
        for (CourseTableDetailResource courseTableDetailResource : groupCourseTableDetail.getCourseTableDetailList()) {
            List<UserBaseInfoAndCollegeBase> resultList = new ArrayList<>();
            if (StringUtils.isNotBlank(courseTableDetailResource.getTeacherNo())) {
                List<String> courseTeacherNoList = Arrays.stream(courseTableDetailResource.getTeacherNo().split(","))
                        .distinct()
                        .collect(Collectors.toList());
                for (int i = 0; i < courseTeacherNoList.size(); i++) {
                    String courseTeacherNo = courseTeacherNoList.get(i);
                    UserBaseInfoAndCollege teacherInfoVo = teacherInfoVoList
                            .stream()
                            .filter(no -> no.getUserNo().equals(courseTeacherNo))
                            .findFirst()
                            .orElse(null);
                    if (Objects.isNull(teacherInfoVo)) {
                        return new ResponseEntity(ErrorResult.customError("未找到工号为：" + courseTeacherNo + "的老师"), HttpStatus.CONFLICT);
                    } else {
                        UserBaseInfoAndCollegeBase teacherInfoVoBase = new UserBaseInfoAndCollegeBase();
                        teacherInfoVoBase.setUserId(teacherInfoVo.getUserId());
                        teacherInfoVoBase.setUserNo(teacherInfoVo.getUserNo());
                        teacherInfoVoBase.setUserName(teacherInfoVo.getUserName());
                        teacherInfoVoBase.setCollegeId(teacherInfoVo.getCollegeId());
                        teacherInfoVoBase.setCollegeName(teacherInfoVo.getCollegeName());
                        teacherInfoVoBase.setShowOrder(i);
                        resultList.add(teacherInfoVoBase);
                    }
                }
            }
            CourseTable courseTable = courseTableList.get(0);
            List<String> segments = getCourseTableDetailSegment(courseTableDetailResource.getSegments());
            CourseTableDetail courseTableDetail = createCourseTableDetail(courseTableDetailResource, courseTable, courseTypeList, now, resultList,
                    segments);

            List<Segment> segmentList = segmentRepository.findByBuildName(courseTableDetailResource.getBuildName());
            if (CollectionUtils.isEmpty(segmentList)) {
                segmentList = segmentRepository.findByBuildId("0");
            }
            tempSegments.clear();
            for (String segmentStr : segments) {
                segmentList.stream().filter(segment -> segmentStr.equals(String.valueOf(segment.getSegmentPK().getSegment()))).findFirst()
                        .ifPresent(buildSegment -> {
                                    courseSegmentList.add(createScheduleSegment(courseTableDetail, buildSegment));
                                    tempSegments.add(buildSegment);
                                }
                        );
            }
            if (!tempSegments.isEmpty()) {
                courseTableDetail.setSegmentStartTime(DateUtils.stringToDate(DateUtils.TIME, tempSegments.get(0).getStartTime()));
                courseTableDetail.setSegmentEndTime(DateUtils.stringToDate(DateUtils.TIME,
                        tempSegments.get(tempSegments.size() - 1).getEndTime()));
            }

            courseTableDetailTeacherList.addAll(resultList
                    .stream()
                    .map(teacherResource ->
                            getCourseTableDetailTeacher(now, courseTableDetail, teacherResource))
                    .collect(Collectors.toList()));

            if (StringUtils.isNotBlank(courseTableDetailResource.getRoomName())) {
                createCourseTableDetailRoomInfo(now, courseTableDetailRoomUserList, courseTableDetailResource, courseTableDetail,
                        classRoomResourceList);
            }
            courseTableDetailList.add(courseTableDetail);
        }
        if (CollectionUtils.isNotEmpty(courseTableDetailList)) {
            courseTableDetailRepository.saveAll(courseTableDetailList);
            courseSegmentRepository.saveAll(courseSegmentList);
            courseTableDetailTeacherRepository.saveAll(courseTableDetailTeacherList);
            courseTableDetailRoomUserRepository.saveAll(courseTableDetailRoomUserList);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private CourseTableDetailTeacher getCourseTableDetailTeacher(Date now, CourseTableDetail courseTableDetail,
                                                                 UserBaseInfoAndCollegeBase teacherResource) {
        CourseTableDetailTeacher courseTableDetailTeacher = new CourseTableDetailTeacher();
        courseTableDetailTeacher.setCourseTableDetail(courseTableDetail);
        courseTableDetailTeacher.setTeacherId(teacherResource.getUserId());
        courseTableDetailTeacher.setTeacherName(teacherResource.getUserName());
        courseTableDetailTeacher.setTeacherNo(teacherResource.getUserNo());
        courseTableDetailTeacher.setTeacherCollegeId(teacherResource.getCollegeId());
        courseTableDetailTeacher.setTeacherCollegeName(teacherResource.getCollegeName());
        courseTableDetailTeacher.setCreateTime(now);
        courseTableDetailTeacher.setShowOrder(teacherResource.getShowOrder());
        return courseTableDetailTeacher;
    }

    private void createCourseTableDetailRoomInfo(Date now,
                                                 List<CourseTableDetailRoomUser> courseTableDetailRoomUserList,
                                                 CourseTableDetailResource courseTableDetailResource,
                                                 CourseTableDetail courseTableDetail,
                                                 List<ClassRoomResource> classRoomResourceList) {
        CourseTableDetailRoomUser courseTableDetailRoomUser = new CourseTableDetailRoomUser();
        ClassRoomResource classRoomResource = classRoomResourceList
                .stream()
                .filter(classroom -> classroom.getRoomName().equals(courseTableDetailResource.getRoomName()))
                .findFirst()
                .orElse(null);
        courseTableDetailRoomUser.setHasLive(false);
        courseTableDetailRoomUser.setCourseTableDetail(courseTableDetail);
        courseTableDetailRoomUser.setRoomName(courseTableDetailResource.getRoomName());
        courseTableDetailRoomUser.setCreateTime(now);
        if (Objects.nonNull(classRoomResource)) {
            courseTableDetailRoomUser.setRoomId(classRoomResource.getId());
            courseTableDetailRoomUser.setRoomCode(classRoomResource.getId());
            courseTableDetailRoomUser.setHasLive(classRoomResource.getHasLive());
        }
        courseTableDetailRoomUserList.add(courseTableDetailRoomUser);
    }

    private List<String> getCourseTableDetailSegment(String segments) {
        List<String> segmentList = new ArrayList<>();
        if (StringUtils.isNotBlank(segments)) {
            String[] segmentArray = segments.split("-");

            for (int i = Integer.valueOf(segmentArray[0]); i <= Integer.valueOf(segmentArray[segmentArray.length - 1]); i++) {
                segmentList.add(String.valueOf(i));
            }

        }
        return segmentList.stream().distinct().collect(Collectors.toList());
    }

    private CourseTableDetail createCourseTableDetail(CourseTableDetailResource courseTableDetailResource,
                                                      CourseTable courseTable,
                                                      List<CourseType> courseTypeList,
                                                      Date now,
                                                      List<UserBaseInfoAndCollegeBase> resultList,
                                                      List<String> segments) {
        CourseTableDetail courseTableDetail = new CourseTableDetail();
        courseTableDetail.setCourseDate(DateUtils.stringToDate(DateUtils.DATE, courseTableDetailResource.getCourseDate()));
        courseTableDetail.setCourseStatus(CourseStatus.NOT_CLASS);
        courseTableDetail.setCourseTable(courseTable);
        courseTableDetail.setSegment(String.join(",", segments));
        courseTableDetail.setWeek(courseTableDetailResource.getWeek());
        courseTableDetail.setWeekNum(Integer.valueOf(courseTableDetailResource.getWeekNum()));
        courseTableDetail.setSource(Source.SYSTEM_ENTRY);
        courseTableDetail.setCreateTime(now);
        CourseType courseType = courseTypeList
                .stream()
                .filter(type -> type.getCourseTypeName().equals(courseTableDetailResource.getCourseTypeName()))
                .findFirst()
                .orElse(null);
        if (Objects.isNull(courseType)) {
            courseType = courseTypeList.stream().sorted(Comparator.comparing(CourseType::getSortName)).collect(Collectors.toList()).get(0);
        }
        courseTableDetail.setCourseType(courseType);
        courseTableDetail.setCourseTypeName(courseType.getCourseTypeName());
        courseTableDetail.setCourseKind(CourseKind.THEORY_COURSE);
        courseTableDetail.setCourseName(courseTable.getCourseName());
        courseTableDetail.setTeacherNames(resultList
                .stream()
                .sorted(Comparator.comparing(UserBaseInfoAndCollegeBase::getShowOrder))
                .map(UserBaseInfoAndCollegeBase::getUserName)
                .distinct()
                .collect(joining(",")));
        courseTableDetail.setTeacherCollegeNames(resultList
                .stream()
                .map(UserBaseInfoAndCollegeBase::getCollegeName)
                .distinct()
                .collect(joining(",")));
        courseTableDetail.setTeacherCollegeIds(resultList
                .stream()
                .map(UserBaseInfoAndCollegeBase::getCollegeId)
                .distinct()
                .collect(joining(",")));
        return courseTableDetail;
    }

    private CourseSegment createScheduleSegment(CourseTableDetail courseTableDetail, Segment buildSegment) {
        CourseSegment courseSegment = new CourseSegment();
        courseSegment.setCourseTableDetail(courseTableDetail);
        courseSegment.setSegment(buildSegment);
        return courseSegment;
    }
}

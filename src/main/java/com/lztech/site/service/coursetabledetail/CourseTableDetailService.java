package com.lztech.site.service.coursetabledetail;

import com.google.common.base.Joiner;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.coursesegment.CourseSegment;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetail.CourseTableDetailProject;
import com.lztech.domain.model.coursetabledetail.enums.CourseKind;
import com.lztech.domain.model.coursetabledetail.enums.CourseStatus;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.coursetype.CourseType;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.domain.model.segment.Segment;
import com.lztech.domain.model.term.Term;
import com.lztech.domain.model.term.enums.TermType;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.coursesegment.CourseSegmentRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetailroomuser.CourseTableDetailRoomUserRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.coursetype.CourseTypeRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.persistence.repositories.segment.SegmentRepository;
import com.lztech.persistence.repositories.terms.TermRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.resource.administrativeclass.AdministrativeVo;
import com.lztech.site.resource.administrativeclass.ClassCourseTable;
import com.lztech.site.resource.administrativeclass.ClassCourseTablePage;
import com.lztech.site.resource.coursetable.CourseTypeCountResource;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailResource;
import com.lztech.site.resource.coursetabledetail.*;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.service.classroom.ClassRoomService;
import com.lztech.site.service.coursetabledetail.pageandgroup.JPASpecialCountErrorHandler;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.CourseTableSimpleVo;
import com.lztech.site.viewmodel.administratorclass.AdministrativeClassIdVo;
import com.lztech.site.viewmodel.authorityapi.TeacherVo;
import com.lztech.site.viewmodel.classroom.ClassroomAndFloorResource;
import com.lztech.site.viewmodel.classroom.ClassroomBaseInfoVo;
import com.lztech.site.viewmodel.coursetable.*;
import com.lztech.site.viewmodel.coursetabledetail.*;
import com.lztech.site.viewmodel.coursetypecount.CourseTypeCount;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.evaluation.EvaluationCourseTableVo;
import com.lztech.site.viewmodel.expertinformation.TeacherInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.coursetabledetail.specification.CourseTableDetailSpecification.*;
import static com.lztech.site.constants.Constant.*;
import static com.lztech.site.until.DateUtils.*;
import static com.lztech.site.until.LzStringUtil.convertToQuotedString;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class CourseTableDetailService extends JPASpecialCountErrorHandler {
    private String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    @Autowired
    private AuthorityApiService authorityApiService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private SegmentRepository segmentRepository;
    @Autowired
    private CourseTableDetailRoomUserRepository courseTableDetailRoomUserRepository;
    @Autowired
    private CourseSegmentRepository courseSegmentRepository;
    @Autowired
    private TermService termService;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private CourseTableDetailExtendService courseTableDetailExtendService;

    public ResponseEntity updateCourseTableDetailCourseStatus(String courseTableDetailId, UpdateCourseStatusInfo updateCourseStatusInfo) {
        Optional<CourseTableDetail> optionalCourseTableDetail = courseTableDetailRepository.findById(courseTableDetailId);
        if (optionalCourseTableDetail.isPresent()) {
            CourseTableDetail courseTableDetail = optionalCourseTableDetail.get();
            CourseStatus courseStatus = CourseStatus.getCourseStatus(updateCourseStatusInfo.getCourseStatus());
            if (courseStatus == null) {
                return new ResponseEntity<>(ErrorResult.customError("课次状态不正确"), HttpStatus.OK);
            } else {
                courseTableDetail.setCourseStatus(courseStatus);
                courseTableDetailRepository.save(courseTableDetail);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<CourseTableInfo> getCourseTableInfoByCourseTableDetailId(String courseTableDetailId) {
        Optional<CourseTableDetail> optionalCourseTableDetail = courseTableDetailRepository.findById(courseTableDetailId);
        if (optionalCourseTableDetail.isPresent()) {
            CourseTableDetail courseTableDetail = optionalCourseTableDetail.get();
            CourseTable courseTable = courseTableDetail.getCourseTable();
            int studentNum = groupMemberRepository.countByGroupId(courseTable.getGroup().getId());
            CourseTableInfo courseTableInfo = new CourseTableInfo();
            courseTableInfo.setId(courseTable.getId());
            courseTableInfo.setGroupMemberNum(studentNum);
            courseTableInfo.setCourseId(courseTable.getCourse().getId());
            courseTableInfo.setCourseName(courseTable.getCourse().getCourseName());
            if (ObjectUtils.isNotEmpty(courseTable.getStudentType())) {
                courseTableInfo.setStudentType(courseTable.getStudentType().getIndex());
            }
            courseTableInfo.setCourseTeacherName(courseTable.getTeacherName());
            courseTableInfo.setCourseCode(courseTable.getCourse().getCourseCode() == null ? "" : courseTable.getCourse().getCourseCode());
            courseTableInfo.setSchoolYear(courseTable.getSchoolYear());
            courseTableInfo.setTerm(courseTable.getTerm());
            courseTableInfo.setSource(courseTable.getSource().getIndex());
            courseTableInfo.setWeekType(courseTable.getWeekType().getIndex());
            courseTableInfo.setGroupId(courseTable.getGroup().getId());
            courseTableInfo.setGroupName(courseTable.getGroup().getGroupName());
            courseTableInfo.setGroupNo(courseTable.getGroup().getGroupNo() == null ? "" : courseTable.getGroup().getGroupNo());
            courseTableInfo.setCollegeId(courseTable.getCollegeId());
            courseTableInfo.setCollegeName(courseTable.getCollegeName());
            if (ObjectUtils.isNotEmpty(courseTable.getCourseCategory())) {
                CourseCategory courseCategory = courseTable.getCourseCategory();
                courseTableInfo.setCourseCategoryId(String.valueOf(courseCategory.getId()));
                courseTableInfo.setCourseCategory(courseCategory.getCourseCategoryName());
            }
            if (ObjectUtils.isNotEmpty(courseTableDetail.getCourseType())) {
                CourseType courseType = courseTableDetail.getCourseType();
                courseTableInfo.setCourseTypeId(courseType.getId());
                courseTableInfo.setCourseTypeName(courseType.getCourseTypeName());
            }
            List<CourseTableDetailInfo> courseTableDetailInfos = new ArrayList<>();
            CourseTableDetailInfo courseTableDetailInfo = new CourseTableDetailInfo();
            courseTableDetailInfo.setId(courseTableDetail.getId());
            courseTableDetailInfo.setCourseDate(courseTableDetail.getCourseDate() == null ? "" :
                    DateUtils.formatDate("yyyy-MM-dd", courseTableDetail.getCourseDate()));
            courseTableDetailInfo.setWeek(courseTableDetail.getWeek());
            courseTableDetailInfo.setWeekNum(courseTableDetail.getWeekNum());
            courseTableDetailInfo.setSegment(courseTableDetail.getSegment());
            courseTableDetailInfo.setSegmentStartTime(formatDate(DATE, courseTableDetail.getCourseDate())
                    + " " + formatDate(TIME_SECOND, courseTableDetail.getSegmentStartTime()));
            courseTableDetailInfo.setSegmentEndTime(formatDate(DATE, courseTableDetail.getCourseDate())
                    + " " + formatDate(TIME_SECOND, courseTableDetail.getSegmentEndTime()));
            List<CourseSegment> courseSegmentList = courseSegmentRepository.findByCourseTableDetail(courseTableDetail);
            List<CourseTableDetailSegmentVo> courseTableDetailSegmentVoList = new ArrayList<>();
            for (CourseSegment courseSegment : courseSegmentList) {
                Segment segment = courseSegment.getSegment();
                if (ObjectUtils.isNotEmpty(segment)) {
                    CourseTableDetailSegmentVo courseTableDetailSegmentVo = new CourseTableDetailSegmentVo();
                    courseTableDetailSegmentVo.setSegment(segment.getSegmentPK().getSegment());
                    courseTableDetailSegmentVo.setSegmentStartTime(
                            DateUtils.formatDate("yyyy-MM-dd", courseTableDetail.getCourseDate()) + " " + segment.getStartTime());
                    courseTableDetailSegmentVo.segmentEndTime(
                            DateUtils.formatDate("yyyy-MM-dd", courseTableDetail.getCourseDate()) + " " + segment.getEndTime());
                    courseTableDetailSegmentVoList.add(courseTableDetailSegmentVo);
                }
            }
            courseTableDetailInfo.setCourseTableDetailSegmentVoList(courseTableDetailSegmentVoList);
            List<CourseTableDetailTeacherInfo> courseTableDetailTeacherInfos = new ArrayList<>();
            courseTableDetail.getCourseTableDetailTeacherList().forEach(courseTableDetailTeacher -> {
                composeCourseTableDetailTeacherInfo(courseTableDetailTeacher, courseTableDetailTeacherInfos);
            });
            courseTableDetailInfo.setCourseTableDetailTeachers(courseTableDetailTeacherInfos);
            courseTableDetailInfo.setSourceDataSource(courseTableDetail.getSourceDataSource());
            courseTableDetailInfo.setSourceDataSourceName(courseTableDetail.getSourceDataSourceName());
            courseTableDetailInfos.add(courseTableDetailInfo);
            courseTableInfo.setCourseTableDetails(courseTableDetailInfos);
            return new ResponseEntity<>(courseTableInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private static void composeCourseTableDetailTeacherInfo(
            CourseTableDetailTeacher courseTableDetailTeacher, List<CourseTableDetailTeacherInfo> courseTableDetailTeacherInfos) {
        CourseTableDetailTeacherInfo courseTableDetailTeacherInfo = new CourseTableDetailTeacherInfo();
        courseTableDetailTeacherInfo.setId(courseTableDetailTeacher.getId());
        courseTableDetailTeacherInfo.setTeacherId(courseTableDetailTeacher.getTeacherId());
        courseTableDetailTeacherInfo.setTeacherName(courseTableDetailTeacher.getTeacherName());
        courseTableDetailTeacherInfo.setTeacherTitle(courseTableDetailTeacher.getTeacherTitle());
        courseTableDetailTeacherInfo.setTeacherNo(courseTableDetailTeacher.getTeacherNo());
        courseTableDetailTeacherInfo.setTeacherCollegeName(courseTableDetailTeacher.getTeacherCollegeName() == null ? ""
                : courseTableDetailTeacher.getTeacherCollegeName());
        courseTableDetailTeacherInfo.setTeacherCollegeId(courseTableDetailTeacher.getTeacherCollegeId() == null ? ""
                : courseTableDetailTeacher.getTeacherCollegeId());
        courseTableDetailTeacherInfos.add(courseTableDetailTeacherInfo);
    }

    public ResponseEntity<CourseTableSimpleVo> getCourseTableSimpleVoByCurrentAndTeacherId(String teacherId) {
        CourseTableSimpleVo courseTableSimpleVo = new CourseTableSimpleVo();
        List<CourseTableDetail> courseTableDetails = getCourseTableDetailByCurrentAndTeacherId(teacherId);
        if (courseTableDetails != null && courseTableDetails.size() > 0) {
            CourseTableDetail courseTableDetail = courseTableDetails.get(0);
            courseTableSimpleVo.setCourseTableId(courseTableDetail.getCourseTable().getId());
            courseTableSimpleVo.setCourseTableDetailId(courseTableDetail.getId());
            courseTableSimpleVo.setGroupId(courseTableDetail.getCourseTable().getGroup().getId());
            courseTableSimpleVo.setCollegeId(courseTableDetail.getCourseTable().getCollegeId());
            courseTableSimpleVo.setCollegeName(courseTableDetail.getCourseTable().getCollegeName());
            List<CourseTableDetailRoomUser> courseTableDetailRoomUser = courseTableDetail.getCourseTableDetailRoomUserList();
            if (courseTableDetailRoomUser != null && courseTableDetailRoomUser.size() > 0) {
                List<String> roomIds = courseTableDetailRoomUser.stream().map(CourseTableDetailRoomUser::getRoomId).collect(toList());
                courseTableSimpleVo.setRoomIds(String.join(",", roomIds));
            }
            return new ResponseEntity<>(courseTableSimpleVo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<CourseTableDetail> getCourseTableDetailByCurrentAndTeacherId(String teacherId) {
        Date date = new Date();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime nowTime = LocalDateTime.now();
        String startTime = nowTime.plusMinutes(attendClassAdvanceTime).format(formatter);
        Date nowDate = DateUtils.stringToDate("yyyy-MM-dd", DateUtils.formatDate("yyyy-MM-dd", date));
        return courseTableDetailRepository.findCourseTableDetail(teacherId, nowTime.format(formatter), startTime, nowDate);
    }

    public long getCourseTableDetailCount() {
        return courseTableDetailRepository.countBySource(Source.DATA_DOCKING);
    }

    public void saveCourseTableDetails(List<CourseTableDetailResource> courseTableDetailResources) {
        List<CourseTableDetail> courseTableDetailList = new ArrayList<>();
        List<CourseSegment> courseSegmentList = new ArrayList<>();
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();
        List<CourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();
        List<Segment> tempSegments = new ArrayList<>();
        for (CourseTableDetailResource courseTableDetailResource : courseTableDetailResources) {
            Group group = groupRepository.findById(courseTableDetailResource.getGroupNo()).orElse(null);
            if (Objects.isNull(group)) {
                continue;
            }
            List<CourseTable> courseTables = courseTableRepository.findByGroup(group);
            if (!courseTables.isEmpty()) {
                CourseTable courseTable = courseTables.get(0);
                CourseTableDetail courseTableDetail = createCourseTableDetail(courseTableDetailResource, courseTable);
                String[] segments = courseTableDetailResource.getSegments().split(",");
                List<Segment> segmentList = segmentRepository.findByBuildName(courseTableDetailResource.getBuildName());
                if (CollectionUtils.isEmpty(segmentList)) {
                    segmentList = segmentRepository.findByBuildId("0");
                }
                tempSegments.clear();
                for (String segmentStr : segments) {
                    segmentList.stream().filter(segment -> segmentStr.equals(String.valueOf(segment.getSegmentPK().getSegment())))
                            .findFirst().ifPresent(buildSegment -> {
                                courseSegmentList.add(createScheduleSegment(courseTableDetail, buildSegment));
                                tempSegments.add(buildSegment);
                            });
                }
                if (!tempSegments.isEmpty()) {
                    courseTableDetail.setSegmentStartTime(DateUtils.stringToDate(DateUtils.TIME, tempSegments.get(0).getStartTime()));
                    courseTableDetail.setSegmentEndTime(DateUtils.stringToDate(DateUtils.TIME,
                            tempSegments.get(tempSegments.size() - 1).getEndTime()));
                }
                courseTableDetailTeacherList.addAll(courseTableDetailResource.getTeacherResourceList().stream().map(teacherResource -> {
                    CourseTableDetailTeacher courseTableDetailTeacher = new CourseTableDetailTeacher();
                    courseTableDetailTeacher.setCourseTableDetail(courseTableDetail);
                    courseTableDetailTeacher.setTeacherId(teacherResource.getTeacherId());
                    courseTableDetailTeacher.setTeacherName(teacherResource.getTeacherName());
                    courseTableDetailTeacher.setTeacherNo(teacherResource.getTeacherNo());
                    courseTableDetailTeacher.setTeacherCollegeId(teacherResource.getTeacherCollegeId());
                    courseTableDetailTeacher.setTeacherCollegeName(teacherResource.getTeacherCollegeName());
                    courseTableDetailTeacher.setCreateTime(new Date());
                    return courseTableDetailTeacher;
                }).collect(Collectors.toList()));
                if (org.apache.commons.lang3.StringUtils.isNotBlank(courseTableDetailResource.getRoomName())) {
                    CourseTableDetailRoomUser courseTableDetailRoomUser = new CourseTableDetailRoomUser();
                    courseTableDetailRoomUser.setCourseTableDetail(courseTableDetail);
                    courseTableDetailRoomUser.setRoomId(courseTableDetailResource.getRoomId());
                    courseTableDetailRoomUser.setRoomName(courseTableDetailResource.getRoomName());
                    courseTableDetailRoomUser.setRoomCode(courseTableDetailResource.getRoomId());
                    courseTableDetailRoomUser.setHasLive(courseTableDetailResource.getHasLive());
                    courseTableDetailRoomUser.setCreateTime(new Date());
                    courseTableDetailRoomUserList.add(courseTableDetailRoomUser);
                }
                courseTableDetailList.add(courseTableDetail);
            }
        }
        List<CourseTableDetail> courseTableDetails = courseTableDetailRepository.saveAll(courseTableDetailList);
        if (courseTableDetails != null) {
            courseSegmentRepository.saveAll(courseSegmentList);
            courseTableDetailTeacherRepository.saveAll(courseTableDetailTeacherList);
            courseTableDetailRoomUserRepository.saveAll(courseTableDetailRoomUserList);
        }
    }

    private CourseSegment createScheduleSegment(CourseTableDetail courseTableDetail, Segment buildSegment) {
        CourseSegment courseSegment = new CourseSegment();
        courseSegment.setCourseTableDetail(courseTableDetail);
        courseSegment.setSegment(buildSegment);
        return courseSegment;
    }

    private CourseTableDetail createCourseTableDetail(CourseTableDetailResource courseTableDetailResource, CourseTable courseTable) {
        CourseTableDetail courseTableDetail = new CourseTableDetail();
        CourseType courseType;
        courseTableDetail.setCourseDate(DateUtils.stringToDate(DateUtils.DATE, courseTableDetailResource.getCourseDate()));
        courseTableDetail.setCourseStatus(CourseStatus.NOT_CLASS);
        courseTableDetail.setCourseTable(courseTable);
        courseTableDetail.setSegment(courseTableDetailResource.getSegments());
        courseTableDetail.setWeek(courseTableDetailResource.getWeek());
        courseTableDetail.setWeekNum(Integer.valueOf(courseTableDetailResource.getWeekNum()));
        courseTableDetail.setSource(Source.DATA_DOCKING);
        courseTableDetail.setCreateTime(new Date());
        if (peCollege.equals(courseTableDetailResource.getCollegeName())) {
            courseType = courseTypeRepository.getOne(Constant.PE_COURSE_TYPE);
        } else if (englishCollege.equals(courseTableDetailResource.getCollegeName())) {
            courseType = courseTypeRepository.getOne(Constant.ENGLISH_COURSE_TYPE);
        } else {
            courseType = courseTypeRepository.getOne(Integer.valueOf(courseTableDetailResource.getCourseType()));
        }
        courseTableDetail.setCourseType(courseType);
        courseTableDetail.setCourseTypeName(courseType.getCourseTypeName());
        courseTableDetail.setCourseKind(CourseKind.THEORY_COURSE);
        courseTableDetail.setCourseName(courseTable.getCourseName());
        courseTableDetail.setTeacherNames(courseTableDetailResource.getTeacherResourceList().stream()
                .map(TeacherResource::getTeacherName).distinct().collect(joining(",")));
        courseTableDetail.setTeacherCollegeNames(courseTableDetailResource.getTeacherResourceList().stream()
                .map(TeacherResource::getTeacherCollegeName).distinct().collect(joining(",")));
        courseTableDetail.setTeacherCollegeIds(courseTableDetailResource.getTeacherResourceList().stream()
                .map(TeacherResource::getTeacherCollegeId).distinct().collect(joining(",")));
        return courseTableDetail;
    }

    public void deleteNowDateAndAfterCourse() {
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository.findAll(searchGreaterNowTimeCourseTables());
        courseTableDetailList = courseTableDetailList.stream().distinct().collect(toList());
        courseTableDetailList.addAll(courseTableDetailRepository.findNowDateAndAfterCourse(new Date(), Source.DATA_DOCKING));
        List<CourseSegment> courseSegmentList = new ArrayList<>();
        List<CourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();
        courseTableDetailList.forEach(courseTableDetail -> {
            courseSegmentList.addAll(courseSegmentRepository.findByCourseTableDetail(courseTableDetail));
            courseTableDetailRoomUserList.addAll(courseTableDetailRoomUserRepository.findByCourseTableDetail(courseTableDetail));
            courseTableDetailTeacherList.addAll(courseTableDetailTeacherRepository.findByCourseTableDetail(courseTableDetail));
        });
        courseTableDetailRepository.deleteAll(courseTableDetailList);
        courseSegmentRepository.deleteAll(courseSegmentList);
        courseTableDetailRoomUserRepository.deleteAll(courseTableDetailRoomUserList);
        courseTableDetailTeacherRepository.deleteAll(courseTableDetailTeacherList);
    }

    @Override
    protected EntityManager getEm() {
        return entityManager;
    }

    public ResponseEntity<ClassCourseTablePage> classesCourseTablesPagePost(AdministrativeVo administrative) {
        Term nowTerm = termService.getNowTerm();
        Pageable pageable = PageRequest.of(administrative.getPage() - 1, administrative.getRows());
        Page<CourseTableDetail> listCourseTableDetails = courseTableDetailRepository
                .findAll(courseTableDetailSpecificationByClass(administrative, nowTerm.getSchoolYear(),
                        nowTerm.getTerm().getIndex()), pageable);
        ClassCourseTablePage classCourseTablePage = new ClassCourseTablePage();
        int allNumber = getCount(courseTableDetailSpecificationByClass(administrative, nowTerm.getSchoolYear(),
                nowTerm.getTerm().getIndex()), CourseTableDetail.class);
        PageImpl pageNum = getPage(listCourseTableDetails.getContent(), pageable, allNumber);
        classCourseTablePage.setPageNum(pageNum.getTotalPages());
        classCourseTablePage.setTotalCount(allNumber);
        List<ClassCourseTable> classCourseTableList = getAllCourseTableList(listCourseTableDetails.getContent());
        classCourseTablePage.setCourseTableList(classCourseTableList);
        return new ResponseEntity<>(classCourseTablePage, HttpStatus.OK);
    }

    private List<ClassCourseTable> getAllCourseTableList(List<CourseTableDetail> courseTableDetailList) {
        List<ClassCourseTable> classCourseTables = new ArrayList<>();
        for (CourseTableDetail courseTableDetail : courseTableDetailList) {
            ClassCourseTable classCourseTable = new ClassCourseTable();
            classCourseTable.setCourseType(courseTableDetail.getCourseType().getId());
            classCourseTable.setCourseName(courseTableDetail.getCourseTable().getCourseName());
            classCourseTable.setCourseAttr(courseTableDetail.getCourseTable().getCourseCategory().getId());
            classCourseTable.setCourseAttrName(courseTableDetail.getCourseTable().getCourseCategory().getCourseCategoryName());
            if (courseTableDetail.getCourseTableDetailTeacherList().size() != 0) {
                String teacherName = courseTableDetail.getCourseTableDetailTeacherList().stream().map(courseTableDetailTeacher ->
                        courseTableDetailTeacher.getTeacherName() + ""
                ).collect(joining(","));
                classCourseTable.setTeacherName(teacherName);
            }
            if (courseTableDetail.getCourseTableDetailRoomUserList().size() != 0) {
                String roomNames = courseTableDetail.getCourseTableDetailRoomUserList()
                        .stream().map(CourseTableDetailRoomUser::getRoomName).collect(Collectors.joining(","));
                classCourseTable.setRoomName(roomNames);
            }
            classCourseTable.setTeachingClassName(courseTableDetail.getCourseTable().getGroup().getGroupName());
            classCourseTable.setCollegeName(courseTableDetail.getCourseTable().getCollegeName());
            classCourseTable.setStudentNumber(courseTableDetail.getCourseTable().getGroup().getGroupMemberList().size());
            classCourseTable.setWeekType(courseTableDetail.getCourseTable().getWeekType().getWeekTypeName());
            if (courseTableDetail.getCourseDate() != null) {
                classCourseTable.setCourseDate(TimeUtils.dateToStr(courseTableDetail.getCourseDate()));
            }
            if (courseTableDetail.getCourseSegmentList().size() != 0) {
                List<CourseSegment> courseSegments = courseTableDetail.getCourseSegmentList().stream()
                        .sorted(Comparator.comparing(courseSegment -> courseSegment.getSegment().getSegment())).collect(toList());
                String segment = courseSegments.stream()
                        .map(courseSegment -> courseSegment.getSegment().getSegment() + "").collect(joining(","));
                classCourseTable.setSegment("第" + segment + "节次");
            }
            classCourseTable.setWeek("第" + courseTableDetail.getWeek() + "周");
            classCourseTable.setWeekDay(weekDays[courseTableDetail.getWeekNum() - 1]);
            classCourseTables.add(classCourseTable);
        }
        return classCourseTables;
    }

    public ResponseEntity<CourseTypeCountResource> classesCourseTablesStatisticsPost(AdministrativeVo administrative) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = createCourseTypeCountSql(administrative, paramMap);
        Query query = entityManager.createNativeQuery(querySql, CourseTypeCount.class);
        paramMap.forEach(query::setParameter);
        List<CourseTypeCount> courseTypeCounts = query.getResultList();
        CourseTypeCountResource courseTypeCountResource = new CourseTypeCountResource();
        courseTypeCountResource.setEnglishCount(0);
        courseTypeCountResource.setSportsCount(0);
        courseTypeCountResource.setExperimentCount(0);
        courseTypeCountResource.setTheoryCount(0);
        if (courseTypeCounts.size() > 0) {
            courseTypeCounts.forEach(courseTypeCount -> {
                if (Objects.equals(courseTypeCount.getType(), "0")) {
                    courseTypeCountResource.setTheoryCount((int) getValue(courseTypeCount));
                } else if (Objects.equals(courseTypeCount.getType(), "1")) {
                    courseTypeCountResource.setExperimentCount((int) getValue(courseTypeCount));
                } else if (Objects.equals(courseTypeCount.getType(), "2")) {
                    courseTypeCountResource.setSportsCount((int) getValue(courseTypeCount));
                } else {
                    courseTypeCountResource.setEnglishCount((int) getValue(courseTypeCount));
                }
            });
        }
        return new ResponseEntity<>(courseTypeCountResource, HttpStatus.OK);
    }

    private Object getValue(CourseTypeCount courseTypeCount) {
        return courseTypeCount.getCount() == null || courseTypeCount.getCount() < 0 ? "0" :
                courseTypeCount.getCount();
    }

    private String createCourseTypeCountSql(AdministrativeVo administrative, Map<String, Object> paramMap) {
        Term nowTerm = termService.getNowTerm();
        String resultSql = "SELECT UUID() AS id, cty.id AS type,COUNT( DISTINCT ctd.id ) AS count,cty.sort_name FROM tb_course_table_detail ctd" +
                " LEFT JOIN tb_course_table ct ON ctd.course_table_id = ct.id  LEFT JOIN tb_group tg ON ct.group_id = tg.id " +
                " LEFT JOIN tb_administrative_class_and_group ac ON tg.id = ac.group_id " +
                " LEFT JOIN tb_course_segment ss ON ss.course_table_detail_id = ctd.id LEFT JOIN tb_course_type cty ON ctd.course_type_id = cty.id " +
                " LEFT OUTER JOIN tb_segment s ON ss.build_id = s.buildid  AND ss.segment = s.segment  where 1=1 ";
        resultSql += " and ct.school_year = :schoolYear and ct.term = :term ";
        paramMap.put("schoolYear", nowTerm.getSchoolYear());
        paramMap.put("term", nowTerm.getTerm().getIndex());
        if (ObjectUtils.isNotEmpty(administrative.getStudentType()) && StudentType.getStudentType(administrative.getStudentType()) != null) {
            resultSql += " and ct.student_type = :studentType";
            paramMap.put("studentType", administrative.getStudentType());
        }
        if (!org.springframework.util.StringUtils.isEmpty(administrative.getYear())) {
            resultSql += " and ac.year = :year ";
            paramMap.put("year", administrative.getYear().trim());
        }
        if (!org.springframework.util.StringUtils.isEmpty(administrative.getCollegeId())) {
            resultSql += " and ac.college_id = :collegeId ";
            paramMap.put("collegeId", administrative.getCollegeId());
        }
        if (!org.springframework.util.StringUtils.isEmpty(administrative.getMajorId())) {
            resultSql += " and ac.major_id = :majorId ";
            paramMap.put("majorId", administrative.getMajorId());
        }
        if (!org.springframework.util.StringUtils.isEmpty(administrative.getAdministrativeClass())) {
            resultSql += " and ac.class_name= :administrativeClass ";
            paramMap.put("administrativeClass", administrative.getAdministrativeClass());
        }
        if (!org.springframework.util.StringUtils.isEmpty(administrative.getSegment()) && administrative.getSegment() != 0) {
            resultSql += " and s.segment= :segment ";
            paramMap.put("segment", administrative.getSegment());
        }
        if (!org.springframework.util.StringUtils.isEmpty(administrative.getWeek()) && administrative.getWeek() != 0) {
            resultSql += " and cast( ctd.WEEK AS signed ) = :week";
            paramMap.put("week", administrative.getWeek());
        }
        if (!org.springframework.util.StringUtils.isEmpty(administrative.getWeekNum()) && administrative.getWeekNum() != 0) {
            resultSql += " and ctd.week_num = :weekNum ";
            paramMap.put("weekNum", administrative.getWeekNum());
        }
        resultSql += " GROUP BY cty.sort_name ";
        return resultSql;
    }

    public ResponseEntity<CourseTableDetailVo> getCourseTableDetailByDate(CourseTableDetailData courseTableDetailData) {
        Pageable pageable = PageRequest.of(courseTableDetailData.getPage() - 1, courseTableDetailData.getPageSize());
        Page<CourseTableDetail> courseTableDetailPages = courseTableDetailRepository
                .findAll(courseTableDetailSpecificationByDate(courseTableDetailData), pageable);
        CourseTableDetailVo courseTableDetailVo = new CourseTableDetailVo();
        courseTableDetailVo.setTotal((int) courseTableDetailPages.getTotalElements());
        courseTableDetailVo.setPageCount(courseTableDetailPages.getTotalPages());
        courseTableDetailVo.setPageSize(courseTableDetailData.getPageSize());
        courseTableDetailVo.setPage(courseTableDetailData.getPage());
        courseTableDetailVo.setCourseTableDetails(findByCourseTableDetails(courseTableDetailPages));
        return new ResponseEntity<>(courseTableDetailVo, HttpStatus.OK);
    }

    private List<CourseTableDetailVoResource> findByCourseTableDetails(Page<CourseTableDetail> courseTableDetailPages) {
        List<CourseTableDetailVoResource> courseTableDetailVoResources = new ArrayList<>();
        courseTableDetailPages.forEach(courseTableDetail -> {
            CourseTableDetailVoResource courseTableDetailVoResource = new CourseTableDetailVoResource();
            courseTableDetailVoResource.setId(courseTableDetail.getId());
            courseTableDetailVoResource.setDateTime(DateUtils.formatDate(DateUtils.DATE, courseTableDetail.getCourseDate()));
            courseTableDetailVoResource.setSegment("第" + courseTableDetail.getSegment() + "节次");
            courseTableDetailVoResource.setCourseName(courseTableDetail.getCourseTable().getCourseName());
            courseTableDetailVoResource.setCourseKind(courseTableDetail.getCourseKind().getIndex());
            List<String> teacherNames = new ArrayList<>();
            List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetail
                    .getCourseTableDetailTeacherList()
                    .stream()
                    .sorted(Comparator.comparing(
                            CourseTableDetailTeacher::getShowOrder,
                            Comparator.nullsLast(Comparator.naturalOrder())
                    ))
                    .collect(toList());
            for (CourseTableDetailTeacher courseTableDetailTeacher : courseTableDetailTeacherList) {
                teacherNames.add(courseTableDetailTeacher.getTeacherName()
                        + "(" + courseTableDetailTeacher.getTeacherNo() + "）");
            }
            courseTableDetailVoResource.setTeacherName(StringUtils.join(teacherNames, "、"));
            String roomNames = courseTableDetail.getCourseTableDetailRoomUserList().stream()
                    .map(CourseTableDetailRoomUser::getRoomName).collect(Collectors.joining("、"));
            courseTableDetailVoResource.setRoomName(roomNames);
            if (Objects.nonNull(courseTableDetail.getCourseTable())) {
                CourseTable courseTable = courseTableDetail.getCourseTable();
                courseTableDetailVoResource.setCourseTableCollegeId(courseTable.getCollegeId());
                courseTableDetailVoResource.setCourseTableCollegeName(courseTable.getCollegeName());
                Group group = courseTable.getGroup();
                if (group != null) {
                    courseTableDetailVoResource.setClassName(group.getGroupName());
                    courseTableDetailVoResource.setClassMember(group.getGroupMemberList() == null ? null
                            : group.getGroupMemberList().stream()
                            .filter(groupMember -> GroupMemberStatus.NORMAL.equals(groupMember.getGroupMemberStatus()))
                            .collect(toList())
                            .size());
                }
            }
            if ("实验课".equals(courseTableDetail.getCourseKind().getValue())) {
                String projectNames = courseTableDetail.getCourseTableDetailProjects().stream()
                        .map(CourseTableDetailProject::getProjectName).collect(Collectors.joining("、"));
                courseTableDetailVoResource.setProject(projectNames);
                List<String> itemTeachers = new ArrayList<>();
                for (CourseTableDetailRoomUser courseTableDetailRoomUser : courseTableDetail.getCourseTableDetailRoomUserList()) {
                    if (courseTableDetailRoomUser.getItemTeacherName() != null) {
                        String itemTeacherNames = courseTableDetailRoomUser.getItemTeacherName()
                                + "(" + courseTableDetailRoomUser.getItemTeacherNo() + "）";
                        if (!itemTeachers.contains(itemTeacherNames)) {
                            itemTeachers.add(itemTeacherNames);
                        }
                    }
                }
                if (itemTeachers.size() != 0) {
                    courseTableDetailVoResource.setItemTeacherName(StringUtils.join(itemTeachers, "、"));
                }
            }
            courseTableDetailVoResources.add(courseTableDetailVoResource);
        });
        return courseTableDetailVoResources;
    }

    public ResponseEntity<List<NowDayCourseTableDetail>> getNowDayCourseTableDetails() {
        String courseDate = TimeUtils.getNowDate();
        String querySQL = this.createQuerySql(courseDate);
        Query query = entityManager.createNativeQuery(querySQL);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<NowDayCourseTableDetail> nowDayCourseTableDetails = query.getResultList();
        if (nowDayCourseTableDetails != null && nowDayCourseTableDetails.size() > 0) {
            return new ResponseEntity<List<NowDayCourseTableDetail>>(nowDayCourseTableDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private String createQuerySql(String courseDate) {
        String sql = "select tb_course_table.id as courseTableId, " +
                "tb_course_table.college_id as collegeId, " +
                "tb_course_table.college_name as collegeName, " +
                "tb_course_table_detail.id as courseTableDetailId, " +
                "tb_course_table_detail_room_user.room_id as roomId, " +
                "tb_course_table_detail_room_user.room_name as roomName " +
                "from  tb_course_table inner join tb_course_table_detail inner join tb_course_table_detail_room_user " +
                "on tb_course_table.id = tb_course_table_detail.course_table_id " +
                "and  tb_course_table_detail.id = tb_course_table_detail_room_user.course_table_detail_id " +
                "where tb_course_table_detail.course_date = ";
        sql += "'" + courseDate + "'" + ";";
        return sql;
    }

    private List<CourseTableDetail> getByDelayTimeAndTeacherId(String teacherId, String advanceTime, Integer delayType, String delayTime) {
        Date date = new Date();
        LocalDateTime nowTime = LocalDateTime.now();

        String startTime = nowTime.plusMinutes(Integer.valueOf(advanceTime)).format(DateTimeFormatter.ofPattern("HH:mm"));
        Date nowDate = DateUtils.stringToDate("yyyy-MM-dd", DateUtils.formatDate("yyyy-MM-dd", date));
        String endTime;
        if (0 == delayType) {
            endTime = nowTime.minusMinutes(Integer.valueOf(delayTime)).format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            endTime = "00:00";
        }
        return courseTableDetailRepository.findCourseTableDetail(teacherId, endTime, startTime, nowDate);
    }

    public ResponseEntity<List<EvaluationCourseTableVo>> getEvaluationCourseTable(
            String teacherId, String advanceTime, String delayTime, Integer delayType) {
        List<CourseTableDetail> courseTableDetails = getByDelayTimeAndTeacherId(teacherId, advanceTime, delayType, delayTime);
        courseTableDetails = courseTableDetails.stream().distinct().collect(toList());
        if (courseTableDetails != null && courseTableDetails.size() > 0) {
            List<EvaluationCourseTableVo> evaluationCourseTableVos = new ArrayList<>();
            for (CourseTableDetail courseTableDetail : courseTableDetails) {
                CourseTable courseTable = courseTableDetail.getCourseTable();
                EvaluationCourseTableVo evaluationCourseTableVo = new EvaluationCourseTableVo();
                evaluationCourseTableVo.setCourseTableDetailId(courseTableDetail.getId());
                Group group = courseTable.getGroup();
                evaluationCourseTableVo.setGroupId(group.getId());
                evaluationCourseTableVo.setClassNickName(group.getClassNickName());
                evaluationCourseTableVo.setClassCompositionName(group.getClassCompositionName());
                evaluationCourseTableVo.setSource(group.getSource().getIndex());
                evaluationCourseTableVo.setGroupNo(courseTable.getGroup().getGroupNo());
                evaluationCourseTableVo.setGroupName(courseTable.getGroup().getGroupName());
                evaluationCourseTableVo.setRoomId(courseTableDetail.getCourseTableDetailRoomUserList().stream()
                        .map(CourseTableDetailRoomUserList ->
                                CourseTableDetailRoomUserList.getRoomId() + "").collect(joining(",")));
                evaluationCourseTableVo.setRoomName(courseTableDetail.getCourseTableDetailRoomUserList().stream()
                        .map(CourseTableDetailRoomUserList ->
                                CourseTableDetailRoomUserList.getRoomName() + "").collect(joining(",")));
                evaluationCourseTableVo.setSegment(courseTableDetail.getSegment());
                evaluationCourseTableVo.setWeek(courseTableDetail.getWeek());
                evaluationCourseTableVo.setWeekNum(courseTableDetail.getWeekNum());
                evaluationCourseTableVo.setCollegeId(courseTable.getCollegeId());
                College college = collegeRepository.findById(courseTable.getCollegeId()).get();
                evaluationCourseTableVo.setCollegeCode(college.getCollegeCode());
                evaluationCourseTableVo.setCollegeName(courseTable.getCollegeName());
                String courseDate = TimeUtils.dateToStr(courseTableDetail.getCourseDate());
                evaluationCourseTableVo.setCourseDate(courseDate);
                evaluationCourseTableVo.setCourseId(courseTable.getCourse().getId());
                evaluationCourseTableVo.setCourseCode(courseTable.getCourse().getCourseCode());
                evaluationCourseTableVo.setCourseName(courseTable.getCourse().getCourseName());
                evaluationCourseTableVo.setCourseTableId(courseTable.getId());
                if (ObjectUtils.isNotEmpty(courseTable.getStudentType())) {
                    evaluationCourseTableVo.setStudentType(courseTable.getStudentType().getIndex());
                }
                evaluationCourseTableVo.setCourseTypeId(courseTableDetail.getCourseType().getId());
                evaluationCourseTableVo.setCourseTypeName(courseTableDetail.getCourseType().getCourseTypeName());
                evaluationCourseTableVo.setSchoolYear(courseTable.getSchoolYear());
                evaluationCourseTableVo.setTerm(courseTable.getTerm());
                evaluationCourseTableVo.setStartTime(courseDate + " " +
                        TimeUtils.dateToString(courseTableDetail.getSegmentStartTime()));
                evaluationCourseTableVo.setEndTime(courseDate + " " +
                        TimeUtils.dateToString(courseTableDetail.getSegmentEndTime()));
                evaluationCourseTableVo.setCourseTableCount(courseTable.getCourseTableDetailList().size());
                evaluationCourseTableVos.add(evaluationCourseTableVo);
            }
            return new ResponseEntity<>(evaluationCourseTableVos, HttpStatus.OK);
        }
        return new ResponseEntity(ErrorResult.customError("未找到老师当前正在上课的课表信息"), HttpStatus.NOT_FOUND);
    }

    //region 获取教室占用课表信息
    public ResponseEntity<OccupancyResource> getOccupancyCourseTableDetail(Integer segment, String courseDate, String roomId) {
        OccupancyResource occupancyResource;
        String sql = "select ct.teacher_name as teacherName,ct.course_name as courseName, " +
                " ct.college_name as collegeName,ct.group_id as groupId" +
                " from tb_course_table_detail ctd" +
                " inner join tb_course_table ct on ct.id = ctd.course_table_id" +
                " inner join tb_course_segment cs on ctd.id = cs.course_table_detail_id" +
                " inner join tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id" +
                " where ctd.course_date = :courseDate" +
                " and ctdru.room_id = :roomId" +
                " and cs.segment = :segment limit 1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseDate", courseDate);
        paramMap.put("roomId", roomId);
        paramMap.put("segment", segment);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(OccupancyResource.class));
        List<OccupancyResource> resultList = query.getResultList();
        if (resultList.size() > 0) {
            occupancyResource = resultList.get(0);
            paramMap = new HashMap<>();
            String groupSql = "select g.group_name as className, count(1) as studentCount,g.id as groupId" +
                    " from tb_group g" +
                    " inner join tb_group_member gm on g.id = gm.group_id " +
                    " and g.id = :groupId ";
            paramMap.put("groupId", occupancyResource.getGroupId());
            Query groupQuery = entityManager.createNativeQuery(groupSql);
            paramMap.forEach(groupQuery::setParameter);
            groupQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(OccupancyResource.class));
            List<OccupancyResource> groupQueryResultList = groupQuery.getResultList();
            if (groupQueryResultList.size() > 0) {
                OccupancyResource groupOccupancyResource = groupQueryResultList.get(0);
                occupancyResource.setClassName(groupOccupancyResource.getClassName());
                occupancyResource.setStudentCount(groupOccupancyResource.getStudentCount());
            } else {
                return new ResponseEntity(ErrorResult.customError("未找到班级信息"), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(ErrorResult.customError("未找到教室占用课表信息"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(occupancyResource, HttpStatus.OK);
    }

    //endregion
    //region 根据上课时间获取课表信息
    public ResponseEntity<List<SegmentCourseTableResource>> getSegmentCourseTableResource(String courseDate) {
        List<SegmentCourseTableResource> segmentCourseTableResources;
        Map<String, Object> paramMap = new HashMap<>();
        String searchSql = getSearchCourseTableSql(courseDate, paramMap);
        Query query = entityManager.createNativeQuery(searchSql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(SegmentCourseTableResource.class));
        segmentCourseTableResources = query.getResultList();
        if (CollectionUtils.isEmpty(segmentCourseTableResources)) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(segmentCourseTableResources, HttpStatus.OK);
    }

    private String getSearchCourseTableSql(String courseDate, Map<String, Object> paramMap) {
        String sql = "select distinct ctd.segment as segment,ctdru.room_name as roomName,ctdru.room_id as roomId," +
                " DATE_FORMAT(ctd.course_date,'%Y-%m-%d') as courseDate,ctd.week as week,ctd.week_num as weekNum" +
                " from tb_course_table_detail ctd" +
                " inner join tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id" +
                " and ctd.course_date>=  :courseDate ";
        paramMap.put("courseDate", courseDate);
        return sql;
    }

    //endregion
    public Integer getTeachingCenterTeachingQuantity(String teachingCenterId) {
        Term term = getTerm();
        if (term == null) {
            return 0;
        }
        List<ClassroomBaseInfoVo> classroomBaseInfoVoList = classRoomService.getTeachingCenterClassroomList(teachingCenterId);
        if (classroomBaseInfoVoList.size() == 0) {
            return 0;
        }
        String roomIds = classroomBaseInfoVoList.stream().map(classroomBaseInfoVo
                -> "'" + classroomBaseInfoVo.getRoomId() + "'").collect(joining(","));
        Date nowDate = new Date();
        String nowDateToString = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String nowTimeToString = DateUtils.formatDate(DateUtils.TIME_SECOND, nowDate);
        String sql = " SELECT DISTINCT ctd.course_table_id AS 'courseTableId',ctd.id as 'courseTableDetailId' ,segment AS 'segment'"
                + " from tb_course_table_detail  ctd "
                + " INNER JOIN tb_course_table_detail_room_user stdru on ctd.id=stdru.course_table_detail_id "
                + " INNER JOIN tb_course_table ct on ct.id=ctd.course_table_id "
                + " where ctd.course_date<='" + nowDateToString + "' and ctd.segment_start_time<='" + nowTimeToString + "' "
                + " and ct.school_year ='" + term.getSchoolYear() + "' and ct.term=" + term.getTerm().getIndex()
                + " and stdru.room_id in (" + roomIds + ")";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, String>> courseTableDetailMapList = query.getResultList();
        if (courseTableDetailMapList.size() == 0) {
            return 0;
        }
        String courseTableIds = courseTableDetailMapList.stream().map(courseTableDetailMap
                -> "'" + courseTableDetailMap.get("courseTableId") + "'").collect(joining(","));
        List<Map<String, Object>> courseTableGroupMemberCountMapList = getCourseTableGroupMemberCount(courseTableIds);
        return courseTableDetailMapList
                .stream().mapToInt(courseTableDetailMap -> {
                    Map<String, Object> filterCourseTableGroupMemberCountMap = courseTableGroupMemberCountMapList
                            .stream().filter(courseTableGroupMemberCountMap ->
                                    courseTableGroupMemberCountMap.get("courseTableId").equals(courseTableDetailMap.get("courseTableId"))
                            ).findFirst().orElse(null);
                    if (filterCourseTableGroupMemberCountMap != null && StringUtils.isNotBlank(courseTableDetailMap.get("segment"))) {
                        int segmentCount = courseTableDetailMap.get("segment").split(",").length;
                        Object groupMemberCount = filterCourseTableGroupMemberCountMap.get("groupMemberCount");
                        return segmentCount * Integer.parseInt(groupMemberCount + "");
                    } else {
                        return 0;
                    }
                }).sum();
    }

    private Term getTerm() {
        Term term = termService.getNowTerm();
        if (term == null) {
            term = termRepository.findTop1ByEndDateLessThanOrderByStartDateDesc(new Date());
        }
        return term;
    }

    private List<Map<String, Object>> getCourseTableGroupMemberCount(String courseTableIds) {
        String sql = " SELECT a.*  from (SELECT DISTINCT ct.id as 'courseTableId' " +
                " ,(SELECT  COUNT(1) from tb_group_member gm where gm.group_id=ct.group_id and group_member_status= 0 ) as groupMemberCount " +
                " from  tb_course_table ct " +
                " where ct.id in (" + courseTableIds + ") ) a  where  a.groupMemberCount > 0";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public Integer getTeacherInClassQuantity(String teachingCenterId) {
        Term term = getTerm();
        if (term == null) {
            return 0;
        }
        List<ClassroomBaseInfoVo> classroomBaseInfoVoList = classRoomService.getTeachingCenterClassroomList(teachingCenterId);
        if (classroomBaseInfoVoList.size() == 0) {
            return 0;
        }
        String roomIds = classroomBaseInfoVoList.stream().map(classroomBaseInfoVo
                -> "'" + classroomBaseInfoVo.getRoomId() + "'").collect(joining(","));
        Date nowDate = new Date();
        String nowDateToString = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String nowTimeToString = DateUtils.formatDate(DateUtils.TIME_SECOND, nowDate);
        String sql = "select COUNT(DISTINCT ctdt.teacher_id ) as teacherInClassCount "
                + " from tb_course_table_detail_teacher  ctdt "
                + " INNER JOIN tb_course_table_detail ctd on ctd.id=ctdt.course_table_detail_id "
                + " INNER JOIN tb_course_table ct on ct.id=ctd.course_table_id "
                + " INNER JOIN tb_course_table_detail_room_user stdru on ctd.id=stdru.course_table_detail_id "
                + " where ctd.course_date<='" + nowDateToString + "' and ctd.segment_start_time<='" + nowTimeToString + "' "
                + " and ct.school_year ='" + term.getSchoolYear() + "' and ct.term=" + term.getTerm().getIndex()
                + " and stdru.room_id in (" + roomIds + ")";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> teacherInClassCountMap = (Map<String, Object>) query.getResultList().get(0);
        return ((BigInteger) teacherInClassCountMap.get("teacherInClassCount")).intValue();
    }

    public List<CourseTableDetailInClassResource> getCourseTableDetailInClassList
            (String courseNameOrTeacherName, CourseIdAndCollegeIdQuery courseIdAndCollegeIdQuery) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT DISTINCT ctd.id  AS courseTableDetailId, c.id as courseId,c.course_name as courseName ," +
                " (SELECT COUNT(1) from tb_group_member gm where gm.group_id=ct.group_id and gm.group_member_status=0) AS studentCount, " +
                " ctd.teacher_names AS teacherName, " +
                " group_concat(ctdru.room_name) AS classroomName, " +
                " group_concat(ctdru.room_id) AS classroomId " +
                " from tb_course_table_detail ctd" +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                " INNER JOIN tb_course c ON ct.course_id = c.id" +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment " +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id ";
        Date nowDate = new Date();
        String day = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String time = DateUtils.formatDate(DateUtils.TIME, nowDate);
        sql += " where c.use_state=1 and ctd.course_date = '" + day + "' and s.start_time<='" + time + "'" +
                " and s.end_time>='" + time + "' " +
                " and  ctdru.has_live=1  and ctdru.room_id is not null";
        if (org.apache.commons.lang3.StringUtils.isNotBlank(courseNameOrTeacherName)) {
            courseNameOrTeacherName = CharactersReplace.replaceCharacters(courseNameOrTeacherName);
            sql += " and (c.course_name like '%" + courseNameOrTeacherName.trim() + "%' " +
                    " or  ctd.teacher_names like '%" + courseNameOrTeacherName.trim() + "%' )";
        }
        if (courseIdAndCollegeIdQuery == null) {
            sql += " and (c.id in ('') or c.college_id in ('')) ";
        } else {
            if (courseIdAndCollegeIdQuery.getCourseIdList() == null || courseIdAndCollegeIdQuery.getCourseIdList().size() == 0) {
                sql += " and (c.id in ('')";
            } else {
                String courseIds = courseIdAndCollegeIdQuery.getCourseIdList().stream()
                        .map(courseId -> "'" + courseId + "'").collect(joining(","));
                sql += " and (c.id  in (" + courseIds + ")";
            }
            if (courseIdAndCollegeIdQuery.getCollegeIdList() == null || courseIdAndCollegeIdQuery.getCollegeIdList().size() == 0) {
                sql += " or c.college_id in (''))";
            } else {
                String collegeIds = courseIdAndCollegeIdQuery.getCollegeIdList().stream()
                        .map(collegeId -> "'" + collegeId + "'").collect(joining(","));
                sql += " or c.college_id in (" + collegeIds + "))";
            }
        }
        if (StringUtils.isNotBlank(courseIdAndCollegeIdQuery.getIsDistinguishCourseStudentType())
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(courseIdAndCollegeIdQuery.getIsDistinguishCourseStudentType())) {
            if (StringUtils.isNotBlank(courseIdAndCollegeIdQuery.getCourseStudentTypeIds())) {
                String result = convertToQuotedString(courseIdAndCollegeIdQuery.getCourseStudentTypeIds());
                sql += " and ct.student_type in (" + result + ")";
            } else {
                sql += " and 1=0";
            }
        }
        sql += " GROUP BY ctd.id ORDER BY ctdru.room_id ; ";
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    //根据老师姓名查询本学期正在上或已经上过的课程以及课程对应的班级
    public Set<CourseTableSuperviseVo> getTeacherGroupInfos(String teacherId, String isDistinguishCourseStudentType,
                                                            String courseStudentTypeIds, Boolean isNeedFilterPoliticalCourse) {
        Set<CourseTableSuperviseVo> courseTableSuperviseVos = new HashSet<>();
        List<CourseTableSuperviseModel> courseTableSuperviseModelList = new ArrayList<>();
        try {
            Term term = termService.getNowTerm();
            if (!org.springframework.util.StringUtils.isEmpty(teacherId)) {
                String querySQL = " SELECT b.course_id as courseId, e.course_code as courseCode, " +
                        "  e.course_name AS courseName, f.id as groupId, " +
                        "  f.group_no as groupNo, f.group_name as groupName, " +
                        "  b.id as courseTableId, " +
                        "  MAX(a.teacher_college_ids) as teacherCollegeIds, " +
                        "  MAX(a.teacher_college_names) as teacherCollegeNames,b.student_type as studentType " +
                        "FROM   tb_course_table_detail a " +
                        "  LEFT OUTER JOIN tb_course_table b ON a.course_table_id = b.id " +
                        "  LEFT OUTER JOIN tb_course_table_detail_teacher c ON a.id = c.course_table_detail_id " +
                        "  LEFT OUTER JOIN tb_course_table_detail_room_user d ON a.id = d.course_table_detail_id  " +
                        "  LEFT OUTER JOIN tb_course e on b.course_id = e.id " +
                        "  LEFT OUTER JOIN tb_group f on b.group_id = f.id " +
                        "WHERE c.teacher_id =:teacherId AND b.school_year =:schoolYear AND b.term =:term  ";
                Map<String, Object> paramMap = new HashMap<>();
                if (Objects.nonNull(isNeedFilterPoliticalCourse) && isNeedFilterPoliticalCourse) {
                    querySQL += " and b.course_id not in  (SELECT DISTINCT course_id  FROM `tb_course_expansion` where " +
                            " expansion_key='politicalCourse')";
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(isDistinguishCourseStudentType)
                        && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(isDistinguishCourseStudentType)) {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(courseStudentTypeIds)) {
                        List<String> courseStudentTypeIdList = Arrays.asList(courseStudentTypeIds.split(","));
                        querySQL += " and b.student_type in (:courseStudentTypeIdList)";
                        paramMap.put("courseStudentTypeIdList", courseStudentTypeIdList);
                    } else {
                        querySQL += " and 1=0 ";
                    }
                }
                querySQL += " group by b.course_id,e.course_code,e.course_name,f.id,f.group_no,f.group_name,b.id ";
                Query query = entityManager.createNativeQuery(querySQL);
                query.setParameter("teacherId", teacherId);
                query.setParameter("schoolYear", term.getSchoolYear());
                query.setParameter("term", term.getTerm().getIndex());
                paramMap.forEach(query::setParameter);
                query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableSuperviseModel.class));
                courseTableSuperviseModelList = query.getResultList();
            }
            Map<String, List<CourseTableSuperviseModel>> courseTableSuperviseModelMap = courseTableSuperviseModelList.stream()
                    .collect(Collectors.groupingBy(CourseTableSuperviseModel::getCourseId));
            courseTableSuperviseModelMap.forEach((key, keyCourseTableSuperviseModelList) -> {
                CourseTableSuperviseVo courseTableSuperviseVo = new CourseTableSuperviseVo();
                courseTableSuperviseVo.setCourseId(keyCourseTableSuperviseModelList.get(0).getCourseId());
                courseTableSuperviseVo.setCourseCode(keyCourseTableSuperviseModelList.get(0).getCourseCode());
                courseTableSuperviseVo.setCourseName(keyCourseTableSuperviseModelList.get(0).getCourseName());
                courseTableSuperviseVo.setCollegeId(keyCourseTableSuperviseModelList.get(0).getTeacherCollegeIds());
                courseTableSuperviseVo.setCollegeName(keyCourseTableSuperviseModelList.get(0).getTeacherCollegeNames());
                keyCourseTableSuperviseModelList.forEach(keyCourseTableSuperviseModel -> {
                    CourseGroupSuperviseVo groupSuperviseVo = new CourseGroupSuperviseVo();
                    groupSuperviseVo.setGroupId(keyCourseTableSuperviseModel.getGroupId());
                    groupSuperviseVo.setGroupName(keyCourseTableSuperviseModel.getGroupName());
                    groupSuperviseVo.setGroupNo(keyCourseTableSuperviseModel.getGroupNo());
                    groupSuperviseVo.setCourseTableId(keyCourseTableSuperviseModel.getCourseTableId());
                    groupSuperviseVo.setStudentType(String.valueOf(keyCourseTableSuperviseModel.getStudentType()));
                    if (courseTableSuperviseVo.getCourseGroup() == null) {
                        Set<CourseGroupSuperviseVo> courseGroupSuperviseVos = new HashSet<>();
                        courseGroupSuperviseVos.add(groupSuperviseVo);
                        courseTableSuperviseVo.setCourseGroup(courseGroupSuperviseVos);
                    } else {
                        courseTableSuperviseVo.getCourseGroup().add(groupSuperviseVo);
                    }
                });
                courseTableSuperviseVos.add(courseTableSuperviseVo);
            });
            return courseTableSuperviseVos;
        } catch (Exception e) {
            e.printStackTrace();
            return courseTableSuperviseVos;
        }
    }

    public List<CourseTableIdAndCourseTableDetailId> getIdByTime(String nowDate, String nowDateMin) {
        String sql = this.sql(nowDate, nowDateMin);
        List<CourseTableIdAndCourseTableDetailId> resultList =
                entityManager.createNativeQuery(sql, CourseTableIdAndCourseTableDetailId.class).getResultList();
        return resultList;
    }

    public String sql(String nowDate, String nowDateMin) {
        StringBuilder querySQL = new StringBuilder("SELECT id AS 'id', " +
                "tb_course_table_detail.course_table_id   FROM `tb_course_table_detail`  WHERE 1 = 1");
        if (!StringUtils.isEmpty(nowDate)) {
            querySQL.append(" AND `course_date` = '" + nowDate + "'");
        }
        if (!StringUtils.isEmpty(nowDateMin)) {
            querySQL.append(" AND `segment_start_time` <='" + nowDateMin + "' AND `segment_end_time` >='" + nowDateMin + "'");
        }
        return querySQL.toString();
    }

    public CourseTableDetailPageResource getLiveCourseTableDetailPage(CourseTableDetailPageRequest courseTableDetailPageRequest,
                                                                      String collegeName, String collegeId) {
        CourseTableDetailPageResource pageResource = new CourseTableDetailPageResource();
        Map<String, Object> paramMap = new HashMap<>();
        String baseSql = courseTableDetailExtendService.createBaseSql(courseTableDetailPageRequest, paramMap, collegeId);
        Query queryCount = entityManager.createNativeQuery(createCountSql(baseSql, courseTableDetailPageRequest, collegeName, collegeId, paramMap));
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        int count = ((BigInteger) resMap.get("allNumber")).intValue();
        if (count == 0) {
            pageResource.setTotal(0);
            pageResource.setPageCount(0);
            pageResource.setPageSize(courseTableDetailPageRequest.getPageSize());
            pageResource.setPage(courseTableDetailPageRequest.getPage());
            pageResource.setCourseTableDetailList(new ArrayList<>());
        } else {
            String roomIdSortList = getSortBySchoolDistrictBuildingLayerClassRoomIdList();
            String sql = courseTableDetailExtendService.
                    createCourseTableDetailSql(baseSql, courseTableDetailPageRequest, roomIdSortList, collegeName, collegeId);
            Query queryData = entityManager.createNativeQuery(sql);
            paramMap.forEach(queryData::setParameter);
            queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailResource.class));
            pageResource.setTotal(count);
            pageResource.setPageCount((int) Math.ceil((double) count /
                    (double) courseTableDetailPageRequest.getPageSize()));
            pageResource.setPageSize(courseTableDetailPageRequest.getPageSize());
            pageResource.setPage(courseTableDetailPageRequest.getPage());
            pageResource.setCourseTableDetailList(queryData.getResultList());
        }
        return pageResource;
    }

    public String getSortBySchoolDistrictBuildingLayerClassRoomIdList() {
        String url = classRoom + Constant.GET_CLASSROOM_ID_AFTER_SORTING + validKey + Md5Utils.md5(signKey);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            StringBuilder roomIdSB = new StringBuilder(Joiner.on("','").join(responseEntity.getBody()));
            roomIdSB.insert(0, "'");
            roomIdSB.append("'");
            return roomIdSB.toString();
        }
        log.info("restTemplate接口：" + url + "访问失败，默认空id数组导致排序失效");
        return "";
    }

    private String createCountSql(String baseSql, CourseTableDetailPageRequest courseTableDetailPageRequest, String collegeName, String collegeId,
                                  Map<String, Object> paramMap) {
        String countSql = "select count(1) as allNumber from (" + baseSql + ") a where 1=1 ";
        if (StringUtils.isNotBlank(courseTableDetailPageRequest.getSearchParams())) {
            String tempString = CharactersReplace.replaceCharacters(courseTableDetailPageRequest.getSearchParams());
            countSql += " and (a.courseName like :searchParam  or a.teacherName like :searchParam  " +
                    " or a.roomName like :searchParam) ";
            paramMap.put("searchParam", "%" + tempString + "%");
        } else {
            if (StringUtils.isNotBlank(courseTableDetailPageRequest.getSearchCourseName())) {
                countSql += " and a.courseName like :searchCourseName";
                paramMap.put("searchCourseName", "%" + CharactersReplace.replaceCharacters(courseTableDetailPageRequest.getSearchCourseName()) + "%");
            }
            if (StringUtils.isNotBlank(courseTableDetailPageRequest.getSearchRoomName())) {
                countSql += " and a.roomName like :searchRoomName";
                paramMap.put("searchRoomName", "%" + CharactersReplace.replaceCharacters(courseTableDetailPageRequest.getSearchRoomName()) + "%");
            }
            if (StringUtils.isNotBlank(courseTableDetailPageRequest.getSearchTeacherName())) {
                countSql += " and a.teacherName like :searchTeacherName";
                paramMap.put("searchTeacherName",
                        "%" + CharactersReplace.replaceCharacters(courseTableDetailPageRequest.getSearchTeacherName()) + "%");
            }
        }

        if (StringUtils.isNotBlank(collegeId)) {
            countSql += " and a.collegeId = :queryCollegeId";
            paramMap.put("queryCollegeId", collegeId);
        }
        if (StringUtils.isNotBlank(collegeName)) {
            String tempString = CharactersReplace.replaceCharacters(collegeName);
            countSql += " and a.collegeName like :queryCollegeName ";
            paramMap.put("queryCollegeName", "%" + tempString + "%");
        }
        return countSql;
    }


    public CoursetableResourceVo getCourseByCourseTableDetailId(String courseTableDetailId) {
        CoursetableResourceVo coursetableResourceVo = new CoursetableResourceVo();
        final Optional<CourseTableDetail> courseTableDetailOptional = courseTableDetailRepository.findById(courseTableDetailId);
        if (!courseTableDetailOptional.isPresent()) {
            return coursetableResourceVo;
        }
        CourseTableDetail courseTableDetail = courseTableDetailOptional.get();
        buildCourseTableResourceVo(coursetableResourceVo, courseTableDetail);
        return coursetableResourceVo;
    }

    private void buildCourseTableResourceVo(CoursetableResourceVo coursetableResourceVo, CourseTableDetail courseTableDetail) {
        CourseTable courseTable = courseTableDetail.getCourseTable();
        coursetableResourceVo.setCourseTableDetailId(courseTableDetail.getId());
        coursetableResourceVo.setCourseDate(courseTableDetail.getCourseDate());
        coursetableResourceVo.setId(courseTableDetail.getCourseTable().getId());
        coursetableResourceVo.setCourseName(courseTableDetail.getCourseTable().getCourseName());
        coursetableResourceVo.setCollegeId(courseTableDetail.getCourseTable().getCollegeId());
        coursetableResourceVo.setCollegeName(courseTableDetail.getCourseTable().getCollegeName());
        coursetableResourceVo.setSchoolYear(courseTableDetail.getCourseTable().getSchoolYear());
        coursetableResourceVo.setSegmentList(courseTableDetail.getSegment());
        coursetableResourceVo.setTeacherName(courseTableDetail.getCourseTable().getTeacherName());
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetail.getCourseTableDetailTeacherList();
        String teacherIds = courseTableDetailTeacherList.stream().map(CourseTableDetailTeacher::getTeacherId).distinct().collect(joining(","));
        coursetableResourceVo.setTeacherId(teacherIds);
        String teacherNo = courseTableDetailTeacherList.stream().map(CourseTableDetailTeacher::getTeacherNo).distinct().collect(joining(","));
        coursetableResourceVo.setTeacherNo(teacherNo);
        coursetableResourceVo.setTeacherInfoList(buildTeacherInfos(courseTableDetailTeacherList));
        coursetableResourceVo.setTerm(courseTableDetail.getCourseTable().getTerm().toString());
        coursetableResourceVo.setWeek(courseTableDetail.getWeek());
        coursetableResourceVo.setWeekNum(courseTableDetail.getWeekNum().toString());
        coursetableResourceVo.setClassName(courseTableDetail.getCourseTable().getGroup().getGroupName());
        coursetableResourceVo.setCourseAttrName(Objects.isNull(courseTable.getCourseCategory()) ? "" :
                courseTable.getCourseCategory().getCourseCategoryName());
        coursetableResourceVo.setMajorName(courseTable.getGroup().getMajorComposition());
        List<CourseTableDetail> courseTableDetailList = courseTableDetail.getCourseTable().getCourseTableDetailList();
        List<String> ids = courseTableDetailList.stream().filter(c -> Objects.nonNull(c.getCourseDate()))
                .sorted(Comparator.comparing(CourseTableDetail::getCourseDate))
                .map(CourseTableDetail::getId).collect(Collectors.toList());
        int index = ids.indexOf(courseTableDetail.getId());
        index++;
        coursetableResourceVo.setClassHour(Integer.toString(index));
        List<Term> terms = termRepository.findAllBySchoolYearAndTerm
                (courseTableDetail.getCourseTable().getSchoolYear(), TermType.getTermType(courseTableDetail.getCourseTable().getTerm()));
        if (!CollectionUtils.isEmpty(terms)) {
            coursetableResourceVo.setStartDate(terms.get(0).getStartDate());
        }
        String segmentStartTime = DateUtils.formatDate(DATE, courseTableDetail.getCourseDate()) + " " +
                DateUtils.formatDate(TIME_SECOND, courseTableDetail.getSegmentStartTime());
        String segmentEndTime = DateUtils.formatDate(DATE, courseTableDetail.getCourseDate()) + " " +
                DateUtils.formatDate(TIME_SECOND, courseTableDetail.getSegmentEndTime());
        String nowTime = DateUtils.formatDate(DATE_TIME, new Date());
        boolean result = nowTime.compareTo(segmentStartTime) >= 0 && nowTime.compareTo(segmentEndTime) <= 0;
        coursetableResourceVo.setIsInClass(result);
        coursetableResourceVo.setCourseAttrName(Objects.isNull(courseTable.getCourseCategory()) ? "" :
                courseTable.getCourseCategory().getCourseCategoryName());
        int studentNum = groupMemberRepository.countByGroupId(courseTable.getGroup().getId());
        coursetableResourceVo.setStudentNum(studentNum);
        coursetableResourceVo.setRoomId(courseTableDetail.getCourseTableDetailRoomUserList().stream()
                .map(CourseTableDetailRoomUser::getRoomId).collect(joining(",")));
        coursetableResourceVo.setRoomName(courseTableDetail.getCourseTableDetailRoomUserList().stream()
                .map(CourseTableDetailRoomUser::getRoomName).collect(joining(",")));
        coursetableResourceVo.setSourceDataSource(courseTableDetail.getSourceDataSource());
        coursetableResourceVo.setSourceDataSourceName(courseTableDetail.getSourceDataSourceName());
    }

    private List<TeacherInfo> buildTeacherInfos(List<CourseTableDetailTeacher> courseTableDetailTeacherList) {
        List<TeacherInfo> teacherInfoList = new ArrayList<>();
        courseTableDetailTeacherList.forEach(courseTableDetailTeacher -> {
            TeacherInfo teacherInfo = new TeacherInfo();
            teacherInfo.setTeacherId(courseTableDetailTeacher.getTeacherId());
            teacherInfo.setTeacherName(courseTableDetailTeacher.getTeacherName());
            teacherInfo.setTeacherNo(courseTableDetailTeacher.getTeacherNo());
            teacherInfo.setTeacherTitle(courseTableDetailTeacher.getTeacherTitle());
            teacherInfoList.add(teacherInfo);
        });
        return teacherInfoList;
    }

    public List<CourseTableDetailSegmentModel> getCourseTableDetailSegments(String groupId, String teacherId) {
        String nowDate = DateUtils.formatDate(DateUtils.DATE, new Date());
        String sql = "select a.id as courseTableDetailId,DATE_FORMAT(a.course_date,'%Y-%m-%d') as courseDate , " +
                "c.course_id as courseId,c.course_name as courseName,b.teacher_id as teacherId,b.teacher_name as teacherName, " +
                "a.segment as segments,d.room_id as roomId " +
                "from tb_course_table_detail a  " +
                "inner join tb_course_table_detail_teacher b on a.id = b.course_table_detail_id " +
                "inner join tb_course_table c on a.course_table_id = c.id " +
                "INNER JOIN tb_course_table_detail_room_user d ON a.id = d.course_table_detail_id " +
                "where c.group_id =:groupId and b.teacher_id =:teacherId " +
                "and a.course_date <:courseDate ORDER BY a.course_date DESC,a.segment_start_time DESC,segment DESC ;";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("groupId", groupId);
        query.setParameter("teacherId", teacherId);
        query.setParameter("courseDate", nowDate);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailSegmentModel.class));
        return (List<CourseTableDetailSegmentModel>) query.getResultList();
    }

    public void saveCourseTableDetail(CourseTable courseTable, String userId, String userName, List<TeacherVo> teacherVoList) {
        CourseTableDetail courseTableDetail = new CourseTableDetail();
        courseTableDetail.setCreatorId(userId);
        courseTableDetail.setCreateTime(new Date());
        courseTableDetail.setCreatorName(userName);
        courseTableDetail.setSource(Source.AUTONOMOUS_CLASS);
        courseTableDetail.setCourseTable(courseTable);
        courseTableDetail.setCourseStatus(CourseStatus.NOT_CLASS);
        courseTableDetail = courseTableDetailRepository.saveAndFlush(courseTableDetail);
        saveCourseTableDetailTeacher(courseTableDetail, userId, userName, teacherVoList);
    }

    private void saveCourseTableDetailTeacher(CourseTableDetail courseTableDetail, String userId, String userName,
                                              List<TeacherVo> teacherVoList) {
        List<CourseTableDetailTeacher> courseTableDetailTeachers = new ArrayList<>();
        teacherVoList.forEach(teacherVo -> {
            CourseTableDetailTeacher courseTableDetailTeacher = new CourseTableDetailTeacher();
            courseTableDetailTeacher.setCourseTableDetail(courseTableDetail);
            courseTableDetailTeacher.setCreatorId(userId);
            courseTableDetailTeacher.setCreatorName(userName);
            courseTableDetailTeacher.setCreateTime(new Date());
            courseTableDetailTeacher.setTeacherId(teacherVo.getTeacherId());
            courseTableDetailTeacher.setTeacherNo(teacherVo.getTeacherNo());
            courseTableDetailTeacher.setTeacherName(teacherVo.getTeacherName());
            courseTableDetailTeacher.setTeacherCollegeId(teacherVo.getCollegeId());
            courseTableDetailTeacher.setTeacherCollegeName(teacherVo.getCollegeName());
            courseTableDetailTeachers.add(courseTableDetailTeacher);
        });
        courseTableDetailTeacherRepository.saveAll(courseTableDetailTeachers);
    }

    public List<RoomSegmentUsageModel> getCourseTableDetailRoomSegmentUsages(String roomId, String courseDate) {
        List<RoomSegmentUsageModel> roomSegmentUsageModelList = new ArrayList<>();
        List<ClassroomAndFloorResource> classroomAndFloorResources = classRoomService.getClassroomFloorInfo(Collections.singletonList(roomId));
        List<Segment> segmentList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(classroomAndFloorResources)) {
            ClassroomAndFloorResource classroomAndFloorResource = classroomAndFloorResources.get(0);
            segmentList.addAll(segmentRepository.findByBuildIdOrderByStartTime(classroomAndFloorResource.getBuildId()));
            if (CollectionUtils.isEmpty(segmentList)) {
                segmentList.addAll(segmentRepository.findByBuildNameOrderByStartTime(String.valueOf(0)));
            }
        } else {
            segmentList.addAll(segmentRepository.findByBuildNameOrderByStartTime(String.valueOf(0)));
        }
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository.getRoomCourseDateList(roomId, stringToDate(DATE, courseDate));
        List<CourseSegment> courseSegmentList = courseSegmentRepository.findByCourseTableDetailIn(courseTableDetailList);
        segmentList.forEach(segment -> {
            RoomSegmentUsageModel roomSegmentUsageModel = new RoomSegmentUsageModel();
            roomSegmentUsageModel.setSegment(segment.getSegment());
            CourseSegment initialCourseSegment = courseSegmentList.stream().filter(courseSegment ->
                    courseSegment.getSegment().getSegment().equals(segment.getSegment())).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(initialCourseSegment)) {
                roomSegmentUsageModel.setUsageIndex(1);
                roomSegmentUsageModel.setUsageName("上课");
                CourseTableDetail courseTableDetail = initialCourseSegment.getCourseTableDetail();
                roomSegmentUsageModel.setSegment(segment.getSegment());
                roomSegmentUsageModel.setCourseId(courseTableDetail.getCourseTable().getCourse().getId());
                roomSegmentUsageModel.setCourseCode(courseTableDetail.getCourseTable().getCourse().getCourseCode());
                roomSegmentUsageModel.setCourseName(courseTableDetail.getCourseName());
                List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetail.getCourseTableDetailTeacherList();
                String teacherIds = courseTableDetailTeacherList.stream().map(CourseTableDetailTeacher::getTeacherId)
                        .distinct().collect(joining(","));
                roomSegmentUsageModel.setTeacherIds(teacherIds);
                String teacherNos = courseTableDetailTeacherList.stream().map(CourseTableDetailTeacher::getTeacherNo)
                        .distinct().collect(joining(","));
                roomSegmentUsageModel.setTeacherNos(teacherNos);
                String teacherNames = courseTableDetailTeacherList.stream().map(CourseTableDetailTeacher::getTeacherName)
                        .distinct().collect(joining(","));
                roomSegmentUsageModel.setTeacherNames(teacherNames);
            } else {
                roomSegmentUsageModel.setUsageIndex(0);
                roomSegmentUsageModel.setUsageName("自习");
            }
            roomSegmentUsageModelList.add(roomSegmentUsageModel);
        });
        return roomSegmentUsageModelList;
    }

    public List<TeachingCalendarResource> getTeachingCalendarResourceList(Term nowTerm, String yearMonth, String teacherId) {
        List<TeachingCalendarResource> teachingCalendarResourceList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        List<String> courseDateList = new ArrayList<>();
        getCourseDateList(yearMonth, courseDateList);
        List<String> nowCourseDateList = new ArrayList<>();
        String termStartDate = DateUtils.formatDate(DATE, nowTerm.getStartDate());
        String termEndDate = DateUtils.formatDate(DATE, nowTerm.getEndDate());
        courseDateList.forEach(courseDate -> {
            if (termStartDate.compareTo(courseDate) <= 0 && termEndDate.compareTo(courseDate) >= 0) {
                nowCourseDateList.add(courseDate);
            }
        });
        if (!CollectionUtils.isEmpty(nowCourseDateList)) {
            String querySql = " select a.course_date as courseDate,count(1) as counts " +
                    " from tb_course_table_detail a INNER JOIN tb_course_table b on a.course_table_id = b.id " +
                    " INNER JOIN tb_course_table_detail_teacher c ON c.course_table_detail_id = a.id " +
                    " where b.school_year = :schoolYear and term = :term and a.course_date in " +
                    "  ( :nowCourseDateList ) and c.teacher_id = :teacherId " +
                    "  GROUP BY a.course_date ";
            paramMap.put("schoolYear", nowTerm.getSchoolYear());
            paramMap.put("term", nowTerm.getTerm().getIndex());
            paramMap.put("nowCourseDateList", nowCourseDateList);
            paramMap.put("teacherId", teacherId);
            Query query = entityManager.createNativeQuery(querySql);
            paramMap.forEach(query::setParameter);
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List<Map<String, Object>> resMapList = query.getResultList();
            nowCourseDateList.forEach(courseDate -> {
                TeachingCalendarResource teachingCalendarResource = new TeachingCalendarResource();
                teachingCalendarResource.setCourseDate(courseDate);
                if (CollectionUtils.isEmpty(resMapList)) {
                    teachingCalendarResource.setHasCourseTable(false);
                } else {
                    List<Map<String, Object>> courseDateResMapList =
                            resMapList.stream().filter(resMap -> resMap.get("courseDate").toString().equals(courseDate)).collect(toList());
                    teachingCalendarResource.setHasCourseTable(CollectionUtils.isNotEmpty(courseDateResMapList));
                }
                teachingCalendarResourceList.add(teachingCalendarResource);
            });
        }
        return teachingCalendarResourceList;
    }

    private static void getCourseDateList(String yearMonth, List<String> courseDateList) {
        LocalDate date = LocalDate.parse(yearMonth + "-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate dateFirst = date.with(TemporalAdjusters.firstDayOfMonth()); // 指定年月的第一天
        LocalDate dateEnd = date.with(TemporalAdjusters.lastDayOfMonth()); // 指定年月的最后一天
        String firstDate = dateFirst.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = dateEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setTime(sdf.parse(firstDate));
            while (c.getTime().compareTo(sdf.parse(endDate)) <= 0) {
                String time = sdf.format(c.getTime());
                courseDateList.add(time);
                c.add(Calendar.DATE, 1);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidDate(String inDate, String pattern) {
        if (inDate == null) {
            return false;
        }
        SimpleDateFormat dataFormat = new SimpleDateFormat(pattern);//yyyy-MM或yyyy-MM-dd
        if (inDate.trim().length() != dataFormat.toPattern().length()) {
            return false;
        }
        dataFormat.setLenient(false);//该方法用于设置Calendar严格解析字符串;默认为true,宽松解析
        try {
            dataFormat.parse(inDate.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public WeekCourseTableDetailPage getCourseTableWeekDetailInfo(Term nowTerm, String courseDate, String teacherId) {
        WeekCourseTableDetailPage weekCourseTableDetailPage = new WeekCourseTableDetailPage();
        Date courseDateTime = DateUtils.stringToDate("yyyy-MM-dd", courseDate);
        String newDate = TimeUtils.getNowDate();
        int weeks = getWeek(courseDateTime, nowTerm.getStartDate());
        weekCourseTableDetailPage.setWeek("第" + weeks + "周次");
        List<String> weekCourseDateList = new ArrayList<>();
        getWeekCourseDateList(courseDateTime, weekCourseDateList);
        List<CourseTableDetailSqlVo> courseTableDetailSqlVoList = getCourseTableDetailSqlVo(nowTerm, teacherId, weekCourseDateList);
        if (CollectionUtils.isNotEmpty(courseTableDetailSqlVoList)) {
            Map<String, List<CourseTableDetailSqlVo>> courseTableDetailSqlVoGroupByCourseDateMap = courseTableDetailSqlVoList.stream()
                    .collect(Collectors.groupingBy(CourseTableDetailSqlVo::getCourseDate));
            List<WeekDateCourseTableDetailResource> weekDateCourseTableDetailList = new ArrayList<>();
            weekCourseDateList.forEach(weekCourseDate -> {
                List<CourseTableDetailSqlVo> groupCourseTableDetailSqlVoList = courseTableDetailSqlVoGroupByCourseDateMap.get(weekCourseDate);
                WeekDateCourseTableDetailResource weekDateCourseTableDetailResource = new WeekDateCourseTableDetailResource();
                weekDateCourseTableDetailResource.setCourseDate(weekCourseDate);
                weekDateCourseTableDetailResource.setWeekNum(dateToWeek(weekCourseDate));
                weekDateCourseTableDetailResource.setHasToday(newDate.equals(weekCourseDate));
                List<CourseTableDetailInfoModel> courseTableDetailInfoModelList = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(groupCourseTableDetailSqlVoList)) {
                    groupCourseTableDetailSqlVoList.forEach(courseTableDetailSqlVo -> {
                        CourseTableDetailInfoModel courseTableDetailInfoModel = new CourseTableDetailInfoModel();
                        courseTableDetailInfoModel.setCourseId(courseTableDetailSqlVo.getCourseId());
                        courseTableDetailInfoModel.setCourseName(courseTableDetailSqlVo.getCourseName());
                        courseTableDetailInfoModel.segmentStartTime(courseTableDetailSqlVo.getSegmentStartTime());
                        courseTableDetailInfoModel.setSegmentEndTime(courseTableDetailSqlVo.getSegmentEndTime());
                        courseTableDetailInfoModel.setRoomNames(Arrays.stream(courseTableDetailSqlVo.getRoomNames().split(",")).collect(toList())
                                .stream().filter(roomName -> !roomName.isEmpty()).collect(joining("、")));
                        courseTableDetailInfoModel.setRoomCodes(Arrays.stream(courseTableDetailSqlVo.getRoomCodes().split(",")).collect(toList())
                                .stream().filter(roomCode -> !roomCode.isEmpty()).collect(joining("、")));
                        courseTableDetailInfoModelList.add(courseTableDetailInfoModel);
                    });
                }
                weekDateCourseTableDetailResource.setCourseTableDetailList(courseTableDetailInfoModelList.stream().sorted(
                        Comparator.comparing(CourseTableDetailInfoModel::getSegmentStartTime)).collect(toList()));
                weekDateCourseTableDetailList.add(weekDateCourseTableDetailResource);
            });
            weekCourseTableDetailPage.setWeekDateCourseTableDetailList(weekDateCourseTableDetailList.stream().sorted(
                    Comparator.comparing(WeekDateCourseTableDetailResource::getCourseDate)).collect(toList()));
        } else {
            weekCourseTableDetailPage.setWeekDateCourseTableDetailList(new ArrayList<>());
        }
        return weekCourseTableDetailPage;
    }

    private List<CourseTableDetailSqlVo> getCourseTableDetailSqlVo(Term nowTerm, String teacherId, List<String> weekCourseDateList) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = " SELECT distinct  b.course_id as courseId, " +
                " b.course_name as courseName,date_format( a.course_date, '%Y-%m-%d') as courseDate, " +
                " DATE_FORMAT(a.segment_start_time,'%H:%i') segmentStartTime,DATE_FORMAT(a.segment_end_time,'%H:%i') segmentEndTime, " +
                "group_concat(if(room_name is null,'',room_name) order by ctdru.id SEPARATOR ',') as roomNames," +
                "group_concat(if(room_code is null,'',room_code) order by ctdru.id SEPARATOR ',') as roomCodes" +
                " FROM tb_course_table_detail a INNER JOIN tb_course_table b on a.course_table_id = b.id " +
                " INNER JOIN tb_course_table_detail_teacher c on a.id = c.course_table_detail_id " +
                " join tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = a.id" +
                " where b.school_year = :schoolYear and b.term =:term and a.course_date in (:weekCourseDateList)  and c.teacher_id = :teacherId " +
                " group by ctdru.course_table_detail_id";
        paramMap.put("schoolYear", nowTerm.getSchoolYear());
        paramMap.put("term", nowTerm.getTerm().getIndex());
        paramMap.put("weekCourseDateList", weekCourseDateList);
        paramMap.put("teacherId", teacherId);
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailSqlVo.class));
        return (List<CourseTableDetailSqlVo>) query.getResultList();
    }


    public static void getWeekCourseDateList(Date courseDateTime, List<String> weekCourseDateList) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(courseDateTime);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        int weekNum = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(Calendar.DATE, -weekNum + 1);
        //获取星期一的日期
        Date mondayDate = calendar.getTime();
        weekCourseDateList.add(DateUtils.formatDate("yyyy-MM-dd", mondayDate));
        //遍历-获取星期二至星期天日期
        for (int i = 0; i < SIX; i++) {
            calendar.add(Calendar.DATE, 1);
            weekCourseDateList.add(DateUtils.formatDate("yyyy-MM-dd", calendar.getTime()));
        }
    }

    public static int getWeek(Date d1, Date d2) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(d2);
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        long daysBetween = (d1.getTime() - d2.getTime() + HUNDRED) / (SIXTY * SIXTY * TWENTY_FOUR * THOUSAND);
        int weekindex = (int) (daysBetween / WEEK + 1);
        if (dayOfWeek + daysBetween % WEEK > WEEK) {
            weekindex += 1;
        }
        return weekindex;
    }

    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = f.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    public List<CourseTableDetailTimeVo> getCourseTableDetailTimeVoList(List<String> courseTableDetailIdList) {
        String querySql = "select id as courseTableDetailId ,  " +
                "CONCAT(DATE_FORMAT(course_date,'%Y-%m-%d'),' ',DATE_FORMAT(segment_start_time,'%H:%i:%s')) as segmentStartTime, " +
                "CONCAT(DATE_FORMAT(course_date,'%Y-%m-%d'),' ',DATE_FORMAT(segment_end_time,'%H:%i:%s')) as segmentEndTime " +
                "from tb_course_table_detail a where id in ( :courseTableDetailIdList);";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseTableDetailIdList", courseTableDetailIdList);
        Query query = entityManager.createNativeQuery(querySql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailTimeVo.class));
        paramMap.forEach(query::setParameter);
        return query.getResultList();
    }

    public List<TeachingCalendarResource> getStudentTeachingCalendarResourceList(
            Term nowTerm, String yearMonth, String studentId) {
        List<TeachingCalendarResource> teachingCalendarResourceList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        List<String> courseDateList = new ArrayList<>();
        getCourseDateList(yearMonth, courseDateList);
        List<String> nowCourseDateList = new ArrayList<>();
        String termStartDate = DateUtils.formatDate(DATE, nowTerm.getStartDate());
        String termEndDate = DateUtils.formatDate(DATE, nowTerm.getEndDate());
        courseDateList.forEach(courseDate -> {
            if (termStartDate.compareTo(courseDate) <= 0 && termEndDate.compareTo(courseDate) >= 0) {
                nowCourseDateList.add(courseDate);
            }
        });
        if (!CollectionUtils.isEmpty(nowCourseDateList)) {
            String querySql = " select a.course_date as courseDate,count(1) as counts " +
                    " from tb_course_table_detail a INNER JOIN tb_course_table b on a.course_table_id = b.id " +
                    " INNER JOIN tb_group_member c on c.group_id = b.group_id " +
                    " where b.school_year = :schoolYear and term = :term and a.course_date in " +
                    "  ( :nowCourseDateList ) and c.student_id = :studentId AND c.group_member_status = 0 " +
                    "  GROUP BY a.course_date ";
            paramMap.put("schoolYear", nowTerm.getSchoolYear());
            paramMap.put("term", nowTerm.getTerm().getIndex());
            paramMap.put("nowCourseDateList", nowCourseDateList);
            paramMap.put("studentId", studentId);
            Query query = entityManager.createNativeQuery(querySql);
            paramMap.forEach(query::setParameter);
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List<Map<String, Object>> resMapList = query.getResultList();
            nowCourseDateList.forEach(courseDate -> {
                TeachingCalendarResource teachingCalendarResource = new TeachingCalendarResource();
                teachingCalendarResource.setCourseDate(courseDate);
                if (CollectionUtils.isEmpty(resMapList)) {
                    teachingCalendarResource.setHasCourseTable(false);
                } else {
                    List<Map<String, Object>> courseDateResMapList =
                            resMapList.stream().filter(resMap -> resMap.get("courseDate").toString()
                                    .equals(courseDate)).collect(toList());
                    teachingCalendarResource.setHasCourseTable(CollectionUtils.isNotEmpty(courseDateResMapList));
                }
                teachingCalendarResourceList.add(teachingCalendarResource);
            });
        }
        return teachingCalendarResourceList;
    }

    public List<CourseTableDetailInfoVo> getCourseTableDetailListByCourseTableId(String courseTableId) {
        List<CourseTableDetailInfoVo> courseTableDetailInfoList = new ArrayList<>();
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository
                .findAll(getCourseTableDetailSpecificationByCourseTableId(courseTableId));
        if (!courseTableDetailList.isEmpty()) {
            courseTableDetailList.forEach(courseTableDetail -> {
                CourseTableDetailInfoVo courseTableDetailInfo = new CourseTableDetailInfoVo();
                courseTableDetailInfo.setCourseTableDetailId(courseTableDetail.getId());
                courseTableDetailInfo.setCourseDate(courseTableDetail.getCourseDate() == null ? "" :
                        DateUtils.formatDate("yyyy-MM-dd", courseTableDetail.getCourseDate()));
                courseTableDetailInfo.setWeek(courseTableDetail.getWeek());
                courseTableDetailInfo.setWeekNum(courseTableDetail.getWeekNum());
                courseTableDetailInfo.setSegment(courseTableDetail.getSegment());
                courseTableDetailInfo.setSegmentStartTime(formatDate(DATE, courseTableDetail.getCourseDate())
                        + " " + formatDate(TIME_SECOND, courseTableDetail.getSegmentStartTime()));
                courseTableDetailInfo.setSegmentEndTime(formatDate(DATE, courseTableDetail.getCourseDate())
                        + " " + formatDate(TIME_SECOND, courseTableDetail.getSegmentEndTime()));
                List<CourseTableDetailRoomUser> courseTableDetailRoomUser = courseTableDetail.getCourseTableDetailRoomUserList();
                if (courseTableDetailRoomUser != null && !courseTableDetailRoomUser.isEmpty()) {
                    List<String> roomIds = courseTableDetailRoomUser.stream().map(CourseTableDetailRoomUser::getRoomId).collect(toList());
                    List<String> roomNames = courseTableDetailRoomUser.stream().map(CourseTableDetailRoomUser::getRoomName).collect(toList());
                    courseTableDetailInfo.setRoomId(String.join(",", roomIds));
                    courseTableDetailInfo.setRoomName(String.join(",", roomNames));
                }
                CourseTable courseTable = courseTableDetail.getCourseTable();
                Course course = courseTable.getCourse();
                if (course != null) {
                    courseTableDetailInfo.setCourseId(course.getId());
                    courseTableDetailInfo.setCourseCode(course.getCourseCode());
                    courseTableDetailInfo.setCourseName(course.getCourseName());
                }
                List<GroupMember> groupMemberList = courseTable.getGroup().getGroupMemberList();
                courseTableDetailInfo.setStudentCount(groupMemberList != null ? groupMemberList.size() + "" : "0");
                List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetail.getCourseTableDetailTeacherList();
                if (courseTableDetailTeacherList != null && !courseTableDetailTeacherList.isEmpty()) {
                    String teacherIds = courseTableDetailTeacherList.stream()
                            .map(CourseTableDetailTeacher::getTeacherId)
                            .collect(joining(","));
                    String teacherNos = courseTableDetailTeacherList.stream()
                            .map(CourseTableDetailTeacher::getTeacherNo)
                            .collect(joining(","));
                    String teacherNames = courseTableDetailTeacherList.stream()
                            .map(CourseTableDetailTeacher::getTeacherName)
                            .collect(joining(","));
                    courseTableDetailInfo.setTeacherIds(teacherIds);
                    courseTableDetailInfo.setTeacherNos(teacherNos);
                    courseTableDetailInfo.setTeacherNames(teacherNames);
                }
                courseTableDetailInfoList.add(courseTableDetailInfo);
            });
        }
        return courseTableDetailInfoList;
    }


    public CourseTableDetailPageResource getAdministrativeClassLiveCourseTableDetailPage(
            CourseTableDetailAdministrativeClassPageRequest pageRequest) {
        CourseTableDetailPageResource courseTableDetailPageResource = new CourseTableDetailPageResource();
        Map<String, Object> paramMap = new HashMap<>();
        String baseSql = createCourseTableDetailBaseSql(pageRequest, paramMap);

        String roomIdSortList = getSortBySchoolDistrictBuildingLayerClassRoomIdList();
        String detailSql = createCourseTableDetailSql(baseSql, pageRequest, roomIdSortList, paramMap);
        Query queryData = entityManager.createNativeQuery(detailSql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailResource.class));
        List<CourseTableDetailResource> courseTableDetailResourceList = queryData.getResultList();

        List<CourseTableDetailResource> effectiveCourseTableDetailResourceList = new ArrayList<>();

        Map<String, List<CourseTableDetailResource>> groupByCourseTableDetailResourceListMap = courseTableDetailResourceList
                .stream().collect(Collectors.groupingBy(CourseTableDetailResource::getGroupId));
        groupByCourseTableDetailResourceListMap.forEach((groupId, groupByCourseTableDetailResourceList) -> {
            List<String> groupMemberIds = groupMemberRepository.findUserIdsByGroupId(groupId);
            checkAndAddEffectiveResources(
                    groupMemberIds, pageRequest, groupByCourseTableDetailResourceList, effectiveCourseTableDetailResourceList);
        });

        int count = effectiveCourseTableDetailResourceList.size();
        if (count == 0) {
            courseTableDetailPageResource.setTotal(0);
            courseTableDetailPageResource.setPageCount(0);
            courseTableDetailPageResource.setPageSize(pageRequest.getPageSize());
            courseTableDetailPageResource.setPage(pageRequest.getPage());
            courseTableDetailPageResource.setCourseTableDetailList(new ArrayList<>());
        } else {
            courseTableDetailPageResource.setTotal(count);
            courseTableDetailPageResource.setPageCount((int) Math.ceil((double) count / (double) pageRequest.getPageSize()));
            courseTableDetailPageResource.setPageSize(pageRequest.getPageSize());
            courseTableDetailPageResource.setPage(pageRequest.getPage());
            // 调用分页方法
            List<CourseTableDetailResource> paginatedList =
                    paginate(effectiveCourseTableDetailResourceList, pageRequest.getPage(), pageRequest.getPageSize());
            courseTableDetailPageResource.setCourseTableDetailList(paginatedList);
        }
        return courseTableDetailPageResource;

    }
    /**
     * 分页方法
     *
     * @param <T> 数据类型
     * @param list 需要分页的数据列表
     * @param page 当前页码，从1开始
     * @param pageSize 每页显示的记录数
     * @return 分页后的数据列表
     */
    private static <T> List<T> paginate(List<T> list, int page, int pageSize) {
        if (page < 1 || pageSize < 1) {
            return new ArrayList<>();
        }
        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= list.size() || fromIndex < 0) {
            return new ArrayList<>(); // 返回空列表
        }
        int toIndex = Math.min(fromIndex + pageSize, list.size());
        return list.subList(fromIndex, toIndex);
    }
    /**
     * 检查并添加有效的课程表详情资源
     *
     * @param groupMemberIds                         用户ID列表
     * @param pageRequest                            页面请求对象
     * @param groupByCourseTableDetailResourceList   课程表详情资源列表
     * @param effectiveCourseTableDetailResourceList 有效资源列表
     */
    public void checkAndAddEffectiveResources(
            List<String> groupMemberIds,
            CourseTableDetailAdministrativeClassPageRequest pageRequest,
            List<CourseTableDetailResource> groupByCourseTableDetailResourceList,
            List<CourseTableDetailResource> effectiveCourseTableDetailResourceList) {

        // 1. 检查用户ID列表是否为空
        if (groupMemberIds == null || groupMemberIds.isEmpty()) {
            return; // 如果没有成员ID，直接返回
        }

        // 2. 获取用户所属的行政班级ID列表
        List<AdministrativeClassIdVo> administrativeClassIdVos = authorityApiService.getAdministrativeClassIdsByUserIds(groupMemberIds);

        // 3. 检查获取到的行政班级ID列表是否为空
        if (administrativeClassIdVos == null || administrativeClassIdVos.isEmpty()) {
            return; // 如果没有获取到行政班级ID，直接返回
        }

        // 4. 解析页面请求中的行政班级ID
        Set<String> requestedClassIdSet = parseRequestedClassIds(pageRequest);

        // 5. 检查用户是否属于请求的行政班级
        boolean hasAccess = administrativeClassIdVos.stream()
                .anyMatch(classIdVo -> requestedClassIdSet.contains(classIdVo.getAdministrativeClassId()));

        // 6. 如果有访问权限，添加资源到有效列表
        if (hasAccess) {
            effectiveCourseTableDetailResourceList.addAll(groupByCourseTableDetailResourceList);
        }
    }

    /**
     * 解析页面请求中的行政班级ID集合
     *
     * @param pageRequest 页面请求对象
     * @return 行政班级ID集合
     */
    private Set<String> parseRequestedClassIds(CourseTableDetailAdministrativeClassPageRequest pageRequest) {
        if (pageRequest.getAdministrativeClassIds() == null) {
            return Collections.emptySet(); // 如果没有请求参数，返回空集合
        }

        // 将逗号分隔的字符串转换为Set，提高查找效率
        return Arrays.stream(pageRequest.getAdministrativeClassIds().split(","))
                .filter(id -> id != null && !id.trim().isEmpty()) // 过滤空值
                .map(String::trim) // 去除前后空格
                .collect(Collectors.toSet()); // 转换为Set以提高查找效率
    }

    public String createCourseTableDetailBaseSql(CourseTableDetailAdministrativeClassPageRequest pageRequest, Map<String, Object> paramMap) {
        String sql = "SELECT ctd.id as id , c.course_name as courseName, ctdru.room_id as roomId,   ctdru.room_name as roomName, " +
                " (select GROUP_CONCAT(teacher_name  order by show_order ) from tb_course_table_detail_teacher " +
                " where course_table_detail_id=ctd.id GROUP BY ctd.id  )" +
                " as teacherName,  ctd.course_kind as courseKind ,c.id as courseId," +
                " ct.college_id as collegeId,ct.college_name as collegeName," +
                " ct.student_type as studentType,ctd.source_data_source_name as sourceDataSourceName,ctd.source_data_source as sourceDataSource," +
                " tg.id as groupId,tg.group_no as groupNo,tg.group_name as groupName " +
                " FROM tb_course_table_detail ctd " +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                " inner join tb_group tg on ct.group_id = tg.id " +
                " INNER JOIN tb_course c ON ct.course_id = c.id " +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment where " +
                " c.use_state=1 and ctdru.has_live=1  " +
                " and (ctdru.room_id is not null or ctdru.room_id!='') ";
        if (org.apache.commons.lang3.StringUtils.isNotBlank(pageRequest.getCourseDate())) {
            sql += " and ctd.course_date= :courseDate ";
            paramMap.put("courseDate", pageRequest.getCourseDate());
        }
        if (StringUtils.isNotBlank(pageRequest.getSegment())) {
            sql += " and cs.segment= :segment ";
            paramMap.put("segment", pageRequest.getSegment());
        }
        if (StringUtils.isNotEmpty(pageRequest.getSourceDataSource())) {
            sql += " and ctd.source_data_source = :sourceDataSource";
            paramMap.put("sourceDataSource", pageRequest.getSourceDataSource());
        }
        return sql;
    }

    public String createCourseTableDetailSql(
            String baseSql, CourseTableDetailAdministrativeClassPageRequest pageRequest, String roomIdSortList, Map<String, Object> paramMap) {
        String sql = "select * from (" + baseSql + ") a where 1=1";
        if (StringUtils.isNotBlank(pageRequest.getSearchParams())) {
            String tempString = CharactersReplace.replaceCharacters(pageRequest.getSearchParams());
            if (StringUtils.isNotBlank(tempString)) {
                sql += " and (a.courseName like :searchParam  or a.teacherName like :searchParam or a.roomName like :searchParam)";
                paramMap.put("searchParam", "%" + tempString + "%");
            }
        } else {
            if (StringUtils.isNotBlank(pageRequest.getSearchCourseName())) {
                sql += " and a.courseName like :searchCourseName";
                paramMap.put("searchCourseName", "%" + CharactersReplace.replaceCharacters(pageRequest.getSearchCourseName()) + "%");
            }
            if (StringUtils.isNotBlank(pageRequest.getSearchRoomName())) {
                sql += " and a.roomName like :searchRoomName";
                paramMap.put("searchRoomName", "%" + CharactersReplace.replaceCharacters(pageRequest.getSearchRoomName()) + "%");
            }
            if (StringUtils.isNotBlank(pageRequest.getSearchTeacherName())) {
                sql += " and a.teacherName like :searchTeacherName";
                paramMap.put("searchTeacherName", "%" + CharactersReplace.replaceCharacters(pageRequest.getSearchTeacherName()) + "%");
            }
        }
        sql += " order by FIELD(roomId," + roomIdSortList + ");  ";
        return sql;
    }
}

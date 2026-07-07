package com.lztech.site.service.teachingcalendarcoursetable;


import com.lztech.domain.model.coursesegment.CourseSegment;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetail.CourseTableDetailProject;
import com.lztech.domain.model.coursetabledetail.enums.CourseKind;
import com.lztech.domain.model.coursetabledetail.enums.CourseStatus;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.coursetype.CourseType;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberSource;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.domain.model.segment.Segment;
import com.lztech.persistence.repositories.coursesegment.CourseSegmentRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailProjectRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetailroomuser.CourseTableDetailRoomUserRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.coursetype.CourseTypeRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.persistence.repositories.segment.SegmentRepository;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.service.classroom.ClassRoomService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.CourseTableSimpleVo;
import com.lztech.site.viewmodel.authorityapi.UsersInfoResource;
import com.lztech.site.viewmodel.classroom.ClassRoomResource;
import com.lztech.site.viewmodel.classroom.ClassroomAndFloorResource;
import com.lztech.site.viewmodel.coursetabledetail.RoomScheduleInformation;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingcalendarcoursetabledetail.*;
import com.lztech.site.viewmodel.userinfo.UserIdAndOpenIdVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.until.DateUtils.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class TeachingCalendarCourseTableService {

    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private CourseTableDetailRoomUserRepository courseTableDetailRoomUserRepository;
    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private CourseTableDetailProjectRepository courseTableDetailProjectRepository;
    @Autowired
    private SegmentRepository segmentRepository;
    @Autowired
    private CourseSegmentRepository courseSegmentRepository;
    @Autowired
    private EntityManager entityManager;

    private int sunday = 7;

    @Autowired
    private AuthorityApiService authorityApiService;

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<CourseTableSimpleVo> saveTeachingCalendarCourseTable(TeachingCalendarCourseTableDetail courseTableDetailResource) {
        // 校验当前时间此开课下是否已存在课表信息
        List<String> segmentList = Arrays.asList(courseTableDetailResource.getSegments().split(","));
        CourseTable courseTable = courseTableRepository.findByGroupId(courseTableDetailResource.getGroupId()).stream().findFirst().orElse(null);
        if (Objects.isNull(courseTable)) {
            return new ResponseEntity(ErrorResult.customError("此班级下不存在开课信息"), HttpStatus.CONFLICT);
        }
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository.findByCourseTableAndCourseDate(courseTable.getId(),
                courseTableDetailResource.getCourseDate());
        if (CollectionUtils.isNotEmpty(courseTableDetailList)) {
            for (String s : segmentList) {
                for (CourseTableDetail courseTableDetail : courseTableDetailList) {
                    if (courseTableDetail.getSegment().contains(s.trim())) {
                        return new ResponseEntity(ErrorResult.customError(courseTableDetailResource.getCourseDate() + "下第" +
                                "" + courseTableDetailResource.getSegments() +
                                "节次已存在课表信息"), HttpStatus.CONFLICT);
                    }
                }
            }
        }
        Date now = new Date();
        // 保存课表详情
        CourseTableDetail courseTableDetail = saveCourseTableDetail(courseTableDetailResource, courseTable, now);
        // 保存教室与分组
        saveGroupAndCourseTableRoom(courseTableDetailResource, courseTableDetail, now);
        //保存项目
        saveCourseTableDetailProjects(courseTableDetailResource, courseTableDetail);
        //保存教师
        List<CourseTableDetailTeacher> teachers = saveCourseTableDetailTeachers(courseTableDetailResource, courseTableDetail, now);
        courseTableDetail.setTeacherNames(
                teachers.stream().map(CourseTableDetailTeacher::getTeacherName).collect(Collectors.joining(",")));
        courseTableDetail.setTeacherCollegeIds(
                teachers.stream().map(CourseTableDetailTeacher::getTeacherCollegeId).collect(Collectors.joining(",")));
        courseTableDetail.setTeacherCollegeNames(
                teachers.stream().map(CourseTableDetailTeacher::getTeacherCollegeName).collect(Collectors.joining(",")));
        courseTableDetail = courseTableDetailRepository.save(courseTableDetail);

        // 保存节次
        List<String> roomIdList = courseTableDetailResource.getLabList().stream().map(LabResource::getRoomId).collect(Collectors.toList());
        List<ClassroomAndFloorResource> classroomAndFloorResources = classRoomService.getClassroomFloorInfo(roomIdList);
        saveCourseTableDetailSegment(segmentList, courseTableDetail, classroomAndFloorResources);
        //更新主课表中老师名称
        updateCourseTableTeacherName(courseTable, courseTableDetailResource.getTeacherList());
        CourseTableSimpleVo courseTableSimpleVo = new CourseTableSimpleVo();
        courseTableSimpleVo.setCourseTableDetailId(courseTableDetail.getId());
        courseTableSimpleVo.setCourseTableId(courseTable.getId());
        return new ResponseEntity<>(courseTableSimpleVo, HttpStatus.OK);
    }

    private void saveCourseTableDetailSegment(List<String> segmentNameList, CourseTableDetail courseTableDetail,
                                              List<ClassroomAndFloorResource> classroomAndFloorResources) {
        List<CourseSegment> courseSegmentList = new ArrayList<>();
        String buildId = "0";
        if (CollectionUtils.isNotEmpty(classroomAndFloorResources)) {
            buildId = classroomAndFloorResources.get(0).getBuildId();
        }
        List<Segment> segmentList = segmentRepository.findByBuildIdOrderByBuildName(buildId);
        segmentNameList.forEach(segmentName -> {
            Segment segment = segmentList
                    .stream()
                    .filter(s -> s.getSegment().equals(Integer.valueOf(segmentName)))
                    .findFirst()
                    .orElse(null);
            if (Objects.nonNull(segment)) {
                CourseSegment courseSegment = new CourseSegment();
                courseSegment.setSegment(segment);
                courseSegment.setCourseTableDetail(courseTableDetail);
                courseSegmentList.add(courseSegment);
            }
        });
        courseSegmentRepository.saveAll(courseSegmentList);
    }

    private void updateCourseTableTeacherName(CourseTable courseTable, List<TeacherResource> teacherList) {
        List<String> teacherNameList = teacherList.stream().map(TeacherResource::getTeacherName).distinct().collect(Collectors.toList());
        String nowTeacherName = courseTable.getTeacherName();
        for (String s : teacherNameList) {
            if (StringUtils.isBlank(nowTeacherName)) {
                nowTeacherName += s;
            } else {
                if (nowTeacherName.contains(s)) {
                    continue;
                } else {
                    nowTeacherName += "," + s;
                }
            }
        }
        courseTable.setTeacherName(nowTeacherName);
        courseTableRepository.save(courseTable);
    }


    private void saveCourseTableDetailProjects(TeachingCalendarCourseTableDetail courseTableDetailResource, CourseTableDetail courseTableDetail) {
        List<ProjectResource> projectResourceList = courseTableDetailResource.getProjectList();
        List<CourseTableDetailProject> courseTableDetailProjectList = new ArrayList<>();
        projectResourceList.forEach(projectResource -> {
            CourseTableDetailProject courseTableDetailProject = new CourseTableDetailProject();
            courseTableDetailProject.setProjectId(projectResource.getProjectId());
            courseTableDetailProject.setProjectCode(projectResource.getProjectCode());
            courseTableDetailProject.setProjectName(projectResource.getProjectName());
            courseTableDetailProject.setCourseTableDetail(courseTableDetail);
            courseTableDetailProjectList.add(courseTableDetailProject);
        });
        courseTableDetailProjectRepository.saveAll(courseTableDetailProjectList);
    }

    private List<CourseTableDetailTeacher> saveCourseTableDetailTeachers(TeachingCalendarCourseTableDetail courseTableDetailResource,
                                               CourseTableDetail courseTableDetail, Date now) {
        List<TeacherResource> teacherResourceList = courseTableDetailResource.getTeacherList();
        List<UserIdAndOpenIdVo> usersList = teacherResourceList.stream()
                .map(obj -> new UserIdAndOpenIdVo() {{
                    this.setUserId(obj.getTeacherId());
                }}).collect(toList());
        List<UsersInfoResource> usersInfoResources = authorityApiService.getUsersInfoResource(usersList);

        List<CourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();
        teacherResourceList.forEach(teacherResource -> {
            CourseTableDetailTeacher courseTableDetailTeacher = new CourseTableDetailTeacher();
            courseTableDetailTeacher.setTeacherId(teacherResource.getTeacherId());
            courseTableDetailTeacher.setTeacherNo(teacherResource.getTeacherNo());
            courseTableDetailTeacher.setTeacherName(teacherResource.getTeacherName());
            UsersInfoResource usersInfoResource = usersInfoResources.stream()
                    .filter(o->teacherResource.getTeacherId().equals(o.getId())).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(usersInfoResource)) {
                courseTableDetailTeacher.setTeacherCollegeId(usersInfoResource.getCollegeId());
                courseTableDetailTeacher.setTeacherCollegeName(usersInfoResource.getCollegeName());
            }

            courseTableDetailTeacher.setCourseTableDetail(courseTableDetail);
            courseTableDetailTeacher.setCreateTime(now);
            courseTableDetailTeacherList.add(courseTableDetailTeacher);
        });
        return courseTableDetailTeacherRepository.saveAll(courseTableDetailTeacherList);
    }

    private void saveGroupAndCourseTableRoom(TeachingCalendarCourseTableDetail courseTableDetailResource, CourseTableDetail courseTableDetail,
                                             Date now) {
        List<LabResource> labResourceList = courseTableDetailResource.getLabList();
        Group classGroup = groupRepository.findById(courseTableDetailResource.getGroupId()).get();
        String roomIds = labResourceList.stream().map(LabResource::getRoomId).collect(Collectors.joining(","));
        List<ClassRoomResource> classRoomResourceList = classRoomService.findClassRoomResourceList(roomIds);
        labResourceList.forEach(labResource -> {
            Group group = new Group();
            group.setGroupName(classGroup.getGroupName() + "-" + labResource.getRoomName());
            group.setGroupStatus(GroupStatus.NORMAL);
            group.setGroupNo(classGroup.getGroupNo() + labResource.getRoomId());
            group.setId(UUID.randomUUID().toString());
            group.setSource(Source.TEACHING_CALENDAR);
            group.setCreateTime(now);
            group.setClassNickName(group.getGroupName());
            group.setSort(0);
            Group savedGroup = groupRepository.save(group);
            savedGroupMembers(savedGroup, labResource.getStudentList(), now);
            saveCourseTableDetailRoomUser(savedGroup, courseTableDetail, labResource, classRoomResourceList, now);
        });
    }

    private void saveCourseTableDetailRoomUser(Group savedGroup, CourseTableDetail courseTableDetail, LabResource labResource,
                                               List<ClassRoomResource> classRoomResourceList, Date now) {
        CourseTableDetailRoomUser courseTableDetailRoomUser = new CourseTableDetailRoomUser();
        courseTableDetailRoomUser.setCourseTableDetail(courseTableDetail);
        courseTableDetailRoomUser.setRoomCode(labResource.getRoomCode());
        courseTableDetailRoomUser.setRoomId(labResource.getRoomId());
        courseTableDetailRoomUser.setRoomName(labResource.getRoomName());
        courseTableDetailRoomUser.setPersonnelNumber(String.valueOf(labResource.getLabStudentCount()));
        courseTableDetailRoomUser.setGroupId(savedGroup.getId());
        ClassRoomResource classRoomResource = classRoomResourceList
                .stream()
                .filter(classroom -> classroom.getId().equals(labResource.getRoomId()) && classroom.getHasLive())
                .findFirst()
                .orElse(null);
        if (Objects.isNull(classRoomResource)) {
            courseTableDetailRoomUser.setHasLive(false);
        } else {
            courseTableDetailRoomUser.setHasLive(true);
        }
        courseTableDetailRoomUser.setItemTeacherId(labResource.getItemTeacherId());
        courseTableDetailRoomUser.setItemTeacherName(labResource.getItemTeacherName());
        courseTableDetailRoomUser.setItemTeacherNo(labResource.getItemTeacherNo());
        courseTableDetailRoomUser.setCreateTime(now);
        courseTableDetailRoomUserRepository.save(courseTableDetailRoomUser);
    }

    private void savedGroupMembers(Group savedGroup, List<TeachingCalendarStudentResource> studentList, Date now) {
        List<GroupMember> groupMemberList = new ArrayList<>();
        studentList.forEach(teachingCalendarStudentResource -> {
            GroupMember groupMember = new GroupMember();
            groupMember.setGroupMemberStatus(GroupMemberStatus.NORMAL);
            groupMember.setGroup(savedGroup);
            groupMember.setStudentId(teachingCalendarStudentResource.getStudentId());
            groupMember.setStudentName(teachingCalendarStudentResource.getStudentName());
            groupMember.setStudentNo(teachingCalendarStudentResource.getStudentNo());
            groupMember.setOpenId(teachingCalendarStudentResource.getOpenId());
            groupMember.setCreateTime(now);
            groupMember.setSource(GroupMemberSource.TEACHING_CALENDAR);
            groupMemberList.add(groupMember);
        });
        groupMemberRepository.saveAll(groupMemberList);
    }

    private CourseTableDetail saveCourseTableDetail(TeachingCalendarCourseTableDetail courseTableDetails, CourseTable courseTable, Date now) {
        CourseTableDetail courseTableDetail = new CourseTableDetail();
        CourseType courseType = courseTypeRepository.findById(1).get();
        courseTableDetail.setCourseStatus(CourseStatus.NOT_CLASS);
        courseTableDetail.setCourseTable(courseTable);
        courseTableDetail.setSource(Source.TEACHING_CALENDAR);
        courseTableDetail.setCourseDate(DateUtils.stringToDate(DATE, courseTableDetails.getCourseDate()));
        courseTableDetail.setCourseType(courseType);
        courseTableDetail.setCourseTypeName(courseType.getCourseTypeName());
        courseTableDetail.setSegment(courseTableDetails.getSegments());
        courseTableDetail.setSegmentStartTime(DateUtils.stringToDate(DateUtils.TIME, courseTableDetails.getSectionMinStartTime()));
        courseTableDetail.setSegmentEndTime(DateUtils.stringToDate(DateUtils.TIME, courseTableDetails.getSectionMaxEndTime()));
        courseTableDetail.setWeek(courseTableDetails.getWeek());
        courseTableDetail.setCourseKind(CourseKind.EXPERIMENTAL_COURSE);
        courseTableDetail.setWeekNum(calcWeekNumByDate(courseTableDetails.getCourseDate()));
        courseTableDetail.setCreateTime(now);
        courseTableDetail.setCourseName(courseTable.getCourseName());
        String teacherNames = courseTableDetails.getTeacherList().stream()
                .map(teacherResource ->
                        teacherResource.getTeacherName() + ""
                ).collect(joining(","));
        courseTableDetail.setTeacherNames(teacherNames);
        return courseTableDetailRepository.save(courseTableDetail);
    }

    private Integer calcWeekNumByDate(String courseDate) {
        Date parseDate = DateUtils.stringToDate(DATE, courseDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        Integer weekNum = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (weekNum == 0) {
            weekNum = sunday;
        }
        return weekNum;
    }

    public ResponseEntity<Void> updateCourseTableItems(String courseTableDetailId, String id, LabResource labResource) {
        CourseTableDetailRoomUser courseTableDetailRoomUser =
                courseTableDetailRoomUserRepository.findByCourseTableDetailIdAndRoomId(courseTableDetailId, id);
        if (Objects.isNull(courseTableDetailRoomUser)) {
            return new ResponseEntity(ErrorResult.customError("暂未生成课表信息"), HttpStatus.NOT_FOUND);
        }
        courseTableDetailRoomUser.setItemTeacherNo(labResource.getItemTeacherNo());
        courseTableDetailRoomUser.setItemTeacherId(labResource.getItemTeacherId());
        courseTableDetailRoomUser.setItemTeacherName(labResource.getItemTeacherName());
        courseTableDetailRoomUserRepository.save(courseTableDetailRoomUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<RoomScheduleInformation>> getRoomScheduleInformationList(String roomIds) {
        List<String> roomIdList = Arrays.asList(roomIds.split(","));
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository
                .getRoomScheduleInformationList(roomIdList, stringToDate(DATE, formatDate(DATE, getSqlDate())));
        if (courseTableDetailList.size() > 0) {
            List<RoomScheduleInformation> roomScheduleInformationList = new ArrayList<>();
            courseTableDetailList.forEach(courseTableDetail -> courseTableDetail
                    .getCourseTableDetailRoomUserList()
                    .forEach(courseTableDetailRoomUser -> {
                        if (Objects.nonNull(courseTableDetailRoomUser)) {
                            RoomScheduleInformation roomScheduleInformation = new RoomScheduleInformation();
                            roomScheduleInformation.setId(courseTableDetail.getId());
                            roomScheduleInformation.setCourseDate(formatDate(DATE, courseTableDetail.getCourseDate()));
                            roomScheduleInformation.setSegmentStartTime(formatDate(DATE, courseTableDetail.getCourseDate())
                                    + " " + formatDate(TIME_SECOND, courseTableDetail.getSegmentStartTime()));
                            roomScheduleInformation.setSegmentEndTime(formatDate(DATE, courseTableDetail.getCourseDate())
                                    + " " + formatDate(TIME_SECOND, courseTableDetail.getSegmentEndTime()));
                            roomScheduleInformation.setRoomId(courseTableDetailRoomUser.getRoomId());
                            roomScheduleInformation.setRoomCode(courseTableDetailRoomUser.getRoomCode());
                            roomScheduleInformation.setRoomName(courseTableDetailRoomUser.getRoomName());
                            roomScheduleInformationList.add(roomScheduleInformation);
                        }
                    }));
            return new ResponseEntity<>(roomScheduleInformationList, HttpStatus.OK);
        }
        return new ResponseEntity(ErrorResult.dataNotExistError("课表"), HttpStatus.NOT_FOUND);
    }


    public List<RoomScheduleInformation> getTimeFrameCourseTableDetailList(String startTime, String endTime) {

        String startDateToString = startTime.substring(0, startTime.indexOf(" "));
        String endDateToString = endTime.substring(0, startTime.indexOf(" "));
        String startTimeToString = startTime.substring(startTime.indexOf(" ") + 1);
        String endTimeToString = endTime.substring(endTime.indexOf(" ") + 1);


        String sql = " select b.id as id,DATE_FORMAT(b.course_date ,'%Y-%m-%d') as courseDate,\n" +
                "CONCAT(b.course_date,' ',b.segment_start_time) as segmentStartTime,\n" +
                "CONCAT(b.course_date,' ',b.segment_end_time) as segmentEndTime,\n" +
                "a.room_id as roomId,a.room_code as roomCode,a.room_name as roomName\n" +
                "from tb_course_table_detail_room_user a INNER JOIN tb_course_table_detail b on a.course_table_detail_id=b.id " +
                " where course_table_detail_id in (" +
                " SELECT  id FROM   tb_course_table_detail   WHERE " +
                " ( course_date BETWEEN '" + startDateToString + "' and '" + endDateToString + "' ) " +
                "and " +
                "( " +
                " '" + startTimeToString + "' BETWEEN segment_start_time and segment_end_time " +
                " OR '" + endTimeToString + "' BETWEEN segment_start_time and segment_end_time  " +
                " OR (segment_start_time BETWEEN '" + startTimeToString + "' and '" + endTimeToString + "' " +
                " and segment_end_time BETWEEN '" + startTimeToString + "' and '" + endTimeToString + "' )" +
                "))";


        Query queryData = entityManager.createNativeQuery(sql);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(RoomScheduleInformation.class));
        List<RoomScheduleInformation> resultList = queryData.getResultList();
        if (CollectionUtils.isNotEmpty(resultList)) {
            return resultList;
        }

        return new ArrayList<>();
    }

}

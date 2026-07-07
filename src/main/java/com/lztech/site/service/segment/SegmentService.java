package com.lztech.site.service.segment;

import com.lztech.domain.model.coursetable.enums.WeekType;
import com.lztech.domain.model.segment.Segment;
import com.lztech.domain.model.segment.SegmentPK;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.segment.SegmentRepository;
import com.lztech.persistence.repositories.terms.TermRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.resource.segment.SegmentTimeResource;
import com.lztech.site.resource.segment.WeekIndexResource;
import com.lztech.site.service.classroom.ClassRoomService;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.until.Week;
import com.lztech.site.viewmodel.SegmentResource;
import com.lztech.site.viewmodel.buildings.Buildings;
import com.lztech.site.viewmodel.classroom.ClassroomAndFloorResource;
import com.lztech.site.viewmodel.segment.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SegmentService {
    private final int two = 2, three = 3;
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private SegmentRepository segmentRepository;
    @Autowired
    private EntityManager entityManager;
    @Value("${studentType}")
    private String studentType;

    @Autowired
    private ClassRoomService classRoomService;

    private int dayOfWeek = 7;

    public ResponseEntity<List<SegmentTaskResource>> getSegmentTaskList(String date, Integer studentType) {
        Term term = getNowTerm();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        long weekCounts = TimeUtils.getWeekCounts(sdf.format(term.getStartDate()), sdf.format(term.getEndDate()));
        Map<Integer, String[]> termCalendar = TimeUtils.getTermCalendar(sdf.format(term.getStartDate()), weekCounts);
        String week = TimeUtils.getWeekNumByDate(termCalendar, date);
        Date parseDate = DateUtils.stringToDate(DateUtils.DATE, date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        Integer weekNum = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (weekNum == 0) {
            weekNum = dayOfWeek;
        }
        List<Segment> segments = segmentRepository.findAll();



        List<CourseTableDetailSegmentVo> courseTableDetailSegmentVos = getCourseTableDetailSegmentVosByDate(date, studentType);
        // 教室未绑定上楼栋上，使用默认楼栋节次录制，适用于tb_segment表中未添加楼栋节次的异常情况  2025-12-19
//        List<Build> builds = classRoomService.getBuild();
//        courseTableDetailSegmentVos.forEach(courseTableDetailSegmentVo -> {
//            for (Build build : builds) {
//                List<String> roomIds = build.getRooms().stream().map(Room::getRoomId).collect(Collectors.toList());
//                if (roomIds.contains(courseTableDetailSegmentVo.getRoomId())) {
//                    courseTableDetailSegmentVo.setBuildId(build.getBuildId());
//                    courseTableDetailSegmentVo.setBuildName(build.getBuildName());
//                }
//            }
//        });
        List<SegmentTaskResource> segmentTaskResources = new ArrayList<>();
        Integer finalWeekNum = weekNum;
        segments.forEach(segment -> {
            SegmentTaskResource segmentTaskResource = formatToSegmentTaskResource(segment);
            segmentTaskResource.setWeek(week);
            segmentTaskResource.setWeekNum(finalWeekNum);
            setCourseTableDetailSegment(segmentTaskResource, courseTableDetailSegmentVos);
            segmentTaskResources.add(segmentTaskResource);
        });

        return new ResponseEntity<>(segmentTaskResources, HttpStatus.OK);
    }

    private List<CourseTableDetailSegmentVo> getCourseTableDetailSegmentVosByDate(String date, Integer defaultStudentType) {
        StringBuilder querySql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<>();
        querySql.append("SELECT ct.student_type as studentType, ctdru.room_id AS roomId," +
                " ctdru.room_name AS roomName,ct.school_year AS schoolYear,ct.term AS term," +
                " ct.group_id AS groupId, g.group_name AS groupName,ctd.week_num as weekNum," +
                " (SELECT count(1) FROM tb_group_member gm WHERE gm.group_id = ct.group_id and gm.group_member_status=0 " +
                " and (gm.learning_method_code is null or gm.learning_method_code =1 )) AS studentCount," +
                " ctd.week AS week, ct.course_id AS courseId,ct.course_name AS courseName,ct.id AS courseTableId," +
                " ctd.id AS courseTableDetailId,s.buildid AS buildId,s.build_name AS buildName,cs.segment AS segment," +
                " (SELECT GROUP_CONCAT(CONCAT(ctdt.teacher_id,',',ctdt.teacher_name,',',ctdt.teacher_no) order by ctdt.show_order)" +
                " FROM tb_course_table_detail_teacher ctdt" +
                " WHERE ctdt.course_table_detail_id = ctd.id group by course_table_detail_id) as teachers,ct.college_id as collegeId," +
                " ct.college_name as collegeName,c.course_code as courseCode,ctd.source_data_source as sourceDataSource," +
                "ctd.source_data_source_name as sourceDataSourceName " +
                " FROM tb_course_table ct" +
                " INNER JOIN tb_course c ON c.id = ct.course_id" +
                " INNER JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id" +
                " INNER JOIN tb_course_segment cs ON cs.course_table_detail_id = ctd.id" +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = ctd.id" +
                " INNER JOIN tb_group g on g.id = ct.group_id" +
                " INNER JOIN tb_segment s ON cs.build_id = s.buildid and cs.segment = s.segment");
        querySql.append(" WHERE  ctd.course_date = STR_TO_DATE(:date,'%Y-%m-%d') and ctdru.room_id is not null ");
        paramMap.put("date", date);
        if (Objects.nonNull(defaultStudentType) && !defaultStudentType.equals(-1)) {
            querySql.append(" and ct.student_type = :studentType ");
            paramMap.put("studentType", defaultStudentType);
        }
        Query query = entityManager.createNativeQuery(querySql.toString());
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailSegmentVo.class));
        return query.getResultList();
    }

    private void setCourseTableDetailSegment(SegmentTaskResource segmentTaskResource, List<CourseTableDetailSegmentVo> courseTableDetailSegmentVos) {
        List<CourseTableDetailSegment> courseTableDetailSegments = new ArrayList<>();

        List<CourseTableDetailSegmentVo> courseTableDetailSegmentVoList = courseTableDetailSegmentVos.stream()
                .filter(courseTableDetailSegmentVo -> courseTableDetailSegmentVo.getBuildId().equals(segmentTaskResource.getBuildId())
                        && courseTableDetailSegmentVo.getSegment().equals(segmentTaskResource.getSegment())).collect(Collectors.toList());

        buildCourseTableDetailSegment(courseTableDetailSegments, courseTableDetailSegmentVoList);

        segmentTaskResource.setCourseTableDetailSegments(courseTableDetailSegments);
    }

    private void buildCourseTableDetailSegment(List<CourseTableDetailSegment> courseTableDetailSegments,
                                               List<CourseTableDetailSegmentVo> courseTableDetailSegmentVoList) {
        courseTableDetailSegmentVoList.forEach(courseTableDetailSegmentVo -> {
            CourseTableDetailSegment courseTableDetailSegment = new CourseTableDetailSegment();
            courseTableDetailSegment.setStudentType(courseTableDetailSegmentVo.getStudentType());
            courseTableDetailSegment.setCourseId(courseTableDetailSegmentVo.getCourseId());
            courseTableDetailSegment.setCourseCode(courseTableDetailSegmentVo.getCourseCode());
            courseTableDetailSegment.setCourseName(courseTableDetailSegmentVo.getCourseName());
            courseTableDetailSegment.setCourseTableDetailId(courseTableDetailSegmentVo.getCourseTableDetailId());
            courseTableDetailSegment.setCourseTableId(courseTableDetailSegmentVo.getCourseTableId());
            courseTableDetailSegment.setGroupId(courseTableDetailSegmentVo.getGroupId());
            courseTableDetailSegment.setGroupName(courseTableDetailSegmentVo.getGroupName());
            courseTableDetailSegment.setRoomId(courseTableDetailSegmentVo.getRoomId());
            courseTableDetailSegment.setRoomName(courseTableDetailSegmentVo.getRoomName());
            courseTableDetailSegment.setSchoolYear(courseTableDetailSegmentVo.getSchoolYear());
            courseTableDetailSegment.setStudentCount(Integer.valueOf(courseTableDetailSegmentVo.getStudentCount().toString()));
            courseTableDetailSegment.setTerm(courseTableDetailSegmentVo.getTerm());
            courseTableDetailSegment.setWeek(courseTableDetailSegmentVo.getWeek());
            courseTableDetailSegment.setWeekNum(courseTableDetailSegmentVo.getWeekNum());
            courseTableDetailSegment.setSegment(courseTableDetailSegmentVo.getSegment());
            courseTableDetailSegment.setCollegeId(courseTableDetailSegmentVo.getCollegeId());
            courseTableDetailSegment.setCollegeName(courseTableDetailSegmentVo.getCollegeName());
            courseTableDetailSegment.setSourceDataSource(courseTableDetailSegmentVo.getSourceDataSource());
            courseTableDetailSegment.setSourceDataSourceName(courseTableDetailSegmentVo.getSourceDataSourceName());
            if (courseTableDetailSegmentVo.getTeachers() != null) {
                String[] teachers = courseTableDetailSegmentVo.getTeachers().split(",");
                StringBuilder teacherIds = new StringBuilder();
                StringBuilder teacherNames = new StringBuilder();
                StringBuilder teacherNos = new StringBuilder();

                for (int i = 0; i < teachers.length / three; i++) {
                    teacherIds.append(teachers[three * i] + ",");
                    teacherNames.append(teachers[three * i + 1] + ",");
                    teacherNos.append(teachers[three * i + two] + ",");
                }
                courseTableDetailSegment.setTeacherIds(teacherIds.substring(0, teacherIds.lastIndexOf(",")));
                courseTableDetailSegment.setTeacherNames(teacherNames.substring(0, teacherNames.lastIndexOf(",")));
                courseTableDetailSegment.setTeacherNos(teacherNos.substring(0, teacherNos.lastIndexOf(",")));
            } else {
                courseTableDetailSegment.setTeacherIds("");
                courseTableDetailSegment.setTeacherNames("");
                courseTableDetailSegment.setTeacherNos("");
            }

            courseTableDetailSegments.add(courseTableDetailSegment);
        });
    }


    private SegmentTaskResource formatToSegmentTaskResource(Segment segment) {
        SegmentTaskResource task = new SegmentTaskResource();
        task.setBuildId(segment.getBuildID());
        task.setBuildName(segment.getBuildName());
        task.setSegmentStartTime(segment.getStartTime());
        task.setSegmentEndTime(segment.getEndTime());
        task.setSegmentRecordStartTime(segment.getRecordStartTime());
        task.setSegmentRecordEndTime(segment.getRecordEndTime());
        task.setSegment(segment.getSegment());
        return task;
    }


    public ResponseEntity<List<SegmentResource>> getSegmentList(String buildId) {
        List<Segment> segmentList;
        if (StringUtils.isEmpty(buildId)) {
            segmentList = segmentRepository.findAllByOrderByBuildName();
        } else {
            segmentList = segmentRepository.findByBuildIdOrderByBuildName(buildId);
        }
        List<SegmentResource> segmentResourceList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(segmentList)) {
            for (Segment segment : segmentList) {
                SegmentResource segmentResource = new SegmentResource();
                segmentResource.setBuildName(segment.getBuildName());
                segmentResource.setDescription(segment.getDescription());
                segmentResource.setEndTime(segment.getEndTime());
                segmentResource.setStartTime(segment.getStartTime());
                segmentResource.setSegment(segment.getSegmentPK().getSegment().toString());
                segmentResourceList.add(segmentResource);
            }
        }
        return new ResponseEntity<>(segmentResourceList, HttpStatus.OK);
    }

    public ResponseEntity<List<Buildings>> getBuildings() {

        List<Buildings> buildingsList = new ArrayList<>();
        List<Segment> segmentList = segmentRepository.findAllGroupByBuildId();
        if (CollectionUtils.isNotEmpty(segmentList)) {
            for (Segment seg : segmentList) {
                Buildings buildings = new Buildings();
                buildings.setBuildId(seg.getSegmentPK().getBuildID());
                buildings.setBuildName(seg.getBuildName());
                buildingsList.add(buildings);
            }
        }
        return new ResponseEntity<>(buildingsList, HttpStatus.OK);
    }


    public List<Integer> getNowSegment(String buildingId) {
        if (StringUtils.isEmpty(buildingId)) {
            buildingId = "0";
        }
        List<Integer> resultList = new ArrayList<>();
        Term nowTerm = getNowTerm();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = TimeUtils.dateToStr(nowTerm.getStartDate());
        LocalDate termStartDate = LocalDate.parse(start, df);
        LocalDate nowDate = LocalDate.now();
        int termDate = (int) (nowDate.toEpochDay() - termStartDate.toEpochDay());
        int weekNum = (termDate + termStartDate.getDayOfWeek().getValue()) / dayOfWeek;
        if (nowDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            weekNum = weekNum - 1;
        }
        int weekNo = nowDate.getDayOfWeek().getValue() - 1;
        int segmentIndex;
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        Segment segment = segmentRepository.getNowSegment(nowTime, buildingId);
        if (segment == null) {//不在节次范围内取下一节次
            List<Segment> segmentList = segmentRepository.getNextSegment(buildingId, nowTime);
            if (segmentList.size() == 0) {
                weekNum = weekNo == dayOfWeek - 1 ? weekNum + 1 : weekNum;
                weekNo = weekNo == dayOfWeek - 1 ? 0 : weekNo + 1;
                segmentIndex = 0;
            } else {
                segmentIndex = segmentList.get(0).getSegment() - 1;
            }
        } else {
            segmentIndex = segment.getSegment() - 1;
        }
        resultList.add(weekNum);
        resultList.add(weekNo);
        resultList.add(segmentIndex);
        return resultList;
    }

    public ResponseEntity<CourseDate> dateByWeek(String buildingId, Integer weekNum, Integer weekDay) {
        if (org.springframework.util.StringUtils.isEmpty(buildingId)) {
            buildingId = "0";
        }
        CourseDate courseDateTime = new CourseDate();
        Term nowTerm = getNowTerm();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(TimeUtils.dateToStr(nowTerm.getStartDate()), df);
        String courseDate = startDate.plusDays((weekNum - 1) * dayOfWeek + weekDay - startDate.getDayOfWeek()
                .getValue()).format(df);
        courseDateTime.setSegmentTime(courseDate);
        return new ResponseEntity<>(courseDateTime, HttpStatus.OK);
    }

    public ResponseEntity<SegmentTimeResource> getSegmentTime(String buildId, Integer weekNum,
                                                              Integer weekDay, Integer segment) {
        if (org.springframework.util.StringUtils.isEmpty(buildId)) {
            buildId = "0";
        }
        Term nowTerm = getNowTerm();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(TimeUtils.dateToStr(nowTerm.getStartDate()), df);
        String courseStartDate = startDate.plusDays((weekNum - 1) * Constant.WEEK_DAYS_NUM + weekDay - startDate.getDayOfWeek()
                .getValue()).format(df);
        String courseEndDate = "";
        SegmentTimeResource segmentTimeResource = new SegmentTimeResource();
        if (weekNum == 0) {
            courseStartDate = TimeUtils.dateToStr(nowTerm.getStartDate());
            courseEndDate = TimeUtils.dateToStr(nowTerm.getEndDate());
        }
        if (weekDay == 0 && weekNum != 0) {
            courseStartDate = startDate.plusDays((weekNum - 1) * Constant.WEEK_DAYS_NUM + weekDay - startDate.getDayOfWeek()
                    .getValue() + 1).format(df);
            courseEndDate = startDate.plusDays((weekNum) * Constant.WEEK_DAYS_NUM + weekDay - startDate.getDayOfWeek()
                    .getValue()).format(df);
        }
        if (segment == 0) {
            List<Segment> segments = segmentRepository.findByBuildId(buildId);
            if (segments.size() == 0) {
                return new ResponseEntity<>(segmentTimeResource, HttpStatus.OK);
            }
            segments.stream().sorted(Comparator.comparing(Segment::getStartTime))
                    .collect(Collectors.toList());
            segmentTimeResource.setSegmentTime(segments.get(0).getStartTime() + "-" + segments.get(segments.size() - 1)
                    .getEndTime());
        }
        if (segment != 0) {
            Segment segment1 = segmentRepository.findBySegmentPK(new SegmentPK(buildId, segment));
            if (segment1 == null) {
                return new ResponseEntity<>(segmentTimeResource, HttpStatus.OK);
            }
            segmentTimeResource.setSegmentTime(segment1.getStartTime() + "-" + segment1.getEndTime());
        }
        segmentTimeResource.setStartDate(courseStartDate);
        segmentTimeResource.setEndDate(courseEndDate);
        return new ResponseEntity<>(segmentTimeResource, HttpStatus.OK);
    }

    public ResponseEntity<WeekIndexResource> getNowSegmentByBuildId(String buildId) {
        if (StringUtils.isEmpty(buildId)) {
            buildId = "0";
        }
        WeekIndexResource weekIndexResource = new WeekIndexResource();
        List<Segment> segmentList = segmentRepository.findByBuildIdOrderByBuildName(buildId);
        if (segmentList.size() == 0) {
            return new ResponseEntity<>(weekIndexResource, HttpStatus.OK);
        }
        List<Integer> segmentIdx = getNowSegment(buildId);
        weekIndexResource.setWeekIndex(segmentIdx);
        return new ResponseEntity<>(weekIndexResource, HttpStatus.OK);
    }

    public ResponseEntity<CourseDate> timeBySegment(String buildingId, Integer sSegment, Integer eSegment) {
        if (org.springframework.util.StringUtils.isEmpty(buildingId)) {
            buildingId = "0";
        }
        CourseDate courseDate = new CourseDate();
        Segment segment1 = segmentRepository.findBySegmentPK(new SegmentPK(buildingId, sSegment));
        Segment segment2 = segmentRepository.findBySegmentPK(new SegmentPK(buildingId, eSegment));
        courseDate.setSegmentTime(segment1.getStartTime() + "-" + segment2.getEndTime());
        return new ResponseEntity<>(courseDate, HttpStatus.OK);
    }

    public NowSegment getNowSegmentInfo(String buildId) {
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        buildId = StringUtils.isEmpty(buildId) ? "0" : buildId;
        Segment segment = segmentRepository.getNowSegment(nowTime, buildId);
        if (!Objects.isNull(segment)) {
            NowSegment nowSegment = new NowSegment();
            nowSegment.setBuildId(buildId);
            nowSegment.setSegment(segment.getSegment());
            nowSegment.setBuildName(segment.getBuildName());
            nowSegment.setStartTime(segment.getStartTime());
            nowSegment.setEndTime(segment.getEndTime());
            return nowSegment;
        }
        return null;
    }

    public ResponseEntity<List<BuildingVo>> getSegmentListByBuildIds(String buildIds) {
        List<BuildingVo> buildingVoList = new ArrayList<>();
        List<String> buildIdList = Arrays.asList(buildIds.split("、"));
        List<Segment> segmentList = segmentRepository.findAllByBuildIDInOrderByBuildName(buildIdList);
        List<Segment> defaultSegments = segmentRepository.findByBuildId("0");
        for (String buildId : buildIdList) {
            List<Segment> segments = segmentList
                    .stream()
                    .filter(segment ->
                            segment.getSegmentPK().getBuildID()
                                    .equals(buildId)).collect(Collectors.toList());
            BuildingVo buildingVo = new BuildingVo();
            buildingVo.setBuildId(buildId);
            if (segments.size() > 0) {
                buildingVo.setSegmentList(segments.stream()
                        .map(segment -> new SegmentVo() {{
                            this.setSegmentEndTime(segment.getEndTime());
                            this.setSegmentStartTime(segment.getStartTime());
                            this.setSegmentName(segment.getSegmentPK().getSegment() + "");
                        }}).collect(Collectors.toList()));
                buildingVoList.add(buildingVo);
            } else {
                buildingVo.setSegmentList(defaultSegments.stream()
                        .map(segment -> new SegmentVo() {{
                            this.setSegmentEndTime(segment.getEndTime());
                            this.setSegmentStartTime(segment.getStartTime());
                            this.setSegmentName(segment.getSegmentPK().getSegment() + "");
                        }}).collect(Collectors.toList()));
                buildingVoList.add(buildingVo);
            }
        }

        return new ResponseEntity<>(buildingVoList, HttpStatus.OK);
    }

    //region 教室查询结果详情
    public ResponseEntity<List<ClassroomSegmentCourseVo>> getSegmentsClassroomSegmentCourseDetails(SegmentQueryParam segmentQueryParam) {
        List<ClassroomSegmentCourseVo> classroomSegmentCourseVos = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "select cs.segment as segment,ct.course_name as courseName,ct.teacher_name as teacherName," +
                " ct.college_name as collegeName,ctdru.room_name as roomName,ct.week_type as weekType,ct.group_id as groupId," +
                " cc.course_category_name as courseCategoryName,ct.id as courseTableId" +
                " from tb_course_segment cs" +
                " inner join tb_course_table_detail ctd on cs.course_table_detail_id = ctd.id" +
                " inner join tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id" +
                " inner join tb_course_table ct on ctd.course_table_id = ct.id" +
                " inner join tb_course_category cc on cc.id = ct.course_category_id " +
                " where ctdru.room_id = :classRommId" +
                " and ctd.course_date = :searchDate";
        paramMap.put("classRommId", segmentQueryParam.getClassRoomId());
        paramMap.put("searchDate", segmentQueryParam.getSearchDate());
        if (!studentType.equals("-1")) {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql += " and ct.student_type = :studentType ";
            paramMap.put("studentType", studentTypeQuery);
        }

        List<Segments> occupySectionList = segmentQueryParam.getSegmentList().stream()
                .filter(segments -> segments.getOccupyTypeIndex() == 0).collect(Collectors.toList());
        List<Integer> segments = occupySectionList.stream().map(Segments::getSegment).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Integer segment : segments) {
            sb.append(segment);
            sb.append(",");
        }
        sql += " and cs.segment in :segments";
        paramMap.put("segments", segments);
        if (StringUtils.isNotEmpty(segmentQueryParam.getBuildingId())) {
            sql += " and cs.build_id = :buildingId";
            paramMap.put("buildingId", segmentQueryParam.getBuildingId());
        }
        sql += " order by cs.segment";
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ClassroomSegmentCourseResource.class));
        classroomSegmentCourseVos = getClassroomSegmentCourseList(query.getResultList());
        return new ResponseEntity<>(classroomSegmentCourseVos, HttpStatus.OK);
    }
    //endregion

    //region 课表查询结果详情
    public ResponseEntity<ClassroomSegmentCoursePage> getSegmentCourseScheduleDetails(CourseQueryParam courseQueryParam) {
        ClassroomSegmentCoursePage classroomSegmentCoursePage = new ClassroomSegmentCoursePage();
        Map<String, Object> paramMap = new HashMap<>();
        Query queryCount = entityManager.createNativeQuery(createSegmentCourseScheduleCountSql(courseQueryParam, paramMap));
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        int count = ((BigInteger) resMap.get("count")).intValue();
        if (count == 0) {
            classroomSegmentCoursePage.setPage(courseQueryParam.getPage());
            classroomSegmentCoursePage.setPageSize(courseQueryParam.getPageSize());
            classroomSegmentCoursePage.setTotalCount(0);
            classroomSegmentCoursePage.setPageCount(0);
            classroomSegmentCoursePage.setClassroomSegmentCourseList(new ArrayList<>());
            classroomSegmentCoursePage.setWeek("");
            classroomSegmentCoursePage.setWeekNum("");
        } else {
            Map<String, Object> paramMapcreateSegment = new HashMap<>();
            Query queryData = entityManager.createNativeQuery(createSegmentCourseScheduleSql(courseQueryParam, paramMapcreateSegment));
            paramMapcreateSegment.forEach(queryData::setParameter);
            queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ClassroomSegmentCourseResource.class));
            classroomSegmentCoursePage.setPage(courseQueryParam.getPage());
            classroomSegmentCoursePage.setPageSize(courseQueryParam.getPageSize());
            classroomSegmentCoursePage.setTotalCount(count);
            classroomSegmentCoursePage.setPageCount((int) Math.ceil((double) count /
                    (double) courseQueryParam.getPageSize()));
            List<ClassroomSegmentCourseResource> resultList = queryData.getResultList();
            classroomSegmentCoursePage.setClassroomSegmentCourseList(getClassroomSegmentCourseList(resultList));
            classroomSegmentCoursePage.setWeek(Week.getEntranceGuardStatusName(resultList.get(0).getWeekNum()));
            classroomSegmentCoursePage.setWeekNum("第" + resultList.get(0).getWeek() + "周");
        }
        return new ResponseEntity<>(classroomSegmentCoursePage, HttpStatus.OK);
    }


    private List<ClassroomSegmentCourseVo> getClassroomSegmentCourseList(List<ClassroomSegmentCourseResource> classroomSegmentCourseResources) {
        List<ClassroomSegmentCourseVo> classroomSegmentCourseVos = new ArrayList<>();
        if (classroomSegmentCourseResources.size() > 0) {
            List<String> groupIds = classroomSegmentCourseResources
                    .stream()
                    .map(ClassroomSegmentCourseResource::getGroupId)
                    .collect(Collectors.toList());
            String groupSql = "select g.group_name as groupName, count(1) as studentNum,g.id as groupId" +
                    " from tb_group g" +
                    " inner join tb_group_member gm on g.id = gm.group_id";
            StringBuilder groupIdBuilder = new StringBuilder();
            for (String groupId : groupIds) {
                groupIdBuilder.append("'");
                groupIdBuilder.append(groupId);
                groupIdBuilder.append("',");
            }
            groupSql += " and g.id in (" + groupIdBuilder.substring(0, groupIdBuilder.length() - 1) + ")";
            groupSql += " group by g.id";
            Query groupQuery = entityManager.createNativeQuery(groupSql);
            groupQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GroupAndMemberVo.class));
            List<GroupAndMemberVo> groupAndMemberVos = groupQuery.getResultList();
            classroomSegmentCourseResources.forEach(classroomSegmentCourseResource -> {
                ClassroomSegmentCourseVo classroomSegmentCourseVo = new ClassroomSegmentCourseVo();
                GroupAndMemberVo memberVo = groupAndMemberVos
                        .stream()
                        .filter(groupAndMemberVo -> classroomSegmentCourseResource.getGroupId().equals(groupAndMemberVo.getGroupId()))
                        .findFirst().orElse(null);
                if (ObjectUtils.isNotEmpty(memberVo)) {
                    classroomSegmentCourseVo.setClassName(memberVo.getGroupName());
                    classroomSegmentCourseVo.setStudentNums(memberVo.getStudentNum().intValue());
                }
                classroomSegmentCourseVo.setCollegeName(classroomSegmentCourseResource.getCollegeName());
                classroomSegmentCourseVo.setCourseCategoryName(classroomSegmentCourseResource.getCourseCategoryName());
                classroomSegmentCourseVo.setCourseName(classroomSegmentCourseResource.getCourseName());
                classroomSegmentCourseVo.setCourseWeekName(
                        WeekType.getWeekTypeSource(classroomSegmentCourseResource.getWeekType()).getWeekTypeName());
                classroomSegmentCourseVo.setRoomName(classroomSegmentCourseResource.getRoomName());
                if (classroomSegmentCourseResource.getSegment() != null) {
                    classroomSegmentCourseVo.setSectionName("第" + classroomSegmentCourseResource.getSegment() + "节次");
                }
                classroomSegmentCourseVo.setTeacherName(classroomSegmentCourseResource.getTeacherName() == null
                        ? "" : classroomSegmentCourseResource.getTeacherName());
                classroomSegmentCourseVos.add(classroomSegmentCourseVo);
            });
        }
        return classroomSegmentCourseVos;
    }

    private String createSegmentCourseScheduleSql(CourseQueryParam courseQueryParam, Map<String, Object> paramMap) {
        String sql = "select ctd.week as week,ctd.week_num as weekNum,ct.course_name as courseName,ctd.teacher_names as teacherName," +
                " ct.college_name as collegeName,ctdru.room_name as roomName,ct.week_type as weekType,ct.group_id as groupId," +
                " cc.course_category_name as courseCategoryName,ct.id as courseTableId" +
                " from tb_course_segment cs" +
                " inner join tb_course_table_detail ctd on cs.course_table_detail_id = ctd.id" +
                " inner join tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id" +
                " inner join tb_course_table ct on ctd.course_table_id = ct.id" +
                " inner join tb_course_category cc on cc.id = ct.course_category_id " +
                " where 1=1";

        sql = setParams(courseQueryParam, sql, paramMap);
        int start = (courseQueryParam.getPage() - 1) * courseQueryParam.getPageSize();
        int size = courseQueryParam.getPageSize();
        sql += " limit " + start + "," + size;
        return sql;
    }

    private String createSegmentCourseScheduleCountSql(CourseQueryParam courseQueryParam, Map<String, Object> paramMap) {
        String queryCountSql = "select count(1) as count" +
                " from tb_course_segment cs" +
                " inner join tb_course_table_detail ctd on cs.course_table_detail_id = ctd.id" +
                " inner join tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id" +
                " inner join tb_course_table ct on ctd.course_table_id = ct.id" +
                " inner join tb_course_category cc on cc.id = ct.course_category_id " +
                " where 1=1";
        queryCountSql = setParams(courseQueryParam, queryCountSql, paramMap);
        return queryCountSql;
    }

    private String setParams(CourseQueryParam courseQueryParam, String queryCountSql, Map<String, Object> paramMap) {
        if (StringUtils.isNotEmpty(courseQueryParam.getCollegeId())) {
            queryCountSql += " and ct.college_id = :collegeId";
            paramMap.put("collegeId", courseQueryParam.getCollegeId());
        }
        if (StringUtils.isNotEmpty(courseQueryParam.getRoomId())) {
            queryCountSql += " and ctdru.room_id = :roomId";
            paramMap.put("roomId", courseQueryParam.getRoomId());
        }
        if (StringUtils.isNotEmpty(courseQueryParam.getCourseOrTeacherName())) {
            String s = "%" + CharactersReplace.replaceCharacters(courseQueryParam.getCourseOrTeacherName()) + "%";
            queryCountSql += " and (ct.course_name like :courseName or ct.teacher_name like :teacherName)";
            paramMap.put("courseName", s);
            paramMap.put("teacherName", s);
        }
        queryCountSql += " and cs.segment = :segment" +
                " and cs.build_id = :buildId" +
                " and ctd.course_date = :courseDate";
        paramMap.put("segment", courseQueryParam.getSegment());
        paramMap.put("buildId", courseQueryParam.getBuildingId());
        paramMap.put("courseDate", courseQueryParam.getSearchDate());

        return queryCountSql;
    }
    //endregion


    public List<SegmentDataResource> getSegmentDataResource() {

        Map<String, Object> paramMap = new HashMap<>();
        Query queryData = entityManager.createNativeQuery(selectSegmentDataResource(paramMap));
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<SegmentDataResource> segmentDataResources = queryData.getResultList();
        return segmentDataResources;
    }

    private String selectSegmentDataResource(Map<String, Object> paramMap) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowDate = LocalDateTime.now().format(df);
        StringBuilder result = new StringBuilder("SELECT " +
                " cs.segment, " +
                " count( DISTINCT ctd.id ) AS counts ," +
                " count( DISTINCT dr.room_id ) AS roomIdCount ," +
                " (count( DISTINCT ctd.id )-count( DISTINCT dr.room_id ) ) AS repetitions " +
                " FROM " +
                " tb_course_table_detail ctd " +
                " JOIN tb_course_table_detail_room_user dr on ctd.id= dr.course_table_detail_id " +
                " JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " WHERE " +
                " ctd.course_date = :nowDate");
        paramMap.put("nowDate", nowDate);
        result.append(" AND dr.room_id != '' " +
                " AND ctd.source = 0 " +
                " GROUP BY cs.segment ORDER BY cs.segment ASC");
        return result.toString();
    }

    public List<NowSegment> getDefaultBuildingSegment() {
        List<Segment> segments = segmentRepository.findByBuildId("0");
        List<NowSegment> nowSegments = new ArrayList<>();
        for (Segment segment : segments) {
            NowSegment nowSegment = new NowSegment();
            nowSegment.setBuildId(segment.getBuildID());
            nowSegment.setSegment(segment.getSegment());
            nowSegment.setBuildName(segment.getBuildName());
            nowSegment.setStartTime(segment.getStartTime());
            nowSegment.setEndTime(segment.getEndTime());
            nowSegments.add(nowSegment);
        }
        return nowSegments;
    }

    public ResponseEntity<List<SegmentVo>> getAllSegmentsDistinct() {
        List<SegmentVo> segmentVos = new ArrayList<>();
        List<Segment> segments = segmentRepository.findAllSegmentsDistinct();
        segments.forEach(segment -> {
            SegmentVo segmentVo = new SegmentVo();
            segmentVo.setSegmentName(segment.getSegment().toString());
            segmentVo.setSegmentStartTime(segment.getStartTime());
            segmentVo.setSegmentEndTime(segment.getEndTime());
            segmentVos.add(segmentVo);
        });
        return new ResponseEntity<>(segmentVos, HttpStatus.OK);
    }

    public BuildingSegmentResource getBuildingSegmentResource(String roomId) {
        BuildingSegmentResource buildingSegmentResource = new BuildingSegmentResource();
        List<SegmentCourseSqlVo> segmentCourseSqlVoList = getSegmentCourseSqlVoList(roomId);
        Map<Integer, List<SegmentCourseSqlVo>> segmentCourseSqlVoListMap = segmentCourseSqlVoList.stream()
                .collect(Collectors.groupingBy(SegmentCourseSqlVo::getSegment));
        List<ClassroomAndFloorResource> classroomAndFloorResources =
                classRoomService.getClassroomFloorInfo(Collections.singletonList(roomId));
        if (CollectionUtils.isNotEmpty(classroomAndFloorResources)) {
            ClassroomAndFloorResource classroomAndFloorResource = classroomAndFloorResources.get(0);
            buildingSegmentResource.setBuildingId(classroomAndFloorResource.getBuildId());
            buildingSegmentResource.setBuildingName(classroomAndFloorResource.getBuildName());
            List<Segment> segmentList = segmentRepository.findByBuildId(classroomAndFloorResource.getBuildId());
            List<SegmentMode> segmentModeList = new ArrayList<>();
            for (Segment segment : segmentList) {
                SegmentMode segmentMode = new SegmentMode();
                segmentMode.setSegmentName(segment.getSegment());
                segmentMode.setSegmentStartTime(segment.getStartTime());
                segmentMode.setSegmentEndTime(segment.getEndTime());
                List<CourseBasicVo> courseBasicVoList = new ArrayList<>();
                List<SegmentCourseSqlVo> groupSegmentCourseSqlVoList = segmentCourseSqlVoListMap.get(segment.getSegment());
                if (CollectionUtils.isNotEmpty(groupSegmentCourseSqlVoList)) {
                    for (SegmentCourseSqlVo segmentCourseSqlVo : groupSegmentCourseSqlVoList) {
                        CourseBasicVo courseBasicVo = new CourseBasicVo();
                        courseBasicVo.setCourseId(segmentCourseSqlVo.getCourseId());
                        courseBasicVo.setCourseCode(segmentCourseSqlVo.getCourseCode());
                        courseBasicVo.setCourseName(segmentCourseSqlVo.getCourseName());
                        courseBasicVo.setCourseCategoryId(segmentCourseSqlVo.getCourseCategoryId());
                        courseBasicVo.setCourseCategoryName(segmentCourseSqlVo.getCourseCategoryName());
                        courseBasicVo.setTeacherNames(segmentCourseSqlVo.getTeacherNames());
                        courseBasicVo.setGroupId(segmentCourseSqlVo.getGroupId());
                        courseBasicVo.setGroupNo(segmentCourseSqlVo.getGroupNo());
                        courseBasicVo.setGroupName(segmentCourseSqlVo.getGroupName());
                        courseBasicVo.setCollegeId(segmentCourseSqlVo.getCollegeId());
                        courseBasicVo.setCollegeName(segmentCourseSqlVo.getCollegeName());
                        courseBasicVo.setGroupMemberCounts(segmentCourseSqlVo.getGroupMemberCounts().intValue());
                        courseBasicVoList.add(courseBasicVo);
                    }
                }
                segmentMode.setCourseList(courseBasicVoList);
                segmentModeList.add(segmentMode);
            }
            buildingSegmentResource.setSegmentList(segmentModeList);
        }
        return buildingSegmentResource;
    }

    private List<SegmentCourseSqlVo> getSegmentCourseSqlVoList(String roomId) {
        List<SegmentCourseSqlVo> segmentCourseSqlVoList = new ArrayList<>();
        String nowDate = TimeUtils.getNowDate();
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = "SELECT DISTINCT  " +
                " c.segment,  " +
                " e.id AS courseId,  " +
                " e.course_code AS courseCode,  " +
                " e.course_name AS courseName,  " +
                " d.course_category_id as courseCategoryId,  " +
                " f.course_category_name as courseCategoryName,  " +
                " a.teacher_names as teacherNames,  " +
                " g.id as groupId,  " +
                " g.group_no as groupNo,  " +
                " g.group_name as groupName,  " +
                " d.college_id as collegeId,  " +
                " d.college_name as collegeName,  " +
                " count(h.id) as groupMemberCounts  " +
                "FROM  tb_course_table_detail a  " +
                " INNER JOIN tb_course_table_detail_room_user b ON a.id = b.course_table_detail_id  " +
                " INNER JOIN tb_course_segment c ON a.id = c.course_table_detail_id  " +
                " INNER JOIN tb_course_table d ON a.course_table_id = d.id  " +
                " INNER JOIN tb_course_category f on d.course_category_id = f.id  " +
                " INNER JOIN tb_course e ON e.id = d.course_id   " +
                " INNER JOIN tb_group g on g.id = d.group_id  " +
                " INNER JOIN tb_group_member h on h.group_id = g.id  " +
                "WHERE  b.room_id =:roomId  AND a.course_date =:nowDate  " +
                " and h.group_member_status = 0  " +
                " group by   " +
                " c.segment,e.id ,e.course_code ,e.course_name ,d.course_category_id ,  " +
                " f.course_category_name ,a.teacher_names,g.id ,g.group_no ,g.group_name, d.college_id,d.college_name";
        paramMap.put("roomId", roomId);
        paramMap.put("nowDate", nowDate);
        Query queryData = entityManager.createNativeQuery(querySql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(SegmentCourseSqlVo.class));
        segmentCourseSqlVoList = queryData.getResultList();
        if (CollectionUtils.isNotEmpty(segmentCourseSqlVoList)) {
            return segmentCourseSqlVoList;
        } else {
            return new ArrayList<>();
        }
    }

    public Term getNowTerm() {
        Date nowStartTime = DateUtils.getEndOfDay(new Date());
        Date nowEndTime = DateUtils.getStartOfDay(new Date());
        return termRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(nowStartTime, nowEndTime);
    }

    public ResponseEntity<List<CourseTableDetailSegment>> getSegmentTasks(String date, Integer studentType) {
        List<CourseTableDetailSegment> courseTableDetailSegments = new ArrayList<>();

        List<CourseTableDetailSegmentVo> courseTableDetailSegmentVos = getCourseTableDetailSegmentVoListByDate(date, studentType);
        buildCourseTableDetailSegment(courseTableDetailSegments, courseTableDetailSegmentVos);

        return new ResponseEntity<>(courseTableDetailSegments, HttpStatus.OK);
    }

    private List<CourseTableDetailSegmentVo> getCourseTableDetailSegmentVoListByDate(String date, Integer defaultStudentType) {
        StringBuilder querySql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<>();
        querySql.append("SELECT ct.student_type as studentType, ctdru.room_id AS roomId," +
                " ctdru.room_name AS roomName,ct.school_year AS schoolYear,ct.term AS term," +
                "ct.group_id AS groupId, g.group_name AS groupName,ctd.week_num as weekNum," +
                " (SELECT count(1) FROM tb_group_member gm WHERE gm.group_id = ct.group_id and gm.group_member_status=0) AS studentCount," +
                "ctd.week AS week, ct.course_id AS courseId,ct.course_name AS courseName,ct.id AS courseTableId," +
                "ctd.id AS courseTableDetailId,cs.segment AS segment," +
                "(SELECT GROUP_CONCAT(CONCAT(ctdt.teacher_id,',',ctdt.teacher_name,',',ctdt.teacher_no))" +
                " FROM tb_course_table_detail_teacher ctdt" +
                " WHERE ctdt.course_table_detail_id = ctd.id group by course_table_detail_id) as teachers,ct.college_id as collegeId," +
                " ct.college_name as collegeName,c.course_code as courseCode" +
                " FROM tb_course_table ct" +
                " INNER JOIN tb_course c ON c.id = ct.course_id" +
                " INNER JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id" +
                " INNER JOIN tb_course_segment cs ON cs.course_table_detail_id = ctd.id" +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = ctd.id" +
                " INNER JOIN tb_group g on g.id = ct.group_id");
        querySql.append(" WHERE  ctd.course_date = STR_TO_DATE(:date,'%Y-%m-%d') and ctdru.room_id is not null ");
        paramMap.put("date", date);
        if (Objects.nonNull(defaultStudentType) && !defaultStudentType.equals(-1)) {
            querySql.append(" and ct.student_type = :studentType ");
            paramMap.put("studentType", defaultStudentType);
        }
        Query query = entityManager.createNativeQuery(querySql.toString());
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailSegmentVo.class));
        return query.getResultList();
    }

    public ResponseEntity<List<SegmentResourceVo>> getBuildAndSegmentList(String buildId) {
        List<Segment> segmentList;
        if (StringUtils.isEmpty(buildId)) {
            segmentList = segmentRepository.findAllByOrderByBuildName();
        } else {
            segmentList = segmentRepository.findByBuildIdOrderByBuildName(buildId);
        }
        List<SegmentResourceVo> segmentResourceList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(segmentList)) {
            for (Segment segment : segmentList) {
                SegmentResourceVo segmentResource = new SegmentResourceVo();
                segmentResource.setBuildId(segment.getSegmentPK().getBuildID());
                segmentResource.setBuildName(segment.getBuildName());
                segmentResource.setDescription(segment.getDescription());
                segmentResource.setEndTime(segment.getEndTime());
                segmentResource.setStartTime(segment.getStartTime());
                segmentResource.setSegment(segment.getSegmentPK().getSegment().toString());
                segmentResource.setRecordStartTime(segment.getRecordStartTime());
                segmentResource.setRecordEndTime(segment.getRecordEndTime());
                segmentResourceList.add(segmentResource);
            }
        }
        return new ResponseEntity<>(segmentResourceList, HttpStatus.OK);
    }

    public List<BuildingVo> getSegmentListByBuildIdList(List<BuildIdVo> buildIdList) {
        List<BuildingVo> buildingVoList = new ArrayList<>();
        List<String> buildIds = buildIdList.stream().map(BuildIdVo::getBuildId).collect(Collectors.toList());

        List<Segment> segmentList = segmentRepository.findAllByBuildIDInOrderByBuildName(buildIds);
        List<Segment> defaultSegments = segmentRepository.findByBuildId("0");

        for (BuildIdVo buildId : buildIdList) {
            List<Segment> segments = segmentList
                    .stream()
                    .filter(segment ->
                            segment.getSegmentPK().getBuildID()
                                    .equals(buildId)).collect(Collectors.toList());
            BuildingVo buildingVo = new BuildingVo();
            buildingVo.setBuildId(buildId.getBuildId());
            if (segments.size() > 0) {
                buildingVo.setSegmentList(segments.stream()
                        .map(segment -> new SegmentVo() {{
                            this.setSegmentEndTime(segment.getEndTime());
                            this.setSegmentStartTime(segment.getStartTime());
                            this.setSegmentName(segment.getSegmentPK().getSegment() + "");
                        }}).collect(Collectors.toList()));
                buildingVoList.add(buildingVo);
            } else {
                buildingVo.setSegmentList(defaultSegments.stream()
                        .map(segment -> new SegmentVo() {{
                            this.setSegmentEndTime(segment.getEndTime());
                            this.setSegmentStartTime(segment.getStartTime());
                            this.setSegmentName(segment.getSegmentPK().getSegment() + "");
                        }}).collect(Collectors.toList()));
                buildingVoList.add(buildingVo);
            }
        }

        return buildingVoList;
    }
}

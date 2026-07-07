package com.lztech.site.service.experimentschedulecoursetabledetail;
import com.lztech.domain.model.coursesegment.CourseSegment;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetail.CourseTableDetailProject;
import com.lztech.domain.model.coursetabledetail.enums.CourseKind;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.coursetype.CourseType;
import com.lztech.domain.model.experimentoriginalcoursetabledetail.*;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.*;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.EntranceGuardGeneratedStatus;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.ScheduleSource;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.ScheduleStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.segment.Segment;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.coursesegment.CourseSegmentRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailProjectRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetailroomuser.CourseTableDetailRoomUserRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.coursetype.CourseTypeRepository;
import com.lztech.persistence.repositories.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetailRepository;
import com.lztech.persistence.repositories.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetailRoomUserRepository;
import com.lztech.persistence.repositories.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetailSegmentRepository;
import com.lztech.persistence.repositories.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.experimentschedulelcoursetabledetail.*;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.persistence.repositories.segment.SegmentRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.classroom.ClassRoomService;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.classroom.ClassRoomResource;
import com.lztech.site.viewmodel.classroom.ClassroomAndFloorResource;
import com.lztech.site.viewmodel.experimentschedulecoursetabledetail.*;
import com.lztech.site.viewmodel.group.StudentVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static com.lztech.site.until.TimeUtils.dateToLocalDate;
import static java.util.stream.Collectors.joining;

@Service
public class ExperimentScheduleCourseTableDetailService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private CourseTableDetailRoomUserRepository courseTableDetailRoomUserRepository;
    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private CourseSegmentRepository courseSegmentRepository;
    @Autowired
    private CourseTableDetailProjectRepository courseTableDetailProjectRepository;
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private ExperimentOriginalCourseTableDetailRepository experimentOriginalCourseTableDetailRepository;
    @Autowired
    private ExperimentOriginalCourseTableDetailTeacherRepository experimentOriginalCourseTableDetailTeacherRepository;
    @Autowired
    private ExperimentOriginalCourseTableDetailRoomUserRepository experimentOriginalCourseTableDetailRoomUserRepository;
    @Autowired
    private ExperimentOriginalCourseTableDetailSegmentRepository experimentOriginalCourseTableDetailSegmentRepository;
    @Autowired
    private ExperimentScheduleCourseTableDetailRepository experimentScheduleCourseTableDetailRepository;
    @Autowired
    private ExperimentScheduleCourseTableDetailTeacherRepository experimentScheduleCourseTableDetailTeacherRepository;
    @Autowired
    private ExperimentScheduleCourseTableDetailRoomUserRepository experimentScheduleCourseTableDetailRoomUserRepository;
    @Autowired
    private ExperimentScheduleCourseTableDetailSegmentRepository experimentScheduleCourseTableDetailSegmentRepository;
    @Autowired
    private ExperimentScheduleCourseTableDetailProjectRepository experimentScheduleCourseTableDetailProjectRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    @Autowired
    private TermService termService;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private SegmentRepository segmentRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private ExperimentScheduleCourseLogService experimentScheduleCourseLogService;

    public DataDockingCourseTableDetailPageResource getDataDockingCourseTableDetailPageResource(
            ExperimentScheduleObtainDataParam experimentScheduleObtainDataParam) {
        DataDockingCourseTableDetailPageResource dataDockingCourseTableDetailPageResource =
                new DataDockingCourseTableDetailPageResource();
        List<ExperimentScheduleUseCourseTableDetailVo> experimentScheduleUseCourseTableDetailVoList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startDate", experimentScheduleObtainDataParam.getStartDate());
        paramMap.put("endDate", experimentScheduleObtainDataParam.getEndDate());
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getRoomNameSearchParams())) {
            paramMap.put("roomNameSearchParams", "%" + experimentScheduleObtainDataParam.getRoomNameSearchParams() + "%");
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getCourseName())) {
            paramMap.put("courseName", "%" + experimentScheduleObtainDataParam.getCourseName() + "%");
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getTeacherName())) {
            paramMap.put("teacherName", "%" + experimentScheduleObtainDataParam.getTeacherName() + "%");
        }
        String countSql = createQueryCountSql(experimentScheduleObtainDataParam);
        Query queryCount = entityManager.createNativeQuery(countSql);
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        int count = ((BigInteger) resMap.get("allNumber")).intValue();
        dataDockingCourseTableDetailPageResource.setPageNum(experimentScheduleObtainDataParam.getPageNum());
        dataDockingCourseTableDetailPageResource.setPageSize(experimentScheduleObtainDataParam.getPageSize());
        dataDockingCourseTableDetailPageResource.setTotal(count);
        if (count == 0) {
            dataDockingCourseTableDetailPageResource.setDataDockingCourseTableDetailList(experimentScheduleUseCourseTableDetailVoList);
            return dataDockingCourseTableDetailPageResource;
        }
        String querySql = createQuerySql(experimentScheduleObtainDataParam.getPageNum()
                , experimentScheduleObtainDataParam.getPageSize(), experimentScheduleObtainDataParam);
        Query queryData = entityManager.createNativeQuery(querySql);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(DataDockingCourseTableDetailSqlVo.class));
        paramMap.forEach(queryData::setParameter);
        List<DataDockingCourseTableDetailSqlVo> dataDockingCourseTableDetailSqlVoList = queryData.getResultList();
        for (DataDockingCourseTableDetailSqlVo dataDockingCourseTableDetailSqlVo : dataDockingCourseTableDetailSqlVoList) {
            ExperimentScheduleUseCourseTableDetailVo experimentScheduleUseCourseTableDetailVo =
                    getExperimentScheduleUseCourseTableDetailVo(dataDockingCourseTableDetailSqlVo);
            experimentScheduleUseCourseTableDetailVoList.add(experimentScheduleUseCourseTableDetailVo);
        }
        dataDockingCourseTableDetailPageResource.setDataDockingCourseTableDetailList(experimentScheduleUseCourseTableDetailVoList.stream()
                .sorted(Comparator.comparing(ExperimentScheduleUseCourseTableDetailVo::getCourseDate)
                        .thenComparing(ExperimentScheduleUseCourseTableDetailVo::getSegment)
                        .thenComparing(ExperimentScheduleUseCourseTableDetailVo::getGroupName)
                        .thenComparing(ExperimentScheduleUseCourseTableDetailVo::getTeacherNames)
                        .thenComparing(ExperimentScheduleUseCourseTableDetailVo::getRoomName))
                .collect(Collectors.toList()));
        return dataDockingCourseTableDetailPageResource;
    }

    private String createQueryCountSql(ExperimentScheduleObtainDataParam experimentScheduleObtainDataParam) {
        String sql = " SELECT COUNT(1) AS allNumber FROM tb_course_table_detail ctd " +
                " INNER JOIN tb_course_table_detail_room_user ctdru ON ctdru.course_table_detail_id = ctd.id" +
                " INNER JOIN tb_course_table ct ON ctd.course_table_id = ct.id " +
                " INNER JOIN tb_group g ON ct.group_id = g.id " +
                " LEFT JOIN (  SELECT DISTINCT eoctd.course_table_id, eoctd.course_date, eoctdru.room_name " +
                " FROM tb_experiment_original_course_table_detail eoctd" +
                " INNER JOIN tb_experiment_original_course_table_detail_room_user eoctdru" +
                " ON eoctd.id = eoctdru.experiment_original_course_table_detail_id " +
                " WHERE eoctd.course_date BETWEEN :startDate AND :endDate ";

        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getCourseName())) {
            sql += " AND eoctd.course_name LIKE :courseName  ";
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getTeacherName())) {
            sql += " AND eoctd.teacher_names LIKE :teacherName ";
        }
        sql += " ) eoctd_filtered ON eoctd_filtered.course_table_id = ctd.course_table_id " +
                "   AND eoctd_filtered.course_date = ctd.course_date " +
                "   AND eoctd_filtered.room_name = ctdru.room_name " +
                "WHERE ctd.course_date BETWEEN :startDate AND :endDate AND ctd.source = 0";
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getRoomNameSearchParams())) {
            sql += " and ctdru.room_name like :roomNameSearchParams ";
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getCourseName())) {
            sql += " AND ct.course_name LIKE :courseName";
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getTeacherName())) {
            sql += " AND ctd.teacher_names LIKE :teacherName";
        }
        sql += " AND eoctd_filtered.course_table_id IS NULL ";
        return sql;
    }

    private String createQuerySql(Integer pageNum, Integer pageSize, ExperimentScheduleObtainDataParam experimentScheduleObtainDataParam) {
        String querySql = " SELECT ctd.id AS courseTableDetailId, ctd.course_table_id AS courseTableId, ctd.`week` AS `week`, " +
                " ct.course_name AS courseName,  g.group_name AS groupName, ctd.teacher_names AS teacherNames, " +
                " DATE_FORMAT(ctd.course_date, '%Y-%m-%d') AS courseDate, DATE_FORMAT(ctd.segment_start_time, '%H:%i:%s') AS segmentStartTime, " +
                " DATE_FORMAT(ctd.segment_end_time, '%H:%i:%s') AS segmentEndTime, ctd.week_num AS weekNum, ctd.segment AS segment, " +
                " ctdru.room_id AS roomId, ctdru.room_name AS roomName FROM tb_course_table_detail ctd " +
                " INNER JOIN tb_course_table_detail_room_user ctdru ON ctdru.course_table_detail_id = ctd.id " +
                " INNER JOIN tb_course_table ct ON ctd.course_table_id = ct.id " +
                " INNER JOIN tb_group g ON ct.group_id = g.id " +
                " LEFT JOIN (SELECT DISTINCT  eoctd.course_table_id, eoctd.course_date, eoctdru.room_name, eoctd.segment " +
                " FROM tb_experiment_original_course_table_detail eoctd " +
                " INNER JOIN tb_experiment_original_course_table_detail_room_user eoctdru " +
                " ON eoctd.id = eoctdru.experiment_original_course_table_detail_id " +
                " WHERE  eoctd.course_date BETWEEN :startDate AND :endDate ";
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getCourseName())) {
            querySql += " AND eoctd.course_name LIKE :courseName ";
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getTeacherName())) {
            querySql += " AND eoctd.teacher_names LIKE :teacherName ";
        }
        querySql += " ) eoctd_filtered ON ( eoctd_filtered.course_table_id = ctd.course_table_id" +
                " AND eoctd_filtered.course_date = ctd.course_date AND eoctd_filtered.room_name = ctdru.room_name" +
                " AND eoctd_filtered.segment = ctd.segment ) WHERE " +
                " ctd.course_date BETWEEN :startDate AND :endDate AND ctd.source = 0";
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getRoomNameSearchParams())) {
            querySql += " and ctdru.room_name like :roomNameSearchParams ";
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getCourseName())) {
            querySql += " AND ct.course_name LIKE :courseName ";
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getTeacherName())) {
            querySql += " AND ctd.teacher_names LIKE :teacherName ";
        }
        querySql += " AND eoctd_filtered.course_table_id IS NULL ORDER BY" +
                " ctd.course_date ASC,  ctd.segment_start_time ASC, ctd.segment ASC, g.group_name ASC ";
        if (pageNum != null && pageSize != null) {
            int start = (pageNum - 1) * pageSize;
            querySql += " limit " + start + "," + pageSize;
        }
        return querySql;
    }

    private static ExperimentScheduleUseCourseTableDetailVo getExperimentScheduleUseCourseTableDetailVo(
            DataDockingCourseTableDetailSqlVo dataDockingCourseTableDetailSqlVo) {
        ExperimentScheduleUseCourseTableDetailVo experimentScheduleUseCourseTableDetailVo =
                new ExperimentScheduleUseCourseTableDetailVo();
        experimentScheduleUseCourseTableDetailVo.setWeek(dataDockingCourseTableDetailSqlVo.getWeek());
        experimentScheduleUseCourseTableDetailVo.setCourseName(dataDockingCourseTableDetailSqlVo.getCourseName());
        experimentScheduleUseCourseTableDetailVo.setGroupName(dataDockingCourseTableDetailSqlVo.getGroupName());
        experimentScheduleUseCourseTableDetailVo.setTeacherNames(dataDockingCourseTableDetailSqlVo.getTeacherNames());
        experimentScheduleUseCourseTableDetailVo.setCourseDate(dataDockingCourseTableDetailSqlVo.getCourseDate());
        experimentScheduleUseCourseTableDetailVo.setWeekNum(String.valueOf(dataDockingCourseTableDetailSqlVo.getWeekNum()));
        experimentScheduleUseCourseTableDetailVo.setSegment(dataDockingCourseTableDetailSqlVo.getSegment());
        experimentScheduleUseCourseTableDetailVo.setRoomName(dataDockingCourseTableDetailSqlVo.getRoomName());
        return experimentScheduleUseCourseTableDetailVo;
    }


    @Transactional
    public void importExperimentScheduleCourseTableDetails(ExperimentScheduleObtainDataParam experimentScheduleObtainDataParam) {
        String creatorId = experimentScheduleObtainDataParam.getCreatorId();
        String creatorName = experimentScheduleObtainDataParam.getCreatorName();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startDate", experimentScheduleObtainDataParam.getStartDate());
        paramMap.put("endDate", experimentScheduleObtainDataParam.getEndDate());
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getRoomNameSearchParams())) {
            paramMap.put("roomNameSearchParams", "%" + experimentScheduleObtainDataParam.getRoomNameSearchParams() + "%");
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getCourseName())) {
            paramMap.put("courseName", "%" + experimentScheduleObtainDataParam.getCourseName() + "%");
        }
        if (StringUtils.isNotBlank(experimentScheduleObtainDataParam.getTeacherName())) {
            paramMap.put("teacherName", "%" + experimentScheduleObtainDataParam.getTeacherName() + "%");
        }
        String querySql = createQuerySql(null, null, experimentScheduleObtainDataParam);
        Query queryData = entityManager.createNativeQuery(querySql);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(DataDockingCourseTableDetailSqlVo.class));
        paramMap.forEach(queryData::setParameter);
        List<DataDockingCourseTableDetailSqlVo> dataDockingCourseTableDetailSqlVoList = queryData.getResultList();
        if (CollectionUtils.isEmpty(dataDockingCourseTableDetailSqlVoList)) {
            return;
        }
        List<CourseTable> courseTableList = new ArrayList<>();
        List<String> courseTableDetailIds = dataDockingCourseTableDetailSqlVoList.stream()
                .map(DataDockingCourseTableDetailSqlVo::getCourseTableDetailId).distinct().collect(Collectors.toList());
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository.findByIdIn(courseTableDetailIds);
        List<ExperimentOriginalCourseTableDetail> experimentOriginalCourseTableDetailList = new ArrayList<>();
        for (CourseTableDetail courseTableDetail : courseTableDetailList) {
            ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail =
                    composeExperimentOriginalCourseTableDetail(courseTableDetail, creatorId, creatorName, courseTableList);
            experimentOriginalCourseTableDetailList.add(experimentOriginalCourseTableDetail);
        }
        List<ExperimentOriginalCourseTableDetailTeacher> combinedExperimentOriginalCourseTableDetailTeacherList =
                experimentOriginalCourseTableDetailList.stream()
                        .flatMap(detail -> detail.getCourseTableDetailTeacherList().stream())
                        .collect(Collectors.toList());
        List<ExperimentOriginalCourseTableDetailRoomUser> combinedExperimentOriginalCourseTableDetailRoomUserList =
                experimentOriginalCourseTableDetailList.stream()
                        .flatMap(detail -> detail.getCourseTableDetailRoomUserList().stream())
                        .collect(Collectors.toList());
        List<ExperimentOriginalCourseTableDetailSegment> combinedExperimentOriginalCourseTableDetailSegmentList =
                experimentOriginalCourseTableDetailList.stream()
                        .flatMap(detail -> detail.getCourseSegmentList().stream())
                        .collect(Collectors.toList());
        experimentOriginalCourseTableDetailList = experimentOriginalCourseTableDetailRepository.saveAll(experimentOriginalCourseTableDetailList);
        experimentOriginalCourseTableDetailTeacherRepository.saveAll(combinedExperimentOriginalCourseTableDetailTeacherList);
        experimentOriginalCourseTableDetailRoomUserRepository.saveAll(combinedExperimentOriginalCourseTableDetailRoomUserList);
        experimentOriginalCourseTableDetailSegmentRepository.saveAll(combinedExperimentOriginalCourseTableDetailSegmentList);
        List<ExperimentScheduleCourseTableDetail> experimentScheduleCourseTableDetailList = new ArrayList<>();
        for (ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail : experimentOriginalCourseTableDetailList) {
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail =
                    composeExperimentScheduleCourseTableDetail(experimentOriginalCourseTableDetail, courseTableList, creatorId, creatorName);
            experimentScheduleCourseTableDetailList.add(experimentScheduleCourseTableDetail);
        }
        List<ExperimentScheduleCourseTableDetailTeacher> combinedExperimentScheduleCourseTableDetailTeacherList =
                experimentScheduleCourseTableDetailList.stream()
                        .flatMap(detail -> detail.getCourseTableDetailTeacherList().stream())
                        .collect(Collectors.toList());
        List<ExperimentScheduleCourseTableDetailRoomUser> combinedExperimentScheduleCourseTableDetailRoomUserList =
                experimentScheduleCourseTableDetailList.stream()
                        .flatMap(detail -> detail.getCourseTableDetailRoomUserList().stream())
                        .collect(Collectors.toList());
        List<ExperimentScheduleCourseTableDetailSegment> combinedExperimentScheduleCourseTableDetailSegmentList =
                experimentScheduleCourseTableDetailList.stream()
                        .flatMap(detail -> detail.getCourseSegmentList().stream())
                        .collect(Collectors.toList());
        List<ExperimentScheduleCourseTableDetail> savedExperimentScheduleCourseTableDetailList =
                experimentScheduleCourseTableDetailRepository.saveAll(experimentScheduleCourseTableDetailList);
        experimentScheduleCourseTableDetailTeacherRepository.saveAll(combinedExperimentScheduleCourseTableDetailTeacherList);
        experimentScheduleCourseTableDetailRoomUserRepository.saveAll(combinedExperimentScheduleCourseTableDetailRoomUserList);
        experimentScheduleCourseTableDetailSegmentRepository.saveAll(combinedExperimentScheduleCourseTableDetailSegmentList);
        //记录导入操作日志
        savedExperimentScheduleCourseTableDetailList
                .forEach(experimentSchedule
                        -> experimentScheduleCourseLogService
                        .createLog(experimentSchedule.getId(), experimentSchedule.getModifierId(),
                                experimentSchedule.getModifierName(), "导入待排课表"));
    }

    private static ExperimentScheduleCourseTableDetail composeExperimentScheduleCourseTableDetail(
            ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail, List<CourseTable> courseTableList,
            String creatorId, String creatorName) {
        ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail = new ExperimentScheduleCourseTableDetail();
        experimentScheduleCourseTableDetail.setWeek(experimentOriginalCourseTableDetail.getWeek());
        experimentScheduleCourseTableDetail.setWeekNum(experimentOriginalCourseTableDetail.getWeekNum());
        experimentScheduleCourseTableDetail.setCourseDate(experimentOriginalCourseTableDetail.getCourseDate());
        experimentScheduleCourseTableDetail.setCourseType(experimentOriginalCourseTableDetail.getCourseType());
        experimentScheduleCourseTableDetail.setCourseTypeName(experimentOriginalCourseTableDetail.getCourseTypeName());
        experimentScheduleCourseTableDetail.setCourseTableId(experimentOriginalCourseTableDetail.getCourseTableId());
        experimentScheduleCourseTableDetail.setSegment(experimentOriginalCourseTableDetail.getSegment());
        experimentScheduleCourseTableDetail.setScheduleSource(ScheduleSource.DATA_DOCKING);
        experimentScheduleCourseTableDetail.setSegmentStartTime(experimentOriginalCourseTableDetail.getSegmentStartTime());
        experimentScheduleCourseTableDetail.setSegmentEndTime(experimentOriginalCourseTableDetail.getSegmentEndTime());
        experimentScheduleCourseTableDetail.setCourseKind(experimentOriginalCourseTableDetail.getCourseKind());
        experimentScheduleCourseTableDetail.setCourseName(experimentOriginalCourseTableDetail.getCourseName());
        experimentScheduleCourseTableDetail.setTeacherNames(experimentOriginalCourseTableDetail.getTeacherNames());
        experimentScheduleCourseTableDetail.setTeacherCollegeIds(experimentOriginalCourseTableDetail.getTeacherCollegeIds());
        experimentScheduleCourseTableDetail.setTeacherCollegeNames(experimentOriginalCourseTableDetail.getTeacherCollegeNames());
        experimentScheduleCourseTableDetail.setExperimentOriginalCourseTableDetailId(experimentOriginalCourseTableDetail.getId());
        experimentScheduleCourseTableDetail.setScheduleStatus(ScheduleStatus.WAIT_SCHEDULE);
        CourseTable courseTable = courseTableList.stream().filter(filterCourseTable -> filterCourseTable.getId().equals(
                experimentOriginalCourseTableDetail.getCourseTableId())).findFirst().orElse(null);
        if (ObjectUtils.isNotEmpty(courseTable)) {
            experimentScheduleCourseTableDetail.setCourseId(courseTable.getCourse().getId());
            experimentScheduleCourseTableDetail.setGroupId(
                    ObjectUtils.isNotEmpty(courseTable.getGroup()) ? courseTable.getGroup().getId() : null);
            experimentScheduleCourseTableDetail.setGroupNo(
                    ObjectUtils.isNotEmpty(courseTable.getGroup()) ? courseTable.getGroup().getGroupNo() : null);
            experimentScheduleCourseTableDetail.setGroupName(
                    ObjectUtils.isNotEmpty(courseTable.getGroup()) ? courseTable.getGroup().getGroupName() : null);
            experimentScheduleCourseTableDetail.setCourseCode(
                    ObjectUtils.isNotEmpty(courseTable.getCourse()) ? courseTable.getCourse().getCourseCode() : null);
        }
        experimentScheduleCourseTableDetail.setCreatorId(creatorId);
        experimentScheduleCourseTableDetail.setCreatorName(creatorName);
        experimentScheduleCourseTableDetail.setCreateTime(new Date());
        experimentScheduleCourseTableDetail.setModifierId(creatorId);
        experimentScheduleCourseTableDetail.setModifierName(creatorName);
        experimentScheduleCourseTableDetail.setModifyTime(new Date());
        List<ExperimentScheduleCourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();
        List<ExperimentOriginalCourseTableDetailTeacher> experimentOriginalCourseTableDetailTeacherList =
                experimentOriginalCourseTableDetail.getCourseTableDetailTeacherList();
        for (ExperimentOriginalCourseTableDetailTeacher experimentOriginalCourseTableDetailTeacher :
                experimentOriginalCourseTableDetailTeacherList) {
            ExperimentScheduleCourseTableDetailTeacher experimentScheduleCourseTableDetailTeacher =
                    composeExperimentScheduleCourseTableDetailTeacher(experimentOriginalCourseTableDetailTeacher,
                            experimentScheduleCourseTableDetail, creatorId, creatorName);
            courseTableDetailTeacherList.add(experimentScheduleCourseTableDetailTeacher);
        }
        experimentScheduleCourseTableDetail.setCourseTableDetailTeacherList(courseTableDetailTeacherList);
        List<ExperimentScheduleCourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();
        List<ExperimentOriginalCourseTableDetailRoomUser> experimentOriginalCourseTableDetailRoomUserList =
                experimentOriginalCourseTableDetail.getCourseTableDetailRoomUserList();
        for (ExperimentOriginalCourseTableDetailRoomUser experimentOriginalCourseTableDetailRoomUser :
                experimentOriginalCourseTableDetailRoomUserList) {
            ExperimentScheduleCourseTableDetailRoomUser experimentScheduleCourseTableDetailRoomUser =
                    composeExperimentScheduleCourseTableDetailRoomUser(experimentOriginalCourseTableDetailRoomUser,
                            experimentScheduleCourseTableDetail, creatorId, creatorName);
            courseTableDetailRoomUserList.add(experimentScheduleCourseTableDetailRoomUser);
        }
        experimentScheduleCourseTableDetail.setCourseTableDetailRoomUserList(courseTableDetailRoomUserList);
        List<ExperimentScheduleCourseTableDetailSegment> courseSegmentList = new ArrayList<>();
        List<ExperimentOriginalCourseTableDetailSegment> experimentOriginalCourseTableDetailSegmentList =
                experimentOriginalCourseTableDetail.getCourseSegmentList();
        for (ExperimentOriginalCourseTableDetailSegment experimentOriginalCourseTableDetailSegment :
                experimentOriginalCourseTableDetailSegmentList) {
            ExperimentScheduleCourseTableDetailSegment experimentScheduleCourseTableDetailSegment =
                    new ExperimentScheduleCourseTableDetailSegment();
            experimentScheduleCourseTableDetailSegment.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
            experimentScheduleCourseTableDetailSegment.setSegment(experimentOriginalCourseTableDetailSegment.getSegment());
            courseSegmentList.add(experimentScheduleCourseTableDetailSegment);
        }
        experimentScheduleCourseTableDetail.setCourseSegmentList(courseSegmentList);
        return experimentScheduleCourseTableDetail;
    }

    private static ExperimentScheduleCourseTableDetailRoomUser composeExperimentScheduleCourseTableDetailRoomUser(
            ExperimentOriginalCourseTableDetailRoomUser experimentOriginalCourseTableDetailRoomUser,
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail, String creatorId, String creatorName) {
        ExperimentScheduleCourseTableDetailRoomUser experimentScheduleCourseTableDetailRoomUser =
                new ExperimentScheduleCourseTableDetailRoomUser();
        experimentScheduleCourseTableDetailRoomUser.setRoomId(experimentOriginalCourseTableDetailRoomUser.getRoomId());
        experimentScheduleCourseTableDetailRoomUser.setRoomName(experimentOriginalCourseTableDetailRoomUser.getRoomName());
        experimentScheduleCourseTableDetailRoomUser.setRoomCode(experimentOriginalCourseTableDetailRoomUser.getRoomCode());
        experimentScheduleCourseTableDetailRoomUser.setPersonnelNumber(experimentOriginalCourseTableDetailRoomUser.getPersonnelNumber());
        experimentScheduleCourseTableDetailRoomUser.setRoomCategoryId(experimentOriginalCourseTableDetailRoomUser.getRoomCategoryId());
        experimentScheduleCourseTableDetailRoomUser.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
        experimentScheduleCourseTableDetailRoomUser.setItemTeacherId(experimentOriginalCourseTableDetailRoomUser.getItemTeacherId());
        experimentScheduleCourseTableDetailRoomUser.setItemTeacherNo(experimentOriginalCourseTableDetailRoomUser.getItemTeacherNo());
        experimentScheduleCourseTableDetailRoomUser.setItemTeacherName(experimentOriginalCourseTableDetailRoomUser.getItemTeacherName());
        experimentScheduleCourseTableDetailRoomUser.setHasLive(experimentOriginalCourseTableDetailRoomUser.isHasLive());
        experimentScheduleCourseTableDetailRoomUser.setGroupId(experimentOriginalCourseTableDetailRoomUser.getGroupId());
        experimentScheduleCourseTableDetailRoomUser.setCreatorId(creatorId);
        experimentScheduleCourseTableDetailRoomUser.setCreatorName(creatorName);
        experimentScheduleCourseTableDetailRoomUser.setCreateTime(new Date());
        return experimentScheduleCourseTableDetailRoomUser;
    }

    private static ExperimentScheduleCourseTableDetailTeacher composeExperimentScheduleCourseTableDetailTeacher(
            ExperimentOriginalCourseTableDetailTeacher experimentOriginalCourseTableDetailTeacher,
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail, String creatorId, String creatorName) {
        ExperimentScheduleCourseTableDetailTeacher experimentScheduleCourseTableDetailTeacher = new
                ExperimentScheduleCourseTableDetailTeacher();
        experimentScheduleCourseTableDetailTeacher.setTeacherId(experimentOriginalCourseTableDetailTeacher.getTeacherId());
        experimentScheduleCourseTableDetailTeacher.setTeacherNo(experimentOriginalCourseTableDetailTeacher.getTeacherNo());
        experimentScheduleCourseTableDetailTeacher.setTeacherName(experimentOriginalCourseTableDetailTeacher.getTeacherName());
        experimentScheduleCourseTableDetailTeacher.setTeacherCollegeId(experimentOriginalCourseTableDetailTeacher.getTeacherCollegeId());
        experimentScheduleCourseTableDetailTeacher.setTeacherCollegeName(experimentOriginalCourseTableDetailTeacher.getTeacherCollegeName());
        experimentScheduleCourseTableDetailTeacher.setTeacherTitle(experimentOriginalCourseTableDetailTeacher.getTeacherTitle());
        experimentScheduleCourseTableDetailTeacher.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
        experimentScheduleCourseTableDetailTeacher.setCreatorId(creatorId);
        experimentScheduleCourseTableDetailTeacher.setCreatorName(creatorName);
        experimentScheduleCourseTableDetailTeacher.setCreateTime(new Date());
        return experimentScheduleCourseTableDetailTeacher;
    }

    private ExperimentOriginalCourseTableDetail composeExperimentOriginalCourseTableDetail(
            CourseTableDetail courseTableDetail, String creatorId, String creatorName, List<CourseTable> courseTableList) {
        ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail = new ExperimentOriginalCourseTableDetail();
        experimentOriginalCourseTableDetail.setWeek(courseTableDetail.getWeek());
        experimentOriginalCourseTableDetail.setWeekNum(courseTableDetail.getWeekNum());
        experimentOriginalCourseTableDetail.setCourseDate(courseTableDetail.getCourseDate());
        experimentOriginalCourseTableDetail.setCourseType(courseTableDetail.getCourseType());
        experimentOriginalCourseTableDetail.setCourseTypeName(courseTableDetail.getCourseTypeName());
        CourseTable courseTable = courseTableDetail.getCourseTable();
        if (ObjectUtils.isNotEmpty(courseTable)) {
            courseTableList.add(courseTable);
            experimentOriginalCourseTableDetail.setCourseTableId(courseTable.getId());
        }
        experimentOriginalCourseTableDetail.setSegment(courseTableDetail.getSegment());
        experimentOriginalCourseTableDetail.setSource(courseTableDetail.getSource());
        experimentOriginalCourseTableDetail.setSegmentStartTime(courseTableDetail.getSegmentStartTime());
        experimentOriginalCourseTableDetail.setSegmentEndTime(courseTableDetail.getSegmentEndTime());
        experimentOriginalCourseTableDetail.setCourseKind(courseTableDetail.getCourseKind());
        experimentOriginalCourseTableDetail.setCourseName(courseTableDetail.getCourseName());
        experimentOriginalCourseTableDetail.setTeacherNames(courseTableDetail.getTeacherNames());
        experimentOriginalCourseTableDetail.setTeacherCollegeIds(courseTableDetail.getTeacherCollegeIds());
        experimentOriginalCourseTableDetail.setTeacherCollegeNames(courseTableDetail.getTeacherCollegeNames());
        experimentOriginalCourseTableDetail.setCreatorId(courseTableDetail.getCreatorId());
        experimentOriginalCourseTableDetail.setCreatorName(courseTableDetail.getCreatorName());
        experimentOriginalCourseTableDetail.setCreateTime(new Date());
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetail.getCourseTableDetailTeacherList();
        List<ExperimentOriginalCourseTableDetailTeacher> experimentOriginalCourseTableDetailTeacherList = new ArrayList<>();
        for (CourseTableDetailTeacher courseTableDetailTeacher : courseTableDetailTeacherList) {
            ExperimentOriginalCourseTableDetailTeacher experimentOriginalCourseTableDetailTeacher =
                    composeExperimentOriginalCourseTableDetailTeacher(
                            courseTableDetailTeacher, experimentOriginalCourseTableDetail, creatorId, creatorName);
            experimentOriginalCourseTableDetailTeacherList.add(experimentOriginalCourseTableDetailTeacher);
        }
        experimentOriginalCourseTableDetail.setCourseTableDetailTeacherList(experimentOriginalCourseTableDetailTeacherList);
        List<ExperimentOriginalCourseTableDetailRoomUser> experimentOriginalCourseTableDetailRoomUserList = new ArrayList<>();
        List<CourseTableDetailRoomUser> courseTableDetailRoomUserList = courseTableDetail.getCourseTableDetailRoomUserList();
        for (CourseTableDetailRoomUser courseTableDetailRoomUser : courseTableDetailRoomUserList) {
            ExperimentOriginalCourseTableDetailRoomUser experimentOriginalCourseTableDetailRoomUser =
                    composeExperimentOriginalCourseTableDetailRoomUser(courseTableDetailRoomUser, experimentOriginalCourseTableDetail,
                            creatorId, creatorName);
            experimentOriginalCourseTableDetailRoomUserList.add(experimentOriginalCourseTableDetailRoomUser);
        }
        experimentOriginalCourseTableDetail.setCourseTableDetailRoomUserList(experimentOriginalCourseTableDetailRoomUserList);
        List<CourseSegment> courseSegmentList = courseTableDetail.getCourseSegmentList();
        List<ExperimentOriginalCourseTableDetailSegment> experimentOriginalCourseTableDetailSegmentList = new ArrayList<>();
        for (CourseSegment courseSegment : courseSegmentList) {
            ExperimentOriginalCourseTableDetailSegment experimentOriginalCourseTableDetailSegment =
                    new ExperimentOriginalCourseTableDetailSegment();
            experimentOriginalCourseTableDetailSegment.setExperimentOriginalCourseTableDetail(experimentOriginalCourseTableDetail);
            experimentOriginalCourseTableDetailSegment.setSegment(courseSegment.getSegment());
            experimentOriginalCourseTableDetailSegmentList.add(experimentOriginalCourseTableDetailSegment);
        }
        experimentOriginalCourseTableDetail.setCourseSegmentList(experimentOriginalCourseTableDetailSegmentList);
        return experimentOriginalCourseTableDetail;
    }

    private ExperimentOriginalCourseTableDetailRoomUser composeExperimentOriginalCourseTableDetailRoomUser(
            CourseTableDetailRoomUser courseTableDetailRoomUser, ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail,
            String creatorId, String creatorName) {
        ExperimentOriginalCourseTableDetailRoomUser experimentOriginalCourseTableDetailRoomUser =
                new ExperimentOriginalCourseTableDetailRoomUser();
        experimentOriginalCourseTableDetailRoomUser.setRoomId(courseTableDetailRoomUser.getRoomId());
        experimentOriginalCourseTableDetailRoomUser.setRoomName(courseTableDetailRoomUser.getRoomName());
        experimentOriginalCourseTableDetailRoomUser.setRoomCode(courseTableDetailRoomUser.getRoomCode());
        experimentOriginalCourseTableDetailRoomUser.setPersonnelNumber(courseTableDetailRoomUser.getPersonnelNumber());
        experimentOriginalCourseTableDetailRoomUser.setRoomCategoryId(courseTableDetailRoomUser.getRoomCategoryId());
        experimentOriginalCourseTableDetailRoomUser.setExperimentOriginalCourseTableDetail(experimentOriginalCourseTableDetail);
        experimentOriginalCourseTableDetailRoomUser.setItemTeacherId(courseTableDetailRoomUser.getItemTeacherId());
        experimentOriginalCourseTableDetailRoomUser.setItemTeacherNo(courseTableDetailRoomUser.getItemTeacherNo());
        experimentOriginalCourseTableDetailRoomUser.setItemTeacherName(courseTableDetailRoomUser.getItemTeacherName());
        experimentOriginalCourseTableDetailRoomUser.setHasLive(courseTableDetailRoomUser.isHasLive());
        experimentOriginalCourseTableDetailRoomUser.setGroupId(courseTableDetailRoomUser.getGroupId());
        experimentOriginalCourseTableDetailRoomUser.setCreatorId(creatorId);
        experimentOriginalCourseTableDetailRoomUser.setCreatorName(creatorName);
        experimentOriginalCourseTableDetailRoomUser.setCreateTime(new Date());
        return experimentOriginalCourseTableDetailRoomUser;
    }

    private ExperimentOriginalCourseTableDetailTeacher composeExperimentOriginalCourseTableDetailTeacher(
            CourseTableDetailTeacher courseTableDetailTeacher,
            ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail, String creatorId, String creatorName) {
        ExperimentOriginalCourseTableDetailTeacher experimentOriginalCourseTableDetailTeacher =
                new ExperimentOriginalCourseTableDetailTeacher();
        experimentOriginalCourseTableDetailTeacher.setTeacherId(courseTableDetailTeacher.getTeacherId());
        experimentOriginalCourseTableDetailTeacher.setTeacherNo(courseTableDetailTeacher.getTeacherNo());
        experimentOriginalCourseTableDetailTeacher.setTeacherName(courseTableDetailTeacher.getTeacherName());
        experimentOriginalCourseTableDetailTeacher.setTeacherCollegeId(courseTableDetailTeacher.getTeacherCollegeId());
        experimentOriginalCourseTableDetailTeacher.setTeacherCollegeName(courseTableDetailTeacher.getTeacherCollegeName());
        experimentOriginalCourseTableDetailTeacher.setTeacherTitle(courseTableDetailTeacher.getTeacherTitle());
        experimentOriginalCourseTableDetailTeacher.setExperimentOriginalCourseTableDetail(experimentOriginalCourseTableDetail);
        experimentOriginalCourseTableDetailTeacher.setCreatorId(creatorId);
        experimentOriginalCourseTableDetailTeacher.setCreatorName(creatorName);
        experimentOriginalCourseTableDetailTeacher.setCreateTime(new Date());
        return experimentOriginalCourseTableDetailTeacher;
    }

    public static boolean validateDates(String startDate, String endDate) {
        if (!DATE_PATTERN.matcher(startDate).matches() || !DATE_PATTERN.matcher(endDate).matches()) {
            return true;
        }
        LocalDate startLocalDate;
        LocalDate endLocalDate;
        LocalDate today = LocalDate.now();
        try {
            startLocalDate = LocalDate.parse(startDate, DATE_FORMATTER);
            endLocalDate = LocalDate.parse(endDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return true;
        }
        if (endLocalDate.isBefore(startLocalDate)) {
            return true;
        }
        if (startLocalDate.isBefore(today) || startLocalDate.isEqual(today)) {
            return true;
        }
        return false;
    }

    public static boolean validateDate(String startDate) {
        if (!DATE_PATTERN.matcher(startDate).matches()) {
            return true;
        }
        LocalDate startLocalDate;
        LocalDate today = LocalDate.now();
        try {
            startLocalDate = LocalDate.parse(startDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return true;
        }
        if (startLocalDate.isBefore(today) || startLocalDate.isEqual(today)) {
            return true;
        }
        return false;
    }

    public ExperimentScheduleCourseTableDetail getExperimentScheduleCourseTableDetail(String id) {
        return experimentScheduleCourseTableDetailRepository.findById(id).orElse(null);
    }

    @Transactional
    public ExperimentScheduleCourseTableDetailRelevantResource pushExperimentScheduleCourseTableDetail(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailPushParam) {
        ExperimentScheduleCourseTableDetailRelevantResource experimentScheduleCourseTableDetailRelevantResource =
                new ExperimentScheduleCourseTableDetailRelevantResource();
        String courseTableDetailId = "";
        courseTableDetailId = getCourseTableDetailId(experimentScheduleCourseTableDetail);
        if (StringUtils.isEmpty(courseTableDetailId)) {
            experimentScheduleCourseTableDetail.setScheduleStatus(ScheduleStatus.EDUCATIONAL_ADMINISTRATION_DELETE);
            experimentScheduleCourseTableDetailRepository.saveAndFlush(experimentScheduleCourseTableDetail);
            experimentScheduleCourseTableDetailRelevantResource.setResult(false);
            experimentScheduleCourseTableDetailRelevantResource.setRemark("教务课表不存在");
            return experimentScheduleCourseTableDetailRelevantResource;
        }
        deleteExperimentScheduleCourseTableDetailRelevantData(experimentScheduleCourseTableDetail);
        List<Integer> segmentList = generateSegmentList(experimentScheduleCourseTableDetailPushParam.getStartSegment(),
                experimentScheduleCourseTableDetailPushParam.getEndSegment());
        List<ExperimentScheduleCourseTableDetailTeacher> experimentScheduleCourseTableDetailTeacherList =
                saveExperimentScheduleCourseTableDetailTeachers(
                        experimentScheduleCourseTableDetail, experimentScheduleCourseTableDetailPushParam);
        experimentScheduleCourseTableDetailTeacherList =
                experimentScheduleCourseTableDetailTeacherRepository.saveAll(experimentScheduleCourseTableDetailTeacherList);
        List<ExperimentScheduleCourseTableDetailRoomUser> experimentScheduleCourseTableDetailRoomUserList =
                saveExperimentScheduleCourseTableDetailRoomUser(
                        experimentScheduleCourseTableDetail, experimentScheduleCourseTableDetailPushParam);
        experimentScheduleCourseTableDetailRoomUserList =
                experimentScheduleCourseTableDetailRoomUserRepository.saveAll(experimentScheduleCourseTableDetailRoomUserList);
        List<ExperimentScheduleCourseTableDetailProject> experimentScheduleCourseTableDetailProjectList =
                saveExperimentScheduleCourseTableDetailProjects(
                        experimentScheduleCourseTableDetail, experimentScheduleCourseTableDetailPushParam);
        experimentScheduleCourseTableDetailProjectList =
                experimentScheduleCourseTableDetailProjectRepository.saveAll(experimentScheduleCourseTableDetailProjectList);
        List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList =
                experimentScheduleCourseTableDetailPushParam.getExperimentScheduleCourseTableDetailRoomList();
        List<String> roomIdList = experimentScheduleCourseTableDetailRoomList.stream()
                .map(ExperimentScheduleCourseTableDetailRoomVo::getRoomId).collect(Collectors.toList());
        List<ClassroomAndFloorResource> classroomAndFloorResources = classRoomService.getClassroomFloorInfo(roomIdList);
        List<ExperimentScheduleCourseTableDetailSegment> experimentScheduleCourseTableDetailSegmentList =
                saveExperimentScheduleCourseTableDetailSegments(
                        segmentList, experimentScheduleCourseTableDetail, classroomAndFloorResources);
        experimentScheduleCourseTableDetailSegmentList =
                experimentScheduleCourseTableDetailSegmentRepository.saveAll(experimentScheduleCourseTableDetailSegmentList);
        CourseType courseType = courseTypeRepository.findById(1).get();
        updateExperimentScheduleCourseTableDetail(
                experimentScheduleCourseTableDetail, experimentScheduleCourseTableDetailPushParam, segmentList, courseType);
        experimentScheduleCourseTableDetail.setCourseTableDetailTeacherList(experimentScheduleCourseTableDetailTeacherList);
        experimentScheduleCourseTableDetail.setCourseTableDetailRoomUserList(experimentScheduleCourseTableDetailRoomUserList);
        experimentScheduleCourseTableDetail.setCourseSegmentList(experimentScheduleCourseTableDetailSegmentList);
        experimentScheduleCourseTableDetail.setCourseTableDetailProjects(experimentScheduleCourseTableDetailProjectList);
        experimentScheduleCourseTableDetail =
                experimentScheduleCourseTableDetailRepository.saveAndFlush(experimentScheduleCourseTableDetail);
        //记录提交排课操作日志
        Integer pushType = experimentScheduleCourseTableDetailPushParam.getPushType();
        String operationDescription = pushType == null || pushType.equals(0) ? "提交排课" : "修改排课";
        experimentScheduleCourseLogService
                .createLog(experimentScheduleCourseTableDetail.getId(), experimentScheduleCourseTableDetail.getModifierId(),
                        experimentScheduleCourseTableDetail.getModifierName(), operationDescription);
        CourseTableDetail courseTableDetail = courseTableDetailRepository.findById(courseTableDetailId).get();
        deleteCourseTableDetailRelevantData(courseTableDetail);
        List<CourseTableDetailTeacher> courseTableDetailTeacherList =
                saveCourseTableDetailTeachers(courseTableDetail, experimentScheduleCourseTableDetailTeacherList);
        courseTableDetailTeacherList = courseTableDetailTeacherRepository.saveAll(courseTableDetailTeacherList);
        List<CourseTableDetailRoomUser> courseTableDetailRoomUserList =
                saveCourseTableDetailRoomUserList(courseTableDetail, experimentScheduleCourseTableDetailRoomUserList);
        courseTableDetailRoomUserList = courseTableDetailRoomUserRepository.saveAll(courseTableDetailRoomUserList);
        List<CourseTableDetailProject> courseTableDetailProjectList =
                saveCourseTableDetailProjects(courseTableDetail, experimentScheduleCourseTableDetailProjectList);
        courseTableDetailProjectList = courseTableDetailProjectRepository.saveAll(courseTableDetailProjectList);
        List<CourseSegment> courseSegmentList =
                saveCourseSegmentList(experimentScheduleCourseTableDetailSegmentList, courseTableDetail);
        courseSegmentList = courseSegmentRepository.saveAll(courseSegmentList);
        updateCourseTableDetail(experimentScheduleCourseTableDetail, courseTableDetail);
        updateCourseTableDetailRelevantData(courseTableDetail, courseTableDetailTeacherList,
                courseTableDetailRoomUserList, courseTableDetailProjectList, courseSegmentList);
        courseTableDetailRepository.saveAndFlush(courseTableDetail);
        ExperimentScheduleCourseTableDetailBaseVo experimentScheduleCourseTableDetailBaseVo =
                getExperimentScheduleCourseTableDetailBaseVo(
                        experimentScheduleCourseTableDetail, experimentScheduleCourseTableDetailPushParam);
        experimentScheduleCourseTableDetailRelevantResource.setResult(true);
        experimentScheduleCourseTableDetailRelevantResource.setRemark("实验课表保存成功");
        experimentScheduleCourseTableDetailRelevantResource.setExperimentScheduleCourseTableDetailBaseVo(
                experimentScheduleCourseTableDetailBaseVo
        );
        return experimentScheduleCourseTableDetailRelevantResource;
    }

    public String getCourseTableDetailId(ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail) {
        String courseTableDetailId;
        if (experimentScheduleCourseTableDetail.getScheduleStatus().equals(ScheduleStatus.ALREADY_SCHEDULE)) {
            courseTableDetailId = getCourseTableDetailId(experimentScheduleCourseTableDetail, Constant.TWO);
        } else {
            courseTableDetailId = getCourseTableDetailId(experimentScheduleCourseTableDetail, Constant.ONE);
        }
        return courseTableDetailId;
    }

    @Transactional
    public void deleteCourseTableDetailRelevantData(CourseTableDetail courseTableDetail) {
        courseTableDetailRoomUserRepository.deleteAll(courseTableDetail.getCourseTableDetailRoomUserList());
        courseTableDetailTeacherRepository.deleteAll(courseTableDetail.getCourseTableDetailTeacherList());
        courseTableDetailProjectRepository.deleteAll(courseTableDetail.getCourseTableDetailProjects());
        courseSegmentRepository.deleteAll(courseTableDetail.getCourseSegmentList());
    }

    @Transactional
    public void deleteExperimentScheduleCourseTableDetailRelevantData(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail) {
        experimentScheduleCourseTableDetailTeacherRepository.deleteAll(experimentScheduleCourseTableDetail.getCourseTableDetailTeacherList());
        experimentScheduleCourseTableDetailRoomUserRepository.deleteAll(experimentScheduleCourseTableDetail.getCourseTableDetailRoomUserList());
        experimentScheduleCourseTableDetailSegmentRepository.deleteAll(experimentScheduleCourseTableDetail.getCourseSegmentList());
        experimentScheduleCourseTableDetailProjectRepository.deleteAll(experimentScheduleCourseTableDetail.getCourseTableDetailProjects());
    }

    private ExperimentScheduleCourseTableDetailBaseVo getExperimentScheduleCourseTableDetailBaseVo(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailPushParam) {
        ExperimentScheduleCourseTableDetailBaseVo experimentScheduleCourseTableDetailBaseVo =
                new ExperimentScheduleCourseTableDetailBaseVo();
        experimentScheduleCourseTableDetailBaseVo.setId(experimentScheduleCourseTableDetail.getId());
        experimentScheduleCourseTableDetailBaseVo.setStartSegment(experimentScheduleCourseTableDetailPushParam.getStartSegment());
        experimentScheduleCourseTableDetailBaseVo.setEndSegment(experimentScheduleCourseTableDetailPushParam.getEndSegment());
        String segmentStartTime = DateUtils.formatDate(DateUtils.TIME_SECOND,
                experimentScheduleCourseTableDetail.getSegmentStartTime());
        String segmentEndTime = DateUtils.formatDate(DateUtils.TIME_SECOND,
                experimentScheduleCourseTableDetail.getSegmentEndTime());
        experimentScheduleCourseTableDetailBaseVo.setSegmentStartTime(segmentStartTime);
        experimentScheduleCourseTableDetailBaseVo.setSegmentEndTime(segmentEndTime);
        String courseDate = DateUtils.formatDate(DateUtils.DATE, experimentScheduleCourseTableDetail.getCourseDate());
        experimentScheduleCourseTableDetailBaseVo.setCourseDate(courseDate);
        experimentScheduleCourseTableDetailBaseVo.setExperimentScheduleCourseTableDetailTeacherList(
                experimentScheduleCourseTableDetailPushParam.getExperimentScheduleCourseTableDetailTeacherList()
        );
        experimentScheduleCourseTableDetailBaseVo.setExperimentScheduleCourseTableDetailRoomList(
                experimentScheduleCourseTableDetailPushParam.getExperimentScheduleCourseTableDetailRoomList()
        );
        experimentScheduleCourseTableDetailBaseVo.setExperimentScheduleCourseTableDetailProjectList(
                experimentScheduleCourseTableDetailPushParam.getExperimentScheduleCourseTableDetailProjectList()
        );
        String courseTableId = experimentScheduleCourseTableDetail.getCourseTableId();
        CourseTable courseTable = courseTableRepository.findById(courseTableId).orElse(null);
        List<StudentVo> list = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(courseTable)) {
            List<GroupMember> groupMemberList = groupMemberRepository.findByStudentByGroupId(courseTable.getGroup().getId());
            for (GroupMember groupMember : groupMemberList) {
                StudentVo studentVo = new StudentVo();
                studentVo.setGroupId(groupMember.getGroup().getId());
                studentVo.setGroupName(groupMember.getGroup().getGroupName());
                studentVo.setStudentId(groupMember.getStudentId());
                studentVo.setStudentName(groupMember.getStudentName());
                studentVo.setStudentNo(groupMember.getStudentNo());
                list.add(studentVo);
            }
        }
        experimentScheduleCourseTableDetailBaseVo.setStudentVoList(list);
        return experimentScheduleCourseTableDetailBaseVo;
    }

    private List<CourseSegment> saveCourseSegmentList(
            List<ExperimentScheduleCourseTableDetailSegment> experimentScheduleCourseTableDetailSegmentList,
            CourseTableDetail courseTableDetail) {
        List<CourseSegment> courseSegmentList = new ArrayList<>();
        for (ExperimentScheduleCourseTableDetailSegment experimentScheduleCourseTableDetailSegment :
                experimentScheduleCourseTableDetailSegmentList) {
            CourseSegment courseSegment = new CourseSegment();
            courseSegment.setCourseTableDetail(courseTableDetail);
            courseSegment.setSegment(experimentScheduleCourseTableDetailSegment.getSegment());
            courseSegmentList.add(courseSegment);
        }
        return courseSegmentList;
    }

    private List<CourseTableDetailProject> saveCourseTableDetailProjects(
            CourseTableDetail courseTableDetail,
            List<ExperimentScheduleCourseTableDetailProject> experimentScheduleCourseTableDetailProjectList) {
        List<CourseTableDetailProject> courseTableDetailProjectList = new ArrayList<>();
        for (ExperimentScheduleCourseTableDetailProject experimentScheduleCourseTableDetailProject :
                experimentScheduleCourseTableDetailProjectList) {
            CourseTableDetailProject courseTableDetailProject = new CourseTableDetailProject();
            courseTableDetailProject.setProjectId(experimentScheduleCourseTableDetailProject.getProjectId());
            courseTableDetailProject.setProjectCode(experimentScheduleCourseTableDetailProject.getProjectCode());
            courseTableDetailProject.setProjectName(experimentScheduleCourseTableDetailProject.getProjectName());
            courseTableDetailProject.setCourseTableDetail(courseTableDetail);
            courseTableDetailProjectList.add(courseTableDetailProject);
        }
        return courseTableDetailProjectList;
    }

    private List<CourseTableDetailRoomUser> saveCourseTableDetailRoomUserList(
            CourseTableDetail courseTableDetail,
            List<ExperimentScheduleCourseTableDetailRoomUser> experimentScheduleCourseTableDetailRoomUserList) {
        List<CourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();
        for (ExperimentScheduleCourseTableDetailRoomUser experimentScheduleCourseTableDetailRoomUser :
                experimentScheduleCourseTableDetailRoomUserList) {
            CourseTableDetailRoomUser courseTableDetailRoomUser = new CourseTableDetailRoomUser();
            courseTableDetailRoomUser.setRoomId(experimentScheduleCourseTableDetailRoomUser.getRoomId());
            courseTableDetailRoomUser.setRoomName(experimentScheduleCourseTableDetailRoomUser.getRoomName());
            courseTableDetailRoomUser.setRoomCode(experimentScheduleCourseTableDetailRoomUser.getRoomCode());
            courseTableDetailRoomUser.setPersonnelNumber(experimentScheduleCourseTableDetailRoomUser.getPersonnelNumber());
            courseTableDetailRoomUser.setRoomCategoryId(experimentScheduleCourseTableDetailRoomUser.getRoomCategoryId());
            courseTableDetailRoomUser.setCourseTableDetail(courseTableDetail);
            courseTableDetailRoomUser.setItemTeacherId(experimentScheduleCourseTableDetailRoomUser.getItemTeacherId());
            courseTableDetailRoomUser.setItemTeacherNo(experimentScheduleCourseTableDetailRoomUser.getItemTeacherNo());
            courseTableDetailRoomUser.setItemTeacherName(experimentScheduleCourseTableDetailRoomUser.getItemTeacherName());
            courseTableDetailRoomUser.setHasLive(experimentScheduleCourseTableDetailRoomUser.isHasLive());
            courseTableDetailRoomUser.setGroupId(experimentScheduleCourseTableDetailRoomUser.getGroupId());
            courseTableDetailRoomUser.setCreatorId(experimentScheduleCourseTableDetailRoomUser.getCreatorId());
            courseTableDetailRoomUser.setCreatorName(experimentScheduleCourseTableDetailRoomUser.getCreatorName());
            courseTableDetailRoomUser.setCreateTime(experimentScheduleCourseTableDetailRoomUser.getCreateTime());
            courseTableDetailRoomUserList.add(courseTableDetailRoomUser);
        }
        return courseTableDetailRoomUserList;
    }

    private List<CourseTableDetailTeacher> saveCourseTableDetailTeachers(
            CourseTableDetail courseTableDetail,
            List<ExperimentScheduleCourseTableDetailTeacher> experimentScheduleCourseTableDetailTeacherList) {
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();
        for (ExperimentScheduleCourseTableDetailTeacher experimentScheduleCourseTableDetailTeacher :
                experimentScheduleCourseTableDetailTeacherList) {
            CourseTableDetailTeacher courseTableDetailTeacher = new CourseTableDetailTeacher();
            courseTableDetailTeacher.setTeacherId(experimentScheduleCourseTableDetailTeacher.getTeacherId());
            courseTableDetailTeacher.setTeacherNo(experimentScheduleCourseTableDetailTeacher.getTeacherNo());
            courseTableDetailTeacher.setTeacherName(experimentScheduleCourseTableDetailTeacher.getTeacherName());
            courseTableDetailTeacher.setTeacherCollegeId(experimentScheduleCourseTableDetailTeacher.getTeacherCollegeId());
            courseTableDetailTeacher.setTeacherCollegeName(experimentScheduleCourseTableDetailTeacher.getTeacherCollegeName());
            courseTableDetailTeacher.setTeacherTitle(experimentScheduleCourseTableDetailTeacher.getTeacherTitle());
            courseTableDetailTeacher.setCourseTableDetail(courseTableDetail);
            courseTableDetailTeacher.setCreatorId(experimentScheduleCourseTableDetailTeacher.getCreatorId());
            courseTableDetailTeacher.setCreatorName(experimentScheduleCourseTableDetailTeacher.getCreatorName());
            courseTableDetailTeacher.setCreateTime(experimentScheduleCourseTableDetailTeacher.getCreateTime());
            courseTableDetailTeacherList.add(courseTableDetailTeacher);
        }
        return courseTableDetailTeacherList;
    }

    private void updateCourseTableDetail(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail, CourseTableDetail courseTableDetail) {
        courseTableDetail.setCourseDate(experimentScheduleCourseTableDetail.getCourseDate());
        courseTableDetail.setTeacherNames(experimentScheduleCourseTableDetail.getTeacherNames());
        courseTableDetail.setTeacherCollegeIds(experimentScheduleCourseTableDetail.getTeacherCollegeIds());
        courseTableDetail.setTeacherCollegeNames(experimentScheduleCourseTableDetail.getTeacherCollegeNames());
        courseTableDetail.setSegment(experimentScheduleCourseTableDetail.getSegment());
        courseTableDetail.setSegmentStartTime(experimentScheduleCourseTableDetail.getSegmentStartTime());
        courseTableDetail.setSegmentEndTime(experimentScheduleCourseTableDetail.getSegmentEndTime());
        courseTableDetail.setSource(Source.TEACHING_CALENDAR);
        courseTableDetail.setWeek(experimentScheduleCourseTableDetail.getWeek());
        courseTableDetail.setWeekNum(experimentScheduleCourseTableDetail.getWeekNum());
        courseTableDetail.setCourseKind(experimentScheduleCourseTableDetail.getCourseKind());
        courseTableDetail.setCreatorId(experimentScheduleCourseTableDetail.getModifierId());
        courseTableDetail.setCreatorName(experimentScheduleCourseTableDetail.getModifierName());
        courseTableDetail.setCreateTime(experimentScheduleCourseTableDetail.getModifyTime());
        courseTableDetail.setCourseType(experimentScheduleCourseTableDetail.getCourseType());
    }

    public String getCourseTableDetailId(ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail, Integer type) {
        String experimentOriginalCourseTableDetailId = experimentScheduleCourseTableDetail.getExperimentOriginalCourseTableDetailId();
        String querySql = "";
        Map<String, Object> paramMap = new HashMap<>();
        if (type == Constant.ONE) {
            paramMap.put("experimentOriginalCourseTableDetailId", experimentOriginalCourseTableDetailId);
            querySql = "SELECT ctd.id AS courseTableDetailId   FROM tb_course_table_detail ctd  " +
                    "INNER JOIN tb_course_table_detail_room_user ctdru ON ctdru.course_table_detail_id = ctd.id  " +
                    "inner join ( SELECT   eoctd.id, eoctd.course_table_id,eoctd.course_date,eoctd.segment, eoctdru.room_id, " +
                    "eoctdru.room_name  FROM tb_experiment_original_course_table_detail eoctd  " +
                    "  INNER JOIN tb_experiment_original_course_table_detail_room_user eoctdru  " +
                    "   ON eoctd.id = eoctdru.experiment_original_course_table_detail_id   " +
                    ") a on a.course_table_id = ctd.course_table_id AND a.course_date = ctd.course_date   " +
                    "and a.segment = ctd.segment AND a.room_name = ctdru.room_name   " +
                    "WHERE ctd.source = 0 and a.id = :experimentOriginalCourseTableDetailId limit 1;";
        } else if (type == Constant.TWO) {
            paramMap.put("experimentScheduleCourseTableDetailId", experimentScheduleCourseTableDetail.getId());
            querySql = "SELECT ctd.id AS courseTableDetailId   FROM tb_course_table_detail ctd " +
                    "  INNER JOIN tb_course_table_detail_room_user ctdru ON ctdru.course_table_detail_id = ctd.id  " +
                    "  INNER JOIN (  SELECT esctd.id, esctd.course_table_id,esctd.course_date,esctd.segment," +
                    "  esctdru.room_id,esctdru.room_name   FROM tb_experiment_schedule_course_table_detail esctd " +
                    "       INNER JOIN tb_experiment_schedule_course_table_detail_room_user esctdru " +
                    "       ON esctd.id = esctdru.experiment_schedule_course_table_detail_id  " +
                    " ) a on a.course_table_id = ctd.course_table_id AND a.course_date = ctd.course_date " +
                    "   AND a.segment = ctd.segment AND a.room_name = ctdru.room_name  " +
                    "  where ctd.source = 5 and a.id = :experimentScheduleCourseTableDetailId limit 1 ";
        }
        Query queryData = entityManager.createNativeQuery(querySql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> queryDataList = queryData.getResultList();
        if (!CollectionUtils.isEmpty(queryDataList)) {
            Map<String, Object> resMap = queryDataList.get(0);
            return String.valueOf(resMap.get("courseTableDetailId"));
        } else {
            return null;
        }
    }

    private void updateExperimentScheduleCourseTableDetail(ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
                                                           ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailPushParam,
                                                           List<Integer> segmentList, CourseType courseType) {
        experimentScheduleCourseTableDetail.setCourseDate(
                DateUtils.stringToDate(DateUtils.DATE, experimentScheduleCourseTableDetailPushParam.getCourseDate()));
        List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherVoList =
                experimentScheduleCourseTableDetailPushParam.getExperimentScheduleCourseTableDetailTeacherList();
        List<ExperimentScheduleCourseTableDetailTeacherVo> sortedList = experimentScheduleCourseTableDetailTeacherVoList.stream()
                .sorted(Comparator.comparing(ExperimentScheduleCourseTableDetailTeacherVo::getTeacherName))
                .collect(Collectors.toList());
        String teacherNames = sortedList.stream()
                .map(ExperimentScheduleCourseTableDetailTeacherVo::getTeacherName)
                .collect(joining(","));
        experimentScheduleCourseTableDetail.setTeacherNames(teacherNames);
        String teacherCollegeIds = sortedList.stream()
                .map(ExperimentScheduleCourseTableDetailTeacherVo::getTeacherCollegeId)
                .collect(joining(","));
        experimentScheduleCourseTableDetail.setTeacherCollegeIds(teacherCollegeIds);
        String teacherCollegeNames = sortedList.stream()
                .map(ExperimentScheduleCourseTableDetailTeacherVo::getTeacherCollegeName)
                .collect(joining(","));
        experimentScheduleCourseTableDetail.setTeacherCollegeNames(teacherCollegeNames);
        String segments = segmentList.stream().map(String::valueOf).collect(joining(","));
        experimentScheduleCourseTableDetail.setSegment(segments);
        experimentScheduleCourseTableDetail.setScheduleSource(ScheduleSource.TEACHING_CALENDAR);
        Term term = termService.getTermByDate(experimentScheduleCourseTableDetail.getCourseDate());
        Date startDate = term.getStartDate();
        LocalDate startDateLocalDate = dateToLocalDate(startDate);
        LocalDate courseDateLocalDate = dateToLocalDate(experimentScheduleCourseTableDetail.getCourseDate());
        String week = String.valueOf(calculateWeekNumber(startDateLocalDate, courseDateLocalDate));
        experimentScheduleCourseTableDetail.setWeek(week);
        int weekNum = getWeekdayAsInt(courseDateLocalDate);
        experimentScheduleCourseTableDetail.setWeekNum(weekNum);
        experimentScheduleCourseTableDetail.setCourseKind(CourseKind.EXPERIMENTAL_COURSE);
        experimentScheduleCourseTableDetail.setScheduleStatus(ScheduleStatus.ALREADY_SCHEDULE);
        experimentScheduleCourseTableDetail.setModifierId(experimentScheduleCourseTableDetailPushParam.getCreatorId());
        experimentScheduleCourseTableDetail.setModifierName(experimentScheduleCourseTableDetailPushParam.getCreatorName());
        experimentScheduleCourseTableDetail.setModifyTime(new Date());
        experimentScheduleCourseTableDetail.setCourseType(courseType);
    }

    private List<ExperimentScheduleCourseTableDetailSegment> saveExperimentScheduleCourseTableDetailSegments(
            List<Integer> segmentList, ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            List<ClassroomAndFloorResource> classroomAndFloorResources) {
        List<ExperimentScheduleCourseTableDetailSegment> courseSegmentList = new ArrayList<>();
        String buildId = "0";
        if (CollectionUtils.isNotEmpty(classroomAndFloorResources)) {
            buildId = classroomAndFloorResources.get(0).getBuildId();
        }
        List<Segment> dataSegmentList = segmentRepository.findByBuildIdOrderByBuildName(buildId);
        List<Segment> effectiveSegmentList = new ArrayList<>();
        String minStartTime = null;
        String maxEndTime = null;
        segmentList.forEach(segmentName -> {
            Segment segment = dataSegmentList.stream().filter(s -> s.getSegment().equals(segmentName)).findFirst().orElse(null);
            if (Objects.nonNull(segment)) {
                effectiveSegmentList.add(segment);
                ExperimentScheduleCourseTableDetailSegment courseSegment = new ExperimentScheduleCourseTableDetailSegment();
                courseSegment.setSegment(segment);
                courseSegment.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
                courseSegmentList.add(courseSegment);
            }
        });
        for (Segment segment : effectiveSegmentList) {
            if (minStartTime == null || segment.getStartTime().compareTo(minStartTime) < 0) {
                minStartTime = segment.getStartTime();
            }
            if (maxEndTime == null || segment.getEndTime().compareTo(maxEndTime) > 0) {
                maxEndTime = segment.getEndTime();
            }
        }
        experimentScheduleCourseTableDetail.setSegmentStartTime(DateUtils.stringToDate(DateUtils.TIME, minStartTime));
        experimentScheduleCourseTableDetail.setSegmentEndTime(DateUtils.stringToDate(DateUtils.TIME, maxEndTime));
        return courseSegmentList;
    }

    private List<ExperimentScheduleCourseTableDetailProject> saveExperimentScheduleCourseTableDetailProjects(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailPushParam) {
        List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectList =
                experimentScheduleCourseTableDetailPushParam.getExperimentScheduleCourseTableDetailProjectList();
        List<ExperimentScheduleCourseTableDetailProject> courseTableDetailProjects = new ArrayList<>();
        for (ExperimentScheduleCourseTableDetailProjectVo experimentScheduleCourseTableDetailProjectVo :
                experimentScheduleCourseTableDetailProjectList) {
            ExperimentScheduleCourseTableDetailProject experimentScheduleCourseTableDetailProject =
                    getExperimentScheduleCourseTableDetailProject(experimentScheduleCourseTableDetail,
                            experimentScheduleCourseTableDetailProjectVo);
            courseTableDetailProjects.add(experimentScheduleCourseTableDetailProject);
        }
        return courseTableDetailProjects;
    }

    private static ExperimentScheduleCourseTableDetailProject getExperimentScheduleCourseTableDetailProject(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentScheduleCourseTableDetailProjectVo experimentScheduleCourseTableDetailProjectVo) {
        ExperimentScheduleCourseTableDetailProject experimentScheduleCourseTableDetailProject =
                new ExperimentScheduleCourseTableDetailProject();
        experimentScheduleCourseTableDetailProject.setProjectId(experimentScheduleCourseTableDetailProjectVo.getProjectId());
        experimentScheduleCourseTableDetailProject.setProjectCode(experimentScheduleCourseTableDetailProjectVo.getProjectCode());
        experimentScheduleCourseTableDetailProject.setProjectName(experimentScheduleCourseTableDetailProjectVo.getProjectName());
        experimentScheduleCourseTableDetailProject.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
        return experimentScheduleCourseTableDetailProject;
    }

    private List<ExperimentScheduleCourseTableDetailRoomUser> saveExperimentScheduleCourseTableDetailRoomUser(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailPushParam) {
        List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomList =
                experimentScheduleCourseTableDetailPushParam.getExperimentScheduleCourseTableDetailRoomList();
        String roomIds = experimentScheduleCourseTableDetailRoomList.stream()
                .map(ExperimentScheduleCourseTableDetailRoomVo::getRoomId)
                .collect(joining(","));
        List<ClassRoomResource> classRoomResourceList = classRoomService.findClassRoomResourceList(roomIds);
        List<ExperimentScheduleCourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();
        for (ExperimentScheduleCourseTableDetailRoomVo experimentScheduleCourseTableDetailRoomVo :
                experimentScheduleCourseTableDetailRoomList) {
            ExperimentScheduleCourseTableDetailRoomUser experimentScheduleCourseTableDetailRoomUser =
                    new ExperimentScheduleCourseTableDetailRoomUser();
            experimentScheduleCourseTableDetailRoomUser.setRoomId(experimentScheduleCourseTableDetailRoomVo.getRoomId());
            experimentScheduleCourseTableDetailRoomUser.setRoomName(experimentScheduleCourseTableDetailRoomVo.getRoomName());
            experimentScheduleCourseTableDetailRoomUser.setRoomCode(experimentScheduleCourseTableDetailRoomVo.getRoomCode());
            experimentScheduleCourseTableDetailRoomUser.setPersonnelNumber(
                    experimentScheduleCourseTableDetailRoomVo.getLabStudentCount());
            ClassRoomResource classRoomResource = classRoomResourceList.stream()
                    .filter(classroom -> classroom.getId().equals(experimentScheduleCourseTableDetailRoomVo.getRoomId()) && classroom.getHasLive())
                    .findFirst().orElse(null);
            experimentScheduleCourseTableDetailRoomUser.setHasLive(!Objects.isNull(classRoomResource));
            experimentScheduleCourseTableDetailRoomUser.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
            experimentScheduleCourseTableDetailRoomUser.setItemTeacherId(experimentScheduleCourseTableDetailRoomVo.getItemTeacherId());
            experimentScheduleCourseTableDetailRoomUser.setItemTeacherNo(experimentScheduleCourseTableDetailRoomVo.getItemTeacherNo());
            experimentScheduleCourseTableDetailRoomUser.setItemTeacherName(experimentScheduleCourseTableDetailRoomVo.getItemTeacherName());
            experimentScheduleCourseTableDetailRoomUser.setCreatorId(experimentScheduleCourseTableDetailPushParam.getCreatorId());
            experimentScheduleCourseTableDetailRoomUser.setCreatorName(experimentScheduleCourseTableDetailPushParam.getCreatorName());
            experimentScheduleCourseTableDetailRoomUser.setCreateTime(new Date());
            courseTableDetailRoomUserList.add(experimentScheduleCourseTableDetailRoomUser);
        }
        return courseTableDetailRoomUserList;
    }

    private List<ExperimentScheduleCourseTableDetailTeacher> saveExperimentScheduleCourseTableDetailTeachers(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailPushParam) {
        List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherVoList =
                experimentScheduleCourseTableDetailPushParam.getExperimentScheduleCourseTableDetailTeacherList();
        List<ExperimentScheduleCourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();
        for (ExperimentScheduleCourseTableDetailTeacherVo experimentScheduleCourseTableDetailTeacherVo :
                experimentScheduleCourseTableDetailTeacherVoList) {
            ExperimentScheduleCourseTableDetailTeacher experimentScheduleCourseTableDetailTeacher =
                    getExperimentScheduleCourseTableDetailTeacher(experimentScheduleCourseTableDetail,
                            experimentScheduleCourseTableDetailPushParam, experimentScheduleCourseTableDetailTeacherVo);
            courseTableDetailTeacherList.add(experimentScheduleCourseTableDetailTeacher);
        }
        return courseTableDetailTeacherList;
    }

    private static ExperimentScheduleCourseTableDetailTeacher getExperimentScheduleCourseTableDetailTeacher(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentScheduleCourseTableDetailPushParam experimentScheduleCourseTableDetailPushParam,
            ExperimentScheduleCourseTableDetailTeacherVo experimentScheduleCourseTableDetailTeacherVo) {
        ExperimentScheduleCourseTableDetailTeacher experimentScheduleCourseTableDetailTeacher =
                new ExperimentScheduleCourseTableDetailTeacher();
        experimentScheduleCourseTableDetailTeacher.setTeacherId(
                experimentScheduleCourseTableDetailTeacherVo.getTeacherId());
        experimentScheduleCourseTableDetailTeacher.setTeacherNo(
                experimentScheduleCourseTableDetailTeacherVo.getTeacherNo()
        );
        experimentScheduleCourseTableDetailTeacher.setTeacherName(
                experimentScheduleCourseTableDetailTeacherVo.getTeacherName()
        );
        experimentScheduleCourseTableDetailTeacher.setTeacherCollegeId(
                experimentScheduleCourseTableDetailTeacherVo.getTeacherCollegeId()
        );
        experimentScheduleCourseTableDetailTeacher.setTeacherCollegeName(
                experimentScheduleCourseTableDetailTeacherVo.getTeacherCollegeName()
        );
        experimentScheduleCourseTableDetailTeacher.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
        experimentScheduleCourseTableDetailTeacher.setCreatorId(experimentScheduleCourseTableDetailPushParam.getCreatorId());
        experimentScheduleCourseTableDetailTeacher.setCreatorName(experimentScheduleCourseTableDetailPushParam.getCreatorName());
        experimentScheduleCourseTableDetailTeacher.setCreateTime(new Date());
        return experimentScheduleCourseTableDetailTeacher;
    }

    public static List<Integer> generateSegmentList(int startSegment, int endSegment) {
        List<Integer> segments = new ArrayList<>();
        for (int i = startSegment; i <= endSegment; i++) {
            segments.add(i);
        }
        return segments;
    }

    public int calculateWeekNumber(LocalDate startDate, LocalDate courseDate) {
        LocalDate firstMonday = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        long daysBetween = ChronoUnit.DAYS.between(firstMonday, courseDate);
        return (int) (daysBetween / Constant.WEEK) + 1;
    }

    public int getWeekdayAsInt(LocalDate courseDate) {
        DayOfWeek weekday = courseDate.getDayOfWeek();
        return weekday.getValue();
    }

    public void updateEntranceGuardGeneratedStatus(String id, Integer entranceGuardGeneratedStatus) {
        ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail = getExperimentScheduleCourseTableDetail(id);
        if (ObjectUtils.isNotEmpty(entranceGuardGeneratedStatus)) {
            experimentScheduleCourseTableDetail.setEntranceGuardGeneratedStatus(
                    EntranceGuardGeneratedStatus.getEntranceGuardGeneratedStatus(entranceGuardGeneratedStatus));
            experimentScheduleCourseTableDetail.setModifyTime(new Date());
            experimentScheduleCourseTableDetailRepository.saveAndFlush(experimentScheduleCourseTableDetail);
        }
    }

    public void updateScheduleStatus(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail, ScheduleStatus scheduleStatus,
            String operatorId, String operatorName) {
        experimentScheduleCourseTableDetail.setScheduleStatus(scheduleStatus);
        experimentScheduleCourseTableDetail.setModifierId(operatorId);
        experimentScheduleCourseTableDetail.setModifierName(operatorName);
        experimentScheduleCourseTableDetail.setModifyTime(new Date());
        experimentScheduleCourseTableDetailRepository.saveAndFlush(experimentScheduleCourseTableDetail);
    }

    public ExperimentScheduleCourseTableDetailInfoVo getExperimentScheduleCourseTableDetailById(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail) {
        ExperimentScheduleCourseTableDetailInfoVo experimentScheduleCourseTableDetailInfoVo =
                new ExperimentScheduleCourseTableDetailInfoVo();
        experimentScheduleCourseTableDetailInfoVo.setId(experimentScheduleCourseTableDetail.getId());
        experimentScheduleCourseTableDetailInfoVo.setCourseId(experimentScheduleCourseTableDetail.getCourseId());
        experimentScheduleCourseTableDetailInfoVo.setCourseCode(experimentScheduleCourseTableDetail.getCourseCode());
        experimentScheduleCourseTableDetailInfoVo.setCourseName(experimentScheduleCourseTableDetail.getCourseName());
        experimentScheduleCourseTableDetailInfoVo.setGroupId(experimentScheduleCourseTableDetail.getGroupId());
        experimentScheduleCourseTableDetailInfoVo.setGroupNo(experimentScheduleCourseTableDetail.getGroupNo());
        experimentScheduleCourseTableDetailInfoVo.setGroupName(experimentScheduleCourseTableDetail.getGroupName());
        List<GroupMember> groupMemberList =
                groupMemberRepository.findByStudentByGroupId(experimentScheduleCourseTableDetail.getGroupId());
        if (ObjectUtils.isNotEmpty(groupMemberList)) {
            experimentScheduleCourseTableDetailInfoVo.setGroupStudentCount(groupMemberList.size());
        } else {
            experimentScheduleCourseTableDetailInfoVo.setGroupStudentCount(0);
        }
        List<ExperimentScheduleCourseTableDetailSegment> experimentScheduleCourseTableDetailSegmentList =
                experimentScheduleCourseTableDetail.getCourseSegmentList();
        Integer startSegment = experimentScheduleCourseTableDetailSegmentList.stream().map(
                e -> e.getSegment().getSegment()).min(Comparator.naturalOrder()).get();
        Integer endSegment = experimentScheduleCourseTableDetailSegmentList.stream().map(
                e -> e.getSegment().getSegment()).max(Comparator.naturalOrder()).get();
        experimentScheduleCourseTableDetailInfoVo.setStartSegment(startSegment);
        experimentScheduleCourseTableDetailInfoVo.setEndSegment(endSegment);
        String courseDate = DateUtils.formatDate(DateUtils.DATE, experimentScheduleCourseTableDetail.getCourseDate());
        experimentScheduleCourseTableDetailInfoVo.setCourseDate(courseDate);
        String segmentStartTime = DateUtils.formatDate(DateUtils.TIME, experimentScheduleCourseTableDetail.getSegmentStartTime());
        experimentScheduleCourseTableDetailInfoVo.setSegmentStartTime(segmentStartTime);
        String segmentEndTime = DateUtils.formatDate(DateUtils.TIME, experimentScheduleCourseTableDetail.getSegmentEndTime());
        experimentScheduleCourseTableDetailInfoVo.setSegmentEndTime(segmentEndTime);
        List<ExperimentScheduleCourseTableDetailTeacherVo> experimentScheduleCourseTableDetailTeacherVoList = new ArrayList<>();
        List<ExperimentScheduleCourseTableDetailTeacher> experimentScheduleCourseTableDetailTeacherList =
                experimentScheduleCourseTableDetail.getCourseTableDetailTeacherList();
        for (ExperimentScheduleCourseTableDetailTeacher experimentScheduleCourseTableDetailTeacher :
                experimentScheduleCourseTableDetailTeacherList) {
            ExperimentScheduleCourseTableDetailTeacherVo experimentScheduleCourseTableDetailTeacherVo =
                    getExperimentScheduleCourseTableDetailTeacherVo(experimentScheduleCourseTableDetailTeacher);
            experimentScheduleCourseTableDetailTeacherVoList.add(experimentScheduleCourseTableDetailTeacherVo);
        }
        experimentScheduleCourseTableDetailInfoVo.setExperimentScheduleCourseTableDetailTeacherList(
                experimentScheduleCourseTableDetailTeacherVoList);
        List<ExperimentScheduleCourseTableDetailRoomVo> experimentScheduleCourseTableDetailRoomVoList = new ArrayList<>();
        List<ExperimentScheduleCourseTableDetailRoomUser> experimentScheduleCourseTableDetailRoomList =
                experimentScheduleCourseTableDetail.getCourseTableDetailRoomUserList();
        for (ExperimentScheduleCourseTableDetailRoomUser experimentScheduleCourseTableDetailRoomUser :
                experimentScheduleCourseTableDetailRoomList) {
            ExperimentScheduleCourseTableDetailRoomVo experimentScheduleCourseTableDetailRoomVo =
                    getExperimentScheduleCourseTableDetailRoomVo(experimentScheduleCourseTableDetailRoomUser);
            experimentScheduleCourseTableDetailRoomVoList.add(experimentScheduleCourseTableDetailRoomVo);
        }
        experimentScheduleCourseTableDetailInfoVo.setExperimentScheduleCourseTableDetailRoomList(
                experimentScheduleCourseTableDetailRoomVoList);
        List<ExperimentScheduleCourseTableDetailProjectVo> experimentScheduleCourseTableDetailProjectVoList = new ArrayList<>();
        List<ExperimentScheduleCourseTableDetailProject> experimentScheduleCourseTableDetailProjectList =
                experimentScheduleCourseTableDetail.getCourseTableDetailProjects();
        for (ExperimentScheduleCourseTableDetailProject experimentScheduleCourseTableDetailProject :
                experimentScheduleCourseTableDetailProjectList) {
            ExperimentScheduleCourseTableDetailProjectVo experimentScheduleCourseTableDetailProjectVo =
                    getExperimentScheduleCourseTableDetailProjectVo(experimentScheduleCourseTableDetailProject);
            experimentScheduleCourseTableDetailProjectVoList.add(experimentScheduleCourseTableDetailProjectVo);
        }
        experimentScheduleCourseTableDetailInfoVo.setExperimentScheduleCourseTableDetailProjectList(
                experimentScheduleCourseTableDetailProjectVoList);
        return experimentScheduleCourseTableDetailInfoVo;
    }

    private static ExperimentScheduleCourseTableDetailProjectVo getExperimentScheduleCourseTableDetailProjectVo(
            ExperimentScheduleCourseTableDetailProject experimentScheduleCourseTableDetailProject) {
        ExperimentScheduleCourseTableDetailProjectVo experimentScheduleCourseTableDetailProjectVo =
                new ExperimentScheduleCourseTableDetailProjectVo();
        experimentScheduleCourseTableDetailProjectVo.setProjectId(experimentScheduleCourseTableDetailProject.getProjectId());
        experimentScheduleCourseTableDetailProjectVo.setProjectCode(experimentScheduleCourseTableDetailProject.getProjectCode());
        experimentScheduleCourseTableDetailProjectVo.setProjectName(experimentScheduleCourseTableDetailProject.getProjectName());
        return experimentScheduleCourseTableDetailProjectVo;
    }

    private static ExperimentScheduleCourseTableDetailRoomVo getExperimentScheduleCourseTableDetailRoomVo(
            ExperimentScheduleCourseTableDetailRoomUser experimentScheduleCourseTableDetailRoomUser) {
        ExperimentScheduleCourseTableDetailRoomVo experimentScheduleCourseTableDetailRoomVo =
                new ExperimentScheduleCourseTableDetailRoomVo();
        experimentScheduleCourseTableDetailRoomVo.setRoomId(experimentScheduleCourseTableDetailRoomUser.getRoomId());
        experimentScheduleCourseTableDetailRoomVo.setRoomCode(experimentScheduleCourseTableDetailRoomUser.getRoomCode());
        experimentScheduleCourseTableDetailRoomVo.setRoomName(experimentScheduleCourseTableDetailRoomUser.getRoomName());
        experimentScheduleCourseTableDetailRoomVo.setLabStudentCount(experimentScheduleCourseTableDetailRoomUser.getPersonnelNumber());
        experimentScheduleCourseTableDetailRoomVo.setItemTeacherId(experimentScheduleCourseTableDetailRoomUser.getItemTeacherId());
        experimentScheduleCourseTableDetailRoomVo.setItemTeacherNo(experimentScheduleCourseTableDetailRoomUser.getItemTeacherNo());
        experimentScheduleCourseTableDetailRoomVo.setItemTeacherName(experimentScheduleCourseTableDetailRoomUser.getItemTeacherName());
        return experimentScheduleCourseTableDetailRoomVo;
    }

    private static ExperimentScheduleCourseTableDetailTeacherVo getExperimentScheduleCourseTableDetailTeacherVo(
            ExperimentScheduleCourseTableDetailTeacher experimentScheduleCourseTableDetailTeacher) {
        ExperimentScheduleCourseTableDetailTeacherVo experimentScheduleCourseTableDetailTeacherVo =
                new ExperimentScheduleCourseTableDetailTeacherVo();
        experimentScheduleCourseTableDetailTeacherVo.setTeacherId(experimentScheduleCourseTableDetailTeacher.getTeacherId());
        experimentScheduleCourseTableDetailTeacherVo.setTeacherNo(experimentScheduleCourseTableDetailTeacher.getTeacherNo());
        experimentScheduleCourseTableDetailTeacherVo.setTeacherName(experimentScheduleCourseTableDetailTeacher.getTeacherName());
        experimentScheduleCourseTableDetailTeacherVo.setTeacherCollegeId(
                experimentScheduleCourseTableDetailTeacher.getTeacherCollegeId());
        experimentScheduleCourseTableDetailTeacherVo.setTeacherCollegeName(
                experimentScheduleCourseTableDetailTeacher.getTeacherCollegeName());
        return experimentScheduleCourseTableDetailTeacherVo;
    }

    @Transactional
    public void cancelExperimentScheduleCourseTableDetailById(
            CourseTableDetail courseTableDetail, ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            String operatorId, String operatorName,
            CancelExperimentScheduleCourseTableDetailModel cancelExperimentScheduleCourseTableDetailModel, Integer operatorType) {
        deleteExperimentScheduleCourseTableDetailRelevantData(experimentScheduleCourseTableDetail);
        deleteCourseTableDetailRelevantData(courseTableDetail);
        ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail =
                experimentOriginalCourseTableDetailRepository.findById(
                        experimentScheduleCourseTableDetail.getExperimentOriginalCourseTableDetailId()).get();
        List<ExperimentScheduleCourseTableDetailTeacher> experimentScheduleCourseTableDetailTeacherList = new ArrayList<>();
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();
        for (ExperimentOriginalCourseTableDetailTeacher experimentOriginalCourseTableDetailTeacher :
                experimentOriginalCourseTableDetail.getCourseTableDetailTeacherList()) {
            ExperimentScheduleCourseTableDetailTeacher experimentScheduleCourseTableDetailTeacher =
                    saveExperimentScheduleCourseTableDetailTeacher(
                            experimentScheduleCourseTableDetail, experimentOriginalCourseTableDetailTeacher);
            experimentScheduleCourseTableDetailTeacherList.add(experimentScheduleCourseTableDetailTeacher);
            CourseTableDetailTeacher courseTableDetailTeacher =
                    savaCourseTableDetailTeacher(courseTableDetail, experimentOriginalCourseTableDetailTeacher);
            courseTableDetailTeacherList.add(courseTableDetailTeacher);
        }
        experimentScheduleCourseTableDetailTeacherList =
                experimentScheduleCourseTableDetailTeacherRepository.saveAll(experimentScheduleCourseTableDetailTeacherList);
        courseTableDetailTeacherList = courseTableDetailTeacherRepository.saveAll(courseTableDetailTeacherList);
        List<ExperimentScheduleCourseTableDetailRoomUser> experimentScheduleCourseTableDetailRoomUserList = new ArrayList<>();
        List<CourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();
        for (ExperimentOriginalCourseTableDetailRoomUser experimentOriginalCourseTableDetailRoomUser :
                experimentOriginalCourseTableDetail.getCourseTableDetailRoomUserList()) {
            ExperimentScheduleCourseTableDetailRoomUser experimentScheduleCourseTableDetailRoomUser =
                    saveExperimentScheduleCourseTableDetailRoomUser(experimentScheduleCourseTableDetail,
                            experimentOriginalCourseTableDetailRoomUser);
            experimentScheduleCourseTableDetailRoomUserList.add(experimentScheduleCourseTableDetailRoomUser);
            CourseTableDetailRoomUser courseTableDetailRoomUser =
                    saveCourseTableDetailRoomUser(courseTableDetail, experimentOriginalCourseTableDetailRoomUser);
            courseTableDetailRoomUserList.add(courseTableDetailRoomUser);
        }
        experimentScheduleCourseTableDetailRoomUserList =
                experimentScheduleCourseTableDetailRoomUserRepository.saveAll(experimentScheduleCourseTableDetailRoomUserList);
        courseTableDetailRoomUserList = courseTableDetailRoomUserRepository.saveAll(courseTableDetailRoomUserList);
        List<ExperimentScheduleCourseTableDetailProject> experimentScheduleCourseTableDetailProjectList = new ArrayList<>();
        List<CourseTableDetailProject> courseTableDetailProjectList = new ArrayList<>();
        for (ExperimentOriginalCourseTableDetailProject experimentOriginalCourseTableDetailProject :
                experimentOriginalCourseTableDetail.getCourseTableDetailProjects()) {
            ExperimentScheduleCourseTableDetailProject experimentScheduleCourseTableDetailProject =
                    saveExperimentScheduleCourseTableDetailProject(experimentScheduleCourseTableDetail, experimentOriginalCourseTableDetailProject);
            experimentScheduleCourseTableDetailProjectList.add(experimentScheduleCourseTableDetailProject);
            CourseTableDetailProject courseTableDetailProject =
                    saveCourseTableDetailProject(courseTableDetail, experimentOriginalCourseTableDetailProject);
            courseTableDetailProjectList.add(courseTableDetailProject);
        }
        experimentScheduleCourseTableDetailProjectList =
                experimentScheduleCourseTableDetailProjectRepository.saveAll(experimentScheduleCourseTableDetailProjectList);
        courseTableDetailProjectList = courseTableDetailProjectRepository.saveAll(courseTableDetailProjectList);
        List<ExperimentScheduleCourseTableDetailSegment> experimentScheduleCourseTableDetailSegmentList = new ArrayList<>();
        List<CourseSegment> courseSegmentList = new ArrayList<>();
        for (ExperimentOriginalCourseTableDetailSegment experimentOriginalCourseTableDetailSegment :
                experimentOriginalCourseTableDetail.getCourseSegmentList()) {
            ExperimentScheduleCourseTableDetailSegment experimentScheduleCourseTableDetailSegment = new ExperimentScheduleCourseTableDetailSegment();
            experimentScheduleCourseTableDetailSegment.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
            experimentScheduleCourseTableDetailSegment.setSegment(experimentOriginalCourseTableDetailSegment.getSegment());
            experimentScheduleCourseTableDetailSegmentList.add(experimentScheduleCourseTableDetailSegment);
            CourseSegment courseSegment = new CourseSegment();
            courseSegment.setCourseTableDetail(courseTableDetail);
            courseSegment.setSegment(experimentOriginalCourseTableDetailSegment.getSegment());
            courseSegmentList.add(courseSegment);
        }
        experimentScheduleCourseTableDetailSegmentList =
                experimentScheduleCourseTableDetailSegmentRepository.saveAll(experimentScheduleCourseTableDetailSegmentList);
        courseSegmentList = courseSegmentRepository.saveAll(courseSegmentList);
        updateExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail, operatorId, operatorName,
                experimentOriginalCourseTableDetail);
        updateExperimentScheduleCourseTableDetailRelevantData(
                experimentScheduleCourseTableDetail, experimentScheduleCourseTableDetailTeacherList,
                experimentScheduleCourseTableDetailRoomUserList, experimentScheduleCourseTableDetailProjectList,
                experimentScheduleCourseTableDetailSegmentList);
        experimentScheduleCourseTableDetailRepository.saveAndFlush(experimentScheduleCourseTableDetail);
        if (operatorType == null || operatorType.equals(0)) {
            //记录取消排课操作日志
            experimentScheduleCourseLogService
                    .createLog(experimentScheduleCourseTableDetail.getId(), experimentScheduleCourseTableDetail.getModifierId(),
                            experimentScheduleCourseTableDetail.getModifierName(), "取消排课");
        }
        updateCourseTableDetail(courseTableDetail, experimentOriginalCourseTableDetail);
        updateCourseTableDetailRelevantData(courseTableDetail, courseTableDetailTeacherList, courseTableDetailRoomUserList,
                courseTableDetailProjectList, courseSegmentList);
        courseTableDetailRepository.saveAndFlush(courseTableDetail);
        cancelExperimentScheduleCourseTableDetailModel.setCancelStatus("200");
        cancelExperimentScheduleCourseTableDetailModel.setCancelStatusRemark("成功");
    }

    private static void updateExperimentScheduleCourseTableDetailRelevantData(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            List<ExperimentScheduleCourseTableDetailTeacher> experimentScheduleCourseTableDetailTeacherList,
            List<ExperimentScheduleCourseTableDetailRoomUser> experimentScheduleCourseTableDetailRoomUserList,
            List<ExperimentScheduleCourseTableDetailProject> experimentScheduleCourseTableDetailProjectList,
            List<ExperimentScheduleCourseTableDetailSegment> experimentScheduleCourseTableDetailSegmentList) {
        experimentScheduleCourseTableDetail.setCourseTableDetailTeacherList(experimentScheduleCourseTableDetailTeacherList);
        experimentScheduleCourseTableDetail.setCourseTableDetailRoomUserList(experimentScheduleCourseTableDetailRoomUserList);
        experimentScheduleCourseTableDetail.setCourseTableDetailProjects(experimentScheduleCourseTableDetailProjectList);
        experimentScheduleCourseTableDetail.setCourseSegmentList(experimentScheduleCourseTableDetailSegmentList);
    }

    private void updateCourseTableDetailRelevantData(
            CourseTableDetail courseTableDetail, List<CourseTableDetailTeacher> courseTableDetailTeacherList,
            List<CourseTableDetailRoomUser> courseTableDetailRoomUserList,
            List<CourseTableDetailProject> courseTableDetailProjectList, List<CourseSegment> courseSegmentList) {
        courseTableDetail.setCourseTableDetailTeacherList(courseTableDetailTeacherList);
        courseTableDetail.setCourseTableDetailRoomUserList(courseTableDetailRoomUserList);
        courseTableDetail.setCourseTableDetailProjects(courseTableDetailProjectList);
        courseTableDetail.setCourseSegmentList(courseSegmentList);
    }

    private static CourseTableDetailProject saveCourseTableDetailProject(
            CourseTableDetail courseTableDetail, ExperimentOriginalCourseTableDetailProject experimentOriginalCourseTableDetailProject) {
        CourseTableDetailProject courseTableDetailProject = new CourseTableDetailProject();
        courseTableDetailProject.setProjectId(experimentOriginalCourseTableDetailProject.getProjectId());
        courseTableDetailProject.setProjectCode(experimentOriginalCourseTableDetailProject.getProjectCode());
        courseTableDetailProject.setProjectName(experimentOriginalCourseTableDetailProject.getProjectName());
        courseTableDetailProject.setCourseTableDetail(courseTableDetail);
        return courseTableDetailProject;
    }

    private static CourseTableDetailRoomUser saveCourseTableDetailRoomUser(
            CourseTableDetail courseTableDetail, ExperimentOriginalCourseTableDetailRoomUser experimentOriginalCourseTableDetailRoomUser) {
        CourseTableDetailRoomUser courseTableDetailRoomUser = new CourseTableDetailRoomUser();
        courseTableDetailRoomUser.setRoomId(experimentOriginalCourseTableDetailRoomUser.getRoomId());
        courseTableDetailRoomUser.setRoomName(experimentOriginalCourseTableDetailRoomUser.getRoomName());
        courseTableDetailRoomUser.setRoomCode(experimentOriginalCourseTableDetailRoomUser.getRoomCode());
        courseTableDetailRoomUser.setPersonnelNumber(experimentOriginalCourseTableDetailRoomUser.getPersonnelNumber());
        courseTableDetailRoomUser.setRoomCategoryId(experimentOriginalCourseTableDetailRoomUser.getRoomCategoryId());
        courseTableDetailRoomUser.setCourseTableDetail(courseTableDetail);
        courseTableDetailRoomUser.setItemTeacherId(experimentOriginalCourseTableDetailRoomUser.getItemTeacherId());
        courseTableDetailRoomUser.setItemTeacherNo(experimentOriginalCourseTableDetailRoomUser.getItemTeacherNo());
        courseTableDetailRoomUser.setItemTeacherName(experimentOriginalCourseTableDetailRoomUser.getItemTeacherName());
        courseTableDetailRoomUser.setHasLive(experimentOriginalCourseTableDetailRoomUser.isHasLive());
        courseTableDetailRoomUser.setGroupId(experimentOriginalCourseTableDetailRoomUser.getGroupId());
        courseTableDetailRoomUser.setCreatorId(experimentOriginalCourseTableDetailRoomUser.getCreatorId());
        courseTableDetailRoomUser.setCreatorName(experimentOriginalCourseTableDetailRoomUser.getCreatorName());
        courseTableDetailRoomUser.setCreateTime(new Date());
        return courseTableDetailRoomUser;
    }

    private static CourseTableDetailTeacher savaCourseTableDetailTeacher(
            CourseTableDetail courseTableDetail, ExperimentOriginalCourseTableDetailTeacher experimentOriginalCourseTableDetailTeacher) {
        CourseTableDetailTeacher courseTableDetailTeacher = new CourseTableDetailTeacher();
        courseTableDetailTeacher.setTeacherId(experimentOriginalCourseTableDetailTeacher.getTeacherId());
        courseTableDetailTeacher.setTeacherNo(experimentOriginalCourseTableDetailTeacher.getTeacherNo());
        courseTableDetailTeacher.setTeacherName(experimentOriginalCourseTableDetailTeacher.getTeacherName());
        courseTableDetailTeacher.setTeacherCollegeId(experimentOriginalCourseTableDetailTeacher.getTeacherCollegeId());
        courseTableDetailTeacher.setTeacherCollegeName(experimentOriginalCourseTableDetailTeacher.getTeacherCollegeName());
        courseTableDetailTeacher.setTeacherTitle(experimentOriginalCourseTableDetailTeacher.getTeacherTitle());
        courseTableDetailTeacher.setCourseTableDetail(courseTableDetail);
        courseTableDetailTeacher.setCreatorId(experimentOriginalCourseTableDetailTeacher.getCreatorId());
        courseTableDetailTeacher.setCreatorName(experimentOriginalCourseTableDetailTeacher.getCreatorName());
        courseTableDetailTeacher.setCreateTime(new Date());
        return courseTableDetailTeacher;
    }

    private static ExperimentScheduleCourseTableDetailProject saveExperimentScheduleCourseTableDetailProject(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentOriginalCourseTableDetailProject experimentOriginalCourseTableDetailProject) {
        ExperimentScheduleCourseTableDetailProject experimentScheduleCourseTableDetailProject =
                new ExperimentScheduleCourseTableDetailProject();
        experimentScheduleCourseTableDetailProject.setProjectId(experimentOriginalCourseTableDetailProject.getProjectId());
        experimentScheduleCourseTableDetailProject.setProjectCode(experimentOriginalCourseTableDetailProject.getProjectCode());
        experimentScheduleCourseTableDetailProject.setProjectName(experimentOriginalCourseTableDetailProject.getProjectName());
        experimentScheduleCourseTableDetailProject.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
        return experimentScheduleCourseTableDetailProject;
    }

    private ExperimentScheduleCourseTableDetailRoomUser saveExperimentScheduleCourseTableDetailRoomUser(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentOriginalCourseTableDetailRoomUser experimentOriginalCourseTableDetailRoomUser) {
        ExperimentScheduleCourseTableDetailRoomUser experimentScheduleCourseTableDetailRoomUser =
                new ExperimentScheduleCourseTableDetailRoomUser();
        experimentScheduleCourseTableDetailRoomUser.setRoomId(experimentOriginalCourseTableDetailRoomUser.getRoomId());
        experimentScheduleCourseTableDetailRoomUser.setRoomName(experimentOriginalCourseTableDetailRoomUser.getRoomName());
        experimentScheduleCourseTableDetailRoomUser.setRoomCode(experimentOriginalCourseTableDetailRoomUser.getRoomCode());
        experimentScheduleCourseTableDetailRoomUser.setPersonnelNumber(experimentOriginalCourseTableDetailRoomUser.getPersonnelNumber());
        experimentScheduleCourseTableDetailRoomUser.setRoomCategoryId(experimentOriginalCourseTableDetailRoomUser.getRoomCategoryId());
        experimentScheduleCourseTableDetailRoomUser.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
        experimentScheduleCourseTableDetailRoomUser.setItemTeacherId(experimentOriginalCourseTableDetailRoomUser.getItemTeacherId());
        experimentScheduleCourseTableDetailRoomUser.setItemTeacherNo(experimentOriginalCourseTableDetailRoomUser.getItemTeacherNo());
        experimentScheduleCourseTableDetailRoomUser.setItemTeacherName(experimentOriginalCourseTableDetailRoomUser.getItemTeacherName());
        experimentScheduleCourseTableDetailRoomUser.setHasLive(experimentOriginalCourseTableDetailRoomUser.isHasLive());
        experimentScheduleCourseTableDetailRoomUser.setGroupId(experimentOriginalCourseTableDetailRoomUser.getGroupId());
        experimentScheduleCourseTableDetailRoomUser.setCreatorId(experimentOriginalCourseTableDetailRoomUser.getCreatorId());
        experimentScheduleCourseTableDetailRoomUser.setCreatorName(experimentOriginalCourseTableDetailRoomUser.getCreatorName());
        experimentScheduleCourseTableDetailRoomUser.setCreateTime(new Date());
        return experimentScheduleCourseTableDetailRoomUser;
    }

    private ExperimentScheduleCourseTableDetailTeacher saveExperimentScheduleCourseTableDetailTeacher(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            ExperimentOriginalCourseTableDetailTeacher experimentOriginalCourseTableDetailTeacher) {
        ExperimentScheduleCourseTableDetailTeacher experimentScheduleCourseTableDetailTeacher =
                new ExperimentScheduleCourseTableDetailTeacher();
        experimentScheduleCourseTableDetailTeacher.setTeacherId(experimentOriginalCourseTableDetailTeacher.getTeacherId());
        experimentScheduleCourseTableDetailTeacher.setTeacherNo(experimentOriginalCourseTableDetailTeacher.getTeacherNo());
        experimentScheduleCourseTableDetailTeacher.setTeacherName(experimentOriginalCourseTableDetailTeacher.getTeacherName());
        experimentScheduleCourseTableDetailTeacher.setTeacherCollegeId(experimentOriginalCourseTableDetailTeacher.getTeacherCollegeId());
        experimentScheduleCourseTableDetailTeacher.setTeacherCollegeName(experimentOriginalCourseTableDetailTeacher.getTeacherCollegeName());
        experimentScheduleCourseTableDetailTeacher.setTeacherTitle(experimentOriginalCourseTableDetailTeacher.getTeacherTitle());
        experimentScheduleCourseTableDetailTeacher.setExperimentScheduleCourseTableDetail(experimentScheduleCourseTableDetail);
        experimentScheduleCourseTableDetailTeacher.setCreatorId(experimentOriginalCourseTableDetailTeacher.getCreatorId());
        experimentScheduleCourseTableDetailTeacher.setCreatorName(experimentOriginalCourseTableDetailTeacher.getCreatorName());
        experimentScheduleCourseTableDetailTeacher.setCreateTime(new Date());
        return experimentScheduleCourseTableDetailTeacher;
    }

    private void updateExperimentScheduleCourseTableDetail(
            ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail,
            String operatorId, String operatorName, ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail) {
        experimentScheduleCourseTableDetail.setCourseDate(experimentOriginalCourseTableDetail.getCourseDate());
        experimentScheduleCourseTableDetail.setTeacherNames(experimentOriginalCourseTableDetail.getTeacherNames());
        experimentScheduleCourseTableDetail.setTeacherCollegeIds(experimentOriginalCourseTableDetail.getTeacherCollegeIds());
        experimentScheduleCourseTableDetail.setTeacherCollegeNames(experimentOriginalCourseTableDetail.getTeacherCollegeNames());
        experimentScheduleCourseTableDetail.setSegment(experimentOriginalCourseTableDetail.getSegment());
        experimentScheduleCourseTableDetail.setSegmentStartTime(experimentOriginalCourseTableDetail.getSegmentStartTime());
        experimentScheduleCourseTableDetail.setSegmentEndTime(experimentOriginalCourseTableDetail.getSegmentEndTime());
        experimentScheduleCourseTableDetail.setScheduleSource(
                ScheduleSource.getScheduleSource(experimentOriginalCourseTableDetail.getSource().getIndex()));
        experimentScheduleCourseTableDetail.setWeek(experimentOriginalCourseTableDetail.getWeek());
        experimentScheduleCourseTableDetail.setWeekNum(experimentOriginalCourseTableDetail.getWeekNum());
        experimentScheduleCourseTableDetail.setCourseKind(CourseKind.THEORY_COURSE);
        experimentScheduleCourseTableDetail.setScheduleStatus(ScheduleStatus.CANCEL_SCHEDULE);
        experimentScheduleCourseTableDetail.setCourseType(experimentOriginalCourseTableDetail.getCourseType());
        experimentScheduleCourseTableDetail.setEntranceGuardGeneratedStatus(EntranceGuardGeneratedStatus.NOT_GENERATED);
        experimentScheduleCourseTableDetail.setModifierId(operatorId);
        experimentScheduleCourseTableDetail.setModifierName(operatorName);
        experimentScheduleCourseTableDetail.setModifyTime(new Date());
    }

    private void updateCourseTableDetail(
            CourseTableDetail courseTableDetail, ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail) {
        courseTableDetail.setCourseDate(experimentOriginalCourseTableDetail.getCourseDate());
        courseTableDetail.setTeacherNames(experimentOriginalCourseTableDetail.getTeacherNames());
        courseTableDetail.setTeacherCollegeIds(experimentOriginalCourseTableDetail.getTeacherCollegeIds());
        courseTableDetail.setTeacherCollegeNames(experimentOriginalCourseTableDetail.getTeacherCollegeNames());
        courseTableDetail.setSegment(experimentOriginalCourseTableDetail.getSegment());
        courseTableDetail.setSegmentStartTime(experimentOriginalCourseTableDetail.getSegmentStartTime());
        courseTableDetail.setSegmentEndTime(experimentOriginalCourseTableDetail.getSegmentEndTime());
        courseTableDetail.setSource(experimentOriginalCourseTableDetail.getSource());
        courseTableDetail.setWeek(experimentOriginalCourseTableDetail.getWeek());
        courseTableDetail.setWeekNum(experimentOriginalCourseTableDetail.getWeekNum());
        courseTableDetail.setCourseKind(CourseKind.THEORY_COURSE);
        courseTableDetail.setCourseType(experimentOriginalCourseTableDetail.getCourseType());
    }

    public void checkExperimentScheduleCourseTableDetailById(
            String courseTableDetailId,
            CheckExperimentScheduleCourseTableDetailParam checkExperimentScheduleCourseTableDetailParam,
            CheckExperimentScheduleCourseTableDetailModel checkExperimentScheduleCourseTableDetailModel) {
        String courseDate = checkExperimentScheduleCourseTableDetailParam.getCourseDate();
        List<String> roomIdList = checkExperimentScheduleCourseTableDetailParam.getRoomIdList();
        List<Integer> segmentList = generateSegments(checkExperimentScheduleCourseTableDetailParam.getStartSegment(),
                checkExperimentScheduleCourseTableDetailParam.getEndSegment());
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = "select DISTINCT  ctdru.room_id as roomId,ctdru.room_code as roomCode," +
                "ctdru.room_name as roomName from tb_course_table_detail ctd  " +
                "INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                "INNER JOIN tb_course_segment cs on ctd.id =cs.course_table_detail_id " +
                "where  ctd.course_date = :courseDate AND ctd.id != :courseTableDetailId  " +
                "and ctdru.room_id in ( :roomIdList) and cs.segment IN ( :segmentList) ORDER BY ctdru.room_id; ";
        paramMap.put("courseDate", courseDate);
        paramMap.put("courseTableDetailId", courseTableDetailId);
        paramMap.put("roomIdList", roomIdList);
        paramMap.put("segmentList", segmentList);
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailRoomSqlVo.class));
        List<CourseTableDetailRoomSqlVo> courseTableDetailRoomSqlVoList = query.getResultList();
        if (!CollectionUtils.isEmpty(courseTableDetailRoomSqlVoList)) {
            String roomNames = courseTableDetailRoomSqlVoList.stream()
                    .map(CourseTableDetailRoomSqlVo::getRoomName).collect(joining("、"));
            checkExperimentScheduleCourseTableDetailModel.setCheckStatus(0);
            checkExperimentScheduleCourseTableDetailModel.setCheckRemark(roomNames + "已被排课");
        } else {
            checkExperimentScheduleCourseTableDetailModel.setCheckStatus(Constant.ONE);
            checkExperimentScheduleCourseTableDetailModel.setCheckRemark("没有教室被排课");
        }
    }

    public List<Integer> generateSegments(int startSegment, int endSegment) {
        List<Integer> segments = new ArrayList<>();
        for (int i = startSegment; i <= endSegment; i++) {
            segments.add(i);
        }
        return segments;
    }

    public List<RoomSegmentTimeVo> getRoomSegmentTimeVoList(Integer startSegment, Integer endSegment, List<String> roomIdList) {
        List<RoomSegmentTimeVo> roomSegmentTimeVoList = new ArrayList<>();
        List<ClassroomAndFloorResource> classroomAndFloorResources = classRoomService.getClassroomFloorInfo(roomIdList);
        List<Integer> segmentList = generateSegmentList(startSegment, endSegment);
        for (ClassroomAndFloorResource classroomAndFloorResource : classroomAndFloorResources) {
            RoomSegmentTimeVo roomSegmentTimeVo = new RoomSegmentTimeVo();
            roomSegmentTimeVo.setRoomId(classroomAndFloorResource.getRoomId());
            roomSegmentTimeVo.setRoomName(classroomAndFloorResource.getRoomName());
            roomSegmentTimeVo.setStartSegment(startSegment);
            roomSegmentTimeVo.setEndSegment(endSegment);
            String buildId = "0";
            if (CollectionUtils.isNotEmpty(classroomAndFloorResources)) {
                buildId = classroomAndFloorResource.getBuildId();
            }
            List<Segment> dataSegmentList = segmentRepository.findByBuildIdOrderByBuildName(buildId);
            List<Segment> effectiveSegmentList = new ArrayList<>();
            String minStartTimeStr = null;
            String maxEndTimeStr = null;
            segmentList.forEach(segmentName -> {
                Segment segment = dataSegmentList.stream().filter(s -> s.getSegment().equals(segmentName))
                        .findFirst().orElse(null);
                if (Objects.nonNull(segment)) {
                    effectiveSegmentList.add(segment);
                }
            });
            for (Segment segment : effectiveSegmentList) {
                if (minStartTimeStr == null || segment.getStartTime().compareTo(minStartTimeStr) < 0) {
                    minStartTimeStr = segment.getStartTime();
                }
                if (maxEndTimeStr == null || segment.getEndTime().compareTo(maxEndTimeStr) > 0) {
                    maxEndTimeStr = segment.getEndTime();
                }
            }
            Date minStartTime = DateUtils.stringToDate(DateUtils.TIME, minStartTimeStr);
            Date maxEndTime = DateUtils.stringToDate(DateUtils.TIME, maxEndTimeStr);
            roomSegmentTimeVo.setSegmentStartTime(DateUtils.formatDate(DateUtils.TIME_SECOND, minStartTime));
            roomSegmentTimeVo.setSegmentEndTime(DateUtils.formatDate(DateUtils.TIME_SECOND, maxEndTime));
            roomSegmentTimeVoList.add(roomSegmentTimeVo);
        }
        return roomSegmentTimeVoList;
    }
}

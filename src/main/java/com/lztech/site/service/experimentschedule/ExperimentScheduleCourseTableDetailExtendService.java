package com.lztech.site.service.experimentschedule;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.lztech.domain.model.experimentoriginalcoursetabledetail.ExperimentOriginalCourseTableDetail;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetail;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetailProject;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetailRoomUser;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.ExperimentScheduleCourseTableDetailTeacher;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.ScheduleSource;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.ScheduleStatus;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.persistence.repositories.experimentoriginalcoursetabledetail.*;
import com.lztech.persistence.repositories.experimentschedulelcoursetabledetail.*;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.easyexcel.CustomCellWriteHeightHandler;
import com.lztech.site.viewmodel.experimentschedule.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExperimentScheduleCourseTableDetailExtendService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentScheduleCourseTableDetailExtendService.class);

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ExperimentScheduleCourseTableDetailTeacherRepository teacherRepository;
    @Autowired
    private ExperimentScheduleCourseTableDetailRoomUserRepository roomUserRepository;
    @Autowired
    private ExperimentScheduleCourseTableDetailProjectRepository projectRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private ExperimentScheduleCourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private ExperimentScheduleCourseTableDetailSegmentRepository segmentRepository;

    @Autowired
    private ExperimentOriginalCourseTableDetailRepository originalCourseTableDetailRepository;
    @Autowired
    private ExperimentOriginalCourseTableDetailTeacherRepository originalCourseTableDetailTeacherRepository;
    @Autowired
    private ExperimentOriginalCourseTableDetailRoomUserRepository originalCourseTableDetailRoomUserRepository;
    @Autowired
    private ExperimentOriginalCourseTableDetailProjectRepository originalCourseTableDetailProjectRepository;
    @Autowired
    private ExperimentOriginalCourseTableDetailSegmentRepository originalCourseTableDetailSegmentRepository;

    public ExperimentScheduleCourseTableDetailPageVo getExperimentScheduleCourseTableDetails(
            ExperimentScheduleCourseTableDetailQueryVo queryParam) {
        ExperimentScheduleCourseTableDetailPageVo pageVo = new ExperimentScheduleCourseTableDetailPageVo();
        pageVo.setPage(queryParam.getPage());
        pageVo.setPageSize(queryParam.getPageSize());
        Integer totalNumber = getExperimentScheduleCourseTableDetailQueryCount(queryParam);
        pageVo.setTotal(totalNumber);
        if (totalNumber == 0) {
            pageVo.setTotalPage(0);
            pageVo.setCourseTableDetails(new ArrayList<>());
        } else {
            pageVo.setTotalPage((int) Math.ceil(totalNumber * 1.0 / queryParam.getPageSize()));
            pageVo.setCourseTableDetails(getExperimentScheduleCourseTableDetailQueryList(queryParam));
        }

        return pageVo;
    }

    private Integer getExperimentScheduleCourseTableDetailQueryCount(
            ExperimentScheduleCourseTableDetailQueryVo queryParam) {
        int totalNumber = 0;

        Map<String, Object> paramMap = new HashMap<>();
        String sql = getExperimentScheduleCourseTableDetailQuerySql(queryParam, paramMap, true);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) query.getResultList().get(0);
        totalNumber = Integer.parseInt(resMap.get("totalNumber").toString());

        return totalNumber;
    }

    private List<ExperimentScheduleCourseTableDetailVo> getExperimentScheduleCourseTableDetailQueryList(
            ExperimentScheduleCourseTableDetailQueryVo queryParam) {
        List<ExperimentScheduleCourseTableDetailVo> courseTableDetailVos = new ArrayList<>();

        Map<String, Object> paramMap = new HashMap<>();
        String sql = getExperimentScheduleCourseTableDetailQuerySql(queryParam, paramMap, false);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> resMapList = query.getResultList();
        for (Map<String, Object> resMap : resMapList) {
            ExperimentScheduleCourseTableDetailVo courseTableDetailVo = new ExperimentScheduleCourseTableDetailVo();
            courseTableDetailVo.setId(resMap.get("id").toString());
            courseTableDetailVo.setWeek(resMap.get("week").toString());
            courseTableDetailVo.setDayOfWeek(DateUtils.dateToWeek(DateUtils.stringToDate(DateUtils.DATE, resMap.get("courseDate").toString())));
            courseTableDetailVo.setCourseId(resMap.get("courseId").toString());
            courseTableDetailVo.setCourseName(resMap.get("courseName").toString());
            courseTableDetailVo.setCourseCode(resMap.get("courseCode").toString());
            courseTableDetailVo.setClassId(resMap.get("groupId").toString());
            courseTableDetailVo.setClassName(resMap.get("groupName").toString());
            courseTableDetailVo.setCourseDate(DateUtils.formatDate(DateUtils.DATE,
                    DateUtils.stringToDate(DateUtils.DATE, resMap.get("courseDate").toString())));
            courseTableDetailVo.setSegment(resMap.get("segment").toString());
            courseTableDetailVo.setScheduleSource(Integer.parseInt(resMap.get("scheduleSource").toString()));
            courseTableDetailVo.setScheduleSourceName(ScheduleSource.getScheduleSource(courseTableDetailVo.getScheduleSource()).getName());
            courseTableDetailVo.setScheduleStatus(Integer.parseInt(resMap.get("scheduleStatus").toString()));
            courseTableDetailVo.setScheduleStatusName(ScheduleStatus.getScheduleStatus(courseTableDetailVo.getScheduleStatus()).getName());
            courseTableDetailVo.setOperatorName(resMap.get("operatorName").toString());
            courseTableDetailVo.setExpire(Integer.parseInt(resMap.get("expire").toString()));
            courseTableDetailVo.setProjectIds("");
            courseTableDetailVo.setProjectNames("");
            courseTableDetailVo.setTeacherIds("");
            courseTableDetailVo.setTeacherNames("");
            courseTableDetailVo.setRoomIds("");
            courseTableDetailVo.setRoomNames("");
            courseTableDetailVo.setModifyTime(DateUtils.formatDate(DateUtils.DATE_TIME,
                    DateUtils.stringToDate(DateUtils.DATE_TIME, resMap.get("modifyTime").toString())));
            courseTableDetailVo.setCollegeName(resMap.get("collegeName").toString());
            courseTableDetailVos.add(courseTableDetailVo);
        }

        setExperimentScheduleCourseTableDetailInfo(courseTableDetailVos);
        return courseTableDetailVos;
    }

    private void setExperimentScheduleCourseTableDetailInfo(List<ExperimentScheduleCourseTableDetailVo> courseTableDetailVos) {

        List<String> idList = courseTableDetailVos.stream().map(ExperimentScheduleCourseTableDetailVo::getId).collect(Collectors.toList());

        List<ExperimentScheduleCourseTableDetailTeacher> teachers =
                teacherRepository.findAllByExperimentScheduleCourseTableDetailIdIn(idList);
        List<ExperimentScheduleCourseTableDetailProject> projects =
                projectRepository.findAllByExperimentScheduleCourseTableDetailIdIn(idList);
        List<ExperimentScheduleCourseTableDetailRoomUser> roomUsers =
                roomUserRepository.findAllByExperimentScheduleCourseTableDetailIdIn(idList);

        courseTableDetailVos.forEach(courseTableDetailVo -> {
            List<ExperimentScheduleCourseTableDetailProject> filterProjects = projects.stream().filter(project ->
                    project.getExperimentScheduleCourseTableDetail().getId().equals(courseTableDetailVo.getId())).collect(Collectors.toList());
            if (!filterProjects.isEmpty()) {
                courseTableDetailVo.setProjectIds(filterProjects.stream()
                        .map(ExperimentScheduleCourseTableDetailProject::getProjectId).collect(Collectors.joining(",")));
                courseTableDetailVo.setProjectNames(filterProjects.stream()
                        .map(ExperimentScheduleCourseTableDetailProject::getProjectName).collect(Collectors.joining(",")));
            }

            courseTableDetailVo.setClassStudentNumber(
                    groupMemberRepository.countByGroupIdAndGroupMemberStatus(courseTableDetailVo.getClassId(), GroupMemberStatus.NORMAL));

            List<ExperimentScheduleCourseTableDetailTeacher> filterTeachers = teachers.stream().filter(teacher ->
                    teacher.getExperimentScheduleCourseTableDetail().getId().equals(courseTableDetailVo.getId())).collect(Collectors.toList());
            if (!filterTeachers.isEmpty()) {
                courseTableDetailVo.setTeacherIds(filterTeachers.stream()
                        .map(ExperimentScheduleCourseTableDetailTeacher::getTeacherId).collect(Collectors.joining(",")));
                courseTableDetailVo.setTeacherNames(filterTeachers.stream()
                        .map(ExperimentScheduleCourseTableDetailTeacher::getTeacherName).collect(Collectors.joining(",")));
            }

            List<ExperimentScheduleCourseTableDetailRoomUser> filterRoomUsers = roomUsers.stream().filter(roomUser ->
                    roomUser.getExperimentScheduleCourseTableDetail().getId().equals(courseTableDetailVo.getId())).collect(Collectors.toList());
            if (!filterRoomUsers.isEmpty()) {
                courseTableDetailVo.setRoomIds(filterRoomUsers.stream()
                        .map(ExperimentScheduleCourseTableDetailRoomUser::getRoomId)
                        .filter(StringUtils::isNotEmpty).collect(Collectors.joining(",")));
                courseTableDetailVo.setRoomNames(filterRoomUsers.stream()
                        .map(ExperimentScheduleCourseTableDetailRoomUser::getRoomName)
                        .filter(StringUtils::isNotEmpty).collect(Collectors.joining(",")));
            }
        });
    }


    private String getExperimentScheduleCourseTableDetailQuerySql(ExperimentScheduleCourseTableDetailQueryVo queryParam,
                                                                  Map<String, Object> paramMap, Boolean isCount) {
        StringBuilder sb = new StringBuilder();
        if (isCount) {
            sb.append("select count(id) as totalNumber from (");
            sb.append("select distinct ctd.id as id," +
                    "if(ctd.schedule_status=0 and ctd.course_date<=CURRENT_DATE,2,schedule_status) as scheduleStatus");
        } else {
            sb.append("select * from (");
            sb.append("select distinct ctd.id,if(ctd.schedule_status=0 and ctd.course_date<=CURRENT_DATE,2,schedule_status) as scheduleStatus" +
                    ",if(ctd.course_date<=CURRENT_DATE,1,0) as expire, ctd.week as week,ctd.week_num as weekNum," +
                    "ctd.course_id as courseId,ctd.course_name as courseName,ctd.course_code as courseCode," +
                    "ctd.group_id as groupId,ctd.course_date as courseDate,ctd.segment as segment,ctd.schedule_source as scheduleSource," +
                    "ctd.modifier_name as operatorName,ctd.group_no as groupCode,ctd.segment_start_time as segmentEndTime," +
                    "ctd.group_name as groupName, ctd.modify_time AS modifyTime, ct.college_name AS collegeName ");
        }

        sb.append(" from tb_experiment_schedule_course_table_detail ctd "
                + " join tb_course_table ct on ct.id = ctd.course_table_id ");

        if (StringUtils.isNoneEmpty(queryParam.getRoomName())) {
            sb.append(" join tb_experiment_schedule_course_table_detail_room_user ctdru" +
                    " on ctdru.experiment_schedule_course_table_detail_id = ctd.id");
        }

        if (StringUtils.isNoneEmpty(queryParam.getTeacherId())) {
            sb.append(" join tb_experiment_schedule_course_table_detail_teacher ctdt " +
                    "on ctdt.experiment_schedule_course_table_detail_id = ctd.id");
        }

        sb.append(" where ctd.course_date>=:startDate and ctd.course_date<=:endDate");
        paramMap.put("startDate", queryParam.getStartDate());
        paramMap.put("endDate", queryParam.getEndDate());
        if (StringUtils.isNoneEmpty(queryParam.getCourseName())) {
            sb.append(" and ctd.course_name like :courseName");
            paramMap.put("courseName", "%" + queryParam.getCourseName() + "%");
        }
        if (StringUtils.isNoneEmpty(queryParam.getClassName())) {
            sb.append(" and ctd.group_name like :className");
            paramMap.put("className", "%" + queryParam.getClassName() + "%");
        }
        if (StringUtils.isNoneEmpty(queryParam.getRoomName())) {
            sb.append(" and ctdru.room_name like :roomName");
            paramMap.put("roomName", "%" + queryParam.getRoomName() + "%");
        }
        if (StringUtils.isNoneEmpty(queryParam.getCourseId())) {
            sb.append(" and ctd.course_id=:courseId");
            paramMap.put("courseId", queryParam.getCourseId());
        }
        if (StringUtils.isNoneEmpty(queryParam.getClassId())) {
            sb.append(" and ctd.group_id=:classId");
            paramMap.put("classId", queryParam.getClassId());
        }
        if (StringUtils.isNoneEmpty(queryParam.getTeacherId())) {
            sb.append(" and ctdt.teacher_id=:teacherId");
            paramMap.put("teacherId", queryParam.getTeacherId());
        }
        if (StringUtils.isNoneEmpty(queryParam.getCollegeId())) {
            sb.append(" and ct.college_id=:collegeId");
            paramMap.put("collegeId", queryParam.getCollegeId());
        }
        sb.append(") a");
        if (StringUtils.isNoneEmpty(queryParam.getScheduleStatus())) {
            sb.append(" where a.scheduleStatus=:scheduleStatus");
            paramMap.put("scheduleStatus", queryParam.getScheduleStatus());
        }
        if (!isCount) {
            sb.append(" order by scheduleStatus asc,courseDate asc,segmentEndTime asc,groupCode asc");
            int start = (queryParam.getPage() - 1) * queryParam.getPageSize();
            sb.append(" limit ").append(start).append(",").append(queryParam.getPageSize());
        }
        return sb.toString();
    }


    public ExperimentScheduleCourseTableDetail findExperimentScheduleCourseTableDetailById(String id) {
        return courseTableDetailRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteExperimentScheduleCourseTableDetails(ExperimentScheduleCourseTableDetail courseTableDetail) {
        ExperimentOriginalCourseTableDetail originalCourseTableDetail =
                originalCourseTableDetailRepository.findById(courseTableDetail.getExperimentOriginalCourseTableDetailId()).orElse(null);
        if (originalCourseTableDetail != null) {
            originalCourseTableDetailRoomUserRepository.deleteAll(originalCourseTableDetail.getCourseTableDetailRoomUserList());
            originalCourseTableDetailProjectRepository.deleteAll(originalCourseTableDetail.getCourseTableDetailProjects());
            originalCourseTableDetailTeacherRepository.deleteAll(originalCourseTableDetail.getCourseTableDetailTeacherList());
            originalCourseTableDetailSegmentRepository.deleteAll(originalCourseTableDetail.getCourseSegmentList());
            originalCourseTableDetailRepository.delete(originalCourseTableDetail);
        }

        roomUserRepository.deleteAll(courseTableDetail.getCourseTableDetailRoomUserList());
        projectRepository.deleteAll(courseTableDetail.getCourseTableDetailProjects());
        teacherRepository.deleteAll(courseTableDetail.getCourseTableDetailTeacherList());
        segmentRepository.deleteAll(courseTableDetail.getCourseSegmentList());
        courseTableDetailRepository.delete(courseTableDetail);
    }


    public List<Map<String, Object>> getExportExperimentScheduleCourseTableDetails(
            ExperimentScheduleCourseTableDetailExportQueryVo queryParam) {
        StringBuilder sb = new StringBuilder();
        sb.append("select a.* from (" +
                "  SELECT distinct" +
                "    ctd.id," +
                "    IF(ctd.schedule_status = 0 AND ctd.course_date <= CURRENT_DATE, 2, schedule_status ) AS scheduleStatus," +
                "    IF(ctd.course_date <= CURRENT_DATE, 1, 0 ) AS expire," +
                "    ctd.week AS week," +
                "    ctd.week_num AS weekNum," +
                "    ctd.course_id AS courseId," +
                "    ctd.course_name AS courseName," +
                "    ctd.course_code AS courseCode," +
                "    ctd.group_id AS groupId," +
                "    ctd.course_date AS courseDate," +
                "    ctd.segment AS segment," +
                "    ctd.schedule_source AS scheduleSource," +
                "    ctd.modifier_name AS operatorName," +
                "    ctd.group_no AS groupCode," +
                "    ctd.segment_start_time AS segmentStartTime," +
                "    ctd.group_name AS groupName," +
                "    ct.college_id AS collegeId," +
                "    ct.college_name AS collegeName," +
                "    (select group_concat(ctdru.room_name order by ctdru.room_name)" +
                "    from tb_experiment_schedule_course_table_detail_room_user ctdru" +
                " where ctdru.experiment_schedule_course_table_detail_id = ctd.id) as roomNames," +
                "    (select group_concat(ctdp.project_name) " +
                "    from tb_experiment_schedule_course_table_detail_project ctdp" +
                " where ctdp.experiment_schedule_course_table_detail_id = ctd.id ) as projectNames," +
                "    (select group_concat(ctdt.teacher_name) " +
                "    from tb_experiment_schedule_course_table_detail_teacher ctdt" +
                " where ctdt.experiment_schedule_course_table_detail_id = ctd.id) as teacherNames ," +
                "    (select group_concat(ctdt.teacher_id) " +
                "    from tb_experiment_schedule_course_table_detail_teacher ctdt" +
                " where ctdt.experiment_schedule_course_table_detail_id = ctd.id) as teacherIds " +
                "  FROM" +
                "  tb_experiment_schedule_course_table_detail ctd " +
                "  join tb_course_table ct on ct.id = ctd.course_table_id " +
                " ) a  ");

        Map<String, Object> paramMap = new HashMap<>();
        sb.append(" where a.courseDate>=:startDate and a.courseDate<=:endDate");
        paramMap.put("startDate", queryParam.getStartDate());
        paramMap.put("endDate", queryParam.getEndDate());
        if (StringUtils.isNoneEmpty(queryParam.getCourseName())) {
            sb.append(" and a.courseName like :courseName");
            paramMap.put("courseName", "%" + queryParam.getCourseName() + "%");
        }
        if (StringUtils.isNoneEmpty(queryParam.getClassName())) {
            sb.append(" and a.groupName like :className");
            paramMap.put("className", "%" + queryParam.getClassName() + "%");
        }
        if (StringUtils.isNoneEmpty(queryParam.getRoomName())) {
            sb.append(" and a.roomNames like :roomName");
            paramMap.put("roomName", "%" + queryParam.getRoomName() + "%");
        }
        if (StringUtils.isNoneEmpty(queryParam.getScheduleStatus())) {
            sb.append(" and a.scheduleStatus=:scheduleStatus");
            paramMap.put("scheduleStatus", queryParam.getScheduleStatus());
        }
        if (StringUtils.isNoneEmpty(queryParam.getTeacherId())) {
            sb.append(" and a.teacherIds like :teacherIds");
            paramMap.put("teacherIds", "%" + queryParam.getTeacherId() + "%");
        }
        if (StringUtils.isNoneEmpty(queryParam.getCollegeId())) {
            sb.append(" and a.collegeId=:collegeId");
            paramMap.put("collegeId", queryParam.getCollegeId());
        }
        sb.append(" order by week,groupName,courseDate,segmentStartTime,roomNames");
        Query query = entityManager.createNativeQuery(sb.toString());
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> resMapList = query.getResultList();
        return resMapList;
    }

    public boolean exportExperimentScheduleCourseTableDetailsExcel(HttpServletResponse response,
                                                                   List<Map<String, Object>> exportData, String term) {
        boolean flag = false;

        try {
            List<ExperimentScheduleCourseTableDetailsExcelVo> excelVoList =
                    getExperimentScheduleCourseTableDetailExcelVoList(exportData);
            //表头策略
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            //设置头居中
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontName("宋体");
            headWriteFont.setFontHeightInPoints(Short.parseShort("12"));
            headWriteCellStyle.setWriteFont(headWriteFont);

            //内容策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            //设置 水平居中
            contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
            contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
            contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
            contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
            contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            contentWriteCellStyle.setWrapped(true);
            WriteFont contentWriteFont = new WriteFont();
            contentWriteFont.setFontName("宋体");
            contentWriteFont.setFontHeightInPoints(Short.parseShort("11"));
            contentWriteCellStyle.setWriteFont(contentWriteFont);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("实验课表_" + DateUtils.formatDate(DateUtils.DATE_HOUR_MINUTES_NOT_SEPARATOR, new Date()), "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(
                            response.getOutputStream(),
                            ExperimentScheduleCourseTableDetailsExcelVo.class
                    ).autoCloseStream(Boolean.FALSE)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .registerWriteHandler(new ExperimentScheduleCourseTableDetailsSheetHead(term + " 实验课程表"))
                    .registerWriteHandler(new CustomCellWriteHeightHandler())
                    .sheet(term + " 实验课程表")
                    .relativeHeadRowIndex(Constant.ONE)
                    .doWrite(excelVoList);
            flag = true;
        } catch (Exception e) {
            LOGGER.error("exportExperimentScheduleCourseTableDetailsExcel->", e);
        }

        return flag;
    }

    private List<ExperimentScheduleCourseTableDetailsExcelVo> getExperimentScheduleCourseTableDetailExcelVoList(
            List<Map<String, Object>> exportData) {
        List<ExperimentScheduleCourseTableDetailsExcelVo> excelVoList = new ArrayList<>();
        for (Map<String, Object> data : exportData) {
            ExperimentScheduleCourseTableDetailsExcelVo excelVo = new ExperimentScheduleCourseTableDetailsExcelVo();
            excelVo.setWeekNum(
                    ObjectUtils.isEmpty(data.get("week")) ? "-" : data.get("week").toString());
            excelVo.setCollegeName(
                    ObjectUtils.isEmpty(data.get("collegeName")) ? "-" : data.get("collegeName").toString());
            excelVo.setClassName(
                    ObjectUtils.isEmpty(data.get("groupName")) ? "-" : data.get("groupName").toString());
            excelVo.setCourseName(
                    ObjectUtils.isEmpty(data.get("courseName")) ? "-" : data.get("courseName").toString());
            excelVo.setProjectName(
                    ObjectUtils.isEmpty(data.get("projectNames")) ? "-" : data.get("projectNames").toString());
            excelVo.setTeacherName(
                    ObjectUtils.isEmpty(data.get("teacherNames")) ? "-" : data.get("teacherNames").toString());
            excelVo.setCourseDate(
                    ObjectUtils.isEmpty(data.get("courseDate")) ? "-" : data.get("courseDate").toString());
            excelVo.setWeek(
                    ObjectUtils.isEmpty(data.get("weekNum")) ? "-" :
                            DateUtils.dateToWeek(DateUtils.stringToDate(DateUtils.DATE, data.get("courseDate").toString())));
            excelVo.setSegment(
                    ObjectUtils.isEmpty(data.get("segment")) ? "-" : formatSegment(data.get("segment").toString()));
            excelVo.setRoomName(
                    ObjectUtils.isEmpty(data.get("roomNames")) ? "-" : data.get("roomNames").toString());
            excelVoList.add(excelVo);
        }

        return excelVoList;
    }

    private String formatSegment(String segment) {
        String result = "";

        String[] segments = segment.split(",");
        result = segments[0] + "-" + segments[segments.length - 1];

        return result;
    }

}

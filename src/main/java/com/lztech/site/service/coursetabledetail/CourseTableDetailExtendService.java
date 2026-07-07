package com.lztech.site.service.coursetabledetail;

import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.coursetable.OccupancyLabResource;
import com.lztech.site.viewmodel.coursetabledetail.CourseIdAndCollegeIdQuery;
import com.lztech.site.viewmodel.coursetabledetail.CourseTableDetailInClassResource;
import com.lztech.site.viewmodel.coursetabledetail.CourseTableDetailPageRequest;
import com.lztech.site.viewmodel.coursetabledetail.CourseTableDetailStatusResource;
import com.lztech.site.viewmodel.coursetabledetail.enums.CourseTableDetailStatus;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

import static com.lztech.site.constants.Constant.NEED_DISTINGUISH_COURSE_STUDENT_TYPE;
import static com.lztech.site.until.LzStringUtil.convertToQuotedString;
import static java.util.stream.Collectors.joining;

@Service
public class CourseTableDetailExtendService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;

    public List<OccupancyLabResource> getCourseTableDetailOccupancyLabList(
            String courseDate, String courseSegments) {
        String querySql = "SELECT distinct ctdru.room_id as roomId FROM tb_course_table_detail ctd" +
                " JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id" +
                " JOIN tb_course_table_detail_room_user ctdru ON ctd.id = ctdru.course_table_detail_id" +
                " WHERE ctdru.room_id is not null and ctd.course_date = :courseDate" +
                " and cs.segment IN (:segmentList)";

        List<String> segmentList = Arrays.asList(courseSegments.split(","));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseDate", courseDate);
        paramMap.put("segmentList", segmentList);
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(OccupancyLabResource.class));
        return (List<OccupancyLabResource>) query.getResultList();
    }

    public String createBaseSql(CourseTableDetailPageRequest courseTableDetailPageRequest, Map<String, Object> paramMap, String collegeId) {
        String sql = "SELECT ctd.id as id ,   c.course_name as courseName,   ctdru.room_id as roomId,   ctdru.room_name as roomName, " +
                " (select GROUP_CONCAT(teacher_name  order by show_order ) from tb_course_table_detail_teacher " +
                " where course_table_detail_id=ctd.id GROUP BY ctd.id  )" +
                " as teacherName,  ctd.course_kind as courseKind ,c.id as courseId," +
                " ct.college_id as collegeId,ct.college_name as collegeName," +
                " ct.student_type as studentType,ctd.source_data_source_name as sourceDataSourceName,ctd.source_data_source as sourceDataSource " +
                " FROM tb_course_table_detail ctd " +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                " INNER JOIN tb_course c ON ct.course_id = c.id " +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment where " +
                " c.use_state=1 and ctdru.has_live=1  " +
                " and (ctdru.room_id is not null or ctdru.room_id!='') ";
        if (StringUtils.isNotBlank(courseTableDetailPageRequest.getIsDistinguishCourseStudentType())
                && "1".equals(courseTableDetailPageRequest.getIsDistinguishCourseStudentType())) {
            if (StringUtils.isNotBlank(courseTableDetailPageRequest.getCourseStudentTypeIds())) {
                List<String> courseStudentTypeIdList = Arrays.asList(courseTableDetailPageRequest.getCourseStudentTypeIds().split(","));
                sql += " and ct.student_type in ( :courseStudentTypeIds) ";
                paramMap.put("courseStudentTypeIds", courseStudentTypeIdList);
            } else {
                sql += " and 1=0 ";
            }
        }

        if (!courseTableDetailPageRequest.getRoomIdList().isEmpty()) {
            sql += " and ctdru.room_id in (:roomIdList) ";
            paramMap.put("roomIdList", courseTableDetailPageRequest.getRoomIdList());
        }

        if (org.apache.commons.lang3.StringUtils.isNotBlank(courseTableDetailPageRequest.getCourseDate())) {
            sql += " and ctd.course_date= :courseDate ";
            paramMap.put("courseDate", courseTableDetailPageRequest.getCourseDate());
        }
        if (StringUtils.isNotBlank(courseTableDetailPageRequest.getSegment())) {
            sql += " and cs.segment= :segment ";
            paramMap.put("segment", courseTableDetailPageRequest.getSegment());
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(courseTableDetailPageRequest.getCourseIds())) {
            sql += " and (ct.course_id in ('')";
        } else {
            List<String> courseIdList = Arrays.asList(courseTableDetailPageRequest.getCourseIds().split(","));
            sql += " and (ct.course_id in (:courseIds)";
            paramMap.put("courseIds", courseIdList);
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(courseTableDetailPageRequest.getCollegeIds())) {
            sql += " or ct.college_id in (''))";
        } else {
            List<String> collegeIdList = Arrays.asList(courseTableDetailPageRequest.getCollegeIds().split(","));
            sql += " or ct.college_id in ( :collegeIds ))";
            paramMap.put("collegeIds", collegeIdList);
        }
        if (StringUtils.isNotBlank(collegeId)) {
            sql += " and ct.college_id = :collegeId";
            paramMap.put("collegeId", collegeId);
        }
        if (StringUtils.isNotEmpty(courseTableDetailPageRequest.getSourceDataSource())) {
            sql += " and ctd.source_data_source = :sourceDataSource";
            paramMap.put("sourceDataSource", courseTableDetailPageRequest.getSourceDataSource());
        }
        return sql;
    }

    public String createCourseTableDetailSql(
            String baseSql, CourseTableDetailPageRequest courseTableDetailPageRequest,
            String roomIdSortList, String collegeName, String collegeId) {
        String sql = "select * from (" + baseSql + ") a where 1=1";
        if (StringUtils.isNotBlank(courseTableDetailPageRequest.getSearchParams())) {
            String tempString = CharactersReplace.replaceCharacters(courseTableDetailPageRequest.getSearchParams());

            if (StringUtils.isNotBlank(tempString)) {
                sql += " and (a.courseName like :searchParam  or a.teacherName " +
                        "like :searchParam or a.roomName like :searchParam)";
            }
        } else {
            if (StringUtils.isNotBlank(courseTableDetailPageRequest.getSearchCourseName())) {
                sql += " and a.courseName like :searchCourseName";
            }
            if (StringUtils.isNotBlank(courseTableDetailPageRequest.getSearchRoomName())) {
                sql += " and a.roomName like :searchRoomName";
            }
            if (StringUtils.isNotBlank(courseTableDetailPageRequest.getSearchTeacherName())) {
                sql += " and a.teacherName like :searchTeacherName";
            }
        }
        if (StringUtils.isNotBlank(collegeId)) {
            sql += " and a.collegeId = :queryCollegeId ";
        }
        if (StringUtils.isNotBlank(collegeName)) {
            String tempString = CharactersReplace.replaceCharacters(collegeName);
            sql += " and a.collegeName like :queryCollegeName ";
        }
        int start = (courseTableDetailPageRequest.getPage() - 1) * courseTableDetailPageRequest.getPageSize();
        sql += " order by FIELD(roomId," + roomIdSortList + ") limit " + start + "," + courseTableDetailPageRequest.getPageSize();
        return sql;
    }

    public List<CourseTableDetailInClassResource> getCourseTableDetailInClassDetailList
            (String courseNameOrTeacherName, CourseIdAndCollegeIdQuery courseIdAndCollegeIdQuery) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT ctd.id  AS courseTableDetailId, c.id as courseId,c.course_name as courseName ," +
                " (SELECT COUNT(1) from tb_group_member gm where gm.group_id=ct.group_id and gm.group_member_status=0) AS studentCount, " +
                " ctd.teacher_names AS teacherName, " +
                " ctdru.room_name AS classroomName, " +
                " ctdru.room_id AS classroomId " +
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
                " and  ctdru.has_live=1 and ctdru.room_id is not null";
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
        sql += " ORDER BY ctdru.room_id ; ";
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public CourseTableDetailStatusResource getCourseTableDetailStatusById(String id, String roomId) {
        CourseTableDetailStatusResource courseTableDetailStatusResource = new CourseTableDetailStatusResource();
        courseTableDetailStatusResource.setCourseTableDetailId(id);
        courseTableDetailStatusResource.setHasLive(false);
        CourseTableDetail courseTableDetail = courseTableDetailRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(courseTableDetail)) {
            courseTableDetailStatusResource.setCourseTableDetailStatus(CourseTableDetailStatus.NOT_EXIST.getValue());
            return courseTableDetailStatusResource;
        }
        List<CourseTableDetailRoomUser> roomList = courseTableDetail.getCourseTableDetailRoomUserList();
        // 获取上课日期和今天比较，在今天之前是已结束，在今天之后是未开始
        LocalDate courseDate = courseTableDetail.getCourseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime startTime = courseTableDetail.getSegmentStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        LocalTime endTime = courseTableDetail.getSegmentEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        LocalTime currentTime = now.toLocalTime();
        // 主判断逻辑
        if (courseDate.isBefore(today)) {
            courseTableDetailStatusResource.setCourseTableDetailStatus(CourseTableDetailStatus.ENDED.getValue());
        } else if (courseDate.isAfter(today)) {
            courseTableDetailStatusResource.setCourseTableDetailStatus(CourseTableDetailStatus.NOT_STARTED.getValue());
        } else {
            if (currentTime.isBefore(startTime)) {
                courseTableDetailStatusResource.setCourseTableDetailStatus(CourseTableDetailStatus.NOT_STARTED.getValue());
            } else if (currentTime.isAfter(endTime)) {
                courseTableDetailStatusResource.setCourseTableDetailStatus(CourseTableDetailStatus.ENDED.getValue());
            } else {
                courseTableDetailStatusResource.setCourseTableDetailStatus(CourseTableDetailStatus.ONGOING.getValue());
                if (CollectionUtils.isEmpty(roomList)) {
                    courseTableDetailStatusResource.setHasLive(false);
                }
                CourseTableDetailRoomUser courseTableDetailRoomUser = roomList.get(0);
                if (StringUtils.isNotBlank(roomId)) {
                    courseTableDetailRoomUser = roomList.stream()
                            .filter(room -> room.getRoomId().equals(roomId)).findFirst().orElse(null);
                }
                if (ObjectUtils.isEmpty(courseTableDetailRoomUser)) {
                    courseTableDetailStatusResource.setHasLive(false);
                } else {
                    courseTableDetailStatusResource.setHasLive(courseTableDetailRoomUser.isHasLive());
                    courseTableDetailStatusResource.setRoomId(courseTableDetailRoomUser.getRoomId());
                }
            }
        }
        return courseTableDetailStatusResource;
    }
}

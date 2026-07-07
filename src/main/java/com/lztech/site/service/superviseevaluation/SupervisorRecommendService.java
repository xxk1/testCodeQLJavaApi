package com.lztech.site.service.superviseevaluation;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailResource;
import com.lztech.site.resource.coursetabledetail.CourseTableTeacherNumVo;
import com.lztech.site.resource.coursetabledetail.TeacherResource;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.coursetable.CourseTableInfo;
import com.lztech.site.viewmodel.superviseevaluation.NotSupervisionCourseQueryParam;
import com.lztech.site.viewmodel.superviseevaluation.SupervisorRecommendQueryParam;
import com.lztech.site.viewmodel.superviseevaluation.SupervisorRecommendVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.*;

@Service
public class SupervisorRecommendService {

    @Resource
    private EntityManager entityManager;
    @Resource
    private TermService termService;
    @Resource
    private CourseTableRepository courseTableRepository;

    public List<CourseTableDetailResource> getSupervisorRecommendCourseTable(SupervisorRecommendQueryParam supervisorListenCourseQueryParam) {
        List<SupervisorRecommendVo> supervisorRecommendVoList = supervisorListenCourseQueryParam.getSupervisorRecommendVoList();
        Term term = termService.getNowTerm();
        if (Objects.isNull(term)) {
            return new ArrayList<>();
        }
        List<String> courseTableIdList = new ArrayList<>();
        supervisorRecommendVoList.forEach(supervisorRecommendVo -> {
            List<CourseTableTeacherNumVo> voList = getCourseTableTeacherNumInfo(supervisorRecommendVo, term.getSchoolYear(),
                    term.getTerm().getIndex());
            if (CollectionUtils.isEmpty(voList)) {
                return;
            }
            voList = voList.stream().filter(vo -> vo.getTeacherNum().intValue() == 1).collect(Collectors.toList());
            courseTableIdList.addAll(voList.stream().map(CourseTableTeacherNumVo::getCourseTableId).collect(Collectors.toList()));
        });
        if (CollectionUtils.isEmpty(courseTableIdList)) {
            return new ArrayList<>();
        }
        List<CourseTableDetailResource> courseTableDetailResourceList = new ArrayList<>();
        List<CourseTable> courseTableList = courseTableRepository.findByIdIn(courseTableIdList);
        LocalDate today = LocalDate.now();
        courseTableList.forEach(courseTable -> {
            List<CourseTableDetail> detailsBeforeTodayList = courseTable.getCourseTableDetailList().stream()
                    .filter(detail -> {
                        if (Objects.isNull(detail.getCourseDate())) {
                            return false;
                        }
                        Date courseDate = detail.getCourseDate();
                        LocalDate localCourseDate = courseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert Date to LocalDate
                        return localCourseDate.isBefore(today);
                    }).collect(Collectors.toList());
            Course course = courseTable.getCourse();
            Group group = courseTable.getGroup();
            CourseCategory courseCategory = courseTable.getCourseCategory();
            detailsBeforeTodayList.forEach(detail -> {
                buildCourseTableDetailResource(courseTableDetailResourceList, courseTable, detail, course, group, courseCategory);
            });
        });
        return courseTableDetailResourceList;
    }

    private List<TeacherResource> buildTeacherList(List<CourseTableDetailTeacher> courseTableDetailTeacherList) {
        return courseTableDetailTeacherList.stream().map(courseTableDetailTeacher -> {
            TeacherResource teacherResource = new TeacherResource();
            teacherResource.setTeacherId(courseTableDetailTeacher.getTeacherId());
            teacherResource.setTeacherName(courseTableDetailTeacher.getTeacherName());
            teacherResource.setTeacherNo(courseTableDetailTeacher.getTeacherNo());
            teacherResource.setTeacherCollegeId(courseTableDetailTeacher.getTeacherCollegeId());
            teacherResource.setTeacherCollegeName(courseTableDetailTeacher.getTeacherCollegeName());
            return teacherResource;
        }).collect(Collectors.toList());
    }


    private List<CourseTableTeacherNumVo> getCourseTableTeacherNumInfo(SupervisorRecommendVo supervisorRecommendVo,
                                                                       String schoolYear,
                                                                       Integer term) {
        if (StringUtils.isAnyBlank(supervisorRecommendVo.getCourseId(), supervisorRecommendVo.getTeacherId())) {
            return new ArrayList<>();
        }
        String sql = "select  a.id as courseTableId,count(distinct c.teacher_id) teacherNum " +
                " from `tb_course_table` a INNER join `tb_course_table_detail` b " +
                " on a.id=b.course_table_id\n" +
                " INNER join `tb_course_table_detail_teacher` c on b.id = c.course_table_detail_id\n" +
                " where b.id in ( select id from `tb_course_table_detail` where course_table_id IN (\n" +
                " select ct.id\n" +
                " from tb_course_table ct\n" +
                " INNER JOIN tb_course_table_detail ctd on ct.id = ctd.course_table_id\n" +
                " inner join tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id\n" +
                " where ct.school_year = :schoolYear and ct.term = :term and ctdt.teacher_id = :teacherId " +
                " and ct.course_id = :courseId )) and a.source = 0  and a.course_id not in " +
                " (SELECT DISTINCT course_id  FROM `tb_course_expansion` where " +
                " expansion_key='politicalCourse') group by a.id ";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("schoolYear", schoolYear);
        query.setParameter("term", term);
        query.setParameter("teacherId", supervisorRecommendVo.getTeacherId());
        query.setParameter("courseId", supervisorRecommendVo.getCourseId());
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableTeacherNumVo.class));
        return query.getResultList();
    }

    public List<CourseTableDetailResource> getNotSupervisedCourse(NotSupervisionCourseQueryParam param) {
        List<CourseTableInfo> courseTableInfoList = getCourseTableInfos(param);
        List<CourseTableInfo> uniqueCourseTableInfoList = getUniqueCourseIds(courseTableInfoList, param.getQueryNum());
        List<String> courseTableIdList = uniqueCourseTableInfoList.stream().map(CourseTableInfo::getId).collect(Collectors.toList());
        List<CourseTable> courseTableList = courseTableRepository.findByIdIn(courseTableIdList);
        return buildCourseTableDetailResourceList(courseTableList);
    }

    private List<CourseTableDetailResource> buildCourseTableDetailResourceList(List<CourseTable> courseTableList) {
        List<CourseTableDetailResource> courseTableDetailResourceList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        courseTableList.forEach(courseTable -> {
            List<CourseTableDetail> courseTableDetailList = courseTable.getCourseTableDetailList()
                    .stream()
                    .filter(detail -> {
                        if (Objects.isNull(detail.getCourseDate())) {
                            return false;
                        }
                        Date courseDate = detail.getCourseDate();
                        LocalDate localCourseDate = courseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert Date to LocalDate
                        List<CourseTableDetailRoomUser> roomUserList = detail.getCourseTableDetailRoomUserList();
                        return localCourseDate.isBefore(today) && CollectionUtils.isNotEmpty(roomUserList) &&
                                roomUserList.stream().noneMatch(roomUser -> StringUtils.isBlank(roomUser.getRoomId()));
                    }).collect(Collectors.toList());
            Collections.shuffle(courseTableDetailList);
            CourseTableDetail detail = courseTableDetailList.get(0);
            Course course = courseTable.getCourse();
            Group group = courseTable.getGroup();
            CourseCategory courseCategory = courseTable.getCourseCategory();

            buildCourseTableDetailResource(courseTableDetailResourceList, courseTable, detail, course, group,
                    courseCategory);
        });
        return courseTableDetailResourceList;
    }

    private void buildCourseTableDetailResource(List<CourseTableDetailResource> courseTableDetailResourceList, CourseTable courseTable,
                                                CourseTableDetail detail, Course course, Group group, CourseCategory courseCategory) {
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = detail.getCourseTableDetailTeacherList();
        List<CourseTableDetailRoomUser> roomUserList = detail.getCourseTableDetailRoomUserList()
                .stream()
                .filter(roomUser -> StringUtils.isNotBlank(roomUser.getRoomId()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roomUserList)) {
            return;
        }
        CourseTableDetailResource courseTableDetailResource = new CourseTableDetailResource();
        courseTableDetailResource.setId(detail.getId());
        courseTableDetailResource.setCourseId(course.getId());
        courseTableDetailResource.setGroupNo(group.getGroupNo());
        courseTableDetailResource.setGroupName(group.getGroupName());
        courseTableDetailResource.setTeacherName(courseTableDetailTeacherList.stream()
                .map(CourseTableDetailTeacher::getTeacherName)
                .collect(Collectors.joining(",")));
        courseTableDetailResource.setSchoolYear(courseTable.getSchoolYear());
        courseTableDetailResource.setTerm(courseTable.getTerm().toString());
        courseTableDetailResource.setCourseDate(DateUtils.formatDate(DateUtils.DATE, detail.getCourseDate()));
        courseTableDetailResource.setCourseName(course.getCourseName());
        courseTableDetailResource.setCourseCode(course.getCourseCode());
        courseTableDetailResource.setRoomId(roomUserList
                .stream()
                .map(CourseTableDetailRoomUser::getRoomId)
                .collect(Collectors.joining(",")));
        courseTableDetailResource.setRoomName(roomUserList
                .stream()
                .map(CourseTableDetailRoomUser::getRoomName)
                .collect(Collectors.joining(",")));
        courseTableDetailResource.setSegments(detail.getSegment());
        courseTableDetailResource.setSegmentStartTime(DateUtils.formatDate(DateUtils.DATE_TIME, detail.getSegmentStartTime()));
        courseTableDetailResource.setSegmentEndTime(DateUtils.formatDate(DateUtils.DATE_TIME, detail.getSegmentEndTime()));
        courseTableDetailResource.setCollegeId(courseTable.getCollegeId());
        courseTableDetailResource.setCollegeName(courseTable.getCollegeName());
        courseTableDetailResource.setCourseAttrName(Objects.isNull(courseCategory) ? null : courseCategory.getCourseCategoryName());
        courseTableDetailResource.setTeacherResourceList(buildTeacherList(courseTableDetailTeacherList));
        courseTableDetailResource.setSource(courseTable.getSource().getIndex());
        courseTableDetailResource.setClassCompositionName(group.getClassCompositionName());
        courseTableDetailResource.setStudentType(courseTable.getStudentType().getIndex());
        courseTableDetailResource.setStudentTypeName(courseTable.getStudentType().getName());
        courseTableDetailResource.setCourseTableId(courseTable.getId());
        courseTableDetailResourceList.add(courseTableDetailResource);
    }

    private List<CourseTableInfo> getCourseTableInfos(NotSupervisionCourseQueryParam param) {
        String today = DateUtils.formatDate(DateUtils.DATE, new Date());
        String sql = "SELECT ct.id as id,ct.course_Id as courseId from\n" +
                " tb_course_table ct \n" +
                " inner join tb_course_table_detail ctd on ct.id = ctd.course_table_id \n" +
                " INNER join `tb_course_table_detail_room_user`  c on ctd.id=c.course_table_detail_id\n" +
                " inner join `tb_course_table_detail_teacher` ctdt on ctd.id=ctdt.course_table_detail_id\n" +
                " where c.room_id!='' and c.room_id is not NULL and ctd.source = 0  and ctd.course_date< '" + today + "'";
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtils.isNotBlank(param.getCollegeIds())) {
            List<String> collegeIdList = Arrays.asList(param.getCollegeIds().split(","));
            if (SUPERVISE_COLLEGE_TYPE_COURSE_TABLE_COLLEGE.equals(param.getSuperviseCollegeType())) {
                sql += " and  ct.college_id in (:collegeIdList)";
            } else {
                sql += " AND ctdt.teacher_college_id in (:collegeIdList)";
            }
            paramMap.put("collegeIdList", collegeIdList);
        }
        if (Objects.nonNull(param.isIsNeedFilterPoliticalCourse()) && param.isIsNeedFilterPoliticalCourse()) {
            sql += " and ct.course_id not in (SELECT DISTINCT course_id  FROM `tb_course_expansion` where expansion_key='politicalCourse')";
        }
        if (StringUtils.isNotBlank(param.getSchoolYear())) {
            sql += " and ct.school_year = :schoolYear ";
            paramMap.put("schoolYear", param.getSchoolYear());
        }
        if (StringUtils.isNotBlank(param.getTerm())) {
            sql += " AND ct.term= :term";
            paramMap.put("term", param.getTerm());
        }
        if (StringUtils.isNotBlank(param.getIsDistinguishCourseStudentType())
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(param.getIsDistinguishCourseStudentType())) {
            if (StringUtils.isNotBlank(param.getCourseStudentTypes())) {
                List<String> courseStudentTypeList = Arrays.asList(param.getCourseStudentTypes().split(","));
                sql += " and ct.student_type in ( :courseStudentTypeList ) ";
                paramMap.put("courseStudentTypeList", courseStudentTypeList);
            } else {
                sql += " and 1=0 ";
            }
        }
        if (CollectionUtils.isNotEmpty(param.getSupervisionCourseIdList())) {
            sql += " and ct.course_id not in ( :supervisionCourseIdList ) ";
            paramMap.put("supervisionCourseIdList", param.getSupervisionCourseIdList());
        }

        Query queryData = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableInfo.class));
        List<CourseTableInfo> courseTableInfoList = queryData.getResultList();
        return courseTableInfoList;
    }

    private List<CourseTableInfo> getUniqueCourseIds(List<CourseTableInfo> list, Integer n) {
        n = Objects.isNull(n) ? FIVE : n;
        // Step 1: Shuffle the input list randomly
        Collections.shuffle(list);
        // Step 2: Collect unique CourseTableInfo objects with distinct courseIds
        List<CourseTableInfo> uniqueCourseTableInfos = new ArrayList<>();
        Set<String> seenCourseIds = new HashSet<>();
        for (CourseTableInfo info : list) {
            if (!seenCourseIds.contains(info.getCourseId())) {
                uniqueCourseTableInfos.add(info);
                seenCourseIds.add(info.getCourseId());
                // Stop when we have collected n unique courseIds
                if (uniqueCourseTableInfos.size() == n) {
                    break;
                }
            }
        }

        return uniqueCourseTableInfos;
    }
}

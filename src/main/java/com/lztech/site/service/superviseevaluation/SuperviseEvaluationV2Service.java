package com.lztech.site.service.superviseevaluation;

import com.lztech.domain.model.college.College;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.superviseevaluation.CanListenCourseTableDetailResourceVo;
import com.lztech.site.viewmodel.superviseevaluation.CourseTableDetailRoomResource;
import com.lztech.site.viewmodel.superviseevaluation.CourseTableDetailTeacherResource;
import com.lztech.site.viewmodel.superviseevaluation.enums.EvaluationStatusType;
import com.lztech.site.viewmodel.superviseevaluation.v2.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.NEED_DISTINGUISH_COURSE_STUDENT_TYPE;
import static com.lztech.site.constants.Constant.SIX;
import static com.lztech.site.until.DateUtils.DATE;
import static com.lztech.site.until.DateUtils.TIME_SECOND;


@Service
public class SuperviseEvaluationV2Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuperviseEvaluationV2Service.class);

    private final Integer isTeacherCollege = 0;
    private final Integer isCourseTableCollege = 1;
    private final Integer segmentStartTimeIndex = 2;
    private final Integer segmentEndTimeIndex = 3;
    private final Integer living = 1;
    private final Integer teacherNameIndex = 2;
    private final Integer teacherCollegeIndexIndex = 3;
    private final Integer teacherCollegeNameIndex = 4;
    private final Integer teacherTitleIndex = 5;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    public List<SupervisionCourseAndCourseTableDetailVo> getSupervisionCourseAndCourseTableDetail(
            SupervisionCourseTableDetialQueryParam param) {
        List<SupervisionCourseAndCourseTableDetailVo> result = new ArrayList<>();
        List<String> courseTableDetailIds = param.getCourseTableDetails().stream()
                .map(SupervisionCourseTableDetailVo::getCourseTableDetailId).collect(Collectors.toList());
        if (EvaluationStatusType.I_HAVE_EVALUATED.getCode().equals(param.getEvaluationStatusType())
                && org.apache.commons.collections4.CollectionUtils.isEmpty(courseTableDetailIds)) {
            return new ArrayList<>();
        }
        // 获取已评节次的
        List<CourseInfoAndTableDetailIds> courseInfoAndTableDetailIdList = getCourseInfoAndTableDetailIds(param);
        // 获取所有节次
        List<CourseInfoAndTableDetailIds> allCourseInfoAndTableDetailIdList = getAllCourseInfoAndTableDetailIds(param);
        // 获取当前节次
        List<CourseInfoAndTableDetailIds> nowCourseInfoAndTableDetailIdList = getNowCourseInfoAndTableDetailIds(param);

        // 获取我已评价的节次
        if (EvaluationStatusType.I_HAVE_EVALUATED.getCode().equals(param.getEvaluationStatusType())) {
            courseInfoAndTableDetailIdList.forEach(courseInfoAndTableDetailIds -> {
                CourseInfoAndTableDetailIds filter = allCourseInfoAndTableDetailIdList.stream()
                        .filter(obj -> courseInfoAndTableDetailIds.getCourseId().equals(obj.getCourseId())
                                && courseInfoAndTableDetailIds.getCourseName().equals(obj.getCourseName())
                                && courseInfoAndTableDetailIds.getStudentType().equals(obj.getStudentType())
                                && courseInfoAndTableDetailIds.getTeachers().equals(obj.getTeachers())).findFirst().orElse(null);

                if (filter != null && filter.getCourseTableDetailIds() != null) {
                    SupervisionCourseAndCourseTableDetailVo detailVo = new SupervisionCourseAndCourseTableDetailVo();
                    detailVo.setCourseName(filter.getCourseName());
                    detailVo.setCourseId(filter.getCourseId());
                    detailVo.studentType(filter.getStudentType().toString());
                    detailVo.teachers(filter.getTeachers());
                    List<String> ids = Arrays.asList(filter.getCourseTableDetailIds().split(";"));
                    List<SupervisionCourseTableDetailVo> detailIds = ids.stream().map(s -> {
                        SupervisionCourseTableDetailVo detailVo1 = new SupervisionCourseTableDetailVo();
                        detailVo1.setCourseTableDetailId(s.split(",")[0]);
                        return detailVo1;
                    }).collect(Collectors.toList());
                    detailVo.setCourseTableDetails(detailIds);
                    detailVo.setLive(false);
                    detailVo.setLiveCourseTableDetailId("");
                    detailVo.setCourseTableDetailSegmentStartTime("");
                    detailVo.setCourseTableDetailSegmentEndTime("");
                    CourseInfoAndTableDetailIds filterLive = nowCourseInfoAndTableDetailIdList.stream()
                            .filter(obj -> courseInfoAndTableDetailIds.getCourseId().equals(obj.getCourseId())
                                    && courseInfoAndTableDetailIds.getCourseName().equals(obj.getCourseName())
                                    && courseInfoAndTableDetailIds.getStudentType().equals(obj.getStudentType())
                                    && courseInfoAndTableDetailIds.getTeachers().equals(obj.getTeachers())).findFirst().orElse(null);
                    if (Objects.nonNull(param.getLive()) && param.getLive().equals(living)
                            && filterLive != null && filterLive.getCourseTableDetailIds() != null) {
                        // 要直播的
                        return;
                    }
                    if (filterLive != null && filterLive.getCourseTableDetailIds() != null) {
                        detailVo.setLive(true);
                        List<String> temp = Arrays.asList(filterLive.getCourseTableDetailIds().split(";"));
                        String[] arrayS = temp.get(0).split(",");
                        detailVo.setLiveCourseTableDetailId(filterLive.getCourseTableDetailIds());
                        detailVo.setCourseTableDetailSegmentStartTime(arrayS[1] + " " + arrayS[segmentStartTimeIndex]);
                        detailVo.setCourseTableDetailSegmentEndTime(arrayS[1] + " " + arrayS[segmentEndTimeIndex]);
                    }
                    detailVo.setSourceDataSource(filter.getSourceDataSource());
                    detailVo.setSourceDataSourceName(filter.getSourceDataSourceName());
                    result.add(detailVo);
                }
            });
        }

        if (EvaluationStatusType.I_HAVE_NOT_EVALUATED.getCode().equals(param.getEvaluationStatusType())) {
            // 获取未评价的节次
            // 获取未评价的节次 - 使用差集计算
            List<CourseInfoAndTableDetailIds> notEvaluatedList = allCourseInfoAndTableDetailIdList.stream()
                    .filter(allCourse -> courseInfoAndTableDetailIdList.stream()
                            .noneMatch(evaluatedCourse ->
                                    evaluatedCourse.getCourseId().equals(allCourse.getCourseId()) &&
                                            evaluatedCourse.getCourseName().equals(allCourse.getCourseName()) &&
                                            evaluatedCourse.getStudentType().equals(allCourse.getStudentType()) &&
                                            evaluatedCourse.getTeachers().equals(allCourse.getTeachers())
                            )
                    )
                    .collect(Collectors.toList());
            notEvaluatedList.forEach(filter -> {
                SupervisionCourseAndCourseTableDetailVo detailVo = new SupervisionCourseAndCourseTableDetailVo();
                detailVo.setCourseName(filter.getCourseName());
                detailVo.setCourseId(filter.getCourseId());
                detailVo.studentType(filter.getStudentType().toString());
                detailVo.teachers(filter.getTeachers());
                List<String> ids = Arrays.asList(filter.getCourseTableDetailIds().split(";"));
                List<SupervisionCourseTableDetailVo> detailIds = ids.stream().map(s -> {
                    SupervisionCourseTableDetailVo detailVo1 = new SupervisionCourseTableDetailVo();
                    detailVo1.setCourseTableDetailId(s.split(",")[0]);
                    return detailVo1;
                }).collect(Collectors.toList());
                detailVo.setCourseTableDetails(detailIds);
                detailVo.setLive(false);
                detailVo.setLiveCourseTableDetailId("");
                detailVo.setCourseTableDetailSegmentStartTime("");
                detailVo.setCourseTableDetailSegmentEndTime("");
                CourseInfoAndTableDetailIds filterLive = nowCourseInfoAndTableDetailIdList.stream()
                        .filter(obj -> filter.getCourseId().equals(obj.getCourseId())
                                && filter.getCourseName().equals(obj.getCourseName())
                                && filter.getStudentType().equals(obj.getStudentType())
                                && filter.getTeachers().equals(obj.getTeachers())).findFirst().orElse(null);
                if (Objects.nonNull(param.getLive()) && param.getLive().equals(living)
                        && filterLive != null && filterLive.getCourseTableDetailIds() != null) {
                    // 要直播的
                    return;
                }
                if (filterLive != null && filterLive.getCourseTableDetailIds() != null) {
                    detailVo.setLive(true);
                    List<String> temp = Arrays.asList(filterLive.getCourseTableDetailIds().split(";"));
                    String[] arrayS = temp.get(0).split(",");
                    detailVo.setLiveCourseTableDetailId(filterLive.getCourseTableDetailIds());
                    detailVo.setCourseTableDetailSegmentStartTime(arrayS[1] + " " + arrayS[segmentStartTimeIndex]);
                    detailVo.setCourseTableDetailSegmentEndTime(arrayS[1] + " " + arrayS[segmentEndTimeIndex]);
                }
                detailVo.setSourceDataSource(filter.getSourceDataSource());
                detailVo.setSourceDataSourceName(filter.getSourceDataSourceName());
                result.add(detailVo);
            });
        }


        return result;
    }

    private List<CourseInfoAndTableDetailIds> getAllCourseInfoAndTableDetailIds(SupervisionCourseTableDetialQueryParam param) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", param.getSchoolYear());
        paramMap.put("term", param.getTerm());

        String sql = buildSelectCourseAndTeachersByDetailIds(
                paramMap, null, param.getCollegeType(), param.getCollegeId(),
                param.getNowTime(),
                Objects.nonNull(param.getLive()) && Objects.equals(param.getLive(), living),
                param.getCollegeIds(), param.getSourceDataSource(), param);

        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfoAndTableDetailIds.class));
        List<CourseInfoAndTableDetailIds> resultList = query.getResultList();
        return resultList;
    }

    private List<CourseInfoAndTableDetailIds> getNowCourseInfoAndTableDetailIds(SupervisionCourseTableDetialQueryParam param) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", param.getSchoolYear());
        paramMap.put("term", param.getTerm());

        String sql = buildSelectCourseAndTeachersByDetailIds(
                paramMap, null, param.getCollegeType(), param.getCollegeId(),
                param.getNowTime(), true, param.getCollegeIds(),
                param.getSourceDataSource(), param);

        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfoAndTableDetailIds.class));
        List<CourseInfoAndTableDetailIds> resultList = query.getResultList();
        return resultList;
    }

    private List<CourseInfoAndTableDetailIds> getCourseInfoAndTableDetailIds(SupervisionCourseTableDetialQueryParam param) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", param.getSchoolYear());
        paramMap.put("term", param.getTerm());
        List<String> courseTableDetailIds = param.getCourseTableDetails().stream()
                .map(SupervisionCourseTableDetailVo::getCourseTableDetailId).collect(Collectors.toList());
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(courseTableDetailIds)) {
            return new ArrayList<>();
        }
        String sql = buildSelectCourseAndTeachersByDetailIds(
                paramMap, courseTableDetailIds, null, null, param.getNowTime(),
                Objects.nonNull(param.getLive()) && Objects.equals(param.getLive(), living),
                param.getCollegeIds(), param.getSourceDataSource(), param);

        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfoAndTableDetailIds.class));
        List<CourseInfoAndTableDetailIds> resultList = query.getResultList();
        return resultList;
    }

    private String buildSelectCourseAndTeachersByDetailIds(
            Map<String, Object> paramMap, List<String> courseTableDetailIds, Integer collegeType, String collegeId,
            String nowTime, Boolean isLive, String collegeIds, String sourceDataSource,
            SupervisionCourseTableDetialQueryParam param) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT a.studentType,a.courseId,a.courseName,a.teachers," +
                "GROUP_CONCAT(DISTINCT CONCAT(a.courseTableDetailId,',',a.courseDate,',',a.segmentStartTime,',',a.segmentEndTime)" +
                " ORDER BY a.courseTableDetailId SEPARATOR ';' ) as courseTableDetailIds,a.sourceDataSourceName,a.sourceDataSource  from (");
        sb.append("SELECT ct.course_id as courseId,ct.course_name as courseName,ctd.id,ct.student_type as studentType,");
        sb.append("GROUP_CONCAT(DISTINCT CONCAT(ctdt.teacher_id,',',ctdt.teacher_no,',',ctdt.teacher_name) " +
                "ORDER BY ctdt.teacher_id SEPARATOR ';') as teachers,");
        sb.append("ctd.id as courseTableDetailId,ctd.course_date as courseDate," +
                "ctd.segment_start_time as segmentStartTime,ctd.segment_end_time as segmentEndTime, " +
                "ctd.source_data_source_name as sourceDataSourceName,ctd.source_data_source as sourceDataSource ");
        sb.append(" FROM tb_course_table ct");
        sb.append(" JOIN tb_course_table_detail ctd on ctd.course_table_id = ct.id");
        sb.append(" JOIN tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd.id");
        sb.append(" JOIN tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = ctd.id");
        sb.append(" WHERE ct.school_year=:schoolYear and ct.term = :term " +
                " and ctd.segment_start_time is not null and ctd.segment_end_time is not null ");
        sb.append(" AND ctdru.room_id IS NOT NULL");
        sb.append(" AND ctdru.has_live = 1");
        if (Objects.nonNull(param.isIsNeedFilterPoliticalCourse()) && param.isIsNeedFilterPoliticalCourse()) {
            sb.append(" AND ct.course_id not in " +
                    " (SELECT DISTINCT course_id  FROM `tb_course_expansion` where expansion_key='politicalCourse')");
        }
        if (!CollectionUtils.isEmpty(courseTableDetailIds)) {
            sb.append(" AND ctd.id in (:courseTableDetailIds)");
            paramMap.put("courseTableDetailIds", courseTableDetailIds);
        }
        if (collegeType != null) {
            if (isTeacherCollege.equals(collegeType)) {
                if (StringUtils.isNotBlank(collegeId)) {
                    sb.append(" and ctdt.teacher_college_id = :collegeId ");
                    paramMap.put("collegeId", collegeId);
                }
                if (StringUtils.isNotBlank(collegeIds)) {
                    if (collegeIds.split(",").length == 1) {
                        sb.append(" and ctdt.teacher_college_id = :collegeIds ");
                        paramMap.put("collegeIds", collegeIds);
                    } else {
                        sb.append(" and ctdt.teacher_college_id in (:collegeIds) ");
                        paramMap.put("collegeIds", Arrays.asList(collegeIds.split(",")));
                    }
                }
            }
            if (isCourseTableCollege.equals(collegeType)) {
                if (StringUtils.isNotBlank(collegeId)) {
                    sb.append(" and ct.college_id = :collegeId ");
                    paramMap.put("collegeId", collegeId);
                }
                if (StringUtils.isNotBlank(collegeIds)) {
                    if (collegeIds.split(",").length == 1) {
                        sb.append(" and ct.college_id = :collegeIds ");
                        paramMap.put("collegeIds", collegeIds);
                    } else {
                        sb.append(" and ct.college_id in (:collegeIds) ");
                        paramMap.put("collegeIds", Arrays.asList(collegeIds.split(",")));
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(param.getIsDistinguishCourseStudentType())
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(param.getIsDistinguishCourseStudentType())) {
            if (StringUtils.isNotBlank(param.getCourseStudentTypes())) {
                List<String> courseStudentTypeList = Arrays.asList(param.getCourseStudentTypes().split(","));
                sb.append(" and ct.student_type in ( :courseStudentTypeList )");
                paramMap.put("courseStudentTypeList", courseStudentTypeList);
            } else {
                sb.append(" and 1=0 ");
            }
        }
        if (isLive && StringUtils.isNotEmpty(nowTime)) {
            sb.append(" AND ctd.course_date = :courseDate ");
            Date date = DateUtils.stringToDate(DateUtils.DATE_TIME, nowTime);
            paramMap.put("courseDate", DateUtils.formatDate(DATE, date));
            sb.append(" AND ctd.segment_start_time <= :nowTime ");
            sb.append(" AND ctd.segment_end_time > :nowTime ");
            String time = DateUtils.formatDate(DateUtils.TIME, date);
            paramMap.put("nowTime", time);
        } else {
            sb.append(" AND ctd.course_date <= :courseDate ");
            paramMap.put("courseDate", DateUtils.formatDate(DATE, DateUtils.stringToDate(DateUtils.DATE_TIME, nowTime)));
        }
        if (StringUtils.isNotEmpty(sourceDataSource)) {
            sb.append(" AND ctd.source_data_source = :sourceDataSource ");
            paramMap.put("sourceDataSource", sourceDataSource);
        }
        sb.append(" GROUP BY ct.student_type,ct.course_id,ct.course_name,ctd.id");
        sb.append(") a ");
        if (StringUtils.isNotBlank(param.getCourseNameOrTeacherName())) {
            String courseNameOrTeacherName = CharactersReplace.replaceCharacters(param.getCourseNameOrTeacherName().trim());
            sb.append(" WHERE a.courseName like :courseNameOrTeacherName OR a.teachers like :courseNameOrTeacherName");
            paramMap.put("courseNameOrTeacherName", "%" + courseNameOrTeacherName + "%");
        }
        sb.append(" GROUP BY a.courseId,a.courseName,a.teachers");
        sb.append(" ORDER BY a.courseName desc,a.courseId");
        return sb.toString();
    }


    public SupervisionCourseListPageV2 getSupervisionCourseAndCourseTableDetailList(
            SupervisionCourseQueryParamV2 param) {
        SupervisionCourseListPageV2 supervisionCoursePageV2 = initSupervisionCourseListPageV2();
        if (living.equals(param.getLive())) {
            getLiveSuperviseCoursePageV2(param, supervisionCoursePageV2);
        } else {
            getAllSuperviseCoursePageV2(param, supervisionCoursePageV2);
        }
        return supervisionCoursePageV2;
    }

    private void getAllSuperviseCoursePageV2(SupervisionCourseQueryParamV2 param,
                                             SupervisionCourseListPageV2 supervisionCoursePageV2) {
        Integer offset = (param.getPage() - 1) * param.getPageSize();
        Integer pageSize = param.getPageSize();
        List<CourseInfoAndTableDetailIds> courseInfoAndTableDetailIdsPage =
                getCourseInfoAndTableDetailIdList(param, offset, pageSize);

        Integer totalCount = getCourseInfoAndTableDetailIdListCount(param);
        List<CourseInfoAndTableDetailIds> liveCourseInfoAndTableDetailIdsList =
                getLiveCourseInfoAndTableDetailIdList(param, null, null);

        List<SupervisionCourseAndCourseTableDetailVo> detailVos = new ArrayList<>();
        courseInfoAndTableDetailIdsPage.forEach(courseInfoAndTableDetailIds -> {
            SupervisionCourseAndCourseTableDetailVo detailVo = new SupervisionCourseAndCourseTableDetailVo();
            detailVo.setCourseId(courseInfoAndTableDetailIds.getCourseId());
            detailVo.setCourseName(courseInfoAndTableDetailIds.getCourseName());
            detailVo.setTeachers(courseInfoAndTableDetailIds.getTeachers());
            detailVo.setTeachers(courseInfoAndTableDetailIds.getTeachers());
            detailVo.setStudentType(courseInfoAndTableDetailIds.getStudentType().toString());

            List<String> ids = Arrays.asList(courseInfoAndTableDetailIds.getCourseTableDetailIds().split(";"));
            List<SupervisionCourseTableDetailVo> detailIds = ids.stream().map(s -> {
                SupervisionCourseTableDetailVo detailVo1 = new SupervisionCourseTableDetailVo();
                detailVo1.setCourseTableDetailId(s.split(",")[0]);
                return detailVo1;
            }).collect(Collectors.toList());
            detailVo.setCourseTableDetails(detailIds);

            detailVo.setLiveCourseTableDetailId("");
            detailVo.setCourseTableDetailSegmentStartTime("");
            detailVo.setCourseTableDetailSegmentEndTime("");
            CourseInfoAndTableDetailIds filterLive = liveCourseInfoAndTableDetailIdsList.stream()
                    .filter(obj -> courseInfoAndTableDetailIds.getCourseId().equals(obj.getCourseId())
                            && courseInfoAndTableDetailIds.getCourseName().equals(obj.getCourseName())
                            && courseInfoAndTableDetailIds.getStudentType().equals(obj.getStudentType())
                            && courseInfoAndTableDetailIds.getTeachers().equals(obj.getTeachers())).findFirst().orElse(null);
            detailVo.setLive(false);
            if (filterLive != null && filterLive.getCourseTableDetailIds() != null) {
                detailVo.setLive(true);
                List<String> temp = Arrays.asList(filterLive.getCourseTableDetailIds().split(";"));
                String[] arrayS = temp.get(0).split(",");
                detailVo.setLiveCourseTableDetailId(filterLive.getCourseTableDetailIds());
                detailVo.setCourseTableDetailSegmentStartTime(arrayS[1] + " " + arrayS[segmentStartTimeIndex]);
                detailVo.setCourseTableDetailSegmentEndTime(arrayS[1] + " " + arrayS[segmentEndTimeIndex]);
            }
            detailVo.setSourceDataSource(courseInfoAndTableDetailIds.getSourceDataSource());
            detailVo.setSourceDataSourceName(courseInfoAndTableDetailIds.getSourceDataSourceName());
            detailVos.add(detailVo);
        });
        supervisionCoursePageV2.setTotalCount(totalCount);
        supervisionCoursePageV2.setPage(param.getPage());
        supervisionCoursePageV2.setPageCount((int) Math.ceil(totalCount / (pageSize * 1.0)));
        supervisionCoursePageV2.setSupervisionCourseList(detailVos);
    }

    private void getLiveSuperviseCoursePageV2(SupervisionCourseQueryParamV2 param,
                                              SupervisionCourseListPageV2 supervisionCoursePageV2) {
        Integer offset = (param.getPage() - 1) * param.getPageSize();
        Integer pageSize = param.getPageSize();

        List<CourseInfoAndTableDetailIds> liveCourseInfoAndTableDetailIdsPage =
                getLiveCourseInfoAndTableDetailIdList(param, offset, pageSize);

        Integer totalCount = getLiveCourseInfoAndTableDetailIdListCount(param);
        List<CourseInfoAndTableDetailIds> courseInfoAndTableDetailIdsList =
                getCourseInfoAndTableDetailIdList(param, null, null);

        List<SupervisionCourseAndCourseTableDetailVo> detailVos = new ArrayList<>();
        liveCourseInfoAndTableDetailIdsPage.forEach(courseInfoAndTableDetailIds -> {
            SupervisionCourseAndCourseTableDetailVo detailVo = new SupervisionCourseAndCourseTableDetailVo();
            detailVo.setCourseId(courseInfoAndTableDetailIds.getCourseId());
            detailVo.setCourseName(courseInfoAndTableDetailIds.getCourseName());
            detailVo.setTeachers(courseInfoAndTableDetailIds.getTeachers());
            detailVo.setTeachers(courseInfoAndTableDetailIds.getTeachers());
            detailVo.setStudentType(courseInfoAndTableDetailIds.getStudentType().toString());
            detailVo.setLive(true);
            List<String> temp = Arrays.asList(courseInfoAndTableDetailIds.getCourseTableDetailIds().split(";"));
            String[] arrayS = temp.get(0).split(",");
            detailVo.setLiveCourseTableDetailId(courseInfoAndTableDetailIds.getCourseTableDetailIds());
            detailVo.setCourseTableDetailSegmentStartTime(arrayS[1] + " " + arrayS[segmentStartTimeIndex]);
            detailVo.setCourseTableDetailSegmentEndTime(arrayS[1] + " " + arrayS[segmentEndTimeIndex]);

            CourseInfoAndTableDetailIds filter = courseInfoAndTableDetailIdsList.stream()
                    .filter(obj -> courseInfoAndTableDetailIds.getCourseId().equals(obj.getCourseId())
                            && courseInfoAndTableDetailIds.getCourseName().equals(obj.getCourseName())
                            && courseInfoAndTableDetailIds.getStudentType().equals(obj.getStudentType())
                            && courseInfoAndTableDetailIds.getTeachers().equals(obj.getTeachers())).findFirst().orElse(null);

            if (filter != null && filter.getCourseTableDetailIds() != null) {
                List<String> ids = Arrays.asList(filter.getCourseTableDetailIds().split(";"));
                List<SupervisionCourseTableDetailVo> detailIds = ids.stream().map(s -> {
                    SupervisionCourseTableDetailVo detailVo1 = new SupervisionCourseTableDetailVo();
                    detailVo1.setCourseTableDetailId(s.split(",")[0]);
                    return detailVo1;
                }).collect(Collectors.toList());
                detailVo.setCourseTableDetails(detailIds);
            }
            detailVo.setSourceDataSource(courseInfoAndTableDetailIds.getSourceDataSource());
            detailVo.setSourceDataSourceName(courseInfoAndTableDetailIds.getSourceDataSourceName());
            detailVos.add(detailVo);
        });

        supervisionCoursePageV2.setTotalCount(totalCount);
        supervisionCoursePageV2.setPage(param.getPage());
        supervisionCoursePageV2.setPageCount((int) Math.ceil(totalCount / (pageSize * 1.0)));
        supervisionCoursePageV2.setSupervisionCourseList(detailVos);
    }

    private List<CourseInfoAndTableDetailIds> getCourseInfoAndTableDetailIdList(SupervisionCourseQueryParamV2 param,
                                                                                Integer offset, Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", param.getSchoolYear());
        paramMap.put("term", param.getTerm());

        String sql = buildSelectCourseAndTeachersByDetailIdList(paramMap, param, offset, pageSize, null, false, param.getSourceDataSource());

        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfoAndTableDetailIds.class));
        List<CourseInfoAndTableDetailIds> resultList = query.getResultList();
        return resultList;
    }

    private Integer getCourseInfoAndTableDetailIdListCount(SupervisionCourseQueryParamV2 param) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", param.getSchoolYear());
        paramMap.put("term", param.getTerm());

        String sql = buildSelectCourseAndTeachersByDetailIdList(paramMap, param, null, null, null, true, param.getSourceDataSource());

        Query queryCount = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        Integer count = ((BigInteger) resMap.get("totalCount")).intValue();
        return count;
    }

    private List<CourseInfoAndTableDetailIds> getLiveCourseInfoAndTableDetailIdList(SupervisionCourseQueryParamV2 param,
                                                                                    Integer offset, Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", param.getSchoolYear());
        paramMap.put("term", param.getTerm());

        String sql = buildSelectCourseAndTeachersByDetailIdList(paramMap, param, offset, pageSize, param.getNowTime(),
                false, param.getSourceDataSource());

        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfoAndTableDetailIds.class));
        List<CourseInfoAndTableDetailIds> resultList = query.getResultList();
        return resultList;
    }

    private Integer getLiveCourseInfoAndTableDetailIdListCount(SupervisionCourseQueryParamV2 param) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", param.getSchoolYear());
        paramMap.put("term", param.getTerm());

        String sql = buildSelectCourseAndTeachersByDetailIdList(paramMap, param, null, null, param.getNowTime(), true, param.getSourceDataSource());

        Query queryCount = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        Integer count = ((BigInteger) resMap.get("totalCount")).intValue();
        return count;
    }

    private String buildSelectCourseAndTeachersByDetailIdList(
            Map<String, Object> paramMap, SupervisionCourseQueryParamV2 param,
            Integer offset, Integer pageSize, String nowTime, Boolean getCount, String sourceDataSource) {
        StringBuilder sb = new StringBuilder();
        if (getCount) {
            sb.append("select count(1) as totalCount  from (");
        }
        sb.append("SELECT a.studentType,a.courseId,a.courseName,a.teachers," +
                "GROUP_CONCAT(DISTINCT CONCAT(a.courseTableDetailId,',',a.courseDate,',',a.segmentStartTime,',',a.segmentEndTime)" +
                " ORDER BY a.courseTableDetailId SEPARATOR ';' ) as courseTableDetailIds,a.sourceDataSourceName,a.sourceDataSource from (");
        sb.append("SELECT ct.course_id as courseId,ct.course_name as courseName,ctd.id,ct.student_type as studentType,");
        sb.append("GROUP_CONCAT(DISTINCT CONCAT(ctdt.teacher_id,',',ctdt.teacher_no,',',ctdt.teacher_name) " +
                "ORDER BY ctdt.teacher_id SEPARATOR ';') as teachers,");
        sb.append("ctd.id as courseTableDetailId,ctd.course_date as courseDate," +
                "ctd.segment_start_time as segmentStartTime,ctd.segment_end_time as segmentEndTime," +
                "ctd.source_data_source_name as sourceDataSourceName,ctd.source_data_source as sourceDataSource ");
        sb.append(" FROM tb_course_table ct");
        sb.append(" JOIN tb_course_table_detail ctd on ctd.course_table_id = ct.id");
        sb.append(" JOIN tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd.id");
        sb.append(" JOIN tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = ctd.id");
        sb.append(" WHERE ct.school_year=:schoolYear and ct.term = :term " +
                " and ctd.segment_start_time is not null and ctd.segment_end_time is not null ");
        sb.append(" AND ctdru.room_id IS NOT NULL");
        sb.append(" AND ctdru.has_live = 1");
        if (Objects.nonNull(param.isIsNeedFilterPoliticalCourse()) && param.isIsNeedFilterPoliticalCourse()) {
            sb.append(" AND ct.course_id not in (SELECT DISTINCT course_id " +
                    " FROM `tb_course_expansion` where expansion_key='politicalCourse')");
        }

        if (param.getSuperviseCollegeType() != null) {
            if (isTeacherCollege.equals(param.getSuperviseCollegeType())) {
                if (StringUtils.isNotBlank(param.getCollegeId())) {
                    sb.append(" AND ctdt.teacher_college_id=:collegeId");
                    paramMap.put("collegeId", param.getCollegeId());
                }
                if (StringUtils.isNotBlank(param.getCollegeIds())) {
                    // 只有一个元素的时候用=，多个元素用in
                    if (param.getCollegeIds().split(",").length == 1) {
                        sb.append(" and ctdt.teacher_college_id = :collegeIds ");
                        paramMap.put("collegeIds", param.getCollegeIds());
                    } else {
                        sb.append(" and ctdt.teacher_college_id in (:collegeIds) ");
                        paramMap.put("collegeIds", Arrays.asList(param.getCollegeIds().split(",")));
                    }
                }
            }
            if (isCourseTableCollege.equals(param.getSuperviseCollegeType())) {
                if (StringUtils.isNotBlank(param.getCollegeId())) {
                    sb.append(" AND ct.college_id=:collegeId");
                    paramMap.put("collegeId", param.getCollegeId());
                }
                if (StringUtils.isNotBlank(param.getCollegeIds())) {
                    // 只有一个元素的时候用=，多个元素用in
                    if (param.getCollegeIds().split(",").length == 1) {
                        sb.append(" and ct.college_id = :collegeIds ");
                        paramMap.put("collegeIds", param.getCollegeIds());
                    } else {
                        sb.append(" and ct.college_id in (:collegeIds) ");
                        paramMap.put("collegeIds", Arrays.asList(param.getCollegeIds().split(",")));
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(param.getIsDistinguishCourseStudentType())
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(param.getIsDistinguishCourseStudentType())) {
            if (StringUtils.isNotBlank(param.getCourseStudentTypes())) {
                List<String> courseStudentTypeList = Arrays.asList(param.getCourseStudentTypes().split(","));
                sb.append(" and ct.student_type in ( :courseStudentTypeList )");
                paramMap.put("courseStudentTypeList", courseStudentTypeList);
            } else {
                sb.append(" and 1=0 ");
            }
        }
        if (Objects.nonNull(param.getOnlyGraduateCourse()) && param.getOnlyGraduateCourse() == 1) {
            sb.append(" and ct.student_type='1'");
        }

        if (StringUtils.isNotEmpty(nowTime)) {
            Date date = DateUtils.stringToDate(DateUtils.DATE_TIME, nowTime);
            sb.append(" AND ctd.course_date = :courseDate ");
            paramMap.put("courseDate", DateUtils.formatDate(DATE, date));
            sb.append(" AND ctd.segment_start_time <= :nowTime ");
            sb.append(" AND ctd.segment_end_time > :nowTime ");
            String time = DateUtils.formatDate(DateUtils.TIME, date);
            paramMap.put("nowTime", time);
        } else {
            sb.append(" AND ctd.course_date <= :courseDate ");
            paramMap.put("courseDate", DateUtils.formatDate(DATE, DateUtils.stringToDate(DateUtils.DATE_TIME, param.getNowTime())));
        }
        if (StringUtils.isNotEmpty(sourceDataSource)) {
            sb.append(" AND ctd.source_data_source = :sourceDataSource ");
            paramMap.put("sourceDataSource", sourceDataSource);
        }
        if (Objects.nonNull(param.getEvaluationStatusType())
                && org.apache.commons.collections4.CollectionUtils.isNotEmpty(param.getEvaluatedDataIdList())) {
            if (EvaluationStatusType.COURSE_NO_EVALUATION.getCode().equals(param.getEvaluationStatusType())) {
                sb.append(" and ct.course_id not in ( :courseIdList )");
                paramMap.put("courseIdList", param.getEvaluatedDataIdList());
            }
            if (EvaluationStatusType.TEACHER_NO_EVALUATION.getCode().equals(param.getEvaluationStatusType())
                    && org.apache.commons.collections4.CollectionUtils.isNotEmpty(param.getEvaluatedDataIdList())) {
                sb.append(" and ctdt.teacher_id not in ( :teacherIdList )");
                paramMap.put("teacherIdList", param.getEvaluatedDataIdList());
            }
        }


        sb.append(" GROUP BY ct.student_type,ct.course_id,ct.course_name,ctd.id) a");

        if (StringUtils.isNotBlank(param.getCourseNameOrTeacherName())) {
            String courseNameOrTeacherName = CharactersReplace.replaceCharacters(param.getCourseNameOrTeacherName().trim());
            sb.append(" WHERE a.courseName like :courseNameOrTeacherName OR a.teachers like :courseNameOrTeacherName");
            paramMap.put("courseNameOrTeacherName", "%" + courseNameOrTeacherName + "%");
        }
        sb.append(" GROUP BY a.courseId,a.courseName,a.teachers");
        if ("courseName".equals(param.getSortName())) {
            sb.append(" ORDER BY a.courseName desc,a.courseId");
        } else {
            sb.append(" ORDER BY a.courseId");
        }
        if (ObjectUtils.isNotEmpty(offset) && ObjectUtils.isNotEmpty(param.isPagingEnabled()) && param.isPagingEnabled()) {
            sb.append(" LIMIT ").append(offset.toString()).append(",").append(pageSize.toString());
        }
        if (getCount) {
            sb.append(") b");
        }
        return sb.toString();
    }

    private SupervisionCourseListPageV2 initSupervisionCourseListPageV2() {
        SupervisionCourseListPageV2 supervisionCoursePageV2 = new SupervisionCourseListPageV2();
        supervisionCoursePageV2.setPage(0);
        supervisionCoursePageV2.setPageCount(0);
        supervisionCoursePageV2.setTotalCount(0);
        supervisionCoursePageV2.setSupervisionCourseList(new ArrayList<>());
        return supervisionCoursePageV2;
    }

    public List<SupervisionCourseTableAndDetailVo> getSupervisionCourseTableAndDetailList(
            SupervisionCourseAndDetailQueryParam param) {
        List<SupervisionCourseTableAndDetailVo> tableAndDetailVoList = new ArrayList<>();
        if (!isTeacherCollege.equals(param.getCollegeType()) && !isCourseTableCollege.equals(param.getCollegeType())) {
            return tableAndDetailVoList;
        }

        String courseTableDetailIds = getCourseTableDetailIds(param);
        if (StringUtils.isNotEmpty(courseTableDetailIds)) {
            List<String> detailIds = Arrays.asList(courseTableDetailIds.split(","));
            List<Map<String, Object>> courseTableAndDetails = getCourseTableAndDetailsByDetailIds(detailIds);
            courseTableAndDetails.forEach(mapInfo -> {
                SupervisionCourseTableAndDetailVo tableAndDetailVo = new SupervisionCourseTableAndDetailVo();
                tableAndDetailVo.setCourseTableId(mapInfo.get("courseTableId").toString());
                tableAndDetailVo.setCourseTableDetailId(mapInfo.get("courseTableDetailId").toString());
                tableAndDetailVo.setGroupId(mapInfo.get("groupId").toString());
                tableAndDetailVo.setClassName(ObjectUtils.isEmpty(mapInfo.get("className")) ? "" : mapInfo.get("className").toString());
                tableAndDetailVo.setClassNickName(ObjectUtils.isEmpty(mapInfo.get("classNickName")) ? "" : mapInfo.get("classNickName").toString());
                tableAndDetailVo.setClassCompositionName(ObjectUtils.isEmpty(mapInfo.get("classCompositionName"))
                        ? "" : mapInfo.get("classCompositionName").toString());
                tableAndDetailVo.setCourseAttrName(ObjectUtils.isEmpty(mapInfo.get("courseAttrName"))
                        ? "" : mapInfo.get("courseAttrName").toString());
                tableAndDetailVo.setSegment(mapInfo.get("segment").toString());
                tableAndDetailVo.setStudentCount(mapInfo.get("studentCount").toString());
                tableAndDetailVo.setCourseDate(mapInfo.get("courseDate").toString());
                tableAndDetailVo.setSegmentStartTime(mapInfo.get("segmentStartTime").toString());
                tableAndDetailVo.setSegmentEndTime(mapInfo.get("segmentEndTime").toString());
                tableAndDetailVo.setSourceDataSource(ObjectUtils.isEmpty(mapInfo.get("sourceDataSource"))
                        ? "" : mapInfo.get("sourceDataSource").toString());
                tableAndDetailVo.setSourceDataSourceName(ObjectUtils.isEmpty(mapInfo.get("sourceDataSourceName"))
                        ? "" : mapInfo.get("sourceDataSourceName").toString());

                String rooms = mapInfo.get("rooms").toString();
                List<SupervisionCourseTableDetailRoomVo> roomVoList = new ArrayList<>();
                if (StringUtils.isNoneEmpty(rooms)) {
                    List<String> roomList = Arrays.asList(rooms.split(";"));
                    roomList.forEach(s -> {
                        SupervisionCourseTableDetailRoomVo roomVo = new SupervisionCourseTableDetailRoomVo();
                        String[] arrayRoom = s.split(",");
                        roomVo.setRoomId(arrayRoom[0]);
                        roomVo.setRoomName(arrayRoom[1]);
                        roomVoList.add(roomVo);
                    });
                }
                tableAndDetailVo.setRooms(roomVoList);

                String teachers = mapInfo.get("teachers").toString();
                List<SupervisionCourseTableDetailTeacherVo> teacherVos = new ArrayList<>();
                if (StringUtils.isNotEmpty(teachers)) {
                    List<String> teacherList = Arrays.asList(teachers.split(";"));
                    teacherList.forEach(s -> {
                        SupervisionCourseTableDetailTeacherVo teacherVo = new SupervisionCourseTableDetailTeacherVo();
                        String[] arrayTeacher = s.split(",", SIX);
                        teacherVo.setTeacherId(arrayTeacher[0]);
                        teacherVo.setTeacherNo(arrayTeacher[1]);
                        teacherVo.setTeacherName(arrayTeacher[teacherNameIndex]);
                        teacherVo.setCollegeId(arrayTeacher[teacherCollegeIndexIndex]);
                        teacherVo.setCollegeName(arrayTeacher[teacherCollegeNameIndex]);
                        teacherVo.setTeacherTitle(arrayTeacher[teacherTitleIndex]);
                        teacherVos.add(teacherVo);
                    });
                }
                tableAndDetailVo.setTeachers(teacherVos);
                tableAndDetailVoList.add(tableAndDetailVo);
            });
        }

        return tableAndDetailVoList;
    }

    private String getCourseTableDetailIds(SupervisionCourseAndDetailQueryParam param) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", param.getSchoolYear());
        paramMap.put("term", param.getTerm());
        paramMap.put("courseId", param.getCourseId());
        paramMap.put("studentType", param.getStudentType());
//        paramMap.put("collegeId", param.getCollegeId());
        paramMap.put("courseDate", DateUtils.formatDate(DATE, DateUtils.stringToDate(DateUtils.DATE_TIME, param.getNowTime())));
        paramMap.put("teachers", param.getTeachers());

        StringBuilder sb = new StringBuilder();
        sb.append("select GROUP_CONCAT(DISTINCT a.courseTableDetailId ORDER BY a.courseTableDetailId SEPARATOR ',' ) as courseTableDetailIds");
        sb.append(" from (");
        sb.append("SELECT ct.course_id as courseId,ct.course_name as courseName,ctd.id,");
        sb.append("GROUP_CONCAT(DISTINCT CONCAT(ctdt.teacher_id,',',ctdt.teacher_no,',',ctdt.teacher_name) " +
                "ORDER BY ctdt.teacher_id SEPARATOR ';') as teachers,");
        sb.append("ctd.id as courseTableDetailId");
        sb.append(" from tb_course_table ct");
        sb.append(" join tb_course_table_detail ctd on ctd.course_table_id = ct.id");
        sb.append(" join tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd.id");
        sb.append(" JOIN tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = ctd.id");
        sb.append(" where ctdru.room_id IS NOT NULL AND ctdru.has_live = 1");
        sb.append(" and ct.school_year=:schoolYear and ct.term = :term");
        sb.append(" and ct.course_id = :courseId and ct.student_type = :studentType");
        if (Objects.nonNull(param.isIsNeedFilterPoliticalCourse()) && param.isIsNeedFilterPoliticalCourse()) {
            sb.append(" AND ct.course_id not in (SELECT DISTINCT course_id " +
                    " FROM `tb_course_expansion` where expansion_key='politicalCourse')");
        }
        if (isTeacherCollege.equals(param.getCollegeType())) {
            if (StringUtils.isNotBlank(param.getCollegeId())) {
                sb.append(" and ctdt.teacher_college_id = :collegeId ");
                paramMap.put("collegeId", param.getCollegeId());
            }
            if (StringUtils.isNotBlank(param.getCollegeIds())) {
                // 只有一个元素的时候用=，多个元素用in
                if (param.getCollegeIds().split(",").length == 1) {
                    sb.append(" and ctdt.teacher_college_id  = :collegeIds ");
                    paramMap.put("collegeIds", param.getCollegeIds());
                } else {
                    sb.append(" and ctdt.teacher_college_id  in (:collegeIds) ");
                    paramMap.put("collegeIds", Arrays.asList(param.getCollegeIds().split(",")));
                }
            }
        }
        if (isCourseTableCollege.equals(param.getCollegeType())) {
            if (StringUtils.isNotBlank(param.getCollegeId())) {
                sb.append(" and ct.college_id = :collegeId ");
                paramMap.put("collegeId", param.getCollegeId());
            }
            if (StringUtils.isNotBlank(param.getCollegeIds())) {
                // 只有一个元素的时候用=，多个元素用in
                if (param.getCollegeIds().split(",").length == 1) {
                    sb.append(" and ct.college_id  = :collegeIds ");
                    paramMap.put("collegeIds", param.getCollegeIds());
                } else {
                    sb.append(" and ct.college_id  in (:collegeIds) ");
                    paramMap.put("collegeIds", Arrays.asList(param.getCollegeIds().split(",")));
                }
            }
        }
        sb.append(" AND ctd.course_date <= :courseDate ");
        sb.append(" group by ct.course_id,ct.course_name,ctd.id) a");
        sb.append(" where a.teachers = :teachers");
        sb.append(" group by a.courseId,a.courseName,a.teachers");

        Query query = entityManager.createNativeQuery(sb.toString());
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> resList = query.getResultList();
        if (CollectionUtils.isEmpty(resList)) {
            return null;
        }
        Map<String, Object> resMap = resList.get(0);
        return resMap.get("courseTableDetailIds").toString();
    }

    private List<Map<String, Object>> getCourseTableAndDetailsByDetailIds(List<String> detailIds) {
        String sb = "select ct.id as courseTableId,ctd.id as courseTableDetailId," +
                "        ct.group_id as groupId,g.group_name as className,g.class_nick_name as classNickName," +
                "                g.class_composition_name as classCompositionName,cc.course_category_name as courseAttrName,ctd.segment," +
                "                (select count(1) from tb_group_member gm where gm.group_id=ct.group_id " +
                "and group_member_status=0 and gm.learning_method_code =1) as studentCount," +
                "        ctd.course_date as courseDate,CONCAT(ctd.course_date,' ',ctd.segment_start_time) as segmentStartTime," +
                "        CONCAT(ctd.course_date,' ',ctd.segment_end_time) as segmentEndTime," +
                "        (select GROUP_CONCAT(DISTINCT CONCAT(ctdt.teacher_id,','," +
                "        if(ctdt.teacher_no is null,'',ctdt.teacher_no),','," +
                "        if(ctdt.teacher_name is null,'',ctdt.teacher_name),','," +
                "        if(ctdt.teacher_college_id is null,'',ctdt.teacher_college_id),','," +
                "        if(ctdt.teacher_college_name is null,'',ctdt.teacher_college_name),','," +
                "        if(ctdt.teacher_title is null,'',ctdt.teacher_title)) ORDER BY ctdt.teacher_id SEPARATOR ';')" +
                "        from tb_course_table_detail_teacher ctdt" +
                "        where ctdt.course_table_detail_id = ctd.id" +
                "        group by course_table_detail_id) as teachers," +
                "        (select  GROUP_CONCAT(DISTINCT CONCAT(" +
                "                ctdru.room_id,','," +
                "                ctdru.room_name) ORDER BY ctdru.room_id SEPARATOR ';')" +
                "        from tb_course_table_detail_room_user ctdru" +
                "        where ctdru.course_table_detail_id = ctd.id and ctdru.room_id is not null and ctdru.has_live=1" +
                "        group by course_table_detail_id" +
                ") as rooms," +
                "       ctd.source_data_source as sourceDataSource,ctd.source_data_source_name as sourceDataSourceName" +
                "        from tb_course_table ct" +
                "        join tb_course_table_detail ctd on ctd.course_table_id = ct.id" +
                "        join tb_group g on g.id = ct.group_id" +
                "        left join tb_course_category cc on cc.id = ct.course_category_id" +
                "        where ctd.id in (:courseTableDetailIds)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseTableDetailIds", detailIds);

        Query query = entityManager.createNativeQuery(sb);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (List<Map<String, Object>>) query.getResultList();
    }

    public ListenCourseInfoVo getListenCourseInfoByCourseDetailId(String courseTableDetailId,
                                                                  Integer collegeType,
                                                                  String collegeId,
                                                                  String teacherId,
                                                                  Boolean hasLive) {
        ListenCourseInfoVo listenCourseInfoVo = new ListenCourseInfoVo();
        if (!isTeacherCollege.equals(collegeType) && !isCourseTableCollege.equals(collegeType)) {
            return listenCourseInfoVo;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT a.studentType,a.courseId,a.courseName,a.teachers," +
                "GROUP_CONCAT(DISTINCT CONCAT(a.courseTableDetailId,',',a.courseDate,',',a.segmentStartTime,',',a.segmentEndTime)" +
                " ORDER BY a.courseTableDetailId SEPARATOR ';' ) as courseTableDetailIds" +
                " from (");

        sb.append("SELECT ct.course_id as courseId,ct.course_name as courseName,ctd.id,ct.student_type as studentType,");
        sb.append("GROUP_CONCAT(DISTINCT CONCAT(ctdt.teacher_id,',',ctdt.teacher_no,',',ctdt.teacher_name) " +
                "ORDER BY ctdt.teacher_id SEPARATOR ';') as teachers,");
        sb.append("ctd.id as courseTableDetailId,ctd.course_date as courseDate," +
                "ctd.segment_start_time as segmentStartTime,ctd.segment_end_time as segmentEndTime");
        sb.append(" FROM tb_course_table ct");
        sb.append(" JOIN tb_course_table_detail ctd on ctd.course_table_id = ct.id");
        sb.append(" JOIN tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd.id");
        sb.append(" JOIN tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = ctd.id");
        sb.append(" WHERE ctdru.room_id IS NOT NULL");
        if (ObjectUtils.isEmpty(hasLive) || (ObjectUtils.isNotEmpty(hasLive) && hasLive)) {
            sb.append(" AND ctdru.has_live = 1");
        }
        sb.append(" AND ct.course_id not in (SELECT DISTINCT course_id  FROM `tb_course_expansion` where expansion_key='politicalCourse')");
        sb.append(" AND ctd.id = :courseTableDetailId");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseTableDetailId", courseTableDetailId);
        if (ObjectUtils.isNotEmpty(collegeType)) {
            if (isTeacherCollege.equals(collegeType)) {
                sb.append(" AND ctdt.teacher_college_id=:collegeId");
                paramMap.put("collegeId", collegeId);
            }

            if (isCourseTableCollege.equals(collegeType)) {
                sb.append(" AND ct.college_id=:collegeId");
                paramMap.put("collegeId", collegeId);
            }
        }
        sb.append(" GROUP BY ct.student_type,ct.course_id,ct.course_name,ctd.id");

        sb.append(") a where a.teachers like :teacherId GROUP BY a.courseId,a.courseName,a.teachers");
        paramMap.put("teacherId", "%" + teacherId + "%");

        Query query = entityManager.createNativeQuery(sb.toString());
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> result = (List<Map<String, Object>>) query.getResultList();
        if (!result.isEmpty()) {
            Map<String, Object> row = result.get(0);
            listenCourseInfoVo.setCourseId(row.get("courseId").toString());
            listenCourseInfoVo.setCourseName(row.get("courseName").toString());
            listenCourseInfoVo.setStudentType(row.get("studentType").toString());
            listenCourseInfoVo.setTeachers(row.get("teachers").toString());
            listenCourseInfoVo.setCollegeId(collegeId);
            listenCourseInfoVo.setCollegeType(collegeType);
            College college = collegeRepository.findById(collegeId).orElse(null);
            listenCourseInfoVo.setCollegeName(ObjectUtils.isEmpty(college) ? "" : college.getCollegeName());
        }

        return listenCourseInfoVo;
    }


    public CanListenCourseTableDetailResourceVo getCanListenCourseTableDetailResourceVo(String courseTableDetailId) {
        CourseTableDetail courseTableDetail = courseTableDetailRepository.findById(courseTableDetailId).orElse(null);
        if (ObjectUtils.isNotEmpty(courseTableDetail)) {
            CanListenCourseTableDetailResourceVo canListenCourseTableDetailResourceVo = new CanListenCourseTableDetailResourceVo();
            canListenCourseTableDetailResourceVo.setCourseTableId(courseTableDetail.getCourseTable().getId());
            canListenCourseTableDetailResourceVo.setCourseTableDetailId(courseTableDetail.getId());
            canListenCourseTableDetailResourceVo.setGroupId(courseTableDetail.getCourseTable().getGroup().getId());
            canListenCourseTableDetailResourceVo.setClassName(courseTableDetail.getCourseTable().getGroup().getGroupName());
            canListenCourseTableDetailResourceVo.setClassNickName(courseTableDetail.getCourseTable().getGroup().getClassNickName());
            canListenCourseTableDetailResourceVo.setClassCompositionName(courseTableDetail.getCourseTable().getGroup().getClassCompositionName());
            canListenCourseTableDetailResourceVo.setCourseAttrName(courseTableDetail.getCourseTable().getCourseCategory().getCourseCategoryName());
            String courseTableDetailSegmentStartTime =
                    DateUtils.formatDate(DATE, courseTableDetail.getCourseDate()) + " " +
                            DateUtils.formatDate(TIME_SECOND, courseTableDetail.getSegmentStartTime());
            canListenCourseTableDetailResourceVo.setCourseTableDetailSegmentStartTime(courseTableDetailSegmentStartTime);
            String courseTableDetailSegmentEndTime =
                    DateUtils.formatDate(DATE, courseTableDetail.getCourseDate()) + " " +
                            DateUtils.formatDate(TIME_SECOND, courseTableDetail.getSegmentEndTime());
            canListenCourseTableDetailResourceVo.setCourseTableDetailSegmentEndTime(courseTableDetailSegmentEndTime);
            canListenCourseTableDetailResourceVo.setSegment(courseTableDetail.getSegment());
            canListenCourseTableDetailResourceVo.setCourseDate(DateUtils.formatDate(DATE, courseTableDetail.getCourseDate()));
            canListenCourseTableDetailResourceVo.setCourseDateOrder(canListenCourseTableDetailResourceVo.getCourseTableDetailSegmentStartTime());
            boolean isLive = isLive(canListenCourseTableDetailResourceVo.getCourseTableDetailSegmentStartTime(),
                    canListenCourseTableDetailResourceVo.getCourseTableDetailSegmentEndTime());
            canListenCourseTableDetailResourceVo.setLive(isLive);
            int studentNum = groupMemberRepository.countByGroupId(courseTableDetail.getCourseTable().getGroup().getId());
            canListenCourseTableDetailResourceVo.setStudentCount(String.valueOf(studentNum));
            canListenCourseTableDetailResourceVo.setTeachers(getCourseTableDetailTeacherResourceList(courseTableDetail));
            canListenCourseTableDetailResourceVo.setRooms(getCourseTableDetailRoomResourceList(courseTableDetail));
            StudentType studentType = courseTableDetail.getCourseTable().getStudentType();
            if (ObjectUtils.isNotEmpty(studentType)) {
                canListenCourseTableDetailResourceVo.setStudentTypeIndex(studentType.getIndex());
                canListenCourseTableDetailResourceVo.setStudentTypeName(studentType.getName());
            }
            return canListenCourseTableDetailResourceVo;
        } else {
            return null;
        }

    }

    private List<CourseTableDetailRoomResource> getCourseTableDetailRoomResourceList(CourseTableDetail courseTableDetail) {
        List<CourseTableDetailRoomUser> courseTableDetailRoomUserList = courseTableDetail.getCourseTableDetailRoomUserList();
        if (!CollectionUtils.isEmpty(courseTableDetailRoomUserList)) {
            List<CourseTableDetailRoomResource> courseTableDetailRoomResourceList = new ArrayList<>();
            for (CourseTableDetailRoomUser courseTableDetailRoomUser : courseTableDetailRoomUserList) {
                CourseTableDetailRoomResource courseTableDetailRoomResource = new CourseTableDetailRoomResource();
                courseTableDetailRoomResource.setRoomId(courseTableDetailRoomUser.getRoomId());
                courseTableDetailRoomResource.setRoomName(courseTableDetailRoomUser.getRoomName());
                courseTableDetailRoomResourceList.add(courseTableDetailRoomResource);
            }
            return courseTableDetailRoomResourceList;
        } else {
            return new ArrayList<>();
        }
    }

    private List<CourseTableDetailTeacherResource> getCourseTableDetailTeacherResourceList(CourseTableDetail courseTableDetail) {
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetail.getCourseTableDetailTeacherList();
        if (!CollectionUtils.isEmpty(courseTableDetailTeacherList)) {
            List<CourseTableDetailTeacherResource> courseTableDetailTeacherResourceList = new ArrayList<>();
            for (CourseTableDetailTeacher courseTableDetailTeacher : courseTableDetailTeacherList) {
                CourseTableDetailTeacherResource courseTableDetailTeacherResource = new CourseTableDetailTeacherResource();
                courseTableDetailTeacherResource.setTeacherId(courseTableDetailTeacher.getTeacherId());
                courseTableDetailTeacherResource.setTeacherName(courseTableDetailTeacher.getTeacherName());
                courseTableDetailTeacherResource.setTeacherNo(courseTableDetailTeacher.getTeacherNo());
                courseTableDetailTeacherResource.setCollegeName(courseTableDetailTeacher.getTeacherCollegeName());
                courseTableDetailTeacherResource.setCollegeId(courseTableDetailTeacher.getTeacherCollegeId());
                courseTableDetailTeacherResourceList.add(courseTableDetailTeacherResource);
            }
            return courseTableDetailTeacherResourceList;
        } else {
            return new ArrayList<>();
        }
    }


    private boolean isLive(String courseTableDetailSegmentStartTime, String courseTableDetailSegmentEndTime) {
        Date now = new Date();
        Date start = DateUtils.stringToDate(DateUtils.DATE_TIME, courseTableDetailSegmentStartTime);
        Date end = DateUtils.stringToDate(DateUtils.DATE_TIME, courseTableDetailSegmentEndTime);
        return now.after(start) && now.before(end);
    }

}

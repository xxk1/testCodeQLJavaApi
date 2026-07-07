package com.lztech.site.service.superviseevaluation;

import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.coursecategory.CourseCategoryRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetail.specification.CourseTableDetailSpecification;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.terms.TermRepository;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.superviseevaluation.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.NEED_DISTINGUISH_COURSE_STUDENT_TYPE;
import static com.lztech.site.constants.Constant.SUPERVISE_COLLEGE_TYPE_COURSE_TABLE_COLLEGE;

@Service
public class SuperviseEvaluationService {
    private final Integer living = 1;

    @Autowired
    private TermRepository termRepository;
    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private EntityManager entityManager;
    @Resource
    private CourseTableRepository courseTableRepository;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;


    //region 根据学院id、课程/老师姓名等条件查询督导听课老师课程列表信息
    public ResponseEntity getSuperviseCoursePage(SupervisionCourseQueryParam param) {
        SupervisionCourseListPage supervisionCoursePage = initSupervisionCourseListPage();
        if (living.equals(param.getLive())) {
            getLiveSuperviseCoursePage(param, supervisionCoursePage);
        } else {
            getAllSuperviseCoursePage(param, supervisionCoursePage);
        }

        return new ResponseEntity(supervisionCoursePage, HttpStatus.OK);
    }

    private SupervisionCourseListPage getAllSuperviseCoursePage(SupervisionCourseQueryParam param,
                                                                SupervisionCourseListPage supervisionCoursePage) {
        Term term = termRepository.findBySchoolYearAndTerm(param.getSchoolYear(), param.getTerm());
        if (Objects.nonNull(term)) {
            Map<String, Object> paramMap = new HashMap<>();
            String courseTableIdsSql = buildCourseTableIdsSql(param, term.getStartDate() == null ? "" : DateUtils.formatDate(DateUtils.DATE_TIME,
                    term.getStartDate()), paramMap);

            return getSupervisionCourseListPage(param, supervisionCoursePage, courseTableIdsSql, paramMap);
        }

        return supervisionCoursePage;
    }

    private SupervisionCourseListPage getSupervisionCourseListPage(SupervisionCourseQueryParam param,
                                                                   SupervisionCourseListPage supervisionCoursePage,
                                                                   String courseTableIdsSql,
                                                                   Map<String, Object> paramMap) {
        String courseTableCountSql = buildCourseTableCountSql(courseTableIdsSql);
        Query queryCount = entityManager.createNativeQuery(courseTableCountSql);
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> countMap = (Map<String, Object>) queryCount.getResultList().get(0);
        int totalCount = ((BigInteger) countMap.get("totalcount")).intValue();
        supervisionCoursePage.setTotalCount(totalCount);
        if (totalCount == 0) {
            return supervisionCoursePage;
        }
        Integer offset = (param.getPage() - 1) * param.getPageSize();
        Integer pageSize = param.getPageSize();
        String courseTableSql = buildCourseTablePageSql(courseTableIdsSql, offset, pageSize, param, paramMap);
        Query queryData = entityManager.createNativeQuery(courseTableSql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(SupervisionCourseTableVo.class));
        List<SupervisionCourseTableVo> supervisionCourseTableVos = queryData.getResultList();
        List<String> courseTableIdList =
                supervisionCourseTableVos.stream().map(SupervisionCourseTableVo::getCourseTableId).collect(Collectors.toList());
        String courseTableIds = getCourseTableIds(supervisionCourseTableVos);
        List<SupervisionGroupVo> classNames = getClassNames(courseTableIdList);
        List<SupervisionCourseVo> supervisionCourseVos = new ArrayList<>();
        List<CourseTable> courseTableList = courseTableRepository.findByIdIn(courseTableIdList);
        supervisionCourseTableVos.forEach(supervisionCourseTableVo -> {
            CourseTable courseTable = courseTableList.stream()
                    .filter(t -> t.getId().equals(supervisionCourseTableVo.getCourseTableId()))
                    .findFirst()
                    .orElse(null);
            if (Objects.isNull(courseTable)) {
                return;
            }
            CourseCategory courseCategory = courseTable.getCourseCategory();
            SupervisionCourseVo supervisionCourseVo = new SupervisionCourseVo();
            supervisionCourseVo.setCourseName(supervisionCourseTableVo.getCourseName());
            supervisionCourseVo.setTeachers(supervisionCourseTableVo.getTeachers());
            supervisionCourseVo.setLive(false);
            if (classNames.size() > 0) {
                SupervisionGroupVo groupVo = classNames
                        .stream()
                        .filter(supervisionGroupVo -> supervisionGroupVo.getCourseTableId().equals(supervisionCourseTableVo.getCourseTableId()))
                        .findFirst()
                        .orElse(null);
                supervisionCourseVo.setClassName(Objects.nonNull(groupVo) ? groupVo.getClassName() : "");
                supervisionCourseVo.setSource(groupVo.getSource());
                supervisionCourseVo.setClassNickName(groupVo.getClassNickName());
                supervisionCourseVo.setClassCompositionName(groupVo.getClassCompositionName());
            }
            supervisionCourseVo.setCourseTableId(supervisionCourseTableVo.getCourseTableId());
            supervisionCourseVo.setStudentType(supervisionCourseTableVo.getStudentType());
            supervisionCourseVo.setCourseAttrName(Objects.isNull(courseCategory) ? null : courseCategory.getCourseCategoryName());
            supervisionCourseVos.add(supervisionCourseVo);
        });

        supervisionCoursePage.setPageCount((int) Math.ceil(totalCount / (pageSize * 1.0)));
        supervisionCoursePage.setPage(param.getPage());

        List<SupervisionCourseTableDetailVo> detailVos = getLiveCourseTableDetails(courseTableIds, param.getNowTime(), param.getSchoolYear(),
                param.getTerm(), param.getCourseStudentTypes(), param.getIsDistinguishCourseStudentType(), param.getNeedFilterPoliticalCourse());
        buildSupervisionCourseVoLiveInfo(supervisionCourseVos, detailVos);
        supervisionCoursePage.setSupervisionCourseList(supervisionCourseVos);

        return supervisionCoursePage;
    }

    private List<SupervisionGroupVo> getClassNames(List<String> courseTableIds) {
        List<SupervisionGroupVo> supervisionGroupVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(courseTableIds)) {
            Map<String, Object> paramMap = new HashMap<>();
            String classNamesSql = getClassNamesSql(courseTableIds, paramMap);
            Query query = entityManager.createNativeQuery(classNamesSql);
            paramMap.forEach(query::setParameter);
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(SupervisionGroupVo.class));
            supervisionGroupVos = query.getResultList();
        }
        return supervisionGroupVos;
    }

    private String getClassNamesSql(List<String> courseTableIds, Map<String, Object> paramMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ct.id AS courseTableId,g.group_name AS className,g.source,g.class_nick_name as classNickName," +
                "g.class_composition_name as classCompositionName");
        sb.append(" FROM tb_course_table ct");
        sb.append(" INNER JOIN tb_group g ON g.id = ct.group_id");
        sb.append(" WHERE ct.id in ( :courseTableIds )");
        paramMap.put("courseTableIds", courseTableIds);
        return sb.toString();
    }

    private SupervisionCourseListPage getLiveSuperviseCoursePage(SupervisionCourseQueryParam param, SupervisionCourseListPage supervisionCoursePage) {
        Map<String, Object> paramMap = new HashMap<>();
        String courseTableIdsSql = buildCourseTableIdsSql(param, "", paramMap);

        return getSupervisionCourseListPage(param, supervisionCoursePage, courseTableIdsSql, paramMap);
    }

    private List<SupervisionCourseTableDetailVo> getLiveCourseTableDetails(String courseTableIds,
                                                                           String nowTime,
                                                                           String schoolYear,
                                                                           Integer term,
                                                                           String courseStudentTypes,
                                                                           String isDistinguishCourseStudentType,
                                                                           Boolean isNeedFilterPoliticalCourse) {
        List<SupervisionCourseTableDetailVo> detailVos = new ArrayList<>();
        if (!StringUtils.isEmpty(courseTableIds)) {
            Map<String, Object> paramMap = new HashMap<>();
            Map<String, Object> termMap = new HashMap<>();
            termMap.put("schoolYear", schoolYear);
            termMap.put("term", term);
            String liveCourseTableDetail = buildLiveCourseTableDetailSql(courseTableIds, nowTime, termMap, courseStudentTypes,
                    isDistinguishCourseStudentType, isNeedFilterPoliticalCourse, paramMap);
            Query queryCourseTableDetail = entityManager.createNativeQuery(liveCourseTableDetail);
            paramMap.forEach(queryCourseTableDetail::setParameter);
            queryCourseTableDetail.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(SupervisionCourseTableDetailVo.class));
            detailVos = queryCourseTableDetail.getResultList();
        }
        return detailVos;
    }

    private String getCourseTableIds(List<SupervisionCourseTableVo> supervisionCourseTableVos) {
        StringBuilder courseTableIds = new StringBuilder();
        if (supervisionCourseTableVos != null) {
            for (SupervisionCourseTableVo item : supervisionCourseTableVos) {
                courseTableIds.append("'").append(item.getCourseTableId()).append("',");
            }
            if (courseTableIds.length() > 0) {
                courseTableIds = new StringBuilder(courseTableIds.substring(0, courseTableIds.length() - 1));
            }
        }

        return courseTableIds.toString();
    }

    private SupervisionCourseListPage initSupervisionCourseListPage() {
        SupervisionCourseListPage supervisionCoursePage = new SupervisionCourseListPage();
        supervisionCoursePage.setPage(0);
        supervisionCoursePage.setPageCount(0);
        supervisionCoursePage.setTotalCount(0);
        supervisionCoursePage.setSupervisionCourseList(new ArrayList<>());
        return supervisionCoursePage;
    }

    private String buildCourseTableCountSql(String courseTableIdsSql) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(1) AS totalcount FROM");
        sb.append(" (");
        sb.append(courseTableIdsSql);
        sb.append(") coursetableids");

        return sb.toString();
    }

    private String buildCourseTablePageSql(String courseTableIdsSql, Integer offset, Integer pageSize,
                                           SupervisionCourseQueryParam param, Map<String, Object> paramMap) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select res.courseTableId,res.courseName,res.teachers ,res.studentType from (");
        sb.append(" SELECT coursetableids.studentType AS studentType, ctd1.course_table_id AS courseTableId, ctd1.course_name AS courseName,");
        sb.append(" (select max(CONCAT(course_date,' ',segment_start_time)) from tb_course_table_detail where CONCAT(course_date,' '," +
                " segment_start_time)<= :segmentMaxTime and course_table_id=ctd1.course_table_id ) as time,");
        sb.append("GROUP_CONCAT(DISTINCT ctdt.teacher_id,',',ctdt.teacher_no,',',ctdt.teacher_name ORDER BY ctdt.teacher_no) as teachers");
        sb.append(" FROM tb_course_table_detail ctd1");
        sb.append(" INNER JOIN tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd1.id");
        sb.append(" INNER JOIN ");
        sb.append("(");
        sb.append(courseTableIdsSql);
        sb.append(") coursetableids");

        sb.append(" ON coursetableids.course_table_id = ctd1.course_table_id");
        sb.append(" GROUP BY ctd1.course_table_id ) res");
        paramMap.put("segmentMaxTime", param.getNowTime());
        if ("courseDate".equals(param.getSortName())) {
            sb.append(" ORDER BY res.time desc,res.courseTableId");
        } else {
            sb.append(" ORDER BY res.courseTableId ");
        }
        if (Objects.isNull(param.getPagingEnabled()) || param.getPagingEnabled()) {
            sb.append(" LIMIT ").append(offset.toString()).append(",").append(pageSize.toString());
        }

        return sb.toString();
    }

    private String buildCourseTableIdsSql(SupervisionCourseQueryParam param, String termStartDate, Map<String, Object> paramMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ctd.course_table_id");
        buildCourseTableIdsPartSql(sb, param.getSchoolYear(), param.getTerm(), param.getCourseStudentTypes(),
                param.getIsDistinguishCourseStudentType(), param.getNeedFilterPoliticalCourse(), paramMap);

        if (StringUtils.isNotBlank(param.getCollegeId())) {
            if (SUPERVISE_COLLEGE_TYPE_COURSE_TABLE_COLLEGE.equals(param.getSuperviseCollegeType())) {
                sb.append(" AND ct.college_id = :collegeId ");
            } else {
                sb.append(" AND ctdt.teacher_college_id = :collegeId");
            }
            paramMap.put("collegeId", param.getCollegeId());

        }
        if (StringUtils.isNotBlank(param.getCourseId())) {
            sb.append(" and ct.course_id= :courseId ");
            paramMap.put("courseId", param.getCourseId());
        }

        if (Objects.nonNull(param.getOnlyGraduateCourse()) && param.getOnlyGraduateCourse() == 1) {
            sb.append(" and ct.student_type='1'");
        }
        if (StringUtils.isNotBlank(param.getCourseNameOrTeacherName())) {
            String courseNameOrTeacherName = CharactersReplace.replaceCharacters(param.getCourseNameOrTeacherName().trim());
            sb.append(" AND (ctd.course_name like :courseNameOrTeacherName OR ctdt.teacher_name like :courseNameOrTeacherName )");
            paramMap.put("courseNameOrTeacherName", "%" + courseNameOrTeacherName + "%");
        }

        if (StringUtils.isNotBlank(param.getTeacherNameOrNo())) {
            String teacherNameOrNo = CharactersReplace.replaceCharacters(param.getTeacherNameOrNo().trim());
            sb.append(" and (ctdt.teacher_name like :teacherNameOrNo or ctdt.teacher_no like :teacherNameOrNo ) ");
            paramMap.put("teacherNameOrNo", "%" + teacherNameOrNo + "%");
        }

        Date date = DateUtils.stringToDate(DateUtils.DATE_TIME, param.getNowTime());
        if (living.equals(param.getLive())) {
            buildLiveWhereSql(sb, date, paramMap);
        } else if (!StringUtils.isEmpty(termStartDate)) {
            Date startDate = DateUtils.stringToDate(DateUtils.DATE_TIME, termStartDate);
            sb.append(" AND ctd.course_date BETWEEN :startDate AND  :endDate ");
            paramMap.put("startDate", DateUtils.formatDate(DateUtils.DATE, startDate));
            paramMap.put("endDate", DateUtils.formatDate(DateUtils.DATE, date));
        }

        sb.append(" GROUP BY ctd.course_table_id");
        return sb.toString();
    }

    private String buildLiveCourseTableDetailSql(String ids, String nowTime, Map<String, Object> termMap,
                                                 String courseStudentTypes, String isDistinguishCourseStudentType,
                                                 Boolean isNeedFilterPoliticalCourse, Map<String, Object> paramMap) {
        String schoolYear = String.valueOf(termMap.get("schoolYear"));
        Integer term = Integer.valueOf(termMap.get("term").toString());
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ctd.course_table_id AS courseTableId,ctd.id AS courseTableDetailId");
        buildCourseTableIdsPartSql(sb, schoolYear, term, courseStudentTypes, isDistinguishCourseStudentType, isNeedFilterPoliticalCourse, paramMap);
        Date date = DateUtils.stringToDate(DateUtils.DATE_TIME, nowTime);
        buildLiveWhereSql(sb, date, paramMap);
        sb.append(" AND ctd.course_table_id in ( :courseTableIds)");
        paramMap.put("courseTableIds", Arrays.asList(ids.replace("'", "").split(",")));
        return sb.toString();

    }

    private void buildCourseTableIdsPartSql(StringBuilder sb, String schoolYear, Integer term, String courseStudentTypes,
                                            String isDistinguishCourseStudentType, Boolean isNeedFilterPoliticalCourse,
                                            Map<String, Object> paramMap) {
        sb.append(" ,ct.student_type as studentType");
        sb.append(" FROM tb_course_table_detail ctd");
        sb.append(" inner join tb_course_table ct on ctd.course_table_id=ct.id ");
        sb.append(" INNER JOIN tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd.id");
        sb.append(" INNER JOIN tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = ctd.id");
        sb.append(" WHERE ctd.source = 0 ");
        sb.append(" AND ctdru.room_id IS NOT NULL ");
        sb.append(" AND ctdru.has_live = 1 ");
        if (Objects.nonNull(isNeedFilterPoliticalCourse) && isNeedFilterPoliticalCourse) {
            sb.append(" and ct.course_id not in (SELECT DISTINCT course_id  FROM `tb_course_expansion` where expansion_key='politicalCourse')");
        }
        if (StringUtils.isNotBlank(schoolYear)) {
            schoolYear = CharactersReplace.replaceCharacters(schoolYear);
            sb.append(" and ct.school_year = :schoolYear ");
            paramMap.put("schoolYear", schoolYear);
        }
        if (Objects.nonNull(term)) {
            sb.append(" AND ct.term= :term");
            paramMap.put("term", term);

        }
        if (StringUtils.isNotBlank(isDistinguishCourseStudentType)
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(isDistinguishCourseStudentType)) {
            if (StringUtils.isNotBlank(courseStudentTypes)) {
                List<String> courseStudentTypeList = Arrays.asList(courseStudentTypes.split(","));
                sb.append(" and ct.student_type in ( :courseStudentTypeList )");
                paramMap.put("courseStudentTypeList", courseStudentTypeList);
            } else {
                sb.append(" and 1=0 ");
            }
        }


    }

    private void buildLiveWhereSql(StringBuilder sb, Date date, Map<String, Object> paramMap) {
        sb.append(" AND ctd.course_date = :courseDate ");
        paramMap.put("courseDate", DateUtils.formatDate(DateUtils.DATE, date));
        sb.append(" AND ctd.segment_start_time <= :nowTime ");
        sb.append(" AND ctd.segment_end_time > :nowTime ");
        String nowTime = DateUtils.formatDate(DateUtils.TIME, date);
        paramMap.put("nowTime", nowTime);
    }

    //endregion

    //region 根据课表id列表查询课程、老师和直播信息
    public ResponseEntity getCourseTablesByIdsAndNowTime(String courseTableIds,
                                                         String nowTime,
                                                         String schoolYear,
                                                         Integer term,
                                                         String courseStudentTypes,
                                                         String isDistinguishCourseStudentType) {

        String ids = getInCourseTablesIds(courseTableIds);
        List<SupervisionCourseVo> supervisionCourseVos = getCourseTableInfos(courseTableIds);
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> termMap = new HashMap<>();
        termMap.put("schoolYear", schoolYear);
        termMap.put("term", term);
        String liveCourseTableDetail = buildLiveCourseTableDetailSql(courseTableIds, nowTime, termMap, courseStudentTypes,
                isDistinguishCourseStudentType, true, paramMap);
        Query queryCourseTableDetail = entityManager.createNativeQuery(liveCourseTableDetail);
        paramMap.forEach(queryCourseTableDetail::setParameter);
        queryCourseTableDetail.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(SupervisionCourseTableDetailVo.class));
        List<SupervisionCourseTableDetailVo> detailVos = queryCourseTableDetail.getResultList();
        if (CollectionUtils.isEmpty(detailVos)) {
            return new ResponseEntity(supervisionCourseVos, HttpStatus.OK);
        }
        List<String> courseTableIdList = detailVos.stream().map(SupervisionCourseTableDetailVo::getCourseTableId).collect(Collectors.toList());
        List<CourseTable> courseTableList = courseTableRepository.findByIdIn(courseTableIdList);
        buildSupervisionCourseVoLiveInfo(supervisionCourseVos, detailVos);
        supervisionCourseVos.forEach(s -> {
            CourseTable courseTable = courseTableList.stream()
                    .filter(item -> item.getId().equals(s.getCourseTableId())).findFirst()
                    .orElse(null);
            if (Objects.isNull(courseTable)) {
                return;
            }
            CourseCategory courseCategory = courseTable.getCourseCategory();
            s.setCourseAttrName(Objects.isNull(courseCategory) ? null : courseCategory.getCourseCategoryName());
        });


        return new ResponseEntity(supervisionCourseVos, HttpStatus.OK);
    }

    private void buildSupervisionCourseVoLiveInfo(List<SupervisionCourseVo> supervisionCourseVos, List<SupervisionCourseTableDetailVo> detailVos) {
        supervisionCourseVos.forEach(supervisionCourseVo -> {
            List<SupervisionCourseTableDetailVo> filterDetailVos = detailVos.stream().filter(item ->
                            item.getCourseTableId().equals(supervisionCourseVo.getCourseTableId()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(filterDetailVos)) {
                supervisionCourseVo.setLiveCourseTableDetailId(filterDetailVos.get(0).getCourseTableDetailId());
                CourseTableDetail courseTableDetail = courseTableDetailRepository.getOne(supervisionCourseVo.getLiveCourseTableDetailId());
                supervisionCourseVo.setCourseTableDetailSegmentStartTime(
                        DateUtils.formatDate(DateUtils.DATE, courseTableDetail.getCourseDate()) + " " +
                                DateUtils.formatDate(DateUtils.TIME_SECOND, courseTableDetail.getSegmentStartTime()));
                supervisionCourseVo.setCourseTableDetailSegmentEndTime(
                        DateUtils.formatDate(DateUtils.DATE, courseTableDetail.getCourseDate()) + " " +
                                DateUtils.formatDate(DateUtils.TIME_SECOND, courseTableDetail.getSegmentEndTime()));
                supervisionCourseVo.setLive(true);
            } else {
                supervisionCourseVo.setLiveCourseTableDetailId("");
            }
        });
    }

    private List<SupervisionCourseVo> getCourseTableInfos(String courseTableIds) {
        List<SupervisionCourseVo> supervisionCourseVos = new ArrayList<>();
        List<String> courseTableIdList = Arrays.asList(courseTableIds.split(","));
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ctd1.course_table_id AS courseTableId, ctd1.course_name AS courseName,");
        sb.append("GROUP_CONCAT(DISTINCT ctdt.teacher_id,',',ctdt.teacher_no,',',ctdt.teacher_name ORDER BY ctdt.teacher_no) as teachers");
        sb.append(" FROM tb_course_table_detail ctd1 inner join tb_course_table ct on ctd1.course_table_id=ct.id ");
        sb.append(" INNER JOIN tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd1.id");
        sb.append(" WHERE ctd1.course_table_id in (:courseTableIdList) and ct.course_Id not in " +
                "(SELECT DISTINCT course_id  FROM `tb_course_expansion` where expansion_key='politicalCourse') ");

        sb.append(" GROUP BY ctd1.course_table_id");
        sb.append(" ORDER BY ctd1.course_table_id");

        Query queryData = entityManager.createNativeQuery(sb.toString());
        queryData.setParameter("courseTableIdList", courseTableIdList);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(SupervisionCourseTableVo.class));
        List<SupervisionCourseTableVo> supervisionCourseTableVos = queryData.getResultList();

        supervisionCourseTableVos.forEach(supervisionCourseTableVo -> {
            SupervisionCourseVo supervisionCourseVo = new SupervisionCourseVo();
            supervisionCourseVo.setCourseTableId(supervisionCourseTableVo.getCourseTableId());
            supervisionCourseVo.setCourseName(supervisionCourseTableVo.getCourseName());
            supervisionCourseVo.setTeachers(supervisionCourseTableVo.getTeachers());
            supervisionCourseVo.setLive(false);
            supervisionCourseVo.setLiveCourseTableDetailId("");
            supervisionCourseVos.add(supervisionCourseVo);
        });

        return supervisionCourseVos;
    }

    private String getInCourseTablesIds(String courseTableIds) {
        StringBuilder result = new StringBuilder();
        String[] ids = courseTableIds.split(",");
        for (String item : ids) {
            result.append("'").append(item).append("',");
        }

        if (StringUtils.isNotBlank(result.toString())) {
            result = new StringBuilder(result.substring(0, result.length() - 1));
        }

        return result.toString();
    }

    //endregion

    //region 根据课表id查询老师、直播及教室信息
    public ResponseEntity<SupervisionCourseResource> getCourseTableByIdAndNowTime(String courseTableId, String nowTime) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ctd.segment," +
                " ctd.id AS courseTableDetailId," +
                " CONCAT(CONCAT(date_format( ctd.course_date, '%Y-%m-%d'),' ')," +
                " DATE_FORMAT( ctd.segment_start_time, '%H:%i:%s')) as segmentStartTime, " +
                " CONCAT(CONCAT(date_format( ctd.course_date, '%Y-%m-%d'),' '),DATE_FORMAT( ctd.segment_end_time, '%H:%i:%s')) as segmentEndTime, " +
                " ctd.course_date AS courseDate," +
                " room_id AS roomId," +
                " room_name AS roomName," +
                " ct.group_id AS groupId,");
        sb.append("GROUP_CONCAT(DISTINCT ctdt.teacher_id,',',ctdt.teacher_no,',',ctdt.teacher_name ORDER BY ctdt.teacher_no) as teachers");
        sb.append(" FROM tb_course_table_detail ctd");
        sb.append(" INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id");
        sb.append(" INNER JOIN tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd.id");
        sb.append(" INNER JOIN tb_course_table_detail_room_user ctdru on ctdru.course_table_detail_id = ctd.id");
        sb.append(" WHERE ctd.course_table_id =  :courseTableId ");
        sb.append(" AND ctd.source = 0");
        sb.append(" AND ctdru.room_id IS NOT NULL");
        sb.append(" AND ctdru.has_live = 1");
        paramMap.put("courseTableId", courseTableId);
        Date date = DateUtils.stringToDate(DateUtils.DATE_TIME, nowTime);
        buildLiveWhereSql(sb, date, paramMap);
        Query queryData = entityManager.createNativeQuery(sb.toString());
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(SupervisionCourseTempResource.class));
        List<SupervisionCourseTempResource> resultList = queryData.getResultList();
        SupervisionCourseResource supervisionCourseResource = new SupervisionCourseResource();
        if (StringUtils.isNotEmpty(resultList.get(0).getCourseTableDetailId())) {
            SupervisionCourseTempResource supervisionCourseTempResource = resultList.get(0);
            supervisionCourseResource.setTeachers(supervisionCourseTempResource.getTeachers());
            supervisionCourseResource.setCourseDate(DateUtils.formatDate(DateUtils.DATE, supervisionCourseTempResource.getCourseDate()));
            supervisionCourseResource.setSegment(supervisionCourseTempResource.getSegment());
            supervisionCourseResource.setRoomId(supervisionCourseTempResource.getRoomId());
            supervisionCourseResource.setRoomName(supervisionCourseTempResource.getRoomName());
            supervisionCourseResource.setLiveCourseTableDetailId(supervisionCourseTempResource.getCourseTableDetailId());
            supervisionCourseResource.setCourseTableDetailSegmentStartTime(supervisionCourseTempResource.getSegmentStartTime());
            supervisionCourseResource.setCourseTableDetailSegmentEndTime(supervisionCourseTempResource.getSegmentEndTime());
            supervisionCourseResource.setGroupId(supervisionCourseTempResource.getGroupId());
            supervisionCourseResource.setLive(true);
        }
        return new ResponseEntity<>(supervisionCourseResource, HttpStatus.OK);
    }
    //endregion

    public ResponseEntity<SuperviseCourseTablePage> getCourseTablePage(SuperviseEvaluationResource superviseEvaluationResource) {
        SuperviseCourseTablePage superviseCourseTablePage = new SuperviseCourseTablePage();
        Pageable pageable = PageRequest.of(superviseEvaluationResource.getPage() - 1, superviseEvaluationResource.getPageSize());

        List<String> courseTableIds = Arrays.asList(superviseEvaluationResource.getCourseTableIds().split(","));
        List<String> collegeIds = Arrays.asList(superviseEvaluationResource.getCollegeIds().split(","));
        Page<CourseTableDetail> courseTableDetailPage = courseTableDetailRepository.findAll(CourseTableDetailSpecification
                        .searchCourseTablePage(collegeIds, courseTableIds, superviseEvaluationResource.getRoomId(),
                                superviseEvaluationResource.getSearchStr(), superviseEvaluationResource.getCourseStudentTypes(),
                                superviseEvaluationResource.getIsDistinguishCourseStudentType(),
                                superviseEvaluationResource.getSuperviseCollegeType(),superviseEvaluationResource.getCourseDate(),
                                superviseEvaluationResource.isIsNeedFilterPoliticalCourse(),superviseEvaluationResource.getRelatedScheduleTypes()),
                pageable);
        superviseCourseTablePage.setPageNum(courseTableDetailPage.getTotalPages());
        superviseCourseTablePage.setTotalCount((int) courseTableDetailPage.getTotalElements());
        superviseCourseTablePage.setCourseTableList(createCourseTableList(courseTableDetailPage.getContent()));
        return new ResponseEntity<>(superviseCourseTablePage, HttpStatus.OK);
    }

    private List<SuperviseCourseTable> createCourseTableList(List<CourseTableDetail> content) {
        List<SuperviseCourseTable> superviseCourseTableList = new ArrayList<>();
        content.forEach(courseTableDetail -> {
            CourseTable courseTable = courseTableDetail.getCourseTable();
            SuperviseCourseTable superviseCourseTable = new SuperviseCourseTable();
            superviseCourseTable.setStudentType(courseTable.getStudentType().getIndex());
            superviseCourseTable.setCourseTableDetailId(courseTableDetail.getId());
            superviseCourseTable.setCollegeName(courseTable.getCollegeName());
            superviseCourseTable.setCollegeId(courseTable.getCollegeId());
            superviseCourseTable.setCourseId(courseTable.getCourse().getId());
            superviseCourseTable.setCourseName(courseTable.getCourseName());
            superviseCourseTable.setRoomName(courseTableDetail.getCourseTableDetailRoomUserList().get(0).getRoomName());
            superviseCourseTable.setRoomId(courseTableDetail.getCourseTableDetailRoomUserList().get(0).getRoomId());
            superviseCourseTable.setSegment(courseTableDetail.getSegment());
            superviseCourseTable.setSchoolYear(courseTable.getSchoolYear());
            superviseCourseTable.setTerm(courseTable.getTerm());
            List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetail.getCourseTableDetailTeacherList();
            List<TeacherInfo> teacherInfos = new ArrayList<>();
            courseTableDetailTeacherList.forEach(courseTableDetailTeacher -> {
                TeacherInfo teacherInfo = new TeacherInfo();
                teacherInfo.setTeacherCollegeId(courseTableDetailTeacher.getTeacherCollegeId());
                teacherInfo.setTeacherCollegeName(courseTableDetailTeacher.getTeacherCollegeName());
                teacherInfo.setTeacherId(courseTableDetailTeacher.getTeacherId());
                teacherInfo.setTeacherName(courseTableDetailTeacher.getTeacherName());
                teacherInfo.setTeacherNo(courseTableDetailTeacher.getTeacherNo());
                teacherInfos.add(teacherInfo);
            });
            superviseCourseTable.setTeacherInfo(teacherInfos);
            superviseCourseTable.setTeachingClassId(courseTable.getGroup().getId());
            superviseCourseTable.setTeachingClassName(courseTable.getGroup().getGroupName());
            if (Objects.nonNull(courseTable.getCourseCategory())){
                superviseCourseTable.setCourseAttrName(courseTable.getCourseCategory().getCourseCategoryName());
            }
            superviseCourseTableList.add(superviseCourseTable);
        });
        return superviseCourseTableList;
    }

    public SuperviseCourseTablePage getPoliticalCourseCourseTablePage(SuperviseEvaluationResource superviseEvaluationResource) {

        SuperviseCourseTablePage superviseCourseTablePage = new SuperviseCourseTablePage();
        List<String> courseTableIds = new ArrayList<>();
        if (!StringUtils.isEmpty(superviseEvaluationResource.getCourseTableIds())) {
            courseTableIds = Arrays.asList(superviseEvaluationResource.getCourseTableIds().split(","));
        }
        Date nowDate = new Date();
        String day = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        Map<String, Object> paramMap = new HashMap<>();

        String countSql = getCountSql(superviseEvaluationResource, paramMap, day, nowTime, courseTableIds);
        Query queryCount = entityManager.createNativeQuery(countSql);
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        int count = ((BigInteger) resMap.get("allNumber")).intValue();
        if (count == 0) {
            superviseCourseTablePage.setTotalCount(0);
            superviseCourseTablePage.setPageNum(0);
            superviseCourseTablePage.setCourseTableList(new ArrayList<>());
        } else {
            String sql = "SELECT ctd.segment AS segment, ct.course_name AS courseName, ct.course_id AS courseId, ctd.id AS courseTableDetailId, " +
                    " tg.group_name AS teachingClassName, ct.group_id AS teachingClassId," +
                    " COUNT(DISTINCT CASE WHEN tgm.group_member_status = 0 THEN tgm.student_id  END) AS teachingClassStudentCount, " +
                    " ctdru.room_id AS roomId, " +
                    " ctdru.room_name AS roomName, ct.college_name AS collegeName, ct.college_id AS collegeId,ct.student_type as studentType " +
                    " FROM tb_course_table_detail ctd " +
                    " INNER JOIN tb_course_table ct ON ctd.course_table_id = ct.id " +
                    " INNER JOIN tb_course c ON ct.course_id = c.id " +
                    " INNER JOIN tb_course_table_detail_room_user ctdru ON ctd.id = ctdru.course_table_detail_id " +
                    " INNER JOIN tb_course_table_detail_teacher ctdt ON ctd.id = ctdt.course_table_detail_id " +
                    " INNER JOIN tb_group tg ON ct.group_id = tg.id " +
                    " LEFT JOIN tb_group_member tgm ON tgm.group_id = tg.id " +
                    " WHERE c.use_state = 1 AND ctd.course_date = :courseDate " +
                    " AND ctd.segment_start_time <= :segmentStartTime " +
                    " AND EXISTS (select course_id from tb_course_expansion where expansion_key = 'politicalCourse' and course_id=ct.course_id ) ";

            if (StringUtils.isNotBlank(superviseEvaluationResource.getIsDistinguishCourseStudentType())
                    && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(superviseEvaluationResource.getIsDistinguishCourseStudentType())) {
                if (StringUtils.isNotBlank(superviseEvaluationResource.getCourseStudentTypes())) {
                    List<String> courseStudentTypeList = Arrays.asList(superviseEvaluationResource.getCourseStudentTypes().split(","));
                    sql += " and ct.student_type in (:courseStudentTypeList )";
                    paramMap.put("courseStudentTypeList", courseStudentTypeList);
                }
            }

            paramMap.put("courseDate", day);
            paramMap.put("segmentStartTime", nowTime);
            if (courseTableIds.size() > 0) {
                sql += "AND ctd.id NOT IN (:courseTableIds) ";
                paramMap.put("courseTableIds", courseTableIds);
            }
            if (!StringUtils.isEmpty(superviseEvaluationResource.getRoomId())) {
                sql += "AND ctdru.room_id = :roomId ";
                paramMap.put("roomId", superviseEvaluationResource.getRoomId());
            }
            if (!StringUtils.isEmpty(superviseEvaluationResource.getSearchStr())) {
                sql += "AND (ctdt.teacher_name LIKE :searchStr OR ct.course_name LIKE :searchStr) ";
                paramMap.put("searchStr", "%" + superviseEvaluationResource.getSearchStr() + "%");
            }
            sql += "GROUP BY ctd.id ORDER BY c.course_code, ctd.segment_start_time LIMIT :start,:pageSize";
            paramMap.put("start", (superviseEvaluationResource.getPage() - 1) * superviseEvaluationResource.getPageSize());
            paramMap.put("pageSize", superviseEvaluationResource.getPageSize());

            Query queryData = entityManager.createNativeQuery(sql);
            paramMap.forEach(queryData::setParameter);
            queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(PoliticalcourseCourseTable.class));

            List<PoliticalcourseCourseTable> politicalcourseCourseTableList = queryData.getResultList();
            superviseCourseTablePage.setTotalCount(count);
            superviseCourseTablePage.setPageNum((int)Math.ceil(count*1.00 / superviseEvaluationResource.getPageSize()));
            superviseCourseTablePage.setCourseTableList(politicalcourseCourseTableList(politicalcourseCourseTableList));
        }
        return superviseCourseTablePage;
    }

    private List<SuperviseCourseTable> politicalcourseCourseTableList(List<PoliticalcourseCourseTable> politicalCourseCourseTableList) {
        if (CollectionUtils.isEmpty(politicalCourseCourseTableList)) {
            return new ArrayList<>();
        }
        List<String> courseTableDetailIdList = politicalCourseCourseTableList.stream()
                .map(PoliticalcourseCourseTable::getCourseTableDetailId).collect(Collectors.toList());
        List<CourseTableDetailTeacher> teacherList = courseTableDetailTeacherRepository
                .findByCourseTableDetailIdIn(courseTableDetailIdList);
        List<SuperviseCourseTable> superviseCourseTableList = new ArrayList<>();
        politicalCourseCourseTableList.forEach(politicalCourseCourseTable -> {
            SuperviseCourseTable superviseCourseTable = new SuperviseCourseTable();
            superviseCourseTable.setSegment(politicalCourseCourseTable.getSegment());
            superviseCourseTable.setCourseName(politicalCourseCourseTable.getCourseName());
            superviseCourseTable.setCourseId(politicalCourseCourseTable.getCourseId());
            superviseCourseTable.setCourseTableDetailId(politicalCourseCourseTable.getCourseTableDetailId());
            superviseCourseTable.setTeachingClassName(politicalCourseCourseTable.getTeachingClassName());
            superviseCourseTable.setTeachingClassId(politicalCourseCourseTable.getTeachingClassId());
            superviseCourseTable.setTeachingClassStudentCount(politicalCourseCourseTable.getTeachingClassStudentCount().intValue());
            superviseCourseTable.setRoomId(politicalCourseCourseTable.getRoomId());
            superviseCourseTable.setRoomName(politicalCourseCourseTable.getRoomName());
            superviseCourseTable.setCollegeId(politicalCourseCourseTable.getCollegeId());
            superviseCourseTable.setCollegeName(politicalCourseCourseTable.getCollegeName());
            List<CourseTableDetailTeacher> thisTeacherList = teacherList.stream()
                    .filter(t -> t.getCourseTableDetail().getId()
                            .equals(politicalCourseCourseTable.getCourseTableDetailId()))
                    .collect(Collectors.toList());
            List<TeacherInfo> teacherInfos = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(thisTeacherList)) {
                thisTeacherList.forEach(teacher -> {
                    TeacherInfo teacherInfo = new TeacherInfo();
                    teacherInfo.setTeacherCollegeId(teacher.getTeacherCollegeId());
                    teacherInfo.setTeacherCollegeName(teacher.getTeacherCollegeName());
                    teacherInfo.setTeacherId(teacher.getTeacherId());
                    teacherInfo.setTeacherName(teacher.getTeacherName());
                    teacherInfo.setTeacherNo(teacher.getTeacherNo());
                    teacherInfos.add(teacherInfo);
                });
            }
            superviseCourseTable.setTeacherInfo(teacherInfos);
            superviseCourseTable.setStudentType(politicalCourseCourseTable.getStudentType());
            superviseCourseTableList.add(superviseCourseTable);
        });
        return superviseCourseTableList;
    }

    private String getCountSql(SuperviseEvaluationResource superviseEvaluationResource,
                               Map<String, Object> paramMap, String day, String nowTime, List<String> courseTableIds) {
        String sql = "SELECT COUNT(*) AS allNumber FROM ( SELECT ctd.segment AS segment, ct.course_name AS courseName, " +
                "ct.course_id AS courseId, ctd.id AS courseTableDetailId, " +
                "tg.group_name AS teachingClassName, ct.group_id AS teachingClassId, ctdru.room_id AS roomId, " +
                "ctdru.room_name AS roomName, ct.college_name AS collegeName, ct.college_id AS collegeId " +
                "FROM tb_course_table_detail ctd " +
                "INNER JOIN tb_course_table ct ON ctd.course_table_id = ct.id " +
                "INNER JOIN tb_course c ON ct.course_id = c.id " +
                "INNER JOIN tb_course_table_detail_room_user ctdru ON ctd.id = ctdru.course_table_detail_id " +
                "INNER JOIN tb_course_table_detail_teacher ctdt ON ctd.id = ctdt.course_table_detail_id " +
                "INNER JOIN tb_group tg ON ct.group_id = tg.id " +
                "WHERE c.use_state = 1 AND ctd.course_date = :courseDate " +
                "AND ctd.segment_start_time <= :segmentStartTime " +
                "AND EXISTS (select course_id from tb_course_expansion where expansion_key = 'politicalCourse' and course_id=ct.course_id ) ";

        if (StringUtils.isNotBlank(superviseEvaluationResource.getIsDistinguishCourseStudentType())
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(superviseEvaluationResource.getIsDistinguishCourseStudentType())) {
            if (StringUtils.isNotBlank(superviseEvaluationResource.getCourseStudentTypes())) {
                List<String> courseStudentTypeList = Arrays.asList(superviseEvaluationResource.getCourseStudentTypes().split(","));
                sql += " and ct.student_type in (:courseStudentTypeList )";
                paramMap.put("courseStudentTypeList", courseStudentTypeList);
            }
        }

        paramMap.put("courseDate", day);
        paramMap.put("segmentStartTime", nowTime);
        if (courseTableIds.size() > 0) {
            sql += "AND ctd.id NOT IN (:courseTableIds) ";
            paramMap.put("courseTableIds", courseTableIds);
        }
        if (!StringUtils.isEmpty(superviseEvaluationResource.getRoomId())) {
            sql += "AND ctdru.room_id = :roomId ";
            paramMap.put("roomId", superviseEvaluationResource.getRoomId());
        }
        if (!StringUtils.isEmpty(superviseEvaluationResource.getSearchStr())) {
            sql += "AND (ctdt.teacher_name LIKE :searchStr OR ct.course_name LIKE :searchStr) ";
            paramMap.put("searchStr", "%" + superviseEvaluationResource.getSearchStr() + "%");
        }
        sql += "GROUP BY ctd.id ORDER BY c.course_code, ctd.segment_start_time ) a ";
        return sql;
    }


}

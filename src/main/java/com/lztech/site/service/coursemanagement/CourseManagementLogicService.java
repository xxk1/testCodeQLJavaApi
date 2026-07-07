package com.lztech.site.service.coursemanagement;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseCompletion;
import com.lztech.domain.model.course.CourseCover;
import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.course.CourseCompletionRepository;
import com.lztech.persistence.repositories.course.CourseTeachingTeamRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.coursemanagement.AiIntelligentCourseSummaryResource;
import com.lztech.site.viewmodel.coursemanagement.AiIntelligentCourseVo;
import com.lztech.site.viewmodel.coursemanagement.CourseByRunTermInfoVo;
import com.lztech.site.viewmodel.coursemanagement.CourseVo;
import com.lztech.site.viewmodel.coursetable.SegmentCourseTableDetailModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.coursetable.specification.CourseTableSpecification.getAITeacherCourseList;
import static java.util.stream.Collectors.toList;

@Service
public class CourseManagementLogicService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseManagementLogicService.class);

    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CourseTableRepository courseTableRepository;

    @Autowired
    private CourseCompletionRepository courseCompletionRepository;

    @Autowired
    private TermService termService;
    @Value("${studentType}")
    private String studentType;


    public AiIntelligentCourseSummaryResource getAiIntelligentCourseSummaryResource(String teacherId) {
        AiIntelligentCourseSummaryResource aiIntelligentCourseSummaryResource = new AiIntelligentCourseSummaryResource();
        List<AiIntelligentCourseVo> aiIntelligentCourseVoList = new ArrayList<>();
        List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository.findByTeacherIdOrderByCourseId(teacherId);
        List<AiIntelligentCourseVo> currentTermAIIntelligentCourseVoList = new ArrayList<>();
        List<AiIntelligentCourseVo> historyTermAIIntelligentCourseVoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(courseTeachingTeams)) {

            List<String> courseIdList = courseTeachingTeams.stream().map(courseTeachingTeam
                            -> courseTeachingTeam.getCourse().getId())
                    .collect(Collectors.toList());
            List<CourseCompletion> completionList = courseCompletionRepository.findByCourseIdList(courseIdList);

            String ids = courseIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(","));
            List<CourseByRunTermInfoVo> courseByRunTermInfoVoList = getCourseByRunTermInfoList(ids);
            Map<String, List<CourseByRunTermInfoVo>> courseByRunTermNumVoByCourseIdListMap =
                    courseByRunTermInfoVoList.stream().collect(Collectors.groupingBy(CourseByRunTermInfoVo::getCourseId));
            List<CourseVo> courseVoList = getCourseTableByCourseIds(ids);

            List<AiIntelligentCourseVo> finalAiIntelligentCourseVoList = aiIntelligentCourseVoList;
            courseTeachingTeams.stream().filter(courseTeachingTeam ->
                    courseTeachingTeam.getCourse().isUseState()).forEach(courseTeachingTeam -> {
                Course course = courseTeachingTeam.getCourse();
                AiIntelligentCourseVo aiIntelligentCourseVo = getAiIntelligentCourseVo(
                        courseTeachingTeam, course, courseVoList, completionList);
                finalAiIntelligentCourseVoList.add(aiIntelligentCourseVo);
            });
            //去重
            aiIntelligentCourseVoList = aiIntelligentCourseVoList.stream()
                    .collect(Collectors.collectingAndThen(Collectors.toCollection(()
                            -> new TreeSet<>(Comparator.comparing(AiIntelligentCourseVo::getCourseId))), ArrayList::new));
            for (AiIntelligentCourseVo aiIntelligentCourseVo : aiIntelligentCourseVoList) {
                String courseId = aiIntelligentCourseVo.getCourseId();
                List<CourseByRunTermInfoVo> courseByRunTermNumVoByCourseIdList = courseByRunTermNumVoByCourseIdListMap
                        .get(courseId);
                aiIntelligentCourseVo.setRunTermNum(courseByRunTermNumVoByCourseIdList.size());
                List<Map<String, String>> groupList = getOpenClassNum(courseByRunTermNumVoByCourseIdList, courseId);
                aiIntelligentCourseVo.setOpenClassNum(groupList.size());
                String serviceStudentNum = getServiceStudentNum(groupList);
                aiIntelligentCourseVo.setServiceStudentNum(Integer.valueOf(serviceStudentNum));
            }
            Term term = termService.getNowTerm();
            List<CourseTable> courseTableList = new ArrayList<>();
            if (ObjectUtils.isNotEmpty(term)) {
                courseTableList = courseTableRepository.findAll(
                        getAITeacherCourseList(teacherId, term.getSchoolYear(), term.getTerm().getIndex(), courseIdList)
                );
            }
            Map<String, List<CourseTable>> courseTableByCourseListMap =
                    courseTableList.stream().collect(Collectors.groupingBy(courseTable -> courseTable.getCourse().getId()));


            for (AiIntelligentCourseVo aiIntelligentCourseVo : aiIntelligentCourseVoList) {
                List<CourseTable> courseTableByCourseList = courseTableByCourseListMap.get(aiIntelligentCourseVo.getCourseId());

                if (CollectionUtils.isNotEmpty(courseTableByCourseList)) {

                    updateAiIntelligentCourseCourseTableData(teacherId, aiIntelligentCourseVo, courseTableByCourseList);

                    currentTermAIIntelligentCourseVoList.add(aiIntelligentCourseVo);
                } else {
                    historyTermAIIntelligentCourseVoList.add(aiIntelligentCourseVo);
                }
            }
        }
        aiIntelligentCourseSummaryResource.setCurrentTermAIIntelligentCourseVoList(currentTermAIIntelligentCourseVoList);
        aiIntelligentCourseSummaryResource.setHistoryTermAIIntelligentCourseVoList(historyTermAIIntelligentCourseVoList);
        return aiIntelligentCourseSummaryResource;
    }

    private void updateAiIntelligentCourseCourseTableData(
            String teacherId, AiIntelligentCourseVo aiIntelligentCourseVo, List<CourseTable> courseTableByCourseList) {
        CourseTable firstCourseTable = courseTableByCourseList.get(0);
        Integer studentType = Objects.isNull(firstCourseTable.getStudentType()) ? null : firstCourseTable.getStudentType().getIndex();
        aiIntelligentCourseVo.setStudentType(studentType);

        String courseCategoryId = firstCourseTable.getCourseCategory() == null ? null :
                String.valueOf(firstCourseTable.getCourseCategory().getId());
        aiIntelligentCourseVo.setCourseCategoryId(courseCategoryId);

        String courseCategory = firstCourseTable.getCourseCategory() == null ? null :
                firstCourseTable.getCourseCategory().getCourseCategoryName();
        aiIntelligentCourseVo.setCourseCategory(courseCategory);

        List<CourseTableDetail> courseTableDetailList = courseTableByCourseList.stream()
                .flatMap(courseTable -> courseTable.getCourseTableDetailList().stream()).collect(toList());
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetailList.stream()
                .flatMap(courseTableDetail -> courseTableDetail.getCourseTableDetailTeacherList().stream())
                .filter(courseTableDetailTeacher -> StringUtils.isNotBlank(courseTableDetailTeacher.getTeacherId()))
                .sorted(Comparator.comparing(CourseTableDetailTeacher::getTeacherId))
                .collect(toList());

        aiIntelligentCourseVo.setTeacherName(getTeacherNameList(courseTableByCourseList, courseTableDetailList, courseTableDetailTeacherList)
                .stream().map(String::trim).distinct().collect(Collectors.joining(",")));


        aiIntelligentCourseVo.setTeacherId(getTeacherIdList(courseTableByCourseList, courseTableDetailList, courseTableDetailTeacherList)
                .stream().map(String::trim).distinct().collect(Collectors.joining(",")));

        aiIntelligentCourseVo.setSchoolYear(firstCourseTable.getSchoolYear());

        aiIntelligentCourseVo.setTerm(firstCourseTable.getTerm());

        List<Group> groupList = getGroupList(courseTableByCourseList);
        int teachingClassStudentCount = Math.toIntExact(groupList
                .stream()
                .flatMap(group -> group.getGroupMemberList().stream())
                .filter(g -> GroupMemberStatus.NORMAL.equals(g.getGroupMemberStatus()))
                .map(GroupMember::getStudentId)
                .distinct()
                .count());

        aiIntelligentCourseVo.setTeachingClassStudentCount(teachingClassStudentCount);

        aiIntelligentCourseVo.setTeachingClassCount(groupList.size())
        ;
        List<String> courseTableDetailIdList =
                courseTableDetailList.stream().map(CourseTableDetail::getId).collect(toList());

        List<SegmentCourseTableDetailModel> segmentCourseTableDetailModelList =
                getSegmentCourseTableDetailModelList(courseTableDetailIdList, teacherId);

        aiIntelligentCourseVo.setTotalClassHour(segmentCourseTableDetailModelList.size());

        String nowTime = TimeUtils.getNowDateTime();

        int completeClassHour = Math.toIntExact(segmentCourseTableDetailModelList.stream()
                .filter(segmentCourseTableDetailModel ->
                        segmentCourseTableDetailModel.getSegmentEndTime().compareTo(nowTime) <= 0).count());

        aiIntelligentCourseVo.setCompleteClassHour(completeClassHour);
    }

    private AiIntelligentCourseVo getAiIntelligentCourseVo(
            CourseTeachingTeam courseTeachingTeam, Course course,
            List<CourseVo> courseVoList,
            List<CourseCompletion> completionList) {
        AiIntelligentCourseVo aiIntelligentCourseVo = new AiIntelligentCourseVo();
        aiIntelligentCourseVo.setCourseId(course.getId());
        aiIntelligentCourseVo.setCourseCode(course.getCourseCode());
        aiIntelligentCourseVo.setCourseName(course.getCourseName());
        aiIntelligentCourseVo.setCourseSource(course.getCourseSource().getIndex());
        aiIntelligentCourseVo.setCourseCollegeId(course.getCollege().getId());
        aiIntelligentCourseVo.setCourseCollegeCode(course.getCollege().getCollegeCode());
        aiIntelligentCourseVo.setCourseCollege(course.getCollege().getCollegeName());
        aiIntelligentCourseVo.setCourseTeachingTeamId(courseTeachingTeam.getId());
        aiIntelligentCourseVo.setTeacherType(courseTeachingTeam.getTeacherType().getIndex());
        aiIntelligentCourseVo.setExistCourseClass(courseVoList.stream().anyMatch(courseTable
                -> courseTable.getCourseId().equals(courseTeachingTeam.getCourse().getId())));
        CourseCover courseCover = courseTeachingTeam.getCourse().getCourseCover();
        if (ObjectUtils.isNotEmpty(courseCover)) {
            aiIntelligentCourseVo.setCoverInnerIp(courseCover.getInnerIp());
            aiIntelligentCourseVo.setCoverOuterIp(courseCover.getOuterIp());
            aiIntelligentCourseVo.setCoverFilepath(courseCover.getFilePath());
        }
        List<CourseCompletion> courseCompletionList = completionList.stream().filter(completion ->
                course.getId().equals(completion.getCourse().getId())).collect(Collectors.toList());
        double completion = 0.0;
        if (CollectionUtils.isNotEmpty(courseCompletionList)) {
            completion = courseCompletionList.stream().mapToDouble(com ->
                    com.getCourseContentEnum().getCompletionPercent()).sum();
        }
        aiIntelligentCourseVo.setCompleteRate(String.valueOf(completion));
        return aiIntelligentCourseVo;
    }

    private List<Group> getGroupList(List<CourseTable> courseTables) {
        List<Group> groupList = new ArrayList<>();
        for (CourseTable courseTable : courseTables.stream().filter(g -> GroupStatus.NORMAL.equals(g.getGroup().getGroupStatus()))
                .collect(toList())) {
            groupList.add(courseTable.getGroup());
        }
        groupList = groupList.stream().distinct().collect(toList());
        groupList.forEach(g -> {
            if (Objects.isNull(g.getSort())) {
                g.setSort(0);
            }
        });
        groupList.sort(Comparator.comparing(Group::getCreateTime).thenComparing(Group::getSort).thenComparing(Group::getId));
        return groupList;
    }

    private List<String> getTeacherNameList(List<CourseTable> courseTables,
                                            List<CourseTableDetail> courseTableDetailList,
                                            List<CourseTableDetailTeacher> courseTableDetailTeacherList) {
        List<String> teacherNameList = courseTableDetailTeacherList.stream().map(CourseTableDetailTeacher::getTeacherName).collect(toList());
        String courseTableTeacherName = courseTables.stream().map(CourseTable::getTeacherName).filter(StringUtils::isNotBlank).collect(Collectors.joining( ","));
        if (StringUtils.isNotBlank(courseTableTeacherName)){
            List<String> courseTableTeacherNameList = Arrays.asList(courseTableTeacherName.split(","));
            teacherNameList.addAll(courseTableTeacherNameList);
        }

        return teacherNameList;
    }

    private List<String> getTeacherIdList(List<CourseTable> courseTableByCourseList,
                                          List<CourseTableDetail> courseTableDetailList, List<CourseTableDetailTeacher> courseTableDetailTeacherList) {
        List<String> teacherIdList = courseTableDetailTeacherList.stream().map(CourseTableDetailTeacher::getTeacherId).distinct().collect(toList());
        String courseTableTeacherId = courseTableByCourseList.stream().map(CourseTable::getCourseTableTeacherId).filter(StringUtils::isNotBlank).collect(Collectors.joining( ","));
        if (StringUtils.isNotBlank(courseTableTeacherId)){
            List<String> courseTableTeacherIdList = Arrays.asList(courseTableTeacherId.split(","));
            teacherIdList.addAll(courseTableTeacherIdList);
        }
        return teacherIdList;
    }

    public List<CourseVo> getCourseTableByCourseIds(String courseIds) {
        String sql = " SELECT " +
                " c.id courseId,c.course_name courseName  " +
                "FROM tb_course_table ct inner join tb_course c on c.id =ct.course_id where course_id in ( " + courseIds + ")";
        Query projectQuery = entityManager.createNativeQuery(sql);
        projectQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseVo.class));
        List<CourseVo> courseTables = (List<CourseVo>) projectQuery.getResultList();
        return courseTables;
    }

    public List<CourseByRunTermInfoVo> getCourseByRunTermInfoList(String courseIds) {
        String sql = "SELECT a.id as courseId, b.id as termId, b.school_year as schoolYear, b.term as term, " +
                "  date_format(b.start_date, '%Y-%m-%d') as startDate, date_format(b.end_date, '%Y-%m-%d')  as endDate, " +
                "  b.description as description FROM tb_course a, tb_term b WHERE " +
                "  ((b.start_date <= date_format(a.create_time, '%Y-%m-%d') AND b.end_date >= date_format(a.create_time, '%Y-%m-%d %H:%i:%s')) " +
                "  OR (b.start_date >= date_format(a.create_time, '%Y-%m-%d') AND b.start_date <= date_format(NOW(), '%Y-%m-%d %H:%i:%s'))) " +
                "  and a.id IN (" + courseIds + ");";
        Query projectQuery = entityManager.createNativeQuery(sql);
        projectQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseByRunTermInfoVo.class));
        List<CourseByRunTermInfoVo> courseByRunTermInfoVoList = (List<CourseByRunTermInfoVo>) projectQuery.getResultList();
        return courseByRunTermInfoVoList;
    }


    public List<SegmentCourseTableDetailModel> getSegmentCourseTableDetailModelList(List<String> courseTableDetailIdList, String userId) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = "SELECT DISTINCT " +
                " a.id, " +
                " date_format(a.course_date,'%Y-%m-%d') as courseDate, " +
                " a.course_name as courseName, " +
                " a.course_table_id as courseTableId, " +
                " a.`week` as `week`, " +
                " a.week_num as weekNum, " +
                " a.course_type_id  as courseTypeId, " +
                " a.course_type_name as courseTypeName, " +
                " a.source as source, " +
                " a.course_kind as courseKind, " +
                " CONCAT(CONCAT(date_format(a.course_date,'%Y-%m-%d'),' '),c.start_time) as segmentStartTime, " +
                "  CONCAT(CONCAT(date_format(a.course_date,'%Y-%m-%d'),' '),c.end_time) as segmentEndTime " +
                "FROM " +
                " `tb_course_table_detail` a " +
                " INNER JOIN tb_course_segment b ON a.id = b.course_table_detail_id " +
                " INNER JOIN tb_segment c ON b.build_id = c.buildid  " +
                " INNER JOIN tb_course_table_detail_teacher d on a.id = d.course_table_detail_id " +
                " AND c.segment = b.segment  " +
                "WHERE " +
                " a.id IN (:courseTableDetailIdList ) and d.teacher_id= :teacherId ; ";
        paramMap.put("courseTableDetailIdList", courseTableDetailIdList);
        paramMap.put("teacherId", userId);
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(SegmentCourseTableDetailModel.class));
        return (List<SegmentCourseTableDetailModel>) query.getResultList();
    }

    public List<Map<String, String>> getOpenClassNum(List<CourseByRunTermInfoVo> courseByRunTermInfoVoList, String courseId) {
        String schoolYearResult = courseByRunTermInfoVoList.stream().map(courseByRunTermInfoVo ->
                "'" + courseByRunTermInfoVo.getSchoolYear() + "'," +
                        courseByRunTermInfoVo.getTerm()).collect(Collectors.joining("),("));
        String sql = "select b.id as id,b.group_no as groupNo,b.group_name as  groupName from tb_course_table  a  " +
                " INNER JOIN tb_group  b on a.group_id = b.id " +
                "where (a.school_year, a.term) in ((" + schoolYearResult + ")) and a.course_id = '" + courseId + "' " +
                "and b.group_status = 0 ";
        Map<String, Object> paramMap = new HashMap<>();
        if (!studentType.equals("-1")) {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql += " and a.student_type = :studentType ";
            paramMap.put("studentType", studentTypeQuery);
        }

        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return (List<Map<String, String>>) query.getResultList();
    }

    public String getServiceStudentNum(List<Map<String, String>> groupList) {

        String groupIdResult = groupList.stream().map(groupMap -> groupMap.get("id")).collect(Collectors.joining("' , '"));
        String sql = "SELECT  count(DISTINCT a.student_id) as memberNum from tb_group_member a  " +
                "where a.group_id in ('" + groupIdResult + "') and a.group_member_status='0' ";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) query.getResultList().get(0);
        int count = ((BigInteger) resMap.get("memberNum")).intValue();
        return String.valueOf(count);
    }

    public AiIntelligentCourseVo getAiIntelligentCourseByCourseIdAndTeacherId(String teacherId, String courseId) {
        AiIntelligentCourseSummaryResource aiIntelligentCourseSummaryResource =
                getAiIntelligentCourseSummaryResource(teacherId);
        AiIntelligentCourseVo aiIntelligentCourseVo = null;
        aiIntelligentCourseVo = aiIntelligentCourseSummaryResource.getCurrentTermAIIntelligentCourseVoList()
                .stream().filter(filterAiIntelligentCourseVo ->
                        filterAiIntelligentCourseVo.getCourseId().equals(courseId)
                ).findFirst().orElse(null);
        if (ObjectUtils.isEmpty(aiIntelligentCourseVo)) {
            aiIntelligentCourseVo = aiIntelligentCourseSummaryResource.getHistoryTermAIIntelligentCourseVoList()
                    .stream().filter(filterAiIntelligentCourseVo ->
                            filterAiIntelligentCourseVo.getCourseId().equals(courseId)
                    ).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(aiIntelligentCourseVo)) {
                aiIntelligentCourseVo.setCurrentTerm(false);
            }
        } else {
            aiIntelligentCourseVo.setCurrentTerm(true);
        }
        return aiIntelligentCourseVo;
    }
}

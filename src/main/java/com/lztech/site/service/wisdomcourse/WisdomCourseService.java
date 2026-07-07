package com.lztech.site.service.wisdomcourse;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.wisdomcourse.WisdomCourse;
import com.lztech.domain.model.wisdomcourse.WisdomCourseHistoryTerm;
import com.lztech.domain.model.wisdomcourse.WisdomCourseTask;
import com.lztech.domain.model.wisdomcourse.enums.HistoryTaskStatus;
import com.lztech.domain.model.wisdomcourse.enums.WisdomCourseTaskStatus;
import com.lztech.domain.model.wisdomcourse.enums.WisdomCourseTaskType;
import com.lztech.persistence.repositories.course.CourseTeachingTeamRepository;
import com.lztech.persistence.repositories.wisdomcourse.WisdomCourseHistoryTermRepository;
import com.lztech.persistence.repositories.wisdomcourse.WisdomCourseRepository;
import com.lztech.persistence.repositories.wisdomcourse.WisdomCourseTaskRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.constants.Result;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.wisdomcourse.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.wisdomcourse.specification.WisdomCourseTaskSpecification.specification;


@Service
public class WisdomCourseService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseService courseService;
    @Autowired
    private WisdomCourseRepository wisdomCourseRepository;
    @Autowired
    private WisdomCourseHistoryTermRepository wisdomCourseHistoryTermRepository;
    @Autowired
    private WisdomCourseTaskRepository wisdomCourseTaskRepository;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;

    @Transactional
    public Result addWisdomCourse(@Valid WisdomCourseParam wisdomCourseParam) {
        Course course = courseService.findById(wisdomCourseParam.getCourseId());
        if (Objects.isNull(course)) {
            return Result.error("课程不存在");
        }
        List<WisdomCourse> existCourseList = wisdomCourseRepository.findByCourseIdAndUseState(course.getId(), true);
        if (CollectionUtils.isNotEmpty(existCourseList)) {
            return Result.error("该门课程已存在，请勿重复添加");
        }
        WisdomCourse wisdomCourse = new WisdomCourse();
        wisdomCourse.setCourseId(course.getId());
        wisdomCourse.setCourseName(course.getCourseName());
        wisdomCourse.setCreatorNo(wisdomCourseParam.getOperatorNo());
        wisdomCourse.setModifyNo(wisdomCourseParam.getOperatorNo());
        wisdomCourse.setModifierId(wisdomCourseParam.getOperatorId());
        wisdomCourse.setModifierName(wisdomCourseParam.getOperatorName());
        wisdomCourse.setModifyTime(new Date());
        wisdomCourse.setCreatorId(wisdomCourseParam.getOperatorId());
        wisdomCourse.setCreatorName(wisdomCourseParam.getOperatorName());
        wisdomCourse.setCreateTime(new Date());
        wisdomCourse.setUseState(true);
        wisdomCourseRepository.save(wisdomCourse);
        List<WisdomCourseHistoryTerm> historyTerms = buildHistoryTermList(wisdomCourseParam, wisdomCourse);
        wisdomCourseHistoryTermRepository.saveAll(historyTerms);
        return Result.success();

    }

    private List<WisdomCourseHistoryTerm> buildHistoryTermList(@Valid WisdomCourseParam wisdomCourseParam, WisdomCourse wisdomCourse) {
        List<WisdomCourseHistoryTerm> wisdomCourseHistoryTermList = new ArrayList<>();
        wisdomCourseParam.getHistoryTermList().forEach(historyTerm -> {
            WisdomCourseHistoryTerm wisdomCourseHistoryTerm = new WisdomCourseHistoryTerm();
            wisdomCourseHistoryTerm.setTermId(historyTerm.getTermId());
            wisdomCourseHistoryTerm.setSchoolYear(historyTerm.getSchoolYear());
            wisdomCourseHistoryTerm.setTerm(historyTerm.getTerm());
            wisdomCourseHistoryTerm.setHistoryTaskStatus(HistoryTaskStatus.NOT_COLLECTED);
            wisdomCourseHistoryTerm.setWisdomCourse(wisdomCourse);
            wisdomCourseHistoryTermList.add(wisdomCourseHistoryTerm);
        });
        return wisdomCourseHistoryTermList;
    }


    public WisdomCourseTaskPageResult queryWisdomCourseTaskPage(WisdomCourseTaskPageParam wisdomCourseTaskPageParam) {
        WisdomCourseTaskPageResult wisdomCourseTaskPageResult = new WisdomCourseTaskPageResult();
        wisdomCourseTaskPageResult.setPage(wisdomCourseTaskPageParam.getPageNum());
        wisdomCourseTaskPageResult.setPageSize(wisdomCourseTaskPageParam.getPageSize());
        wisdomCourseTaskPageResult.setTotal(0);
        wisdomCourseTaskPageResult.setPageCount(0);
        wisdomCourseTaskPageResult.setTaskList(new ArrayList<>());
        WisdomCourse wisdomCourse = wisdomCourseRepository.findById(wisdomCourseTaskPageParam.getWisdomCourseId()).orElse(null);
        if (Objects.isNull(wisdomCourse) || !wisdomCourse.isUseState()) {
            return wisdomCourseTaskPageResult;
        }
        Pageable pageable = PageRequest.of(wisdomCourseTaskPageParam.getPageNum() - 1, wisdomCourseTaskPageParam.getPageSize());
        Page<WisdomCourseTask> taskPage = wisdomCourseTaskRepository.findAll(specification(wisdomCourseTaskPageParam), pageable);
        List<WisdomCourseTask> taskList = taskPage.getContent();
        wisdomCourseTaskPageResult.setTotal((int) taskPage.getTotalElements());
        wisdomCourseTaskPageResult.setPageCount(taskPage.getTotalPages());
        wisdomCourseTaskPageResult.setTotalCount((int) taskPage.getTotalElements());
        wisdomCourseTaskPageResult.setTaskList(getWisdomCourseTaskResultList(taskList));

        return wisdomCourseTaskPageResult;
    }

    private List<WisdomCourseTaskResult> getWisdomCourseTaskResultList(List<WisdomCourseTask> taskList) {
        List<WisdomCourseTaskResult> taskResultList = new ArrayList<>();
        for (WisdomCourseTask task : taskList) {
            WisdomCourseTaskResult taskResult = new WisdomCourseTaskResult();
            taskResult.setTaskId(task.getId());
            taskResult.setTaskType(task.getTaskType().getCode());
            taskResult.setTaskTypeName(task.getTaskType().getShowName());
            taskResult.setRemark(task.getRemark());
            taskResult.setTaskStatus(task.getTaskStatus().getCode());
            taskResult.setTaskStatusName(task.getTaskStatus().getMessage());
            taskResult.setResourceName(task.getResourceName());
            taskResult.setResourceId(task.getResourceId());
            taskResult.setCreateTime(DateUtils.formatDate(DateUtils.DATE_TIME, task.getCreateTime()));
            taskResult.setResourceType(task.getResourceType().getCode());
            taskResult.setResourceTypeName(task.getResourceType().getMessage());
            taskResultList.add(taskResult);
        }
        return taskResultList;
    }

    public WisdomCoursePageResult queryWisdomCoursePage(WisdomCoursePageParam wisdomCoursePageParam) {
        Map<String, Object> queryParamMap = new HashMap<>();
        // 先获取基础查询SQL（不包含分组和排序）
        String baseQuerySql = getBaseQueryWisdomCourseSql(wisdomCoursePageParam, queryParamMap);
        // 获取总记录数
        String countSql = "SELECT COUNT(DISTINCT w.id) " + baseQuerySql;
        Query countQuery = entityManager.createNativeQuery(countSql);
        queryParamMap.forEach(countQuery::setParameter);
        int totalCount = ((Number) countQuery.getSingleResult()).intValue();

        // 如果总数为0，直接返回空结果
        if (totalCount == 0) {
            return buildEmptyResult(wisdomCoursePageParam);
        }

        // 获取完整查询SQL（包含分组和排序）
        String fullQuerySql = getFullQueryWisdomCourseSql(wisdomCoursePageParam, queryParamMap);

        // 创建原生查询获取数据
        Query query = entityManager.createNativeQuery(fullQuerySql);
        queryParamMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(WisdomCourseResultSqlVo.class));


        // 设置分页参数
        int pageNum = wisdomCoursePageParam.getPageNum() != null ? wisdomCoursePageParam.getPageNum() : 1;
        int pageSize = wisdomCoursePageParam.getPageSize() != null ? wisdomCoursePageParam.getPageSize() : Constant.MAGIC_TEN;
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);

        // 获取分页数据
        List<WisdomCourseResultSqlVo> resultList = query.getResultList();
        List<WisdomCourseResult> courseResults = convertToCourseResults(resultList);

        // 构建返回结果
        return buildResult(wisdomCoursePageParam, totalCount, courseResults);
    }


    // 构建基础查询SQL（不包含分组和排序）
    private String getBaseQueryWisdomCourseSql(WisdomCoursePageParam param, Map<String, Object> paramMap) {
        StringBuilder sql = new StringBuilder(
                "FROM tb_wisdom_course w "
                        + "INNER JOIN tb_course c ON w.course_id = c.id "
                        + "INNER JOIN tb_course_version cv ON c.id = cv.course_id "
                        + "WHERE w.use_state = 1 AND c.use_state = 1 AND cv.course_version_status = 1 "
        );

        // 添加课程编号/名称条件
        if (StringUtils.isNotBlank(param.getCourseNoOrName())) {
            sql.append(" AND (c.course_code LIKE :courseNoOrName OR c.course_name LIKE :courseNoOrName) ");
            paramMap.put("courseNoOrName", "%" + param.getCourseNoOrName() + "%");
        }

        // 添加教师编号/名称条件（改为 EXISTS 子查询，保证能返回完整团队）
        if (StringUtils.isNotBlank(param.getTeacherNoOrName())) {
            sql.append(" AND EXISTS ( ")
                    .append("   SELECT 1 FROM tb_course_teaching_team tt ")
                    .append("   WHERE tt.course_version_id = cv.id ")
                    .append("     AND (tt.teacher_no LIKE :teacherNoOrName OR tt.teacher_name LIKE :teacherNoOrName) ")
                    .append(") ");
            paramMap.put("teacherNoOrName", "%" + param.getTeacherNoOrName() + "%");
        }

        return sql.toString();
    }

    // 构建完整查询SQL（包含分组和排序）
    private String getFullQueryWisdomCourseSql(WisdomCoursePageParam param, Map<String, Object> paramMap) {
        // 先获取基础SQL（条件部分）
        String baseSql = getBaseQueryWisdomCourseSql(param, paramMap);

        // 添加选择字段、分组和排序
        return "SELECT "
                + "w.id AS wid, c.id AS courseId, c.course_code as courseCode, c.course_name as courseName,cv.id as courseVersionId, "
                + " DATE_FORMAT(w.create_time,'%Y-%m-%d %H:%i:%s') as createTime "
                + baseSql
                + " GROUP BY w.id, c.id, c.course_code, c.course_name, w.create_time "
                + " ORDER BY w.create_time DESC";
    }

    private List<WisdomCourseResult> convertToCourseResults(List<WisdomCourseResultSqlVo> tuples) {
        List<WisdomCourseResult> results = new ArrayList<>();

        List<String> widList = tuples.stream().map(WisdomCourseResultSqlVo::getWid).collect(Collectors.toList());
        List<String> courseVersionIdList = tuples.stream().map(WisdomCourseResultSqlVo::getCourseVersionId).collect(Collectors.toList());
        List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository.findByCourseVersionIdList(courseVersionIdList);
        List<WisdomCourseTask> wisdomCourseTaskList = wisdomCourseTaskRepository.findByWisdomCourseIdIn(widList);
        for (WisdomCourseResultSqlVo tuple : tuples) {
            WisdomCourseResult result = new WisdomCourseResult();
            result.setId(tuple.getWid());
            result.setCourseId(tuple.getCourseId());
            result.setCourseName(tuple.getCourseName());
            result.setCourseNo(tuple.getCourseCode());
            result.setCreateTime(tuple.getCreateTime());
            List<CourseTeachingTeam> thisCourseTeachingTeams = courseTeachingTeams.stream()
                    .filter(team -> team.getCourseVersion().getId().equals(tuple.getCourseVersionId()))
                    .collect(Collectors.toList());
            String teacherNames = getCourseTeachingTeamNames(thisCourseTeachingTeams);
            result.setCourseTeacherTeamNames(teacherNames);
            List<WisdomCourseTask> taskList = wisdomCourseTaskList.stream()
                    .filter(task -> task.getWisdomCourse().getId().equals(result.getId()))
                    .collect(Collectors.toList());
            result.setTotalTaskNum(0);
            result.setCompletedTaskNum(0);
            if (CollectionUtils.isNotEmpty(taskList)) {
                result.setCompletedTaskNum((int) taskList.stream()
                        .filter(task -> task.getTaskStatus()
                                .equals(WisdomCourseTaskStatus.SUCCESS)).count());
                result.setTotalTaskNum(taskList.size());
            }

            results.add(result);
        }
        return results;
    }

    private String getCourseTeachingTeamNames(List<CourseTeachingTeam> thisCourseTeachingTeams) {
        if (CollectionUtils.isEmpty(thisCourseTeachingTeams)) {
            return "";
        }
        return thisCourseTeachingTeams.stream()
                .map(teacher -> {
                    // 当工号不为空时：姓名(工号)
                    if (StringUtils.isNotBlank(teacher.getTeacherNo())) {
                        return teacher.getTeacherName() + "(" + teacher.getTeacherNo() + ")";
                    }
                    // 当工号为空时：仅姓名
                    else {
                        return teacher.getTeacherName();
                    }
                })
                .collect(Collectors.joining("、")); // 用顿号分隔
    }

    // 构建空结果
    private WisdomCoursePageResult buildEmptyResult(WisdomCoursePageParam param) {
        WisdomCoursePageResult result = new WisdomCoursePageResult();
        result.setPage(param.getPageNum() != null ? param.getPageNum() : 1);
        result.setPageSize(param.getPageSize() != null ? param.getPageSize() : Constant.MAGIC_TEN);
        result.setTotalCount(0);
        result.setTotal(0);
        result.setPageCount(0);
        result.setCourseList(new ArrayList<>());
        return result;
    }

    // 构建完整结果
    private WisdomCoursePageResult buildResult(WisdomCoursePageParam param, int totalCount, List<WisdomCourseResult> courseResults) {
        int pageNum = param.getPageNum() != null ? param.getPageNum() : 1;
        int pageSize = param.getPageSize() != null ? param.getPageSize() : Constant.MAGIC_TEN;
        int pageCount = (int) Math.ceil((double) totalCount / pageSize);

        WisdomCoursePageResult result = new WisdomCoursePageResult();
        result.setPage(pageNum);
        result.setPageSize(pageSize);
        result.setTotalCount(totalCount);
        result.setTotal(totalCount);
        result.setPageCount(pageCount);
        result.setCourseList(courseResults);

        return result;
    }

    public Result deleteWisdomCourse(String wisdomCourseId, String operatorId, String operatorName,
                                     String operatorNo) {
        WisdomCourse wisdomCourse = wisdomCourseRepository.findById(wisdomCourseId).orElse(null);
        if (wisdomCourse == null) {
            return Result.error("智慧课程不存在");
        }
        wisdomCourse.setUseState(false);
        wisdomCourse.setModifierId(operatorId);
        wisdomCourse.setModifierName(operatorName);
        wisdomCourse.setModifyTime(new Date());
        wisdomCourse.setModifyNo(operatorNo);
        wisdomCourseRepository.save(wisdomCourse);
        return Result.success();
    }

    public List<WisdomCourseResult> queryWisdomCourse(@Valid String courseId) {
        List<WisdomCourse> wisdomCourseList = new ArrayList<>();
        if (StringUtils.isBlank(courseId)) {
            wisdomCourseList = wisdomCourseRepository.findByUseState(true);
        } else {
            List<String> courseIds = Arrays.asList(courseId.split(","));
            wisdomCourseList = wisdomCourseRepository.findByCourseIdInAndUseState(courseIds, true);
        }
        List<WisdomCourseResult> wisdomCourseResultList = new ArrayList<>();
        wisdomCourseList.forEach(wisdomCourse -> {
            WisdomCourseResult wisdomCourseResult = new WisdomCourseResult();
            wisdomCourseResult.setId(wisdomCourse.getId());
            wisdomCourseResult.setCourseId(wisdomCourse.getCourseId());
            wisdomCourseResult.setCourseName(wisdomCourse.getCourseName());
            wisdomCourseResultList.add(wisdomCourseResult);
        });
        return wisdomCourseResultList;
    }

    public Result retryWisdomCourseTask(WisdomCourseTaskRetryParam wisdomCourseTaskRetryParam) {
        List<String> taskIds = Arrays.asList(wisdomCourseTaskRetryParam.getTaskIds().split(","));
        List<WisdomCourseTask> wisdomCourseTaskList = wisdomCourseTaskRepository.findByIdIn(taskIds);
        if (wisdomCourseTaskList.stream().anyMatch(t -> WisdomCourseTaskStatus.WAITING.equals(t.getTaskStatus())
                || WisdomCourseTaskStatus.GENERATING.equals(t.getTaskStatus()))) {
            return Result.error("待处理和处理中的任务不能操作重试，请重新选择");
        }
        wisdomCourseTaskList.forEach(t -> {
            t.setTaskStatus(WisdomCourseTaskStatus.WAITING);
            t.setModifierId(wisdomCourseTaskRetryParam.getOperatorId());
            t.setModifierName(wisdomCourseTaskRetryParam.getOperatorName());
            t.setModifyTime(new Date());
            t.setRemark("");
        });
        wisdomCourseTaskRepository.saveAll(wisdomCourseTaskList);
        return Result.success();
    }

    public List<WisdomCourseTaskResult> findWisdomCourseTaskResults(String courseId,
                                                                    List<WisdomCourseTaskType> taskTypes,
                                                                    List<WisdomCourseTaskStatus> taskStatusEnum){
        Map<String,Object> conditions = new HashMap<>();
        conditions.put("courseId",courseId);
        conditions.put("taskType",taskTypes);
        conditions.put("taskStatus",taskStatusEnum);
        List<WisdomCourseTask>  wisdomCourseTasks = wisdomCourseTaskRepository.findAll(specification(conditions));
        List<WisdomCourseTaskResult> courseResults = getWisdomCourseTaskResultList(wisdomCourseTasks);
        return courseResults;
    }


    public List<WisdomCourseStatisticsResult> getWisdomCourseStatisticsByCourse(String courseId,List<Integer> taskTypes){
        String sql = "select b.task_type as taskType,b.task_type_name as taskTypeName, " +
                " CAST(COUNT(b.task_type) AS SIGNED) AS count FROM tb_wisdom_course w " +
                " left join tb_wisdom_course_task b on w.id = b.wisdom_course_id " +
                "                       INNER JOIN tb_course c ON w.course_id = c.id  " +
                "                       INNER JOIN tb_course_version cv ON c.id = cv.course_id  " +
                "                       WHERE w.use_state = 1 AND c.use_state = 1 AND cv.course_version_status = 1  " +
                " AND b.task_type in (:taskTypes) " +
                " AND w.course_id = :courseId GROUP BY b.task_type";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("taskTypes",taskTypes);
        query.setParameter("courseId",courseId);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(WisdomCourseStatisticsResult.class));
        List<WisdomCourseStatisticsResult> results = query.getResultList();
        return results;
    }
}

package com.lztech.site.service.teachingreport;

import com.google.common.collect.Lists;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.CourseResource;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNode;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.course.CourseResourceRepository;
import com.lztech.persistence.repositories.courseknowledgestructure.CourseKnowledgeStructureRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.viewmodel.teachingreport.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.constants.Constant.FIVE_HUNDRED;
import static com.lztech.site.constants.Constant.ONE_HUNDRED;
import static com.lztech.site.until.DateUtils.DATE_TIME;
import static org.neo4j.driver.Values.parameters;

@Service
public class TeachingReportService {
    @Autowired
    private EntityManager entityManager;
    @Value("${request.address.authorityApi}")
    private String authorityApi;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseKnowledgeStructureRepository courseKnowledgeStructureRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private Neo4jUtil neo4jUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingReportService.class);

    // 创建固定大小线程池（根据CPU核心数调整）
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public OfferCourseInfoVo getTeachingReportOfferingCourseInfo(OfferingCourseRequest queryParam) {
        OfferCourseInfoVo offerCourseInfoVo = new OfferCourseInfoVo();

        // 并行执行所有获取参数的任务
        CompletableFuture<String> courseNumberFuture = CompletableFuture.supplyAsync(() -> getCourseNumber(queryParam), EXECUTOR_SERVICE);
        CompletableFuture<String> teacherNumberFuture = CompletableFuture.supplyAsync(() -> getTeacherNumber(queryParam), EXECUTOR_SERVICE);
        CompletableFuture<String> totalSegmentNumberFuture = CompletableFuture.supplyAsync(() -> getTotalSegmentNumber(queryParam), EXECUTOR_SERVICE);
        CompletableFuture<String> groupNumberFuture = CompletableFuture.supplyAsync(() -> getGroupNumber(queryParam), EXECUTOR_SERVICE);
        CompletableFuture<String> studentNumberFuture = CompletableFuture.supplyAsync(() -> getStudentNumber(queryParam), EXECUTOR_SERVICE);

        // 等待所有任务完成并收集结果
        CompletableFuture.allOf(courseNumberFuture, teacherNumberFuture, totalSegmentNumberFuture,
                groupNumberFuture, studentNumberFuture).join();
        offerCourseInfoVo.setCourseNumber(courseNumberFuture.join());
        offerCourseInfoVo.setTeacherNumber(teacherNumberFuture.join());
        offerCourseInfoVo.setTotalSegmentNumber(totalSegmentNumberFuture.join());
        offerCourseInfoVo.setGroupNumber(groupNumberFuture.join());
        offerCourseInfoVo.setStudentNumber(studentNumberFuture.join());

        return offerCourseInfoVo;
    }

    private Map<String, Object> setQueryOfferingCourseInfoParam(OfferingCourseRequest queryParam) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", queryParam.getSchoolYear());
        paramMap.put("term", queryParam.getTerm());
        List<String> ids = queryParam.getColleges().stream().map(OfferingCourseCollegeRequest::getCollegeId).collect(Collectors.toList());
        paramMap.put("collegeId", ids);
        paramMap.put("startDate", queryParam.getStartDate());
        paramMap.put("endDate", queryParam.getEndDate());
        if(!StringUtils.isEmpty(queryParam.getStudentType())){
            List<String> studentTypes = Arrays.asList(queryParam.getStudentType().split(","));
            paramMap.put("studentType",studentTypes);
        }
        return paramMap;
    }

    private String getCourseNumber(OfferingCourseRequest queryParam) {
        long start = System.currentTimeMillis();
        String sql = "SELECT COUNT(DISTINCT ct.course_id) AS courseNumber" +
                " FROM tb_course_table ct" +
                " JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id" +
                " JOIN tb_group g ON g.id = ct.group_id AND g.group_status = 0" +
                " WHERE ct.source IN (0,1,2,3,4,5)" +
                " AND ct.college_id IN (:collegeId)" +
                " AND ct.school_year = :schoolYear AND ct.term = :term" +
                " AND ctd.course_date BETWEEN :startDate AND :endDate";
        if(!StringUtils.isEmpty(queryParam.getStudentType())){
            sql += " AND ct.student_type in(:studentType)";
        }
        Map<String, Object> paramMap = setQueryOfferingCourseInfoParam(queryParam);

        String courseNumber = handleQueryData(sql, paramMap);
        long end = System.currentTimeMillis();
        LOGGER.info("获取开课课程数 消耗毫秒数:{}",end-start);
        return courseNumber;
    }

    private String getTeacherNumber(OfferingCourseRequest queryParam) {
        long start = System.currentTimeMillis();
        String sql = "SELECT COUNT(DISTINCT ctdt.teacher_id) AS teacherNumber" +
                " FROM tb_course_table ct" +
                " JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id" +
                " JOIN tb_group g ON g.id = ct.group_id AND g.group_status = 0" +
                " JOIN tb_course_table_detail_teacher ctdt ON ctdt.course_table_detail_id = ctd.id" +
                " WHERE ct.source IN (0,1,2,3,4,5)" +
                " AND ct.college_id IN (:collegeId)" +
                " AND ct.school_year = :schoolYear AND ct.term = :term" +
                " AND ctd.course_date BETWEEN :startDate AND :endDate";
        if(!StringUtils.isEmpty(queryParam.getStudentType())){
            sql += " AND ct.student_type in(:studentType)";
        }
        Map<String, Object> paramMap = setQueryOfferingCourseInfoParam(queryParam);

        String teacherNumber = handleQueryData(sql, paramMap);
        long end = System.currentTimeMillis();
        LOGGER.info("获取授课教师数 消耗毫秒数:{}",end-start);
        return teacherNumber;
    }

    private String getTotalSegmentNumber(OfferingCourseRequest queryParam) {
        long start = System.currentTimeMillis();
        String sql = "SELECT COALESCE(SUM(LENGTH(ctd.segment) - LENGTH(REPLACE(ctd.segment, ',', '')) + 1),0) AS totalSegmentNumber" +
                " FROM tb_course_table ct" +
                " JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id" +
                " JOIN tb_group g ON g.id = ct.group_id AND g.group_status = 0" +
                " WHERE ct.source IN (0,1,2,3,4,5)" +
                " AND ctd.segment IS NOT NULL" +
                " AND ct.college_id IN (:collegeId)" +
                " AND ct.school_year = :schoolYear AND ct.term = :term" +
                " AND ctd.course_date BETWEEN :startDate AND :endDate";
        if(!StringUtils.isEmpty(queryParam.getStudentType())){
            sql += " AND ct.student_type in(:studentType)";
        }
        Map<String, Object> paramMap = setQueryOfferingCourseInfoParam(queryParam);

        String totalSegmentNumber =  handleQueryData(sql, paramMap);
        long end = System.currentTimeMillis();
        LOGGER.info("获取上课总学时 消耗毫秒数:{}",end-start);
        return totalSegmentNumber;
    }

    private String getGroupNumber(OfferingCourseRequest queryParam) {
        long start = System.currentTimeMillis();
        String sql = "SELECT COUNT(DISTINCT ct.group_id) AS groupNumber" +
                " FROM tb_course_table ct" +
                " JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id" +
                " JOIN tb_group g ON g.id = ct.group_id AND g.group_status = 0" +
                " WHERE ct.source IN (0,1,2,3,4,5)" +
                " AND ct.college_id IN (:collegeId)" +
                " AND ct.school_year = :schoolYear AND ct.term = :term" +
                " AND ctd.course_date BETWEEN :startDate AND :endDate";
        if(!StringUtils.isEmpty(queryParam.getStudentType())){
            sql += " AND ct.student_type in(:studentType)";
        }
        Map<String, Object> paramMap = setQueryOfferingCourseInfoParam(queryParam);

        String groupNumber =  handleQueryData(sql, paramMap);
        long end = System.currentTimeMillis();
        LOGGER.info("获取上课班级数 消耗毫秒数:{}",end-start);
        return groupNumber;
    }

    private String getStudentNumber(OfferingCourseRequest queryParam) {
        long start = System.currentTimeMillis();
        String sql = "SELECT COUNT(DISTINCT gm.student_id) AS studentNumber" +
                " FROM tb_group_member gm " +
                " inner join ($associationSql) tjm on gm.group_id = tjm.group_id " +
                " WHERE gm.group_member_status = 0 " ;
        String associationSql =  "SELECT DISTINCT ct.group_id AS group_id" +
                " FROM tb_course_table ct" +
                " JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id" +
                " JOIN tb_group g ON g.id = ct.group_id AND g.group_status = 0" +
                " WHERE ct.source IN (0,1,2,3,4,5)" +
                " AND ct.college_id IN (:collegeId)" +
                " AND ct.school_year = :schoolYear AND ct.term = :term" +
                " AND ctd.course_date BETWEEN :startDate AND :endDate " ;
        if(!StringUtils.isEmpty(queryParam.getStudentType())){
            associationSql += " AND ct.student_type in(:studentType)";
        }
        sql = sql.replace("$associationSql", associationSql);
        Map<String, Object> paramMap = setQueryOfferingCourseInfoParam(queryParam);
        String studentNumber = handleQueryData(sql, paramMap);
        long end = System.currentTimeMillis();
        LOGGER.info("获取上课学生数 消耗毫秒数:{}",end-start);
        return studentNumber;
    }

    private String handleQueryData(String sql, Map<String, Object> paramMap) {
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        // 直接获取结果而不使用结果转换器
        Object result = query.getSingleResult();
        if (result instanceof BigInteger) {
            return String.valueOf(((BigInteger) result).intValue());
        } else if (result instanceof Long) {
            return String.valueOf(((Long) result).intValue());
        } else {
            return String.valueOf(result);
        }
    }


    public TeachingReportCourseResourceStatisticsVo getTeachingReportCourseResourceStatistics(OfferingCourseRequest queryParam) {
        Date courseDateStartTime = DateUtils.stringToDate(DATE_TIME, queryParam.getStartDate() + " 00:00:00");
        Date courseDateEndTime = DateUtils.stringToDate(DATE_TIME, queryParam.getEndDate() + " 23:59:59");
        List<CollegeIdVo> collegeIdVoList = new ArrayList<>();
        queryParam.getColleges().forEach(offeringCourseCollegeRequest -> {
            CollegeIdVo collegeIdVo = new CollegeIdVo();
            collegeIdVo.setCollegeId(offeringCourseCollegeRequest.getCollegeId());
            collegeIdVoList.add(collegeIdVo);
        });
        List<CollegeTeacherIdVo> collegeTeacherIdVoList = getCollegeTeacherList(collegeIdVoList);
        List<String> teacherIdList = new ArrayList<>();
        collegeTeacherIdVoList.forEach(collegeTeacherIdVo -> collegeTeacherIdVo.getTeacherIds().forEach(teacherIdVo ->
                teacherIdList.add(teacherIdVo.getTeacherId())));
        List<CourseResource> courseResourceList = new ArrayList<>();
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        List<List<String>> splitTeacherLists = Lists.partition(teacherIdList, FIVE_HUNDRED);
        obtainDataList(splitTeacherLists, courseDateStartTime, courseDateEndTime, courseResourceList);
        String startTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseDateStartTime);
        String endTime = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseDateEndTime);
        String neo4jSql = "MATCH (n) where n.knowledgeNodeLevel <>0 and n.createTime>=$startTime " +
                        "and n.createTime<=$endTime   return n ";
        getCourseKnowledgeGraphNodeList(startTime, endTime, teacherIdList, courseKnowledgeGraphNodeList, neo4jSql);
        //老师id
        Set<String> teacherIdSet = new HashSet<>();
        //课程资源个数
        int courseResourceNum = 0, resourceReferenceNum = 0, teachingCourseWareNum = 0, microVideoNum = 0, imageNum = 0,
                classTestNum = 0, knowledgePointNum = 0, themeNum = 0;
        for (CourseResource courseResource : courseResourceList) {
            teacherIdSet.add(courseResource.getCreatorId());
            courseResourceNum++;
            if (courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE)) {
                teachingCourseWareNum++;
            } else if (courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO)) {
                microVideoNum++;
            } else if (courseResource.getResourceType().equals(ResourceType.IMAGE)) {
                imageNum++;
            } else if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) {
                classTestNum++;
            } else if (courseResource.getResourceType().equals(ResourceType.THEME)) {
                themeNum++;
            }
            if (courseResource.getResourceReferences() + courseResource.getResourceOtherReferences() > 0) {
                resourceReferenceNum++;
            }
        }
        for (CourseKnowledgeGraphNode courseKnowledgeGraphNode : courseKnowledgeGraphNodeList) {
            teacherIdSet.add(courseKnowledgeGraphNode.getCreatorId());
            knowledgePointNum++;
        }
        TeachingReportCourseResourceStatisticsVo teachingReportCourseResourceStatisticsVo = new TeachingReportCourseResourceStatisticsVo();
        teachingReportCourseResourceStatisticsVo.setCourseResourceNum(courseResourceNum + knowledgePointNum);
        teachingReportCourseResourceStatisticsVo.setJoinTeacherNumber(teacherIdSet.size());
        teachingReportCourseResourceStatisticsVo.setTeacherTotalNumber(teacherIdList.size());
        teachingReportCourseResourceStatisticsVo.setResourceReferenceNum(resourceReferenceNum);
        teachingReportCourseResourceStatisticsVo.setTeachingCourseWareNum(teachingCourseWareNum);
        teachingReportCourseResourceStatisticsVo.setMicroVideoNum(microVideoNum);
        teachingReportCourseResourceStatisticsVo.setImageNum(imageNum);
        teachingReportCourseResourceStatisticsVo.setClassTestNum(classTestNum);
        teachingReportCourseResourceStatisticsVo.setKnowledgePointNum(knowledgePointNum);
        teachingReportCourseResourceStatisticsVo.setThemeNum(themeNum);
        List<CourseResourceStatisticsTeacherVo> teacherList = new ArrayList<>();
        for (String teacherId : teacherIdList) {
            CourseResourceStatisticsTeacherVo courseResourceStatisticsTeacherVo = new CourseResourceStatisticsTeacherVo();
            courseResourceStatisticsTeacherVo.setTeacherId(teacherId);
            courseResourceStatisticsTeacherVo.setUploadType(teacherIdSet.contains(teacherId) ? 1 : 0);
            teacherList.add(courseResourceStatisticsTeacherVo);
        }
        teachingReportCourseResourceStatisticsVo.setTeacherList(teacherList);
        return teachingReportCourseResourceStatisticsVo;
    }

    private void obtainDataList(List<List<String>> splitTeacherLists, Date courseDateStartTime, Date courseDateEndTime,
                                List<CourseResource> courseResourceList) {
        for (List<String> splitTeacherList : splitTeacherLists) {
            List<CourseResource> splitCourseResourceList =
                    courseResourceRepository.findCreateTimeAndTeacherIdIn(courseDateStartTime, courseDateEndTime, splitTeacherList);
            if (!CollectionUtils.isEmpty(splitCourseResourceList)) {
                courseResourceList.addAll(splitCourseResourceList);
            }
        }
    }

    public List<CollegeTeacherIdVo> getCollegeTeacherList(List<CollegeIdVo> collegeIdVoList) {
        RestTemplate restTemplate = new RestTemplate();
        List<CollegeTeacherIdVo> collegeTeacherIdVoList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<CollegeIdVo>> request = new HttpEntity<>(collegeIdVoList, headers);
        String url = authorityApi + ConstantWebApi.POST_COLLEGE_TEACHERS + "?validCode=" + Md5Utils.md5(signKey);
        try {
            ResponseEntity<List<CollegeTeacherIdVo>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request,
                    new ParameterizedTypeReference<List<CollegeTeacherIdVo>>() {
                    });
            collegeTeacherIdVoList = responseEntity.getBody();
            return collegeTeacherIdVoList;
        } catch (Exception e) {
            LOGGER.error("getLowValueArticlesResources->{}", e.getMessage());
            e.printStackTrace();
            return collegeTeacherIdVoList;
        }
    }


    public List<CourseResourceCollegeStatisticsModel> getCourseResourceCollegeStatisticsList(
            CollegeTeacherResourceStatisticsRequest queryParam) {
        String courseDateStartTime = queryParam.getStartDate() + " 00:00:00";
        String courseDateEndTime = queryParam.getEndDate() + " 23:59:59";
        List<CourseResourceCollegeStatisticsModel> courseResourceCollegeStatisticsModelList = new ArrayList<>();
        List<College> collegeList = collegeRepository.findAll();
        List<CollegeIdVo> collegeIdVoList = new ArrayList<>();
        collegeList.forEach(college -> {
            CollegeIdVo collegeIdVo = new CollegeIdVo();
            collegeIdVo.setCollegeId(college.getId());
            collegeIdVoList.add(collegeIdVo);
        });
        List<CollegeTeacherIdVo> collegeTeacherIdVoList = getCollegeTeacherList(collegeIdVoList);
        List<String> teacherIdList = new ArrayList<>();
        collegeTeacherIdVoList.forEach(collegeTeacherIdVo -> collegeTeacherIdVo.getTeacherIds().forEach(teacherIdVo ->
                teacherIdList.add(teacherIdVo.getTeacherId())));
        List<List<String>> splitTeacherLists = Lists.partition(teacherIdList, ONE_HUNDRED);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseDateStartTime", courseDateStartTime);
        paramMap.put("courseDateEndTime", courseDateEndTime);
        StringBuilder sql = new StringBuilder("SELECT a.creator_id as teacherId,a.resource_type as resourceType, " +
                "CAST(count(a.id) as char) as resourceNum " +
                "FROM tb_course_resource a INNER JOIN tb_course_structure b ON a.course_structure_id = b.id " +
                "INNER JOIN tb_course_version c ON b.course_version_id = c.id INNER JOIN tb_course d ON d.id = c.course_id " +
                "WHERE  a.source_type = 0  AND c.course_version_status = 1 " +
                " AND a.create_time >=:courseDateStartTime AND a.create_time <=:courseDateEndTime AND (");
        getInjectTeacherIdList(splitTeacherLists, sql, "resource", paramMap);
        sql.append(") group by a.creator_id ");
        Query query = entityManager.createNativeQuery(String.valueOf(sql));
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TeacherResourceTypeStatisticModel.class));
        List<TeacherResourceTypeStatisticModel> teacherResourceTypeStatisticModelList = query.getResultList();

        List<CourseKnowledgeGraphNode > courseKnowledgeGraphNodeList = new ArrayList<>();
        /*查询知识*/
        String neo4jSql = "MATCH (n) where n.knowledgeNodeLevel <>0 and n.createTime>=$startTime " +
                        "and n.createTime<=$endTime  return n ";
        getCourseKnowledgeGraphNodeList(courseDateStartTime, courseDateEndTime, teacherIdList, courseKnowledgeGraphNodeList, neo4jSql);
        Map<String, List<CourseKnowledgeGraphNode>> groupCourseKnowledgeGraphNodeModelListMap =
                courseKnowledgeGraphNodeList.stream().collect(Collectors.groupingBy(CourseKnowledgeGraphNode::getCreatorId));
        List<TeacherResourceTypeStatisticModel> knowledgeGraphNodeTeacherResourceTypeStatisticModels =
                new ArrayList<>();
        groupCourseKnowledgeGraphNodeModelListMap.forEach((teacherId,teacherCourseKnowledgeGraphNodeModelList) ->{
            TeacherResourceTypeStatisticModel oneTeacherResourceTypeStatisticModel = new TeacherResourceTypeStatisticModel();
            oneTeacherResourceTypeStatisticModel.setTeacherId(teacherId);
            oneTeacherResourceTypeStatisticModel.setResourceType(Constant.FIVE);
            oneTeacherResourceTypeStatisticModel.setResourceNum(String.valueOf(teacherCourseKnowledgeGraphNodeModelList.size()));
            knowledgeGraphNodeTeacherResourceTypeStatisticModels.add(oneTeacherResourceTypeStatisticModel);
        });
        teacherResourceTypeStatisticModelList.addAll(knowledgeGraphNodeTeacherResourceTypeStatisticModels);
        Map<String, List<TeacherResourceTypeStatisticModel>> groupTeacherResourceTypeStatisticModelListMap =
                teacherResourceTypeStatisticModelList.stream()
                        .collect(Collectors.groupingBy(TeacherResourceTypeStatisticModel::getTeacherId));
        getCourseResourceCollegeStatisticsModelList(collegeTeacherIdVoList, groupTeacherResourceTypeStatisticModelListMap,
                courseResourceCollegeStatisticsModelList);
        return courseResourceCollegeStatisticsModelList;
    }

    private void getCourseKnowledgeGraphNodeList(
            String courseDateStartTime, String courseDateEndTime, List<String> teacherIdList,
            List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList, String neo4jSql) {
        org.neo4j.driver.Value value = parameters("startTime", courseDateStartTime, "endTime", courseDateEndTime);
        List<CourseKnowledgeGraphNode> neo4jCourseKnowledgeGraphNodeList = neo4jUtil.getDataByDateTimeAndTeacherIdIn(neo4jSql,value);
        if (!CollectionUtils.isEmpty(neo4jCourseKnowledgeGraphNodeList)) {
            List<CourseKnowledgeGraphNode> handleCourseKnowledgeGraphNodeList =
                    neo4jCourseKnowledgeGraphNodeList.stream().filter(courseKnowledgeGraphNode ->
                            teacherIdList.contains(courseKnowledgeGraphNode.getCreatorId())).collect(Collectors.toList());
            courseKnowledgeGraphNodeList.addAll(handleCourseKnowledgeGraphNodeList);
        }
    }

    private void getInjectTeacherIdList(List<List<String>> splitTeacherLists, StringBuilder sql,
                                        String sourceName, Map<String, Object> paramMap) {
        for (int i = 0; i < splitTeacherLists.size(); i++) {
            String queryName = sourceName + "_teacher_" + i;
            if (i == 0) {
                sql.append(" a.creator_id IN (:").append(queryName).append(") ");
            } else {
                sql.append(" or a.creator_id IN (:").append(queryName).append(") ");
            }
            paramMap.put(queryName, splitTeacherLists.get(i));
        }
    }

    private static void getCourseResourceCollegeStatisticsModelList(
            List<CollegeTeacherIdVo> collegeTeacherIdVoList,
            Map<String, List<TeacherResourceTypeStatisticModel>> groupTeacherResourceTypeStatisticModelListMap,
            List<CourseResourceCollegeStatisticsModel> courseResourceCollegeStatisticsModelList) {
        collegeTeacherIdVoList.forEach(collegeTeacherIdVo -> {
            CourseResourceCollegeStatisticsModel courseResourceCollegeStatisticsModel =
                    new CourseResourceCollegeStatisticsModel();
            courseResourceCollegeStatisticsModel.setCollegeId(collegeTeacherIdVo.getCollegeId());
            courseResourceCollegeStatisticsModel.setCollegeName(collegeTeacherIdVo.getCollegeName());
            List<CourseResourceTeacherStatisticsModel> courseResourceTeacherStatisticsList = new ArrayList<>();
            collegeTeacherIdVo.getTeacherIds().forEach(teacherIdVo -> {
                CourseResourceTeacherStatisticsModel courseResourceTeacherStatisticsModel = new CourseResourceTeacherStatisticsModel();
                courseResourceTeacherStatisticsModel.setTeacherId(teacherIdVo.getTeacherId());
                List<TeacherResourceTypeStatisticModel> groupTeacherResourceTypeStatisticModelList =
                        groupTeacherResourceTypeStatisticModelListMap.get(teacherIdVo.getTeacherId());
                if (!CollectionUtils.isEmpty(groupTeacherResourceTypeStatisticModelList)) {
                    int resourceNum = 0;
                    for (TeacherResourceTypeStatisticModel teacherResourceTypeStatisticModel :
                            groupTeacherResourceTypeStatisticModelList) {
                        resourceNum += Integer.parseInt(teacherResourceTypeStatisticModel.getResourceNum());
                    }
                    courseResourceTeacherStatisticsModel.setResourceNum(String.valueOf(resourceNum));
                } else {
                    courseResourceTeacherStatisticsModel.setResourceNum(String.valueOf(0));
                }
                courseResourceTeacherStatisticsList.add(courseResourceTeacherStatisticsModel);
            });
            courseResourceCollegeStatisticsModel.setCourseResourceTeacherStatisticsList(courseResourceTeacherStatisticsList);
            courseResourceCollegeStatisticsModelList.add(courseResourceCollegeStatisticsModel);
        });
    }
}
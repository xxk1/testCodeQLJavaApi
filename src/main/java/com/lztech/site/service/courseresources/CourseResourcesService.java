package com.lztech.site.service.courseresources;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.course.specification.CourseResourceSpecification;
import com.lztech.persistence.repositories.course.specification.CourseSpecification;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.resource.course.*;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.CustomRuntimeException;
import com.lztech.site.viewmodel.courseconstruction.CourseResourceKnowledgePointVo;
import com.lztech.site.viewmodel.courseresource.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.coursetable.specification.CourseTableSpecification.getTeacherCourseList;
import static com.lztech.site.until.CharactersReplace.replaceCharacters;

@Service
public class CourseResourcesService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseResourceKnowledgePointRepository courseResourceKnowledgePointRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private TermService termService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Value("${studentType}")
    private String studentType;

    public CourseResourcesPageVo getCourseList(String collegeId, String courseName,
                                               Integer page, Integer pageSize,
                                               Integer courseSource) {
        List<CourseInfoResource> courseInfoResourceList = new ArrayList<>();
        CourseResourcesPageVo courseResourcesPageVo = new CourseResourcesPageVo();
        Map<String, Object> paramMap = new HashMap<>();
        String countSql = createQueryCountSql(collegeId, courseName, courseSource, paramMap);
        Query queryCount = entityManager.createNativeQuery(countSql);
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        int count = ((BigInteger) resMap.get("allNumber")).intValue();
        courseResourcesPageVo.setPage(page);
        courseResourcesPageVo.setPageSize(pageSize);
        if (count == 0) {
            courseResourcesPageVo.setTotal(0);
            courseResourcesPageVo.setCourseResourcesList(courseInfoResourceList);
            return courseResourcesPageVo;
        }
        String querySql = createQuerySql(collegeId, courseName, courseSource, page, pageSize, paramMap);
        Query queryData = entityManager.createNativeQuery(querySql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfoResource.class));
        courseResourcesPageVo.setTotal(count);
        courseResourcesPageVo.setCourseResourcesList(queryData.getResultList());
        return courseResourcesPageVo;
    }

    private String createQuerySql(String collegeId, String courseName, Integer courseSource, Integer page, Integer pageSize, Map paramMap) {
        String sql = "SELECT " +
                " college_code AS collegeCode, " +
                " college_name AS collegeName, " +
                " course_code AS courseCode, " +
                " id AS courseId, " +
                " course_name AS courseName, " +
                " course_leader_id AS courseLeaderId, " +
                " course_leader_name AS courseLeaderName, " +
                " course_source as courseSource, " +
                " (select count(1) from tb_course_table a INNER JOIN tb_group g on a.group_id=g.id where  " +
                " a.course_id=c.id and g.group_status=0) as courseClassNum, " +
                " IFNULL(c.creator_name,'系统') as operatorName " +
                " FROM  tb_course c where use_state=1 ";
        sql = setQueryPrams(collegeId, courseName, courseSource, sql, paramMap);

        sql += " ORDER BY create_time desc ,id asc ";

        int start = (page - 1) * pageSize;

        sql += " limit " + start + "," + pageSize;

        return sql;
    }


    private String createQueryCountSql(String collegeId, String courseName, Integer courseSource, Map paramMap) {

        String countSql = "select count(1) as allNumber from tb_course where use_state=1 ";
        countSql = setQueryPrams(collegeId, courseName, courseSource, countSql, paramMap);
        return countSql;
    }

    private String setQueryPrams(String collegeId, String courseName, Integer courseSource, String countSql, Map paramMap) {


        if (StringUtils.isNotBlank(collegeId)) {
            countSql += " and college_id= :collegeId ";

            paramMap.put("collegeId", collegeId);
        }

        if (StringUtils.isNotBlank(courseName)) {
            String name = "%" + CharactersReplace.replaceCharacters(courseName).trim() + "%";
            countSql += " and course_name like :name";
            paramMap.put("name", name);

        }
        if (Objects.nonNull(courseSource)) {
            countSql += " and course_source=:courseSource";
            paramMap.put("courseSource", courseSource);
        }
        return countSql;
    }


    private CourseResourcesPageVo getCoursePageVo(int total, Integer page, Integer pageSize,
                                                  List<CourseInfoResource> courseInfoResourceList) {
        CourseResourcesPageVo courseResourcesPageVo = new CourseResourcesPageVo();
        courseResourcesPageVo.setTotal(total);
        courseResourcesPageVo.setPage(page);
        courseResourcesPageVo.setPageSize(pageSize);
        courseResourcesPageVo.setCourseResourcesList(courseInfoResourceList);
        return courseResourcesPageVo;
    }


    //region 根据课程名称查询课程信息
    public ResponseEntity<List<CourseInfoResource>> getCourseInfoResources(
            String courseName) {

        String courseVagueName = "%" + replaceCharacters(courseName) + "%";
        List<Course> courseList = courseRepository.findByCourseNameLikeAndUseState(courseVagueName, true);
        if (CollectionUtils.isNotEmpty(courseList)) {
            List<CourseInfoResource> courseInfoResourceList =
                    courseList.stream().map(course -> new CourseInfoResource() {
                                {
                                    this.setCourseId(course.getId());
                                    this.setCollegeCode(course.getCollegeCode());
                                    this.setCollegeName(course.getCollegeName());
                                    this.setCourseCode(course.getCourseCode());
                                    this.setCourseName(course.getCourseName());
                                }
                            }
                    ).collect(Collectors.toList());
            return new ResponseEntity<>(courseInfoResourceList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private List<Course> filterCourse(List<Course> courseList) {
        List<String> collect = courseList.stream().map(Course::getId).collect(Collectors.toList());
        List<CourseTable> courseTables = courseTableRepository.findByCourseIdIn(collect);
        Map<Course, List<CourseTable>> courseListMap = courseTables.stream().collect(Collectors.groupingBy(CourseTable::getCourse));
        Iterator<Course> iterator = courseList.iterator();
        List<Course> courseArrayList = new ArrayList<>();
        while (iterator.hasNext()) {

            switch (studentType) {

                case "-1":
                    Course next = iterator.next();
                    courseArrayList.add(next);
                    break;
                case "0":
                    Course course = iterator.next();
                    List<CourseTable> courseTableList = courseListMap.get(course);
                    if (!CollectionUtils.isEmpty(courseTableList)) {
                        List<StudentType> studentTypes = courseTableList.stream().map(CourseTable::getStudentType).collect(Collectors.toList());
                        if (studentTypes.contains(StudentType.UNDERGRADUATE)) {
                            courseArrayList.add(course);
                        }
                    }
                    break;
                case "1":
                    Course course1 = iterator.next();
                    List<CourseTable> courseTableList1 = courseListMap.get(course1);
                    if (!CollectionUtils.isEmpty(courseTableList1)) {
                        List<StudentType> studentTypes1 = courseTableList1.stream().map(CourseTable::getStudentType).collect(Collectors.toList());
                        if (studentTypes1.contains("0")) {
                            courseArrayList.add(course1);
                        }
                    }
                    break;
                case "2":
                    Course course2 = iterator.next();
                    List<CourseTable> courseTableList2 = courseListMap.get(course2);
                    if (!CollectionUtils.isEmpty(courseTableList2)) {
                        List<StudentType> studentTypes2 = courseTableList2.stream().map(CourseTable::getStudentType).collect(Collectors.toList());

                        if (studentTypes2.contains("2")) {
                            courseArrayList.add(course2);
                        }
                    }
                    break;
                case "3":
                    Course course3 = iterator.next();
                    List<CourseTable> courseTableList3 = courseListMap.get(course3);
                    if (!CollectionUtils.isEmpty(courseTableList3)) {
                        List<StudentType> studentTypes3 = courseTableList3.stream().map(CourseTable::getStudentType).collect(Collectors.toList());
                        if (studentTypes3.contains("3")) {
                            courseArrayList.add(course3);
                        }
                    }
                    break;
                case "4":
                    Course course4 = iterator.next();
                    List<CourseTable> courseTableList4 = courseListMap.get(course4);
                    if (!CollectionUtils.isEmpty(courseTableList4)) {
                        List<StudentType> studentTypes4 = courseTableList4.stream().map(CourseTable::getStudentType).collect(Collectors.toList());
                        if (studentTypes4.contains("4")) {
                            courseArrayList.add(course4);
                        }
                    }
                    break;
                default:
                    Course course5 = iterator.next();
                    courseArrayList.add(course5);
                    break;
            }


        }
        return courseArrayList;
    }
    //endregion

    public ResponseEntity<List<CourseResourceVo>> getAllCourseList() {
        List<CourseResourceVo> courseResourceVos = new ArrayList<>();
        List<Course> courseArrayList = courseRepository.findByCollegeNotNullAndUseStateIsTrue();
        if (CollectionUtils.isEmpty(courseArrayList)) {
            return new ResponseEntity<>(courseResourceVos, HttpStatus.OK);
        }
        courseArrayList.forEach(course -> {
            CourseResourceVo courseResourceVo = new CourseResourceVo();
            courseResourceVo.setCourseId(course.getId());
            courseResourceVo.setCourseCode(course.getCourseCode());
            courseResourceVo.setCourseName(course.getCourseName());
            courseResourceVo.setCollegeId(course.getCollege().getId());
            courseResourceVo.setCollegeCode(course.getCollegeCode());
            courseResourceVo.setCollegeName(course.getCollegeName());
            courseResourceVos.add(courseResourceVo);
        });
        return new ResponseEntity<>(courseResourceVos, HttpStatus.OK);
    }

    public List<TestPaperResource> getTestPapers(String courseId, String teacherId, String testName) {
        List<TestPaperResource> testPaperResourceList = new ArrayList<>();
        CourseTeachingTeam courseTeachingTeam = courseTeachingTeamRepository.findByTeacherIdAndCourseId(teacherId, courseId);
        if (ObjectUtils.isNotEmpty(courseTeachingTeam)) {
            CourseVersion courseVersion = courseTeachingTeam.getCourseVersion();

            List<CourseResource> courseResources = courseResourceRepository.findAll(
                    CourseResourceSpecification.getTestPaperSpecification(teacherId, courseId, courseVersion, testName));
            courseResources.forEach(courseResource -> {
                TestPaperResource testPaperResource = new TestPaperResource();
                testPaperResource.setId(courseResource.getResourceDetailId());
                testPaperResource.setCourseResourceId(courseResource.getId());
                testPaperResource.setName(courseResource.getResourceName());
                if (StringUtils.isNotEmpty(courseResource.getCourseStructure().getParentId())) {
                    testPaperResource.setCourseStructureId(courseResource.getCourseStructure().getParentId());
                } else {
                    testPaperResource.setCourseStructureId(courseResource.getCourseStructure().getId());
                }
                testPaperResourceList.add(testPaperResource);
            });
            return testPaperResourceList;
        } else {
            throw new CustomRuntimeException(ErrorResult.customError("该老师不在教学团队内"));
        }
    }

    public ResponseEntity<List<CourseResourceDetailVo>> getCourseResourceDetail(String resourceIds) {
        List<String> ids = Arrays.asList(resourceIds.split(","));
        List<CourseResource> courseResources = courseResourceRepository.findByResourceDetailIdInAndResourceStatus(ids, ResourceStatus.NORMAL);
        if (courseResources.size() == 0) {
            return new ResponseEntity(ErrorResult.customError("资源不存在"), HttpStatus.NOT_FOUND);
        }
        List<CourseResourceDetailVo> courseResourceDetailVos = new ArrayList<>();
        courseResources.forEach(courseResource -> {
            CourseResourceDetailVo courseResourceDetailVo = new CourseResourceDetailVo();
            courseResourceDetailVo.setResourceId(courseResource.getId());
            courseResourceDetailVo.setResourceDetailId(courseResource.getResourceDetailId());
            courseResourceDetailVo.setIsPublic(courseResource.getIsPublic());
            courseResourceDetailVos.add(courseResourceDetailVo);
        });
        return new ResponseEntity<>(courseResourceDetailVos, HttpStatus.OK);
    }

    public CourseInfoPageVo getCourseInfoList(String courseNameOrNo, Integer page, Integer pageSize) {
        courseNameOrNo = "%" + replaceCharacters(courseNameOrNo) + "%";

        String sql = "select DISTINCT tb_course.id as courseId,tb_course.course_name as courseName,tb_course.course_code as courseCode " +
                " from tb_course  INNER   JOIN  tb_course_table ON tb_course_table.course_id=tb_course.id  where use_state=1 ";
        Map<String, Object> paramMap = new HashMap<>();
        String prams = setPrams(courseNameOrNo, sql, paramMap);
        int start = (page - 1) * pageSize;
        int size = pageSize;
        prams += " limit " + start + "," + size;
        Query queryData = entityManager.createNativeQuery(prams);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<CourseInfoVo> courseInfoVos = queryData.getResultList();
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfoVo.class));
        return getCourseInfoPageVo(courseNameOrNo, page, pageSize, courseInfoVos);
    }

    private String setPrams(String courseNameOrNo, String sql, Map paramMap) {
        if (StringUtils.isNotBlank(courseNameOrNo)) {
            sql += "and ( tb_course.course_name like  :courseName  or tb_course.course_code like  :courseName )";

            paramMap.put("courseName", courseNameOrNo);
        }
        if (!studentType.equals("-1")) {
            sql += "   and tb_course_table.student_type=:studentType";
            paramMap.put("studentType", studentType);
        }

        return sql;
    }

    private CourseInfoPageVo getCourseInfoPageVo(String courseNameOrNo, Integer page, Integer pageSize,
                                                 List<CourseInfoVo> courseInfoVos) {

        CourseInfoPageVo courseInfoPageVo = new CourseInfoPageVo();
        Integer count = getCount(courseNameOrNo);
        courseInfoPageVo.setTotal(count);
        courseInfoPageVo.setCourseInfoVos(courseInfoVos);
        courseInfoPageVo.setPage(page);
        courseInfoPageVo.setPageSize(pageSize);
        courseInfoPageVo.setPageCount((int) Math.ceil((double) count / pageSize));
        return courseInfoPageVo;
    }

    private Integer getCount(String courseNameOrNo) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = " SELECT   count(DISTINCT tb_course.id) as total from " +
                " tb_course  INNER   JOIN  tb_course_table ON tb_course_table.course_id=tb_course.id  where  use_state=1 ";
        String prams = setPrams(courseNameOrNo, sql, paramMap);
        Query queryCount = entityManager.createNativeQuery(prams);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        paramMap.forEach(queryCount::setParameter);
        Map<String, Object> countMap = (Map<String, Object>) queryCount.getResultList().get(0);
        return ((BigInteger) countMap.get("total")).intValue();
    }


    public FoundationCoursePageVo getFoundationCourseList
            (String courseName, Integer page, Integer pageSize, String teacherNo, String startTime, String endTime) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        FoundationCoursePageVo foundationCoursePageVo = new FoundationCoursePageVo();
        if (StringUtils.isBlank(teacherNo)) {
            Page<Course> coursePage = courseRepository.findAll(
                    CourseSpecification.getFoundationCourseSpecification(replaceCharacters(courseName)), pageable);
            List<FoundationCourseVo> foundationCourseVoList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(coursePage.getContent())) {
                for (Course course : coursePage.getContent()) {
                    FoundationCourseVo foundationCourseVo = new FoundationCourseVo();
                    foundationCourseVo.setCourseId(course.getId());
                    foundationCourseVo.setCourseName(course.getCourseName());
                    foundationCourseVo.setCourseNo(course.getCourseCode());
                    foundationCourseVoList.add(foundationCourseVo);
                }
            }
            int total = (int) coursePage.getTotalElements();
            foundationCoursePageVo.setTotal(total);
            foundationCoursePageVo.setPage(page);
            foundationCoursePageVo.setPageSize(pageSize);
            foundationCoursePageVo.setFoundationCourseList(foundationCourseVoList);
            return foundationCoursePageVo;
        } else {
            Page<CourseTable> courseTablePage = courseTableRepository.findAll
                    (getTeacherCourseList(teacherNo, courseName, startTime, endTime), pageable);
            List<FoundationCourseVo> foundationCourseVoList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(courseTablePage.getContent())) {
                for (CourseTable courseTable : courseTablePage.getContent()) {
                    FoundationCourseVo foundationCourseVo = new FoundationCourseVo();
                    foundationCourseVo.setCourseId(courseTable.getCourse().getId());
                    foundationCourseVo.setCourseName(courseTable.getCourse().getCourseName());
                    foundationCourseVo.setCourseNo(courseTable.getCourse().getCourseCode());
                    foundationCourseVoList.add(foundationCourseVo);
                }
            }
            int total = (int) courseTablePage.getTotalElements();
            foundationCoursePageVo.setTotal(total);
            foundationCoursePageVo.setPage(page);
            foundationCoursePageVo.setPageSize(pageSize);
            foundationCoursePageVo.setFoundationCourseList(foundationCourseVoList);
            return foundationCoursePageVo;


        }


    }

    public boolean getCourseDeleteState(String courseId) {

        Optional<Course> optionalCourse = courseRepository.findByIdAndUseStateIsTrue(courseId);

        return optionalCourse.map(Course::isUseState).orElse(false);
    }


    private String buildQuerySql(String collegeId, String courseNameOrNo, Map paramMap) {
        String baseSql = "SELECT " +
                " tc.id AS courseId, " +
                " tc.course_name AS courseName, " +
                " tc.college_name AS collegeName, " +
                " tc.course_code AS courseCode,  " +
                " tc.college_id AS collegeId  " +
                " FROM " +
                " tb_course tc " +
                " LEFT JOIN tb_course_table tct ON tct.course_id = tc.id" +
                " WHERE 1=1";

        if (StringUtils.isNotBlank(courseNameOrNo)) {
            if (courseNameOrNo.length() < 3) {
                courseNameOrNo = "%" + replaceCharacters(courseNameOrNo) + "%";
                baseSql += " AND (tct.course_name LIKE :courseName )";
                paramMap.put("courseName", courseNameOrNo);
            } else {
                courseNameOrNo = "%" + replaceCharacters(courseNameOrNo) + "%";
                baseSql += " AND (tct.course_name LIKE :courseName OR tc.course_code LIKE :courseNo)";
                paramMap.put("courseName", courseNameOrNo);
                paramMap.put("courseNo", courseNameOrNo);
            }

        }
        if (StringUtils.isNotBlank(collegeId)) {
            baseSql += " AND tct.college_id=:collegeId";
            paramMap.put("collegeId", collegeId);
        }
        baseSql += " AND tc.use_state=true group by tc.id";
        return baseSql;
    }

    public List<CourseInfosTO> getAvailableCourseInfo(String collegeId, String courseNameOrNo) {

        Map<String, Object> paramMap = new HashMap<>();
        String nativeSql = buildQuerySql(collegeId, courseNameOrNo, paramMap);
        Query nativeQuery = entityManager.createNativeQuery(nativeSql);
        paramMap.forEach(nativeQuery::setParameter);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfosTO.class));
        return nativeQuery.getResultList();
    }

    public Result addCourseResourceKnowledgePoint(CourseResourceKnowledgePointVo resourcePointVo) {
        List<CourseResourceKnowledgePoint> pointList = new ArrayList<>();
        courseResourceKnowledgePointRepository.deleteByResourceId(resourcePointVo.getResourceId());
        Date now = new Date();
        resourcePointVo.getPointIdList().stream().distinct().forEach(s -> {
            if (StringUtils.isBlank(s)) {
                return;
            }
            CourseResourceKnowledgePoint courseResourceKnowledgePoint = new CourseResourceKnowledgePoint();
            courseResourceKnowledgePoint.setKnowledgePointId(s);
            courseResourceKnowledgePoint.setResourceId(resourcePointVo.getResourceId());
            courseResourceKnowledgePoint.setModifierId(resourcePointVo.getUserId());
            courseResourceKnowledgePoint.setModifierName(resourcePointVo.getUserName());
            courseResourceKnowledgePoint.setModifyTime(now);
            courseResourceKnowledgePoint.setCreatorId(resourcePointVo.getUserId());
            courseResourceKnowledgePoint.setCreatorName(resourcePointVo.getUserName());
            courseResourceKnowledgePoint.setCreateTime(now);
            courseResourceKnowledgePoint.setCourseKnowledgeGraphDomainId(resourcePointVo.getCourseKnowledgeGraphId());
            pointList.add(courseResourceKnowledgePoint);

        });
        courseResourceKnowledgePointRepository.saveAll(pointList);
        return Result.success();
    }


    public CourseInfoVo getCourseInfoByCourseNo(String courseNo) {
        courseNo = replaceCharacters(courseNo);
        String sql = "select id as courseId,course_name as courseName,course_code as courseCode " +
                " from tb_course where use_state=1 and course_code = :courseNo ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseNo", courseNo);
        Query queryData = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<CourseInfoVo> courseInfoVos =
                JSONArray.parseArray(JSONObject.toJSON(queryData.getResultList()).toString(), CourseInfoVo.class);
        return CollectionUtils.isNotEmpty(courseInfoVos) ? courseInfoVos.get(0) : null;
    }

    public List<CourseInfoResourceVo> getCourseInfos(String courseNameOrNo) {
        List<CourseInfoResourceVo> courseInfoResourceVos = new ArrayList<>();
        String courseName = "%" + replaceCharacters(courseNameOrNo) + "%";
        String courseNo = courseName;
        List<String> ids = courseRepository.findByCourseNameLikeOrCourseCodeLikeBySql(courseName, courseNo);
        List<Course> courseList = courseRepository.findByIdIn(ids);
        if (CollectionUtils.isEmpty(courseList)) {
            return courseInfoResourceVos;
        }
        List<CourseVersion> courseVersions = courseVersionRepository.findByCourseInAndCourseVersionStatus(courseList, CourseVersionStatus.IN_USE);
        List<Course> courses = courseVersions.stream().map(CourseVersion::getCourse).collect(Collectors.toList());
        List<CourseMaterial> courseMaterialList = courseMaterialRepository.findByCourseInAndCourseVersionIn(courses, courseVersions);

        List<String> exitVersionIds = courses.stream().map(Course::getId).collect(Collectors.toList());
        List<Course> courseNot = courseList.stream().filter(f -> !exitVersionIds.contains(f.getId())).collect(Collectors.toList());
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findByCourseIn(courseNot);

        Map<Course, List<CourseMaterial>> courseListMap = courseMaterialList.stream().collect(Collectors.groupingBy(CourseMaterial::getCourse));
        Iterator<Map.Entry<Course, List<CourseMaterial>>> iterator = courseListMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Course, List<CourseMaterial>> next = iterator.next();
            CourseInfoResourceVo courseInfoResourceVo = new CourseInfoResourceVo();
            Course course = next.getKey();
            courseInfoResourceVo.setCourseId(course.getId());
            courseInfoResourceVo.setCourseCode(course.getCourseCode());
            courseInfoResourceVo.setCourseName(course.getCourseName());
            courseInfoResourceVo.setCollegeCode(course.getCollegeCode());
            courseInfoResourceVo.setCollegeId(StringUtils.isAnyEmpty(course.getCollege().getId()) ? null : course.getCollege().getId());
            courseInfoResourceVo.setCollegeName(course.getCollegeName());
            List<CourseMaterial> courseMaterialsI = next.getValue();
            if (CollectionUtils.isNotEmpty(courseMaterialsI)) {
                CourseMaterial courseMaterial = courseMaterialsI.get(0);
                courseInfoResourceVo.setCourseContent(courseMaterial.getCourseContent());
                courseInfoResourceVo.setTeachingUserType(courseMaterial.getTeachingUserType());
                courseInfoResourceVo.setTeachingMaterial(courseMaterial.getTeachingMaterial());
                courseInfoResourceVo.setReferenceMaterials(courseMaterial.getReferenceMaterials());
                courseInfoResourceVo.setClassHours
                        (ObjectUtils.isEmpty(courseMaterial.getClassHours()) ? null : courseMaterial.getClassHours().toString());
            }
            courseInfoResourceVos.add(courseInfoResourceVo);
        }

        courseMaterials.forEach(courseMaterial -> {
            CourseInfoResourceVo courseInfoResourceVo = new CourseInfoResourceVo();
            courseInfoResourceVo.setCourseContent(courseMaterial.getCourseContent());
            courseInfoResourceVo.setTeachingUserType(courseMaterial.getTeachingUserType());
            courseInfoResourceVo.setTeachingMaterial(courseMaterial.getTeachingMaterial());
            courseInfoResourceVo.setReferenceMaterials(courseMaterial.getReferenceMaterials());
            courseInfoResourceVo.setClassHours
                    (ObjectUtils.isEmpty(courseMaterial.getClassHours()) ? null : courseMaterial.getClassHours().toString());
            if (ObjectUtils.isNotEmpty(courseMaterial.getCourse())) {
                Course course = courseMaterial.getCourse();
                courseInfoResourceVo.setCourseId(course.getId());
                courseInfoResourceVo.setCourseCode(course.getCourseCode());
                courseInfoResourceVo.setCourseName(course.getCourseName());
                courseInfoResourceVo.setCollegeCode(course.getCollegeCode());
                courseInfoResourceVo.setCollegeId(StringUtils.isAnyEmpty(course.getCollege().getId()) ? null : course.getCollege().getId());
                courseInfoResourceVo.setCollegeName(course.getCollegeName());
            }
            courseInfoResourceVos.add(courseInfoResourceVo);
        });

        return courseInfoResourceVos;
    }

    public List<CourseInfoVo> queryCourseList(String courseNameOrNo) {
        List<Course> courseList = new ArrayList<>();
        if (StringUtils.isBlank(courseNameOrNo)) {
            courseList = courseRepository.findByUseState(true);
        } else {
            if (courseNameOrNo.length() < 3) {
                String courseName = "%" + replaceCharacters(courseNameOrNo) + "%";
                courseList = courseRepository.findByCourseNameLike(courseName);
            } else {
                String courseName = "%" + replaceCharacters(courseNameOrNo) + "%";
                courseList = courseRepository.findByCourseNameCodeLike(courseName);
            }

        }
        List<CourseInfoVo> courseInfoVoList = new ArrayList<>();
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(courseList)) {
            return courseInfoVoList;
        }
        courseList.forEach(course -> {
            CourseInfoVo courseInfoVo = new CourseInfoVo();
            courseInfoVo.setCourseId(course.getId());
            courseInfoVo.setCourseName(course.getCourseName());
            courseInfoVo.setCourseCode(course.getCourseCode());
            courseInfoVoList.add(courseInfoVo);
        });
        return courseInfoVoList;
    }
}

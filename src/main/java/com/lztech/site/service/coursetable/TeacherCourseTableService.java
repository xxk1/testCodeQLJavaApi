package com.lztech.site.service.coursetable;

import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupAttribute;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.project.ProjectRepository;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.CourseResourceTeachingClassName;
import com.lztech.site.viewmodel.coursetable.SegmentCourseTableDetailModel;
import com.lztech.site.viewmodel.coursetable.TeacherCourseResource;
import com.lztech.site.viewmodel.peerreview.TeacherPeerReviewCourseResource;
import com.lztech.site.viewmodel.peerreview.TeacherPeerReviewGroupResource;
import com.lztech.site.viewmodel.peerreview.TeacherPeerReviewProjectResource;
import com.lztech.site.viewmodel.peerreview.sqlvo.CourseProjectSqlVo;
import com.lztech.site.viewmodel.peerreview.sqlvo.TeacherCourseTableSqlVo;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.coursetable.specification.CourseTableSpecification.getTeacherCourseList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class TeacherCourseTableService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Value("${studentType}")
    private String studentType;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private CollegeRepository collegeRepository;

    public List<TeacherPeerReviewCourseResource> getTeacherCourseTableGroupAndProject(String userId,
                                                                                      String schoolYear,
                                                                                      Integer term) {
        Map<String, Object> paramMap = new HashMap<>();
        String teacherCourseTableSql = getTeacherCourseTableSql(userId, schoolYear, term, paramMap);
        Query courseTableQuery = entityManager.createNativeQuery(teacherCourseTableSql);
        paramMap.forEach(courseTableQuery::setParameter);
        courseTableQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TeacherCourseTableSqlVo.class));
        List<TeacherCourseTableSqlVo> courseTableSqlVoList = courseTableQuery.getResultList();
        if (CollectionUtils.isEmpty(courseTableSqlVoList)) {
            return new ArrayList<>();
        }
        List<String> groupIds = courseTableSqlVoList.stream().map(TeacherCourseTableSqlVo::getGroupId).collect(Collectors.toList());
        List<String> courseIds = courseTableSqlVoList.stream().map(TeacherCourseTableSqlVo::getCourseId).distinct().collect(Collectors.toList());
        List<Group> groupList = groupRepository.findByIdInAndGroupStatus(groupIds, GroupStatus.NORMAL);
        String queryProjectSql = getTeacherCourseProjectSql(courseIds);
        Query projectQuery = entityManager.createNativeQuery(queryProjectSql);
        projectQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseProjectSqlVo.class));
        List<CourseProjectSqlVo> courseProjectSqlVoList = projectQuery.getResultList();
        if (CollectionUtils.isEmpty(courseProjectSqlVoList)) {
            return new ArrayList<>();
        }
        Map<String, List<TeacherCourseTableSqlVo>> courseTableSqlVoMap =
                courseTableSqlVoList.stream().collect(Collectors.groupingBy(TeacherCourseTableSqlVo::getCourseId));
        List<TeacherPeerReviewCourseResource> resultList = new ArrayList<>();
        courseTableSqlVoMap.forEach((k, v) -> {
            List<String> courseGroupIds = v.stream().map(TeacherCourseTableSqlVo::getGroupId).collect(Collectors.toList());
            List<Group> courseGroupList = groupList.stream().filter(g -> courseGroupIds.contains(g.getId())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(courseGroupList)) {
                return;
            }
            List<CourseProjectSqlVo> courseProjectList =
                    courseProjectSqlVoList.stream().filter(c -> k.equals(c.getCourseId())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(courseProjectList)) {
                return;
            }
            TeacherCourseTableSqlVo vo = v.get(0);
            TeacherPeerReviewCourseResource resource = new TeacherPeerReviewCourseResource();
            resource.setCourseId(k);
            resource.setCourseCode(vo.getCourseId());
            resource.setCourseName(vo.getCourseName());
            resource.setProjectList(setCourseProjectList(courseProjectList));
            resource.setGroupList(setGroupList(courseGroupList));
            resultList.add(resource);
        });
        return resultList;
    }

    private List<TeacherPeerReviewGroupResource> setGroupList(List<Group> courseGroupList) {
        List<TeacherPeerReviewGroupResource> groupResourceList = new ArrayList<>();
        courseGroupList.forEach(c -> {
            TeacherPeerReviewGroupResource groupResource = new TeacherPeerReviewGroupResource();
            groupResource.setGroupId(c.getId());
            groupResource.setGroupNo(c.getGroupNo());
            groupResource.setGroupName(c.getGroupName());
            Integer sort = ObjectUtils.isEmpty(c.getSort()) ? 0 : c.getSort();
            groupResource.setGroupSort(sort);
            groupResourceList.add(groupResource);
        });
        return groupResourceList.stream()
                .sorted(Comparator.comparingInt(TeacherPeerReviewGroupResource::getGroupSort)
                        .thenComparing((TeacherPeerReviewGroupResource obj) ->
                                (obj.getGroupNo() != null && obj.getGroupNo().startsWith("2")) ? 0 : 1)
                        .thenComparing(TeacherPeerReviewGroupResource::getGroupNo))
                .collect(Collectors.toList());
    }

    private List<TeacherPeerReviewProjectResource> setCourseProjectList(List<CourseProjectSqlVo> courseProjectList) {
        List<TeacherPeerReviewProjectResource> resourceList = new ArrayList<>();
        courseProjectList.forEach(c -> {
            TeacherPeerReviewProjectResource projectResource = new TeacherPeerReviewProjectResource();
            projectResource.setProjectId(c.getProjectId());
            projectResource.setProjectCode(c.getProjectCode());
            projectResource.setProjectName(c.getProjectName());
            resourceList.add(projectResource);

        });
        return resourceList;
    }

    private String getTeacherCourseProjectSql(List<String> courseIds) {
        String courseIdIn = courseIds
                .stream()
                .map(c -> "'" + c + "'")
                .collect(joining(","));

        return "select p.project_code as projectCode,p.id as projectId,p.project_name as projectName,tpa.data_id as courseId from \n" +
                "tb_project p \n" +
                "inner join tb_project_attribute tpa on p.id = tpa.project_id\n" +
                "where tpa.data_id in (" + courseIdIn + ") and tpa.attribute_kind=2";
    }

    private String getTeacherCourseTableSql(String userId, String schoolYear, Integer term, Map<String, Object> paramMap) {

        String sql = "select ct.course_id courseId,ct.course_name courseName,group_id groupId " +
                " from tb_course_table ct " +
                " INNER JOIN tb_course_table_detail ctd on ctd.course_table_id=ct.id\n" +
                " INNER JOIN tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id\n" +
                " where ctdt.teacher_id ='" + userId + "' and ct.school_year ='" + schoolYear + "' and term=" + term + "\n";
        if (!studentType.equals("-1")) {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql += " and ct.student_type = :studentType ";
            paramMap.put("studentType", studentTypeQuery);
        }
        sql += " GROUP BY ct.id ";
        return sql;
    }


    public List<TeacherCourseResource> getTeacherCourseInfos(String userId, String schoolYear, Integer term,
                                                             String courseStudentTypes, Boolean isNeedDistinguish) {
        List<CourseTable> courseTableList = courseTableRepository.findAll(getTeacherCourseList(userId, schoolYear,
                term, courseStudentTypes, isNeedDistinguish, null));
        Map<Course, List<CourseTable>> courseTableGroup = courseTableList.stream().collect(Collectors.groupingBy(CourseTable::getCourse));
        return getTeacherCourseResources(getCourseTableGroup(courseTableGroup), userId);
    }

    public List<TeacherCourseResource> getTeacherCourseResources(Map<Course, List<CourseTable>> courseTableGroup, String userId) {
        List<TeacherCourseResource> teacherCourseResourceList = new ArrayList<>();
        courseTableGroup.forEach((course, courseTables) -> {
            TeacherCourseResource teacherCourseResource = new TeacherCourseResource();
            teacherCourseResource.setCourseId(course.getId());
            teacherCourseResource.setCourseSource(Objects.isNull(course.getCourseSource()) ? 0 : course.getCourseSource().getIndex());
            teacherCourseResource.setCourseCode(course.getCourseCode());
            teacherCourseResource.setCourseName(course.getCourseName());
            teacherCourseResource.setCourseCollege(course.getCollege().getCollegeName());
            teacherCourseResource.setCourseCollegeCode(course.getCollege().getCollegeCode());
            teacherCourseResource.setCourseCollegeId(course.getCollege().getId());
            teacherCourseResource.setCourseCategoryId(courseTables.get(0).getCourseCategory() == null ? null
                    : String.valueOf(courseTables.get(0).getCourseCategory().getId()));
            teacherCourseResource.setCourseCategory(courseTables.get(0).getCourseCategory() == null ? null
                    : courseTables.get(0).getCourseCategory().getCourseCategoryName());
            teacherCourseResource.setTeacherName(getTeacherNameList(courseTables)
                    .stream().map(String::trim).distinct().collect(Collectors.joining(",")));
            List<CourseTableDetail> courseTableDetailList = courseTables.stream()
                    .flatMap(courseTable -> courseTable.getCourseTableDetailList().stream()).collect(toList());
            teacherCourseResource.setTeacherId(getTeacherIdList(courseTableDetailList)
                    .stream().map(String::trim).distinct().collect(Collectors.joining(",")));
            teacherCourseResource.setSchoolYear(courseTables.get(0).getSchoolYear());
            teacherCourseResource.setTerm(courseTables.get(0).getTerm());
            teacherCourseResource.setStudentType(Objects.isNull(courseTables.get(0).getStudentType()) ? null :
                    courseTables.get(0).getStudentType().getIndex());
            List<Group> groupList = getGroupList(courseTables);
            List<CourseResourceTeachingClassName> courseResourceTeachingClassNameList = new ArrayList<>();
            for (Group group : groupList) {
                CourseResourceTeachingClassName courseResourceTeachingClassName = new CourseResourceTeachingClassName();
                composeCourseResourceTeachingClassName(course, group, courseResourceTeachingClassName);
                courseResourceTeachingClassNameList.add(courseResourceTeachingClassName);
            }
            List<CourseResourceTeachingClassName> sortCourseResourceTeachingClassNameList =
                    courseResourceTeachingClassNameList.stream()
                            .sorted(Comparator.comparingInt(CourseResourceTeachingClassName::getTeachingClassSort)
                                    .thenComparing((CourseResourceTeachingClassName obj) ->
                                            (obj.getTeachingClassNo() != null && obj.getTeachingClassNo().startsWith("2")) ? 0 : 1)
                                    .thenComparing(CourseResourceTeachingClassName::getTeachingClassNo))
                            .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(sortCourseResourceTeachingClassNameList)) {
                int teachingClassMembers = sortCourseResourceTeachingClassNameList.stream()
                        .mapToInt(CourseResourceTeachingClassName::getTeachingClassMembers).sum();
                teacherCourseResource.setTeachingClassStudentCount(teachingClassMembers);
            } else {
                teacherCourseResource.setTeachingClassStudentCount(0);
            }
            teacherCourseResource.setTeachingClassName(sortCourseResourceTeachingClassNameList);
            teacherCourseResource.setTeachingClassCount(groupList.size());
            List<String> courseTableDetailIdList =
                    courseTableDetailList.stream().map(CourseTableDetail::getId).collect(toList());
            List<SegmentCourseTableDetailModel> segmentCourseTableDetailModelList =
                    getSegmentCourseTableDetailModelList(courseTableDetailIdList, userId);
            String nowTime = TimeUtils.getNowDateTime();
            int completeClassHour = Math.toIntExact(segmentCourseTableDetailModelList.stream().filter(segmentCourseTableDetailModel ->
                    segmentCourseTableDetailModel.getSegmentEndTime().compareTo(nowTime) <= 0).count());
            teacherCourseResource.setCompleteClassHour(completeClassHour);
            teacherCourseResource.setTotalClassHour(segmentCourseTableDetailModelList.size());
            teacherCourseResourceList.add(teacherCourseResource);
        });
        return teacherCourseResourceList.stream().sorted(Comparator.comparing(TeacherCourseResource::getCourseCode)).collect(toList());
    }

    private void composeCourseResourceTeachingClassName(Course course, Group group, CourseResourceTeachingClassName courseResourceTeachingClassName) {
        courseResourceTeachingClassName.setTeachingClassId(group.getId());
        courseResourceTeachingClassName.setTeachingClassName(group.getGroupName());
        courseResourceTeachingClassName.setTeachingClassNo(group.getGroupNo());
        Integer sort = ObjectUtils.isEmpty(group.getSort())?0:group.getSort();
        courseResourceTeachingClassName.setTeachingClassSort(sort);
        courseResourceTeachingClassName.setClassNickName(group.getClassNickName());
        List<CourseTable> courseTableFilterList = group.getCourseTableList().stream().filter(
                courseTable -> courseTable.getCourse().getId().equals(course.getId())
                        && courseTable.getGroup().getId().equals(group.getId())).distinct().collect(toList());
        courseResourceTeachingClassName.setCourseTableId(courseTableFilterList.get(0).getId());
        if (!ObjectUtils.isEmpty(courseTableFilterList.get(0).getStudentType())) {
            courseResourceTeachingClassName.setStudentType(courseTableFilterList.get(0).getStudentType().getIndex());
        }
        String teacherName = courseTableFilterList.get(0).getTeacherName();
        if (teacherName.contains(",")) {
            teacherName = Arrays.stream(teacherName.split(",")).distinct().collect(joining(","));
        }
        courseResourceTeachingClassName.setTeachingClassTeacherName(teacherName);
        courseResourceTeachingClassName.setTeachingClassMembers(group.getGroupMemberList() == null ?
                0 : (int) group.getGroupMemberList().stream()
                .filter(g -> GroupMemberStatus.NORMAL.equals(g.getGroupMemberStatus())).count());
        courseResourceTeachingClassName.setSource(group.getSource().getIndex() + "");
        courseResourceTeachingClassName.setClassNickName(group.getClassNickName());
        courseResourceTeachingClassName.setClassCompositionName(group.getClassCompositionName());
        courseResourceTeachingClassName.setCourseTableCollegeId(courseTableFilterList.get(0).getCollegeId());
        courseResourceTeachingClassName.setCourseTableCollegeName(courseTableFilterList.get(0).getCollegeName());
        College college = collegeRepository.findById(courseTableFilterList.get(0).getCollegeId()).orElse(null);
        if (!ObjectUtils.isEmpty(college)) {
            courseResourceTeachingClassName.setCourseTableCollegeCode(college.getCollegeCode());
        }
        GroupAttribute groupAttribute = group.getGroupAttribute();
        if (!ObjectUtils.isEmpty(groupAttribute)) {
            courseResourceTeachingClassName.groupAttributeValue(groupAttribute.getValue());
            courseResourceTeachingClassName.groupAttributeName(groupAttribute.getName());
        }
    }

    private List<String> getTeacherNameList(List<CourseTable> courseTables) {
        String teacherNames = courseTables.stream().map(CourseTable::getTeacherName).distinct().collect(Collectors.joining(","));
        return Arrays.asList(teacherNames.split(","));
    }

    private List<String> getTeacherIdList(List<CourseTableDetail> courseTableDetailList) {
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetailList.stream()
                .flatMap(courseTableDetail -> courseTableDetail.getCourseTableDetailTeacherList().stream()).collect(toList());
        return courseTableDetailTeacherList.stream().map(CourseTableDetailTeacher::getTeacherId).distinct().collect(toList());
    }

    public Map<Course, List<CourseTable>> getCourseTableGroup(Map<Course, List<CourseTable>> courseTableGroup) {
        courseTableGroup = courseTableGroup.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Course::getCreateTime).thenComparing(Course::getId)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return courseTableGroup;
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
}

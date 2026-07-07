package com.lztech.site.service.coursetable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseStructure;
import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.StructureStatus;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.coursesegment.CourseSegment;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.domain.model.coursetable.enums.WeekType;
import com.lztech.domain.model.coursetabledetail.CourseParms;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetail.CourseTableDetailProject;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseAndMembers;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupAttribute;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.CourseStructureRepository;
import com.lztech.persistence.repositories.course.CourseTeachingTeamRepository;
import com.lztech.persistence.repositories.coursecategory.CourseCategoryRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetail.specification.CourseTableDetailSpecification;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.terms.TermRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.resource.course.CourseResourceStudentClassName;
import com.lztech.site.resource.course.CourseStudentResource;
import com.lztech.site.resource.course.StudentHomepageCourse;
import com.lztech.site.resource.course.StudentHomepageCourseTeachingClassName;
import com.lztech.site.resource.coursetable.CourseTableResource;
import com.lztech.site.resource.coursetable.*;
import com.lztech.site.resource.term.TermResource;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.CourseResource;
import com.lztech.site.viewmodel.CourseResourceTeachingClassName;
import com.lztech.site.viewmodel.coursetable.*;
import com.lztech.site.viewmodel.coursetabledetail.RealTimeSchedule;
import com.lztech.site.viewmodel.coursetableinfo.CourseTableInfoByRoomIdAndNowResource;
import com.lztech.site.viewmodel.coursetableinfo.CourseTableInfoResource;
import com.lztech.site.viewmodel.coursetableinfo.CourseTableInfoResourceSegmentList;
import com.lztech.site.viewmodel.coursetypecount.CourseTypeCount;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.group.TeachingClassVo;
import com.lztech.site.viewmodel.preparelessonplugin.CourseStructureInfo;
import com.lztech.site.viewmodel.preparelessonplugin.TeachingCourseInfo;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseAndCourseTableId;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseNameFirstSpell;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections.CollectionUtils;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.coursetable.specification.CourseTableSpecification.*;
import static com.lztech.persistence.repositories.coursetabledetail.specification.CourseTableDetailSpecification.getCourseTableByRoomIdNowTime;
import static com.lztech.persistence.repositories.coursetabledetail.specification.CourseTableDetailSpecification.getRoomScheduleInformationList;
import static com.lztech.site.constants.Constant.NEED_DISTINGUISH_COURSE_STUDENT_TYPE;
import static com.lztech.site.until.CharactersReplace.replaceCharacters;
import static com.lztech.site.until.DateUtils.*;
import static com.lztech.site.until.TimeUtils.getNowDate;
import static com.lztech.site.until.TimeUtils.getNowDateMin;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class CourseTableService {
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private TermService termService;
    private String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    @Autowired
    private EntityManager entityManager;
    @Value("${studentType}")
    private String studentType;

    public Map<Course, List<CourseTable>> getCourseTableGroup(Map<Course, List<CourseTable>> courseTableGroup) {
        courseTableGroup = courseTableGroup.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Course::getCreateTime).thenComparing(Course::getId)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return courseTableGroup;
    }

    private List<String> getTeacherNameList(List<CourseTable> courseTables) {
        if (courseTables == null || courseTables.isEmpty()) {
            return Collections.emptyList();
        }
        Set<String> teacherNameSet = new HashSet<>();
        courseTables.forEach(c -> {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(c.getTeacherName())) {
                teacherNameSet.addAll(Arrays.asList(c.getTeacherName().split(",")));
            }
        });
        return new ArrayList<>(teacherNameSet);

    }

    private List<String> getTeacherIdList(List<CourseTable> courseTables) {
        List<CourseTableDetail> courseTableDetailList = courseTables.stream()
                .flatMap(courseTable -> courseTable.getCourseTableDetailList().stream()).collect(toList());
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetailList.stream()
                .flatMap(courseTableDetail -> courseTableDetail.getCourseTableDetailTeacherList().stream()).collect(toList());
        return courseTableDetailTeacherList.stream().map(CourseTableDetailTeacher::getTeacherId).distinct().collect(toList());
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

    public List<CourseResource> getCourseTableResource(String courseIds, String schoolYear,
                                                       Integer term, String teacherId) {
        List<String> courseIdList = Arrays.asList(courseIds.split(","));
        List<CourseTable> courseTableList = courseTableRepository.findAll(specification(studentType, courseIdList, schoolYear, term, teacherId));
        Map<Course, List<CourseTable>> courseTableGroup = courseTableList.stream().collect(Collectors.groupingBy(CourseTable::getCourse));
        return getCourseResources(getCourseTableGroup(courseTableGroup));
    }

    public List<CourseResource> getTeacherIdCourseTableList(String userId, String schoolYear, Integer term,
                                                            String courseStudentTypes, Boolean isNeedDistinguish,
                                                            String relatedScheduleTypes) {
        List<CourseTable> courseTableList = courseTableRepository.findAll(getTeacherCourseList(userId, schoolYear,
                term, courseStudentTypes, isNeedDistinguish, relatedScheduleTypes));
        Map<Course, List<CourseTable>> courseTableGroup = courseTableList.stream().collect(Collectors.groupingBy(CourseTable::getCourse));
        return getCourseResources(getCourseTableGroup(courseTableGroup));
    }

    public List<CourseResource> getCourseResources(Map<Course, List<CourseTable>> courseTableGroup) {
        List<CourseResource> courseResourceList = new ArrayList<>();
        courseTableGroup.forEach((course, courseTables) -> {
            CourseResource courseResource = new CourseResource();
            courseResource.setCourseId(course.getId());
            courseResource.setCourseSource(Objects.isNull(course.getCourseSource()) ? 0 : course.getCourseSource().getIndex());
            courseResource.setCourseCode(course.getCourseCode());
            courseResource.setCourseName(course.getCourseName());
            courseResource.setCourseCollege(course.getCollege().getCollegeName());
            courseResource.setCourseCollegeCode(course.getCollege().getCollegeCode());
            courseResource.setCourseCollegeId(course.getCollege().getId());
            courseResource.setCourseCategoryId(courseTables.get(0).getCourseCategory() == null ? null
                    : String.valueOf(courseTables.get(0).getCourseCategory().getId()));
            courseResource.setCourseCategory(courseTables.get(0).getCourseCategory() == null ? null
                    : courseTables.get(0).getCourseCategory().getCourseCategoryName());
            courseResource.setTeacherName(getTeacherNameList(courseTables)
                    .stream().map(String::trim).distinct().collect(Collectors.joining(",")));
            courseResource.setTeacherId(getTeacherIdList(courseTables)
                    .stream().map(String::trim).distinct().collect(Collectors.joining(",")));
            courseResource.setSchoolYear(courseTables.get(0).getSchoolYear());
            courseResource.setTerm(courseTables.get(0).getTerm());
            courseResource.setStudentType(Objects.isNull(courseTables.get(0).getStudentType()) ? null :
                    courseTables.get(0).getStudentType().getIndex());
            List<CourseResourceTeachingClassName> courseResourceTeachingClassNameList = new ArrayList<>();
            List<Group> groupList = getGroupList(courseTables);
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
            courseResource.setTeachingClassName(sortCourseResourceTeachingClassNameList);
            courseResourceList.add(courseResource);
        });
        return courseResourceList;
    }

    private void composeCourseResourceTeachingClassName(Course course, Group group, CourseResourceTeachingClassName courseResourceTeachingClassName) {
        courseResourceTeachingClassName.setTeachingClassId(group.getId());
        courseResourceTeachingClassName.setTeachingClassName(group.getGroupName());
        courseResourceTeachingClassName.setTeachingClassNo(group.getGroupNo());
        Integer sort = ObjectUtils.isEmpty(group.getSort()) ? 0 : group.getSort();
        courseResourceTeachingClassName.setTeachingClassSort(sort);
        courseResourceTeachingClassName.setClassNickName(group.getClassNickName());
        List<CourseTable> courseTableFilterList = group.getCourseTableList().stream().filter(courseTable
                -> courseTable.getCourse().getId().equals(course.getId())
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
        courseResourceTeachingClassName.setTeachingClassMembers(group.getGroupMemberList() == null ? 0 :
                (int) group.getGroupMemberList().stream()
                        .filter(g -> GroupMemberStatus.NORMAL.equals(g.getGroupMemberStatus())).count());
        courseResourceTeachingClassName.setSource(group.getSource().getIndex() + "");
        courseResourceTeachingClassName.setClassNickName(group.getClassNickName());
        courseResourceTeachingClassName.setClassCompositionName(group.getClassCompositionName());
        courseResourceTeachingClassName.setCourseTableCollegeId(courseTableFilterList.get(0).getCollegeId());
        College college = collegeRepository.findById(courseTableFilterList.get(0).getCollegeId()).orElse(null);
        if (!ObjectUtils.isEmpty(college)) {
            courseResourceTeachingClassName.setCourseTableCollegeCode(college.getCollegeCode());
        }
        GroupAttribute groupAttribute = group.getGroupAttribute();
        if (!ObjectUtils.isEmpty(groupAttribute)) {
            courseResourceTeachingClassName.setGroupAttributeValue(groupAttribute.getValue());
            courseResourceTeachingClassName.setGroupAttributeName(groupAttribute.getName());
        }
        courseResourceTeachingClassName.setCourseTableCollegeName(courseTableFilterList.get(0).getCollegeName());
    }

    public ResponseEntity<List<CourseStudentResource>> convertToCourseTableList(String userId, String schoolYear,
                                                                                Integer term, String openId) {
        List<CourseTable> courseTableList = courseTableRepository.findAll(getStudentCourseList(userId, schoolYear, term, openId));
        Map<Course, List<CourseTable>> courseTableGroup = courseTableList.stream().collect(Collectors.groupingBy(CourseTable::getCourse));
        List<CourseStudentResource> courseStudentResources = getCourseStudentResources(getCourseTableGroup(courseTableGroup));
        return new ResponseEntity<>(courseStudentResources, HttpStatus.OK);
    }

    private List<CourseStudentResource> getCourseStudentResources(Map<Course, List<CourseTable>> courseTableGroup) {
        List<CourseStudentResource> courseStudentResources = new ArrayList<>();
        courseTableGroup.forEach((course, courseTables) -> {
            CourseStudentResource courseStudentResource = new CourseStudentResource();
            courseStudentResource.setCourseId(course.getId());
            courseStudentResource.setCourseCode(course.getCourseCode());
            courseStudentResource.setTeacherName(getTeacherNameList(courseTables)
                    .stream().map(String::trim).distinct().collect(Collectors.joining("、")));
            courseStudentResource.setTeacherId(getTeacherIdList(courseTables)
                    .stream().map(String::trim).distinct().collect(Collectors.joining(",")));
            courseStudentResource.setCourseName(course.getCourseName());
            courseStudentResource.setCollegeName(course.getCollegeName());
            courseStudentResource.setCourseCategoryId(courseTables.get(0).getCourseCategory() == null ? null
                    : courseTables.get(0).getCourseCategory().getId());
            courseStudentResource.setCourseCategory(courseTables.get(0).getCourseCategory() == null ? null
                    : courseTables.get(0).getCourseCategory().getCourseCategoryName());
            List<Group> groupList = getGroupList(courseTables);
            List<CourseResourceStudentClassName> courseResourceStudentClassNameList =
                    groupList.stream().map(group -> new CourseResourceStudentClassName() {{
                        this.setClassId(group.getId());
                        this.setClassName(group.getGroupName());
                        this.setClassNo(group.getGroupNo());
                        Integer sort = ObjectUtils.isEmpty(group.getSort()) ? 0 : group.getSort();
                        this.setClassSort(sort);
                        Integer classType = group.getSource() == null ? null : group.getSource().getIndex();
                        this.setClassType(classType);
                        this.setClassMembers(group.getGroupMemberList() == null ? 0 : (int) group.getGroupMemberList().stream()
                                .filter(g -> GroupMemberStatus.NORMAL.equals(g.getGroupMemberStatus())).count());
                    }}).distinct().collect(toList());
            List<CourseResourceStudentClassName> sortCourseResourceStudentClassNameList =
                    courseResourceStudentClassNameList.stream()
                            .sorted(Comparator.comparingInt(CourseResourceStudentClassName::getClassSort)
                                    .thenComparing((CourseResourceStudentClassName obj) ->
                                            (obj.getClassNo() != null && obj.getClassNo().startsWith("2")) ? 0 : 1)
                                    .thenComparing(CourseResourceStudentClassName::getClassNo))
                            .collect(Collectors.toList());
            courseStudentResource.setTeachingClasses(sortCourseResourceStudentClassNameList);
            courseStudentResources.add(courseStudentResource);
        });
        courseStudentResources.sort(Comparator.comparing(CourseStudentResource::getCourseCategoryId)
                .thenComparing(CourseStudentResource::getCourseId));
        return courseStudentResources;
    }

    public List<StudentHomepageCourse> getStudentCourseTableResource(String userId, String courseIds, String schoolYear, Integer term) {
        List<String> courseIdList = Arrays.asList(courseIds.split(","));
        List<CourseTable> courseTableList = courseTableRepository.findAll(studentSpecification(userId, courseIdList, schoolYear, term));
        Map<Course, List<CourseTable>> courseTableGroup = courseTableList.stream().collect(Collectors.groupingBy(CourseTable::getCourse));
        return getStudentCourseResources(getCourseTableGroup(courseTableGroup));
    }

    private List<StudentHomepageCourse> getStudentCourseResources(Map<Course, List<CourseTable>> courseTableGroup) {
        List<StudentHomepageCourse> studentHomepageCourses = new ArrayList<>();
        courseTableGroup.forEach((course, courseTables) -> {
            StudentHomepageCourse studentHomepageCourse = new StudentHomepageCourse();
            studentHomepageCourse.setTeacherName(getTeacherNameList(courseTables)
                    .stream().map(String::trim).distinct().collect(Collectors.joining(",")));
            studentHomepageCourse.setCourseId(course.getId());
            studentHomepageCourse.setCourseName(course.getCourseName());
            studentHomepageCourse.setCourseCollege(course.getCollegeName());
            studentHomepageCourse.setCourseCategory(courseTables.get(0).getCourseCategory() == null ? null
                    : courseTables.get(0).getCourseCategory().getCourseCategoryName());
            studentHomepageCourse.setCourseCategoryId(courseTables.get(0).getCourseCategory() == null ? null
                    : String.valueOf(courseTables.get(0).getCourseCategory().getId()));
            List<StudentHomepageCourseTeachingClassName> teachingClassNameList =
                    getGroupList(courseTables).stream().map(group ->
                            new StudentHomepageCourseTeachingClassName() {{
                                this.setTeachingClassId(group.getId());
                                this.setTeachingClassName(group.getGroupName());
                                this.setTeachingClassCode(group.getGroupNo());
                                Integer sort = ObjectUtils.isEmpty(group.getSort()) ? 0 : group.getSort();
                                this.setTeachingClassSort(sort);
                                this.setTeachingClassMembers(group.getGroupMemberList() == null ? 0 : (int) group.getGroupMemberList().stream()
                                        .filter(g -> GroupMemberStatus.NORMAL.equals(g.getGroupMemberStatus())).count());
                            }}).distinct().collect(toList());
            List<StudentHomepageCourseTeachingClassName> sortTeachingClassNameList =
                    teachingClassNameList.stream()
                            .sorted(Comparator.comparingInt(StudentHomepageCourseTeachingClassName::getTeachingClassSort)
                                    .thenComparing((StudentHomepageCourseTeachingClassName obj) ->
                                            (obj.getTeachingClassCode() != null && obj.getTeachingClassCode().startsWith("2")) ? 0 : 1)
                                    .thenComparing(StudentHomepageCourseTeachingClassName::getTeachingClassCode))
                            .collect(Collectors.toList());
            studentHomepageCourse.setTeachingClassName(sortTeachingClassNameList);
            studentHomepageCourse.setTeacherId(getTeacherIdList(courseTables)
                    .stream().map(String::trim).distinct().collect(Collectors.joining(",")));
            studentHomepageCourses.add(studentHomepageCourse);
        });
        studentHomepageCourses.sort(Comparator.comparing(StudentHomepageCourse::getCourseId));
        return studentHomepageCourses;
    }

    public List<CourseTable> saveCourseTables(List<CourseTableResource> courseTables) {
        List<CourseTable> courseTableList = new ArrayList<>();
        courseTables.forEach(courseTableResource -> {
            CourseTable courseTable = new CourseTable();
            courseTable = createCourseTable(courseTableResource);
            if (Objects.isNull(courseTable)) {
                return;
            }
            courseTableList.add(courseTable);
        });
        return courseTableRepository.saveAll(courseTableList);
    }

    private CourseTable createCourseTable(CourseTableResource courseTableResource) {
        Group group = groupRepository.findById(courseTableResource.getGroupNo()).orElse(null);
        if (Objects.isNull(group)) {
            return null;
        }
        List<CourseTable> courseTableList = courseTableRepository.findByGroupId(group.getId());
        CourseTable courseTable;
        if (courseTableList.isEmpty()) {
            courseTable = new CourseTable();
            courseTable.setCreateTime(new Date());
        } else {
            courseTable = courseTableList.get(0);
        }
        Course course = courseRepository.findByCourseCodeAndUseState(courseTableResource.getCourseCode(), true);
        courseTable.setCollegeId(course.getCollege().getId());
        courseTable.setCollegeName(course.getCollegeName());
        courseTable.setCourse(course);
        courseTable.setSchoolYear(courseTableResource.getSchoolYear());
        courseTable.setTerm(Integer.valueOf(courseTableResource.getTerm()));
        courseTable.setCourseName(course.getCourseName());
        courseTable.setGroup(group);
        courseTable.setTeacherName(courseTableResource.getTeacherName());
        courseTable.setSource(Source.DATA_DOCKING);
        courseTable.setWeekType(WeekType.getWeekTypeByName(courseTableResource.getWeekType()));
        CourseCategory courseCategory = courseCategoryRepository.findByCourseCategoryName(courseTableResource.getCourseType());
        courseTable.setCourseCategory(courseCategory);
        return courseTable;
    }

    public CourseSegmentResource queryCourseTableDetail(CourseTableDetailParam courseTableDetailParam) {
        Term nowTerm = termService.getNowTerm();
        courseTableDetailParam.setSchoolYear(nowTerm.getSchoolYear());
        courseTableDetailParam.setTerm(nowTerm.getTerm().getIndex());
        Pageable pageable = PageRequest.of(courseTableDetailParam.getPage() - 1, courseTableDetailParam.getRow());
        Page<CourseTableDetail> listCourseTable = courseTableDetailRepository
                .findAll(CourseTableDetailSpecification.courseSegmentSpecification(courseTableDetailParam), pageable);
        CourseSegmentResource courseSegmentResource = new CourseSegmentResource();
        courseSegmentResource.setPageCount(listCourseTable.getTotalPages());
        courseSegmentResource.setNoData(listCourseTable.getContent().size() == 0);
        courseSegmentResource.setCourseTables(getAllCourseTableList(listCourseTable.getContent()));
        return courseSegmentResource;
    }

    private List<CourseTableDetailsResource> getAllCourseTableList(List<CourseTableDetail> courseTableDetailList) {
        List<CourseTableDetailsResource> courseTableDetailsResources = new ArrayList<>();
        courseTableDetailList.stream().map(courseTableDetail -> new HashMap<String, Object>() {{
            CourseTableDetailsResource courseTableDetailsResource = new CourseTableDetailsResource();
            courseTableDetailsResource.setCourseType(courseTableDetail.getCourseType() == null ? null :
                    courseTableDetail.getCourseType().getId());
            courseTableDetailsResource.setCourseName(courseTableDetail.getCourseTable().getCourseName());
            if (courseTableDetail.getCourseTable().getCourseCategory() != null) {
                courseTableDetailsResource.setCourseAttr(courseTableDetail.getCourseTable().getCourseCategory().getId());
                courseTableDetailsResource.setCourseAttrName(courseTableDetail.getCourseTable().getCourseCategory().getCourseCategoryName());
            }
            if (courseTableDetail.getCourseTableDetailTeacherList().size() != 0) {
                String teacherName = courseTableDetail.getCourseTableDetailTeacherList().stream().map(courseTableDetailTeacher ->
                        courseTableDetailTeacher.getTeacherName() + "").collect(joining(","));
                courseTableDetailsResource.setTeacherName(teacherName);
            }
            courseTableDetailsResource.setClassName(courseTableDetail.getCourseTable().getGroup().getGroupName());
            courseTableDetailsResource.setCollegeName(courseTableDetail.getCourseTable().getCollegeName());
            if (courseTableDetail.getCourseTableDetailRoomUserList().size() != 0) {
                String roomNames = courseTableDetail.getCourseTableDetailRoomUserList()
                        .stream().map(CourseTableDetailRoomUser::getRoomName).collect(Collectors.joining(","));
                courseTableDetailsResource.setRoomName(roomNames);
            }
            Integer studentsNum = courseTableDetail.getCourseTable().getGroup().getGroupMemberList().size();
            courseTableDetailsResource.setStudentNum(studentsNum);
            WeekType weekType = courseTableDetail.getCourseTable().getWeekType();
            if (!ObjectUtils.isEmpty(weekType)) {
                courseTableDetailsResource.setWeekType(weekType.getWeekTypeName());
            }
            if (courseTableDetail.getCourseDate() != null) {
                courseTableDetailsResource.setCourseDate(TimeUtils.dateToStr(courseTableDetail.getCourseDate()));
            }
            courseTableDetailsResource.setWeek("第" + courseTableDetail.getWeek() + "周");
            courseTableDetailsResource.setWeekDay(weekDays[courseTableDetail.getWeekNum() - 1]);
            if (courseTableDetail.getCourseSegmentList().size() != 0) {
                List<CourseSegment> courseSegments = courseTableDetail.getCourseSegmentList().stream()
                        .sorted(Comparator.comparing(courseSegment -> courseSegment.getSegment().getSegment())).collect(toList());
                String segment = courseSegments.stream()
                        .map(courseSegment -> courseSegment.getSegment().getSegment() + "").collect(joining(","));
                courseTableDetailsResource.setSegment("第" + segment + "节次");
            }
            courseTableDetailsResources.add(courseTableDetailsResource);
        }}).collect(toList());
        return courseTableDetailsResources;
    }

    public ResponseEntity<CourseTypeCountResource> queryCourseTypeIdCount(CourseTableDetailParam param) {
        CourseTypeCountResource courseTypeCountResource = new CourseTypeCountResource();
        List<CourseTypeCount> courseTypeCounts = queryCourseTypeCount(param);
        courseTypeCountResource.setTheoryCount(0);
        courseTypeCountResource.setExperimentCount(0);
        courseTypeCountResource.setSportsCount(0);
        courseTypeCountResource.setEnglishCount(0);
        if (!courseTypeCounts.isEmpty()) {
            courseTypeCounts.forEach(courseTypeCount -> {
                if (Objects.equals(courseTypeCount.getType(), "0")) {
                    courseTypeCountResource.setTheoryCount(getValue(courseTypeCount) == null ? 0 : getValue(courseTypeCount));
                } else if (Objects.equals(courseTypeCount.getType(), "1")) {
                    courseTypeCountResource.setExperimentCount(getValue(courseTypeCount) == null ? 0 : getValue(courseTypeCount));
                } else if (Objects.equals(courseTypeCount.getType(), "2")) {
                    courseTypeCountResource.setSportsCount(getValue(courseTypeCount) == null ? 0 : getValue(courseTypeCount));
                } else if (Objects.equals(courseTypeCount.getType(), "3")) {
                    courseTypeCountResource.setEnglishCount(getValue(courseTypeCount) == null ? 0 : getValue(courseTypeCount));
                }
            });
        }
        return new ResponseEntity<>(courseTypeCountResource, HttpStatus.OK);
    }

    private Integer getValue(CourseTypeCount courseTypeCount) {
        return courseTypeCount.getCount() > 0 ? courseTypeCount.getCount() : 0;
    }

    private List<CourseTypeCount> queryCourseTypeCount(CourseTableDetailParam param) {
        Term nowTerm = termService.getNowTerm();
        if (Objects.isNull(nowTerm)) {
            return new ArrayList<>();
        }
        StringBuffer sql = new StringBuffer(" SELECT UUID() AS id,c.id AS type,COUNT( DISTINCT ctd.id  ) AS count," +
                " c.sort_name  " +
                " FROM tb_course_table_detail ctd ");
        sql.append(" LEFT JOIN tb_course_table ct ON ct.id = ctd.course_table_id  ");
        sql.append(" LEFT JOIN tb_course_segment cs ON cs.course_table_detail_id = ctd.id ");
        sql.append(" LEFT JOIN tb_course_table_detail_room_user ctdru ON ctdru.course_table_detail_id = ctd.id  ");
        sql.append(" LEFT JOIN tb_course_type c ON ctd.course_type_id = c.id ");
        sql.append(" WHERE ct.school_year = :schoolYear AND ct.term = :term ");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", nowTerm.getSchoolYear());
        paramMap.put("term", nowTerm.getTerm().getIndex());
        if (!StringUtils.isEmpty(param.getCourseDate())) {
            sql.append(" AND ctd.course_date= :courseDate AND cs.segment=  :segment ");
            paramMap.put("courseDate", param.getCourseDate());
            paramMap.put("segment", param.getSegment());
        }
        if (!ObjectUtils.isEmpty(param.getStudentType()) && StudentType.getStudentType(param.getStudentType()) != null) {
            sql.append(" AND ct.student_type = :studentType ");
            paramMap.put("studentType", param.getStudentType());
        }
        if (CollectionUtils.isNotEmpty(param.getClassRooms())) {
            sql.append(" AND ctdru.room_id IN ( :roomIdList )");
            paramMap.put("roomIdList", param.getClassRooms());
        }
        if (!StringUtils.isEmpty(param.getCollegeId())) {
            sql.append(" AND ct.college_id= :collegeId ");
            paramMap.put("collegeId", param.getCollegeId());
        }
        if (!StringUtils.isEmpty(param.getTeacherNameOrCourseName())) {
            String teacherNameOrCourseName = replaceCharacters(param.getTeacherNameOrCourseName()).trim();
            sql.append(" AND ( ct.course_name LIKE  :teacherNameOrCourseName  OR ct.teacher_name LIKE :teacherNameOrCourseName )");
            paramMap.put("teacherNameOrCourseName", "%" + teacherNameOrCourseName + "%");
        }
        if (param.getWeekNum() != 0) {
            sql.append(" AND ctd.week_num= :weekNum ");
            paramMap.put("weekNum", param.getWeekNum());
        }
        if (param.getWeek() != 0) {
            sql.append(" AND ctd.week= :week ");
            paramMap.put("week", param.getWeek());
        }
        if (StringUtils.isEmpty(param.getBuildingId()) && param.getSegment() != 0) {
            sql.append(" AND cs.segment= :segment ");
            paramMap.put("segment", param.getSegment());
        }
        if (!StringUtils.isEmpty(param.getBuildingId()) && param.getSegment() == 0) {
            sql.append(" AND cs.build_id= :buildingId");
            paramMap.put("buildingId", param.getBuildingId());
        }
        if (!StringUtils.isEmpty(param.getBuildingId()) && param.getSegment() != 0) {
            sql.append(" AND cs.build_id= :buildingId AND cs.segment= :segment");
            paramMap.put("buildingId", param.getBuildingId());
            paramMap.put("segment", param.getSegment());
        }
        sql.append(" GROUP BY c.sort_name ");
        Query query = entityManager.createNativeQuery(sql.toString(), CourseTypeCount.class);
        paramMap.forEach(query::setParameter);
        return (List<CourseTypeCount>) query.getResultList();
    }

    public List<CourseTableDate> getCourseTableByRoomId(CourseParms courseParms) {
        Date newDate = new Date();
        List<CourseTableDetailDateSql> courseTableDetailDateSqlList = getCourseTableDetailDateSqlList(courseParms);
        List<CourseTableDate> courseTableDateList = new ArrayList<>();
        Map<String, List<CourseTableDetailDateSql>> resultMap = courseTableDetailDateSqlList.stream()
                .collect(Collectors.groupingBy(CourseTableDetailDateSql::getCourseTableDetailId));
        resultMap.forEach((courseTableDetailId, groupCourseTableDetailDateSqlList) -> {
            CourseTableDetailDateSql courseTableDetailDateSql = groupCourseTableDetailDateSqlList.get(0);
            CourseTableDate courseTableDate = new CourseTableDate();
            courseTableDate.setProjectNames(courseTableDetailDateSql.getProjectNames());
            courseTableDate.setCourseTableId(courseTableDetailDateSql.getCourseTableId());
            courseTableDate.setTeacherId(courseTableDetailDateSql.getTeacherId());
            courseTableDate.setTeacherName(courseTableDetailDateSql.getTeacherName());
            courseTableDate.setCourseDate(courseTableDetailDateSql.getCourseDate());
            courseTableDate.setWeekNum(courseTableDetailDateSql.getWeekNum());
            courseTableDate.setRoomId(StringUtils.isEmpty(courseTableDetailDateSql.getRoomId()) ? "" : courseTableDetailDateSql.getRoomId());
            courseTableDate.setRoomName(StringUtils.isEmpty(courseTableDetailDateSql.getRoomName()) ? "" : courseTableDetailDateSql.getRoomName());
            courseTableDate.setCourseName(courseTableDetailDateSql.getCourseName());
            courseTableDate.setCourseId(courseTableDetailDateSql.getCourseId());
            courseTableDate.setTeachingClassName(courseTableDetailDateSql.getTeachingClassName());
            courseTableDate.setTeachingClassId(courseTableDetailDateSql.getTeachingClassId());
            courseTableDate.setCourseAttr(courseTableDetailDateSql.getCourseAttr().intValue());
            courseTableDate.setCollegeId(courseTableDetailDateSql.getCollegeId());
            courseTableDate.setCollegeName(courseTableDetailDateSql.getCollegeName());
            courseTableDate.setSegment(courseTableDetailDateSql.getSegments());
            courseTableDate.setNowWeek(courseTableDetailDateSql.getNowWeek());
            courseTableDate.setStudentCount(String.valueOf(courseTableDetailDateSql.getStudentCount().intValue()));
            courseTableDate.setCourseType(courseTableDetailDateSql.getCourseType());
            courseTableDate.setCourseTypeName(courseTableDetailDateSql.getCourseTypeName());
            courseTableDate.setWeekType(WeekType.getWeekTypeSource(courseTableDetailDateSql.getWeekType()).getWeekTypeName());
            courseTableDate.setClassName(courseTableDetailDateSql.getClassName());
            courseTableDate.setCourseCategory(courseTableDetailDateSql.getCourseCategory());
            courseTableDate.setCourseCategoryName(courseTableDetailDateSql.getCourseCategoryName());
            List<CourseTableDateSegmentList> courseTableDateSegmentListList = getCourseTableDateSegmentLists(groupCourseTableDetailDateSqlList);
            courseTableDateSegmentListList = courseTableDateSegmentListList.stream()
                    .sorted(Comparator.comparingInt(CourseTableDateSegmentList::getSegment)).collect(Collectors.toList());
            courseTableDate.setSegmentList(courseTableDateSegmentListList);
            List<CourseTableDateSegmentList> courseTableDateSegmentLists = courseTableDate.getSegmentList();
            String startTime = courseTableDateSegmentLists.stream().sorted(Comparator.comparing(CourseTableDateSegmentList::getStartTime))
                    .map(CourseTableDateSegmentList::getStartTime).findFirst().orElse(null);
            String endTime = courseTableDateSegmentLists.stream().sorted(Comparator.comparing(CourseTableDateSegmentList::getEndTime).reversed())
                    .map(CourseTableDateSegmentList::getEndTime).findFirst().orElse(null);
            courseTableDate.startTime(startTime);
            courseTableDate.endTime(endTime);
            String segmentStartTimeCharacter = courseTableDate.getCourseDate() + " " + courseTableDate.getStartTime();
            String segmentEndTimeCharacter = courseTableDate.getCourseDate() + " " + courseTableDate.getEndTime();
            if (isValidDate(segmentStartTimeCharacter) && isValidDate(segmentEndTimeCharacter)) {
                Date segmentStartTime = DateUtils.stringToDate(DateUtils.DATE_TIME_MINUTES, segmentStartTimeCharacter);
                Date segmentEndTime = DateUtils.stringToDate(DateUtils.DATE_TIME_MINUTES, segmentStartTimeCharacter);
                courseTableDate.setStatus(isWithinRange(newDate, segmentStartTime, segmentEndTime));
            }
            courseTableDateList.add(courseTableDate);
        });
        return courseTableDateList.stream().sorted(Comparator.comparing(CourseTableDate::getRoomId)
                .thenComparing(CourseTableDate::getStartTime)).collect(Collectors.toList());
    }

    private static List<CourseTableDateSegmentList> getCourseTableDateSegmentLists(
            List<CourseTableDetailDateSql> groupCourseTableDetailDateSqlList) {
        List<CourseTableDateSegmentList> courseTableDateSegmentListList = new ArrayList<>();
        for (CourseTableDetailDateSql tableDetailDateSqlVo : groupCourseTableDetailDateSqlList) {
            CourseTableDateSegmentList courseTableDateSegment = new CourseTableDateSegmentList();
            courseTableDateSegment.setSegment(tableDetailDateSqlVo.getSegment());
            courseTableDateSegment.setStartTime(tableDetailDateSqlVo.getStartTime());
            courseTableDateSegment.setEndTime(tableDetailDateSqlVo.getEndTime());
            courseTableDateSegmentListList.add(courseTableDateSegment);
        }
        return courseTableDateSegmentListList;
    }

    public int isWithinRange(Date date, Date date1, Date date2) {
        long time = date.getTime(); // 获取date的毫秒时间戳
        long time1 = date1.getTime(); // 获取date1的毫秒时间戳
        long time2 = date2.getTime(); // 获取date2的毫秒时间戳
        if (time < Math.min(time1, time2)) {
            return Constant.ZREO;
        } else if (time <= Math.max(time1, time2)) {
            return Constant.ONE;
        } else {
            return Constant.TWO;
        }
    }

    public boolean isValidDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_MINUTES); // 根据你的日期格式调整
        try {
            formatter.parse(dateString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<CourseTableDetailDateSql> getCourseTableDetailDateSqlList(CourseParms courseParms) {
        Map<String, Object> paramMap = new HashMap<>();
        List<CourseTableDetailDateSql> courseTableDetailDateSqlList = new ArrayList<>();
        Integer week = courseParms.getWeek();
        String courseDate = courseParms.getCourseDate();
        String querySql = getCourseTableDetailDateSqlSql();
        if (StringUtils.isEmpty(week)) {
            courseDate = StringUtils.isEmpty(courseDate) ? getNowDate() : courseDate;//当前时间
            courseParms.setCourseDate(courseDate);
            querySql = getDayCourseTableDetailDateSqlList(courseParms, paramMap, querySql);
            querySql += " group by ctd.id,tcs.segment ORDER BY roomId asc,startTime ASC;";
        } else {
            String schoolYear = courseParms.getSchoolYear();
            Integer term = courseParms.getTerm();
            if (StringUtils.isEmpty(schoolYear) || term == null) {
                Term nowTerm = termService.getNowTerm();
                courseParms.setSchoolYear(nowTerm.getSchoolYear());
                courseParms.setTerm(nowTerm.getTerm().getIndex());
            }
            querySql = getWeekCourseTableDetailDateSqlList(courseParms, paramMap, querySql);
            querySql += " group by ctd.id,tcs.segment ORDER BY roomId asc,courseDate asc,startTime ASC;";
        }
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailDateSql.class));
        courseTableDetailDateSqlList = query.getResultList();
        return courseTableDetailDateSqlList;
    }

    private String getWeekCourseTableDetailDateSqlList(CourseParms courseParms, Map<String, Object> paramMap, String querySql) {
        Integer week = courseParms.getWeek();
        String schoolYear = courseParms.getSchoolYear();
        Integer term = courseParms.getTerm();
        String roomId = courseParms.getRoomId();
        String teacherId = courseParms.getTeacherId();
        String studentId = courseParms.getStudentId();
        String courseInfoId = courseParms.getCourseInfoId();
        if (!StringUtils.isEmpty(week)) {
            querySql += " and ctd.week=:week ";
            paramMap.put("week", week);
        }
        if (!StringUtils.isEmpty(schoolYear)) {
            querySql += " and ct.school_year=:schoolYear ";
            paramMap.put("schoolYear", schoolYear);
        }
        if (!StringUtils.isEmpty(term)) {
            querySql += " and ct.term=:term ";
            paramMap.put("term", term);
        }
        if (!StringUtils.isEmpty(roomId)) {
            querySql += " and ctdru.room_id=:roomId ";
            paramMap.put("roomId", roomId);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            querySql += " and EXISTS( select teacher_id " +
                    "from tb_course_table_detail_teacher where teacher_id =:teacherId and course_table_detail_id=ctd.id) ";
            paramMap.put("teacherId", teacherId);
        }
        if (!StringUtils.isEmpty(studentId)) {
            querySql += " and EXISTS(select group_id from tb_group_member where student_id =:studentId " +
                    " and group_member_status= '0'  and group_id = tg.id)";
            paramMap.put("studentId", studentId);
        }
        if (!StringUtils.isEmpty(courseInfoId)) {
            querySql += " and b.id=:courseInfoId ";
            paramMap.put("courseInfoId", courseInfoId);
        }
        return querySql;
    }

    private String getDayCourseTableDetailDateSqlList(CourseParms courseParms, Map<String, Object> paramMap, String querySql) {
        String courseInfoId = courseParms.getCourseInfoId();
        String courseDate = courseParms.getCourseDate();
        String roomId = courseParms.getRoomId();
        String teacherId = courseParms.getTeacherId();
        String studentId = courseParms.getStudentId();
        String schoolYear = courseParms.getSchoolYear();
        Integer term = courseParms.getTerm();
        querySql += " and DATE_FORMAT( ctd.course_date,'%Y-%m-%d')=:courseDate ";
        paramMap.put("courseDate", courseDate);
        if (!StringUtils.isEmpty(courseInfoId)) {
            querySql += " and b.id=:courseInfoId ";
            paramMap.put("courseInfoId", courseInfoId);
        }
        if (!StringUtils.isEmpty(schoolYear)) {
            querySql += " and ct.school_year=:schoolYear ";
            paramMap.put("schoolYear", schoolYear);
        }
        if (!StringUtils.isEmpty(term)) {
            querySql += " and ct.term=:term ";
            paramMap.put("term", term);
        }
        if (!StringUtils.isEmpty(roomId)) {
            querySql += " and ctdru.room_id=:roomId ";
            paramMap.put("roomId", roomId);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            querySql += " and EXISTS( select teacher_id " +
                    "from tb_course_table_detail_teacher where teacher_id =:teacherId and course_table_detail_id=ctd.id) ";
            paramMap.put("teacherId", teacherId);
        }
        if (!StringUtils.isEmpty(studentId)) {
            querySql += " and EXISTS(select group_id from tb_group_member where student_id =:studentId and group_member_status= '0'  " +
                    " and group_id = tg.id)";
            paramMap.put("studentId", studentId);
        }
        return querySql;
    }

    private String getCourseTableDetailDateSqlSql() {
        return "select ctd.id as 'courseTableDetailId', tg.group_name as 'className' ,ct.college_id as 'collegeId',  " +
                " CONCAT(DATE_FORMAT( ctd.course_date,'%Y-%m-%d'), ' ', ctd.segment_start_time)  as 'segmentStartTime', " +
                " CONCAT(DATE_FORMAT( ctd.course_date,'%Y-%m-%d'), ' ', ctd.segment_end_time)    as 'segmentEndTime', " +
                "ct.college_name as 'collegeName',0 as 'courseAttr',ct.course_category_id as 'courseCategory',  " +
                "(select course_category_name from   tb_course_category where id = ct.course_category_id limit 1) as 'courseCategoryName',  " +
                " DATE_FORMAT( ctd.course_date,'%Y-%m-%d') as 'courseDate',  " +
                "ct.course_id as 'courseId',ct.course_name as 'courseName',ctd.id as 'courseTableId',  " +
                "ctd.course_type_id as 'courseType',cty.course_type_name as 'courseTypeName',IFNULL(ctd.`week`,null) as 'nowWeek',  " +
                "GROUP_CONCAT(DISTINCT ctdp.project_name) as 'projectNames',  " +
                "ctdru.room_id as 'roomId',ctdru.room_name as 'roomName',ctd.segment as 'segments',  " +
                "(select count(DISTINCT student_id) from tb_group_member " +
                "where group_id = tg.id and group_member_status=0) as 'studentCount',  " +
                "(select GROUP_CONCAT(DISTINCT teacher_id) from tb_course_table_detail_teacher " +
                "where course_table_detail_id = ctd.id) as  'teacherId'," +
                "(select GROUP_CONCAT(DISTINCT teacher_name) from tb_course_table_detail_teacher " +
                "where course_table_detail_id = ctd.id) as 'teacherName'," +
                "tg.id as 'teachingClassId',tg.group_name as 'teachingClassName',ctd.week_num as 'weekNum',  " +
                "ct.week_type as 'weekType',tcs.segment as 'segment',  " +
                "(select start_time from tb_course_segment cs   " +
                "INNER JOIN tb_segment s on cs.build_id=s.buildid and cs.segment=s.segment   " +
                "where cs.course_table_detail_id = ctd.id and cs.segment=tcs.segment limit 1) as 'startTime',  " +
                "(select end_time from tb_course_segment cs   " +
                "INNER JOIN tb_segment s   on cs.build_id=s.buildid and cs.segment=s.segment   " +
                "where cs.course_table_detail_id = ctd.id and cs.segment=tcs.segment limit 1) as 'endTime'  " +
                "from tb_course_table_detail ctd   " +
                "INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id  " +
                "INNER JOIN tb_group tg  on ct.group_id = tg.id  " +
                "left JOIN tb_course_type cty on ctd.course_type_id = cty.id  " +
                "left JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id  " +
                "INNER JOIN tb_course_segment tcs on ctd.id = tcs.course_table_detail_id  " +
                "LEFT JOIN tb_course_table_detail_project ctdp on ctd.id = ctdp.course_table_detail_id where 1=1  ";
    }

    public ResponseEntity<List<RoomResource>> getCourseOccupancyLabList(List<String> courseDateList, String courseSegments) {
        String querySql = findCourseDateRoomInfoSql(courseDateList, courseSegments);
        Query query = entityManager.createNativeQuery(querySql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(RoomResource.class));
        List<RoomResource> roomResourceList = query.getResultList();
        if (CollectionUtils.isNotEmpty(roomResourceList)) {
            return new ResponseEntity<>(roomResourceList, HttpStatus.OK);
        }
        return new ResponseEntity(ErrorResult.dataNotExistError("实验室"), HttpStatus.NOT_FOUND);
    }

    private String findCourseDateRoomInfoSql(List<String> courseDateList, String segments) {
        String sql = "SELECT ctdru.room_code as roomCode,ctdru.room_id as roomId," +
                " ctdru.room_name as roomName, date_format(course_date,'%Y-%m-%d')  as courseDate," +
                " ctd.segment as courseSegments FROM  tb_course_table_detail ctd " +
                " JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " JOIN tb_course_table_detail_room_user ctdru ON ctd.id = ctdru.course_table_detail_id where source=5";
        if (CollectionUtils.isNotEmpty(courseDateList)) {
            StringBuilder courseDateBuilder = new StringBuilder();
            for (String courseDate : courseDateList) {
                courseDateBuilder.append("\'");
                courseDateBuilder.append(courseDate);
                courseDateBuilder.append("\',");
            }
            sql += " and ctd.course_date IN (" + courseDateBuilder.substring(0, courseDateBuilder.length() - 1) + ")";
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(segments)) {
            List<String> courseSegmentList = Arrays.asList(segments.split(","));
            StringBuilder segmentBuilder = new StringBuilder();
            for (String segment : courseSegmentList) {
                segmentBuilder.append("\'");
                segmentBuilder.append(segment);
                segmentBuilder.append("\',");
            }
            sql += " and cs.segment IN (" + segmentBuilder.substring(0, segmentBuilder.length() - 1) + ")";
        }
        sql += " group by ctdru.id ";
        return sql;
    }

    public ResponseEntity<CourseTableVo> getCourseTableById(String id) {
        CourseTable courseTable = courseTableRepository.findById(id).orElse(null);
        if (courseTable != null) {
            CourseTableVo courseTableVo = new CourseTableVo();
            courseTableVo.setCollegeId(courseTable.getCollegeId());
            courseTableVo.setCollegeName(courseTable.getCollegeName());
            if (courseTable.getGroup() != null) {
                courseTableVo.setGroupId(courseTable.getGroup().getId());
                courseTableVo.setGroupName(courseTable.getGroup().getGroupName());
                courseTableVo.setGroupMember(courseTable.getGroup().getGroupMemberList().size());
            }
            if (courseTable.getCourseCategory() != null) {
                courseTableVo.setCourseCategory(courseTable.getCourseCategory().getId());
                courseTableVo.setCourseCategoryName(courseTable.getCourseCategory().getCourseCategoryName());
            }
            return new ResponseEntity<>(courseTableVo, HttpStatus.OK);
        }
        return new ResponseEntity(ErrorResult.dataNotExistError("课表"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity getCourseTableBaseInfo(String courseTableId, Integer type) {
        CourseTable courseTable = courseTableRepository.findById(courseTableId).orElse(null);
        if (courseTable != null) {
            CourseTableBaseInfo courseTableBaseInfo = new CourseTableBaseInfo();
            courseTableBaseInfo.setCourseId(courseTable.getCourse().getId());
            courseTableBaseInfo.setGroupId(courseTable.getGroup().getId());
            courseTableBaseInfo.setSchoolYear(courseTable.getSchoolYear());
            courseTableBaseInfo.setTerm(courseTable.getTerm());
            courseTableBaseInfo.setCollegeId(courseTable.getCollegeId());
            courseTableBaseInfo.setCollegeName(courseTable.getCollegeName());
            return new ResponseEntity<>(courseTableBaseInfo, HttpStatus.OK);
        }
        return new ResponseEntity(ErrorResult.dataNotExistError("课表"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<CourseTableInfoResource>> getCourseTableInfoListResource(Integer courseInfoSource) {
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository.findAll
                (getRoomScheduleInformationList(stringToDate(DATE, formatDate(DATE, getSqlDate())), courseInfoSource));
        if (courseTableDetailList.size() > 0) {
            List<CourseTableInfoResource> courseTableInfoResourceList = courseTableDetailList
                    .stream().map(courseTableDetail -> new CourseTableInfoResource() {{
                        this.setCourseDate(formatDate(DATE, courseTableDetail.getCourseDate()));
                        this.setCourseId(courseTableDetail.getCourseTable().getCourse().getId());
                        this.setCourseName(courseTableDetail.getCourseTable().getCourse().getCourseName());
                        this.setCourseTableDetailId(courseTableDetail.getId());
                        this.setStartTime(formatDate(TIME, courseTableDetail.getSegmentStartTime()));
                        this.setEndTime(formatDate(TIME, courseTableDetail.getSegmentEndTime()));
                        this.setCourseTableId(courseTableDetail.getCourseTable().getId());
                        this.setRoomId(courseTableDetail.getCourseTableDetailRoomUserList().stream()
                                .map(CourseTableDetailRoomUser::getRoomId).collect(joining(",")));
                        this.setRoomName(courseTableDetail.getCourseTableDetailRoomUserList().stream()
                                .map(CourseTableDetailRoomUser::getRoomName).collect(joining(",")));
                        this.setSegment(Arrays.stream(courseTableDetail.getSegment().split(","))
                                .sorted(Comparator.comparingInt(Integer::parseInt)).collect(joining(",")));
                        this.setTeacherId(courseTableDetail.getCourseTableDetailTeacherList().stream()
                                .map(CourseTableDetailTeacher::getTeacherId).collect(joining(",")));
                        this.setTeacherName(courseTableDetail.getCourseTableDetailTeacherList().stream()
                                .map(CourseTableDetailTeacher::getTeacherName).collect(joining(",")));
                        this.setClassName(courseTableDetail.getCourseTable().getGroup().getGroupName());
                        this.setSegmentList(courseTableDetail.getCourseSegmentList()
                                .stream().map(courseSegment -> new CourseTableInfoResourceSegmentList() {{
                                    this.setSegment(courseSegment.getSegment().getSegment());
                                    this.setStartTime(courseSegment.getSegment().getStartTime());
                                    this.setEndTime(courseSegment.getSegment().getEndTime());
                                }}).collect(toList()));
                    }}).collect(toList());
            return new ResponseEntity(courseTableInfoResourceList, HttpStatus.OK);
        } else {
            return new ResponseEntity(ErrorResult.dataNotExistError(formatDate(DATE, getSqlDate()) + " 课表"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<CourseTableInfoByRoomIdAndNowResource>> getCourseTableByRoomIdAndNowTime
            (Integer courseInfoSource, String roomId) {
        List<CourseTableDetail> courseTableListByRoomIdNowTime = courseTableDetailRepository.findAll
                (getCourseTableByRoomIdNowTime(courseInfoSource, roomId));
        if (CollectionUtils.isNotEmpty(courseTableListByRoomIdNowTime)) {
            List<CourseTableInfoByRoomIdAndNowResource> courseTableInfoByRoomIdAndNowResourceList =
                    courseTableListByRoomIdNowTime.stream().map(courseTableDetail -> new CourseTableInfoByRoomIdAndNowResource() {{
                        this.setClassName(courseTableDetail.getCourseTable().getGroup().getGroupName());
                        this.setCollegeName(courseTableDetail.getCourseTable().getCollegeName());
                        this.setCourseName(courseTableDetail.getCourseTable().getCourseName());
                        this.setCourseDate(formatDate(DATE, courseTableDetail.getCourseDate()));
                        this.setCourseTableDetailId(courseTableDetail.getId());
                        this.setCourseType(courseTableDetail.getCourseType().getCourseTypeName());
                        this.setRoomId(courseTableDetail.getCourseTableDetailRoomUserList().stream()
                                .map(CourseTableDetailRoomUser::getRoomId).collect(joining(",")));
                        this.setRoomName(courseTableDetail.getCourseTableDetailRoomUserList().stream()
                                .map(CourseTableDetailRoomUser::getRoomName).collect(joining(",")));
                        this.setSegmentList(Arrays.stream(courseTableDetail.getSegment().split(","))
                                .sorted(Comparator.comparingInt(Integer::parseInt)).collect(joining(",")));
                        this.setTeacherName(courseTableDetail.getCourseTableDetailTeacherList().stream()
                                .map(CourseTableDetailTeacher::getTeacherName).collect(joining(",")));
                        this.setStudentCount(courseTableDetail.getCourseTable().getGroup().getGroupMemberList().size() + "");
                    }}).collect(toList());
            return new ResponseEntity(courseTableInfoByRoomIdAndNowResourceList, HttpStatus.OK);
        } else {
            return new ResponseEntity(ErrorResult.dataNotExistError(formatDate(DATE, getSqlDate()) + " 课表"), HttpStatus.NOT_FOUND);
        }
    }

    //region 接口-获取开课课程信息
    public ResponseEntity<List<CourseTableBasicInfo>> getCourseTablesBasicInfos() {
        String sql = "SELECT course_id AS courseId,course_name AS courseName FROM tb_course_table GROUP BY course_id ";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<CourseTableBasicInfo> courseTableBasicInfos = JSONArray.parseArray(JSONObject.toJSON(query.getResultList()).toString(),
                CourseTableBasicInfo.class);
        return new ResponseEntity(courseTableBasicInfos, HttpStatus.OK);
    }

    //endregion
    public ResponseEntity<List<CourseTableTeacherResource>> getCourseTableTeacherInfo(String courseTableIds) {
        List<CourseTableTeacherResource> courseTableTeacherResourceList = new ArrayList<>();
        String querySql = "select  course_table_id as courseTableId,teacher_id as teacherIds,teacher_name as teacherNames," +
                " teacher_no as teacherNos" +
                " from tb_course_table_detail_teacher t " +
                " inner join tb_course_table_detail d on t.course_table_detail_id=d.id " +
                " where 1=1 ";
        if (org.apache.commons.lang3.StringUtils.isNotBlank(courseTableIds)) {
            List<String> courseTableIdList = Arrays.asList(courseTableIds.split(","));
            StringBuilder suffer = new StringBuilder();
            for (String courseTableId : courseTableIdList) {
                suffer.append("\'");
                suffer.append(courseTableId);
                suffer.append("\',");
            }
            querySql += " and course_table_id in(" + suffer.substring(0, suffer.length() - 1) + ")";
        }
        querySql += " GROUP BY d.course_table_id,t.teacher_id";
        Query query = entityManager.createNativeQuery(querySql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableTeacherResource.class));
        List<CourseTableTeacherResource> queryResultList = query.getResultList();
        if (CollectionUtils.isNotEmpty(queryResultList)) {
            courseTableTeacherResourceList = transformCourseTableTeachers(queryResultList);
        }
        return new ResponseEntity<>(courseTableTeacherResourceList, HttpStatus.OK);
    }

    private List<CourseTableTeacherResource> transformCourseTableTeachers(List<CourseTableTeacherResource> queryResultList) {
        List<CourseTableTeacherResource> courseTableTeacherResourceList = new ArrayList<>();
        Map<String, List<CourseTableTeacherResource>> resultMap =
                queryResultList.stream().collect(Collectors.groupingBy(CourseTableTeacherResource::getCourseTableId));
        resultMap.forEach((courseTableId, teacherList) -> {
            CourseTableTeacherResource courseTableTeacherResource = new CourseTableTeacherResource();
            courseTableTeacherResource.setCourseTableId(courseTableId);
            courseTableTeacherResource.setTeacherIds(teacherList.stream().map(CourseTableTeacherResource::getTeacherIds)
                    .collect(Collectors.joining(",")));
            courseTableTeacherResource.setTeacherNames(teacherList.stream().map(CourseTableTeacherResource::getTeacherNames)
                    .collect(Collectors.joining(",")));
            courseTableTeacherResource.setTeacherNos(teacherList.stream().map(CourseTableTeacherResource::getTeacherNos)
                    .collect(Collectors.joining(",")));
            courseTableTeacherResourceList.add(courseTableTeacherResource);
        });
        return courseTableTeacherResourceList;
    }

    public ResponseEntity<List<StatisticsResource>> getCourseTableStatistics() {
        Term nowTerm = termService.getNowTerm();
        List<StatisticsResource> statisticsResources = new ArrayList<>();
        String querySql = "select  a.id AS statisticsOrder,a.course_category_name AS statisticsName,(  select count(*) " +
                "from tb_course_table b where b.course_category_id= a.id" +
                " and b.school_year = '" + nowTerm.getSchoolYear() + "' AND b.term = '" + nowTerm.getTerm().getIndex() + "' ) " +
                "as statisticsNumber from tb_course_category as a";
        Query query = entityManager.createNativeQuery(querySql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> queryResultList = query.getResultList();
        if (CollectionUtils.isNotEmpty(queryResultList)) {
            queryResultList.stream().forEach(resultMap -> {
                StatisticsResource statisticsResource = new StatisticsResource();
                statisticsResource.setStatisticsName(String.valueOf(resultMap.get("statisticsName")));
                Integer statisticsOrder = resultMap.get("statisticsOrder") == null ? 0 : Integer.valueOf(resultMap.get("statisticsOrder").toString());
                statisticsResource.setStatisticsOrder(statisticsOrder);
                Integer statisticsNumber = resultMap.get("statisticsNumber") == null ? 0 :
                        Integer.parseInt(resultMap.get("statisticsNumber").toString());
                statisticsResource.setStatisticsNumber(statisticsNumber);
                statisticsResources.add(statisticsResource);
            });
        }
        return new ResponseEntity<>(statisticsResources, HttpStatus.OK);
    }

    public List<StatisticsResource> getCourseTableTypeStatistics(String schoolYear, Integer term) {
        List<StatisticsResource> statisticsResources = new ArrayList<>();
        String querySql = "select  a.id AS statisticsOrder,a.course_category_name AS statisticsName,(  select count(DISTINCT b.course_id)  " +
                "from tb_course_table b where b.course_category_id= a.id" +
                " and b.school_year = '" + schoolYear + "' AND b.term = '" + term + "' ) " +
                "as statisticsNumber from tb_course_category as a";
        Query query = entityManager.createNativeQuery(querySql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> queryResultList = query.getResultList();
        if (CollectionUtils.isNotEmpty(queryResultList)) {
            queryResultList.forEach(resultMap -> {
                StatisticsResource statisticsResource = new StatisticsResource();
                statisticsResource.setStatisticsName(String.valueOf(resultMap.get("statisticsName")));
                Integer statisticsOrder = resultMap.get("statisticsOrder") == null ? 0 : Integer.valueOf(resultMap.get("statisticsOrder").toString());
                statisticsResource.setStatisticsOrder(statisticsOrder);
                Integer statisticsNumber = resultMap.get("statisticsNumber") == null ? 0 :
                        Integer.valueOf(resultMap.get("statisticsNumber").toString());
                statisticsResource.setStatisticsNumber(statisticsNumber);
                statisticsResources.add(statisticsResource);
            });
        }
        return statisticsResources;
    }

    public ResponseEntity<List<CourseCategoryResource>> getCourseCategorys() {
        List<CourseCategory> courseCategories = courseCategoryRepository.findAll();
        List<CourseCategoryResource> courseCategoryResources = courseCategories.stream().map(
                courseCategory -> new CourseCategoryResource() {{
                    this.courseCategoryId(courseCategory.getId());
                    this.courseCategoryName(courseCategory.getCourseCategoryName());
                    this.setSortName(courseCategory.getSortName());
                }}).collect(toList());
        return new ResponseEntity<>(courseCategoryResources, HttpStatus.OK);
    }

    public ResponseEntity<RealTimeSchedule> getRealtimeScheduleGet(String roomId) {
        String nowDate = getNowDate();
        String nowTime = getNowDateMin();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository.findAll
                (getRealtimeScheduleSpecification(studentType, roomId, nowDate, nowTime));
        RealTimeSchedule realTimeSchedule = new RealTimeSchedule();
        if (CollectionUtils.isNotEmpty(courseTableDetailList)) {
            CourseTableDetail nowCourseTableDetail = courseTableDetailList.stream()
                    .filter(courseTableDetail -> ((LocalTime.parse(formatDate(DATE, courseTableDetail.getCourseDate())
                            + " " + formatDate(TIME_SECOND, courseTableDetail.getSegmentStartTime()), df).equals(LocalTime.now())
                            || LocalTime.parse(formatDate(DATE, courseTableDetail.getCourseDate()) + " "
                            + formatDate(TIME_SECOND, courseTableDetail.getSegmentStartTime()), df).isBefore(LocalTime.now())))
                            && (LocalTime.parse(formatDate(DATE, courseTableDetail.getCourseDate()) + " "
                            + formatDate(TIME_SECOND, courseTableDetail.getSegmentEndTime()), df).equals(LocalTime.now())
                            || LocalTime.parse(formatDate(DATE, courseTableDetail.getCourseDate()) + " "
                            + formatDate(TIME_SECOND, courseTableDetail.getSegmentEndTime()), df).isAfter(LocalTime.now())))
                    .findFirst().orElse(null);
            if (Objects.nonNull(nowCourseTableDetail)) {
                realTimeSchedule.setCourseTableDetailId(nowCourseTableDetail.getId());
                realTimeSchedule.setNowCourseName(nowCourseTableDetail.getCourseName());
                realTimeSchedule.setNowCourseGroupName(nowCourseTableDetail.getCourseTable().getGroup().getGroupName());
                realTimeSchedule.setNowCourseGroupCount(nowCourseTableDetail.getCourseTable().getGroup().getGroupMemberList().stream()
                        .filter(m -> GroupMemberStatus.NORMAL.equals(m.getGroupMemberStatus())).count() + "");
                realTimeSchedule.setNowCourseSection(nowCourseTableDetail.getSegment());
                realTimeSchedule.setNowCourseSectionTime(formatDate(TIME, nowCourseTableDetail.getSegmentStartTime())
                        + "-" + formatDate(TIME, nowCourseTableDetail.getSegmentEndTime()));
                realTimeSchedule.setNowCourseTeacherName(nowCourseTableDetail.getTeacherNames());
                realTimeSchedule.setNowCourseDate(formatDate(DATE, nowCourseTableDetail.getCourseDate()));
                realTimeSchedule.setNowCourseCollegeName(nowCourseTableDetail.getCourseTable().getCollegeName());
                realTimeSchedule.setNowCourseWeek(dateToWeek(nowCourseTableDetail.getCourseDate()));
                realTimeSchedule.setNowCourseWeekly(nowCourseTableDetail.getWeek());
                realTimeSchedule.setNowCourseCategoryName(nowCourseTableDetail.getCourseTable().getCourseCategory().getCourseCategoryName());
                realTimeSchedule.setNowCourseCategoryId(nowCourseTableDetail.getCourseTable().getCourseCategory().getId() + "");
                nowCourseTableDetail.getCourseTableDetailRoomUserList()
                        .stream().filter(courseTableDetailRoomUser -> courseTableDetailRoomUser.getRoomId().equals(roomId)).limit(1)
                        .findFirst().ifPresent(tableDetailRoomUser -> realTimeSchedule.setNowCourseRoomName(tableDetailRoomUser.getRoomName()));
                realTimeSchedule.setNowProjectName(CollectionUtils.isEmpty(nowCourseTableDetail.getCourseTableDetailProjects()) ? null :
                        nowCourseTableDetail.getCourseTableDetailProjects().stream()
                                .map(CourseTableDetailProject::getProjectName).distinct().collect(Collectors.joining(",")));
            }
            CourseTableDetail nextNowCourseTableDetail = courseTableDetailList.stream().filter(courseTableDetail -> {
                if (nowCourseTableDetail != null) {
                    return !nowCourseTableDetail.getId().equals(courseTableDetail.getId());
                }
                return true;
            }).min(Comparator.comparing(CourseTableDetail::getSegmentStartTime)).orElse(null);
            if (Objects.nonNull(nextNowCourseTableDetail)) {
                realTimeSchedule.setNextCourseName(nextNowCourseTableDetail.getCourseName());
                realTimeSchedule.setNextCourseTeacherName(nextNowCourseTableDetail.getTeacherNames());
                realTimeSchedule.setNextCourseTime(formatDate(TIME, nextNowCourseTableDetail.getSegmentStartTime())
                        + "-" + formatDate(TIME, nextNowCourseTableDetail.getSegmentEndTime()));
                realTimeSchedule.setNextCourseSegment(nextNowCourseTableDetail.getSegment());
                realTimeSchedule.setNextProjectName(CollectionUtils.isEmpty(nextNowCourseTableDetail.getCourseTableDetailProjects()) ? null :
                        nextNowCourseTableDetail.getCourseTableDetailProjects()
                                .stream().map(CourseTableDetailProject::getProjectName).distinct().collect(Collectors.joining(",")));
            }
            return new ResponseEntity<>(realTimeSchedule, HttpStatus.OK);
        }
        return new ResponseEntity(ErrorResult.dataNotExistError("课表"), HttpStatus.NOT_FOUND);
    }

    public List<TeachingDetail> getTeachingDetailList(CourseTableSearch courseTableSearch) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = "  select a.* from  (SELECT  " +
                "  d.id as classId, d.group_name as className, a.course_id as courseNo," +
                " a.course_name as courseName, " +
                "  GROUP_CONCAT(DISTINCT c.teacher_name ) as teacherNames, " +
                "  GROUP_CONCAT(DISTINCT c.teacher_college_id )as collegeIds, " +
                "  GROUP_CONCAT(DISTINCT c.teacher_college_name ) as collegeNames," +
                " (case d.source when 0 then 0 else 1 end ) as groupSource " +
                " FROM tb_course_table a " +
                "  LEFT JOIN tb_course_table_detail b ON a.id = b.course_table_id " +
                "  LEFT JOIN tb_course_table_detail_teacher c ON b.id = c.course_table_detail_id " +
                "  LEFT JOIN tb_group d ON a.group_id = d.id where 1=1  ";
        querySql += " and a.school_year= :schoolYear and a.term = :term ";
        paramMap.put("schoolYear", courseTableSearch.getSchoolYear());
        paramMap.put("term", courseTableSearch.getTerm());
        if (!StringUtils.isEmpty(courseTableSearch.getClassName())) {
            querySql += " and d.group_name LIKE  :groupName ";
            paramMap.put("groupName", "%" + courseTableSearch.getClassName() + "%");
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(courseTableSearch.getIsDistinguishCourseStudentType())
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(courseTableSearch.getIsDistinguishCourseStudentType())) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(courseTableSearch.getCourseStudentTypeIds())) {
                List<String> courseStudentTypeIdList = Arrays.asList(courseTableSearch.getCourseStudentTypeIds().split(","));
                querySql += " and a.student_type in ( :courseStudentTypeIdList )";
                paramMap.put("courseStudentTypeIdList", courseStudentTypeIdList);
            } else {
                querySql += " and 1=0 ";
            }
        }
        if (!StringUtils.isEmpty(courseTableSearch.getCourseNameOrNo())) {
            querySql += " and (a.course_name LIKE :courseNameOrNo " +
                    " OR a.course_id LIKE :courseNameOrNo ) ";
            paramMap.put("courseNameOrNo", "%" + courseTableSearch.getCourseNameOrNo() + "%");
        }
        if (Objects.nonNull(courseTableSearch.getGroupSource())) {
            if (Source.DATA_DOCKING.getIndex() == courseTableSearch.getGroupSource()) {
                querySql += " and d.source = '0'";
            } else {
                querySql += " and d.source != '0'";
            }
        }
        querySql += " GROUP BY d.id, a.course_id, a.course_name  ORDER BY d.id ) a";
        //学院数据筛选
        List<String> collegeIds = Arrays.asList(courseTableSearch.getCollegeId().split(","));
        collegeIds = collegeIds.stream().filter(s -> !StringUtils.isEmpty(s)).collect(toList());
        for (int i = 0; i < collegeIds.size(); i++) {
            String collegeId = "collegeId" + i;
            if (i == 0) {
                querySql += " where  a.collegeIds like  :" + collegeId;
            } else {
                querySql += " or a.collegeIds like  :" + collegeId;
            }
            paramMap.put(collegeId, collegeIds.get(i));
        }
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TeachingDetail.class));
        List<TeachingDetail> teachingDetailList = query.getResultList();
        return teachingDetailList;
    }

    public List<TeachingClassVo> getTeachingClassVos(String schoolYear, Integer term, String userId, String groupName, String courseStudentTypes,
                                                     Boolean isNeedDistinguish) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = getSql(schoolYear, term, userId, groupName, courseStudentTypes, isNeedDistinguish, paramMap);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<TeachingClassVo> teachingClassVos = JSONArray.parseArray(JSONObject.toJSON(query.getResultList()).toString(),
                TeachingClassVo.class);
        return teachingClassVos.stream().sorted(Comparator.comparing(TeachingClassVo::getTeachingClassNo)).collect(toList());
    }

    private String getSql(String schoolYear, Integer term, String userId, String groupName,
                          String courseStudentTypes, Boolean isNeedDistinguish,
                          Map<String, Object> paramMap) {
        String sql = "SELECT a.group_id as teachingClassId,a.source ,tg.group_no as teachingClassNo," +
                " tg.group_name as teachingClassName , a.id as courseTableId , a.student_type as studentType," +
                " (select count(1) from tb_group_member m where m.group_id=tg.id and group_member_status=0) as teachingClassMembers " +
                " FROM tb_course_table a left JOIN tb_course_table_detail b on a.id=b.course_table_id  " +
                " left join tb_course_table_detail_teacher c on b.id = c.course_table_detail_id left JOIN tb_group tg on a.group_id = tg.id  " +
                " where a.school_year = '" + schoolYear + "' and a.term = '" + term +
                "' and tg.group_status = 0 and c.teacher_id = '" + userId + "'";
        if (!StringUtils.isEmpty(groupName)) {
            sql += " and tg.group_name like :groupName ";
            paramMap.put("groupName", "%" + CharactersReplace.replaceCharacters(groupName.trim()) + "%");
        }
        if (Objects.nonNull(isNeedDistinguish) && isNeedDistinguish) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(courseStudentTypes)) {
                List<String> courseStudentTypeList = Arrays.asList(courseStudentTypes.split(","));
                if (CollectionUtils.isNotEmpty(courseStudentTypeList)) {
                    sql += " and a.student_type in (:courseStudentTypeList)";
                    paramMap.put("courseStudentTypeList", courseStudentTypeList);
                } else {
                    sql += " and 1=0 ";
                }
            } else {
                sql += " and a.student_type='-1'";
            }
        }
        sql += " GROUP BY a.group_id ORDER BY a.group_id";
        return sql;
    }

    public ExperimentalCoursePage getExperimentalCourseList
            (String schoolYear, Integer term, Integer page, Integer pageSize, String courseName) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = buildGetExperimentalCourseSql(schoolYear, term, courseName, false, paramMap);
        int start = (page - 1) * pageSize;
        int size = pageSize;
        sql += " limit " + start + "," + size;
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, String>> experimentalCourseMapList = query.getResultList();
        ExperimentalCoursePage experimentalCoursePage = new ExperimentalCoursePage();
        if (experimentalCourseMapList.size() > 0) {
            int total = getExperimentalCourseListTotal(schoolYear, term, courseName);
            String courseTableIds = experimentalCourseMapList.stream().map(experimentalCourseMap
                    -> "'" + experimentalCourseMap.get("courseTableId") + "'").collect(Collectors.joining(","));
            List<Map<String, String>> teacherInfoMapList = getTeacherInfoMapList(courseTableIds);
            List<Map<String, String>> segmentInfoMapList = getSegmentMapList(courseTableIds);
            List<Map<String, String>> projectMapList = getProjectMapList(courseTableIds);
            List<ExperimentalCourseVo> experimentalCourseList = experimentalCourseMapList
                    .stream().map(experimentalCourseMap -> new ExperimentalCourseVo() {{
                        this.setCourseTableId(experimentalCourseMap.get("courseTableId"));
                        this.setClassName(experimentalCourseMap.get("groupName"));
                        this.setCourseName(experimentalCourseMap.get("courseName") + "(" + experimentalCourseMap.get("courseCode") + ")");
                        String teacherInfo = teacherInfoMapList.stream()
                                .filter(teacherInfoMap -> teacherInfoMap.get("courseTableId").equals(experimentalCourseMap.get("courseTableId")))
                                .map(teacherInfoMap -> teacherInfoMap.get("teacherName") + "（" + teacherInfoMap.get("teacherNo") + ")")
                                .collect(joining(","));
                        this.setTeacherInfos(teacherInfo);
                        long period = segmentInfoMapList.stream()
                                .filter(segmentInfoMap -> segmentInfoMap.get("courseTableId").equals(experimentalCourseMap.get("courseTableId")))
                                .mapToInt(segmentInfoMap -> segmentInfoMap.get("segment").split(",").length).sum();
                        this.setPeriod((int) period);
                        long projectCount = projectMapList.stream()
                                .filter(projectMap -> projectMap.get("courseTableId").equals(experimentalCourseMap.get("courseTableId")))
                                .count();
                        this.setProjectCount((int) projectCount);
                    }}).collect(toList());
            experimentalCoursePage.setExperimentalCourseList(experimentalCourseList);
            experimentalCoursePage.setTotal(total);
            experimentalCoursePage.setPageCount(((total % pageSize == 0) ? total / pageSize : (total / pageSize + 1)));
        } else {
            experimentalCoursePage.setExperimentalCourseList(new ArrayList<>());
            experimentalCoursePage.setTotal(0);
            experimentalCoursePage.setPageCount(0);
        }
        experimentalCoursePage.setPageSize(pageSize);
        experimentalCoursePage.setPage(page);
        return experimentalCoursePage;
    }

    private Integer getExperimentalCourseListTotal(String schoolYear, Integer term, String courseName) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = buildGetExperimentalCourseSql(schoolYear, term, courseName, true, paramMap);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> countMap = (Map<String, Object>) query.getResultList().get(0);
        return ((BigInteger) countMap.get("total")).intValue();
    }

    private String buildGetExperimentalCourseSql(String schoolYear, Integer term, String courseName,
                                                 boolean whetherTotalNumber, Map<String, Object> paramMap) {
        String sql = "";
        if (whetherTotalNumber) {
            sql += " SELECT  COUNT(DISTINCT ct.id) as total ";
        } else {
            sql += " SELECT DISTINCT ct.id as courseTableId,c.course_name as courseName,c.course_code as courseCode,g.group_name as groupName";
        }
        sql += " from tb_course_table ct INNER JOIN tb_course c ON c.id = ct.course_id "
                + " INNER JOIN tb_course_table_detail ctd on ct.id=ctd.course_table_id INNER JOIN tb_group g on g.id=ct.group_id  "
                + " where c.use_state=1 and ct.school_year= :schoolYear and ct.term= :term and  ctd.course_kind='1' ";
        paramMap.put("schoolYear", schoolYear);
        paramMap.put("term", term);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(courseName)) {
            sql += " and c.course_name like :courseName ";
            paramMap.put("courseName", "%" + replaceCharacters(courseName) + "%");
        }
        sql += " ORDER BY c.course_code ";
        return sql;
    }

    private List<Map<String, String>> getTeacherInfoMapList(String courseTableId) {
        String sql = " SELECT DISTINCT ctdt.teacher_no as teacherNo,ctdt.teacher_name as teacherName,ct.id as courseTableId "
                + " from tb_course_table_detail_teacher ctdt  "
                + " INNER JOIN tb_course_table_detail ctd on ctd.id=ctdt.course_table_detail_id "
                + " INNER JOIN tb_course_table ct on ct.id=ctd.course_table_id "
                + " where ct.id in (" + courseTableId + ") ";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    private List<Map<String, String>> getSegmentMapList(String courseTableId) {
        String sql = " SELECT DISTINCT  ctd.segment  as segment ,ct.id as courseTableId  from  tb_course_table_detail ctd  "
                + " INNER JOIN tb_course_table ct on ct.id=ctd.course_table_id "
                + " where ct.id in (" + courseTableId + ")  and ctd.segment is not null ";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    private List<Map<String, String>> getProjectMapList(String courseTableId) {
        String sql = "select DISTINCT  ctdp.project_id as projectId,ctdp.project_code as projectCode"
                + " ,ctdp.project_name as projectName,ct.id  as courseTableId "
                + " from  tb_course_table_detail ctd "
                + " INNER JOIN tb_course_table_detail_project ctdp on ctdp.course_table_detail_id=ctd.id "
                + " INNER JOIN tb_course_table ct on ct.id=ctd.course_table_id "
                + " where ct.id in (" + courseTableId + ") ";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public List<ExperimentalCourseProject> getExperimentalCourseProjectList(String courseTableId) {
        List<Map<String, String>> experimentalCourseProjectMapList = getProjectMapList("'" + courseTableId + "'");
        if (experimentalCourseProjectMapList.size() > 0) {
            return experimentalCourseProjectMapList.stream()
                    .map(experimentalCourseProjectMap -> new ExperimentalCourseProject() {{
                        this.setProjectId(experimentalCourseProjectMap.get("projectId"));
                        this.setProjectCode(experimentalCourseProjectMap.get("projectCode"));
                        this.setProjectName(experimentalCourseProjectMap.get("projectName"));
                    }}).collect(toList());
        }
        return new ArrayList<>();
    }

    public ResponseEntity<Integer> getCollegeINum() {
        Term nowTerm = termService.getNowTerm();
        String sql = "select course.id from tb_course_table course INNER JOIN " +
                " tb_college co on co.id = course.college_id " +
                " where  course.school_year = " + "\'" + nowTerm.getSchoolYear() + "\'" +
                " and term =" + nowTerm.getTerm().getIndex() + "  group by course.college_id";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<CourseTable> courseTables = JSONArray.parseArray(JSONObject.toJSON(query.getResultList()).toString(), CourseTable.class);
        return new ResponseEntity(courseTables.size(), HttpStatus.OK);
    }

    public ResponseEntity<Integer> getCourseTablesInfo(String schoolYear, Integer term) {
        if (StringUtils.isEmpty(schoolYear) || ObjectUtils.isEmpty(term)) {
            Term nowTerm = termService.getNowTerm();
            schoolYear = nowTerm.getSchoolYear();
            term = nowTerm.getTerm().getIndex();
        }
        String sql = "select course.id from tb_course_table course " +
                "where  course.school_year = :schoolYear and term = :term group by course.course_id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("schoolYear", schoolYear);
        query.setParameter("term", term);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<CourseTable> courseTables = JSONArray.parseArray(JSONObject.toJSON(query.getResultList()).toString(), CourseTable.class);
        return new ResponseEntity(courseTables.size(), HttpStatus.OK);
    }

    public CourseTableTermResource getCourseTableTermInfo(Term term) {
        CourseTableTermResource courseTableTermResource = new CourseTableTermResource();
        courseTableTermResource.setId(term.getId());
        courseTableTermResource.setSchoolYear(term.getSchoolYear());
        courseTableTermResource.setTerm(term.getTerm().getIndex() + "");
        courseTableTermResource.setStartDate(formatDate(DATE, term.getStartDate()));
        courseTableTermResource.setEndDate(formatDate(DATE, term.getEndDate()));
        courseTableTermResource.setDescription(term.getDescription());
        return courseTableTermResource;
    }

    public List<CourseAndMembers> getTeacherIdCourseAndMembers(String userId, String schoolYear, Integer term) {
        List<CourseAndMembers> courseAndMembersList = new ArrayList<>(1);
        List<CourseResource> teacherIdCourseTableList = getTeacherIdCourseTableList(userId, schoolYear, term, null, false, null);
        teacherIdCourseTableList.forEach(teacherIdCourseTable -> teacherIdCourseTable.getTeachingClassName().forEach(teachingClassName -> {
            CourseAndMembers courseAndMembers = new CourseAndMembers();
            courseAndMembers.setTeachingClassId(teachingClassName.getTeachingClassId());
            courseAndMembers.setTeachingClassMembers(teachingClassName.getTeachingClassMembers());
            String teachingClassTeacherName = teachingClassName.getTeachingClassTeacherName();
            if (teachingClassTeacherName.contains(",")) {
                teachingClassTeacherName = Arrays.stream(teachingClassTeacherName.split(",")).distinct().collect(joining(","));
            }
            courseAndMembers.setTeachingClassTeacherName(teachingClassTeacherName);
            courseAndMembersList.add(courseAndMembers);
        }));
        return courseAndMembersList;
    }

    public List<TermCourseStatisticsResource> getTermCourseStatisticsResourceList(Integer topNum) {
        String sql = "SELECT a.id AS termId, a.school_year AS schoolYear, " +
                "   a.term AS term, a.description as description," +
                " ( a.start_date <= DATE_FORMAT(NOW(), '%Y-%m-%d') " +
                " && a.end_date >= DATE_FORMAT(NOW(), '%Y-%m-%d')) AS whetherCurrentTerm  ,d.statisticsNumber" +
                "  FROM tb_term AS a INNER JOIN (SELECT" +
                " count(1 ) as statisticsNumber, b.school_year, b.term " +
                "FROM tb_course_table b right JOIN tb_group c ON b.group_id = c.id " +
                "WHERE  c.group_status = 0 " +
                "GROUP BY  b.school_year,  b.term) d on a.school_year=d.school_year and a.term=d.term " +
                "ORDER BY a.school_year desc , a.term desc ;";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<TermCourseStatisticsResource> termCourseStatisticsResourceList =
                JSONArray.parseArray(JSONObject.toJSON(query.getResultList()).toString(), TermCourseStatisticsResource.class);
        termCourseStatisticsResourceList = termCourseStatisticsResourceList.stream().limit(topNum).collect(toList());
        return termCourseStatisticsResourceList;
    }

    public List<CourseInfoModel> getCourseInfosBySchoolYearAndTerm(String schoolYear, String term) {
        int termNum = Integer.parseInt(term);
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT a.id as courseInfoId,a.group_id as courseInfoCode,a.course_name as courseName,a.course_id as courseId," +
                "e.course_code as courseCode,a.school_year as schoolYear,a.term,GROUP_CONCAT(DISTINCT d.teacher_id) as teacherId," +
                "GROUP_CONCAT(DISTINCT d.teacher_name) as teacherName,GROUP_CONCAT(DISTINCT d.teacher_no) as teacherNo," +
                "a.college_name as collegeName,a.college_id as collegeId,a.group_id as teachingClassId," +
                "b.group_name as teachingClassName," +
                "(case a.week_type when 0 then \"单周\" when 1 then \"双周\"  ELSE \"单双周\" end )  AS weekType , " +
                "c.course_type_id as courseType,a.notice_category as noticeCategory ,a.auxiliary_name as auxiliaryName " +
                "from tb_course_table a INNER JOIN tb_course e on a.course_id=e.id " +
                "INNER JOIN tb_group b on a.group_id=b.id LEFT JOIN tb_course_table_detail c on a.id=c.course_table_id " +
                "left JOIN tb_course_table_detail_teacher d on c.id=d.course_table_detail_id " +
                "where a.school_year= :schoolYear and a.term= :termNum and a.source=0 and a.notice_category in (1,2,3,4) GROUP BY a.id";
        paramMap.put("schoolYear", schoolYear);
        paramMap.put("termNum", termNum);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfoModel.class));
        List<CourseInfoModel> courseInfoModels = query.getResultList();
        return courseInfoModels;
    }

    public CourseTableRunningTerm getCourseTableStatisticsByStartClass(String courseId) {
        CourseTableRunningTerm courseTableRunning = new CourseTableRunningTerm();
        Term term = termService.getNowTerm();
        List<CourseTable> courseTables = courseTableRepository.findByCourseIdAndTermAndSchoolYear(
                courseId, term.getTerm().getIndex(), term.getSchoolYear());
        if (CollectionUtils.isNotEmpty(courseTables)) {
            courseTableRunning.setNowTerm(term);
        }
        List<TermResource> termResourceList = new ArrayList<>();
        List<Term> termList = termRepository.findByStartDateLessThan(new Date());
        if (CollectionUtils.isNotEmpty(termList)) {
            termList.forEach(item -> {
                if (!item.getId().equals(term.getId())) {
                    List<CourseTable> courseTablesPast = courseTableRepository.findByCourseIdAndTermAndSchoolYear(
                            courseId, item.getTerm().getIndex(), item.getSchoolYear());
                    if (CollectionUtils.isNotEmpty(courseTablesPast)) {
                        TermResource termResource = new TermResource();
                        termResource.setTerm((String.valueOf(item.getTerm().getIndex())));
                        termResource.setSchoolYear(item.getSchoolYear());
                        termResource.setDescription(item.getDescription());
                        termResource.setSchoolYearTermNickName(term.getNickName());
                        termResource.setStartDate(TimeUtils.dateToStr(item.getStartDate()));
                        termResource.setEndDate(TimeUtils.dateToStr(item.getEndDate()));
                        termResource.setId(item.getId());
                        termResourceList.add(termResource);
                    }
                }
            });
        }
        courseTableRunning.setRunningTerm(termResourceList);
        return courseTableRunning;
    }

    public List<CourseTableCollegeResource> getCourseTableColleges(String courseIds) {
        String querySql = "select DISTINCT d.id as collegeId,d.college_code as collegeCode," +
                " d.college_name as collegeName,d.show_order as showOrder " +
                " from tb_course_table t  inner join tb_college d on t.college_id=d.id where 1=1 ";
        List<String> courseIdList = Arrays.asList(courseIds.split(","));
        querySql += " and t.course_id in( :courseIdList )";
        querySql += " order by CONVERT( d.college_name USING gbk ) COLLATE gbk_chinese_ci ASC ";
        Query query = entityManager.createNativeQuery(querySql);
        query.setParameter("courseIdList", courseIdList);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableCollegeResource.class));
        return (List<CourseTableCollegeResource>) query.getResultList();
    }

    public List<TermResource> getCourseTableOperationTerms(String courseId) {
        List<TermResource> termResourceList = new ArrayList<>();
        Course course = courseRepository.findById(courseId).orElse(null);
        if ((!ObjectUtils.isEmpty(course)) && (!ObjectUtils.isEmpty(course.getCreateTime()))) {
            Date nowStartTime = DateUtils.getEndOfDay(new Date());
            String querySql = "select a.id AS id,a.description AS description," +
                    " DATE_FORMAT(a.end_date,'%Y-%m-%d') AS endDate,a.school_year AS schoolYear," +
                    " DATE_FORMAT(a.start_date,'%Y-%m-%d')  AS startDate, " +
                    " CAST(a.term  AS CHAR )AS term, " +
                    " a.nick_name as schoolYearTermNickName  from tb_term a  " +
                    " where (a.start_date <='" + DateUtils.formatDate(DATE, course.getCreateTime())
                    + "'and a.end_date >='" + DateUtils.formatDate(DATE_TIME, DateUtils.getEndOfDay(course.getCreateTime())) + "') "
                    + "or(  a.start_date >= '" + DateUtils.formatDate(DATE, course.getCreateTime()) + "'" +
                    " AND a.start_date <='" + DateUtils.formatDate(DATE_TIME, nowStartTime) + "' ) ORDER BY start_date DESC";
            Query query = entityManager.createNativeQuery(querySql);
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TermResource.class));
            termResourceList = query.getResultList();
        }
        return termResourceList;
    }

    public List<TeachingCourseInfo> getTeacherCourseTableCourseStructures(String teacherId, String schoolYear, Integer term) {
        List<TeachingCourseInfo> teachingCourseInfoList = new ArrayList<>();
        List<CourseTable> courseTableList = courseTableRepository.findAll(getTeacherCourseList(teacherId, schoolYear,
                term, null, null, null));
        List<Course> courseList = courseTableList.stream().map(CourseTable::getCourse).collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Course::getId))), ArrayList::new));
        List<String> courseIds = courseList.stream().map(Course::getId).collect(toList());
        List<CourseVersion> courseVersions = courseVersionRepository.findCourseVersionByCourseIds(courseIds);
        if (CollectionUtils.isNotEmpty(courseList)) {
            courseList.forEach(course -> {
                TeachingCourseInfo teachingCourseInfo = new TeachingCourseInfo();
                teachingCourseInfo.setCourseId(course.getId());
                teachingCourseInfo.setCourseCode(course.getCourseCode());
                teachingCourseInfo.setCourseName(course.getCourseName());
                CourseVersion courseVersion = courseVersions.stream().filter(courseVersion1 ->
                        courseVersion1.getCourse().getId().equals(teachingCourseInfo.getCourseId())).findFirst().orElse(null);
                if (!ObjectUtils.isEmpty(courseVersion)) {
                    teachingCourseInfo.setVersionId(courseVersion.getId());
                    CourseTeachingTeam courseTeachingTeam =
                            courseTeachingTeamRepository.findByTeacherIdAndCourseId(teacherId, teachingCourseInfo.getCourseId());
                    if (!ObjectUtils.isEmpty(courseTeachingTeam)) {
                        teachingCourseInfo.setCourseTeachingTeamId(courseTeachingTeam.getId());
                    }
                }
                teachingCourseInfoList.add(teachingCourseInfo);
            });
        }
        setCourseStructuresAndResources(teachingCourseInfoList);
        return teachingCourseInfoList.stream().sorted(Comparator.comparing(TeachingCourseInfo::getCourseCode)).collect(toList());
    }

    public void setCourseStructuresAndResources(List<TeachingCourseInfo> courseResourceInfos) {
        List<String> courseIds = new ArrayList<>();
        HashMap<String, TeachingCourseInfo> courseResourceInfoMap = new HashMap<>();
        for (TeachingCourseInfo courseResourceInfo : courseResourceInfos) {
            if (!ObjectUtils.isEmpty(courseResourceInfo.getCourseTeachingTeamId())) {
                courseIds.add(courseResourceInfo.getCourseId());
                courseResourceInfoMap.put(courseResourceInfo.getCourseId(), courseResourceInfo);
            }
        }
        List<Course> courses = courseRepository.findAllById(courseIds);
        List<CourseStructure> courseStructureList = courseStructureRepository.getInUseVersionCourseStructureByCourseIds(courseIds);
        if (courses.size() > 0) {
            for (Course course : courses) {
                //一级结构hashMap
                HashMap<String, CourseStructureInfo> firstCourseStructureInfoMap = new HashMap<>();
                //一级结构List
                List<CourseStructureInfo> firstCourseStructureInfoList = new ArrayList<>();
                //二级结构List
                List<CourseStructureInfo> secondCourseStructureInfoList = new ArrayList<>();
                //全结构排序（一级结构，二级结构混合）
                List<CourseStructure> courseStructures = courseStructureList.stream()
                        .filter(s -> s.getCourse().getId().equals(course.getId()))
                        .sorted(Comparator.comparing(CourseStructure::getShowOrder)).collect(Collectors.toList());
                for (CourseStructure courseStructure : courseStructures) {
                    if (courseStructure.getStructureStatus().equals(StructureStatus.NORMAL)) {
                        CourseStructureInfo courseStructureInfo = this.getCourseStructureInfo(courseStructure);
                        if (org.apache.commons.lang3.StringUtils.isEmpty(courseStructureInfo.getParentId())) {
                            firstCourseStructureInfoMap.put(courseStructureInfo.getCourseStructureId(), courseStructureInfo);
                            firstCourseStructureInfoList.add(courseStructureInfo);
                        } else {
                            secondCourseStructureInfoList.add(courseStructureInfo);
                        }
                    }
                }
                //二级结构插入一级结构
                for (CourseStructureInfo courseStructureInfo : secondCourseStructureInfoList) {
                    if (firstCourseStructureInfoMap.containsKey(courseStructureInfo.getParentId())) {
                        CourseStructureInfo firstCourseStructureInfo
                                = firstCourseStructureInfoMap.get(courseStructureInfo.getParentId());
                        firstCourseStructureInfo.getChildStructureList().add(courseStructureInfo);
                    }
                }
                String courseId = course.getId();
                if (courseResourceInfoMap.containsKey(courseId)) {
                    TeachingCourseInfo courseResourceInfo = courseResourceInfoMap.get(courseId);
                    courseResourceInfo.setCourseStructureInfoList(firstCourseStructureInfoList);
                }
            }
        }
    }

    private CourseStructureInfo getCourseStructureInfo(CourseStructure courseStructure) {
        CourseStructureInfo courseStructureInfo = new CourseStructureInfo();
        courseStructureInfo.setCourseStructureId(courseStructure.getId());
        courseStructureInfo.setCourseStructureName(courseStructure.getCourseStructureName());
        courseStructureInfo.setParentId(courseStructure.getParentId());
        courseStructureInfo.setShowOrder(courseStructure.getShowOrder());
        courseStructureInfo.setChildStructureList(new ArrayList<>());
        return courseStructureInfo;
    }


    public List<CourseInfoCode> getCourseInfoCodes(String schoolYear, String term, Integer classTime, Boolean courseCompletionStatus) {
        List<CourseInfoCode> courseInfoCodeList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        Date nowDate = new Date();
        Integer termNum = Integer.valueOf(term);
        String sql = "SELECT a.group_id AS courseInfoCode,c.course_date as courseDate " +
                "FROM tb_course_table a " +
                "LEFT JOIN tb_course_table_detail c ON a.id = c.course_table_id " +
                "WHERE a.school_year= :schoolYear and a.term= :termNum AND a.source = 0 AND a.notice_category IN ( 1, 2, 3 ,4) " +
                "GROUP BY c.id ORDER BY a.id,c.course_date ";
        paramMap.put("schoolYear", schoolYear);
        paramMap.put("termNum", termNum);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);

        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseInfoCodeVo.class));
        List<CourseInfoCodeVo> courseInfoCodes = query.getResultList();
        Map<String, List<CourseInfoCodeVo>> map = courseInfoCodes
                .stream()
                .collect(Collectors.groupingBy(CourseInfoCodeVo::getCourseInfoCode));
        map.forEach((code, codeList) -> {
            CourseInfoCode courseInfoCode = new CourseInfoCode();
            codeList.sort(Comparator.comparing(CourseInfoCodeVo::getCourseDate));
            if ("202220231000719".equals(code)) {
                int a = 0;
            }
            if (courseCompletionStatus) {
                Integer number = (int) codeList.stream().filter(infoCode -> !infoCode.getCourseDate().before(nowDate)).count();
                if (number == 0) {
                    courseInfoCode.setCourseInfoCode(code);
                    courseInfoCodeList.add(courseInfoCode);
                }
            }
            if (classTime != null && classTime >= 0) {
                Integer number = (int) codeList.stream().filter(infoCode -> infoCode.getCourseDate().before(nowDate)).count();
                if (number >= classTime) {
                    courseInfoCode.setCourseInfoCode(code);
                    courseInfoCodeList.add(courseInfoCode);
                }
            }
        });
        return courseInfoCodeList.stream().distinct().collect(Collectors.toList());
    }


    public List<StudentCourseNameFirstSpell> getCourseAndCourseTableIdsByStudentId(
            String userId, String schoolYear,
            Integer term, String openId) {

        List<StudentCourseAndCourseTableId> courseAndCourseTableIds = new ArrayList<>();
        List<CourseTable> courseTables = courseTableRepository.findAll(getStudentCourseList(userId, schoolYear, term, openId));

        Map<String, List<CourseTable>> groupByCourseIdMap = courseTables.stream().
                collect(Collectors.groupingBy(o -> o.getCourse().getId()));

        groupByCourseIdMap.forEach((courseId, list) -> {
            StudentCourseAndCourseTableId info = new StudentCourseAndCourseTableId();
            info.setCourseId(courseId);
            info.setCourseCode(list.get(0).getCourse().getCourseCode());
            info.setCourseName(list.get(0).getCourse().getCourseName());

            info.setCourseTableIds(list.stream().map(CourseTable::getId).collect(Collectors.toList()));
            info.setGroupIds(list.stream().map(o -> o.getGroup().getId()).distinct().collect(Collectors.toList()));

            courseAndCourseTableIds.add(info);
        });

        return groupAndSortCourses(courseAndCourseTableIds);
    }

    /**
     * 获取课程名称的首字母
     */
    private String getFirstSpell(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            return "#";
        }

        String firstChar = courseName.substring(0, 1);
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        try {
            // 判断是否为汉字
            if (firstChar.matches("[\\u4E00-\\u9FA5]")) {
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(firstChar.charAt(0), format);
                if (pinyinArray != null && pinyinArray.length > 0) {
                    return pinyinArray[0].substring(0, 1);
                }
            }
            // 判断是否为字母
            else if (firstChar.matches("[a-zA-Z]")) {
                return firstChar.toUpperCase();
            }
            // 数字或特殊字符
            else if (firstChar.matches("[0-9]") || !firstChar.matches("[a-zA-Z]")) {
                return "#";
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return "#";
    }

    /**
     * 获取课程名称的拼音字符串用于排序
     */
    private String getPinyinString(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            return "";
        }

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuilder pinyinBuilder = new StringBuilder();

        for (char c : courseName.toCharArray()) {
            // 如果是汉字，转换为拼音
            if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]")) {
                try {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyinArray != null && pinyinArray.length > 0) {
                        pinyinBuilder.append(pinyinArray[0]);
                    } else {
                        pinyinBuilder.append(c);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    pinyinBuilder.append(c);
                }
            } else {
                // 非汉字直接添加
                pinyinBuilder.append(c);
            }
        }

        return pinyinBuilder.toString();
    }

    /**
     * 按课程名称拼音首字母分组排序
     */
    public List<StudentCourseNameFirstSpell> groupAndSortCourses(
            List<StudentCourseAndCourseTableId> courseList) {

        if (courseList == null || courseList.isEmpty()) {
            return new ArrayList<>();
        }

        // 首先按拼音排序课程
        List<StudentCourseAndCourseTableId> sortedCourses = courseList.stream()
                .sorted((c1, c2) -> {
                    String pinyin1 = getPinyinString(c1.getCourseName());
                    String pinyin2 = getPinyinString(c2.getCourseName());
                    return pinyin1.compareTo(pinyin2);
                })
                .collect(Collectors.toList());

        // 按首字母分组
        Map<String, List<StudentCourseAndCourseTableId>> groupMap = new HashMap<>();

        for (StudentCourseAndCourseTableId course : sortedCourses) {
            String firstSpell = getFirstSpell(course.getCourseName());
            groupMap.computeIfAbsent(firstSpell, k -> new ArrayList<>()).add(course);
        }

        // 创建分组结果列表
        List<StudentCourseNameFirstSpell> result = new ArrayList<>();

        // 处理字母分组（A-Z）
        for (char c = 'A'; c <= 'Z'; c++) {
            String firstSpell = String.valueOf(c);
            if (groupMap.containsKey(firstSpell)) {
                StudentCourseNameFirstSpell group = new StudentCourseNameFirstSpell();
                group.setFirstSpell(firstSpell);
                group.setCourseList(groupMap.get(firstSpell));
                result.add(group);
            }
        }

        // 处理#分组（特殊字符和数字）
        if (groupMap.containsKey("#")) {
            StudentCourseNameFirstSpell group = new StudentCourseNameFirstSpell();
            group.setFirstSpell("#");
            group.setCourseList(groupMap.get("#"));
            result.add(group);
        }

        return result;
    }

    public List<String> getCourseTableIdsByCollegeIds(List<String> collegeIds) {
        return courseTableRepository.getCourseTableIdsByCollegeIds(collegeIds);
    }

    public List<CourseTableIdAndNameInfo> getCourseTableInfoByCollegeIds(List<String> collegeIds) {

        List<CourseTableIdAndNameInfo> courseTableIdAndNameInfoList = new ArrayList<>();
        String baseSql = this.getBaseSql(collegeIds);
        Query queryData = entityManager.createNativeQuery(baseSql);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableIdAndNameInfo.class));
        List<CourseTableIdAndNameInfo> queryCourseTableIdAndNameInfoList = queryData.getResultList();
        if (queryCourseTableIdAndNameInfoList.size() > 0) {
            for (CourseTableIdAndNameInfo courseTableIdAndNameInfo : queryCourseTableIdAndNameInfoList) {
                CourseTableIdAndNameInfo info = new CourseTableIdAndNameInfo();
                info.setCollegeName(courseTableIdAndNameInfo.getCollegeName());
                info.setCourseTableId(courseTableIdAndNameInfo.getCourseTableId());
                courseTableIdAndNameInfoList.add(info);
            }
        }
        return queryCourseTableIdAndNameInfoList;
    }

    private String getBaseSql(List<String> collegeIds) {
        return "select id as courseTableId ,college_name as collegeName from tb_course_table " +
                "where college_id in ('" + String.join("','", collegeIds) + "')";
    }
}

package com.lztech.site.service.classresource;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.domain.model.coursetable.enums.WeekType;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetail.enums.CourseStatus;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupAttribute;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.major.Major;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.coursecategory.CourseCategoryRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.site.resource.classresource.*;
import com.lztech.site.resource.group.ClassYear;
import com.lztech.site.resource.major.MajorResource;
import com.lztech.site.service.event.EventService;
import com.lztech.site.service.term.TermService;
import com.lztech.site.viewmodel.event.GroupEvent;
import net.sf.json.JSONObject;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Service
public class ClassResourceService {
    private static int three = 3, twenty = 20, ten = 10;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TermService termService;

    @Autowired
    private EventService eventService;

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;

    @Value("${default.defaultStudentType}")
    private String defaultStudentType;

    public synchronized ResponseEntity<Void> classSavePost(ClassTeacher classTeacher) {

        if (classTeacher.getClasses().size() > three) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Course course = courseRepository.findByIdAndUseStateIsTrue(classTeacher.getCourseId()).orElse(null);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Term term = termService.getNowTerm();
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        String resultMonth = month < ten ? "0" + month : String.valueOf(month);
        String resultDate = date < ten ? "0" + date : String.valueOf(date);
        String groupNoPrefix = term.getSchoolYear().replace("-", "") + resultMonth + resultDate;
        Group group = groupRepository.findByGroupNoLike(groupNoPrefix);
        int startNum = 0;
        if (Objects.nonNull(group)) {
            try {
                startNum = Integer.parseInt(group.getGroupNo().substring(group.getGroupNo().length() - three, group.getGroupNo().length()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        saveGroupList(classTeacher, course, groupNoPrefix, startNum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void saveGroupList(ClassTeacher classTeacher,
                               Course course,
                               String groupNoPrefix,
                               int startNum) {
        for (int i = 0; i < classTeacher.getClasses().size(); i++) {
            ClassResource classResource = classTeacher.getClasses().get(i);
            Group group = new Group();
            group.setCreatorId(classTeacher.getUserId());
            group.setCreatorName(classTeacher.getUserName());
            group.setCreateTime(new Date());
            group.setGroupName(classResource.getClassName());
            group.setGroupStatus(GroupStatus.NORMAL);
            group.setSource(Source.AUTONOMOUS_CLASS);
            startNum = startNum + 1;
            String groupNo = groupNoPrefix + String.format("%03d", startNum);
            group.setGroupNo(groupNo);
            group.setId(UUID.randomUUID().toString());
            group.setClassNickName(classResource.getClassName());
            group.setSort(i);
            group.setGroupAttribute(GroupAttribute.getGroupAttribute(classResource.getClassAttribute()));
            group = groupRepository.save(group);
            CourseTable courseTable = saveCourseTable(group, course, classTeacher);
            List<CourseTableDetail> courseTableDetails = saveCourseTableDetail(courseTable, 1,
                    classTeacher.getUserId(), classTeacher.getUserName());
            saveCourseTableDetailTeacher(courseTableDetails, classTeacher.getUserId(),
                    classTeacher.getUserNo(), classTeacher.getUserName(), classTeacher.getCollegeName(), classTeacher.getCollegeId());
            if (dataVisualEnable) {
                GroupEvent groupEvent = eventService.buildGroupEvent(group);
                eventService.sendGroupEvent(groupEvent.getId(), groupEvent);
            }
        }

    }

    private CourseTable saveCourseTable(Group group, Course course, ClassTeacher classTeacher) {
        CourseTable courseTable = new CourseTable();
        courseTable.setCreatorId(classTeacher.getUserId());
        courseTable.setCreatorName(classTeacher.getUserName());
        courseTable.setCreateTime(new Date());
        courseTable.setCourse(course);
        courseTable.setCreateTime(new Date());
        courseTable.setTeacherName(classTeacher.getUserName());
        courseTable.setTeacherNo(classTeacher.getUserNo());
        courseTable.setCourseTableTeacherId(classTeacher.getUserId());
        courseTable.setSchoolYear(classTeacher.getSchoolYear());
        courseTable.setTerm(classTeacher.getTerm());
        CourseCategory category = courseCategoryRepository.findById(Integer.valueOf(classTeacher.getCourseCategory()));
        courseTable.setCourseCategory(category);
        courseTable.setCourseName(course.getCourseName());
        courseTable.setSource(Source.AUTONOMOUS_CLASS);
        courseTable.setWeekType(WeekType.ONE_OR_TWO_WEEKS);
        courseTable.setCollegeId(course.getCollege() == null ? null : course.getCollege().getId());
        courseTable.setCollegeName(course.getCollege() == null ? null : course.getCollege().getCollegeName());
        courseTable.setGroup(group);
        if (StringUtils.isEmpty(classTeacher.getStudentType())) {
            courseTable.setStudentType(StudentType.getStudentType(Integer.parseInt(defaultStudentType)));
        } else {
            courseTable.setStudentType(StudentType.getStudentType(Integer.parseInt(classTeacher.getStudentType())));
        }
        courseTableRepository.save(courseTable);
        return courseTable;
    }

    private List<CourseTableDetail> saveCourseTableDetail(CourseTable courseTable, Integer courseSegment,
                                                          String userId, String userName) {
        List<CourseTableDetail> courseTableDetails = new ArrayList<>();
        for (int i = 1; i <= courseSegment; i++) {
            CourseTableDetail courseTableDetail = new CourseTableDetail();
            courseTableDetail.setCreatorId(userId);
            courseTableDetail.setCreateTime(new Date());
            courseTableDetail.setCreatorName(userName);
            courseTableDetail.setSource(Source.AUTONOMOUS_CLASS);
            courseTableDetail.setCourseTable(courseTable);
            courseTableDetail.setCourseStatus(CourseStatus.NOT_CLASS);
            courseTableDetail.setCourseName(courseTable.getCourseName());
            courseTableDetails.add(courseTableDetail);
        }
        return courseTableDetailRepository.saveAll(courseTableDetails);
    }

    private void saveCourseTableDetailTeacher(List<CourseTableDetail> courseTableDetails, String userId,
                                              String userNo, String userName, String collegeName, String collegeId) {
        List<CourseTableDetailTeacher> courseTableDetailTeachers = new ArrayList<>();
        for (CourseTableDetail courseTableDetail : courseTableDetails) {
            CourseTableDetailTeacher courseTableDetailTeacher = new CourseTableDetailTeacher();
            courseTableDetailTeacher.setCourseTableDetail(courseTableDetail);
            courseTableDetailTeacher.setTeacherId(userId);
            courseTableDetailTeacher.setTeacherNo(userNo);
            courseTableDetailTeacher.setCreatorId(userId);
            courseTableDetailTeacher.setCreatorName(userName);
            courseTableDetailTeacher.setCreateTime(new Date());
            courseTableDetailTeacher.setTeacherName(userName);
            courseTableDetailTeacher.setTeacherCollegeId(collegeId);
            courseTableDetailTeacher.setTeacherCollegeName(collegeName);
            courseTableDetailTeachers.add(courseTableDetailTeacher);
        }
        courseTableDetailTeacherRepository.saveAll(courseTableDetailTeachers);
    }

    public ResponseEntity<List<ClassYear>> getClassYear() {
        String querySql = "SELECT DISTINCT year FROM tb_major where year is not null ORDER BY year desc LIMIT 6";
        Query query = entityManager.createNativeQuery(querySql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Object> yearList = query.getResultList();
        List<ClassYear> classYears = new ArrayList<>();
        if (yearList.isEmpty()) {
            return new ResponseEntity<>(classYears, HttpStatus.OK);
        }
        ClassYear allYear = new ClassYear();
        allYear.setYear("全部");
        allYear.setChecked(true);
        classYears.add(0, allYear);
        for (Object obj : yearList) {
            JSONObject jsonObject = JSONObject.fromObject(obj);
            ClassYear classYear = new ClassYear();
            classYear.setYear(String.valueOf(jsonObject.get("year")));
            classYear.setChecked(false);
            classYears.add(classYear);
        }
        return new ResponseEntity<>(classYears, HttpStatus.OK);
    }

    public ResponseEntity<List<MajorResource>> getClassMajor(String year, String collegeId) {
        Map<String, Object> paramMap = new HashMap<>();
        Query query = entityManager.createNativeQuery(createMajorsSql(year, collegeId, paramMap), Major.class);
        paramMap.forEach(query::setParameter);
        List<Major> majorList = query.getResultList();
        List<MajorResource> majorResourceList = new ArrayList<>();
        if (majorList.isEmpty()) {
            return new ResponseEntity<>(majorResourceList, HttpStatus.OK);
        }
        return new ResponseEntity<>(getNeedsMajor(majorList), HttpStatus.OK);
    }

    private String createMajorsSql(String year, String collegeId, Map paramMap) {
        String querySql = "SELECT * FROM tb_major  where 1=1";
        if (!StringUtils.isEmpty(year)) {
            querySql += " and year =:year ";
            paramMap.put("year", year.trim());
        } else {
            querySql += " and year in (select t.year from ( SELECT DISTINCT YEAR FROM tb_major WHERE YEAR IS NOT NULL ORDER BY YEAR DESC LIMIT 6 )t)";
        }
        if (!StringUtils.isEmpty(collegeId)) {
            querySql += " and college_id =:collegeId ";
            paramMap.put("collegeId", collegeId.trim());

        }
        querySql += " order by year desc,id asc";
        return querySql;
    }

    private List<MajorResource> getNeedsMajor(List<Major> majors) {
        List<MajorResource> majorResources = new ArrayList<>();
        MajorResource majorResource = new MajorResource();
        majorResource.setMajorName("全部");
        majorResource.setChecked(true);
        majorResource.setId("");
        majorResources.add(majorResource);
        majors.forEach(major -> {
            MajorResource newMajorResource = new MajorResource();
            newMajorResource.setId(major.getId());
            newMajorResource.setChecked(false);
            newMajorResource.setMajorName(major.getMajorName());
            majorResources.add(newMajorResource);
        });
        return majorResources;
    }


    public ResponseEntity<List<AdministrativeClassResource>> getAdministrativeClass(String year, String collegeId, String majorId) {
        List<AdministrativeClassResource> administrativeClassResources = getNeedAdministrativeClass(year, collegeId, majorId);
        if (administrativeClassResources == null || administrativeClassResources.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(administrativeClassResources, HttpStatus.OK);
        }
    }

    private List<AdministrativeClassResource> getNeedAdministrativeClass(String year, String collegeId, String majorId) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = "SELECT DISTINCT class_name className FROM `tb_administrative_class_and_group` where 1=1 ";
        if (!StringUtils.isEmpty(year)) {
            querySql += " and year =:year ";
            paramMap.put("year", year.trim());
        } else {
            querySql += " and year in (select t.year from ( SELECT DISTINCT YEAR FROM tb_administrative_class_and_group " +
                    "WHERE YEAR IS NOT NULL ORDER BY YEAR " +
                    "DESC LIMIT 6 )t)";
        }

        if (!StringUtils.isEmpty(collegeId)) {
            querySql += " and college_id =:collegeId ";
            paramMap.put("collegeId", collegeId.trim());
        }
        if (!StringUtils.isEmpty(majorId)) {
            querySql += " and major_id =:majorId ";
            paramMap.put("majorId", majorId.trim() );
        }

        querySql += " order by year desc, id asc";

        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Object> classNameList = query.getResultList();
        List<AdministrativeClassResource> administrativeClassResourceList = new ArrayList<>();
        AdministrativeClassResource allClassName = new AdministrativeClassResource();
        allClassName.setAdministrativeClassName("全部");
        allClassName.setChecked(true);
        administrativeClassResourceList.add(allClassName);
        for (Object object : classNameList) {
            JSONObject jsonObject = JSONObject.fromObject(object);
            AdministrativeClassResource administrativeClassResource = new AdministrativeClassResource();
            administrativeClassResource.setAdministrativeClassName(String.valueOf(jsonObject.get("className")));
            administrativeClassResource.setChecked(false);
            administrativeClassResourceList.add(administrativeClassResource);
        }
        return administrativeClassResourceList;
    }

    public void changeSort(ClassSortResource classSort) throws Exception {
        List<ClassSort> classSortList = classSort.getClassSort();
        if (classSortList.size() > 0) {
            classSortList.forEach(sort -> {
                Optional<Group> group = groupRepository.findById(sort.getTeachingClassId());
                if (group.isPresent()) {
                    Group currentGroup = group.get();
                    currentGroup.setSort(sort.getSort());
                    groupRepository.save(currentGroup);
                }
            });
        } else {
            throw new Exception("排序数据集为空");
        }
    }
}

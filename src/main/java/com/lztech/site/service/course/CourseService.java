package com.lztech.site.service.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseCover;
import com.lztech.domain.model.course.CourseResource;
import com.lztech.domain.model.course.CourseStructure;
import com.lztech.domain.model.course.enums.CourseSource;
import com.lztech.domain.model.courseteachingpackage.CourseTeachingPackage;
import com.lztech.domain.model.downloadrecord.DownloadRecord;
import com.lztech.domain.model.downloadrecord.enums.DowmloadRecordStatus;
import com.lztech.persistence.repositories.course.CourseCoverRepository;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.downloadrecord.DownloadRecordRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.teachingpackage.TeachingPackageRepository;
import com.lztech.persistence.repositories.teachingpackage.TeachingPackageResourceRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.resource.college.CollegeCourseResource;
import com.lztech.site.resource.course.CourseInfo;
import com.lztech.site.resource.course.CourseResourceInfoVo;
import com.lztech.site.service.college.CollegeService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.course.CourseCoverVo;
import com.lztech.site.viewmodel.course.CourseData;
import com.lztech.site.viewmodel.course.CourseImageInfoVo;
import com.lztech.site.viewmodel.event.CourseEvent;
import com.lztech.site.viewmodel.event.CourseResourceEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.ConstantDataVisual.DATA_VISUAL_SOURCE_CLOUD_CLASSROOM;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private DownloadRecordRepository downloadRecordRepository;
    @Autowired
    private TeachingPackageRepository teachingPackageRepository;
    @Autowired
    private TeachingPackageResourceRepository teachingPackageResourceRepository;
    @Autowired
    private CourseCoverRepository courseCoverRepository;

    @Autowired
    private EventService eventService;
    @Autowired
    private EntityManager entityManager;
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Value("${studentType}")
    private String studentType;

    public List<Course> saveCourses(List<CourseInfo> courseInfoList) {
        List<Course> courseList = new ArrayList<>();
        courseInfoList.forEach(courseInfo -> {
            Course course = courseRepository.findByCourseCodeAndUseState(courseInfo.getCourseCode(), true);
            if (course == null) {
                course = new Course();
                course.setCreateTime(new Date());
            }
            course.setCourseCode(courseInfo.getCourseCode());
            course.setCourseName(courseInfo.getCourseName());
            College college = collegeService.getCollege(courseInfo.getCollegeName());
            course.setCollege(college);
            course.setCollegeName(college.getCollegeName());
            course.setCollegeCode(college.getCollegeCode());
            courseList.add(course);
        });
        return courseRepository.saveAll(courseList);
    }

    public Course findById(String id) {
        return courseRepository.findByIdAndUseStateIsTrue(id).orElse(null);
    }

    @Transactional
    public Result deleteCourse(String id) {
        Course course = courseRepository.findByIdAndUseState(id, true);
        if (Objects.isNull(course)) {
            return Result.error("此课程不存在");
        }
        if (CourseSource.DATA_DOCKING.equals(course.getCourseSource())) {
            return Result.error("对接的课程不允许删除");
        }
        course.setUseState(false);

        courseRepository.save(course);
        List<DownloadRecord> byCourseIdAndDowmloadRecordStatus = downloadRecordRepository.
                findByCourseIdAndPackStatus(course.getId(), DowmloadRecordStatus.GENERATE_END);
        byCourseIdAndDowmloadRecordStatus.stream().forEach(item -> {
            item.setPackStatus(DowmloadRecordStatus.UP_END);
        });
        List<CourseTeachingPackage> courseTeachingPackages =
                teachingPackageRepository.findByCourseId(course.getId());
        teachingPackageRepository.deleteInBatch(courseTeachingPackages);
        downloadRecordRepository.saveAll(byCourseIdAndDowmloadRecordStatus);
        groupRepository.updateGroupStatusByCourseId(id);
        if (dataVisualEnable) {
            eventService.sendDeleteCourseEvent(course.getId());
        }
        return Result.success();
    }

    @Transactional
    public Result updateCourseName(String id, String newCourseName) {
        Course course = courseRepository.findByIdAndUseState(id, true);
        if (Objects.isNull(course)) {
            return Result.error("此课程不存在");
        }
        if (CourseSource.DATA_DOCKING.equals(course.getCourseSource())) {
            return Result.error("对接的课程不允许修改课程名称");
        }
        boolean existSameNameCourse = checkExistSameNameCourse(newCourseName, id);
        if (existSameNameCourse) {
            return Result.error("课程名称已存在");
        }
        course.setCourseName(newCourseName);
        courseRepository.save(course);

        courseTableRepository.updateCourseNameByCourseId(newCourseName, id);
        return Result.success();
    }

    public boolean checkExistSameNameCourse(String newCourseName, String id) {
        return courseRepository.countByCourseNameAndIdIsNotAndUseStateIsTrue(newCourseName, id) > 0;
    }

    @Async
    public void createCourseEvent(Course course) {
        CourseEvent courseEvent = new CourseEvent();
        courseEvent.setId(course.getId());
        courseEvent.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        courseEvent.setCourseCode(course.getCourseCode());
        courseEvent.setCourseName(course.getCourseName());
        courseEvent.setCourseSource(ObjectUtils.isNotEmpty(course.getCourseSource()) ? course.getCourseSource().getIndex() : 0);
        if (ObjectUtils.isNotEmpty(course.getCollege())) {
            courseEvent.setCourseCollegeId(course.getCollege().getId());
            courseEvent.setCourseCollegeCode(course.getCollege().getCollegeCode());
            courseEvent.setCourseCollegeName(course.getCollege().getCollegeName());
        }
        courseEvent.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", course.getCreateTime()));
        eventService.sendCourseEvent(courseEvent);
    }


    @Async
    public void sendCourseResourceEvent(CourseResource courseResource) {
        CourseResourceEvent courseResourceEvent = new CourseResourceEvent();
        courseResourceEvent.setId(courseResource.getId());
        courseResourceEvent.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        courseResourceEvent.setIsPublic(courseResource.getIsPublic());
        courseResourceEvent.setResourceType(courseResource.getResourceType().getIndex());
        courseResourceEvent.setResourceDetailId(courseResource.getResourceDetailId());
        courseResourceEvent.setResourceName(courseResource.getResourceName());
        courseResourceEvent.setResourceSize(courseResource.getResourceSize());
        courseResourceEvent.setResourceContentNum(courseResource.getResourceContentNum());
        CourseStructure courseStructure = courseResource.getCourseStructure();
        if (ObjectUtils.isNotEmpty(courseStructure)) {
            courseResourceEvent.setCourseStructureId(courseStructure.getId());
            courseResourceEvent.setCourseStructureName(courseStructure.getCourseStructureName());
            courseResourceEvent.setCourseId(courseStructure.getCourse().getId());
            courseResourceEvent.setCourseCode(courseStructure.getCourse().getCourseCode());
            courseResourceEvent.setCourseName(courseStructure.getCourse().getCourseName());
        }
        courseResourceEvent.setCourseVersionId(courseResource.getCourseStructure().getCourseVersion().getId());
        courseResourceEvent.setModifyTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", courseResource.getModifyTime()));
        courseResourceEvent.setModifierId(courseResource.getModifierId());
        courseResourceEvent.setModifierName(courseResource.getModifierName());
        eventService.sendCourseResourceEvent(courseResourceEvent);
    }

    public List<CourseData> getAllCourseList() {
        StringBuffer stringBuffer = new StringBuffer(" SELECT a.course_name as courseName,a.id as courseId,a.course_code as " +
                "  courseCode,a.college_name as collegeName, " +
                "  c.course_type_id as courseTypeId from tb_course a " +
                "  LEFT JOIN tb_course_table b on a.id=b.course_id " +
                "  LEFT JOIN  tb_course_table_detail c on b.id=c.course_table_id " +
                "  where a.course_source=0 ");
        if (!studentType.equals("-1")) {
            stringBuffer.append("   and b.student_type=" + studentType);
        }
        stringBuffer.append(" GROUP BY a.id  " +
                " ORDER BY c.create_time DESC,b.create_time desc ,a.create_time desc ");
        Query queryData = entityManager.createNativeQuery(stringBuffer.toString());
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return JSONArray.parseArray(JSONObject.toJSON(queryData.getResultList()).toString(), CourseData.class);
    }

    public Course getCourseByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }

    public void addCourse(CourseResourceInfoVo courseResourceInfoVo, College college) {
        Course course = new Course();
        course.setCreateTime(new Date());
        course.setCreatorId(courseResourceInfoVo.getOperatorId());
        course.setCreatorName(courseResourceInfoVo.getOperatorName());
        course.setCourseCode(courseResourceInfoVo.getCourseCode());
        course.setCourseName(courseResourceInfoVo.getCourseName());
        course.setCollege(college);
        course.setCollegeName(college.getCollegeName());
        course.setCollegeCode(college.getCollegeCode());
        course.setCourseSource(CourseSource.COURSE_MANAGEMENT);
        course.setUseState(true);
        courseRepository.save(course);
        if (dataVisualEnable) {
            this.createCourseEvent(course);
        }
    }

    public List<CollegeCourseResource> getCourseInfosByColegeIds(List<String> collegeIds) {
        List<CollegeCourseResource> collegeCourseResources = new ArrayList<>();
        if (CollectionUtils.isEmpty(collegeIds)) {
            return collegeCourseResources;
        }
        List<Course> courseList = courseRepository.findAllByCollegeIdInAndUseState(collegeIds, Boolean.TRUE);
        if (CollectionUtils.isEmpty(courseList)) {
            return collegeCourseResources;
        }
        courseList.forEach(course -> {
            CollegeCourseResource collegeCourseResource = new CollegeCourseResource();
            collegeCourseResource.setCourseCode(course.getCourseCode());
            collegeCourseResource.setCourseId(course.getId());
            collegeCourseResource.setCourseName(course.getCourseName());
            collegeCourseResource.setCourseCode(course.getCollege().getId());
            collegeCourseResource.setId(course.getCollege().getId());
            collegeCourseResource.setCollegeName(course.getCollegeName());
            collegeCourseResources.add(collegeCourseResource);
        });
        return collegeCourseResources;
    }

    public void addOrUpdateCourseCover(Course course, CourseCoverVo courseCoverVo) {
        CourseCover courseCover;
        if (ObjectUtils.isNotEmpty(course.getCourseCover())) {
            courseCover = course.getCourseCover();
        } else {
            courseCover = new CourseCover();
            courseCover.setCreatorId(courseCoverVo.getModifierId());
            courseCover.setCreatorName(courseCoverVo.getModifierName());
            courseCover.setCreateTime(new Date());
        }
        courseCover.setCourse(course);
        courseCover.setFilePath(courseCoverVo.getFilepath());
        courseCover.setFileRealName(courseCoverVo.getFileRealName());
        courseCover.setFileSize(Long.valueOf(courseCoverVo.getFileSize()));
        courseCover.setFileType(courseCoverVo.getFileType());
        courseCover.setInnerIp(courseCoverVo.getInnerServerIp());
        courseCover.setOuterIp(courseCoverVo.getOuterServerIp());
        courseCover.setFileSavedName(courseCoverVo.getFileSavedName());
        courseCover.setModifierId(courseCoverVo.getModifierId());
        courseCover.setModifierName(courseCoverVo.getModifierName());
        courseCover.setModifyTime(new Date());
        courseCoverRepository.saveAndFlush(courseCover);
    }

    public List<CourseImageInfoVo> getCourseImageInfos(String courseIds) {
        List<String> courseIdList = Arrays.stream(courseIds.split(","))
                .filter(StringUtils::isNotBlank).collect(Collectors.toList());
        List<Course> courseList = courseRepository.findByIdIn(courseIdList);
        List<CourseImageInfoVo> courseImageInfoVoList = new ArrayList<>();
        for (Course course : courseList) {
            CourseImageInfoVo courseImageInfoVo =  new CourseImageInfoVo();
            courseImageInfoVo.setCourseId(course.getId());
            courseImageInfoVo.setCourseCode(course.getCourseCode());
            courseImageInfoVo.setCourseName(course.getCourseName());
            CourseCover courseCover = course.getCourseCover();
            if (ObjectUtils.isNotEmpty(courseCover)){
                courseImageInfoVo.setInnerIp(courseCover.getInnerIp());
                courseImageInfoVo.setOuterIp(courseCover.getOuterIp());
                courseImageInfoVo.setFilePath(courseCover.getFilePath());
            }
            courseImageInfoVoList.add(courseImageInfoVo);
        }
        courseImageInfoVoList.sort(Comparator.comparing(CourseImageInfoVo::getCourseId));
        return courseImageInfoVoList;
    }
}

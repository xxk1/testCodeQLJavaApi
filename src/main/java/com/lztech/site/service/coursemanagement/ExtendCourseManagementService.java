package com.lztech.site.service.coursemanagement;

import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.course.specification.CourseResourceSpecification;
import com.lztech.persistence.repositories.coursecategory.CourseCategoryRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetheme.CourseThemeRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.resource.course.CourseInfoResource;
import com.lztech.site.service.college.CollegeService;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.service.courseconstruction.CourseTeachingTeamService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.service.questionlibrary.QuestionLibraryService;
import com.lztech.site.until.ConflictRuntimeException;
import com.lztech.site.until.CustomRuntimeException;
import com.lztech.site.until.JsonUtils;
import com.lztech.site.viewmodel.coursemanagement.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.questionlibrary.QuestionLibraryPageResource;
import com.lztech.site.viewmodel.questionlibrary.QuestionLibraryResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lztech.site.constants.Constant.COURSE_MAX_NUM;
import static com.lztech.site.constants.Constant.ONE;
import static java.util.stream.Collectors.*;

/**
 * @author 智教云项目组
 * @version 1.0
 * @date 2022/3/24 20:27
 * @content:
 */
@Service
public class ExtendCourseManagementService {
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseThemeRepository courseThemeRepository;
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private CourseResourceKnowledgePointRepository courseResourceKnowledgePointRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private QuestionLibraryService questionLibraryService;

    private static double trillion = 1048576.0;
    private static double zeroPointOne = 0.1;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Autowired
    private CourseTeachingTeamService courseTeachingTeamService;
    @Autowired
    private CourseCompletionRepository courseCompletionRepository;

    public ResponseEntity<Void> adminDeleteCourseResources(String courseResourceIds, String userId, String courseId) {
        List<CourseResource> courseResources = getCourseResources(courseResourceIds);
        if (CollectionUtils.isEmpty(courseResources)) {
            return new ResponseEntity(ErrorResult.customError("课程资源未找到"), HttpStatus.NOT_FOUND);
        }
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return new ResponseEntity(ErrorResult.customError("未找到课程信息"), HttpStatus.NOT_FOUND);
        }
        List<CourseResource> courseResourceList = new ArrayList<>();
        List<String> themeIdList = new ArrayList<>();
        courseResources.forEach(courseResource -> {
            courseResource.setResourceStatus(ResourceStatus.DELETE);
            if (ResourceType.THEME.equals(courseResource.getResourceType())) {
                themeIdList.add(courseResource.getResourceDetailId());
            }
            courseResourceList.add(courseResource);
        });
        courseResourceRepository.saveAll(courseResourceList);
        if (CollectionUtils.isNotEmpty(themeIdList)) {
            courseThemeRepository.deleteCourseThemeByIds(themeIdList);
        }
        if (dataVisualEnable) {
            sendDeleteCourseResourceList(courseResourceList);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<CourseResource> getCourseResources(String courseResourceIds) {
        List<String> ids = Arrays.asList(courseResourceIds.split(","));
        return courseResourceRepository.findByIdInAndResourceStatus(ids, ResourceStatus.NORMAL);
    }

    @Async
    public void sendDeleteCourseResourceList(List<CourseResource> courseResourceList) {
        courseResourceList.forEach(courseResource -> eventService.sendDeleteCourseResourceEvent(courseResource.getId()));
    }

    /****/
    public ResponseEntity<Boolean> getCourseStructureIdIsDelete(String courseStructureId) {
        //ture 已删除 false 未删除
        CourseStructure courseStructure = courseStructureRepository.findByIdAndStructureStatus(courseStructureId, StructureStatus.NORMAL);
        return new ResponseEntity<>(ObjectUtils.isEmpty(courseStructure), HttpStatus.OK);
    }

    //region 接口-查询课程列表(题库添加题目使用)
    public ResponseEntity<List<CourseInfoResource>> getJoinCourseInfoResources(String teacherId) {
        List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository
                .findByTeacherIdOrderByCourseId(teacherId);
        List<String> courseIds = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(courseTeachingTeams)) {
            courseIds.addAll(courseTeachingTeams.stream().map(courseTeachingTeam ->
                    courseTeachingTeam.getCourse().getId()).collect(Collectors.toList()));
            courseIds = courseIds.stream().distinct().collect(Collectors.toList());
        }
        List<Course> courses = courseRepository.findByIdIsInAndUseStateIsTrueOrderById(courseIds);
        List<CourseInfoResource> courseInfoResources = new ArrayList<>();
        courseInfoResources.addAll(courses.stream().map(course -> new CourseInfoResource() {{
            this.courseId(course.getId());
            this.courseCode(course.getCourseCode());
            this.courseName(course.getCourseName());
            this.collegeCode(course.getCollegeCode());
            this.collegeName(course.getCollegeName());
        }}).collect(Collectors.toList()));
        return new ResponseEntity<>(courseInfoResources, HttpStatus.OK);
    }

    //region 接口-判断当前课程老师是否加入教师团队
    public ResponseEntity<IsJoinTeachingTeam> getIfCourseIdCourseTeachingTeam(String courseId, String teacherId, String courseVisionId) {
        IsJoinTeachingTeam isJoinTeachingTeam = new IsJoinTeachingTeam();
        CourseTeachingTeam teachingTeam =
                courseTeachingTeamRepository.findByCourseIdAndTeacherIdAndCourseVersionId(courseId, teacherId, courseVisionId);
        isJoinTeachingTeam.setStatus(ObjectUtils.isNotEmpty(teachingTeam));
        return new ResponseEntity<>(isJoinTeachingTeam, HttpStatus.OK);
    }

    //region 接口-获取当前课程一级结构列表
    public ResponseEntity<List<PrimaryStructure>> getPrimaryStructure(String courseId, String versionId,Boolean showDeleted) {
        if (StringUtils.isBlank(versionId)) {
            CourseVersion courseVersion = courseVersionRepository.findByCourseIdAndVersionStatus(courseId, CourseVersionStatus.IN_USE.getValue())
                    .stream()
                    .findFirst()
                    .orElse(null);
            if (Objects.nonNull(courseVersion)) {
                versionId = courseVersion.getId();
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
        }
        List<CourseStructure> courseStructures = courseStructureRepository
                    .findTopByCourseIdAndCourseVersion(courseId, versionId);
        Stream<PrimaryStructure> s = courseStructures.stream().map(
                courseStructure -> new PrimaryStructure() {{
                    this.setStructureId(courseStructure.getId());
                    this.setStructureName(courseStructure.getCourseStructureName());
                    this.setStructureStatus(courseStructure.getStructureStatus().getIndex());
                    this.setShowOrder(courseStructure.getShowOrder());
                }});
        if (showDeleted != null && !showDeleted){
            s = s.filter(courseStructure -> courseStructure.getStructureStatus() == StructureStatus.NORMAL.getIndex());
        }
        List<PrimaryStructure> primaryStructures =
                s.sorted(Comparator.comparing(PrimaryStructure::getStructureStatus)
                        .thenComparing(PrimaryStructure::getShowOrder))
                .collect(Collectors.toList());
        return new ResponseEntity<>(primaryStructures, HttpStatus.OK);
    }


    //region 接口-课程结构排序
    @Transactional
    public ResponseEntity<Void> updateCourseStructureOrder(String userId, String userName,
                                                           List<CourseStructureData> courseStructureDataList) {
        List<String> courseStructureIds = new ArrayList<>();
        List<String> ids = getCourseStructureIds(courseStructureIds, courseStructureDataList);
        List<CourseStructure> courseStructureList = courseStructureRepository
                .findByIdInAndStructureStatus(ids, StructureStatus.NORMAL);
        if (courseStructureList.size() < ids.size()) {
            return new ResponseEntity(ErrorResult.customError("排序失败，课程结构已被删除"), HttpStatus.CONFLICT);
        }
        for (CourseStructureData courseStructureData : courseStructureDataList) {
            List<CourseStructureData> structureDataList = courseStructureData.getChildStructureList();
            for (CourseStructureData c : structureDataList) {
                c.setParentId(courseStructureData.getStructureId());
            }
        }
        List<CourseStructure> courseStructures = new ArrayList<>();
        List<CourseStructure> structureList = getCourseStructureSort(courseStructures, courseStructureDataList,
                courseStructureList, userId, userName);
        courseStructureRepository.saveAll(structureList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion

    private List<String> getCourseStructureIds(List<String> ids, List<CourseStructureData> courseStructureDataList) {
        courseStructureDataList.forEach(courseStructureData -> {
            ids.add(courseStructureData.getStructureId());
            getCourseStructureIds(ids, courseStructureData.getChildStructureList());
        });
        return ids;
    }

    private List<CourseStructure> getCourseStructureSort(List<CourseStructure> courseStructures, List<CourseStructureData> courseStructureDataList,
                                                         List<CourseStructure> courseStructureList, String userId, String userName) {
        for (int i = 0; i < courseStructureDataList.size(); i++) {
            CourseStructureData courseStructureData = courseStructureDataList.get(i);
            CourseStructure courseStructure = courseStructureList.stream()
                    .filter(c -> courseStructureData.getStructureId().equals(c.getId()))
                    .findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(courseStructure)) {
                courseStructure.setParentId("".equals(courseStructureData.getParentId()) ? null :
                        courseStructureData.getParentId());
                courseStructure.setShowOrder(i + 1);
                courseStructure.setModifierId(userId);
                courseStructure.setModifierName(userName);
                courseStructure.setModifyTime(new Date());
                courseStructures.add(courseStructure);
            }
            getCourseStructureSort(courseStructures, courseStructureData.getChildStructureList(),
                    courseStructureList, userId, userName);
        }
        return courseStructures;
    }


    //region 接口-删除课程结构
    @Transactional
    public ResponseEntity<Void> deleteCourseStructure(String id, String userId, String userName) {
        Date now = new Date();
        CourseStructure courseStructure = courseStructureRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(courseStructure)) {
            return new ResponseEntity(ErrorResult.customError("未找到课程结构信息"), HttpStatus.NOT_FOUND);
        }
        Course course = courseStructure.getCourse();
        if (ObjectUtils.isEmpty(course)) {
            return new ResponseEntity(ErrorResult.customError("未找到课程信息"), HttpStatus.NOT_FOUND);
        }
        if (Objects.isNull(courseStructure.getCourseVersion()) || !CourseVersionStatus.IN_USE
                .equals(courseStructure.getCourseVersion().getCourseVersionStatus())) {
            return new ResponseEntity(ErrorResult.customError("无法保存，当前版本已归档，请刷新页面"), HttpStatus.CONFLICT);

        }
        List<CourseStructureInUsedVo> courseStructureInUsedVos = new ArrayList<>();
        //1. 我的题库
        List<QuestionLibraryResource> useQuestionLibraries = getCourseLibraries(course.getId(),courseStructure.getId());
        Map<String,List<QuestionLibraryResource>> useQuestionLibrariesMap =
                useQuestionLibraries.stream().collect(Collectors.groupingBy(QuestionLibraryResource::getCreatorId));
        useQuestionLibrariesMap.forEach((s,questionLibraries) ->{
            CourseStructureInUsedVo courseStructureInUsedVo =
                    courseStructureInUsedVos.stream()
                            .filter(i-> Objects.equals(i.getUserId(), s)).findFirst().orElse(null);
            if(courseStructureInUsedVo == null){
                QuestionLibraryResource questionLibrary = questionLibraries.get(0);
                courseStructureInUsedVo = new CourseStructureInUsedVo();

                courseStructureInUsedVo.setUserId(questionLibrary.getCreatorId());
                courseStructureInUsedVo.setUserName(questionLibrary.getCreatorName());
                List<String> inUsed = new ArrayList<>();
                inUsed.add("课程题库");
                courseStructureInUsedVo.setInUsedFunctions(inUsed);
                courseStructureInUsedVos.add(courseStructureInUsedVo);
            }else {
                courseStructureInUsedVo.getInUsedFunctions().add("课程题库");
            }
        });
        // 备课包
        List<CourseStructure> courseStructures =
                courseStructureRepository.findByStructureStatusAndParentId(StructureStatus.NORMAL,courseStructure.getId());
        courseStructures.add(courseStructure);
        Map<String,List<CourseResource>> userCourseResources =
                courseStructures.stream().map(CourseStructure::getCourseResources).flatMap(List::stream)
                        .filter(c-> c.getResourceStatus() == ResourceStatus.NORMAL).collect(groupingBy(
                        CourseResource::getCreatorId));


        userCourseResources.forEach((s, courseResources) -> {
            CourseStructureInUsedVo courseStructureInUsedVo;
            List<String> usedFunction;
            if(courseStructureInUsedVos.stream().anyMatch(c->c.getUserId().equals(s))){
               courseStructureInUsedVo =
                       courseStructureInUsedVos.stream().filter(c->c.getUserId().equals(s))
                               .findFirst().get();
                usedFunction = courseStructureInUsedVo.getInUsedFunctions();
            }else {
                usedFunction = new ArrayList<>();
                courseStructureInUsedVo = new CourseStructureInUsedVo();
                courseStructureInUsedVos.add(courseStructureInUsedVo);
            }
            CourseResource tempCourseResource = courseResources.get(0);

            long publicResource = courseResources.stream().filter(CourseResource::getIsPublic).count();
            long privateResource = courseResources.stream().filter(c -> !c.getIsPublic()).count();
            if(privateResource >0){
                usedFunction.add("教师备课");
            }
            if (publicResource > 0) {
                usedFunction.add("教师备课");
                usedFunction.add("课程建设");
            }
            courseStructureInUsedVo.setInUsedFunctions(usedFunction.stream().distinct().collect(toList()));
            courseStructureInUsedVo.setUserId(tempCourseResource.getCreatorId());
            courseStructureInUsedVo.setUserName(tempCourseResource.getCreatorName());

        });
        if(!courseStructureInUsedVos.isEmpty()){
            try{
                courseStructureInUsedVos.forEach(c-> c.setOrder(courseStructureInUsedVos.indexOf(c) + 1));
                String json = JsonUtils.getJson(courseStructureInUsedVos);
                return new ResponseEntity(ErrorResult.customError(json), HttpStatus.NOT_ACCEPTABLE);
            }catch (Exception e){
                return new ResponseEntity(ErrorResult.customError("服务器异常"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        courseStructures.forEach(c->{
            c.setStructureStatus(StructureStatus.DELETE);
            c.setModifyTime(now);
            c.setModifierId(userId);
            c.setModifierName(userName);
        });
        courseStructureRepository.saveAll(courseStructures);
        List<CourseStructure> allCourseStructures =
                courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus(courseStructure.getCourse().getId(),
                        courseStructure.getCourseVersion().getId(), StructureStatus.NORMAL);
        if (CollectionUtils.isEmpty(allCourseStructures)) {
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_STRUCTURE, false, userId, userName);
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_OBJECTIVES, false, userId, userName);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion


    //region 接口-删除课程资源
    public void deleteCourseResources(String courseResourceIds, String userId, String courseId, String versionId) {
        List<CourseResource> courseResources = getCourseResources(courseResourceIds);
        if (CollectionUtils.isEmpty(courseResources)) {
            throw new CustomRuntimeException(ErrorResult.customError("课程资源未找到"));
        }
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            throw new CustomRuntimeException(ErrorResult.customError("未找到课程信息"));
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(versionId, CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            throw new CustomRuntimeException(ErrorResult.customError("无法删除，当前版本已归档，请刷新页面"));
        }
        if (!Objects.equals(userId, course.getCourseLeaderId())) {
            List<CourseResource> resources = getCourseResourceList(courseResources, userId);
            if (resources.size() > 0) {
                String resourceNames = resources.stream()
                        .map(CourseResource::getResourceName)
                        .collect(Collectors.joining(","));
                throw new ConflictRuntimeException(ErrorResult.customError("名称为" + resourceNames + "的课程资源不属于您的资源，无法删除"));
            }
        }
        Date date = new Date();
        courseResources.forEach(courseResource -> {
            if (userId.equals(courseResource.getCreatorId())) {
                courseResource.setResourceStatus(ResourceStatus.DELETE);
            }
            courseResource.setIsPublic(false);
            courseResource.setModifyTime(date);
        });
        courseResourceRepository.saveAll(courseResources);
        courseResourceKnowledgePointRepository.deleteByResourceIdList(Arrays.asList(courseResourceIds.split(",")));
        deleteCourseThemes(courseResources);
        int courseResourceNum = courseResourceRepository.countResourceNumByVersionId(versionId);
        if (courseResourceNum == 0) {
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.TEACHING_RESOURCES, false, userId, null);
        }
        int coursePreparatoryActivityNum = courseResourceRepository.countPreparatoryActivityNumByVersionId(versionId);
        if (coursePreparatoryActivityNum == 0) {
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.PREPARATORY_ACTIVITIES, false, userId, null);
        }
        if (dataVisualEnable) {
            sendDeleteCourseResourceList(courseResources);
        }

    }

    private void deleteCourseThemes(List<CourseResource> courseResources) {
        List<String> themeIdList = new ArrayList<>();
        courseResources.forEach(courseResource -> {
            if (ResourceType.THEME.equals(courseResource.getResourceType())
                    && courseResource.getResourceStatus() == ResourceStatus.DELETE) {
                themeIdList.add(courseResource.getResourceDetailId());
            }
        });
        if (CollectionUtils.isNotEmpty(themeIdList)) {
            courseThemeRepository.deleteCourseThemeByIds(themeIdList);
        }
    }

    private List<CourseResource> getCourseResourceList(List<CourseResource> courseResources, String userId) {
        return courseResources.stream()
                .filter(courseResource -> !userId.equals(courseResource.getCreatorId()))
                .collect(Collectors.toList());
    }

    //region 接口-老师新增课程
    @Transactional
    public synchronized ResponseEntity<Void> createCourse(CourseModule courseModule) {
        List<Course> courseList = courseRepository.findByCourseNameLikeAndUseState(courseModule.getCourseName(), true);
        if (CollectionUtils.isNotEmpty(courseList)) {
            return new ResponseEntity(ErrorResult.customError("课程名称已存在"), HttpStatus.CONFLICT);
        }
        Date date = new Date();
        String courseCode = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<Course> courses = courseRepository.findByCourseSourceAndCourseCodeLikeOrderByCreateTimeDesc(CourseSource.COURSE_MANAGEMENT,
                courseCode + "%");
        if (CollectionUtils.isEmpty(courses)) {
            courseCode = courseCode + "001";
        } else if (courses.size() >= COURSE_MAX_NUM) {
            return new ResponseEntity(ErrorResult.customError("当天新增课程已达到上限"), HttpStatus.CONFLICT);
        } else {
            courseCode = Long.valueOf(courses.get(0).getCourseCode().trim()) + ONE + "";
        }
        // 获取此人有无在其他教学团队的职称
        CourseTeachingTeam otherCourseTeachingTeam = courseTeachingTeamRepository.getCourseTeachingTeamByTeacherId(courseModule.getTeacherId());

        /**课程**/
        Course course = new Course();
        getCourse(course, courseModule, courseCode);
        /**课程资料**/
        CourseMaterial courseMaterial = new CourseMaterial();
        createBaseCourseMaterial(courseMaterial, courseModule, course);
        /**加入教师团队**/
        CourseTeachingTeam courseTeachingTeam = new CourseTeachingTeam();
        courseTeachingTeam.setTeacherId(courseModule.getTeacherId());
        courseTeachingTeam.setTeacherNo(courseModule.getTeacherNo());
        courseTeachingTeam.setTeacherName(courseModule.getTeacherName());
        courseTeachingTeam.setTeacherType(TeacherType.COURSE_LEADER);
        if (Objects.nonNull(otherCourseTeachingTeam)) {
            courseTeachingTeam.setJobTitle(otherCourseTeachingTeam.getJobTitle());
        }
        courseTeachingTeam.setCreateTime(date);
        courseTeachingTeam.setCreatorId(courseModule.getTeacherId());
        courseTeachingTeam.setCreatorName(courseModule.getTeacherName());
        courseTeachingTeam.setModifyTime(date);
        courseTeachingTeam.setModifierName(courseModule.getTeacherName());
        courseTeachingTeam.setModifierId(courseModule.getTeacherId());
        courseTeachingTeam.setCourse(course);
        courseTeachingTeam.setTeacherDataSource(TeacherDataSource.CAMPUS_USER);

        // 保存版本信息
        CourseVersion courseVersion = saveCourseVersion(course, courseModule, date);
        courseTeachingTeam.setCourseVersion(courseVersion);
        courseMaterial.setCourseVersion(courseVersion);
        /**进行保存**/
        courseMaterialRepository.save(courseMaterial);
        courseRepository.save(course);

        courseTeachingTeamRepository.save(courseTeachingTeam);
        if (dataVisualEnable) {
            /*新建课程推送*/
            courseService.createCourseEvent(course);
            /*教学团队推送*/
            courseTeachingTeamService.createCourseTeachingTeamEvent(courseTeachingTeam);
        }
        saveCourseCompletion(course, courseModule.getTeacherId(),
                courseModule.getTeacherName(), new Date(), courseVersion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void getCourse(Course course, CourseModule courseModule, String courseCode) {
        Date date = new Date();
        College college = collegeService.getCollege(courseModule.getCollegeName());
        course.setCollege(college);
        course.setCollegeCode(college.getCollegeCode());
        course.setCollegeName(college.getCollegeName());
        course.setCourseCode(courseCode);
        course.setCourseSource(CourseSource.COURSE_MANAGEMENT);
        course.setCourseName(courseModule.getCourseName());
        course.setCourseLeaderId(courseModule.getTeacherId());
        course.setCourseLeaderName(courseModule.getTeacherName());
        course.setCreateTime(date);
        course.setCreatorId(courseModule.getTeacherId());
        course.setCreatorName(courseModule.getTeacherName());
        course.setUseState(true);
    }

    private void createBaseCourseMaterial(CourseMaterial courseMaterial, CourseModule courseModule, Course course) {
        Date date = new Date();
        courseMaterial.setCreateTime(date);
        courseMaterial.setCreatorId(courseModule.getTeacherId());
        courseMaterial.setCreatorName(courseModule.getTeacherName());
        courseMaterial.setModifyTime(date);
        courseMaterial.setModifierId(courseModule.getTeacherId());
        courseMaterial.setModifierName(courseModule.getTeacherName());
        CourseCategory courseCategory = courseCategoryRepository.findById(Integer.valueOf(courseModule.getCourseCategoryId()));
        courseMaterial.setCourseCategory(courseCategory);
        courseMaterial.setTeachingUserType(courseModule.getTeachingUserType());
        courseMaterial.setClassHours(Objects.isNull(courseModule.getClassHours()) ? null : courseModule.getClassHours().doubleValue());
        courseMaterial.setCourseContent(courseModule.getCourseContent());
        courseMaterial.setCourse(course);
    }

    private CourseVersion saveCourseVersion(Course course, CourseModule courseModule, Date date) {
        CourseVersion courseVersion = new CourseVersion();
        courseVersion.setVersionSerialNumber(ONE);
        courseVersion.setCourseVersionStatus(CourseVersionStatus.IN_USE);
        courseVersion.setCourse(course);
        courseVersion.setModifierId(courseModule.getTeacherId());
        courseVersion.setModifierName(courseModule.getTeacherName());
        courseVersion.setModifyTime(date);
        courseVersion.setCreatorId(courseModule.getTeacherId());
        courseVersion.setCreatorName(courseModule.getTeacherName());
        courseVersion.setCreateTime(date);
        return courseVersionRepository.save(courseVersion);

    }

    private void saveCourseCompletion(Course course, String teacherId, String teacherName,
                                      Date date, CourseVersion courseVersion) {
        List<CourseCompletion> courseCompletionList = new ArrayList<>();
        CourseCompletion courseBaseInfoCompletion = new CourseCompletion();
        courseBaseInfoCompletion.setCourseContentEnum(CourseContentTypeEnum.COURSE_BASE_INFO);
        createCourseCompletion(course, teacherId, teacherName, date, courseVersion, courseBaseInfoCompletion);
        courseCompletionList.add(courseBaseInfoCompletion);

        CourseCompletion courseTeamLeaderCompletion = new CourseCompletion();
        courseTeamLeaderCompletion.setCourseContentEnum(CourseContentTypeEnum.COURSE_TEAM_LEADER);
        createCourseCompletion(course, teacherId, teacherName, date, courseVersion, courseTeamLeaderCompletion);
        courseCompletionList.add(courseTeamLeaderCompletion);
        courseCompletionRepository.saveAll(courseCompletionList);
        if (dataVisualEnable) {
            /*课程版本推送*/
            courseCompletionService.createCourseVersionEvent(courseVersion, courseCompletionList);
        }
    }

    private void createCourseCompletion(Course course, String teacherId, String teacherName, Date date,
                                        CourseVersion courseVersion, CourseCompletion courseCompletion) {
        courseCompletion.setCompleted(true);
        courseCompletion.setCourse(course);
        courseCompletion.setCourseVersion(courseVersion);
        courseCompletion.setCreatorId(teacherId);
        courseCompletion.setCreatorName(teacherName);
        courseCompletion.setCreateTime(date);
        courseCompletion.setModifierId(teacherId);
        courseCompletion.setModifierName(teacherName);
        courseCompletion.setModifyTime(date);

    }

    public List<String> getAllResourceNames(String userId, CourseStructure courseStructure, Boolean isPublic) {
        List<CourseStructure> courseStructures = courseStructureRepository.findByCourseIdAndStructureStatusAndCourseVersion(
                courseStructure.getCourse().getId(), StructureStatus.NORMAL, courseStructure.getCourseVersion());
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.specificationAllResources(userId, courseStructure.getCourse().getId(),
                        "", courseStructure.getCourseVersion(), courseStructures));
        if (Objects.nonNull(isPublic)) {
            courseResources = courseResources.stream().filter(courseResource -> courseResource.getIsPublic().equals(isPublic)).collect(toList());
        }
        List<String> resourceFileIds = courseResources.stream().map(CourseResource::getResourceDetailId).collect(toList());
        List<CourseResourceFile> courseResourceFileList = courseResourceFileRepository.findByIdIn(resourceFileIds);
        List<String> resourceNames = new ArrayList<>();
        courseResources.forEach(courseResource -> {
            courseResourceFileList.stream().filter(courseResourceFile ->
                            courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                    .forEach(courseResourceFile -> {
                        if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) {
                            resourceNames.add(courseResource.getResourceName());
                        } else {
                            resourceNames.add(courseResource.getResourceName() + "." + courseResourceFile.getFileType());
                        }
                    });

        });
        return resourceNames;
    }

    private List<QuestionLibraryResource> getCourseLibraries(String courseId,String structureId){
        int page = 1;
        int pageSize = 1000;
        List<QuestionLibraryResource> questionLibraryResources = new ArrayList<>();
        QuestionLibraryPageResource questionLibraryPageResource =
                questionLibraryService.getQuestionLibraryPage(courseId,structureId,page,pageSize);
        questionLibraryResources.addAll(questionLibraryPageResource.getQuestionList());
        while (questionLibraryPageResource.getPage()<page){
            page += 1;
            questionLibraryPageResource = questionLibraryService.getQuestionLibraryPage(courseId,structureId,page,pageSize);
            questionLibraryResources.addAll(questionLibraryPageResource.getQuestionList());
        }
        return questionLibraryResources;
    }

}

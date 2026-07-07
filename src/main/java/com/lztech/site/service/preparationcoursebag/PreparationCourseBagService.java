package com.lztech.site.service.preparationcoursebag;
import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.course.specification.CourseResourceSpecification;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.resource.courseteacher.CourseTeacherVo;
import com.lztech.site.resource.term.TermResource;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.service.courseconstruction.CourseTeachingTeamService;
import com.lztech.site.service.courseconstruction.CourseVersionService;
import com.lztech.site.service.coursemanagement.CourseManagementService;
import com.lztech.site.service.coursemanagement.ExtendCourseManagementService;
import com.lztech.site.service.coursetable.CourseTableService;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.CustomRuntimeException;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.until.Util;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceVo;
import com.lztech.site.viewmodel.coursemanagement.ReorderParams;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.preparationcoursebag.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import static com.lztech.persistence.repositories.coursetable.specification.CourseTableSpecification.getCourseTableSpecification;
import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.constants.Constant.INTERACTIVE_CLASSROOM_NOT_ALLOWED_PREPARATION_COURSE_BAG_FILE_TYPE;
import static java.util.stream.Collectors.toList;
@Service
@Log4j2
public class PreparationCourseBagService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseHiddenRecordRepository courseHiddenRecordRepository;
    @Autowired
    private TermService termService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseVersionService courseVersionService;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private CourseTableService courseTableService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private CourseTeachingTeamService courseTeachingTeamService;
    @Autowired
    private ExtendCourseManagementService extendCourseManagementService;
    @Autowired
    private CourseManagementService courseManagementService;
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Value("${request.address.teachingApi}")
    private String teachingApi;
    private static double trillion = 1048576.0;
    private static double zeroPointOne = 0.1;
    @Transactional
    public ResponseEntity<Void> preparationCourseHide(String userId, String userName, String courseId) {
        Date now = new Date();
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return new ResponseEntity(ErrorResult.customError("未找到课程信息"), HttpStatus.NOT_FOUND);
        }
        Term nowTerm = termService.getNowTerm();
        CourseHiddenRecord courseHiddenRecord = new CourseHiddenRecord();
        courseHiddenRecord.setCreateTime(now);
        courseHiddenRecord.setCreatorId(userId);
        courseHiddenRecord.setCreatorName(userName);
        courseHiddenRecord.setCourseId(course.getId());
        courseHiddenRecord.setCourseName(course.getCourseName());
        courseHiddenRecord.setSchoolYear(nowTerm.getSchoolYear());
        courseHiddenRecord.setTerm(nowTerm.getTerm().getIndex());
        courseHiddenRecord.setTeacherId(userId);
        courseHiddenRecord.setTeacherName(userName);
        courseHiddenRecordRepository.save(courseHiddenRecord);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<Void> preparationCourseShow(String userId, String courseId) {
        List<CourseHiddenRecord> courseHiddenRecords = courseHiddenRecordRepository.findByCourseIdAndTeacherId(courseId, userId);
        if (courseHiddenRecords.size() == 0) {
            return new ResponseEntity(ErrorResult.customError("未找到课程隐藏信息"), HttpStatus.NOT_FOUND);
        }
        courseHiddenRecordRepository.deleteAll(courseHiddenRecords);
        return new ResponseEntity(HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<TestPaperParmVo> createClassTestPaper(TestPaperParmVo testPaperParmVo) {
        CourseStructure courseStructure = courseStructureRepository.findByIdAndStructureStatus(
                testPaperParmVo.getCourseStructureId(), StructureStatus.NORMAL);
        if (courseStructure == null) {
            return new ResponseEntity(ErrorResult.customError("保存失败，课程结构已被删除"), HttpStatus.NOT_FOUND);
        }
        CourseVersion courseVersion = courseStructure.getCourseVersion();
        if (courseVersion == null || !CourseVersionStatus.IN_USE.equals(courseVersion.getCourseVersionStatus())) {
            return new ResponseEntity(ErrorResult.customError("保存失败，当前版本已归档，请刷新页面"), HttpStatus.NOT_FOUND);
        }
        CourseResource courseResource;
        if (StringUtils.isNotEmpty(testPaperParmVo.getId())) {
            courseResource = courseResourceRepository.findByResourceDetailIdAndResourceStatusAndCourseStructure(
                    testPaperParmVo.getId(), ResourceStatus.NORMAL, courseStructure);
        } else {
            courseResource = new CourseResource();
        }
        List<CourseResource> courseResources = checkSameNameTestPaper(testPaperParmVo.getUserId(),
                courseStructure, testPaperParmVo.getPaperName(), testPaperParmVo.isIsPublic(), ResourceType.CLASS_TEST);
        //测验名称一样
        List<CourseResource> resourceList = courseResources.stream()
                .filter(resource -> ResourceType.CLASS_TEST.equals(resource.getResourceType()) &&
                        !resource.getId().equals(courseResource.getId()))
                .collect(toList());
        if (CollectionUtils.isEmpty(resourceList)) {
            isEmptyHandle(testPaperParmVo, courseStructure, courseResource);
        } else {
            this.setCourseResourceName(resourceList, testPaperParmVo, courseResource);
            this.buildCourseResource(courseResource, testPaperParmVo, courseStructure);
            CourseResource save = courseResourceRepository.save(courseResource);
            List<CourseResource> courseResourceList =
                    checkParentIdLike(courseResource.getCreatorId(), courseStructure, save.getResourceName(), save.getIsPublic());
            courseResourceList = courseResourceList.stream()
                    .filter(resource -> !resource.getResourceDetailId().equals(testPaperParmVo.getId())).collect(toList());
            if (CollectionUtils.isNotEmpty(courseResourceList)) {
                List<String> screenLeft = Arrays.stream(save.getResourceName().split("\\(")).collect(toList());
                List<String> saveIndex = new ArrayList<>();
                for (String left : screenLeft) {
                    List<String> collect = Arrays.stream(left.split("\\)")).collect(Collectors.toList());
                    List<String> collect1 = collect.stream().filter(StringUtils::isNumeric).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(collect1)) {
                        saveIndex.add(collect1.get(0));
                    }
                }
                List<CourseResource> loopCourseResources = new ArrayList<>();
                for (CourseResource resource : courseResourceList) {
                    List<String> indexNum = Arrays.stream(resource.getResourceName().split("\\(")).collect(Collectors.toList());
                    List<String> index = new ArrayList<>();
                    for (String string : indexNum) {
                        List<String> collect = Arrays.stream(string.split("\\)")).collect(Collectors.toList());
                        List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(collect1)) {
                            index.add(collect1.get(0));
                        }
                    }
                    if (index.size() == saveIndex.size() + 1) {
                        loopCourseResources.add(resource);
                    }
                }
                loopCourseResources.forEach(lopesources -> {
                    lopesources.setParentId(save.getId());
                });
                courseResourceRepository.saveAll(loopCourseResources);
            }
            testPaperParmVo.setPaperName(save.getResourceName());
        }
        if (dataVisualEnable) {
            courseService.sendCourseResourceEvent(courseResource);
        }
        courseCompletionService.saveCourseCompletion(courseStructure.getCourse(), CourseContentTypeEnum.PREPARATORY_ACTIVITIES, true,
                testPaperParmVo.getUserId(), testPaperParmVo.getUserName());
        return new ResponseEntity(testPaperParmVo, HttpStatus.OK);
    }
    private void isEmptyHandle(TestPaperParmVo testPaperParmVo, CourseStructure courseStructure, CourseResource courseResource) {
        List<CourseResource> courseResourcesLike = checkSameNameTestPaperLike(testPaperParmVo.getUserId(),
                courseStructure, testPaperParmVo.getPaperName(), testPaperParmVo.isIsPublic());
        List<CourseResource> resourceListLike = courseResourcesLike.stream()
                .filter(resource -> resource.getResourceType() == ResourceType.CLASS_TEST &&
                        !resource.getId().equals(courseResource.getId()))
                .collect(toList());
        String realName = testPaperParmVo.getPaperName();
        realName = StringUtils.substringAfterLast(realName, "(");
        realName = StringUtils.substringBeforeLast(realName, ")");
        if (CollectionUtils.isEmpty(resourceListLike)) {
            processingParentNodeNotExist(testPaperParmVo, courseStructure, courseResource, realName);
        } else {
            List<CourseResource> exitCourseResources = new ArrayList<>();
            List<String> pIdS = new ArrayList<>();
            resourceListLike.forEach(resource -> {
                if (StringUtils.isAnyEmpty(resource.getParentId())) {
                    pIdS.add(resource.getParentId());
                }
            });
            resourceListLike.forEach(resource -> {
                if (StringUtils.isAnyEmpty(resource.getParentId()) || !pIdS.contains(resource.getId())) {
                    exitCourseResources.add(resource);
                }
            });
            if (testPaperParmVo.getPaperName().contains(")") && StringUtils.isNumeric(realName)) {
                String name = testPaperParmVo.getPaperName();
                name = StringUtils.substringBeforeLast(name, "(");
                List<CourseResource> courseResourceList = checkSameNameTestPaperLike(testPaperParmVo.getUserId(),
                        courseStructure, name, testPaperParmVo.isIsPublic());
                courseResourceList = courseResourceList.stream().filter(resource -> !resource.getResourceDetailId()
                        .equals(testPaperParmVo.getId())).collect(toList());
                if (CollectionUtils.isNotEmpty(courseResourceList)) {
                    CourseResource resource = courseResourceList.get(0);
                    courseResource.setParentId(resource.getId());
                }
                courseResource.setResourceName(testPaperParmVo.getPaperName());
                int index = Integer.parseInt(realName);
                courseResource.setVisionNumber(index);
                this.buildCourseResource(courseResource, testPaperParmVo, courseStructure);
                CourseResource save = courseResourceRepository.save(courseResource);
                testPaperParmVo.setPaperName(save.getResourceName());
            } else {
                courseResource.setResourceName(testPaperParmVo.getPaperName());
                courseResource.setVisionNumber(0);
                this.buildCourseResource(courseResource, testPaperParmVo, courseStructure);
                CourseResource save = courseResourceRepository.save(courseResource);
                exitCourseResources.forEach(resource -> resource.setParentId(save.getId()));
                courseResourceRepository.saveAll(exitCourseResources);
                testPaperParmVo.setPaperName(save.getResourceName());
            }
        }
    }
    private void processingParentNodeNotExist(TestPaperParmVo testPaperParmVo, CourseStructure courseStructure,
                                              CourseResource courseResource, String realName) {
        if (testPaperParmVo.getPaperName().contains(")") && StringUtils.isNumeric(realName)) {
            int index = Integer.parseInt(realName);
            courseResource.setVisionNumber(index);
            courseResource.setResourceName(testPaperParmVo.getPaperName());
            this.buildCourseResource(courseResource, testPaperParmVo, courseStructure);
            CourseResource save = courseResourceRepository.save(courseResource);
            testPaperParmVo.setPaperName(save.getResourceName());
        } else {
            courseResource.setVisionNumber(0);
            courseResource.setResourceName(testPaperParmVo.getPaperName());
            this.buildCourseResource(courseResource, testPaperParmVo, courseStructure);
            CourseResource save = courseResourceRepository.save(courseResource);
            testPaperParmVo.setPaperName(save.getResourceName());
        }
    }
    private void buildCourseResource(CourseResource courseResource, TestPaperParmVo testPaperParmVo, CourseStructure courseStructure) {
        Date now = new Date();
        courseResource.setCourseStructure(courseStructure);
        courseResource.setResourceDetailId(testPaperParmVo.getPaperId());
        if (StringUtils.isEmpty(testPaperParmVo.getId())) {
            courseResource.setCreateTime(now);
            courseResource.setCreatorId(testPaperParmVo.getUserId());
            courseResource.setCreatorName(testPaperParmVo.getUserName());
            if ((ObjectUtils.isNotEmpty(testPaperParmVo.getSource()) && 1 == testPaperParmVo.getSource()) || testPaperParmVo.isIsPublic()) {
                CourseResource resource = courseResourceRepository.findTop1ByCourseStructureIdAndIsPublicAndResourceStatusOrderByAuxiliarySortDesc(
                        courseStructure.getId(), true, ResourceStatus.NORMAL);
                if (ObjectUtils.isNotEmpty(resource)) {
                    courseResource.setAuxiliarySort(resource.getAuxiliarySort() + 1);
                } else {
                    courseResource.setAuxiliarySort(0);
                }
            }
            CourseResource resource = courseResourceRepository.findTop1ByCreatorIdAndCourseStructureIdAndResourceStatusOrderBySortDesc(
                    testPaperParmVo.getUserId(), courseStructure.getId(), ResourceStatus.NORMAL);
            if (ObjectUtils.isNotEmpty(resource)) {
                courseResource.setSort(resource.getSort() + 1);
            } else {
                courseResource.setSort(0);
            }
        }
        courseResource.setModifierId(testPaperParmVo.getUserId());
        courseResource.setModifierName(testPaperParmVo.getUserName());
        courseResource.setModifyTime(now);
        courseResource.setResourceType(ResourceType.CLASS_TEST);
        courseResource.setResourceContentNum(testPaperParmVo.getQuestionNum());
        courseResource.setResourceStatus(ResourceStatus.NORMAL);
        courseResource.setIsPublic(testPaperParmVo.isIsPublic());
        courseResource.setSourceType(ResourceSourceType.USER_ADDED);
    }


    private void setCourseResourceName(List<CourseResource> courseResources, TestPaperParmVo testPaperParmVo
            , CourseResource courseResource) {
        List<CourseResource> judgeCourseResources = new ArrayList<>();
        courseResources.forEach(resource -> {
            if (resource.getResourceName().equals(testPaperParmVo.getPaperName())) {
                judgeCourseResources.add(resource);
            }
        });
        CourseResource existCourseResource = judgeCourseResources.get(0);
        List<CourseResource> existCourseResources =
                courseResourceRepository.findByParentIdAndResourceStatus(existCourseResource.getId(), ResourceStatus.NORMAL);
        existCourseResources.sort(Comparator.comparingInt(CourseResource::getVisionNumber));
        List<Integer> nowVisionNumberList = existCourseResources
                .stream().map(CourseResource::getVisionNumber).distinct().collect(Collectors.toList());
        int maxNowVisionNumber = nowVisionNumberList.stream().max(Integer::compareTo).orElse(0);
        courseResource.setVisionNumber(maxNowVisionNumber + 1);
        courseResource.setParentId(existCourseResource.getId());
        courseResource.setResourceName(testPaperParmVo.getPaperName() + "(" + (maxNowVisionNumber + 1) + ")");
    }

    private List<CourseResource> checkSameNameTestPaper(String creatorId, CourseStructure courseStructure, String name, Boolean isPublic
            , ResourceType resourceType) {
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.testPaperSpecification(creatorId, courseStructure.getCourse(), name, isPublic,
                        courseStructure.getCourseVersion()));
        return courseResources.stream().filter(c -> c.getResourceType().equals(resourceType)).collect(Collectors.toList());

    }

    /**
     * 新增判断重复
     **/
    private List<CourseResource> checkSameNameToPublic(String creatorId, CourseStructure courseStructure, String name, boolean isPublic
            , ResourceType resourceType) {
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.specification(creatorId, courseStructure.getCourse().getId(), name, isPublic,
                        courseStructure.getCourseVersion(), courseStructure));
        return courseResources.stream().filter(c -> c.getResourceType().equals(resourceType)).collect(Collectors.toList());
    }

    /**
     * 除考核测验外的模糊查询
     **/
    private List<CourseResource> checkSameNamePaperLike(String creatorId, CourseStructure courseStructure,
                                                        String name, boolean isIsPublic, ResourceType resourceType) {
        if (name.contains("(")) {
            name = StringUtils.substringBeforeLast(name, "(");
        }
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.specificationlike(creatorId, courseStructure.getCourse().getId(), name, isIsPublic,
                        courseStructure.getCourseVersion(), courseStructure));

        return courseResources.stream().filter(c -> c.getResourceType().equals(resourceType)).collect(Collectors.toList());
    }

    private List<CourseResource> checkSameNameTestPaperLike(String creatorId, CourseStructure courseStructure, String name, Boolean isPublic) {
        if (name.contains("(")) {
            name = StringUtils.substringBeforeLast(name, "(");
        }
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.testPaperSpecificationlike(creatorId, courseStructure.getCourse(), name, isPublic,
                        courseStructure.getCourseVersion()));
        return courseResources;
    }

    private List<CourseResource> checkParentIdLike(String creatorId, CourseStructure courseStructure, String name, Boolean isPublic) {
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.testPaperSpecificationlike(creatorId, courseStructure.getCourse(), name, isPublic,
                        courseStructure.getCourseVersion()));
        return courseResources;
    }

    public ResponseEntity<List<PreparationCourse>> getPreparationCourses(String teacherId) {
        Term term = termService.getNowTerm();
        List<PreparationCourse> courseConstructionPreparationCourses = new ArrayList<>();
        List<CourseHiddenRecord> courseHiddenRecords = courseHiddenRecordRepository.findByTeacherId(teacherId);
        List<PreparationCourse> teacherPreparationCourses = createPreparationResource(teacherId, term);
        List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository.findByTeacherIdOrderByCourseId(teacherId);
        List<CourseTeachingTeam> finalCourseTeachingTeams = courseTeachingTeams;

        // 添加本学期的课程的
        teacherPreparationCourses.forEach(preparationCourse -> {
            buildTermTeachingCourse(term, courseHiddenRecords, finalCourseTeachingTeams, preparationCourse);
        });

        List<String> termCourseIds = teacherPreparationCourses.stream().map(
                PreparationCourse::getCourseId).collect(Collectors.toList());
        List<String> hiddenCourseIds = courseHiddenRecords.stream().map(
                CourseHiddenRecord::getCourseId).collect(Collectors.toList());
        courseTeachingTeams = courseTeachingTeams
                .stream()
                .filter(courseTeachingTeam -> !termCourseIds.contains(courseTeachingTeam.getCourse().getId())
                        && courseTeachingTeam.getCourse().isUseState())
                .collect(Collectors.toList());

        // 非本学期教学团队的
        if (CollectionUtils.isNotEmpty(courseTeachingTeams)) {
            courseConstructionPreparationCourses = buildCourseConstruction(courseTeachingTeams, hiddenCourseIds);
        }
        teacherPreparationCourses.addAll(courseConstructionPreparationCourses);
        List<PreparationCourse> hiddenPreparationCourseList = new ArrayList<>();
        hiddenPreparationCourseList = teacherPreparationCourses.stream().filter(
                preparationCourse -> preparationCourse.getIfHide() == 1).collect(Collectors.toList());
        teacherPreparationCourses = teacherPreparationCourses.stream()
                .filter(preparationCourse -> preparationCourse.getIfHide() == 0).collect(Collectors.toList());
        teacherPreparationCourses.addAll(hiddenPreparationCourseList);
        return new ResponseEntity<>(teacherPreparationCourses, HttpStatus.OK);
    }

    private List<PreparationCourse> buildCourseConstruction(List<CourseTeachingTeam> courseTeachingTeams, List<String> hiddenCourseIds) {
        List<PreparationCourse> preparationCourses;
        preparationCourses = courseTeachingTeams.stream().map(courseTeachingTeam -> new PreparationCourse() {{
            this.setCourseId(courseTeachingTeam.getCourse().getId());
            this.setCourseCode(courseTeachingTeam.getCourse().getCourseCode());
            this.setCourseName(courseTeachingTeam.getCourse().getCourseName());
            if (Objects.nonNull(courseTeachingTeam.getCourse().getCollege())) {
                this.setCollegeId(courseTeachingTeam.getCourse().getCollege().getId());
            }
            this.setCollegeName(courseTeachingTeam.getCourse().getCollegeName());
            this.setCollegeCode(courseTeachingTeam.getCourse().getCollegeCode());
            this.setCourseSource(Objects.nonNull(courseTeachingTeam.getCourse().getCourseSource()) ?
                    courseTeachingTeam.getCourse().getCourseSource().getIndex()
                    : 0);
            if (ObjectUtils.isEmpty(courseTeachingTeam.getCourseVersion())) {
                CourseVersion courseVersion = courseVersionService.generateAndUpdateCourseVersion(courseTeachingTeam.getCourse());
                courseTeachingTeam.setCourseVersion(courseVersion);
            }
            this.setVersionId(courseTeachingTeam.getCourseVersion().getId());
            this.setIsJoin(1);
            this.setIsPrincipal(courseTeachingTeam.getTeacherType().getIndex());
            List<CourseStructure> courseStructures =
                    courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus(courseTeachingTeam.getCourse().getId(),
                            courseTeachingTeam.getCourseVersion().getId(), StructureStatus.NORMAL);
            if (CollectionUtils.isNotEmpty(courseStructures)) {
                courseStructures.sort(Comparator.comparing(CourseStructure::getModifyTime).reversed());
                this.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        Locale.getDefault()).format(courseStructures.get(0).getModifyTime()));
            } else {
                this.setModifyTime("");
            }
            if (hiddenCourseIds.contains(courseTeachingTeam.getCourse().getId())) {
                this.setIfHide(1);
            } else {
                this.setIfHide(0);
            }
        }}).collect(Collectors.toList());
        return preparationCourses;
    }

    private void buildTermTeachingCourse(Term term, List<CourseHiddenRecord> courseHiddenRecords, List<CourseTeachingTeam> finalCourseTeachingTeams
            , PreparationCourse preparationCourse) {
        if (StringUtils.isBlank(preparationCourse.getVersionId())) {
            Course course = courseRepository.findByIdAndUseState(preparationCourse.getCourseId(), true);
            if (Objects.nonNull(course)) {
                CourseVersion courseVersion = courseVersionService.generateAndUpdateCourseVersion(course);
                preparationCourse.setVersionId(courseVersion.getId());
                if (Objects.nonNull(course.getCollege())) {
                    preparationCourse.setCollegeId(course.getCollege().getId());
                }
                preparationCourse.setCollegeCode(course.getCollegeCode());
                preparationCourse.setCollegeName(course.getCollegeName());
            }
        }
        CourseTeachingTeam joinCourseTeachingTeam = finalCourseTeachingTeams
                .stream().filter(courseTeachingTeam -> courseTeachingTeam.getCourse().getId().equals(preparationCourse.getCourseId()))
                .findFirst().orElse(null);
        if (ObjectUtils.isNotEmpty(joinCourseTeachingTeam)) {
            preparationCourse.setIsJoin(1);
            preparationCourse.setIsPrincipal(joinCourseTeachingTeam.getTeacherType().getIndex());
            if (ObjectUtils.isEmpty(joinCourseTeachingTeam.getCourseVersion())) {
                CourseVersion courseVersion = courseVersionService.generateAndUpdateCourseVersion(joinCourseTeachingTeam.getCourse());
                joinCourseTeachingTeam.setCourseVersion(courseVersion);
            }

            List<CourseStructure> courseStructures =
                    courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus(joinCourseTeachingTeam.getCourse().getId(),
                            joinCourseTeachingTeam.getCourseVersion().getId(), StructureStatus.NORMAL);
            if (CollectionUtils.isNotEmpty(courseStructures)) {
                courseStructures.sort(Comparator.comparing(CourseStructure::getModifyTime).reversed());
                preparationCourse.setModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        Locale.getDefault()).format(courseStructures.get(0).getModifyTime()));
            } else {
                preparationCourse.setModifyTime("");
            }
        } else {
            preparationCourse.setIsJoin(0);
            preparationCourse.setIsPrincipal(0);
            preparationCourse.setModifyTime("");
        }
        List<String> termHiddenCourseIds = courseHiddenRecords.stream()
                .filter(c -> c.getTerm().equals(term.getTerm().getIndex()) && c.getSchoolYear().equals(term.getSchoolYear()))
                .map(CourseHiddenRecord::getCourseId).collect(Collectors.toList());
        if (termHiddenCourseIds.contains(preparationCourse.getCourseId())) {
            preparationCourse.setIfHide(1);
        } else {
            preparationCourse.setIfHide(0);
        }
    }

    public List<PreparationCourse> createPreparationResource(String teacherId, Term term) {
        String querySql = "SELECT tc.id AS courseId, " +
                " tc.course_code as courseCode, tc.course_name AS courseName, tc.course_source AS courseSource  " +
                " FROM tb_course tc " +
                " INNER JOIN tb_course_table tct ON tct.course_id = tc.id " +
                " INNER JOIN tb_course_table_detail tctd ON tctd.course_table_id = tct.id " +
                " INNER JOIN tb_course_table_detail_teacher tctdt ON tctdt.course_table_detail_id = tctd.id " +
                " left join tb_course_version cv on tc.id = cv.course_id and course_version_status=1" +
                " WHERE tc.use_state=1 and  teacher_id = :teacherId " +
                " and tct.term = :term  and tct.school_year = :schoolYear " +
                " GROUP BY tctdt.teacher_id, tc.id ";
        Query queryData = entityManager.createNativeQuery(querySql);
        queryData.setParameter("teacherId", teacherId);
        queryData.setParameter("term", term.getTerm().getIndex());
        queryData.setParameter("schoolYear", term.getSchoolYear());
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(PreparationCourse.class));
        return (List<PreparationCourse>) queryData.getResultList();
    }

    public ResponseEntity<List<CourseResourceVo>> getCourseStructureIdCourseResources(
            String courseStructureId, String teacherId, String resourceName) {
        List<CourseResourceVo> courseResourceVos = new ArrayList<>();
        CourseStructure courseStructure = courseStructureRepository.findById(courseStructureId).orElse(null);
        if (ObjectUtils.isEmpty(courseStructure)) {
            return new ResponseEntity(ErrorResult.dataNotExistError("课程结构"), HttpStatus.NOT_FOUND);
        }
        List<CourseResource> courseResources = courseStructure.getCourseResources();
        courseResources = courseResources
                .stream()
                .filter(courseResource -> courseResource.getResourceStatus().equals(ResourceStatus.NORMAL) &&
                        courseResource.getCreatorId().equals(teacherId)).sorted(Comparator.comparing(CourseResource::getSort,
                                Comparator.nullsFirst(Integer::compareTo)).reversed()
                        .thenComparing(CourseResource::getModifyTime, Comparator.reverseOrder())
                        .thenComparing(CourseResource::getId))
                .collect(Collectors.toList());
        if (StringUtils.isNotBlank(resourceName)) {
            courseResources = courseResources
                    .stream()
                    .filter(courseResource -> courseResource.getResourceName().toLowerCase().contains(resourceName.toLowerCase()))
                    .collect(Collectors.toList());
        }
        List<String> detailIds = courseResources
                .stream()
                .map(CourseResource::getResourceDetailId)
                .collect(Collectors.toList());
        List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
        courseResourceVos = getCourseResourceList(courseResources, courseResourceFiles);
        return new ResponseEntity<>(courseResourceVos, HttpStatus.OK);
    }

    private List<CourseResourceVo> getCourseResourceList(List<CourseResource> courseResources,
                                                         List<CourseResourceFile> courseResourceFiles) {
        return courseResources.stream()
                .map(courseResource -> new CourseResourceVo() {{
                    this.setResourceId(courseResource.getId());
                    this.setResourceName(courseResource.getResourceName());
                    this.setResourceType(courseResource.getResourceType().getIndex());
                    this.setModifierId(courseResource.getModifierId());
                    this.setModifyTime(DateUtils.formatDate(DateUtils.DATE_TIME, courseResource.getModifyTime()));
                    this.setModifierName(courseResource.getModifierName());
                    this.setIsPublic(courseResource.getIsPublic() ? 1 : 0);
                    this.setResourceDetailId(courseResource.getResourceDetailId());
                    this.creatorId(courseResource.getCreatorId());
                    this.setSourceType(Objects.isNull(courseResource.getSourceType()) ? ResourceSourceType.USER_ADDED.getValue() :
                            courseResource.getSourceType().getValue());
                    if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) {
                        this.setQuestionNum(courseResource.getResourceContentNum());
                    }
                    this.setResourceNum(Objects.isNull(courseResource.getResourceContentNum()) ?
                            0 : courseResource.getResourceContentNum());
                    this.setResourceReferences(courseResource.getResourceReferences() == null ? 0 :
                            (courseResource.getResourceReferences()));
                    this.setResourceOtherReferences(courseResource.getResourceOtherReferences() == null ? 0 :
                            (courseResource.getResourceOtherReferences()));
                    CourseResourceFile file = courseResourceFiles
                            .stream()
                            .filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                            .findFirst()
                            .orElse(null);
                    if (ObjectUtils.isNotEmpty(file)) {
                        if (TranscodeStatus.TRANSCODE_SUCCESS.equals(file.getTranscodeStatus())) {
                            this.setInnerIp(file.getTranscodeInnerIp());
                            this.setOuterIp(file.getTranscodeOuterIp());
                            this.setFilePath(file.getTranscodeFilePath());
                            this.setFileType(file.getTranscodeFileType());
                            this.setFileSize(file.getTranscodeFileSize() + "");
                            this.setFileSavedName(file.getTranscodeFileSavedName());
                            String sizeStr = "";
                            if (file.getTranscodeFileSize() != null) {
                                double size = file.getTranscodeFileSize() / trillion;
                                if (size <= zeroPointOne) {
                                    sizeStr += "0.1M";
                                } else {
                                    sizeStr += Util.initDecimalFormat(size) + "M";
                                }
                            }
                            this.setResourceSize(sizeStr);
                        } else {
                            this.setInnerIp(file.getInnerIp());
                            this.setOuterIp(file.getOuterIp());
                            this.setFilePath(file.getFilePath());
                            this.setFileType(file.getFileType());
                            this.setFileSize(file.getFileSize() + "");
                            this.setFileSavedName(file.getFileSavedName());
                            String sizeStr = "";
                            if (file.getFileSize() != null) {
                                double size = file.getFileSize() / trillion;
                                if (size <= zeroPointOne) {
                                    sizeStr += "0.1M";
                                } else {
                                    sizeStr += Util.initDecimalFormat(size) + "M";
                                }
                            }
                            this.setResourceSize(sizeStr);
                        }
                        this.setTranscodeStatus(file.getTranscodeStatus().getValue());
                    } else {
                        this.setInnerIp("");
                        this.setOuterIp("");
                        this.setFilePath("");
                        this.setResourceSize("");
                        this.setFileType("");
                        this.setFileSavedName("");
                        this.setFileSize("");
                    }
                }}).collect(Collectors.toList());
    }

    //endregion
    //region 接口-获取发布资源信息
    public ResponseEntity<CourseResourceModule> getIdCourseIdTeacherIdCourseResource(String id, String teacherId, String courseId) {
        CourseResource courseResource = courseResourceRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(courseResource)) {
            return new ResponseEntity(ErrorResult.customError("没有该课程资源"), HttpStatus.CONFLICT);
        }
        if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) {
            return new ResponseEntity(ErrorResult.customError("课堂测验无法上传"), HttpStatus.CONFLICT);
        }
        CourseResourceFile courseResourceFile = courseResourceFileRepository.findById(courseResource.getResourceDetailId()).orElse(null);
        if (ObjectUtils.isEmpty(courseResourceFile)) {
            return new ResponseEntity(ErrorResult.customError("该课程资源没有对应详情"), HttpStatus.CONFLICT);
        }
        /**获取一级结构**/
        CourseStructure courseStructure = courseResource.getCourseStructure();
        while (StringUtils.isNotEmpty(courseStructure.getParentId())) {
            courseStructure = courseStructureRepository.findById(courseStructure.getParentId()).orElse(null);
        }
        /**获取当前学期和下一个学期**/
        List<TermResource> termResources = termService.getNowTermAndNextTerm().getBody();
        /**查询老师当前学期或下学期该课程是否存在班级**/
        List<Group> groupList = new ArrayList<>();
        termResources.stream().forEach(termResource -> {
            List<CourseTable> courseTableList = courseTableRepository.findAll(getCourseTableSpecification(
                    teacherId, courseId, termResource.getSchoolYear(), Integer.valueOf(termResource.getTerm()),
                    null));
            groupList.addAll(getGroupList(courseTableList));
        });
        if (CollectionUtils.isEmpty(groupList)) {
            return new ResponseEntity(ErrorResult.customError("该课程下老师没有班级，不能上传"), HttpStatus.CONFLICT);
        }
        CourseResourceModule courseResourceModule = new CourseResourceModule();
        courseResourceModule.setCourseStructureId(courseStructure.getId());
        courseResourceModule.setCourseStructureName(courseStructure.getCourseStructureName());
        courseResourceModule.setResourceId(courseResource.getId());
        courseResourceModule.setFileName(courseResourceFile.getFileRealName());
        courseResourceModule.setFilePath(courseResourceFile.getFilePath());
        courseResourceModule.setFileType(courseResourceFile.getFileType());
        courseResourceModule.setIntranetFilePath(courseResourceFile.getInnerIp());
        courseResourceModule.setOuttranetFilePath(courseResourceFile.getOuterIp());
        courseResourceModule.setUploadedFileName(courseResourceFile.getFileSavedName());
        courseResourceModule.setFileSize(courseResourceFile.getFileSize().toString());
        return new ResponseEntity<>(courseResourceModule, HttpStatus.OK);


    }

    private List<Group> getGroupList(List<CourseTable> courseTables) {
        List<Group> groupList = new ArrayList<>();
        for (CourseTable courseTable : courseTables
                .stream()
                .filter(g -> GroupStatus.NORMAL.equals(g.getGroup().getGroupStatus()))
                .collect(toList())) {
            groupList.add(courseTable.getGroup());
        }
        groupList.stream()
                .distinct()
                .collect(toList());
        groupList.sort(Comparator.comparing(Group::getCreateTime).thenComparing(Group::getId));
        return groupList;
    }
    //endregion

    public ResponseEntity<com.lztech.site.viewmodel.CourseResource> getCourseIdTeacherCourse(String teacherId, String courseId) {
        /**获取当前学期和下一个学期**/
        List<TermResource> termResources = termService.getNowTermAndNextTerm().getBody();
        List<CourseTable> courseTableList = new ArrayList<>();
        for (TermResource termResource : termResources) {
            courseTableList = courseTableRepository.findAll(getCourseTableSpecification(
                    teacherId, courseId, termResource.getSchoolYear(), Integer.valueOf(termResource.getTerm()), null));
            if (CollectionUtils.isNotEmpty(courseTableList)) {
                break;
            }
        }
        if (CollectionUtils.isNotEmpty(courseTableList)) {
            Map<Course, List<CourseTable>> courseTableGroup = courseTableList.stream()
                    .collect(Collectors.groupingBy(CourseTable::getCourse));
            List<com.lztech.site.viewmodel.CourseResource> courseResourceList =
                    courseTableService.getCourseResources(courseTableService.getCourseTableGroup(courseTableGroup));
            if (CollectionUtils.isNotEmpty(courseResourceList)) {
                com.lztech.site.viewmodel.CourseResource courseResource = courseResourceList.get(0);
                return new ResponseEntity<>(courseResource, HttpStatus.OK);
            }
        }
        return new ResponseEntity(ErrorResult.customError("发布失败，该课程下老师暂无班级"), HttpStatus.NOT_FOUND);
    }


    //region 获取备课包老师教学资源
    public List<TeachingFileVo> getTeachingFiles(String teacherId, String courseId) {
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            throw new CustomRuntimeException(ErrorResult.customError("未找到课程信息"));
        }
        List<TeachingFileVo> teachingFileVos = new ArrayList<>();
        if (courseTeachingTeamService.judgeTeacherInCourseTeachingTeam(courseId, teacherId)) {
            List<CourseStructure> courseStructures = courseStructureRepository.getInUseVersionCourseStructure(courseId,
                    StructureStatus.NORMAL.getIndex());
            List<CourseResource> courseResources = new ArrayList<>();
            courseStructures.forEach(structure -> {
                courseResources.addAll(structure.getCourseResources()
                        .stream()
                        .filter(courseResource ->
                                courseResource.getResourceStatus().equals(ResourceStatus.NORMAL) &&
                                        courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE) &&
                                        (courseResource.getIsPublic() || teacherId.equals(courseResource.getCreatorId())))
                        .collect(Collectors.toList()));
            });
            List<CourseResource> resources = courseResources
                    .stream()
                    .sorted(Comparator.comparing(CourseResource::getModifyTime))
                    .collect(toList());
            List<String> detailIds = resources
                    .stream()
                    .map(CourseResource::getResourceDetailId)
                    .collect(Collectors.toList());
            List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
            resources.forEach(courseResource -> {
                CourseResourceFile file = courseResourceFiles
                        .stream()
                        .filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                        .findFirst()
                        .orElse(null);
                if (ObjectUtils.isNotEmpty(file)) {
                    TeachingFileVo teachingFileVo = new TeachingFileVo();
                    teachingFileVo.setResourceId(courseResource.getId());
                    teachingFileVo.setResourceName(courseResource.getResourceName());
                    teachingFileVo.setResourceType(file.getFileType());
                    teachingFileVo.setInnerResourceUrl((file.getInnerIp() == null ? "" : file.getInnerIp())
                            + (file.getFilePath() == null ? "" : file.getFilePath()));
                    teachingFileVo.setOuterResourceUrl((file.getOuterIp() == null ? "" : file.getOuterIp())
                            + (file.getFilePath() == null ? "" : file.getFilePath()));
                    teachingFileVos.add(teachingFileVo);
                }
            });
        }
        return teachingFileVos;
    }
    //endregion

    //region 获取备课包老师教学资源
    public List<TeachingFileVo> getTeacherPreparationCourseBag(String teacherId, String courseId) {
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            throw new CustomRuntimeException(ErrorResult.customError("未找到课程信息"));
        }
        List<TeachingFileVo> teachingFileVos = new ArrayList<>();
        if (courseTeachingTeamService.judgeTeacherInCourseTeachingTeam(courseId, teacherId)) {
            List<CourseStructure> courseStructures = courseStructureRepository.getInUseVersionCourseStructure(courseId,
                    StructureStatus.NORMAL.getIndex());
            List<CourseResource> courseResources = new ArrayList<>();
            courseStructures.forEach(structure -> {
                courseResources.addAll(structure.getCourseResources()
                        .stream()
                        .filter(courseResource ->
                                courseResource.getResourceStatus().equals(ResourceStatus.NORMAL) &&
                                        (courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE) ||
                                                courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO)) &&
                                        teacherId.equals(courseResource.getCreatorId()))
                        .collect(Collectors.toList()));
            });
            List<CourseResource> resources = courseResources
                    .stream()
                    .sorted(Comparator.comparing(CourseResource::getModifyTime))
                    .collect(toList());
            List<String> detailIds = resources
                    .stream()
                    .map(CourseResource::getResourceDetailId)
                    .collect(Collectors.toList());
            List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
            resources.forEach(courseResource -> {
                CourseResourceFile file = courseResourceFiles
                        .stream()
                        .filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                        .findFirst()
                        .orElse(null);
                if (ObjectUtils.isNotEmpty(file)
                        && !isInteractiveClassroomNotAllowedFileType(file.getFileType())) {
                    TeachingFileVo teachingFileVo = new TeachingFileVo();
                    teachingFileVo.setResourceId(courseResource.getId());
                    teachingFileVo.setResourceName(courseResource.getResourceName());
                    teachingFileVo.setResourceType(file.getFileType());
                    teachingFileVo.setInnerResourceUrl((file.getInnerIp() == null ? "" : file.getInnerIp())
                            + (file.getFilePath() == null ? "" : file.getFilePath()));
                    teachingFileVo.setOuterResourceUrl((file.getOuterIp() == null ? "" : file.getOuterIp())
                            + (file.getFilePath() == null ? "" : file.getFilePath()));
                    teachingFileVos.add(teachingFileVo);
                }
            });
        }
        return teachingFileVos;
    }
    //endregion

    //region 获取备课包老师测验（试卷）资源
    public List<ClassTestPaperVo> getClassTestPapers(String teacherId, String courseId, String courseVisionId) {
        List<ClassTestPaperVo> classTestPaperVos = new ArrayList<>();
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            throw new CustomRuntimeException(ErrorResult.customError("未找到课程信息"));
        }
        List<CourseTeachingTeam> courseTeachingTeams = course.getCourseTeachingTeams();
        CourseTeachingTeam teacher = courseTeachingTeams
                .stream()
                .filter(courseTeachingTeam -> teacherId.equals(courseTeachingTeam.getTeacherId()))
                .findFirst()
                .orElse(null);
        if (ObjectUtils.isEmpty(teacher)) {
            return classTestPaperVos;
        }
        List<CourseResource> courseResources = course.getCourseStructures().stream().filter(
                        courseStructure -> courseStructure.getStructureStatus().equals(StructureStatus.NORMAL)
                                && ObjectUtils.isNotEmpty(courseStructure.getCourseVersion())
                                && courseVisionId.equals(courseStructure.getCourseVersion().getId()))
                .flatMap(filterCourseStructure -> filterCourseStructure.getCourseResources().stream())
                .filter(courseResource -> courseResource.getResourceType().equals(ResourceType.CLASS_TEST)
                        && courseResource.getResourceStatus().equals(ResourceStatus.NORMAL)
                        && teacherId.equals(courseResource.getCreatorId()))
                .collect(toList());
        List<String> classTestPaperIds = courseResources.stream().map(CourseResource::getResourceDetailId).collect(toList());
        List<String> withoutFillElectionResourceIds = filterFillElection(classTestPaperIds);
        courseResources = courseResources.stream()
                .filter(courseResource -> withoutFillElectionResourceIds.contains(courseResource.getResourceDetailId()))
                .collect(toList());
        classTestPaperVos = getClassTestPaperVos(courseResources);
        return classTestPaperVos;
    }

    private List<String> filterFillElection(List<String> courseStructureIds) {
        List<String> withoutFillElectionStructureIds = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String url = teachingApi + ConstantWebApi.FILTER_FILL_ELECTION + "?validCode=" + Md5Utils.md5(signKey);

        //设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<List<String>> httpEntity = new HttpEntity<>(courseStructureIds, headers);

        ResponseEntity<List<String>> responseEntity = restTemplate
                .exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<String>>() {
                });
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            withoutFillElectionStructureIds = responseEntity.getBody();
        }
        return withoutFillElectionStructureIds;
    }

    //公开资源，或个人的非公开资源
    private boolean checkIdPublicOrPersonalResource(CourseResource courseResource, String teacherId) {
        boolean isPublic = courseResource.getIsPublic();
        boolean isPersonal = !isPublic && teacherId.equals(courseResource.getCreatorId());
        return (isPublic || isPersonal);
    }

    private List<ClassTestPaperVo> getClassTestPaperVos(List<CourseResource> courseResources) {
        List<CourseResource> resources = courseResources
                .stream()
                .sorted(Comparator.comparing(CourseResource::getModifyTime).reversed())
                .collect(toList());
        List<ClassTestPaperVo> classTestPaperVoList = resources.stream().map(courseResource -> {
            ClassTestPaperVo classTestPaperVo = new ClassTestPaperVo();
            classTestPaperVo.setId(courseResource.getId());
            classTestPaperVo.setResourceId(courseResource.getResourceDetailId());
            classTestPaperVo.setResourceName(courseResource.getResourceName());
            classTestPaperVo.setSubjectNum(courseResource.getResourceContentNum() == null ? 0 : courseResource.getResourceContentNum());
            classTestPaperVo.setUpdateTime(DateUtils.formatDate(DateUtils.DATE_TIME, courseResource.getModifyTime()));
            return classTestPaperVo;
        }).collect(toList());
        return classTestPaperVoList;
    }
    //endregion

    public List<CourseTeacherVo> getTeacherIdTeacherCourses(String teacherId) {
        Term term = termService.getNowTerm();
        /** 老师当前学期所上课程 **/
        List<CourseTeacherVo> courseTeachers = creatCourseTeacherResource(teacherId, term);
        /** 老师所加入教师团队课程 **/
        List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository.findByTeacherIdOrderByCourseId(teacherId);
        /**加入教师团队课程筛选开课**/
        List<String> termCourseIds = courseTeachers
                .stream()
                .map(CourseTeacherVo::getCourseId)
                .collect(Collectors.toList());

        List<CourseTeacherVo> preparationCourseTeachers = courseTeachingTeams
                .stream()
                .filter(courseTeachingTeam
                        -> !termCourseIds.contains(courseTeachingTeam.getCourse().getId())
                        && courseTeachingTeam.getCourse().isUseState())
                .map(courseTeachingTeam
                        -> new CourseTeacherVo() {{
                    this.setCourseId(courseTeachingTeam.getCourse().getId());
                    this.setCourseCode(courseTeachingTeam.getCourse().getCourseCode());
                    this.setCourseName(courseTeachingTeam.getCourse().getCourseName());
                }})
                .collect(Collectors.toList());

        courseTeachers.addAll(preparationCourseTeachers);
        return courseTeachers;
    }


    public List<CourseTeacherVo> creatCourseTeacherResource(String teacherId, Term term) {
        String querySql = "SELECT " +
                " tc.id AS courseId, " +
                " tc.course_code as courseCode, " +
                " tc.course_name AS courseName , " +
                " tct.course_category_id AS courseCategoryId ," +
                " tcc.course_category_name AS courseCategoryName " +
                " FROM tb_course tc " +
                " INNER JOIN tb_course_table tct ON tct.course_id = tc.id " +
                " INNER JOIN tb_course_table_detail tctd ON tctd.course_table_id = tct.id " +
                " INNER JOIN tb_course_table_detail_teacher tctdt ON tctdt.course_table_detail_id = tctd.id " +
                " INNER JOIN tb_course_category tcc on tct.course_category_id =tcc.id " +
                " WHERE tc.use_state=1 and teacher_id = :teacherId " +
                " and tct.term = :term " +
                " and tct.school_year = :schoolYear" +
                " and tc.use_state = 1 " +
                " GROUP BY tctdt.teacher_id, tc.id ";
        Query queryData = entityManager.createNativeQuery(querySql);
        queryData.setParameter("teacherId", teacherId);
        queryData.setParameter("term", term.getTerm().getIndex());
        queryData.setParameter("schoolYear", term.getSchoolYear());
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTeacherVo.class));
        List<CourseTeacherVo> courseTeacherList = queryData.getResultList();
        return courseTeacherList;
    }


    public List<CourseResourceVo> getCourseIdTeacherIdImages(String courseId, String teacherId) {
        List<CourseResourceVo> courseResourceVos = new ArrayList<>();
        List<CourseStructure> courseStructures =
                courseStructureRepository.getInUseVersionCourseStructure(courseId, StructureStatus.NORMAL.getIndex());
        if (CollectionUtils.isEmpty(courseStructures)) {
            throw new CustomRuntimeException(ErrorResult.dataNotExistError("未找到课程结构"));
        }
        List<CourseResource> finalCourseResources = new ArrayList<>();
        courseStructures.forEach(courseStructure -> {
            List<CourseResource> courseResourceList = courseStructure.getCourseResources()
                    .stream()
                    .filter(
                            courseResource ->
                                    courseResource.getCreatorId().equals(teacherId) &&
                                            courseResource.getResourceType().equals(ResourceType.IMAGE) &&
                                            courseResource.getResourceStatus().equals(ResourceStatus.NORMAL))
                    .collect(toList());
            finalCourseResources.addAll(courseResourceList);
        });
        List<String> detailIds = finalCourseResources
                .stream()
                .map(CourseResource::getResourceDetailId)
                .collect(Collectors.toList());
        List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
        courseResourceVos = getCourseResourceList(finalCourseResources, courseResourceFiles);
        return courseResourceVos;
    }

    public void updateCourseResourceListIsPublic(String courseResourceId, Boolean isPublic, CourseResource resource) {

        Date now = new Date();
        if (!ResourceType.THEME.equals(resource.getResourceType()) && isPublic) {
            List<String> resourceNames = extendCourseManagementService.getAllResourceNames("", resource.getCourseStructure(), true);
            String newName = this.buildResourceNewName(resourceNames, resource);
            resource.setResourceName(newName);
        }
        resource.setIsPublic(isPublic);
        if (isPublic) {
            CourseResource courseResource = courseResourceRepository.findTop1ByCourseStructureIdAndIsPublicAndResourceStatusOrderByAuxiliarySortDesc(
                    resource.getCourseStructure().getId(), true, ResourceStatus.NORMAL);
            if (ObjectUtils.isNotEmpty(courseResource)) {
                resource.setAuxiliarySort(courseResource.getAuxiliarySort() + 1);
            } else {
                resource.setAuxiliarySort(0);
            }
        }
        resource.setModifyTime(now);
        courseResourceRepository.save(resource);
        if (dataVisualEnable) {
            CourseResource courseResource = courseResourceRepository.findById(courseResourceId).get();
            courseResource.setIsPublic(isPublic);
            courseService.sendCourseResourceEvent(courseResource);
        }

    }

    public String buildResourceNewName(List<String> resourceNames, CourseResource resource) {
        String finalName = resource.getResourceName();
        CourseResourceFile courseResourceFile = new CourseResourceFile();
        if (!resource.getResourceType().equals(ResourceType.THEME) && !resource.getResourceType().equals(ResourceType.CLASS_TEST)) {
            courseResourceFile = courseResourceFileRepository.findByIdAndResourceStatus(
                    resource.getResourceDetailId(), ResourceStatus.NORMAL);
            courseResourceFile.setIsPublic(Boolean.TRUE);
            courseResourceFileRepository.saveAndFlush(courseResourceFile);
        }
        if (!resource.getResourceType().equals(ResourceType.CLASS_TEST.getIndex())) {
            if (resource.getResourceName().indexOf("(") > 0) {
                String suffix = resource.getResourceName().substring(resource.getResourceName().indexOf("("));
                String originallyName = resource.getResourceName().replace(suffix, "");
                finalName = originallyName + "." + courseResourceFile.getFileType() + suffix;
            } else {
                finalName = resource.getResourceName() + "." + courseResourceFile.getFileType();
            }
        }
        AtomicReference<String> newName = new AtomicReference<>(courseManagementService.checkZipName(resourceNames, finalName, 1));
        String finalName1 = finalName;
        resourceNames.stream().filter(item -> item.equals(finalName1))
                .forEach(item -> {
                    if (resourceNames.contains(finalName1 + "(1)")) {
                        newName.set(courseManagementService.checkZipName(resourceNames, finalName1 + "(1)", 1));
                    } else {
                        newName.set(finalName1 + "(1)");
                    }
                });
        finalName = newName.get();
        resourceNames.add(newName.get());
        if (finalName.lastIndexOf(".") > 0 && finalName.indexOf("(") > 0) {
            String suffixType = finalName.substring(finalName.lastIndexOf("."), finalName.indexOf("("));
            finalName = finalName.replace(suffixType, "");
        } else if (finalName.lastIndexOf(".") > 0 && finalName.indexOf("(") < 0) {
            String suffixType = finalName.substring(finalName.lastIndexOf("."));
            finalName = finalName.replace(suffixType, "");
        }
        return finalName;
    }


    private CourseResourceFile updateCourseResourceFileLikeListIsNoteEmpty(CourseResourceFile courseResourceFile,
                                                                           String filename,
                                                                           List<CourseResource> courseResources,
                                                                           String fileRealName, boolean isPublic, CourseResource resource) {
        if (filename.contains(")") && StringUtils.isNumeric(fileRealName)) {
            String name = filename;
            name = StringUtils.substringBeforeLast(name, "(");
            CourseStructure courseStructure = resource.getCourseStructure();
            List<CourseResource> resources = checkSameNameToPublic
                    (courseResourceFile.getCreatorId(), courseStructure, name,
                            isPublic, resource.getResourceType());
            if (CollectionUtils.isNotEmpty(resources)) {

                CourseResource courseResource = resources.get(0);
                int index = Integer.parseInt(fileRealName);
                courseResourceFile.setVisionNumber(index);
                courseResourceFile.setFileRealName(filename);
                Optional<CourseResourceFile> optionalCourseResourceFile =
                        courseResourceFileRepository.findById(courseResource.getResourceDetailId());
                if (optionalCourseResourceFile.isPresent()) {
                    courseResourceFile.setParentId(optionalCourseResourceFile.get().getId());
                    CourseResourceFile resourceFile = optionalCourseResourceFile.get();
                    List<CourseResourceFile> fileList =
                            courseResourceFileRepository.findByParentIdAndResourceStatus(resourceFile.getId(), ResourceStatus.NORMAL);
                    List<CourseResourceFile> resourceFileList =
                            fileList.stream().filter(f -> !f.getId().equals(courseResourceFile.getId())).collect(toList());
                    if (CollectionUtils.isNotEmpty(resourceFileList)) {
                        fileList.sort(Comparator.comparingInt(CourseResourceFile::getVisionNumber));
                        List<Integer> nowVisionNumberList = resourceFileList
                                .stream()
                                .map(CourseResourceFile::getVisionNumber)
                                .distinct()
                                .collect(Collectors.toList());
                        int maxNowVisionNumber = nowVisionNumberList.stream().max(Integer::compareTo).orElse(0);
                        courseResourceFile.setVisionNumber(maxNowVisionNumber + 1);
                        courseResourceFile.setFileRealName(filename + "(" + (maxNowVisionNumber + 1) + ")");
                    }

                }
            }


            courseResourceFile.setIsPublic(isPublic);

            courseResourceFile.setModifyTime(new Date());
            List<CourseResourceFile> resourceStatus =
                    courseResourceFileRepository.findByParentIdAndResourceStatus(courseResourceFile.getId(), ResourceStatus.NORMAL);
            resourceStatus.forEach(file -> {
                file.setParentId(null);
            });
            courseResourceFileRepository.save(courseResourceFile);
            courseResourceFileRepository.saveAll(resourceStatus);
            return courseResourceFile;
        } else {
            return updateCourseResourceFileLiseListIsNoteEmptyNoteContain(courseResourceFile, filename, courseResources, isPublic);
        }
    }

    private CourseResourceFile updateCourseResourceFileLiseListIsNoteEmptyNoteContain(CourseResourceFile courseResourceFile,
                                                                                      String filename,
                                                                                      List<CourseResource> courseResources, boolean isPublic) {
        List<String> detailIds = courseResources
                .stream()
                .map(CourseResource::getResourceDetailId)
                .collect(Collectors.toList());
        List<CourseResourceFile> exitFiles = new ArrayList<>();
        List<CourseResourceFile> courseResourceFileList = courseResourceFileRepository.findByIdIn(detailIds);
        List<CourseResourceFile> files =
                courseResourceFileList.stream().filter(file -> !file.getId().equals(courseResourceFile.getId())).collect(toList());
        List<String> pids = new ArrayList<>();
        files.forEach(file -> {
            if (StringUtils.isAnyEmpty(file.getParentId())) {
                pids.add(file.getParentId());
            }
        });
        files.forEach(file -> {
            if (StringUtils.isAnyEmpty(file.getParentId()) || !pids.contains(file.getId())) {
                exitFiles.add(file);
            }
        });
        List<String> screenLeft = Arrays.stream(filename.split("\\(")).collect(toList());
        List<String> saveIndex = new ArrayList<>();
        for (String left : screenLeft) {
            List<String> collect = Arrays.stream(left.split("\\)")).collect(Collectors.toList());
            List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect1)) {
                saveIndex.add(collect1.get(0));
            }
        }
        List<CourseResourceFile> loopFiles = new ArrayList<>();
        for (CourseResourceFile file : exitFiles) {
            List<String> indexNum = Arrays.stream(file.getFileRealName().split("\\(")).collect(Collectors.toList());
            List<String> index = new ArrayList<>();
            for (String string : indexNum) {
                List<String> collect = Arrays.stream(string.split("\\)")).collect(Collectors.toList());
                List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(collect1)) {
                    index.add(collect1.get(0));
                }
            }
            if (index.size() == saveIndex.size() + 1) {
                loopFiles.add(file);
            }
        }
        courseResourceFile.setFileRealName(filename);
        courseResourceFile.setVisionNumber(0);
        courseResourceFile.setModifyTime(new Date());
        courseResourceFile.setIsPublic(isPublic);
        CourseResourceFile saveCourseResourceFile = courseResourceFileRepository.save(courseResourceFile);
        loopFiles.forEach(file -> {
            file.setParentId(saveCourseResourceFile.getId());
        });
        List<CourseResourceFile> resourceStatus =
                courseResourceFileRepository.findByParentIdAndResourceStatus(courseResourceFile.getId(), ResourceStatus.NORMAL);
        resourceStatus.forEach(file -> {
            file.setParentId(null);
        });
        courseResourceFileRepository.saveAll(resourceStatus);
        courseResourceFileRepository.saveAll(loopFiles);
        return saveCourseResourceFile;
    }


    private CourseResourceFile updateCourseResourceFileForNoteEmpty(CourseResourceFile courseResourceFile,
                                                                    String fileName, Date now,
                                                                    List<CourseResourceFile> exitFile, boolean isPublic,
                                                                    CourseResource courseResource) {
        CourseResourceFile resourceFile = exitFile.get(0);
        List<CourseResourceFile> resourceFilesByParentId =
                courseResourceFileRepository.findByParentIdAndResourceStatus(resourceFile.getId(), ResourceStatus.NORMAL);
        List<CourseResourceFile> excludeIdList = new ArrayList<>();
        for (CourseResourceFile file : resourceFilesByParentId) {
            if (!file.getId().equals(courseResourceFile.getId())) {
                excludeIdList.add(file);
            }
        }
        List<String> ids = excludeIdList.stream().map(CourseResourceFile::getId).collect(Collectors.toList());
        List<CourseResource> courseResourceList =
                courseResourceRepository.findByResourceDetailIdIsInAndCourseStructure(ids, courseResource.getCourseStructure());
        List<CourseResourceFile> judgeFiles = new ArrayList<>();
        resourceFilesByParentId.forEach(file -> {
            courseResourceList.forEach(resource -> {
                if (file.getId().equals(resource.getResourceDetailId()) && resource.getResourceStatus() == ResourceStatus.NORMAL) {
                    judgeFiles.add(file);
                }
            });
        });
        judgeFiles.sort(Comparator.comparingInt(CourseResourceFile::getVisionNumber));
        List<Integer> nowVisionNumberList = judgeFiles
                .stream()
                .map(CourseResourceFile::getVisionNumber)
                .distinct()
                .collect(Collectors.toList());
        int maxNowVisionNumber = nowVisionNumberList.stream().max(Integer::compareTo).orElse(0);
        courseResourceFile.setVisionNumber(maxNowVisionNumber + 1);
        courseResourceFile.setParentId(resourceFile.getId());
        courseResourceFile.setIsPublic(isPublic);
        courseResourceFile.setFileRealName(fileName + "(" + (maxNowVisionNumber + 1) + ")");
        courseResourceFile.setModifyTime(now);
        courseResourceFileRepository.save(courseResourceFile);
        List<CourseResourceFile> fileList =
                courseResourceFileRepository.findByParentIdAndResourceStatus(courseResourceFile.getId(), ResourceStatus.NORMAL);
        fileList.forEach(f -> {
            f.setParentId(null);
        });
        courseResourceFileRepository.saveAll(fileList);
        return courseResourceFile;
    }


    private CourseResource isNoteEmptyToCourseResource(CourseResource courseResource, String name,
                                                       List<CourseResource> resourceListLike,
                                                       String realName, boolean isPublic) {
        List<CourseResource> exitCourseResources = new ArrayList<>();
        List<String> pIdS = new ArrayList<>();
        resourceListLike.forEach(resource -> {
            if (StringUtils.isAnyEmpty(resource.getParentId())) {
                pIdS.add(resource.getParentId());
            }
        });
        resourceListLike.forEach(resource -> {
            if (StringUtils.isAnyEmpty(resource.getParentId()) || !pIdS.contains(resource.getId())) {
                exitCourseResources.add(resource);
            }
        });
        if (name.contains(")") && StringUtils.isNumeric(realName)) {
            String nameLike = name;
            nameLike = StringUtils.substringBeforeLast(nameLike, "(");
            List<CourseResource> courseResourceList = updateCheckSameNamePaperLike(courseResource.getCreatorId(),
                    courseResource.getCourseStructure(), nameLike, courseResource.getIsPublic());
            if (CollectionUtils.isNotEmpty(courseResourceList)) {
                CourseResource resource = courseResourceList.get(0);
                courseResource.setParentId(resource.getId());
            }
            courseResource.setResourceName(name);
            int index = Integer.parseInt(realName);
            courseResource.setIsPublic(isPublic);
            courseResource.setVisionNumber(index);
            courseResource.setModifyTime(new Date());
            courseResourceRepository.save(courseResource);
            List<CourseResource> courseResources =
                    courseResourceRepository.findByParentIdAndResourceStatus(courseResource.getId(), ResourceStatus.NORMAL);
            courseResources.forEach(courseResource1 -> {
                courseResource1.setParentId(null);
            });
            courseResourceRepository.saveAll(courseResources);
            return courseResource;
        } else {
            courseResource.setResourceName(name);
            courseResource.setIsPublic(isPublic);
            courseResource.setVisionNumber(0);
            courseResource.setModifyTime(new Date());
            CourseResource save = courseResourceRepository.save(courseResource);
            List<String> screenLeft = Arrays.stream(name.split("\\(")).collect(toList());
            List<String> saveIndex = new ArrayList<>();
            for (String left : screenLeft) {
                List<String> collect = Arrays.stream(left.split("\\)")).collect(Collectors.toList());
                List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(collect1)) {
                    saveIndex.add(collect1.get(0));
                }
            }
            List<CourseResource> loopCourseResources = new ArrayList<>();
            for (CourseResource resource : exitCourseResources) {
                List<String> indexNum = Arrays.stream(resource.getResourceName().split("\\(")).collect(Collectors.toList());
                List<String> index = new ArrayList<>();
                for (String string : indexNum) {
                    List<String> collect = Arrays.stream(string.split("\\)")).collect(Collectors.toList());
                    List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(collect1)) {
                        index.add(collect1.get(0));
                    }
                }
                if (index.size() == saveIndex.size() + 1) {
                    loopCourseResources.add(resource);
                }
            }
            loopCourseResources.forEach(resource -> resource.setParentId(save.getId()));
            courseResourceRepository.saveAll(loopCourseResources);
            List<CourseResource> courseResources =
                    courseResourceRepository.findByParentIdAndResourceStatus(courseResource.getId(), ResourceStatus.NORMAL);
            courseResources.forEach(courseResource1 -> {
                courseResource1.setParentId(null);
            });
            courseResourceRepository.saveAll(courseResources);
            return courseResource;
        }
    }

    private List<CourseResource> updateCheckSameNamePaperLike(String creatorId, CourseStructure courseStructure, String name, Boolean isPublic) {
        if (name.contains("(")) {
            name = StringUtils.substringBeforeLast(name, "(");
        }
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.testPaperSpecificationlike(creatorId, courseStructure.getCourse(), name, isPublic,
                        courseStructure.getCourseVersion()));
        return courseResources;
    }

    private CourseResource setCourseResourceName(List<CourseResource> courseResources, CourseResource courseResource, String name) {
        List<CourseResource> judgeCourseResources = new ArrayList<>();
        courseResources.forEach(resource -> {
            if (resource.getResourceName().equals(name) && !resource.getId().equals(courseResource.getId())) {
                judgeCourseResources.add(resource);
            }
        });
        CourseResource existCourseResource = judgeCourseResources.get(0);
        List<CourseResource> existCourseResources =
                courseResourceRepository.findByParentIdAndResourceStatus(existCourseResource.getId(), ResourceStatus.NORMAL);
        List<CourseResource> courseResourceList =
                existCourseResources.stream().filter(resource -> !resource.getId().equals(courseResource.getId())).collect(toList());
        courseResourceList.sort(Comparator.comparingInt(CourseResource::getVisionNumber));
        List<Integer> nowVisionNumberList = courseResourceList
                .stream().map(CourseResource::getVisionNumber).distinct().collect(Collectors.toList());
        int maxNowVisionNumber = nowVisionNumberList.stream().max(Integer::compareTo).orElse(0);
        courseResource.setVisionNumber(maxNowVisionNumber + 1);
        courseResource.setParentId(existCourseResource.getId());
        courseResource.setResourceName(name + "(" + (maxNowVisionNumber + 1) + ")");
        return courseResource;
    }

    private List<CourseResource> checkNameToPublic(String creatorId, CourseStructure courseStructure, String name, Boolean isPublic) {
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.testPaperSpecification(creatorId, courseStructure.getCourse(), name, isPublic,
                        courseStructure.getCourseVersion()));
        return courseResources;
    }

    public List<TeacherCourseResourceStatisticVo> getPreparationCourseBagTeacherStatistic() {
        List<TeacherCourseResourceStatisticVo> handleTeacherCourseResourceStatisticVoList = new ArrayList<>();
        String querySql = "SELECT a.creator_id AS teacherId, a.creator_name AS teacherName, " +
                " a.resource_type AS resourceTypeId,count( 1 ) AS resourceCount  FROM tb_course_resource a " +
                " INNER JOIN tb_course_structure b ON a.course_structure_id = b.id " +
                " INNER JOIN tb_course_version c on b.course_version_id = c.id " +
                " INNER JOIN tb_course d ON d.id = c.course_id " +
                "WHERE a.resource_status = 0  AND a.source_type = 0 AND b.structure_status = 0  " +
                " and c.course_version_status = 1 AND d.use_state = 1  " +
                "GROUP BY a.creator_id,a.resource_type;";
        Query queryData = entityManager.createNativeQuery(querySql);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TeacherCourseResourceStatisticVo.class));
        List<TeacherCourseResourceStatisticVo> teacherCourseResourceStatisticVoList = queryData.getResultList();
        Map<String, List<TeacherCourseResourceStatisticVo>> teacherCourseResourceStatisticVoMap =
                teacherCourseResourceStatisticVoList.stream()
                        .collect(Collectors.groupingBy(TeacherCourseResourceStatisticVo::getTeacherId));
        teacherCourseResourceStatisticVoMap.forEach((teacherId, mapTeacherCourseResourceStatisticVoList) -> {
            Arrays.stream(ResourceType.values()).forEach(resourceType -> {
                TeacherCourseResourceStatisticVo seekTeacherCourseResourceStatisticVo =
                        mapTeacherCourseResourceStatisticVoList.stream().filter(teacherCourseResourceStatisticVo ->
                                        teacherCourseResourceStatisticVo.getResourceTypeId().equals(resourceType.getIndex()))
                                .findFirst().orElse(null);
                if (ObjectUtils.isNotEmpty(seekTeacherCourseResourceStatisticVo)) {
                    seekTeacherCourseResourceStatisticVo.setResourceTypeName(resourceType.getValue());
                    handleTeacherCourseResourceStatisticVoList.add(seekTeacherCourseResourceStatisticVo);
                } else {
                    TeacherCourseResourceStatisticVo teacherCourseResourceStatisticVo = new TeacherCourseResourceStatisticVo();
                    teacherCourseResourceStatisticVo.setTeacherId(mapTeacherCourseResourceStatisticVoList.get(0).getTeacherId());
                    teacherCourseResourceStatisticVo.setTeacherName(mapTeacherCourseResourceStatisticVoList.get(0).getTeacherName());
                    teacherCourseResourceStatisticVo.setResourceTypeId(resourceType.getIndex());
                    teacherCourseResourceStatisticVo.setResourceTypeName(resourceType.getValue());
                    teacherCourseResourceStatisticVo.setResourceCount(BigInteger.valueOf(0));
                    handleTeacherCourseResourceStatisticVoList.add(teacherCourseResourceStatisticVo);
                }
            });
        });
        return handleTeacherCourseResourceStatisticVoList;
    }

    public List<TeacherCourseResourceReferenceStatisticVo> getPreparationCourseBagTeacherReferenceStatistics() {
        String querySql = "SELECT  a.creator_id AS teacherId,  a.creator_name AS teacherName,  " +
                "  SUM( a.resource_references ) AS individualReference,  " +
                "  SUM( a.resource_other_references ) AS otherReference,  " +
                "  count( CASE WHEN a.resource_references != 0 or a.resource_other_references!=0 THEN 1 END ) AS useResourceCount,  " +
                "  count( 1 ) AS resourceTotalCount FROM tb_course_resource a  " +
                "  INNER JOIN tb_course_structure b ON a.course_structure_id = b.id  " +
                "  INNER JOIN tb_course_version c ON b.course_version_id = c.id  " +
                "  INNER JOIN tb_course d ON d.id = c.course_id  " +
                "WHERE a.resource_status = 0 AND b.structure_status = 0   AND c.course_version_status = 1 AND d.use_state = 1   " +
                "GROUP BY  a.creator_id  HAVING SUM( a.resource_references )+SUM( a.resource_other_references )>0;";
        Query queryData = entityManager.createNativeQuery(querySql);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TeacherCourseResourceReferenceStatisticSqlVo.class));
        List<TeacherCourseResourceReferenceStatisticSqlVo> teacherCourseResourceReferenceStatisticSqlVoList = queryData.getResultList();
        List<TeacherCourseResourceReferenceStatisticVo> teacherCourseResourceReferenceStatisticVoList = new ArrayList<>();
        teacherCourseResourceReferenceStatisticSqlVoList.forEach(teacherCourseResourceReferenceStatisticSqlVo -> {
            TeacherCourseResourceReferenceStatisticVo teacherCourseResourceReferenceStatisticVo = new TeacherCourseResourceReferenceStatisticVo();
            teacherCourseResourceReferenceStatisticVo.setTeacherId(teacherCourseResourceReferenceStatisticSqlVo.getTeacherId());
            teacherCourseResourceReferenceStatisticVo.setTeacherName(teacherCourseResourceReferenceStatisticSqlVo.getTeacherName());
            teacherCourseResourceReferenceStatisticVo.setIndividualReference(
                    teacherCourseResourceReferenceStatisticSqlVo.getIndividualReference().intValue());
            teacherCourseResourceReferenceStatisticVo.setOtherReference(
                    teacherCourseResourceReferenceStatisticSqlVo.getOtherReference().intValue()
            );
            teacherCourseResourceReferenceStatisticVo.setUseResourceCount(
                    teacherCourseResourceReferenceStatisticSqlVo.getUseResourceCount().intValue());
            teacherCourseResourceReferenceStatisticVo.setResourceTotalCount(
                    teacherCourseResourceReferenceStatisticSqlVo.getResourceTotalCount().intValue());
            teacherCourseResourceReferenceStatisticVoList.add(teacherCourseResourceReferenceStatisticVo);
        });
        return teacherCourseResourceReferenceStatisticVoList;
    }

    public void courseResourceReorder(ReorderParams reorderParams) {
        Date now = new Date();
        // 拿到需要移动的题和目标位置之前的题的ID
        String needToBeMovedResourceId = reorderParams.getNeedToBeMovedResourceId();
        Optional<CourseResource> needToBeMovedResource = courseResourceRepository.findById(needToBeMovedResourceId);
        CourseResource currentResource = needToBeMovedResource.get();
        Integer currentResourceSort = currentResource.getSort();
        // 判断之前位置的ID是否为空
        if (ObjectUtils.isEmpty(reorderParams.getTargetLocationBeforeResourceId())) {
            // 为空置顶
            CourseResource courseResource = courseResourceRepository.findTop1ByCreatorIdAndCourseStructureIdAndResourceStatusOrderBySortDesc(
                    reorderParams.getTeacherId(),
                    reorderParams.getCourseStructureId(),
                    ResourceStatus.NORMAL
            );
            courseResourceRepository.forwardByCreatorIdAndCourseIdWithIndexBetween(
                    reorderParams.getTeacherId(), reorderParams.getCourseStructureId(),
                    currentResourceSort, courseResource.getSort() + 1
            );
            currentResource.setSort(courseResource.getSort());
        } else {
            // 不为空
            // 拿到目标题和目标前一个题的索引，判断是前移还是后移
            CourseResource targetLocationBeforeQuestion = courseResourceRepository
                    .findById(reorderParams.getTargetLocationBeforeResourceId())
                    .get();
            Integer targetLocationBeforeSort = targetLocationBeforeQuestion.getSort();
            int directionOfMovement = targetLocationBeforeSort - currentResourceSort;
            if (directionOfMovement < 0) {
                // 后移一位
                if (directionOfMovement == -1) {
                    int tmpLocation = targetLocationBeforeSort;
                    targetLocationBeforeQuestion.setSort(currentResourceSort);
                    courseResourceRepository.save(targetLocationBeforeQuestion);
                    currentResource.setSort(tmpLocation);
                } else {
                    // 后移
                    courseResourceRepository.backwardByCreatorIdAndCourseIdWithIndexBetween(
                            reorderParams.getTeacherId(), reorderParams.getCourseStructureId(),
                            targetLocationBeforeSort - 1, currentResourceSort);
                    currentResource.setSort(targetLocationBeforeSort);
                }
            } else {
                // 前移一位
                if (directionOfMovement == Constant.TWO) {
                    CourseResource nextResource = courseResourceRepository
                            .findById(reorderParams.getTargetLocationAfterResourceId())
                            .get();
                    int tmpLocation = nextResource.getSort();
                    nextResource.setSort(currentResourceSort);
                    courseResourceRepository.save(nextResource);
                    currentResource.setSort(tmpLocation);
                } else {
                    // 前移
                    CourseResource nextResource = courseResourceRepository
                            .findById(reorderParams.getTargetLocationAfterResourceId())
                            .get();
                    int targetLocationAfterSort = nextResource.getSort();
                    courseResourceRepository.forwardByCreatorIdAndCourseIdWithIndexBetween(
                            reorderParams.getTeacherId(), reorderParams.getCourseStructureId(),
                            currentResourceSort, targetLocationAfterSort + 1
                    );
                    currentResource.setSort(targetLocationAfterSort);
                }
            }
        }
        currentResource.setModifyTime(now);
        courseResourceRepository.save(currentResource);
    }

    private Boolean isInteractiveClassroomNotAllowedFileType(String fileType) {
        List<String> notAllowedTypeList = Arrays.asList(INTERACTIVE_CLASSROOM_NOT_ALLOWED_PREPARATION_COURSE_BAG_FILE_TYPE.split(","));
        long filterCount = notAllowedTypeList.stream().filter(s -> s.equals(fileType.toLowerCase())).count();
        return filterCount > 0;
    }
}

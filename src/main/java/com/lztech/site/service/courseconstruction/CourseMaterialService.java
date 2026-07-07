package com.lztech.site.service.courseconstruction;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.CourseContentTypeEnum;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.persistence.repositories.course.CourseIntroductionRepository;
import com.lztech.persistence.repositories.course.CourseMaterialRepository;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.TeachingMaterialFileRepository;
import com.lztech.persistence.repositories.coursecategory.CourseCategoryRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.courseconstruction.CourseTeachingMaterialFileInfo;
import com.lztech.site.viewmodel.coursemanagement.CourseIntroductionResource;
import com.lztech.site.viewmodel.coursemanagement.CourseMaterialResource;
import com.lztech.site.viewmodel.coursemanagement.MaterialParam;
import com.lztech.site.viewmodel.coursemanagement.TeachingMaterialFileVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.*;

/**
 * 课程简介
 */
@Service
public class CourseMaterialService {

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseMaterialService.class);
    private Integer coursePresentation = 0;
    private Integer courseOutline = 1;
    private Integer assessmentMethod = 2;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseIntroductionRepository courseIntroductionRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private TeachingMaterialFileRepository teachingMaterialFileRepository;

    /**
     * 修改课程简介相关信息
     *
     * @param courseId
     * @param param
     * @return
     */
    @Transactional
    public Result updateCourseMaterial(String courseId, MaterialParam param) {
        Date now = new Date();
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(param.getVersionId(), CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        if (!assessmentMethod.equals(param.getMaterialType())
                && !course.getCourseName().equals(param.getCourseName())) {
            boolean existSameNameCourse = courseService.checkExistSameNameCourse(param.getCourseName(), courseId);
            if (existSameNameCourse) {
                return Result.error("课程名称已存在");
            }
            course.setCourseName(param.getCourseName());
        }
        boolean flag = false;
        CourseMaterial courseMaterial = courseMaterialRepository.findByCourseIdAndVersionId(courseId, param.getVersionId());
        if (ObjectUtils.isEmpty(courseMaterial)) {
            courseMaterial = new CourseMaterial();
            courseMaterial.setCreateTime(now);
            courseMaterial.setCreatorId(param.getUserId());
            courseMaterial.setCreatorName(param.getUserName());
            courseMaterial.setCourseVersion(courseVersion);
            courseMaterial.setCourse(course);
            flag = true;
        }
        if (coursePresentation.equals(param.getMaterialType())) {
            saveCourseBaseInfo(course, courseMaterial, param);
            saveCourseIntroduction(course, param, now, courseVersion);
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_BASE_INFO, true, param.getUserId(),
                    param.getUserName());
        } else if (assessmentMethod.equals(param.getMaterialType())) {
            if (StringUtils.isNotBlank(param.getMaterialContent())) {
                courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.ASSESSMENT_SCHEME, true, param.getUserId(),
                        param.getUserName());
            }
            courseMaterial.setAssessmentMethod(param.getMaterialContent());
        } else {
            return Result.success();
        }
        courseMaterial.setModifyTime(now);
        courseMaterial.setModifierId(param.getUserId());
        courseMaterial.setModifierName(param.getUserName());
        CourseMaterial courseMaterialSaved = courseMaterialRepository.saveAndFlush(courseMaterial);
        List<TeachingMaterialFileVo> teachingMaterialFileVoList = param.getTeachingMaterialFileVoList();
        if (CollectionUtils.isNotEmpty(teachingMaterialFileVoList)) {
            List<TeachingMaterialFileVo> deleteTeachingMaterialFileVoList = teachingMaterialFileVoList.stream().filter(teachingMaterialFileVo ->
                    teachingMaterialFileVo.getDataOperationType().equals(DATA_OPERATION_TYPE_DELETE)).collect(Collectors.toList());
            List<TeachingMaterialFileVo> addTeachingMaterialFileVoList = teachingMaterialFileVoList.stream().filter(teachingMaterialFileVo ->
                            teachingMaterialFileVo.getDataOperationType().equals(DATA_OPERATION_TYPE_ADD))
                    .collect(Collectors.toList());
            List<TeachingMaterialFileVo> updateTeachingMaterialFileVoList = teachingMaterialFileVoList.stream().filter(teachingMaterialFileVo ->
                            teachingMaterialFileVo.getDataOperationType().equals(DATA_OPERATION_TYPE_UPDATE))
                    .collect(Collectors.toList());

            if (!deleteTeachingMaterialFileVoList.isEmpty()) {
                teachingMaterialFileRepository.deleteTeachingMaterialFileByIdList(deleteTeachingMaterialFileVoList.stream()
                        .map(TeachingMaterialFileVo::getFileId).collect(Collectors.toList()));
            }

            List<TeachingMaterialFile> teachingMaterialFileList = new ArrayList<>();
            if (!addTeachingMaterialFileVoList.isEmpty()) {
                addTeachingMaterialFileVoList.forEach(materialFileVo -> {
                    buildTeachingMaterialFile(param, materialFileVo, courseMaterialSaved, now, teachingMaterialFileList, null);
                });
            }
            if (!updateTeachingMaterialFileVoList.isEmpty()) {
                List<TeachingMaterialFile> files = teachingMaterialFileRepository.findAllByIdIn(
                        updateTeachingMaterialFileVoList.stream().map(TeachingMaterialFileVo::getFileId).collect(Collectors.toList()));
                updateTeachingMaterialFileVoList.forEach(materialFileVo -> {
                    TeachingMaterialFile filterFile = files.stream().filter(teachingMaterialFileVo ->
                            teachingMaterialFileVo.getId().equals(materialFileVo.getFileId())).findFirst().orElse(null);
                    if (!ObjectUtils.isEmpty(filterFile)) {
                        buildTeachingMaterialFile(param, materialFileVo, courseMaterialSaved, now, teachingMaterialFileList, filterFile);
                    }
                });
            }
            teachingMaterialFileRepository.saveAll(teachingMaterialFileList);
        }
        courseRepository.saveAndFlush(course);
        return Result.success();
    }

    private void buildTeachingMaterialFile(MaterialParam param,
                                           TeachingMaterialFileVo materialFileVo,
                                           CourseMaterial courseMaterialSaved,
                                           Date now,
                                           List<TeachingMaterialFile> teachingMaterialFileList,
                                           TeachingMaterialFile materialFile) {
        if (ObjectUtils.isEmpty(materialFile)) {
            materialFile = new TeachingMaterialFile();
        }
        materialFile.setCourseMaterial(courseMaterialSaved);
        materialFile.setFilePath(materialFileVo.getFilePath());
        materialFile.setFileType(materialFileVo.getFileType());
        materialFile.setFileSize(Long.parseLong(materialFileVo.getFileSize()));
        materialFile.setFileRealName(materialFileVo.getFileRealName());
        materialFile.setFileDisplayName(materialFileVo.getFileDisplayName());
        materialFile.setInnerUrl(materialFileVo.getInnerUrl());
        materialFile.setOuterUrl(materialFileVo.getOuterUrl());
        materialFile.setCreateTime(now);
        materialFile.setCreatorId(param.getUserId());
        materialFile.setCreatorName(param.getUserName());
        materialFile.setCoverFilePath(materialFileVo.getCoverFilePath());
        materialFile.setCoverFileType(materialFileVo.getCoverFileType());
        materialFile.setCoverFileSize(StringUtils.isAnyBlank(materialFileVo.getCoverFileSize())
                ? null : Long.parseLong(materialFileVo.getCoverFileSize()));
        materialFile.setCoverFileRealName(materialFileVo.getCoverFileRealName());
        materialFile.setCoverFileDisplayName(materialFileVo.getCoverFileDisplayName());
        materialFile.setCoverInnerUrl(materialFileVo.getCoverInnerUrl());
        materialFile.setCoverOuterUrl(materialFileVo.getCoverOuterUrl());
        teachingMaterialFileList.add(materialFile);
    }

    private void saveCourseIntroduction(Course course, MaterialParam param, Date now, CourseVersion courseVersion) {
        courseIntroductionRepository.deleteByCourse(course);
        if (CollectionUtils.isNotEmpty(param.getCourseIntroductionList())) {
            List<CourseIntroduction> courseIntroductionList = new ArrayList<>();
            param.getCourseIntroductionList().forEach(i -> {
                CourseIntroduction courseIntroduction = new CourseIntroduction();
                courseIntroduction.setClassificationName(i.getClassificationName());
                courseIntroduction.setClassificationContent(i.getClassificationContent());
                courseIntroduction.setCourse(course);
                courseIntroduction.setModifyTime(now);
                courseIntroduction.setModifierId(param.getUserId());
                courseIntroduction.setModifierName(param.getUserName());
                courseIntroduction.setCreateTime(now);
                courseIntroduction.setCreatorId(param.getUserId());
                courseIntroduction.setCreatorName(param.getUserName());
                courseIntroduction.setCourseVersion(courseVersion);
                courseIntroductionList.add(courseIntroduction);
            });
            courseIntroductionRepository.saveAll(courseIntroductionList);
            if (CollectionUtils.isNotEmpty(courseIntroductionList)) {
                courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_INTRODUCTION, true, param.getUserId(),
                        param.getUserName());
            }
        } else {
            courseCompletionService.saveCourseCompletion(course, CourseContentTypeEnum.COURSE_INTRODUCTION, false, param.getUserId(),
                    param.getUserName());
        }


    }

    private void saveCourseBaseInfo(Course course,
                                    CourseMaterial courseMaterial,
                                    MaterialParam param) {
        CourseCategory courseCategory = courseCategoryRepository.findById(Integer.valueOf(param.getCourseCategoryId()));
        courseMaterial.setCourseCategory(courseCategory);
        courseMaterial.setTeachingUserType(param.getTeachingUserType());
        courseMaterial.setClassHours(Objects.isNull(param.getClassHours()) ? null : param.getClassHours().doubleValue());
        courseMaterial.setCourseContent(param.getCourseContent());
        courseMaterial.setTeachingMaterial(param.getTeachingMaterial());
        courseMaterial.setReferenceMaterials(param.getReferenceMaterials());
    }

    public Result getCourseIdMaterialModuleInfo(String courseId, Integer materialModuleType, String versionId) {
        Course course = courseRepository.findByIdAndUseStateIsTrue(courseId).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseMaterialResource courseMaterialResource = new CourseMaterialResource();
        courseMaterialResource.setCourseName(course.getCourseName());
        if (course.getCollege() != null) {
            courseMaterialResource.setCollegeId(course.getCollege().getId());
            courseMaterialResource.setCollegeName(course.getCollegeName());
        }
        CourseMaterial courseMaterial = courseMaterialRepository.findByCourseIdAndVersionId(courseId, versionId);
        if (Objects.nonNull(courseMaterial)) {
            if (assessmentMethod.equals(materialModuleType)) {
                courseMaterialResource.setAssessmentMethod(courseMaterial.getAssessmentMethod());
            } else {
                if (Objects.isNull(courseMaterial.getCourseCategory())) {
                    courseMaterialResource.setCourseCategoryName(null);
                    courseMaterialResource.setCourseCategoryId(null);
                } else {
                    courseMaterialResource.setCourseCategoryName(
                            courseMaterial.getCourseCategory().getCourseCategoryName());
                    courseMaterialResource.setCourseCategoryId(String.valueOf(courseMaterial.getCourseCategory().getId()));

                }
                courseMaterialResource.setTeachingUserType(courseMaterial.getTeachingUserType());
                courseMaterialResource.setClassHours(Objects.isNull(courseMaterial.getClassHours()) ? null :
                        BigDecimal.valueOf(courseMaterial.getClassHours()));
                courseMaterialResource.setCourseContent(courseMaterial.getCourseContent());
                courseMaterialResource.setTeachingMaterial(courseMaterial.getTeachingMaterial());
                courseMaterialResource.setReferenceMaterials(courseMaterial.getReferenceMaterials());
            }

            if (!courseMaterial.getTeachingMaterialFileList().isEmpty()) {
                List<TeachingMaterialFileVo> teachingMaterialFileVoList = new ArrayList<>();
                courseMaterial.getTeachingMaterialFileList().stream().sorted(Comparator.comparing(TeachingMaterialFile::getCreateTime).reversed())
                        .forEach(materialFile -> {
                            buildTeachingMaterialFileVo(materialFile, teachingMaterialFileVoList);
                        });
                courseMaterialResource.setTeachingMaterialFileVoList(teachingMaterialFileVoList);
            }
        }
        if (!assessmentMethod.equals(materialModuleType)) {
            List<CourseIntroduction> courseIntroductionList = courseIntroductionRepository.findByCourseIdAndCourseVersionId(courseId, versionId);
            List<CourseIntroductionResource> resourceList = new ArrayList<>();
            courseIntroductionList.forEach(item -> {
                CourseIntroductionResource courseIntroductionResource = new CourseIntroductionResource();
                courseIntroductionResource.setClassificationName(item.getClassificationName());
                courseIntroductionResource.setClassificationContent(item.getClassificationContent());
                resourceList.add(courseIntroductionResource);
            });
            courseMaterialResource.setCourseIntroductionList(resourceList);
        }
        CourseCover courseCover = course.getCourseCover();
        if (ObjectUtils.isNotEmpty(courseCover)) {
            courseMaterialResource.setCoverInnerIp(courseCover.getInnerIp());
            courseMaterialResource.setCoverOuterIp(courseCover.getOuterIp());
            courseMaterialResource.setCoverFilepath(courseCover.getFilePath());
        }

        return Result.success().setData(courseMaterialResource);
    }

    private static void buildTeachingMaterialFileVo(TeachingMaterialFile materialFile, List<TeachingMaterialFileVo> teachingMaterialFileVoList) {
        TeachingMaterialFileVo materialFileVo = new TeachingMaterialFileVo();
        materialFileVo.setCreatorId(materialFile.getCreatorId());
        materialFileVo.setCreatorName(materialFile.getCreatorName());
        materialFileVo.setFileId(materialFile.getId());
        materialFileVo.setCourseMaterialId(materialFile.getCourseMaterial().getId());
        materialFileVo.setFilePath(materialFile.getFilePath());
        materialFileVo.setFileType(materialFile.getFileType());
        materialFileVo.setFileSize(materialFile.getFileSize() + "");
        materialFileVo.setFileRealName(materialFile.getFileRealName());
        materialFileVo.setFileDisplayName(materialFile.getFileDisplayName());
        materialFileVo.setInnerUrl(materialFile.getInnerUrl());
        materialFileVo.setOuterUrl(materialFile.getOuterUrl());
        materialFileVo.setCreateTime(DateUtils.formatDate(DateUtils.DATE_TIME, materialFile.getCreateTime()));
        materialFileVo.setCoverFilePath(StringUtils.isBlank(materialFile.getCoverFilePath())
                ?"": materialFile.getCoverFilePath());
        materialFileVo.setCoverFileType(StringUtils.isBlank(materialFile.getCoverFileType())
                ?"": materialFile.getCoverFileType());
        materialFileVo.setCoverFileSize(ObjectUtils.isEmpty(materialFile.getCoverFileSize())
                ?"": materialFile.getCoverFileSize() + "");
        materialFileVo.setCoverFileRealName(StringUtils.isBlank(materialFile.getCoverFileRealName())
                ?"": materialFile.getCoverFileRealName());
        materialFileVo.setCoverFileDisplayName(StringUtils.isBlank(materialFile.getCoverFileDisplayName())
                ?"": materialFile.getCoverFileDisplayName());
        materialFileVo.setCoverInnerUrl(StringUtils.isBlank(materialFile.getCoverInnerUrl())
                ?"": materialFile.getCoverInnerUrl());
        materialFileVo.setCoverOuterUrl(StringUtils.isBlank(materialFile.getCoverOuterUrl())
                ?"": materialFile.getCoverOuterUrl());
        teachingMaterialFileVoList.add(materialFileVo);
    }

    public Result uploadTeachingMaterial(CourseTeachingMaterialFileInfo fileInfo) {
        Date now = new Date();
        Course course = courseRepository.findByIdAndUseStateIsTrue(fileInfo.getCourseId()).orElse(null);
        if (ObjectUtils.isEmpty(course)) {
            return Result.error("未找到课程信息");
        }
        CourseVersion courseVersion = courseVersionRepository.findByIdAndCourseVersionStatus(fileInfo.getVersionId(), CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return Result.error("无法保存，当前版本已归档，请刷新页面");
        }
        CourseMaterial courseMaterial = courseMaterialRepository.findByCourseIdAndVersionId(fileInfo.getCourseId(), fileInfo.getVersionId());
        if (ObjectUtils.isEmpty(courseMaterial)) {
            courseMaterial = new CourseMaterial();
            courseMaterial.setCreateTime(now);
            courseMaterial.setCreatorId(fileInfo.getOperatorId());
            courseMaterial.setCreatorName(fileInfo.getOperatorName());
            courseMaterial.setCourseVersion(courseVersion);
            courseMaterial.setCourse(course);
            courseMaterial.setModifyTime(now);
            courseMaterial.setModifierId(fileInfo.getOperatorId());
            courseMaterial.setModifierName(fileInfo.getOperatorName());
            courseMaterial = courseMaterialRepository.saveAndFlush(courseMaterial);
        }
        TeachingMaterialFile materialFile = new TeachingMaterialFile();
        materialFile.setCourseMaterial(courseMaterial);
        materialFile.setFilePath(fileInfo.getFilePath());
        materialFile.setFileType(fileInfo.getFileType());
        materialFile.setFileSize(Long.parseLong(fileInfo.getFileSize()));
        materialFile.setFileRealName(fileInfo.getFileRealName());
        materialFile.setFileDisplayName(fileInfo.getFileDisplayName());
        materialFile.setInnerUrl(fileInfo.getInnerUrl());
        materialFile.setOuterUrl(fileInfo.getOuterUrl());
        materialFile.setCreateTime(now);
        materialFile.setCreatorId(fileInfo.getOperatorId());
        materialFile.setCreatorName(fileInfo.getOperatorName());
        teachingMaterialFileRepository.save(materialFile);

        return Result.success();
    }
}

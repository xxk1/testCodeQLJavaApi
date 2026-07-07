package com.lztech.site.service.coursetheme;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.persistence.repositories.course.CourseResourceRepository;
import com.lztech.persistence.repositories.course.CourseStructureRepository;
import com.lztech.persistence.repositories.coursetheme.CourseThemeDetailRepository;
import com.lztech.persistence.repositories.coursetheme.CourseThemeRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.course.CourseService;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.viewmodel.coursetheme.CourseThemeDetailResource;
import com.lztech.site.viewmodel.coursetheme.CourseThemeResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseThemeService {

    @Autowired
    private CourseThemeRepository courseThemeRepository;
    @Autowired
    private CourseThemeDetailRepository courseThemeDetailRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;

    @Transactional
    public Result insertOrUpdateCourseTheme(CourseThemeResource courseThemeResource) {
        boolean existSameThemeContent = checkSameThemeContent(courseThemeResource.getThemeDetailList());
        if (existSameThemeContent) {
            return Result.error("主题不能相同，请修改！");
        }
        CourseStructure courseStructure = courseStructureRepository
                .findByIdAndStructureStatus(courseThemeResource.getCourseStructureId(), StructureStatus.NORMAL);
        if (ObjectUtils.isEmpty(courseStructure)) {
            return Result.error("保存失败，课程结构已被删除");
        }
        CourseVersion courseVersion = courseStructure.getCourseVersion();
        if (ObjectUtils.isEmpty(courseVersion) || !CourseVersionStatus.IN_USE.equals(courseVersion.getCourseVersionStatus())) {
            return Result.error("保存失败，当前版本已归档，请刷新页面");
        }
        Date now = new Date();
        CourseTheme courseTheme = null;
        if (StringUtils.isNotBlank(courseThemeResource.getId())) {
            courseTheme = courseThemeRepository.findById(courseThemeResource.getId()).orElse(null);
            if (Objects.isNull(courseTheme) || ResourceStatus.DELETE.equals(courseTheme.getThemeStatus())) {
                return Result.error("主题不存在");
            }
            courseThemeDetailRepository.deleteThemeDetailByThemeId(courseTheme.getId());
        } else {
            courseTheme = new CourseTheme();
            courseTheme.setCreatorId(courseThemeResource.getOperatorId());
            courseTheme.setCreatorName(courseThemeResource.getOperatorName());
            courseTheme.setCreateTime(now);
        }
        courseTheme.setModifierId(courseThemeResource.getOperatorId());
        courseTheme.setModifierName(courseThemeResource.getOperatorName());
        courseTheme.setModifyTime(now);
        String themeName = courseThemeResource.getThemeDetailList()
                .stream().min(Comparator.comparing(CourseThemeDetailResource::getShowOrder))
                .orElse(new CourseThemeDetailResource())
                .getContent();
        courseTheme.setThemeStatus(ResourceStatus.NORMAL);
        courseTheme.setThemeName(themeName);
        courseTheme.setThemeDesc(themeName);
        courseTheme.setThemeDetailNum(courseThemeResource.getThemeDetailList().size());
        courseTheme.setCourseThemeDetailList(createCourseThemeDetailList(courseThemeResource, courseTheme, now));
        CourseTheme savedCourseTheme = courseThemeRepository.save(courseTheme);
        insertOrUpdateCourseResource(savedCourseTheme, courseThemeResource, now, courseStructure);
        if (StringUtils.isBlank(courseThemeResource.getId())) {
            courseCompletionService.saveCourseCompletion(courseStructure.getCourse(), CourseContentTypeEnum.PREPARATORY_ACTIVITIES, true,
                    courseThemeResource.getOperatorId(), courseThemeResource.getOperatorName());
        }
        return Result.success();
    }

    private void insertOrUpdateCourseResource(CourseTheme courseTheme,
                                              CourseThemeResource courseThemeResource,
                                              Date now,
                                              CourseStructure courseStructure) {
        CourseResource courseResource =
                courseResourceRepository.findByResourceDetailIdAndResourceStatusAndCourseStructure
                        (courseTheme.getId(), ResourceStatus.NORMAL, courseStructure);
        if (Objects.isNull(courseResource)) {
            courseResource = new CourseResource();
            courseResource.setResourceType(ResourceType.THEME);
            courseResource.setCreateTime(now);
            courseResource.setCreatorId(courseThemeResource.getOperatorId());
            courseResource.setCreatorName(courseThemeResource.getOperatorName());
            courseResource.setResourceDetailId(courseTheme.getId());
            courseResource.setResourceReferences(0);
            courseResource.setResourceOtherReferences(0);
            courseResource.setCourseStructure(courseStructure);
            courseResource.setSourceType(ResourceSourceType.USER_ADDED);
            if ((ObjectUtils.isNotEmpty(courseThemeResource.getSource()) && 1 == courseThemeResource.getSource())
                    || courseThemeResource.isWhetherPublic()) {
                CourseResource resource = courseResourceRepository.findTop1ByCourseStructureIdAndIsPublicAndResourceStatusOrderByAuxiliarySortDesc(
                        courseStructure.getId(), true, ResourceStatus.NORMAL);
                if (ObjectUtils.isNotEmpty(resource)) {
                    courseResource.setAuxiliarySort(resource.getAuxiliarySort() + 1);
                } else {
                    courseResource.setAuxiliarySort(0);
                }
            }
            CourseResource resource = courseResourceRepository.findTop1ByCreatorIdAndCourseStructureIdAndResourceStatusOrderBySortDesc(
                    courseThemeResource.getOperatorId(),
                    courseStructure.getId(),
                    ResourceStatus.NORMAL);
            if (ObjectUtils.isNotEmpty(resource)) {
                courseResource.setSort(resource.getSort() + 1);
            } else {
                courseResource.setSort(0);
            }
        }
        courseResource.setResourceName(courseTheme.getThemeName());
        courseResource.setIsPublic(courseThemeResource.isWhetherPublic());
        courseResource.setResourceContentNum(courseTheme.getCourseThemeDetailList().size());
        courseResource.setModifierId(courseThemeResource.getOperatorId());
        courseResource.setResourceStatus(courseTheme.getThemeStatus());
        courseResource.setModifyTime(now);
        courseResource.setModifierName(courseThemeResource.getOperatorName());
        courseResourceRepository.save(courseResource);
        if (dataVisualEnable) {
            courseService.sendCourseResourceEvent(courseResource);
        }

    }

    private List<CourseThemeDetail> createCourseThemeDetailList(CourseThemeResource courseThemeResource,
                                                                CourseTheme courseTheme,
                                                                Date now) {

        List<CourseThemeDetail> courseThemeDetailList = new ArrayList<>();
        courseThemeResource.getThemeDetailList().forEach(detail -> {
            CourseThemeDetail courseThemeDetail = new CourseThemeDetail();
            courseThemeDetail.setCourseTheme(courseTheme);
            courseThemeDetail.setThemeDetailContent(detail.getContent());
            courseThemeDetail.setShowOrder(detail.getShowOrder());
            courseThemeDetail.setCreatorId(courseThemeResource.getOperatorId());
            courseThemeDetail.setCreatorName(courseThemeResource.getOperatorName());
            courseThemeDetail.setCreateTime(now);
            courseThemeDetailList.add(courseThemeDetail);
        });
        return courseThemeDetailList;
    }

    private boolean checkSameThemeContent(List<CourseThemeDetailResource> themeDetailList) {
        List<String> contentList = themeDetailList.stream().map(detail -> detail.getContent().trim()).collect(Collectors.toList());
        return contentList.size() != themeDetailList.size();
    }

    public CourseTheme findByIdAndStatus(String id, ResourceStatus status) {
        return courseThemeRepository.findByIdAndThemeStatus(id, status);
    }

    public CourseThemeResource transformCourseTheme(CourseTheme courseTheme) {
        CourseThemeResource courseThemeResource = new CourseThemeResource();
        List<CourseResource> courseResources =
                courseResourceRepository.findByResourceDetailIdAndResourceStatus(courseTheme.getId(), ResourceStatus.NORMAL);
        courseThemeResource.setId(courseTheme.getId());
        courseThemeResource.setOperatorId(courseTheme.getModifierId());
        courseThemeResource.setOperatorName(courseTheme.getModifierName());
        courseThemeResource.setThemeName(courseTheme.getThemeName());
        if (CollectionUtils.isNotEmpty(courseResources)) {
            courseThemeResource.setWhetherPublic(courseResources.get(0).getIsPublic());
            courseThemeResource.setCourseStructureId(courseResources.get(0).getCourseStructure().getId());
        }
        courseThemeResource.setThemeDetailList(buildCourseThemeDetailList(courseTheme.getCourseThemeDetailList()));
        return courseThemeResource;
    }

    private List<CourseThemeDetailResource> buildCourseThemeDetailList(List<CourseThemeDetail> courseThemeDetailList) {
        List<CourseThemeDetailResource> courseThemeDetailResourceList = new ArrayList<>();
        courseThemeDetailList.forEach(courseThemeDetail -> {
            CourseThemeDetailResource courseThemeDetailResource = new CourseThemeDetailResource();
            courseThemeDetailResource.setId(courseThemeDetail.getId());
            courseThemeDetailResource.setContent(courseThemeDetail.getThemeDetailContent());
            courseThemeDetailResource.setShowOrder(courseThemeDetail.getShowOrder());
            courseThemeDetailResourceList.add(courseThemeDetailResource);
        });
        return courseThemeDetailResourceList
                .stream()
                .sorted(Comparator.comparing(CourseThemeDetailResource::getShowOrder))
                .collect(Collectors.toList());

    }

    public List<CourseThemeResource> getTeacherCourseTheme(String courseId, String teacherId) {
        List<CourseTheme> courseThemeList = courseThemeRepository.findByTeacherIdAndCourseId(teacherId, courseId);
        List<CourseThemeResource> courseThemeResourceList = new ArrayList<>();
        courseThemeList.forEach(courseTheme -> {
            courseThemeResourceList.add(transformCourseTheme(courseTheme));
        });
        return courseThemeResourceList;
    }
}

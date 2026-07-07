package com.lztech.site.service.teachingpackage;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.domain.model.courseteachingpackage.CourseTeachingPackage;
import com.lztech.domain.model.courseteachingpackage.CourseTeachingPackageResource;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.CourseResourceFileRepository;
import com.lztech.persistence.repositories.course.CourseResourceRepository;
import com.lztech.persistence.repositories.course.CourseStructureRepository;
import com.lztech.persistence.repositories.coursetheme.CourseThemeDetailRepository;
import com.lztech.persistence.repositories.coursetheme.CourseThemeRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.persistence.repositories.teachingpackage.TeachingPackageRepository;
import com.lztech.persistence.repositories.teachingpackage.TeachingPackageResourceRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.courseconstruction.CourseStructureService;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceVo;
import com.lztech.site.viewmodel.coursemanagement.CourseStructureVo;
import com.lztech.site.viewmodel.teachingpackage.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class TeachingPackageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingPackageService.class);

    @Autowired
    private TeachingPackageRepository teachingPackageRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseStructureService courseStructureService;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private TeachingPackageResourceRepository teachingPackageResourceRepository;
    @Autowired
    private CourseThemeRepository courseThemeRepository;
    @Autowired
    private CourseThemeDetailRepository courseThemeDetailRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;

    public Result addTeachingPackage(CourseTeachingPackageVo teachingPackageVo) {
        List<CourseStructure> courseStructuresOld = courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus(
                teachingPackageVo.getCourseId(), null, StructureStatus.NORMAL);
        if (CollectionUtils.isNotEmpty(courseStructuresOld)) {
            courseStructuresOld.forEach(courseStructure -> {
                courseStructure.setStructureStatus(StructureStatus.DELETE);
                courseStructureRepository.saveAndFlush(courseStructure);
            });
        }
        List<CourseStructure> courseStructures =
                courseStructureRepository.findByIdInAndStructureStatus(teachingPackageVo.getCourseStructureIds(), StructureStatus.NORMAL);
        if (CollectionUtils.isEmpty(courseStructures)) {
            return Result.error("未找到资源信息");
        }
        CourseTeachingPackage courseTeachingPackage = new CourseTeachingPackage();
        BeanUtils.copyProperties(teachingPackageVo, courseTeachingPackage);
        List<CourseTeachingPackage> courseTeachingPackages =
                teachingPackageRepository.findByCourseId(teachingPackageVo.getCourseId());
        if (CollectionUtils.isNotEmpty(courseTeachingPackages)) {
            CourseTeachingPackage maxNowVisionNumber = courseTeachingPackages.stream()
                    .max(Comparator.comparing(CourseTeachingPackage::getVisionNumber)).orElse(null);
            int versionNum = maxNowVisionNumber.getVisionNumber();
            courseTeachingPackage.setVisionNumber(++versionNum);
        } else {
            courseTeachingPackage.setVisionNumber(1);
        }
        Date now = new Date();
        courseTeachingPackage.setCreateTime(now);
        courseTeachingPackage.setModifyTime(now);
        courseTeachingPackage.setQuoteNum(0);
        courseTeachingPackage.setCreatorId(teachingPackageVo.getUserId());
        courseTeachingPackage.setCreatorName(teachingPackageVo.getUserName());
        courseTeachingPackage.setModifierId(teachingPackageVo.getUserId());
        courseTeachingPackage.setModifierName(teachingPackageVo.getUserName());
        if (CollectionUtils.isNotEmpty(teachingPackageVo.getLabelIds()) && CollectionUtils.isNotEmpty(teachingPackageVo.getLabelNames())) {
            courseTeachingPackage.setLabelId(teachingPackageVo.getLabelIds().stream().collect(Collectors.joining(",")));
            courseTeachingPackage.setLabelName(teachingPackageVo.getLabelNames().stream().collect(Collectors.joining(",")));
        }
        courseTeachingPackage.setVisionTime(now);
        List<CourseStructure> newCourseStructure = new ArrayList<>();
        List<CourseResource> newCourseResource = new ArrayList<>();
        List<CourseResource> courseResourceList = courseResourceRepository
                .findByIdInAndResourceStatus(teachingPackageVo.getResourceIds(), ResourceStatus.NORMAL);
        List<CourseStructure> courseStructuresList = courseStructureRepository
                .findByIdInAndStructureStatus(teachingPackageVo.getCourseStructureIds(), StructureStatus.NORMAL);
        if (CollectionUtils.isNotEmpty(teachingPackageVo.getCourseStructureIds())) {
            List<CourseStructure> rootStructureList = courseStructuresList.stream().filter(courseStructure ->
                    StringUtils.isBlank(courseStructure.getParentId())).collect(Collectors.toList());
            buildCourseTeachingPackage(rootStructureList, teachingPackageVo, newCourseStructure, courseStructuresList, courseResourceList,
                    newCourseResource);
        }
        List<String> resourceIds = new ArrayList<>();
        newCourseResource.forEach(courseResource -> {
            CourseResource saveResource = courseResourceRepository.saveAndFlush(courseResource);
            resourceIds.add(saveResource.getId());
        });
        courseTeachingPackage.setCourseStructureId(newCourseStructure.stream().map(CourseStructure::getId).collect(Collectors.joining(",")));
        courseTeachingPackage.setCourseResourceFileId(teachingPackageVo.getResourceFileIds().stream().collect(Collectors.joining(",")));
        courseTeachingPackage.setTeachingPackageFileId(teachingPackageVo.getTeachingPackageFileId());
        courseTeachingPackage.setCourseResourceId(resourceIds.stream().map(String::toString).collect(Collectors.joining(",")));
        teachingPackageRepository.saveAndFlush(courseTeachingPackage);
        return Result.success();
    }

    public void buildCourseTeachingPackage(List<CourseStructure> rootStructureList, CourseTeachingPackageVo teachingPackageVo,
                                           List<CourseStructure> newCourseStructure, List<CourseStructure> courseStructuresList,
                                           List<CourseResource> courseResourceList, List<CourseResource> newCourseResource) {
        rootStructureList.forEach(courseStructure -> {
            String newStructureId = UUID.randomUUID().toString();
            CourseStructure root = new CourseStructure();
            copyCourseStructure(courseStructure, root, teachingPackageVo, newStructureId, null);
            CourseStructure result = courseStructureRepository.saveAndFlush(root);
            newCourseStructure.add(result);
            newStructureId = result.getId();
            List<CourseStructure> leafStructureList = courseStructuresList.stream().filter(courseStructureLeaf ->
                    StringUtils.isNotBlank(courseStructureLeaf.getParentId()) &&
                            courseStructureLeaf.getParentId().equals(courseStructure.getId())).collect(Collectors.toList());
            String finalNewStructureId = newStructureId;
            leafStructureList.forEach(courseStructureLeaf -> {
                String parentReaourceId = UUID.randomUUID().toString();
                CourseStructure leafStructure = new CourseStructure();
                copyCourseStructure(courseStructureLeaf, leafStructure, teachingPackageVo, parentReaourceId, finalNewStructureId);
                CourseStructure leafResult = courseStructureRepository.saveAndFlush(leafStructure);
                newCourseStructure.add(leafResult);
                parentReaourceId = leafResult.getId();
                if (CollectionUtils.isNotEmpty(teachingPackageVo.getResourceIds())) {
                    List<CourseResource> leafResourceList = courseResourceList.stream().filter(courseResource ->
                            courseResource.getCourseStructure().getId().equals(courseStructureLeaf.getId())).collect(Collectors.toList());
                    String finalParentReaourceId = parentReaourceId;
                    leafResourceList.forEach(courseResource -> {
                        String detailId = null;
                        detailId = setResourceDetail(detailId, courseResource);
                        courseResource.setParentId(finalParentReaourceId);
                        courseResource.setCourseStructure(courseStructureLeaf);
                        CourseResource leaf = new CourseResource();
                        leaf.setResourceDetailId(detailId);
                        copyCourseRrsource(courseResource, leaf, teachingPackageVo, leafResult.getParentId(),
                                finalParentReaourceId, finalParentReaourceId);
                        newCourseResource.add(leaf);
                    });
                }

            });
            List<CourseResource> rootResourceList = courseResourceList.stream().filter(courseResource ->
                    courseResource.getCourseStructure().getId().equals(courseStructure.getId())).collect(Collectors.toList());
            rootResourceList.forEach(courseResource -> {
                String detailId = null;
                detailId = setResourceDetail(detailId, courseResource);
                CourseResource leaf = new CourseResource();
                courseResource.setCourseStructure(courseStructure);
                leaf.setResourceDetailId(detailId);
                copyCourseRrsource(courseResource, leaf, teachingPackageVo, null, null, finalNewStructureId);
                newCourseResource.add(leaf);
            });
        });
    }

    public String setResourceDetail(String detailId, CourseResource courseResource) {
        if (courseResource.getResourceDetailId() != null && courseResource.getResourceType().equals(ResourceType.THEME)) {
            CourseTheme courseTheme = courseThemeRepository.findByIdAndThemeStatus(courseResource.getResourceDetailId(), ResourceStatus.NORMAL);
            CourseTheme newCourseTheme = new CourseTheme();
            BeanUtil.copyProperties(courseTheme, newCourseTheme, "id");
            newCourseTheme.setId(null);
            CourseTheme newObject = courseThemeRepository.saveAndFlush(newCourseTheme);
            detailId = newObject.getId();
            List<CourseThemeDetail> courseThemeDetailList = courseTheme.getCourseThemeDetailList();
            courseThemeDetailList.forEach(courseThemeDetail -> {
                CourseThemeDetail newDetail = new CourseThemeDetail();
                BeanUtil.copyProperties(courseThemeDetail, newDetail, "id");
                newDetail.setCourseTheme(newObject);
                courseThemeDetailRepository.saveAndFlush(newDetail);
            });
        } else if (courseResource.getResourceDetailId() != null &&
                (courseResource.getResourceType().equals(ResourceType.IMAGE) ||
                        courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE) ||
                        courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO))) {
            Optional<CourseResourceFile> resourceFile = courseResourceFileRepository.findById(courseResource.getResourceDetailId());
            if (resourceFile.isPresent()) {
                CourseResourceFile courseResourceFile = new CourseResourceFile();
                courseResourceFile = resourceFile.get();
                CourseResourceFile newCourseResourceFile = new CourseResourceFile();
                BeanUtil.copyProperties(courseResourceFile, newCourseResourceFile, "id");
                newCourseResourceFile = courseResourceFileRepository.save(newCourseResourceFile);
                detailId = newCourseResourceFile.getId();
            }
        } else if (courseResource.getResourceDetailId() != null && courseResource.getResourceType().equals(ResourceType.CLASS_TEST)) {
            detailId = courseResource.getResourceDetailId();
        }
        return detailId;
    }

    public void copyCourseStructure(CourseStructure source, CourseStructure target,
                                    CourseTeachingPackageVo teachingPackageVo, String id, String parentId) {
        target.setCourse(source.getCourse());
        target.setId(id);
        target.setStructureStatus(source.getStructureStatus());
        target.setParentId(parentId);
        target.setShowOrder(source.getShowOrder());
        target.setCreateTime(new Date());
        target.setCreatorId(teachingPackageVo.getUserId());
        target.setCreatorName(teachingPackageVo.getUserName());
        target.setCourseStructureName(source.getCourseStructureName());
        target.setModifierId(teachingPackageVo.getUserId());
        target.setModifierName(teachingPackageVo.getUserName());
        target.setModifyTime(new Date());
        target.setCourseResources(source.getCourseResources());
    }

    public void copyCourseRrsource(CourseResource source, CourseResource target, CourseTeachingPackageVo teachingPackageVo, String structureParentId,
                                   String parentReaourceId, String structureId) {
        CourseStructure courseStructure = new CourseStructure();
        copyCourseStructure(source.getCourseStructure(), courseStructure, teachingPackageVo, structureId, structureParentId);
        courseStructure.setId(structureId);
        target.setCourseStructure(courseStructure);
        target.setResourceType(source.getResourceType());
        target.setParentId(parentReaourceId);
        target.setResourceStatus(source.getResourceStatus());
        target.setCreateTime(new Date());
        target.setCreatorId(teachingPackageVo.getUserId());
        target.setCreatorName(teachingPackageVo.getUserName());
        target.setModifierId(teachingPackageVo.getUserId());
        target.setModifierName(teachingPackageVo.getUserName());
        target.setModifyTime(new Date());
        target.setIsPublic(source.getIsPublic());
        target.setResourceContentNum(source.getResourceContentNum());
        if (target.getResourceDetailId() == null) {
            target.setResourceDetailId(source.getResourceDetailId());
        }
        target.setResourceName(source.getResourceName());
        target.setResourceReferences(0);
        target.setResourceOtherReferences(0);
        target.setResourceSize(source.getResourceSize());
    }

    public List<TeachingPackageVo> getTeachingPackageByName(String courseName, String courseId, String userId) {
        List<TeachingPackageVo> teachingPackageVos = new ArrayList<>();
        try {
            //发布的课程包
            List<CourseTeachingPackage> courseTeachingPackages =
                    teachingPackageRepository.findByCourseNameLikeOrderByCreateTime("%" + courseName + "%");
            Map<String, List<CourseTeachingPackage>> mapPackages = courseTeachingPackages.stream()
                    .collect(Collectors.groupingBy(CourseTeachingPackage::getCourseId));
            List<String> courseIds = courseTeachingPackages.stream().map(CourseTeachingPackage::getCourseId).collect(toList());
            List<Course> courseByIdIn = courseRepository.findByIdIn(courseIds);
            List<String> courseIdUse = courseByIdIn.stream().filter(item -> item.isUseState()).map(Course::getId).collect(toList());
            mapPackages.forEach((packageCourseId, courseTeachingPackageList) -> {
                if (courseIdUse.contains(packageCourseId)) {
                    int sumQuote = courseTeachingPackageList.stream().mapToInt(CourseTeachingPackage::getQuoteNum).sum();
                    Optional<CourseTeachingPackage> maxCourseTeachingPackage = courseTeachingPackageList.stream()
                            .max(Comparator.comparing(CourseTeachingPackage::getVisionNumber));
                    if (maxCourseTeachingPackage.isPresent()) {
                        CourseTeachingPackage courseTeachingPackage = maxCourseTeachingPackage.get();
                        TeachingPackageVo teachingPackageVo = new TeachingPackageVo();
                        teachingPackageVo.setTeachingPackageId(courseTeachingPackage.getId());
                        teachingPackageVo.setTeachingPackageName(courseTeachingPackage.getTeachingPackageName());
                        teachingPackageVo.setTeachingPackageIntroduction(courseTeachingPackage.getTeachingPackageIntroduction());
                        teachingPackageVo.setCourseId(courseTeachingPackage.getCourseId());
                        teachingPackageVo.setCourseName(courseTeachingPackage.getCourseName());
                        teachingPackageVo.setUserId(courseTeachingPackage.getCreatorId());
                        teachingPackageVo.setUserName(courseTeachingPackage.getCreatorName());
                        teachingPackageVo.setQuoteNum(sumQuote);
                        setCourseStructureVo(courseTeachingPackage, teachingPackageVo, courseId, userId);
                        teachingPackageVos.add(teachingPackageVo);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teachingPackageVos;
    }


    public void setCourseStructureVo(CourseTeachingPackage courseTeachingPackage, TeachingPackageVo teachingPackageVo, String courseId,
                                     String userId) {
        List<CourseTeachingPackageResource> courseTeachingPackageResources = teachingPackageResourceRepository
                .findByCourseIdAndCourseTeachingPackageId(courseId, courseTeachingPackage.getId());
        List<String> resourceIds = courseTeachingPackageResources.stream()
                .map(CourseTeachingPackageResource::getResourceId).collect(Collectors.toList());
        Course course = courseRepository.findByIdAndUseState(courseTeachingPackage.getCourseId(), true);
        if (ObjectUtils.isNotEmpty(course)) {
            Result result = courseStructureService.getCoursesContentPack(courseTeachingPackage.getCourseId(), null, 0);
            List<CourseStructureVo> courseStructureVos = (List<CourseStructureVo>) result.getData();
            String jsonResult = JSONObject.toJSONString(courseStructureVos);
            List<TeachingPackageCourseStructureVo> teachingPackageCourseStructureVos = JSONObject.parseArray(jsonResult,
                    TeachingPackageCourseStructureVo.class);
            teachingPackageVo.setCourseStructureList(teachingPackageCourseStructureVos);
            if (CollectionUtils.isNotEmpty(teachingPackageCourseStructureVos)) {
                teachingPackageCourseStructureVos.forEach(teachingPackageCourseStructureVo -> {
                    isQuote(teachingPackageCourseStructureVo, resourceIds);
                });
            }
        }
    }

    public void isQuote(TeachingPackageCourseStructureVo teachingPackageCourseStructureVo, List<String> resourceIds) {
        if (CollectionUtils.isNotEmpty(teachingPackageCourseStructureVo.getChildStructureList())) {
            teachingPackageCourseStructureVo.getChildStructureList().forEach(child -> {
                isQuote(child, resourceIds);
            });
        }
        if (CollectionUtils.isEmpty(resourceIds)) {
            teachingPackageCourseStructureVo.getCourseResourceList().forEach(resource -> {
                resource.setIsQuote(false);
            });
        } else {
            teachingPackageCourseStructureVo.getCourseResourceList().forEach(resource -> {
                if (resourceIds.contains(resource.getResourceId())) {
                    resource.setIsQuote(true);
                } else {
                    resource.setIsQuote(false);
                }
            });
        }
    }

    public List<TeachingPackageCourseVersionResource> getPackageVersion(String courseId, String userId) {
        List<CourseTeachingPackage> courseTeachingPackages =
                teachingPackageRepository.findByCourseIdAndCreatorId(courseId, userId);
        if (CollectionUtils.isEmpty(courseTeachingPackages)) {
            List<TeachingPackageCourseVersionResource> teachingPackageCourseVersionResources = new ArrayList<>();
            TeachingPackageCourseVersionResource teachingPackageCourseVersionResource = new TeachingPackageCourseVersionResource();
            teachingPackageCourseVersionResource.setVersionNumber("1");
            teachingPackageCourseVersionResources.add(teachingPackageCourseVersionResource);
            return teachingPackageCourseVersionResources;
        }
        CourseTeachingPackage maxNowVisionNumber = courseTeachingPackages.stream()
                .max(Comparator.comparing(CourseTeachingPackage::getVisionNumber)).orElse(null);
        List<TeachingPackageCourseVersionResource> teachingPackageCourseVersionResources = new ArrayList<>();
        TeachingPackageCourseVersionResource teachingPackageCourseVersionResource = new TeachingPackageCourseVersionResource();
        teachingPackageCourseVersionResource.setVersionNumber(String.valueOf(maxNowVisionNumber.getVisionNumber() + 1));
        teachingPackageCourseVersionResource.setFilingUserId(maxNowVisionNumber.getCreatorId());
        teachingPackageCourseVersionResource.setFilingUserName(maxNowVisionNumber.getCreatorName());
        teachingPackageCourseVersionResource.setVisionTime(String.valueOf(maxNowVisionNumber.getVisionTime()));
        teachingPackageCourseVersionResources.add(teachingPackageCourseVersionResource);
        return teachingPackageCourseVersionResources;
    }

    public CourseTeachingPackagePageResource getTeachingPackageList(List<String> courseIds, Integer page, Integer pageSize) {
        List<CourseTeachingPackage> courseTeachingPackages = teachingPackageRepository.findByCourseIdIn(courseIds);
        return getCourseTeachingPackagePageResource(page, pageSize, courseTeachingPackages);
    }

    private CourseTeachingPackagePageResource getCourseTeachingPackagePageResource
            (Integer page, Integer pageSize, List<CourseTeachingPackage> courseTeachingPackages) {
        CourseTeachingPackagePageResource courseTeachingPackagePageResource = new CourseTeachingPackagePageResource();
        courseTeachingPackagePageResource.setPage(page);
        courseTeachingPackagePageResource.setPageSize(pageSize);
        if (CollectionUtils.isEmpty(courseTeachingPackages)) {
            courseTeachingPackagePageResource.setPage(page);
            courseTeachingPackagePageResource.setPageCount(0);
            courseTeachingPackagePageResource.setPageSize(pageSize);
            courseTeachingPackagePageResource.setCourseTeachingPackageListVo(new ArrayList<>());
            return courseTeachingPackagePageResource;
        }
        Map<String, List<CourseTeachingPackage>> courseTeachingPackageMap = courseTeachingPackages
                .stream().collect(Collectors.groupingBy(CourseTeachingPackage::getCourseId));
        Iterator<Map.Entry<String, List<CourseTeachingPackage>>> courseTeachingPackageIterator =
                courseTeachingPackageMap.entrySet().iterator();

        List<CourseTeachingPackage> courseTeachingPackageList = new ArrayList<>();
        Map<String, Integer> maxQuoteMap = new HashMap<>();
        while (courseTeachingPackageIterator.hasNext()) {
            final Map.Entry<String, List<CourseTeachingPackage>> courseTeachingPackageNext =
                    courseTeachingPackageIterator.next();
            List<CourseTeachingPackage> packageNextValue = courseTeachingPackageNext.getValue();
            int maxVisionNum = packageNextValue.stream().mapToInt(CourseTeachingPackage::getVisionNumber).max().orElse(0);
            int sumQuote = packageNextValue.stream().mapToInt(CourseTeachingPackage::getQuoteNum).sum();
            packageNextValue.forEach(courseTeachingPackage -> {
                if (courseTeachingPackage.getVisionNumber() == maxVisionNum) {
                    courseTeachingPackageList.add(courseTeachingPackage);
                    maxQuoteMap.put(courseTeachingPackage.getId(), sumQuote);
                }
            });
        }
        courseTeachingPackageList.sort(Comparator.comparing(CourseTeachingPackage::getModifyTime).reversed());
        courseTeachingPackagePageResource.setTotal(courseTeachingPackageList.size());
        courseTeachingPackagePageResource.setPageCount((courseTeachingPackageList.size() / pageSize) + 1);
        List<CourseTeachingPackage> packages = courseTeachingPackageList.stream()
                .skip((long) (page - 1) * pageSize).limit(pageSize).collect(toList());
        List<CourseTeachingPackageListVo> courseTeachingPackageListVos = new ArrayList<>();
        packages.forEach(courseTeachingPackage -> {
            CourseTeachingPackageListVo courseTeachingPackageListVo = new CourseTeachingPackageListVo();
            courseTeachingPackageListVo.setId(courseTeachingPackage.getId());
            courseTeachingPackageListVo.setTeachingPackageName(courseTeachingPackage.getTeachingPackageName());
            courseTeachingPackageListVo.setCourseId(courseTeachingPackage.getCourseId());
            courseTeachingPackageListVo.setCourseName(courseTeachingPackage.getCourseName());
            courseTeachingPackageListVo.setCourseVersionId(courseTeachingPackage.getCourseVersionId());
            courseTeachingPackageListVo.setFilePath(courseTeachingPackage.getFilePath());
            courseTeachingPackageListVo.setInnerIp(courseTeachingPackage.getInnerIp());
            courseTeachingPackageListVo.setOuterIp(courseTeachingPackage.getOuterIp());
            courseTeachingPackageListVo.setQuoteNum(courseTeachingPackage.getQuoteNum() == null ? 0 : courseTeachingPackage.getQuoteNum());
            courseTeachingPackageListVo.setVisionTime(courseTeachingPackage.getVisionTime());
            courseTeachingPackageListVo.setVisionNumber(courseTeachingPackage.getVisionNumber());
            courseTeachingPackageListVo.setQuoteNum(maxQuoteMap.get(courseTeachingPackage.getId()));
            courseTeachingPackageListVos.add(courseTeachingPackageListVo);
        });
        courseTeachingPackagePageResource.setCourseTeachingPackageListVo(courseTeachingPackageListVos);
        return courseTeachingPackagePageResource;
    }

    public List<CourseTeachingPackage> findByCourseId(String courseId, String userId) {
        List<CourseTeachingPackage> courseTeachingPackages = teachingPackageRepository.findByCourseId(courseId);
        return courseTeachingPackages;
    }

    public TeachingPackageVo getTeachingPackageDetail(List<CourseTeachingPackage> teachingPackages, String courseId, String userId) {
        TeachingPackageVo teachingPackageVo = new TeachingPackageVo();
        int maxVisionNum = teachingPackages.stream().mapToInt(CourseTeachingPackage::getVisionNumber).max().orElse(0);
        int sumQuote = teachingPackages.stream().mapToInt(CourseTeachingPackage::getQuoteNum).sum();
        teachingPackages.forEach(courseTeachingPackage -> {
            if (courseTeachingPackage.getVisionNumber() == maxVisionNum) {
                teachingPackageVo.setTeachingPackageName(courseTeachingPackage.getTeachingPackageName());
                teachingPackageVo.setTeachingPackageIntroduction(courseTeachingPackage.getTeachingPackageIntroduction());
                teachingPackageVo.setCourseId(courseTeachingPackage.getCourseId());
                teachingPackageVo.setCourseName(courseTeachingPackage.getCourseName());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                teachingPackageVo.setVisionTime(sdf.format(courseTeachingPackage.getVisionTime()));
                teachingPackageVo.setVisionNumber(courseTeachingPackage.getVisionNumber());
                teachingPackageVo.setUserId(courseTeachingPackage.getCreatorId());
                teachingPackageVo.setUserName(courseTeachingPackage.getCreatorName());
                teachingPackageVo.setQuoteNum(sumQuote);
                teachingPackageVo.setFilePath(courseTeachingPackage.getFilePath());
                teachingPackageVo.setInnerIp(courseTeachingPackage.getInnerIp());
                teachingPackageVo.setOuterIp(courseTeachingPackage.getOuterIp());
                if (StringUtils.isNotEmpty(courseTeachingPackage.getLabelName())) {
                    List<String> labelNames =
                            Arrays.stream(courseTeachingPackage.getLabelName().split(",")).collect(toList());
                    teachingPackageVo.setLabelNames(labelNames);
                }
                setCourseStructureVo(courseTeachingPackage, teachingPackageVo, courseId, userId);
            }
        });
        return teachingPackageVo;
    }

    public Result quoteTeachingPackage(QuoteTeachingPackageVo quoteTeachingPackage) {
        try {
            Optional<CourseTeachingPackage> teachingPackageOptional = teachingPackageRepository
                    .findById(quoteTeachingPackage.getTeachingPackageId());
            CourseTeachingPackage courseTeachingPackage = teachingPackageOptional.get();
            Optional<Course> courseOptional = courseRepository.findById(quoteTeachingPackage.getCourseId());
            Course course = courseOptional.get();
            List<CourseVersion> courseVersions = courseVersionRepository.findByCourseIdAndVersionStatus(quoteTeachingPackage.getCourseId(),
                    CourseVersionStatus.IN_USE.getValue());
            CourseVersion courseVersion = courseVersions.stream().max(Comparator.comparing(CourseVersion::getVersionSerialNumber)).get();
            Result result = courseStructureService.getCoursesContent(quoteTeachingPackage.getCourseId(),
                    quoteTeachingPackage.getVersionId(), 0, null, null);
            List<CourseStructureVo> courseStructureVos = (List<CourseStructureVo>) result.getData();
            Map<String, String> structureName = getStructureName(courseStructureVos);
            Map<String, List<TeachingPackageCourseResourceVo>> packageReource = new HashMap<>();
            quoteTeachingPackage.getTeachingPackageQuote().forEach(teachingPackageCourseStructureVo -> {
                if (CollectionUtils.isNotEmpty(teachingPackageCourseStructureVo.getCourseResourceList())) {
                    teachingPackageCourseStructureVo.getCourseResourceList().forEach(item -> {
                        List<TeachingPackageCourseResourceVo> mapResource = packageReource.get(teachingPackageCourseStructureVo.getStructureName());
                        if (CollectionUtils.isNotEmpty(mapResource)) {
                            mapResource.add(item);
                            packageReource.put(teachingPackageCourseStructureVo.getStructureName(), mapResource);
                        } else {
                            mapResource = new ArrayList<>();
                            mapResource.add(item);
                            packageReource.put(teachingPackageCourseStructureVo.getStructureName(), mapResource);
                        }
                    });
                }
                if (CollectionUtils.isNotEmpty(teachingPackageCourseStructureVo.getChildStructureList())) {
                    teachingPackageCourseStructureVo.getChildStructureList().forEach(childStructure -> {
                        if (CollectionUtils.isNotEmpty(childStructure.getCourseResourceList())) {
                            childStructure.getCourseResourceList().forEach(item -> {
                                List<TeachingPackageCourseResourceVo> mapResource = packageReource
                                        .get(teachingPackageCourseStructureVo.getStructureName() + childStructure.getStructureName());
                                if (CollectionUtils.isNotEmpty(mapResource)) {
                                    mapResource.add(item);
                                    packageReource.put(teachingPackageCourseStructureVo.getStructureName() + childStructure.getStructureName(),
                                            mapResource);
                                } else {
                                    mapResource = new ArrayList<>();
                                    mapResource.add(item);
                                    packageReource.put(teachingPackageCourseStructureVo.getStructureName() + childStructure.getStructureName(),
                                            mapResource);
                                }
                            });
                        }
                    });
                }
            });
            buildQuoteTeachingPackage(packageReource, structureName, courseVersion, quoteTeachingPackage, course, courseStructureVos);
            int quoteNum = courseTeachingPackage.getQuoteNum();
            courseTeachingPackage.setQuoteNum(quoteNum + 1);
            teachingPackageRepository.saveAndFlush(courseTeachingPackage);
            List<String> resourceIds = getQuotePackageResourceIds(quoteTeachingPackage.getTeachingPackageQuote());
            List<CourseTeachingPackageResource> courseTeachingPackageResources = new ArrayList<>();
            resourceIds.forEach(resourceId -> {
                CourseTeachingPackageResource courseTeachingPackageResource = new CourseTeachingPackageResource();
                courseTeachingPackageResource.setCourseTeachingPackageId(quoteTeachingPackage.getTeachingPackageId());
                courseTeachingPackageResource.setUserId(quoteTeachingPackage.getUserId());
                courseTeachingPackageResource.setCourseId(quoteTeachingPackage.getCourseId());
                courseTeachingPackageResource.setResourceId(resourceId);
                courseTeachingPackageResources.add(courseTeachingPackageResource);
            });
            if (CollectionUtils.isNotEmpty(courseTeachingPackageResources)) {
                teachingPackageResourceRepository.saveAll(courseTeachingPackageResources);
            }
        } catch (Exception e) {
            LOGGER.error("quoteTeachingPackage->", e);
            return Result.error("服务异常");
        }
        return Result.success();
    }

    public void buildQuoteTeachingPackage(Map<String, List<TeachingPackageCourseResourceVo>> packageReource, Map<String, String> structureName,
        CourseVersion courseVersion, QuoteTeachingPackageVo quoteTeachingPackage, Course course, List<CourseStructureVo> courseStructureVos) {
        packageReource.forEach((name, resources) -> {
            if (structureName.get(name) != null) {
                List<String> contrastName = new ArrayList<>();
                resources.forEach(resource -> {
                    Result result = courseStructureService.getCoursesContent(quoteTeachingPackage.getCourseId(),
                            quoteTeachingPackage.getVersionId(), 0, null, null);
                    List<CourseStructureVo> courseStructureVoses = (List<CourseStructureVo>) result.getData();
                    contrastName.addAll(getEquallyResourceName(courseStructureVos, name));
                    CourseResource courseResource = new CourseResource();
                    buildCourseResource(resource, courseResource, quoteTeachingPackage);
                    if (ObjectUtils.isNotEmpty(resource) && StringUtils.isNotEmpty(resource.getFileSize())
                            && !resource.getFileSize().equals("null")) {
                        courseResource.setResourceSize(Long.valueOf(resource.getFileSize()));
                    }
                    String detailId = null;
                    detailId = setCourseResourceVos(resource, courseResource, detailId);
                    courseResource.setResourceDetailId(detailId);
                    courseResource.setVisionNumber(courseVersion.getVersionSerialNumber());
                    String repeatName = isDuplicateName(courseStructureVoses, resource, name, contrastName);
                    if (StringUtils.isNotBlank(repeatName)) {
                        courseResource.setResourceName(repeatName);
                    }
                    Optional<CourseStructure> structureParent = courseStructureRepository.findById(structureName.get(name));
                    courseResource.setCourseStructure(structureParent.get());
                    fillCourseResourceSort(courseResource, structureParent.get());
                    courseResourceRepository.saveAndFlush(courseResource);
                });
            } else {
                Map<String, List<CourseStructure>> courseStructures = getStructureList(quoteTeachingPackage.getTeachingPackageQuote(), resources,
                        course, courseVersion, quoteTeachingPackage);
                List<String> contrastName = new ArrayList<>();
                resources.forEach(resource -> {
                    Result result = courseStructureService.getCoursesContent(quoteTeachingPackage.getCourseId(),
                            quoteTeachingPackage.getVersionId(), 0, null, null);
                    List<CourseStructureVo> courseStructureVoses = (List<CourseStructureVo>) result.getData();
                    contrastName.addAll(getEquallyResourceName(courseStructureVos, name));
                    CourseResource courseResource = new CourseResource();
                    buildCourseResource(resource, courseResource, quoteTeachingPackage);
                    if (ObjectUtils.isNotEmpty(resource) && StringUtils.isNotBlank(resource.getFileSize())
                            && !resource.getFileSize().equals("null")) {
                        courseResource.setResourceSize(Long.valueOf(resource.getFileSize()));
                    }
                    courseResource.setVisionNumber(courseVersion.getVersionSerialNumber());
                    AtomicReference<CourseStructure> root = new AtomicReference<>(new CourseStructure());
                    AtomicReference<CourseStructure> leaf = new AtomicReference<>(new CourseStructure());
                    courseStructures.get("root").stream().filter(courseStructure ->
                            StringUtils.isBlank(courseStructure.getParentId())).forEach(item -> {
                        if (structureName.get(item.getCourseStructureName()) != null) {
                            root.set(courseStructureRepository.findByIdAndStructureStatus(
                                    structureName.get(item.getCourseStructureName()), StructureStatus.NORMAL));
                        } else {
                            CourseStructure courseStructure = courseStructureRepository.saveAndFlush(item);
                            root.set(courseStructure);
                            structureName.put(courseStructure.getCourseStructureName(), courseStructure.getId());
                        }
                    });
                    courseStructures.get("leaf").stream().forEach(item -> {
                        item.setParentId(root.get().getId());
                        CourseStructure courseStructure = courseStructureRepository.saveAndFlush(item);
                        leaf.set(courseStructure);
                    });
                    if (StringUtils.isNotBlank(leaf.get().getCourseStructureName())) {
                        courseResource.setCourseStructure(leaf.get());
                    } else {
                        courseResource.setCourseStructure(root.get());
                    }
                    String detailId = null;
                    detailId = setCourseResourceVos(resource, courseResource, detailId);
                    courseResource.setVisionNumber(courseVersion.getVersionSerialNumber());
                    courseResource.setResourceDetailId(detailId);
                    String repeatName = isDuplicateName(courseStructureVoses, resource, name, contrastName);
                    if (StringUtils.isNotBlank(repeatName)) {
                        courseResource.setResourceName(repeatName);
                    }
                    fillCourseResourceSort(courseResource, courseResource.getCourseStructure());
                    courseResourceRepository.saveAndFlush(courseResource);
                });
            }
        });
    }

    private void fillCourseResourceSort(CourseResource courseResource, CourseStructure structureParent) {
        CourseResource oldResource =
                courseResourceRepository.findTop1ByCreatorIdAndCourseStructureIdAndResourceStatusOrderBySortDesc(
                        courseResource.getCourseId(),
                        structureParent.getId(), ResourceStatus.NORMAL);
        if (ObjectUtils.isNotEmpty(oldResource)) {
            courseResource.setSort(oldResource.getSort() + 1);
        } else {
            courseResource.setSort(0);
        }
        CourseResource resource = courseResourceRepository.findTop1ByCourseStructureIdAndIsPublicAndResourceStatusOrderByAuxiliarySortDesc(
                structureParent.getId(), true, ResourceStatus.NORMAL);
        if (ObjectUtils.isNotEmpty(resource)) {
            courseResource.setAuxiliarySort(resource.getAuxiliarySort() + 1);
        } else {
            courseResource.setAuxiliarySort(0);
        }
    }

    public String setCourseResourceVos(TeachingPackageCourseResourceVo resource, CourseResource courseResource, String detailId) {
        if (resource.getResourceDetailId() != null && resource.getResourceType().equals(ResourceType.THEME.getIndex())) {
            CourseTheme courseTheme = courseThemeRepository.findByIdAndThemeStatus(courseResource.getResourceDetailId(), ResourceStatus.NORMAL);
            CourseTheme newCourseTheme = new CourseTheme();
            BeanUtil.copyProperties(courseTheme, newCourseTheme, "id");
            newCourseTheme.setId(null);
            CourseTheme newObject = courseThemeRepository.saveAndFlush(newCourseTheme);
            detailId = newObject.getId();
            List<CourseThemeDetail> courseThemeDetailList = courseTheme.getCourseThemeDetailList();
            courseThemeDetailList.forEach(courseThemeDetail -> {
                CourseThemeDetail courseThemeDetail1 = new CourseThemeDetail();
                BeanUtil.copyProperties(courseThemeDetail, courseThemeDetail1, "id");
                courseThemeDetail1.setCourseTheme(newObject);
                courseThemeDetail1 = courseThemeDetailRepository.saveAndFlush(courseThemeDetail1);
            });
        } else if (resource.getResourceDetailId() != null &&
                (resource.getResourceType().equals(ResourceType.IMAGE.getIndex()) ||
                        resource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE.getIndex()) ||
                        resource.getResourceType().equals(ResourceType.MICRO_VIDEO.getIndex())
                        || resource.getResourceType().equals(ResourceType.AUDIO.getIndex()))) {
            Optional<CourseResourceFile> resourceFile = courseResourceFileRepository.findById(courseResource.getResourceDetailId());
            if (resourceFile.isPresent()) {
                CourseResourceFile courseResourceFile = new CourseResourceFile();
                courseResourceFile = resourceFile.get();
                CourseResourceFile newCourseResourceFile = new CourseResourceFile();
                BeanUtil.copyProperties(courseResourceFile, newCourseResourceFile, "id");
                newCourseResourceFile = courseResourceFileRepository.save(newCourseResourceFile);
                detailId = newCourseResourceFile.getId();
            }
        } else if (resource.getResourceDetailId() != null && resource.getResourceType().equals(ResourceType.CLASS_TEST.getIndex())) {
            detailId = courseResource.getResourceDetailId();
        }
        return detailId;
    }

    public String isDuplicateName(List<CourseStructureVo> courseStructureVos, TeachingPackageCourseResourceVo courseResource, String structureName,
                                  List<String> contrastName) {
        String resourceName = "";
        if (courseResource.getResourceType().equals(ResourceType.IMAGE.getIndex()) ||
                courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO.getIndex()) ||
                courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE.getIndex())) {
            if (courseResource.getResourceName().indexOf("(") > 0) {
                String originallyName = courseResource.getResourceName().replace(courseResource
                                .getResourceName().substring(courseResource.getResourceName().indexOf("(")
                                        , courseResource.getResourceName().lastIndexOf(")") + 1)
                        , "");
                String prefix = courseResource.getResourceName().substring(courseResource.getResourceName().lastIndexOf("("));
                resourceName = originallyName + "." + courseResource.getFileType() + prefix;
            } else {
                resourceName = courseResource.getResourceName() + "." + courseResource.getFileType();
            }
        } else {
            resourceName = courseResource.getResourceName();
        }
        AtomicReference<String> nameResult = new AtomicReference<>("");
        String finalResourceName = resourceName;
        courseStructureVos.forEach(courseStructureVo -> {
            courseStructureVo.getChildStructureList().forEach(child -> {
                if ((courseStructureVo.getStructureName() + child.getStructureName()).equals(structureName)) {
                    AtomicLong identicalNum = new AtomicLong();
                    if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST.getIndex())) {
                        identicalNum.set(child.getCourseResourceList().stream().filter(courseResourceVo ->
                                courseResourceVo.getResourceType().equals(ResourceType.CLASS_TEST.getIndex()) &&
                                        courseResourceVo.getResourceName().equals(finalResourceName)).count());
                    } else if (courseResource.getResourceType().equals(ResourceType.THEME.getIndex())) {
                        identicalNum.set(0);
                    } else {
                        identicalNum.set(0);
                        child.getCourseResourceList().stream().filter(courseResourceVo ->
                                !(courseResourceVo.getResourceType().equals(ResourceType.CLASS_TEST.getIndex())
                                        || courseResourceVo.getResourceType().equals(ResourceType.THEME.getIndex()))).forEach(courseResourceVo -> {
                            isCarryBrackets(courseResourceVo, finalResourceName, identicalNum);
                        });
                    }
                    getMidGame(identicalNum.get(), courseResource, child, finalResourceName, contrastName, nameResult);
                }
            });
            if (courseStructureVo.getStructureName().equals(structureName)) {
                AtomicLong identicalNum = new AtomicLong();
                if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST.getIndex())) {
                    identicalNum.set(courseStructureVo.getCourseResourceList().stream().filter(courseResourceVo ->
                            courseResourceVo.getResourceType().equals(ResourceType.CLASS_TEST.getIndex()) &&
                                    courseResourceVo.getResourceName().equals(finalResourceName)).count());
                } else if (courseResource.getResourceType().equals(ResourceType.THEME.getIndex())) {
                    identicalNum.set(0);
                } else {
                    identicalNum.set(0);
                    courseStructureVo.getCourseResourceList().stream().filter(courseResourceVo ->
                            !(courseResourceVo.getResourceType().equals(ResourceType.CLASS_TEST.getIndex())
                                    || courseResourceVo.getResourceType().equals(ResourceType.THEME.getIndex()))).forEach(courseResourceVo -> {
                        isCarryBrackets(courseResourceVo, finalResourceName, identicalNum);
                    });
                }
                getMidGame(identicalNum.get(), courseResource, courseStructureVo, finalResourceName, contrastName, nameResult);
            }
        });
        contrastName.add(nameResult.get());
        if (courseResource.getResourceType().equals(ResourceType.IMAGE.getIndex()) ||
                courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO.getIndex()) ||
                courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE.getIndex())) {
            if (nameResult.get().lastIndexOf(".") > 0 && nameResult.get().indexOf("(") < 0) {
                String suffix = nameResult.get().substring(nameResult.get().lastIndexOf("."));
                nameResult.set(nameResult.get().replace(suffix, ""));
            } else if (nameResult.get().lastIndexOf(".") > 0 && nameResult.get().indexOf("(") > 0) {
                String suffix = nameResult.get().substring(nameResult.get().lastIndexOf("."), nameResult.get().indexOf("("));
                nameResult.set(nameResult.get().replace(suffix, ""));
            }
        }
        return nameResult.get();
    }

    public void isCarryBrackets(CourseResourceVo courseResourceVo, String finalResourceName, AtomicLong identicalNum) {
        if (courseResourceVo.getResourceName().indexOf("(") > 0) {
            String originallyName = courseResourceVo.getResourceName().replace(courseResourceVo
                            .getResourceName().substring(courseResourceVo.getResourceName().indexOf("(")
                                    , courseResourceVo.getResourceName().lastIndexOf(")") + 1)
                    , "");
            String prefix = courseResourceVo.getResourceName().substring(courseResourceVo.getResourceName().lastIndexOf("("));
            if (finalResourceName.equals(originallyName + "." + courseResourceVo.getFileType() + prefix)) {
                identicalNum.incrementAndGet();
            }
        } else {
            if ((courseResourceVo.getResourceName() + "." + courseResourceVo.getFileType()).equals(finalResourceName)) {
                identicalNum.incrementAndGet();
            }
        }
    }

    public void getMidGame(long identicalNum, TeachingPackageCourseResourceVo courseResource, CourseStructureVo courseStructureVo,
                           String finalResourceName, List<String> contrastName, AtomicReference<String> nameResult) {
        if (identicalNum > 0) {
            List<CourseResourceVo> filterResources = new ArrayList<>();
            if (courseResource.getResourceType().equals(ResourceType.CLASS_TEST.getIndex()) ||
                    courseResource.getResourceType().equals(ResourceType.THEME.getIndex())) {
                filterResources = courseStructureVo.getCourseResourceList().stream().filter(courseResourceVo ->
                        courseResourceVo.getResourceName().equals(finalResourceName)).collect(toList());
            } else {
                List<CourseResourceVo> finalFilterResources = filterResources;
                courseStructureVo.getCourseResourceList().stream().forEach(item -> {
                    if (item.getResourceName().indexOf("(") > 0) {
                        String resourceName = item.getResourceName().replace(item
                                        .getResourceName().substring(item.getResourceName().indexOf("(")
                                                , item.getResourceName().lastIndexOf(")") + 1)
                                , "");
                        String prefix = item.getResourceName().substring(item.getResourceName().lastIndexOf("("));
                        if ((resourceName + "." + item.getFileType() + prefix).equals(finalResourceName)) {
                            finalFilterResources.add(item);
                        }
                    } else {
                        if ((item.getResourceName() + "." + item.getFileType()).equals(finalResourceName)) {
                            finalFilterResources.add(item);
                        }
                    }
                });
                filterResources = finalFilterResources;
            }
            filterResources.forEach(courseResourceVo -> {
                String name = "";
                if (courseResourceVo.getResourceType().equals(ResourceType.CLASS_TEST.getIndex()) ||
                        courseResourceVo.getResourceType().equals(ResourceType.THEME.getIndex())) {
                    name = courseResourceVo.getResourceName();
                } else {
                    if (courseResourceVo.getResourceName().indexOf("(") > 0) {
                        String resourceName = courseResourceVo.getResourceName().replace(courseResourceVo
                                        .getResourceName().substring(courseResource.getResourceName().indexOf("(")
                                                , courseResourceVo.getResourceName().lastIndexOf(")") + 1)
                                , "");
                        String prefix = courseResourceVo.getResourceName().substring(courseResourceVo.getResourceName().lastIndexOf("("));
                        name = resourceName + "." + courseResourceVo.getFileType() + prefix;
                    } else {
                        name = courseResourceVo.getResourceName() + "." + courseResourceVo.getFileType();
                    }

                }
                AtomicReference<String> newName = new AtomicReference<>(checkZipName(contrastName, name, 1));
                String finalName = name;
                contrastName.stream().filter(item -> item.equals(finalName))
                        .forEach(item -> {
                            if (contrastName.contains(finalName + "(1)")) {
                                newName.set(checkZipName(contrastName, finalName + "(1)", 1));
                            } else {
                                newName.set(finalName + "(1)");
                            }
                        });
                nameResult.set(newName.get());
            });
        } else {
            nameResult.set(finalResourceName);
        }
    }

    public List<String> getEquallyResourceName(List<CourseStructureVo> courseStructureVos, String structureName) {
        List<String> equallyResourceName = new ArrayList<>();
        courseStructureVos.forEach(courseStructureVo -> {
            if (structureName.equals(courseStructureVo.getStructureName())) {
                courseStructureVo.getCourseResourceList().forEach(courseResourceVo -> {
                    if (courseResourceVo.getResourceType().equals(ResourceType.IMAGE.getIndex()) ||
                            courseResourceVo.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE.getIndex()) ||
                            courseResourceVo.getResourceType().equals(ResourceType.MICRO_VIDEO.getIndex())) {
                        if (courseResourceVo.getResourceName().indexOf("(") > 0) {
                            String resourceName = courseResourceVo.getResourceName().replace(courseResourceVo
                                            .getResourceName().substring(courseResourceVo.getResourceName().indexOf("(")
                                                    , courseResourceVo.getResourceName().lastIndexOf(")") + 1)
                                    , "");
                            String prefix = courseResourceVo.getResourceName().substring(courseResourceVo.getResourceName().lastIndexOf("("));
                            equallyResourceName.add(resourceName + "." + courseResourceVo.getFileType() + prefix);
                        } else {
                            equallyResourceName.add(courseResourceVo.getResourceName() + "." + courseResourceVo.getFileType());
                        }

                    } else {
                        equallyResourceName.add(courseResourceVo.getResourceName());
                    }
                });
            }
            courseStructureVo.getChildStructureList().forEach(child -> {
                if (structureName.equals(courseStructureVo.getStructureName() + child.getStructureName())) {
                    child.getCourseResourceList().forEach(courseResourceVo -> {
                        if (courseResourceVo.getResourceType().equals(ResourceType.IMAGE.getIndex()) ||
                                courseResourceVo.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE.getIndex()) ||
                                courseResourceVo.getResourceType().equals(ResourceType.MICRO_VIDEO.getIndex())) {
                            if (courseResourceVo.getResourceName().indexOf("(") > 0) {
                                String resourceName = courseResourceVo.getResourceName().replace(courseResourceVo
                                                .getResourceName().substring(courseResourceVo.getResourceName().indexOf("(")
                                                        , courseResourceVo.getResourceName().lastIndexOf(")") + 1)
                                        , "");
                                String prefix = courseResourceVo.getResourceName().substring(courseResourceVo.getResourceName().lastIndexOf("("));
                                equallyResourceName.add(resourceName + "." + courseResourceVo.getFileType() + prefix);
                            } else {
                                equallyResourceName.add(courseResourceVo.getResourceName() + "." + courseResourceVo.getFileType());
                            }
                        } else {
                            equallyResourceName.add(courseResourceVo.getResourceName());
                        }
                    });
                }
            });
        });
        return equallyResourceName;
    }

    private String checkZipName(List<String> structureNames, String fileName, int j) {
        if (!structureNames.contains(fileName)) {
            return fileName;
        }
        if (decide(fileName)) { //文件名字没有(2)的形式
            return fileName = checkZipName(structureNames, fileName + "(" + j + ")", j++); //递归生成文件名字
        }
        String numCountString = fileName.substring(fileName.lastIndexOf("(") + 1, fileName.lastIndexOf(")"));
        int numCount = -1;
        if (Integer.valueOf(numCountString) != null) {
            numCount = Integer.parseInt(numCountString);
        }
        if (numCount >= 1) { //是数字，不是字符串，截取(x)之前的字符  在numCount这个数字的基础上实现自增
            String realName = fileName.substring(0, fileName.lastIndexOf("("));
            final int two = 2;
            return fileName = checkZipName(structureNames, realName + "(" + ++numCount + ")", two); //递归生成文件名字
        }
        return fileName = checkZipName(structureNames, fileName + "(" + j + ")", j++); //递归生成文件名字
    }

    public boolean decide(String fileName) {
        if (fileName.lastIndexOf("(") < 0) {
            return true;
        }
        if (fileName.lastIndexOf(")") < 0) {
            return true;
        }
        if ((fileName.length() - 1) > fileName.lastIndexOf(")")) {
            return true;
        }
        if ((fileName.lastIndexOf(")") - fileName.lastIndexOf("(")) <= 1) {
            return true;
        }
        return false;
    }

    public List<String> getQuotePackageResourceIds(List<TeachingPackageCourseStructureVo> teachingPackageCourseStructureVos) {
        List<String> resourceIds = new ArrayList<>();
        teachingPackageCourseStructureVos.forEach(teachingPackageCourseStructureVo -> {
            if (CollectionUtils.isNotEmpty(teachingPackageCourseStructureVo.getCourseResourceList())) {
                teachingPackageCourseStructureVo.getCourseResourceList().forEach(teachingPackageCourseResourceVo -> {
                    resourceIds.add(teachingPackageCourseResourceVo.getResourceId());
                });
            }
            if (CollectionUtils.isNotEmpty(teachingPackageCourseStructureVo.getChildStructureList())) {
                teachingPackageCourseStructureVo.getChildStructureList().forEach(child -> {
                    child.getCourseResourceList().forEach(item -> {
                        resourceIds.add(item.getResourceId());
                    });
                });
            }
        });
        return resourceIds;

    }

    public Map<String, List<CourseStructure>> getStructureList(List<TeachingPackageCourseStructureVo> teachingPackageCourseStructureVos,
                                                               List<TeachingPackageCourseResourceVo> courseResources, Course course,
                                                               CourseVersion courseVersion,
                                                               QuoteTeachingPackageVo quoteTeachingPackageVo) {
        List<String> resourceIds = courseResources.stream().map(TeachingPackageCourseResourceVo::getResourceId).collect(Collectors.toList());
        Set<CourseStructure> rootCourseStructures = new HashSet<>();
        Set<CourseStructure> leafCourseStructures = new HashSet<>();
        teachingPackageCourseStructureVos.forEach(teachingPackageCourseStructureVo -> {
            if (CollectionUtils.isNotEmpty(teachingPackageCourseStructureVo.getCourseResourceList())) {
                teachingPackageCourseStructureVo.getCourseResourceList().forEach(teachingPackageCourseResourceVo -> {
                    if (resourceIds.contains(teachingPackageCourseResourceVo.getResourceId())) {
                        CourseStructure courseStructure = new CourseStructure();
                        buildCourseStructure(teachingPackageCourseStructureVo, courseStructure, course, courseVersion, quoteTeachingPackageVo);
                        if (CollectionUtils.isEmpty(rootCourseStructures)) {
                            rootCourseStructures.add(courseStructure);
                        }
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(teachingPackageCourseStructureVo.getChildStructureList())) {
                teachingPackageCourseStructureVo.getChildStructureList().forEach(child -> {
                    child.getCourseResourceList().forEach(item -> {
                        if (resourceIds.contains(item.getResourceId())) {
                            CourseStructure courseStructure = new CourseStructure();
                            buildCourseStructure(child, courseStructure, course, courseVersion, quoteTeachingPackageVo);
                            if (CollectionUtils.isEmpty(leafCourseStructures)) {
                                leafCourseStructures.add(courseStructure);
                                CourseStructure courseStructureRoot = new CourseStructure();
                                buildCourseStructure(teachingPackageCourseStructureVo, courseStructureRoot, course
                                        , courseVersion, quoteTeachingPackageVo);
                                if (CollectionUtils.isEmpty(rootCourseStructures)) {
                                    rootCourseStructures.add(courseStructureRoot);
                                }
                            }
                        }
                    });
                });
            }
        });
        Map<String, List<CourseStructure>> result = new HashMap<>();
        result.put("root", new ArrayList<>(rootCourseStructures));
        result.put("leaf", new ArrayList<>(leafCourseStructures));
        return result;
    }

    public void buildCourseStructure(TeachingPackageCourseStructureVo teachingPackageCourseStructureVo,
                                     CourseStructure courseStructure, Course course, CourseVersion courseVersion,
                                     QuoteTeachingPackageVo quoteTeachingPackage) {
        courseStructure.setCourseStructureName(teachingPackageCourseStructureVo.getStructureName());
        courseStructure.setShowOrder(teachingPackageCourseStructureVo.getShowOrder());
        courseStructure.setCourse(course);
        courseStructure.setStructureStatus(StructureStatus.NORMAL);
        courseStructure.setCourseVersion(courseVersion);
        courseStructure.setCreatorId(quoteTeachingPackage.getUserId());
        courseStructure.setCreateTime(new Date());
        courseStructure.setCreatorName(quoteTeachingPackage.getUserName());
        courseStructure.setModifierId(quoteTeachingPackage.getUserId());
        courseStructure.setModifyTime(new Date());
        courseStructure.setModifierName(quoteTeachingPackage.getUserName());
    }

    public void buildCourseResource(TeachingPackageCourseResourceVo teachingPackageCourseResourceVo,
                                    CourseResource courseResource, QuoteTeachingPackageVo quoteTeachingPackage) {
        Date now = new Date();
        BeanUtil.copyProperties(teachingPackageCourseResourceVo, courseResource);
        courseResource.setResourceStatus(ResourceStatus.NORMAL);
        courseResource.setIsPublic(Boolean.TRUE);
        courseResource.setResourceDetailId(teachingPackageCourseResourceVo.getResourceDetailId());
        courseResource.setResourceSize(Long.getLong(teachingPackageCourseResourceVo.getFileSize()));
        courseResource.setResourceType(ResourceType.getResourceType(teachingPackageCourseResourceVo.getResourceType()));
        courseResource.setCreatorId(quoteTeachingPackage.getUserId());
        courseResource.setCreatorName(quoteTeachingPackage.getUserName());
        courseResource.setCreateTime(now);
        courseResource.setModifyTime(now);
        courseResource.setModifierId(quoteTeachingPackage.getUserId());
        courseResource.setModifierName(quoteTeachingPackage.getUserName());
        courseResource.setResourceContentNum(StringUtils.isBlank(
                teachingPackageCourseResourceVo.getResourceNum()) ? 0 : Integer.valueOf(teachingPackageCourseResourceVo.getResourceNum()));
        courseResource.setResourceReferences(0);
        courseResource.setResourceOtherReferences(0);
        courseResource.setCourseId(quoteTeachingPackage.getCourseId());
        courseResource.setSourceType(ResourceSourceType.USER_ADDED);
    }

    public Map<String, String> getStructureName(List<CourseStructureVo> courseStructureVos) {
        Map<String, String> structureName = new HashMap<>();
        courseStructureVos.forEach(courseStructureVo -> {
            if (CollectionUtils.isNotEmpty(courseStructureVo.getChildStructureList())) {
                courseStructureVo.getChildStructureList().forEach(courseStructureVoChild -> {
                    if (StringUtils.isBlank(structureName.get(courseStructureVo.getStructureName() + courseStructureVoChild.getStructureName()))) {
                        structureName.put(courseStructureVo.getStructureName() + courseStructureVoChild.getStructureName()
                                , courseStructureVoChild.getStructureId());
                    }
                });
            }
            structureName.put(courseStructureVo.getStructureName(), courseStructureVo.getStructureId());
        });
        return structureName;
    }

    public Map<List<CourseResourceVo>, String> getStructureResource(List<CourseStructureVo> courseStructureVos) {
        Map<List<CourseResourceVo>, String> structureName = new HashMap<>();
        List<CourseResourceVo> courseResourceList = new ArrayList<>();
        courseStructureVos.forEach(courseStructureVo -> {
            if (CollectionUtils.isNotEmpty(courseStructureVo.getChildStructureList())) {
                courseStructureVo.getChildStructureList().forEach(courseStructureVoChild -> {
                    if (CollectionUtils.isNotEmpty(courseStructureVoChild.getCourseResourceList())) {
                        courseStructureVoChild.getCourseResourceList().forEach(resource -> {
                            courseResourceList.add(resource);
                            structureName.put(courseResourceList, courseStructureVo.getStructureName() + courseStructureVoChild.getStructureName());
                        });
                    }
                });
            } else {
                if (CollectionUtils.isNotEmpty(courseStructureVo.getCourseResourceList())) {
                    courseStructureVo.getCourseResourceList().forEach(resource -> {
                        courseResourceList.add(resource);
                        structureName.put(courseResourceList, courseStructureVo.getStructureName());
                    });
                }

            }
        });
        return structureName;
    }

    public void getCourseStructures(CourseStructureVo courseStructureVo, List<CourseStructureVo> courseStructureVos, String parentId) {
        String parent = UUID.randomUUID().toString();
        courseStructureVo.setStructureId(parent);

        if (CollectionUtils.isNotEmpty(courseStructureVo.getChildStructureList())) {
            courseStructureVo.getChildStructureList().forEach(child -> {
                getCourseStructures(child, courseStructureVos, parent);
            });
        }
        if (StringUtils.isBlank(parent)) {
            parentId = UUID.randomUUID().toString();
        }
        courseStructureVo.setParentId(parentId);
        courseStructureVos.add(courseStructureVo);
    }

    public String getIdenticalStructureName(CourseStructureVo courseStructureVo, String resourceName) {
        if (CollectionUtils.isNotEmpty(courseStructureVo.getChildStructureList())) {
            courseStructureVo.getChildStructureList().forEach(childCourseStructureVo -> {
                getIdenticalStructureName(childCourseStructureVo, resourceName);
            });
        }
        AtomicReference<String> parentId = new AtomicReference<String>();
        courseStructureVo.getCourseResourceList().forEach(courseResourceVo -> {
            if (courseResourceVo.getResourceName().equals(resourceName)) {
                parentId.set(courseStructureVo.getStructureId());
            }
        });
        if (StringUtils.isNotBlank(parentId.get())) {
            return parentId.get();
        }
        return null;
    }

    public CourseTeachingPackagePageResource getTeachingPackageMyList(String userId, Integer page, Integer pageSize) {
        List<CourseTeachingPackage> courseTeachingPackages = teachingPackageRepository.findByCreatorId(userId);
        return getCourseTeachingPackagePageResource(page, pageSize, courseTeachingPackages);
    }

    public CourseTeachingPackage findById(String id) {
        Optional<CourseTeachingPackage> optionalCourseTeachingPackage = teachingPackageRepository.findById(id);
        return optionalCourseTeachingPackage.orElse(null);
    }


    public List<CourseTeachingPackage> findByCourseIdAndUserId(String courseId, String userId) {
        return teachingPackageRepository.findByCourseIdAndCreatorId(courseId, userId);
    }

    public TeachingPackageVo getTeachingPackageMyDetail(List<CourseTeachingPackage> teachingPackages, String courseId, String userId) {
        TeachingPackageVo teachingPackageVo = new TeachingPackageVo();
        int maxVisionNum = teachingPackages.stream().mapToInt(CourseTeachingPackage::getVisionNumber).max().orElse(0);
        int sumQuote = teachingPackages.stream().mapToInt(CourseTeachingPackage::getQuoteNum).sum();
        teachingPackages.forEach(courseTeachingPackage -> {
            if (courseTeachingPackage.getVisionNumber() == maxVisionNum) {
                teachingPackageVo.setTeachingPackageName(courseTeachingPackage.getTeachingPackageName());
                teachingPackageVo.setTeachingPackageIntroduction(courseTeachingPackage.getTeachingPackageIntroduction());
                teachingPackageVo.setCourseId(courseTeachingPackage.getCourseId());
                teachingPackageVo.setCourseName(courseTeachingPackage.getCourseName());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                teachingPackageVo.setVisionTime(sdf.format(courseTeachingPackage.getVisionTime()));
                teachingPackageVo.setVisionNumber(courseTeachingPackage.getVisionNumber());
                teachingPackageVo.setUserId(courseTeachingPackage.getCreatorId());
                teachingPackageVo.setUserName(courseTeachingPackage.getCreatorName());
                teachingPackageVo.setQuoteNum(sumQuote);
                teachingPackageVo.setFilePath(courseTeachingPackage.getFilePath());
                teachingPackageVo.setInnerIp(courseTeachingPackage.getInnerIp());
                teachingPackageVo.setOuterIp(courseTeachingPackage.getOuterIp());
                if (StringUtils.isNotEmpty(courseTeachingPackage.getLabelName())) {
                    List<String> labelNames =
                            Arrays.stream(courseTeachingPackage.getLabelName().split(",")).collect(toList());
                    teachingPackageVo.setLabelNames(labelNames);
                }
                setCourseStructureVo(courseTeachingPackage, teachingPackageVo, courseId, userId);
            }
        });
        return teachingPackageVo;
    }
}

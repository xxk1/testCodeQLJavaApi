package com.lztech.site.service.preparelessonplugin;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.domain.model.course.enums.StructureStatus;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.course.specification.CourseResourceSpecification;
import com.lztech.site.service.coursemanagement.CourseManagementService;
import com.lztech.site.service.coursemanagement.ExtendCourseManagementService;
import com.lztech.site.viewmodel.preparelessonplugin.CourseStructureInfo;
import com.lztech.site.viewmodel.preparelessonplugin.PptTeachingContentResource;
import com.lztech.site.viewmodel.preparelessonplugin.TeachingCourseInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class PrepareLessonPluginService {

    @Autowired
    private CourseStructureRepository courseStructureRepository;

    @Autowired
    private CourseResourceRepository courseResourceRepository;

    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;

    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ExtendCourseManagementService extendCourseManagementService;
    @Autowired
    private CourseManagementService courseManagementService;

    //region 保存教学资源信息

    //校验ppt资源信息
    public boolean isNotEmptyPptTeachingContentResource(PptTeachingContentResource pptTeachingContentResource) {
        boolean hasUserId = StringUtils.isNotEmpty(pptTeachingContentResource.getUserId());
        boolean hasUserName = StringUtils.isNotEmpty(pptTeachingContentResource.getUserName());
        boolean hasCourseStructureId = StringUtils.isNotEmpty(pptTeachingContentResource.getCourseStructureId());
        boolean hasFileRealName = StringUtils.isNotEmpty(pptTeachingContentResource.getFileRealName());
        boolean hasResourceType = pptTeachingContentResource.getResourceType() != null;
        boolean hasFileType = StringUtils.isNotEmpty(pptTeachingContentResource.getFileType());
        return (hasUserId && hasUserName && hasCourseStructureId && hasFileRealName && hasResourceType && hasFileType);
    }

    //获取课程结构 : 重载
    public CourseStructure getCourseStructure(String courseStructureId) {
        CourseStructure courseStructure = this.courseStructureRepository
                .findByIdAndStructureStatus(courseStructureId, StructureStatus.NORMAL);
        if (ObjectUtils.isEmpty(courseStructure)) {
            return new CourseStructure();
        } else {
            return courseStructure;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void createPptTeachingContentResource(PptTeachingContentResource pptTeachingContentResource,
                                                 CourseStructure courseStructure) {
        if(pptTeachingContentResource.isIsCover()){
            List<CourseResource> courseResources
                    =   getSameCourseResources(pptTeachingContentResource.getUserId(),pptTeachingContentResource.getFileRealName(),courseStructure);
            List<CourseResourceFile> sameNameFiles
                    = this.getSameNamePpt(courseResources,
                    pptTeachingContentResource.getUserId(),pptTeachingContentResource.getFileRealName(),
                    pptTeachingContentResource.getFileType());
            if (sameNameFiles.size() > 0) {
                for (CourseResourceFile courseResourceFile : sameNameFiles) {
                    courseResourceFile.setResourceStatus(ResourceStatus.DELETE);
                }
                courseResourceFileRepository.saveAll(sameNameFiles);
            }
                for (CourseResource courseResource : courseResources) {
                    courseResource.setResourceStatus(ResourceStatus.DELETE);
                }
                if(courseResources.size()>0) {
                    courseResourceRepository.saveAll(courseResources);
                }
            Date now = new Date();
            CourseResourceFile courseResourceFile = saveCourseResourceFile(pptTeachingContentResource, now);
            saveCourseResources(pptTeachingContentResource, courseStructure,
                    courseResourceFile, now);
        }else{
            Date now = new Date();
            List<String> resourceNames = extendCourseManagementService.getAllResourceNames(
                    pptTeachingContentResource.getUserId(),courseStructure,false);
            String newName = this.buildResourceNewName(resourceNames,pptTeachingContentResource);
            CourseResourceFile courseResourceFile = new CourseResourceFile();
            buildCourseResourceFile(courseResourceFile,pptTeachingContentResource,now);
            courseResourceFile.setFileRealName(newName);
            courseResourceFile = courseResourceFileRepository.saveAndFlush(courseResourceFile);
            CourseResource courseResource = new CourseResource();
            buildCourseResource(courseResource,pptTeachingContentResource,now,courseResourceFile.getId(),courseStructure);
            courseResource.setResourceName(newName);
            courseResourceRepository.saveAndFlush(courseResource);
        }
    }

    public List<CourseResource> getSameCourseResources(String userId,String fileRealName,CourseStructure courseStructure){
        List<CourseResource> courseResources
                = this.courseResourceRepository.findAll(
                CourseResourceSpecification.specificationOwnResources(userId,
                        courseStructure.getCourse().getId(), fileRealName
                        , courseStructure.getCourseVersion(),courseStructure));
        return courseResources;
    }

    private CourseResource saveCourseResources(PptTeachingContentResource pptTeachingContentResource, CourseStructure courseStructure,
                                               CourseResourceFile courseResourceFile, Date now) {
        CourseResource courseResource = new CourseResource();
        courseResource.setCreateTime(now);
        courseResource.setIsPublic(false);
        courseResource.setModifyTime(now);
        courseResource.setCreatorId(pptTeachingContentResource.getUserId());
        courseResource.setCreatorName(pptTeachingContentResource.getUserName());
        courseResource.setModifierId(pptTeachingContentResource.getUserId());
        courseResource.setModifierName(pptTeachingContentResource.getUserName());
        courseResource.setResourceStatus(ResourceStatus.NORMAL);
        courseResource.setCourseStructure(courseStructure);
        courseResource.setResourceType(ResourceType.getResourceType(pptTeachingContentResource.getResourceType()));
        courseResource.setResourceDetailId(courseResourceFile.getId());
        courseResource.setResourceSize(courseResourceFile.getFileSize());
        courseResource.setResourceName(pptTeachingContentResource.getFileRealName());
        return courseResourceRepository.save(courseResource);
    }

    private CourseResourceFile buildCourseResourceFile(CourseResourceFile courseResourceFile,
                                                       PptTeachingContentResource pptTeachingContentResource, Date now){
        courseResourceFile.setCreateTime(now);
        courseResourceFile.setModifyTime(now);
        courseResourceFile.setCreatorId(pptTeachingContentResource.getUserId());
        courseResourceFile.setCreatorName(pptTeachingContentResource.getUserName());
        courseResourceFile.setModifierId(pptTeachingContentResource.getUserId());
        courseResourceFile.setModifierName(pptTeachingContentResource.getUserName());
        courseResourceFile.setResourceStatus(ResourceStatus.NORMAL);
        courseResourceFile.setFilePath(pptTeachingContentResource.getFilePath());
        courseResourceFile.setFileSavedName(pptTeachingContentResource.getFileSavedName());
        courseResourceFile.setFileSize(pptTeachingContentResource.getFileSize().longValue());
        courseResourceFile.setFileType(pptTeachingContentResource.getFileType());
        courseResourceFile.setInnerIp(pptTeachingContentResource.getIntranetFilePath());
        courseResourceFile.setOuterIp(pptTeachingContentResource.getOuterNetFilePath());
        courseResourceFile.setIsPublic(Boolean.FALSE);
        return courseResourceFile;
    }

    private CourseResource buildCourseResource(CourseResource courseResource,
                                               PptTeachingContentResource pptTeachingContentResource,Date now,
                                               String detailId,CourseStructure courseStructure){
        courseResource.setCourseStructure(courseStructure);
        courseResource.setCreateTime(now);
        courseResource.setModifyTime(now);
        courseResource.setCreatorId(pptTeachingContentResource.getUserId());
        courseResource.setCreatorName(pptTeachingContentResource.getUserName());
        courseResource.setModifierId(pptTeachingContentResource.getUserId());
        courseResource.setModifierName(pptTeachingContentResource.getUserName());
        courseResource.setResourceStatus(ResourceStatus.NORMAL);
        courseResource.setResourceReferences(0);
        courseResource.setResourceOtherReferences(0);
        courseResource.setResourceContentNum(1);
        courseResource.setResourceDetailId(detailId);
        courseResource.setResourceSize(pptTeachingContentResource.getFileSize().longValue());
        courseResource.setVisionNumber(0);
        courseResource.setIsPublic(Boolean.FALSE);
        courseResource.setResourceType(ResourceType.getResourceType(pptTeachingContentResource.getResourceType()));
        return courseResource;
    }
    public String buildResourceNewName(List<String> resourceNames,PptTeachingContentResource pptTeachingContentResource){
        String finalName = pptTeachingContentResource.getFileRealName();
        if(!pptTeachingContentResource.getResourceType().equals(ResourceType.CLASS_TEST.getIndex())){
            if(pptTeachingContentResource.getFileRealName().indexOf("(")>0){
                String suffix = pptTeachingContentResource.getFileRealName().substring(pptTeachingContentResource.getFileRealName().indexOf("("));
                String originallyName = pptTeachingContentResource.getFileRealName().replace(suffix,"");
                finalName = originallyName+"."+pptTeachingContentResource.getFileType()+suffix;
            }else{
                finalName = pptTeachingContentResource.getFileRealName()+"."+pptTeachingContentResource.getFileType();
            }
        }
        AtomicReference<String> newName = new AtomicReference<>(courseManagementService.checkZipName(resourceNames, finalName, 1));
        String finalName1 = finalName;
        resourceNames.stream().filter(item->item.equals(finalName1))
                .forEach(item->{
                    if(resourceNames.contains(finalName1+"(1)")){
                        newName.set(courseManagementService.checkZipName(resourceNames, finalName1 + "(1)", 1));
                    }else{
                        newName.set(finalName1 + "(1)");
                    }
                });
        finalName = newName.get();
        resourceNames.add(newName.get());
        if(finalName.indexOf(".")>0&&finalName.indexOf("(")>0){
            String suffixType = finalName.substring(finalName.indexOf("."),finalName.indexOf("("));
            finalName = finalName.replace(suffixType,"");
        }else if(finalName.indexOf(".")>0&&finalName.indexOf("(")<0){
            String suffixType = finalName.substring(finalName.indexOf("."));
            finalName = finalName.replace(suffixType,"");
        }
        return finalName;
    }



    private void selectLikeListSetParentId(CourseResourceFile saveCourseResourceFile, List<CourseResource> resourcesList) {
        List<String> screenLeft = Arrays.stream(saveCourseResourceFile.getFileRealName().split("\\(")).collect(toList());
        List<String> saveIndex=new ArrayList<>();
        for (String left : screenLeft) {
            List<String> collect = Arrays.stream(left.split("\\)")).collect(Collectors.toList());
            List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect1)){
                saveIndex.add(collect1.get(0));
            }
        }
        List<CourseResource> loopCourseResources= new ArrayList<>();
        for (CourseResource resource : resourcesList) {
            List<String> indexNum = Arrays.stream(resource.getResourceName().split("\\(")).collect(Collectors.toList());
            List<String> indexList=new ArrayList<>();
            for (String string : indexNum) {
                List<String> collect = Arrays.stream(string.split("\\)")).collect(Collectors.toList());
                List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(collect1)){
                    indexList.add(collect1.get(0));
                }
            }
            if (indexList.size()==saveIndex.size()+1){
                loopCourseResources.add(resource);
            }
        }
        if (CollectionUtils.isNotEmpty(loopCourseResources)){
            List<String> resourcesDetailIds =
                    loopCourseResources.stream().map(CourseResource::getResourceDetailId).collect(toList());
            List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(resourcesDetailIds);
            courseResourceFiles.forEach(file->{
                file.setParentId(saveCourseResourceFile.getId());
            });
            courseResourceFileRepository.saveAll(courseResourceFiles);
        }
    }

    /**
     * 新增判断重复
     **/
    private List<CourseResource> checkSameNamePaper(String creatorId, CourseStructure courseStructure,
                                                    String name, boolean isIsPublic, String fileType, String resourceId) {
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.specification(creatorId, courseStructure.getCourse().getId(), name, isIsPublic,
                        courseStructure.getCourseVersion(),courseStructure));
        courseResources = courseResources.stream().filter(c -> !resourceId.equals(c.getId())).collect(Collectors.toList());
        return courseResources;
    }


    //校验是否是同名资源：只检查私人，未公开资源
    private boolean checkIsSameFile(PptTeachingContentResource pptTeachingContentResource,
                                    CourseResourceFile courseResourceFile) {
        boolean isSameFileName = pptTeachingContentResource.getFileRealName().equals(courseResourceFile.getFileRealName());
        boolean isSameFileType = StringUtils.isNotEmpty(courseResourceFile.getFileType()) ?
                pptTeachingContentResource.getFileType().toLowerCase().equals(courseResourceFile.getFileType().toLowerCase()) : false;
        boolean isNormal = courseResourceFile.getResourceStatus() == ResourceStatus.NORMAL;
        boolean isSameCreator = pptTeachingContentResource.getUserId().equals(courseResourceFile.getCreatorId());
        boolean isNotPublic = (courseResourceFile.getIsPublic() != null && !courseResourceFile.getIsPublic());
        return (isSameFileName && isSameFileType && isNormal && isSameCreator && isNotPublic);
    }

    private CourseResourceFile saveCourseResourceFile(PptTeachingContentResource pptTeachingContentResource, Date now) {
        CourseResourceFile courseResourceFile = new CourseResourceFile();
        courseResourceFile.setCreateTime(now);
        courseResourceFile.setModifyTime(now);
        courseResourceFile.setCreatorId(pptTeachingContentResource.getUserId());
        courseResourceFile.setCreatorName(pptTeachingContentResource.getUserName());
        courseResourceFile.setModifierId(pptTeachingContentResource.getUserId());
        courseResourceFile.setModifierName(pptTeachingContentResource.getUserName());
        courseResourceFile.setResourceStatus(ResourceStatus.NORMAL);
        courseResourceFile.setFilePath(pptTeachingContentResource.getFilePath());
        courseResourceFile.setFileRealName(pptTeachingContentResource.getFileRealName());
        courseResourceFile.setFileSavedName(pptTeachingContentResource.getFileSavedName());
        courseResourceFile.setFileSize(pptTeachingContentResource.getFileSize().longValue());
        courseResourceFile.setFileType(pptTeachingContentResource.getFileType());
        courseResourceFile.setInnerIp(pptTeachingContentResource.getIntranetFilePath());
        courseResourceFile.setOuterIp(pptTeachingContentResource.getOuterNetFilePath());
        courseResourceFile.setIsPublic(false);
        return courseResourceFileRepository.save(courseResourceFile);
    }

    private CourseResourceFile saveCourseResourceFile(PptTeachingContentResource pptTeachingContentResource, Date now
            , List<CourseResource> courseResources,CourseStructure courseStructure) {

            List<String> detailIds = courseResources
                    .stream()
                    .map(CourseResource::getResourceDetailId)
                    .collect(Collectors.toList());
            List<CourseResourceFile> exitFile = new ArrayList<>();
            List<CourseResourceFile> courseResourceFileList = courseResourceFileRepository.findByIdIn(detailIds);
            for (CourseResourceFile courseResourceFile : courseResourceFileList) {
                if (courseResourceFile.getFileType().equals(pptTeachingContentResource.getFileType())) {
                    exitFile.add(courseResourceFile);
                }
            }
            CourseResourceFile courseResourceFile = new CourseResourceFile();
            if (CollectionUtils.isEmpty(exitFile)){
                courseResourceFile.setVisionNumber(0);
                courseResourceFile.setParentId(null);
                courseResourceFile.setFileRealName(pptTeachingContentResource.getFileRealName());
                courseResourceFile =
                        this.buildCourseResourceFile(pptTeachingContentResource, now, courseResourceFile);
                List<CourseResource> resourceList = checkSameNamePaperLike
                        (pptTeachingContentResource.getUserId(), courseStructure, pptTeachingContentResource.getFileRealName(),
                                false, pptTeachingContentResource.getFileType(), "");
                List<String> resourceDetailIds = resourceList
                        .stream()
                        .map(CourseResource::getResourceDetailId)
                        .collect(Collectors.toList());
                List<CourseResourceFile> exitSaveFile = new ArrayList<>();
                List<CourseResourceFile> resourceFileList = courseResourceFileRepository.findByIdIn(resourceDetailIds);
                for (CourseResourceFile resourceFile : resourceFileList) {
                    if (resourceFile.getFileType().equals(pptTeachingContentResource.getFileType())) {
                        exitSaveFile.add(courseResourceFile);
                    }
                }
                CourseResourceFile saveCourseResourceFile = courseResourceFileRepository.save(courseResourceFile);
                exitSaveFile.forEach(file->{
                    file.setParentId(saveCourseResourceFile.getId());
                });
                courseResourceFileRepository.saveAll(exitSaveFile);
                return saveCourseResourceFile;

            }else {
                return saveCourseResourceFileForNotEmpty(pptTeachingContentResource, now, courseStructure, exitFile, courseResourceFile);
            }
    }

    private CourseResourceFile saveCourseResourceFileForNotEmpty(PptTeachingContentResource pptTeachingContentResource,
                                                                 Date now, CourseStructure courseStructure,
                                                                 List<CourseResourceFile> exitFile,
                                                                 CourseResourceFile courseResourceFile) {
        CourseResourceFile resourceFile = exitFile.get(0);
        List<CourseResourceFile> resourceFilesByParentId =
                courseResourceFileRepository.findByParentIdAndResourceStatus(resourceFile.getId(),ResourceStatus.NORMAL);
        List<String> ids = resourceFilesByParentId.stream().map(CourseResourceFile::getId).collect(Collectors.toList());
        List<CourseResource> courseResourceList = courseResourceRepository.findByResourceDetailIdIsInAndCourseStructure(ids,courseStructure);
        List<CourseResourceFile> judgeFiles=new ArrayList<>();
        resourceFilesByParentId.forEach(file ->{
            courseResourceList.forEach(resource -> {
                if (file.getId().equals(resource.getResourceDetailId())&&resource.getResourceStatus()==ResourceStatus.NORMAL){
                    judgeFiles.add(file);
                }
            });
        } );
        judgeFiles.sort(Comparator.comparingInt(CourseResourceFile::getVisionNumber));
        List<Integer> nowVisionNumberList = judgeFiles
                .stream()
                .map(CourseResourceFile::getVisionNumber)
                .distinct()
                .collect(Collectors.toList());
        int maxNowVisionNumber = nowVisionNumberList.stream().max(Integer::compareTo).orElse(0);
        courseResourceFile.setVisionNumber(maxNowVisionNumber+1);
        courseResourceFile.setParentId(resourceFile.getId());
        courseResourceFile.setFileRealName(pptTeachingContentResource.getFileRealName()+"("+(maxNowVisionNumber+1)+")");
        courseResourceFile =
                this.buildCourseResourceFile(pptTeachingContentResource, now, courseResourceFile);
        CourseResourceFile saveCourseResourceFile = courseResourceFileRepository.save(courseResourceFile);

        List<CourseResource> resources =
                checkParentIdLike(pptTeachingContentResource.getUserId(), courseStructure,
                        pptTeachingContentResource.getFileRealName(), false);
        if (CollectionUtils.isNotEmpty(resources)){
            List<String> screenLeft = Arrays.stream(saveCourseResourceFile.getFileRealName().split("\\(")).collect(toList());
            List<String> saveIndex=new ArrayList<>();
            for (String left : screenLeft) {
                List<String> collect = Arrays.stream(left.split("\\)")).collect(Collectors.toList());
                List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(collect1)){
                    saveIndex.add(collect1.get(0));
                }
            }
            List<CourseResource> loopCourseResources= new ArrayList<>();
            for (CourseResource resource : resources) {
                List<String> indexNum = Arrays.stream(resource.getResourceName().split("\\(")).collect(Collectors.toList());
                List<String> index=new ArrayList<>();
                for (String string : indexNum) {
                    List<String> collect = Arrays.stream(string.split("\\)")).collect(Collectors.toList());
                    List<String> collect1 = collect.stream().filter(s -> StringUtils.isNumeric(s)).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(collect1)){
                        index.add(collect1.get(0));
                    }
                }
                if (index.size()==saveIndex.size()+1){
                    loopCourseResources.add(resource);
                }
            }
            if (CollectionUtils.isNotEmpty(loopCourseResources)){
                List<String> resourcesDetailIds =
                        loopCourseResources.stream().map(CourseResource::getResourceDetailId).collect(toList());
                List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(resourcesDetailIds);
                courseResourceFiles.forEach(file->{
                    file.setParentId(saveCourseResourceFile.getId());
                });
                courseResourceFileRepository.saveAll(courseResourceFiles);
            }
        }

        return saveCourseResourceFile;
    }

    /**
     * 检查父ID
     */

    private List<CourseResource> checkParentIdLike(String creatorId, CourseStructure courseStructure, String name, Boolean isPublic) {
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.specificationlike(creatorId, courseStructure.getCourse().getId(), name, isPublic,
                        courseStructure.getCourseVersion(),courseStructure));
        return courseResources;
    }

    /**
     * 新增模糊判断重复
     **/
    private List<CourseResource> checkSameNamePaperLike(String creatorId, CourseStructure courseStructure,
                                                        String name, boolean isIsPublic, String fileType, String resourceId) {
        if (name.contains("(")) {
            name = StringUtils.substringBeforeLast(name, "(");
        }
        List<CourseResource> courseResources = courseResourceRepository.findAll(
                CourseResourceSpecification.specificationlike(creatorId, courseStructure.getCourse().getId(), name, isIsPublic,
                        courseStructure.getCourseVersion(),courseStructure));
        courseResources = courseResources.stream().filter(c -> !resourceId.equals(c.getId())).collect(Collectors.toList());
        return courseResources;
    }

    private CourseResourceFile buildCourseResourceFile(PptTeachingContentResource pptTeachingContentResource,
                                                       Date now, CourseResourceFile courseResourceFile){
        courseResourceFile.setCreateTime(now);
        courseResourceFile.setModifyTime(now);
        courseResourceFile.setCreatorId(pptTeachingContentResource.getUserId());
        courseResourceFile.setCreatorName(pptTeachingContentResource.getUserName());
        courseResourceFile.setModifierId(pptTeachingContentResource.getUserId());
        courseResourceFile.setModifierName(pptTeachingContentResource.getUserName());
        courseResourceFile.setResourceStatus(ResourceStatus.NORMAL);
        courseResourceFile.setFilePath(pptTeachingContentResource.getFilePath());
        courseResourceFile.setFileSavedName(pptTeachingContentResource.getFileSavedName());
        courseResourceFile.setFileSize(pptTeachingContentResource.getFileSize().longValue());
        courseResourceFile.setFileType(pptTeachingContentResource.getFileType());
        courseResourceFile.setInnerIp(pptTeachingContentResource.getIntranetFilePath());
        courseResourceFile.setOuterIp(pptTeachingContentResource.getOuterNetFilePath());
        courseResourceFile.setIsPublic(false);
        return courseResourceFile;
    }

    //endregion

    //region 获取老师教学团队下课程，结构

    //查寻老师加入的课程
    public List<TeachingCourseInfo> getCourseResourceInfos(String userId) {
        List<TeachingCourseInfo> courseResourceInfos = new ArrayList<>();
        List<CourseTeachingTeam> courseTeachingTeams
                = courseTeachingTeamRepository.findByTeacherIdOrderByCourseId(userId);
        if (CollectionUtils.isNotEmpty(courseTeachingTeams)) {
            courseResourceInfos.addAll(courseTeachingTeams
                    .stream()
                    .map(courseTeachingTeam -> new TeachingCourseInfo() {{
                        this.courseId(courseTeachingTeam.getCourse().getId());
                        this.courseCode(courseTeachingTeam.getCourse().getCourseCode());
                        this.courseName(courseTeachingTeam.getCourse().getCourseName());
                        this.courseTeachingTeamId(courseTeachingTeam.getId());
                        this.setVersionId(Objects.isNull(courseTeachingTeam.getCourseVersion()) ? null :
                                courseTeachingTeam.getCourseVersion().getId());
                    }})
                    .collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                            new TreeSet<>(Comparator.comparing(TeachingCourseInfo::getCourseId))), ArrayList::new)));
        }
        return courseResourceInfos;
    }

    public void setCourseStructuresAndResources(List<TeachingCourseInfo> courseResourceInfos) {
        List<String> courseIds = new ArrayList<>();
        HashMap<String, TeachingCourseInfo> courseResourceInfoMap = new HashMap<>();
        for (TeachingCourseInfo courseResourceInfo : courseResourceInfos) {
            courseIds.add(courseResourceInfo.getCourseId());
            courseResourceInfoMap.put(courseResourceInfo.getCourseId(), courseResourceInfo);
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
                List<CourseStructure> courseStructures = courseStructureList
                        .stream()
                        .filter(s -> s.getCourse().getId().equals(course.getId()))
                        .sorted(Comparator.comparing(CourseStructure::getShowOrder))
                        .collect(Collectors.toList());
                for (CourseStructure courseStructure : courseStructures) {
                    if (courseStructure.getStructureStatus().equals(StructureStatus.NORMAL)) {
                        CourseStructureInfo courseStructureInfo = this.getCourseStructureInfo(courseStructure);
                        if (StringUtils.isEmpty(courseStructureInfo.getParentId())) {
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

    //endregion

    //region 检查课程结构下该老师是否存在重名的PPT（私有）

    //获取重复的ppt
    public List<CourseResourceFile> getSameNamePpt(List<CourseResource> courseResources,
                                                   String userId, String fileRealName, String fileType) {

        List<CourseResourceFile> sameNameFiles = new ArrayList<>();
        if (CollectionUtils.isEmpty(courseResources)) {
            return sameNameFiles;
        } else {
            List<String> detailIds = new ArrayList<>();
            HashMap<String, CourseResource> courseResourceMap = new HashMap<>();
            for (CourseResource courseResource : courseResources) {
                if (StringUtils.isNotEmpty(courseResource.getResourceDetailId())
                        && !courseResourceMap.containsKey(courseResource.getResourceDetailId())) {
                    detailIds.add(courseResource.getResourceDetailId());
                    courseResourceMap.put(courseResource.getResourceDetailId(), courseResource);
                }
            }
            List<CourseResourceFile> courseResourceFileList = this.courseResourceFileRepository.findByIdIn(detailIds);
            for (CourseResourceFile courseResourceFile : courseResourceFileList) {
                if (this.checkIsSameFile(fileRealName, fileType, userId, courseResourceFile)) {
                    sameNameFiles.add(courseResourceFile);
                }
            }
            return sameNameFiles;
        }
    }

    private boolean checkIsSameFile(String fileRealName, String fileType, String userId,
                                    CourseResourceFile courseResourceFile) {
        boolean isSameFileName = fileRealName.equals(courseResourceFile.getFileRealName());
        boolean isSameFileType = StringUtils.isNotEmpty(courseResourceFile.getFileType()) ?
                fileType.toLowerCase().equals(courseResourceFile.getFileType().toLowerCase()) : false;
        boolean isNormal = courseResourceFile.getResourceStatus() == ResourceStatus.NORMAL;
        boolean isSameCreator = userId.equals(courseResourceFile.getCreatorId());
        boolean isNotPublic = (courseResourceFile.getIsPublic() != null && !courseResourceFile.getIsPublic());
        return (isSameFileName && isSameFileType && isNormal && isSameCreator && isNotPublic);
    }
    //endregion
}
package com.lztech.site.service.teacherpreparelessons;

import com.lztech.domain.model.course.CourseResource;
import com.lztech.domain.model.course.CourseResourceFile;
import com.lztech.domain.model.course.CourseTheme;
import com.lztech.domain.model.course.CourseThemeDetail;
import com.lztech.domain.model.course.enums.ResourceSourceType;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.domain.model.course.enums.TranscodeStatus;
import com.lztech.persistence.repositories.course.CourseResourceFileRepository;
import com.lztech.persistence.repositories.course.CourseResourceRepository;
import com.lztech.persistence.repositories.coursetheme.CourseThemeRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.service.coursemanagement.ExtendCourseManagementService;
import com.lztech.site.service.coursemanagement.TranscodeService;
import com.lztech.site.viewmodel.teacherpreparelessons.CopiedCourseResourceDetailVo;
import com.lztech.site.viewmodel.teacherpreparelessons.CopiedCourseResourceVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class TeacherPrepareLessonsService {

    @Autowired
    private CourseThemeRepository courseThemeRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Autowired
    private ExtendCourseManagementService extendCourseManagementService;
    @Autowired
    private TranscodeService transcodeService;

    public Result copyTeacherCourseResource(CopiedCourseResourceVo copiedCourseResourceVo) {
        Map<Integer, List<CopiedCourseResourceDetailVo>> resourceTypeMap =
                copiedCourseResourceVo.getResourceList().stream().collect(Collectors.groupingBy(CopiedCourseResourceDetailVo::getResourceType));
        List<String> resourceIdList =
                copiedCourseResourceVo.getResourceList().stream().map(CopiedCourseResourceDetailVo::getResourceId).collect(Collectors.toList());
        List<CourseResource> oldCourseResourceList = courseResourceRepository.findByIdIn(resourceIdList);
        //被复制课程材料他人引用次数+1
        oldCourseResourceList.forEach(oldCourseResource ->
                oldCourseResource.setResourceOtherReferences(oldCourseResource.getResourceOtherReferences() == null ?
                        1 : (oldCourseResource.getResourceOtherReferences() + 1))
        );
        courseResourceRepository.saveAll(oldCourseResourceList);

        List<CourseResource> newCourseResourceList = new ArrayList<>();
        Date now = new Date();
        resourceTypeMap.forEach((type, resources) -> {
            List<String> contentIdList =
                    resources.stream().map(CopiedCourseResourceDetailVo::getResourceDetailId).collect(Collectors.toList());
            if (ResourceType.THEME.getIndex().equals(type)) {
                // 复制主题，复制资源
                handleCopyCourseTheme(copiedCourseResourceVo, oldCourseResourceList, newCourseResourceList, now, resources, contentIdList);
            } else if (ResourceType.CLASS_TEST.getIndex().equals(type)) {
                handleCopyClassTestPaper(copiedCourseResourceVo, oldCourseResourceList, newCourseResourceList, now, resources);
            } else {
                handleCopyResourceFile(copiedCourseResourceVo, oldCourseResourceList, newCourseResourceList, now, resources, contentIdList);
            }
        });
        courseResourceRepository.saveAll(newCourseResourceList);
        return Result.success();
    }

    private void handleCopyResourceFile(CopiedCourseResourceVo copiedCourseResourceVo, List<CourseResource> oldCourseResourceList,
                                        List<CourseResource> newCourseResourceList, Date now, List<CopiedCourseResourceDetailVo> resources,
                                        List<String> contentIdList) {
        List<CourseResourceFile> oldFileList = courseResourceFileRepository.findByIdIn(contentIdList);
        List<CourseResourceFile> newFileList = new ArrayList<>();
        for (CourseResourceFile courseResourceFile : oldFileList) {
            CourseResource oldCourseResource = oldCourseResourceList
                    .stream()
                    .filter(r -> courseResourceFile.getId().equals(r.getResourceDetailId()))
                    .findFirst()
                    .orElse(null);
            if (Objects.isNull(oldCourseResource)) {
                continue;
            }
            List<String> resourceNames = extendCourseManagementService.getAllResourceNames(copiedCourseResourceVo.getOperatorId(),
                    oldCourseResource.getCourseStructure(), null);
            String newName = buildResourceNewName(resourceNames, courseResourceFile.getFileRealName(), courseResourceFile.getFileType());
            CourseResourceFile newCourseResourceFile = copyTeacherCourseResourceFile(copiedCourseResourceVo, now, courseResourceFile, newName);
            newFileList.add(newCourseResourceFile);
        }
        List<CourseResourceFile> newCourseResourceFileList = courseResourceFileRepository.saveAll(newFileList);
        List<CourseResourceFile> transcodeFiles = new ArrayList<>();
        newCourseResourceFileList.forEach(file -> {
            if (file.getTranscodeStatus() == TranscodeStatus.TRANS_CODING) {
                transcodeFiles.add(file);
            }
        });
        if (transcodeFiles.size() > 0) {
            transcodeService.sendTranscodeMqRequestMessage(transcodeFiles);
        }

        for (CopiedCourseResourceDetailVo resource : resources) {
            CourseResource oldCourseResource = filterOldCourseResource(oldCourseResourceList, resource.getResourceId());
            if (Objects.isNull(oldCourseResource)) {
                continue;
            }
            CourseResourceFile newCourseResourceFile = newCourseResourceFileList
                    .stream().filter(c -> resource.getResourceDetailId().equals(c.getCopiedResourceFileId())).findFirst().orElse(null);
            if (Objects.isNull(newCourseResourceFile)) {
                continue;
            }
            CourseResource newCourseResource = buildCopiedCourseResource(copiedCourseResourceVo, now, oldCourseResource,
                    newCourseResourceFile.getId(), newCourseResourceFile.getFileRealName());
            newCourseResourceList.add(newCourseResource);
        }
    }

    public String buildResourceNewName(List<String> resourceNames, String fileRealName, String fileType) {
        String finalName = fileRealName;
        if (finalName.indexOf("(") > 0) {
            String suffix = fileRealName.substring(fileRealName.indexOf("("));
            String originallyName = fileRealName.replace(suffix, "");
            finalName = originallyName + "." + fileType + suffix;
        } else {
            finalName = fileRealName + "." + fileType;
        }
        AtomicReference<String> newName = new AtomicReference<>(checkFileName(resourceNames, finalName, 1));
        String finalName1 = finalName;
        resourceNames.stream().filter(item -> item.equals(finalName1)).forEach(item -> {
            if (resourceNames.contains(finalName1 + "(1)")) {
                newName.set(checkFileName(resourceNames, finalName1 + "(1)", 1));
            } else {
                newName.set(finalName1 + "(1)");
            }
        });
        finalName = newName.get();
        resourceNames.add(newName.get());
        if (finalName.lastIndexOf(".") > 0 && finalName.indexOf("(") > 0) {
            String suffixType = finalName.substring(finalName.lastIndexOf("."), finalName.indexOf("("));
            finalName = finalName.replace(suffixType, "");
        } else if (finalName.lastIndexOf(".") > 0 && !finalName.contains("(")) {
            String suffixType = finalName.substring(finalName.lastIndexOf("."));
            finalName = finalName.replace(suffixType, "");
        }
        return finalName;
    }

    public String checkFileName(List<String> allResourceName, String fileName, int j) {
        if (!allResourceName.contains(fileName)) {
            return fileName;
        }
        if (decide(fileName)) {//文件名字没有(2)的形式
            return fileName = checkFileName(allResourceName, fileName + "(" + j + ")", j++); //递归生成文件名字
        }
        String numCountString = fileName.substring(fileName.lastIndexOf("(") + 1, fileName.lastIndexOf(")"));
        int numCount = -1;
        if (StringUtils.isNotBlank(numCountString)) {
            numCount = Integer.parseInt(numCountString);
        }
        if (numCount >= 1) { //是数字，不是字符串，截取(x)之前的字符  在numCount这个数字的基础上实现自增
            String realName = fileName.substring(0, fileName.lastIndexOf("("));
            final int two = 2;
            return checkFileName(allResourceName, realName + "(" + ++numCount + ")", two); //递归生成文件名字
        }
        return checkFileName(allResourceName, fileName + "(" + j + ")", j++); //递归生成文件名字
    }

    private boolean decide(String fileName) {
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

    private void handleCopyClassTestPaper(CopiedCourseResourceVo copiedCourseResourceVo,
                                          List<CourseResource> oldCourseResourceList,
                                          List<CourseResource> newCourseResourceList,
                                          Date now,
                                          List<CopiedCourseResourceDetailVo> resources) {
        for (CopiedCourseResourceDetailVo resource : resources) {
            CourseResource oldCourseResource = filterOldCourseResource(oldCourseResourceList, resource.getResourceId());
            if (Objects.isNull(oldCourseResource)) {
                continue;
            }
            List<String> testPaperNameList =
                    courseResourceRepository.findByCreatorIdAndCourseId(oldCourseResource.getCourseStructure().getCourse().getId(),
                            oldCourseResource.getCourseStructure().getCourseVersion().getId(), copiedCourseResourceVo.getOperatorId(),
                            ResourceType.CLASS_TEST.getIndex());
            String newTestPaperName = checkNewTestPaperName(testPaperNameList, oldCourseResource.getResourceName());
            CourseResource newCourseResource = buildCopiedCourseResource(copiedCourseResourceVo, now, oldCourseResource,
                    resource.getCopiedResourceDetailId(), newTestPaperName);
            newCourseResourceList.add(newCourseResource);
        }
    }

    private String checkNewTestPaperName(List<String> testPaperNameList, String resourceName) {
        String uniqueName = resourceName;
        int count = 1;
        while (testPaperNameList.contains(uniqueName)) {
            uniqueName = resourceName + "(" + count + ")";
            count++;

        }
        return uniqueName;
    }

    public static String getUniqueName(String name, List<String> dbNames) {
        String uniqueName = name;
        int count = 1;
        while (dbNames.contains(uniqueName)) {
            uniqueName = name + "(" + count + ")";
            count++;
        }
        return uniqueName;
    }


    private void handleCopyCourseTheme(CopiedCourseResourceVo copiedCourseResourceVo, List<CourseResource> oldCourseResourceList,
                                       List<CourseResource> newCourseResourceList, Date now, List<CopiedCourseResourceDetailVo> resources,
                                       List<String> contentIdList) {
        List<CourseTheme> oldThemeList = courseThemeRepository.findByIdIn(contentIdList);
        List<CourseTheme> newCourseThemeList = new ArrayList<>();
        for (CourseTheme oldCourseTheme : oldThemeList) {
            CourseResource oldCourseResource = oldCourseResourceList
                    .stream()
                    .filter(c -> oldCourseTheme.getId().equals(c.getResourceDetailId()))
                    .findFirst()
                    .orElse(null);
            if (Objects.isNull(oldCourseResource)) {
                continue;
            }
            CourseTheme newCourseTheme = copyTeacherCourseTheme(copiedCourseResourceVo, now, oldCourseTheme);
            newCourseThemeList.add(newCourseTheme);
        }
        List<CourseTheme> savedCourseThemeList = courseThemeRepository.saveAll(newCourseThemeList);

        for (CopiedCourseResourceDetailVo resource : resources) {
            CourseResource oldCourseResource = filterOldCourseResource(oldCourseResourceList, resource.getResourceId());
            if (Objects.isNull(oldCourseResource)) {
                continue;
            }
            CourseTheme courseTheme = savedCourseThemeList
                    .stream()
                    .filter(c -> resource.getResourceDetailId().equals(c.getCopiedThemeId()))
                    .findFirst()
                    .orElse(null);
            if (Objects.isNull(courseTheme)) {
                continue;
            }
            CourseResource newCourseResource = buildCopiedCourseResource(copiedCourseResourceVo, now, oldCourseResource,
                    courseTheme.getId(), null);
            newCourseResourceList.add(newCourseResource);
        }
    }

    private CourseResource filterOldCourseResource(List<CourseResource> oldCourseResourceList, String resourceId) {
        return oldCourseResourceList
                .stream()
                .filter(c -> resourceId.equals(c.getId()))
                .findFirst()
                .orElse(null);
    }

    private CourseResourceFile copyTeacherCourseResourceFile(CopiedCourseResourceVo copiedCourseResourceVo,
                                                             Date now,
                                                             CourseResourceFile courseResourceFile, String newName) {
        CourseResourceFile newCourseResourceFile = new CourseResourceFile();
        newCourseResourceFile.setFileSize(courseResourceFile.getFileSize());
        newCourseResourceFile.setFileType(courseResourceFile.getFileType());
        newCourseResourceFile.setFilePath(courseResourceFile.getFilePath());
        newCourseResourceFile.setFileRealName(newName);
        newCourseResourceFile.setFileSavedName(courseResourceFile.getFileSavedName());
        newCourseResourceFile.setInnerIp(courseResourceFile.getInnerIp());
        newCourseResourceFile.setOuterIp(courseResourceFile.getOuterIp());
        newCourseResourceFile.setResourceStatus(ResourceStatus.NORMAL);
        newCourseResourceFile.setIsPublic(false);
        newCourseResourceFile.setVisionNumber(courseResourceFile.getVisionNumber());
        newCourseResourceFile.setParentId(courseResourceFile.getParentId());
        newCourseResourceFile.setModifierId(copiedCourseResourceVo.getOperatorId());
        newCourseResourceFile.setModifierName(copiedCourseResourceVo.getOperatorName());
        newCourseResourceFile.setModifyTime(now);
        newCourseResourceFile.setCreatorId(copiedCourseResourceVo.getOperatorId());
        newCourseResourceFile.setCreatorName(copiedCourseResourceVo.getOperatorName());
        newCourseResourceFile.setCreateTime(now);
        newCourseResourceFile.setCopiedResourceFileId(courseResourceFile.getId());
        newCourseResourceFile.setTranscodeFilePath(courseResourceFile.getTranscodeFilePath());
        newCourseResourceFile.setTranscodeFileType(courseResourceFile.getTranscodeFileType());
        newCourseResourceFile.setTranscodeFileSize(courseResourceFile.getTranscodeFileSize());
        newCourseResourceFile.setTranscodeFileSavedName(courseResourceFile.getTranscodeFileSavedName());
        newCourseResourceFile.setTranscodeInnerIp(courseResourceFile.getTranscodeInnerIp());
        newCourseResourceFile.setTranscodeOuterIp(courseResourceFile.getTranscodeOuterIp());
        newCourseResourceFile.setTranscodeStatus(courseResourceFile.getTranscodeStatus());
        return newCourseResourceFile;
    }

    private CourseTheme copyTeacherCourseTheme(CopiedCourseResourceVo copiedCourseResourceVo, Date now, CourseTheme oldCourseTheme) {
        CourseTheme newCourseTheme = new CourseTheme();
        newCourseTheme.setThemeName(oldCourseTheme.getThemeName());
        newCourseTheme.setThemeDesc(oldCourseTheme.getThemeDesc());
        newCourseTheme.setThemeStatus(ResourceStatus.NORMAL);
        newCourseTheme.setThemeDetailNum(oldCourseTheme.getThemeDetailNum());
        newCourseTheme.setCopiedThemeId(oldCourseTheme.getId());
        newCourseTheme.setCourseThemeDetailList(buildCopiedCourseThemeDetailList(oldCourseTheme.getCourseThemeDetailList(), newCourseTheme));
        newCourseTheme.setModifierId(copiedCourseResourceVo.getOperatorId());
        newCourseTheme.setModifierName(copiedCourseResourceVo.getOperatorName());
        newCourseTheme.setModifyTime(now);
        newCourseTheme.setCreatorId(copiedCourseResourceVo.getOperatorId());
        newCourseTheme.setCreatorName(copiedCourseResourceVo.getOperatorName());
        newCourseTheme.setCreateTime(now);
        return newCourseTheme;
    }

    private List<CourseThemeDetail> buildCopiedCourseThemeDetailList(List<CourseThemeDetail> courseThemeDetailList,
                                                                     CourseTheme newCourseTheme) {
        List<CourseThemeDetail> resultList = new ArrayList<>();
        courseThemeDetailList.forEach(detail -> {
            CourseThemeDetail newCourseThemeDetail = new CourseThemeDetail();
            newCourseThemeDetail.setThemeDetailContent(detail.getThemeDetailContent());
            newCourseThemeDetail.setShowOrder(detail.getShowOrder());
            newCourseThemeDetail.setCourseTheme(newCourseTheme);
            newCourseThemeDetail.setCreatorId(newCourseTheme.getCreatorId());
            newCourseThemeDetail.setCreatorName(newCourseTheme.getCreatorName());
            newCourseThemeDetail.setCreateTime(newCourseTheme.getCreateTime());
            resultList.add(newCourseThemeDetail);
        });
        return resultList;
    }

    private CourseResource buildCopiedCourseResource(CopiedCourseResourceVo copiedCourseResourceVo, Date now, CourseResource oldCourseResource,
                                                     String newResourceDetailId, String newFileName) {
        CourseResource newCourseResource = new CourseResource();
        newCourseResource.setResourceStatus(ResourceStatus.NORMAL);
        newCourseResource.setIsPublic(false);
        newCourseResource.setResourceType(oldCourseResource.getResourceType());
        newCourseResource.setResourceDetailId(newResourceDetailId);
        newCourseResource.setResourceName(StringUtils.isNotBlank(newFileName) ? newFileName : oldCourseResource.getResourceName());
        newCourseResource.setResourceSize(oldCourseResource.getResourceSize());
        newCourseResource.setResourceContentNum(oldCourseResource.getResourceContentNum());
        newCourseResource.setCourseStructure(oldCourseResource.getCourseStructure());
        newCourseResource.setResourceReferences(0);
        newCourseResource.setResourceOtherReferences(0);
        newCourseResource.setVisionNumber(oldCourseResource.getVisionNumber());
        newCourseResource.setParentId(oldCourseResource.getParentId());
        newCourseResource.setCourseId(oldCourseResource.getCourseId());
        newCourseResource.setSourceType(ResourceSourceType.USER_COPIED);
        newCourseResource.setSourceId(oldCourseResource.getId());
        newCourseResource.setModifierId(oldCourseResource.getModifierId());
        newCourseResource.setModifierName(oldCourseResource.getModifierName());
        newCourseResource.setModifyTime(oldCourseResource.getModifyTime());
        newCourseResource.setCreatorId(copiedCourseResourceVo.getOperatorId());
        newCourseResource.setCreatorName(copiedCourseResourceVo.getOperatorName());
        newCourseResource.setCreateTime(now);
        newCourseResource.setCitedUserId(oldCourseResource.getCreatorId());
        newCourseResource.setCitedUserName(oldCourseResource.getCreatorName());
        CourseResource resource = courseResourceRepository.findTop1ByCreatorIdAndCourseStructureIdAndResourceStatusOrderBySortDesc(
                copiedCourseResourceVo.getOperatorId(),
                oldCourseResource.getCourseStructure().getId(),
                ResourceStatus.NORMAL);
        if (ObjectUtils.isNotEmpty(resource)){
            newCourseResource.setSort(resource.getSort()+1);
        }else {
            newCourseResource.setSort(0);
        }
        return newCourseResource;
    }
}

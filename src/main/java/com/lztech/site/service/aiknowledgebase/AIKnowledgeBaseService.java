package com.lztech.site.service.aiknowledgebase;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.ResourceSourceType;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.persistence.repositories.course.CourseMaterialRepository;
import com.lztech.persistence.repositories.course.CourseResourceFileRepository;
import com.lztech.persistence.repositories.course.CourseStructureRepository;
import com.lztech.persistence.repositories.course.TeachingMaterialFileRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.aiknowledgebase.AIKnowledgeBaseParam;
import com.lztech.site.viewmodel.aiknowledgebase.AIKnowledgeBaseVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AIKnowledgeBaseService {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseResourceFileRepository courseResourceFileRepository;
    @Autowired
    private TeachingMaterialFileRepository teachingMaterialFileRepository;

    private final int STUDENT_TYPE = 1;

    public List<AIKnowledgeBaseVo> getAIKnowledgeBaseList(
            Course course,CourseVersion courseVersion, AIKnowledgeBaseParam aiKnowledgeBaseParam) {
        List<AIKnowledgeBaseVo> aiKnowledgeBaseVoList = new ArrayList<>();
        List<Integer> aiKnowledgeBaseTypeIndexList = new ArrayList<>();
        Integer aiKnowledgeBaseType = aiKnowledgeBaseParam.getAiKnowledgeBaseType();

        if (ObjectUtils.isEmpty(aiKnowledgeBaseType)) {
            aiKnowledgeBaseTypeIndexList.add(0);
            aiKnowledgeBaseTypeIndexList.add(1);
        } else {
            aiKnowledgeBaseTypeIndexList.add(aiKnowledgeBaseType);
        }
        String courseId = course.getId();
        String userId = aiKnowledgeBaseParam.getUserId();
        String courseVersionId = courseVersion.getId();
        for (Integer filterAiKnowledgeBaseTypeIndex : aiKnowledgeBaseTypeIndexList) {
            if (filterAiKnowledgeBaseTypeIndex == 0) {
                //课程教材
                CourseMaterial courseMaterial = courseMaterialRepository.findByCourseIdAndVersionId(courseId, courseVersionId);
                if (ObjectUtils.isNotEmpty(courseMaterial)) {
                    List<TeachingMaterialFile> teachingMaterialFileList = courseMaterial.getTeachingMaterialFileList();
                    for (TeachingMaterialFile teachingMaterialFile : teachingMaterialFileList) {
                        AIKnowledgeBaseVo aiKnowledgeBaseVo = new AIKnowledgeBaseVo();
                        composeCourseMaterialAIKnowledgeBaseVo(userId, teachingMaterialFile, aiKnowledgeBaseVo);
                        aiKnowledgeBaseVoList.add(aiKnowledgeBaseVo);
                    }
                }
            }
            if (filterAiKnowledgeBaseTypeIndex == 1) {
                //课件资源
                List<CourseResource> allCourseResourceList = new ArrayList<>();
                List<CourseStructure> courseStructureList = courseStructureRepository.findByCourseIdAndCourseVersionId(courseId, courseVersionId);
                if (CollectionUtils.isNotEmpty(courseStructureList)) {
                    for (CourseStructure courseStructure : courseStructureList) {
                        List<CourseResource> courseResources = courseStructure.getCourseResources();
                        if(aiKnowledgeBaseParam.getUserType() == STUDENT_TYPE){
                            courseResources = courseResources
                                    .stream()
                                    .filter(courseResource -> courseResource.getResourceStatus().equals(ResourceStatus.NORMAL) &&
                                            courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE))
                                    .sorted(Comparator.comparing(CourseResource::getSort,
                                            Comparator.nullsFirst(Integer::compareTo)).reversed())
                                    .collect(Collectors.toList());
                        }else {
                            courseResources = courseResources
                                    .stream()
                                    .filter(courseResource -> courseResource.getResourceStatus().equals(ResourceStatus.NORMAL) &&
                                            courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE) &&
                                            courseResource.getCreatorId().equals(userId)).sorted(Comparator.comparing(CourseResource::getSort,
                                            Comparator.nullsFirst(Integer::compareTo)).reversed())
                                    .collect(Collectors.toList());
                        }
                        allCourseResourceList.addAll(courseResources);
                    }
                }
                List<String> detailIds = allCourseResourceList
                        .stream()
                        .map(CourseResource::getResourceDetailId)
                        .collect(Collectors.toList());
                List<CourseResourceFile> courseResourceFiles = courseResourceFileRepository.findByIdIn(detailIds);
                for (CourseResource courseResource : allCourseResourceList) {
                    CourseResourceFile file = courseResourceFiles
                            .stream()
                            .filter(courseResourceFile -> courseResourceFile.getId().equals(courseResource.getResourceDetailId()))
                            .findFirst()
                            .orElse(null);
                    if (ObjectUtils.isNotEmpty(file)) {
                        AIKnowledgeBaseVo aiKnowledgeBaseVo = new AIKnowledgeBaseVo();
                        composeCourseResourceAIKnowledgeBaseVo(courseResource, aiKnowledgeBaseVo, file);
                        aiKnowledgeBaseVoList.add(aiKnowledgeBaseVo);
                    }
                }
            }
        }
        aiKnowledgeBaseVoList = filterAIKnowledgeBaseVoList(aiKnowledgeBaseVoList, aiKnowledgeBaseParam);
        return aiKnowledgeBaseVoList;

    }

    private List<AIKnowledgeBaseVo> filterAIKnowledgeBaseVoList(
            List<AIKnowledgeBaseVo> aiKnowledgeBaseVoList, AIKnowledgeBaseParam aiKnowledgeBaseParam) {
        aiKnowledgeBaseVoList = aiKnowledgeBaseVoList.stream().filter(aiKnowledgeBaseVo -> {


            Integer aiKnowledgeBaseType = aiKnowledgeBaseParam.getAiKnowledgeBaseType();
            Integer sourceType = aiKnowledgeBaseParam.getSourceType();
            boolean flag = true;
            if (StringUtils.isNotEmpty(aiKnowledgeBaseParam.getSearchStr())) {
                flag = aiKnowledgeBaseVo.getResourceName().contains(aiKnowledgeBaseParam.getSearchStr());
            }
            if (flag && StringUtils.isNotEmpty(aiKnowledgeBaseParam.getStartDate())) {
                String startDateTime = aiKnowledgeBaseParam.getStartDate() + " 00:00:00";
                flag = DateUtils.stringToDate("yyyy-MM-dd HH:mm:ss", startDateTime)
                        .getTime() <= DateUtils.stringToDate("yyyy-MM-dd HH:mm:ss", aiKnowledgeBaseVo.getCreateTime()).getTime();
            }
            if (flag && StringUtils.isNotEmpty(aiKnowledgeBaseParam.getEndDate())) {
                String endDateTime = aiKnowledgeBaseParam.getEndDate() + " 23:59:59";
                flag = DateUtils.stringToDate("yyyy-MM-dd HH:mm:ss", endDateTime)
                        .getTime() >= DateUtils.stringToDate("yyyy-MM-dd HH:mm:ss", aiKnowledgeBaseVo.getCreateTime()).getTime();
            }
            if (flag && ObjectUtils.isNotEmpty(aiKnowledgeBaseType)) {
                flag = Objects.equals(aiKnowledgeBaseVo.getAiKnowledgeBaseTypeIndex(), aiKnowledgeBaseType);
            }
            if (flag && ObjectUtils.isNotEmpty(sourceType)) {
                flag = Objects.equals(aiKnowledgeBaseVo.getSourceType(), sourceType);
            }
            return flag;


        }).collect(Collectors.toList());
        return aiKnowledgeBaseVoList;
    }

    private static void composeCourseMaterialAIKnowledgeBaseVo(
            String userId, TeachingMaterialFile teachingMaterialFile, AIKnowledgeBaseVo aiKnowledgeBaseVo) {
        aiKnowledgeBaseVo.setId(teachingMaterialFile.getId());
        aiKnowledgeBaseVo.setResourceName(teachingMaterialFile.getFileDisplayName());
        aiKnowledgeBaseVo.setAiKnowledgeBaseTypeIndex(0);
        aiKnowledgeBaseVo.setAiKnowledgeBaseTypeName("课程教材");
        aiKnowledgeBaseVo.setInnerIp(teachingMaterialFile.getInnerUrl());
        aiKnowledgeBaseVo.setOuterIp(teachingMaterialFile.getOuterUrl());
        aiKnowledgeBaseVo.setFilePath(teachingMaterialFile.getFilePath());
        aiKnowledgeBaseVo.setFileType(teachingMaterialFile.getFileType());
        if (teachingMaterialFile.getCreatorId().equals(userId)) {
            aiKnowledgeBaseVo.setSourceType(0);
        } else {
            aiKnowledgeBaseVo.setSourceType(1);
        }
        aiKnowledgeBaseVo.setCreatorId(teachingMaterialFile.getCreatorId());
        aiKnowledgeBaseVo.setCreatorName(teachingMaterialFile.getCreatorName());
        aiKnowledgeBaseVo.setCreateTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", teachingMaterialFile.getCreateTime()));
    }

    private static void composeCourseResourceAIKnowledgeBaseVo(
            CourseResource courseResource, AIKnowledgeBaseVo aiKnowledgeBaseVo, CourseResourceFile file) {
        aiKnowledgeBaseVo.setId(courseResource.getId());
        aiKnowledgeBaseVo.setResourceName(courseResource.getResourceName());
        aiKnowledgeBaseVo.setAiKnowledgeBaseTypeIndex(1);
        aiKnowledgeBaseVo.setAiKnowledgeBaseTypeName("课件资源");
        aiKnowledgeBaseVo.setInnerIp(file.getInnerIp());
        aiKnowledgeBaseVo.setOuterIp(file.getOuterIp());
        aiKnowledgeBaseVo.setFilePath(file.getFilePath());
        aiKnowledgeBaseVo.setFileType(file.getFileType());
        Integer sourceType = courseResource.getSourceType().equals(ResourceSourceType.USER_ADDED) ? 0 : 1;
        aiKnowledgeBaseVo.setSourceType(sourceType);
        aiKnowledgeBaseVo.setCreatorId(file.getCreatorId());
        aiKnowledgeBaseVo.setCreatorName(file.getCreatorName());
        aiKnowledgeBaseVo.setCreateTime(DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", file.getCreateTime()));
    }
}

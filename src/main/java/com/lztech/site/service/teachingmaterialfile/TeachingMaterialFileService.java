package com.lztech.site.service.teachingmaterialfile;

import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.CourseVersionStatus;
import com.lztech.domain.model.course.enums.FileContentStatus;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.course.TeachingMaterialFileRepository;
import com.lztech.persistence.repositories.coursestructuremapping.CourseStructureMappingRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.viewmodel.teachingmaterialfile.TeachingMaterialFileContentResource;
import com.lztech.site.viewmodel.teachingmaterialfile.TeachingMaterialFileResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeachingMaterialFileService {
    @Autowired
    private TeachingMaterialFileRepository teachingMaterialFileRepository;

    @Autowired
    private CourseVersionRepository courseVersionRepository;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseStructureMappingRepository courseStructureMappingRepository;

    public List<TeachingMaterialFileResource> getTeachingMaterialFileResourceList(String courseId) {
        List<TeachingMaterialFileResource> teachingMaterialFileResourceList = new ArrayList<>();
        Course course = courseRepository.findByIdAndUseState(courseId, true);
        if (ObjectUtils.isEmpty(course)) {
            return teachingMaterialFileResourceList;
        }
        CourseVersion courseVersion = courseVersionRepository.findFirstByCourseInAndCourseVersionStatus(
                Collections.singletonList(course), CourseVersionStatus.IN_USE);
        if (ObjectUtils.isEmpty(courseVersion)) {
            return teachingMaterialFileResourceList;
        }

        String courseVersionId = courseVersion.getId();
        List<CourseStructureMapping> courseStructureMappingList = courseStructureMappingRepository.findByCourseIdAndCourseVersionId(
                courseId, courseVersionId);
        List<TeachingMaterialFile> teachingMaterialFileList = teachingMaterialFileRepository.findData(courseVersionId);
        if (CollectionUtils.isNotEmpty(teachingMaterialFileList)) {
            for (TeachingMaterialFile teachingMaterialFile : teachingMaterialFileList) {
                TeachingMaterialFileResource teachingMaterialFileResource = new TeachingMaterialFileResource();
                teachingMaterialFileResource.setTeachingMaterialFileId(teachingMaterialFile.getId());
                teachingMaterialFileResource.setFileDisplayName(teachingMaterialFile.getFileDisplayName());
                teachingMaterialFileResource.setFileRealName(teachingMaterialFile.getFileRealName());
                teachingMaterialFileResource.setFileType(teachingMaterialFile.getFileType());
                teachingMaterialFileResource.setInnerUrl(teachingMaterialFile.getInnerUrl());
                teachingMaterialFileResource.setOuterUrl(teachingMaterialFile.getOuterUrl());
                teachingMaterialFileResource.setFilePath(teachingMaterialFile.getFilePath());
                List<TeachingMaterialFileContent> teachingMaterialFileContentList = teachingMaterialFile.getTeachingMaterialFileContentList();
                teachingMaterialFileContentList = teachingMaterialFileContentList.stream()
                        .filter(teachingMaterialFileContent ->
                                teachingMaterialFileContent.getFileContentStatus().equals(FileContentStatus.NORMAL))
                        .sorted(Comparator.comparingInt(TeachingMaterialFileContent::getChapterIndex))
                        .collect(Collectors.toList());
                List<TeachingMaterialFileContentResource> teachingMaterialFileContentResourceList = new ArrayList<>();
                for (TeachingMaterialFileContent teachingMaterialFileContent : teachingMaterialFileContentList) {
                    TeachingMaterialFileContentResource teachingMaterialFileContentResource =
                            getTeachingMaterialFileContentResource(teachingMaterialFileContent, courseStructureMappingList);
                    teachingMaterialFileContentResourceList.add(teachingMaterialFileContentResource);
                }
                teachingMaterialFileResource.setTeachingMaterialFileContentList(teachingMaterialFileContentResourceList);
                teachingMaterialFileResourceList.add(teachingMaterialFileResource);
            }

        }
        return teachingMaterialFileResourceList;

    }

    private TeachingMaterialFileContentResource getTeachingMaterialFileContentResource(
            TeachingMaterialFileContent teachingMaterialFileContent,
            List<CourseStructureMapping> courseStructureMappingList) {
        CourseStructureMapping courseStructureMapping = courseStructureMappingList.stream()
                .filter(mapping -> mapping.getOcrStructureId().equals(teachingMaterialFileContent.getId()))
                .max(Comparator.comparing(CourseStructureMapping::getCreateTime))
                .orElse(null);
        TeachingMaterialFileContentResource teachingMaterialFileContentResource = new TeachingMaterialFileContentResource();
        teachingMaterialFileContentResource.setTeachingMaterialFileContentId(teachingMaterialFileContent.getId());
        teachingMaterialFileContentResource.setChapterName(teachingMaterialFileContent.getChapterName());
        teachingMaterialFileContentResource.setChapterIndex(teachingMaterialFileContent.getChapterIndex());
        teachingMaterialFileContentResource.setFileType(teachingMaterialFileContent.getFileType());
        teachingMaterialFileContentResource.setContentInnerUrl(teachingMaterialFileContent.getContentInnerUrl());
        teachingMaterialFileContentResource.setContentOuterUrl(teachingMaterialFileContent.getContentOuterUrl());
        teachingMaterialFileContentResource.setContentPath(teachingMaterialFileContent.getContentPath());
        if (Objects.nonNull(courseStructureMapping)) {
            teachingMaterialFileContentResource.setMappingStructureId(courseStructureMapping.getCourseCustomStructureId());
        }
        return teachingMaterialFileContentResource;
    }
}

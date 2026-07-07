package com.lztech.site.service.coursestructuremapping;

import com.lztech.domain.model.course.CourseStructureMapping;
import com.lztech.persistence.repositories.coursestructuremapping.CourseStructureMappingRepository;
import com.lztech.site.viewmodel.coursestructuremapping.CourseStructureMappingDetailResource;
import com.lztech.site.viewmodel.coursestructuremapping.CourseStructureMappingParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseStructureMappingService {

    @Autowired
    private CourseStructureMappingRepository courseStructureMappingRepository;


    public List<CourseStructureMappingDetailResource> getCourseStructureMappingList(
            String courseId, String versionId) {
        List<CourseStructureMapping> courseStructureMappingList = courseStructureMappingRepository.findByCourseIdAndCourseVersionId(
                courseId, versionId);
        if (CollectionUtils.isEmpty(courseStructureMappingList)) {
            return new ArrayList<>();
        }
        return courseStructureMappingList.stream().map(courseStructureMapping -> {
            CourseStructureMappingDetailResource mappingDetailResource = new CourseStructureMappingDetailResource();
            mappingDetailResource.setOcrStructureId(courseStructureMapping.getOcrStructureId());
            mappingDetailResource.setCourseStructureId(courseStructureMapping.getCourseCustomStructureId());
            return mappingDetailResource;
        }).collect(Collectors.toList());

    }

    @Transactional
    public void createCourseStructureMapping(CourseStructureMappingParam param) {
        courseStructureMappingRepository.deleteByCourseIdAndCourseVersionId(param.getCourseId(), param.getVersionId());
        Date now = new Date();
        List<CourseStructureMapping> courseStructureMappingList = param.getMappingList().stream().map(mappingDetailResource -> {
            CourseStructureMapping courseStructureMapping = new CourseStructureMapping();
            courseStructureMapping.setCourseId(param.getCourseId());
            courseStructureMapping.setCourseVersionId(param.getVersionId());
            courseStructureMapping.setModifierId(param.getOperatorId());
            courseStructureMapping.setModifierName(param.getOperatorName());
            courseStructureMapping.setModifyTime(now);
            courseStructureMapping.setCreatorId(param.getOperatorId());
            courseStructureMapping.setCreatorName(param.getOperatorName());
            courseStructureMapping.setCreateTime(now);
            courseStructureMapping.setOcrStructureId(mappingDetailResource.getOcrStructureId());
            courseStructureMapping.setCourseCustomStructureId(mappingDetailResource.getCourseStructureId());
            return courseStructureMapping;
        }).collect(Collectors.toList());
        courseStructureMappingRepository.saveAll(courseStructureMappingList);
    }
}

package com.lztech.site.service.teachingresearchsection;

import com.lztech.domain.model.teachingresearchcourse.TeachingResearchCourse;
import com.lztech.domain.model.teachingresearchsection.TeachingResearchSection;
import com.lztech.persistence.repositories.teachingresearchsection.TeachingResearchCourseRepository;
import com.lztech.persistence.repositories.teachingresearchsection.TeachingResearchSectionRepository;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.teachingresearchsection.TeachingResearchCourseVo;
import com.lztech.site.viewmodel.teachingresearchsection.TeachingResearchSectionPage;
import com.lztech.site.viewmodel.teachingresearchsection.TeachingResearchSectionResource;
import com.lztech.site.viewmodel.teachingresearchsection.TeachingResearchSectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeachingResearchSectionService {
    @Autowired
    private TeachingResearchSectionRepository teachingResearchSectionRepository;
    @Autowired
    private TeachingResearchCourseRepository teachingResearchCourseRepository;

    @Transactional
    public ResponseEntity<Void> teachingResearchSectionAddOrUpdate(TeachingResearchSectionVo teachingResearchSectionVo) {
        Date date = new Date();
        TeachingResearchSection teachingResearchSection = new TeachingResearchSection();
        if (!StringUtils.isEmpty(teachingResearchSectionVo.getId())) {
            teachingResearchSection = teachingResearchSectionRepository.findById(teachingResearchSectionVo.getId()).orElse(null);
            TeachingResearchSection oldTeachingResearchSection = teachingResearchSectionRepository.findByTeachingResearchName(
                    teachingResearchSectionVo.getTeachingResearchName());
            if (!StringUtils.isEmpty(oldTeachingResearchSection) && !oldTeachingResearchSection.getId().equals(teachingResearchSectionVo.getId())) {
                return new ResponseEntity(ErrorResult.customError("教研室名称已存在"), HttpStatus.CONFLICT);
            }
        } else {
            TeachingResearchSection oldTeachingResearchSection = teachingResearchSectionRepository.findByTeachingResearchName(
                    teachingResearchSectionVo.getTeachingResearchName());
            if (!StringUtils.isEmpty(oldTeachingResearchSection)) {
                return new ResponseEntity(ErrorResult.customError("教研室名称已存在"), HttpStatus.CONFLICT);
            }
            teachingResearchSection.setCreateTime(date);
            teachingResearchSection.setCreatorId(teachingResearchSectionVo.getUserId());
            teachingResearchSection.setCreatorName(teachingResearchSectionVo.getUserName());
        }
        teachingResearchSection.setCollegeId(teachingResearchSectionVo.getCollegeId());
        teachingResearchSection.setCollegeName(teachingResearchSectionVo.getCollegeName());
        teachingResearchSection.setTeachingResearchName(teachingResearchSectionVo.getTeachingResearchName());
        teachingResearchSection.setModifierId(teachingResearchSectionVo.getUserId());
        teachingResearchSection.setModifierName(teachingResearchSectionVo.getUserName());
        teachingResearchSection.setModifyTime(date);
        teachingResearchSectionRepository.saveAndFlush(teachingResearchSection);
        teachingResearchCourseRepository.deleteAllByTeachingResearchSection(teachingResearchSection);
        List<TeachingResearchCourse> teachingResearchCourseList = new ArrayList<>();
        for (TeachingResearchCourseVo teachingResearchCourseVo : teachingResearchSectionVo.getTeachingResearchCourseVoList()) {
            TeachingResearchCourse teachingResearchCourse = new TeachingResearchCourse();
            teachingResearchCourse.setCourseId(teachingResearchCourseVo.getCourseId());
            teachingResearchCourse.setCourseName(teachingResearchCourseVo.getCourseName());
            teachingResearchCourse.setCourseCode(teachingResearchCourseVo.getCourseCode());
            teachingResearchCourse.setTeachingResearchSection(teachingResearchSection);
            teachingResearchCourse.setCreateTime(date);
            teachingResearchCourse.setCreatorId(teachingResearchSectionVo.getUserId());
            teachingResearchCourse.setCreatorName(teachingResearchSectionVo.getUserName());
            teachingResearchCourseList.add(teachingResearchCourse);
        }
        teachingResearchCourseRepository.saveAll(teachingResearchCourseList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<TeachingResearchSectionPage> getTeachingResearchSectionList(Integer page, Integer pageSize) {
        TeachingResearchSectionPage teachingResearchSectionPage = new TeachingResearchSectionPage();
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<TeachingResearchSection> teachingResearchSections = teachingResearchSectionRepository.findByOrderByModifyTimeDesc(pageable);

        teachingResearchSectionPage.setTotal((int) teachingResearchSections.getTotalElements());
        teachingResearchSectionPage.setPageSize(pageSize);
        teachingResearchSectionPage.setPage(page);
        teachingResearchSectionPage.setTeachingResearchSectionResourceList(getTeachingResearchSectionResources(teachingResearchSections));
        return new ResponseEntity<>(teachingResearchSectionPage, HttpStatus.OK);
    }

    private List<TeachingResearchSectionResource> getTeachingResearchSectionResources(Page<TeachingResearchSection> teachingResearchSections) {
        List<TeachingResearchSectionResource> teachingResearchSectionResourceList = new ArrayList<>();
        teachingResearchSections.forEach(teachingResearchSection -> {
            TeachingResearchSectionResource teachingResearchSectionResource = new TeachingResearchSectionResource();
            List<TeachingResearchCourseVo> teachingResearchCourseVoList = new ArrayList<>();
            teachingResearchSectionResource.setId(teachingResearchSection.getId());
            teachingResearchSectionResource.setTeachingResearchName(teachingResearchSection.getTeachingResearchName());
            teachingResearchSectionResource.setCollegeId(teachingResearchSection.getCollegeId());
            teachingResearchSectionResource.setCollegeName(teachingResearchSection.getCollegeName());
            teachingResearchSection.getTeachingResearchCourseList().forEach(teachingResearchCourse -> {
                TeachingResearchCourseVo teachingResearchCourseVo = new TeachingResearchCourseVo();
                teachingResearchCourseVo.setCourseId(teachingResearchCourse.getCourseId());
                teachingResearchCourseVo.setCourseCode(teachingResearchCourse.getCourseCode());
                teachingResearchCourseVo.setCourseName(teachingResearchCourse.getCourseName());
                teachingResearchCourseVoList.add(teachingResearchCourseVo);
            });

            teachingResearchSectionResource.setTeachingResearchCourseVoList(teachingResearchCourseVoList);
            teachingResearchSectionResourceList.add(teachingResearchSectionResource);
        });
        return teachingResearchSectionResourceList;
    }

    public ResponseEntity<Void> deleteTeachingResearchSection(String id) {
        TeachingResearchSection teachingResearchSection = teachingResearchSectionRepository.findById(id).orElse(null);
        if (StringUtils.isEmpty(teachingResearchSection)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        teachingResearchSectionRepository.deleteById(id);
        teachingResearchCourseRepository.deleteAllByTeachingResearchSection(teachingResearchSection);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
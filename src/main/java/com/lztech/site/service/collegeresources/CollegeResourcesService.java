package com.lztech.site.service.collegeresources;

import com.lztech.domain.model.college.College;
import com.lztech.domain.model.college.enums.CourseInfoCollege;
import com.lztech.site.resource.collegeresources.CollegeResources;
import com.lztech.persistence.repositories.college.CollegeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CollegeResourcesService {

    @Autowired
    CollegeRepository collegeRepository;

    public ResponseEntity<List<CollegeResources>> getCollegeResources(String collegeName,Integer courseInfoCollege) {
        List<CollegeResources> collegeManagements = new ArrayList<>();
        List<College> colleges = collegeRepository.findAll();
        List<College> filterCollege;
        Stream<College> filterCollegeCollection = colleges.stream();
        if(!StringUtils.isEmpty(collegeName)){
            filterCollegeCollection = filterCollegeCollection.filter(college -> college.getCollegeName().contains(collegeName.trim()));
        }
        if(courseInfoCollege != null){
            filterCollegeCollection = filterCollegeCollection.filter(college -> college.getCourseInfoCollege().getIndex() == courseInfoCollege);
        }
        filterCollege = filterCollegeCollection.collect(Collectors.toList());
        for (College college : filterCollege) {
            CollegeResources collegeResources = new CollegeResources();
            collegeResources.setCollegeId(college.getId());
            collegeResources.setCollegeCode(college.getCollegeCode());
            collegeResources.setCollegeName(college.getCollegeName());
            collegeResources.setCourseInfoCollege(college.getCourseInfoCollege().getIndex());
            collegeResources.setCourseTableCollege(college.getCourseTableCollege().getIndex());
            collegeManagements.add(collegeResources);
        }

        return new ResponseEntity<>(collegeManagements, HttpStatus.OK);
    }
}

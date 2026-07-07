package com.lztech.site.service.coursetype;

import com.lztech.domain.model.coursetype.CourseType;
import com.lztech.persistence.repositories.coursetype.CourseTypeRepository;
import com.lztech.site.viewmodel.coursetype.CourseTypeResource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseTypeService {

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    public ResponseEntity<List<CourseTypeResource>> findAllCourseTypeResource() {
        List<CourseType> courseTypeList = courseTypeRepository.findAll();
        List<CourseTypeResource> courseTypeResourceList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(courseTypeList)) {
            List<CourseTypeResource> finalCourseTypeResourceList = courseTypeResourceList;
            courseTypeList.forEach(courseType -> {
                CourseTypeResource courseTypeResource = new CourseTypeResource();
                courseTypeResource.setId(String.valueOf(courseType.getId()));
                courseTypeResource.setCourseTypeName(courseType.getCourseTypeName());
                courseTypeResource.setImagePath(courseType.getImagePath());
                courseTypeResource.setSort(courseType.getSortName());
                finalCourseTypeResourceList.add(courseTypeResource);
            });
            courseTypeResourceList = finalCourseTypeResourceList
                    .stream()
                    .sorted(Comparator.comparing(CourseTypeResource::getSort))
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(courseTypeResourceList, HttpStatus.OK);
    }
}

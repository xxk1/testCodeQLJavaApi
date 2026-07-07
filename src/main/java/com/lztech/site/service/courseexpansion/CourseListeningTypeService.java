package com.lztech.site.service.courseexpansion;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseExpansion;
import com.lztech.domain.model.course.enums.CourseExpansionEnum;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.courseexpansion.CourseExpansionRepository;
import com.lztech.site.viewmodel.courseexpansion.CourseListeningTypeResource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseListeningTypeService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseExpansionRepository courseExpansionRepository;

    public CourseListeningTypeResource getCourseListeningTypeResource(String courseId) {
        CourseListeningTypeResource resource = new CourseListeningTypeResource();
        Optional<Course> courseOptional = courseRepository.findByIdAndUseStateIsTrue(courseId);
        if (!courseOptional.isPresent()) {
            resource.setExpansionKey(CourseExpansionEnum.UNKNOWN_COURSE.getKey());
            resource.setListeningTypeName(CourseExpansionEnum.UNKNOWN_COURSE.getValue());
            return resource;
        }
        Course course = courseOptional.get();
        resource.setCourseId(course.getId());
        resource.setCourseCode(course.getCourseCode());
        resource.setCourseName(course.getCourseName());

        CourseExpansion expansion = courseExpansionRepository.findByCourseId(course.getId());
        if (ObjectUtils.isEmpty(expansion)) {
            resource.setExpansionKey(CourseExpansionEnum.UNKNOWN_COURSE.getKey());
            resource.setListeningTypeName(CourseExpansionEnum.UNKNOWN_COURSE.getValue());
            return resource;
        }
        resource.setExpansionKey(expansion.getExpansionKey());
        resource.setListeningTypeName(expansion.getExpansionValue());
        return resource;
    }
}


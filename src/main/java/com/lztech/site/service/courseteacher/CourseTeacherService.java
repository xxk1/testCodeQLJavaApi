package com.lztech.site.service.courseteacher;

import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.site.resource.courseteacher.CourseTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CourseTeacherService {

    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;
    @Value("${studentType}")
    private String studentType;

    public ResponseEntity<List<CourseTeacher>> getCourseList(String userId) {

        List<CourseTableDetail> courseTableDetails = courseTableDetailRepository.findByTeacherId(userId);
        if (courseTableDetails.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseTableDetails = courseTableDetails.stream()
                .sorted(Comparator.comparing(courseTableDetail ->
                        courseTableDetail.getCourseTable().getCourse().getCreateTime()))
                .collect(toList());

        List<CourseTeacher> courseTeacherList = new ArrayList<>();
        for (CourseTableDetail courseTableDetail : courseTableDetails) {
            CourseTeacher courseTeacher = new CourseTeacher();
            if (courseTableDetail.getCourseTable() != null) {
                CourseTable courseTable = courseTableDetail.getCourseTable();
                courseTeacher.setCourseId(courseTable.getCourse() == null ? "" : courseTable.getCourse().getId());
                courseTeacher.setCourseName(courseTable.getCourse() == null ? "" : courseTable.getCourse().getCourseName());
                courseTeacher.setCourseCategory(courseTable.getCourseCategory() == null ? null : courseTable.getCourseCategory().getId());
                courseTeacher.setCourseCode(courseTable.getCourse() == null ? null : courseTable.getCourse().getCourseCode());
                courseTeacherList.add(courseTeacher);
            }
        }
        courseTeacherList = courseTeacherList.stream().distinct().collect(toList());
        courseTeacherList.sort(Comparator.comparing(CourseTeacher::getCourseId));
        return new ResponseEntity<>(courseTeacherList, HttpStatus.OK);
    }

}

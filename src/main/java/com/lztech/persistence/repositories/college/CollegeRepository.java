package com.lztech.persistence.repositories.college;

import com.lztech.domain.model.college.College;
import com.lztech.domain.model.college.enums.CourseInfoCollege;
import com.lztech.domain.model.college.enums.CourseTableCollege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeRepository extends JpaRepository<College, String> {

    List<College> findByCourseInfoCollegeAndCourseTableCollege(CourseInfoCollege courseInfoCollege,
                                                               CourseTableCollege courseTableCollege);

    College findByCollegeName(String collegeName);

    College findByCollegeCode(String collegeCode);

    List<College> findAllByIdIn(List<String> ids);

    List<College> findByCollegeNameIn(List<String> collegeNameList);

    List<College> findAllByIdInAndCourseInfoCollege(List<String> ids,CourseInfoCollege courseInfoCollege);

}

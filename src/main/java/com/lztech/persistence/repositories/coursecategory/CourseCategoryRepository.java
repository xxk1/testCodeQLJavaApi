package com.lztech.persistence.repositories.coursecategory;

import com.lztech.domain.model.coursecategory.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, String> {
    CourseCategory findById(Integer id);
    CourseCategory findByCourseCategoryName(String name);
    List<CourseCategory> findAllByCourseCategoryNameIn(List<String> nameList);
}

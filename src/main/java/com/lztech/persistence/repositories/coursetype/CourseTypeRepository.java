package com.lztech.persistence.repositories.coursetype;

import com.lztech.domain.model.coursetype.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTypeRepository extends JpaRepository<CourseType, Integer>{
}

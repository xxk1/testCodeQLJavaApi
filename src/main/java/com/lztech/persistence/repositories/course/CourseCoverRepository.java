package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.CourseCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCoverRepository extends JpaRepository<CourseCover, String>, JpaSpecificationExecutor {

}

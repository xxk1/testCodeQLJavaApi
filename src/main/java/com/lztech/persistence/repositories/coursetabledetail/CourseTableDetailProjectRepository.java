package com.lztech.persistence.repositories.coursetabledetail;

import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetail.CourseTableDetailProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseTableDetailProjectRepository extends JpaRepository<CourseTableDetailProject,String> {

    @Transactional
    @Modifying
    void deleteByCourseTableDetail(CourseTableDetail courseTableDetail);

    List<CourseTableDetailProject> findByCourseTableDetailIdIn(List<String> courseTableDetailIdList);
}

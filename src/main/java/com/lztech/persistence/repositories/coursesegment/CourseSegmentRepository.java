package com.lztech.persistence.repositories.coursesegment;

import com.lztech.domain.model.coursesegment.CourseSegment;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseSegmentRepository extends JpaRepository<CourseSegment, String>, JpaSpecificationExecutor {
    List<CourseSegment> findByCourseTableDetail(CourseTableDetail courseTableDetail);

    List<CourseSegment> findByCourseTableDetailIn(List<CourseTableDetail> courseTableDetailList);

    @Transactional
    @Modifying
    void deleteByCourseTableDetail(CourseTableDetail courseTableDetail);
}

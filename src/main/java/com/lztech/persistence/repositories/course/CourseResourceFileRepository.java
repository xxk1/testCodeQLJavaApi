package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.CourseResourceFile;
import com.lztech.domain.model.course.enums.ResourceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface CourseResourceFileRepository extends JpaRepository<CourseResourceFile, String>, JpaSpecificationExecutor {
    @Transactional
    @Modifying
    @Query(value = "update tb_course_resource_file set file_real_name = :fileName , modify_time= :date" +
            " where id=:resourceDetailId", nativeQuery = true)
    void updateFileName(@Param("fileName") String fileName,
                        @Param("resourceDetailId") String resourceDetailId,
                        @Param("date") Date date);

    List<CourseResourceFile> findByIdIn(List<String> ids);
    List<CourseResourceFile> findByParentId(String parentId);
    List<CourseResourceFile> findByParentIdAndResourceStatus(String parentId, ResourceStatus resourceStatus);

    CourseResourceFile findByIdAndResourceStatus(String fileId,ResourceStatus resourceStatus);
}

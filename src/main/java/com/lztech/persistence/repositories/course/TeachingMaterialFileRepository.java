package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.TeachingMaterialFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeachingMaterialFileRepository extends JpaRepository<TeachingMaterialFile, String>,
        JpaSpecificationExecutor<TeachingMaterialFile> {


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = " delete from tb_teaching_material_file where id in (?1)")
    void deleteTeachingMaterialFileByIdList(List<String> idList);

    @Query(nativeQuery = true,
            value = "select a.* from tb_teaching_material_file a " +
                    " inner join tb_course_material b on a.course_material_id = b.id " +
                    " where b.course_version_id =:courseVersionId " +
                    " and a.ocr_status =2 order by file_display_name ")
    List<TeachingMaterialFile> findData(@Param("courseVersionId") String courseVersionId);

    List<TeachingMaterialFile> findAllByIdIn(List<String> idList);
}

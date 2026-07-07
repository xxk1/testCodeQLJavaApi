package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.CourseStructure;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.StructureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseStructureRepository extends JpaRepository<CourseStructure, String>, JpaSpecificationExecutor {
    Integer countByParentIdAndStructureStatus(String parentId, StructureStatus structureStatus);

    CourseStructure findByIdAndStructureStatus(String id, StructureStatus structureStatus);

    List<CourseStructure> findByIdInAndStructureStatus(List<String> ids, StructureStatus structureStatus);

    List<CourseStructure> findByCourseIdAndStructureStatusAndParentIdIsNullOrderByShowOrder(String courseId, StructureStatus structureStatus);

    List<CourseStructure> findByStructureStatusAndParentId( StructureStatus structureStatus,String parentId);

    List<CourseStructure> findByCourseIdAndStructureStatus(String courseId, StructureStatus structureStatus);

    List<CourseStructure> findByCourseIdAndStructureStatusAndCourseVersion(String courseId, StructureStatus structureStatus,
                                                                           CourseVersion courseVersion);

    List<CourseStructure> findByStructureStatus(StructureStatus structureStatus);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_course_structure set course_version_id=?2 " +
            " where course_id =?1 and course_version_id is null")
    void updateCourseVersion(String courseId, String courseVersionId);

    int countByIdAndStructureStatus(String id, StructureStatus structureStatus);

    @Query(nativeQuery = true, value = "select max(show_order) from tb_course_structure " +
            " where course_id=?1 and course_version_id=?2 ")
    Integer getMaxShowOrderWithoutParentId(String id, String id1);

    @Query(nativeQuery = true, value = "select max(show_order) from tb_course_structure " +
            " where course_id=?1 and course_version_id=?2 and parent_id=?3 ")
    Integer getMaxShowOrderWithParentId(String id, String id1, String parentId);

    List<CourseStructure> findByCourseIdAndCourseVersionIdAndStructureStatus(String courseId,
                                                                             String versionId,
                                                                             StructureStatus structureStatus);

    @Query(nativeQuery = true, value = "select * from tb_course_structure " +
            " where course_id=?1 and course_version_id=?2 and parent_id is null order by structure_status ,show_order")
    List<CourseStructure> findTopByCourseIdAndCourseVersion(String courseId,
                                                            String versionId);

    @Query(nativeQuery = true, value = "select * from tb_course_structure " +
            " where course_id=:courseId and course_version_id in (:versionIdList) and structure_status=:structureStatus and parent_id is null")
    List<CourseStructure> findTopByCourseIdListAndCourseVersion(@Param("courseId") String courseId,
                                                            @Param("versionIdList") List<String> versionIdList,
                                                            @Param("structureStatus") Integer structureStatus);

    @Query(nativeQuery = true, value = "select * from tb_course_structure cs " +
            " inner join tb_course_version cv on cs.course_version_id = cv.id " +
            " where course_version_status=1 and cs.course_id=?1 and structure_status=?2")
    List<CourseStructure> getInUseVersionCourseStructure(String courseId, Integer structureStatus);

    @Query(nativeQuery = true, value = "select * from tb_course_structure cs " +
            " inner join tb_course_version cv on cs.course_version_id = cv.id " +
            " where course_version_status=1 and cs.course_id in (?1) and structure_status=0")
    List<CourseStructure> getInUseVersionCourseStructureByCourseIds(List<String> courseIdList);


    List<CourseStructure> findByCourseIdAndCourseVersionId(String courseId,String versionId);

}

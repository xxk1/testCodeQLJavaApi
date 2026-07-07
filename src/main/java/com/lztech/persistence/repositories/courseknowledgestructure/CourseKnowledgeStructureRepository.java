package com.lztech.persistence.repositories.courseknowledgestructure;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseKnowledgeStructure;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.domain.model.course.enums.KnowledgeStructureType;
import com.lztech.domain.model.course.enums.StructureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseKnowledgeStructureRepository extends JpaRepository<CourseKnowledgeStructure, String> {

    @Query(nativeQuery = true, value = "select max(show_order) from tb_course_knowledge_structure " +
            " where course_id=?1 and course_version_id=?2 ")
    Integer getMaxShowOrderWithoutParentId(String courseId, String versionId);


    int countByIdAndStructureStatus(String id, StructureStatus structureStatus);

    @Query(nativeQuery = true, value = "select max(show_order) from tb_course_knowledge_structure " +
            " where course_id=?1 and course_version_id=?2 and parent_id=?3 ")
    Integer getMaxShowOrderWithParentId(String courseId, String versionId, String parentId);


    @Query(nativeQuery = true, value = "select * from tb_course_knowledge_structure " +
            " where course_id=?1 and course_version_id=?2 and structure_status=0 ")
    List<CourseKnowledgeStructure> findByCourseIdAndVersionId(String courseId, String versionId);


    int countByParentIdAndStructureStatus(String id, StructureStatus structureStatus);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_course_knowledge_structure set structure_status=1 ,modifier_id=?2,modifier_name=?3,modify_time=?4" +
            " where id =?1")
    void updateCourseKnowledgeStructureStatus(String id, String operatorId, String operatorName, Date now);

    @Query(nativeQuery = true, value = "select  count(1) from tb_course_knowledge_structure where course_id=?1 and course_version_id=?2 and " +
            "structure_status=0 and knowledge_structure_type=?3")
    int countByCourseIdAndVersionIdAndType(String courseId, String versionId, Integer structureType);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update tb_course_knowledge_structure set content=?2 ," +
            " modifier_id=?3,modifier_name=?4,modify_time=?5 " +
            " where id =?1")
    void updateCourseKnowledgeStructureContent(String id,
                                               String content,
                                               String operatorId,
                                               String operatorName,
                                               Date now);

    @Query(nativeQuery = true, value = "select * from tb_course_knowledge_structure " +
            " where course_id=?1 and course_version_id=?2 and structure_status=0" +
            " and knowledge_structure_type in (0,1) ")
    List<CourseKnowledgeStructure> findByCourseIdAndVersionIdWithoutPoint(String courseId, String versionId);

    List<CourseKnowledgeStructure>
    findByKnowledgeStructureTypeAndStructureStatusAndAndCourseAndCourseVersion
            (KnowledgeStructureType knowledgeStructureType, StructureStatus structureStatus, Course course, CourseVersion courseVersion);

    List<CourseKnowledgeStructure> findByIdIn(List<String> ids);

    @Query(nativeQuery = true, value = "SELECT a.* FROM tb_course_knowledge_structure a  " +
            " INNER JOIN tb_course_version b ON a.course_version_id = b.id " +
            " INNER JOIN tb_course c ON c.id = b.course_id " +
            " WHERE a.knowledge_structure_type = 2  " +
            " AND b.course_version_status = 1  " +
            " and a.create_time>=:startDate and a.create_time<=:endDate and a.creator_id in (:teacherIdList)")
    List<CourseKnowledgeStructure> findCreateTimeAndTeacherIdIn(@Param("startDate") Date startDate,
                                                                @Param("endDate") Date endDate,
                                                                @Param("teacherIdList") List<String> teacherIdList);
    @Query(nativeQuery = true, value = "SELECT a.* FROM tb_course_knowledge_structure a  " +
            "INNER JOIN tb_course_version b on a.course_version_id = b.id " +
            "LEFT JOIN tb_course_knowledge_graph_domain c  on c.course_id =a.course_id " +
            "where c.id is  null and a.structure_status = 0 and b.course_version_status=1  ORDER BY a.course_id,a.knowledge_structure_type ")
    List<CourseKnowledgeStructure> findEffectiveDataList();
    @Query(nativeQuery = true, value = "SELECT a.* FROM tb_course_knowledge_structure a  " +
            "INNER JOIN tb_course_version b on a.course_version_id = b.id  " +
            "where  a.structure_status = 0 and b.course_version_status=1   ORDER BY a.course_id,a.knowledge_structure_type ")
    List<CourseKnowledgeStructure> findAllDataList();
}

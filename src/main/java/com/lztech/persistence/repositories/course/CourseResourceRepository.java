package com.lztech.persistence.repositories.course;

import com.lztech.domain.model.course.CourseResource;
import com.lztech.domain.model.course.CourseStructure;
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
public interface CourseResourceRepository extends JpaRepository<CourseResource, String>
        , JpaSpecificationExecutor<CourseResource> {

    @Transactional
    @Modifying
    @Query(value = "update tb_course_resource set resource_name = :fileName , modify_time= :modifyTime where id=:fileId", nativeQuery = true)
    void updateByFileName(@Param("fileName") String fileName, @Param("fileId") String fileId, @Param("modifyTime") Date modifyTime);

    List<CourseResource> findByIdInAndResourceStatus(List<String> ids, ResourceStatus resourceStatus);

    List<CourseResource> findByResourceDetailIdInAndResourceStatus(List<String> ids, ResourceStatus status);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = " update tb_course_resource" +
            " set resource_content_num =resource_content_num-1" +
            " where resource_detail_id in :classTestPaperIdList ")
    void updateQuestionNum(@Param("classTestPaperIdList") List<String> classTestPaperIdList);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = " update tb_course_resource" +
            " set resource_content_num = :questionNum , modify_time=:modifyTime" +
            " where resource_detail_id = :classTestPaperId ")
    void updateQuestionNum(@Param("classTestPaperId") String classTestPaperId,
                           @Param("modifyTime") Date modifyTime,
                           @Param("questionNum") Integer questionNum);

    List<CourseResource> findByResourceDetailIdAndResourceStatus(String resourceId, ResourceStatus status);

    CourseResource findByResourceDetailIdAndResourceStatusAndCourseStructure
            (String resourceId, ResourceStatus status, CourseStructure courseStructure);

    CourseResource findByIdAndResourceStatus(String id, ResourceStatus status);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = " update tb_course_resource SET resource_references = ifnull(resource_references,0)+1 " +
            " where resource_detail_id = ?1")
    void updateReferenceNumByResourceDetailId(String resourceDetailId);


    @Transactional
    @Modifying
    @Query(value = "update tb_course_resource set is_public = :isPublic , modify_time= :date" +
            " where id =:resourceDetailId", nativeQuery = true)
    void updateCourseResourceListIsPublic(@Param("isPublic") boolean isPublic,
                                          @Param("resourceDetailId") String resourceDetailId,
                                          @Param("date") Date date);

    @Query(nativeQuery = true, value = "select sum(resource_references) from tb_course_resource where resource_status=0")
    int countResourceReferences();

    List<CourseResource> findAllByCourseStructureIdInAndResourceStatus(List<String> courseStructureIds, ResourceStatus resourceStatus);


    @Query(nativeQuery = true, value = "select count(1) from tb_course_resource " +
            " where course_structure_id in (select id from tb_course_structure where course_version_id=?1) " +
            " and resource_type in (0,1,3)  and resource_status=0")
    int countResourceNumByVersionId(String versionId);

    @Query(nativeQuery = true, value = "select count(1) from tb_course_resource " +
            " where course_structure_id in (select id from tb_course_structure where course_version_id=?1) " +
            " and resource_type in (2,4) and resource_status=0")
    int countPreparatoryActivityNumByVersionId(String versionId);

    List<CourseResource> findByResourceStatus(ResourceStatus resourceStatus);

    List<CourseResource> findByParentIdAndResourceStatus(String id, ResourceStatus normal);

    List<CourseResource> findByResourceDetailIdIsInAndCourseStructure(List<String> ids, CourseStructure courseStructure);

    List<CourseResource> findByCourseId(String courseId);

    List<CourseResource> findByIdIn(List<String> ids);

    @Query(nativeQuery = true, value = "select resource_name from tb_course_resource where resource_status= 0 " +
            " and course_structure_id in (select id from tb_course_structure where course_id=?1 and course_version_id =?2 and structure_status=0 ) " +
            " and creator_id = ?3 and resource_type=?4")
    List<String> findByCreatorIdAndCourseId(String courseId, String versionId, String operatorId, Integer index);

    @Query(nativeQuery = true, value = "SELECT a.* FROM tb_course_resource a INNER JOIN tb_course_structure b ON a.course_structure_id = b.id " +
            " INNER JOIN tb_course_version c ON b.course_version_id = c.id INNER JOIN tb_course d ON d.id = c.course_id  " +
            "WHERE  a.source_type = 0  " +
            "AND c.course_version_status = 1 and a.create_time>=:startDate and a.create_time<=:endDate and a.creator_id in (:teacherIdList)")
    List<CourseResource> findCreateTimeAndTeacherIdIn(@Param("startDate") Date startDate,
                                                      @Param("endDate") Date endDate,
                                                      @Param("teacherIdList") List<String> teacherIdList);

    @Query(nativeQuery = true, value = "UPDATE tb_course_resource SET sort = (sort + 1),modify_time=now()  " +
            "WHERE creator_id = :creatorId AND resource_status=0 AND course_structure_id=:courseStructureId And  sort >:from and sort<:to")
    @Modifying
    @Transactional
    void backwardByCreatorIdAndCourseIdWithIndexBetween(
            @Param("creatorId") String creatorId,
            @Param("courseStructureId") String courseStructureId,
            @Param("from") int from, @Param("to") int to
    );

    @Query(nativeQuery = true, value = "UPDATE tb_course_resource SET sort = (sort - 1) ,modify_time=now() " +
            "WHERE creator_id = :creatorId AND resource_status=0 AND course_structure_id=:courseStructureId And sort >:from and sort<:to")
    @Modifying
    @Transactional
    void forwardByCreatorIdAndCourseIdWithIndexBetween(@Param("creatorId") String creatorId,
                                                       @Param("courseStructureId") String courseStructureId,
                                                       @Param("from") int from,
                                                       @Param("to") int to);

    CourseResource findByCreatorIdAndCourseStructureIdAndSort(String creatorId, String courseStructureId, int i);

    int countByCreatorIdAndCourseStructureIdAndResourceStatus(String teacherId, String courseStructureId, ResourceStatus resourceStatus);

    CourseResource findTop1ByCreatorIdAndCourseStructureIdAndResourceStatusOrderBySortDesc(
            String teacherId, String courseStructureId, ResourceStatus resourceStatus);

    @Query(nativeQuery = true, value = "UPDATE tb_course_resource SET auxiliary_sort = (auxiliary_sort - 1) ,modify_time=now() " +
            "WHERE resource_status=0 and is_public=1 AND course_structure_id=:courseStructureId " +
            "And auxiliary_sort >:from and auxiliary_sort<:to")
    @Modifying
    @Transactional
    void forwardPublicByCourseIdWithIndexBetween(
                                                       @Param("courseStructureId") String courseStructureId,
                                                       @Param("from") int from,
                                                       @Param("to") int to);
    @Query(nativeQuery = true, value = "UPDATE tb_course_resource SET auxiliary_sort = (auxiliary_sort + 1),modify_time=now()  " +
            "WHERE resource_status=0 and is_public=1 AND course_structure_id=:courseStructureId " +
            "And  auxiliary_sort >:from and auxiliary_sort<:to")
    @Modifying
    @Transactional
    void backwardPublicByCourseIdWithIndexBetween(
            @Param("courseStructureId") String courseStructureId,
            @Param("from") int from, @Param("to") int to
    );
    CourseResource findTop1ByCourseStructureIdAndIsPublicAndResourceStatusOrderByAuxiliarySortDesc(
            String courseStructureId,Boolean isPublic, ResourceStatus resourceStatus);
}

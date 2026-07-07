package com.lztech.persistence.repositories.downloadrecord;

import com.lztech.domain.model.downloadrecord.DownloadRecord;
import com.lztech.domain.model.downloadrecord.enums.DowmloadRecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadRecordRepository extends JpaRepository<DownloadRecord, String>
        , JpaSpecificationExecutor<DownloadRecord> {
    List<DownloadRecord> findByCourseIdAndPackStatus(String courseId, DowmloadRecordStatus dowmloadRecordStatus);
    DownloadRecord findByCreatorIdAndCourseIdAndVersionId(String userId,String courseId,String versionId);


    List<DownloadRecord> findByCreatorIdAndPackType(String userId,Integer packType);


    DownloadRecord findByCreatorIdAndPackTypeAndCourseIdAndVersionSerialNumberAndPackStatusIn(
            String userId,Integer packType,String courseId,Integer versionNum,List<DowmloadRecordStatus> dowmloadRecordStatuses);

    List<DownloadRecord> findByCreatorIdAndPackTypeAndCourseIdAndCollegeIdOrderByCreateTimeDesc(
            String userId,Integer packType,String courseId,String collegeId);
}

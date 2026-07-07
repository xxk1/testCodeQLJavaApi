package com.lztech.persistence.repositories.segment;

import com.lztech.domain.model.segment.Segment;
import com.lztech.domain.model.segment.SegmentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, String>, JpaSpecificationExecutor<Segment> {

    List<Segment> findAllByOrderByBuildName();

    @Query(nativeQuery = true, value = "SELECT * FROM `tb_segment` WHERE buildid in (:buildIdList)")
    List<Segment> findAllByBuildIDInOrderByBuildName(@Param("buildIdList") List<String> buildIdList);

    @Query(nativeQuery = true, value = "SELECT * FROM `tb_segment` WHERE buildid=:buildId")
    List<Segment> findByBuildIdOrderByBuildName(@Param("buildId") String buildId);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_segment WHERE buildid !='0' GROUP BY buildid ")
    List<Segment> findAllGroupByBuildId();

    List<Segment> findByBuildName(String buildName);

    List<Segment> findByBuildNameOrderByStartTime(String buildName);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_segment WHERE buildid =:buildId ORDER BY start_time ")
    List<Segment> findByBuildIdOrderByStartTime(@Param("buildId") String buildId);

    @Query("select s from Segment s where s.segmentPK.buildID = :buildId ")
    List<Segment> findByBuildId(@Param("buildId") String buildId);


    @Query("select s from Segment s where s.startTime <= :nowTime and  s.endTime >= :nowTime and s.segmentPK.buildID = :buildingId")
    Segment getNowSegment(@Param("nowTime") String nowTime, @Param("buildingId") String buildingId);


    @Query("select s from  Segment s where s.segmentPK.buildID = :buildId and s.startTime > :nowTime")
    List<Segment> getNextSegment(@Param("buildId") String buildId, @Param("nowTime") String nowTime);

    Segment findBySegmentPK(SegmentPK segmentPK);

    @Query(nativeQuery = true, value = "select * from tb_segment s " +
            "group by s.segment order by s.start_time")
    List<Segment> findAllSegmentsDistinct();
}

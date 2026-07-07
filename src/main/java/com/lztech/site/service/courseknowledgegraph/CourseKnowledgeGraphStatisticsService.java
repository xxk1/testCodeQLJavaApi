package com.lztech.site.service.courseknowledgegraph;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.course.enums.ResourceType;
import com.lztech.domain.model.course.enums.TeacherDataSource;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.persistence.repositories.course.CourseTeachingTeamRepository;
import com.lztech.persistence.repositories.courseknowledgegraph.CourseKnowledgeGraphDomainRepository;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.viewmodel.courseknowledgegraphstatistics.CourseKnowledgeGraphStatisticsVo;
import com.lztech.site.viewmodel.courseknowledgegraphstatistics.KnowledgeGraphResourceStatisticsVo;
import com.lztech.site.viewmodel.courseknowledgegraphstatistics.KnowledgeGraphUploadResourceFileStatisticsVo;
import com.lztech.site.viewmodel.courseknowledgegraphstatistics.LevelKnowledgePointNumberVo;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.*;

@Service
public class CourseKnowledgeGraphStatisticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseKnowledgeGraphStatisticsService.class);
    @Autowired
    private CourseKnowledgeGraphDomainRepository courseKnowledgeGraphDomainRepository;
    @Autowired
    private Neo4jUtil neo4jUtil;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;

    public CourseKnowledgeGraphStatisticsVo getCourseKnowledgeGraphLevelPointStatistics(
            Course course, Double minSimilarityScore) {
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                courseKnowledgeGraphDomainRepository.getEffectiveCourseKnowledgeGraphDomainByCourseId(course.getId());
        CourseKnowledgeGraphStatisticsVo statisticsVo = new CourseKnowledgeGraphStatisticsVo();

        initCourseKnowledgeGraphStatistics(statisticsVo);
        if (ObjectUtils.isNotEmpty(courseKnowledgeGraphDomain)) {
            getLevelKnowledgePointNumber(courseKnowledgeGraphDomain.getId(), statisticsVo);

            getCourseKnowledgeGraphAssociationVideoFragmentCount(
                    courseKnowledgeGraphDomain.getId(), minSimilarityScore, statisticsVo,
                    course.getId(), courseKnowledgeGraphDomain.getCourseVersion().getId());

            getAssociationImageAndLearnVideoAndCoursewareCount(courseKnowledgeGraphDomain.getId(), statisticsVo);

            getCourseKnowledgeGraphClassTestCount(courseKnowledgeGraphDomain.getId(), statisticsVo);

            getCourseKnowledgeGraphUploadQuestionCount(courseKnowledgeGraphDomain.getId(), statisticsVo);
        }
        return statisticsVo;
    }

    private void getLevelKnowledgePointNumber(String courseKnowledgeGraphDomainId,
                                              CourseKnowledgeGraphStatisticsVo statisticsVo) {
        statisticsVo.setFirstLevelCount(0);
        statisticsVo.setSecondLevelCount(0);
        statisticsVo.setThirdLevelCount(0);
        statisticsVo.setOtherLevelCount(0);
        statisticsVo.setTotalLevelCount(0);

        List<LevelKnowledgePointNumberVo> levelKnowledgePointNumberVos =
                neo4jUtil.getDomainLevelNodeCount(courseKnowledgeGraphDomainId);
        LevelKnowledgePointNumberVo firstFilter = levelKnowledgePointNumberVos.stream()
                .filter(obj -> obj.getLevel().equals(ONE)).findFirst().orElse(null);
        if (ObjectUtils.isNotEmpty(firstFilter)) {
            statisticsVo.setFirstLevelCount(firstFilter.getNodeCount());
        }

        LevelKnowledgePointNumberVo secondFilter = levelKnowledgePointNumberVos.stream()
                .filter(obj -> obj.getLevel().equals(TWO)).findFirst().orElse(null);
        if (ObjectUtils.isNotEmpty(secondFilter)) {
            statisticsVo.setSecondLevelCount(secondFilter.getNodeCount());
        }

        LevelKnowledgePointNumberVo thirdFilter = levelKnowledgePointNumberVos.stream()
                .filter(obj -> obj.getLevel().equals(THREE)).findFirst().orElse(null);
        if (ObjectUtils.isNotEmpty(thirdFilter)) {
            statisticsVo.setThirdLevelCount(thirdFilter.getNodeCount());
        }

        AtomicReference<Integer> otherCount = new AtomicReference<>(0);
        List<LevelKnowledgePointNumberVo> others = levelKnowledgePointNumberVos.stream()
                .filter(obj -> obj.getLevel() > THREE).collect(Collectors.toList());
        others.forEach(levelKnowledgePointNumberVo -> {
            otherCount.set(otherCount.get() + levelKnowledgePointNumberVo.getNodeCount());
        });

        statisticsVo.setOtherLevelCount(otherCount.get());

        statisticsVo.setTotalLevelCount(statisticsVo.getFirstLevelCount() + statisticsVo.getSecondLevelCount()
                + statisticsVo.getThirdLevelCount() + statisticsVo.getOtherLevelCount());
    }

    private void getCourseKnowledgeGraphAssociationVideoFragmentCount(String courseKnowledgeGraphDomainId,
                                                                      double minGraphSpeechRecognitionSimilarityScore,
                                                                      CourseKnowledgeGraphStatisticsVo statisticsVo,
                                                                      String courseId,
                                                                      String versionId) {
        List<CourseTeachingTeam> courseTeachingTeamList = courseTeachingTeamRepository.findByCourseIdAndCourseVersionId(courseId, versionId);
        List<String> teacherIds = courseTeachingTeamList.stream().map(o -> {
            if (o.getTeacherDataSource().equals(TeacherDataSource.CAMPUS_USER))
                return o.getTeacherId();
            else
                return o.getId();
        }).collect(Collectors.toList());

        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(video_info_id) as fragmentCount");
        sb.append(" FROM tb_course_knowledge_graph_node_video_info_text");
        sb.append(" WHERE text_data_source = 1");
        sb.append(" AND course_knowledge_graph_domain_id = :courseKnowledgeGraphDomainId");
        sb.append(" AND similarity_score >= :minGraphSpeechRecognitionSimilarityScore");

        if (!teacherIds.isEmpty()) {
            sb.append(" AND (");
            int i = 1;
            for (String teacherId : teacherIds) {
                if (i < teacherIds.size()) {
                    sb.append(" FIND_IN_SET(:teacherId").append(i).append(", teacher_ids) OR");
                } else {
                    sb.append(" FIND_IN_SET(:teacherId").append(i).append(", teacher_ids)");
                }

                paramMap.put("teacherId" + i, teacherId);
                i = i + 1;
            }
            sb.append(")");
        }


        paramMap.put("courseKnowledgeGraphDomainId", courseKnowledgeGraphDomainId);
        paramMap.put("minGraphSpeechRecognitionSimilarityScore", minGraphSpeechRecognitionSimilarityScore);
        Query query = entityManager.createNativeQuery(sb.toString());
        paramMap.forEach(query::setParameter);

        Object result = query.getSingleResult();

        int videoFragmentCount = 0;
        if (result instanceof BigInteger) {
            videoFragmentCount = ((BigInteger) result).intValue();
        } else if (result instanceof Long) {
            videoFragmentCount = ((Long) result).intValue();
        }

        statisticsVo.setVideoFragmentCount(videoFragmentCount);
    }

    private void getAssociationImageAndLearnVideoAndCoursewareCount(
            String courseKnowledgeGraphDomainId,
            CourseKnowledgeGraphStatisticsVo statisticsVo) {
        statisticsVo.setImageResourceCount(0);
        statisticsVo.setLearnVideoResourceCount(0);
        statisticsVo.setDocumentResourceCount(0);
        AtomicReference<Integer> imageResourceCount = new AtomicReference<>(0);
        AtomicReference<Integer> learnVideoResourceCount = new AtomicReference<>(0);
        AtomicReference<Integer> documentResourceCount = new AtomicReference<>(0);

        List<KnowledgeGraphResourceStatisticsVo> resourceStatisticsVos =
                getAssociationImageAndLearnVideoAndCoursewareCount(courseKnowledgeGraphDomainId);
        resourceStatisticsVos.forEach(resourceStatisticsVo -> {
            Integer resourceType = resourceStatisticsVo.getResourceType();
            if (Objects.equals(resourceType, ResourceType.TEACHING_COURSE_WARE.getIndex())) {
                documentResourceCount.set(documentResourceCount.get() + resourceStatisticsVo.getResourceCount().intValue());
            } else if (Objects.equals(resourceType, ResourceType.MICRO_VIDEO.getIndex())) {
                learnVideoResourceCount.set(learnVideoResourceCount.get() + resourceStatisticsVo.getResourceCount().intValue());
            } else if (Objects.equals(resourceType, ResourceType.IMAGE.getIndex())) {
                imageResourceCount.set(imageResourceCount.get() + resourceStatisticsVo.getResourceCount().intValue());
            }
        });

        List<KnowledgeGraphUploadResourceFileStatisticsVo> uploadResourceFileStatisticsVos =
                getUploadLearnResourceFileCount(courseKnowledgeGraphDomainId);

        List<String> pictureTypes = Arrays.asList(ALLOWED_PIC_TYPE.split(","));
        List<String> videoTypes = Arrays.asList(ALLOWED_VIDEO_TYPE.split(","));
        uploadResourceFileStatisticsVos.forEach(uploadResourceFileStatisticsVo -> {
            String fileType = uploadResourceFileStatisticsVo.getFileType();
            if (pictureTypes.stream().anyMatch(s -> s.equals(fileType.toLowerCase()))) {
                imageResourceCount.set(imageResourceCount.get() + uploadResourceFileStatisticsVo.getFileCount().intValue());
            } else if (videoTypes.stream().anyMatch(s -> s.equals(fileType.toLowerCase()))) {
                learnVideoResourceCount.set(learnVideoResourceCount.get() + uploadResourceFileStatisticsVo.getFileCount().intValue());
            } else {
                documentResourceCount.set(documentResourceCount.get() + uploadResourceFileStatisticsVo.getFileCount().intValue());
            }
        });

        statisticsVo.setImageResourceCount(imageResourceCount.get());
        statisticsVo.setLearnVideoResourceCount(learnVideoResourceCount.get());
        statisticsVo.setDocumentResourceCount(documentResourceCount.get());
    }

    private List<KnowledgeGraphResourceStatisticsVo> getAssociationImageAndLearnVideoAndCoursewareCount(
            String courseKnowledgeGraphDomainId) {
        String sql = "SELECT COUNT(DISTINCT crf.id) as resourceCount,resource_type as resourceType" +
                " FROM tb_course_resource_knowledge_point crkp" +
                " INNER JOIN tb_course_resource cr ON cr.id = crkp.resource_id AND cr.resource_status = 0" +
                " INNER JOIN tb_course_resource_file crf ON crf.id = cr.resource_detail_id and crf.resource_status = 0" +
                " WHERE crkp.course_knowledge_graph_domain_id = :courseKnowledgeGraphDomainId" +
                " GROUP BY resource_type";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseKnowledgeGraphDomainId", courseKnowledgeGraphDomainId);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(KnowledgeGraphResourceStatisticsVo.class));
        return query.getResultList();
    }

    private List<KnowledgeGraphUploadResourceFileStatisticsVo> getUploadLearnResourceFileCount(
            String courseKnowledgeGraphDomainId) {
        String sql = "SELECT COUNT(DISTINCT ckgnrf.id) as fileCount,LOWER(ckgnrf.file_type) as fileType" +
                " FROM tb_course_knowledge_graph_node_resource ckgnr" +
                " INNER JOIN tb_course_knowledge_graph_node_resource_file ckgnrf ON ckgnr.resource_detail_id = ckgnrf.id" +
                " WHERE ckgnr.course_knowledge_graph_domain_id = :courseKnowledgeGraphDomainId" +
                " AND ckgnr.graph_node_resource_status = 0" +
                " GROUP BY LOWER(ckgnrf.file_type)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseKnowledgeGraphDomainId", courseKnowledgeGraphDomainId);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(KnowledgeGraphUploadResourceFileStatisticsVo.class));
        return query.getResultList();
    }

    private void getCourseKnowledgeGraphClassTestCount(String courseKnowledgeGraphDomainId,
                                                       CourseKnowledgeGraphStatisticsVo statisticsVo) {
        String sql = "SELECT COUNT(DISTINCT cr.id) as testCount" +
                " FROM tb_course_resource_knowledge_point crkp" +
                " INNER JOIN tb_course_resource cr ON cr.id = crkp.resource_id AND cr.resource_status = 0 and resource_type = 2" +
                " WHERE crkp.course_knowledge_graph_domain_id = :courseKnowledgeGraphDomainId";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseKnowledgeGraphDomainId", courseKnowledgeGraphDomainId);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);

        Object result = query.getSingleResult();

        int classTestCount = 0;
        if (result instanceof BigInteger) {
            classTestCount = ((BigInteger) result).intValue();
        } else if (result instanceof Long) {
            classTestCount = ((Long) result).intValue();
        }

        statisticsVo.setClassTestCount(classTestCount);
    }

    private void getCourseKnowledgeGraphUploadQuestionCount(String courseKnowledgeGraphDomainId,
                                                            CourseKnowledgeGraphStatisticsVo statisticsVo) {
        String sql = "select COUNT(1) as questionCount" +
                " from tb_course_knowledge_graph_node_question ckgnq " +
                " WHERE ckgnq.course_knowledge_graph_domain_id = :courseKnowledgeGraphDomainId";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("courseKnowledgeGraphDomainId", courseKnowledgeGraphDomainId);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);

        Object result = query.getSingleResult();

        int uploadQuestionCount = 0;
        if (result instanceof BigInteger) {
            uploadQuestionCount = ((BigInteger) result).intValue();
        } else if (result instanceof Long) {
            uploadQuestionCount = ((Long) result).intValue();
        }

        statisticsVo.setUploadQuestionCount(uploadQuestionCount);
    }

    private void initCourseKnowledgeGraphStatistics(CourseKnowledgeGraphStatisticsVo statisticsVo) {
        statisticsVo.setTotalLevelCount(0);
        statisticsVo.setFirstLevelCount(0);
        statisticsVo.setSecondLevelCount(0);
        statisticsVo.setThirdLevelCount(0);
        statisticsVo.setOtherLevelCount(0);
        statisticsVo.setVideoFragmentCount(0);
        statisticsVo.setImageResourceCount(0);
        statisticsVo.setLearnVideoResourceCount(0);
        statisticsVo.setDocumentResourceCount(0);
        statisticsVo.classTestCount(0);
        statisticsVo.setUploadQuestionCount(0);
    }
}

package com.lztech.site.service.courseexpansion;
import com.lztech.domain.model.course.CourseExpansion;
import com.lztech.domain.model.course.enums.CourseExpansionEnum;
import com.lztech.persistence.repositories.courseexpansion.CourseExpansionRepository;
import com.lztech.site.constants.Result;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionCreateResource;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionImportFileModel;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionImportValidateResource;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionQuery;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionResource;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionResourcePage;
import com.lztech.site.viewmodel.courseexpansion.ListeningTypeConfigResource;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.lztech.site.config.Access.signKey;
import static com.lztech.site.until.Md5Utils.md5;

@Service
public class CourseExpansionService {

    @Autowired
    private CourseExpansionRepository courseExpansionRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseExpansionImportVerifyService courseExpansionImportVerifyService;
    @Value("${request.address.teachingApi}")
    private String teachingApi;

    public Result createCourseExpansion(CourseExpansionCreateResource expansionInfo) {
        // 判断课程有无已经存在此扩展
        // CourseExpansionEnum courseExpansionEnum = CourseExpansionEnum.getCourseExpansionEnumByKey(expansionInfo.getExpansionKey());
        int num = courseExpansionRepository.countByCourseId(expansionInfo.getCourseId());
        if (num > 0) {
            return Result.error(expansionInfo.getCourseCode() + "已录入过，请勿重复导入。");
        }
        CourseExpansion courseExpansion = new CourseExpansion();
        courseExpansion.setCourseId(expansionInfo.getCourseId());
        courseExpansion.setExpansionKey(expansionInfo.getExpansionKey());
        courseExpansion.setExpansionValue(expansionInfo.getExpansionKeyName());
        courseExpansion.setCreatorId(expansionInfo.getCreatorId());
        courseExpansion.setCreatorName(expansionInfo.getCreatorName());
        courseExpansion.setCreateTime(new Date());
        courseExpansionRepository.save(courseExpansion);
        return Result.success();
    }

    @Transactional
    public Result importCourseExpansion(String creatorId, String creatorName, Integer superviseModel, Integer usageRange,
                                        String courseStudentTypeIds, Integer isDistinguishCourseStudentType, MultipartFile importFile) {
        if (StringUtils.isAnyBlank(creatorId, creatorName) || superviseModel == null) {
            return Result.error("参数不正确");
        }
        if (superviseModel != 0 && superviseModel != 1) {
            return Result.error("参数不正确");
        }

        Map<String, String> listeningTypeNameToKey = buildListeningTypeNameToKey(superviseModel, usageRange, courseStudentTypeIds, isDistinguishCourseStudentType);
        if (listeningTypeNameToKey.isEmpty()) {
            return Result.error("听课类型配置不正确");
        }

        try {
            CourseExpansionImportValidateResource validateResource =
                    courseExpansionImportVerifyService.validateAndParseExcel(importFile, listeningTypeNameToKey);
            if (validateResource.getStatus() != null && validateResource.getStatus() == 0) {
                return Result.error(validateResource.getMessage());
            }
            List<CourseExpansionImportFileModel> rows = validateResource.getCourseExpansionImportFileModelList();
            if (rows == null || rows.isEmpty()) {
                return Result.error("导入失败，导入模板内无有效数据！");
            }

            List<CourseExpansion> courseExpansionList = new ArrayList<>();
            for (CourseExpansionImportFileModel row : rows) {
                CourseExpansion courseExpansion = new CourseExpansion();
                courseExpansion.setCourseId(row.getCourseId());
                courseExpansion.setExpansionKey(row.getExpansionKey());
                courseExpansion.setExpansionValue(row.getListeningTypeName());
                courseExpansion.setCreatorId(creatorId);
                courseExpansion.setCreatorName(creatorName);
                courseExpansion.setCreateTime(new Date());
                courseExpansionList.add(courseExpansion);
            }
            courseExpansionRepository.saveAll(courseExpansionList);
            return Result.success();
        } catch (Exception e) {
            return Result.error("导入失败");
        }
    }

    public List<CourseExpansionResource> getCourseExpansionResource(String expansionKey, String sortName,
                                                                    String courseId) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = createQueryCourseSql(expansionKey, sortName, paramMap, courseId);
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseExpansionResource.class));
        return query.getResultList();
    }

    private String createQueryCourseSql(String expansionKey, String sortName, Map<String, Object> paramMap, String courseId) {
        String sql = "select c.id as courseId,\n" +
                "       c.course_code as courseCode,\n" +
                "       c.course_name as courseName,\n" +
                "       c.college_id as collegeId,\n" +
                "       c.college_name as collegeName,\n" +
                "       ce.id as courseExpansionId\n" +
                " from tb_course c\n" +
                "         inner join tb_course_expansion ce\n" +
                "                    on c.id = ce.course_id\n" +
                " where c.use_state =1 and ce.expansion_key=:expansionKey ";
        paramMap.put("expansionKey", expansionKey);
        if (StringUtils.isNotBlank(courseId)) {
            sql += " and c.id=:courseId ";
            paramMap.put("courseId", courseId);
        }
        if ("courseCode".equals(sortName)) {
            sql += " order by c.course_code";
        } else {
            sql += " order  by  ce.create_time desc";
        }
        return sql;
    }

    public void deleteCourseExpansionById(String id) {

        courseExpansionRepository.deleteCourseExpansionById(id);
    }

    public CourseExpansionResourcePage getCourseExpansionPage(CourseExpansionQuery courseExpansionQuery) {
        CourseExpansionResourcePage courseExpansionResourcePage = new CourseExpansionResourcePage();
        courseExpansionResourcePage.setTotal(0);
        courseExpansionResourcePage.setPage(courseExpansionQuery.getPage());
        courseExpansionResourcePage.setPageSize(courseExpansionQuery.getPageSize());
        courseExpansionResourcePage.setTotalPage(0);
        // 构建查询参数
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = buildPagingQuerySql(courseExpansionQuery, paramMap);

        // 查询总数
        String countSql = "SELECT COUNT(*) FROM (" + querySql + ") temp";
        Query countQuery = entityManager.createNativeQuery(countSql);
        paramMap.forEach(countQuery::setParameter);
        Long totalCount = ((Number) countQuery.getSingleResult()).longValue();

        courseExpansionResourcePage.setTotal(Math.toIntExact(totalCount));
        int totalPages = (int) Math.ceil((double) totalCount / courseExpansionQuery.getPageSize());
        courseExpansionResourcePage.setTotalPage(totalPages);

        // 执行分页查询
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);

        // 设置分页参数
        int offset = (courseExpansionQuery.getPage() - 1) * courseExpansionQuery.getPageSize();
        query.setFirstResult(offset);
        query.setMaxResults(courseExpansionQuery.getPageSize());

        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(CourseExpansionResource.class));

        List<CourseExpansionResource> resultList = query.getResultList();
        courseExpansionResourcePage.setResourceList(resultList);

        return courseExpansionResourcePage;


    }

    private String buildPagingQuerySql(CourseExpansionQuery courseExpansionQuery, Map<String, Object> paramMap) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c.id AS courseId, ")
                .append("c.course_code AS courseCode, ")
                .append("c.course_name AS courseName, ")
                .append("c.college_id AS collegeId, ")
                .append("c.college_name AS collegeName, ")
                .append("ce.id AS courseExpansionId, ")
                .append("ce.expansion_key AS expansionKey, ")
                .append("ce.expansion_value AS expansionKeyName ")
                .append("FROM tb_course c ")
                .append("INNER JOIN tb_course_expansion ce ON c.id = ce.course_id ")
                .append("WHERE c.use_state = 1 ");

        if (StringUtils.isNotBlank(courseExpansionQuery.getExpansionKey())) {
            sql.append("AND ce.expansion_key = :expansionKey ");
            paramMap.put("expansionKey", courseExpansionQuery.getExpansionKey());
        }

        if (StringUtils.isNotBlank(courseExpansionQuery.getCourseNameOrCode())) {
            sql.append("AND (c.course_code LIKE :courseNameOrCode or c.course_name LIKE :courseNameOrCode )");
            paramMap.put("courseNameOrCode", "%" + courseExpansionQuery.getCourseNameOrCode() + "%");
        }
        sql.append("ORDER BY ce.create_time DESC ");
        return sql.toString();
    }

    private Map<String, String> buildListeningTypeNameToKey(Integer superviseModel, Integer usageRange, String courseStudentTypeIds,
                                                            Integer isDistinguishCourseStudentType) {
        if (superviseModel != null && superviseModel == 0) {
            Map<String, String> fixed = new LinkedHashMap<>();
            fixed.put(CourseExpansionEnum.POLITICAL_COURSE.getValue(), CourseExpansionEnum.POLITICAL_COURSE.getKey());
            return fixed;
        }
        List<ListeningTypeConfigResource> configList = fetchListeningTypeConfigList(usageRange, courseStudentTypeIds, isDistinguishCourseStudentType);
        return buildListeningTypeNameToKeyFromConfig(configList);
    }

    private List<ListeningTypeConfigResource> fetchListeningTypeConfigList(Integer usageRange, String courseStudentTypeIds,
                                                                           Integer isDistinguishCourseStudentType) {
        RestTemplate restTemplate = new RestTemplate();
        int range = usageRange == null ? 0 : usageRange;
        String studentTypeIds = StringUtils.isBlank(courseStudentTypeIds) ? "0" : courseStudentTypeIds;
        int distinguish = isDistinguishCourseStudentType == null ? 1 : isDistinguishCourseStudentType;
        String validCode = md5(signKey);
        String url = teachingApi + "/api/v1/listeningType"
                + "?usageRange=" + range
                + "&courseStudentTypeIds=" + studentTypeIds
                + "&isDistinguishCourseStudentType=" + distinguish
                + "&validCode=" + validCode;
        try {
            ResponseEntity<List<ListeningTypeConfigResource>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ListeningTypeConfigResource>>() {});
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> buildListeningTypeNameToKeyFromConfig(List<ListeningTypeConfigResource> configList) {
        Map<String, String> nameToKey = new LinkedHashMap<>();
        if (configList == null) {
            return nameToKey;
        }
        for (ListeningTypeConfigResource config : configList) {
            if (config == null) {
                continue;
            }
            String name = StringUtils.trimToNull(config.getListeningTypeName());
            String key = StringUtils.trimToNull(config.getRelatedScheduleTypes());
            if (StringUtils.isAnyBlank(name, key)) {
                continue;
            }
            nameToKey.put(name, key);
        }
        return nameToKey;
    }
}

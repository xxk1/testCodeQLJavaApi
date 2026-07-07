package com.lztech.site.service.online;

import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.site.constants.Constant;
import com.lztech.site.resource.coursetabledetail.CourseTableDetailResource;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.coursetabledetail.*;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.NEED_DISTINGUISH_COURSE_STUDENT_TYPE;
import static com.lztech.site.until.DateUtils.DATE;
import static com.lztech.site.until.DateUtils.formatDate;
import static com.lztech.site.until.LzStringUtil.convertToQuotedStringBySemicolon;

@Service
public class OnlineTourService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TermService termService;

    @Value("${expertListeningEndTime}")
    private String expertListeningEndTime;

    public ResponseEntity<List<CourseCourseTableDetailResource>> statisticsCourseCount(String searchParams,
                                                                                       String courseIds,
                                                                                       String collegeIds,
                                                                                       String courseStudentTypeIds,
                                                                                       String isDistinguishCourseStudentType) {
        Map<String, Object> paramMap = new HashMap<>();
        String querySql = createQuerySql(searchParams, courseIds, collegeIds, courseStudentTypeIds, isDistinguishCourseStudentType, paramMap);
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<CourseCourseTableDetailResource> courseCourseTableDetailResourceList = query.getResultList();
        if (CollectionUtils.isEmpty(courseCourseTableDetailResourceList)) {
            return new ResponseEntity(ErrorResult.customError("未查询到正在上课的课表信息"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseCourseTableDetailResourceList, HttpStatus.OK);
    }

    public ResponseEntity<List<CourseCourseTableDetailResource>> statisticsCourseCountPost(String searchParams,
                                                                                           String courseIds,
                                                                                           String collegeIds) {
        String querySql = createQuerySqlNoId(searchParams, courseIds, collegeIds);
        Query query = entityManager.createNativeQuery(querySql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<CourseCourseTableDetailResource> courseCourseTableDetailResourceList = query.getResultList();
        if (CollectionUtils.isEmpty(courseCourseTableDetailResourceList)) {
            return new ResponseEntity(ErrorResult.customError("未查询到正在上课的课表信息"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseCourseTableDetailResourceList, HttpStatus.OK);
    }

    private String createQuerySql(String searchParams, String courseIds, String collegeIds,
                                  String courseStudentTypeIds,
                                  String isDistinguishCourseStudentType, Map<String, Object> paramMap) {
        String sql = "SELECT c.id as courseId,c.course_name as courseName,count(1) as inClassCount from tb_course_table_detail ctd" +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id ";
        sql += " INNER JOIN tb_course c ON ct.course_id = c.id" +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment " +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id ";
        if (StringUtils.isNotBlank(searchParams)) {
            sql += " INNER JOIN tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd.id ";
        }
        Date nowDate = new Date();
        String day = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String time = DateUtils.formatDate(DateUtils.TIME, nowDate);
        sql += " where c.use_state=1 and ctd.course_date = :day and s.start_time<= :time and s.end_time>= :time" +
                " and  ctdru.has_live=1 and ctdru.room_id is not null";
        paramMap.put("day", day);
        paramMap.put("time", time);
        if (StringUtils.isNotBlank(searchParams)) {
            List<String> list = Arrays.asList(searchParams.split(" "));
            int index = 0;
            for (String tempString : list) {
                tempString = CharactersReplace.replaceCharacters(tempString);
                if (StringUtils.isNotBlank(tempString)) {
                    sql += " and (c.course_name like :tempString" + index +
                            " or ctdt.teacher_name like :tempString" + index +
                            " or ctdru.room_name like :tempString" + index + ")";
                    index++;
                    paramMap.put("tempString" + index, "%" + tempString + "%");
                }
            }
        }
        if (StringUtils.isNotBlank(isDistinguishCourseStudentType)
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(isDistinguishCourseStudentType)) {
            if (StringUtils.isNotBlank(courseStudentTypeIds)) {
                List<String> ids = Arrays.asList(courseStudentTypeIds.split(","));
                sql += " and ct.student_type in (:studentTypeList)";
                paramMap.put("studentTypeList", ids);
            } else {
                sql += " and 1=0 ";
            }
        }
        if (StringUtils.isEmpty(courseIds)) {
            sql += " and (c.id in ('')";
        } else {
            List<String> ids = Arrays.asList(courseIds.split(","));
            sql += " and (c.id  in (:courseIdList)";
            paramMap.put("courseIdList", ids);
        }
        if (StringUtils.isEmpty(collegeIds)) {
            sql += " or c.college_id in (''))";
        } else {
            List<String> ids = Arrays.asList(collegeIds.split(","));
            sql += " or c.college_id in (:collegeIdList))";
            paramMap.put("collegeIdList", ids);
        }
        sql += " GROUP BY c.id ORDER BY c.course_code ";
        return sql;
    }

    private String createQuerySqlNoId(String searchParams, String courseIds, String collegeIds) {
        String sql = "SELECT c.id as courseId,c.course_name as courseName,count(1) as inClassCount from tb_course_table_detail ctd" +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                " INNER JOIN tb_course c ON ct.course_id = c.id" +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment " +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id ";
        if (StringUtils.isNotBlank(searchParams)) {
            sql += " INNER JOIN tb_course_table_detail_teacher ctdt on ctdt.course_table_detail_id = ctd.id ";
        }
        Date nowDate = new Date();
        String day = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String time = DateUtils.formatDate(DateUtils.TIME, nowDate);
        sql += " where c.use_state=1 and ctd.course_date = '" + day + "' and s.start_time<='" + time + "' and s.end_time>='" + time + "' " +
                " and  ctdru.has_live=1 and ctd.source= 0 and ctdru.room_id is not null";
        if (StringUtils.isNotBlank(searchParams)) {
            List<String> list = Arrays.asList(searchParams.split(" "));
            for (String tempString : list) {
                tempString = CharactersReplace.replaceCharacters(tempString);
                if (StringUtils.isNotBlank(tempString)) {
                    sql += " and (c.course_name like '%" + tempString.trim() + "%' " +
                            " or ctdt.teacher_name like '%" + tempString.trim() + "%' " +
                            " or ctdru.room_name like '%" + tempString.trim() + "%' )";
                }
            }
        }
        if (!StringUtils.isEmpty(courseIds)) {
            List<String> ids = Arrays.asList(courseIds.split(","));
            StringBuilder suffer = new StringBuilder();
            for (String id : ids) {
                suffer.append("\'");
                suffer.append(id);
                suffer.append("\',");
            }
            sql += " and (c.id  in (" + suffer.substring(0, suffer.length() - 1) + ")";
        }
        if (!StringUtils.isEmpty(collegeIds)) {
            List<String> ids = Arrays.asList(collegeIds.split(","));
            StringBuilder suffer = new StringBuilder();
            for (String id : ids) {
                suffer.append("\'");
                suffer.append(id);
                suffer.append("\',");
            }
            sql += " or c.college_id in (" + suffer.substring(0, suffer.length() - 1) + "))";
        }
        sql += " GROUP BY c.id ORDER BY c.course_code ";
        return sql;
    }

    public ResponseEntity<CourseTableDetailPageResource> findInClassCourseTableDetails(InclassPageVo inclassPageVo) {
        CourseTableDetailPageResource courseTableDetailPageResource = new CourseTableDetailPageResource();
        Map<String, Object> paramMap = new HashMap<>();
        String baseSql = createBaseSql(paramMap, inclassPageVo);
        Query queryCount = entityManager.createNativeQuery(createCountSql(inclassPageVo, baseSql, paramMap));
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        int count = ((BigInteger) resMap.get("allNumber")).intValue();
        if (count == 0) {
            courseTableDetailPageResource.setTotal(0);
            courseTableDetailPageResource.setPage(inclassPageVo.getPage());
            courseTableDetailPageResource.setPageCount(0);
            courseTableDetailPageResource.setPageSize(inclassPageVo.getPageSize());
            courseTableDetailPageResource.setCourseTableDetailList(new ArrayList<>());
        } else {
            String sql = createCourseTableDetailSql(inclassPageVo, baseSql, paramMap);
            Query queryData = entityManager.createNativeQuery(sql);
            paramMap.forEach(queryData::setParameter);
            queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailResource.class));
            courseTableDetailPageResource.setTotal(count);
            courseTableDetailPageResource.setPage(inclassPageVo.getPage());
            courseTableDetailPageResource.setPageCount((int) Math.ceil((double) count /
                    (double) inclassPageVo.getPageSize()));
            courseTableDetailPageResource.setPageSize(inclassPageVo.getPageSize());
            courseTableDetailPageResource.setCourseTableDetailList(queryData.getResultList());
        }
        return new ResponseEntity<>(courseTableDetailPageResource, HttpStatus.OK);
    }

    private String createBaseSql(Map<String, Object> paramMap, InclassPageVo inclassPageVo) {
        Date nowDate = new Date();
        String day = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String time = DateUtils.formatDate(DateUtils.TIME, nowDate);
        String sql = "SELECT ctd.id as id ,\n" +
                " c.course_name as courseName,\n" +
                " ctdru.room_id as roomId,\n" +
                " ctdru.room_name as roomName,\n" +
                " (select GROUP_CONCAT(teacher_name) from tb_course_table_detail_teacher where course_table_detail_id=ctd.id GROUP BY ctd.id )" +
                " as teacherName," +
                " ctd.course_kind as courseKind ,c.id as courseId," +
                "concat(s.start_time,':00') as segmentStartTime,concat(s.end_time,':00') as segmentEndTime," +
                " c.college_id as collegeId FROM tb_course_table_detail ctd " +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id ";
        sql += " INNER JOIN tb_course c ON ct.course_id = c.id " +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment where " +
                " c.use_state=1 and ctd.course_date = :day " +
                "and s.start_time<= :time  and s.end_time>= :time and  ctdru.has_live=1 " +
                "and ctdru.room_id is not null ";
        paramMap.put("day", day);
        paramMap.put("time", time);
        if (StringUtils.isNotBlank(inclassPageVo.getIsDistinguishCourseStudentType())
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(inclassPageVo.getIsDistinguishCourseStudentType())) {
            if (StringUtils.isNotBlank(inclassPageVo.getCourseStudentTypeIds())) {
                List<String> courseStudentTypeIdList = Arrays.asList(inclassPageVo.getCourseStudentTypeIds().split(","));
                sql += " and ct.student_type in ( :courseStudentTypeIdList )";
                paramMap.put("courseStudentTypeIdList", courseStudentTypeIdList);
            } else {
                sql += " and  1=0 ";
            }
        }

        return sql;
    }

    private String createCountSql(InclassPageVo inclassPageVo, String baseSql, Map<String, Object> paramMap) {
        String countSql = "select count(1) as allNumber from (" + baseSql + ") a where 1=1 ";
        countSql = setParams(inclassPageVo, countSql, paramMap);
        return countSql;
    }

    private String setParams(InclassPageVo inclassPageVo, String querySql, Map<String, Object> paramMap) {

        if (StringUtils.isNotBlank(inclassPageVo.getSearchParams())) {
            List<String> list = Arrays.asList(inclassPageVo.getSearchParams().split(" "));
            int index = 0;
            for (String tempString : list) {
                tempString = CharactersReplace.replaceCharacters(tempString);
                if (StringUtils.isNotBlank(tempString)) {
                    querySql += " and (a.course_name like :tempString" + index +
                            " or a.teacher_name like :tempString" + index +
                            " or a.room_name like :tempString" + index + ")";
                    index++;
                    paramMap.put("tempString" + index, "%" + tempString + "%");
                }
            }
        }

        if (StringUtils.isNotBlank(inclassPageVo.getCourseId())) {
            querySql += " and a.courseId= :courseId ";
            paramMap.put("courseId", inclassPageVo.getCourseId());
        }

        if (StringUtils.isEmpty(inclassPageVo.getCourseIds())) {
            querySql += " and (a.courseId in ('')";
        } else {
            List<String> ids = Arrays.asList(inclassPageVo.getCourseIds().split(","));
            querySql += " and (a.courseId in ( :courseIdList )";
            paramMap.put("courseIdList", ids);
        }
        if (StringUtils.isEmpty(inclassPageVo.getCollegeIds())) {
            querySql += " or a.collegeId in (''))";
        } else {
            List<String> ids = Arrays.asList(inclassPageVo.getCollegeIds().split(","));
            querySql += " or a.collegeId in ( :collegeIdList ))";
            paramMap.put("collegeIdList", ids);
        }
        return querySql;
    }

    private String createCourseTableDetailSql(InclassPageVo inclassPageVo,
                                              String baseSql,
                                              Map<String, Object> paramMap) {
        String sql = "select * from (" + baseSql + ") a where 1=1";
        sql = setParams(inclassPageVo, sql, paramMap);
        int start = (inclassPageVo.getPage() - 1) * inclassPageVo.getPageSize();
        int size = inclassPageVo.getPageSize();
        sql += " ORDER BY a.roomId asc limit " + start + "," + size;
        return sql;
    }

    public List<CourseTableDetailResource> findInClassCourseTableDetailByRoomId(String id,
                                                                                String isDistinguishCourseStudentType,
                                                                                String courseStudentTypeIds) {
        List<CourseTableDetailResource> courseTableDetailResourceList = findInClassCourseTableDetailByRoomId(id, null, isDistinguishCourseStudentType
                , courseStudentTypeIds);
        if (CollectionUtils.isEmpty(courseTableDetailResourceList) || (courseTableDetailResourceList.size() == 1 &&
                StringUtils.isBlank(courseTableDetailResourceList.get(0).getId()))) {
        } else {
            courseTableDetailResourceList.forEach(courseTableDetailResource -> {
                Integer studentTypeIndex = courseTableDetailResource.getStudentType();
                if (ObjectUtils.isNotEmpty(studentTypeIndex)) {
                    StudentType studentType = StudentType.getStudentType(studentTypeIndex);
                    if (ObjectUtils.isNotEmpty(studentType)) {
                        courseTableDetailResource.setStudentTypeName(studentType.getAliasName());
                    } else {
                        courseTableDetailResource.setStudentTypeName("");
                    }
                } else {
                    courseTableDetailResource.setStudentTypeName("");
                }

            });
        }
        return courseTableDetailResourceList;
    }

    public List<CourseTableDetailResource> findInClassCourseTableDetailByRoomId(String id, String sources,
                                                                                String isDistinguishCourseStudentType,
                                                                                String courseStudentTypeIds) {
        Date nowDate = new Date();
        String day = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String time = DateUtils.formatDate(DateUtils.TIME, nowDate);
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT ctd.id AS id, DATE_FORMAT(ctd.course_date, '%Y-%m-%d')  as courseDate, c.course_name AS courseName," +
                " ctdru.room_id AS roomId, ctdru.room_name AS roomName,g.class_composition_name as classCompositionName,g.source," +
                " CONCAT(CONCAT(date_format( ctd.course_date, '%Y-%m-%d'),' ')," +
                "DATE_FORMAT( ctd.segment_start_time, '%H:%i:%s')) as segmentStartTime, " +
                " CONCAT(CONCAT(date_format( ctd.course_date, '%Y-%m-%d'),' ')," +
                "DATE_FORMAT( ctd.segment_end_time, '%H:%i:%s')) as segmentEndTime, " +
                "(SELECT GROUP_CONCAT(teacher_name,IF( teacher_title is not null , '(', '')," +
                "IF( teacher_title is not null , teacher_title, '')," +
                "IF( teacher_title is not null , ')', '') order by show_order ) FROM tb_course_table_detail_teacher WHERE " +
                "course_table_detail_id = ctd.id GROUP BY ctd.id) AS teacherName," +
                "( SELECT GROUP_CONCAT( segment  ORDER BY segment ) FROM tb_course_segment WHERE " +
                " course_table_detail_id = ctd.id GROUP BY ctd.id ) AS segments," +
                "ctd.course_kind AS courseKind,c.id AS courseId,cs.segment as currentSegment," +
                "cc.course_category_name as courseAttrName,ct.college_name as collegeName," +
                "g.id as groupId,g.group_name as groupName,ctd.week as week,(case count( DISTINCT gm.id ) when 0 then null " +
                " else count(DISTINCT gm.id) end )AS studentNum,cast(ctd.week_num as char) as weekNum ," +
                "(SELECT GROUP_CONCAT( teacher_name, ' ', teacher_no order by show_order ) FROM tb_course_table_detail_teacher WHERE " +
                "course_table_detail_id = ctd.id GROUP BY ctd.id) AS teacherNameAndNo ,ct.student_type as studentType, " +
                "ctd.source_data_source as sourceDataSource,ctd.source_data_source_name as sourceDataSourceName" +
                " FROM tb_course_table_detail ctd " +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct ON ctd.course_table_id = ct.id " +
                " INNER JOIN tb_course c ON ct.course_id = c.id " +
                " INNER JOIN tb_course_table_detail_room_user ctdru ON ctd.id = ctdru.course_table_detail_id " +
                " INNER JOIN tb_segment s ON cs.build_id = s.buildid AND cs.segment = s.segment  " +
                " INNER JOIN tb_course_category cc on ct.course_category_id =cc.id " +
                " INNER JOIN tb_group g on ct.group_id= g.id left JOIN tb_group_member gm " +
                " on g.id = gm.group_id and gm.group_member_status = '0' and gm.learning_method_code =1 where c.use_state=1 and" +
                " ctd.course_date = :day and s.start_time<= :time and s.end_time> :time and ctdru.has_live=1 ";
        paramMap.put("day", day);
        paramMap.put("time", time);
        if (StringUtils.isNotBlank(sources)) {
            List<String> sourceList = Arrays.asList(sources.split(","));
            sql += " and ctd.source in ( :sourceList )";
            paramMap.put("sourceList", sourceList);
        }
        if (StringUtils.isNotBlank(isDistinguishCourseStudentType)
                && NEED_DISTINGUISH_COURSE_STUDENT_TYPE.equals(isDistinguishCourseStudentType)) {
            if (StringUtils.isNotBlank(courseStudentTypeIds)) {
                List<String> courseStudentTypeList = Arrays.asList(courseStudentTypeIds.split(","));
                sql += " and ct.student_type in ( :courseStudentTypeList )";
                paramMap.put("courseStudentTypeList", courseStudentTypeList);
            } else {
                sql += " and 1=0 ";
            }
        }

        sql += " and ctdru.room_id= :roomId ";
        paramMap.put("roomId", id);
        Query queryData = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailResource.class));
        return queryData.getResultList();
    }

    public ResponseEntity<CourseTableDetailPageModel> findCourseTableDetailInClassDetails(InclassModelRequest inclassModelRequest) {
        CourseTableDetailPageModel courseTableDetailPageModel = new CourseTableDetailPageModel();
        Map<String, Object> paramMap = new HashMap<>();
        String baseSql = createCourseTableDetailInClassDetailsBaseSql(inclassModelRequest.getSources(), paramMap);
        Query queryCount = entityManager.createNativeQuery(createCourseTableDetailInClassDetailsCountSql(inclassModelRequest, baseSql, paramMap));
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        int count = ((BigInteger) resMap.get("allNumber")).intValue();
        if (count == 0) {
            courseTableDetailPageModel.setTotal(0);
            courseTableDetailPageModel.setPage(inclassModelRequest.getPage());
            courseTableDetailPageModel.setPageCount(0);
            courseTableDetailPageModel.setPageSize(inclassModelRequest.getPageSize());
            courseTableDetailPageModel.setCourseTableDetailModelList(new ArrayList<>());
        } else {
            String sql = createCourseTableDetailInClassDetailsSql(inclassModelRequest, baseSql, paramMap);
            Query queryData = entityManager.createNativeQuery(sql);
            paramMap.forEach(queryData::setParameter);
            queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseTableDetailModel.class));
            courseTableDetailPageModel.setTotal(count);
            courseTableDetailPageModel.setPage(inclassModelRequest.getPage());
            courseTableDetailPageModel.setPageCount((int) Math.ceil((double) count /
                    (double) inclassModelRequest.getPageSize()));
            courseTableDetailPageModel.setPageSize(inclassModelRequest.getPageSize());
            courseTableDetailPageModel.setCourseTableDetailModelList(queryData.getResultList());
            courseTableDetailPageModel.getCourseTableDetailModelList().forEach(courseTableDetailModel ->
                    courseTableDetailModel.setSourceName(
                            Objects.requireNonNull(
                                    Source.getUserSource(Integer.parseInt(courseTableDetailModel.getSourceId()))
                            ).getSourceName()
                    )
            );
        }
        return new ResponseEntity<>(courseTableDetailPageModel, HttpStatus.OK);
    }

    private String createCourseTableDetailInClassDetailsBaseSql(String sources, Map<String, Object> paramMap) {
        Date nowDate = new Date();
        String day = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String time = DateUtils.formatDate(DateUtils.TIME, nowDate);
        String sql = "SELECT ctd.id as id , c.course_name as courseName,\n" +
                " ctdru.room_id as roomId,  ctdru.room_name as roomName,\n" +
                " (select GROUP_CONCAT(teacher_name) from tb_course_table_detail_teacher where course_table_detail_id=ctd.id GROUP BY ctd.id )" +
                " as teacherName,  ctd.course_kind as courseKind ,c.id as courseId," +
                " c.college_id as collegeId, ctd.source as sourceId " +
                " FROM tb_course_table_detail ctd " +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                " INNER JOIN tb_course c ON ct.course_id = c.id " +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment where " +
                " c.use_state=1 and ctd.course_date = :day " +
                "and s.start_time<= :time and s.end_time> :time and  ctdru.has_live=1 ";
        paramMap.put("day", day);
        paramMap.put("time", time);
        if (StringUtils.isNotBlank(sources)) {
            List<String> sourceIds = Arrays.asList(sources.split(","));
            sql += " and ctd.source in ( :sourceIdList )";
            paramMap.put("sourceIdList", sourceIds);
        } else {
            sql += " and ctd.source in ('')";
        }
        sql += "and ctdru.room_id is not null ";
        return sql;
    }

    private String createCourseTableDetailInClassDetailsCountSql(InclassModelRequest inclassModelRequest, String baseSql,
                                                                 Map<String, Object> paramMap) {
        String countSql = "select count(1) as allNumber from (" + baseSql + ") a where 1=1 ";
        countSql = setCourseTableDetailInClassDetailsParams(inclassModelRequest, countSql, paramMap);
        return countSql;
    }

    private String setCourseTableDetailInClassDetailsParams(InclassModelRequest inclassModelRequest, String querySql, Map<String, Object> paramMap) {

        if (StringUtils.isNotBlank(inclassModelRequest.getSearchParams())) {
            String[] list = inclassModelRequest.getSearchParams().split(" ");
            for (String tempString : list) {
                tempString = CharactersReplace.replaceCharacters(tempString);
                int index = 0;
                if (StringUtils.isNotBlank(tempString)) {
                    querySql += " and (a.courseName like :tempString" + index +
                            " or a.teacherName like :tempString" + index +
                            " or a.roomName like :tempString" + index + ")";
                    index++;
                    paramMap.put("tempString" + index, "%" + tempString + "%");
                }
            }
        }
        if (StringUtils.isNotBlank(inclassModelRequest.getCourseId())) {
            querySql += " and a.courseId= :courseId ";
            paramMap.put("courseId", inclassModelRequest.getCourseId());
        }

        if (StringUtils.isEmpty(inclassModelRequest.getCourseIds())) {
            querySql += " and (a.courseId in ('')";
        } else {
            List<String> ids = Arrays.asList(inclassModelRequest.getCourseIds().split(","));
            querySql += " and (a.courseId in ( :courseIdList )";
            paramMap.put("courseIdList", ids);
        }
        if (StringUtils.isEmpty(inclassModelRequest.getCollegeIds())) {
            querySql += " or a.collegeId in (''))";
        } else {
            List<String> ids = Arrays.asList(inclassModelRequest.getCollegeIds().split(","));
            querySql += " or a.collegeId in ( :collegeIdList ))";
            paramMap.put("collegeIdList", ids);
        }
        return querySql;
    }

    private String createCourseTableDetailInClassDetailsSql(InclassModelRequest inclassModelRequest, String baseSql, Map<String, Object> paramMap) {
        String sql = "select * from (" + baseSql + ") a where 1=1";
        sql = setCourseTableDetailInClassDetailsParams(inclassModelRequest, sql, paramMap);
        int start = (inclassModelRequest.getPage() - 1) * inclassModelRequest.getPageSize();
        int size = inclassModelRequest.getPageSize();
        sql += " ORDER BY a.roomId asc limit " + start + "," + size;
        return sql;
    }


    public List<ExpertAuthorizationChildListOne> getExpertAuthorizationScheduleResource(
            ExpertAuthorizationScheduleParam expertAuthorizationScheduleParam) {
        List<ExpertAuthorizationScheduleResource> scheduleResourceList;
        Map<String, Object> paramMap = new HashMap<>();
        String baseSql = createExpertAuthorizationScheduleParamSql(paramMap, expertAuthorizationScheduleParam);
        Query queryData = entityManager.createNativeQuery(baseSql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ExpertAuthorizationScheduleResource.class));
        scheduleResourceList = queryData.getResultList();

        Map<String, List<ExpertAuthorizationScheduleResource>> groupByCourseCode = scheduleResourceList.stream()
                .collect(Collectors.groupingBy(ExpertAuthorizationScheduleResource::getCourseCode));
        List<ExpertAuthorizationChildListOne> expertAuthorizationChildListOneList = new ArrayList<>();

        groupByCourseCode.forEach((k, v) -> {
            ExpertAuthorizationChildListOne expertAuthorizationChildListOne = new ExpertAuthorizationChildListOne();
            expertAuthorizationChildListOne.setCourseCode(v.get(0).getCourseCode());
            expertAuthorizationChildListOne.setCourseName(v.get(0).getCourseName());
            expertAuthorizationChildListOne.setSchoolYear(v.get(0).getSchoolYear());
            expertAuthorizationChildListOne.setTerm(v.get(0).getTerm());
            expertAuthorizationChildListOne.setNodeId(v.get(0).getCourseCode());
            expertAuthorizationChildListOne.setNodeLevel(1);
            expertAuthorizationChildListOne.setParentId(String.valueOf(0));

            List<ExpertAuthorizationChildListTwo> expertAuthorizationChildListTwoList = new ArrayList<>();
            for (ExpertAuthorizationScheduleResource expertAuthorizationScheduleResource : v) {
                ExpertAuthorizationChildListTwo expertAuthorizationChildListTwo = new ExpertAuthorizationChildListTwo();
                expertAuthorizationChildListTwo.setTeacherNameAndNo(expertAuthorizationScheduleResource.getTeacherNameAndNo());
                expertAuthorizationChildListTwo.setCourseDate(expertAuthorizationScheduleResource.getCourseDate());
                expertAuthorizationChildListTwo.setSegments(expertAuthorizationScheduleResource.getSegments());
                expertAuthorizationChildListTwo.setGroupId(expertAuthorizationScheduleResource.getGroupId());
                expertAuthorizationChildListTwo.setRoomId(expertAuthorizationScheduleResource.getRoomId());
                expertAuthorizationChildListTwo.setCourseTableId(expertAuthorizationScheduleResource.getCourseTableId());
                expertAuthorizationChildListTwo.setNodeId(expertAuthorizationScheduleResource.getId());
                expertAuthorizationChildListTwo.setNodeLevel(Constant.TWO);
                expertAuthorizationChildListTwo.setParentId(expertAuthorizationScheduleResource.getCourseCode());
                expertAuthorizationChildListTwo.setCourseTableDetailId(expertAuthorizationScheduleResource.getCourseTableDetailId());

                List<ExpertAuthorizationChildListThree> expertAuthorizationChildListThreeList = new ArrayList<>();
                List<String> segmentList = Arrays.asList(expertAuthorizationScheduleResource.getSegments().split(","));
                segmentList.forEach(s -> {
                    ExpertAuthorizationChildListThree expertAuthorizationChildListThree = new ExpertAuthorizationChildListThree();
                    expertAuthorizationChildListThree.setSegments(s);
                    expertAuthorizationChildListThree.setNodeId(expertAuthorizationScheduleResource.getId() + '-' + s);
                    expertAuthorizationChildListThree.setNodeLevel(Constant.THREE);
                    expertAuthorizationChildListThree.setParentId(expertAuthorizationScheduleResource.getId());
                    expertAuthorizationChildListThreeList.add(expertAuthorizationChildListThree);

                });
                expertAuthorizationChildListTwo.setChildList(expertAuthorizationChildListThreeList);
                expertAuthorizationChildListTwoList.add(expertAuthorizationChildListTwo);
            }
            expertAuthorizationChildListOne.setChildList(expertAuthorizationChildListTwoList);
            expertAuthorizationChildListOneList.add(expertAuthorizationChildListOne);
        });
        return expertAuthorizationChildListOneList;
    }

    private String createExpertAuthorizationScheduleParamSql(Map<String, Object> paramMap, ExpertAuthorizationScheduleParam scheduleParam) {
        String sql = "SELECT * FROM ( SELECT CONCAT(ctd.course_table_id,ctd.course_date,ctd.segment) as id ," +
                "c.course_name as courseName,c.course_code as courseCode ,ctd.course_table_id as courseTableId," +
                "GROUP_CONCAT(CONCAT( ctdt.teacher_name,'(',ctdt.teacher_no,')')) as teacherNameAndNo," +
                "ct.school_year as schoolYear,ct.term ,ctd.segment as segments,CAST(ctd.course_date as char) as courseDate, " +
                "ctdru.room_id as roomId, ct.group_id as groupId,ctd.id as courseTableDetailId " +
                "FROM tb_course_table_detail ctd " +
                "INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                "INNER JOIN tb_course c ON ct.course_id = c.id " +
                "INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                "INNER JOIN tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id " +
                "where c.use_state=1 and ctd.course_date = :courseDate " +
                "and ctdru.has_live = 1 " +
                "and ctd.source = 0 " +
                "and ct.student_type = 0 " +
                "and ctdru.room_id is not null ";

        paramMap.put("courseDate", scheduleParam.getCourseDate());

        if (StringUtils.isNotEmpty(scheduleParam.getCourseCodeOrName())) {
            sql += " and (c.course_name like  :courseCodeOrName  " +
                    " or c.course_code like   :courseCodeOrName  ) ";
            paramMap.put("courseCodeOrName", "%" + scheduleParam.getCourseCodeOrName().trim() + "%");
        }
        if (StringUtils.isNotEmpty(scheduleParam.getCourseTableCustomIds())) {
            String result = convertToQuotedStringBySemicolon(scheduleParam.getCourseTableCustomIds());
            sql += " and CONCAT(ctd.course_table_id,ctd.course_date,ctd.segment) not  in ( :courseTableCustomIds )";
            paramMap.put("courseTableCustomIds", result);
        }
        if (StringUtils.isNotBlank(scheduleParam.getCourseTableCollegeId())) {
            sql += " and ct.college_id = :courseTableCollegeId ";
            paramMap.put("courseTableCollegeId", scheduleParam.getCourseTableCollegeId());
        }

        sql += "GROUP BY ctd.id order by ctd.segment_start_time,ctd.segment_end_time,course_table_id ) a  ";

        if (StringUtils.isNotEmpty(scheduleParam.getTeacherNoOrName())) {
            sql += "WHERE a.teacherNameAndNo like :teacherNoOrName ";
            paramMap.put("teacherNoOrName", "%" + scheduleParam.getTeacherNoOrName().trim() + "%");
        }

        return sql;
    }

    public List<LiveScheduleResource> getLiveCourseTableDetailList(LiveCourseTableDetailParam liveCourseTableDetailParam) {
        List<LiveScheduleResource> liveScheduleResourceList;
        Map<String, Object> paramMap = new HashMap<>();
        String baseSql = createLiveCourseTableSql(liveCourseTableDetailParam, paramMap);
        Query queryData = entityManager.createNativeQuery(baseSql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(LiveScheduleResource.class));
        liveScheduleResourceList = (List<LiveScheduleResource>) queryData.getResultList();
        if (CollectionUtils.isNotEmpty(liveScheduleResourceList)) {
            return new ArrayList<>(liveScheduleResourceList);
        } else {
            return liveScheduleResourceList;
        }
    }

    private String createLiveCourseTableSql(LiveCourseTableDetailParam liveCourseTableDetailParam, Map<String, Object> paramMap) {
        Date nowDate = new Date();
        String startTime = DateUtils.formatDate(DateUtils.TIME, nowDate);
        LocalDateTime segmentEndTime = LocalDateTime.parse(formatDate(DATE, new Date()) + " "
                + startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        startTime = segmentEndTime.plusMinutes(Long.parseLong(expertListeningEndTime)).format(df);
        String endTime = DateUtils.formatDate(DateUtils.TIME, nowDate);
        String date = DateUtils.formatDate(DateUtils.DATE, nowDate);
       // Term term = termService.getNowTerm();
        String sql = "SELECT  distinct ctd.id as courseTableDetailId , c.course_name as courseName,c.course_code as courseCode, " +
                " ctdru.room_id as roomId," +
                " ctdru.room_name as roomName, ctd.course_table_id as courseTableId ,g.group_name AS groupName," +
                " g.class_composition_name AS classCompositionName," +
                " GROUP_CONCAT(distinct CONCAT( ctdt.teacher_name,'(',ctdt.teacher_no,')')) as teacherNameAndNo," +
                " ct.school_year as schoolYear,ct.term ,CONVERT(cs.segment,CHAR(10)) as segments,CAST(ctd.course_date as char) as courseDate," +
                " CAST(s.start_time as char) as segmentStartTime,CAST(s.end_time as char) as segmentEndTime, " +
                " CAST(ctd.segment_start_time as char) as startTime,CAST(ctd.segment_end_time as char) as endTime, " +
                " sum( ctd.course_date ='" + date + "' and s.start_time<='" + startTime +
                "' and s.end_time>'" + endTime + "'    ) >0  as hasLiveBroadcast " +
                " FROM tb_course_table_detail ctd " +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                " INNER JOIN tb_group g ON ct.group_id = g.id " +
                " INNER JOIN tb_course c ON ct.course_id = c.id " +
                " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                " INNER JOIN tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id " +
                " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment where " +
                " c.use_state=1 and ctdru.has_live=1 and ctd.source= 0 " +
                " and (ctdru.room_id is not null or ctdru.room_id!='') ";
        if (1 == liveCourseTableDetailParam.getAuthorizationScopeType() || date.equals(liveCourseTableDetailParam.getCourseDate())) {
            sql += " and ctd.course_date =:date and s.start_time<='" + startTime + "' and s.end_time>'" + endTime + "'";
            paramMap.put("date", liveCourseTableDetailParam.getCourseDate());
        } else if (Constant.SCOPE_OF_AUTHORITY_ON_DEMAND == liveCourseTableDetailParam.getAuthorizationScopeType()) {
            sql += "  and ctd.course_date <'" + date + "'  ";

        } else {
            sql += "  and ((ctd.course_date ='" + date + "' and s.start_time<='" + startTime + "' and s.end_time>'" + endTime + "') " +
                    "|| ctd.course_date !='" + date + "')";
        }

        if (ObjectUtils.isNotEmpty(liveCourseTableDetailParam) && ObjectUtils.isNotEmpty(liveCourseTableDetailParam.getCourseDate())) {
            sql += " and ctd.course_date =:date  ";
            paramMap.put("date", liveCourseTableDetailParam.getCourseDate());
        }

        if (ObjectUtils.isNotEmpty(liveCourseTableDetailParam) && ObjectUtils.isNotEmpty(liveCourseTableDetailParam.getCourseTableCustomIdList())
                && liveCourseTableDetailParam.getCourseTableCustomIdList().size() > 0) {
            sql += " and CONCAT( ctd.course_table_id, ctd.course_date, ctd.segment, cs.segment, ctdru.room_id ) in (:courseTableCustomIdList) ";
            paramMap.put("courseTableCustomIdList", liveCourseTableDetailParam.getCourseTableCustomIdList());
        }

        if (ObjectUtils.isNotEmpty(liveCourseTableDetailParam) && StringUtils.isNotBlank(liveCourseTableDetailParam.getStudentType())) {
            sql += " and ct.student_type = :studentType ";
            paramMap.put("studentType", liveCourseTableDetailParam.getStudentType());
        }

        if (ObjectUtils.isNotEmpty(liveCourseTableDetailParam) && StringUtils.isNotBlank(liveCourseTableDetailParam.getCourseCode())) {
            sql += " and c.course_code  = :courseCode ";
            paramMap.put("courseCode", liveCourseTableDetailParam.getCourseCode());
        }

        sql += " GROUP BY cs.id order by ctd.course_date desc,ctd.segment_start_time,ctd.segment_end_time,course_table_id ";

        return sql;
    }


    public LiveScheduleResourcePageVo getLiveCourseList(LiveCourseParam liveCourseParam) {
        LiveScheduleResourcePageVo liveScheduleResourcePageVo = new LiveScheduleResourcePageVo();
        List<LiveCourseSqlVo> courseResourceList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        String baseCountSql = createLiveCourseCountSql(liveCourseParam, paramMap);
        Query queryCount = entityManager.createNativeQuery(baseCountSql);
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        int count = ((BigInteger) resMap.get("allNumber")).intValue();
        if (count == 0) {
            liveScheduleResourcePageVo.setTotalCount(0);
            liveScheduleResourcePageVo.setPage(liveCourseParam.getPage());
            liveScheduleResourcePageVo.setPageCount(0);
        } else {
            String baseSql = createLiveCourseSql(liveCourseParam, paramMap);
            Query queryData = entityManager.createNativeQuery(baseSql);
            paramMap.forEach(queryData::setParameter);
            queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(LiveCourseSqlVo.class));
            courseResourceList = (List<LiveCourseSqlVo>) queryData.getResultList();
            liveScheduleResourcePageVo.setTotalCount(count);
            liveScheduleResourcePageVo.setPage(liveCourseParam.getPage());
            liveScheduleResourcePageVo.setPageCount((int) Math.ceil((double) count /
                    (double) liveCourseParam.getPageSize()));
        }
        liveScheduleResourcePageVo.setPageSize(liveCourseParam.getPageSize());
        liveScheduleResourcePageVo.setLiveScheduleResourceList(buildLiveCourseResource(courseResourceList));
        return liveScheduleResourcePageVo;
    }

    private List<LiveCourseResource> buildLiveCourseResource(List<LiveCourseSqlVo> courseResourceList) {

        List<LiveCourseResource> resourceList = new ArrayList<>();
        for (LiveCourseSqlVo liveCourseSqlVo : courseResourceList) {
            LiveCourseResource courseResource = new LiveCourseResource();
            courseResource.setCourseName(liveCourseSqlVo.getCourseName());
            courseResource.setCourseCode(liveCourseSqlVo.getCourseCode());
            courseResource.setId(liveCourseSqlVo.getId());
            courseResource.setHasLiveBroadcast(liveCourseSqlVo.getHasLiveBroadcast().intValue());
            resourceList.add(courseResource);
        }
        return resourceList;
    }

    private String createLiveCourseSql(LiveCourseParam liveCourseParam, Map<String, Object> paramMap) {
        Date nowDate = new Date();
        String startTime = DateUtils.formatDate(DateUtils.TIME, nowDate);
        LocalDateTime segmentEndTime = LocalDateTime.parse(formatDate(DATE, new Date()) + " "
                + startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        startTime = segmentEndTime.plusMinutes(Long.parseLong(expertListeningEndTime)).format(df);
        String endTime = DateUtils.formatDate(DateUtils.TIME, nowDate);
        String date = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String sql = "SELECT * FROM ( SELECT DISTINCT courseCode,courseName,id, " +
                " CAST(max( hasLiveBroadcast ) AS UNSIGNED INTEGER) AS hasLiveBroadcast  FROM ( ";
        if (1 == liveCourseParam.getAuthorizationScopeType() || 0 == liveCourseParam.getAuthorizationScopeType()) {
            sql += "SELECT DISTINCT c.id as 'id',c.course_name as 'courseName',c.course_code as 'courseCode' ," +
                    " sum( ctd.course_date ='" + date + "' and s.start_time<='" + startTime +
                    "' and s.end_time>'" + endTime + "'    ) >0  as hasLiveBroadcast " +
                    " FROM tb_course_table_detail ctd INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                    " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id  " +
                    " INNER JOIN tb_course c ON ct.course_id = c.id " +
                    " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                    " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment where " +
                    " c.use_state=1 and ctdru.has_live=1 and ctd.source= 0 " +
                    " and (ctdru.room_id is not null or ctdru.room_id!='') " +
                    " and ct.school_year=:schoolYear and ct.term =:term " +
                    " and ctd.course_date ='" + date + "' and s.start_time<='" + startTime + "' and s.end_time>'" + endTime + "'";
            paramMap.put("schoolYear", liveCourseParam.getSchoolYear());
            paramMap.put("term", liveCourseParam.getTerm());
            if (ObjectUtils.isNotEmpty(liveCourseParam) && ObjectUtils.isNotEmpty(liveCourseParam.getCourseTableCustomIdList())
                    && liveCourseParam.getCourseTableCustomIdList().size() > 0) {
                sql += " and CONCAT(ctd.course_table_id,ctd.course_date,ctd.segment,cs.segment) in (:courseTableCustomIdList) ";
                paramMap.put("courseTableCustomIdList", liveCourseParam.getCourseTableCustomIdList());
            }
            if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getStudentType())) {
                sql += " and ct.student_type = :studentType ";
                paramMap.put("studentType", liveCourseParam.getStudentType());
            }
            if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getCourseName())) {
                sql += " and c.course_name like :courseName ";
                paramMap.put("courseName", "%" + liveCourseParam.getCourseName() + "%");
            }
            sql += "GROUP BY c.id ";
        }
        if (0 == liveCourseParam.getAuthorizationScopeType()) {
            sql += " UNION ";
        }
        if (Constant.SCOPE_OF_AUTHORITY_ON_DEMAND == liveCourseParam.getAuthorizationScopeType()
                || 0 == liveCourseParam.getAuthorizationScopeType()) {
            sql += "SELECT DISTINCT c.id as 'id',c.course_name as 'courseName',c.course_code as 'courseCode' , " +
                    " 0 as hasLiveBroadcast FROM tb_course_table_detail ctd " +
                    " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                    " INNER JOIN tb_course c ON ct.course_id = c.id " +
                    " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                    " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                    " INNER JOIN tb_segment s ON cs.build_id = s.buildid AND cs.segment = s.segment " +
                    " where c.use_state=1 and ctdru.has_live=1 and ctd.source= 0 " +
                    " and (ctdru.room_id is not null or ctdru.room_id!='') " +
                    " and ct.school_year=:schoolYear and ct.term =:term  and ctd.course_date <:date ";
            paramMap.put("schoolYear", liveCourseParam.getSchoolYear());
            paramMap.put("term", liveCourseParam.getTerm());
            paramMap.put("date", date);
            if (ObjectUtils.isNotEmpty(liveCourseParam) && ObjectUtils.isNotEmpty(liveCourseParam.getCourseTableCustomIdList())
                    && liveCourseParam.getCourseTableCustomIdList().size() > 0) {
                sql += " and CONCAT(ctd.course_table_id,ctd.course_date,ctd.segment,cs.segment) in (:courseTableCustomIdList) ";
                paramMap.put("courseTableCustomIdList", liveCourseParam.getCourseTableCustomIdList());
            }
            if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getStudentType())) {
                sql += " and ct.student_type = :studentType ";
                paramMap.put("studentType", liveCourseParam.getStudentType());
            }
            if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getCourseName())) {
                sql += " and c.course_name like :courseName ";
                paramMap.put("courseName", "%" + liveCourseParam.getCourseName() + "%");
            }
            sql += "GROUP BY c.id ORDER BY courseCode ";
        }
        sql += " ) aa GROUP BY courseCode ) aaa ORDER BY aaa.hasLiveBroadcast DESC, aaa.courseCode ";
        int start = (liveCourseParam.getPage() - 1) * liveCourseParam.getPageSize();
        int size = liveCourseParam.getPageSize();
        sql += " limit " + start + "," + size;
        return sql;
    }

    private String createLiveCourseCountSql(LiveCourseParam liveCourseParam, Map<String, Object> paramMap) {
        Date nowDate = new Date();
        String startTime = DateUtils.formatDate(DateUtils.TIME, nowDate);
        LocalDateTime segmentEndTime = LocalDateTime.parse(formatDate(DATE, new Date()) + " "
                + startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        startTime = segmentEndTime.plusMinutes(Long.parseLong(expertListeningEndTime)).format(df);
        String endTime = DateUtils.formatDate(DateUtils.TIME, nowDate);
        String date = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String sql = "SELECT COUNT( a.id ) AS 'allNumber' FROM ( SELECT * FROM ( SELECT DISTINCT courseCode,courseName,id, " +
                " CAST(max( hasLiveBroadcast ) AS UNSIGNED INTEGER) AS hasLiveBroadcast FROM ( ";
        if (1 == liveCourseParam.getAuthorizationScopeType() || 0 == liveCourseParam.getAuthorizationScopeType()) {
            sql += "SELECT DISTINCT c.id as 'id',c.course_name as 'courseName',c.course_code as 'courseCode' ," +
                    " sum( ctd.course_date ='" + date + "' and s.start_time<='" + startTime +
                    "' and s.end_time>'" + endTime + "'    ) >0  as hasLiveBroadcast " +
                    " FROM tb_course_table_detail ctd " +
                    " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                    " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id  " +
                    " INNER JOIN tb_course c ON ct.course_id = c.id " +
                    " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                    " INNER JOIN tb_segment s on cs.build_id = s.buildid and cs.segment = s.segment where " +
                    " c.use_state=1 and ctdru.has_live=1 and ctd.source= 0 " +
                    " and (ctdru.room_id is not null or ctdru.room_id!='') " +
                    " and ct.school_year=:schoolYear and ct.term =:term " +
                    " and ctd.course_date ='" + date + "' and s.start_time<='" + startTime + "' and s.end_time>'" + endTime + "'";
            paramMap.put("schoolYear", liveCourseParam.getSchoolYear());
            paramMap.put("term", liveCourseParam.getTerm());
            if (ObjectUtils.isNotEmpty(liveCourseParam) && ObjectUtils.isNotEmpty(liveCourseParam.getCourseTableCustomIdList())
                    && liveCourseParam.getCourseTableCustomIdList().size() > 0) {
                sql += " and CONCAT(ctd.course_table_id,ctd.course_date,ctd.segment,cs.segment) in (:courseTableCustomIdList) ";
                paramMap.put("courseTableCustomIdList", liveCourseParam.getCourseTableCustomIdList());
            }
            if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getStudentType())) {
                sql += " and ct.student_type = :studentType ";
                paramMap.put("studentType", liveCourseParam.getStudentType());
            }
            if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getCourseName())) {
                sql += " and c.course_name like :courseName ";
                paramMap.put("courseName", "%" + liveCourseParam.getCourseName() + "%");
            }
            sql += " GROUP BY c.id ";
        }
        if (0 == liveCourseParam.getAuthorizationScopeType()) {
            sql += " UNION ";
        }
        if (Constant.SCOPE_OF_AUTHORITY_ON_DEMAND == liveCourseParam.getAuthorizationScopeType()
                || 0 == liveCourseParam.getAuthorizationScopeType()) {
            sql += "SELECT DISTINCT c.id as 'id',c.course_name as 'courseName',c.course_code as 'courseCode' ," +
                    " 0 as hasLiveBroadcast FROM tb_course_table_detail ctd " +
                    " INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                    " INNER JOIN tb_course c ON ct.course_id = c.id " +
                    " INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                    " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                    " INNER JOIN tb_segment s ON cs.build_id = s.buildid AND cs.segment = s.segment " +
                    " where c.use_state=1 and ctdru.has_live=1 and ctd.source= 0 " +
                    " and (ctdru.room_id is not null or ctdru.room_id!='') " +
                    " and ct.school_year=:schoolYear and ct.term =:term  and ctd.course_date <:date ";
            paramMap.put("schoolYear", liveCourseParam.getSchoolYear());
            paramMap.put("term", liveCourseParam.getTerm());
            paramMap.put("date", date);
            if (ObjectUtils.isNotEmpty(liveCourseParam) && ObjectUtils.isNotEmpty(liveCourseParam.getCourseTableCustomIdList())
                    && liveCourseParam.getCourseTableCustomIdList().size() > 0) {
                sql += " and CONCAT(ctd.course_table_id,ctd.course_date,ctd.segment,cs.segment) in (:courseTableCustomIdList) ";
                paramMap.put("courseTableCustomIdList", liveCourseParam.getCourseTableCustomIdList());
            }
            if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getStudentType())) {
                sql += " and ct.student_type = :studentType ";
                paramMap.put("studentType", liveCourseParam.getStudentType());
            }
            if (ObjectUtils.isNotEmpty(liveCourseParam) && StringUtils.isNotBlank(liveCourseParam.getCourseName())) {
                sql += " and c.course_name like :courseName ";
                paramMap.put("courseName", "%" + liveCourseParam.getCourseName() + "%");
            }
            sql += " GROUP BY c.id ORDER BY courseCode ";
        }
        sql += " ) aa GROUP BY courseCode ) aaa ORDER BY aaa.hasLiveBroadcast DESC, aaa.courseCode ) a ";
        return sql;
    }


    public LessonPreviewPage getLiveCourseTablePage(LessonPreviewParam lessonPreviewParam) {
        Date nowDate = new Date();
        String startTime = DateUtils.formatDate(DateUtils.TIME, nowDate);
        LocalDateTime segmentEndTime = LocalDateTime.parse(formatDate(DATE, new Date()) + " "
                + startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        startTime = segmentEndTime.plusMinutes(Long.parseLong(expertListeningEndTime)).format(df);
        LessonPreviewPage lessonPreviewPage = new LessonPreviewPage();
        List<LessonPreview> lessonPreviewList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
//        String baseCountSql = createLiveCourseTableCountSql(lessonPreviewParam, paramMap, startTime);
//        Query queryCount = entityManager.createNativeQuery(baseCountSql);
//        paramMap.forEach(queryCount::setParameter);
//        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
//        int count = ((BigInteger) resMap.get("allNumber")).intValue();
//        if (count == 0) {
//            lessonPreviewPage.setTotalCount(0);
//            lessonPreviewPage.setPage(lessonPreviewParam.getPage());
//            lessonPreviewPage.setPageCount(0);
//        } else {
//            String baseSql = createLiveCourseTableSql(lessonPreviewParam, paramMap, startTime);
//            Query queryData = entityManager.createNativeQuery(baseSql);
//            paramMap.forEach(queryData::setParameter);
//            queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(LessonPreview.class));
//            lessonPreviewList = (List<LessonPreview>) queryData.getResultList();
//            lessonPreviewPage.setTotalCount(count);
//            lessonPreviewPage.setPage(lessonPreviewParam.getPage());
//            lessonPreviewPage.setPageCount((int) Math.ceil((double) count /
//                    (double) lessonPreviewParam.getPageSize()));
//        }
        String baseSql = createLiveCourseTableSql(lessonPreviewParam, paramMap, startTime);
        Query queryData = entityManager.createNativeQuery(baseSql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(LessonPreview.class));
        lessonPreviewList = (List<LessonPreview>) queryData.getResultList();
        //  lessonPreviewPage.setTotalCount(count);
        lessonPreviewPage.setPage(lessonPreviewParam.getPage());
//        lessonPreviewPage.setPageCount((int) Math.ceil((double) count /
//                (double) lessonPreviewParam.getPageSize()));
        lessonPreviewPage.setPageSize(lessonPreviewParam.getPageSize());
        lessonPreviewPage.setLessonPreviewList(lessonPreviewList);
        return lessonPreviewPage;
    }

    private String createLiveCourseTableSql(LessonPreviewParam lessonPreviewParam, Map<String, Object> paramMap, String startTime) {
        Date nowDate = new Date();
        String date = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String sql = "SELECT DISTINCT c.course_code AS courseCode, ctd.course_name as courseName, " +
                "ctd.teacher_names as teacherNames,CAST(ctd.course_date as char) as courseDate," +
                " CONVERT(ts.segment, CHAR(10)) as segments, " +
                "CAST(ts.start_time as char) as segmentStartTime ,CAST(ts.end_time as char) as segmentEndTime " +
                "FROM tb_course_table_detail ctd " +
                "INNER JOIN tb_course_table ct on ctd.course_table_id = ct.id " +
                "INNER JOIN tb_course c ON ct.course_id = c.id " +
                "INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id " +
                "INNER JOIN tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id " +
                "INNER JOIN tb_course_segment tcs ON ctd.id = tcs.course_table_detail_id " +
                "INNER JOIN tb_segment ts ON tcs.segment = ts.segment " +
                "where ctdru.has_live=1 and ctd.source= 0 and (ctdru.room_id is not null or ctdru.room_id!='') ";
        if (StringUtils.isBlank(lessonPreviewParam.getCourseDate()) || date.equals(lessonPreviewParam.getCourseDate())) {
            sql += " and ctd.course_date =:date and ts.start_time>:time ";
            paramMap.put("date", date);
            paramMap.put("time", startTime);
        } else {
            sql += " and ctd.course_date =:date ";
            paramMap.put("date", lessonPreviewParam.getCourseDate());
        }
        if (ObjectUtils.isNotEmpty(lessonPreviewParam) && ObjectUtils.isNotEmpty(lessonPreviewParam.getCourseTableCustomIdList())
                && lessonPreviewParam.getCourseTableCustomIdList().size() > 0) {
            sql += " and CONCAT( ctd.course_table_id,ctd.course_date,ctd.segment,tcs.segment ) in (:courseTableCustomIdList) ";
            paramMap.put("courseTableCustomIdList", lessonPreviewParam.getCourseTableCustomIdList());
        }
        if (ObjectUtils.isNotEmpty(lessonPreviewParam) && StringUtils.isNotBlank(lessonPreviewParam.getStudentType())) {
            sql += "and ct.student_type = :studentType ";
            paramMap.put("studentType", lessonPreviewParam.getStudentType());
        }
        if (ObjectUtils.isNotEmpty(lessonPreviewParam) && StringUtils.isNotBlank(lessonPreviewParam.getCourseName())) {
            sql += "and ctd.course_name like :courseName ";
            paramMap.put("courseName", "%" + lessonPreviewParam.getCourseName() + "%");
        }
        sql += "GROUP BY tcs.id order by ts.start_time,ts.end_time,ct.id ";

//        int start = (lessonPreviewParam.getPage() - 1) * lessonPreviewParam.getPageSize();
//        int size = lessonPreviewParam.getPageSize();
//        sql += " limit " + start + "," + size;
        return sql;
    }

    private String createLiveCourseTableCountSql(LessonPreviewParam lessonPreviewParam, Map<String, Object> paramMap, String startTime) {
        Date nowDate = new Date();
        String date = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String sql = "SELECT COUNT(DISTINCT a.id) AS 'allNumber' FROM ( SELECT DISTINCT c.course_code AS courseCode, " +
                "ctd.course_name AS courseName, ctd.teacher_names AS teacherNames, CONVERT (ts.segment, CHAR ( 10 )) AS segments, " +
                "CAST( ts.start_time AS CHAR ) AS segmentStartTime, CAST( ts.end_time AS CHAR ) AS segmentEndTime, " +
                "CONCAT( ctd.course_table_id, ctd.course_date, ctd.segment, tcs.segment ) AS id " +
                "FROM tb_course_table_detail ctd " +
                "INNER JOIN tb_course_table ct ON ctd.course_table_id = ct.id " +
                "INNER JOIN tb_course c ON ct.course_id = c.id " +
                "INNER JOIN tb_course_table_detail_room_user ctdru ON ctd.id = ctdru.course_table_detail_id " +
                "INNER JOIN tb_course_table_detail_teacher ctdt ON ctd.id = ctdt.course_table_detail_id " +
                "INNER JOIN tb_course_segment tcs ON ctd.id = tcs.course_table_detail_id " +
                "INNER JOIN tb_segment ts ON tcs.segment = ts.segment " +
                "where ctdru.has_live=1 and ctd.source= 0 and (ctdru.room_id is not null or ctdru.room_id!='') ";

        if (StringUtils.isBlank(lessonPreviewParam.getCourseDate()) || date.equals(lessonPreviewParam.getCourseDate())) {
            sql += " and ctd.course_date =:date and ts.start_time>:time ";
            paramMap.put("date", date);
            paramMap.put("time", startTime);
        } else {
            sql += " and ctd.course_date =:date ";
            paramMap.put("date", lessonPreviewParam.getCourseDate());
        }

        if (ObjectUtils.isNotEmpty(lessonPreviewParam) && ObjectUtils.isNotEmpty(lessonPreviewParam.getCourseTableCustomIdList())
                && lessonPreviewParam.getCourseTableCustomIdList().size() > 0) {
            sql += " and CONCAT( ctd.course_table_id, ctd.course_date, ctd.segment, tcs.segment ) in (:courseTableCustomIdList) ";
            paramMap.put("courseTableCustomIdList", lessonPreviewParam.getCourseTableCustomIdList());
        }
        if (ObjectUtils.isNotEmpty(lessonPreviewParam) && StringUtils.isNotBlank(lessonPreviewParam.getStudentType())) {
            sql += " and ct.student_type = :studentType ";
            paramMap.put("studentType", lessonPreviewParam.getStudentType());
        }
        if (ObjectUtils.isNotEmpty(lessonPreviewParam) && StringUtils.isNotBlank(lessonPreviewParam.getCourseName())) {
            sql += " and ctd.course_name like :courseName ";
            paramMap.put("courseName", "%" + lessonPreviewParam.getCourseName() + "%");
        }
        sql += "GROUP BY tcs.id order by ts.start_time,ts.end_time,ct.id ) a ";
        return sql;
    }
}

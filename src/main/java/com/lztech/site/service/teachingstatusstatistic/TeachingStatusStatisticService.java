package com.lztech.site.service.teachingstatusstatistic;

import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetailroomuser.CourseTableDetailRoomUserRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.coursetabledetail.CourseTableDetailService;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.coursetabledetail.CourseTableIdAndCourseTableDetailId;
import com.lztech.site.viewmodel.teachingstatusstatistic.StatisticQueryResult;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TeachingStatusStatisticService {

    private final Logger log = LoggerFactory.getLogger(TeachingStatusStatisticService.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CourseTableDetailService courseTableDetailService;


    @Autowired
    private CourseTableDetailRoomUserRepository courseTableDetailRoomUserRepository;


    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;

    @Autowired
    private CourseTableRepository courseTableRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingStatusStatisticService.class);

    public Integer getCurrentInClassStudentNum() {
        Integer currentInClassStudentNum = 0;
        List<CourseTableIdAndCourseTableDetailId> courseTableDetailIds =
                courseTableDetailService.getIdByTime(TimeUtils.getNowDate(), TimeUtils.getNowDateMin());
        List<String> courseTableIds = courseTableDetailIds
                .stream()
                .map(CourseTableIdAndCourseTableDetailId::getCourseTableId)
                .collect(Collectors.toList());
        if (courseTableIds.size() < 1) {
            LOGGER.error("查询当前没有正在上课");
        } else {
            String querySql = buildCurrentInClassStudentNumQuerySql(courseTableIds);
            List resultList = entityManager.createNativeQuery(querySql, StatisticQueryResult.class).getResultList();
            if (resultList.size() > 0) {
                StatisticQueryResult statisticQueryResult = (StatisticQueryResult) resultList.get(0);
                currentInClassStudentNum = statisticQueryResult.getQueryResultNum();
            }
        }
        return currentInClassStudentNum;
    }

    private String buildCurrentInClassStudentNumQuerySql(List<String> courseTableIds) {
        String courseTableIdsStr;
        if (courseTableIds.size() == 1) {
            courseTableIdsStr = courseTableIds.stream()
                    .collect(Collectors.joining(",", "'", "'"));
        } else {
            courseTableIdsStr = courseTableIds.stream()
                    .collect(Collectors.joining(",", "'", "'")).replace(",", "','");
        }
        return "SELECT count(1) AS 'query_result_num' FROM tb_group_member WHERE " +
                " group_member_status = 0" +
                " AND group_id IN (" +
                " SELECT group_id  FROM tb_course_table WHERE id IN (" + courseTableIdsStr + "))";
    }

    public Integer getCurrentInClassTeacherNum() {
        Integer currentInClassTeacherNum = 0;
        List<CourseTableIdAndCourseTableDetailId> courseTableIdAndCourseTableDetailId =
                courseTableDetailService.getIdByTime(TimeUtils.getNowDate(), TimeUtils.getNowDateMin());
        List<String> courseTableDetailIds = courseTableIdAndCourseTableDetailId
                .stream()
                .map(CourseTableIdAndCourseTableDetailId::getId)
                .collect(Collectors.toList());
        if (courseTableDetailIds.size() < 1) {
            LOGGER.error("查询当前没有正在上课");
        } else {
            String querySql = buildCurrentInClassTeacherNumQuerySql(courseTableDetailIds);
            List resultList = entityManager.createNativeQuery(querySql, StatisticQueryResult.class).getResultList();
            if (resultList.size() > 0) {
                StatisticQueryResult statisticQueryResult = (StatisticQueryResult) resultList.get(0);
                currentInClassTeacherNum = statisticQueryResult.getQueryResultNum();
            }
        }
        return currentInClassTeacherNum;
    }

    private String buildCurrentInClassTeacherNumQuerySql(List<String> courseTableDetailIds) {
        String courseTableIdsStr;
        if (courseTableDetailIds.size() == 1) {
            courseTableIdsStr = courseTableDetailIds.stream()
                    .collect(Collectors.joining(",", "'", "'"));
        } else {
            courseTableIdsStr = courseTableDetailIds.stream()
                    .collect(Collectors.joining(",", "'", "'")).replace(",", "','");
        }
        return "SELECT count( 1 ) AS 'query_result_num' FROM tb_course_table_detail_teacher WHERE" +
                " course_table_detail_id IN (" + courseTableIdsStr + ")";
    }


    /**
     * 获取当前开课学院数量
     *
     * @return
     */
    public Integer getAllCollegeNum() {
        String nowDate = TimeUtils.getNowDate();
        String nowDateMin = TimeUtils.getNowDateMin();

        String sql = " SELECT COUNT( DISTINCT college_id ) AS 'collegeCount' " +
                " FROM tb_course_table WHERE id IN ( SELECT course_table_id " +
                " FROM tb_course_table_detail " +
                " WHERE course_date = '" + nowDate + "'" +
                " AND `segment_start_time` <='" + nowDateMin + "' AND `segment_end_time` >='" + nowDateMin + "')";

        Query queryCount = entityManager.createNativeQuery(sql);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        return ((BigInteger) resMap.get("collegeCount")).intValue();
    }

    /**
     * 获取当前开课课程数量
     *
     * @return
     */
    public Integer getAllCourseNum() {
        String nowDate = TimeUtils.getNowDate();
        String nowDateMin = TimeUtils.getNowDateMin();

        String sql = " SELECT COUNT( DISTINCT course_id ) AS 'courseCount' " +
                " FROM tb_course_table WHERE id IN ( SELECT course_table_id " +
                " FROM tb_course_table_detail " +
                " WHERE course_date = '" + nowDate + "'" +
                " AND `segment_start_time` <='" + nowDateMin + "' AND `segment_end_time` >='" + nowDateMin + "')";

        Query queryCount = entityManager.createNativeQuery(sql);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        Map<String, Object> resMap = (Map<String, Object>) queryCount.getResultList().get(0);
        return ((BigInteger) resMap.get("courseCount")).intValue();
    }


    public Integer getCourseResourceNum() {
        return null;
    }

    /**
     * 获取当前正在上课的教室数量
     *
     * @return
     */
    @Transactional
    public Integer getCurrentUsingClassroom() {
        String nowDate = TimeUtils.getNowDate();
        String nowDateMin = TimeUtils.getNowDateMin();

        List<CourseTableIdAndCourseTableDetailId> courseTableIds = courseTableDetailService.getIdByTime(nowDate, nowDateMin);

        if (CollectionUtils.isEmpty(courseTableIds)) {
            return Constant.ZREO;
        }
        List<String> ids = new ArrayList<>();
        courseTableIds.forEach(i -> {
            ids.add(i.getId());
        });
        String sql = this.classRoomSql(ids);
        Query queryData = entityManager.createNativeQuery(sql);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> queryDataMaps = queryData.getResultList();
        Map map = queryDataMaps.get(Constant.ZREO);
        Object o = map.get("total");
        String s = o.toString();
        return Integer.parseInt(s);
    }

    private String classRoomSql(List<String> ids) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(id) AS total FROM tb_course_table_detail_room_user WHERE 1=1");

        //将字符串集合转换为数组
        String[] array = new String[ids.size()];
        String[] strings = ids.toArray(array);
        //将字符串数组转换为在 in（）中使用的参数
        StringBuffer idsStr = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            if (i > 0) {
                idsStr.append(",");
            }
            idsStr.append("'").append(strings[i]).append("'");
        }
        sql.append(" AND  course_table_detail_id IN (" + idsStr + ")");

        sql.append(" AND room_id !=''");
        return sql.toString();
    }
}

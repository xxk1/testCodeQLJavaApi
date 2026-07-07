package com.lztech.site.service.coursemanagementportal;

import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.portal.TeachingCourseResource;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class InTeachingCoursesService {
    @Autowired
    private EntityManager entityManager;

    public List<TeachingCourseResource> getInTeachingCourses(Integer num) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("displayNum", num);
        String sql = createSql();
        Query queryData = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TeachingCourseResource.class));
        List<TeachingCourseResource> inTeachingCourseResources = queryData.getResultList();

        List<TeachingCourseResource> teachingCourseResourceList = new ArrayList<>();
        inTeachingCourseResources.forEach(inTeachingCourse -> {
            TeachingCourseResource teachingCourseResource = new TeachingCourseResource();
            teachingCourseResource.setCourseId(inTeachingCourse.getCourseId());
            teachingCourseResource.setCourseName(inTeachingCourse.getCourseName());
            teachingCourseResource.setTeacherNames(inTeachingCourse.getTeacherNames());
            teachingCourseResource.setCollegeName(inTeachingCourse.getCollegeName());
            teachingCourseResource.setGroupNames(inTeachingCourse.getGroupNames());
            teachingCourseResourceList.add(teachingCourseResource);
        });
        return teachingCourseResourceList;
    }

    private String createSql() {
        Date nowDate = new Date();
        String day = DateUtils.formatDate(DateUtils.DATE, nowDate);
        String time = DateUtils.formatDate(DateUtils.TIME, nowDate);
        String sql = "SELECT c.id AS courseId, " +
                " c.course_name AS courseName, " +
                " ( SELECT GROUP_CONCAT( teacher_name ) FROM tb_course_table_detail_teacher WHERE course_table_detail_id = ctd.id GROUP BY ctd.id )" +
                " AS teacherNames," +
                " ct.college_name AS collegeName," +
                " g.group_name AS groupNames " +
                " FROM tb_course_table_detail ctd " +
                " INNER JOIN tb_course_segment cs ON ctd.id = cs.course_table_detail_id " +
                " INNER JOIN tb_course_table ct ON ctd.course_table_id = ct.id " +
                " INNER JOIN tb_course c ON ct.course_id = c.id " +
                " INNER JOIN tb_course_table_detail_room_user ctdru ON ctd.id = ctdru.course_table_detail_id " +
                " INNER JOIN tb_segment s ON cs.build_id = s.buildid " +
                " AND cs.segment = s.segment " +
                " INNER JOIN tb_group g ON ct.group_id = g.id " +
                " WHERE " +
                " c.use_state = 1 " +
                " AND ctd.course_date = '" + day + "'" +
                " AND s.start_time <= '" + time + "' AND s.end_time >= '" + time + "'" +
                " ORDER BY c.course_code " +
                " LIMIT :displayNum";
        return sql;
    }
}

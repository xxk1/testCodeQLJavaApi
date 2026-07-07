package com.lztech.site.service.coursemanagementportal;

import com.lztech.site.viewmodel.portal.TourCoursesInfoVo;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class TourCoursesService {

    @Autowired
    private EntityManager entityManager;

    public List<TourCoursesInfoVo> getCoursesTeacherAndCollegeNameByDetailId(String ids) {
        List<String> detailIdList = Arrays.asList(ids.split(","));
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("detailIds",detailIdList);
        String sql = "SELECT td.id AS detailId ,tdt.teacher_name AS teacherName,t.college_name AS collegeName " +
                " FROM" +
                " tb_course_table_detail td " +
                " INNER JOIN tb_course_table_detail_teacher tdt ON td.id = tdt.course_table_detail_id " +
                " INNER JOIN tb_course_table t ON td.course_table_id = t.id " +
                " WHERE " +
                " td.id IN :detailIds ";
        Query queryData = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(TourCoursesInfoVo.class));
        List<TourCoursesInfoVo> tourCoursesInfoVos = queryData.getResultList();
        return tourCoursesInfoVos;
    }
}

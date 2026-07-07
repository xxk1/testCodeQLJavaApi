package com.lztech.site.service.preparation;

import com.lztech.site.viewmodel.preparation.PreparationResource;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PreparationService {
    @Autowired
    private EntityManager entityManager;
    @Value("${studentType}")
    private String studentType;

    public ResponseEntity<List<PreparationResource>> getPreparation(String teacherId) {
        List<PreparationResource> preparationResources = createPreparationResource(teacherId);
        return new ResponseEntity<>(preparationResources, HttpStatus.OK);
    }

    public List<PreparationResource> createPreparationResource(String teacherId) {
        String querySql = "SELECT " +
                " md5(uuid()) AS id, " +
                " tc.id AS courseId, " +
                " tc.course_name AS courseName, " +
                " tctdt.teacher_id AS teacherId, " +
                " tctdt.teacher_name AS teacherName " +
                " FROM tb_course tc " +
                " INNER JOIN tb_course_table tct ON tct.course_id = tc.id " +
                " INNER JOIN tb_course_table_detail tctd ON tctd.course_table_id = tct.id " +
                " INNER JOIN tb_course_table_detail_teacher tctdt ON tctdt.course_table_detail_id = tctd.id " +
                " WHERE tc.use_state=1  ";

        Map<String, Object> paramMap = new HashMap<>();


        querySql+=" and teacher_id =:teacherId  GROUP BY tctdt.teacher_id, tc.id ";
        paramMap.put("teacherId", teacherId);

        Query queryData = entityManager.createNativeQuery(querySql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(PreparationResource.class));
        List<PreparationResource> preparationResources = queryData.getResultList();
        return preparationResources;
    }
}

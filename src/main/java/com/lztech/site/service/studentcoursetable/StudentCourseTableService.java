package com.lztech.site.service.studentcoursetable;

import com.lztech.domain.model.term.Term;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseTableProjectResource;
import com.lztech.site.viewmodel.studentcoursetable.StudentCourseTableResource;
import com.lztech.site.viewmodel.studentcoursetable.sql.StudentCourseTableProjectSqlVo;
import com.lztech.site.viewmodel.studentcoursetable.sql.StudentCourseTableSqlVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentCourseTableService {

    @Autowired
    private EntityManager entityManager;
    @Value("${studentType}")
    private String studentType;

    @Autowired
    private TermService termService;

    public List<StudentCourseTableResource> getStudentNowTimeCourseTable(String id) {
        List<StudentCourseTableResource> studentCourseTableResourceList = new ArrayList<>();
        Date now = new Date();
        String date = DateUtils.formatDate(DateUtils.DATE, now);
        String time = DateUtils.formatDate(DateUtils.TIME_SECOND, now);
        Map<String, Object> paramMap = new HashMap<>();
        String sql = getQueryStudentCourseTableSql(date, time, id, paramMap);
        Term nowTerm = termService.getNowTerm();
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(StudentCourseTableSqlVo.class));
        StudentCourseTableSqlVo studentCourseTableSqlVo = (StudentCourseTableSqlVo) query.getResultList().stream().findFirst().orElse(null);
        String queryProjectSql;
        Map<String, Object> projectQueryparamMap = new HashMap<>();
        if (Objects.isNull(studentCourseTableSqlVo)) {
            queryProjectSql = getQueryStudentProjectSql(nowTerm, id, projectQueryparamMap);
        } else {
            queryProjectSql = getQueryStudentProjectSql(studentCourseTableSqlVo.getCourseTableDetailId(), nowTerm, id, projectQueryparamMap);
        }
        Query projectQuery = entityManager.createNativeQuery(queryProjectSql);
        projectQueryparamMap.forEach(projectQuery::setParameter);
        projectQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(StudentCourseTableProjectSqlVo.class));
        List<StudentCourseTableProjectSqlVo> projectSqlVoList = (List<StudentCourseTableProjectSqlVo>) projectQuery.getResultList();
        Map<String, List<StudentCourseTableProjectSqlVo>> studentCourseTableProjectSqlVoMap = projectSqlVoList.stream()
                .collect(Collectors.groupingBy(StudentCourseTableProjectSqlVo::getCourseId));
        studentCourseTableProjectSqlVoMap.forEach((k, v) -> {
            StudentCourseTableResource studentCourseTableResource = new StudentCourseTableResource();
            if (ObjectUtils.isNotEmpty(studentCourseTableSqlVo) && studentCourseTableSqlVo.getCourseId().equals(k)) {
                studentCourseTableResource.setCourseTableDetailId(studentCourseTableSqlVo.getCourseTableDetailId());
                studentCourseTableResource.setSegmentStartTime(studentCourseTableSqlVo.getSegmentStartTime());
                studentCourseTableResource.setSegmentEndTime(studentCourseTableSqlVo.getSegmentEndTime());
            }
            studentCourseTableResource.setCourseId(v.get(0).getCourseId());
            studentCourseTableResource.setCourseName(v.get(0).getCourseName());
            studentCourseTableResource.setProjectList(transformProjectResource(v));
            studentCourseTableResourceList.add(studentCourseTableResource);

        });

        return studentCourseTableResourceList;
    }

    private List<StudentCourseTableProjectResource> transformProjectResource(List<StudentCourseTableProjectSqlVo> projectSqlVoList) {
        List<StudentCourseTableProjectResource> resourceList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(projectSqlVoList)) {
            projectSqlVoList.forEach(vo -> {
                StudentCourseTableProjectResource resource = new StudentCourseTableProjectResource();
                resource.setProjectId(vo.getProjectId());
                resource.setProjectCode(vo.getProjectCode());
                resource.setProjectName(vo.getProjectName());
                resource.setIsInclass(Integer.valueOf(vo.getIsInClass()));
                resourceList.add(resource);
            });
        }
        return resourceList;
    }

    private String getQueryStudentProjectSql(String courseTableDetailId, Term term, String studentId,
                                             Map<String, Object> paramMap) {

        String sql = " select * from ( " +
                " select d.id as courseId,d.course_code as courseCode,d.course_name as courseName, " +
                " project_id projectId, project_code projectCode, project_name projectName,'1' isInClass " +
                " from tb_course_table_detail_project a " +
                " INNER JOIN tb_course_table_detail b on a.course_table_detail_id = b.id " +
                " INNER JOIN tb_course_table c on b.course_table_id = c.id " +
                " INNER JOIN tb_course d on c.course_id = d.id " +
                " where course_table_detail_id =  :courseTableDetailId  " +
                " union  " +
                " select DISTINCT  " +
                " e.data_id as courseId,  " +
                " e.data_code as courseCode,   " +
                " e.data_name as courseName,   " +
                " f.id projectId,  " +
                " f.project_code projectCode,  " +
                " f.project_name projectName,  " +
                " '0' as isInClass  " +
                " from tb_course_table a   " +
                " INNER JOIN tb_group b on a.group_id = b.id  " +
                " INNER JOIN tb_group_member c on c.group_id = b.id  " +
                " INNER JOIN tb_project_attribute e on e.data_id = a.course_id  " +
                " INNER JOIN tb_project f on e.project_id = f.id  " +
                " where a.school_year= :schoolYear and a.term = :term " +
                " and b.group_status = 0 and c.group_member_status = 0  " +
                " and c.student_id = :studentId " +
                " )a group by a.projectId";
        paramMap.put("courseTableDetailId", courseTableDetailId);
        paramMap.put("schoolYear", term.getSchoolYear());
        paramMap.put("term", term.getTerm().getIndex());
        paramMap.put("studentId", studentId);
        return sql;

    }

    private String getQueryStudentProjectSql(Term term, String studentId, Map<String, Object> paramMap) {

        String sql = " select * from (  " +
                " select DISTINCT  " +
                " e.data_id as courseId,  " +
                " e.data_code as courseCode,   " +
                " e.data_name as courseName,   " +
                " f.id projectId,  " +
                " f.project_code projectCode,  " +
                " f.project_name projectName,  " +
                " '0' as isInClass  " +
                " from tb_course_table a   " +
                " INNER JOIN tb_group b on a.group_id = b.id  " +
                " INNER JOIN tb_group_member c on c.group_id = b.id  " +
                " INNER JOIN tb_project_attribute e on e.data_id = a.course_id  " +
                " INNER JOIN tb_project f on e.project_id = f.id  " +
                " where a.school_year= :schoolYear and a.term = :term " +
                " and b.group_status = 0 and c.group_member_status = 0  " +
                " and c.student_id =  :studentId " +
                " )a group by a.projectId";
        paramMap.put("schoolYear", term.getSchoolYear());
        paramMap.put("term", term.getTerm().getIndex());
        paramMap.put("studentId", studentId);
        return sql;
    }

    private String getQueryStudentCourseTableSql(String date, String time, String id, Map<String, Object> paramMap) {

        String sql = " select ct.course_id           courseId,\n" +
                "       ct.course_name         courseName,\n" +
                "       ctd.id                 courseTableDetailId,\n" +
                "       DATE_FORMAT(ctd.segment_start_time,'%H:%i:%s') segmentStartTime,\n" +
                "       DATE_FORMAT(ctd.segment_end_time,'%H:%i:%s')   segmentEndTime\n" +
                " from tb_group g\n" +
                "         inner join tb_group_member gm on g.id = gm.group_id\n" +
                "         inner join tb_course_table ct on g.id = ct.group_id\n" +
                "         inner join tb_course_table_detail ctd on ct.id = ctd.course_table_id\n" +
                " where g.group_status = 0\n" +
                "  and gm.group_member_status = 0\n" +
                "  and gm.student_id = :id " +
                "  and ctd.course_date = :date " +
                "  and ctd.source = 5 " +
                "  and segment_start_time <= :segmentStartTime" +
                "  and segment_end_time >= :segmentEndTime";
        paramMap.put("id", id);
        paramMap.put("date", date);
        paramMap.put("segmentStartTime", time);
        paramMap.put("segmentEndTime", time);
        sql += " group by ctd.id";
        return sql;

    }
}

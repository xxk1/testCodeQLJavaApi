package com.lztech.site.service.coursetablestatistics;

import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetailroomuser.CourseTableDetailRoomUserRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.coursetable.StatisticsResource;
import com.lztech.site.viewmodel.coursetable.StudentTypeCourseTableStatisticsResource;
import com.lztech.site.viewmodel.coursetable.StudentTypeCourseTableStatisticsSqlVo;
import com.lztech.site.viewmodel.coursetablestatistics.CourseTableStatisticsResource;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Service
public class CourseTableStatisticsService {

    @Autowired
    private CourseTableDetailRoomUserRepository courseTableDetailRoomUserRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private EntityManager entityManager;
    @Value("${studentType}")
    private String studentType;

    public CourseTableStatisticsResource getCourseTableStatistics() {
        CourseTableStatisticsResource courseTableStatisticsResource = new CourseTableStatisticsResource();

        Date now = new Date();
        String nowDateTime = DateUtils.formatDate(DateUtils.DATE_TIME, now);
        String nowDate = DateUtils.formatDate(DateUtils.DATE, now);
        int inClassRoomNum =0;
        inClassRoomNum = courseTableDetailRoomUserRepository.getInClassRoomNum(nowDateTime);

        int inClassStudentNum = groupMemberRepository.getInClassStudentNum(nowDateTime);
        int courseInfoCourseNum = courseTableRepository.getCourseInfoCourseNum(nowDate);
        int courseInfoCollegeNum = courseTableRepository.getCourseInfoCollegeNum(nowDate);

        courseTableStatisticsResource.setInClassRoomNum(inClassRoomNum);
        courseTableStatisticsResource.setInClassStudentNum(inClassStudentNum);
        courseTableStatisticsResource.setCourseInfoCourseNum(courseInfoCourseNum);
        courseTableStatisticsResource.setCourseInfoCollegeNum(courseInfoCollegeNum);

        return courseTableStatisticsResource;
    }

    public StudentTypeCourseTableStatisticsResource statisticsCourseTableByStudentType(String schoolYear,
                                                                                       Integer term) {
        String querySql = "select student_type as studentTypeIndex,count(distinct course_id) as courseTableNum\n" +
                " from tb_course_table ct inner join tb_group g " +
                " on ct.group_id = g.id\n" +
                " where g.group_status=0";
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtils.isNotBlank(schoolYear)) {
            querySql += " and ct.school_year= :schoolYear ";
            paramMap.put("schoolYear", schoolYear);
        }
        if (Objects.nonNull(term)) {
            querySql += " and ct.term = :term ";
            paramMap.put("term", term);
        }
        querySql += "group by student_type";
        Query query = entityManager.createNativeQuery(querySql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(StudentTypeCourseTableStatisticsSqlVo.class));
        List<StudentTypeCourseTableStatisticsSqlVo> queryResultList = (List<StudentTypeCourseTableStatisticsSqlVo>) query.getResultList();
        StudentType[] studentTypeArray = StudentType.values();
        Integer totalNum = 0;
        List<StatisticsResource> statisticsResourceList = new ArrayList<>();
        for (StudentType studentType : studentTypeArray) {
            StatisticsResource statisticsResource = new StatisticsResource();
            StudentTypeCourseTableStatisticsSqlVo vo = queryResultList
                    .stream()
                    .filter(v -> v.getStudentTypeIndex().equals(studentType.getIndex()))
                    .findFirst()
                    .orElse(null);
            statisticsResource.setStatisticsName(studentType.getName());
            statisticsResource.setStatisticsNumber(Objects.isNull(vo) ? 0 : vo.getCourseTableNum().intValue());
            statisticsResource.setStatisticsOrder(studentType.getIndex());
            totalNum += statisticsResource.getStatisticsNumber();
            statisticsResourceList.add(statisticsResource);
        }
        StudentTypeCourseTableStatisticsResource studentTypeCourseTableStatisticsResource = new StudentTypeCourseTableStatisticsResource();
        studentTypeCourseTableStatisticsResource.setTotalNum(totalNum);
        studentTypeCourseTableStatisticsResource.setDetailList(statisticsResourceList);
        return studentTypeCourseTableStatisticsResource;
    }
}

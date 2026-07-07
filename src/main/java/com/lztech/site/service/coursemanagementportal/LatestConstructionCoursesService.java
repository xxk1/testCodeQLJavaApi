package com.lztech.site.service.coursemanagementportal;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseVersion;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.site.service.courseconstruction.CourseCompletionService;
import com.lztech.site.viewmodel.coursemanagement.CourseCompletionResource;
import com.lztech.site.viewmodel.portal.NewCourseResource;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LatestConstructionCoursesService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseCompletionService courseCompletionService;
    @Autowired
    private EntityManager entityManager;

    public List<NewCourseResource> getLatestCourses(Integer num) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("displayNum", num);
        String sql = "select distinct c.id as id,c.course_name as courseName,c.college_name as collegeName," +
                " c.course_leader_name as courseLeaderName from tb_course c " +
                " inner join tb_course_version v on c.id = v.course_id " +
                " inner join tb_course_completion cc on cc.course_id = c.id and cc.course_version_id = v.id " +
                " where c.use_state = 1 and v.course_version_status = 1 and cc.is_completed = 1 " +
                " order by v.create_time desc limit :displayNum ";
        Query queryData = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(Course.class));
        List<Course> courses = queryData.getResultList();

        List<String> partsCourseIds = courses.stream().map(Course::getId).collect(Collectors.toList());
        List<CourseVersion> courseVersions = courseVersionRepository.findCourseVersionByCourseIds(partsCourseIds);
        List<String> courseVersionIds = courseVersions.stream().map(CourseVersion::getId).collect(Collectors.toList());
        List<NewCourseResource> newCourseResourceList = new ArrayList<>();
        List<CourseCompletionResource> courseCompletion = courseCompletionService.getLatestCourseCompletion(partsCourseIds, courseVersionIds);
        courseCompletion.forEach(p -> {
            Optional<Course> firstCourse = courses.stream().filter(c -> c.getId().equals(p.getCourseId())).findFirst();
            if (firstCourse.isPresent()) {
                Course course = firstCourse.get();
                NewCourseResource resource = new NewCourseResource();
                resource.setCourseId(course.getId());
                resource.setCourseName(course.getCourseName());
                resource.setCollegeName(course.getCollegeName());
                resource.setCourseLeaderName(course.getCourseLeaderName());
                resource.setCourseCompletion(p.getCompletion());
                newCourseResourceList.add(resource);
            }
        });
        return newCourseResourceList;
    }
}

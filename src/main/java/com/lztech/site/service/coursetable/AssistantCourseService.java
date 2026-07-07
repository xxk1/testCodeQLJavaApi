package com.lztech.site.service.coursetable;

import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.site.viewmodel.CourseResource;
import com.lztech.site.viewmodel.CourseResourceTeachingClassName;
import com.lztech.site.viewmodel.assistantcourse.AssistantCourseParam;
import com.lztech.site.viewmodel.assistantcourse.AssistantCourseVo;
import com.lztech.site.viewmodel.assistantcourse.TeacherAndCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.coursetable.specification.CourseTableSpecification.getAssistantTeacherCourseList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


@Service
public class AssistantCourseService {

    @Autowired
    private CourseTableRepository courseTableRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    public List<AssistantCourseVo> getAssistantTeacherCourseTableList(AssistantCourseParam assistantCourseParam) {
        List<AssistantCourseVo> assistantCourseVoList = new ArrayList<>();
        List<String> courseIdList = assistantCourseParam.getTeacherAndCourseList().stream().map(TeacherAndCourse::getCourseId)
                .collect(Collectors.toList());
        List<String> teacherIdList = assistantCourseParam.getTeacherAndCourseList().stream().map(TeacherAndCourse::getTeacherId)
                .collect(Collectors.toList());
        List<CourseTable> courseTableList = courseTableRepository.findAll(
                getAssistantTeacherCourseList(assistantCourseParam, courseIdList, teacherIdList));
        List<CourseResource> courseResourceList = getCourseResources(courseTableList);
        List<CourseResource> matchCourseResourceList = new ArrayList<>();
        assistantCourseParam.getTeacherAndCourseList().forEach(t -> {
            courseResourceList.forEach(c -> {
                if (t.getCourseId().equals(c.getCourseId()) && c.getTeacherId().contains(t.getTeacherId())) {
                    CourseResource courseResource = new CourseResource();
                    courseResource.setCourseId(c.getCourseId());
                    courseResource.setCourseName(c.getCourseName());
                    courseResource.setCourseCode(c.getCourseCode());
                    courseResource.setTeachingClassName(c.getTeachingClassName());
                    courseResource.setCourseCollegeId(c.getCourseCollegeId());
                    courseResource.setCourseCollege(c.getCourseCollege());
                    courseResource.setTeachingClassName(c.getTeachingClassName());
                    courseResource.setCourseCategory(c.getCourseCategory());
                    courseResource.setCourseCategoryId(c.getCourseCategoryId());
                    courseResource.setCourseSource(c.getCourseSource());
                    courseResource.setSchoolYear(c.getSchoolYear());
                    courseResource.setTerm(c.getTerm());
                    courseResource.setTeacherId(t.getTeacherId());
                    courseResource.setTeacherName(t.getTeacherName());
                    courseResource.setTeacherNo(t.getTeacherNo());
                    courseResource.setRoleId(t.getRoleId());
                    matchCourseResourceList.add(courseResource);
                }
            });
        });
        Map<String, List<CourseResource>> courseResourceTeacherMap = matchCourseResourceList.stream().distinct()
                .collect(Collectors.groupingBy(CourseResource::getTeacherId));
        courseResourceTeacherMap.forEach((teacherId, courseResources) -> {
            AssistantCourseVo assistantCourseVo = new AssistantCourseVo();
            assistantCourseVo.setTeacherId(courseResources.get(0).getTeacherId());
            assistantCourseVo.setTeacherName(courseResources.get(0).getTeacherName());
            List<CourseResource> courseResource = new ArrayList<>();
            Map<String, List<CourseResource>> courseResourceCourseMap = courseResources.stream()
                    .collect(Collectors.groupingBy(CourseResource::getCourseId));
            courseResourceCourseMap.forEach((courseId, resources) -> {
                CourseResource resource = resources.get(0);
                List<CourseResourceTeachingClassName> teachingClassNameList = resources
                        .stream()
                        .flatMap(s -> s.getTeachingClassName().stream()).distinct()
                        .collect(toList());
                resource.setTeachingClassName(teachingClassNameList);
                courseResource.add(resource);
            });
            assistantCourseVo.setCourseResourceList(courseResource.stream()
                    .sorted(Comparator.comparing(CourseResource::getCourseCode)).collect(toList()));
            assistantCourseVoList.add(assistantCourseVo);
        });
        return assistantCourseVoList;
    }

    public List<CourseResource> getCourseResources(List<CourseTable> courseTableList) {
        List<CourseResource> courseResourceList = new ArrayList<>();
        courseTableList.forEach(courseTable -> {
            CourseResource courseResource = new CourseResource();
            Course course = courseTable.getCourse();
            courseResource.setCourseId(course.getId());
            courseResource.setCourseSource(Objects.isNull(course.getCourseSource()) ? 0 : course.getCourseSource().getIndex());
            courseResource.setCourseCode(course.getCourseCode());
            courseResource.setCourseName(course.getCourseName());
            courseResource.setCourseCollege(course.getCollege().getCollegeName());
            courseResource.setCourseCollegeCode(course.getCollege().getCollegeCode());
            courseResource.setCourseCollegeId(course.getCollege().getId());
            courseResource.setCourseCategoryId(courseTable.getCourseCategory() == null ? null
                    : String.valueOf(courseTable.getCourseCategory().getId()));
            courseResource.setCourseCategory(courseTable.getCourseCategory() == null ? null
                    : courseTable.getCourseCategory().getCourseCategoryName());
            List<CourseTableDetail> courseTableDetailList = courseTable.getCourseTableDetailList();
            List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetailList
                    .stream()
                    .flatMap(courseTableDetail -> courseTableDetail.getCourseTableDetailTeacherList().stream())
                    .collect(toList());
            courseResource.setTeacherName(courseTableDetailTeacherList
                    .stream()
                    .map(CourseTableDetailTeacher::getTeacherName).distinct().collect(Collectors.joining(",")));
            courseResource.setTeacherId(courseTableDetailTeacherList
                    .stream()
                    .map(CourseTableDetailTeacher::getTeacherId).distinct().collect(Collectors.joining(",")));
            courseResource.setSchoolYear(courseTable.getSchoolYear());
            courseResource.setTerm(courseTable.getTerm());
            List<CourseResourceTeachingClassName> courseResourceTeachingClassNameList = new ArrayList<>();
            CourseResourceTeachingClassName courseResourceTeachingClassName = new CourseResourceTeachingClassName();
            Group group = courseTable.getGroup();
            courseResourceTeachingClassName.setTeachingClassId(group.getId());
            courseResourceTeachingClassName.setTeachingClassName(group.getGroupName());
            courseResourceTeachingClassName.setTeachingClassNo(group.getGroupNo());
            Integer sort = ObjectUtils.isEmpty(group.getSort())?0:group.getSort();
            courseResourceTeachingClassName.setTeachingClassSort(sort);
            courseResourceTeachingClassName.setClassNickName(group.getClassNickName());
            List<CourseTable> courseTableFilterList = group.getCourseTableList().stream().filter(c
                            -> c.getCourse().getId().equals(course.getId())
                            && c.getGroup().getId().equals(group.getId()))
                    .distinct().collect(toList());
            courseResourceTeachingClassName.setCourseTableId(courseTableFilterList.get(0).getId());
            String teacherName = courseTableFilterList.get(0).getTeacherName();
            if (teacherName.contains(",")) {
                teacherName = Arrays.stream(teacherName.split(","))
                        .distinct()
                        .collect(joining(","));
            }
            courseResourceTeachingClassName.setTeachingClassTeacherName(teacherName);
            courseResourceTeachingClassName.setTeachingClassMembers(group.getGroupMemberList() == null ? 0 :
                    (int) group.getGroupMemberList()
                            .stream()
                            .filter(g -> GroupMemberStatus.NORMAL.equals(g.getGroupMemberStatus())).count());
            courseResourceTeachingClassName.setSource(group.getSource().getIndex() + "");
            courseResourceTeachingClassName.setClassNickName(group.getClassNickName());
            courseResourceTeachingClassName.setClassCompositionName(group.getClassCompositionName());
            courseResourceTeachingClassName.setCourseTableCollegeId(courseTableFilterList.get(0).getCollegeId());
            courseResourceTeachingClassName.setCourseTableCollegeName(courseTableFilterList.get(0).getCollegeName());
            College college = collegeRepository.findById(courseTableFilterList.get(0).getCollegeId()).orElse(null);
            if (!ObjectUtils.isEmpty(college)){
                courseResourceTeachingClassName.setCourseTableCollegeCode(college.getCollegeCode());
            }
            courseResourceTeachingClassNameList.add(courseResourceTeachingClassName);
            courseResource.setTeachingClassName(courseResourceTeachingClassNameList);
            courseResourceList.add(courseResource);
        });
        return courseResourceList;
    }

}

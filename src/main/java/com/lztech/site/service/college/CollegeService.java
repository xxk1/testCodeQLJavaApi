package com.lztech.site.service.college;

import com.lztech.domain.model.college.College;
import com.lztech.domain.model.college.enums.CourseInfoCollege;
import com.lztech.domain.model.college.enums.CourseTableCollege;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseTeachingTeam;
import com.lztech.domain.model.course.enums.TeacherType;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.site.resource.college.CollegeCourseResource;
import com.lztech.site.resource.college.CollegeResource;
import com.lztech.site.resource.college.CollegeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;

    public College getCollege(String collegeName) {
        College college = collegeRepository.findByCollegeName(collegeName);
        if (college == null) {
            college = new College();
            String uuid = UUID.randomUUID().toString();
            college.setId(uuid);
            college.setCollegeCode(uuid);
            college.setCollegeName(collegeName);
            college.setCourseInfoCollege(CourseInfoCollege.TRUE);
            college.setCourseTableCollege(CourseTableCollege.TRUE);
            collegeRepository.save(college);
        }
        return college;
    }

    public List<College> saveColleges(List<CollegeResource> collegeResourceList) {
        List<College> colleges = collegeRepository.findAll();
        List<College> collegeList = new ArrayList<>();
        collegeResourceList.forEach(collegeResource -> {
            boolean anyMatch = colleges
                    .stream().anyMatch(college -> college.getCollegeName().equals(collegeResource.getCollegeName()));
            if (!anyMatch) {
                College college = new College();
                college.setId(collegeResource.getId());
                college.setCollegeName(collegeResource.getCollegeName());
                college.setCollegeCode(collegeResource.getCollegeCode());
                college.setCourseInfoCollege(CourseInfoCollege.TRUE);
                college.setCourseTableCollege(CourseTableCollege.TRUE);
                collegeList.add(college);
            }
        });
        return collegeRepository.saveAll(collegeList);
    }

    public ResponseEntity<List<CollegeVo>> getAllColleges() {
        List<College> collegeList = collegeRepository.findByCourseInfoCollegeAndCourseTableCollege(CourseInfoCollege.TRUE, CourseTableCollege.TRUE);
        List<CollegeVo> collegeVos = new ArrayList<>();
        if (collegeList == null || collegeList.size() == 0) {
            return new ResponseEntity(collegeVos, HttpStatus.OK);
        }
        collegeList = collegeList.stream().sorted(Comparator.comparing(College::getId)).collect(Collectors.toList());
        CollegeVo collegeVo = new CollegeVo();
        collegeVo.setId("");
        collegeVo.setName("全部");
        collegeVo.setChecked(true);
        collegeVos.add(collegeVo);
        for (College college : collegeList) {
            CollegeVo collegeVs = new CollegeVo();
            collegeVs.setId(college.getId());
            collegeVs.setName(college.getCollegeName());
            collegeVs.setChecked(false);
            collegeVos.add(collegeVs);
        }
        return new ResponseEntity(collegeVos, HttpStatus.OK);
    }

    public List<CollegeResource> getColleges() {
        List<College> collegeList = collegeRepository.findAll();
        List<CollegeResource> collegeResourceList = collegeList.stream().map(college -> {
            CollegeResource collegeResource = new CollegeResource();
            collegeResource.setId(college.getId());
            collegeResource.setCollegeCode(college.getCollegeCode());
            collegeResource.setCollegeName(college.getCollegeName());
            return collegeResource;
        }).collect(Collectors.toList());
        return collegeResourceList;
    }

    public College saveCollege(CollegeResource collegeResource) {
        College college = new College();
        String uuid = UUID.randomUUID().toString();
        college.setId(uuid);
        college.setCollegeCode(uuid);
        college.setCollegeName(collegeResource.getCollegeName());
        college.setCourseInfoCollege(CourseInfoCollege.TRUE);
        college.setCourseTableCollege(CourseTableCollege.TRUE);
        return collegeRepository.save(college);
    }

    public College findByName(String collegeName) {
        return collegeRepository.findByCollegeName(collegeName);
    }


    public ResponseEntity getCollegesByIds(String collegeIds) {
        List<CollegeResource> collegeResourceList = new ArrayList<>();

        List<String> ids = Arrays.asList(collegeIds.split(","));

        List<College> colleges = collegeRepository.findAllByIdIn(ids);
        if (CollectionUtils.isEmpty(colleges)) {
            return new ResponseEntity(collegeResourceList, HttpStatus.OK);
        }
        Collator collator = Collator.getInstance(Locale.CHINA);

        colleges = colleges
                .stream()
                .sorted(Comparator.comparing(College::getShowOrder, Comparator.naturalOrder())
                        .thenComparing(College::getCollegeName, collator))
                .collect(Collectors.toList());
        colleges.forEach(college -> {
            CollegeResource collegeResource = new CollegeResource();
            collegeResource.setId(college.getId());
            collegeResource.setCollegeCode(college.getCollegeCode() == null ? "" : college.getCollegeCode());
            collegeResource.setCollegeName(college.getCollegeName());
            collegeResourceList.add(collegeResource);
        });

        return new ResponseEntity(collegeResourceList, HttpStatus.OK);
    }

    public List<CollegeCourseResource> getCollegeInfoByCourseId(List<String> courseIds) {
        Set<CollegeCourseResource> collegeCourseResources = new HashSet<>();
        List<Course> courseList = courseRepository.findByIdIn(courseIds);
        if (!CollectionUtils.isEmpty(courseList)) {
            for (Course course : courseList) {
                CollegeCourseResource collegeCourseResource = new CollegeCourseResource();
                collegeCourseResource.setId(course.getCollege().getId());
                collegeCourseResource.setCollegeName(course.getCollegeName());
                collegeCourseResource.setCourseId(course.getId());
                collegeCourseResource.setCourseName(course.getCourseName());
                collegeCourseResource.setCourseCode(course.getCourseCode());
                List<CourseTeachingTeam> teachingTeams = course
                        .getCourseTeachingTeams()
                        .stream()
                        .filter(f -> f.getTeacherType().equals(TeacherType.COURSE_LEADER))
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(teachingTeams)) {
                    collegeCourseResource.setTeacherId(teachingTeams.get(0).getTeacherId());
                    collegeCourseResource.setTeacherName(teachingTeams.get(0).getTeacherName());
                }
                collegeCourseResources.add(collegeCourseResource);
            }
        }
        return new ArrayList<>(collegeCourseResources);
    }

    public College getCollegeById(String id) {
        Optional<College> optional = collegeRepository.findById(id);
        return optional.orElse(null);
    }

    public void updateCollegeCourseInfoCollege(College college, Boolean courseInfoCollegeStatus) {
        college.setCourseInfoCollege(courseInfoCollegeStatus ? CourseInfoCollege.TRUE : CourseInfoCollege.FALSE);
        collegeRepository.save(college);
    }

    public ResponseEntity getCourseInfoOrAllCollegeByIds(String collegeIds, Boolean courseInfoCollegeStatus) {
        List<CollegeResource> collegeResourceList = new ArrayList<>();

        List<String> ids = Arrays.asList(collegeIds.split(","));

        List<College> colleges = new ArrayList<>();

        if (courseInfoCollegeStatus) {
            colleges = collegeRepository.findAllByIdInAndCourseInfoCollege(ids, CourseInfoCollege.TRUE);
        } else {
            colleges = collegeRepository.findAllByIdIn(ids);
        }
        if (CollectionUtils.isEmpty(colleges)) {
            return new ResponseEntity(collegeResourceList, HttpStatus.OK);
        }
        Collator collator = Collator.getInstance(Locale.CHINA);

        colleges = colleges
                .stream()
                .sorted(Comparator.comparing(College::getShowOrder, Comparator.naturalOrder())
                        .thenComparing(College::getCollegeName, collator))
                .collect(Collectors.toList());
        colleges.forEach(college -> {
            CollegeResource collegeResource = new CollegeResource();
            collegeResource.setId(college.getId());
            collegeResource.setCollegeCode(college.getCollegeCode() == null ? "" : college.getCollegeCode());
            collegeResource.setCollegeName(college.getCollegeName());
            collegeResource.setShowOrder(college.getShowOrder());
            collegeResourceList.add(collegeResource);
        });
        return new ResponseEntity(collegeResourceList, HttpStatus.OK);
    }

    public List<CollegeCourseResource> getCollegeInfoByCourseCode(List<String> courseCodes) {
        Set<CollegeCourseResource> collegeCourseResources = new HashSet<>();
        List<Course> courses = courseRepository.findByCourseCodeIn(courseCodes);
        if (!CollectionUtils.isEmpty(courses)) {
            for (Course course : courses) {
                CollegeCourseResource collegeCourseResource = new CollegeCourseResource();
                collegeCourseResource.setId(course.getCollege().getId());
                collegeCourseResource.setCollegeName(course.getCollegeName());
                collegeCourseResource.setCourseId(course.getId());
                collegeCourseResource.setCourseName(course.getCourseName());
                collegeCourseResource.setCourseCode(course.getCourseCode());
                List<CourseTeachingTeam> teachingTeams = course
                        .getCourseTeachingTeams()
                        .stream()
                        .filter(f -> f.getTeacherType().equals(TeacherType.COURSE_LEADER))
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(teachingTeams)) {
                    collegeCourseResource.setTeacherId(teachingTeams.get(0).getTeacherId());
                    collegeCourseResource.setTeacherName(teachingTeams.get(0).getTeacherName());
                }
                collegeCourseResources.add(collegeCourseResource);
            }
        }
        return new ArrayList<>(collegeCourseResources);
    }
}

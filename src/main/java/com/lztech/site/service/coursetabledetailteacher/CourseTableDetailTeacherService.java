package com.lztech.site.service.coursetabledetailteacher;

import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.site.viewmodel.coursetabledetailteacher.AutonomousClassTeacher;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseTableDetailTeacherService {

    @Autowired
    private CourseTableRepository courseTableRepository;


    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;

    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Value("${studentType}")
    private String studentType;


    public ResponseEntity<Void> autonomousClassTeacherAddTeacherPost
            (List<AutonomousClassTeacher> addTeacherList, String courseTableId, String operatorId, String operatorName) {

        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository.findByCourseTableId(courseTableId);
        boolean whetherOpenClasses = courseTableDetailList.stream().anyMatch(courseTableDetail
                -> courseTableDetail.getCourseTable().getSource() == Source.AUTONOMOUS_CLASS);
        if (courseTableDetailList.size() > 0 && whetherOpenClasses) {
            List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetailList.get(0).getCourseTableDetailTeacherList();
            courseTableDetailList.forEach(courseTableDetail -> addTeacherList.forEach(addTeacher -> {
                boolean flag = courseTableDetail.getCourseTableDetailTeacherList()
                        .stream()
                        .noneMatch(courseTableDetailTeacher
                                -> courseTableDetailTeacher.getTeacherId()
                                .equals(addTeacher.getTeacherId()));
                if (flag) {
                    CourseTableDetailTeacher courseTableDetailTeacher = new CourseTableDetailTeacher();
                    courseTableDetailTeacher.setCourseTableDetail(courseTableDetail);
                    courseTableDetailTeacher.setTeacherId(addTeacher.getTeacherId());
                    courseTableDetailTeacher.setTeacherNo(addTeacher.getTeacherNo());
                    courseTableDetailTeacher.setTeacherName(addTeacher.getTeacherName());
                    courseTableDetailTeacher.setCreatorId(operatorId);
                    courseTableDetailTeacher.setCreatorName(operatorName);
                    courseTableDetailTeacher.setTeacherCollegeId(addTeacher.getTeacherCollegeId());
                    courseTableDetailTeacher.setTeacherCollegeName(addTeacher.getTeacherCollegeName());
                    courseTableDetailTeacher.setCreateTime(new Date());
                    courseTableDetailTeacherRepository.save(courseTableDetailTeacher);
                    courseTableDetailTeacherList.add(courseTableDetailTeacher);
                }
            }));
            if (CollectionUtils.isNotEmpty(courseTableDetailTeacherList)) {
                CourseTable courseTable = courseTableDetailList.get(0).getCourseTable();
                courseTable.setTeacherName(courseTableDetailTeacherList.stream()
                        .filter(c -> StringUtils.isNotBlank(c.getTeacherId()))
                        .sorted(Comparator.comparing(CourseTableDetailTeacher::getTeacherId))
                        .map(CourseTableDetailTeacher::getTeacherName)
                        .collect(Collectors.joining(",")));
                courseTable.setTeacherNo(courseTableDetailTeacherList.stream()
                        .filter(c -> StringUtils.isNotBlank(c.getTeacherId()))
                        .sorted(Comparator.comparing(CourseTableDetailTeacher::getTeacherId))
                        .map(CourseTableDetailTeacher::getTeacherNo)
                        .collect(Collectors.joining(",")));
                courseTable.setCourseTableTeacherId(courseTableDetailTeacherList.stream()
                        .filter(c -> StringUtils.isNotBlank(c.getTeacherId()))
                        .sorted(Comparator.comparing(CourseTableDetailTeacher::getTeacherId))
                        .map(CourseTableDetailTeacher::getTeacherId)
                        .collect(Collectors.joining(",")));
                courseTableRepository.save(courseTable);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(ErrorResult.customError("自由班级信息不存在/添加均老师已存在"), HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<Void> autonomousClassTeacherDeleteTeacherDelete(List<AutonomousClassTeacher> deleteTeacherList, String courseTableId) {
        List<String> deleteTeacherIdList = deleteTeacherList.stream().map(AutonomousClassTeacher::getTeacherId).collect(Collectors.toList());
        List<CourseTableDetailTeacher> courseTableDetailTeacherList = courseTableDetailTeacherRepository
                .getCourseTableDetailTeacher(deleteTeacherIdList, courseTableId);
        if (courseTableDetailTeacherList.size() > 0) {
            CourseTable courseTable = courseTableDetailTeacherList.get(0).getCourseTableDetail().getCourseTable();
            courseTableDetailTeacherRepository.deleteCourseTableDetailTeacher(deleteTeacherIdList, courseTableId);
            List<CourseTableDetailTeacher> courseTableDetailTeachers = new ArrayList<>();
            courseTable.getCourseTableDetailList()
                    .forEach(courseTableDetail -> courseTableDetailTeachers
                            .addAll(courseTableDetail.getCourseTableDetailTeacherList()));
            courseTable.setTeacherName(courseTableDetailTeachers.stream()
                    .filter(courseTableDetailTeacher -> deleteTeacherIdList.stream()
                            .noneMatch(teacherId -> teacherId.equals(courseTableDetailTeacher.getTeacherId())))
                    .map(CourseTableDetailTeacher::getTeacherName)
                    .collect(Collectors.joining(",")));
            courseTableRepository.save(courseTable);
            return new ResponseEntity(HttpStatus.OK);
        }


        return new ResponseEntity(ErrorResult.customError("自由班级信息不存在/删除老师不存在"), HttpStatus.NOT_FOUND);
    }


}

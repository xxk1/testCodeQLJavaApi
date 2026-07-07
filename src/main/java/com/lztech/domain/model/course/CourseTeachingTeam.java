package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.TeacherDataSource;
import com.lztech.domain.model.course.enums.TeacherType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * 课程教学团队
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_teaching_team")
public class CourseTeachingTeam extends BaseUpdateModel {
    /**
     * 老师id
     */
    private String teacherId;

    /**
     * 老师姓名
     */
    private String teacherName;

    /**
     * 老师工号
     */
    private String teacherNo;

    /**
     * 老师类型
     */
    @Enumerated(value = EnumType.ORDINAL)
    private TeacherType teacherType;

    /**
     * 教师来源
     */
    private TeacherDataSource teacherDataSource;

    /**
     * 教师简介
     */
    private String teacherProfile;

    /**
     *
     */
    private String jobTitle;

    /**
     *
     */
    private String schoolName;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_version_id")
    private CourseVersion courseVersion;
}

package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课程隐藏记录表
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_hidden_Record")
public class CourseHiddenRecord extends BaseModel {
    /**
     * 学年
     */
    private String schoolYear;

    /**
     * 学期
     */
    private Integer term;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 老师id
     */
    private String teacherId;

    /**
     * 老师名称
     */
    private String teacherName;
}

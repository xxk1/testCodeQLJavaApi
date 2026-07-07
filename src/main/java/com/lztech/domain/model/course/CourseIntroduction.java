package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 课程介绍表
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_introduction")
public class CourseIntroduction extends BaseUpdateModel {

    /**
     * 课程介绍分类名称
     */
    private String classificationName;


    /**
     * 课程介绍分类内容
     */
    private String classificationContent;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_version_id")
    private CourseVersion courseVersion;
}

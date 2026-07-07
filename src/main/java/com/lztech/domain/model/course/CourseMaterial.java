package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.coursecategory.CourseCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程资料表
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_material")
public class CourseMaterial extends BaseUpdateModel {
    /**
     * 资料完成度 ###2022-sprint1改版之前使用
     */
    private Double completeRate;

    /**
     * 课程介绍 ###2022-sprint1改版之前使用
     */
    private String coursePresentation;

    /**
     * 课程大纲 ###2022-sprint1改版之前使用
     */
    private String courseOutline;

    /**
     * 考核方案
     */
    private String assessmentMethod;

    /**
     * 课程类别
     */
    @ManyToOne
    @JoinColumn(name = "course_category_id")
    private CourseCategory courseCategory;

    /**
     * 授课对象
     */
    private String teachingUserType;

    /**
     * 学时
     */
    private Double classHours;

    /**
     * 课程内容
     */
    private String courseContent;

    /**
     * 教材
     */
    private String teachingMaterial;

    /**
     * 教材附件，支持格式：doc、docx、ppt、pptx、pdf
     */
    @OneToMany(mappedBy = "courseMaterial", cascade = CascadeType.ALL)
    private List<TeachingMaterialFile> teachingMaterialFileList = new ArrayList<>();

    /**
     * 参考教材
     */
    private String referenceMaterials;

    @ManyToOne
    @JoinColumn(name = "course_version_id")
    private CourseVersion courseVersion;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}

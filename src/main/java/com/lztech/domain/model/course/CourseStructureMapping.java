package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "tb_course_structure_mapping")
public class CourseStructureMapping extends BaseUpdateModel {

    /**
     * ocr识别结构id
     */
    private String ocrStructureId;

    /**
     * 自定义结构id
     */
    private String courseCustomStructureId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程版本id
     */
    private String courseVersionId;

}

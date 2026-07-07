package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课程目标
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_objective")
public class CourseObjective extends BaseUpdateModel {

    private String name;

    private String content;

    private Integer showOrder = 0;

    private String courseId;

    private Boolean useState = true;

    private String modifierNo;

    private String creatorNo;

    private Integer relatedKnowledgePointNum = 0;
}

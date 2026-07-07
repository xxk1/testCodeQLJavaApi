package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课程目标关联知识点
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_objective_kg_node")
public class CourseObjectiveRelatedKgNode extends BaseUpdateModel {

    /**
     * 知识图谱版本Id
     */
    private String courseKnowledgeGraphId;

    /**
     * 课程目标Id
     */
    private String courseObjectiveId;

    /**
     * 关联知识点Id
     */
    private String relatedKnowledgePointId;

    /**
     * 创建人编号
     */
    private String creatorNo;

    /**
     * 修改人编号
     */
    private String modifierNo;


}

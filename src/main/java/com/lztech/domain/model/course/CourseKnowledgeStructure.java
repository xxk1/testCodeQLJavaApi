package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.KnowledgeStructureType;
import com.lztech.domain.model.course.enums.StructureStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 课程z知识结构
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_knowledge_structure")
public class CourseKnowledgeStructure extends BaseUpdateModel {

    /**
     * 知识结构内容
     */
    private String content;

    /**
     * 知识结构类型
     */
    private KnowledgeStructureType knowledgeStructureType;

    /**
     * 课程结构状态
     */
    private StructureStatus structureStatus;

    /**
     * 父id
     */
    private String parentId;


    /**
     * 排序序号
     */
    private int showOrder;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_version_id")
    private CourseVersion courseVersion;

}

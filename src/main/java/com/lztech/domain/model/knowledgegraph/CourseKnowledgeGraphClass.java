package com.lztech.domain.model.knowledgegraph;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_course_knowledge_graph_class")
public class CourseKnowledgeGraphClass extends BaseModel {
    /**
     * 班级id
     */
    private String groupId;

    /**
     * 班级名称
     */
    private String groupName;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 开课学年
     */
    private String schoolYear;

    /**
     * 开课学期
     */
    private Integer term;

}

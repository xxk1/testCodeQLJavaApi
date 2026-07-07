package com.lztech.domain.model.knowledgegraph;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_course_knowledge_graph_node_question")
public class CourseKnowledgeGraphNodeQuestion extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "course_knowledge_graph_domain_id")
    private CourseKnowledgeGraphDomain courseKnowledgeGraphDomain;
    /**
     * 知识图谱id
     */
    private String knowledgeNodeId;

    /**
     * 题目id
     */
    private String questionId;

    /**
     * 排序
     */
    private Integer sort;


}

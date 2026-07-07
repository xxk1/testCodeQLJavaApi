package com.lztech.domain.model.knowledgegraph;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.knowledgegraph.enums.GraphNodeResourceStatus;
import com.lztech.domain.model.knowledgegraph.enums.GraphNodeResourceType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_course_knowledge_graph_node_resource")
public class CourseKnowledgeGraphNodeResource extends BaseUpdateModel {
    /**
     * 图谱节点资源状态
     */
    private GraphNodeResourceStatus graphNodeResourceStatus;

    /**
     * 图谱节点资源类型
     */
    @Enumerated(value = EnumType.ORDINAL)
    private GraphNodeResourceType resourceType;

    /**
     * 图谱节点资源详情id
     */
    private String resourceDetailId;

    /**
     * 图谱节点资源名称
     */
    private String resourceName;

    /**
     * 资源大小
     */
    private Long resourceSize;

    /**
     * 资源内容数量
     */
    private Integer resourceContentNum;

    @ManyToOne
    @JoinColumn(name = "course_knowledge_graph_domain_id")
    private CourseKnowledgeGraphDomain courseKnowledgeGraphDomain;
    /**
     * 知识图谱名称
     */
    private String knowledgeNodeId;

    /**
     * 排序
     */
    private Integer sort;



}

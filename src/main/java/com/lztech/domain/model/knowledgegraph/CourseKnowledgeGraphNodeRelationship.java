package com.lztech.domain.model.knowledgegraph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * neo4j关系模型
 */
public class CourseKnowledgeGraphNodeRelationship {
    /**
     * 开始节点id
     */
    private String startNodeId;
    /**
     * 开始节点名称
     */
    private String startNodeName;

    /**
     * 知识节点级别
     */
    private Integer startKnowledgeNodeLevel;

    /**
     * 结束节点id
     */
    private String endNodeId;
    /**
     * 结束节点名称
     */
    private String endNodeName;

    /**
     * 知识节点级别
     */
    private Integer endKnowledgeNodeLevel;
    /**
     * 关系id
     */
    private Integer relationshipId;
    /**
     * 关系类型
     */
    private String relationshipType;
    /**
     * 关系名称
     */
    private String relationshipName;


    private Long relationshipCreateTimestamp;

    @Override
    public String toString() {
        return "CourseKnowledgeGraphNodeRelationship{" +
                "startNodeId='" + startNodeId + '\'' +
                ", startNodeName='" + startNodeName + '\'' +
                ", startKnowledgeNodeLevel=" + startKnowledgeNodeLevel +
                ", endNodeId='" + endNodeId + '\'' +
                ", endNodeName='" + endNodeName + '\'' +
                ", endKnowledgeNodeLevel=" + endKnowledgeNodeLevel +
                ", relationshipId=" + relationshipId +
                ", relationshipType='" + relationshipType + '\'' +
                ", relationshipName='" + relationshipName + '\'' +
                ", relationshipCreateTimestamp=" + relationshipCreateTimestamp +
                '}';
    }
}

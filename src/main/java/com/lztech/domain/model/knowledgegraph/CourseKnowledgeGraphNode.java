package com.lztech.domain.model.knowledgegraph;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
/**
 * neo4j数据模型
 */
public class CourseKnowledgeGraphNode {

    private String id;

    /**
     * 知识节点名称
     */
    private String knowledgeNodeName;
    /**
     * 层级类型
     */
    private Integer levelType;
    /**
     * 内容详情
     */
    private String contentDetail;
    /**
     * 知识节点级别
     */
    private Integer knowledgeNodeLevel;
    /**
     * 顺序
     */
    private Integer sort;
    /**
     * 创建时间  (yyyy-MM-dd HH:mm:ss)
     */
    private String createTime;
    /**
     * 创建人id
     */
    private String creatorId;
    /**
     * 创建人名称
     */
    private String creatorName;
    /**
     * 更新时间  (yyyy-MM-dd HH:mm:ss)
     */
    private String modifyTime;
    /**
     * 更新人id
     */
    private String modifierId;
    /**
     * 修改人姓名
     */
    private String modifierName;

    /**
     * neo4j 显示名称（节点名称）
     */
    private String name;


    /**
     * 内容生成方式
     */
    private Integer contentGenerationModeIndex;

    /**
     * 节点生成方式
     */
    private Integer nodeGenerationModeIndex;


    @Override
    public String toString() {
        return "CourseKnowledgeGraphNode{" +
                "id='" + id + '\'' +
                ", knowledgeNodeName='" + knowledgeNodeName + '\'' +
                ", levelType=" + levelType +
                ", contentDetail='" + contentDetail + '\'' +
                ", knowledgeNodeLevel=" + knowledgeNodeLevel +
                ", sort=" + sort +
                ", createTime='" + createTime + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", modifierId='" + modifierId + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", contentGenerationModeIndex='" + contentGenerationModeIndex + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.lztech.site.viewmodel.courseknowledgegraph;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImportCourseKnowledgeGraphNode {
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
     * 父节点
     */
    private String parentCourseKnowledgeGraphNode;
    /**
     * 前置课程知识图谱节点id
     */
    private List<String> preKnowledgeGraphNodeIdList = new ArrayList<>();
    /**
     * 后置课程知识图谱节点id
     */
    private List<String> postKnowledgeGraphNodeIdList = new ArrayList<>();
    /**
     * 关联课程知识图谱节点id
     */
    private List<String> relevanceKnowledgeGraphNodeIdList = new ArrayList<>();
    /**
     * 是否新增
     */
    private Boolean isSave = false;
    /**
     * 是否更新
     */
    private Boolean isUpdate = false;

    /**
     * 第几行
     */
    private Integer row;

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
                ", name='" + name + '\'' +
                '}';
    }

}


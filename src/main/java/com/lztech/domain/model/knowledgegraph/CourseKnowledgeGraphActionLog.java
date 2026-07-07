package com.lztech.domain.model.knowledgegraph;

import com.lztech.domain.model.knowledgegraph.enums.BusinessType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tb_course_knowledge_graph_action_log")
public class CourseKnowledgeGraphActionLog {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;//序列

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程图谱id
     */
    private String courseKnowledgeGraphDomainId;

    /**
     * 模块标题
     */
    private String title;
    /**
     * 业务类型
     */
    private BusinessType businessType;

    /**
     * 旧知识点id
     */
    private String oldKnowledgeNodeId;

    /**
     * 旧知识点名称
     */
    private String oldKnowledgeNodeName;

    /**
     * 旧知识点层级编号
     */
    private Integer oldKnowledgeNodeLevelIndex;

    /**
     * 旧知识点层级名称
     */
    private String oldKnowledgeNodeLevelName;

    /**
     * 旧关联知识点id(删除关系使用)
     */
    private String oldRelationshipKnowledgeNodeId;

    /**
     * 旧关联知识点名称(删除关系使用)
     */
    private String oldRelationshipKnowledgeNodeName;

    /**
     * 旧关联知识点层级编号(删除关系使用)
     */
    private Integer oldRelationshipKnowledgeNodeLevelIndex;

    /**
     * 旧关联知识点层级名称(删除关系使用)
     */
    private String  oldRelationshipKnowledgeNodeLevelName;

    /**
     * 旧知识点数据id
     */
    private String oldKnowledgeNodeDataId;

    /**
     * 旧知识点数据内容
     */
    private String oldKnowledgeNodeDataContent;

    /**
     * 新知识点id
     */
    private String nowKnowledgeNodeId;

    /**
     * 新知识点名称
     */
    private String nowKnowledgeNodeName;

    /**
     * 新知识点数据id
     */
    private String nowKnowledgeNodeDataId;

    /**
     * 新知识点数据内容
     */
    private String nowKnowledgeNodeDataContent;

    /**
     * 新知识点层级编号
     */
    private Integer nowKnowledgeNodeLevelIndex;

    /**
     * 新知识点层级名称
     */
    private String nowKnowledgeNodeLevelName;

    /**
     * 新关联知识点id(新增关系使用)
     */
    private String nowRelationshipKnowledgeNodeId;

    /**
     * 新关联知识点名称(新增关系使用)
     */
    private String nowRelationshipKnowledgeNodeName;

    /**
     * 新关联知识点层级编号(新增关系使用)
     */
    private Integer nowRelationshipKnowledgeNodeLevelIndex;

    /**
     * 新关联知识点层级名称(新增关系使用)
     */
    private String  nowRelationshipKnowledgeNodeLevelName;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人工号
     */
    private String operatorNo;

    /**
     * 操作人名称
     */
    private String operatorName;

    /**
     * 操作人所在ip
     */
    private String operatorIp;

    /**
     * 详细内容
     */
    private String detailContent;

    /**
     * 操作时间
     */
    private Date operatorTime;

}

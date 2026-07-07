package com.lztech.domain.model.kggentask;

import com.lztech.domain.model.knowledgegraph.enums.LevelType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tb_kg_gen_node")
public class KgGenNode {

    @Id
    private String id;//序列
    private String creatorId; // 创建人id
    private String creatorName; //创建人姓名
    private Date createTime; // 创建时间
    private String modifierId; // 更新人id
    private String modifierName; //修改人姓名
    private Date modifyTime;// 更新时间

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 父任务id
     */
    private String masterTaskId;

    /**
     * 子任务id
     */
    private String subTaskId;

    /**
     * 节点层级
     */
    private Integer level;

    /**
     * 父节点id
     */
    private String parentNodeId;

    /**
     * 节点详情
     */
    private String content;

    /**
     * 章节内容Id
     */
    private String chapterContentId;

    /**
     * 节点顺序
     */
    private Integer nodeOrder;

    private LevelType levelType;

}

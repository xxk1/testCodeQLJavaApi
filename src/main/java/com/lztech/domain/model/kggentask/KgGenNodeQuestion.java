package com.lztech.domain.model.kggentask;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "tb_kg_gen_node_question")
public class KgGenNodeQuestion extends BaseUpdateModel {

    /**
     * 知识图谱id
     */
    private String nodeId;

    /**
     * 题目id
     */
    private String questionId;

    /**
     * 排序
     */
    private Integer sort;
    /**
     * 题目创建人Id
     */
    private String questionCreatorId;
    /**
     * 创建人名称
     *
     */
    private String questionCreatorName;

    /**
     * 主任务id
     */
    private String masterTaskId;

    /**
     * 子任务id
     */
    private String subTaskId;
}

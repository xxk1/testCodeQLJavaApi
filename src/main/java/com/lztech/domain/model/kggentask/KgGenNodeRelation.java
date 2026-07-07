package com.lztech.domain.model.kggentask;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tb_kg_gen_node_relation")
public class KgGenNodeRelation {
    @Id
    private String id;//序列


    private Date createTime;

    /**
     * 源节点id
     */
    private String originalNodeId;
    /**
     * 目标节点id
     */
    private String newNodeId;

    /**
     * 新的主任务id
     */
    private String newMasterTaskId;
}

package com.lztech.domain.model.kggentask;

import com.lztech.domain.model.kggentask.enums.TaskNodeStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Table(name = "tb_kg_gen_task_node")
@Entity
public class KgGenTaskNode {

    @Id
    private String id;//序列
    private String creatorId; // 创建人id
    private String creatorName; //创建人姓名
    private Date createTime; // 创建时间
    private String modifierId; // 更新人id
    private String modifierName; //修改人姓名
    private Date modifyTime;// 更新时间
    /**
     * 子任务id
     */
    private String subTaskId;

    /**
     * 文件内容id
     */
    private String fileContentId;

    /**
     * 节点状态
     */
    private TaskNodeStatus status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 内部链接
     */
    private String innerUrl;

    /**
     * 外部链接
     */
    private String outerUrl;

    /**
     * 路径
     */
    private String path;

    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 接收时间
     */
    private Date receiveTime;

}

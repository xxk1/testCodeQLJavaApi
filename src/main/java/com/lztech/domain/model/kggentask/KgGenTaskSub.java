package com.lztech.domain.model.kggentask;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.kggentask.enums.KgGenTaskStepCode;
import com.lztech.domain.model.kggentask.enums.KgGenTaskSubStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Table(name = "tb_kg_gen_task_sub")
@Entity
public class KgGenTaskSub extends BaseUpdateModel {

    /**
     * 主任务id
     */
    private String masterId;

    /**
     * 任务类型
     */
    private KgGenTaskStepCode stepCode;

    /**
     * 任务状态
     */
    private KgGenTaskSubStatus status;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 备注
     */
    private String remark;


}

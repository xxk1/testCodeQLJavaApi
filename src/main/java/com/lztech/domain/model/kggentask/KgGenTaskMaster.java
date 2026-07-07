package com.lztech.domain.model.kggentask;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.kggentask.enums.KgGenTaskMasterStatus;
import com.lztech.domain.model.kggentask.enums.KgGenTaskType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Table(name = "tb_kg_gen_task_master")
@Entity
public class KgGenTaskMaster extends BaseUpdateModel {

    /**
     * 课程id
     */
    private String courseId;
    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 教材Id
     */
    private String materialFileId;
    /**
     * 课堂实录关联班级id
     */
    private String groupId;

    /**
     * 课堂实录关联学年
     */
    private String schoolYear;

    /**
     * 课堂实录关联学期
     */
    private Integer term;


    /**
     * 类型 1:教材 2:课堂实录
     */
    private KgGenTaskType type;

    /**
     * 状态 0:待生成 1:生成中 2:生成成功 3:生成失败 4:部分成功
     */
    private KgGenTaskMasterStatus status;

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

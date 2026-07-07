package com.lztech.domain.model.experimentschedulelcoursetabledetail;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_experiment_schedule_course_table_detail_room_user")
public class ExperimentScheduleCourseTableDetailRoomUser extends BaseModel {

    /**
     * 教室Id
     */
    private String roomId;
    /**
     * 教室名称
     */
    private String roomName;
    /**
     * 教室编号
     */
    private String roomCode;
    /**
     * 人员数量
     */
    private String personnelNumber;
    /**
     * 楼栋Id
     */
    private String roomCategoryId;
    /**
     * 课表明细Id
     */
    @ManyToOne
    @JoinColumn(name = "experiment_schedule_course_table_detail_id")
    private ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail;

    /**
     * 物品准备老师id
     */
    private String itemTeacherId;

    /**
     * 物品准备老师编号
     */
    private String itemTeacherNo;

    /**
     * 物品准备老师名称
     */
    private String itemTeacherName;
    /**
     * 是否直播
     */
    private boolean hasLive;
    /**
     * 教室关联组
     */
    private String groupId;
}

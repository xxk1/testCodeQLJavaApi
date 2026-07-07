package com.lztech.domain.model.experimentschedulelcoursetabledetail;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_experiment_schedule_course_log")
public class ExperimentScheduleCourseLog extends BaseModel {

    /**
     * 待排课表id
     */
    private String experimentScheduleId;
    /**
     * 操作描述
     */
    private String operationDescription;

}

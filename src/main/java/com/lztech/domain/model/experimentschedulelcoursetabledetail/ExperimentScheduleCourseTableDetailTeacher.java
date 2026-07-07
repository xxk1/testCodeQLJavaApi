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
@Table(name = "tb_experiment_schedule_course_table_detail_teacher")
public class ExperimentScheduleCourseTableDetailTeacher extends BaseModel {

    /**
     * 上课老师Id
     */
    private String teacherId;
    /**
     * 上课老师工号
     */
    private String teacherNo;
    /**
     * 上课老师名称
     */
    private String teacherName;
    /**
     * 上课老师所属学院id
     */
    private String teacherCollegeId;
    /**
     * 上课老师所属学院名称
     */
    private String teacherCollegeName;

    private String teacherTitle;
    /**
     * 课表明细Id
     */
    @ManyToOne
    @JoinColumn(name = "experiment_schedule_course_table_detail_id")
    private ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail;
}

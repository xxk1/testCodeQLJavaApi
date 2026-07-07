package com.lztech.domain.model.experimentschedulelcoursetabledetail;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_experiment_schedule_course_table_detail_project")
public class ExperimentScheduleCourseTableDetailProject {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    /**
     * 项目id
     */
    private String projectId;

    /**
     * 项目编号
     */
    private String projectCode;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 课表明细Id
     */
    @ManyToOne
    @JoinColumn(name = "experiment_schedule_course_table_detail_id")
    private ExperimentScheduleCourseTableDetail experimentScheduleCourseTableDetail;
}

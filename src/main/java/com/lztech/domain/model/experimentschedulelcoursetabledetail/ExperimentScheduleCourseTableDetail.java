package com.lztech.domain.model.experimentschedulelcoursetabledetail;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.coursetabledetail.enums.CourseKind;
import com.lztech.domain.model.coursetype.CourseType;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.EntranceGuardGeneratedStatus;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.ScheduleSource;
import com.lztech.domain.model.experimentschedulelcoursetabledetail.enums.ScheduleStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_experiment_schedule_course_table_detail")
public class ExperimentScheduleCourseTableDetail extends BaseUpdateModel {

    /**
     * 第几周
     */
    private String week;
    /**
     * 周几
     */
    private Integer weekNum;

    /**
     * 日期
     */
    private Date courseDate;
    /**
     * 课程类型ID
     */
    @ManyToOne
    @JoinColumn(name = "course_type_id")
    private CourseType courseType;

    /**
     * 课程类型名称
     */
    private String courseTypeName;

    /**
     * 主课表id
     */
    private String courseTableId;

    private String segment;

    /**
     * 数据来源
     */
    @Enumerated(EnumType.ORDINAL)
    private ScheduleSource scheduleSource;

    /**
     * 上课老师
     */
    @OneToMany(mappedBy = "experimentScheduleCourseTableDetail", fetch = FetchType.EAGER)
    private List<ExperimentScheduleCourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();

    /**
     * 上课教室_老师
     */
    @OneToMany(mappedBy = "experimentScheduleCourseTableDetail")
    private List<ExperimentScheduleCourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();

    /**
     * 课表明细节次详情
     */
    @OneToMany(mappedBy = "experimentScheduleCourseTableDetail")
    private List<ExperimentScheduleCourseTableDetailSegment> courseSegmentList = new ArrayList<>();


    /**
     * 节次开始时间
     */
    private Date segmentStartTime;

    /**
     * 节次结束时间
     */
    private Date segmentEndTime;

    /**
     * 课表类型(0:理论课；1：实验课)
     */
    @Enumerated(EnumType.ORDINAL)
    private CourseKind courseKind;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 老师姓名冗余字段，多个使用逗号隔开
     */
    private String teacherNames;

    /**
     * 老师所属学院id冗余字段，多个使用逗号隔开
     */
    private String teacherCollegeIds;

    /**
     * 老师所属学院冗余字段，多个使用逗号隔开
     */
    private String teacherCollegeNames;

    /**
     * 课表明细项目
     */
    @OneToMany(mappedBy = "experimentScheduleCourseTableDetail")
    private List<ExperimentScheduleCourseTableDetailProject> courseTableDetailProjects = new ArrayList<>();


    /**
     * 实验原始课表明细Id
     */
    public String experimentOriginalCourseTableDetailId;

    /**
     * 排课状态
     */
    @Enumerated(EnumType.ORDINAL)
    private ScheduleStatus scheduleStatus;

    /**
     * 班级id
     */
    private String groupId;

    /**
     * 班级编号
     */
    private String groupNo;

    /**
     * 班级名
     */
    private String groupName;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程编号
     */
    private String courseCode;

    private EntranceGuardGeneratedStatus entranceGuardGeneratedStatus = EntranceGuardGeneratedStatus.NOT_GENERATED;
}

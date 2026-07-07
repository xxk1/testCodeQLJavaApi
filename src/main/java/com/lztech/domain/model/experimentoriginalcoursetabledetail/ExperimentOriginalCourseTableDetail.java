package com.lztech.domain.model.experimentoriginalcoursetabledetail;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetabledetail.enums.CourseKind;
import com.lztech.domain.model.coursetype.CourseType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_experiment_original_course_table_detail")
public class ExperimentOriginalCourseTableDetail extends BaseModel {

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
     * 主课表
     */
    private String courseTableId;

    private String segment;

    /**
     * 数据来源
     */
    @Enumerated(EnumType.ORDINAL)
    private Source source;

    /**
     * 上课老师
     */
    @OneToMany(mappedBy = "experimentOriginalCourseTableDetail", fetch = FetchType.EAGER)
    private List<ExperimentOriginalCourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();

    /**
     * 上课教室_老师
     */
    @OneToMany(mappedBy = "experimentOriginalCourseTableDetail")
    private List<ExperimentOriginalCourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();

    /**
     * 课表明细节次详情
     */
    @OneToMany(mappedBy = "experimentOriginalCourseTableDetail")
    private List<ExperimentOriginalCourseTableDetailSegment> courseSegmentList = new ArrayList<>();


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
    @OneToMany(mappedBy = "experimentOriginalCourseTableDetail")
    private List<ExperimentOriginalCourseTableDetailProject> courseTableDetailProjects = new ArrayList<>();

}

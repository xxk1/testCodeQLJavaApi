package com.lztech.domain.model.coursetabledetail;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.coursesegment.CourseSegment;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetabledetail.enums.CourseKind;
import com.lztech.domain.model.coursetabledetail.enums.CourseStatus;
import com.lztech.domain.model.coursetabledetailroomuser.CourseTableDetailRoomUser;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.coursetype.CourseType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "tb_course_table_detail")
public class CourseTableDetail extends BaseModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
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
    @ManyToOne
    @JoinColumn(name = "course_table_id")
    private CourseTable courseTable;

    private String segment;

    /**
     * 数据来源
     */
    @Enumerated(EnumType.ORDINAL)
    private Source source;

    /**
     * 上课老师
     */
    @OneToMany(mappedBy = "courseTableDetail", fetch = FetchType.EAGER)
    private List<CourseTableDetailTeacher> courseTableDetailTeacherList = new ArrayList<>();

    /**
     * 上课教室_老师
     */
    @OneToMany(mappedBy = "courseTableDetail")
    private List<CourseTableDetailRoomUser> courseTableDetailRoomUserList = new ArrayList<>();
    /**
     * 课表明细节次详情
     */
    @OneToMany(mappedBy = "courseTableDetail")
    private List<CourseSegment> courseSegmentList = new ArrayList<>();

    /**
     * 课程/课次状态 0：已上课；1：未上课
     */
    @Enumerated(EnumType.ORDINAL)
    private CourseStatus courseStatus;

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
     * 数据来源
     */
    private String sourceDataSource = "0";
    /**
     * 数据来源名称
     */
    private String sourceDataSourceName = "本科生";
    /**
     * 课表明细项目
     */
    @OneToMany(mappedBy = "courseTableDetail")
    private List<CourseTableDetailProject> courseTableDetailProjects = new ArrayList<>();
}


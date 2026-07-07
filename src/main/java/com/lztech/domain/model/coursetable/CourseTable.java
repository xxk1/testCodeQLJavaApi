package com.lztech.domain.model.coursetable;


import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.domain.model.coursetable.enums.WeekType;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.group.Group;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_course_table")
public class CourseTable extends BaseModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    /**
     * 组
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    private String collegeId;
    private String collegeName;
    private String schoolYear;
    private Integer term;
    /**
     * 数据来源
     */
    @Enumerated(EnumType.ORDINAL)
    private Source source;
    /**
     * 周类型
     */
    @Enumerated(EnumType.ORDINAL)
    private WeekType weekType;

    /**
     * 课程类别
     */
    @ManyToOne
    @JoinColumn(name = "course_category_id")
    private CourseCategory courseCategory;

    private String teacherName;

    /**
     * 课表明细
     */
    @OneToMany(mappedBy = "courseTable", fetch = FetchType.EAGER)
    private List<CourseTableDetail> courseTableDetailList = new ArrayList<>();
    /**
     * 药科大期末评教对接使用 通知单类型
     */
    private  String noticeCategory;

    /**
     * 药科大期末评教对接使用 辅助名称
     */
    private String auxiliaryName;

    @Enumerated(EnumType.ORDINAL)
    private StudentType studentType;

    /**
     * 教师工号, 多个用逗号拼接
     */
    private String teacherNo;
    /**
     * 教师id,多个用逗号拼接
     */
    private String courseTableTeacherId;
}

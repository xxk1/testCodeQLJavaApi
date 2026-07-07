package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.enums.CourseSource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_course")
public class Course extends BaseModel {
    @Id
    private String id;
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;
    private String collegeName;
    private String collegeCode;
    /**
    课程编号
     */
    private String courseCode;

    /**
     * 课程负责人id
     */
    private String courseLeaderId;

    /**
     * 课程负责人名称
     */
    private String courseLeaderName;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 课程来源
     */
    @Enumerated(value = EnumType.ORDINAL)
    private CourseSource courseSource;

    /**
     * 使用状态(true:正使用；false:已删除)
     */
    private boolean useState;

    @OneToMany(mappedBy = "course")
    private List<CourseStructure> courseStructures = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<CourseTeachingTeam> courseTeachingTeams = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<CourseIntroduction> courseIntroductionList = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<CourseCompletion> courseCompletionList = new ArrayList<>();
    @OneToOne(mappedBy = "course")
    private CourseCover courseCover ;
}

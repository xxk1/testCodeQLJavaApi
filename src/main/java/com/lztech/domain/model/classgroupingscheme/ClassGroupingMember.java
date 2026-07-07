package com.lztech.domain.model.classgroupingscheme;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 班级分组成员
 */
@Setter
@Getter
@Entity
@Table(name = "tb_class_grouping_member")
public class ClassGroupingMember extends BaseModel {

    /**
     * 学生Id
     */
    private String studentId;

    /**
     * 学生学号
     */
    private String studentNo;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 专业ID
     */
    private String majorId;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 学生所属学院id
     */
    private String collegeId;

    /**
     * 学生所属学院名称
     */
    private String collegeName;

    /**
     * 行政班
     */
    private String administrativeClassName;

    /**
     * 是否为组长（true：为组长；false：普通成员）
     */
    private Boolean whetherGroupLeader;

    /**
     * 班级分组
     */
    @ManyToOne
    @JoinColumn(name = "class_grouping_id")
    private ClassGrouping classGrouping;
}

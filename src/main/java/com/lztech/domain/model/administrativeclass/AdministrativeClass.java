package com.lztech.domain.model.administrativeclass;


import com.lztech.domain.model.administrativeclassandgroup.AdministrativeClassAndGroup;
import com.lztech.domain.model.coursetable.enums.Source;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_administrative_class")
public class AdministrativeClass {

    @Id
    private String id;
    /**
     * 行政班名称
     */
    private String className;
    /**
     * 专业ID
     */
    private String majorId;
    /**
     * 专业名称
     */
    private String majorName;
    /**
     * 年级
     */
    private String year;
    /**
     * 学院ID
     */
    private String collegeId;
    /**
     * 学院名称
     */
    private String collegeName;
    /**
     * 专业代码
     */
    private String gradeMajorCode;
    /**
     * 学校制度
     */
    private String schoolSystem;
    /**
     * 学生人数
     */
    private Integer studentCount;
    /**
     * 数据来源
     */
    @Enumerated(EnumType.ORDINAL)
    private Source source;
    /**
     * 班主任老师id
     */
    private String teacherId;

    /**
     * 行政班和课表班
     */
    @OneToMany(mappedBy = "administrativeClass")
    private List<AdministrativeClassAndGroup> administrativeClasses = new ArrayList<>();
    /**
     * 辅导员Id
     */
    private String counsellorId;
    /**
     * 辅导员工号
     */
    private String counsellorNo;
    /**
     * 辅导员姓名
     */
    private String counsellorName;
}

package com.lztech.domain.model.classgroupingscheme;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 班级分组
 */
@Setter
@Getter
@Entity
@Table(name = "tb_class_grouping")
public class ClassGrouping extends BaseModel {

    /**
     * 班级分组名称
     */
    private String classGroupingName;

    /**
     * 负责教师id
     */
    private String responsibleTeacherId;

    /**
     * 负责教师工号
     */
    private String responsibleTeacherNo;

    /**
     * 负责教师名称
     */
    private String responsibleTeacherName;

    /**
     * 班级分组方案
     */
    @ManyToOne
    @JoinColumn(name = "class_grouping_scheme_id")
    private ClassGroupingScheme classGroupingScheme;

    /**
     * 班级分组成员
     */
    @OneToMany(mappedBy = "classGrouping", cascade = CascadeType.ALL)
    private List<ClassGroupingMember> classGroupingMemberList = new ArrayList<>();
}

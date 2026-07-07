package com.lztech.domain.model.project;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.project.enums.*;
import com.lztech.domain.model.projectattribute.ProjectAttribute;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "tb_project")
public class Project extends BaseUpdateModel {


    private String projectCode;

    private String projectName;

    private String projectDescription;

    /**
     * 项目标准时长 单位：h
     */
    private Double standardDuration;
    /**
     * 项目和项目归属
     */
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    List<ProjectAttribute> projectAttributeList = new ArrayList<>();
    /**
     * 项目分类id
     */
    private String projectClassificationId;

    /**
     * 项目分类名称
     */
    private String projectClassificationName;

    /**
     * 项目种类
     */
    @Enumerated(EnumType.ORDINAL)
    private ProjectType projectType;

    /**
     * 项目类别
     */
    @Enumerated(EnumType.ORDINAL)
    private ProjectCategory projectCategory;

    /**
     * 项目要求
     */
    @Enumerated(EnumType.ORDINAL)
    protected ProjectClaim projectClaim;

    /**
     * 项目类型
     */
    @Enumerated(EnumType.ORDINAL)
    protected ProjectGenre projectGenre;

    /**
     * 是否关联课程
     */
    @Enumerated(EnumType.ORDINAL)
    protected WhetherAssociateCourses whetherAssociateCourses;
}

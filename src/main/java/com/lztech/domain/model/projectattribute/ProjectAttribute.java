package com.lztech.domain.model.projectattribute;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.project.Project;
import com.lztech.domain.model.projectattribute.enums.ProjectAttributeKind;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 项目归属表
 */
@Setter
@Getter
@Entity
@Table(name = "tb_project_attribute")
public class ProjectAttribute  extends BaseModel {


    @Enumerated(EnumType.ORDINAL)
    private ProjectAttributeKind attributeKind;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    /**
     * 归属id
     */
    private  String dataId;
    /**
     * 归属名称
     */
    private  String dataName;
    /**
     * 归属编号
     */
    private String dataCode;
}

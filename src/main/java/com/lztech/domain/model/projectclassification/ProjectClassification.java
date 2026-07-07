package com.lztech.domain.model.projectclassification;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_project_classification")
public class ProjectClassification extends BaseModel {
    /**
     * 分类名称
     */
    private String classificationName;

}

package com.lztech.domain.model.classgroupingscheme;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.classgroupingscheme.enums.SchemeType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 班级分组方案
 */
@Setter
@Getter
@Entity
@Table(name = "tb_class_grouping_scheme")
public class ClassGroupingScheme extends BaseUpdateModel {

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 方案描述
     */
    private String schemePresentation;

    /**
     * 方案类型
     */

    private SchemeType schemeType;

    /**
     * 所属班级id
     */
    private String groupId;

    /**
     * 已分组总人数
     */
    private Integer groupedHeadCount;

    /**
     * 班级分组
     */
    @OneToMany(mappedBy = "classGroupingScheme", cascade = CascadeType.ALL)
    private List<ClassGrouping> classGroupingList = new ArrayList<>();
}

package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 主题明细表
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_theme_detail")
public class CourseThemeDetail extends BaseModel {

    /**
     * 主题明细内容
     */
    private String themeDetailContent;

    /**
     * 主题明细排序
     */
    private Integer showOrder;

    @ManyToOne
    @JoinColumn(name = "course_theme_id")
    private CourseTheme courseTheme;
}

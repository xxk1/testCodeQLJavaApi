package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.ResourceStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程主题信息
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_theme")
public class CourseTheme extends BaseUpdateModel {

    /**
     * 主题名称
     */
    private String themeName;

    /**
     * 主题描述
     */
    private String themeDesc;

    /**
     * 主题状态
     */
    private ResourceStatus themeStatus;

    /**
     * 主题数量
     */
    private Integer themeDetailNum;

    /**
     * 引用的主题ID
     */
    private String copiedThemeId;

    @OneToMany(mappedBy = "courseTheme", cascade = CascadeType.ALL)
    private List<CourseThemeDetail> courseThemeDetailList = new ArrayList<>();

}

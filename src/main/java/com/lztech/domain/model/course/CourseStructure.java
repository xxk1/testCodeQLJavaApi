package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.StructureStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程结构
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_structure")
public class CourseStructure extends BaseUpdateModel {
    /**
     * 课程结构名称
     */
    private String courseStructureName;

    /**
     * 课程结构状态
     */
    private StructureStatus structureStatus;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 排序序号
     */
    private Integer showOrder;

    @OneToMany(mappedBy = "courseStructure",fetch=FetchType.EAGER)
    private List<CourseResource> courseResources = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_version_id")
    private CourseVersion courseVersion;
}

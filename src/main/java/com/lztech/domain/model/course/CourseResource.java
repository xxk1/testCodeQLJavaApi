package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.ResourceSourceType;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.domain.model.course.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 课程资源
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_resource")
public class CourseResource extends BaseUpdateModel {

    /**
     * 文件状态
     */
    private ResourceStatus resourceStatus;

    /**
     * 是否为公共资源
     */
    private Boolean isPublic;

    /**
     * 资源类型
     */
    @Enumerated(value = EnumType.ORDINAL)
    private ResourceType resourceType;

    /**
     * 资源详情id
     */
    private String resourceDetailId;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 资源大小
     */
    private Long resourceSize;

    /**
     * 资源内容数量
     */
    private Integer resourceContentNum;

    @ManyToOne
    @JoinColumn(name = "course_structure_id")
    private CourseStructure courseStructure;

    /**
     * 资源引用次数(个人引用次数)
     */
    private Integer resourceReferences = 0;
    /**
     * 资源引用次数(他人引用次数)
     */
    private Integer resourceOtherReferences = 0;

    /**
     * 版本号 文件名称使用
     */
    private Integer visionNumber;
    /**
     * 父id 名称关联使用
     */
    private String parentId;

    /**
     * 课程ID
     */
    private String courseId;

    /**
     * 资源来源类型
     */
    private ResourceSourceType sourceType;

    /**
     * 资源来源ID
     */
    private String sourceId;

    /**
     * 被引用人ID
     */
    private String citedUserId;

    /**
     * 被引用人姓名
     */
    private String citedUserName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 排序 用于教学内容排序
     */
    private Integer auxiliarySort;
}

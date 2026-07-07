package com.lztech.domain.model.knowledgegraph;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseVersion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_course_knowledge_graph_domain")
public class CourseKnowledgeGraphDomain extends BaseUpdateModel {
    /**
     * 节点个数
     */
    private Integer nodeCount;

    /**
     * 关系个数
     */
    private Integer shipCount;

    /**
     * 状态（true 启用，false 禁用）
     */
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    /**
     * 课程编号
     */
    private String courseCode;
    /**
     * 课程名称
     */
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "course_version_id")
    private CourseVersion courseVersion;

    /**
     * 排序
     */
    private Integer showOrder;

    /**
     * 版本标签
     */
    private String versionLabel;


}

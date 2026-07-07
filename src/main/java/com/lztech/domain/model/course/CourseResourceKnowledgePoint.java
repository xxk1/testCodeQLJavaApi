package com.lztech.domain.model.course;


import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课程资源知识点关联表
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_resource_knowledge_point")
public class CourseResourceKnowledgePoint extends BaseUpdateModel {

    private String knowledgePointId;

    private String resourceId;

    private String courseKnowledgeGraphDomainId;

}

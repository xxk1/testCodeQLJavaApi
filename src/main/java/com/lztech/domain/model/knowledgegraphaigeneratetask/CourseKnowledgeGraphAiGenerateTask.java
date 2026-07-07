package com.lztech.domain.model.knowledgegraphaigeneratetask;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.knowledgegraphaigeneratetask.enums.GraphAiGenerateStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_course_knowledge_graph_ai_generate_task")
public class CourseKnowledgeGraphAiGenerateTask extends BaseUpdateModel {

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 学年
     */
    private String schoolYear;

    /**
     * 学期
     */
    private Integer term;

    /**
     * 教学班id
     */
    private String groupId;

    /**
     * 知识图谱智能生成任务状态
     */
    private GraphAiGenerateStatus graphAiGenerateStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 知识图谱智能生成任务结果
     */
    private String graphContent;

}

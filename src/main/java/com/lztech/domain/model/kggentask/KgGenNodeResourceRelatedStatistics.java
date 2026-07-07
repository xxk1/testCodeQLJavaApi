package com.lztech.domain.model.kggentask;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_kg_gen_node_resource_related_statistics")
public class KgGenNodeResourceRelatedStatistics extends BaseUpdateModel {

    /**
     * 视频总数
     */
    private Integer totalVideoNum;

    /**
     * 语音转写支持的
     */
    private Integer supportVoiceTranVideoNum;

    /**
     * 语音转写支持的教师名称
     */
    private String supportVoiceTranVideoTeacherNames;

    /**
     * 关联的片段数
     */
    private Integer relatedVideoClipNum;

    /**
     * 课程题目数
     */
    private Integer courseQuestionNum;

    /**
     * 课程题目教师信息
     */
    private String courseQuestionTeacherNames;

    /**
     * 关联的课程题目数
     */
    private Integer relatedQuestionNum;

    /**
     * 主任务id
     */
    private String masterTaskId;

    /**
     * 子任务id
     */
    private String subTaskId;
}

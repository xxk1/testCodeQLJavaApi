package com.lztech.domain.model.knowledgegraph;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.knowledgegraph.enums.TextDataSource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_course_knowledge_graph_node_video_info_text")
public class CourseKnowledgeGraphNodeVideoInfoText extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "course_knowledge_graph_domain_id")
    private CourseKnowledgeGraphDomain courseKnowledgeGraphDomain;

    /**
     * 文本数据来源
     */
    @Enumerated(value = EnumType.ORDINAL)
    private TextDataSource textDataSource;

    /**
     * 视频信息id
     */
    private String videoInfoId;

    /**
     * 学年
     */
    private String schoolYear;

    /**
     * 学期
     */
    private Integer term;

    /**
     * 课表明细老师id(多个时","分割)
     */
    private  String teacherIds;

    /**
     * 课表明细老师工号(多个时","分割)
     */
    private String teacherNos;

    /**
     * 课表明细老师名称(多个时","分割)
     */
    private String teacherNames;

    /**
     * 视频日期
     */
    private String videoDate;
    /**
     * 课表id
     */
    private String courseTableDetailId;

    /**
     * 节次(第几节次)
     */
    private Integer segment;

    /**
     * 开课id
     */
    private String courseTableId;

    /**
     * 教学班id
     */
    private String groupId;

    /**
     * 课程id
     */
    private String courseId;


    /**
     * 内网ip
     */
    private String innerIp;

    /**
     * 外网ip
     */
    private String outerIp;

    /**
     * 缩略图路径
     */
    private String thumbnailPath;

    /**
     * 知识图谱知识点id
     */
    private String knowledgeNodeId;

    /**
     * 知识图谱知识点名称
     */
    private String knowledgeNodeName;

    /**
     * 文本内容
     */
    private String textContent;

    /**
     * 开始时间戳(离视频开始相隔多少秒)
     */
    private Double startTimestamp;

    /**
     * 结束时间戳(离视频开始相隔多少秒)
     */
    private Double endTimestamp;

    /**
     * 高亮文本内容
     */
    private String highLightTextContent;

    /**
     * 相似性分数
     */
    private Double similarityScore;



}

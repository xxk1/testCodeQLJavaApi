package com.lztech.domain.model.knowledgegraph;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.TranscodeStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_course_knowledge_graph_node_resource_file")
public class CourseKnowledgeGraphNodeResourceFile extends BaseUpdateModel {
    /**
     * 文件显示名称
     */
    private String fileDisplayName;
    /**
     * 文件存储名称
     */
    private String fileRealName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 内网地址
     */
    private String innerUrl;
    /**
     * 外网地址
     */
    private String outerUrl;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 转码状态
     */
    private TranscodeStatus transcodeStatus;

    /**
     * 转码结果信息
     */
    private String transcodeMessage;
    /**
     * 转码后内网ip
     */
    private String transcodeInnerIp;

    /**
     * 转码后外网IP
     */
    private String transcodeOuterIp;
    /**
     * 转码后文件类型
     */
    private String transcodeFileType;
    /**
     * 转码后文件路径
     */
    private String transcodeFilePath;

    /**
     * 转码后文件服务器存储的名称
     */
    private String transcodeFileSavedName;

    /**
     * 转码后文件大小
     */
    private Long transcodeFileSize;
}

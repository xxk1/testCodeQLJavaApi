package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.AIIdentifyStatus;
import com.lztech.domain.model.course.enums.ResourceStatus;
import com.lztech.domain.model.course.enums.TranscodeStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课程资源文件
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_resource_file")
public class CourseResourceFile extends BaseUpdateModel {
    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件显示名
     */
    private String fileRealName;

    /**
     * 文件服务器存储的名称
     */
    private String fileSavedName;

    /**
     * 内网ip
     */
    private String innerIp;

    /**
     * 外网IP
     */
    private String outerIp;

    /**
     * 文件状态
     */
    private ResourceStatus resourceStatus;

    /**
     *  是否为公共资源
     */
    private Boolean isPublic;
    /**
     * 版本号 文件名称使用
     */
    private Integer visionNumber;
    /**
     * 父id 名称关联使用
     */
    private String parentId;
    /**
     * 引用的资源文件ID
     */
    private String copiedResourceFileId;

    /**
     * 转码后文件大小
     */
    private Long transcodeFileSize;

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
     * 转码后内网ip
     */
    private String transcodeInnerIp;

    /**
     * 转码后外网IP
     */
    private String transcodeOuterIp;

    /**
     * 转码状态
     */
    private TranscodeStatus transcodeStatus;

    /**
     * 转码结果信息
     */
    private String transcodeMessage;

    /**
     * AI识别状态
     */
    private AIIdentifyStatus aiIdentifyStatus = AIIdentifyStatus.UNRECOGNIZED;

}

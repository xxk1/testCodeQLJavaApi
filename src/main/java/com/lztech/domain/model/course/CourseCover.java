package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 课程资源文件
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_cover")
public class CourseCover extends BaseUpdateModel {
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

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

}

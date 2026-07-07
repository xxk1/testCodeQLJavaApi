package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.SearchEngineStatus;
import com.lztech.domain.model.course.enums.WisdomCourseTaskCollectStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 课程资源文件内容表
 */
@Entity
@Getter
@Setter
@Table(name = "tb_course_resource_file_content")
public class CourseResourceFileContent extends BaseUpdateModel {

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 文件显示名称
     */
    private String fileDisplayName;


    /**
     * 文件类型（即文件后缀）
     */
    private String fileType;

    /**
     * 章名
     */
    private String chapterTitle;

    /**
     * 内网地址
     */
    private String contentInnerUrl;

    /**
     * 外网地址
     */
    private String contentOuterUrl;

    /**
     * 文件路径
     */
    private String contentPath;


    /**
     * 课程资源文件
     */
    @OneToOne
    @JoinColumn(name = "course_resource_file_id")
    private CourseResourceFile courseResourceFile;

    /**
     * 推送搜索引擎状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    private SearchEngineStatus searchEngineStatus;


    private String courseResourceId;

    /**
     * 智慧课程任务提取状态
     */
    private WisdomCourseTaskCollectStatus wisdomCourseTaskCollectStatus = WisdomCourseTaskCollectStatus.NOT_COLLECT;

}

package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.course.enums.FileContentStatus;
import com.lztech.domain.model.course.enums.QuestionGenerateStatus;
import com.lztech.domain.model.course.enums.WisdomCourseTaskCollectStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 课程建设教材内容表
 */
@Entity
@Getter
@Setter
@Table(name = "tb_teaching_material_file_content")
public class TeachingMaterialFileContent extends BaseUpdateModel {

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 文件名
     */
    private String fileDisplayName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 章名
     */
    private String chapterName;

    /**
     * 章序号
     */
    private Integer chapterIndex;

    /**
     * 知识点标签
     */
    private String knowledgeTag;

    /**
     * 文件路径
     */
    private String contentPath;

    /**
     * 文件内网地址
     */
    private String contentInnerUrl;

    /**
     * 文件外网地址
     */
    private String contentOuterUrl;

    /**
     * 课程建设教材
     */
    @ManyToOne
    @JoinColumn(name = "material_file_id")
    private TeachingMaterialFile teachingMaterialFile;

    /**
     * ai生成题目状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    private QuestionGenerateStatus questionGenerateStatus = QuestionGenerateStatus.UNRECOGNIZED;

    /**
     * 教材内容状态(0:已删除;1:正常)
     */
    @Enumerated(value = EnumType.ORDINAL)
    private FileContentStatus fileContentStatus = FileContentStatus.NORMAL;

    /**
     * 智慧课程任务提取状态
     */
    private WisdomCourseTaskCollectStatus wisdomCourseTaskCollectStatus = WisdomCourseTaskCollectStatus.NOT_COLLECT;
}

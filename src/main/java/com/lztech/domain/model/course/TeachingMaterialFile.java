package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.course.enums.OcrStatus;
import com.lztech.domain.model.course.enums.QuestionGenerateStatus;
import com.lztech.domain.model.course.enums.WisdomCourseTaskCollectStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程教材附件表
 */
@Entity
@Getter
@Setter
@Table(name = "tb_teaching_material_file")
public class TeachingMaterialFile extends BaseModel {
    /**
     * 文件显示名称
     */
    private String fileDisplayName;

    /**
     * 文件存储名称
     */
    private String fileRealName;

    /**
     * 文件类型（即文件后缀）
     * 例如:1.jpg，则为jpg
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
     * 课程资料表
     */
    @ManyToOne
    @JoinColumn(name = "course_material_id")
    private CourseMaterial courseMaterial;

    /**
     * AI识别状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    private OcrStatus ocrStatus = OcrStatus.UNRECOGNIZED;

    /**
     * ai生成题目状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    private QuestionGenerateStatus questionGenerateStatus = QuestionGenerateStatus.UNRECOGNIZED;

    @OneToMany(mappedBy = "teachingMaterialFile", cascade = CascadeType.ALL)
    private List<TeachingMaterialFileContent> teachingMaterialFileContentList = new ArrayList<>();

    /**
     * 智慧课程任务提取状态
     */
    private WisdomCourseTaskCollectStatus wisdomCourseTaskCollectStatus = WisdomCourseTaskCollectStatus.NOT_COLLECT;

    /**
     * 封面文件显示名称
     */
    private String coverFileDisplayName;

    /**
     * 封面文件存储名称
     */
    private String coverFileRealName;

    /**
     * 封面文件类型
     */
    private String coverFileType;

    /**
     * 封面内网地址
     */
    private String coverInnerUrl;

    /**
     * 封面外网地址
     */
    private String coverOuterUrl;

    /**
     * 封面文件路径
     */
    private String coverFilePath;

    /**
     * 封面文件大小
     */
    private Long coverFileSize;

}

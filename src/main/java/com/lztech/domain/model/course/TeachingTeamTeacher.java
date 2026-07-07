package com.lztech.domain.model.course;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 教学团队教师信息
 */
@Entity
@Getter
@Setter
@Table(name = "tb_teaching_team_teacher")
public class TeachingTeamTeacher  extends BaseUpdateModel {
    /**
     * 老师id(校内)/tb_course_teaching_team id（校外）
     */
    private String teacherId;

    /**
     * 老师姓名
     */
    private String teacherName;

    /**
     * 老师工号
     */
    private String teacherNo;

    /**
     * 照片文件显示名称
     */
    private String photoFileDisplayName;

    /**
     * 照片文件存储名称
     */
    private String photoFileRealName;

    /**
     * 照片文件类型
     */
    private String photoFileType;

    /**
     * 照片内网地址
     */
    private String photoInnerUrl;

    /**
     * 照片外网地址
     */
    private String photoOuterUrl;

    /**
     * 照片文件路径
     */
    private String photoFilePath;

    /**
     * 照片文件大小
     */
    private Long photoFileSize;
}

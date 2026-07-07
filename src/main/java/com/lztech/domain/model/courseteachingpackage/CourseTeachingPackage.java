package com.lztech.domain.model.courseteachingpackage;

import com.lztech.domain.model.base.BaseUpdateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tb_course_teaching_package")
public class CourseTeachingPackage extends BaseUpdateModel {

    /**
     * 课程结构ID
     */
    private String courseStructureId;
    /**
     * 课程资源ID
     */
    private String courseResourceId;
    /**
     * 课程资源文件ID
     */
    private String courseResourceFileId;
    /**
     * 教学包名称
     */
    private String teachingPackageName;
    /**
     * 教学包简介
     */
    private String teachingPackageIntroduction;
    /**
     * 教学包封面文件ID
     */
    private String teachingPackageFileId;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 内网ip
     */
    private String innerIp;

    /**
     * 外网IP
     */
    private String outerIp;

    /**
     * 标签ID
     */
    private String labelId;
    /**
     * 标签名称
     */
    private String labelName;
    /**
     * 版本ID
     */
    private String courseVersionId;
    /**
     * 课程ID
     */
    private String courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 版本号
     */
    private Integer visionNumber;
    /**
     * 版本时间
     */
    private Date visionTime;
    /**
     * 引用次数
     */
    private Integer quoteNum;
}

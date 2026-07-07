package com.lztech.site.viewmodel.event;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class CourseVersionEvent {
    private String id;

    private int source;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程编码
     */
    private String courseCode;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 版本序号
     */
    private Integer versionNumber;

    /**
     * 归档人id
     */
    private String filingUserId;

    /**
     * 归档人工号
     */
    private String filingUserNo;

    /**
     * 归档人姓名
     */
    private String filingUserName;

    /**
     * 完成度
     */
    private double completeRate;

    /**
     * 更新人id
     */
    private String modifierId;

    /**
     * 更新人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private String modifyTime;



}

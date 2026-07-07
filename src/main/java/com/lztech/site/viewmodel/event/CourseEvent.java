package com.lztech.site.viewmodel.event;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class CourseEvent {
    private String id;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 课程编码
     */
    private String courseCode;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程来源
     */
    private Integer courseSource;

    /**
     * 课程学院id
     */
    private String courseCollegeId;

    /**
     * 课程学院编号
     */
    private String courseCollegeCode;

    /**
     * 课程学院名称
     */
    private String courseCollegeName;

    private String modifyTime;
}

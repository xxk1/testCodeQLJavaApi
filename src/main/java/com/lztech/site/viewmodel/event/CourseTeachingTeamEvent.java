package com.lztech.site.viewmodel.event;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class CourseTeachingTeamEvent {
    private String id;
    private Integer source;
    private String courseId;
    private String courseCode;
    private String courseName;
    private String courseVersionId;
    private String teacherId;
    private String teacherNo;
    private String teacherName;
    private String jobTitle;
    private String schoolName ;
    private Integer teacherType;
    private Integer teacherDataSource;
    private String modifierId;
    private String modifierName;
    private String modifyTime;
}

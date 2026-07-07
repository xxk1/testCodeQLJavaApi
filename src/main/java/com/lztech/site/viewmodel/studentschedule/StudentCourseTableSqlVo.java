package com.lztech.site.viewmodel.studentschedule;

import lombok.Data;

@Data
public class StudentCourseTableSqlVo {

    private String courseTableId;

    private String courseId;

    private String courseName;

    private String collegeId;

    private String collegeName;


    private String groupName;

    private String groupId;

    private Integer courseCategoryId;

    private String courseCategoryName;

    private Integer weekType;
}

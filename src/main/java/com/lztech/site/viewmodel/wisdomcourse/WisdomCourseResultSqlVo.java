package com.lztech.site.viewmodel.wisdomcourse;

import lombok.Data;

@Data
public class WisdomCourseResultSqlVo {

    private String wid;

    private String courseId;

    private String courseName;

    private String courseCode;

    private String courseTeacherTeamNames;

    private String createTime;

    private String courseVersionId;

    private Integer completedTaskNum;

    private Integer totalTaskNum;
}

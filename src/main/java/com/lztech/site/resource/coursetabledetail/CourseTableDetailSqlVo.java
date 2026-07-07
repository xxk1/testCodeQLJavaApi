package com.lztech.site.resource.coursetabledetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseTableDetailSqlVo {
    public String id;

    private String courseId;

    private String courseName;

    private String courseDate;

    private String segmentStartTime;

    private String segmentEndTime;

    private String roomCodes;

    private String roomNames;

}

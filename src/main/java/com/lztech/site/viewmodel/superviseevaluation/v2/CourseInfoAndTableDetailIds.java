package com.lztech.site.viewmodel.superviseevaluation.v2;

import lombok.Data;

@Data
public class CourseInfoAndTableDetailIds {
    private Integer studentType;
    private String courseId;
    private String courseName;
    private String teachers;
    private String courseTableDetailIds;
    private String sourceDataSource;
    private String sourceDataSourceName;
}

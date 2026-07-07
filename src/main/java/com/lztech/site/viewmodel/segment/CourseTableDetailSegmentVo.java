package com.lztech.site.viewmodel.segment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
public class CourseTableDetailSegmentVo {
    private String roomId;
    private String roomName;
    private String schoolYear;
    private Integer term;
    private String groupId;
    private String groupName;
    private BigInteger studentCount;
    private String week;
    private Integer weekNum;
    private String courseId;
    private String courseName;
    private String courseTableId;
    private String courseTableDetailId;
    private String buildId;
    private String buildName;
    private Integer segment;
    private String teachers;
    private String collegeId;
    private String collegeName;
    private Integer studentType;
    private String courseCode;
    private String sourceDataSource;
    private String sourceDataSourceName;
}

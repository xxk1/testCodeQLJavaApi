package com.lztech.site.viewmodel.segment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class SegmentCourseSqlVo {

    /**
     * 节次
     */
    private Integer segment;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 课程编号
     */
    private String courseCode;
    /**
     * 课程名称
     */
    private String courseName;

    private Integer courseCategoryId;

    private String courseCategoryName;

    private String teacherNames;

    private String groupId;

    private String groupNo;

    private String groupName;

    private String collegeId;

    private String collegeName;

    private BigInteger groupMemberCounts;

}

package com.lztech.site.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 学生课程查询结果 DTO
 * 用于接收 Native Query 查询结果，避免加载不必要的关联实体
 */
@Data
public class StudentCourseQueryDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 课程表ID
     */
    private String courseTableId;
    
    /**
     * 课程ID
     */
    private String courseId;
    
    /**
     * 课程名称（冗余字段，避免 JOIN course 表）
     */
    private String courseName;
    
    /**
     * 课程编号
     */
    private String courseCode;
    
    /**
     * 学院名称（冗余字段，避免 JOIN college 表）
     */
    private String collegeName;
    
    /**
     * 课程类别ID
     */
    private Integer courseCategoryId;
    
    /**
     * 课程类别名称
     */
    private String courseCategoryName;
    
    /**
     * 班级/组ID
     */
    private String groupId;
    
    /**
     * 班级/组名称
     */
    private String groupName;
    
    /**
     * 班级编号
     */
    private String groupNo;
    
    /**
     * 班级排序
     */
    private Integer groupSort;
    
    /**
     * 班级来源
     */
    private Integer groupSource;
    
    /**
     * 班级人数（通过补充查询填充）
     */
    private Integer memberCount;
}

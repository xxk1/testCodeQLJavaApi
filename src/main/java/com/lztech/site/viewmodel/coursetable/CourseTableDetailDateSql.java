package com.lztech.site.viewmodel.coursetable;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CourseTableDetailDateSql {

    /**
     * 课表id
     */
    private String courseTableDetailId;
    /**
     * 上课班级
     */
    private String className;
    /**
     * 学院id
     */
    private String collegeId;
    /**
     * 学院名称
     */
    private String collegeName;
    /**
     * 0:课标班 1:辅导班
     */
    private BigInteger courseAttr;
    /**
     * 课程种类id
     */
    private Integer courseCategory;
    /**
     * 课程种类名称
     */
    private String courseCategoryName;

    /**
     * 上课时间
     */
    private String courseDate;

    /**
     * 课程id
     */
    private String courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课表id
     */
    private String courseTableId;

    /**
     * 课程类型 0:必修 1:选修 2: 公选 3:指选 4:国际课 5:双学位 6:其他
     */
    private Integer courseType;
    /**
     * 课程类型 0:必修 1:选修 2: 公选 3:指选 4:国际课 5:双学位 6:其他
     */
    private String courseTypeName;
    /**
     * 当前周次
     */
    private String nowWeek;

    /**
     * 项目名称(多个逗号拼接)
     */
    private String projectNames;
    /**
     * 教室id
     */
    private String roomId;
    /**
     * 教室名称
     */
    private String roomName;
    /**
     * 节次列表
     */
    private String segments;

    /**
     * 课表开始时间
     */
    private String segmentStartTime;

    /**
     * 课表结束时间
     */
    private String segmentEndTime;

    /**
     * 学生总数
     */
    private BigInteger studentCount;
    /**
     * 老师id
     */
    private String teacherId;
    /**
     * 老师名称
     */
    private String teacherName;
    /**
     * 教学班id
     */
    private String teachingClassId;
    /**
     * 教学班名称
     */
    private String teachingClassName;
    /**
     * 周几
     */
    private Integer weekNum;
    /**
     * 单双周
     */
    private Integer weekType;
    /**
     * 小节次
     */
    private Integer segment;
    /**
     * 小节次开始时间
     */
    private String startTime;
    /**
     * 小节次结束时间
     */
    private String endTime;

}

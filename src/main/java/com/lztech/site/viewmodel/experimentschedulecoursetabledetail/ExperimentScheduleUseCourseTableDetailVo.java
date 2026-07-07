package com.lztech.site.viewmodel.experimentschedulecoursetabledetail;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentScheduleUseCourseTableDetailVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-12T16:34:51.679Z")
public class ExperimentScheduleUseCourseTableDetailVo {


    private String week;


    private String courseName;


    private String groupName;

    private String teacherNames;

    private String courseDate;

    private String weekNum;

    private String segment;

    private String roomName;

    public ExperimentScheduleUseCourseTableDetailVo week(String week) {
        this.week = week;
        return this;
    }
    /**
     * 周次
     *
     * @return week
     **/
    @ApiModelProperty(value = "周次",position = 1, required = true)
    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
    public ExperimentScheduleUseCourseTableDetailVo courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }
    /**
     * 课程名称
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称",position = 2, required = true)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public ExperimentScheduleUseCourseTableDetailVo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
    /**
     * 班级名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "班级名称",position = 3, required = true)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public ExperimentScheduleUseCourseTableDetailVo teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }
    /**
     * 老师名称(多个,拼接)
     *
     * @return teacherNames
     **/
    @ApiModelProperty(value = "老师名称(多个,拼接)",position = 4, required = true)
    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }
    public ExperimentScheduleUseCourseTableDetailVo courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }
    /**
     * 课表日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "课表日期",position = 5, required = true)
    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }
    public ExperimentScheduleUseCourseTableDetailVo weekNum(String weekNum) {
        this.weekNum = weekNum;
        return this;
    }
    /**
     * 星期
     *
     * @return weekNum
     **/
    @ApiModelProperty(value = "星期",position = 6, required = true)
    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public ExperimentScheduleUseCourseTableDetailVo segment(String segment) {
        this.segment = segment;
        return this;
    }
    /**
     * 节次(多个,拼接)
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次(多个,拼接)",position = 7, required = true)
    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public ExperimentScheduleUseCourseTableDetailVo roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }
    /**
     * 教室名称
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "教室名称",position = 8, required = true)
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentScheduleUseCourseTableDetailVo experimentScheduleUseCourseTableDetailVo = (ExperimentScheduleUseCourseTableDetailVo) o;
        return Objects.equals(this.week, experimentScheduleUseCourseTableDetailVo.week) &&
                Objects.equals(this.courseName, experimentScheduleUseCourseTableDetailVo.courseName) &&
                Objects.equals(this.groupName, experimentScheduleUseCourseTableDetailVo.groupName) &&
                Objects.equals(this.teacherNames, experimentScheduleUseCourseTableDetailVo.teacherNames) &&
                Objects.equals(this.courseDate, experimentScheduleUseCourseTableDetailVo.courseDate) &&
                Objects.equals(this.weekNum, experimentScheduleUseCourseTableDetailVo.weekNum) &&
                Objects.equals(this.segment, experimentScheduleUseCourseTableDetailVo.segment) &&
                Objects.equals(this.roomName, experimentScheduleUseCourseTableDetailVo.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(week, courseName, groupName, teacherNames, courseDate, weekNum,segment,roomName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentScheduleUseCourseTableDetailVo {\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }


}

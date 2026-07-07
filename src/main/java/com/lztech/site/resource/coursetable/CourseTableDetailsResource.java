package com.lztech.site.resource.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-18T07:59:30.969Z")

public class CourseTableDetailsResource {
    @JsonProperty("courseType")
    private Integer courseType = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseAttr")
    private Integer courseAttr = null;

    @JsonProperty("courseAttrName")
    private String courseAttrName = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("studentNum")
    private Integer studentNum = null;

    @JsonProperty("weekType")
    private String weekType = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("weekDay")
    private String weekDay = null;

    @JsonProperty("segment")
    private String segment = null;

    public CourseTableDetailsResource courseType(Integer courseType) {
        this.courseType = courseType;
        return this;
    }

    /**
     * 课程类型
     *
     * @return courseType
     **/
    @ApiModelProperty(value = "课程类型 ")


    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public CourseTableDetailsResource courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseTableDetailsResource courseAttr(Integer courseAttr) {
        this.courseAttr = courseAttr;
        return this;
    }

    /**
     * 课程类别 0:必修 1:选修 2:公选 3:指选 4:国际课 5:双学位 6：其他
     *
     * @return courseAttr
     **/
    @ApiModelProperty(value = "课程类别 0:必修 1:选修 2:公选 3:指选 4:国际课 5:双学位 6：其他")


    public Integer getCourseAttr() {
        return courseAttr;
    }

    public void setCourseAttr(Integer courseAttr) {
        this.courseAttr = courseAttr;
    }

    public CourseTableDetailsResource courseAttrName(String courseAttrName) {
        this.courseAttrName = courseAttrName;
        return this;
    }

    /**
     * 课程类型名称
     *
     * @return courseAttrName
     **/
    @ApiModelProperty(value = "课程类型名称")


    public String getCourseAttrName() {
        return courseAttrName;
    }

    public void setCourseAttrName(String courseAttrName) {
        this.courseAttrName = courseAttrName;
    }

    public CourseTableDetailsResource teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 教室姓名
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "教室姓名")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseTableDetailsResource className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 班级名称
     *
     * @return className
     **/
    @ApiModelProperty(value = "班级名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CourseTableDetailsResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public CourseTableDetailsResource roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 教室名称
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "教室名称")


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public CourseTableDetailsResource studentNum(Integer studentNum) {
        this.studentNum = studentNum;
        return this;
    }

    /**
     * 学生人数
     *
     * @return studentNum
     **/
    @ApiModelProperty(value = "学生人数")


    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public CourseTableDetailsResource weekType(String weekType) {
        this.weekType = weekType;
        return this;
    }

    /**
     * 周类型（单双周）
     *
     * @return weekType
     **/
    @ApiModelProperty(value = "周类型（单双周）")


    public String getWeekType() {
        return weekType;
    }

    public void setWeekType(String weekType) {
        this.weekType = weekType;
    }

    public CourseTableDetailsResource courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public CourseTableDetailsResource week(String week) {
        this.week = week;
        return this;
    }

    /**
     * 周次
     *
     * @return week
     **/
    @ApiModelProperty(value = "周次")


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public CourseTableDetailsResource weekDay(String weekDay) {
        this.weekDay = weekDay;
        return this;
    }

    /**
     * 星期几
     *
     * @return weekDay
     **/
    @ApiModelProperty(value = "星期几")


    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public CourseTableDetailsResource segment(String segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次")


    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailsResource courseTableDetailsResource = (CourseTableDetailsResource) o;
        return Objects.equals(this.courseType, courseTableDetailsResource.courseType) &&
                Objects.equals(this.courseName, courseTableDetailsResource.courseName) &&
                Objects.equals(this.courseAttr, courseTableDetailsResource.courseAttr) &&
                Objects.equals(this.courseAttrName, courseTableDetailsResource.courseAttrName) &&
                Objects.equals(this.teacherName, courseTableDetailsResource.teacherName) &&
                Objects.equals(this.className, courseTableDetailsResource.className) &&
                Objects.equals(this.collegeName, courseTableDetailsResource.collegeName) &&
                Objects.equals(this.roomName, courseTableDetailsResource.roomName) &&
                Objects.equals(this.studentNum, courseTableDetailsResource.studentNum) &&
                Objects.equals(this.weekType, courseTableDetailsResource.weekType) &&
                Objects.equals(this.courseDate, courseTableDetailsResource.courseDate) &&
                Objects.equals(this.week, courseTableDetailsResource.week) &&
                Objects.equals(this.weekDay, courseTableDetailsResource.weekDay) &&
                Objects.equals(this.segment, courseTableDetailsResource.segment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseType, courseName, courseAttr, courseAttrName, teacherName, className, collegeName,
                roomName, studentNum, weekType, courseDate, week, weekDay, segment);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailsResource {\n");

        sb.append("    courseType: ").append(toIndentedString(courseType)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseAttr: ").append(toIndentedString(courseAttr)).append("\n");
        sb.append("    courseAttrName: ").append(toIndentedString(courseAttrName)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    studentNum: ").append(toIndentedString(studentNum)).append("\n");
        sb.append("    weekType: ").append(toIndentedString(weekType)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    weekDay: ").append(toIndentedString(weekDay)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
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


package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-02-22T05:44:45.129Z")

public class CourseTableDetailResource {
    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("buildName")
    private String buildName = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("weekNum")
    private Integer weekNum = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("courseTypeName")
    private String courseTypeName = null;

    public CourseTableDetailResource teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 教师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "教师工号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public CourseTableDetailResource roomName(String roomName) {
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

    public CourseTableDetailResource buildName(String buildName) {
        this.buildName = buildName;
        return this;
    }

    /**
     * 楼栋名称
     *
     * @return buildName
     **/
    @ApiModelProperty(value = "楼栋名称")


    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public CourseTableDetailResource courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 课程日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "课程日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public CourseTableDetailResource week(String week) {
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

    public CourseTableDetailResource weekNum(Integer weekNum) {
        this.weekNum = weekNum;
        return this;
    }

    /**
     * 周几
     *
     * @return weekNum
     **/
    @ApiModelProperty(value = "周几")


    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public CourseTableDetailResource segments(String segments) {
        this.segments = segments;
        return this;
    }

    /**
     * 节次
     *
     * @return segments
     **/
    @ApiModelProperty(value = "节次")


    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }

    public CourseTableDetailResource courseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
        return this;
    }

    /**
     * 课程类型
     *
     * @return courseTypeName
     **/
    @ApiModelProperty(value = "课程类型")


    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailResource courseTableDetailResource = (CourseTableDetailResource) o;
        return Objects.equals(this.teacherNo, courseTableDetailResource.teacherNo) &&
                Objects.equals(this.roomName, courseTableDetailResource.roomName) &&
                Objects.equals(this.buildName, courseTableDetailResource.buildName) &&
                Objects.equals(this.courseDate, courseTableDetailResource.courseDate) &&
                Objects.equals(this.week, courseTableDetailResource.week) &&
                Objects.equals(this.weekNum, courseTableDetailResource.weekNum) &&
                Objects.equals(this.segments, courseTableDetailResource.segments) &&
                Objects.equals(this.courseTypeName, courseTableDetailResource.courseTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherNo, roomName, buildName, courseDate, week, weekNum, segments, courseTypeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailResouce {\n");

        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    buildName: ").append(toIndentedString(buildName)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
        sb.append("    courseTypeName: ").append(toIndentedString(courseTypeName)).append("\n");
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


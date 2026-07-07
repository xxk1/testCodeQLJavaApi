package com.lztech.site.viewmodel.studentcoursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StudentCourseTableDetailBaseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-04-29T14:36:17.223+08:00")

public class StudentCourseTableDetailBaseResource {
    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;


    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public StudentCourseTableDetailBaseResource courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表详情Id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表详情Id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public StudentCourseTableDetailBaseResource courseName(String courseName) {
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

    public StudentCourseTableDetailBaseResource roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室Id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "教室Id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public StudentCourseTableDetailBaseResource roomName(String roomName) {
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

    public StudentCourseTableDetailBaseResource courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课日期
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课日期")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public StudentCourseTableDetailBaseResource segments(String segments) {
        this.segments = segments;
        return this;
    }

    /**
     * 上课节次
     *
     * @return segments
     **/
    @ApiModelProperty(value = "上课节次")


    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }

    public StudentCourseTableDetailBaseResource segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 上课开始时间
     *
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "上课开始时间")


    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public StudentCourseTableDetailBaseResource segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 上课结束时间
     *
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "上课结束时间")


    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentCourseTableDetailBaseResource studentCourseTableDetailBaseResource = (StudentCourseTableDetailBaseResource) o;
        return Objects.equals(this.courseTableDetailId, studentCourseTableDetailBaseResource.courseTableDetailId) &&
                Objects.equals(this.courseName, studentCourseTableDetailBaseResource.courseName) &&
                Objects.equals(this.roomId, studentCourseTableDetailBaseResource.roomId) &&
                Objects.equals(this.roomName, studentCourseTableDetailBaseResource.roomName) &&
                Objects.equals(this.courseDate, studentCourseTableDetailBaseResource.courseDate) &&
                Objects.equals(this.segments, studentCourseTableDetailBaseResource.segments) &&
                Objects.equals(this.segmentStartTime, studentCourseTableDetailBaseResource.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, studentCourseTableDetailBaseResource.segmentEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId, courseName, roomId, roomName, courseDate, segments, segmentStartTime, segmentEndTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentCourseTableDetailBaseResource {\n");

        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
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


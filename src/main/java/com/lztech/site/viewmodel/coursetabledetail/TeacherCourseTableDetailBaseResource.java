package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherCourseTableDetailBaseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-04-02T19:33:25.650+08:00")

public class TeacherCourseTableDetailBaseResource {
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

    public TeacherCourseTableDetailBaseResource courseTableDetailId(String courseTableDetailId) {
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

    public TeacherCourseTableDetailBaseResource courseName(String courseName) {
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

    public TeacherCourseTableDetailBaseResource roomId(String roomId) {
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

    public TeacherCourseTableDetailBaseResource roomName(String roomName) {
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

    public TeacherCourseTableDetailBaseResource courseDate(String courseDate) {
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

    public TeacherCourseTableDetailBaseResource segments(String segments) {
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

    public TeacherCourseTableDetailBaseResource segmentStartTime(String segmentStartTime) {
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

    public TeacherCourseTableDetailBaseResource segmentEndTime(String segmentEndTime) {
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
        TeacherCourseTableDetailBaseResource teacherCourseTableDetailBaseResource = (TeacherCourseTableDetailBaseResource) o;
        return Objects.equals(this.courseTableDetailId, teacherCourseTableDetailBaseResource.courseTableDetailId) &&
                Objects.equals(this.courseName, teacherCourseTableDetailBaseResource.courseName) &&
                Objects.equals(this.roomId, teacherCourseTableDetailBaseResource.roomId) &&
                Objects.equals(this.roomName, teacherCourseTableDetailBaseResource.roomName) &&
                Objects.equals(this.courseDate, teacherCourseTableDetailBaseResource.courseDate) &&
                Objects.equals(this.segments, teacherCourseTableDetailBaseResource.segments) &&
                Objects.equals(this.segmentStartTime, teacherCourseTableDetailBaseResource.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, teacherCourseTableDetailBaseResource.segmentEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId, courseName, roomId, roomName, courseDate, segments, segmentStartTime, segmentEndTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherCourseTableDetailBaseResource {\n");

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


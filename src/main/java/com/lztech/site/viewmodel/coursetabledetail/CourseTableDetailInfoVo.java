package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-14T17:57:13.951+08:00")

public class CourseTableDetailInfoVo {
    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("weekNum")
    private Integer weekNum = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("studentCount")
    private String studentCount = null;

    @JsonProperty("teacherIds")
    private String teacherIds = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    @JsonProperty("teacherNos")
    private String teacherNos = null;

    public CourseTableDetailInfoVo courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表明细id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表明细id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public CourseTableDetailInfoVo courseDate(String courseDate) {
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

    public CourseTableDetailInfoVo week(String week) {
        this.week = week;
        return this;
    }

    /**
     * 第几周
     *
     * @return week
     **/
    @ApiModelProperty(value = "第几周")


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public CourseTableDetailInfoVo weekNum(Integer weekNum) {
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

    public CourseTableDetailInfoVo segment(String segment) {
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

    public CourseTableDetailInfoVo segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 节次开始时间
     *
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "节次开始时间")


    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public CourseTableDetailInfoVo segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 节次结束时间
     *
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "节次结束时间")


    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }

    public CourseTableDetailInfoVo roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "教室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public CourseTableDetailInfoVo roomName(String roomName) {
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

    public CourseTableDetailInfoVo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseTableDetailInfoVo courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编码
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编码")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public CourseTableDetailInfoVo courseName(String courseName) {
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

    public CourseTableDetailInfoVo studentCount(String studentCount) {
        this.studentCount = studentCount;
        return this;
    }

    /**
     * 学生数量
     *
     * @return studentCount
     **/
    @ApiModelProperty(value = "学生数量")


    public String getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(String studentCount) {
        this.studentCount = studentCount;
    }

    public CourseTableDetailInfoVo teacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
        return this;
    }

    /**
     * 课表明细老师id(多个时','分割)
     *
     * @return teacherIds
     **/
    @ApiModelProperty(value = "课表明细老师id(多个时','分割)")


    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public CourseTableDetailInfoVo teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 课表明细老师名称(多个时','分割)
     *
     * @return teacherNames
     **/
    @ApiModelProperty(value = "课表明细老师名称(多个时','分割)")


    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    public CourseTableDetailInfoVo teacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
        return this;
    }

    /**
     * 课表明细老师工号(多个时','分割)
     *
     * @return teacherNos
     **/
    @ApiModelProperty(value = "课表明细老师工号(多个时','分割)")


    public String getTeacherNos() {
        return teacherNos;
    }

    public void setTeacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailInfoVo courseTableDetailInfoVo = (CourseTableDetailInfoVo) o;
        return Objects.equals(this.courseTableDetailId, courseTableDetailInfoVo.courseTableDetailId) &&
                Objects.equals(this.courseDate, courseTableDetailInfoVo.courseDate) &&
                Objects.equals(this.week, courseTableDetailInfoVo.week) &&
                Objects.equals(this.weekNum, courseTableDetailInfoVo.weekNum) &&
                Objects.equals(this.segment, courseTableDetailInfoVo.segment) &&
                Objects.equals(this.segmentStartTime, courseTableDetailInfoVo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, courseTableDetailInfoVo.segmentEndTime) &&
                Objects.equals(this.roomId, courseTableDetailInfoVo.roomId) &&
                Objects.equals(this.roomName, courseTableDetailInfoVo.roomName) &&
                Objects.equals(this.courseId, courseTableDetailInfoVo.courseId) &&
                Objects.equals(this.courseCode, courseTableDetailInfoVo.courseCode) &&
                Objects.equals(this.courseName, courseTableDetailInfoVo.courseName) &&
                Objects.equals(this.studentCount, courseTableDetailInfoVo.studentCount) &&
                Objects.equals(this.teacherIds, courseTableDetailInfoVo.teacherIds) &&
                Objects.equals(this.teacherNames, courseTableDetailInfoVo.teacherNames) &&
                Objects.equals(this.teacherNos, courseTableDetailInfoVo.teacherNos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId, courseDate, week, weekNum, segment, segmentStartTime, segmentEndTime,
                roomId, roomName, courseId, courseCode, courseName, studentCount, teacherIds, teacherNames, teacherNos);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailInfoVo {\n");

        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
        sb.append("    teacherIds: ").append(toIndentedString(teacherIds)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    teacherNos: ").append(toIndentedString(teacherNos)).append("\n");
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


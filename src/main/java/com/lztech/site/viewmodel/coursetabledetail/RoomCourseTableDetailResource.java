package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.Objects;

/**
 * RoomCourseTableDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-02T11:09:50.065Z")


public class RoomCourseTableDetailResource {
    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    @JsonProperty("teacherNos")
    private String teacherNos = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("studentNum")
    private BigInteger studentNum = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    public RoomCourseTableDetailResource courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public RoomCourseTableDetailResource teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 上课教师名称
     * @return teacherNames
     **/
    @ApiModelProperty(value = "上课教师名称")


    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    public RoomCourseTableDetailResource teacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
        return this;
    }

    /**
     * 上课教师工号
     * @return teacherNos
     **/
    @ApiModelProperty(value = "上课教师工号")


    public String getTeacherNos() {
        return teacherNos;
    }

    public void setTeacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
    }


    public RoomCourseTableDetailResource groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 班级名称
     * @return groupName
     **/
    @ApiModelProperty(value = "班级名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public RoomCourseTableDetailResource segments(String segments) {
        this.segments = segments;
        return this;
    }

    /**
     * 节次信息
     * @return segments
     **/
    @ApiModelProperty(value = "节次信息")


    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }

    public RoomCourseTableDetailResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 开课院系名称
     * @return collegeName
     **/
    @ApiModelProperty(value = "开课院系名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public RoomCourseTableDetailResource studentNum(BigInteger studentNum) {
        this.studentNum = studentNum;
        return this;
    }

    /**
     * 选课学生人数
     * @return studentNum
     **/
    @ApiModelProperty(value = "选课学生人数")


    public BigInteger getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(BigInteger studentNum) {
        this.studentNum = studentNum;
    }

    public RoomCourseTableDetailResource segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 节次开始时间
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "节次开始时间")


    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public RoomCourseTableDetailResource segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 节次结束时间
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "节次结束时间")


    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }

    public RoomCourseTableDetailResource roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 教室id
     * @return roomId
     **/
    @ApiModelProperty(value = "教室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public RoomCourseTableDetailResource roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 教室名称
     * @return roomName
     **/
    @ApiModelProperty(value = "教室名称")


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
        RoomCourseTableDetailResource roomCourseTableDetailResource = (RoomCourseTableDetailResource) o;
        return Objects.equals(this.courseName, roomCourseTableDetailResource.courseName) &&
                Objects.equals(this.teacherNames, roomCourseTableDetailResource.teacherNames) &&
                Objects.equals(this.teacherNos, roomCourseTableDetailResource.teacherNos) &&
                Objects.equals(this.groupName, roomCourseTableDetailResource.groupName) &&
                Objects.equals(this.segments, roomCourseTableDetailResource.segments) &&
                Objects.equals(this.collegeName, roomCourseTableDetailResource.collegeName) &&
                Objects.equals(this.studentNum, roomCourseTableDetailResource.studentNum) &&
                Objects.equals(this.segmentStartTime, roomCourseTableDetailResource.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, roomCourseTableDetailResource.segmentEndTime) &&
                Objects.equals(this.roomId, roomCourseTableDetailResource.roomId) &&
                Objects.equals(this.roomName, roomCourseTableDetailResource.roomName)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, teacherNames,teacherNos, groupName, segments, collegeName,
                studentNum, segmentStartTime, segmentEndTime,roomId,roomName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RoomCourseTableDetailResource {\n");

        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    teacherNos: ").append(toIndentedString(teacherNos)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    studentNum: ").append(toIndentedString(studentNum)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
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


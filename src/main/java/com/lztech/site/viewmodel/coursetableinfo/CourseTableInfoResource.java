package com.lztech.site.viewmodel.coursetableinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableInfoResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-18T12:21:18.712Z")


public class CourseTableInfoResource {
    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("startTime")
    private String startTime = null;

    @JsonProperty("endTime")
    private String endTime = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("roomId")
    private String roomId = null;

    @JsonProperty("roomName")
    private String roomName = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("segmentList")
    @Valid
    private List<CourseTableInfoResourceSegmentList> segmentList = null;

    public CourseTableInfoResource courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 主课表id
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "主课表id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public CourseTableInfoResource courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表详情id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表详情id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public CourseTableInfoResource startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * 课表开始时间 格式：HH:mm
     *
     * @return startTime
     **/
    @ApiModelProperty(value = "课表开始时间 格式：HH:mm")


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public CourseTableInfoResource endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * 课表结束时间 格式：HH:mm
     *
     * @return endTime
     **/
    @ApiModelProperty(value = "课表结束时间 格式：HH:mm")


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public CourseTableInfoResource teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 上课老师id,多个老师使用“,” 隔开
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "上课老师id,多个老师使用“,” 隔开")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CourseTableInfoResource teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 上课老师名称,多个老师使用“,” 隔开
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "上课老师名称,多个老师使用“,” 隔开")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseTableInfoResource courseDate(String courseDate) {
        this.courseDate = courseDate;
        return this;
    }

    /**
     * 上课时间 格式：yyyy-MM-dd
     *
     * @return courseDate
     **/
    @ApiModelProperty(value = "上课时间 格式：yyyy-MM-dd")


    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public CourseTableInfoResource roomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * 上课教室id
     *
     * @return roomId
     **/
    @ApiModelProperty(value = "上课教室id")


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public CourseTableInfoResource roomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    /**
     * 上课教室名称
     *
     * @return roomName
     **/
    @ApiModelProperty(value = "上课教室名称")


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public CourseTableInfoResource courseName(String courseName) {
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

    public CourseTableInfoResource courseId(String courseId) {
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

    public CourseTableInfoResource segment(String segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次(多个“,”隔开)
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次(多个“,”隔开)")


    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public CourseTableInfoResource className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 上课班级
     *
     * @return className
     **/
    @ApiModelProperty(value = "上课班级")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CourseTableInfoResource segmentList(List<CourseTableInfoResourceSegmentList> segmentList) {
        this.segmentList = segmentList;
        return this;
    }

    public CourseTableInfoResource addSegmentListItem(CourseTableInfoResourceSegmentList segmentListItem) {
        if (this.segmentList == null) {
            this.segmentList = new ArrayList<CourseTableInfoResourceSegmentList>();
        }
        this.segmentList.add(segmentListItem);
        return this;
    }

    /**
     * 节次列表
     *
     * @return segmentList
     **/
    @ApiModelProperty(value = "节次列表")

    @Valid

    public List<CourseTableInfoResourceSegmentList> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<CourseTableInfoResourceSegmentList> segmentList) {
        this.segmentList = segmentList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableInfoResource courseTableInfoResource = (CourseTableInfoResource) o;
        return Objects.equals(this.courseTableId, courseTableInfoResource.courseTableId) &&
                Objects.equals(this.courseTableDetailId, courseTableInfoResource.courseTableDetailId) &&
                Objects.equals(this.startTime, courseTableInfoResource.startTime) &&
                Objects.equals(this.endTime, courseTableInfoResource.endTime) &&
                Objects.equals(this.teacherId, courseTableInfoResource.teacherId) &&
                Objects.equals(this.teacherName, courseTableInfoResource.teacherName) &&
                Objects.equals(this.courseDate, courseTableInfoResource.courseDate) &&
                Objects.equals(this.roomId, courseTableInfoResource.roomId) &&
                Objects.equals(this.roomName, courseTableInfoResource.roomName) &&
                Objects.equals(this.courseName, courseTableInfoResource.courseName) &&
                Objects.equals(this.courseId, courseTableInfoResource.courseId) &&
                Objects.equals(this.segment, courseTableInfoResource.segment) &&
                Objects.equals(this.className, courseTableInfoResource.className) &&
                Objects.equals(this.segmentList, courseTableInfoResource.segmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableId, courseTableDetailId, startTime, endTime,
                teacherId, teacherName, courseDate, roomId, roomName, courseName,
                courseId, segment, className, segmentList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableInfoResource {\n");

        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    segmentList: ").append(toIndentedString(segmentList)).append("\n");
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


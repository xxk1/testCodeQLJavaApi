package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-19T07:56:11.253Z")


public class CourseTableDetailResourceVo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("roomIds")
    private String roomIds = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("teacherResourceList")
    @Valid
    private List<TeacherResourceVo> teacherResourceList = null;

    @JsonProperty("studentResourceList")
    @Valid
    private List<StudentResourceVo> studentResourceList = null;

    public CourseTableDetailResourceVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 课表id
     *
     * @return id
     **/
    @ApiModelProperty(value = "课表id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseTableDetailResourceVo courseDate(String courseDate) {
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

    public CourseTableDetailResourceVo roomIds(String roomIds) {
        this.roomIds = roomIds;
        return this;
    }

    /**
     * 教室ids,多个用“，”隔开
     *
     * @return roomIds
     **/
    @ApiModelProperty(value = "教室ids,多个用“，”隔开")


    public String getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(String roomIds) {
        this.roomIds = roomIds;
    }

    public CourseTableDetailResourceVo segments(String segments) {
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

    public CourseTableDetailResourceVo segmentStartTime(String segmentStartTime) {
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

    public CourseTableDetailResourceVo segmentEndTime(String segmentEndTime) {
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

    public CourseTableDetailResourceVo teacherResourceList(List<TeacherResourceVo> teacherResourceList) {
        this.teacherResourceList = teacherResourceList;
        return this;
    }

    public CourseTableDetailResourceVo addTeacherResourceListItem(TeacherResourceVo teacherResourceListItem) {
        if (this.teacherResourceList == null) {
            this.teacherResourceList = new ArrayList<TeacherResourceVo>();
        }
        this.teacherResourceList.add(teacherResourceListItem);
        return this;
    }

    /**
     * Get teacherResourceList
     *
     * @return teacherResourceList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<TeacherResourceVo> getTeacherResourceList() {
        return teacherResourceList;
    }

    public void setTeacherResourceList(List<TeacherResourceVo> teacherResourceList) {
        this.teacherResourceList = teacherResourceList;
    }

    public CourseTableDetailResourceVo studentResourceList(List<StudentResourceVo> studentResourceList) {
        this.studentResourceList = studentResourceList;
        return this;
    }

    public CourseTableDetailResourceVo addStudentResourceListItem(StudentResourceVo studentResourceListItem) {
        if (this.studentResourceList == null) {
            this.studentResourceList = new ArrayList<StudentResourceVo>();
        }
        this.studentResourceList.add(studentResourceListItem);
        return this;
    }

    /**
     * Get studentResourceList
     *
     * @return studentResourceList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<StudentResourceVo> getStudentResourceList() {
        return studentResourceList;
    }

    public void setStudentResourceList(List<StudentResourceVo> studentResourceList) {
        this.studentResourceList = studentResourceList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailResourceVo courseTableDetailResourceVo = (CourseTableDetailResourceVo) o;
        return Objects.equals(this.id, courseTableDetailResourceVo.id) &&
                Objects.equals(this.courseDate, courseTableDetailResourceVo.courseDate) &&
                Objects.equals(this.roomIds, courseTableDetailResourceVo.roomIds) &&
                Objects.equals(this.segments, courseTableDetailResourceVo.segments) &&
                Objects.equals(this.segmentStartTime, courseTableDetailResourceVo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, courseTableDetailResourceVo.segmentEndTime) &&
                Objects.equals(this.teacherResourceList, courseTableDetailResourceVo.teacherResourceList) &&
                Objects.equals(this.studentResourceList, courseTableDetailResourceVo.studentResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseDate, roomIds, segments, segmentStartTime, segmentEndTime, teacherResourceList, studentResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    roomIds: ").append(toIndentedString(roomIds)).append("\n");
        sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    teacherResourceList: ").append(toIndentedString(teacherResourceList)).append("\n");
        sb.append("    studentResourceList: ").append(toIndentedString(studentResourceList)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


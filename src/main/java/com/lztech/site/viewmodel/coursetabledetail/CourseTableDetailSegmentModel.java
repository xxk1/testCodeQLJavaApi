package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailSegmentModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-10-19T11:19:28.452Z")


public class CourseTableDetailSegmentModel {
    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("courseDate")
    private String courseDate = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("segments")
    private String segments = null;

    @JsonProperty("roomId")
    private String roomId = null;

    public CourseTableDetailSegmentModel courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public CourseTableDetailSegmentModel courseDate(String courseDate) {
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

    public CourseTableDetailSegmentModel courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程Id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程Id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseTableDetailSegmentModel courseName(String courseName) {
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

    public CourseTableDetailSegmentModel teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CourseTableDetailSegmentModel teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 老师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "老师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseTableDetailSegmentModel segments(String segments) {
        this.segments = segments;
        return this;
    }

    /**
     * 节次(多个逗号拼接)
     *
     * @return segments
     **/
    @ApiModelProperty(value = "节次(多个逗号拼接)")


    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }


    public CourseTableDetailSegmentModel roomId(String roomId) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailSegmentModel courseTableDetailSegmentModel = (CourseTableDetailSegmentModel) o;
        return Objects.equals(this.courseTableDetailId, courseTableDetailSegmentModel.courseTableDetailId) &&
                Objects.equals(this.courseDate, courseTableDetailSegmentModel.courseDate) &&
                Objects.equals(this.courseId, courseTableDetailSegmentModel.courseId) &&
                Objects.equals(this.courseName, courseTableDetailSegmentModel.courseName) &&
                Objects.equals(this.teacherId, courseTableDetailSegmentModel.teacherId) &&
                Objects.equals(this.teacherName, courseTableDetailSegmentModel.teacherName) &&
                Objects.equals(this.segments, courseTableDetailSegmentModel.segments) &&
                Objects.equals(this.roomId,courseTableDetailSegmentModel.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId, courseDate, courseId,
                courseName, teacherId, teacherName, segments,roomId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailSegmentModel {\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
        sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
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


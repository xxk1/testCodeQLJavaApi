package com.lztech.site.resource.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailInfoModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-23T02:08:42.086Z")


public class CourseTableDetailInfoModel {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("roomCodes")
    private String roomCodes = null;

    @JsonProperty("roomNames")
    private String roomNames = null;

    public CourseTableDetailInfoModel courseId(String courseId) {
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

    public CourseTableDetailInfoModel courseName(String courseName) {
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

    public CourseTableDetailInfoModel segmentStartTime(String segmentStartTime) {
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

    public CourseTableDetailInfoModel segmentEndTime(String segmentEndTime) {
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

    public CourseTableDetailInfoModel roomCodes(String roomCodes) {
        this.roomCodes = roomCodes;
        return this;
    }

    /**
     * 教室编号（‘、’拼接）
     *
     * @return roomCodes
     **/
    @ApiModelProperty(value = "教室编号（‘、’拼接）")


    public String getRoomCodes() {
        return roomCodes;
    }

    public void setRoomCodes(String roomCodes) {
        this.roomCodes = roomCodes;
    }

    public CourseTableDetailInfoModel roomNames(String roomNames) {
        this.roomNames = roomNames;
        return this;
    }

    /**
     * 教室名称（‘、’拼接）
     *
     * @return roomNames
     **/
    @ApiModelProperty(value = "教室名称（‘、’拼接）")


    public String getRoomNames() {
        return roomNames;
    }

    public void setRoomNames(String roomNames) {
        this.roomNames = roomNames;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailInfoModel courseTableDetailInfoModel = (CourseTableDetailInfoModel) o;
        return Objects.equals(this.courseId, courseTableDetailInfoModel.courseId) &&
                Objects.equals(this.courseName, courseTableDetailInfoModel.courseName) &&
                Objects.equals(this.segmentStartTime, courseTableDetailInfoModel.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, courseTableDetailInfoModel.segmentEndTime) &&
                Objects.equals(this.roomCodes, courseTableDetailInfoModel.roomCodes) &&
                Objects.equals(this.roomNames, courseTableDetailInfoModel.roomNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, segmentStartTime, segmentEndTime, roomCodes, roomNames);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailInfoModel {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    roomCodes: ").append(toIndentedString(roomCodes)).append("\n");
        sb.append("    roomNames: ").append(toIndentedString(roomNames)).append("\n");
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


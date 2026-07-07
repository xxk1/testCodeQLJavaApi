package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * RoomSegmentUsageModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-08-04T02:09:37.197Z")


public class RoomSegmentUsageModel {
    @JsonProperty("segment")
    private Integer segment = null;

    @JsonProperty("usageIndex")
    private Integer usageIndex = null;

    @JsonProperty("usageName")
    private String usageName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("teacherIds")
    private String teacherIds = null;

    @JsonProperty("teacherNos")
    private String teacherNos = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    public RoomSegmentUsageModel segment(Integer segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次")


    public Integer getSegment() {
        return segment;
    }

    public void setSegment(Integer segment) {
        this.segment = segment;
    }

    public RoomSegmentUsageModel usageIndex(Integer usageIndex) {
        this.usageIndex = usageIndex;
        return this;
    }

    /**
     * 使用情况编号(0:自习;1:上课)
     *
     * @return usageIndex
     **/
    @ApiModelProperty(value = "使用情况编号(0:自习;1:上课)")


    public Integer getUsageIndex() {
        return usageIndex;
    }

    public void setUsageIndex(Integer usageIndex) {
        this.usageIndex = usageIndex;
    }

    public RoomSegmentUsageModel usageName(String usageName) {
        this.usageName = usageName;
        return this;
    }

    /**
     * 使用情况名称(0:自习;1:上课)
     *
     * @return usageName
     **/
    @ApiModelProperty(value = "使用情况名称(0:自习;1:上课)")


    public String getUsageName() {
        return usageName;
    }

    public void setUsageName(String usageName) {
        this.usageName = usageName;
    }

    public RoomSegmentUsageModel courseId(String courseId) {
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

    public RoomSegmentUsageModel courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public RoomSegmentUsageModel courseName(String courseName) {
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

    public RoomSegmentUsageModel teacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
        return this;
    }

    /**
     * 上课老师Id(多个,拼接)
     *
     * @return teacherIds
     **/
    @ApiModelProperty(value = "上课老师Id(多个,拼接)")


    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public RoomSegmentUsageModel teacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
        return this;
    }

    /**
     * 上课老师工号(多个,拼接)
     *
     * @return teacherNos
     **/
    @ApiModelProperty(value = "上课老师工号(多个,拼接)")


    public String getTeacherNos() {
        return teacherNos;
    }

    public void setTeacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
    }

    public RoomSegmentUsageModel teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 上课老师名称(多个,拼接)
     *
     * @return teacherNames
     **/
    @ApiModelProperty(value = "上课老师名称(多个,拼接)")


    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomSegmentUsageModel roomSegmentUsageModel = (RoomSegmentUsageModel) o;
        return Objects.equals(this.segment, roomSegmentUsageModel.segment) &&
                Objects.equals(this.usageIndex, roomSegmentUsageModel.usageIndex) &&
                Objects.equals(this.usageName, roomSegmentUsageModel.usageName) &&
                Objects.equals(this.courseId, roomSegmentUsageModel.courseId) &&
                Objects.equals(this.courseCode, roomSegmentUsageModel.courseCode) &&
                Objects.equals(this.courseName, roomSegmentUsageModel.courseName) &&
                Objects.equals(this.teacherIds, roomSegmentUsageModel.teacherIds) &&
                Objects.equals(this.teacherNos, roomSegmentUsageModel.teacherNos) &&
                Objects.equals(this.teacherNames, roomSegmentUsageModel.teacherNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segment, usageIndex, usageName, courseId, courseCode, courseName, teacherIds, teacherNos, teacherNames);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RoomSegmentUsageModel {\n");

        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    usageIndex: ").append(toIndentedString(usageIndex)).append("\n");
        sb.append("    usageName: ").append(toIndentedString(usageName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    teacherIds: ").append(toIndentedString(teacherIds)).append("\n");
        sb.append("    teacherNos: ").append(toIndentedString(teacherNos)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
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


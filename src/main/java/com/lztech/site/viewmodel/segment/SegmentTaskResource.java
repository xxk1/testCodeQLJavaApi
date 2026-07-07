package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SegmentTaskResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T12:28:18.338Z")

public class SegmentTaskResource {
    @JsonProperty("buildId")
    private String buildId = null;

    @JsonProperty("buildName")
    private String buildName = null;

    @JsonProperty("segment")
    private Integer segment = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("segmentRecordStartTime")
    private String segmentRecordStartTime = null;

    @JsonProperty("segmentRecordEndTime")
    private String segmentRecordEndTime = null;

    @JsonProperty("week")
    private String week = null;

    @JsonProperty("weekNum")
    private Integer weekNum = null;

    @JsonProperty("courseTableDetailSegments")
    @Valid
    private List<CourseTableDetailSegment> courseTableDetailSegments = null;

    public SegmentTaskResource buildId(String buildId) {
        this.buildId = buildId;
        return this;
    }

    /**
     * 楼栋id
     *
     * @return buildId
     **/
    @ApiModelProperty(value = "楼栋id")


    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public SegmentTaskResource buildName(String buildName) {
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

    public SegmentTaskResource segment(Integer segment) {
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

    public SegmentTaskResource segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 节次开始时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "节次开始时间（yyyy-MM-dd HH:mm:ss）")


    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public SegmentTaskResource segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 节次节次时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "节次节次时间（yyyy-MM-dd HH:mm:ss）")


    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }


    public SegmentTaskResource segmentRecordStartTime(String segmentRecordStartTime) {
        this.segmentRecordStartTime = segmentRecordStartTime;
        return this;
    }

    /**
     * 节次录制开始时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return segmentRecordStartTime
     **/
    @ApiModelProperty(value = "节次录制开始时间（yyyy-MM-dd HH:mm:ss）")


    public String getSegmentRecordStartTime() {
        return segmentRecordStartTime;
    }

    public void setSegmentRecordStartTime(String segmentRecordStartTime) {
        this.segmentRecordStartTime = segmentRecordStartTime;
    }

    public SegmentTaskResource segmentRecordEndTime(String segmentRecordEndTime) {
        this.segmentRecordEndTime = segmentRecordEndTime;
        return this;
    }

    /**
     * 节次录制结束时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return segmentRecordEndTime
     **/
    @ApiModelProperty(value = "节次录制结束时间（yyyy-MM-dd HH:mm:ss）")


    public String getSegmentRecordEndTime() {
        return segmentRecordEndTime;
    }

    public void setSegmentRecordEndTime(String segmentRecordEndTime) {
        this.segmentRecordEndTime = segmentRecordEndTime;
    }


    public SegmentTaskResource week(String week) {
        this.week = week;
        return this;
    }

    /**
     * 周次（第几周）
     *
     * @return week
     **/
    @ApiModelProperty(value = "周次（第几周）")


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public SegmentTaskResource weekNum(Integer weekNum) {
        this.weekNum = weekNum;
        return this;
    }

    /**
     * 星期几
     *
     * @return weekNum
     **/
    @ApiModelProperty(value = "星期几")


    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public SegmentTaskResource courseTableDetailSegments(List<CourseTableDetailSegment> courseTableDetailSegments) {
        this.courseTableDetailSegments = courseTableDetailSegments;
        return this;
    }

    public SegmentTaskResource addCourseTableDetailSegmentsItem(CourseTableDetailSegment courseTableDetailSegmentsItem) {
        if (this.courseTableDetailSegments == null) {
            this.courseTableDetailSegments = new ArrayList<CourseTableDetailSegment>();
        }
        this.courseTableDetailSegments.add(courseTableDetailSegmentsItem);
        return this;
    }

    /**
     * 该节次下课表节次信息
     *
     * @return courseTableDetailSegments
     **/
    @ApiModelProperty(value = "该节次下课表节次信息")

    @Valid

    public List<CourseTableDetailSegment> getCourseTableDetailSegments() {
        return courseTableDetailSegments;
    }

    public void setCourseTableDetailSegments(List<CourseTableDetailSegment> courseTableDetailSegments) {
        this.courseTableDetailSegments = courseTableDetailSegments;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SegmentTaskResource segmentTaskResource = (SegmentTaskResource) o;
        return Objects.equals(this.buildId, segmentTaskResource.buildId) &&
                Objects.equals(this.buildName, segmentTaskResource.buildName) &&
                Objects.equals(this.segment, segmentTaskResource.segment) &&
                Objects.equals(this.segmentStartTime, segmentTaskResource.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, segmentTaskResource.segmentEndTime) &&
                Objects.equals(this.segmentRecordStartTime, segmentTaskResource.segmentRecordStartTime) &&
                Objects.equals(this.segmentRecordEndTime, segmentTaskResource.segmentRecordEndTime) &&
                Objects.equals(this.week, segmentTaskResource.week) &&
                Objects.equals(this.weekNum, segmentTaskResource.weekNum) &&
                Objects.equals(this.courseTableDetailSegments, segmentTaskResource.courseTableDetailSegments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildId, buildName, segment,
                segmentStartTime, segmentEndTime,
                segmentRecordStartTime, segmentRecordEndTime,
                week, weekNum, courseTableDetailSegments);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SegmentTaskResource {\n");

        sb.append("    buildId: ").append(toIndentedString(buildId)).append("\n");
        sb.append("    buildName: ").append(toIndentedString(buildName)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    segmentRecordStartTime: ").append(toIndentedString(segmentRecordStartTime)).append("\n");
        sb.append("    segmentRecordEndTime: ").append(toIndentedString(segmentRecordEndTime)).append("\n");
        sb.append("    week: ").append(toIndentedString(week)).append("\n");
        sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
        sb.append("    courseTableDetailSegments: ").append(toIndentedString(courseTableDetailSegments)).append("\n");
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


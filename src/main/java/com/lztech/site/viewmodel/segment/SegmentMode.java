package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SegmentMode
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-02-01T02:48:42.193Z")


public class SegmentMode {
    @JsonProperty("segmentName")
    private Integer segmentName = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("courseList")
    @Valid
    private List<CourseBasicVo> courseList = null;

    public SegmentMode segmentName(Integer segmentName) {
        this.segmentName = segmentName;
        return this;
    }

    /**
     * 节次
     *
     * @return segmentName
     **/
    @ApiModelProperty(value = "节次")


    public Integer getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(Integer segmentName) {
        this.segmentName = segmentName;
    }

    public SegmentMode segmentStartTime(String segmentStartTime) {
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

    public SegmentMode segmentEndTime(String segmentEndTime) {
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

    public SegmentMode courseList(List<CourseBasicVo> courseList) {
        this.courseList = courseList;
        return this;
    }

    public SegmentMode addCourseListItem(CourseBasicVo courseListItem) {
        if (this.courseList == null) {
            this.courseList = new ArrayList<CourseBasicVo>();
        }
        this.courseList.add(courseListItem);
        return this;
    }

    /**
     * Get courseList
     *
     * @return courseList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseBasicVo> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseBasicVo> courseList) {
        this.courseList = courseList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SegmentMode segmentMode = (SegmentMode) o;
        return Objects.equals(this.segmentName, segmentMode.segmentName) &&
                Objects.equals(this.segmentStartTime, segmentMode.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, segmentMode.segmentEndTime) &&
                Objects.equals(this.courseList, segmentMode.courseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentName, segmentStartTime, segmentEndTime, courseList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SegmentMode {\n");

        sb.append("    segmentName: ").append(toIndentedString(segmentName)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    courseList: ").append(toIndentedString(courseList)).append("\n");
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


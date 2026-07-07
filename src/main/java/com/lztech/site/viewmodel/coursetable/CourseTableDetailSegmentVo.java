package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-13T03:16:43.514Z")

public class CourseTableDetailSegmentVo {

    @JsonProperty("segment")
    private Integer segment = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;


    public CourseTableDetailSegmentVo segment(Integer courseId) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次
     * @return segment
     **/
    @ApiModelProperty(value = "节次")
    public Integer getSegment() {
        return segment;
    }

    public void setSegment(Integer segment) {
        this.segment = segment;
    }
    public CourseTableDetailSegmentVo segmentStartTime(String segmentStartTime) {
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
    public CourseTableDetailSegmentVo segmentEndTime(String segmentEndTime) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailSegmentVo courseTableDetailSegmentVo = (CourseTableDetailSegmentVo) o;
        return Objects.equals(this.segment, courseTableDetailSegmentVo.segment) &&
                Objects.equals(this.segmentStartTime, courseTableDetailSegmentVo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, courseTableDetailSegmentVo.segmentEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segment,segmentStartTime, segmentEndTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailSegmentVo {\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
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

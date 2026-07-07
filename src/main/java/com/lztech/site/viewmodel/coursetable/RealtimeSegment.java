package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * RealtimeSegment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-10-10T14:19:44.442+08:00")

public class RealtimeSegment {
    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("courseSegmentId")
    private String courseSegmentId = null;

    @JsonProperty("startTime")
    private String startTime = null;

    @JsonProperty("endTime")
    private String endTime = null;

    public RealtimeSegment courseTableDetailId(String courseTableDetailId) {
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

    public RealtimeSegment courseSegmentId(String courseSegmentId) {
        this.courseSegmentId = courseSegmentId;
        return this;
    }

    /**
     * 课表节次id
     *
     * @return courseSegmentId
     **/
    @ApiModelProperty(value = "课表节次id")


    public String getCourseSegmentId() {
        return courseSegmentId;
    }

    public void setCourseSegmentId(String courseSegmentId) {
        this.courseSegmentId = courseSegmentId;
    }

    public RealtimeSegment startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * 开始时间（HH:mm）
     *
     * @return startTime
     **/
    @ApiModelProperty(value = "开始时间（HH:mm）")


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public RealtimeSegment endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * 结束时间（HH:mm）
     *
     * @return endTime
     **/
    @ApiModelProperty(value = "结束时间（HH:mm）")


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RealtimeSegment realtimeSegment = (RealtimeSegment) o;
        return Objects.equals(this.courseTableDetailId, realtimeSegment.courseTableDetailId) &&
                Objects.equals(this.courseSegmentId, realtimeSegment.courseSegmentId) &&
                Objects.equals(this.startTime, realtimeSegment.startTime) &&
                Objects.equals(this.endTime, realtimeSegment.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId, courseSegmentId, startTime, endTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RealtimeSegment {\n");

        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    courseSegmentId: ").append(toIndentedString(courseSegmentId)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
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


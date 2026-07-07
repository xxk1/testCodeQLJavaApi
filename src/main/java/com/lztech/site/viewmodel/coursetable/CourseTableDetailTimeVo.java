package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailTimeVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-03T08:34:51.679Z")

public class CourseTableDetailTimeVo {
    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;
    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;
    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;
    public CourseTableDetailTimeVo courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表明细id
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表明细id")

    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }
    public CourseTableDetailTimeVo segmentStartTime(String segmentStartTime) {
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
    public CourseTableDetailTimeVo segmentEndTime(String segmentEndTime) {
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
        CourseTableDetailTimeVo courseTableDetailTimeVo = (CourseTableDetailTimeVo) o;
        return Objects.equals(this.courseTableDetailId,courseTableDetailTimeVo.courseTableDetailId) &&
                Objects.equals(this.segmentStartTime,courseTableDetailTimeVo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime,courseTableDetailTimeVo.segmentEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId,segmentStartTime,segmentEndTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailTimeVo {\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
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

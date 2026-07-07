package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SegmentVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-19T08:44:16.470Z")


public class SegmentVo {
    @JsonProperty("segmentName")
    private String segmentName = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    public SegmentVo segmentName(String segmentName) {
        this.segmentName = segmentName;
        return this;
    }

    /**
     * 节次
     *
     * @return segmentName
     **/
    @ApiModelProperty(value = "节次")


    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public SegmentVo segmentStartTime(String segmentStartTime) {
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

    public SegmentVo segmentEndTime(String segmentEndTime) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SegmentVo segmentVo = (SegmentVo) o;
        return Objects.equals(this.segmentName, segmentVo.segmentName) &&
                Objects.equals(this.segmentStartTime, segmentVo.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, segmentVo.segmentEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentName, segmentStartTime, segmentEndTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SegmentVo {\n");

        sb.append("    segmentName: ").append(toIndentedString(segmentName)).append("\n");
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


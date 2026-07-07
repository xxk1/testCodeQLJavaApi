package com.lztech.site.resource.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SegmentTimeResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-17T05:39:03.987Z")

public class SegmentTimeResource {
    @JsonProperty("startDate")
    private String startDate = null;

    @JsonProperty("endDate")
    private String endDate = null;

    @JsonProperty("segmentTime")
    private String segmentTime = null;

    public SegmentTimeResource startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * 开始时间 yyyy-mm-dd
     *
     * @return startDate
     **/
    @ApiModelProperty(value = "开始时间 yyyy-mm-dd")


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public SegmentTimeResource endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * 结束时间 yyyy-mm-dd
     *
     * @return endDate
     **/
    @ApiModelProperty(value = "结束时间 yyyy-mm-dd")


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public SegmentTimeResource segmentTime(String segmentTime) {
        this.segmentTime = segmentTime;
        return this;
    }

    /**
     * 节次时间
     *
     * @return segmentTime
     **/
    @ApiModelProperty(value = "节次时间")


    public String getSegmentTime() {
        return segmentTime;
    }

    public void setSegmentTime(String segmentTime) {
        this.segmentTime = segmentTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SegmentTimeResource segmentTimeResource = (SegmentTimeResource) o;
        return Objects.equals(this.startDate, segmentTimeResource.startDate) &&
                Objects.equals(this.endDate, segmentTimeResource.endDate) &&
                Objects.equals(this.segmentTime, segmentTimeResource.segmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, segmentTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SegmentTimeResource {\n");

        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    segmentTime: ").append(toIndentedString(segmentTime)).append("\n");
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


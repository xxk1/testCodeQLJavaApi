package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SegmentDataResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-02-26T08:16:30.572Z")


public class SegmentDataResource {
    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("counts")
    private String counts = null;

    @JsonProperty("roomIdCount")
    private String roomIdCount = null;

    @JsonProperty("repetitions")
    private String repetitions = null;

    public SegmentDataResource segment(String segment) {
        this.segment = segment;
        return this;
    }

    /**
     * 节次
     *
     * @return segment
     **/
    @ApiModelProperty(value = "节次")


    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public SegmentDataResource counts(String counts) {
        this.counts = counts;
        return this;
    }

    /**
     * 课表数
     *
     * @return counts
     **/
    @ApiModelProperty(value = "课表数")


    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public SegmentDataResource roomIdCount(String roomIdCount) {
        this.roomIdCount = roomIdCount;
        return this;
    }

    /**
     * 教室个数
     *
     * @return roomIdCount
     **/
    @ApiModelProperty(value = "教室个数")


    public String getRoomIdCount() {
        return roomIdCount;
    }

    public void setRoomIdCount(String roomIdCount) {
        this.roomIdCount = roomIdCount;
    }

    public SegmentDataResource repetitions(String repetitions) {
        this.repetitions = repetitions;
        return this;
    }

    /**
     * 重复课表
     *
     * @return repetitions
     **/
    @ApiModelProperty(value = "重复课表")


    public String getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(String repetitions) {
        this.repetitions = repetitions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SegmentDataResource segmentDataResource = (SegmentDataResource) o;
        return Objects.equals(this.segment, segmentDataResource.segment) &&
                Objects.equals(this.counts, segmentDataResource.counts) &&
                Objects.equals(this.roomIdCount, segmentDataResource.roomIdCount) &&
                Objects.equals(this.repetitions, segmentDataResource.repetitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segment, counts, roomIdCount, repetitions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SegmentDataResource {\n");

        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    counts: ").append(toIndentedString(counts)).append("\n");
        sb.append("    roomIdCount: ").append(toIndentedString(roomIdCount)).append("\n");
        sb.append("    repetitions: ").append(toIndentedString(repetitions)).append("\n");
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


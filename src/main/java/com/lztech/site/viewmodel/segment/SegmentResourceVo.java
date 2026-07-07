package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SegmentResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-09-19T13:02:48.290Z")


public class SegmentResourceVo {
    @JsonProperty("buildId")
    private String buildId = null;

    @JsonProperty("buildName")
    private String buildName = null;

    @JsonProperty("segment")
    private String segment = null;

    @JsonProperty("startTime")
    private String startTime = null;

    @JsonProperty("endTime")
    private String endTime = null;

    @JsonProperty("recordStartTime")
    private String recordStartTime = null;

    @JsonProperty("recordEndTime")
    private String recordEndTime = null;

    @JsonProperty("description")
    private String description = null;

    public SegmentResourceVo buildId(String buildId) {
        this.buildId = buildId;
        return this;
    }

    /**
     * 楼栋
     *
     * @return buildId
     **/
    @ApiModelProperty(value = "楼栋")


    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public SegmentResourceVo buildName(String buildName) {
        this.buildName = buildName;
        return this;
    }

    /**
     * 楼栋
     *
     * @return buildName
     **/
    @ApiModelProperty(value = "楼栋")


    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public SegmentResourceVo segment(String segment) {
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

    public SegmentResourceVo startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * 开始时间
     *
     * @return startTime
     **/
    @ApiModelProperty(value = "开始时间")


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public SegmentResourceVo endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * 结束时间
     *
     * @return endTime
     **/
    @ApiModelProperty(value = "结束时间")


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public SegmentResourceVo recordStartTime(String recordStartTime) {
        this.recordStartTime = recordStartTime;
        return this;
    }

    /**
     * 录制开始时间
     *
     * @return recordStartTime
     **/
    @ApiModelProperty(value = "录制开始时间")


    public String getRecordStartTime() {
        return recordStartTime;
    }

    public void setRecordStartTime(String recordStartTime) {
        this.recordStartTime = recordStartTime;
    }

    public SegmentResourceVo recordEndTime(String recordEndTime) {
        this.recordEndTime = recordEndTime;
        return this;
    }

    /**
     * 录制结束时间
     *
     * @return recordEndTime
     **/
    @ApiModelProperty(value = "录制结束时间")


    public String getRecordEndTime() {
        return recordEndTime;
    }

    public void setRecordEndTime(String recordEndTime) {
        this.recordEndTime = recordEndTime;
    }

    public SegmentResourceVo description(String description) {
        this.description = description;
        return this;
    }

    /**
     * 描述
     *
     * @return description
     **/
    @ApiModelProperty(value = "描述")


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SegmentResourceVo segmentResourceVo = (SegmentResourceVo) o;
        return Objects.equals(this.buildId, segmentResourceVo.buildId) &&
                Objects.equals(this.buildName, segmentResourceVo.buildName) &&
                Objects.equals(this.segment, segmentResourceVo.segment) &&
                Objects.equals(this.startTime, segmentResourceVo.startTime) &&
                Objects.equals(this.endTime, segmentResourceVo.endTime) &&
                Objects.equals(this.recordStartTime, segmentResourceVo.recordStartTime) &&
                Objects.equals(this.recordEndTime, segmentResourceVo.recordEndTime) &&
                Objects.equals(this.description, segmentResourceVo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildId, buildName, segment, startTime, endTime, recordStartTime, recordEndTime, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SegmentResourceVo {\n");

        sb.append("    buildId: ").append(toIndentedString(buildId)).append("\n");
        sb.append("    buildName: ").append(toIndentedString(buildName)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
        sb.append("    recordStartTime: ").append(toIndentedString(recordStartTime)).append("\n");
        sb.append("    recordEndTime: ").append(toIndentedString(recordEndTime)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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


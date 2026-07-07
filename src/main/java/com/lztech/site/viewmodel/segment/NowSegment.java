package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * NowSegment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-14T07:53:59.818Z")


public class NowSegment {
    @JsonProperty("buildId")
    private String buildId = null;

    @JsonProperty("buildName")
    private String buildName = null;

    @JsonProperty("segment")
    private Integer segment = null;

    @JsonProperty("startTime")
    private String startTime = null;

    @JsonProperty("endTime")
    private String endTime = null;

    public NowSegment buildId(String buildId) {
        this.buildId = buildId;
        return this;
    }

    /**
     * 楼栋Id
     *
     * @return buildId
     **/
    @ApiModelProperty(value = "楼栋Id")


    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public NowSegment buildName(String buildName) {
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

    public NowSegment segment(Integer segment) {
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

    public NowSegment startTime(String startTime) {
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

    public NowSegment endTime(String endTime) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NowSegment nowSegment = (NowSegment) o;
        return Objects.equals(this.buildId, nowSegment.buildId) &&
                Objects.equals(this.buildName, nowSegment.buildName) &&
                Objects.equals(this.segment, nowSegment.segment) &&
                Objects.equals(this.startTime, nowSegment.startTime) &&
                Objects.equals(this.endTime, nowSegment.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildId, buildName, segment, startTime, endTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NowSegment {\n");

        sb.append("    buildId: ").append(toIndentedString(buildId)).append("\n");
        sb.append("    buildName: ").append(toIndentedString(buildName)).append("\n");
        sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
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


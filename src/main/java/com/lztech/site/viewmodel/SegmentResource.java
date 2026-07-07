package com.lztech.site.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import java.util.Objects;

/**
 * SegmentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-08T02:36:10.542Z")

public class SegmentResource   {
  @JsonProperty("segment")
  private String segment = null;

  @JsonProperty("buildName")
  private String buildName = null;

  @JsonProperty("startTime")
  private String startTime = null;

  @JsonProperty("endTime")
  private String endTime = null;

  @JsonProperty("description")
  private String description = null;

  public SegmentResource segment(String segment) {
    this.segment = segment;
    return this;
  }

  /**
   * 节次
   * @return segment
  **/
  @ApiModelProperty(value = "节次")


  public String getSegment() {
    return segment;
  }

  public void setSegment(String segment) {
    this.segment = segment;
  }

  public SegmentResource buildName(String buildName) {
    this.buildName = buildName;
    return this;
  }

  /**
   * 楼栋
   * @return buildName
  **/
  @ApiModelProperty(value = "楼栋")


  public String getBuildName() {
    return buildName;
  }

  public void setBuildName(String buildName) {
    this.buildName = buildName;
  }

  public SegmentResource startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(value = "")


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public SegmentResource endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

  /**
   * Get endTime
   * @return endTime
  **/
  @ApiModelProperty(value = "")


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public SegmentResource description(String description) {
    this.description = description;
    return this;
  }

  /**
   * 描述
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SegmentResource segmentResource = (SegmentResource) o;
    return Objects.equals(this.segment, segmentResource.segment) &&
        Objects.equals(this.buildName, segmentResource.buildName) &&
        Objects.equals(this.startTime, segmentResource.startTime) &&
        Objects.equals(this.endTime, segmentResource.endTime) &&
        Objects.equals(this.description, segmentResource.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(segment, buildName, startTime, endTime, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SegmentResource {\n");
    
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    buildName: ").append(toIndentedString(buildName)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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


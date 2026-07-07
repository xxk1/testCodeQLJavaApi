package com.lztech.site.viewmodel.coursetable;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseTableDateSegmentList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T06:59:17.529Z")

public class CourseTableDateSegmentList   {
  @JsonProperty("segment")
  private Integer segment = null;

  @JsonProperty("startTime")
  private String startTime = null;

  @JsonProperty("endTime")
  private String endTime = null;

  public CourseTableDateSegmentList segment(Integer segment) {
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

  public CourseTableDateSegmentList startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * 开始时间
   * @return startTime
  **/
  @ApiModelProperty(value = "开始时间")


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public CourseTableDateSegmentList endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

  /**
   * 结束时间
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
    CourseTableDateSegmentList courseTableDateSegmentList = (CourseTableDateSegmentList) o;
    return Objects.equals(this.segment, courseTableDateSegmentList.segment) &&
        Objects.equals(this.startTime, courseTableDateSegmentList.startTime) &&
        Objects.equals(this.endTime, courseTableDateSegmentList.endTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(segment, startTime, endTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTableDateSegmentList {\n");
    
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


package com.lztech.site.viewmodel.segment;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseDate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T01:55:42.708Z")

public class CourseDate   {
  @JsonProperty("segmentTime")
  private String segmentTime = null;

  public CourseDate segmentTime(String segmentTime) {
    this.segmentTime = segmentTime;
    return this;
  }

  /**
   * 返回课程时间
   * @return segmentTime
  **/
  @ApiModelProperty(value = "返回课程时间")


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
    CourseDate courseDate = (CourseDate) o;
    return Objects.equals(this.segmentTime, courseDate.segmentTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(segmentTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseDate {\n");
    
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


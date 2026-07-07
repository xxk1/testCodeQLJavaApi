package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableIdAndNameInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-02-22T06:31:20.839Z")


public class CourseTableIdAndNameInfo   {
  @JsonProperty("courseTableId")
  private String courseTableId = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  public CourseTableIdAndNameInfo courseTableId(String courseTableId) {
    this.courseTableId = courseTableId;
    return this;
  }

  /**
   * id
   * @return courseTableId
  **/
  @ApiModelProperty(value = "id")


  public String getCourseTableId() {
    return courseTableId;
  }

  public void setCourseTableId(String courseTableId) {
    this.courseTableId = courseTableId;
  }

  public CourseTableIdAndNameInfo collegeName(String collegeName) {
    this.collegeName = collegeName;
    return this;
  }

  /**
   * 开课学院名称
   * @return collegeName
  **/
  @ApiModelProperty(value = "开课学院名称")


  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTableIdAndNameInfo courseTableIdAndNameInfo = (CourseTableIdAndNameInfo) o;
    return Objects.equals(this.courseTableId, courseTableIdAndNameInfo.courseTableId) &&
        Objects.equals(this.collegeName, courseTableIdAndNameInfo.collegeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseTableId, collegeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTableIdAndNameInfo {\n");
    
    sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
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


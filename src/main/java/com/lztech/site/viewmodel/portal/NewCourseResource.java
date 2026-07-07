package com.lztech.site.viewmodel.portal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * NewCourseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-07-04T06:28:37.038Z")


public class NewCourseResource {
  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("courseLeaderName")
  private String courseLeaderName = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("courseCompletion")
  private BigDecimal courseCompletion = null;

  public NewCourseResource courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 课程id
   * @return courseId
  **/
  @ApiModelProperty(value = "课程id")


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public NewCourseResource courseName(String courseName) {
    this.courseName = courseName;
    return this;
  }

  /**
   * 课程名称
   * @return courseName
  **/
  @ApiModelProperty(value = "课程名称")


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public NewCourseResource courseLeaderName(String courseLeaderName) {
    this.courseLeaderName = courseLeaderName;
    return this;
  }

  /**
   * 课程负责人名称
   * @return courseLeaderName
  **/
  @ApiModelProperty(value = "课程负责人名称")


  public String getCourseLeaderName() {
    return courseLeaderName;
  }

  public void setCourseLeaderName(String courseLeaderName) {
    this.courseLeaderName = courseLeaderName;
  }

  public NewCourseResource collegeName(String collegeName) {
    this.collegeName = collegeName;
    return this;
  }

  /**
   * 课程所属学院
   * @return collegeName
  **/
  @ApiModelProperty(value = "课程所属学院")


  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public NewCourseResource courseCompletion(BigDecimal courseCompletion) {
    this.courseCompletion = courseCompletion;
    return this;
  }

  /**
   * 课程完成度
   * @return courseCompletion
  **/
  @ApiModelProperty(value = "课程完成度")

  @Valid

  public BigDecimal getCourseCompletion() {
    return courseCompletion;
  }

  public void setCourseCompletion(BigDecimal courseCompletion) {
    this.courseCompletion = courseCompletion;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewCourseResource newCourseResource = (NewCourseResource) o;
    return Objects.equals(this.courseId, newCourseResource.courseId) &&
        Objects.equals(this.courseName, newCourseResource.courseName) &&
        Objects.equals(this.courseLeaderName, newCourseResource.courseLeaderName) &&
        Objects.equals(this.collegeName, newCourseResource.collegeName) &&
        Objects.equals(this.courseCompletion, newCourseResource.courseCompletion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, courseName, courseLeaderName, collegeName, courseCompletion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewCourseResource {\n");
    
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    courseLeaderName: ").append(toIndentedString(courseLeaderName)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    courseCompletion: ").append(toIndentedString(courseCompletion)).append("\n");
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


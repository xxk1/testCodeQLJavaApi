package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * LiveCourseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-08-18T11:45:13.687Z")


public class LiveCourseResource   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("hasLiveBroadcast")
  private Integer hasLiveBroadcast = null;

  public LiveCourseResource id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 课程id
   * @return id
  **/
  @ApiModelProperty(value = "课程id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LiveCourseResource courseCode(String courseCode) {
    this.courseCode = courseCode;
    return this;
  }

  /**
   * 课程编号
   * @return courseCode
  **/
  @ApiModelProperty(value = "课程编号")


  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public LiveCourseResource courseName(String courseName) {
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

  public LiveCourseResource hasLiveBroadcast(Integer  hasLiveBroadcast) {
    this.hasLiveBroadcast = hasLiveBroadcast;
    return this;
  }

  public Integer getHasLiveBroadcast() {
    return hasLiveBroadcast;
  }

  public void setHasLiveBroadcast(Integer hasLiveBroadcast) {
    this.hasLiveBroadcast = hasLiveBroadcast;
  }

  /**
   * 是否有直播(0:没有;1:有)
   * @return hasLiveBroadcast
  **/
  @ApiModelProperty(value = "是否有直播(0:没有;1:有)")




  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LiveCourseResource liveCourseResource = (LiveCourseResource) o;
    return Objects.equals(this.id, liveCourseResource.id) &&
        Objects.equals(this.courseCode, liveCourseResource.courseCode) &&
        Objects.equals(this.courseName, liveCourseResource.courseName) &&
        Objects.equals(this.hasLiveBroadcast, liveCourseResource.hasLiveBroadcast);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, courseCode, courseName, hasLiveBroadcast);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LiveCourseResource {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    hasLiveBroadcast: ").append(toIndentedString(hasLiveBroadcast)).append("\n");
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


package com.lztech.site.viewmodel.portal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingCourseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-07-07T06:46:31.197Z")


public class TeachingCourseResource {
  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("teacherNames")
  private String teacherNames = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("groupNames")
  private String groupNames = null;

  public TeachingCourseResource courseId(String courseId) {
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

  public TeachingCourseResource courseName(String courseName) {
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

  public TeachingCourseResource teacherNames(String teacherNames) {
    this.teacherNames = teacherNames;
    return this;
  }

  /**
   * 教师名称
   * @return teacherNames
  **/
  @ApiModelProperty(value = "教师名称")


  public String getTeacherNames() {
    return teacherNames;
  }

  public void setTeacherNames(String teacherNames) {
    this.teacherNames = teacherNames;
  }

  public TeachingCourseResource collegeName(String collegeName) {
    this.collegeName = collegeName;
    return this;
  }

  /**
   * 学院名称
   * @return collegeName
  **/
  @ApiModelProperty(value = "学院名称")


  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public TeachingCourseResource groupNames(String groupNames) {
    this.groupNames = groupNames;
    return this;
  }

  /**
   * 班级名称
   * @return groupNames
  **/
  @ApiModelProperty(value = "班级名称")


  public String getGroupNames() {
    return groupNames;
  }

  public void setGroupNames(String groupNames) {
    this.groupNames = groupNames;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeachingCourseResource teachingCourseResource = (TeachingCourseResource) o;
    return Objects.equals(this.courseId, teachingCourseResource.courseId) &&
        Objects.equals(this.courseName, teachingCourseResource.courseName) &&
        Objects.equals(this.teacherNames, teachingCourseResource.teacherNames) &&
        Objects.equals(this.collegeName, teachingCourseResource.collegeName) &&
        Objects.equals(this.groupNames, teachingCourseResource.groupNames);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, courseName, teacherNames, collegeName, groupNames);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeachingCourseResource {\n");
    
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    groupNames: ").append(toIndentedString(groupNames)).append("\n");
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


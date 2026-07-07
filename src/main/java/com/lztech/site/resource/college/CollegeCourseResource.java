package com.lztech.site.resource.college;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CollegeCourseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-25T09:03:22.585Z")


public class CollegeCourseResource   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("teacherId")
  private String teacherId = null;

  @JsonProperty("teacherName")
  private String teacherName = null;

  public CollegeCourseResource id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 学院id
   * @return id
   **/
  @ApiModelProperty(value = "学院id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CollegeCourseResource collegeName(String collegeName) {
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

  public CollegeCourseResource courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 课程Id
   * @return courseId
   **/
  @ApiModelProperty(value = "课程Id")


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public CollegeCourseResource courseName(String courseName) {
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

  public CollegeCourseResource courseCode(String courseCode) {
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

  public CollegeCourseResource teacherId(String teacherId) {
    this.teacherId = teacherId;
    return this;
  }

  /**
   * 老师ID
   * @return teacherId
   **/
  @ApiModelProperty(value = "老师ID")


  public String getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
  }

  public CollegeCourseResource teacherName(String teacherName) {
    this.teacherName = teacherName;
    return this;
  }

  /**
   * 老师名称
   * @return teacherName
   **/
  @ApiModelProperty(value = "老师名称")


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollegeCourseResource collegeCourseResource = (CollegeCourseResource) o;
    return Objects.equals(this.id, collegeCourseResource.id) &&
            Objects.equals(this.collegeName, collegeCourseResource.collegeName) &&
            Objects.equals(this.courseId, collegeCourseResource.courseId) &&
            Objects.equals(this.courseName, collegeCourseResource.courseName) &&
            Objects.equals(this.courseCode, collegeCourseResource.courseCode) &&
            Objects.equals(this.teacherId, collegeCourseResource.teacherId) &&
            Objects.equals(this.teacherName, collegeCourseResource.teacherName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, collegeName, courseId, courseName, courseCode, teacherId, teacherName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollegeCourseResource {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
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


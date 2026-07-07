package com.lztech.site.resource.courseteacher;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseTeacherNewVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-11-11T08:21:05.240Z")


public class CourseTeacherNewVo   {
  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("courseCategoryId")
  private Integer courseCategoryId = null;

  @JsonProperty("courseCategoryName")
  private String courseCategoryName = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("courseType")
  private Integer courseType = null;

  @JsonProperty("courseSource")
  private Integer courseSource = null;

  public CourseTeacherNewVo courseId(String courseId) {
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

  public CourseTeacherNewVo courseName(String courseName) {
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

  public CourseTeacherNewVo courseCategoryId(Integer courseCategoryId) {
    this.courseCategoryId = courseCategoryId;
    return this;
  }

  /**
   * 课程类别Id
   * @return courseCategoryId
  **/
  @ApiModelProperty(value = "课程类别Id")


  public Integer getCourseCategoryId() {
    return courseCategoryId;
  }

  public void setCourseCategoryId(Integer courseCategoryId) {
    this.courseCategoryId = courseCategoryId;
  }

  public CourseTeacherNewVo courseCategoryName(String courseCategoryName) {
    this.courseCategoryName = courseCategoryName;
    return this;
  }

  /**
   * 课程类别名称
   * @return courseCategoryName
  **/
  @ApiModelProperty(value = "课程类别名称")


  public String getCourseCategoryName() {
    return courseCategoryName;
  }

  public void setCourseCategoryName(String courseCategoryName) {
    this.courseCategoryName = courseCategoryName;
  }

  public CourseTeacherNewVo courseCode(String courseCode) {
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

  public CourseTeacherNewVo courseType(Integer courseType) {
    this.courseType = courseType;
    return this;
  }

  /**
   * 课程类型（0：理论课 1：实验课）
   * @return courseType
  **/
  @ApiModelProperty(value = "课程类型（0：理论课 1：实验课）")


  public Integer getCourseType() {
    return courseType;
  }

  public void setCourseType(Integer courseType) {
    this.courseType = courseType;
  }

  public CourseTeacherNewVo courseSource(Integer courseSource) {
    this.courseSource = courseSource;
    return this;
  }

  /**
   * 课程来源（0：数据对接 1：课程管理）
   * @return courseSource
  **/
  @ApiModelProperty(value = "课程来源（0：数据对接 1：课程管理）")


  public Integer getCourseSource() {
    return courseSource;
  }

  public void setCourseSource(Integer courseSource) {
    this.courseSource = courseSource;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTeacherNewVo courseTeacherNewVo = (CourseTeacherNewVo) o;
    return Objects.equals(this.courseId, courseTeacherNewVo.courseId) &&
        Objects.equals(this.courseName, courseTeacherNewVo.courseName) &&
        Objects.equals(this.courseCategoryId, courseTeacherNewVo.courseCategoryId) &&
        Objects.equals(this.courseCategoryName, courseTeacherNewVo.courseCategoryName) &&
        Objects.equals(this.courseCode, courseTeacherNewVo.courseCode) &&
        Objects.equals(this.courseType, courseTeacherNewVo.courseType) &&
        Objects.equals(this.courseSource, courseTeacherNewVo.courseSource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, courseName, courseCategoryId, courseCategoryName, courseCode, courseType, courseSource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTeacherNewVo {\n");
    
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
    sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    courseType: ").append(toIndentedString(courseType)).append("\n");
    sb.append("    courseSource: ").append(toIndentedString(courseSource)).append("\n");
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


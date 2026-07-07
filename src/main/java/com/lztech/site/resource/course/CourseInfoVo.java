package com.lztech.site.resource.course;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-12T08:10:44.996Z")


public class CourseInfoVo   {
  @JsonProperty("studentType")
  private Integer studentType = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  public CourseInfoVo studentType(Integer studentType) {
    this.studentType = studentType;
    return this;
  }

  /**
   * 开课类型
   * @return studentType
  **/
  @ApiModelProperty(value = "开课类型")


  public Integer getStudentType() {
    return studentType;
  }

  public void setStudentType(Integer studentType) {
    this.studentType = studentType;
  }

  public CourseInfoVo courseId(String courseId) {
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

  public CourseInfoVo courseName(String courseName) {
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

  public CourseInfoVo courseCode(String courseCode) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseInfoVo courseInfoVo = (CourseInfoVo) o;
    return Objects.equals(this.studentType, courseInfoVo.studentType) &&
        Objects.equals(this.courseId, courseInfoVo.courseId) &&
        Objects.equals(this.courseName, courseInfoVo.courseName) &&
        Objects.equals(this.courseCode, courseInfoVo.courseCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentType, courseId, courseName, courseCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseInfoVo {\n");
    
    sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
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


package com.lztech.site.viewmodel.superviseevaluation.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ListenCourseInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-07-15T07:26:54.115Z")


public class ListenCourseInfoVo   {
  @JsonProperty("studentType")
  private String studentType = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("teachers")
  private String teachers = null;

  @JsonProperty("collegeType")
  private Integer collegeType = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  public ListenCourseInfoVo studentType(String studentType) {
    this.studentType = studentType;
    return this;
  }

  /**
   * 开课类型
   * @return studentType
  **/
  @ApiModelProperty(value = "开课类型")


  public String getStudentType() {
    return studentType;
  }

  public void setStudentType(String studentType) {
    this.studentType = studentType;
  }

  public ListenCourseInfoVo courseId(String courseId) {
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

  public ListenCourseInfoVo courseName(String courseName) {
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

  public ListenCourseInfoVo teachers(String teachers) {
    this.teachers = teachers;
    return this;
  }

  /**
   * 老师信息（id,工号,姓名，多个;分割）
   * @return teachers
  **/
  @ApiModelProperty(value = "老师信息（id,工号,姓名，多个;分割）")


  public String getTeachers() {
    return teachers;
  }

  public void setTeachers(String teachers) {
    this.teachers = teachers;
  }

  public ListenCourseInfoVo collegeType(Integer collegeType) {
    this.collegeType = collegeType;
    return this;
  }

  /**
   * 学院类型（0：授课教师学院；1：开课学院）
   * @return collegeType
  **/
  @ApiModelProperty(value = "学院类型（0：授课教师学院；1：开课学院）")


  public Integer getCollegeType() {
    return collegeType;
  }

  public void setCollegeType(Integer collegeType) {
    this.collegeType = collegeType;
  }

  public ListenCourseInfoVo collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院id
   * @return collegeId
  **/
  @ApiModelProperty(value = "学院id")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public ListenCourseInfoVo collegeName(String collegeName) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListenCourseInfoVo listenCourseInfoVo = (ListenCourseInfoVo) o;
    return Objects.equals(this.studentType, listenCourseInfoVo.studentType) &&
        Objects.equals(this.courseId, listenCourseInfoVo.courseId) &&
        Objects.equals(this.courseName, listenCourseInfoVo.courseName) &&
        Objects.equals(this.teachers, listenCourseInfoVo.teachers) &&
        Objects.equals(this.collegeType, listenCourseInfoVo.collegeType) &&
        Objects.equals(this.collegeId, listenCourseInfoVo.collegeId) &&
        Objects.equals(this.collegeName, listenCourseInfoVo.collegeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentType, courseId, courseName, teachers, collegeType, collegeId, collegeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListenCourseInfoVo {\n");
    
    sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    teachers: ").append(toIndentedString(teachers)).append("\n");
    sb.append("    collegeType: ").append(toIndentedString(collegeType)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
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


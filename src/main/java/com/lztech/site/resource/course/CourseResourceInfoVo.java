package com.lztech.site.resource.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-05T08:20:18.184Z")


public class CourseResourceInfoVo   {
  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("operatorId")
  private String operatorId = null;

  @JsonProperty("operatorName")
  private String operatorName = null;

  public CourseResourceInfoVo courseCode(String courseCode) {
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

  public CourseResourceInfoVo courseName(String courseName) {
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

  public CourseResourceInfoVo collegeId(String collegeId) {
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

  public CourseResourceInfoVo operatorId(String operatorId) {
    this.operatorId = operatorId;
    return this;
  }

  /**
   * 操作人Id
   * @return operatorId
  **/
  @ApiModelProperty(value = "操作人Id")


  public String getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(String operatorId) {
    this.operatorId = operatorId;
  }

  public CourseResourceInfoVo operatorName(String operatorName) {
    this.operatorName = operatorName;
    return this;
  }

  /**
   * 操作人姓名
   * @return operatorName
  **/
  @ApiModelProperty(value = "操作人姓名")


  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseResourceInfoVo courseResourceInfoVo = (CourseResourceInfoVo) o;
    return Objects.equals(this.courseCode, courseResourceInfoVo.courseCode) &&
        Objects.equals(this.courseName, courseResourceInfoVo.courseName) &&
        Objects.equals(this.collegeId, courseResourceInfoVo.collegeId) &&
        Objects.equals(this.operatorId, courseResourceInfoVo.operatorId) &&
        Objects.equals(this.operatorName, courseResourceInfoVo.operatorName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseCode, courseName, collegeId, operatorId, operatorName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseResourceInfoVo {\n");
    
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
    sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
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


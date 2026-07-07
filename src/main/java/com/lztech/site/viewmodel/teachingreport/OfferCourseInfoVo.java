package com.lztech.site.viewmodel.teachingreport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * OfferCourseInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-12-07T01:58:14.760Z")


public class OfferCourseInfoVo   {
  @JsonProperty("courseNumber")
  private String courseNumber = null;

  @JsonProperty("teacherNumber")
  private String teacherNumber = null;

  @JsonProperty("totalSegmentNumber")
  private String totalSegmentNumber = null;

  @JsonProperty("groupNumber")
  private String groupNumber = null;

  @JsonProperty("studentNumber")
  private String studentNumber = null;

  public OfferCourseInfoVo courseNumber(String courseNumber) {
    this.courseNumber = courseNumber;
    return this;
  }

  /**
   * 开课课程数
   * @return courseNumber
  **/
  @ApiModelProperty(value = "开课课程数")


  public String getCourseNumber() {
    return courseNumber;
  }

  public void setCourseNumber(String courseNumber) {
    this.courseNumber = courseNumber;
  }

  public OfferCourseInfoVo teacherNumber(String teacherNumber) {
    this.teacherNumber = teacherNumber;
    return this;
  }

  /**
   * 授课教师数
   * @return teacherNumber
  **/
  @ApiModelProperty(value = "授课教师数")


  public String getTeacherNumber() {
    return teacherNumber;
  }

  public void setTeacherNumber(String teacherNumber) {
    this.teacherNumber = teacherNumber;
  }

  public OfferCourseInfoVo totalSegmentNumber(String totalSegmentNumber) {
    this.totalSegmentNumber = totalSegmentNumber;
    return this;
  }

  /**
   * 上课总学时
   * @return totalSegmentNumber
  **/
  @ApiModelProperty(value = "上课总学时")


  public String getTotalSegmentNumber() {
    return totalSegmentNumber;
  }

  public void setTotalSegmentNumber(String totalSegmentNumber) {
    this.totalSegmentNumber = totalSegmentNumber;
  }

  public OfferCourseInfoVo groupNumber(String groupNumber) {
    this.groupNumber = groupNumber;
    return this;
  }

  /**
   * 上课班级数
   * @return groupNumber
  **/
  @ApiModelProperty(value = "上课班级数")


  public String getGroupNumber() {
    return groupNumber;
  }

  public void setGroupNumber(String groupNumber) {
    this.groupNumber = groupNumber;
  }

  public OfferCourseInfoVo studentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
    return this;
  }

  /**
   * 上课学生数
   * @return studentNumber
  **/
  @ApiModelProperty(value = "上课学生数")


  public String getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OfferCourseInfoVo offerCourseInfoVo = (OfferCourseInfoVo) o;
    return Objects.equals(this.courseNumber, offerCourseInfoVo.courseNumber) &&
        Objects.equals(this.teacherNumber, offerCourseInfoVo.teacherNumber) &&
        Objects.equals(this.totalSegmentNumber, offerCourseInfoVo.totalSegmentNumber) &&
        Objects.equals(this.groupNumber, offerCourseInfoVo.groupNumber) &&
        Objects.equals(this.studentNumber, offerCourseInfoVo.studentNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseNumber, teacherNumber, totalSegmentNumber, groupNumber, studentNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OfferCourseInfoVo {\n");
    
    sb.append("    courseNumber: ").append(toIndentedString(courseNumber)).append("\n");
    sb.append("    teacherNumber: ").append(toIndentedString(teacherNumber)).append("\n");
    sb.append("    totalSegmentNumber: ").append(toIndentedString(totalSegmentNumber)).append("\n");
    sb.append("    groupNumber: ").append(toIndentedString(groupNumber)).append("\n");
    sb.append("    studentNumber: ").append(toIndentedString(studentNumber)).append("\n");
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


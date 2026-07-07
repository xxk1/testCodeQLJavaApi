package com.lztech.site.resource.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.Objects;

/**
 * CourseInfoResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-06T05:38:57.814Z")


public class CourseInfoResource   {
  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("collegeCode")
  private String collegeCode = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("courseLeaderId")
  private String courseLeaderId = null;

  @JsonProperty("courseLeaderName")
  private String courseLeaderName = null;

  @JsonProperty("courseSource")
  private Integer courseSource = null;

  @JsonProperty("courseClassNum")
  private BigInteger courseClassNum = null;

  @JsonProperty("operatorName")
  private String operatorName = null;

  public CourseInfoResource courseId(String courseId) {
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

  public CourseInfoResource courseName(String courseName) {
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

  public CourseInfoResource collegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
    return this;
  }

  /**
   * 学院编号
   * @return collegeCode
  **/
  @ApiModelProperty(value = "学院编号")


  public String getCollegeCode() {
    return collegeCode;
  }

  public void setCollegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
  }

  public CourseInfoResource courseCode(String courseCode) {
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

  public CourseInfoResource collegeName(String collegeName) {
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

  public CourseInfoResource courseLeaderId(String courseLeaderId) {
    this.courseLeaderId = courseLeaderId;
    return this;
  }

  /**
   * 课程负责人id
   * @return courseLeaderId
  **/
  @ApiModelProperty(value = "课程负责人id")


  public String getCourseLeaderId() {
    return courseLeaderId;
  }

  public void setCourseLeaderId(String courseLeaderId) {
    this.courseLeaderId = courseLeaderId;
  }

  public CourseInfoResource courseLeaderName(String courseLeaderName) {
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

  public CourseInfoResource courseSource(Integer courseSource) {
    this.courseSource = courseSource;
    return this;
  }

  /**
   * 课程来源 0：数据对接；1：课程管理新建
   * @return courseSource
  **/
  @ApiModelProperty(value = "课程来源 0：数据对接；1：课程管理新建")


  public Integer getCourseSource() {
    return courseSource;
  }

  public void setCourseSource(Integer courseSource) {
    this.courseSource = courseSource;
  }

  public CourseInfoResource courseClassNum(BigInteger courseClassNum) {
    this.courseClassNum = courseClassNum;
    return this;
  }

  /**
   * 课程班级数量 biginteger类型
   * @return courseClassNum
  **/
  @ApiModelProperty(value = "课程班级数量 biginteger类型")


  public BigInteger getCourseClassNum() {
    return courseClassNum;
  }

  public void setCourseClassNum(BigInteger courseClassNum) {
    this.courseClassNum = courseClassNum;
  }

  public CourseInfoResource operatorName(String operatorName) {
    this.operatorName = operatorName;
    return this;
  }

  /**
   * 操作人
   * @return operatorName
  **/
  @ApiModelProperty(value = "操作人")


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
    CourseInfoResource courseInfoResource = (CourseInfoResource) o;
    return Objects.equals(this.courseId, courseInfoResource.courseId) &&
        Objects.equals(this.courseName, courseInfoResource.courseName) &&
        Objects.equals(this.collegeCode, courseInfoResource.collegeCode) &&
        Objects.equals(this.courseCode, courseInfoResource.courseCode) &&
        Objects.equals(this.collegeName, courseInfoResource.collegeName) &&
        Objects.equals(this.courseLeaderId, courseInfoResource.courseLeaderId) &&
        Objects.equals(this.courseLeaderName, courseInfoResource.courseLeaderName) &&
        Objects.equals(this.courseSource, courseInfoResource.courseSource) &&
        Objects.equals(this.courseClassNum, courseInfoResource.courseClassNum) &&
        Objects.equals(this.operatorName, courseInfoResource.operatorName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, courseName, collegeCode, courseCode, collegeName,
            courseLeaderId, courseLeaderName, courseSource, courseClassNum, operatorName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseInfoResource {\n");
    
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    courseLeaderId: ").append(toIndentedString(courseLeaderId)).append("\n");
    sb.append("    courseLeaderName: ").append(toIndentedString(courseLeaderName)).append("\n");
    sb.append("    courseSource: ").append(toIndentedString(courseSource)).append("\n");
    sb.append("    courseClassNum: ").append(toIndentedString(courseClassNum)).append("\n");
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


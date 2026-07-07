package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.Objects;

/**
 * OccupancyResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-17T06:13:19.191Z")




public class OccupancyResource   {
  @JsonProperty("groupId")
  private String groupId = null;

  @JsonProperty("className")
  private String className = null;

  @JsonProperty("studentCount")
  private BigInteger studentCount = null;

  @JsonProperty("teacherName")
  private String teacherName = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  public OccupancyResource groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * 班级id
   * @return groupId
  **/
  @ApiModelProperty(value = "班级id")


  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public OccupancyResource className(String className) {
    this.className = className;
    return this;
  }

  /**
   * 班级名称
   * @return className
  **/
  @ApiModelProperty(value = "班级名称")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public OccupancyResource studentCount(BigInteger studentCount) {
    this.studentCount = studentCount;
    return this;
  }

  /**
   * 学生人数
   * @return studentCount
  **/
  @ApiModelProperty(value = "学生人数")


  public BigInteger getStudentCount() {
    return studentCount;
  }

  public void setStudentCount(BigInteger studentCount) {
    this.studentCount = studentCount;
  }

  public OccupancyResource teacherName(String teacherName) {
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

  public OccupancyResource courseName(String courseName) {
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

  public OccupancyResource collegeName(String collegeName) {
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
    OccupancyResource occupancyResource = (OccupancyResource) o;
    return Objects.equals(this.groupId, occupancyResource.groupId) &&
        Objects.equals(this.className, occupancyResource.className) &&
        Objects.equals(this.studentCount, occupancyResource.studentCount) &&
        Objects.equals(this.teacherName, occupancyResource.teacherName) &&
        Objects.equals(this.courseName, occupancyResource.courseName) &&
        Objects.equals(this.collegeName, occupancyResource.collegeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupId, className, studentCount, teacherName, courseName, collegeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OccupancyResource {\n");
    
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
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


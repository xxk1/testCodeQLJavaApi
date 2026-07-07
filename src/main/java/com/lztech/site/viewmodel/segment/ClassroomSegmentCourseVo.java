package com.lztech.site.viewmodel.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassroomSegmentCourseVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-16T07:44:21.241Z")




public class ClassroomSegmentCourseVo   {
  @JsonProperty("sectionName")
  private String sectionName = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("teacherName")
  private String teacherName = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("studentNums")
  private Integer studentNums = null;

  @JsonProperty("className")
  private String className = null;

  @JsonProperty("roomName")
  private String roomName = null;

  @JsonProperty("courseCategoryName")
  private String courseCategoryName = null;

  @JsonProperty("courseWeekName")
  private String courseWeekName = null;

  public ClassroomSegmentCourseVo sectionName(String sectionName) {
    this.sectionName = sectionName;
    return this;
  }

  /**
   * 节次名称
   * @return sectionName
  **/
  @ApiModelProperty(value = "节次名称")


  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public ClassroomSegmentCourseVo courseName(String courseName) {
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

  public ClassroomSegmentCourseVo teacherName(String teacherName) {
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

  public ClassroomSegmentCourseVo collegeName(String collegeName) {
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

  public ClassroomSegmentCourseVo studentNums(Integer studentNums) {
    this.studentNums = studentNums;
    return this;
  }

  /**
   * 学生数量
   * @return studentNums
  **/
  @ApiModelProperty(value = "学生数量")


  public Integer getStudentNums() {
    return studentNums;
  }

  public void setStudentNums(Integer studentNums) {
    this.studentNums = studentNums;
  }

  public ClassroomSegmentCourseVo className(String className) {
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

  public ClassroomSegmentCourseVo roomName(String roomName) {
    this.roomName = roomName;
    return this;
  }

  /**
   * 教室名称
   * @return roomName
  **/
  @ApiModelProperty(value = "教室名称")


  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public ClassroomSegmentCourseVo courseCategoryName(String courseCategoryName) {
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

  public ClassroomSegmentCourseVo courseWeekName(String courseWeekName) {
    this.courseWeekName = courseWeekName;
    return this;
  }

  /**
   * 课程周类型名称
   * @return courseWeekName
  **/
  @ApiModelProperty(value = "课程周类型名称")


  public String getCourseWeekName() {
    return courseWeekName;
  }

  public void setCourseWeekName(String courseWeekName) {
    this.courseWeekName = courseWeekName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassroomSegmentCourseVo classroomSegmentCourseVo = (ClassroomSegmentCourseVo) o;
    return Objects.equals(this.sectionName, classroomSegmentCourseVo.sectionName) &&
        Objects.equals(this.courseName, classroomSegmentCourseVo.courseName) &&
        Objects.equals(this.teacherName, classroomSegmentCourseVo.teacherName) &&
        Objects.equals(this.collegeName, classroomSegmentCourseVo.collegeName) &&
        Objects.equals(this.studentNums, classroomSegmentCourseVo.studentNums) &&
        Objects.equals(this.className, classroomSegmentCourseVo.className) &&
        Objects.equals(this.roomName, classroomSegmentCourseVo.roomName) &&
        Objects.equals(this.courseCategoryName, classroomSegmentCourseVo.courseCategoryName) &&
        Objects.equals(this.courseWeekName, classroomSegmentCourseVo.courseWeekName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sectionName, courseName, teacherName, collegeName, studentNums, className, roomName, courseCategoryName, courseWeekName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClassroomSegmentCourseVo {\n");
    
    sb.append("    sectionName: ").append(toIndentedString(sectionName)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    studentNums: ").append(toIndentedString(studentNums)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
    sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
    sb.append("    courseWeekName: ").append(toIndentedString(courseWeekName)).append("\n");
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


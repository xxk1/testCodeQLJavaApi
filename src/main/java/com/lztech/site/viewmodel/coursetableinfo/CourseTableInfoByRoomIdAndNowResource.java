package com.lztech.site.viewmodel.coursetableinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableInfoByRoomIdAndNowResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-19T04:29:50.006Z")


public class CourseTableInfoByRoomIdAndNowResource {
  @JsonProperty("courseTableDetailId")
  private String courseTableDetailId = null;

  @JsonProperty("courseType")
  private String courseType = null;

  @JsonProperty("studentCount")
  private String studentCount = null;

  @JsonProperty("teacherName")
  private String teacherName = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("roomName")
  private String roomName = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("segmentList")
  private String segmentList = null;

  @JsonProperty("className")
  private String className = null;

  public CourseTableInfoByRoomIdAndNowResource courseTableDetailId(String courseTableDetailId) {
    this.courseTableDetailId = courseTableDetailId;
    return this;
  }

  /**
   * 课表详情id
   *
   * @return courseTableDetailId
   **/
  @ApiModelProperty(value = "课表详情id")


  public String getCourseTableDetailId() {
    return courseTableDetailId;
  }

  public void setCourseTableDetailId(String courseTableDetailId) {
    this.courseTableDetailId = courseTableDetailId;
  }

  public CourseTableInfoByRoomIdAndNowResource courseType(String courseType) {
    this.courseType = courseType;
    return this;
  }

  /**
   * 课表类型名称
   *
   * @return courseType
   **/
  @ApiModelProperty(value = "课表类型名称")


  public String getCourseType() {
    return courseType;
  }

  public void setCourseType(String courseType) {
    this.courseType = courseType;
  }

  public CourseTableInfoByRoomIdAndNowResource studentCount(String studentCount) {
    this.studentCount = studentCount;
    return this;
  }

  /**
   * 学生数量
   *
   * @return studentCount
   **/
  @ApiModelProperty(value = "学生数量")


  public String getStudentCount() {
    return studentCount;
  }

  public void setStudentCount(String studentCount) {
    this.studentCount = studentCount;
  }

  public CourseTableInfoByRoomIdAndNowResource teacherName(String teacherName) {
    this.teacherName = teacherName;
    return this;
  }

  /**
   * 上课老师名称,多个老师使用“,” 隔开
   *
   * @return teacherName
   **/
  @ApiModelProperty(value = "上课老师名称,多个老师使用“,” 隔开")


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public CourseTableInfoByRoomIdAndNowResource courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 上课时间 格式：yyyy-MM-dd
   *
   * @return courseDate
   **/
  @ApiModelProperty(value = "上课时间 格式：yyyy-MM-dd")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public CourseTableInfoByRoomIdAndNowResource roomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * 上课教室id
   *
   * @return roomId
   **/
  @ApiModelProperty(value = "上课教室id")


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public CourseTableInfoByRoomIdAndNowResource roomName(String roomName) {
    this.roomName = roomName;
    return this;
  }

  /**
   * 上课教室名称
   *
   * @return roomName
   **/
  @ApiModelProperty(value = "上课教室名称")


  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public CourseTableInfoByRoomIdAndNowResource courseName(String courseName) {
    this.courseName = courseName;
    return this;
  }

  /**
   * 课程名称
   *
   * @return courseName
   **/
  @ApiModelProperty(value = "课程名称")


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public CourseTableInfoByRoomIdAndNowResource collegeName(String collegeName) {
    this.collegeName = collegeName;
    return this;
  }

  /**
   * 学院名称
   *
   * @return collegeName
   **/
  @ApiModelProperty(value = "学院名称")


  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public CourseTableInfoByRoomIdAndNowResource segmentList(String segmentList) {
    this.segmentList = segmentList;
    return this;
  }

  /**
   * 节次(多个“,”隔开)
   *
   * @return segmentList
   **/
  @ApiModelProperty(value = "节次(多个“,”隔开)")


  public String getSegmentList() {
    return segmentList;
  }

  public void setSegmentList(String segmentList) {
    this.segmentList = segmentList;
  }

  public CourseTableInfoByRoomIdAndNowResource className(String className) {
    this.className = className;
    return this;
  }

  /**
   * 上课班级
   *
   * @return className
   **/
  @ApiModelProperty(value = "上课班级")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTableInfoByRoomIdAndNowResource courseTableInfoByRoomIdAndNowResource = (CourseTableInfoByRoomIdAndNowResource) o;
    return Objects.equals(this.courseTableDetailId, courseTableInfoByRoomIdAndNowResource.courseTableDetailId) &&
            Objects.equals(this.courseType, courseTableInfoByRoomIdAndNowResource.courseType) &&
            Objects.equals(this.studentCount, courseTableInfoByRoomIdAndNowResource.studentCount) &&
            Objects.equals(this.teacherName, courseTableInfoByRoomIdAndNowResource.teacherName) &&
            Objects.equals(this.courseDate, courseTableInfoByRoomIdAndNowResource.courseDate) &&
            Objects.equals(this.roomId, courseTableInfoByRoomIdAndNowResource.roomId) &&
            Objects.equals(this.roomName, courseTableInfoByRoomIdAndNowResource.roomName) &&
            Objects.equals(this.courseName, courseTableInfoByRoomIdAndNowResource.courseName) &&
            Objects.equals(this.collegeName, courseTableInfoByRoomIdAndNowResource.collegeName) &&
            Objects.equals(this.segmentList, courseTableInfoByRoomIdAndNowResource.segmentList) &&
            Objects.equals(this.className, courseTableInfoByRoomIdAndNowResource.className);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseTableDetailId, courseType, studentCount, teacherName
            , courseDate, roomId, roomName, courseName, collegeName, segmentList, className);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTableInfoByRoomIdAndNowResource {\n");

    sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
    sb.append("    courseType: ").append(toIndentedString(courseType)).append("\n");
    sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    segmentList: ").append(toIndentedString(segmentList)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
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


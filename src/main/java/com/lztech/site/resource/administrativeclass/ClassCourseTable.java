package com.lztech.site.resource.administrativeclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassCourseTable
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-24T08:56:00.785Z")




public class ClassCourseTable   {
  @JsonProperty("week")
  private String week = null;

  @JsonProperty("segment")
  private String segment = null;

  @JsonProperty("weekDay")
  private String weekDay = null;

  @JsonProperty("weekType")
  private String weekType = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("courseType")
  private Integer courseType = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseTableId")
  private String courseTableId = null;

  @JsonProperty("teachingClassName")
  private String teachingClassName = null;

  @JsonProperty("teachingClassId")
  private String teachingClassId = null;

  @JsonProperty("studentNumber")
  private Integer studentNumber = null;

  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("roomName")
  private String roomName = null;

  @JsonProperty("courseAttr")
  private Integer courseAttr = null;

  @JsonProperty("courseAttrName")
  private String courseAttrName = null;

  @JsonProperty("teacherName")
  private String teacherName = null;

  @JsonProperty("teacherId")
  private String teacherId = null;

  @JsonProperty("collgegId")
  private String collgegId = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  public ClassCourseTable week(String week) {
    this.week = week;
    return this;
  }

  /**
   * 第几周
   * @return week
  **/
  @ApiModelProperty(value = "第几周")


  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }

  public ClassCourseTable segment(String segment) {
    this.segment = segment;
    return this;
  }

  /**
   * 第几节次
   * @return segment
  **/
  @ApiModelProperty(value = "第几节次")


  public String getSegment() {
    return segment;
  }

  public void setSegment(String segment) {
    this.segment = segment;
  }

  public ClassCourseTable weekDay(String weekDay) {
    this.weekDay = weekDay;
    return this;
  }

  /**
   * 星期几
   * @return weekDay
  **/
  @ApiModelProperty(value = "星期几")


  public String getWeekDay() {
    return weekDay;
  }

  public void setWeekDay(String weekDay) {
    this.weekDay = weekDay;
  }

  public ClassCourseTable weekType(String weekType) {
    this.weekType = weekType;
    return this;
  }

  /**
   * 单双周
   * @return weekType
  **/
  @ApiModelProperty(value = "单双周")


  public String getWeekType() {
    return weekType;
  }

  public void setWeekType(String weekType) {
    this.weekType = weekType;
  }

  public ClassCourseTable courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 上课日期
   * @return courseDate
  **/
  @ApiModelProperty(value = "上课日期")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public ClassCourseTable courseType(Integer courseType) {
    this.courseType = courseType;
    return this;
  }

  /**
   * 课程类型 0：理论课 1：实验课 2：体育课 3：外语课
   * @return courseType
  **/
  @ApiModelProperty(value = "课程类型 0：理论课 1：实验课 2：体育课 3：外语课")


  public Integer getCourseType() {
    return courseType;
  }

  public void setCourseType(Integer courseType) {
    this.courseType = courseType;
  }

  public ClassCourseTable courseName(String courseName) {
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

  public ClassCourseTable courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 课程id
   * @return courseId
  **/
  @ApiModelProperty(value = "课程id")


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public ClassCourseTable courseTableId(String courseTableId) {
    this.courseTableId = courseTableId;
    return this;
  }

  /**
   * 课程表id
   * @return courseTableId
  **/
  @ApiModelProperty(value = "课程表id")


  public String getCourseTableId() {
    return courseTableId;
  }

  public void setCourseTableId(String courseTableId) {
    this.courseTableId = courseTableId;
  }

  public ClassCourseTable teachingClassName(String teachingClassName) {
    this.teachingClassName = teachingClassName;
    return this;
  }

  /**
   * 上课班级
   * @return teachingClassName
  **/
  @ApiModelProperty(value = "上课班级")


  public String getTeachingClassName() {
    return teachingClassName;
  }

  public void setTeachingClassName(String teachingClassName) {
    this.teachingClassName = teachingClassName;
  }

  public ClassCourseTable teachingClassId(String teachingClassId) {
    this.teachingClassId = teachingClassId;
    return this;
  }

  /**
   * 班级id
   * @return teachingClassId
  **/
  @ApiModelProperty(value = "班级id")


  public String getTeachingClassId() {
    return teachingClassId;
  }

  public void setTeachingClassId(String teachingClassId) {
    this.teachingClassId = teachingClassId;
  }

  public ClassCourseTable studentNumber(Integer studentNumber) {
    this.studentNumber = studentNumber;
    return this;
  }

  /**
   * 上课人数
   * @return studentNumber
  **/
  @ApiModelProperty(value = "上课人数")


  public Integer getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(Integer studentNumber) {
    this.studentNumber = studentNumber;
  }

  public ClassCourseTable roomId(String roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * 教室id
   * @return roomId
  **/
  @ApiModelProperty(value = "教室id")


  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public ClassCourseTable roomName(String roomName) {
    this.roomName = roomName;
    return this;
  }

  /**
   * 上课教室
   * @return roomName
  **/
  @ApiModelProperty(value = "上课教室")


  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public ClassCourseTable courseAttr(Integer courseAttr) {
    this.courseAttr = courseAttr;
    return this;
  }

  /**
   * 0 :必修 1: 选修 2：公选 3：指选 4：国际课 5：双学位 6：其他
   * @return courseAttr
  **/
  @ApiModelProperty(value = "0 :必修 1: 选修 2：公选 3：指选 4：国际课 5：双学位 6：其他")


  public Integer getCourseAttr() {
    return courseAttr;
  }

  public void setCourseAttr(Integer courseAttr) {
    this.courseAttr = courseAttr;
  }

  public ClassCourseTable courseAttrName(String courseAttrName) {
    this.courseAttrName = courseAttrName;
    return this;
  }

  /**
   * 课程种类名称
   * @return courseAttrName
  **/
  @ApiModelProperty(value = "课程种类名称")


  public String getCourseAttrName() {
    return courseAttrName;
  }

  public void setCourseAttrName(String courseAttrName) {
    this.courseAttrName = courseAttrName;
  }

  public ClassCourseTable teacherName(String teacherName) {
    this.teacherName = teacherName;
    return this;
  }

  /**
   * 多个老师使用“,” 隔开 
   * @return teacherName
  **/
  @ApiModelProperty(value = "多个老师使用“,” 隔开 ")


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public ClassCourseTable teacherId(String teacherId) {
    this.teacherId = teacherId;
    return this;
  }

  /**
   * 多个老师使用“,” 隔开
   * @return teacherId
  **/
  @ApiModelProperty(value = "多个老师使用“,” 隔开")


  public String getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
  }

  public ClassCourseTable collgegId(String collgegId) {
    this.collgegId = collgegId;
    return this;
  }

  /**
   * 学院id
   * @return collgegId
  **/
  @ApiModelProperty(value = "学院id")


  public String getCollgegId() {
    return collgegId;
  }

  public void setCollgegId(String collgegId) {
    this.collgegId = collgegId;
  }

  public ClassCourseTable collegeName(String collegeName) {
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
    ClassCourseTable classCourseTable = (ClassCourseTable) o;
    return Objects.equals(this.week, classCourseTable.week) &&
        Objects.equals(this.segment, classCourseTable.segment) &&
        Objects.equals(this.weekDay, classCourseTable.weekDay) &&
        Objects.equals(this.weekType, classCourseTable.weekType) &&
        Objects.equals(this.courseDate, classCourseTable.courseDate) &&
        Objects.equals(this.courseType, classCourseTable.courseType) &&
        Objects.equals(this.courseName, classCourseTable.courseName) &&
        Objects.equals(this.courseId, classCourseTable.courseId) &&
        Objects.equals(this.courseTableId, classCourseTable.courseTableId) &&
        Objects.equals(this.teachingClassName, classCourseTable.teachingClassName) &&
        Objects.equals(this.teachingClassId, classCourseTable.teachingClassId) &&
        Objects.equals(this.studentNumber, classCourseTable.studentNumber) &&
        Objects.equals(this.roomId, classCourseTable.roomId) &&
        Objects.equals(this.roomName, classCourseTable.roomName) &&
        Objects.equals(this.courseAttr, classCourseTable.courseAttr) &&
        Objects.equals(this.courseAttrName, classCourseTable.courseAttrName) &&
        Objects.equals(this.teacherName, classCourseTable.teacherName) &&
        Objects.equals(this.teacherId, classCourseTable.teacherId) &&
        Objects.equals(this.collgegId, classCourseTable.collgegId) &&
        Objects.equals(this.collegeName, classCourseTable.collegeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(week, segment, weekDay, weekType, courseDate, courseType, courseName, courseId, courseTableId,
            teachingClassName, teachingClassId, studentNumber, roomId, roomName, courseAttr,
            courseAttrName, teacherName, teacherId, collgegId, collegeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClassCourseTable {\n");
    
    sb.append("    week: ").append(toIndentedString(week)).append("\n");
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    weekDay: ").append(toIndentedString(weekDay)).append("\n");
    sb.append("    weekType: ").append(toIndentedString(weekType)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    courseType: ").append(toIndentedString(courseType)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
    sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
    sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
    sb.append("    studentNumber: ").append(toIndentedString(studentNumber)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
    sb.append("    courseAttr: ").append(toIndentedString(courseAttr)).append("\n");
    sb.append("    courseAttrName: ").append(toIndentedString(courseAttrName)).append("\n");
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
    sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
    sb.append("    collgegId: ").append(toIndentedString(collgegId)).append("\n");
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


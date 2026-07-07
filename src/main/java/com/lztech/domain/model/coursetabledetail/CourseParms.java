package com.lztech.domain.model.coursetabledetail;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;


/**
 * CourseParms
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-21T11:37:42.008Z")

public class CourseParms   {
  @JsonProperty("roomId")
  private String roomId = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("teacherId")
  private String teacherId = null;

  @JsonProperty("studentId")
  private String studentId = null;

  @JsonProperty("week")
  private Integer week = null;

  @JsonProperty("courseInfoId")
  private String courseInfoId = null;

  @JsonProperty("schoolYear")
  private String schoolYear = null;

  @JsonProperty("term")
  private Integer term = null;

  public CourseParms roomId(String roomId) {
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

  public CourseParms courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 课程日期
   * @return courseDate
  **/
  @ApiModelProperty(value = "课程日期")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public CourseParms teacherId(String teacherId) {
    this.teacherId = teacherId;
    return this;
  }

  /**
   * 教师id
   * @return teacherId
  **/
  @ApiModelProperty(value = "教师id")


  public String getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
  }

  public CourseParms studentId(String studentId) {
    this.studentId = studentId;
    return this;
  }

  /**
   * 学生id
   * @return studentId
  **/
  @ApiModelProperty(value = "学生id")


  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public CourseParms week(Integer week) {
    this.week = week;
    return this;
  }

  /**
   * 周次数
   * @return week
  **/
  @ApiModelProperty(value = "周次数")


  public Integer getWeek() {
    return week;
  }

  public void setWeek(Integer week) {
    this.week = week;
  }

  public CourseParms courseInfoId(String courseInfoId) {
    this.courseInfoId = courseInfoId;
    return this;
  }

  /**
   * 开课id不为空时，课程日期默认全部
   * @return courseInfoId
  **/
  @ApiModelProperty(value = "开课id不为空时，课程日期默认全部")


  public String getCourseInfoId() {
    return courseInfoId;
  }

  public void setCourseInfoId(String courseInfoId) {
    this.courseInfoId = courseInfoId;
  }

  public CourseParms schoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
    return this;
  }

  /**
   * 学年
   * @return schoolYear
  **/
  @ApiModelProperty(value = "学年")


  public String getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
  }

  public CourseParms term(Integer term) {
    this.term = term;
    return this;
  }

  /**
   * 学期
   * @return term
  **/
  @ApiModelProperty(value = "学期")


  public Integer getTerm() {
    return term;
  }

  public void setTerm(Integer term) {
    this.term = term;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseParms courseParms = (CourseParms) o;
    return Objects.equals(this.roomId, courseParms.roomId) &&
        Objects.equals(this.courseDate, courseParms.courseDate) &&
        Objects.equals(this.teacherId, courseParms.teacherId) &&
        Objects.equals(this.studentId, courseParms.studentId) &&
        Objects.equals(this.week, courseParms.week) &&
        Objects.equals(this.courseInfoId, courseParms.courseInfoId) &&
        Objects.equals(this.schoolYear, courseParms.schoolYear) &&
        Objects.equals(this.term, courseParms.term);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomId, courseDate, teacherId, studentId, week, courseInfoId, schoolYear, term);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseParms {\n");
    
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
    sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
    sb.append("    week: ").append(toIndentedString(week)).append("\n");
    sb.append("    courseInfoId: ").append(toIndentedString(courseInfoId)).append("\n");
    sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
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


package com.lztech.site.resource.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-08T12:09:12.680Z")

public class CourseTableResource   {
  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("groupNo")
  private String groupNo = null;

  @JsonProperty("courseType")
  private String courseType = null;

  @JsonProperty("weekType")
  private String weekType = null;

  @JsonProperty("teacherName")
  private String teacherName = null;

  @JsonProperty("schoolYear")
  private String schoolYear = null;

  @JsonProperty("term")
  private String term = null;

  public CourseTableResource courseCode(String courseCode) {
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

  public CourseTableResource groupNo(String groupNo) {
    this.groupNo = groupNo;
    return this;
  }

  /**
   * 组编号
   * @return groupNo
  **/
  @ApiModelProperty(value = "组编号")


  public String getGroupNo() {
    return groupNo;
  }

  public void setGroupNo(String groupNo) {
    this.groupNo = groupNo;
  }

  public CourseTableResource courseType(String courseType) {
    this.courseType = courseType;
    return this;
  }

  /**
   * 课程类别
   * @return courseType
  **/
  @ApiModelProperty(value = "课程类别")


  public String getCourseType() {
    return courseType;
  }

  public void setCourseType(String courseType) {
    this.courseType = courseType;
  }

  public CourseTableResource weekType(String weekType) {
    this.weekType = weekType;
    return this;
  }

  /**
   * 周次类别
   * @return weekType
  **/
  @ApiModelProperty(value = "周次类别")


  public String getWeekType() {
    return weekType;
  }

  public void setWeekType(String weekType) {
    this.weekType = weekType;
  }

  public CourseTableResource teacherName(String teacherName) {
    this.teacherName = teacherName;
    return this;
  }

  /**
   * 教师名称
   * @return teacherName
  **/
  @ApiModelProperty(value = "教师名称")


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public CourseTableResource schoolYear(String schoolYear) {
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

  public CourseTableResource term(String term) {
    this.term = term;
    return this;
  }

  /**
   * 学期
   * @return term
  **/
  @ApiModelProperty(value = "学期")


  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
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
    CourseTableResource courseTableResource = (CourseTableResource) o;
    return Objects.equals(this.courseCode, courseTableResource.courseCode) &&
        Objects.equals(this.groupNo, courseTableResource.groupNo) &&
        Objects.equals(this.courseType, courseTableResource.courseType) &&
        Objects.equals(this.weekType, courseTableResource.weekType) &&
        Objects.equals(this.teacherName, courseTableResource.teacherName) &&
        Objects.equals(this.schoolYear, courseTableResource.schoolYear) &&
        Objects.equals(this.term, courseTableResource.term);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseCode, groupNo, courseType, weekType, teacherName, schoolYear, term);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTableResource {\n");
    
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
    sb.append("    courseType: ").append(toIndentedString(courseType)).append("\n");
    sb.append("    weekType: ").append(toIndentedString(weekType)).append("\n");
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
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


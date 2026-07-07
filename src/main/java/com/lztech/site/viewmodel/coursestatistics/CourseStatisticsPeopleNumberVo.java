package com.lztech.site.viewmodel.coursestatistics;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseStatisticsPeopleNumberVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-11T09:06:54.065Z")


public class CourseStatisticsPeopleNumberVo   {
  @JsonProperty("teacherNumber")
  private Integer teacherNumber = null;

  @JsonProperty("studentNumebr")
  private Integer studentNumebr = null;

  @JsonProperty("classNumebr")
  private Integer classNumebr = null;

  public CourseStatisticsPeopleNumberVo teacherNumber(Integer teacherNumber) {
    this.teacherNumber = teacherNumber;
    return this;
  }

  /**
   * 选课教师数
   * @return teacherNumber
  **/
  @ApiModelProperty(value = "选课教师数")


  public Integer getTeacherNumber() {
    return teacherNumber;
  }

  public void setTeacherNumber(Integer teacherNumber) {
    this.teacherNumber = teacherNumber;
  }

  public CourseStatisticsPeopleNumberVo studentNumebr(Integer studentNumebr) {
    this.studentNumebr = studentNumebr;
    return this;
  }

  /**
   * 选课学生数
   * @return studentNumebr
  **/
  @ApiModelProperty(value = "选课学生数")


  public Integer getStudentNumebr() {
    return studentNumebr;
  }

  public void setStudentNumebr(Integer studentNumebr) {
    this.studentNumebr = studentNumebr;
  }

  public CourseStatisticsPeopleNumberVo classNumebr(Integer classNumebr) {
    this.classNumebr = classNumebr;
    return this;
  }

  /**
   * 开班数量
   * @return classNumebr
  **/
  @ApiModelProperty(value = "开班数量")


  public Integer getClassNumebr() {
    return classNumebr;
  }

  public void setClassNumebr(Integer classNumebr) {
    this.classNumebr = classNumebr;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseStatisticsPeopleNumberVo courseStatisticsPeopleNumberVo = (CourseStatisticsPeopleNumberVo) o;
    return Objects.equals(this.teacherNumber, courseStatisticsPeopleNumberVo.teacherNumber) &&
        Objects.equals(this.studentNumebr, courseStatisticsPeopleNumberVo.studentNumebr) &&
        Objects.equals(this.classNumebr, courseStatisticsPeopleNumberVo.classNumebr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teacherNumber, studentNumebr, classNumebr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseStatisticsPeopleNumberVo {\n");
    
    sb.append("    teacherNumber: ").append(toIndentedString(teacherNumber)).append("\n");
    sb.append("    studentNumebr: ").append(toIndentedString(studentNumebr)).append("\n");
    sb.append("    classNumebr: ").append(toIndentedString(classNumebr)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


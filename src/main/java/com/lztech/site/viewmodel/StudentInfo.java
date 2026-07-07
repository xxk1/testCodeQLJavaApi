package com.lztech.site.viewmodel;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-16T12:26:57.068Z")

public class StudentInfo   {
  @JsonProperty("className")
  private String className  = null;

  @JsonProperty("studentId")
  private String studentId = null;

  @JsonProperty("studentName")
  private String studentName = null;

  @JsonProperty("studentNo")
  private String studentNo = null;

  @JsonProperty("openId")
  private String openId = null;

  public StudentInfo className(String className) {
    this.className = className;
    return this;
  }

  /**
   * 行政班
   * @return className
   **/
  @ApiModelProperty(value = "行政班")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public StudentInfo studentId(String studentId) {
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

  public StudentInfo studentName(String studentName) {
    this.studentName = studentName;
    return this;
  }

  /**
   * 学生名称
   * @return studentName
   **/
  @ApiModelProperty(value = "学生名称")


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public StudentInfo studentNo(String studentNo) {
    this.studentNo = studentNo;
    return this;
  }

  /**
   * 学生学号
   * @return studentNo
   **/
  @ApiModelProperty(value = "学生学号")


  public String getStudentNo() {
    return studentNo;
  }

  public void setStudentNo(String studentNo) {
    this.studentNo = studentNo;
  }


  public StudentInfo openId(String openId) {
    this.openId = openId;
    return this;
  }

  /**
   * openId
   * @return openId
   **/
  @ApiModelProperty(value = "openId")


  public String getopenId() {
    return openId;
  }

  public void setopenId(String openId) {
    this.openId = openId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentInfo studentInfo = (StudentInfo) o;
    return Objects.equals(this.className, studentInfo.className) &&
            Objects.equals(this.studentId, studentInfo.studentId) &&
            Objects.equals(this.studentName, studentInfo.studentName) &&
            Objects.equals(this.openId, studentInfo.openId) &&
            Objects.equals(this.studentNo, studentInfo.studentNo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(className, openId,studentId, studentName, studentNo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentInfo {\n");

    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
    sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
    sb.append("    studentNo: ").append(toIndentedString(studentNo)).append("\n");
    sb.append("    openId: ").append(toIndentedString(openId)).append("\n");
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

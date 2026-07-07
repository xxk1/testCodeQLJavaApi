package com.lztech.site.viewmodel.group;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * StudentVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-30T06:24:31.345Z")

public class StudentVo   {
  @JsonProperty("studentId")
  private String studentId = null;

  @JsonProperty("studentNo")
  private String studentNo = null;

  @JsonProperty("studentName")
  private String studentName = null;

  @JsonProperty("groupId")
  private String groupId = null;

  @JsonProperty("groupName")
  private String groupName = null;

  public StudentVo studentId(String studentId) {
    this.studentId = studentId;
    return this;
  }

  /**
   * 学生ID
   * @return studentId
  **/
  @ApiModelProperty(value = "学生ID")


  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public StudentVo studentNo(String studentNo) {
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

  public StudentVo studentName(String studentName) {
    this.studentName = studentName;
    return this;
  }

  /**
   * 学生姓名
   * @return studentName
  **/
  @ApiModelProperty(value = "学生姓名")


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public StudentVo groupId(String groupId) {
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

  public StudentVo groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * 班级名称
   * @return groupName
  **/
  @ApiModelProperty(value = "班级名称")


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentVo studentVo = (StudentVo) o;
    return Objects.equals(this.studentId, studentVo.studentId) &&
        Objects.equals(this.studentNo, studentVo.studentNo) &&
        Objects.equals(this.studentName, studentVo.studentName) &&
        Objects.equals(this.groupId, studentVo.groupId) &&
        Objects.equals(this.groupName, studentVo.groupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, studentNo, studentName, groupId, groupName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentVo {\n");
    
    sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
    sb.append("    studentNo: ").append(toIndentedString(studentNo)).append("\n");
    sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
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


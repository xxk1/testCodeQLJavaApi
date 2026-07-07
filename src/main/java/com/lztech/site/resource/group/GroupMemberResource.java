package com.lztech.site.resource.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupMemberResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T01:41:32.525Z")

public class GroupMemberResource   {
  @JsonProperty("studentId")
  private String studentId = null;

  @JsonProperty("studentNo")
  private String studentNo = null;

  @JsonProperty("studentName")
  private String studentName = null;

  @JsonProperty("className")
  private String className = null;

  public GroupMemberResource studentId(String studentId) {
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

  public GroupMemberResource studentNo(String studentNo) {
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

  public GroupMemberResource studentName(String studentName) {
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

  public GroupMemberResource className(String className) {
    this.className = className;
    return this;
  }

  /**
   * 行政班名称
   * @return className
  **/
  @ApiModelProperty(value = "行政班名称")


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
    GroupMemberResource groupMemberResource = (GroupMemberResource) o;
    return Objects.equals(this.studentId, groupMemberResource.studentId) &&
        Objects.equals(this.studentNo, groupMemberResource.studentNo) &&
        Objects.equals(this.studentName, groupMemberResource.studentName) &&
        Objects.equals(this.className, groupMemberResource.className);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, studentNo, studentName, className);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupMemberResource {\n");
    
    sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
    sb.append("    studentNo: ").append(toIndentedString(studentNo)).append("\n");
    sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
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


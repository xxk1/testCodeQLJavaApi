package com.lztech.site.viewmodel.classgroupingscheme;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * GroupingSchemeStudent
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-18T10:37:19.607Z")


public class GroupingSchemeStudent   {
  @JsonProperty("studentId")
  private String studentId = null;

  @JsonProperty("studentName")
  private String studentName = null;

  public GroupingSchemeStudent studentId(String studentId) {
    this.studentId = studentId;
    return this;
  }

  /**
   * 教师ID
   * @return studentId
  **/
  @ApiModelProperty(value = "教师ID")


  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public GroupingSchemeStudent studentName(String studentName) {
    this.studentName = studentName;
    return this;
  }

  /**
   * 教师名称
   * @return studentName
  **/
  @ApiModelProperty(value = "教师名称")


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupingSchemeStudent groupingSchemeStudent = (GroupingSchemeStudent) o;
    return Objects.equals(this.studentId, groupingSchemeStudent.studentId) &&
        Objects.equals(this.studentName, groupingSchemeStudent.studentName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, studentName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupingSchemeStudent {\n");
    
    sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
    sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
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


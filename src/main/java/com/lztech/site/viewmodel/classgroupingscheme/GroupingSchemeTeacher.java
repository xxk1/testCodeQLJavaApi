package com.lztech.site.viewmodel.classgroupingscheme;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * GroupingSchemeTeacher
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-16T05:37:13.881Z")


public class GroupingSchemeTeacher   {
  @JsonProperty("teacherId")
  private String teacherId = null;

  @JsonProperty("teacherName")
  private String teacherName = null;

  public GroupingSchemeTeacher teacherId(String teacherId) {
    this.teacherId = teacherId;
    return this;
  }

  /**
   * 教师ID
   * @return teacherId
  **/
  @ApiModelProperty(value = "教师ID")


  public String getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
  }

  public GroupingSchemeTeacher teacherName(String teacherName) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupingSchemeTeacher groupingSchemeTeacher = (GroupingSchemeTeacher) o;
    return Objects.equals(this.teacherId, groupingSchemeTeacher.teacherId) &&
        Objects.equals(this.teacherName, groupingSchemeTeacher.teacherName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teacherId, teacherName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupingSchemeTeacher {\n");
    
    sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
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


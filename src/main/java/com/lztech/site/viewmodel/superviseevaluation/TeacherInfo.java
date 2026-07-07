package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-11T10:29:38.621Z")




public class TeacherInfo   {
  @JsonProperty("teacherName")
  private String teacherName = null;

  @JsonProperty("teacherId")
  private String teacherId = null;

  @JsonProperty("teacherNo")
  private String teacherNo = null;

  @JsonProperty("teacherCollegeId")
  private String teacherCollegeId = null;

  @JsonProperty("teacherCollegeName")
  private String teacherCollegeName = null;

  public TeacherInfo teacherName(String teacherName) {
    this.teacherName = teacherName;
    return this;
  }

  /**
   * 老师姓名
   * @return teacherName
  **/
  @ApiModelProperty(value = "老师姓名")


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public TeacherInfo teacherId(String teacherId) {
    this.teacherId = teacherId;
    return this;
  }

  /**
   * 老师ID
   * @return teacherId
  **/
  @ApiModelProperty(value = "老师ID")


  public String getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
  }

  public TeacherInfo teacherNo(String teacherNo) {
    this.teacherNo = teacherNo;
    return this;
  }

  /**
   * 老师工号
   * @return teacherNo
  **/
  @ApiModelProperty(value = "老师工号")


  public String getTeacherNo() {
    return teacherNo;
  }

  public void setTeacherNo(String teacherNo) {
    this.teacherNo = teacherNo;
  }

  public TeacherInfo teacherCollegeId(String teacherCollegeId) {
    this.teacherCollegeId = teacherCollegeId;
    return this;
  }

  /**
   * 老师所属学院ID
   * @return teacherCollegeId
  **/
  @ApiModelProperty(value = "老师所属学院ID")


  public String getTeacherCollegeId() {
    return teacherCollegeId;
  }

  public void setTeacherCollegeId(String teacherCollegeId) {
    this.teacherCollegeId = teacherCollegeId;
  }

  public TeacherInfo teacherCollegeName(String teacherCollegeName) {
    this.teacherCollegeName = teacherCollegeName;
    return this;
  }

  /**
   * 老师所属学院姓名
   * @return teacherCollegeName
  **/
  @ApiModelProperty(value = "老师所属学院姓名")


  public String getTeacherCollegeName() {
    return teacherCollegeName;
  }

  public void setTeacherCollegeName(String teacherCollegeName) {
    this.teacherCollegeName = teacherCollegeName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeacherInfo teacherInfo = (TeacherInfo) o;
    return Objects.equals(this.teacherName, teacherInfo.teacherName) &&
        Objects.equals(this.teacherId, teacherInfo.teacherId) &&
        Objects.equals(this.teacherNo, teacherInfo.teacherNo) &&
        Objects.equals(this.teacherCollegeId, teacherInfo.teacherCollegeId) &&
        Objects.equals(this.teacherCollegeName, teacherInfo.teacherCollegeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teacherName, teacherId, teacherNo, teacherCollegeId, teacherCollegeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeacherInfo {\n");
    
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
    sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
    sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
    sb.append("    teacherCollegeId: ").append(toIndentedString(teacherCollegeId)).append("\n");
    sb.append("    teacherCollegeName: ").append(toIndentedString(teacherCollegeName)).append("\n");
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


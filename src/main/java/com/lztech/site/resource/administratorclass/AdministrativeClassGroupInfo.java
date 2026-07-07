package com.lztech.site.resource.administratorclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AdministrativeClassGroupInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T08:30:00.298Z")

public class AdministrativeClassGroupInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("className")
  private String className = null;

  @JsonProperty("classId")
  private String classId = null;

  @JsonProperty("groupNo")
  private String groupNo = null;

  @JsonProperty("year")
  private String year = null;

  @JsonProperty("majorId")
  private String majorId = null;

  @JsonProperty("teacherId")
  private String teacherId = null;

  @JsonProperty("teacherName")
  private String teacherName = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("studentNum")
  private String studentNum = null;

  public AdministrativeClassGroupInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * id
   * @return id
  **/
  @ApiModelProperty(value = "id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AdministrativeClassGroupInfo className(String className) {
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

  public AdministrativeClassGroupInfo classId(String classId) {
    this.classId = classId;
    return this;
  }

  /**
   * 行政班Id
   * @return classId
  **/
  @ApiModelProperty(value = "行政班Id")


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }

  public AdministrativeClassGroupInfo groupNo(String groupNo) {
    this.groupNo = groupNo;
    return this;
  }

  /**
   * 组号
   * @return groupNo
  **/
  @ApiModelProperty(value = "组号")


  public String getGroupNo() {
    return groupNo;
  }

  public void setGroupNo(String groupNo) {
    this.groupNo = groupNo;
  }

  public AdministrativeClassGroupInfo year(String year) {
    this.year = year;
    return this;
  }

  /**
   * 年级
   * @return year
  **/
  @ApiModelProperty(value = "年级")


  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public AdministrativeClassGroupInfo majorId(String majorId) {
    this.majorId = majorId;
    return this;
  }

  /**
   * 专业Id
   * @return majorId
  **/
  @ApiModelProperty(value = "专业Id")


  public String getMajorId() {
    return majorId;
  }

  public void setMajorId(String majorId) {
    this.majorId = majorId;
  }

  public AdministrativeClassGroupInfo teacherId(String teacherId) {
    this.teacherId = teacherId;
    return this;
  }

  /**
   * 老师Id
   * @return teacherId
  **/
  @ApiModelProperty(value = "老师Id")


  public String getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
  }

  public AdministrativeClassGroupInfo teacherName(String teacherName) {
    this.teacherName = teacherName;
    return this;
  }

  /**
   * 老师名称
   * @return teacherName
  **/
  @ApiModelProperty(value = "老师名称")


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public AdministrativeClassGroupInfo collegeName(String collegeName) {
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

  public AdministrativeClassGroupInfo collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院Id
   * @return collegeId
  **/
  @ApiModelProperty(value = "学院Id")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public AdministrativeClassGroupInfo studentNum(String studentNum) {
    this.studentNum = studentNum;
    return this;
  }

  /**
   * 学生人数
   * @return studentNum
  **/
  @ApiModelProperty(value = "学生人数")


  public String getStudentNum() {
    return studentNum;
  }

  public void setStudentNum(String studentNum) {
    this.studentNum = studentNum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdministrativeClassGroupInfo administrativeClassGroupInfo = (AdministrativeClassGroupInfo) o;
    return Objects.equals(this.id, administrativeClassGroupInfo.id) &&
        Objects.equals(this.className, administrativeClassGroupInfo.className) &&
        Objects.equals(this.classId, administrativeClassGroupInfo.classId) &&
        Objects.equals(this.groupNo, administrativeClassGroupInfo.groupNo) &&
        Objects.equals(this.year, administrativeClassGroupInfo.year) &&
        Objects.equals(this.majorId, administrativeClassGroupInfo.majorId) &&
        Objects.equals(this.teacherId, administrativeClassGroupInfo.teacherId) &&
        Objects.equals(this.teacherName, administrativeClassGroupInfo.teacherName) &&
        Objects.equals(this.collegeName, administrativeClassGroupInfo.collegeName) &&
        Objects.equals(this.collegeId, administrativeClassGroupInfo.collegeId) &&
        Objects.equals(this.studentNum, administrativeClassGroupInfo.studentNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, className, classId, groupNo, year, majorId, teacherId, teacherName, collegeName, collegeId, studentNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeClassGroupInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
    sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    majorId: ").append(toIndentedString(majorId)).append("\n");
    sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    studentNum: ").append(toIndentedString(studentNum)).append("\n");
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


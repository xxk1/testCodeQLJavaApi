package com.lztech.site.resource.administratorclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AdministrativeClassInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T08:30:00.298Z")

public class AdministrativeClassInfo   {
  @JsonProperty("className")
  private String className = null;

  @JsonProperty("classId")
  private String classId = null;

  @JsonProperty("year")
  private String year = null;

  @JsonProperty("majorId")
  private String majorId = null;

  @JsonProperty("majorName")
  private String majorName = null;

  @JsonProperty("gradeMajorCode")
  private String gradeMajorCode = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("schoolSystem")
  private String schoolSystem = null;

  @JsonProperty("studentNum")
  private String studentNum = null;

  public AdministrativeClassInfo className(String className) {
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

  public AdministrativeClassInfo classId(String classId) {
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

  public AdministrativeClassInfo year(String year) {
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

  public AdministrativeClassInfo majorId(String majorId) {
    this.majorId = majorId;
    return this;
  }

  /**
   * 专业id
   * @return majorId
  **/
  @ApiModelProperty(value = "专业id")


  public String getMajorId() {
    return majorId;
  }

  public void setMajorId(String majorId) {
    this.majorId = majorId;
  }

  public AdministrativeClassInfo majorName(String majorName) {
    this.majorName = majorName;
    return this;
  }

  /**
   * 专业名称
   * @return majorName
  **/
  @ApiModelProperty(value = "专业名称")


  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public AdministrativeClassInfo gradeMajorCode(String gradeMajorCode) {
    this.gradeMajorCode = gradeMajorCode;
    return this;
  }

  /**
   * 年度专业代码
   * @return gradeMajorCode
  **/
  @ApiModelProperty(value = "年度专业代码")


  public String getGradeMajorCode() {
    return gradeMajorCode;
  }

  public void setGradeMajorCode(String gradeMajorCode) {
    this.gradeMajorCode = gradeMajorCode;
  }

  public AdministrativeClassInfo collegeName(String collegeName) {
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

  public AdministrativeClassInfo collegeId(String collegeId) {
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

  public AdministrativeClassInfo schoolSystem(String schoolSystem) {
    this.schoolSystem = schoolSystem;
    return this;
  }

  /**
   * 学制
   * @return schoolSystem
  **/
  @ApiModelProperty(value = "学制")


  public String getSchoolSystem() {
    return schoolSystem;
  }

  public void setSchoolSystem(String schoolSystem) {
    this.schoolSystem = schoolSystem;
  }

  public AdministrativeClassInfo studentNum(String studentNum) {
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
    AdministrativeClassInfo administrativeClassInfo = (AdministrativeClassInfo) o;
    return Objects.equals(this.className, administrativeClassInfo.className) &&
        Objects.equals(this.classId, administrativeClassInfo.classId) &&
        Objects.equals(this.year, administrativeClassInfo.year) &&
        Objects.equals(this.majorId, administrativeClassInfo.majorId) &&
        Objects.equals(this.majorName, administrativeClassInfo.majorName) &&
        Objects.equals(this.gradeMajorCode, administrativeClassInfo.gradeMajorCode) &&
        Objects.equals(this.collegeName, administrativeClassInfo.collegeName) &&
        Objects.equals(this.collegeId, administrativeClassInfo.collegeId) &&
        Objects.equals(this.schoolSystem, administrativeClassInfo.schoolSystem) &&
        Objects.equals(this.studentNum, administrativeClassInfo.studentNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(className, classId, year, majorId, majorName, gradeMajorCode, collegeName, collegeId, schoolSystem, studentNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeClassInfo {\n");
    
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    majorId: ").append(toIndentedString(majorId)).append("\n");
    sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
    sb.append("    gradeMajorCode: ").append(toIndentedString(gradeMajorCode)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    schoolSystem: ").append(toIndentedString(schoolSystem)).append("\n");
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


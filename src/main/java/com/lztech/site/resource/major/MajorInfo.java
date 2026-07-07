package com.lztech.site.resource.major;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * MajorInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T08:30:00.298Z")

public class MajorInfo   {
  @JsonProperty("majorId")
  private String majorId = null;

  @JsonProperty("majorName")
  private String majorName = null;

  @JsonProperty("year")
  private String year = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("collegeCode")
  private String collegeCode = null;

  public MajorInfo majorId(String majorId) {
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

  public MajorInfo majorName(String majorName) {
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

  public MajorInfo year(String year) {
    this.year = year;
    return this;
  }

  /**
   * 年度
   * @return year
  **/
  @ApiModelProperty(value = "年度")


  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public MajorInfo collegeName(String collegeName) {
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

  public MajorInfo collegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
    return this;
  }

  /**
   * 学院编号
   * @return collegeCode
  **/
  @ApiModelProperty(value = "学院编号")


  public String getCollegeCode() {
    return collegeCode;
  }

  public void setCollegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MajorInfo majorInfo = (MajorInfo) o;
    return Objects.equals(this.majorId, majorInfo.majorId) &&
        Objects.equals(this.majorName, majorInfo.majorName) &&
        Objects.equals(this.year, majorInfo.year) &&
        Objects.equals(this.collegeName, majorInfo.collegeName) &&
        Objects.equals(this.collegeCode, majorInfo.collegeCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(majorId, majorName, year, collegeName, collegeCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MajorInfo {\n");
    
    sb.append("    majorId: ").append(toIndentedString(majorId)).append("\n");
    sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
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


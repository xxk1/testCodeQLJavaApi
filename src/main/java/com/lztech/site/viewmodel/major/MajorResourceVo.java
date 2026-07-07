package com.lztech.site.viewmodel.major;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * MajorResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-20T07:09:56.790Z")

public class MajorResourceVo   {
  @JsonProperty("majorId")
  private String majorId = null;

  @JsonProperty("majorName")
  private String majorName = null;

  @JsonProperty("year")
  private String year = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  public MajorResourceVo majorId(String majorId) {
    this.majorId = majorId;
    return this;
  }

  /**
   * 专业ID
   * @return majorId
   **/
  @ApiModelProperty(value = "专业ID")


  public String getMajorId() {
    return majorId;
  }

  public void setMajorId(String majorId) {
    this.majorId = majorId;
  }

  public MajorResourceVo majorName(String majorName) {
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

  public MajorResourceVo year(String year) {
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

  public MajorResourceVo collegeName(String collegeName) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MajorResourceVo majorResourceVo = (MajorResourceVo) o;
    return Objects.equals(this.majorId, majorResourceVo.majorId) &&
            Objects.equals(this.majorName, majorResourceVo.majorName) &&
            Objects.equals(this.year, majorResourceVo.year) &&
            Objects.equals(this.collegeName, majorResourceVo.collegeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(majorId, majorName, year, collegeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MajorResourceVo {\n");

    sb.append("    majorId: ").append(toIndentedString(majorId)).append("\n");
    sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
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


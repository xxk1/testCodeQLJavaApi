package com.lztech.site.viewmodel.teachingpackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TeachingPackageCourseVersionResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-24T12:14:22.074Z")


public class TeachingPackageCourseVersionResource   {
  @JsonProperty("versionNumber")
  private String versionNumber = null;

  @JsonProperty("visionTime")
  private String visionTime = null;

  @JsonProperty("filingUserId")
  private String filingUserId = null;

  @JsonProperty("filingUserName")
  private String filingUserName = null;

  public TeachingPackageCourseVersionResource versionNumber(String versionNumber) {
    this.versionNumber = versionNumber;
    return this;
  }

  /**
   * 版本号
   * @return versionNumber
  **/
  @ApiModelProperty(value = "版本号")


  public String getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(String versionNumber) {
    this.versionNumber = versionNumber;
  }

  public TeachingPackageCourseVersionResource visionTime(String visionTime) {
    this.visionTime = visionTime;
    return this;
  }

  /**
   * 归档时间
   * @return visionTime
  **/
  @ApiModelProperty(value = "归档时间")


  public String getVisionTime() {
    return visionTime;
  }

  public void setVisionTime(String visionTime) {
    this.visionTime = visionTime;
  }

  public TeachingPackageCourseVersionResource filingUserId(String filingUserId) {
    this.filingUserId = filingUserId;
    return this;
  }

  /**
   * 归档人id
   * @return filingUserId
  **/
  @ApiModelProperty(value = "归档人id")


  public String getFilingUserId() {
    return filingUserId;
  }

  public void setFilingUserId(String filingUserId) {
    this.filingUserId = filingUserId;
  }

  public TeachingPackageCourseVersionResource filingUserName(String filingUserName) {
    this.filingUserName = filingUserName;
    return this;
  }

  /**
   * 归档人姓名
   * @return filingUserName
  **/
  @ApiModelProperty(value = "归档人姓名")


  public String getFilingUserName() {
    return filingUserName;
  }

  public void setFilingUserName(String filingUserName) {
    this.filingUserName = filingUserName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeachingPackageCourseVersionResource teachingPackageCourseVersionResource = (TeachingPackageCourseVersionResource) o;
    return Objects.equals(this.versionNumber, teachingPackageCourseVersionResource.versionNumber) &&
        Objects.equals(this.visionTime, teachingPackageCourseVersionResource.visionTime) &&
        Objects.equals(this.filingUserId, teachingPackageCourseVersionResource.filingUserId) &&
        Objects.equals(this.filingUserName, teachingPackageCourseVersionResource.filingUserName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(versionNumber, visionTime, filingUserId, filingUserName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeachingPackageCourseVersionResource {\n");
    
    sb.append("    versionNumber: ").append(toIndentedString(versionNumber)).append("\n");
    sb.append("    visionTime: ").append(toIndentedString(visionTime)).append("\n");
    sb.append("    filingUserId: ").append(toIndentedString(filingUserId)).append("\n");
    sb.append("    filingUserName: ").append(toIndentedString(filingUserName)).append("\n");
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


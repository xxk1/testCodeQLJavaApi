package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-20T02:48:18.930Z")




public class CourseResourceInfo   {
  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("resourceName")
  private String resourceName = null;

  @JsonProperty("innerIp")
  private String innerIp = null;

  @JsonProperty("outerIp")
  private String outerIp = null;

  @JsonProperty("fileSavedName")
  private String fileSavedName = null;

  @JsonProperty("filePath")
  private String filePath = null;

  @JsonProperty("fileType")
  private String fileType = null;

  @JsonProperty("fileSize")
  private String fileSize = null;

  public CourseResourceInfo resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * 资源id
   * @return resourceId
  **/
  @ApiModelProperty(value = "资源id")


  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public CourseResourceInfo resourceName(String resourceName) {
    this.resourceName = resourceName;
    return this;
  }

  /**
   * 资源名称
   * @return resourceName
  **/
  @ApiModelProperty(value = "资源名称")


  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public CourseResourceInfo innerIp(String innerIp) {
    this.innerIp = innerIp;
    return this;
  }

  /**
   * 内网ip
   * @return innerIp
  **/
  @ApiModelProperty(value = "内网ip")


  public String getInnerIp() {
    return innerIp;
  }

  public void setInnerIp(String innerIp) {
    this.innerIp = innerIp;
  }

  public CourseResourceInfo outerIp(String outerIp) {
    this.outerIp = outerIp;
    return this;
  }

  /**
   * 外网ip
   * @return outerIp
  **/
  @ApiModelProperty(value = "外网ip")


  public String getOuterIp() {
    return outerIp;
  }

  public void setOuterIp(String outerIp) {
    this.outerIp = outerIp;
  }

  public CourseResourceInfo fileSavedName(String fileSavedName) {
    this.fileSavedName = fileSavedName;
    return this;
  }

  /**
   * 文件服务器存储名称
   * @return fileSavedName
  **/
  @ApiModelProperty(value = "文件服务器存储名称")


  public String getFileSavedName() {
    return fileSavedName;
  }

  public void setFileSavedName(String fileSavedName) {
    this.fileSavedName = fileSavedName;
  }

  public CourseResourceInfo filePath(String filePath) {
    this.filePath = filePath;
    return this;
  }

  /**
   * 文件路径
   * @return filePath
  **/
  @ApiModelProperty(value = "文件路径")


  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public CourseResourceInfo fileType(String fileType) {
    this.fileType = fileType;
    return this;
  }

  /**
   * 文件类型
   * @return fileType
  **/
  @ApiModelProperty(value = "文件类型")


  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public CourseResourceInfo fileSize(String fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  /**
   * 文件大小
   * @return fileSize
  **/
  @ApiModelProperty(value = "文件大小")


  public String getFileSize() {
    return fileSize;
  }

  public void setFileSize(String fileSize) {
    this.fileSize = fileSize;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseResourceInfo courseResourceInfo = (CourseResourceInfo) o;
    return Objects.equals(this.resourceId, courseResourceInfo.resourceId) &&
        Objects.equals(this.resourceName, courseResourceInfo.resourceName) &&
        Objects.equals(this.innerIp, courseResourceInfo.innerIp) &&
        Objects.equals(this.outerIp, courseResourceInfo.outerIp) &&
        Objects.equals(this.fileSavedName, courseResourceInfo.fileSavedName) &&
        Objects.equals(this.filePath, courseResourceInfo.filePath) &&
        Objects.equals(this.fileType, courseResourceInfo.fileType) &&
        Objects.equals(this.fileSize, courseResourceInfo.fileSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceId, resourceName, innerIp, outerIp, fileSavedName, filePath, fileType, fileSize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseResourceInfo {\n");
    
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
    sb.append("    innerIp: ").append(toIndentedString(innerIp)).append("\n");
    sb.append("    outerIp: ").append(toIndentedString(outerIp)).append("\n");
    sb.append("    fileSavedName: ").append(toIndentedString(fileSavedName)).append("\n");
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
    sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
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


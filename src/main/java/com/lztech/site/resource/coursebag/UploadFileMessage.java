package com.lztech.site.resource.coursebag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UploadFileMessage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T07:37:00.028Z")

public class UploadFileMessage {
  @JsonProperty("fileSize")
  private String fileSize = null;

  @JsonProperty("fileName")
  private String fileName = null;

  @JsonProperty("intranetFilePath")
  private String intranetFilePath = null;

  @JsonProperty("outerNetFilePath")
  private String outerNetFilePath = null;

  @JsonProperty("uploadedFileName")
  private String uploadedFileName = null;

  @JsonProperty("physicalPath")
  private String physicalPath = null;

  @JsonProperty("fileType")
  private String fileType = null;

  public UploadFileMessage fileSize(String fileSize) {
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

  public UploadFileMessage fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * 上传文件名称
   * @return fileName
  **/
  @ApiModelProperty(value = "上传文件名称")


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public UploadFileMessage intranetFilePath(String intranetFilePath) {
    this.intranetFilePath = intranetFilePath;
    return this;
  }

  /**
   * 内网访问地址
   * @return intranetFilePath
  **/
  @ApiModelProperty(value = "内网访问地址")


  public String getIntranetFilePath() {
    return intranetFilePath;
  }

  public void setIntranetFilePath(String intranetFilePath) {
    this.intranetFilePath = intranetFilePath;
  }

  public UploadFileMessage outerNetFilePath(String outerNetFilePath) {
    this.outerNetFilePath = outerNetFilePath;
    return this;
  }

  /**
   * 外网访问地址
   * @return outerNetFilePath
  **/
  @ApiModelProperty(value = "外网访问地址")


  public String getOuterNetFilePath() {
    return outerNetFilePath;
  }

  public void setOuterNetFilePath(String outerNetFilePath) {
    this.outerNetFilePath = outerNetFilePath;
  }

  public UploadFileMessage uploadedFileName(String uploadedFileName) {
    this.uploadedFileName = uploadedFileName;
    return this;
  }

  /**
   * 访问文件名称
   * @return uploadedFileName
  **/
  @ApiModelProperty(value = "访问文件名称")


  public String getUploadedFileName() {
    return uploadedFileName;
  }

  public void setUploadedFileName(String uploadedFileName) {
    this.uploadedFileName = uploadedFileName;
  }

  public UploadFileMessage physicalPath(String physicalPath) {
    this.physicalPath = physicalPath;
    return this;
  }

  /**
   * 文件物理路径
   * @return physicalPath
  **/
  @ApiModelProperty(value = "文件物理路径")


  public String getPhysicalPath() {
    return physicalPath;
  }

  public void setPhysicalPath(String physicalPath) {
    this.physicalPath = physicalPath;
  }

  public UploadFileMessage fileType(String fileType) {
    this.fileType = fileType;
    return this;
  }

  /**
   * 文件格式
   * @return fileType
  **/
  @ApiModelProperty(value = "文件格式")


  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadFileMessage uploadFileMessage = (UploadFileMessage) o;
    return Objects.equals(this.fileSize, uploadFileMessage.fileSize) &&
        Objects.equals(this.fileName, uploadFileMessage.fileName) &&
        Objects.equals(this.intranetFilePath, uploadFileMessage.intranetFilePath) &&
        Objects.equals(this.outerNetFilePath, uploadFileMessage.outerNetFilePath) &&
        Objects.equals(this.uploadedFileName, uploadFileMessage.uploadedFileName) &&
        Objects.equals(this.physicalPath, uploadFileMessage.physicalPath) &&
        Objects.equals(this.fileType, uploadFileMessage.fileType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileSize, fileName, intranetFilePath, outerNetFilePath, uploadedFileName, physicalPath, fileType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UploadFileMessage {\n");
    
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    intranetFilePath: ").append(toIndentedString(intranetFilePath)).append("\n");
    sb.append("    outerNetFilePath: ").append(toIndentedString(outerNetFilePath)).append("\n");
    sb.append("    uploadedFileName: ").append(toIndentedString(uploadedFileName)).append("\n");
    sb.append("    physicalPath: ").append(toIndentedString(physicalPath)).append("\n");
    sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
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


package com.lztech.site.resource.coursebag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * FileInfoVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-11T07:44:25.091Z")


public class FileInfoVo {
  @JsonProperty("filePath")
  private String filePath = null;

  @JsonProperty("fileSaveName")
  private String fileSaveName = null;

  @JsonProperty("fileType")
  private String fileType = null;

  public FileInfoVo filePath(String filePath) {
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

  public FileInfoVo fileSaveName(String fileSaveName) {
    this.fileSaveName = fileSaveName;
    return this;
  }

  /**
   * 文件保存名称
   * @return fileSaveName
  **/
  @ApiModelProperty(value = "文件保存名称")


  public String getFileSaveName() {
    return fileSaveName;
  }

  public void setFileSaveName(String fileSaveName) {
    this.fileSaveName = fileSaveName;
  }

  public FileInfoVo fileType(String fileType) {
    this.fileType = fileType;
    return this;
  }

  /**
   * 文件类型后缀
   * @return fileType
  **/
  @ApiModelProperty(value = "文件类型后缀")


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
    FileInfoVo fileInfoVo = (FileInfoVo) o;
    return Objects.equals(this.filePath, fileInfoVo.filePath) &&
        Objects.equals(this.fileSaveName, fileInfoVo.fileSaveName) &&
        Objects.equals(this.fileType, fileInfoVo.fileType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filePath, fileSaveName, fileType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileInfoVo {\n");
    
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
    sb.append("    fileSaveName: ").append(toIndentedString(fileSaveName)).append("\n");
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


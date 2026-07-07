package com.lztech.site.viewmodel.preparelessonplugin;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * PptTeachingContentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-01T03:22:33.254Z")


public class PptTeachingContentResource   {
  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("courseStructureId")
  private String courseStructureId = null;

  @JsonProperty("fileRealName")
  private String fileRealName = null;

  @JsonProperty("fileSavedName")
  private String fileSavedName = null;

  @JsonProperty("intranetFilePath")
  private String intranetFilePath = null;

  @JsonProperty("outerNetFilePath")
  private String outerNetFilePath = null;

  @JsonProperty("filePath")
  private String filePath = null;

  @JsonProperty("fileType")
  private String fileType = null;

  @JsonProperty("fileSize")
  private Integer fileSize = null;

  @JsonProperty("resourceType")
  private Integer resourceType = null;

  @JsonProperty("isCover")
  private Boolean isCover = true;

  public PptTeachingContentResource userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * 用户Id
   * @return userId
  **/
  @ApiModelProperty(value = "用户Id")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public PptTeachingContentResource userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * 用户姓名
   * @return userName
  **/
  @ApiModelProperty(value = "用户姓名")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public PptTeachingContentResource courseStructureId(String courseStructureId) {
    this.courseStructureId = courseStructureId;
    return this;
  }

  /**
   * 课程结构Id
   * @return courseStructureId
  **/
  @ApiModelProperty(value = "课程结构Id")


  public String getCourseStructureId() {
    return courseStructureId;
  }

  public void setCourseStructureId(String courseStructureId) {
    this.courseStructureId = courseStructureId;
  }

  public PptTeachingContentResource fileRealName(String fileRealName) {
    this.fileRealName = fileRealName;
    return this;
  }

  /**
   * 上传文件名称
   * @return fileRealName
  **/
  @ApiModelProperty(value = "上传文件名称")


  public String getFileRealName() {
    return fileRealName;
  }

  public void setFileRealName(String fileRealName) {
    this.fileRealName = fileRealName;
  }

  public PptTeachingContentResource fileSavedName(String fileSavedName) {
    this.fileSavedName = fileSavedName;
    return this;
  }

  /**
   * 课程名称
   * @return fileSavedName
  **/
  @ApiModelProperty(value = "课程名称")


  public String getFileSavedName() {
    return fileSavedName;
  }

  public void setFileSavedName(String fileSavedName) {
    this.fileSavedName = fileSavedName;
  }

  public PptTeachingContentResource intranetFilePath(String intranetFilePath) {
    this.intranetFilePath = intranetFilePath;
    return this;
  }

  /**
   * 内网文件Ip
   * @return intranetFilePath
  **/
  @ApiModelProperty(value = "内网文件Ip")


  public String getIntranetFilePath() {
    return intranetFilePath;
  }

  public void setIntranetFilePath(String intranetFilePath) {
    this.intranetFilePath = intranetFilePath;
  }

  public PptTeachingContentResource outerNetFilePath(String outerNetFilePath) {
    this.outerNetFilePath = outerNetFilePath;
    return this;
  }

  /**
   * 外网文件Ip
   * @return outerNetFilePath
  **/
  @ApiModelProperty(value = "外网文件Ip")


  public String getOuterNetFilePath() {
    return outerNetFilePath;
  }

  public void setOuterNetFilePath(String outerNetFilePath) {
    this.outerNetFilePath = outerNetFilePath;
  }

  public PptTeachingContentResource filePath(String filePath) {
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

  public PptTeachingContentResource fileType(String fileType) {
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

  public PptTeachingContentResource fileSize(Integer fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  /**
   * 文件大小
   * @return fileSize
  **/
  @ApiModelProperty(value = "文件大小")


  public Integer getFileSize() {
    return fileSize;
  }

  public void setFileSize(Integer fileSize) {
    this.fileSize = fileSize;
  }

  public PptTeachingContentResource resourceType(Integer resourceType) {
    this.resourceType = resourceType;
    return this;
  }

  /**
   * 资源类型
   * @return resourceType
  **/
  @ApiModelProperty(value = "资源类型")


  public Integer getResourceType() {
    return resourceType;
  }

  public void setResourceType(Integer resourceType) {
    this.resourceType = resourceType;
  }

  public PptTeachingContentResource isCover(Boolean isCover) {
    this.isCover = isCover;
    return this;
  }

  /**
   * 是否覆盖
   * @return isCover
  **/
  @ApiModelProperty(value = "是否覆盖")


  public Boolean isIsCover() {
    return isCover;
  }

  public void setIsCover(Boolean isCover) {
    this.isCover = isCover;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PptTeachingContentResource pptTeachingContentResource = (PptTeachingContentResource) o;
    return Objects.equals(this.userId, pptTeachingContentResource.userId) &&
        Objects.equals(this.userName, pptTeachingContentResource.userName) &&
        Objects.equals(this.courseStructureId, pptTeachingContentResource.courseStructureId) &&
        Objects.equals(this.fileRealName, pptTeachingContentResource.fileRealName) &&
        Objects.equals(this.fileSavedName, pptTeachingContentResource.fileSavedName) &&
        Objects.equals(this.intranetFilePath, pptTeachingContentResource.intranetFilePath) &&
        Objects.equals(this.outerNetFilePath, pptTeachingContentResource.outerNetFilePath) &&
        Objects.equals(this.filePath, pptTeachingContentResource.filePath) &&
        Objects.equals(this.fileType, pptTeachingContentResource.fileType) &&
        Objects.equals(this.fileSize, pptTeachingContentResource.fileSize) &&
        Objects.equals(this.resourceType, pptTeachingContentResource.resourceType) &&
        Objects.equals(this.isCover, pptTeachingContentResource.isCover);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userName, courseStructureId, fileRealName, fileSavedName, intranetFilePath,
            outerNetFilePath, filePath, fileType, fileSize, resourceType, isCover);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PptTeachingContentResource {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
    sb.append("    fileRealName: ").append(toIndentedString(fileRealName)).append("\n");
    sb.append("    fileSavedName: ").append(toIndentedString(fileSavedName)).append("\n");
    sb.append("    intranetFilePath: ").append(toIndentedString(intranetFilePath)).append("\n");
    sb.append("    outerNetFilePath: ").append(toIndentedString(outerNetFilePath)).append("\n");
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
    sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
    sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
    sb.append("    isCover: ").append(toIndentedString(isCover)).append("\n");
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


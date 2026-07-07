package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeFileModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-06-27T14:53:18.440+08:00")

public class CourseKnowledgeGraphNodeFileModel   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resourceTypeIndex")
  private Integer resourceTypeIndex = null;

  @JsonProperty("resourceTypeName")
  private String resourceTypeName = null;

  @JsonProperty("resourceName")
  private String resourceName = null;

  @JsonProperty("fileRealName")
  private String fileRealName = null;

  @JsonProperty("fileType")
  private String fileType = null;

  @JsonProperty("innerUrl")
  private String innerUrl = null;

  @JsonProperty("outerUrl")
  private String outerUrl = null;

  @JsonProperty("filePath")
  private String filePath = null;

  @JsonProperty("fileSize")
  private String fileSize = null;

  @JsonProperty("transcodeStatus")
  private Integer transcodeStatus = null;

  @JsonProperty("sort")
  private Integer sort = null;

  @JsonProperty("initialCreatorId")
  private String initialCreatorId = null;

  @JsonProperty("initialCreatorName")
  private String initialCreatorName = null;

  public CourseKnowledgeGraphNodeFileModel id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 附件信息id
   * @return id
  **/
  @ApiModelProperty(value = "附件信息id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CourseKnowledgeGraphNodeFileModel resourceTypeIndex(Integer resourceTypeIndex) {
    this.resourceTypeIndex = resourceTypeIndex;
    return this;
  }

  /**
   * 文件类型序号
   * @return resourceTypeIndex
  **/
  @ApiModelProperty(value = "文件类型序号")


  public Integer getResourceTypeIndex() {
    return resourceTypeIndex;
  }

  public void setResourceTypeIndex(Integer resourceTypeIndex) {
    this.resourceTypeIndex = resourceTypeIndex;
  }

  public CourseKnowledgeGraphNodeFileModel resourceTypeName(String resourceTypeName) {
    this.resourceTypeName = resourceTypeName;
    return this;
  }

  /**
   * 文件类型名称
   * @return resourceTypeName
  **/
  @ApiModelProperty(value = "文件类型名称")


  public String getResourceTypeName() {
    return resourceTypeName;
  }

  public void setResourceTypeName(String resourceTypeName) {
    this.resourceTypeName = resourceTypeName;
  }

  public CourseKnowledgeGraphNodeFileModel resourceName(String resourceName) {
    this.resourceName = resourceName;
    return this;
  }

  /**
   * 文件显示名称
   * @return resourceName
  **/
  @ApiModelProperty(value = "文件显示名称")


  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public CourseKnowledgeGraphNodeFileModel fileRealName(String fileRealName) {
    this.fileRealName = fileRealName;
    return this;
  }

  /**
   * 文件存储名称
   * @return fileRealName
  **/
  @ApiModelProperty(value = "文件存储名称")


  public String getFileRealName() {
    return fileRealName;
  }

  public void setFileRealName(String fileRealName) {
    this.fileRealName = fileRealName;
  }

  public CourseKnowledgeGraphNodeFileModel fileType(String fileType) {
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

  public CourseKnowledgeGraphNodeFileModel innerUrl(String innerUrl) {
    this.innerUrl = innerUrl;
    return this;
  }

  /**
   * 内网地址
   * @return innerUrl
  **/
  @ApiModelProperty(value = "内网地址")


  public String getInnerUrl() {
    return innerUrl;
  }

  public void setInnerUrl(String innerUrl) {
    this.innerUrl = innerUrl;
  }

  public CourseKnowledgeGraphNodeFileModel outerUrl(String outerUrl) {
    this.outerUrl = outerUrl;
    return this;
  }

  /**
   * 外网地址
   * @return outerUrl
  **/
  @ApiModelProperty(value = "外网地址")


  public String getOuterUrl() {
    return outerUrl;
  }

  public void setOuterUrl(String outerUrl) {
    this.outerUrl = outerUrl;
  }

  public CourseKnowledgeGraphNodeFileModel filePath(String filePath) {
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

  public CourseKnowledgeGraphNodeFileModel fileSize(String fileSize) {
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

  public CourseKnowledgeGraphNodeFileModel transcodeStatus(Integer transcodeStatus) {
    this.transcodeStatus = transcodeStatus;
    return this;
  }

  /**
   * 转码状态：0:不转码;1:转码中;2:转码成功;3:转码失败
   * @return transcodeStatus
  **/
  @ApiModelProperty(value = "转码状态：0:不转码;1:转码中;2:转码成功;3:转码失败")


  public Integer getTranscodeStatus() {
    return transcodeStatus;
  }

  public void setTranscodeStatus(Integer transcodeStatus) {
    this.transcodeStatus = transcodeStatus;
  }

  public CourseKnowledgeGraphNodeFileModel sort(Integer sort) {
    this.sort = sort;
    return this;
  }

  /**
   * 顺序
   * @return sort
  **/
  @ApiModelProperty(value = "顺序")


  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  public CourseKnowledgeGraphNodeFileModel initialCreatorId(String initialCreatorId) {
    this.initialCreatorId = initialCreatorId;
    return this;
  }

  /**
   * 初始创建人id
   * @return initialCreatorId
  **/
  @ApiModelProperty(value = "初始创建人id")


  public String getInitialCreatorId() {
    return initialCreatorId;
  }

  public void setInitialCreatorId(String initialCreatorId) {
    this.initialCreatorId = initialCreatorId;
  }

  public CourseKnowledgeGraphNodeFileModel initialCreatorName(String initialCreatorName) {
    this.initialCreatorName = initialCreatorName;
    return this;
  }

  /**
   * 初始创建人姓名
   * @return initialCreatorName
  **/
  @ApiModelProperty(value = "初始创建人姓名")


  public String getInitialCreatorName() {
    return initialCreatorName;
  }

  public void setInitialCreatorName(String initialCreatorName) {
    this.initialCreatorName = initialCreatorName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseKnowledgeGraphNodeFileModel courseKnowledgeGraphNodeFileModel = (CourseKnowledgeGraphNodeFileModel) o;
    return Objects.equals(this.id, courseKnowledgeGraphNodeFileModel.id) &&
        Objects.equals(this.resourceTypeIndex, courseKnowledgeGraphNodeFileModel.resourceTypeIndex) &&
        Objects.equals(this.resourceTypeName, courseKnowledgeGraphNodeFileModel.resourceTypeName) &&
        Objects.equals(this.resourceName, courseKnowledgeGraphNodeFileModel.resourceName) &&
        Objects.equals(this.fileRealName, courseKnowledgeGraphNodeFileModel.fileRealName) &&
        Objects.equals(this.fileType, courseKnowledgeGraphNodeFileModel.fileType) &&
        Objects.equals(this.innerUrl, courseKnowledgeGraphNodeFileModel.innerUrl) &&
        Objects.equals(this.outerUrl, courseKnowledgeGraphNodeFileModel.outerUrl) &&
        Objects.equals(this.filePath, courseKnowledgeGraphNodeFileModel.filePath) &&
        Objects.equals(this.fileSize, courseKnowledgeGraphNodeFileModel.fileSize) &&
        Objects.equals(this.transcodeStatus, courseKnowledgeGraphNodeFileModel.transcodeStatus) &&
        Objects.equals(this.sort, courseKnowledgeGraphNodeFileModel.sort) &&
        Objects.equals(this.initialCreatorId, courseKnowledgeGraphNodeFileModel.initialCreatorId) &&
        Objects.equals(this.initialCreatorName, courseKnowledgeGraphNodeFileModel.initialCreatorName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceTypeIndex, resourceTypeName, resourceName, fileRealName, fileType, innerUrl, outerUrl,
            filePath, fileSize, transcodeStatus, sort, initialCreatorId, initialCreatorName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseKnowledgeGraphNodeFileModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceTypeIndex: ").append(toIndentedString(resourceTypeIndex)).append("\n");
    sb.append("    resourceTypeName: ").append(toIndentedString(resourceTypeName)).append("\n");
    sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
    sb.append("    fileRealName: ").append(toIndentedString(fileRealName)).append("\n");
    sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
    sb.append("    innerUrl: ").append(toIndentedString(innerUrl)).append("\n");
    sb.append("    outerUrl: ").append(toIndentedString(outerUrl)).append("\n");
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
    sb.append("    transcodeStatus: ").append(toIndentedString(transcodeStatus)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    initialCreatorId: ").append(toIndentedString(initialCreatorId)).append("\n");
    sb.append("    initialCreatorName: ").append(toIndentedString(initialCreatorName)).append("\n");
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


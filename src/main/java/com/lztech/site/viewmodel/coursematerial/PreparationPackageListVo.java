package com.lztech.site.viewmodel.coursematerial;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * PreparationPackageListVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-12T07:42:49.504Z")


public class PreparationPackageListVo   {
  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("taskType")
  private String taskType = null;

  @JsonProperty("fileSize")
  private String fileSize = null;

  @JsonProperty("startTime")
  private String startTime = null;

  @JsonProperty("taskStatus")
  private String taskStatus = null;

  @JsonProperty("intranetFilePath")
  private String intranetFilePath = null;

  @JsonProperty("outerNetFilePath")
  private String outerNetFilePath = null;

  @JsonProperty("physicalPath")
  private String physicalPath = null;

  public PreparationPackageListVo collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院ID
   * @return collegeId
  **/
  @ApiModelProperty(value = "学院ID")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public PreparationPackageListVo collegeName(String collegeName) {
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

  public PreparationPackageListVo taskType(String taskType) {
    this.taskType = taskType;
    return this;
  }

  /**
   * 任务类型
   * @return taskType
  **/
  @ApiModelProperty(value = "任务类型")


  public String getTaskType() {
    return taskType;
  }

  public void setTaskType(String taskType) {
    this.taskType = taskType;
  }

  public PreparationPackageListVo fileSize(String fileSize) {
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

  public PreparationPackageListVo startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * 打包开始时间
   * @return startTime
  **/
  @ApiModelProperty(value = "打包开始时间")


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public PreparationPackageListVo taskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
    return this;
  }

  /**
   * 任务状态
   * @return taskStatus
  **/
  @ApiModelProperty(value = "任务状态")


  public String getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
  }

  public PreparationPackageListVo intranetFilePath(String intranetFilePath) {
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

  public PreparationPackageListVo outerNetFilePath(String outerNetFilePath) {
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

  public PreparationPackageListVo physicalPath(String physicalPath) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PreparationPackageListVo preparationPackageListVo = (PreparationPackageListVo) o;
    return Objects.equals(this.collegeId, preparationPackageListVo.collegeId) &&
        Objects.equals(this.collegeName, preparationPackageListVo.collegeName) &&
        Objects.equals(this.taskType, preparationPackageListVo.taskType) &&
        Objects.equals(this.fileSize, preparationPackageListVo.fileSize) &&
        Objects.equals(this.startTime, preparationPackageListVo.startTime) &&
        Objects.equals(this.taskStatus, preparationPackageListVo.taskStatus) &&
        Objects.equals(this.intranetFilePath, preparationPackageListVo.intranetFilePath) &&
        Objects.equals(this.outerNetFilePath, preparationPackageListVo.outerNetFilePath) &&
        Objects.equals(this.physicalPath, preparationPackageListVo.physicalPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collegeId, collegeName, taskType, fileSize, startTime, taskStatus, intranetFilePath, outerNetFilePath, physicalPath);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PreparationPackageListVo {\n");
    
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    taskStatus: ").append(toIndentedString(taskStatus)).append("\n");
    sb.append("    intranetFilePath: ").append(toIndentedString(intranetFilePath)).append("\n");
    sb.append("    outerNetFilePath: ").append(toIndentedString(outerNetFilePath)).append("\n");
    sb.append("    physicalPath: ").append(toIndentedString(physicalPath)).append("\n");
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


package com.lztech.site.resource.coursebag;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CoursePackageListVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-26T02:05:25.511Z")


public class CoursePackageListVo   {
  @JsonProperty("taskType")
  private String taskType = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("versionId")
  private String versionId = null;

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

  public CoursePackageListVo taskType(String taskType) {
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

  public CoursePackageListVo courseName(String courseName) {
    this.courseName = courseName;
    return this;
  }

  /**
   * 课程名称
   * @return courseName
  **/
  @ApiModelProperty(value = "课程名称")


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public CoursePackageListVo courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 课程ID
   * @return courseId
  **/
  @ApiModelProperty(value = "课程ID")


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public CoursePackageListVo versionId(String versionId) {
    this.versionId = versionId;
    return this;
  }

  /**
   * 版本ID
   * @return versionId
  **/
  @ApiModelProperty(value = "版本ID")


  public String getVersionId() {
    return versionId;
  }

  public void setVersionId(String versionId) {
    this.versionId = versionId;
  }

  public CoursePackageListVo fileSize(String fileSize) {
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

  public CoursePackageListVo startTime(String startTime) {
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

  public CoursePackageListVo taskStatus(String taskStatus) {
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

  public CoursePackageListVo intranetFilePath(String intranetFilePath) {
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

  public CoursePackageListVo outerNetFilePath(String outerNetFilePath) {
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

  public CoursePackageListVo physicalPath(String physicalPath) {
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
    CoursePackageListVo coursePackageListVo = (CoursePackageListVo) o;
    return Objects.equals(this.taskType, coursePackageListVo.taskType) &&
        Objects.equals(this.courseName, coursePackageListVo.courseName) &&
        Objects.equals(this.courseId, coursePackageListVo.courseId) &&
        Objects.equals(this.versionId, coursePackageListVo.versionId) &&
        Objects.equals(this.fileSize, coursePackageListVo.fileSize) &&
        Objects.equals(this.startTime, coursePackageListVo.startTime) &&
        Objects.equals(this.taskStatus, coursePackageListVo.taskStatus) &&
        Objects.equals(this.intranetFilePath, coursePackageListVo.intranetFilePath) &&
        Objects.equals(this.outerNetFilePath, coursePackageListVo.outerNetFilePath) &&
        Objects.equals(this.physicalPath, coursePackageListVo.physicalPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskType, courseName, courseId, versionId, fileSize, startTime, taskStatus, intranetFilePath, outerNetFilePath, physicalPath);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CoursePackageListVo {\n");
    
    sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
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


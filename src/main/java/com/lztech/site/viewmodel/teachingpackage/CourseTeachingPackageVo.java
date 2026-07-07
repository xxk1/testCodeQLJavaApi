package com.lztech.site.viewmodel.teachingpackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseTeachingPackageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-24T07:00:44.292Z")


public class CourseTeachingPackageVo   {
  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("courseStructureIds")
  @Valid
  private List<String> courseStructureIds = null;

  @JsonProperty("resourceIds")
  @Valid
  private List<String> resourceIds = null;

  @JsonProperty("resourceFileIds")
  @Valid
  private List<String> resourceFileIds = null;

  @JsonProperty("teachingPackageName")
  private String teachingPackageName = null;

  @JsonProperty("teachingPackageIntroduction")
  private String teachingPackageIntroduction = null;

  @JsonProperty("teachingPackageFileId")
  private String teachingPackageFileId = null;

  @JsonProperty("filePath")
  private String filePath = null;

  @JsonProperty("innerIp")
  private String innerIp = null;

  @JsonProperty("outerIp")
  private String outerIp = null;

  @JsonProperty("labelIds")
  @Valid
  private List<String> labelIds = null;

  @JsonProperty("labelNames")
  @Valid
  private List<String> labelNames = null;

  @JsonProperty("courseVersionId")
  private String courseVersionId = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("visionNumber")
  private String visionNumber = null;

  @JsonProperty("visionTime")
  private String visionTime = null;

  public CourseTeachingPackageVo userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * 操作人id
   * @return userId
  **/
  @ApiModelProperty(value = "操作人id")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public CourseTeachingPackageVo userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * 操作人姓名
   * @return userName
  **/
  @ApiModelProperty(value = "操作人姓名")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public CourseTeachingPackageVo courseStructureIds(List<String> courseStructureIds) {
    this.courseStructureIds = courseStructureIds;
    return this;
  }

  public CourseTeachingPackageVo addCourseStructureIdsItem(String courseStructureIdsItem) {
    if (this.courseStructureIds == null) {
      this.courseStructureIds = new ArrayList<String>();
    }
    this.courseStructureIds.add(courseStructureIdsItem);
    return this;
  }

  /**
   * 课程结构id
   * @return courseStructureIds
  **/
  @ApiModelProperty(value = "课程结构id")


  public List<String> getCourseStructureIds() {
    return courseStructureIds;
  }

  public void setCourseStructureIds(List<String> courseStructureIds) {
    this.courseStructureIds = courseStructureIds;
  }

  public CourseTeachingPackageVo resourceIds(List<String> resourceIds) {
    this.resourceIds = resourceIds;
    return this;
  }

  public CourseTeachingPackageVo addResourceIdsItem(String resourceIdsItem) {
    if (this.resourceIds == null) {
      this.resourceIds = new ArrayList<String>();
    }
    this.resourceIds.add(resourceIdsItem);
    return this;
  }

  /**
   * 课程资源id
   * @return resourceIds
  **/
  @ApiModelProperty(value = "课程资源id")


  public List<String> getResourceIds() {
    return resourceIds;
  }

  public void setResourceIds(List<String> resourceIds) {
    this.resourceIds = resourceIds;
  }

  public CourseTeachingPackageVo resourceFileIds(List<String> resourceFileIds) {
    this.resourceFileIds = resourceFileIds;
    return this;
  }

  public CourseTeachingPackageVo addResourceFileIdsItem(String resourceFileIdsItem) {
    if (this.resourceFileIds == null) {
      this.resourceFileIds = new ArrayList<String>();
    }
    this.resourceFileIds.add(resourceFileIdsItem);
    return this;
  }

  /**
   * 课程资源文件ID
   * @return resourceFileIds
  **/
  @ApiModelProperty(value = "课程资源文件ID")


  public List<String> getResourceFileIds() {
    return resourceFileIds;
  }

  public void setResourceFileIds(List<String> resourceFileIds) {
    this.resourceFileIds = resourceFileIds;
  }

  public CourseTeachingPackageVo teachingPackageName(String teachingPackageName) {
    this.teachingPackageName = teachingPackageName;
    return this;
  }

  /**
   * 课程包名称
   * @return teachingPackageName
  **/
  @ApiModelProperty(value = "课程包名称")


  public String getTeachingPackageName() {
    return teachingPackageName;
  }

  public void setTeachingPackageName(String teachingPackageName) {
    this.teachingPackageName = teachingPackageName;
  }

  public CourseTeachingPackageVo teachingPackageIntroduction(String teachingPackageIntroduction) {
    this.teachingPackageIntroduction = teachingPackageIntroduction;
    return this;
  }

  /**
   * 课程包简介
   * @return teachingPackageIntroduction
  **/
  @ApiModelProperty(value = "课程包简介")


  public String getTeachingPackageIntroduction() {
    return teachingPackageIntroduction;
  }

  public void setTeachingPackageIntroduction(String teachingPackageIntroduction) {
    this.teachingPackageIntroduction = teachingPackageIntroduction;
  }

  public CourseTeachingPackageVo teachingPackageFileId(String teachingPackageFileId) {
    this.teachingPackageFileId = teachingPackageFileId;
    return this;
  }

  /**
   * 课程包封面文件ID
   * @return teachingPackageFileId
  **/
  @ApiModelProperty(value = "课程包封面文件ID")


  public String getTeachingPackageFileId() {
    return teachingPackageFileId;
  }

  public void setTeachingPackageFileId(String teachingPackageFileId) {
    this.teachingPackageFileId = teachingPackageFileId;
  }

  public CourseTeachingPackageVo filePath(String filePath) {
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

  public CourseTeachingPackageVo innerIp(String innerIp) {
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

  public CourseTeachingPackageVo outerIp(String outerIp) {
    this.outerIp = outerIp;
    return this;
  }

  /**
   * 外网IP
   * @return outerIp
  **/
  @ApiModelProperty(value = "外网IP")


  public String getOuterIp() {
    return outerIp;
  }

  public void setOuterIp(String outerIp) {
    this.outerIp = outerIp;
  }

  public CourseTeachingPackageVo labelIds(List<String> labelIds) {
    this.labelIds = labelIds;
    return this;
  }

  public CourseTeachingPackageVo addLabelIdsItem(String labelIdsItem) {
    if (this.labelIds == null) {
      this.labelIds = new ArrayList<String>();
    }
    this.labelIds.add(labelIdsItem);
    return this;
  }

  /**
   * 标签ID
   * @return labelIds
  **/
  @ApiModelProperty(value = "标签ID")


  public List<String> getLabelIds() {
    return labelIds;
  }

  public void setLabelIds(List<String> labelIds) {
    this.labelIds = labelIds;
  }

  public CourseTeachingPackageVo labelNames(List<String> labelNames) {
    this.labelNames = labelNames;
    return this;
  }

  public CourseTeachingPackageVo addLabelNamesItem(String labelNamesItem) {
    if (this.labelNames == null) {
      this.labelNames = new ArrayList<String>();
    }
    this.labelNames.add(labelNamesItem);
    return this;
  }

  /**
   * 标签名称
   * @return labelNames
  **/
  @ApiModelProperty(value = "标签名称")


  public List<String> getLabelNames() {
    return labelNames;
  }

  public void setLabelNames(List<String> labelNames) {
    this.labelNames = labelNames;
  }

  public CourseTeachingPackageVo courseVersionId(String courseVersionId) {
    this.courseVersionId = courseVersionId;
    return this;
  }

  /**
   * 版本ID
   * @return courseVersionId
  **/
  @ApiModelProperty(value = "版本ID")


  public String getCourseVersionId() {
    return courseVersionId;
  }

  public void setCourseVersionId(String courseVersionId) {
    this.courseVersionId = courseVersionId;
  }

  public CourseTeachingPackageVo courseId(String courseId) {
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

  public CourseTeachingPackageVo courseName(String courseName) {
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

  public CourseTeachingPackageVo visionNumber(String visionNumber) {
    this.visionNumber = visionNumber;
    return this;
  }

  /**
   * 版本号
   * @return visionNumber
  **/
  @ApiModelProperty(value = "版本号")


  public String getVisionNumber() {
    return visionNumber;
  }

  public void setVisionNumber(String visionNumber) {
    this.visionNumber = visionNumber;
  }

  public CourseTeachingPackageVo visionTime(String visionTime) {
    this.visionTime = visionTime;
    return this;
  }

  /**
   * 版本时间
   * @return visionTime
  **/
  @ApiModelProperty(value = "版本时间")


  public String getVisionTime() {
    return visionTime;
  }

  public void setVisionTime(String visionTime) {
    this.visionTime = visionTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTeachingPackageVo courseTeachingPackageVo = (CourseTeachingPackageVo) o;
    return Objects.equals(this.userId, courseTeachingPackageVo.userId) &&
        Objects.equals(this.userName, courseTeachingPackageVo.userName) &&
        Objects.equals(this.courseStructureIds, courseTeachingPackageVo.courseStructureIds) &&
        Objects.equals(this.resourceIds, courseTeachingPackageVo.resourceIds) &&
        Objects.equals(this.resourceFileIds, courseTeachingPackageVo.resourceFileIds) &&
        Objects.equals(this.teachingPackageName, courseTeachingPackageVo.teachingPackageName) &&
        Objects.equals(this.teachingPackageIntroduction, courseTeachingPackageVo.teachingPackageIntroduction) &&
        Objects.equals(this.teachingPackageFileId, courseTeachingPackageVo.teachingPackageFileId) &&
        Objects.equals(this.filePath, courseTeachingPackageVo.filePath) &&
        Objects.equals(this.innerIp, courseTeachingPackageVo.innerIp) &&
        Objects.equals(this.outerIp, courseTeachingPackageVo.outerIp) &&
        Objects.equals(this.labelIds, courseTeachingPackageVo.labelIds) &&
        Objects.equals(this.labelNames, courseTeachingPackageVo.labelNames) &&
        Objects.equals(this.courseVersionId, courseTeachingPackageVo.courseVersionId) &&
        Objects.equals(this.courseId, courseTeachingPackageVo.courseId) &&
        Objects.equals(this.courseName, courseTeachingPackageVo.courseName) &&
        Objects.equals(this.visionNumber, courseTeachingPackageVo.visionNumber) &&
        Objects.equals(this.visionTime, courseTeachingPackageVo.visionTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userName, courseStructureIds, resourceIds, resourceFileIds,
            teachingPackageName, teachingPackageIntroduction, teachingPackageFileId, filePath,
            innerIp, outerIp, labelIds, labelNames, courseVersionId, courseId, courseName, visionNumber, visionTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTeachingPackageVo {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    courseStructureIds: ").append(toIndentedString(courseStructureIds)).append("\n");
    sb.append("    resourceIds: ").append(toIndentedString(resourceIds)).append("\n");
    sb.append("    resourceFileIds: ").append(toIndentedString(resourceFileIds)).append("\n");
    sb.append("    teachingPackageName: ").append(toIndentedString(teachingPackageName)).append("\n");
    sb.append("    teachingPackageIntroduction: ").append(toIndentedString(teachingPackageIntroduction)).append("\n");
    sb.append("    teachingPackageFileId: ").append(toIndentedString(teachingPackageFileId)).append("\n");
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
    sb.append("    innerIp: ").append(toIndentedString(innerIp)).append("\n");
    sb.append("    outerIp: ").append(toIndentedString(outerIp)).append("\n");
    sb.append("    labelIds: ").append(toIndentedString(labelIds)).append("\n");
    sb.append("    labelNames: ").append(toIndentedString(labelNames)).append("\n");
    sb.append("    courseVersionId: ").append(toIndentedString(courseVersionId)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    visionNumber: ").append(toIndentedString(visionNumber)).append("\n");
    sb.append("    visionTime: ").append(toIndentedString(visionTime)).append("\n");
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


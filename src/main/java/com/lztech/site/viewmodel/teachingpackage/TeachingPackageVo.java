package com.lztech.site.viewmodel.teachingpackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TeachingPackageVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-28T11:09:28.012Z")


public class TeachingPackageVo   {
  @JsonProperty("teachingPackageId")
  private String teachingPackageId = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("teachingPackageName")
  private String teachingPackageName = null;

  @JsonProperty("teachingPackageIntroduction")
  private String teachingPackageIntroduction = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("filePath")
  private String filePath = null;

  @JsonProperty("innerIp")
  private String innerIp = null;

  @JsonProperty("outerIp")
  private String outerIp = null;

  @JsonProperty("quoteNum")
  private Integer quoteNum = null;

  @JsonProperty("visionNumber")
  private Integer visionNumber = null;

  @JsonProperty("visionTime")
  private String visionTime = null;

  @JsonProperty("labelNames")
  @Valid
  private List<String> labelNames = null;

  @JsonProperty("courseStructureList")
  @Valid
  private List<TeachingPackageCourseStructureVo> courseStructureList = null;

  public TeachingPackageVo teachingPackageId(String teachingPackageId) {
    this.teachingPackageId = teachingPackageId;
    return this;
  }

  /**
   * 教学包ID
   * @return teachingPackageId
   **/
  @ApiModelProperty(value = "教学包ID")


  public String getTeachingPackageId() {
    return teachingPackageId;
  }

  public void setTeachingPackageId(String teachingPackageId) {
    this.teachingPackageId = teachingPackageId;
  }

  public TeachingPackageVo courseId(String courseId) {
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

  public TeachingPackageVo courseName(String courseName) {
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

  public TeachingPackageVo teachingPackageName(String teachingPackageName) {
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

  public TeachingPackageVo teachingPackageIntroduction(String teachingPackageIntroduction) {
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

  public TeachingPackageVo userId(String userId) {
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

  public TeachingPackageVo userName(String userName) {
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

  public TeachingPackageVo filePath(String filePath) {
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

  public TeachingPackageVo innerIp(String innerIp) {
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

  public TeachingPackageVo outerIp(String outerIp) {
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

  public TeachingPackageVo quoteNum(Integer quoteNum) {
    this.quoteNum = quoteNum;
    return this;
  }

  /**
   * 引用次数
   * @return quoteNum
   **/
  @ApiModelProperty(value = "引用次数")


  public Integer getQuoteNum() {
    return quoteNum;
  }

  public void setQuoteNum(Integer quoteNum) {
    this.quoteNum = quoteNum;
  }

  public TeachingPackageVo visionNumber(Integer visionNumber) {
    this.visionNumber = visionNumber;
    return this;
  }

  /**
   * 版本号
   * @return visionNumber
   **/
  @ApiModelProperty(value = "版本号")


  public Integer getVisionNumber() {
    return visionNumber;
  }

  public void setVisionNumber(Integer visionNumber) {
    this.visionNumber = visionNumber;
  }

  public TeachingPackageVo visionTime(String visionTime) {
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

  public TeachingPackageVo labelNames(List<String> labelNames) {
    this.labelNames = labelNames;
    return this;
  }

  public TeachingPackageVo addLabelNamesItem(String labelNamesItem) {
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

  public TeachingPackageVo courseStructureList(List<TeachingPackageCourseStructureVo> courseStructureList) {
    this.courseStructureList = courseStructureList;
    return this;
  }

  public TeachingPackageVo addCourseStructureListItem(TeachingPackageCourseStructureVo courseStructureListItem) {
    if (this.courseStructureList == null) {
      this.courseStructureList = new ArrayList<TeachingPackageCourseStructureVo>();
    }
    this.courseStructureList.add(courseStructureListItem);
    return this;
  }

  /**
   * 课程结构
   * @return courseStructureList
   **/
  @ApiModelProperty(value = "课程结构")

  @Valid

  public List<TeachingPackageCourseStructureVo> getCourseStructureList() {
    return courseStructureList;
  }

  public void setCourseStructureList(List<TeachingPackageCourseStructureVo> courseStructureList) {
    this.courseStructureList = courseStructureList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeachingPackageVo teachingPackageVo = (TeachingPackageVo) o;
    return Objects.equals(this.teachingPackageId, teachingPackageVo.teachingPackageId) &&
            Objects.equals(this.courseId, teachingPackageVo.courseId) &&
            Objects.equals(this.courseName, teachingPackageVo.courseName) &&
            Objects.equals(this.teachingPackageName, teachingPackageVo.teachingPackageName) &&
            Objects.equals(this.teachingPackageIntroduction, teachingPackageVo.teachingPackageIntroduction) &&
            Objects.equals(this.userId, teachingPackageVo.userId) &&
            Objects.equals(this.userName, teachingPackageVo.userName) &&
            Objects.equals(this.filePath, teachingPackageVo.filePath) &&
            Objects.equals(this.innerIp, teachingPackageVo.innerIp) &&
            Objects.equals(this.outerIp, teachingPackageVo.outerIp) &&
            Objects.equals(this.quoteNum, teachingPackageVo.quoteNum) &&
            Objects.equals(this.visionNumber, teachingPackageVo.visionNumber) &&
            Objects.equals(this.visionTime, teachingPackageVo.visionTime) &&
            Objects.equals(this.labelNames, teachingPackageVo.labelNames) &&
            Objects.equals(this.courseStructureList, teachingPackageVo.courseStructureList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teachingPackageId, courseId, courseName, teachingPackageName, teachingPackageIntroduction, userId,
            userName, filePath, innerIp, outerIp, quoteNum, visionNumber, visionTime, labelNames, courseStructureList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeachingPackageVo {\n");

    sb.append("    teachingPackageId: ").append(toIndentedString(teachingPackageId)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    teachingPackageName: ").append(toIndentedString(teachingPackageName)).append("\n");
    sb.append("    teachingPackageIntroduction: ").append(toIndentedString(teachingPackageIntroduction)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
    sb.append("    innerIp: ").append(toIndentedString(innerIp)).append("\n");
    sb.append("    outerIp: ").append(toIndentedString(outerIp)).append("\n");
    sb.append("    quoteNum: ").append(toIndentedString(quoteNum)).append("\n");
    sb.append("    visionNumber: ").append(toIndentedString(visionNumber)).append("\n");
    sb.append("    visionTime: ").append(toIndentedString(visionTime)).append("\n");
    sb.append("    labelNames: ").append(toIndentedString(labelNames)).append("\n");
    sb.append("    courseStructureList: ").append(toIndentedString(courseStructureList)).append("\n");
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


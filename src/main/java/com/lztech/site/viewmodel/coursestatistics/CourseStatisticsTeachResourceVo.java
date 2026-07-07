package com.lztech.site.viewmodel.coursestatistics;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseStatisticsTeachResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-14T09:58:23.179Z")


public class CourseStatisticsTeachResourceVo   {
  @JsonProperty("teacherName")
  private String teacherName = null;

  @JsonProperty("teacherId")
  private String teacherId = null;

  @JsonProperty("jobTitle")
  private String jobTitle = null;

  @JsonProperty("avatarPath")
  private String avatarPath = null;

  @JsonProperty("avatarInnerUrl")
  private String avatarInnerUrl = null;

  @JsonProperty("avatarOuterUrl")
  private String avatarOuterUrl = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("schoolName")
  private String schoolName = null;

  @JsonProperty("resourceNum")
  private String resourceNum = null;

  @JsonProperty("useNum")
  private String useNum = null;

  public CourseStatisticsTeachResourceVo teacherName(String teacherName) {
    this.teacherName = teacherName;
    return this;
  }

  /**
   * 老师名称
   * @return teacherName
  **/
  @ApiModelProperty(value = "老师名称")


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public CourseStatisticsTeachResourceVo teacherId(String teacherId) {
    this.teacherId = teacherId;
    return this;
  }

  /**
   * 老师账号ID
   * @return teacherId
  **/
  @ApiModelProperty(value = "老师账号ID")


  public String getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
  }

  public CourseStatisticsTeachResourceVo jobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  /**
   * 职称
   * @return jobTitle
  **/
  @ApiModelProperty(value = "职称")


  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public CourseStatisticsTeachResourceVo avatarPath(String avatarPath) {
    this.avatarPath = avatarPath;
    return this;
  }

  /**
   * 头像路径
   * @return avatarPath
  **/
  @ApiModelProperty(value = "头像路径")


  public String getAvatarPath() {
    return avatarPath;
  }

  public void setAvatarPath(String avatarPath) {
    this.avatarPath = avatarPath;
  }

  public CourseStatisticsTeachResourceVo avatarInnerUrl(String avatarInnerUrl) {
    this.avatarInnerUrl = avatarInnerUrl;
    return this;
  }

  /**
   * 头像内网地址
   * @return avatarInnerUrl
  **/
  @ApiModelProperty(value = "头像内网地址")


  public String getAvatarInnerUrl() {
    return avatarInnerUrl;
  }

  public void setAvatarInnerUrl(String avatarInnerUrl) {
    this.avatarInnerUrl = avatarInnerUrl;
  }

  public CourseStatisticsTeachResourceVo avatarOuterUrl(String avatarOuterUrl) {
    this.avatarOuterUrl = avatarOuterUrl;
    return this;
  }

  /**
   * 头像外网地址
   * @return avatarOuterUrl
  **/
  @ApiModelProperty(value = "头像外网地址")


  public String getAvatarOuterUrl() {
    return avatarOuterUrl;
  }

  public void setAvatarOuterUrl(String avatarOuterUrl) {
    this.avatarOuterUrl = avatarOuterUrl;
  }

  public CourseStatisticsTeachResourceVo collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院id
   * @return collegeId
  **/
  @ApiModelProperty(value = "学院id")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public CourseStatisticsTeachResourceVo schoolName(String schoolName) {
    this.schoolName = schoolName;
    return this;
  }

  /**
   * 学校名称
   * @return schoolName
  **/
  @ApiModelProperty(value = "学校名称")


  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  public CourseStatisticsTeachResourceVo resourceNum(String resourceNum) {
    this.resourceNum = resourceNum;
    return this;
  }

  /**
   * 资源数量
   * @return resourceNum
  **/
  @ApiModelProperty(value = "资源数量")


  public String getResourceNum() {
    return resourceNum;
  }

  public void setResourceNum(String resourceNum) {
    this.resourceNum = resourceNum;
  }

  public CourseStatisticsTeachResourceVo useNum(String useNum) {
    this.useNum = useNum;
    return this;
  }

  /**
   * 使用数量
   * @return useNum
  **/
  @ApiModelProperty(value = "使用数量")


  public String getUseNum() {
    return useNum;
  }

  public void setUseNum(String useNum) {
    this.useNum = useNum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseStatisticsTeachResourceVo courseStatisticsTeachResourceVo = (CourseStatisticsTeachResourceVo) o;
    return Objects.equals(this.teacherName, courseStatisticsTeachResourceVo.teacherName) &&
        Objects.equals(this.teacherId, courseStatisticsTeachResourceVo.teacherId) &&
        Objects.equals(this.jobTitle, courseStatisticsTeachResourceVo.jobTitle) &&
        Objects.equals(this.avatarPath, courseStatisticsTeachResourceVo.avatarPath) &&
        Objects.equals(this.avatarInnerUrl, courseStatisticsTeachResourceVo.avatarInnerUrl) &&
        Objects.equals(this.avatarOuterUrl, courseStatisticsTeachResourceVo.avatarOuterUrl) &&
        Objects.equals(this.collegeId, courseStatisticsTeachResourceVo.collegeId) &&
        Objects.equals(this.schoolName, courseStatisticsTeachResourceVo.schoolName) &&
        Objects.equals(this.resourceNum, courseStatisticsTeachResourceVo.resourceNum) &&
        Objects.equals(this.useNum, courseStatisticsTeachResourceVo.useNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teacherName, teacherId, jobTitle, avatarPath, avatarInnerUrl, avatarOuterUrl, collegeId, schoolName, resourceNum, useNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseStatisticsTeachResourceVo {\n");
    
    sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
    sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
    sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
    sb.append("    avatarPath: ").append(toIndentedString(avatarPath)).append("\n");
    sb.append("    avatarInnerUrl: ").append(toIndentedString(avatarInnerUrl)).append("\n");
    sb.append("    avatarOuterUrl: ").append(toIndentedString(avatarOuterUrl)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    schoolName: ").append(toIndentedString(schoolName)).append("\n");
    sb.append("    resourceNum: ").append(toIndentedString(resourceNum)).append("\n");
    sb.append("    useNum: ").append(toIndentedString(useNum)).append("\n");
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


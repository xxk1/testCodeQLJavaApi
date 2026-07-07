package com.lztech.site.viewmodel.coursemanagement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;


/**
 * Userbaseinfoandcollegevo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-07T09:23:59.932Z")


public class Userbaseinfoandcollegevo   {
  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userNo")
  private String userNo = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("collegeName")
  private String collegeName = null;

  @JsonProperty("position")
  private String position = null;

  @JsonProperty("gender")
  private Integer gender = null;

  @JsonProperty("mobile")
  private String mobile = null;

  @JsonProperty("jobTitle")
  private String jobTitle = null;

  @JsonProperty("avatarInnerUrl")
  private String avatarInnerUrl = null;

  @JsonProperty("avatarOuterUrl")
  private String avatarOuterUrl = null;

  @JsonProperty("avatarPath")
  private String avatarPath = null;

  @JsonProperty("jobTitleOptions")
  @Valid
  private List<String> jobTitleOptions = null;

  public Userbaseinfoandcollegevo userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * 用户id
   * @return userId
   **/
  @ApiModelProperty(value = "用户id")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Userbaseinfoandcollegevo userNo(String userNo) {
    this.userNo = userNo;
    return this;
  }

  /**
   * 用户工号/学号
   * @return userNo
   **/
  @ApiModelProperty(value = "用户工号/学号")


  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }

  public Userbaseinfoandcollegevo userName(String userName) {
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

  public Userbaseinfoandcollegevo collegeId(String collegeId) {
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

  public Userbaseinfoandcollegevo collegeName(String collegeName) {
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

  public Userbaseinfoandcollegevo position(String position) {
    this.position = position;
    return this;
  }

  /**
   * 职务
   * @return position
   **/
  @ApiModelProperty(value = "职务")


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Userbaseinfoandcollegevo gender(Integer gender) {
    this.gender = gender;
    return this;
  }

  /**
   * 性别（0：未知；1：男；2：女）
   * @return gender
   **/
  @ApiModelProperty(value = "性别（0：未知；1：男；2：女）")


  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Userbaseinfoandcollegevo mobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  /**
   * 手机号
   * @return mobile
   **/
  @ApiModelProperty(value = "手机号")


  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Userbaseinfoandcollegevo jobTitle(String jobTitle) {
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

  public Userbaseinfoandcollegevo avatarInnerUrl(String avatarInnerUrl) {
    this.avatarInnerUrl = avatarInnerUrl;
    return this;
  }

  /**
   * 头像内网url
   * @return avatarInnerUrl
   **/
  @ApiModelProperty(value = "头像内网url")


  public String getAvatarInnerUrl() {
    return avatarInnerUrl;
  }

  public void setAvatarInnerUrl(String avatarInnerUrl) {
    this.avatarInnerUrl = avatarInnerUrl;
  }

  public Userbaseinfoandcollegevo avatarOuterUrl(String avatarOuterUrl) {
    this.avatarOuterUrl = avatarOuterUrl;
    return this;
  }

  /**
   * 头像外网url
   * @return avatarOuterUrl
   **/
  @ApiModelProperty(value = "头像外网url")


  public String getAvatarOuterUrl() {
    return avatarOuterUrl;
  }

  public void setAvatarOuterUrl(String avatarOuterUrl) {
    this.avatarOuterUrl = avatarOuterUrl;
  }

  public Userbaseinfoandcollegevo avatarPath(String avatarPath) {
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

  public Userbaseinfoandcollegevo jobTitleOptions(List<String> jobTitleOptions) {
    this.jobTitleOptions = jobTitleOptions;
    return this;
  }

  public Userbaseinfoandcollegevo addJobTitleOptionsItem(String jobTitleOptionsItem) {
    if (this.jobTitleOptions == null) {
      this.jobTitleOptions = new ArrayList<String>();
    }
    this.jobTitleOptions.add(jobTitleOptionsItem);
    return this;
  }

  /**
   * 职称下拉列表数据
   * @return jobTitleOptions
   **/
  @ApiModelProperty(value = "职称下拉列表数据")


  public List<String> getJobTitleOptions() {
    return jobTitleOptions;
  }

  public void setJobTitleOptions(List<String> jobTitleOptions) {
    this.jobTitleOptions = jobTitleOptions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Userbaseinfoandcollegevo userbaseinfoandcollegevo = (Userbaseinfoandcollegevo) o;
    return Objects.equals(this.userId, userbaseinfoandcollegevo.userId) &&
            Objects.equals(this.userNo, userbaseinfoandcollegevo.userNo) &&
            Objects.equals(this.userName, userbaseinfoandcollegevo.userName) &&
            Objects.equals(this.collegeId, userbaseinfoandcollegevo.collegeId) &&
            Objects.equals(this.collegeName, userbaseinfoandcollegevo.collegeName) &&
            Objects.equals(this.position, userbaseinfoandcollegevo.position) &&
            Objects.equals(this.gender, userbaseinfoandcollegevo.gender) &&
            Objects.equals(this.mobile, userbaseinfoandcollegevo.mobile) &&
            Objects.equals(this.jobTitle, userbaseinfoandcollegevo.jobTitle) &&
            Objects.equals(this.avatarInnerUrl, userbaseinfoandcollegevo.avatarInnerUrl) &&
            Objects.equals(this.avatarOuterUrl, userbaseinfoandcollegevo.avatarOuterUrl) &&
            Objects.equals(this.avatarPath, userbaseinfoandcollegevo.avatarPath) &&
            Objects.equals(this.jobTitleOptions, userbaseinfoandcollegevo.jobTitleOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userNo, userName, collegeId, collegeName,
            position, gender, mobile, jobTitle, avatarInnerUrl,
            avatarOuterUrl, avatarPath, jobTitleOptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Userbaseinfoandcollegevo {\n");

    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    mobile: ").append(toIndentedString(mobile)).append("\n");
    sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
    sb.append("    avatarInnerUrl: ").append(toIndentedString(avatarInnerUrl)).append("\n");
    sb.append("    avatarOuterUrl: ").append(toIndentedString(avatarOuterUrl)).append("\n");
    sb.append("    avatarPath: ").append(toIndentedString(avatarPath)).append("\n");
    sb.append("    jobTitleOptions: ").append(toIndentedString(jobTitleOptions)).append("\n");
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


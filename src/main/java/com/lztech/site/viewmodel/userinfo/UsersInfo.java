package com.lztech.site.viewmodel.userinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UsersInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-10-08T09:38:02.502Z")


public class UsersInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("openId")
  private String openId = null;

  @JsonProperty("userNo")
  private String userNo = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("collegeCode")
  private String collegeCode = null;

  @JsonProperty("college")
  private String college = null;

  @JsonProperty("eamil")
  private String eamil = null;

  @JsonProperty("avatarPath")
  private String avatarPath = null;

  @JsonProperty("classId")
  private String classId = null;

  @JsonProperty("className")
  private String className = null;

  @JsonProperty("majorName")
  private String majorName = null;

  @JsonProperty("gender")
  private String gender = null;

  @JsonProperty("updateTime")
  private String updateTime = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("classNickName")
  private String classNickName = null;

  @JsonProperty("groupNo")
  private String groupNo = null;

  @JsonProperty("avatarInnerUrl")
  private String avatarInnerUrl = null;

  @JsonProperty("avatarOuterUrl")
  private String avatarOuterUrl = null;

  @JsonProperty("grade")
  private String grade = null;

  @JsonProperty("studentIdentityCode")
  private String studentIdentityCode = null;

  @JsonProperty("studentIdentityName")
  private String studentIdentityName = null;

  public UsersInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 用户id
   * @return id
  **/
  @ApiModelProperty(value = "用户id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UsersInfo openId(String openId) {
    this.openId = openId;
    return this;
  }

  /**
   * 微信账号
   * @return openId
  **/
  @ApiModelProperty(value = "微信账号")


  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public UsersInfo userNo(String userNo) {
    this.userNo = userNo;
    return this;
  }

  /**
   * 用户学号
   * @return userNo
  **/
  @ApiModelProperty(value = "用户学号")


  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }

  public UsersInfo userName(String userName) {
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

  public UsersInfo collegeId(String collegeId) {
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

  public UsersInfo collegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
    return this;
  }

  /**
   * 学院编号
   * @return collegeCode
   **/
  @ApiModelProperty(value = "学院编号")


  public String getCollegeCode() {
    return collegeCode;
  }

  public void setCollegeCode(String collegeCode) {
    this.collegeCode = collegeCode;
  }

  public UsersInfo college(String college) {
    this.college = college;
    return this;
  }

  /**
   * 学院
   * @return college
  **/
  @ApiModelProperty(value = "学院")


  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public UsersInfo eamil(String eamil) {
    this.eamil = eamil;
    return this;
  }

  /**
   * 邮箱
   * @return eamil
  **/
  @ApiModelProperty(value = "邮箱")


  public String getEamil() {
    return eamil;
  }

  public void setEamil(String eamil) {
    this.eamil = eamil;
  }

  public UsersInfo avatarPath(String avatarPath) {
    this.avatarPath = avatarPath;
    return this;
  }

  /**
   * 用户头像
   * @return avatarPath
  **/
  @ApiModelProperty(value = "用户头像")


  public String getAvatarPath() {
    return avatarPath;
  }

  public void setAvatarPath(String avatarPath) {
    this.avatarPath = avatarPath;
  }

  public UsersInfo classId(String classId) {
    this.classId = classId;
    return this;
  }

  /**
   * 行政班id
   * @return classId
   **/
  @ApiModelProperty(value = "行政班id")


  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }

  public UsersInfo className(String className) {
    this.className = className;
    return this;
  }

  /**
   * 行政班
   * @return className
  **/
  @ApiModelProperty(value = "行政班")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public UsersInfo majorName(String majorName) {
    this.majorName = majorName;
    return this;
  }

  /**
   * 专业
   * @return majorName
  **/
  @ApiModelProperty(value = "专业")


  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public UsersInfo gender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * 性别 0=未知 1=男 2=女
   * @return gender
  **/
  @ApiModelProperty(value = "性别 0=未知 1=男 2=女")


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public UsersInfo updateTime(String updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  /**
   * 学生加入班级时间
   * @return updateTime
  **/
  @ApiModelProperty(value = "学生加入班级时间")


  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public UsersInfo createTime(String createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * 学生最初加入班级时间
   * @return createTime
  **/
  @ApiModelProperty(value = "学生最初加入班级时间")


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public UsersInfo classNickName(String classNickName) {
    this.classNickName = classNickName;
    return this;
  }

  /**
   * 班级昵称
   * @return classNickName
  **/
  @ApiModelProperty(value = "班级昵称")


  public String getClassNickName() {
    return classNickName;
  }

  public void setClassNickName(String classNickName) {
    this.classNickName = classNickName;
  }

  public UsersInfo groupNo(String groupNo) {
    this.groupNo = groupNo;
    return this;
  }

  /**
   * 班级编号
   * @return groupNo
  **/
  @ApiModelProperty(value = "班级编号")


  public String getGroupNo() {
    return groupNo;
  }

  public void setGroupNo(String groupNo) {
    this.groupNo = groupNo;
  }

  public UsersInfo avatarInnerUrl(String avatarInnerUrl) {
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

  public UsersInfo avatarOuterUrl(String avatarOuterUrl) {
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

  public UsersInfo grade(String grade) {
    this.grade = grade;
    return this;
  }

  /**
   * 年级
   * @return grade
  **/
  @ApiModelProperty(value = "年级")


  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public UsersInfo studentIdentityCode(String studentIdentityCode) {
    this.studentIdentityCode = studentIdentityCode;
    return this;
  }

  /**
   * 学生身份编号
   * @return studentIdentityCode
   **/
  @ApiModelProperty(value = "学生身份编号")


  public String getStudentIdentityCode() {
    return studentIdentityCode;
  }

  public void setStudentIdentityCode(String studentIdentityCode) {
    this.studentIdentityCode = studentIdentityCode;
  }
  public UsersInfo studentIdentityName(String studentIdentityName) {
    this.studentIdentityName = studentIdentityName;
    return this;
  }

  /**
   * 学生身份名称
   * @return studentIdentityName
   **/
  @ApiModelProperty(value = "学生身份名称")


  public String getStudentIdentityName() {
    return studentIdentityName;
  }

  public void setStudentIdentityName(String studentIdentityName) {
    this.studentIdentityName = studentIdentityName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsersInfo usersInfo = (UsersInfo) o;
    return Objects.equals(this.id, usersInfo.id) &&
        Objects.equals(this.openId, usersInfo.openId) &&
        Objects.equals(this.userNo, usersInfo.userNo) &&
        Objects.equals(this.userName, usersInfo.userName) &&
        Objects.equals(this.collegeId, usersInfo.collegeId) &&
        Objects.equals(this.collegeCode,usersInfo.collegeCode) &&
        Objects.equals(this.college, usersInfo.college) &&
        Objects.equals(this.eamil, usersInfo.eamil) &&
        Objects.equals(this.avatarPath, usersInfo.avatarPath) &&
        Objects.equals(this.classId, usersInfo.classId) &&
        Objects.equals(this.className, usersInfo.className) &&
        Objects.equals(this.majorName, usersInfo.majorName) &&
        Objects.equals(this.gender, usersInfo.gender) &&
        Objects.equals(this.updateTime, usersInfo.updateTime) &&
        Objects.equals(this.createTime, usersInfo.createTime) &&
        Objects.equals(this.classNickName, usersInfo.classNickName) &&
        Objects.equals(this.groupNo, usersInfo.groupNo) &&
        Objects.equals(this.avatarInnerUrl, usersInfo.avatarInnerUrl) &&
        Objects.equals(this.avatarOuterUrl, usersInfo.avatarOuterUrl) &&
        Objects.equals(this.grade, usersInfo.grade) &&
        Objects.equals(this.studentIdentityCode,usersInfo.studentIdentityCode) &&
        Objects.equals(this.studentIdentityName,usersInfo.studentIdentityName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, openId, userNo, userName, collegeId,collegeCode,
            college, eamil, avatarPath,classId, className, majorName, gender, updateTime,
            createTime, classNickName, groupNo, avatarInnerUrl, avatarOuterUrl, grade,studentIdentityCode,studentIdentityName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsersInfo {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    openId: ").append(toIndentedString(openId)).append("\n");
    sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
    sb.append("    college: ").append(toIndentedString(college)).append("\n");
    sb.append("    eamil: ").append(toIndentedString(eamil)).append("\n");
    sb.append("    avatarPath: ").append(toIndentedString(avatarPath)).append("\n");
    sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
    sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
    sb.append("    avatarInnerUrl: ").append(toIndentedString(avatarInnerUrl)).append("\n");
    sb.append("    avatarOuterUrl: ").append(toIndentedString(avatarOuterUrl)).append("\n");
    sb.append("    grade: ").append(toIndentedString(grade)).append("\n");
    sb.append("    studentIdentityCode: ").append(toIndentedString(studentIdentityCode)).append("\n");
    sb.append("    studentIdentityName: ").append(toIndentedString(studentIdentityName)).append("\n");
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


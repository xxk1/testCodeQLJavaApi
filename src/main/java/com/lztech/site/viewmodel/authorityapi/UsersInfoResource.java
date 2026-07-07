package com.lztech.site.viewmodel.authorityapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UsersInfoResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-10-08T08:48:27.212Z")


public class UsersInfoResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("openId")
    private String openId = null;

    @JsonProperty("userNo")
    private String userNo = null;

    @JsonProperty("userRealName")
    private String userRealName = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("avatarPath")
    private String avatarPath = null;

    @JsonProperty("avatarInnerUrl")
    private String avatarInnerUrl = null;

    @JsonProperty("avatarOuterUrl")
    private String avatarOuterUrl = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("majorName")
    private String majorName = null;

    @JsonProperty("gender")
    private String gender = null;

    @JsonProperty("jobTitle")
    private String jobTitle = null;

    @JsonProperty("grade")
    private String grade = null;


    @JsonProperty("classId")
    private String classId = null;



    public UsersInfoResource classId(String classId) {
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

    public UsersInfoResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 用户id
     *
     * @return id
     **/
    @ApiModelProperty(value = "用户id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UsersInfoResource openId(String openId) {
        this.openId = openId;
        return this;
    }

    /**
     * 微信小程openId
     *
     * @return openId
     **/
    @ApiModelProperty(value = "微信小程openId")


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public UsersInfoResource userNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    /**
     * 用户学号
     *
     * @return userNo
     **/
    @ApiModelProperty(value = "用户学号")


    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public UsersInfoResource userRealName(String userRealName) {
        this.userRealName = userRealName;
        return this;
    }

    /**
     * 用户姓名
     *
     * @return userRealName
     **/
    @ApiModelProperty(value = "用户姓名")


    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public UsersInfoResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public UsersInfoResource collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 学院编号
     *
     * @return collegeCode
     **/
    @ApiModelProperty(value = "学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }


    public UsersInfoResource collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public UsersInfoResource email(String email) {
        this.email = email;
        return this;
    }

    /**
     * 邮箱
     *
     * @return email
     **/
    @ApiModelProperty(value = "邮箱")


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsersInfoResource avatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        return this;
    }

    /**
     * 头像路径
     *
     * @return avatarPath
     **/
    @ApiModelProperty(value = "头像路径")


    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public UsersInfoResource avatarInnerUrl(String avatarInnerUrl) {
        this.avatarInnerUrl = avatarInnerUrl;
        return this;
    }

    /**
     * 头像内网url
     *
     * @return avatarInnerUrl
     **/
    @ApiModelProperty(value = "头像内网url")


    public String getAvatarInnerUrl() {
        return avatarInnerUrl;
    }

    public void setAvatarInnerUrl(String avatarInnerUrl) {
        this.avatarInnerUrl = avatarInnerUrl;
    }

    public UsersInfoResource avatarOuterUrl(String avatarOuterUrl) {
        this.avatarOuterUrl = avatarOuterUrl;
        return this;
    }

    /**
     * 头像外网url
     *
     * @return avatarOuterUrl
     **/
    @ApiModelProperty(value = "头像外网url")


    public String getAvatarOuterUrl() {
        return avatarOuterUrl;
    }

    public void setAvatarOuterUrl(String avatarOuterUrl) {
        this.avatarOuterUrl = avatarOuterUrl;
    }

    public UsersInfoResource className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 行政班
     *
     * @return className
     **/
    @ApiModelProperty(value = "行政班")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public UsersInfoResource majorName(String majorName) {
        this.majorName = majorName;
        return this;
    }

    /**
     * 专业
     *
     * @return majorName
     **/
    @ApiModelProperty(value = "专业")


    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public UsersInfoResource gender(String gender) {
        this.gender = gender;
        return this;
    }

    /**
     * 性别 0=未知 1=男 2=女
     *
     * @return gender
     **/
    @ApiModelProperty(value = "性别 0=未知 1=男 2=女")


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UsersInfoResource jobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    /**
     * 职称
     *
     * @return jobTitle
     **/
    @ApiModelProperty(value = "职称")


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public UsersInfoResource grade(String grade) {
        this.grade = grade;
        return this;
    }

    /**
     * 年级
     *
     * @return grade
     **/
    @ApiModelProperty(value = "年级")


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsersInfoResource usersInfoResource = (UsersInfoResource) o;
        return Objects.equals(this.id, usersInfoResource.id) &&
                Objects.equals(this.openId, usersInfoResource.openId) &&
                Objects.equals(this.userNo, usersInfoResource.userNo) &&
                Objects.equals(this.userRealName, usersInfoResource.userRealName) &&
                Objects.equals(this.collegeName, usersInfoResource.collegeName) &&
                Objects.equals(this.collegeCode, usersInfoResource.collegeCode) &&
                Objects.equals(this.collegeId, usersInfoResource.collegeId) &&
                Objects.equals(this.email, usersInfoResource.email) &&
                Objects.equals(this.avatarPath, usersInfoResource.avatarPath) &&
                Objects.equals(this.avatarInnerUrl, usersInfoResource.avatarInnerUrl) &&
                Objects.equals(this.avatarOuterUrl, usersInfoResource.avatarOuterUrl) &&
                Objects.equals(this.className, usersInfoResource.className) &&
                Objects.equals(this.majorName, usersInfoResource.majorName) &&
                Objects.equals(this.gender, usersInfoResource.gender) &&
                Objects.equals(this.jobTitle, usersInfoResource.jobTitle) &&
                Objects.equals(this.grade, usersInfoResource.grade) &&
                Objects.equals(this.classId, usersInfoResource.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, openId, userNo, userRealName, collegeName,collegeCode,
                collegeId, email, avatarPath, avatarInnerUrl, avatarOuterUrl,
                className, majorName, gender, jobTitle, grade,classId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UsersInfoResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    openId: ").append(toIndentedString(openId)).append("\n");
        sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
        sb.append("    userRealName: ").append(toIndentedString(userRealName)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    avatarPath: ").append(toIndentedString(avatarPath)).append("\n");
        sb.append("    avatarInnerUrl: ").append(toIndentedString(avatarInnerUrl)).append("\n");
        sb.append("    avatarOuterUrl: ").append(toIndentedString(avatarOuterUrl)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
        sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
        sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
        sb.append("    grade: ").append(toIndentedString(grade)).append("\n");
        sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
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


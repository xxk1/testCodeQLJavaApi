package com.lztech.site.viewmodel.authorityapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UserResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-01T03:27:48.008Z")

public class UserResource {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("password")
    private String password = null;

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("userAccountNo")
    private String userAccountNo = null;

    @JsonProperty("userNo")
    private String userNo = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("userType")
    private Integer userType = null;

    @JsonProperty("userCardNo")
    private String userCardNo = null;

    @JsonProperty("userStatus")
    private Integer userStatus = null;

    @JsonProperty("administrativeClassId")
    private String administrativeClassId = null;

    @JsonProperty("administrativeClassName")
    private String administrativeClassName = null;

    @JsonProperty("avatarPath")
    private String avatarPath = null;

    @JsonProperty("avatarInnerUrl")
    private String avatarInnerUrl = null;

    @JsonProperty("avatarOuterUrl")
    private String avatarOuterUrl = null;

    @JsonProperty("gender")
    private Integer gender = null;

    @JsonProperty("majorId")
    private String majorId = null;

    @JsonProperty("majorName")
    private String majorName = null;

    @JsonProperty("educationType")
    private Integer educationType = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("mobile")
    private String mobile = null;

    @JsonProperty("jobTitle")
    private String jobTitle = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("schoolName")
    private String schoolName = null;

    public UserResource userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 用户id
     *
     * @return userId
     **/
    @ApiModelProperty(value = "用户id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserResource password(String password) {
        this.password = password;
        return this;
    }

    /**
     * 用户密码
     *
     * @return password
     **/
    @ApiModelProperty(value = "用户密码")


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserResource source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 用户来源（0：数据对接；1：系统录入；2：外部导入）
     *
     * @return source
     **/
    @ApiModelProperty(value = "用户来源（0：数据对接；1：系统录入；2：外部导入）")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public UserResource userAccountNo(String userAccountNo) {
        this.userAccountNo = userAccountNo;
        return this;
    }

    /**
     * 用户账号
     *
     * @return userAccountNo
     **/
    @ApiModelProperty(value = "用户账号")


    public String getUserAccountNo() {
        return userAccountNo;
    }

    public void setUserAccountNo(String userAccountNo) {
        this.userAccountNo = userAccountNo;
    }

    public UserResource userNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    /**
     * 用户工号/学号
     *
     * @return userNo
     **/
    @ApiModelProperty(value = "用户工号/学号")


    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public UserResource userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 用户姓名
     *
     * @return userName
     **/
    @ApiModelProperty(value = "用户姓名")


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserResource userType(Integer userType) {
        this.userType = userType;
        return this;
    }

    /**
     * 用户类型（0老师；1学生）
     *
     * @return userType
     **/
    @ApiModelProperty(value = "用户类型（0老师；1学生）")


    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public UserResource userCardNo(String userCardNo) {
        this.userCardNo = userCardNo;
        return this;
    }

    /**
     * 用户卡号
     *
     * @return userCardNo
     **/
    @ApiModelProperty(value = "用户卡号")


    public String getUserCardNo() {
        return userCardNo;
    }

    public void setUserCardNo(String userCardNo) {
        this.userCardNo = userCardNo;
    }

    public UserResource userStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    /**
     * 用户状态（0：启用；1：禁用）
     *
     * @return userStatus
     **/
    @ApiModelProperty(value = "用户状态（0：启用；1：禁用）")


    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public UserResource administrativeClassId(String administrativeClassId) {
        this.administrativeClassId = administrativeClassId;
        return this;
    }

    /**
     * 行政班id
     *
     * @return administrativeClassId
     **/
    @ApiModelProperty(value = "行政班id")


    public String getAdministrativeClassId() {
        return administrativeClassId;
    }

    public void setAdministrativeClassId(String administrativeClassId) {
        this.administrativeClassId = administrativeClassId;
    }

    public UserResource administrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
        return this;
    }

    /**
     * 行政班名称
     *
     * @return administrativeClassName
     **/
    @ApiModelProperty(value = "行政班名称")


    public String getAdministrativeClassName() {
        return administrativeClassName;
    }

    public void setAdministrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
    }

    public UserResource avatarPath(String avatarPath) {
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

    public UserResource avatarInnerUrl(String avatarInnerUrl) {
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

    public UserResource avatarOuterUrl(String avatarOuterUrl) {
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

    public UserResource gender(Integer gender) {
        this.gender = gender;
        return this;
    }

    /**
     * 性别（0：未知；1：男；2：女）
     *
     * @return gender
     **/
    @ApiModelProperty(value = "性别（0：未知；1：男；2：女）")


    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public UserResource majorId(String majorId) {
        this.majorId = majorId;
        return this;
    }

    /**
     * 专业id
     *
     * @return majorId
     **/
    @ApiModelProperty(value = "专业id")


    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public UserResource majorName(String majorName) {
        this.majorName = majorName;
        return this;
    }

    /**
     * 专业名称
     *
     * @return majorName
     **/
    @ApiModelProperty(value = "专业名称")


    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public UserResource educationType(Integer educationType) {
        this.educationType = educationType;
        return this;
    }

    /**
     * 学历（0：本科生；1：研究生；2：继续教育，3：其他）
     *
     * @return educationType
     **/
    @ApiModelProperty(value = "学历（0：本科生；1：研究生；2：继续教育，3：其他）")


    public Integer getEducationType() {
        return educationType;
    }

    public void setEducationType(Integer educationType) {
        this.educationType = educationType;
    }

    public UserResource email(String email) {
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

    public UserResource mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    /**
     * 手机号
     *
     * @return mobile
     **/
    @ApiModelProperty(value = "手机号")


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public UserResource jobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    /**
     * 职位名称
     *
     * @return jobTitle
     **/
    @ApiModelProperty(value = "职位名称")


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public UserResource collegeId(String collegeId) {
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

    public UserResource collegeName(String collegeName) {
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

    public UserResource schoolName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }

    /**
     * 学校名称
     *
     * @return schoolName
     **/
    @ApiModelProperty(value = "学校名称")


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserResource userResource = (UserResource) o;
        return Objects.equals(this.userId, userResource.userId) &&
                Objects.equals(this.password, userResource.password) &&
                Objects.equals(this.source, userResource.source) &&
                Objects.equals(this.userAccountNo, userResource.userAccountNo) &&
                Objects.equals(this.userNo, userResource.userNo) &&
                Objects.equals(this.userName, userResource.userName) &&
                Objects.equals(this.userType, userResource.userType) &&
                Objects.equals(this.userCardNo, userResource.userCardNo) &&
                Objects.equals(this.userStatus, userResource.userStatus) &&
                Objects.equals(this.administrativeClassId, userResource.administrativeClassId) &&
                Objects.equals(this.administrativeClassName, userResource.administrativeClassName) &&
                Objects.equals(this.avatarPath, userResource.avatarPath) &&
                Objects.equals(this.avatarInnerUrl, userResource.avatarInnerUrl) &&
                Objects.equals(this.avatarOuterUrl, userResource.avatarOuterUrl) &&
                Objects.equals(this.gender, userResource.gender) &&
                Objects.equals(this.majorId, userResource.majorId) &&
                Objects.equals(this.majorName, userResource.majorName) &&
                Objects.equals(this.educationType, userResource.educationType) &&
                Objects.equals(this.email, userResource.email) &&
                Objects.equals(this.mobile, userResource.mobile) &&
                Objects.equals(this.jobTitle, userResource.jobTitle) &&
                Objects.equals(this.collegeId, userResource.collegeId) &&
                Objects.equals(this.collegeName, userResource.collegeName) &&
                Objects.equals(this.schoolName, userResource.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, password, source, userAccountNo, userNo, userName, userType, userCardNo, userStatus,
                administrativeClassId, administrativeClassName, avatarPath, avatarInnerUrl, avatarOuterUrl, gender, majorId, majorName,
                educationType, email, mobile, jobTitle, collegeId, collegeName, schoolName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserResource {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    userAccountNo: ").append(toIndentedString(userAccountNo)).append("\n");
        sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
        sb.append("    userCardNo: ").append(toIndentedString(userCardNo)).append("\n");
        sb.append("    userStatus: ").append(toIndentedString(userStatus)).append("\n");
        sb.append("    administrativeClassId: ").append(toIndentedString(administrativeClassId)).append("\n");
        sb.append("    administrativeClassName: ").append(toIndentedString(administrativeClassName)).append("\n");
        sb.append("    avatarPath: ").append(toIndentedString(avatarPath)).append("\n");
        sb.append("    avatarInnerUrl: ").append(toIndentedString(avatarInnerUrl)).append("\n");
        sb.append("    avatarOuterUrl: ").append(toIndentedString(avatarOuterUrl)).append("\n");
        sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
        sb.append("    majorId: ").append(toIndentedString(majorId)).append("\n");
        sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
        sb.append("    educationType: ").append(toIndentedString(educationType)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    mobile: ").append(toIndentedString(mobile)).append("\n");
        sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    schoolName: ").append(toIndentedString(schoolName)).append("\n");
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


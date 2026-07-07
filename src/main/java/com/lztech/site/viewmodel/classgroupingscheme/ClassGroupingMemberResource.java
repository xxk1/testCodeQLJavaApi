package com.lztech.site.viewmodel.classgroupingscheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassGroupingMemberResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-23T11:28:22.247Z")


public class ClassGroupingMemberResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("userNo")
    private String userNo = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("college")
    private String college = null;

    @JsonProperty("eamil")
    private String eamil = null;

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

    @JsonProperty("whetherGroupLeader")
    private Boolean whetherGroupLeader = null;

    public Boolean getWhetherGroupLeader() {
        return whetherGroupLeader;
    }

    public void setWhetherGroupLeader(Boolean whetherGroupLeader) {
        this.whetherGroupLeader = whetherGroupLeader;
    }

    public ClassGroupingMemberResource id(String id) {
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

    public ClassGroupingMemberResource userNo(String userNo) {
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

    public ClassGroupingMemberResource userName(String userName) {
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

    public ClassGroupingMemberResource college(String college) {
        this.college = college;
        return this;
    }

    /**
     * 学院
     *
     * @return college
     **/
    @ApiModelProperty(value = "学院")


    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public ClassGroupingMemberResource eamil(String eamil) {
        this.eamil = eamil;
        return this;
    }

    /**
     * 邮箱
     *
     * @return eamil
     **/
    @ApiModelProperty(value = "邮箱")


    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public ClassGroupingMemberResource avatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        return this;
    }

    /**
     * 用户头像
     *
     * @return avatarPath
     **/
    @ApiModelProperty(value = "用户头像")


    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public ClassGroupingMemberResource avatarInnerUrl(String avatarInnerUrl) {
        this.avatarInnerUrl = avatarInnerUrl;
        return this;
    }

    /**
     * 头像内网地址
     *
     * @return avatarInnerUrl
     **/
    @ApiModelProperty(value = "头像内网地址")


    public String getAvatarInnerUrl() {
        return avatarInnerUrl;
    }

    public void setAvatarInnerUrl(String avatarInnerUrl) {
        this.avatarInnerUrl = avatarInnerUrl;
    }

    public ClassGroupingMemberResource avatarOuterUrl(String avatarOuterUrl) {
        this.avatarOuterUrl = avatarOuterUrl;
        return this;
    }

    /**
     * 头像外网地址
     *
     * @return avatarOuterUrl
     **/
    @ApiModelProperty(value = "头像外网地址")


    public String getAvatarOuterUrl() {
        return avatarOuterUrl;
    }

    public void setAvatarOuterUrl(String avatarOuterUrl) {
        this.avatarOuterUrl = avatarOuterUrl;
    }

    public ClassGroupingMemberResource className(String className) {
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

    public ClassGroupingMemberResource majorName(String majorName) {
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

    public ClassGroupingMemberResource gender(String gender) {
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


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassGroupingMemberResource classGroupingMemberResource = (ClassGroupingMemberResource) o;
        return Objects.equals(this.id, classGroupingMemberResource.id) &&
                Objects.equals(this.userNo, classGroupingMemberResource.userNo) &&
                Objects.equals(this.userName, classGroupingMemberResource.userName) &&
                Objects.equals(this.college, classGroupingMemberResource.college) &&
                Objects.equals(this.eamil, classGroupingMemberResource.eamil) &&
                Objects.equals(this.avatarPath, classGroupingMemberResource.avatarPath) &&
                Objects.equals(this.avatarInnerUrl, classGroupingMemberResource.avatarInnerUrl) &&
                Objects.equals(this.avatarOuterUrl, classGroupingMemberResource.avatarOuterUrl) &&
                Objects.equals(this.className, classGroupingMemberResource.className) &&
                Objects.equals(this.majorName, classGroupingMemberResource.majorName) &&
                Objects.equals(this.gender, classGroupingMemberResource.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userNo, userName, college, eamil, avatarPath, avatarInnerUrl, avatarOuterUrl, className, majorName, gender);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassGroupingMemberResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    college: ").append(toIndentedString(college)).append("\n");
        sb.append("    eamil: ").append(toIndentedString(eamil)).append("\n");
        sb.append("    avatarPath: ").append(toIndentedString(avatarPath)).append("\n");
        sb.append("    avatarInnerUrl: ").append(toIndentedString(avatarInnerUrl)).append("\n");
        sb.append("    avatarOuterUrl: ").append(toIndentedString(avatarOuterUrl)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
        sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
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


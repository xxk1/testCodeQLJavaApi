package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupCardData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-22T09:51:22.994+08:00")

public class GroupCardData {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("userNo")
    private String userNo = null;

    @JsonProperty("administrativeClassName")
    private String administrativeClassName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    public GroupCardData groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 班级Id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "班级Id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public GroupCardData userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 用户Id
     *
     * @return userId
     **/
    @ApiModelProperty(value = "用户Id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GroupCardData userName(String userName) {
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

    public GroupCardData userNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    /**
     * 用户编号
     *
     * @return userNo
     **/
    @ApiModelProperty(value = "用户编号")


    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public GroupCardData administrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
        return this;
    }

    /**
     * 行政班
     *
     * @return administrativeClassName
     **/
    @ApiModelProperty(value = "行政班")


    public String getAdministrativeClassName() {
        return administrativeClassName;
    }

    public void setAdministrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
    }

    public GroupCardData collegeId(String collegeId) {
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

    public GroupCardData collegeName(String collegeName) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupCardData groupCardData = (GroupCardData) o;
        return Objects.equals(this.groupId, groupCardData.groupId) &&
                Objects.equals(this.userId, groupCardData.userId) &&
                Objects.equals(this.userName, groupCardData.userName) &&
                Objects.equals(this.userNo, groupCardData.userNo) &&
                Objects.equals(this.administrativeClassName, groupCardData.administrativeClassName) &&
                Objects.equals(this.collegeId, groupCardData.collegeId) &&
                Objects.equals(this.collegeName, groupCardData.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, userId, userName, userNo, administrativeClassName, collegeId, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupCardData {\n");

        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
        sb.append("    administrativeClassName: ").append(toIndentedString(administrativeClassName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
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


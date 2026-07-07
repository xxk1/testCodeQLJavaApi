package com.lztech.site.resource.classresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassTeacherResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-18T07:23:25.558Z")

public class ClassTeacherResource {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("classNickName")
    private String classNickName = null;

    @JsonProperty("classAttribute")
    private Integer classAttribute = null;

    public Integer getClassAttribute() {
        return classAttribute;
    }

    public void setClassAttribute(Integer classAttribute) {
        this.classAttribute = classAttribute;
    }

    public ClassTeacherResource userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 老师Id
     * @return userId
     **/
    @ApiModelProperty(value = "老师Id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ClassTeacherResource userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 老师姓名
     * @return userName
     **/
    @ApiModelProperty(value = "老师姓名")


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ClassTeacherResource groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 班级Id
     * @return groupId
     **/
    @ApiModelProperty(value = "班级Id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public ClassTeacherResource groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 班级名称
     * @return groupName
     **/
    @ApiModelProperty(value = "班级名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ClassTeacherResource classNickName(String classNickName) {
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


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassTeacherResource classTeacherResource = (ClassTeacherResource) o;
        return Objects.equals(this.userId, classTeacherResource.userId) &&
                Objects.equals(this.userName, classTeacherResource.userName) &&
                Objects.equals(this.groupId, classTeacherResource.groupId) &&
                Objects.equals(this.groupName, classTeacherResource.groupName) &&
                Objects.equals(this.classNickName, classTeacherResource.classNickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, groupId, groupName, classNickName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassTeacherResource {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
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


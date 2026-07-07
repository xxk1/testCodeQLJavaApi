package com.lztech.site.viewmodel.authorityapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UserBaseInfoAndCollege
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-18T07:04:21.732Z")


public class UserBaseInfoAndCollege {
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

    public UserBaseInfoAndCollege userId(String userId) {
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

    public UserBaseInfoAndCollege userNo(String userNo) {
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

    public UserBaseInfoAndCollege userName(String userName) {
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

    public UserBaseInfoAndCollege collegeId(String collegeId) {
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

    public UserBaseInfoAndCollege collegeName(String collegeName) {
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
        UserBaseInfoAndCollege userBaseInfoAndCollege = (UserBaseInfoAndCollege) o;
        return Objects.equals(this.userId, userBaseInfoAndCollege.userId) &&
                Objects.equals(this.userNo, userBaseInfoAndCollege.userNo) &&
                Objects.equals(this.userName, userBaseInfoAndCollege.userName) &&
                Objects.equals(this.collegeId, userBaseInfoAndCollege.collegeId) &&
                Objects.equals(this.collegeName, userBaseInfoAndCollege.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userNo, userName, collegeId, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserBaseInfoAndCollege {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
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


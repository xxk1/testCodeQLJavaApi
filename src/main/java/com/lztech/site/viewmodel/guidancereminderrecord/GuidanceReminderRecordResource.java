package com.lztech.site.viewmodel.guidancereminderrecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GuidanceReminderRecordResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-03-20T08:10:52.237Z")


public class GuidanceReminderRecordResource {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userNo")
    private String userNo = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("functionCode")
    private String functionCode = null;

    @JsonProperty("functionDescription")
    private String functionDescription = null;

    @JsonProperty("validCode")
    private String validCode = null;

    public GuidanceReminderRecordResource userId(String userId) {
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

    public GuidanceReminderRecordResource userNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    /**
     * 用户工号
     *
     * @return userNo
     **/
    @ApiModelProperty(value = "用户工号")


    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public GuidanceReminderRecordResource userName(String userName) {
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

    public GuidanceReminderRecordResource functionCode(String functionCode) {
        this.functionCode = functionCode;
        return this;
    }

    /**
     * 功能编码：功能简称首字母+三位数字，例如：BJGL001
     *
     * @return functionCode
     **/
    @ApiModelProperty(value = "功能编码：功能简称首字母+三位数字，例如：BJGL001")


    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public GuidanceReminderRecordResource functionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
        return this;
    }

    /**
     * 功能描述
     *
     * @return functionDescription
     **/
    @ApiModelProperty(value = "功能描述")


    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public GuidanceReminderRecordResource validCode(String validCode) {
        this.validCode = validCode;
        return this;
    }

    /**
     * 加密验证码userId=?&signKey=123123
     *
     * @return validCode
     **/
    @ApiModelProperty(value = "加密验证码userId=?&signKey=123123")


    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GuidanceReminderRecordResource guidanceReminderRecordResource = (GuidanceReminderRecordResource) o;
        return Objects.equals(this.userId, guidanceReminderRecordResource.userId) &&
                Objects.equals(this.userNo, guidanceReminderRecordResource.userNo) &&
                Objects.equals(this.userName, guidanceReminderRecordResource.userName) &&
                Objects.equals(this.functionCode, guidanceReminderRecordResource.functionCode) &&
                Objects.equals(this.functionDescription, guidanceReminderRecordResource.functionDescription) &&
                Objects.equals(this.validCode, guidanceReminderRecordResource.validCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userNo, userName, functionCode, functionDescription, validCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GuidanceReminderRecordResource {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    functionCode: ").append(toIndentedString(functionCode)).append("\n");
        sb.append("    functionDescription: ").append(toIndentedString(functionDescription)).append("\n");
        sb.append("    validCode: ").append(toIndentedString(validCode)).append("\n");
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


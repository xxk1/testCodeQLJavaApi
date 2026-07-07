package com.lztech.site.viewmodel.userinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UserIdAndOpenIdVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-06T08:18:22.212Z")


public class UserIdAndOpenIdVo {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("openId")
    private String openId = null;

    public UserIdAndOpenIdVo userId(String userId) {
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

    public UserIdAndOpenIdVo openId(String openId) {
        this.openId = openId;
        return this;
    }

    /**
     * 微信小程序openId
     *
     * @return openId
     **/
    @ApiModelProperty(value = "微信小程序openId")


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserIdAndOpenIdVo userIdAndOpenIdVo = (UserIdAndOpenIdVo) o;
        return Objects.equals(this.userId, userIdAndOpenIdVo.userId) &&
                Objects.equals(this.openId, userIdAndOpenIdVo.openId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, openId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserIdAndOpenIdVo {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    openId: ").append(toIndentedString(openId)).append("\n");
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


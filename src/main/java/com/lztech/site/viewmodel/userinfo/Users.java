package com.lztech.site.viewmodel.userinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Users
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-19T08:05:38.655Z")

public class Users {
  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("openId")
  private String openId = null;

  public Users userId(String userId) {
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

  public Users openId(String openId) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Users users = (Users) o;
    return Objects.equals(this.userId, users.userId) &&
        Objects.equals(this.openId, users.openId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, openId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Users {\n");
    
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


package com.lztech.site.viewmodel.userinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-30T03:17:58.498Z")
public class UserNoOrUserIdVo {
    @JsonProperty("userIdList")
    @Valid
    private List<String> userIdList = null;

    @JsonProperty("userNoList")
    @Valid
    private List<String> userNoList = null;

    @JsonProperty("whetherExistEmail")
    private Boolean whetherExistEmail = null;

    public UserNoOrUserIdVo userIdList(List<String> userIdList) {
        this.userIdList = userIdList;
        return this;
    }

    public UserNoOrUserIdVo addUserIdListItem(String userIdListItem) {
        if (this.userIdList == null) {
            this.userIdList = new ArrayList<String>();
        }
        this.userIdList.add(userIdListItem);
        return this;
    }

    /**
     * 用户id集合
     *
     * @return userIdList
     **/
    @ApiModelProperty(value = "用户id集合")


    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    public UserNoOrUserIdVo userNoList(List<String> userNoList) {
        this.userNoList = userNoList;
        return this;
    }

    public UserNoOrUserIdVo addUserNoListItem(String userNoListItem) {
        if (this.userNoList == null) {
            this.userNoList = new ArrayList<String>();
        }
        this.userNoList.add(userNoListItem);
        return this;
    }

    /**
     * 用户编号集合
     *
     * @return userNoList
     **/
    @ApiModelProperty(value = "用户编号集合")


    public List<String> getUserNoList() {
        return userNoList;
    }

    public void setUserNoList(List<String> userNoList) {
        this.userNoList = userNoList;
    }

    public UserNoOrUserIdVo whetherExistEmail(Boolean whetherExistEmail) {
        this.whetherExistEmail = whetherExistEmail;
        return this;
    }

    /**
     * 是否仅获取存在邮箱地址的用户信息（true：仅返回存在邮箱地址的用户；false：返回全部所查用户信息）
     *
     * @return whetherExistEmail
     **/
    @ApiModelProperty(value = "是否仅获取存在邮箱地址的用户信息（true：仅返回存在邮箱地址的用户；false：返回全部所查用户信息）")


    public Boolean isWhetherExistEmail() {
        return whetherExistEmail;
    }

    public void setWhetherExistEmail(Boolean whetherExistEmail) {
        this.whetherExistEmail = whetherExistEmail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserNoOrUserIdVo userNoOrUserIdVo = (UserNoOrUserIdVo) o;
        return Objects.equals(this.userIdList, userNoOrUserIdVo.userIdList) &&
                Objects.equals(this.userNoList, userNoOrUserIdVo.userNoList) &&
                Objects.equals(this.whetherExistEmail, userNoOrUserIdVo.whetherExistEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userIdList, userNoList, whetherExistEmail);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserNoOrUserIdVo {\n");

        sb.append("    userIdList: ").append(toIndentedString(userIdList)).append("\n");
        sb.append("    userNoList: ").append(toIndentedString(userNoList)).append("\n");
        sb.append("    whetherExistEmail: ").append(toIndentedString(whetherExistEmail)).append("\n");
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


package com.lztech.site.viewmodel.guidancereminderrecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GuidanceReminderRecordVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-03-20T07:51:06.982Z")


public class GuidanceReminderRecordVo {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("reminderConfirmation")
    private Boolean reminderConfirmation = null;

    public GuidanceReminderRecordVo userId(String userId) {
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

    public GuidanceReminderRecordVo reminderConfirmation(Boolean reminderConfirmation) {
        this.reminderConfirmation = reminderConfirmation;
        return this;
    }

    /**
     * 提醒确认状态
     *
     * @return reminderConfirmation
     **/
    @ApiModelProperty(value = "提醒确认状态")


    public Boolean isReminderConfirmation() {
        return reminderConfirmation;
    }

    public void setReminderConfirmation(Boolean reminderConfirmation) {
        this.reminderConfirmation = reminderConfirmation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GuidanceReminderRecordVo guidanceReminderRecordVo = (GuidanceReminderRecordVo) o;
        return Objects.equals(this.userId, guidanceReminderRecordVo.userId) &&
                Objects.equals(this.reminderConfirmation, guidanceReminderRecordVo.reminderConfirmation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, reminderConfirmation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GuidanceReminderRecordVo {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    reminderConfirmation: ").append(toIndentedString(reminderConfirmation)).append("\n");
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


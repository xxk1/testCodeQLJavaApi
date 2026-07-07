package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-05T07:16:33.006Z")


public class ClassVo {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("publicStatus")
    private Boolean publicStatus = null;

    public ClassVo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 班级id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "班级id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public ClassVo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 班级名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "班级名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ClassVo publicStatus(Boolean publicStatus) {
        this.publicStatus = publicStatus;
        return this;
    }

    /**
     * 公开状态
     *
     * @return publicStatus
     **/
    @ApiModelProperty(value = "公开状态")


    public Boolean isPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(Boolean publicStatus) {
        this.publicStatus = publicStatus;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassVo classVo = (ClassVo) o;
        return Objects.equals(this.groupId, classVo.groupId) &&
                Objects.equals(this.groupName, classVo.groupName) &&
                Objects.equals(this.publicStatus, classVo.publicStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, publicStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassVo {\n");

        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    publicStatus: ").append(toIndentedString(publicStatus)).append("\n");
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


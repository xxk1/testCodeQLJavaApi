package com.lztech.site.resource.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.group.GroupMembersResource;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ImportGroupMemberResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-20T06:43:17.728Z")

public class ImportGroupMemberResource {
    @JsonProperty("message")
    private String message = null;

    @JsonProperty("groupMembers")
    @Valid
    private List<GroupMembersResource> groupMembers = null;

    public ImportGroupMemberResource message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 返回消息
     *
     * @return message
     **/
    @ApiModelProperty(value = "返回消息")


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ImportGroupMemberResource groupMembers(List<GroupMembersResource> groupMembers) {
        this.groupMembers = groupMembers;
        return this;
    }

    public ImportGroupMemberResource addGroupMembersItem(GroupMembersResource groupMembersItem) {
        if (this.groupMembers == null) {
            this.groupMembers = new ArrayList<GroupMembersResource>();
        }
        this.groupMembers.add(groupMembersItem);
        return this;
    }

    /**
     * 班级成员信息
     *
     * @return groupMembers
     **/
    @ApiModelProperty(value = "班级成员信息")

    @Valid

    public List<GroupMembersResource> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMembersResource> groupMembers) {
        this.groupMembers = groupMembers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImportGroupMemberResource importGroupMemberResource = (ImportGroupMemberResource) o;
        return Objects.equals(this.message, importGroupMemberResource.message) &&
                Objects.equals(this.groupMembers, importGroupMemberResource.groupMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, groupMembers);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ImportGroupMemberResource {\n");

        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    groupMembers: ").append(toIndentedString(groupMembers)).append("\n");
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


package com.lztech.site.resource.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GroupResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-07T11:56:24.734Z")

public class GroupResource {
    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("groupMembersNum")
    private BigDecimal groupMembersNum = null;

    @JsonProperty("groupMembers")
    @Valid
    private List<GroupMemberResource> groupMembers = null;

    public GroupResource groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 组名
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "组名")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupResource groupNo(String groupNo) {
        this.groupNo = groupNo;
        return this;
    }

    /**
     * 组编号
     *
     * @return groupNo
     **/
    @ApiModelProperty(value = "组编号")


    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public BigDecimal getGroupMembersNum() {
        return groupMembersNum;
    }

    public void setGroupMembersNum(BigDecimal groupMembersNum) {
        this.groupMembersNum = groupMembersNum;
    }

    public GroupResource groupMembers(List<GroupMemberResource> groupMembers) {
        this.groupMembers = groupMembers;
        return this;
    }

    public GroupResource addGroupMembersItem(GroupMemberResource groupMembersItem) {
        if (this.groupMembers == null) {
            this.groupMembers = new ArrayList<GroupMemberResource>();
        }
        this.groupMembers.add(groupMembersItem);
        return this;
    }


    /**
     * Get groupMembers
     *
     * @return groupMembers
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<GroupMemberResource> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMemberResource> groupMembers) {
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
        GroupResource groupResource = (GroupResource) o;
        return Objects.equals(this.groupName, groupResource.groupName) &&
                Objects.equals(this.groupNo, groupResource.groupNo) &&
                Objects.equals(this.groupMembers, groupResource.groupMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, groupNo, groupMembers);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupResource {\n");

        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
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


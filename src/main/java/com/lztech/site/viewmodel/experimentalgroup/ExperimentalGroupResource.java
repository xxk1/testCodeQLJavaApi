package com.lztech.site.viewmodel.experimentalgroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ExperimentalGroupResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-01-19T10:09:54.312Z")


public class ExperimentalGroupResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("responsibleTeacherId")
    private String responsibleTeacherId = null;

    @JsonProperty("responsibleTeacherName")
    private String responsibleTeacherName = null;

    @JsonProperty("responsibleTeacherNo")
    private String responsibleTeacherNo = null;

    @JsonProperty("groupLeaderId")
    private String groupLeaderId = null;

    @JsonProperty("groupLeaderName")
    private String groupLeaderName = null;

    @JsonProperty("groupLeaderNo")
    private String groupLeaderNo = null;

    @JsonProperty("memberNum")
    private Integer memberNum = null;

    @JsonProperty("memberList")
    @Valid
    private List<ExperimentalGroupMemberResource> memberList = null;

    public ExperimentalGroupResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 实验小组id
     *
     * @return id
     **/
    @ApiModelProperty(value = "实验小组id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExperimentalGroupResource groupName(String groupName) {
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

    public ExperimentalGroupResource responsibleTeacherId(String responsibleTeacherId) {
        this.responsibleTeacherId = responsibleTeacherId;
        return this;
    }

    /**
     * 负责教师id
     *
     * @return responsibleTeacherId
     **/
    @ApiModelProperty(value = "负责教师id")


    public String getResponsibleTeacherId() {
        return responsibleTeacherId;
    }

    public void setResponsibleTeacherId(String responsibleTeacherId) {
        this.responsibleTeacherId = responsibleTeacherId;
    }

    public ExperimentalGroupResource responsibleTeacherName(String responsibleTeacherName) {
        this.responsibleTeacherName = responsibleTeacherName;
        return this;
    }

    /**
     * 负责教师姓名
     *
     * @return responsibleTeacherName
     **/
    @ApiModelProperty(value = "负责教师姓名")


    public String getResponsibleTeacherName() {
        return responsibleTeacherName;
    }

    public void setResponsibleTeacherName(String responsibleTeacherName) {
        this.responsibleTeacherName = responsibleTeacherName;
    }

    public ExperimentalGroupResource responsibleTeacherNo(String responsibleTeacherNo) {
        this.responsibleTeacherNo = responsibleTeacherNo;
        return this;
    }

    /**
     * 负责教师工号
     *
     * @return responsibleTeacherNo
     **/
    @ApiModelProperty(value = "负责教师工号")


    public String getResponsibleTeacherNo() {
        return responsibleTeacherNo;
    }

    public void setResponsibleTeacherNo(String responsibleTeacherNo) {
        this.responsibleTeacherNo = responsibleTeacherNo;
    }

    public ExperimentalGroupResource groupLeaderId(String groupLeaderId) {
        this.groupLeaderId = groupLeaderId;
        return this;
    }

    /**
     * 组长id
     *
     * @return groupLeaderId
     **/
    @ApiModelProperty(value = "组长id")


    public String getGroupLeaderId() {
        return groupLeaderId;
    }

    public void setGroupLeaderId(String groupLeaderId) {
        this.groupLeaderId = groupLeaderId;
    }

    public ExperimentalGroupResource groupLeaderName(String groupLeaderName) {
        this.groupLeaderName = groupLeaderName;
        return this;
    }

    /**
     * 组长姓名
     *
     * @return groupLeaderName
     **/
    @ApiModelProperty(value = "组长姓名")


    public String getGroupLeaderName() {
        return groupLeaderName;
    }

    public void setGroupLeaderName(String groupLeaderName) {
        this.groupLeaderName = groupLeaderName;
    }

    public ExperimentalGroupResource groupLeaderNo(String groupLeaderNo) {
        this.groupLeaderNo = groupLeaderNo;
        return this;
    }

    /**
     * 组长工号
     *
     * @return groupLeaderNo
     **/
    @ApiModelProperty(value = "组长工号")


    public String getGroupLeaderNo() {
        return groupLeaderNo;
    }

    public void setGroupLeaderNo(String groupLeaderNo) {
        this.groupLeaderNo = groupLeaderNo;
    }

    public ExperimentalGroupResource memberNum(Integer memberNum) {
        this.memberNum = memberNum;
        return this;
    }

    /**
     * 组员数量
     *
     * @return memberNum
     **/
    @ApiModelProperty(value = "组员数量")


    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public ExperimentalGroupResource memberList(List<ExperimentalGroupMemberResource> memberList) {
        this.memberList = memberList;
        return this;
    }

    public ExperimentalGroupResource addMemberListItem(ExperimentalGroupMemberResource memberListItem) {
        if (this.memberList == null) {
            this.memberList = new ArrayList<ExperimentalGroupMemberResource>();
        }
        this.memberList.add(memberListItem);
        return this;
    }

    /**
     * 实验小组成员列表
     *
     * @return memberList
     **/
    @ApiModelProperty(value = "实验小组成员列表")

    @Valid

    public List<ExperimentalGroupMemberResource> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<ExperimentalGroupMemberResource> memberList) {
        this.memberList = memberList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentalGroupResource experimentalGroupResource = (ExperimentalGroupResource) o;
        return Objects.equals(this.id, experimentalGroupResource.id) &&
                Objects.equals(this.groupName, experimentalGroupResource.groupName) &&
                Objects.equals(this.responsibleTeacherId, experimentalGroupResource.responsibleTeacherId) &&
                Objects.equals(this.responsibleTeacherName, experimentalGroupResource.responsibleTeacherName) &&
                Objects.equals(this.responsibleTeacherNo, experimentalGroupResource.responsibleTeacherNo) &&
                Objects.equals(this.groupLeaderId, experimentalGroupResource.groupLeaderId) &&
                Objects.equals(this.groupLeaderName, experimentalGroupResource.groupLeaderName) &&
                Objects.equals(this.groupLeaderNo, experimentalGroupResource.groupLeaderNo) &&
                Objects.equals(this.memberNum, experimentalGroupResource.memberNum) &&
                Objects.equals(this.memberList, experimentalGroupResource.memberList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, responsibleTeacherId, responsibleTeacherName, responsibleTeacherNo, groupLeaderId, groupLeaderName,
                groupLeaderNo, memberNum, memberList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentalGroupResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    responsibleTeacherId: ").append(toIndentedString(responsibleTeacherId)).append("\n");
        sb.append("    responsibleTeacherName: ").append(toIndentedString(responsibleTeacherName)).append("\n");
        sb.append("    responsibleTeacherNo: ").append(toIndentedString(responsibleTeacherNo)).append("\n");
        sb.append("    groupLeaderId: ").append(toIndentedString(groupLeaderId)).append("\n");
        sb.append("    groupLeaderName: ").append(toIndentedString(groupLeaderName)).append("\n");
        sb.append("    groupLeaderNo: ").append(toIndentedString(groupLeaderNo)).append("\n");
        sb.append("    memberNum: ").append(toIndentedString(memberNum)).append("\n");
        sb.append("    memberList: ").append(toIndentedString(memberList)).append("\n");
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


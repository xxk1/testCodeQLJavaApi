package com.lztech.site.viewmodel.experimentalgroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ExperimentalGroupCreateResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-01-19T11:39:38.071Z")


public class ExperimentalGroupCreateResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("creatorId")
    private String creatorId = null;

    @JsonProperty("creatorName")
    private String creatorName = null;

    @JsonProperty("classId")
    private String classId = null;

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

    public ExperimentalGroupCreateResource id(String id) {
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

    public ExperimentalGroupCreateResource groupName(String groupName) {
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

    public ExperimentalGroupCreateResource creatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    /**
     * 创建人id
     *
     * @return creatorId
     **/
    @ApiModelProperty(value = "创建人id")


    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public ExperimentalGroupCreateResource creatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    /**
     * 创建人姓名
     *
     * @return creatorName
     **/
    @ApiModelProperty(value = "创建人姓名")


    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public ExperimentalGroupCreateResource classId(String classId) {
        this.classId = classId;
        return this;
    }

    /**
     * 班级id
     *
     * @return classId
     **/
    @ApiModelProperty(value = "班级id")


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public ExperimentalGroupCreateResource responsibleTeacherId(String responsibleTeacherId) {
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

    public ExperimentalGroupCreateResource responsibleTeacherName(String responsibleTeacherName) {
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

    public ExperimentalGroupCreateResource responsibleTeacherNo(String responsibleTeacherNo) {
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

    public ExperimentalGroupCreateResource groupLeaderId(String groupLeaderId) {
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

    public ExperimentalGroupCreateResource groupLeaderName(String groupLeaderName) {
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

    public ExperimentalGroupCreateResource groupLeaderNo(String groupLeaderNo) {
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

    public ExperimentalGroupCreateResource memberNum(Integer memberNum) {
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

    public ExperimentalGroupCreateResource memberList(List<ExperimentalGroupMemberResource> memberList) {
        this.memberList = memberList;
        return this;
    }

    public ExperimentalGroupCreateResource addMemberListItem(ExperimentalGroupMemberResource memberListItem) {
        if (this.memberList == null) {
            this.memberList = new ArrayList<ExperimentalGroupMemberResource>();
        }
        this.memberList.add(memberListItem);
        return this;
    }

    /**
     * Get memberList
     *
     * @return memberList
     **/
    @ApiModelProperty(value = "")

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
        ExperimentalGroupCreateResource experimentalGroupCreateResource = (ExperimentalGroupCreateResource) o;
        return Objects.equals(this.id, experimentalGroupCreateResource.id) &&
                Objects.equals(this.groupName, experimentalGroupCreateResource.groupName) &&
                Objects.equals(this.creatorId, experimentalGroupCreateResource.creatorId) &&
                Objects.equals(this.creatorName, experimentalGroupCreateResource.creatorName) &&
                Objects.equals(this.classId, experimentalGroupCreateResource.classId) &&
                Objects.equals(this.responsibleTeacherId, experimentalGroupCreateResource.responsibleTeacherId) &&
                Objects.equals(this.responsibleTeacherName, experimentalGroupCreateResource.responsibleTeacherName) &&
                Objects.equals(this.responsibleTeacherNo, experimentalGroupCreateResource.responsibleTeacherNo) &&
                Objects.equals(this.groupLeaderId, experimentalGroupCreateResource.groupLeaderId) &&
                Objects.equals(this.groupLeaderName, experimentalGroupCreateResource.groupLeaderName) &&
                Objects.equals(this.groupLeaderNo, experimentalGroupCreateResource.groupLeaderNo) &&
                Objects.equals(this.memberNum, experimentalGroupCreateResource.memberNum) &&
                Objects.equals(this.memberList, experimentalGroupCreateResource.memberList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, creatorId, creatorName, classId, responsibleTeacherId,
                responsibleTeacherName, responsibleTeacherNo, groupLeaderId, groupLeaderName,
                groupLeaderNo, memberNum, memberList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentalGroupCreateResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
        sb.append("    creatorName: ").append(toIndentedString(creatorName)).append("\n");
        sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
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


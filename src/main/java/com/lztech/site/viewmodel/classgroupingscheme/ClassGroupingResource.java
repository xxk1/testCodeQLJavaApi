package com.lztech.site.viewmodel.classgroupingscheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassGroupingResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-23T11:28:22.247Z")


public class ClassGroupingResource {
    @JsonProperty("classGroupingId")
    private String classGroupingId = null;

    @JsonProperty("classGroupingName")
    private String classGroupingName = null;

    @JsonProperty("classGroupingType")
    private Integer classGroupingType = null;

    @JsonProperty("responsibleTeacherId")
    private String responsibleTeacherId = null;

    @JsonProperty("responsibleTeacherName")
    private String responsibleTeacherName = null;

    @JsonProperty("responsibleTeacherNo")
    private String responsibleTeacherNo = null;

    @JsonProperty("memberNum")
    private Integer memberNum = null;

    @JsonProperty("memberList")
    @Valid
    private List<ClassGroupingMemberResource> memberList = null;

    public ClassGroupingResource classGroupingId(String classGroupingId) {
        this.classGroupingId = classGroupingId;
        return this;
    }

    /**
     * 班级小组id
     *
     * @return classGroupingId
     **/
    @ApiModelProperty(value = "班级小组id")


    public String getClassGroupingId() {
        return classGroupingId;
    }

    public void setClassGroupingId(String classGroupingId) {
        this.classGroupingId = classGroupingId;
    }

    public ClassGroupingResource classGroupingName(String classGroupingName) {
        this.classGroupingName = classGroupingName;
        return this;
    }

    /**
     * 班级小组名称
     *
     * @return classGroupingName
     **/
    @ApiModelProperty(value = "班级小组名称")


    public String getClassGroupingName() {
        return classGroupingName;
    }

    public void setClassGroupingName(String classGroupingName) {
        this.classGroupingName = classGroupingName;
    }

    public ClassGroupingResource classGroupingType(Integer classGroupingType) {
        this.classGroupingType = classGroupingType;
        return this;
    }

    /**
     * 班级小组类型  0: 已分配；1：未分配
     *
     * @return classGroupingType
     **/
    @ApiModelProperty(value = "班级小组类型  0: 已分配；1：未分配")


    public Integer getClassGroupingType() {
        return classGroupingType;
    }

    public void setClassGroupingType(Integer classGroupingType) {
        this.classGroupingType = classGroupingType;
    }

    public ClassGroupingResource responsibleTeacherId(String responsibleTeacherId) {
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

    public ClassGroupingResource responsibleTeacherName(String responsibleTeacherName) {
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

    public ClassGroupingResource responsibleTeacherNo(String responsibleTeacherNo) {
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

    public ClassGroupingResource memberNum(Integer memberNum) {
        this.memberNum = memberNum;
        return this;
    }

    /**
     * 班级小组人数
     *
     * @return memberNum
     **/
    @ApiModelProperty(value = "班级小组人数")


    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public ClassGroupingResource memberList(List<ClassGroupingMemberResource> memberList) {
        this.memberList = memberList;
        return this;
    }

    public ClassGroupingResource addMemberListItem(ClassGroupingMemberResource memberListItem) {
        if (this.memberList == null) {
            this.memberList = new ArrayList<ClassGroupingMemberResource>();
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

    public List<ClassGroupingMemberResource> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<ClassGroupingMemberResource> memberList) {
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
        ClassGroupingResource classGroupingResource = (ClassGroupingResource) o;
        return Objects.equals(this.classGroupingId, classGroupingResource.classGroupingId) &&
                Objects.equals(this.classGroupingName, classGroupingResource.classGroupingName) &&
                Objects.equals(this.classGroupingType, classGroupingResource.classGroupingType) &&
                Objects.equals(this.responsibleTeacherId, classGroupingResource.responsibleTeacherId) &&
                Objects.equals(this.responsibleTeacherName, classGroupingResource.responsibleTeacherName) &&
                Objects.equals(this.responsibleTeacherNo, classGroupingResource.responsibleTeacherNo) &&
                Objects.equals(this.memberNum, classGroupingResource.memberNum) &&
                Objects.equals(this.memberList, classGroupingResource.memberList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classGroupingId, classGroupingName, classGroupingType, responsibleTeacherId, responsibleTeacherName,
                responsibleTeacherNo, memberNum, memberList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassGroupingResource {\n");

        sb.append("    classGroupingId: ").append(toIndentedString(classGroupingId)).append("\n");
        sb.append("    classGroupingName: ").append(toIndentedString(classGroupingName)).append("\n");
        sb.append("    classGroupingType: ").append(toIndentedString(classGroupingType)).append("\n");
        sb.append("    responsibleTeacherId: ").append(toIndentedString(responsibleTeacherId)).append("\n");
        sb.append("    responsibleTeacherName: ").append(toIndentedString(responsibleTeacherName)).append("\n");
        sb.append("    responsibleTeacherNo: ").append(toIndentedString(responsibleTeacherNo)).append("\n");
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


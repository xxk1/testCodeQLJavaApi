package com.lztech.site.viewmodel.classgroupingscheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassGroupingInsertResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-23T06:48:13.056Z")


public class ClassGroupingInsertResource {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("responsibleTeacherId")
    private String responsibleTeacherId = null;

    @JsonProperty("responsibleTeacherName")
    private String responsibleTeacherName = null;

    @JsonProperty("responsibleTeacherNo")
    private String responsibleTeacherNo = null;

    @JsonProperty("memberList")
    @Valid
    private List<ClassGroupingMemberInsertResource> memberList = null;

    public ClassGroupingInsertResource name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 小组名称
     * @return name
     **/
    @ApiModelProperty(value = "小组名称")


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassGroupingInsertResource responsibleTeacherId(String responsibleTeacherId) {
        this.responsibleTeacherId = responsibleTeacherId;
        return this;
    }

    /**
     * 负责教师id
     * @return responsibleTeacherId
     **/
    @ApiModelProperty(value = "负责教师id")


    public String getResponsibleTeacherId() {
        return responsibleTeacherId;
    }

    public void setResponsibleTeacherId(String responsibleTeacherId) {
        this.responsibleTeacherId = responsibleTeacherId;
    }

    public ClassGroupingInsertResource responsibleTeacherName(String responsibleTeacherName) {
        this.responsibleTeacherName = responsibleTeacherName;
        return this;
    }

    /**
     * 负责教师姓名
     * @return responsibleTeacherName
     **/
    @ApiModelProperty(value = "负责教师姓名")


    public String getResponsibleTeacherName() {
        return responsibleTeacherName;
    }

    public void setResponsibleTeacherName(String responsibleTeacherName) {
        this.responsibleTeacherName = responsibleTeacherName;
    }

    public ClassGroupingInsertResource responsibleTeacherNo(String responsibleTeacherNo) {
        this.responsibleTeacherNo = responsibleTeacherNo;
        return this;
    }

    /**
     * 负责教师工号
     * @return responsibleTeacherNo
     **/
    @ApiModelProperty(value = "负责教师工号")


    public String getResponsibleTeacherNo() {
        return responsibleTeacherNo;
    }

    public void setResponsibleTeacherNo(String responsibleTeacherNo) {
        this.responsibleTeacherNo = responsibleTeacherNo;
    }

    public ClassGroupingInsertResource memberList(List<ClassGroupingMemberInsertResource> memberList) {
        this.memberList = memberList;
        return this;
    }

    public ClassGroupingInsertResource addMemberListItem(ClassGroupingMemberInsertResource memberListItem) {
        if (this.memberList == null) {
            this.memberList = new ArrayList<ClassGroupingMemberInsertResource>();
        }
        this.memberList.add(memberListItem);
        return this;
    }

    /**
     * Get memberList
     * @return memberList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ClassGroupingMemberInsertResource> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<ClassGroupingMemberInsertResource> memberList) {
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
        ClassGroupingInsertResource classGroupingInsertResource = (ClassGroupingInsertResource) o;
        return Objects.equals(this.name, classGroupingInsertResource.name) &&
                Objects.equals(this.responsibleTeacherId, classGroupingInsertResource.responsibleTeacherId) &&
                Objects.equals(this.responsibleTeacherName, classGroupingInsertResource.responsibleTeacherName) &&
                Objects.equals(this.responsibleTeacherNo, classGroupingInsertResource.responsibleTeacherNo) &&
                Objects.equals(this.memberList, classGroupingInsertResource.memberList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, responsibleTeacherId, responsibleTeacherName, responsibleTeacherNo, memberList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassGroupingInsertResource {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    responsibleTeacherId: ").append(toIndentedString(responsibleTeacherId)).append("\n");
        sb.append("    responsibleTeacherName: ").append(toIndentedString(responsibleTeacherName)).append("\n");
        sb.append("    responsibleTeacherNo: ").append(toIndentedString(responsibleTeacherNo)).append("\n");
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


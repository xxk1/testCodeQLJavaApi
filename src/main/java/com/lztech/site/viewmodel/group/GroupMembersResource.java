package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupMembersResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-22T09:37:00.379+08:00")

public class GroupMembersResource {
    @JsonProperty("studentId")
    private String studentId = null;

    @JsonProperty("studentNo")
    private String studentNo = null;

    @JsonProperty("studentName")
    private String studentName = null;

    @JsonProperty("openId")
    private String openId = null;

    @JsonProperty("administrativeClassName")
    private String administrativeClassName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    public GroupMembersResource studentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    /**
     * 学生Id
     *
     * @return studentId
     **/
    @ApiModelProperty(value = "学生Id")


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public GroupMembersResource studentNo(String studentNo) {
        this.studentNo = studentNo;
        return this;
    }

    /**
     * 学生学号
     *
     * @return studentNo
     **/
    @ApiModelProperty(value = "学生学号")


    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public GroupMembersResource studentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    /**
     * 学生姓名
     *
     * @return studentName
     **/
    @ApiModelProperty(value = "学生姓名")


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public GroupMembersResource openId(String openId) {
        this.openId = openId;
        return this;
    }

    /**
     * 微信账号
     *
     * @return openId
     **/
    @ApiModelProperty(value = "微信账号")


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public GroupMembersResource administrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
        return this;
    }

    /**
     * 行政班名称
     *
     * @return administrativeClassName
     **/
    @ApiModelProperty(value = "行政班名称")


    public String getAdministrativeClassName() {
        return administrativeClassName;
    }

    public void setAdministrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
    }

    public GroupMembersResource collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public GroupMembersResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupMembersResource groupMembersResource = (GroupMembersResource) o;
        return Objects.equals(this.studentId, groupMembersResource.studentId) &&
                Objects.equals(this.studentNo, groupMembersResource.studentNo) &&
                Objects.equals(this.studentName, groupMembersResource.studentName) &&
                Objects.equals(this.openId, groupMembersResource.openId) &&
                Objects.equals(this.administrativeClassName, groupMembersResource.administrativeClassName) &&
                Objects.equals(this.collegeId, groupMembersResource.collegeId) &&
                Objects.equals(this.collegeName, groupMembersResource.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentNo, studentName, openId, administrativeClassName, collegeId, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupMembersResource {\n");

        sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
        sb.append("    studentNo: ").append(toIndentedString(studentNo)).append("\n");
        sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
        sb.append("    openId: ").append(toIndentedString(openId)).append("\n");
        sb.append("    administrativeClassName: ").append(toIndentedString(administrativeClassName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
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


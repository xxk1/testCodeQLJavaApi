package com.lztech.site.viewmodel.classgroupingscheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassGroupingMemberInsertResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-23T06:48:13.056Z")

public class ClassGroupingMemberInsertResource {
    @JsonProperty("studentId")
    private String studentId = null;

    @JsonProperty("studentName")
    private String studentName = null;

    @JsonProperty("studentNo")
    private String studentNo = null;

    @JsonProperty("administrativeClassName")
    private String administrativeClassName = null;

    @JsonProperty("majorName")
    private String majorName = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("whetherGroupLeader")
    private Boolean whetherGroupLeader = null;

    public ClassGroupingMemberInsertResource studentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    /**
     * 学生id
     *
     * @return studentId
     **/
    @ApiModelProperty(value = "学生id")


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public ClassGroupingMemberInsertResource studentName(String studentName) {
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

    public ClassGroupingMemberInsertResource studentNo(String studentNo) {
        this.studentNo = studentNo;
        return this;
    }

    /**
     * 学号
     *
     * @return studentNo
     **/
    @ApiModelProperty(value = "学号")


    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public ClassGroupingMemberInsertResource administrativeClassName(String administrativeClassName) {
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

    public ClassGroupingMemberInsertResource majorName(String majorName) {
        this.majorName = majorName;
        return this;
    }

    /**
     * 专业
     *
     * @return majorName
     **/
    @ApiModelProperty(value = "专业")


    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public ClassGroupingMemberInsertResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学员名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学员名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public ClassGroupingMemberInsertResource whetherGroupLeader(Boolean whetherGroupLeader) {
        this.whetherGroupLeader = whetherGroupLeader;
        return this;
    }

    /**
     * 是否为组长
     *
     * @return whetherGroupLeader
     **/
    @ApiModelProperty(value = "是否为组长")


    public Boolean isWhetherGroupLeader() {
        return whetherGroupLeader;
    }

    public void setWhetherGroupLeader(Boolean whetherGroupLeader) {
        this.whetherGroupLeader = whetherGroupLeader;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassGroupingMemberInsertResource classGroupingMemberInsertResource = (ClassGroupingMemberInsertResource) o;
        return Objects.equals(this.studentId, classGroupingMemberInsertResource.studentId) &&
                Objects.equals(this.studentName, classGroupingMemberInsertResource.studentName) &&
                Objects.equals(this.studentNo, classGroupingMemberInsertResource.studentNo) &&
                Objects.equals(this.administrativeClassName, classGroupingMemberInsertResource.administrativeClassName) &&
                Objects.equals(this.majorName, classGroupingMemberInsertResource.majorName) &&
                Objects.equals(this.collegeName, classGroupingMemberInsertResource.collegeName) &&
                Objects.equals(this.whetherGroupLeader, classGroupingMemberInsertResource.whetherGroupLeader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, studentNo, administrativeClassName, majorName, collegeName, whetherGroupLeader);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassGroupingMemberInsertResource {\n");

        sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
        sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
        sb.append("    studentNo: ").append(toIndentedString(studentNo)).append("\n");
        sb.append("    administrativeClassName: ").append(toIndentedString(administrativeClassName)).append("\n");
        sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    whetherGroupLeader: ").append(toIndentedString(whetherGroupLeader)).append("\n");
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


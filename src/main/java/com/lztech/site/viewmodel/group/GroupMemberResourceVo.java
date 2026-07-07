package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupMemberResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-18T08:19:25.649Z")


public class GroupMemberResourceVo {
    @JsonProperty("administrativeClassName")
    private String administrativeClassName = null;

    @JsonProperty("teachingClassId")
    private String teachingClassId = null;

    @JsonProperty("teachingClassName")
    private String teachingClassName = null;

    @JsonProperty("studentId")
    private String studentId = null;

    @JsonProperty("studentNo")
    private String studentNo = null;

    @JsonProperty("studentName")
    private String studentName = null;

    public GroupMemberResourceVo administrativeClassName(String administrativeClassName) {
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

    public GroupMemberResourceVo teachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
        return this;
    }

    /**
     * 教学班Id
     *
     * @return teachingClassId
     **/
    @ApiModelProperty(value = "教学班Id")


    public String getTeachingClassId() {
        return teachingClassId;
    }

    public void setTeachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    public GroupMemberResourceVo teachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    /**
     * 教学班名称
     *
     * @return teachingClassName
     **/
    @ApiModelProperty(value = "教学班名称")


    public String getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
    }

    public GroupMemberResourceVo studentId(String studentId) {
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

    public GroupMemberResourceVo studentNo(String studentNo) {
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

    public GroupMemberResourceVo studentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    /**
     * 用户姓名
     *
     * @return studentName
     **/
    @ApiModelProperty(value = "用户姓名")


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupMemberResourceVo groupMemberResourceVo = (GroupMemberResourceVo) o;
        return Objects.equals(this.administrativeClassName, groupMemberResourceVo.administrativeClassName) &&
                Objects.equals(this.teachingClassId, groupMemberResourceVo.teachingClassId) &&
                Objects.equals(this.teachingClassName, groupMemberResourceVo.teachingClassName) &&
                Objects.equals(this.studentId, groupMemberResourceVo.studentId) &&
                Objects.equals(this.studentNo, groupMemberResourceVo.studentNo) &&
                Objects.equals(this.studentName, groupMemberResourceVo.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(administrativeClassName, teachingClassId, teachingClassName, studentId, studentNo, studentName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupMemberResourceVo {\n");

        sb.append("    administrativeClassName: ").append(toIndentedString(administrativeClassName)).append("\n");
        sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
        sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
        sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
        sb.append("    studentNo: ").append(toIndentedString(studentNo)).append("\n");
        sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
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


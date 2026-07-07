package com.lztech.site.viewmodel.experimentalgroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ExperimentalGroupMemberResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-01-19T11:39:38.071Z")

public class ExperimentalGroupMemberResource {
    @JsonProperty("studentId")
    private String studentId = null;

    @JsonProperty("studentName")
    private String studentName = null;

    @JsonProperty("studentNo")
    private String studentNo = null;

    @JsonProperty("isGroupLeader")
    private Boolean isGroupLeader = null;

    public ExperimentalGroupMemberResource studentId(String studentId) {
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

    public ExperimentalGroupMemberResource studentName(String studentName) {
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

    public ExperimentalGroupMemberResource studentNo(String studentNo) {
        this.studentNo = studentNo;
        return this;
    }

    /**
     * 学生工号
     *
     * @return studentNo
     **/
    @ApiModelProperty(value = "学生工号")


    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public ExperimentalGroupMemberResource isGroupLeader(Boolean isGroupLeader) {
        this.isGroupLeader = isGroupLeader;
        return this;
    }

    /**
     * 是否是组长
     *
     * @return isGroupLeader
     **/
    @ApiModelProperty(value = "是否是组长")


    public Boolean isIsGroupLeader() {
        return isGroupLeader;
    }

    public void setIsGroupLeader(Boolean isGroupLeader) {
        this.isGroupLeader = isGroupLeader;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExperimentalGroupMemberResource experimentalGroupMemberResource = (ExperimentalGroupMemberResource) o;
        return Objects.equals(this.studentId, experimentalGroupMemberResource.studentId) &&
                Objects.equals(this.studentName, experimentalGroupMemberResource.studentName) &&
                Objects.equals(this.studentNo, experimentalGroupMemberResource.studentNo) &&
                Objects.equals(this.isGroupLeader, experimentalGroupMemberResource.isGroupLeader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, studentNo, isGroupLeader);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExperimentalGroupMemberResource {\n");

        sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
        sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
        sb.append("    studentNo: ").append(toIndentedString(studentNo)).append("\n");
        sb.append("    isGroupLeader: ").append(toIndentedString(isGroupLeader)).append("\n");
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


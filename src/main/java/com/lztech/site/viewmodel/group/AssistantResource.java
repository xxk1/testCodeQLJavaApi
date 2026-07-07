package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AssistantResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-09-16T02:34:17.432Z")


public class AssistantResource {
    @JsonProperty("studentId")
    private String studentId = null;

    @JsonProperty("studentNo")
    private String studentNo = null;

    @JsonProperty("studentName")
    private String studentName = null;

    public AssistantResource studentId(String studentId) {
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

    public AssistantResource studentNo(String studentNo) {
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

    public AssistantResource studentName(String studentName) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssistantResource assistantResource = (AssistantResource) o;
        return Objects.equals(this.studentId, assistantResource.studentId) &&
                Objects.equals(this.studentNo, assistantResource.studentNo) &&
                Objects.equals(this.studentName, assistantResource.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentNo, studentName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AssistantResource {\n");

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


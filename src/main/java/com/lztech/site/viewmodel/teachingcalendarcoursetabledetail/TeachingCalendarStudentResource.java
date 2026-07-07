package com.lztech.site.viewmodel.teachingcalendarcoursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingCalendarStudentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-20T12:04:45.217Z")

public class TeachingCalendarStudentResource {
    @JsonProperty("studentId")
    private String studentId = null;

    @JsonProperty("studentNo")
    private String studentNo = null;

    @JsonProperty("studentName")
    private String studentName = null;

    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public TeachingCalendarStudentResource studentId(String studentId) {
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

    public TeachingCalendarStudentResource studentNo(String studentNo) {
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

    public TeachingCalendarStudentResource studentName(String studentName) {
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
        TeachingCalendarStudentResource teachingCalendarStudentResource = (TeachingCalendarStudentResource) o;
        return Objects.equals(this.studentId, teachingCalendarStudentResource.studentId) &&
                Objects.equals(this.studentNo, teachingCalendarStudentResource.studentNo) &&
                Objects.equals(this.studentName, teachingCalendarStudentResource.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentNo, studentName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingCalendarStudentResource {\n");

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


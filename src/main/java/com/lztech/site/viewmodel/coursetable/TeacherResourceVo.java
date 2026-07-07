package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-19T07:56:11.253Z")


public class TeacherResourceVo {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    public TeacherResourceVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 教师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "教师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public TeacherResourceVo teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 教师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "教师工号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public TeacherResourceVo teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 教师姓名
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "教师姓名")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherResourceVo teacherResourceVo = (TeacherResourceVo) o;
        return Objects.equals(this.teacherId, teacherResourceVo.teacherId) &&
                Objects.equals(this.teacherNo, teacherResourceVo.teacherNo) &&
                Objects.equals(this.teacherName, teacherResourceVo.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherNo, teacherName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherResourceVo {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
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


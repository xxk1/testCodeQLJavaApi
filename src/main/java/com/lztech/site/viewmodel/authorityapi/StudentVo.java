package com.lztech.site.viewmodel.authorityapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StudentVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-22T07:52:01.555Z")


public class StudentVo {
    @JsonProperty("studentId")
    private String studentId = null;

    @JsonProperty("studentNo")
    private String studentNo = null;

    @JsonProperty("studentName")
    private String studentName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("className")
    private String className = null;

    public StudentVo studentId(String studentId) {
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

    public StudentVo studentNo(String studentNo) {
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

    public StudentVo studentName(String studentName) {
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

    public StudentVo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 所在学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "所在学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public StudentVo collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 所在学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "所在学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public StudentVo className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 行政班名称
     *
     * @return className
     **/
    @ApiModelProperty(value = "行政班名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentVo studentVo = (StudentVo) o;
        return Objects.equals(this.studentId, studentVo.studentId) &&
                Objects.equals(this.studentNo, studentVo.studentNo) &&
                Objects.equals(this.studentName, studentVo.studentName) &&
                Objects.equals(this.collegeId, studentVo.collegeId) &&
                Objects.equals(this.collegeName, studentVo.collegeName) &&
                Objects.equals(this.className, studentVo.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentNo, studentName, collegeId, collegeName, className);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentVo {\n");

        sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
        sb.append("    studentNo: ").append(toIndentedString(studentNo)).append("\n");
        sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
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


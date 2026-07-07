package com.lztech.site.viewmodel.authorityapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-18T02:13:07.892Z")


public class TeacherVo {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherCardNo")
    private String teacherCardNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    public TeacherVo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public TeacherVo teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 老师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "老师工号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public TeacherVo teacherCardNo(String teacherCardNo) {
        this.teacherCardNo = teacherCardNo;
        return this;
    }

    /**
     * 老师一卡通号
     *
     * @return teacherCardNo
     **/
    @ApiModelProperty(value = "老师一卡通号")


    public String getTeacherCardNo() {
        return teacherCardNo;
    }

    public void setTeacherCardNo(String teacherCardNo) {
        this.teacherCardNo = teacherCardNo;
    }

    public TeacherVo teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 老师姓名
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "老师姓名")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public TeacherVo collegeId(String collegeId) {
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

    public TeacherVo collegeName(String collegeName) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherVo teacherVo = (TeacherVo) o;
        return Objects.equals(this.teacherId, teacherVo.teacherId) &&
                Objects.equals(this.teacherNo, teacherVo.teacherNo) &&
                Objects.equals(this.teacherCardNo, teacherVo.teacherCardNo) &&
                Objects.equals(this.teacherName, teacherVo.teacherName) &&
                Objects.equals(this.collegeId, teacherVo.collegeId) &&
                Objects.equals(this.collegeName, teacherVo.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherNo, teacherCardNo, teacherName, collegeId, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherVo {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherCardNo: ").append(toIndentedString(teacherCardNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
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


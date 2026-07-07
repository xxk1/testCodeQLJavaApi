package com.lztech.site.viewmodel.expertinformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-04-15T08:04:04.064Z")


public class TeacherInfo {

    @JsonProperty("teacherId")
    private String teacherId = null;
    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherTitle")
    private String teacherTitle = null;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public TeacherInfo teacherName(String teacherName) {
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

    public TeacherInfo teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 老师编号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "老师编号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public TeacherInfo teacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
        return this;
    }

    /**
     * 老师职称
     *
     * @return teacherTitle
     **/
    @ApiModelProperty(value = "老师职称")


    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherInfo teacherInfo = (TeacherInfo) o;
        return Objects.equals(this.teacherName, teacherInfo.teacherName) &&
                Objects.equals(this.teacherNo, teacherInfo.teacherNo) &&
                Objects.equals(this.teacherTitle, teacherInfo.teacherTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherName, teacherNo, teacherTitle);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherInfo {\n");

        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherTitle: ").append(toIndentedString(teacherTitle)).append("\n");
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


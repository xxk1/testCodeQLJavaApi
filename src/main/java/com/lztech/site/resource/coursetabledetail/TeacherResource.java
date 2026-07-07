package com.lztech.site.resource.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-10T07:34:24.480Z")

public class TeacherResource {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherCollegeId")
    private String teacherCollegeId = null;

    @JsonProperty("teacherCollegeName")
    private String teacherCollegeName = null;

    public TeacherResource teacherId(String teacherId) {
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

    public TeacherResource teacherNo(String teacherNo) {
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

    public TeacherResource teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 教师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "教师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherCollegeId() {
        return teacherCollegeId;
    }

    public void setTeacherCollegeId(String teacherCollegeId) {
        this.teacherCollegeId = teacherCollegeId;
    }

    public String getTeacherCollegeName() {
        return teacherCollegeName;
    }

    public void setTeacherCollegeName(String teacherCollegeName) {
        this.teacherCollegeName = teacherCollegeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherResource teacherResource = (TeacherResource) o;
        return Objects.equals(this.teacherId, teacherResource.teacherId) &&
                Objects.equals(this.teacherNo, teacherResource.teacherNo) &&
                Objects.equals(this.teacherName, teacherResource.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherNo, teacherName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherResource {\n");

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
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


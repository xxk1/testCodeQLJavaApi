package com.lztech.site.viewmodel.superviseevaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailTeacherResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-12-10T08:57:08.369Z")


public class CourseTableDetailTeacherResource {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    public CourseTableDetailTeacherResource teacherId(String teacherId) {
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

    public CourseTableDetailTeacherResource teacherName(String teacherName) {
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

    public CourseTableDetailTeacherResource teacherNo(String teacherNo) {
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

    public CourseTableDetailTeacherResource collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public CourseTableDetailTeacherResource collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院ID
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院ID")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailTeacherResource courseTableDetailTeacherResource = (CourseTableDetailTeacherResource) o;
        return Objects.equals(this.teacherId, courseTableDetailTeacherResource.teacherId) &&
                Objects.equals(this.teacherName, courseTableDetailTeacherResource.teacherName) &&
                Objects.equals(this.teacherNo, courseTableDetailTeacherResource.teacherNo) &&
                Objects.equals(this.collegeName, courseTableDetailTeacherResource.collegeName) &&
                Objects.equals(this.collegeId, courseTableDetailTeacherResource.collegeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherName, teacherNo, collegeName, collegeId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailTeacherResource {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
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


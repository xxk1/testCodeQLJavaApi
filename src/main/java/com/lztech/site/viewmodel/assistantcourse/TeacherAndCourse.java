package com.lztech.site.viewmodel.assistantcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeacherAndCourse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-05-18T08:03:23.344Z")


public class TeacherAndCourse {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("roleId")
    private String roleId = null;

    public TeacherAndCourse courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public TeacherAndCourse teacherId(String teacherId) {
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

    public TeacherAndCourse teacherNo(String teacherNo) {
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

    public TeacherAndCourse teacherName(String teacherName) {
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

    public TeacherAndCourse roleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    /**
     * 角色id
     *
     * @return roleId
     **/
    @ApiModelProperty(value = "角色id")


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherAndCourse teacherAndCourse = (TeacherAndCourse) o;
        return Objects.equals(this.courseId, teacherAndCourse.courseId) &&
                Objects.equals(this.teacherId, teacherAndCourse.teacherId) &&
                Objects.equals(this.teacherNo, teacherAndCourse.teacherNo) &&
                Objects.equals(this.teacherName, teacherAndCourse.teacherName) &&
                Objects.equals(this.roleId, teacherAndCourse.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, teacherId, teacherNo, teacherName, roleId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherAndCourse {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    roleId: ").append(toIndentedString(roleId)).append("\n");
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


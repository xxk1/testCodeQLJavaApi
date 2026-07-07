package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * CourseModule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-12T06:24:43.405Z")


public class CourseModule {
    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseCategoryId")
    private String courseCategoryId = null;

    @JsonProperty("teachingUserType")
    private String teachingUserType = null;

    @JsonProperty("classHours")
    private BigDecimal classHours = null;

    @JsonProperty("courseContent")
    private String courseContent = null;

    public CourseModule teacherId(String teacherId) {
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

    public CourseModule teacherNo(String teacherNo) {
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

    public CourseModule teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 老师名称
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "老师名称")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseModule courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseModule collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院Id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院Id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CourseModule collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 学院编号
     *
     * @return collegeCode
     **/
    @ApiModelProperty(value = "学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public CourseModule collegeName(String collegeName) {
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

    public CourseModule courseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
        return this;
    }

    /**
     * 课程类别id
     *
     * @return courseCategoryId
     **/
    @ApiModelProperty(value = "课程类别id")


    public String getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public CourseModule teachingUserType(String teachingUserType) {
        this.teachingUserType = teachingUserType;
        return this;
    }

    /**
     * 授课对象
     *
     * @return teachingUserType
     **/
    @ApiModelProperty(value = "授课对象")


    public String getTeachingUserType() {
        return teachingUserType;
    }

    public void setTeachingUserType(String teachingUserType) {
        this.teachingUserType = teachingUserType;
    }

    public CourseModule classHours(BigDecimal classHours) {
        this.classHours = classHours;
        return this;
    }

    /**
     * 学时
     *
     * @return classHours
     **/
    @ApiModelProperty(value = "学时")

    @Valid

    public BigDecimal getClassHours() {
        return classHours;
    }

    public void setClassHours(BigDecimal classHours) {
        this.classHours = classHours;
    }

    public CourseModule courseContent(String courseContent) {
        this.courseContent = courseContent;
        return this;
    }

    /**
     * 课程内容
     *
     * @return courseContent
     **/
    @ApiModelProperty(value = "课程内容")


    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseModule courseModule = (CourseModule) o;
        return Objects.equals(this.teacherId, courseModule.teacherId) &&
                Objects.equals(this.teacherNo, courseModule.teacherNo) &&
                Objects.equals(this.teacherName, courseModule.teacherName) &&
                Objects.equals(this.courseName, courseModule.courseName) &&
                Objects.equals(this.collegeId, courseModule.collegeId) &&
                Objects.equals(this.collegeCode, courseModule.collegeCode) &&
                Objects.equals(this.collegeName, courseModule.collegeName) &&
                Objects.equals(this.courseCategoryId, courseModule.courseCategoryId) &&
                Objects.equals(this.teachingUserType, courseModule.teachingUserType) &&
                Objects.equals(this.classHours, courseModule.classHours) &&
                Objects.equals(this.courseContent, courseModule.courseContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherNo, teacherName, courseName, collegeId, collegeCode, collegeName, courseCategoryId, teachingUserType,
                classHours, courseContent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseModule {\n");

        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    teachingUserType: ").append(toIndentedString(teachingUserType)).append("\n");
        sb.append("    classHours: ").append(toIndentedString(classHours)).append("\n");
        sb.append("    courseContent: ").append(toIndentedString(courseContent)).append("\n");
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


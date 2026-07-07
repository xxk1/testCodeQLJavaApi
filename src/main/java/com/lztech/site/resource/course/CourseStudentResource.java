package com.lztech.site.resource.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseStudentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-15T06:51:59.564Z")

public class CourseStudentResource {
    @JsonProperty("courseId")
    private String courseId = null;
    @JsonProperty("courseCode")
    private String courseCode = null;
    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("courseCategoryId")
    private Integer courseCategoryId = null;

    @JsonProperty("courseCategory")
    private String courseCategory = null;

    @JsonProperty("teachingClasses")
    @Valid
    private List<CourseResourceStudentClassName> teachingClasses = null;

    public CourseStudentResource courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程Id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程Id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }



    public CourseStudentResource courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }


    public CourseStudentResource courseName(String courseName) {
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

    public CourseStudentResource collegeName(String collegeName) {
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

    public CourseStudentResource teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 上课老师id,多个老师使用“,” 隔开
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "上课老师id,多个老师使用“,” 隔开")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CourseStudentResource teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 上课老师名称,多个老师使用“,” 隔开
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "上课老师名称,多个老师使用“,” 隔开")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseStudentResource courseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
        return this;
    }

    /**
     * 课程类别Id
     *
     * @return courseCategoryId
     **/
    @ApiModelProperty(value = "课程类别Id")


    public Integer getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public CourseStudentResource courseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    /**
     * 课程类别
     *
     * @return courseCategory
     **/
    @ApiModelProperty(value = "课程类别")


    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public CourseStudentResource teachingClasses(List<CourseResourceStudentClassName> teachingClasses) {
        this.teachingClasses = teachingClasses;
        return this;
    }

    public CourseStudentResource addTeachingClassesItem(CourseResourceStudentClassName teachingClassesItem) {
        if (this.teachingClasses == null) {
            this.teachingClasses = new ArrayList<CourseResourceStudentClassName>();
        }
        this.teachingClasses.add(teachingClassesItem);
        return this;
    }

    /**
     * 上课班级列表
     *
     * @return teachingClasses
     **/
    @ApiModelProperty(value = "上课班级列表")

    @Valid

    public List<CourseResourceStudentClassName> getTeachingClasses() {
        return teachingClasses;
    }

    public void setTeachingClasses(List<CourseResourceStudentClassName> teachingClasses) {
        this.teachingClasses = teachingClasses;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseStudentResource courseStudentResource = (CourseStudentResource) o;
        return Objects.equals(this.courseId, courseStudentResource.courseId) &&
                Objects.equals(this.courseCode, courseStudentResource.courseCode) &&
                Objects.equals(this.courseName, courseStudentResource.courseName) &&
                Objects.equals(this.collegeName, courseStudentResource.collegeName) &&
                Objects.equals(this.teacherId, courseStudentResource.teacherId) &&
                Objects.equals(this.teacherName, courseStudentResource.teacherName) &&
                Objects.equals(this.courseCategoryId, courseStudentResource.courseCategoryId) &&
                Objects.equals(this.courseCategory, courseStudentResource.courseCategory) &&
                Objects.equals(this.teachingClasses, courseStudentResource.teachingClasses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode, courseName, collegeName, teacherId,
                teacherName, courseCategoryId, courseCategory, teachingClasses);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseStudentResource {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    courseCategory: ").append(toIndentedString(courseCategory)).append("\n");
        sb.append("    teachingClasses: ").append(toIndentedString(teachingClasses)).append("\n");
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


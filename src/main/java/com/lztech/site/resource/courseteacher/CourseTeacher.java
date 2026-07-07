package com.lztech.site.resource.courseteacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTeacher
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T07:23:50.088Z")

public class CourseTeacher {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseCategory")
    private Integer courseCategory = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    public CourseTeacher courseId(String courseId) {
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

    public CourseTeacher courseName(String courseName) {
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

    public CourseTeacher courseCategory(Integer courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    /**
     * 课程类别
     *
     * @return courseCategory
     **/
    @ApiModelProperty(value = "课程类别")


    public Integer getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(Integer courseCategory) {
        this.courseCategory = courseCategory;
    }

    public CourseTeacher courseCode(String courseCode) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTeacher courseTeacher = (CourseTeacher) o;
        return Objects.equals(this.courseId, courseTeacher.courseId) &&
                Objects.equals(this.courseName, courseTeacher.courseName) &&
                Objects.equals(this.courseCategory, courseTeacher.courseCategory) &&
                Objects.equals(this.courseCode, courseTeacher.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseCategory, courseCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTeacher {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCategory: ").append(toIndentedString(courseCategory)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
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


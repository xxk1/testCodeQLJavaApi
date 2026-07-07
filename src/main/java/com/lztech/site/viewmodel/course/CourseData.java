package com.lztech.site.viewmodel.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-09T06:58:32.214Z")


public class CourseData {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseTypeId")
    private String courseTypeId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    public CourseData courseId(String courseId) {
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

    public CourseData courseCode(String courseCode) {
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

    public CourseData courseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
        return this;
    }

    /**
     * 课程类型id
     *
     * @return courseTypeId
     **/
    @ApiModelProperty(value = "课程类型id")


    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public CourseData courseName(String courseName) {
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

    public CourseData collegeName(String collegeName) {
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
        CourseData courseData = (CourseData) o;
        return Objects.equals(this.courseId, courseData.courseId) &&
                Objects.equals(this.courseCode, courseData.courseCode) &&
                Objects.equals(this.courseTypeId, courseData.courseTypeId) &&
                Objects.equals(this.courseName, courseData.courseName) &&
                Objects.equals(this.collegeName, courseData.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode, courseTypeId, courseName, collegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseData {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseTypeId: ").append(toIndentedString(courseTypeId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
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


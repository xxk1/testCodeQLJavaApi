package com.lztech.site.resource.courseteacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTeacherVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-27T07:35:19.608Z")


public class CourseTeacherVo {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseCategoryId")
    private Integer courseCategoryId = null;

    @JsonProperty("courseCategoryName")
    private String courseCategoryName = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    public CourseTeacherVo courseId(String courseId) {
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

    public CourseTeacherVo courseName(String courseName) {
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

    public CourseTeacherVo courseCategoryId(Integer courseCategoryId) {
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

    public CourseTeacherVo courseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
        return this;
    }

    /**
     * 课程类别名称
     *
     * @return courseCategoryName
     **/
    @ApiModelProperty(value = "课程类别名称")


    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
    }

    public CourseTeacherVo courseCode(String courseCode) {
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
        CourseTeacherVo courseTeacherVo = (CourseTeacherVo) o;
        return Objects.equals(this.courseId, courseTeacherVo.courseId) &&
                Objects.equals(this.courseName, courseTeacherVo.courseName) &&
                Objects.equals(this.courseCategoryId, courseTeacherVo.courseCategoryId) &&
                Objects.equals(this.courseCategoryName, courseTeacherVo.courseCategoryName) &&
                Objects.equals(this.courseCode, courseTeacherVo.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseCategoryId, courseCategoryName, courseCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTeacherVo {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
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


package com.lztech.site.viewmodel.courseexpansion;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseExpansionResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-08T06:03:14.171Z")


public class CourseExpansionResource {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseExpansionId")
    private String courseExpansionId = null;

    @JsonProperty("expansionKey")
    private String expansionKey = null;

    @JsonProperty("expansionKeyName")
    private String expansionKeyName = null;

    public String getExpansionKey() {
        return expansionKey;
    }

    public void setExpansionKey(String expansionKey) {
        this.expansionKey = expansionKey;
    }

    public String getExpansionKeyName() {
        return expansionKeyName;
    }

    public void setExpansionKeyName(String expansionKeyName) {
        this.expansionKeyName = expansionKeyName;
    }

    public CourseExpansionResource courseId(String courseId) {
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

    public CourseExpansionResource courseCode(String courseCode) {
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

    public CourseExpansionResource courseName(String courseName) {
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

    public CourseExpansionResource collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CourseExpansionResource collegeName(String collegeName) {
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

    public CourseExpansionResource courseExpansionId(String courseExpansionId) {
        this.courseExpansionId = courseExpansionId;
        return this;
    }

    /**
     * 课程附属属性id
     *
     * @return courseExpansionId
     **/
    @ApiModelProperty(value = "课程附属属性id")


    public String getCourseExpansionId() {
        return courseExpansionId;
    }

    public void setCourseExpansionId(String courseExpansionId) {
        this.courseExpansionId = courseExpansionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseExpansionResource courseExpansionResource = (CourseExpansionResource) o;
        return Objects.equals(this.courseId, courseExpansionResource.courseId) &&
                Objects.equals(this.courseCode, courseExpansionResource.courseCode) &&
                Objects.equals(this.courseName, courseExpansionResource.courseName) &&
                Objects.equals(this.collegeId, courseExpansionResource.collegeId) &&
                Objects.equals(this.collegeName, courseExpansionResource.collegeName) &&
                Objects.equals(this.courseExpansionId, courseExpansionResource.courseExpansionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode, courseName, collegeId, collegeName, courseExpansionId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseExpansionResource {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    courseExpansionId: ").append(toIndentedString(courseExpansionId)).append("\n");
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


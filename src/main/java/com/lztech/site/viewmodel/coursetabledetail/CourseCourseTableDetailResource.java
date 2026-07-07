package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseCourseTableDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-14T07:52:01.318Z")

public class CourseCourseTableDetailResource {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("inClassCount")
    private Integer inClassCount = null;

    public CourseCourseTableDetailResource courseId(String courseId) {
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

    public CourseCourseTableDetailResource courseName(String courseName) {
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

    public CourseCourseTableDetailResource inClassCount(Integer inClassCount) {
        this.inClassCount = inClassCount;
        return this;
    }

    /**
     * 正在上课的课程数量
     *
     * @return inClassCount
     **/
    @ApiModelProperty(value = "正在上课的课程数量")


    public Integer getInClassCount() {
        return inClassCount;
    }

    public void setInClassCount(Integer inClassCount) {
        this.inClassCount = inClassCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseCourseTableDetailResource courseCourseTableDetailResource = (CourseCourseTableDetailResource) o;
        return Objects.equals(this.courseId, courseCourseTableDetailResource.courseId) &&
                Objects.equals(this.courseName, courseCourseTableDetailResource.courseName) &&
                Objects.equals(this.inClassCount, courseCourseTableDetailResource.inClassCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, inClassCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseCourseTableDetailResource {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    inClassCount: ").append(toIndentedString(inClassCount)).append("\n");
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


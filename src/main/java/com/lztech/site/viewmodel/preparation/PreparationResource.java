package com.lztech.site.viewmodel.preparation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PreparationResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-25T03:07:00.312Z")


public class PreparationResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    public PreparationResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * id
     *
     * @return id
     **/
    @ApiModelProperty(value = "id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PreparationResource courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程ID")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public PreparationResource courseName(String courseName) {
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

    public PreparationResource teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师Id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师Id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public PreparationResource teacherName(String teacherName) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PreparationResource preparationResource = (PreparationResource) o;
        return Objects.equals(this.id, preparationResource.id) &&
                Objects.equals(this.courseId, preparationResource.courseId) &&
                Objects.equals(this.courseName, preparationResource.courseName) &&
                Objects.equals(this.teacherId, preparationResource.teacherId) &&
                Objects.equals(this.teacherName, preparationResource.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, courseName, teacherId, teacherName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PreparationResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
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


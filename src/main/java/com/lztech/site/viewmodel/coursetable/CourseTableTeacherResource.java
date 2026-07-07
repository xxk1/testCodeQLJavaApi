package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableTeacherResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-06T07:38:14.152Z")


public class CourseTableTeacherResource {
    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("teacherIds")
    private String teacherIds = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    @JsonProperty("teacherNos")
    private String teacherNos = null;

    public CourseTableTeacherResource courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 课表id
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "课表id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public CourseTableTeacherResource teacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
        return this;
    }

    /**
     * 老师id拼接
     *
     * @return teacherIds
     **/
    @ApiModelProperty(value = "老师id拼接")


    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public CourseTableTeacherResource teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 老师名称拼接
     *
     * @return teacherNames
     **/
    @ApiModelProperty(value = "老师名称拼接")


    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    public CourseTableTeacherResource teacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
        return this;
    }

    /**
     * 老师工号拼接
     *
     * @return teacherNos
     **/
    @ApiModelProperty(value = "老师工号拼接")


    public String getTeacherNos() {
        return teacherNos;
    }

    public void setTeacherNos(String teacherNos) {
        this.teacherNos = teacherNos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableTeacherResource courseTableTeacherResource = (CourseTableTeacherResource) o;
        return Objects.equals(this.courseTableId, courseTableTeacherResource.courseTableId) &&
                Objects.equals(this.teacherIds, courseTableTeacherResource.teacherIds) &&
                Objects.equals(this.teacherNames, courseTableTeacherResource.teacherNames) &&
                Objects.equals(this.teacherNos, courseTableTeacherResource.teacherNos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableId, teacherIds, teacherNames, teacherNos);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableTeacherResource {\n");

        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    teacherIds: ").append(toIndentedString(teacherIds)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    teacherNos: ").append(toIndentedString(teacherNos)).append("\n");
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


package com.lztech.domain.model.coursetabledetailteacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseAndMembers
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-01-07T03:37:07.715Z")


public class CourseAndMembers {
    @JsonProperty("teachingClassId")
    private String teachingClassId = null;

    @JsonProperty("teachingClassMembers")
    private Integer teachingClassMembers = null;

    @JsonProperty("teachingClassTeacherName")
    private String teachingClassTeacherName = null;

    public CourseAndMembers teachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
        return this;
    }

    /**
     * 上课班级Id
     *
     * @return teachingClassId
     **/
    @ApiModelProperty(value = "上课班级Id")


    public String getTeachingClassId() {
        return teachingClassId;
    }

    public void setTeachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    public CourseAndMembers teachingClassMembers(Integer teachingClassMembers) {
        this.teachingClassMembers = teachingClassMembers;
        return this;
    }

    /**
     * 上课班级人数
     *
     * @return teachingClassMembers
     **/
    @ApiModelProperty(value = "上课班级人数")


    public Integer getTeachingClassMembers() {
        return teachingClassMembers;
    }

    public void setTeachingClassMembers(Integer teachingClassMembers) {
        this.teachingClassMembers = teachingClassMembers;
    }

    public CourseAndMembers teachingClassTeacherName(String teachingClassTeacherName) {
        this.teachingClassTeacherName = teachingClassTeacherName;
        return this;
    }

    /**
     * 上课老師
     *
     * @return teachingClassTeacherName
     **/
    @ApiModelProperty(value = "上课老師")


    public String getTeachingClassTeacherName() {
        return teachingClassTeacherName;
    }

    public void setTeachingClassTeacherName(String teachingClassTeacherName) {
        this.teachingClassTeacherName = teachingClassTeacherName;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseAndMembers courseAndMembers = (CourseAndMembers) o;
        return Objects.equals(this.teachingClassId, courseAndMembers.teachingClassId) &&
                Objects.equals(this.teachingClassMembers, courseAndMembers.teachingClassMembers) &&
                Objects.equals(this.teachingClassTeacherName, courseAndMembers.teachingClassTeacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachingClassId, teachingClassMembers, teachingClassTeacherName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseAndMembers {\n");

        sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
        sb.append("    teachingClassMembers: ").append(toIndentedString(teachingClassMembers)).append("\n");
        sb.append("    teachingClassTeacherName: ").append(toIndentedString(teachingClassTeacherName)).append("\n");
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


package com.lztech.site.resource.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StudentHomepageCourseTeachingClassName
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-17T06:55:18.036Z")

public class StudentHomepageCourseTeachingClassName {
    @JsonProperty("teachingClassId")
    private String teachingClassId = null;

    @JsonProperty("teachingClassName")
    private String teachingClassName = null;

    @JsonProperty("teachingClassCode")
    private String teachingClassCode = null;

    @JsonProperty("teachingClassSort")
    private Integer teachingClassSort = null;

    @JsonProperty("teachingClassMembers")
    private Integer teachingClassMembers = null;

    public StudentHomepageCourseTeachingClassName teachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
        return this;
    }

    /**
     * 上课班级Id(学生所在班级)
     *
     * @return teachingClassId
     **/
    @ApiModelProperty(value = "上课班级Id(学生所在班级)")


    public String getTeachingClassId() {
        return teachingClassId;
    }

    public void setTeachingClassId(String teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    public StudentHomepageCourseTeachingClassName teachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
        return this;
    }

    /**
     * 上课班级(学生所在班级)
     *
     * @return teachingClassName
     **/
    @ApiModelProperty(value = "上课班级(学生所在班级)")


    public String getTeachingClassName() {
        return teachingClassName;
    }

    public void setTeachingClassName(String teachingClassName) {
        this.teachingClassName = teachingClassName;
    }

    public StudentHomepageCourseTeachingClassName teachingClassCode(String teachingClassCode) {
        this.teachingClassCode = teachingClassCode;
        return this;
    }

    /**
     * teachingClassCode(学生所在班级编号)
     *
     * @return teachingClassCode
     **/
    @ApiModelProperty(value = "学生所在班级编号")


    public String getTeachingClassCode() {
        return teachingClassCode;
    }

    public void setTeachingClassCode(String teachingClassCode) {
        this.teachingClassCode = teachingClassCode;
    }

    public StudentHomepageCourseTeachingClassName teachingClassSort(Integer teachingClassSort) {
        this.teachingClassSort = teachingClassSort;
        return this;
    }

    /**
     * 上课班级排序
     *
     * @return teachingClassSort
     **/
    @ApiModelProperty(value = "上课班级排序")


    public Integer getTeachingClassSort() {
        return teachingClassSort;
    }

    public void setTeachingClassSort(Integer teachingClassSort) {
        this.teachingClassSort = teachingClassSort;
    }

    public StudentHomepageCourseTeachingClassName teachingClassMembers(Integer teachingClassMembers) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentHomepageCourseTeachingClassName studentHomepageCourseTeachingClassName = (StudentHomepageCourseTeachingClassName) o;
        return Objects.equals(this.teachingClassId, studentHomepageCourseTeachingClassName.teachingClassId) &&
                Objects.equals(this.teachingClassName, studentHomepageCourseTeachingClassName.teachingClassName) &&
                Objects.equals(this.teachingClassCode, studentHomepageCourseTeachingClassName.teachingClassCode) &&
                Objects.equals(this.teachingClassSort, studentHomepageCourseTeachingClassName.teachingClassSort) &&
                Objects.equals(this.teachingClassMembers, studentHomepageCourseTeachingClassName.teachingClassMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachingClassId, teachingClassName,teachingClassCode,teachingClassSort, teachingClassMembers);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentHomepageCourseTeachingClassName {\n");
        sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
        sb.append("    teachingClassName: ").append(toIndentedString(teachingClassName)).append("\n");
        sb.append("    teachingClassCode: ").append(toIndentedString(teachingClassCode)).append("\n");
        sb.append("    teachingClassSort: ").append(toIndentedString(teachingClassSort)).append("\n");
        sb.append("    teachingClassMembers: ").append(toIndentedString(teachingClassMembers)).append("\n");
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


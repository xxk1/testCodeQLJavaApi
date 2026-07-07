package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-18T09:14:06.862Z")

public class GroupDetails {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("classId")
    private String classId = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("classSize")
    private String classSize = null;

    @JsonProperty("courseTableId")
    private String courseTableId = null;

    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    public GroupDetails courseId(String courseId) {
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

    public GroupDetails classId(String classId) {
        this.classId = classId;
        return this;
    }

    /**
     * 班级Id
     *
     * @return classId
     **/
    @ApiModelProperty(value = "班级Id")


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public GroupDetails className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 班级名称
     *
     * @return className
     **/
    @ApiModelProperty(value = "班级名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public GroupDetails classSize(String classSize) {
        this.classSize = classSize;
        return this;
    }

    /**
     * 班级人数
     *
     * @return classSize
     **/
    @ApiModelProperty(value = "班级人数")


    public String getClassSize() {
        return classSize;
    }

    public void setClassSize(String classSize) {
        this.classSize = classSize;
    }

    public GroupDetails courseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
        return this;
    }

    /**
     * 主课表Id
     *
     * @return courseTableId
     **/
    @ApiModelProperty(value = "主课表Id")


    public String getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(String courseTableId) {
        this.courseTableId = courseTableId;
    }

    public GroupDetails courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课程明细Id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课程明细Id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupDetails groupDetails = (GroupDetails) o;
        return Objects.equals(this.courseId, groupDetails.courseId) &&
                Objects.equals(this.classId, groupDetails.classId) &&
                Objects.equals(this.className, groupDetails.className) &&
                Objects.equals(this.classSize, groupDetails.classSize) &&
                Objects.equals(this.courseTableId, groupDetails.courseTableId) &&
                Objects.equals(this.courseTableDetailId, groupDetails.courseTableDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, classId, className, classSize, courseTableId, courseTableDetailId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupDetails {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    classSize: ").append(toIndentedString(classSize)).append("\n");
        sb.append("    courseTableId: ").append(toIndentedString(courseTableId)).append("\n");
        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
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


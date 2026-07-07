package com.lztech.site.resource.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceStudentClassName
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-13T06:14:52.200Z")

public class CourseResourceStudentClassName {
    @JsonProperty("classId")
    private String classId = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("classNo")
    private String classNo = null;

    @JsonProperty("classSort")
    private Integer classSort = null;

    @JsonProperty("classType")
    private Integer classType = null;

    @JsonProperty("classMembers")
    private Integer classMembers = null;

    public CourseResourceStudentClassName classId(String classId) {
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

    public CourseResourceStudentClassName classType(Integer classType) {
        this.classType = classType;
        return this;
    }

    /**
     * 班级类型
     *
     * @return classType
     **/
    @ApiModelProperty(value = "班级类型")

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public CourseResourceStudentClassName className(String className) {
        this.className = className;
        return this;
    }
    /**
     * 班级名称
     * @return className
     **/
    @ApiModelProperty(value = "班级名称")

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CourseResourceStudentClassName classNo(String classNo) {
        this.classNo = classNo;
        return this;
    }
    /**
     * 班级编号
     * @return classNo
     **/
    @ApiModelProperty(value = "班级编号")
    public String getClassNo() {
        return classNo;
    }
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public CourseResourceStudentClassName classSort(Integer classSort) {
        this.classSort = classSort;
        return this;
    }
    /**
     * 班级排序
     * @return classSort
     **/
    @ApiModelProperty(value = "班级排序")
    public Integer getClassSort() {
        return classSort;
    }
    public void setClassSort(Integer classSort) {
        this.classSort = classSort;
    }


    public CourseResourceStudentClassName classMembers(Integer classMembers) {
        this.classMembers = classMembers;
        return this;
    }

    /**
     * 班级人数
     *
     * @return classMembers
     **/
    @ApiModelProperty(value = "班级人数")


    public Integer getClassMembers() {
        return classMembers;
    }

    public void setClassMembers(Integer classMembers) {
        this.classMembers = classMembers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceStudentClassName courseResourceStudentClassName = (CourseResourceStudentClassName) o;
        return Objects.equals(this.classId, courseResourceStudentClassName.classId) &&
                Objects.equals(this.className, courseResourceStudentClassName.className) &&
                Objects.equals(this.classNo, courseResourceStudentClassName.classNo) &&
                Objects.equals(this.classSort, courseResourceStudentClassName.classSort) &&
                Objects.equals(this.classType, courseResourceStudentClassName.classType) &&
                Objects.equals(this.classMembers, courseResourceStudentClassName.classMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, className,classNo,classSort, classType,classMembers);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceStudentClassName {\n");
        sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    classNo: ").append(toIndentedString(classNo)).append("\n");
        sb.append("    classSort: ").append(toIndentedString(classSort)).append("\n");
        sb.append("    classType: ").append(toIndentedString(classType)).append("\n");
        sb.append("    classMembers: ").append(toIndentedString(classMembers)).append("\n");
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


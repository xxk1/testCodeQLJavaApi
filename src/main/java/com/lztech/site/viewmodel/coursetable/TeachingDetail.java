package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingDetail
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-09T06:48:39.976Z")


public class TeachingDetail {

    @JsonProperty("groupSource")
    private Integer groupSource;

    @JsonProperty("classId")
    private String classId = null;

    @JsonProperty("courseNo")
    private String courseNo = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("teacherNames")
    private String teacherNames = null;

    @JsonProperty("collegeIds")
    private String collegeIds = null;

    @JsonProperty("collegeNames")
    private String collegeNames = null;

    public Integer getGroupSource() {
        return groupSource;
    }

    public void setGroupSource(Integer groupSource) {
        this.groupSource = groupSource;
    }

    public TeachingDetail classId(String classId) {
        this.classId = classId;
        return this;
    }

    /**
     * 教学班id
     *
     * @return classId
     **/
    @ApiModelProperty(value = "教学班id")


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public TeachingDetail courseNo(String courseNo) {
        this.courseNo = courseNo;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseNo
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public TeachingDetail courseName(String courseName) {
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

    public TeachingDetail className(String className) {
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

    public TeachingDetail teacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
        return this;
    }

    /**
     * 老师名称
     *
     * @return teacherNames
     **/
    @ApiModelProperty(value = "老师名称")


    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    public TeachingDetail collegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
        return this;
    }

    /**
     * 学院ids
     *
     * @return collegeIds
     **/
    @ApiModelProperty(value = "学院ids")


    public String getCollegeIds() {
        return collegeIds;
    }

    public void setCollegeIds(String collegeIds) {
        this.collegeIds = collegeIds;
    }

    public TeachingDetail collegeNames(String collegeNames) {
        this.collegeNames = collegeNames;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeNames
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeNames() {
        return collegeNames;
    }

    public void setCollegeNames(String collegeNames) {
        this.collegeNames = collegeNames;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachingDetail teachingDetail = (TeachingDetail) o;
        return Objects.equals(this.classId, teachingDetail.classId) &&
                Objects.equals(this.courseNo, teachingDetail.courseNo) &&
                Objects.equals(this.courseName, teachingDetail.courseName) &&
                Objects.equals(this.className, teachingDetail.className) &&
                Objects.equals(this.teacherNames, teachingDetail.teacherNames) &&
                Objects.equals(this.collegeIds, teachingDetail.collegeIds) &&
                Objects.equals(this.collegeNames, teachingDetail.collegeNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, courseNo, courseName, className, teacherNames, collegeIds, collegeNames);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeachingDetail {\n");

        sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
        sb.append("    courseNo: ").append(toIndentedString(courseNo)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    teacherNames: ").append(toIndentedString(teacherNames)).append("\n");
        sb.append("    collegeIds: ").append(toIndentedString(collegeIds)).append("\n");
        sb.append("    collegeNames: ").append(toIndentedString(collegeNames)).append("\n");
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


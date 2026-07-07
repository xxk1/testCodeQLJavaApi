package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * CourseTableSuperviseVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-08-30T03:36:18.420Z")


public class CourseTableSuperviseVo {


    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseGroup")
    @Valid
    private Set<CourseGroupSuperviseVo> courseGroup = null;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public CourseTableSuperviseVo courseName(String courseName) {
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


    public CourseTableSuperviseVo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院ID
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院ID")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CourseTableSuperviseVo collegeName(String collegeName) {
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


    public CourseTableSuperviseVo courseId(String courseId) {
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

    public CourseTableSuperviseVo courseGroup(Set<CourseGroupSuperviseVo> courseGroup) {
        this.courseGroup = courseGroup;
        return this;
    }

    public CourseTableSuperviseVo addCourseGroupItem(CourseGroupSuperviseVo courseGroupItem) {
        if (this.courseGroup == null) {
            this.courseGroup = new HashSet<CourseGroupSuperviseVo>();
        }
        this.courseGroup.add(courseGroupItem);
        return this;
    }

    /**
     * 上课班级信息
     *
     * @return courseGroup
     **/
    @ApiModelProperty(value = "上课班级信息")

    @Valid

    public Set<CourseGroupSuperviseVo> getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(Set<CourseGroupSuperviseVo> courseGroup) {
        this.courseGroup = courseGroup;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableSuperviseVo courseTableSuperviseVo = (CourseTableSuperviseVo) o;
        return Objects.equals(this.courseName, courseTableSuperviseVo.courseName) &&
                Objects.equals(this.courseId, courseTableSuperviseVo.courseId) &&
                Objects.equals(this.courseGroup, courseTableSuperviseVo.courseGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseId, courseGroup);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableSuperviseVo {\n");

        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseGroup: ").append(toIndentedString(courseGroup)).append("\n");
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


package com.lztech.site.viewmodel.studentcoursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * StudentCourseTableResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-11-15T08:46:21.704Z")


public class StudentCourseTableResource {
    @JsonProperty("courseTableDetailId")
    private String courseTableDetailId = null;

    @JsonProperty("segmentStartTime")
    private String segmentStartTime = null;

    @JsonProperty("segmentEndTime")
    private String segmentEndTime = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("projectList")
    @Valid
    private List<StudentCourseTableProjectResource> projectList = null;

    public StudentCourseTableResource courseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
        return this;
    }

    /**
     * 课表明细id
     *
     * @return courseTableDetailId
     **/
    @ApiModelProperty(value = "课表明细id")


    public String getCourseTableDetailId() {
        return courseTableDetailId;
    }

    public void setCourseTableDetailId(String courseTableDetailId) {
        this.courseTableDetailId = courseTableDetailId;
    }

    public StudentCourseTableResource segmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
        return this;
    }

    /**
     * 节次开始时间
     *
     * @return segmentStartTime
     **/
    @ApiModelProperty(value = "节次开始时间")


    public String getSegmentStartTime() {
        return segmentStartTime;
    }

    public void setSegmentStartTime(String segmentStartTime) {
        this.segmentStartTime = segmentStartTime;
    }

    public StudentCourseTableResource segmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
        return this;
    }

    /**
     * 节次结束时间
     *
     * @return segmentEndTime
     **/
    @ApiModelProperty(value = "节次结束时间")


    public String getSegmentEndTime() {
        return segmentEndTime;
    }

    public void setSegmentEndTime(String segmentEndTime) {
        this.segmentEndTime = segmentEndTime;
    }

    public StudentCourseTableResource courseId(String courseId) {
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

    public StudentCourseTableResource courseName(String courseName) {
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

    public StudentCourseTableResource projectList(List<StudentCourseTableProjectResource> projectList) {
        this.projectList = projectList;
        return this;
    }

    public StudentCourseTableResource addProjectListItem(StudentCourseTableProjectResource projectListItem) {
        if (this.projectList == null) {
            this.projectList = new ArrayList<StudentCourseTableProjectResource>();
        }
        this.projectList.add(projectListItem);
        return this;
    }

    /**
     * Get projectList
     *
     * @return projectList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<StudentCourseTableProjectResource> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<StudentCourseTableProjectResource> projectList) {
        this.projectList = projectList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentCourseTableResource studentCourseTableResource = (StudentCourseTableResource) o;
        return Objects.equals(this.courseTableDetailId, studentCourseTableResource.courseTableDetailId) &&
                Objects.equals(this.segmentStartTime, studentCourseTableResource.segmentStartTime) &&
                Objects.equals(this.segmentEndTime, studentCourseTableResource.segmentEndTime) &&
                Objects.equals(this.courseId, studentCourseTableResource.courseId) &&
                Objects.equals(this.courseName, studentCourseTableResource.courseName) &&
                Objects.equals(this.projectList, studentCourseTableResource.projectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTableDetailId, segmentStartTime, segmentEndTime, courseId, courseName, projectList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentCourseTableResource {\n");

        sb.append("    courseTableDetailId: ").append(toIndentedString(courseTableDetailId)).append("\n");
        sb.append("    segmentStartTime: ").append(toIndentedString(segmentStartTime)).append("\n");
        sb.append("    segmentEndTime: ").append(toIndentedString(segmentEndTime)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    projectList: ").append(toIndentedString(projectList)).append("\n");
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


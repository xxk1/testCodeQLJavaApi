package com.lztech.site.viewmodel.peerreview;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TeacherPeerReviewCourseResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-22T02:31:37.314Z")


public class TeacherPeerReviewCourseResource {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("projectList")
    @Valid
    private List<TeacherPeerReviewProjectResource> projectList = null;

    @JsonProperty("groupList")
    @Valid
    private List<TeacherPeerReviewGroupResource> groupList = null;

    public TeacherPeerReviewCourseResource courseId(String courseId) {
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

    public TeacherPeerReviewCourseResource courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public TeacherPeerReviewCourseResource courseName(String courseName) {
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

    public TeacherPeerReviewCourseResource projectList(List<TeacherPeerReviewProjectResource> projectList) {
        this.projectList = projectList;
        return this;
    }

    public TeacherPeerReviewCourseResource addProjectListItem(TeacherPeerReviewProjectResource projectListItem) {
        if (this.projectList == null) {
            this.projectList = new ArrayList<TeacherPeerReviewProjectResource>();
        }
        this.projectList.add(projectListItem);
        return this;
    }

    /**
     * 课程项目列表
     *
     * @return projectList
     **/
    @ApiModelProperty(value = "课程项目列表")

    @Valid

    public List<TeacherPeerReviewProjectResource> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<TeacherPeerReviewProjectResource> projectList) {
        this.projectList = projectList;
    }

    public TeacherPeerReviewCourseResource groupList(List<TeacherPeerReviewGroupResource> groupList) {
        this.groupList = groupList;
        return this;
    }

    public TeacherPeerReviewCourseResource addGroupListItem(TeacherPeerReviewGroupResource groupListItem) {
        if (this.groupList == null) {
            this.groupList = new ArrayList<TeacherPeerReviewGroupResource>();
        }
        this.groupList.add(groupListItem);
        return this;
    }

    /**
     * 组列表
     *
     * @return groupList
     **/
    @ApiModelProperty(value = "组列表")

    @Valid

    public List<TeacherPeerReviewGroupResource> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<TeacherPeerReviewGroupResource> groupList) {
        this.groupList = groupList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherPeerReviewCourseResource teacherPeerReviewCourseResource = (TeacherPeerReviewCourseResource) o;
        return Objects.equals(this.courseId, teacherPeerReviewCourseResource.courseId) &&
                Objects.equals(this.courseCode, teacherPeerReviewCourseResource.courseCode) &&
                Objects.equals(this.courseName, teacherPeerReviewCourseResource.courseName) &&
                Objects.equals(this.projectList, teacherPeerReviewCourseResource.projectList) &&
                Objects.equals(this.groupList, teacherPeerReviewCourseResource.groupList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode, courseName, projectList, groupList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherPeerReviewCourseResource {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    projectList: ").append(toIndentedString(projectList)).append("\n");
        sb.append("    groupList: ").append(toIndentedString(groupList)).append("\n");
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


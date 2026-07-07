package com.lztech.site.viewmodel.projectcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProjectCardVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-24T08:52:44.163Z")


public class ProjectCardVo {
    @JsonProperty("projectId")
    private String projectId = null;

    @JsonProperty("projectName")
    private String projectName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("projectSuiteIds")
    @Valid
    private List<String> projectSuiteIds = null;

    public ProjectCardVo projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * 项目Id
     *
     * @return projectId
     **/
    @ApiModelProperty(value = "项目Id")


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ProjectCardVo projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    /**
     * 项目名称
     *
     * @return projectName
     **/
    @ApiModelProperty(value = "项目名称")


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectCardVo courseId(String courseId) {
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

    public ProjectCardVo courseCode(String courseCode) {
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

    public ProjectCardVo courseName(String courseName) {
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

    public ProjectCardVo projectSuiteIds(List<String> projectSuiteIds) {
        this.projectSuiteIds = projectSuiteIds;
        return this;
    }

    public ProjectCardVo addProjectSuiteIdsItem(String projectSuiteIdsItem) {
        if (this.projectSuiteIds == null) {
            this.projectSuiteIds = new ArrayList<String>();
        }
        this.projectSuiteIds.add(projectSuiteIdsItem);
        return this;
    }

    /**
     * 項目套件id集合
     *
     * @return projectSuiteIds
     **/
    @ApiModelProperty(value = "項目套件id集合")


    public List<String> getProjectSuiteIds() {
        return projectSuiteIds;
    }

    public void setProjectSuiteIds(List<String> projectSuiteIds) {
        this.projectSuiteIds = projectSuiteIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectCardVo projectCardVo = (ProjectCardVo) o;
        return Objects.equals(this.projectId, projectCardVo.projectId) &&
                Objects.equals(this.projectName, projectCardVo.projectName) &&
                Objects.equals(this.courseId, projectCardVo.courseId) &&
                Objects.equals(this.courseCode, projectCardVo.courseCode) &&
                Objects.equals(this.courseName, projectCardVo.courseName) &&
                Objects.equals(this.projectSuiteIds, projectCardVo.projectSuiteIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, courseId,courseCode, courseName, projectSuiteIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectCardVo {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    projectSuiteIds: ").append(toIndentedString(projectSuiteIds)).append("\n");
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


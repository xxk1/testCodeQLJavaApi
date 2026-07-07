package com.lztech.site.viewmodel.projectsuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectSuiteResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-19T10:55:50.034Z")


public class ProjectSuiteResource {
  @JsonProperty("projectId")
  private String projectId = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("projectSuiteId")
  private String projectSuiteId = null;

  @JsonProperty("projectSuiteNo")
  private String projectSuiteNo = null;

  @JsonProperty("projectSuiteName")
  private String projectSuiteName = null;

  public ProjectSuiteResource projectId(String projectId) {
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

  public ProjectSuiteResource courseId(String courseId) {
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

  public ProjectSuiteResource projectSuiteId(String projectSuiteId) {
    this.projectSuiteId = projectSuiteId;
    return this;
  }

  /**
   * 项目套件id
   *
   * @return projectSuiteId
   **/
  @ApiModelProperty(value = "项目套件id")


  public String getProjectSuiteId() {
    return projectSuiteId;
  }

  public void setProjectSuiteId(String projectSuiteId) {
    this.projectSuiteId = projectSuiteId;
  }

  public ProjectSuiteResource projectSuiteNo(String projectSuiteNo) {
    this.projectSuiteNo = projectSuiteNo;
    return this;
  }

  /**
   * 项目套件编号
   *
   * @return projectSuiteNo
   **/
  @ApiModelProperty(value = "项目套件编号")


  public String getProjectSuiteNo() {
    return projectSuiteNo;
  }

  public void setProjectSuiteNo(String projectSuiteNo) {
    this.projectSuiteNo = projectSuiteNo;
  }

  public ProjectSuiteResource projectSuiteName(String projectSuiteName) {
    this.projectSuiteName = projectSuiteName;
    return this;
  }

  /**
   * 项目套件名称
   *
   * @return projectSuiteName
   **/
  @ApiModelProperty(value = "项目套件名称")


  public String getProjectSuiteName() {
    return projectSuiteName;
  }

  public void setProjectSuiteName(String projectSuiteName) {
    this.projectSuiteName = projectSuiteName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectSuiteResource projectSuiteResource = (ProjectSuiteResource) o;
    return Objects.equals(this.projectId, projectSuiteResource.projectId) &&
            Objects.equals(this.courseId, projectSuiteResource.courseId) &&
            Objects.equals(this.projectSuiteId, projectSuiteResource.projectSuiteId) &&
            Objects.equals(this.projectSuiteNo, projectSuiteResource.projectSuiteNo) &&
            Objects.equals(this.projectSuiteName, projectSuiteResource.projectSuiteName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, courseId, projectSuiteId, projectSuiteNo, projectSuiteName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectSuiteResource {\n");

    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    projectSuiteId: ").append(toIndentedString(projectSuiteId)).append("\n");
    sb.append("    projectSuiteNo: ").append(toIndentedString(projectSuiteNo)).append("\n");
    sb.append("    projectSuiteName: ").append(toIndentedString(projectSuiteName)).append("\n");
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


package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-03T11:42:17.442Z")

public class ProjectVo {
  @JsonProperty("projectId")
  private String projectId = null;

  @JsonProperty("projectCode")
  private String projectCode = null;

  @JsonProperty("projectName")
  private String projectName = null;

  @JsonProperty("projectCourseId")
  private String projectCourseId = null;

  @JsonProperty("projectCourseName")
  private String projectCourseName = null;

  public ProjectVo projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

  /**
   * 项目id
   *
   * @return projectId
   **/
  @ApiModelProperty(value = "项目id")


  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public ProjectVo projectCode(String projectCode) {
    this.projectCode = projectCode;
    return this;
  }

  /**
   * 项目编号
   * @return projectCode
  **/
  @ApiModelProperty(value = "项目编号")


  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }

  public ProjectVo projectName(String projectName) {
    this.projectName = projectName;
    return this;
  }

  /**
   * 项目名称
   * @return projectName
  **/
  @ApiModelProperty(value = "项目名称")


  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public ProjectVo projectCourseId(String projectCourseId) {
    this.projectCourseId = projectCourseId;
    return this;
  }

  /**
   * 预约项目所属课程id
   *
   * @return projectCourseId
   **/
  @ApiModelProperty(value = "预约项目所属课程id")


  public String getProjectCourseId() {
    return projectCourseId;
  }

  public void setProjectCourseId(String projectCourseId) {
    this.projectCourseId = projectCourseId;
  }

  public ProjectVo projectCourseName(String projectCourseName) {
    this.projectCourseName = projectCourseName;
    return this;
  }

  /**
   * 预约项目所属课程名称
   *
   * @return projectCourseName
   **/
  @ApiModelProperty(value = "预约项目所属课程名称")


  public String getProjectCourseName() {
    return projectCourseName;
  }

  public void setProjectCourseName(String projectCourseName) {
    this.projectCourseName = projectCourseName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectVo projectVo = (ProjectVo) o;
    return Objects.equals(this.projectId, projectVo.projectId) &&
            Objects.equals(this.projectCode, projectVo.projectCode) &&
            Objects.equals(this.projectName, projectVo.projectName) &&
            Objects.equals(this.projectCourseId, projectVo.projectCourseId) &&
            Objects.equals(this.projectCourseName, projectVo.projectCourseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, projectCode, projectName, projectCourseId, projectCourseName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectVo {\n");

    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    projectCode: ").append(toIndentedString(projectCode)).append("\n");
    sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
    sb.append("    projectCourseId: ").append(toIndentedString(projectCourseId)).append("\n");
    sb.append("    projectCourseName: ").append(toIndentedString(projectCourseName)).append("\n");
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


package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-19T08:01:54.453Z")




public class ProjectQueryParam   {
  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("projectName")
  private String projectName = null;

  @JsonProperty("projectTypeValue")
  private Integer projectTypeValue = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("projectClassificationId")
  private String projectClassificationId = null;

  public ProjectQueryParam page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * 当前页码
   * @return page
  **/
  @ApiModelProperty(value = "当前页码")


  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public ProjectQueryParam pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 每页记录数
   * @return pageSize
  **/
  @ApiModelProperty(value = "每页记录数")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public ProjectQueryParam projectName(String projectName) {
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

  public ProjectQueryParam projectTypeValue(Integer projectTypeValue) {
    this.projectTypeValue = projectTypeValue;
    return this;
  }

  /**
   * 项目种类值
   * @return projectTypeValue
  **/
  @ApiModelProperty(value = "项目种类值")


  public Integer getProjectTypeValue() {
    return projectTypeValue;
  }

  public void setProjectTypeValue(Integer projectTypeValue) {
    this.projectTypeValue = projectTypeValue;
  }

  public ProjectQueryParam courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 所属课程id
   * @return courseId
  **/
  @ApiModelProperty(value = "所属课程id")


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public ProjectQueryParam projectClassificationId(String projectClassificationId) {
    this.projectClassificationId = projectClassificationId;
    return this;
  }

  /**
   * 项目分类id
   * @return projectClassificationId
  **/
  @ApiModelProperty(value = "项目分类id")


  public String getProjectClassificationId() {
    return projectClassificationId;
  }

  public void setProjectClassificationId(String projectClassificationId) {
    this.projectClassificationId = projectClassificationId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectQueryParam projectQueryParam = (ProjectQueryParam) o;
    return Objects.equals(this.page, projectQueryParam.page) &&
        Objects.equals(this.pageSize, projectQueryParam.pageSize) &&
        Objects.equals(this.projectName, projectQueryParam.projectName) &&
        Objects.equals(this.projectTypeValue, projectQueryParam.projectTypeValue) &&
        Objects.equals(this.courseId, projectQueryParam.courseId) &&
        Objects.equals(this.projectClassificationId, projectQueryParam.projectClassificationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, pageSize, projectName, projectTypeValue, courseId, projectClassificationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectQueryParam {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
    sb.append("    projectTypeValue: ").append(toIndentedString(projectTypeValue)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    projectClassificationId: ").append(toIndentedString(projectClassificationId)).append("\n");
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


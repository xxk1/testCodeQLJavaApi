package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProjectPageResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-19T08:59:39.873Z")




public class ProjectPageResource   {
  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("pageCount")
  private Integer pageCount = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  @JsonProperty("projectList")
  @Valid
  private List<ProjectResource> projectList = null;

  public ProjectPageResource page(Integer page) {
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

  public ProjectPageResource pageSize(Integer pageSize) {
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

  public ProjectPageResource pageCount(Integer pageCount) {
    this.pageCount = pageCount;
    return this;
  }

  /**
   * 总页数
   * @return pageCount
  **/
  @ApiModelProperty(value = "总页数")


  public Integer getPageCount() {
    return pageCount;
  }

  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  public ProjectPageResource totalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * 总条数
   * @return totalCount
  **/
  @ApiModelProperty(value = "总条数")


  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public ProjectPageResource projectList(List<ProjectResource> projectList) {
    this.projectList = projectList;
    return this;
  }

  public ProjectPageResource addProjectListItem(ProjectResource projectListItem) {
    if (this.projectList == null) {
      this.projectList = new ArrayList<ProjectResource>();
    }
    this.projectList.add(projectListItem);
    return this;
  }

  /**
   * 项目信息列表
   * @return projectList
  **/
  @ApiModelProperty(value = "项目信息列表")

  @Valid

  public List<ProjectResource> getProjectList() {
    return projectList;
  }

  public void setProjectList(List<ProjectResource> projectList) {
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
    ProjectPageResource projectPageResource = (ProjectPageResource) o;
    return Objects.equals(this.page, projectPageResource.page) &&
        Objects.equals(this.pageSize, projectPageResource.pageSize) &&
        Objects.equals(this.pageCount, projectPageResource.pageCount) &&
        Objects.equals(this.totalCount, projectPageResource.totalCount) &&
        Objects.equals(this.projectList, projectPageResource.projectList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, pageSize, pageCount, totalCount, projectList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectPageResource {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
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


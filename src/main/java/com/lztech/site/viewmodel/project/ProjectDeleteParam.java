package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProjectDeleteParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-19T08:01:54.453Z")

public class ProjectDeleteParam {

  @JsonProperty("projectIdList")
  private List<String> projectIdList = new ArrayList<>();



  public ProjectDeleteParam projectIdList(List<String> projectIdList) {
    this.projectIdList = projectIdList;
    return this;
  }

  public ProjectDeleteParam addProjectIdListItem(String projectId) {
    if (this.projectIdList == null) {
      this.projectIdList = new ArrayList<>();
    }
    this.projectIdList.add(projectId);
    return this;
  }
  /**
   * 项目id列表
   * @return projectIdList
  **/
  @ApiModelProperty(value = "项目id列表")


  public List<String> getProjectIdList() {
    return projectIdList;
  }

  public void setProjectIdList(List<String> projectIdList) {
    this.projectIdList = projectIdList;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDeleteParam projectQueryParam = (ProjectDeleteParam) o;
    return Objects.equals(this.projectIdList, projectQueryParam.projectIdList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectIdList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDeleteParam {\n");
    sb.append("    projectIdList: ").append(toIndentedString(projectIdList)).append("\n");
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


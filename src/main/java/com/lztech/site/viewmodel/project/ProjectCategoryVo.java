package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectCategoryVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-16T12:04:19.898Z")




public class ProjectCategoryVo   {
  @JsonProperty("value")
  private Integer value = null;

  @JsonProperty("projectCategoryName")
  private String projectCategoryName = null;

  public ProjectCategoryVo value(Integer value) {
    this.value = value;
    return this;
  }

  /**
   * 项目类别值
   * @return value
  **/
  @ApiModelProperty(value = "项目类别值")


  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public ProjectCategoryVo projectCategoryName(String projectCategoryName) {
    this.projectCategoryName = projectCategoryName;
    return this;
  }

  /**
   * 项目类别名称
   * @return projectCategoryName
  **/
  @ApiModelProperty(value = "项目类别名称")


  public String getProjectCategoryName() {
    return projectCategoryName;
  }

  public void setProjectCategoryName(String projectCategoryName) {
    this.projectCategoryName = projectCategoryName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectCategoryVo projectCategoryVo = (ProjectCategoryVo) o;
    return Objects.equals(this.value, projectCategoryVo.value) &&
        Objects.equals(this.projectCategoryName, projectCategoryVo.projectCategoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, projectCategoryName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectCategoryVo {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    projectCategoryName: ").append(toIndentedString(projectCategoryName)).append("\n");
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


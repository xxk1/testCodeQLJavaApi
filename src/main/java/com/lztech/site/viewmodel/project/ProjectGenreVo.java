package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectGenreVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-19T02:01:54.346Z")




public class ProjectGenreVo   {
  @JsonProperty("value")
  private Integer value = null;

  @JsonProperty("projectGenreName")
  private String projectGenreName = null;

  public ProjectGenreVo value(Integer value) {
    this.value = value;
    return this;
  }

  /**
   * 项目类型值
   * @return value
  **/
  @ApiModelProperty(value = "项目类型值")


  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public ProjectGenreVo projectGenreName(String projectGenreName) {
    this.projectGenreName = projectGenreName;
    return this;
  }

  /**
   * 项目类型名称
   * @return projectGenreName
  **/
  @ApiModelProperty(value = "项目类型名称")


  public String getProjectGenreName() {
    return projectGenreName;
  }

  public void setProjectGenreName(String projectGenreName) {
    this.projectGenreName = projectGenreName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectGenreVo projectGenreVo = (ProjectGenreVo) o;
    return Objects.equals(this.value, projectGenreVo.value) &&
        Objects.equals(this.projectGenreName, projectGenreVo.projectGenreName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, projectGenreName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectGenreVo {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    projectGenreName: ").append(toIndentedString(projectGenreName)).append("\n");
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


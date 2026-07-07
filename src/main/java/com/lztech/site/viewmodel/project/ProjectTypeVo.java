package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectTypeVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-16T11:56:13.420Z")




public class ProjectTypeVo   {
  @JsonProperty("value")
  private Integer value = null;

  @JsonProperty("projectTypeName")
  private String projectTypeName = null;

  public ProjectTypeVo value(Integer value) {
    this.value = value;
    return this;
  }

  /**
   * 项目种类值
   * @return value
  **/
  @ApiModelProperty(value = "项目种类值")


  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public ProjectTypeVo projectTypeName(String projectTypeName) {
    this.projectTypeName = projectTypeName;
    return this;
  }

  /**
   * 项目种类名称
   * @return projectTypeName
  **/
  @ApiModelProperty(value = "项目种类名称")


  public String getProjectTypeName() {
    return projectTypeName;
  }

  public void setProjectTypeName(String projectTypeName) {
    this.projectTypeName = projectTypeName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectTypeVo projectTypeVo = (ProjectTypeVo) o;
    return Objects.equals(this.value, projectTypeVo.value) &&
        Objects.equals(this.projectTypeName, projectTypeVo.projectTypeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, projectTypeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectTypeVo {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    projectTypeName: ").append(toIndentedString(projectTypeName)).append("\n");
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


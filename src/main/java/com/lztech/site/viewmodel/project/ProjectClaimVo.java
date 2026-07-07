package com.lztech.site.viewmodel.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectClaimVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-19T01:29:54.918Z")




public class ProjectClaimVo   {
  @JsonProperty("value")
  private Integer value = null;

  @JsonProperty("projectClaimName")
  private String projectClaimName = null;

  public ProjectClaimVo value(Integer value) {
    this.value = value;
    return this;
  }

  /**
   * 项目要求值
   * @return value
  **/
  @ApiModelProperty(value = "项目要求值")


  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public ProjectClaimVo projectClaimName(String projectClaimName) {
    this.projectClaimName = projectClaimName;
    return this;
  }

  /**
   * 项目要求名称
   * @return projectClaimName
  **/
  @ApiModelProperty(value = "项目要求名称")


  public String getProjectClaimName() {
    return projectClaimName;
  }

  public void setProjectClaimName(String projectClaimName) {
    this.projectClaimName = projectClaimName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectClaimVo projectClaimVo = (ProjectClaimVo) o;
    return Objects.equals(this.value, projectClaimVo.value) &&
        Objects.equals(this.projectClaimName, projectClaimVo.projectClaimName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, projectClaimName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectClaimVo {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    projectClaimName: ").append(toIndentedString(projectClaimName)).append("\n");
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


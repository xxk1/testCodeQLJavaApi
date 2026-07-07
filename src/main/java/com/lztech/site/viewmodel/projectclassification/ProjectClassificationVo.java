package com.lztech.site.viewmodel.projectclassification;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ProjectClassificationVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-15T09:02:09.480Z")




public class ProjectClassificationVo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("classificationName")
  private String classificationName = null;

  public ProjectClassificationVo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 项目分类id
   * @return id
  **/
  @ApiModelProperty(value = "项目分类id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProjectClassificationVo classificationName(String classificationName) {
    this.classificationName = classificationName;
    return this;
  }

  /**
   * 项目分类名称
   * @return classificationName
  **/
  @ApiModelProperty(value = "项目分类名称")


  public String getClassificationName() {
    return classificationName;
  }

  public void setClassificationName(String classificationName) {
    this.classificationName = classificationName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectClassificationVo projectClassificationVo = (ProjectClassificationVo) o;
    return Objects.equals(this.id, projectClassificationVo.id) &&
        Objects.equals(this.classificationName, projectClassificationVo.classificationName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, classificationName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectClassificationVo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    classificationName: ").append(toIndentedString(classificationName)).append("\n");
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


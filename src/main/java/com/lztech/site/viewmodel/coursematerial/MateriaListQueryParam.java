package com.lztech.site.viewmodel.coursematerial;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * MateriaListQueryParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-17T02:28:29.024Z")


public class MateriaListQueryParam   {
  @JsonProperty("pageNum")
  private Integer pageNum = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("resourceType")
  private Integer resourceType = null;

  @JsonProperty("resourceName")
  private String resourceName = null;

  @JsonProperty("modifierName")
  private String modifierName = null;

  public MateriaListQueryParam pageNum(Integer pageNum) {
    this.pageNum = pageNum;
    return this;
  }

  /**
   * 当前页码
   * @return pageNum
  **/
  @ApiModelProperty(value = "当前页码")


  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public MateriaListQueryParam pageSize(Integer pageSize) {
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

  public MateriaListQueryParam resourceType(Integer resourceType) {
    this.resourceType = resourceType;
    return this;
  }

  /**
   * 资源类型(0-课件 1-视频 2-测验 3-图片 4-主题)
   * @return resourceType
  **/
  @ApiModelProperty(value = "资源类型(0-课件 1-视频 2-测验 3-图片 4-主题)")


  public Integer getResourceType() {
    return resourceType;
  }

  public void setResourceType(Integer resourceType) {
    this.resourceType = resourceType;
  }

  public MateriaListQueryParam resourceName(String resourceName) {
    this.resourceName = resourceName;
    return this;
  }

  /**
   * 素材名称
   * @return resourceName
  **/
  @ApiModelProperty(value = "素材名称")


  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public MateriaListQueryParam modifierName(String modifierName) {
    this.modifierName = modifierName;
    return this;
  }

  /**
   * 教师名称
   * @return modifierName
  **/
  @ApiModelProperty(value = "教师名称")


  public String getModifierName() {
    return modifierName;
  }

  public void setModifierName(String modifierName) {
    this.modifierName = modifierName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MateriaListQueryParam materiaListQueryParam = (MateriaListQueryParam) o;
    return Objects.equals(this.pageNum, materiaListQueryParam.pageNum) &&
        Objects.equals(this.pageSize, materiaListQueryParam.pageSize) &&
        Objects.equals(this.resourceType, materiaListQueryParam.resourceType) &&
        Objects.equals(this.resourceName, materiaListQueryParam.resourceName) &&
        Objects.equals(this.modifierName, materiaListQueryParam.modifierName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageNum, pageSize, resourceType, resourceName, modifierName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MateriaListQueryParam {\n");
    
    sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
    sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
    sb.append("    modifierName: ").append(toIndentedString(modifierName)).append("\n");
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


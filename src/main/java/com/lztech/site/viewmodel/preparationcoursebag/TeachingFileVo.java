package com.lztech.site.viewmodel.preparationcoursebag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * TeachingFileVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-22T06:07:52.907Z")




public class TeachingFileVo   {
  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("resourceName")
  private String resourceName = null;

  @JsonProperty("innerResourceUrl")
  private String innerResourceUrl = null;

  @JsonProperty("outerResourceUrl")
  private String outerResourceUrl = null;

  @JsonProperty("resourceType")
  private String resourceType = null;

  public TeachingFileVo resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * 资源文件Id
   * @return resourceId
  **/
  @ApiModelProperty(value = "资源文件Id")


  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public TeachingFileVo resourceName(String resourceName) {
    this.resourceName = resourceName;
    return this;
  }

  /**
   * 资源文件名称
   * @return resourceName
  **/
  @ApiModelProperty(value = "资源文件名称")


  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public TeachingFileVo innerResourceUrl(String innerResourceUrl) {
    this.innerResourceUrl = innerResourceUrl;
    return this;
  }

  /**
   * 内网资源文件路径(互动课堂：内网Ip+路径)
   * @return innerResourceUrl
  **/
  @ApiModelProperty(value = "内网资源文件路径(互动课堂：内网Ip+路径)")


  public String getInnerResourceUrl() {
    return innerResourceUrl;
  }

  public void setInnerResourceUrl(String innerResourceUrl) {
    this.innerResourceUrl = innerResourceUrl;
  }

  public TeachingFileVo outerResourceUrl(String outerResourceUrl) {
    this.outerResourceUrl = outerResourceUrl;
    return this;
  }

  /**
   * 外网资源文件路径(互动课堂：外网Ip+路径)
   * @return outerResourceUrl
  **/
  @ApiModelProperty(value = "外网资源文件路径(互动课堂：外网Ip+路径)")


  public String getOuterResourceUrl() {
    return outerResourceUrl;
  }

  public void setOuterResourceUrl(String outerResourceUrl) {
    this.outerResourceUrl = outerResourceUrl;
  }

  public TeachingFileVo resourceType(String resourceType) {
    this.resourceType = resourceType;
    return this;
  }

  /**
   * 资源文件后缀名
   * @return resourceType
  **/
  @ApiModelProperty(value = "资源文件后缀名")


  public String getResourceType() {
    return resourceType;
  }

  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeachingFileVo teachingFileVo = (TeachingFileVo) o;
    return Objects.equals(this.resourceId, teachingFileVo.resourceId) &&
        Objects.equals(this.resourceName, teachingFileVo.resourceName) &&
        Objects.equals(this.innerResourceUrl, teachingFileVo.innerResourceUrl) &&
        Objects.equals(this.outerResourceUrl, teachingFileVo.outerResourceUrl) &&
        Objects.equals(this.resourceType, teachingFileVo.resourceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceId, resourceName, innerResourceUrl, outerResourceUrl, resourceType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeachingFileVo {\n");
    
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
    sb.append("    innerResourceUrl: ").append(toIndentedString(innerResourceUrl)).append("\n");
    sb.append("    outerResourceUrl: ").append(toIndentedString(outerResourceUrl)).append("\n");
    sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
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


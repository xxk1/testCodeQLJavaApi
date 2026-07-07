package com.lztech.site.viewmodel.preparationcoursebag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassTestPaperVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-25T06:29:06.397Z")




public class ClassTestPaperVo   {
  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resourceName")
  private String resourceName = null;

  @JsonProperty("subjectNum")
  private Integer subjectNum = null;

  @JsonProperty("updateTime")
  private String updateTime = null;

  public ClassTestPaperVo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * id
   * @return id
   **/
  @ApiModelProperty(value = "资源id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ClassTestPaperVo resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * 资源id
   * @return resourceId
  **/
  @ApiModelProperty(value = "资源id")

  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public ClassTestPaperVo resourceName(String resourceName) {
    this.resourceName = resourceName;
    return this;
  }

  /**
   * 资源名称
   * @return resourceName
  **/
  @ApiModelProperty(value = "资源名称")


  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public ClassTestPaperVo subjectNum(Integer subjectNum) {
    this.subjectNum = subjectNum;
    return this;
  }

  /**
   * 题目数量
   * @return subjectNum
  **/
  @ApiModelProperty(value = "题目数量")


  public Integer getSubjectNum() {
    return subjectNum;
  }

  public void setSubjectNum(Integer subjectNum) {
    this.subjectNum = subjectNum;
  }

  public ClassTestPaperVo updateTime(String updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  /**
   * 更新时间
   * @return updateTime
  **/
  @ApiModelProperty(value = "更新时间")


  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassTestPaperVo classTestPaperVo = (ClassTestPaperVo) o;
    return Objects.equals(this.resourceId, classTestPaperVo.resourceId) &&
            Objects.equals(this.id, classTestPaperVo.id) &&
        Objects.equals(this.resourceName, classTestPaperVo.resourceName) &&
        Objects.equals(this.subjectNum, classTestPaperVo.subjectNum) &&
        Objects.equals(this.updateTime, classTestPaperVo.updateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceId,id,resourceName, subjectNum, updateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClassTestPaperVo {\n");
    
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
    sb.append("    subjectNum: ").append(toIndentedString(subjectNum)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
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


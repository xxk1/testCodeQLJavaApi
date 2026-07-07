package com.lztech.site.viewmodel.coursestatistics;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CourseStatisticsResourceTopVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-18T11:04:12.465Z")


public class CourseStatisticsResourceTopVo   {
  @JsonProperty("resourceName")
  private String resourceName = null;

  @JsonProperty("updateTime")
  private String updateTime = null;

  @JsonProperty("updateName")
  private String updateName = null;

  @JsonProperty("updateId")
  private String updateId = null;

  public CourseStatisticsResourceTopVo resourceName(String resourceName) {
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

  public CourseStatisticsResourceTopVo updateTime(String updateTime) {
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

  public CourseStatisticsResourceTopVo updateName(String updateName) {
    this.updateName = updateName;
    return this;
  }

  /**
   * 更新人名称
   * @return updateName
  **/
  @ApiModelProperty(value = "更新人名称")


  public String getUpdateName() {
    return updateName;
  }

  public void setUpdateName(String updateName) {
    this.updateName = updateName;
  }

  public CourseStatisticsResourceTopVo updateId(String updateId) {
    this.updateId = updateId;
    return this;
  }

  /**
   * 更新人ID
   * @return updateId
  **/
  @ApiModelProperty(value = "更新人ID")


  public String getUpdateId() {
    return updateId;
  }

  public void setUpdateId(String updateId) {
    this.updateId = updateId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseStatisticsResourceTopVo courseStatisticsResourceTopVo = (CourseStatisticsResourceTopVo) o;
    return Objects.equals(this.resourceName, courseStatisticsResourceTopVo.resourceName) &&
        Objects.equals(this.updateTime, courseStatisticsResourceTopVo.updateTime) &&
        Objects.equals(this.updateName, courseStatisticsResourceTopVo.updateName) &&
        Objects.equals(this.updateId, courseStatisticsResourceTopVo.updateId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceName, updateTime, updateName, updateId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseStatisticsResourceTopVo {\n");
    
    sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
    sb.append("    updateName: ").append(toIndentedString(updateName)).append("\n");
    sb.append("    updateId: ").append(toIndentedString(updateId)).append("\n");
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


package com.lztech.site.viewmodel.coursestatistics;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-18T11:04:12.465Z")


public class CourseStatisticsResource   {
  @JsonProperty("totalNum")
  private Integer totalNum = null;

  @JsonProperty("resourceList")
  @Valid
  private List<CourseStatisticsDetailResource> resourceList = null;

  public CourseStatisticsResource totalNum(Integer totalNum) {
    this.totalNum = totalNum;
    return this;
  }

  /**
   * 总数
   * @return totalNum
  **/
  @ApiModelProperty(value = "总数")


  public Integer getTotalNum() {
    return totalNum;
  }

  public void setTotalNum(Integer totalNum) {
    this.totalNum = totalNum;
  }

  public CourseStatisticsResource resourceList(List<CourseStatisticsDetailResource> resourceList) {
    this.resourceList = resourceList;
    return this;
  }

  public CourseStatisticsResource addResourceListItem(CourseStatisticsDetailResource resourceListItem) {
    if (this.resourceList == null) {
      this.resourceList = new ArrayList<CourseStatisticsDetailResource>();
    }
    this.resourceList.add(resourceListItem);
    return this;
  }

  /**
   * Get resourceList
   * @return resourceList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CourseStatisticsDetailResource> getResourceList() {
    return resourceList;
  }

  public void setResourceList(List<CourseStatisticsDetailResource> resourceList) {
    this.resourceList = resourceList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseStatisticsResource courseStatisticsResource = (CourseStatisticsResource) o;
    return Objects.equals(this.totalNum, courseStatisticsResource.totalNum) &&
        Objects.equals(this.resourceList, courseStatisticsResource.resourceList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalNum, resourceList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseStatisticsResource {\n");
    
    sb.append("    totalNum: ").append(toIndentedString(totalNum)).append("\n");
    sb.append("    resourceList: ").append(toIndentedString(resourceList)).append("\n");
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


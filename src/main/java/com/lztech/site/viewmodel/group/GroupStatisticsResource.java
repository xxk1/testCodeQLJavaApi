package com.lztech.site.viewmodel.group;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * GroupStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-11-26T06:41:56.762Z")


public class GroupStatisticsResource   {
  @JsonProperty("groupCount")
  private Integer groupCount = null;

  public GroupStatisticsResource groupCount(Integer groupCount) {
    this.groupCount = groupCount;
    return this;
  }

  /**
   * 班级总数
   * @return groupCount
  **/
  @ApiModelProperty(value = "班级总数")


  public Integer getGroupCount() {
    return groupCount;
  }

  public void setGroupCount(Integer groupCount) {
    this.groupCount = groupCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupStatisticsResource groupStatisticsResource = (GroupStatisticsResource) o;
    return Objects.equals(this.groupCount, groupStatisticsResource.groupCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupStatisticsResource {\n");
    
    sb.append("    groupCount: ").append(toIndentedString(groupCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


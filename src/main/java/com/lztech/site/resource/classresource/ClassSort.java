package com.lztech.site.resource.classresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassSort
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-10-15T06:59:40.717Z")


public class ClassSort   {
  @JsonProperty("teachingClassId")
  private String teachingClassId = null;

  @JsonProperty("sort")
  private Integer sort = null;

  public ClassSort teachingClassId(String teachingClassId) {
    this.teachingClassId = teachingClassId;
    return this;
  }

  /**
   * 授课班级ID
   * @return teachingClassId
  **/
  @ApiModelProperty(value = "授课班级ID")


  public String getTeachingClassId() {
    return teachingClassId;
  }

  public void setTeachingClassId(String teachingClassId) {
    this.teachingClassId = teachingClassId;
  }

  public ClassSort sort(Integer sort) {
    this.sort = sort;
    return this;
  }

  /**
   * 班级排序
   * @return sort
  **/
  @ApiModelProperty(value = "班级排序")


  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassSort classSort = (ClassSort) o;
    return Objects.equals(this.teachingClassId, classSort.teachingClassId) &&
        Objects.equals(this.sort, classSort.sort);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teachingClassId, sort);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClassSort {\n");
    
    sb.append("    teachingClassId: ").append(toIndentedString(teachingClassId)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
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


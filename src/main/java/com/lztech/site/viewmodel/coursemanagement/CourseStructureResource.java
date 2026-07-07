package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseStructureResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-20T02:48:18.930Z")




public class CourseStructureResource   {
  @JsonProperty("structureId")
  private String structureId = null;

  @JsonProperty("parentId")
  private String parentId = null;

  @JsonProperty("structureName")
  private String structureName = null;

  @JsonProperty("showOrder")
  private Integer showOrder = null;

  @JsonProperty("childStructureList")
  @Valid
  private List<CourseStructureResource> childStructureList = null;

  @JsonProperty("courseResourceList")
  @Valid
  private List<CourseResourceInfo> courseResourceList = null;

  public CourseStructureResource structureId(String structureId) {
    this.structureId = structureId;
    return this;
  }

  /**
   * 课程结构id
   * @return structureId
  **/
  @ApiModelProperty(value = "课程结构id")


  public String getStructureId() {
    return structureId;
  }

  public void setStructureId(String structureId) {
    this.structureId = structureId;
  }

  public CourseStructureResource parentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  /**
   * 父id
   * @return parentId
  **/
  @ApiModelProperty(value = "父id")


  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public CourseStructureResource structureName(String structureName) {
    this.structureName = structureName;
    return this;
  }

  /**
   * 课程结构名称
   * @return structureName
  **/
  @ApiModelProperty(value = "课程结构名称")


  public String getStructureName() {
    return structureName;
  }

  public void setStructureName(String structureName) {
    this.structureName = structureName;
  }

  public CourseStructureResource showOrder(Integer showOrder) {
    this.showOrder = showOrder;
    return this;
  }

  /**
   * 排序字段
   * @return showOrder
  **/
  @ApiModelProperty(value = "排序字段")


  public Integer getShowOrder() {
    return showOrder;
  }

  public void setShowOrder(Integer showOrder) {
    this.showOrder = showOrder;
  }

  public CourseStructureResource childStructureList(List<CourseStructureResource> childStructureList) {
    this.childStructureList = childStructureList;
    return this;
  }

  public CourseStructureResource addChildStructureListItem(CourseStructureResource childStructureListItem) {
    if (this.childStructureList == null) {
      this.childStructureList = new ArrayList<CourseStructureResource>();
    }
    this.childStructureList.add(childStructureListItem);
    return this;
  }

  /**
   * 子课程结构
   * @return childStructureList
  **/
  @ApiModelProperty(value = "子课程结构")

  @Valid

  public List<CourseStructureResource> getChildStructureList() {
    return childStructureList;
  }

  public void setChildStructureList(List<CourseStructureResource> childStructureList) {
    this.childStructureList = childStructureList;
  }

  public CourseStructureResource courseResourceList(List<CourseResourceInfo> courseResourceList) {
    this.courseResourceList = courseResourceList;
    return this;
  }

  public CourseStructureResource addCourseResourceListItem(CourseResourceInfo courseResourceListItem) {
    if (this.courseResourceList == null) {
      this.courseResourceList = new ArrayList<CourseResourceInfo>();
    }
    this.courseResourceList.add(courseResourceListItem);
    return this;
  }

  /**
   * 资源集合
   * @return courseResourceList
  **/
  @ApiModelProperty(value = "资源集合")

  @Valid

  public List<CourseResourceInfo> getCourseResourceList() {
    return courseResourceList;
  }

  public void setCourseResourceList(List<CourseResourceInfo> courseResourceList) {
    this.courseResourceList = courseResourceList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseStructureResource courseStructureResource = (CourseStructureResource) o;
    return Objects.equals(this.structureId, courseStructureResource.structureId) &&
        Objects.equals(this.parentId, courseStructureResource.parentId) &&
        Objects.equals(this.structureName, courseStructureResource.structureName) &&
        Objects.equals(this.showOrder, courseStructureResource.showOrder) &&
        Objects.equals(this.childStructureList, courseStructureResource.childStructureList) &&
        Objects.equals(this.courseResourceList, courseStructureResource.courseResourceList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(structureId, parentId, structureName, showOrder, childStructureList, courseResourceList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseStructureResource {\n");
    
    sb.append("    structureId: ").append(toIndentedString(structureId)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    structureName: ").append(toIndentedString(structureName)).append("\n");
    sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
    sb.append("    childStructureList: ").append(toIndentedString(childStructureList)).append("\n");
    sb.append("    courseResourceList: ").append(toIndentedString(courseResourceList)).append("\n");
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


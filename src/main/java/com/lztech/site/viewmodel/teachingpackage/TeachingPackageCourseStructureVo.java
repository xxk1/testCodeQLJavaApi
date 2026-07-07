package com.lztech.site.viewmodel.teachingpackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TeachingPackageCourseStructureVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-28T02:32:37.171Z")


public class TeachingPackageCourseStructureVo   {
  @JsonProperty("structureId")
  private String structureId = null;

  @JsonProperty("parentId")
  private String parentId = null;

  @JsonProperty("parentName")
  private String parentName = null;

  @JsonProperty("structureName")
  private String structureName = null;

  @JsonProperty("showOrder")
  private Integer showOrder = null;

  @JsonProperty("childStructureList")
  @Valid
  private List<TeachingPackageCourseStructureVo> childStructureList = null;

  @JsonProperty("courseResourceList")
  @Valid
  private List<TeachingPackageCourseResourceVo> courseResourceList = null;

  public TeachingPackageCourseStructureVo structureId(String structureId) {
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

  public TeachingPackageCourseStructureVo parentId(String parentId) {
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

  public TeachingPackageCourseStructureVo parentName(String parentName) {
    this.parentName = parentName;
    return this;
  }

  /**
   * 父课程结构名称
   * @return parentName
  **/
  @ApiModelProperty(value = "父课程结构名称")


  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public TeachingPackageCourseStructureVo structureName(String structureName) {
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

  public TeachingPackageCourseStructureVo showOrder(Integer showOrder) {
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

  public TeachingPackageCourseStructureVo childStructureList(List<TeachingPackageCourseStructureVo> childStructureList) {
    this.childStructureList = childStructureList;
    return this;
  }

  public TeachingPackageCourseStructureVo addChildStructureListItem(TeachingPackageCourseStructureVo childStructureListItem) {
    if (this.childStructureList == null) {
      this.childStructureList = new ArrayList<TeachingPackageCourseStructureVo>();
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

  public List<TeachingPackageCourseStructureVo> getChildStructureList() {
    return childStructureList;
  }

  public void setChildStructureList(List<TeachingPackageCourseStructureVo> childStructureList) {
    this.childStructureList = childStructureList;
  }

  public TeachingPackageCourseStructureVo courseResourceList(List<TeachingPackageCourseResourceVo> courseResourceList) {
    this.courseResourceList = courseResourceList;
    return this;
  }

  public TeachingPackageCourseStructureVo addCourseResourceListItem(TeachingPackageCourseResourceVo courseResourceListItem) {
    if (this.courseResourceList == null) {
      this.courseResourceList = new ArrayList<TeachingPackageCourseResourceVo>();
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

  public List<TeachingPackageCourseResourceVo> getCourseResourceList() {
    return courseResourceList;
  }

  public void setCourseResourceList(List<TeachingPackageCourseResourceVo> courseResourceList) {
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
    TeachingPackageCourseStructureVo teachingPackageCourseStructureVo = (TeachingPackageCourseStructureVo) o;
    return Objects.equals(this.structureId, teachingPackageCourseStructureVo.structureId) &&
        Objects.equals(this.parentId, teachingPackageCourseStructureVo.parentId) &&
        Objects.equals(this.parentName, teachingPackageCourseStructureVo.parentName) &&
        Objects.equals(this.structureName, teachingPackageCourseStructureVo.structureName) &&
        Objects.equals(this.showOrder, teachingPackageCourseStructureVo.showOrder) &&
        Objects.equals(this.childStructureList, teachingPackageCourseStructureVo.childStructureList) &&
        Objects.equals(this.courseResourceList, teachingPackageCourseStructureVo.courseResourceList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(structureId, parentId, parentName, structureName, showOrder, childStructureList, courseResourceList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeachingPackageCourseStructureVo {\n");
    
    sb.append("    structureId: ").append(toIndentedString(structureId)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    parentName: ").append(toIndentedString(parentName)).append("\n");
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


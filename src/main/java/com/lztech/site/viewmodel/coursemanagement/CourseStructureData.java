package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseStructureData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-13T01:58:01.169Z")


public class CourseStructureData {
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
    private List<CourseStructureData> childStructureList = null;

    public CourseStructureData structureId(String structureId) {
        this.structureId = structureId;
        return this;
    }

    /**
     * 课程结构id
     *
     * @return structureId
     **/
    @ApiModelProperty(value = "课程结构id")


    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public CourseStructureData parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * 父id
     *
     * @return parentId
     **/
    @ApiModelProperty(value = "父id")


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public CourseStructureData structureName(String structureName) {
        this.structureName = structureName;
        return this;
    }

    /**
     * 课程结构名称
     *
     * @return structureName
     **/
    @ApiModelProperty(value = "课程结构名称")


    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public CourseStructureData showOrder(Integer showOrder) {
        this.showOrder = showOrder;
        return this;
    }

    /**
     * 排序字段
     *
     * @return showOrder
     **/
    @ApiModelProperty(value = "排序字段")


    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public CourseStructureData childStructureList(List<CourseStructureData> childStructureList) {
        this.childStructureList = childStructureList;
        return this;
    }

    public CourseStructureData addChildStructureListItem(CourseStructureData childStructureListItem) {
        if (this.childStructureList == null) {
            this.childStructureList = new ArrayList<CourseStructureData>();
        }
        this.childStructureList.add(childStructureListItem);
        return this;
    }

    /**
     * 子课程结构
     *
     * @return childStructureList
     **/
    @ApiModelProperty(value = "子课程结构")

    @Valid

    public List<CourseStructureData> getChildStructureList() {
        return childStructureList;
    }

    public void setChildStructureList(List<CourseStructureData> childStructureList) {
        this.childStructureList = childStructureList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseStructureData courseStructureData = (CourseStructureData) o;
        return Objects.equals(this.structureId, courseStructureData.structureId) &&
                Objects.equals(this.parentId, courseStructureData.parentId) &&
                Objects.equals(this.structureName, courseStructureData.structureName) &&
                Objects.equals(this.showOrder, courseStructureData.showOrder) &&
                Objects.equals(this.childStructureList, courseStructureData.childStructureList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId, parentId, structureName, showOrder, childStructureList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseStructureData {\n");

        sb.append("    structureId: ").append(toIndentedString(structureId)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
        sb.append("    structureName: ").append(toIndentedString(structureName)).append("\n");
        sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
        sb.append("    childStructureList: ").append(toIndentedString(childStructureList)).append("\n");
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


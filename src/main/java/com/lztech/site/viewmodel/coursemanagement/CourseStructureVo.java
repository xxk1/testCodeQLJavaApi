package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseStructureVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-12T02:38:43.244Z")


public class CourseStructureVo {
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
    private List<CourseStructureVo> childStructureList = null;

    @JsonProperty("courseResourceList")
    @Valid
    private List<CourseResourceVo> courseResourceList = null;

    public CourseStructureVo structureId(String structureId) {
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

    public CourseStructureVo parentId(String parentId) {
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

    public CourseStructureVo structureName(String structureName) {
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

    public CourseStructureVo showOrder(Integer showOrder) {
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

    public CourseStructureVo childStructureList(List<CourseStructureVo> childStructureList) {
        this.childStructureList = childStructureList;
        return this;
    }

    public CourseStructureVo addChildStructureListItem(CourseStructureVo childStructureListItem) {
        if (this.childStructureList == null) {
            this.childStructureList = new ArrayList<CourseStructureVo>();
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

    public List<CourseStructureVo> getChildStructureList() {
        return childStructureList;
    }

    public void setChildStructureList(List<CourseStructureVo> childStructureList) {
        this.childStructureList = childStructureList;
    }

    public CourseStructureVo courseResourceList(List<CourseResourceVo> courseResourceList) {
        this.courseResourceList = courseResourceList;
        return this;
    }

    public CourseStructureVo addCourseResourceListItem(CourseResourceVo courseResourceListItem) {
        if (this.courseResourceList == null) {
            this.courseResourceList = new ArrayList<CourseResourceVo>();
        }
        this.courseResourceList.add(courseResourceListItem);
        return this;
    }

    /**
     * 资源集合
     *
     * @return courseResourceList
     **/
    @ApiModelProperty(value = "资源集合")

    @Valid

    public List<CourseResourceVo> getCourseResourceList() {
        return courseResourceList;
    }

    public void setCourseResourceList(List<CourseResourceVo> courseResourceList) {
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
        CourseStructureVo courseStructureVo = (CourseStructureVo) o;
        return Objects.equals(this.structureId, courseStructureVo.structureId) &&
                Objects.equals(this.parentId, courseStructureVo.parentId) &&
                Objects.equals(this.structureName, courseStructureVo.structureName) &&
                Objects.equals(this.showOrder, courseStructureVo.showOrder) &&
                Objects.equals(this.childStructureList, courseStructureVo.childStructureList) &&
                Objects.equals(this.courseResourceList, courseStructureVo.courseResourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId, parentId, structureName, showOrder, childStructureList, courseResourceList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseStructureVo {\n");

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


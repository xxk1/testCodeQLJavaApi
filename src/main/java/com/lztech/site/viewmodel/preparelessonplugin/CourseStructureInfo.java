package com.lztech.site.viewmodel.preparelessonplugin;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseStructureInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-12T11:52:44.035Z")

public class CourseStructureInfo   {
    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    @JsonProperty("parentId")
    private String parentId = null;

    @JsonProperty("courseStructureName")
    private String courseStructureName = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    @JsonProperty("childStructureList")
    @Valid
    private List<CourseStructureInfo> childStructureList = null;


    public CourseStructureInfo courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 课程结构id
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "课程结构id")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }

    public CourseStructureInfo parentId(String parentId) {
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

    public CourseStructureInfo courseStructureName(String courseStructureName) {
        this.courseStructureName = courseStructureName;
        return this;
    }

    /**
     * 课程结构名称
     * @return courseStructureName
     **/
    @ApiModelProperty(value = "课程结构名称")


    public String getCourseStructureName() {
        return courseStructureName;
    }

    public void setCourseStructureName(String courseStructureName) {
        this.courseStructureName = courseStructureName;
    }

    public CourseStructureInfo showOrder(Integer showOrder) {
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

    public CourseStructureInfo childStructureList(List<CourseStructureInfo> childStructureList) {
        this.childStructureList = childStructureList;
        return this;
    }

    public CourseStructureInfo addChildStructureListItem(CourseStructureInfo childStructureListItem) {
        if (this.childStructureList == null) {
            this.childStructureList = new ArrayList<CourseStructureInfo>();
        }
        this.childStructureList.add(childStructureListItem);
        return this;
    }

    /**
     * Get childStructureList
     * @return childStructureList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseStructureInfo> getChildStructureList() {
        return childStructureList;
    }

    public void setChildStructureList(List<CourseStructureInfo> childStructureList) {
        this.childStructureList = childStructureList;
    }



    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseStructureInfo courseStructureInfo = (CourseStructureInfo) o;
        return Objects.equals(this.courseStructureId, courseStructureInfo.courseStructureId) &&
                Objects.equals(this.parentId, courseStructureInfo.parentId) &&
                Objects.equals(this.courseStructureName, courseStructureInfo.courseStructureName) &&
                Objects.equals(this.showOrder, courseStructureInfo.showOrder) &&
                Objects.equals(this.childStructureList, courseStructureInfo.childStructureList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseStructureId, parentId, courseStructureName, showOrder, childStructureList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseStructureInfo {\n");

        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
        sb.append("    courseStructureName: ").append(toIndentedString(courseStructureName)).append("\n");
        sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
        sb.append("    childStructureList: ").append(toIndentedString(childStructureList)).append("\n");
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


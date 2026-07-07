package com.lztech.site.viewmodel.courseconstruction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeStructureResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-22T09:07:03.474Z")


public class CourseKnowledgeStructureResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("structureType")
    private Integer structureType = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    @JsonProperty("childList")
    @Valid
    private List<CourseKnowledgeStructureResource> childList = null;

    public CourseKnowledgeStructureResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 知识结构id
     *
     * @return id
     **/
    @ApiModelProperty(value = "知识结构id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseKnowledgeStructureResource content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 知识结构内容
     *
     * @return content
     **/
    @ApiModelProperty(value = "知识结构内容")


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CourseKnowledgeStructureResource structureType(Integer structureType) {
        this.structureType = structureType;
        return this;
    }

    /**
     * 知识结构类型
     *
     * @return structureType
     **/
    @ApiModelProperty(value = "知识结构类型")


    public Integer getStructureType() {
        return structureType;
    }

    public void setStructureType(Integer structureType) {
        this.structureType = structureType;
    }

    public CourseKnowledgeStructureResource showOrder(Integer showOrder) {
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

    public CourseKnowledgeStructureResource childList(List<CourseKnowledgeStructureResource> childList) {
        this.childList = childList;
        return this;
    }

    public CourseKnowledgeStructureResource addChildListItem(CourseKnowledgeStructureResource childListItem) {
        if (this.childList == null) {
            this.childList = new ArrayList<CourseKnowledgeStructureResource>();
        }
        this.childList.add(childListItem);
        return this;
    }

    /**
     * Get childList
     *
     * @return childList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseKnowledgeStructureResource> getChildList() {
        return childList;
    }

    public void setChildList(List<CourseKnowledgeStructureResource> childList) {
        this.childList = childList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeStructureResource courseKnowledgeStructureResource = (CourseKnowledgeStructureResource) o;
        return Objects.equals(this.id, courseKnowledgeStructureResource.id) &&
                Objects.equals(this.content, courseKnowledgeStructureResource.content) &&
                Objects.equals(this.structureType, courseKnowledgeStructureResource.structureType) &&
                Objects.equals(this.showOrder, courseKnowledgeStructureResource.showOrder) &&
                Objects.equals(this.childList, courseKnowledgeStructureResource.childList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, structureType, showOrder, childList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeStructureResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    structureType: ").append(toIndentedString(structureType)).append("\n");
        sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
        sb.append("    childList: ").append(toIndentedString(childList)).append("\n");
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


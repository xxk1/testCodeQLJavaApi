package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphCorrelationNodeInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-11-25T07:23:54.980Z")

public class CourseKnowledgeGraphCorrelationNodeInfo {

    @JsonProperty("greatGrandParentKnowledgeNodeId")
    private String greatGrandParentKnowledgeNodeId = null;

    @JsonProperty("greatGrandParentKnowledgeNodeName")
    private String greatGrandParentKnowledgeNodeName = null;

    @JsonProperty("grandParentKnowledgeNodeId")
    private String grandParentKnowledgeNodeId = null;

    @JsonProperty("grandParentKnowledgeNodeName")
    private String grandParentKnowledgeNodeName = null;

    @JsonProperty("parentKnowledgeNodeId")
    private String parentKnowledgeNodeId = null;

    @JsonProperty("parentKnowledgeNodeName")
    private String parentKnowledgeNodeName = null;

    @JsonProperty("childKnowledgeNodeId")
    private String childKnowledgeNodeId = null;

    @JsonProperty("childKnowledgeNodeName")
    private String childKnowledgeNodeName = null;

    @JsonProperty("childKnowledgeNodeLevel")
    private Integer childKnowledgeNodeLevel = null;

    @JsonProperty("childKnowledgeNodeSort")
    private Integer childKnowledgeNodeSort = null;

    public CourseKnowledgeGraphCorrelationNodeInfo greatGrandParentKnowledgeNodeId(String greatGrandParentKnowledgeNodeId) {
        this.greatGrandParentKnowledgeNodeId = greatGrandParentKnowledgeNodeId;
        return this;
    }

    /**
     * 上上上一个节点id
     *
     * @return greatGrandParentKnowledgeNodeId
     **/
    @ApiModelProperty(value = "上上上一个节点id")


    public String getGreatGrandParentKnowledgeNodeId() {
        return greatGrandParentKnowledgeNodeId;
    }

    public void setGreatGrandParentKnowledgeNodeId(String greatGrandParentKnowledgeNodeId) {
        this.greatGrandParentKnowledgeNodeId = greatGrandParentKnowledgeNodeId;
    }

    public CourseKnowledgeGraphCorrelationNodeInfo greatGrandParentKnowledgeNodeName(String greatGrandParentKnowledgeNodeName) {
        this.greatGrandParentKnowledgeNodeName = greatGrandParentKnowledgeNodeName;
        return this;
    }

    /**
     * 上上上一个节点名称
     *
     * @return greatGrandParentKnowledgeNodeName
     **/
    @ApiModelProperty(value = "上上上一个节点名称")

    public String getGreatGrandParentKnowledgeNodeName() {
        return greatGrandParentKnowledgeNodeName;
    }

    public void setGreatGrandParentKnowledgeNodeName(String greatGrandParentKnowledgeNodeName) {
        this.greatGrandParentKnowledgeNodeName = greatGrandParentKnowledgeNodeName;
    }

    public CourseKnowledgeGraphCorrelationNodeInfo grandParentKnowledgeNodeId(String grandParentKnowledgeNodeId) {
        this.grandParentKnowledgeNodeId = grandParentKnowledgeNodeId;
        return this;
    }

    /**
     * 上上一个节点id
     *
     * @return grandParentKnowledgeNodeId
     **/
    @ApiModelProperty(value = "上上一个节点id")


    public String getGrandParentKnowledgeNodeId() {
        return grandParentKnowledgeNodeId;
    }

    public void setGrandParentKnowledgeNodeId(String grandParentKnowledgeNodeId) {
        this.grandParentKnowledgeNodeId = grandParentKnowledgeNodeId;
    }

    public CourseKnowledgeGraphCorrelationNodeInfo grandParentKnowledgeNodeName(String grandParentKnowledgeNodeName) {
        this.grandParentKnowledgeNodeName = grandParentKnowledgeNodeName;
        return this;
    }

    /**
     * 上上一个节点名称
     *
     * @return grandParentKnowledgeNodeName
     **/
    @ApiModelProperty(value = "上上一个节点名称")

    public String getGrandParentKnowledgeNodeName() {
        return grandParentKnowledgeNodeName;
    }

    public void setGrandParentKnowledgeNodeName(String grandParentKnowledgeNodeName) {
        this.grandParentKnowledgeNodeName = grandParentKnowledgeNodeName;
    }
    public CourseKnowledgeGraphCorrelationNodeInfo parentKnowledgeNodeId(String parentKnowledgeNodeId) {
        this.parentKnowledgeNodeId = parentKnowledgeNodeId;
        return this;
    }

    /**
     * 上一个节点id
     *
     * @return parentKnowledgeNodeId
     **/
    @ApiModelProperty(value = "上一个节点id")

    public String getParentKnowledgeNodeId() {
        return parentKnowledgeNodeId;
    }

    public void setParentKnowledgeNodeId(String parentKnowledgeNodeId) {
        this.parentKnowledgeNodeId = parentKnowledgeNodeId;
    }
    public CourseKnowledgeGraphCorrelationNodeInfo parentKnowledgeNodeName(String parentKnowledgeNodeName) {
        this.parentKnowledgeNodeName = parentKnowledgeNodeName;
        return this;
    }

    /**
     * 上一个节点名称
     *
     * @return parentKnowledgeNodeName
     **/
    @ApiModelProperty(value = "上一个节点名称")

    public String getParentKnowledgeNodeName() {
        return parentKnowledgeNodeName;
    }

    public void setParentKnowledgeNodeName(String parentKnowledgeNodeName) {
        this.parentKnowledgeNodeName = parentKnowledgeNodeName;
    }
    public CourseKnowledgeGraphCorrelationNodeInfo childKnowledgeNodeId(String childKnowledgeNodeId) {
        this.childKnowledgeNodeId = childKnowledgeNodeId;
        return this;
    }

    /**
     * 搜索节点id
     *
     * @return childKnowledgeNodeId
     **/
    @ApiModelProperty(value = "搜索节点id")

    public String getChildKnowledgeNodeId() {
        return childKnowledgeNodeId;
    }

    public void setChildKnowledgeNodeId(String childKnowledgeNodeId) {
        this.childKnowledgeNodeId = childKnowledgeNodeId;
    }

    public CourseKnowledgeGraphCorrelationNodeInfo childKnowledgeNodeName(String childKnowledgeNodeName) {
        this.childKnowledgeNodeName = childKnowledgeNodeName;
        return this;
    }

    /**
     * 搜索节点名称
     *
     * @return childKnowledgeNodeName
     **/
    @ApiModelProperty(value = "搜索节点名称")

    public String getChildKnowledgeNodeName() {
        return childKnowledgeNodeName;
    }

    public void setChildKnowledgeNodeName(String childKnowledgeNodeName) {
        this.childKnowledgeNodeName = childKnowledgeNodeName;
    }


    public CourseKnowledgeGraphCorrelationNodeInfo childKnowledgeNodeLevel(Integer childKnowledgeNodeLevel) {
        this.childKnowledgeNodeLevel = childKnowledgeNodeLevel;
        return this;
    }

    /**
     * 搜索节点级别
     *
     * @return childKnowledgeNodeLevel
     **/
    @ApiModelProperty(value = "搜索节点级别")

    public Integer getChildKnowledgeNodeLevel() {
        return childKnowledgeNodeLevel;
    }

    public void setChildKnowledgeNodeLevel(Integer childKnowledgeNodeLevel) {
        this.childKnowledgeNodeLevel = childKnowledgeNodeLevel;
    }
    public CourseKnowledgeGraphCorrelationNodeInfo childKnowledgeNodeSort(Integer childKnowledgeNodeSort) {
        this.childKnowledgeNodeSort = childKnowledgeNodeSort;
        return this;
    }

    /**
     * 搜索节点排序
     *
     * @return childKnowledgeNodeSort
     **/
    @ApiModelProperty(value = "搜索节点排序")

    public Integer getChildKnowledgeNodeSort() {
        return childKnowledgeNodeSort;
    }

    public void setChildKnowledgeNodeSort(Integer childKnowledgeNodeSort) {
        this.childKnowledgeNodeSort = childKnowledgeNodeSort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphCorrelationNodeInfo courseKnowledgeGraphCorrelationNodeInfo = (CourseKnowledgeGraphCorrelationNodeInfo) o;
        return Objects.equals(this.greatGrandParentKnowledgeNodeId, courseKnowledgeGraphCorrelationNodeInfo.greatGrandParentKnowledgeNodeId) &&
                Objects.equals(this.greatGrandParentKnowledgeNodeName, courseKnowledgeGraphCorrelationNodeInfo.greatGrandParentKnowledgeNodeName) &&
                Objects.equals(this.grandParentKnowledgeNodeId, courseKnowledgeGraphCorrelationNodeInfo.grandParentKnowledgeNodeId) &&
                Objects.equals(this.grandParentKnowledgeNodeName, courseKnowledgeGraphCorrelationNodeInfo.grandParentKnowledgeNodeName) &&
                Objects.equals(this.parentKnowledgeNodeId, courseKnowledgeGraphCorrelationNodeInfo.parentKnowledgeNodeId) &&
                Objects.equals(this.parentKnowledgeNodeName, courseKnowledgeGraphCorrelationNodeInfo.parentKnowledgeNodeName) &&
                Objects.equals(this.childKnowledgeNodeId, courseKnowledgeGraphCorrelationNodeInfo.childKnowledgeNodeId) &&
                Objects.equals(this.childKnowledgeNodeName, courseKnowledgeGraphCorrelationNodeInfo.childKnowledgeNodeName) &&
                Objects.equals(this.childKnowledgeNodeLevel, courseKnowledgeGraphCorrelationNodeInfo.childKnowledgeNodeLevel) &&
                Objects.equals(this.childKnowledgeNodeSort, courseKnowledgeGraphCorrelationNodeInfo.childKnowledgeNodeSort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(greatGrandParentKnowledgeNodeId,greatGrandParentKnowledgeNodeName,grandParentKnowledgeNodeId,
                grandParentKnowledgeNodeName, parentKnowledgeNodeId, parentKnowledgeNodeName,
                childKnowledgeNodeId,childKnowledgeNodeName,childKnowledgeNodeLevel,childKnowledgeNodeSort);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphCorrelationNodeInfo {\n");
        sb.append("    greatGrandParentKnowledgeNodeId: ").append(toIndentedString(greatGrandParentKnowledgeNodeId)).append("\n");
        sb.append("    greatGrandParentKnowledgeNodeName: ").append(toIndentedString(greatGrandParentKnowledgeNodeName)).append("\n");
        sb.append("    grandParentKnowledgeNodeId: ").append(toIndentedString(grandParentKnowledgeNodeId)).append("\n");
        sb.append("    grandParentKnowledgeNodeName: ").append(toIndentedString(grandParentKnowledgeNodeName)).append("\n");
        sb.append("    parentKnowledgeNodeId: ").append(toIndentedString(parentKnowledgeNodeId)).append("\n");
        sb.append("    parentKnowledgeNodeName: ").append(toIndentedString(parentKnowledgeNodeName)).append("\n");
        sb.append("    childKnowledgeNodeId: ").append(toIndentedString(childKnowledgeNodeId)).append("\n");
        sb.append("    childKnowledgeNodeName: ").append(toIndentedString(childKnowledgeNodeName)).append("\n");
        sb.append("    childKnowledgeNodeLevel: ").append(toIndentedString(childKnowledgeNodeLevel)).append("\n");
        sb.append("    childKnowledgeNodeSort: ").append(toIndentedString(childKnowledgeNodeSort)).append("\n");
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

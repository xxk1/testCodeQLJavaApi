package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenNodeSortedParamNode
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-04T09:25:23.340+08:00")

public class KgAIGenNodeSortedParamNode {
    @JsonProperty("nodeId")
    private String nodeId = null;

    @JsonProperty("parentNodeId")
    private String parentNodeId = null;

    @JsonProperty("sort")
    private Integer sort = null;

    @JsonProperty("chapterContentId")
    private String chapterContentId = null;

    @JsonProperty("isSortedNode")
    private Boolean isSortedNode = null;

    public KgAIGenNodeSortedParamNode nodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * 节点id
     *
     * @return nodeId
     **/
    @ApiModelProperty(value = "节点id")


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public KgAIGenNodeSortedParamNode parentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
        return this;
    }

    /**
     * 父节点id
     *
     * @return parentNodeId
     **/
    @ApiModelProperty(value = "父节点id")


    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public KgAIGenNodeSortedParamNode sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    /**
     * 排序
     *
     * @return sort
     **/
    @ApiModelProperty(value = "排序")


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public KgAIGenNodeSortedParamNode chapterContentId(String chapterContentId) {
        this.chapterContentId = chapterContentId;
        return this;
    }

    /**
     * 章节内容id
     *
     * @return chapterContentId
     **/
    @ApiModelProperty(value = "章节内容id")


    public String getChapterContentId() {
        return chapterContentId;
    }

    public void setChapterContentId(String chapterContentId) {
        this.chapterContentId = chapterContentId;
    }

    public KgAIGenNodeSortedParamNode isSortedNode(Boolean isSortedNode) {
        this.isSortedNode = isSortedNode;
        return this;
    }

    /**
     * 是否排序节点
     *
     * @return isSortedNode
     **/
    @ApiModelProperty(value = "是否排序节点")


    public Boolean isIsSortedNode() {
        return isSortedNode;
    }

    public void setIsSortedNode(Boolean isSortedNode) {
        this.isSortedNode = isSortedNode;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenNodeSortedParamNode kgAIGenNodeSortedParamNode = (KgAIGenNodeSortedParamNode) o;
        return Objects.equals(this.nodeId, kgAIGenNodeSortedParamNode.nodeId) &&
                Objects.equals(this.parentNodeId, kgAIGenNodeSortedParamNode.parentNodeId) &&
                Objects.equals(this.sort, kgAIGenNodeSortedParamNode.sort) &&
                Objects.equals(this.chapterContentId, kgAIGenNodeSortedParamNode.chapterContentId) &&
                Objects.equals(this.isSortedNode, kgAIGenNodeSortedParamNode.isSortedNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, parentNodeId, sort, chapterContentId, isSortedNode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenNodeSortedParamNode {\n");

        sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
        sb.append("    parentNodeId: ").append(toIndentedString(parentNodeId)).append("\n");
        sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
        sb.append("    chapterContentId: ").append(toIndentedString(chapterContentId)).append("\n");
        sb.append("    isSortedNode: ").append(toIndentedString(isSortedNode)).append("\n");
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


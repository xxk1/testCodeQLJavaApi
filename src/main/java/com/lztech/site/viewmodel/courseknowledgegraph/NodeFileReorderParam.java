package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * NodeFileReorderParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-30T08:44:28.866Z")


public class NodeFileReorderParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("knowledgeGraphNodeId")
    private String knowledgeGraphNodeId = null;

    @JsonProperty("needToBeMovedNodeFileId")
    private String needToBeMovedNodeFileId = null;

    @JsonProperty("targetLocationBeforeNodeFileId")
    private String targetLocationBeforeNodeFileId = null;

    @JsonProperty("targetLocationAfterNodeFileId")
    private String targetLocationAfterNodeFileId = null;

    public NodeFileReorderParam courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public NodeFileReorderParam courseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
        return this;
    }

    /**
     * 课程知识图谱id
     *
     * @return courseKnowledgeGraphId
     **/
    @ApiModelProperty(value = "课程知识图谱id")

    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }

    public NodeFileReorderParam knowledgeGraphNodeId(String knowledgeGraphNodeId) {
        this.knowledgeGraphNodeId = knowledgeGraphNodeId;
        return this;
    }

    /**
     * 课程知识图谱节点id
     *
     * @return knowledgeGraphNodeId
     **/
    @ApiModelProperty(value = "课程知识图谱节点id")


    public String getKnowledgeGraphNodeId() {
        return knowledgeGraphNodeId;
    }

    public void setKnowledgeGraphNodeId(String knowledgeGraphNodeId) {
        this.knowledgeGraphNodeId = knowledgeGraphNodeId;
    }

    public NodeFileReorderParam needToBeMovedNodeFileId(String needToBeMovedNodeFileId) {
        this.needToBeMovedNodeFileId = needToBeMovedNodeFileId;
        return this;
    }

    /**
     * 被移动的节点文件ID
     *
     * @return needToBeMovedNodeFileId
     **/
    @ApiModelProperty(value = "被移动的节点文件ID")


    public String getNeedToBeMovedNodeFileId() {
        return needToBeMovedNodeFileId;
    }

    public void setNeedToBeMovedNodeFileId(String needToBeMovedNodeFileId) {
        this.needToBeMovedNodeFileId = needToBeMovedNodeFileId;
    }

    public NodeFileReorderParam targetLocationBeforeNodeFileId(String targetLocationBeforeNodeFileId) {
        this.targetLocationBeforeNodeFileId = targetLocationBeforeNodeFileId;
        return this;
    }

    /**
     * 目标位置前节点文件ID
     *
     * @return targetLocationBeforeNodeFileId
     **/
    @ApiModelProperty(value = "目标位置前节点文件ID")


    public String getTargetLocationBeforeNodeFileId() {
        return targetLocationBeforeNodeFileId;
    }

    public void setTargetLocationBeforeNodeFileId(String targetLocationBeforeNodeFileId) {
        this.targetLocationBeforeNodeFileId = targetLocationBeforeNodeFileId;
    }

    public NodeFileReorderParam targetLocationAfterNodeFileId(String targetLocationAfterNodeFileId) {
        this.targetLocationAfterNodeFileId = targetLocationAfterNodeFileId;
        return this;
    }

    /**
     * 目标位置后节点文件ID
     *
     * @return targetLocationAfterNodeFileId
     **/
    @ApiModelProperty(value = "目标位置后节点文件ID")


    public String getTargetLocationAfterNodeFileId() {
        return targetLocationAfterNodeFileId;
    }

    public void setTargetLocationAfterNodeFileId(String targetLocationAfterNodeFileId) {
        this.targetLocationAfterNodeFileId = targetLocationAfterNodeFileId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeFileReorderParam nodeFileReorderParam = (NodeFileReorderParam) o;
        return Objects.equals(this.courseId, nodeFileReorderParam.courseId) &&
                Objects.equals(this.courseKnowledgeGraphId, nodeFileReorderParam.courseKnowledgeGraphId) &&
                Objects.equals(this.knowledgeGraphNodeId, nodeFileReorderParam.knowledgeGraphNodeId) &&
                Objects.equals(this.needToBeMovedNodeFileId, nodeFileReorderParam.needToBeMovedNodeFileId) &&
                Objects.equals(this.targetLocationBeforeNodeFileId, nodeFileReorderParam.targetLocationBeforeNodeFileId) &&
                Objects.equals(this.targetLocationAfterNodeFileId, nodeFileReorderParam.targetLocationAfterNodeFileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseKnowledgeGraphId,knowledgeGraphNodeId, needToBeMovedNodeFileId, targetLocationBeforeNodeFileId,
                targetLocationAfterNodeFileId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NodeFileReorderParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseKnowledgeGraphId: ").append(toIndentedString(courseKnowledgeGraphId)).append("\n");
        sb.append("    knowledgeGraphNodeId: ").append(toIndentedString(knowledgeGraphNodeId)).append("\n");
        sb.append("    needToBeMovedNodeFileId: ").append(toIndentedString(needToBeMovedNodeFileId)).append("\n");
        sb.append("    targetLocationBeforeNodeFileId: ").append(toIndentedString(targetLocationBeforeNodeFileId)).append("\n");
        sb.append("    targetLocationAfterNodeFileId: ").append(toIndentedString(targetLocationAfterNodeFileId)).append("\n");
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


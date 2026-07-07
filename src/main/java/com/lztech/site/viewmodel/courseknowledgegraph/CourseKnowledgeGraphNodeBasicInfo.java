package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeBasicInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-20T07:23:54.980Z")


public class CourseKnowledgeGraphNodeBasicInfo {
    @JsonProperty("knowledgeNodeId")
    private String knowledgeNodeId = null;

    @JsonProperty("knowledgeNodeName")
    private String knowledgeNodeName = null;

    @JsonProperty("knowledgeNodeLevelIndex")
    private Integer knowledgeNodeLevelIndex = null;

    @JsonProperty("knowledgeNodeLevelName")
    private String knowledgeNodeLevelName = null;

    public CourseKnowledgeGraphNodeBasicInfo knowledgeNodeId(String knowledgeNodeId) {
        this.knowledgeNodeId = knowledgeNodeId;
        return this;
    }

    /**
     * 知识节点id
     *
     * @return knowledgeNodeId
     **/
    @ApiModelProperty(value = "知识节点id")


    public String getKnowledgeNodeId() {
        return knowledgeNodeId;
    }

    public void setKnowledgeNodeId(String knowledgeNodeId) {
        this.knowledgeNodeId = knowledgeNodeId;
    }

    public CourseKnowledgeGraphNodeBasicInfo knowledgeNodeName(String knowledgeNodeName) {
        this.knowledgeNodeName = knowledgeNodeName;
        return this;
    }

    /**
     * 知识节点名称
     *
     * @return knowledgeNodeName
     **/
    @ApiModelProperty(value = "知识节点名称")


    public String getKnowledgeNodeName() {
        return knowledgeNodeName;
    }

    public void setKnowledgeNodeName(String knowledgeNodeName) {
        this.knowledgeNodeName = knowledgeNodeName;
    }

    public CourseKnowledgeGraphNodeBasicInfo knowledgeNodeLevelIndex(Integer knowledgeNodeLevelIndex) {
        this.knowledgeNodeLevelIndex = knowledgeNodeLevelIndex;
        return this;
    }

    /**
     * 知识节点级别编号
     *
     * @return knowledgeNodeLevelIndex
     **/
    @ApiModelProperty(value = "知识节点级别编号")


    public Integer getKnowledgeNodeLevelIndex() {
        return knowledgeNodeLevelIndex;
    }

    public void setKnowledgeNodeLevelIndex(Integer knowledgeNodeLevelIndex) {
        this.knowledgeNodeLevelIndex = knowledgeNodeLevelIndex;
    }

    public CourseKnowledgeGraphNodeBasicInfo knowledgeNodeLevelName(String knowledgeNodeLevelName) {
        this.knowledgeNodeLevelName = knowledgeNodeLevelName;
        return this;
    }

    /**
     * 知识节点级别名称
     *
     * @return knowledgeNodeLevelName
     **/
    @ApiModelProperty(value = "知识节点级别名称")


    public String getKnowledgeNodeLevelName() {
        return knowledgeNodeLevelName;
    }

    public void setKnowledgeNodeLevelName(String knowledgeNodeLevelName) {
        this.knowledgeNodeLevelName = knowledgeNodeLevelName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeBasicInfo courseKnowledgeGraphNodeBasicInfo = (CourseKnowledgeGraphNodeBasicInfo) o;
        return Objects.equals(this.knowledgeNodeId, courseKnowledgeGraphNodeBasicInfo.knowledgeNodeId) &&
                Objects.equals(this.knowledgeNodeName, courseKnowledgeGraphNodeBasicInfo.knowledgeNodeName) &&
                Objects.equals(this.knowledgeNodeLevelIndex, courseKnowledgeGraphNodeBasicInfo.knowledgeNodeLevelIndex) &&
                Objects.equals(this.knowledgeNodeLevelName, courseKnowledgeGraphNodeBasicInfo.knowledgeNodeLevelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knowledgeNodeId, knowledgeNodeName, knowledgeNodeLevelIndex, knowledgeNodeLevelName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeBasicInfo {\n");

        sb.append("    knowledgeNodeId: ").append(toIndentedString(knowledgeNodeId)).append("\n");
        sb.append("    knowledgeNodeName: ").append(toIndentedString(knowledgeNodeName)).append("\n");
        sb.append("    knowledgeNodeLevelIndex: ").append(toIndentedString(knowledgeNodeLevelIndex)).append("\n");
        sb.append("    knowledgeNodeLevelName: ").append(toIndentedString(knowledgeNodeLevelName)).append("\n");
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


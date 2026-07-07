package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-26T11:20:06.069Z")


public class CourseKnowledgeGraphNodeVo {
    @JsonProperty("knowledgeGraphNodeId")
    private String knowledgeGraphNodeId = null;

    @JsonProperty("knowledgeNodeName")
    private String knowledgeNodeName = null;

    @JsonProperty("relationshipId")
    private Integer relationshipId = null;

    @JsonProperty("relationshipCreateTimestamp")
    private Long relationshipCreateTimestamp = null;

    public CourseKnowledgeGraphNodeVo knowledgeGraphNodeId(String knowledgeGraphNodeId) {
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

    public CourseKnowledgeGraphNodeVo knowledgeNodeName(String knowledgeNodeName) {
        this.knowledgeNodeName = knowledgeNodeName;
        return this;
    }

    /**
     * 课程知识图谱节点名称
     *
     * @return knowledgeNodeName
     **/
    @ApiModelProperty(value = "课程知识图谱节点名称")


    public String getKnowledgeNodeName() {
        return knowledgeNodeName;
    }

    public void setKnowledgeNodeName(String knowledgeNodeName) {
        this.knowledgeNodeName = knowledgeNodeName;
    }

    public CourseKnowledgeGraphNodeVo relationshipId(Integer relationshipId) {
        this.relationshipId = relationshipId;
        return this;
    }

    /**
     * 关系id
     *
     * @return relationshipId
     **/
    @ApiModelProperty(value = "关系id")


    public Integer getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Integer relationshipId) {
        this.relationshipId = relationshipId;
    }

    public CourseKnowledgeGraphNodeVo relationshipCreateTimestamp(Long relationshipCreateTimestamp) {
        this.relationshipCreateTimestamp = relationshipCreateTimestamp;
        return this;
    }

    /**
     * 关系创建时间戳
     *
     * @return relationshipCreateTimestamp
     **/
    @ApiModelProperty(value = "关系创建时间戳")


    public Long getRelationshipCreateTimestamp() {
        return relationshipCreateTimestamp;
    }

    public void setRelationshipCreateTimestamp(Long relationshipCreateTimestamp) {
        this.relationshipCreateTimestamp = relationshipCreateTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeVo courseKnowledgeGraphNodeVo = (CourseKnowledgeGraphNodeVo) o;
        return Objects.equals(this.knowledgeGraphNodeId, courseKnowledgeGraphNodeVo.knowledgeGraphNodeId) &&
                Objects.equals(this.knowledgeNodeName, courseKnowledgeGraphNodeVo.knowledgeNodeName) &&
                Objects.equals(this.relationshipId, courseKnowledgeGraphNodeVo.relationshipId) &&
                Objects.equals(this.relationshipCreateTimestamp, courseKnowledgeGraphNodeVo.relationshipCreateTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knowledgeGraphNodeId, knowledgeNodeName, relationshipId, relationshipCreateTimestamp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeVo {\n");
        sb.append("    knowledgeGraphNodeId: ").append(toIndentedString(knowledgeGraphNodeId)).append("\n");
        sb.append("    knowledgeNodeName: ").append(toIndentedString(knowledgeNodeName)).append("\n");
        sb.append("    relationshipId: ").append(toIndentedString(relationshipId)).append("\n");
        sb.append("    relationshipCreateTimestamp: ").append(toIndentedString(relationshipCreateTimestamp)).append("\n");
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


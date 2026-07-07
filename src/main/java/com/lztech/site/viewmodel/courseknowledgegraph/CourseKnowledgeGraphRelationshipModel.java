package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphRelationshipModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-31T03:06:00.478Z")


public class CourseKnowledgeGraphRelationshipModel {
    @JsonProperty("startNodeId")
    private String startNodeId = null;

    @JsonProperty("endNodeId")
    private String endNodeId = null;

    @JsonProperty("relationshipId")
    private Integer relationshipId = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    public CourseKnowledgeGraphRelationshipModel startNodeId(String startNodeId) {
        this.startNodeId = startNodeId;
        return this;
    }

    /**
     * 开始节点id
     *
     * @return startNodeId
     **/
    @ApiModelProperty(value = "开始节点id")


    public String getStartNodeId() {
        return startNodeId;
    }

    public void setStartNodeId(String startNodeId) {
        this.startNodeId = startNodeId;
    }

    public CourseKnowledgeGraphRelationshipModel endNodeId(String endNodeId) {
        this.endNodeId = endNodeId;
        return this;
    }

    /**
     * 结束节点id
     *
     * @return endNodeId
     **/
    @ApiModelProperty(value = "结束节点id")


    public String getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(String endNodeId) {
        this.endNodeId = endNodeId;
    }

    public CourseKnowledgeGraphRelationshipModel relationshipId(Integer relationshipId) {
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


    public CourseKnowledgeGraphRelationshipModel courseKnowledgeGraphId(String courseKnowledgeGraphId) {
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



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphRelationshipModel courseKnowledgeGraphRelationshipModel = (CourseKnowledgeGraphRelationshipModel) o;
        return Objects.equals(this.startNodeId, courseKnowledgeGraphRelationshipModel.startNodeId) &&
                Objects.equals(this.endNodeId, courseKnowledgeGraphRelationshipModel.endNodeId) &&
                Objects.equals(this.relationshipId, courseKnowledgeGraphRelationshipModel.relationshipId) &&
                Objects.equals(this.courseKnowledgeGraphId, courseKnowledgeGraphRelationshipModel.courseKnowledgeGraphId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startNodeId, endNodeId, relationshipId,courseKnowledgeGraphId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphRelationshipModel {\n");

        sb.append("    startNodeId: ").append(toIndentedString(startNodeId)).append("\n");
        sb.append("    endNodeId: ").append(toIndentedString(endNodeId)).append("\n");
        sb.append("    relationshipId: ").append(toIndentedString(relationshipId)).append("\n");
        sb.append("    courseKnowledgeGraphId: ").append(toIndentedString(courseKnowledgeGraphId)).append("\n");

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


package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeRelationshipParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-29T08:33:28.451Z")


public class CourseKnowledgeGraphNodeRelationshipParam {
    @JsonProperty("knowledgeGraphNodeId")
    private String knowledgeGraphNodeId = null;

    @JsonProperty("preKnowledgeGraphNodeId")
    private String preKnowledgeGraphNodeId = null;

    @JsonProperty("postKnowledgeGraphNodeId")
    private String postKnowledgeGraphNodeId = null;

    @JsonProperty("relevanceKnowledgeGraphNodeId")
    private String relevanceKnowledgeGraphNodeId = null;

    @JsonProperty("accessUserId")
    private String accessUserId = null;

    @JsonProperty("accessUserName")
    private String accessUserName = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    public CourseKnowledgeGraphNodeRelationshipParam knowledgeGraphNodeId(String knowledgeGraphNodeId) {
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

    public CourseKnowledgeGraphNodeRelationshipParam preKnowledgeGraphNodeId(String preKnowledgeGraphNodeId) {
        this.preKnowledgeGraphNodeId = preKnowledgeGraphNodeId;
        return this;
    }

    /**
     * 前置课程知识图谱节点id
     *
     * @return preKnowledgeGraphNodeId
     **/
    @ApiModelProperty(value = "前置课程知识图谱节点id")


    public String getPreKnowledgeGraphNodeId() {
        return preKnowledgeGraphNodeId;
    }

    public void setPreKnowledgeGraphNodeId(String preKnowledgeGraphNodeId) {
        this.preKnowledgeGraphNodeId = preKnowledgeGraphNodeId;
    }

    public CourseKnowledgeGraphNodeRelationshipParam postKnowledgeGraphNodeId(String postKnowledgeGraphNodeId) {
        this.postKnowledgeGraphNodeId = postKnowledgeGraphNodeId;
        return this;
    }

    /**
     * 后置课程知识图谱节点id
     *
     * @return postKnowledgeGraphNodeId
     **/
    @ApiModelProperty(value = "后置课程知识图谱节点id")


    public String getPostKnowledgeGraphNodeId() {
        return postKnowledgeGraphNodeId;
    }

    public void setPostKnowledgeGraphNodeId(String postKnowledgeGraphNodeId) {
        this.postKnowledgeGraphNodeId = postKnowledgeGraphNodeId;
    }

    public CourseKnowledgeGraphNodeRelationshipParam relevanceKnowledgeGraphNodeId(String relevanceKnowledgeGraphNodeId) {
        this.relevanceKnowledgeGraphNodeId = relevanceKnowledgeGraphNodeId;
        return this;
    }

    /**
     * 关联课程知识图谱节点id
     *
     * @return relevanceKnowledgeGraphNodeId
     **/
    @ApiModelProperty(value = "关联课程知识图谱节点id")


    public String getRelevanceKnowledgeGraphNodeId() {
        return relevanceKnowledgeGraphNodeId;
    }

    public void setRelevanceKnowledgeGraphNodeId(String relevanceKnowledgeGraphNodeId) {
        this.relevanceKnowledgeGraphNodeId = relevanceKnowledgeGraphNodeId;
    }

    public CourseKnowledgeGraphNodeRelationshipParam accessUserId(String accessUserId) {
        this.accessUserId = accessUserId;
        return this;
    }

    /**
     * 访问用户id
     *
     * @return accessUserId
     **/
    @ApiModelProperty(value = "访问用户id")


    public String getAccessUserId() {
        return accessUserId;
    }

    public void setAccessUserId(String accessUserId) {
        this.accessUserId = accessUserId;
    }

    public CourseKnowledgeGraphNodeRelationshipParam accessUserName(String accessUserName) {
        this.accessUserName = accessUserName;
        return this;
    }

    /**
     * 访问用户名称
     *
     * @return accessUserName
     **/
    @ApiModelProperty(value = "访问用户名称")


    public String getAccessUserName() {
        return accessUserName;
    }

    public void setAccessUserName(String accessUserName) {
        this.accessUserName = accessUserName;
    }


    public CourseKnowledgeGraphNodeRelationshipParam courseKnowledgeGraphId(String courseKnowledgeGraphId) {
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
        CourseKnowledgeGraphNodeRelationshipParam courseKnowledgeGraphNodeRelationshipParam = (CourseKnowledgeGraphNodeRelationshipParam) o;
        return Objects.equals(this.knowledgeGraphNodeId, courseKnowledgeGraphNodeRelationshipParam.knowledgeGraphNodeId)
                && Objects.equals(this.preKnowledgeGraphNodeId, courseKnowledgeGraphNodeRelationshipParam.preKnowledgeGraphNodeId)
                && Objects.equals(this.postKnowledgeGraphNodeId, courseKnowledgeGraphNodeRelationshipParam.postKnowledgeGraphNodeId)
                && Objects.equals(this.relevanceKnowledgeGraphNodeId, courseKnowledgeGraphNodeRelationshipParam.relevanceKnowledgeGraphNodeId)
                && Objects.equals(this.accessUserId, courseKnowledgeGraphNodeRelationshipParam.accessUserId)
                && Objects.equals(this.accessUserName, courseKnowledgeGraphNodeRelationshipParam.accessUserName)
                && Objects.equals(this.courseKnowledgeGraphId, courseKnowledgeGraphNodeRelationshipParam.courseKnowledgeGraphId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knowledgeGraphNodeId, preKnowledgeGraphNodeId, postKnowledgeGraphNodeId,
                relevanceKnowledgeGraphNodeId, accessUserId, accessUserName,courseKnowledgeGraphId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeRelationshipParam {\n");

        sb.append("    knowledgeGraphNodeId: ").append(toIndentedString(knowledgeGraphNodeId)).append("\n");
        sb.append("    preKnowledgeGraphNodeId: ").append(toIndentedString(preKnowledgeGraphNodeId)).append("\n");
        sb.append("    postKnowledgeGraphNodeId: ").append(toIndentedString(postKnowledgeGraphNodeId)).append("\n");
        sb.append("    relevanceKnowledgeGraphNodeId: ").append(toIndentedString(relevanceKnowledgeGraphNodeId)).append("\n");
        sb.append("    accessUserId: ").append(toIndentedString(accessUserId)).append("\n");
        sb.append("    accessUserName: ").append(toIndentedString(accessUserName)).append("\n");
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


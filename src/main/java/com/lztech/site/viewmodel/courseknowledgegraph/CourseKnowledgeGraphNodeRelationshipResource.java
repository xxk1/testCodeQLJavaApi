package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeRelationshipResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-26T11:20:06.069Z")


public class CourseKnowledgeGraphNodeRelationshipResource {
    @JsonProperty("preposeKnowledgeGraphNodeList")
    @Valid
    private List<CourseKnowledgeGraphNodeVo> preposeKnowledgeGraphNodeList = null;

    @JsonProperty("postposeKnowledgeGraphNodeList")
    @Valid
    private List<CourseKnowledgeGraphNodeVo> postposeKnowledgeGraphNodeList = null;

    @JsonProperty("relevanceKnowledgeGraphNodeList")
    @Valid
    private List<CourseKnowledgeGraphNodeVo> relevanceKnowledgeGraphNodeList = null;

    public CourseKnowledgeGraphNodeRelationshipResource preposeKnowledgeGraphNodeList(
            List<CourseKnowledgeGraphNodeVo> preposeKnowledgeGraphNodeList) {
        this.preposeKnowledgeGraphNodeList = preposeKnowledgeGraphNodeList;
        return this;
    }

    public CourseKnowledgeGraphNodeRelationshipResource addPreposeKnowledgeGraphNodeListItem(
            CourseKnowledgeGraphNodeVo preposeKnowledgeGraphNodeListItem) {
        if (this.preposeKnowledgeGraphNodeList == null) {
            this.preposeKnowledgeGraphNodeList = new ArrayList<CourseKnowledgeGraphNodeVo>();
        }
        this.preposeKnowledgeGraphNodeList.add(preposeKnowledgeGraphNodeListItem);
        return this;
    }

    /**
     * 前置关系知识节点列表
     *
     * @return preposeKnowledgeGraphNodeList
     **/
    @ApiModelProperty(value = "前置关系知识节点列表")

    @Valid

    public List<CourseKnowledgeGraphNodeVo> getPreposeKnowledgeGraphNodeList() {
        return preposeKnowledgeGraphNodeList;
    }

    public void setPreposeKnowledgeGraphNodeList(List<CourseKnowledgeGraphNodeVo> preposeKnowledgeGraphNodeList) {
        this.preposeKnowledgeGraphNodeList = preposeKnowledgeGraphNodeList;
    }

    public CourseKnowledgeGraphNodeRelationshipResource postposeKnowledgeGraphNodeList(
            List<CourseKnowledgeGraphNodeVo> postposeKnowledgeGraphNodeList) {
        this.postposeKnowledgeGraphNodeList = postposeKnowledgeGraphNodeList;
        return this;
    }

    public CourseKnowledgeGraphNodeRelationshipResource addPostposeKnowledgeGraphNodeListItem(
            CourseKnowledgeGraphNodeVo postposeKnowledgeGraphNodeListItem) {
        if (this.postposeKnowledgeGraphNodeList == null) {
            this.postposeKnowledgeGraphNodeList = new ArrayList<CourseKnowledgeGraphNodeVo>();
        }
        this.postposeKnowledgeGraphNodeList.add(postposeKnowledgeGraphNodeListItem);
        return this;
    }

    /**
     * 后置关系知识节点列表
     *
     * @return postposeKnowledgeGraphNodeList
     **/
    @ApiModelProperty(value = "后置关系知识节点列表")

    @Valid

    public List<CourseKnowledgeGraphNodeVo> getPostposeKnowledgeGraphNodeList() {
        return postposeKnowledgeGraphNodeList;
    }

    public void setPostposeKnowledgeGraphNodeList(List<CourseKnowledgeGraphNodeVo> postposeKnowledgeGraphNodeList) {
        this.postposeKnowledgeGraphNodeList = postposeKnowledgeGraphNodeList;
    }

    public CourseKnowledgeGraphNodeRelationshipResource relevanceKnowledgeGraphNodeList(
            List<CourseKnowledgeGraphNodeVo> relevanceKnowledgeGraphNodeList) {
        this.relevanceKnowledgeGraphNodeList = relevanceKnowledgeGraphNodeList;
        return this;
    }

    public CourseKnowledgeGraphNodeRelationshipResource addRelevanceKnowledgeGraphNodeListItem(
            CourseKnowledgeGraphNodeVo relevanceKnowledgeGraphNodeListItem) {
        if (this.relevanceKnowledgeGraphNodeList == null) {
            this.relevanceKnowledgeGraphNodeList = new ArrayList<CourseKnowledgeGraphNodeVo>();
        }
        this.relevanceKnowledgeGraphNodeList.add(relevanceKnowledgeGraphNodeListItem);
        return this;
    }

    /**
     * 关联关系知识节点列表
     *
     * @return relevanceKnowledgeGraphNodeList
     **/
    @ApiModelProperty(value = "关联关系知识节点列表")

    @Valid

    public List<CourseKnowledgeGraphNodeVo> getRelevanceKnowledgeGraphNodeList() {
        return relevanceKnowledgeGraphNodeList;
    }

    public void setRelevanceKnowledgeGraphNodeList(
            List<CourseKnowledgeGraphNodeVo> relevanceKnowledgeGraphNodeList) {
        this.relevanceKnowledgeGraphNodeList = relevanceKnowledgeGraphNodeList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeRelationshipResource courseKnowledgeGraphNodeRelationshipResource = (CourseKnowledgeGraphNodeRelationshipResource) o;
        return Objects.equals(this.preposeKnowledgeGraphNodeList, courseKnowledgeGraphNodeRelationshipResource.preposeKnowledgeGraphNodeList) &&
                Objects.equals(this.postposeKnowledgeGraphNodeList, courseKnowledgeGraphNodeRelationshipResource.postposeKnowledgeGraphNodeList) &&
                Objects.equals(this.relevanceKnowledgeGraphNodeList, courseKnowledgeGraphNodeRelationshipResource.relevanceKnowledgeGraphNodeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preposeKnowledgeGraphNodeList, postposeKnowledgeGraphNodeList, relevanceKnowledgeGraphNodeList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeRelationshipResource {\n");

        sb.append("    preposeKnowledgeGraphNodeList: ").append(toIndentedString(preposeKnowledgeGraphNodeList)).append("\n");
        sb.append("    postposeKnowledgeGraphNodeList: ").append(toIndentedString(postposeKnowledgeGraphNodeList)).append("\n");
        sb.append("    relevanceKnowledgeGraphNodeList: ").append(toIndentedString(relevanceKnowledgeGraphNodeList)).append("\n");
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


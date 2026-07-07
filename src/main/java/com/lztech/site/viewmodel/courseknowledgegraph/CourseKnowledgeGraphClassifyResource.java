package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeGraphClassifyResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-31T03:06:00.478Z")


public class CourseKnowledgeGraphClassifyResource {
    @JsonProperty("courseKnowledgeGraphNodeList")
    @Valid
    private List<CourseKnowledgeGraphNodeBaseModel> courseKnowledgeGraphNodeList = new ArrayList<>();

    @JsonProperty("parentChildKnowledgeGraphRelationshipList")
    @Valid
    private List<CourseKnowledgeGraphRelationshipModel> parentChildKnowledgeGraphRelationshipList = new ArrayList<>();

    @JsonProperty("frontRearKnowledgeGraphRelationshipList")
    @Valid
    private List<CourseKnowledgeGraphRelationshipModel> frontRearKnowledgeGraphRelationshipList = new ArrayList<>();

    @JsonProperty("relevanceKnowledgeGraphRelationshipList")
    @Valid
    private List<CourseKnowledgeGraphRelationshipModel> relevanceKnowledgeGraphRelationshipList = new ArrayList<>();

    public CourseKnowledgeGraphClassifyResource courseKnowledgeGraphNodeList(
            List<CourseKnowledgeGraphNodeBaseModel> courseKnowledgeGraphNodeList) {
        this.courseKnowledgeGraphNodeList = courseKnowledgeGraphNodeList;
        return this;
    }

    public CourseKnowledgeGraphClassifyResource addCourseKnowledgeGraphNodeListItem(
            CourseKnowledgeGraphNodeBaseModel courseKnowledgeGraphNodeListItem) {
        if (this.courseKnowledgeGraphNodeList == null) {
            this.courseKnowledgeGraphNodeList = new ArrayList<CourseKnowledgeGraphNodeBaseModel>();
        }
        this.courseKnowledgeGraphNodeList.add(courseKnowledgeGraphNodeListItem);
        return this;
    }

    /**
     * 知识节点列表
     *
     * @return courseKnowledgeGraphNodeList
     **/
    @ApiModelProperty(value = "知识节点列表")

    @Valid

    public List<CourseKnowledgeGraphNodeBaseModel> getCourseKnowledgeGraphNodeList() {
        return courseKnowledgeGraphNodeList;
    }

    public void setCourseKnowledgeGraphNodeList(List<CourseKnowledgeGraphNodeBaseModel> courseKnowledgeGraphNodeList) {
        this.courseKnowledgeGraphNodeList = courseKnowledgeGraphNodeList;
    }

    public CourseKnowledgeGraphClassifyResource parentChildKnowledgeGraphRelationshipList(
            List<CourseKnowledgeGraphRelationshipModel> parentChildKnowledgeGraphRelationshipList) {
        this.parentChildKnowledgeGraphRelationshipList = parentChildKnowledgeGraphRelationshipList;
        return this;
    }

    public CourseKnowledgeGraphClassifyResource addParentChildKnowledgeGraphRelationshipListItem(
            CourseKnowledgeGraphRelationshipModel parentChildKnowledgeGraphRelationshipListItem) {
        if (this.parentChildKnowledgeGraphRelationshipList == null) {
            this.parentChildKnowledgeGraphRelationshipList = new ArrayList<CourseKnowledgeGraphRelationshipModel>();
        }
        this.parentChildKnowledgeGraphRelationshipList.add(parentChildKnowledgeGraphRelationshipListItem);
        return this;
    }

    /**
     * 父子知识节点关系列表
     *
     * @return parentChildKnowledgeGraphRelationshipList
     **/
    @ApiModelProperty(value = "父子知识节点关系列表")

    @Valid

    public List<CourseKnowledgeGraphRelationshipModel> getParentChildKnowledgeGraphRelationshipList() {
        return parentChildKnowledgeGraphRelationshipList;
    }

    public void setParentChildKnowledgeGraphRelationshipList(
            List<CourseKnowledgeGraphRelationshipModel> parentChildKnowledgeGraphRelationshipList) {
        this.parentChildKnowledgeGraphRelationshipList = parentChildKnowledgeGraphRelationshipList;
    }

    public CourseKnowledgeGraphClassifyResource frontRearKnowledgeGraphRelationshipList(
            List<CourseKnowledgeGraphRelationshipModel> frontRearKnowledgeGraphRelationshipList) {
        this.frontRearKnowledgeGraphRelationshipList = frontRearKnowledgeGraphRelationshipList;
        return this;
    }

    public CourseKnowledgeGraphClassifyResource addFrontRearKnowledgeGraphRelationshipListItem(
            CourseKnowledgeGraphRelationshipModel frontRearKnowledgeGraphRelationshipListItem) {
        if (this.frontRearKnowledgeGraphRelationshipList == null) {
            this.frontRearKnowledgeGraphRelationshipList = new ArrayList<CourseKnowledgeGraphRelationshipModel>();
        }
        this.frontRearKnowledgeGraphRelationshipList.add(frontRearKnowledgeGraphRelationshipListItem);
        return this;
    }

    /**
     * 前后置知识节点关系列表
     *
     * @return frontRearKnowledgeGraphRelationshipList
     **/
    @ApiModelProperty(value = "前后置知识节点关系列表")

    @Valid

    public List<CourseKnowledgeGraphRelationshipModel> getFrontRearKnowledgeGraphRelationshipList() {
        return frontRearKnowledgeGraphRelationshipList;
    }

    public void setFrontRearKnowledgeGraphRelationshipList(
            List<CourseKnowledgeGraphRelationshipModel> frontRearKnowledgeGraphRelationshipList) {
        this.frontRearKnowledgeGraphRelationshipList = frontRearKnowledgeGraphRelationshipList;
    }

    public CourseKnowledgeGraphClassifyResource relevanceKnowledgeGraphRelationshipList(
            List<CourseKnowledgeGraphRelationshipModel> relevanceKnowledgeGraphRelationshipList) {
        this.relevanceKnowledgeGraphRelationshipList = relevanceKnowledgeGraphRelationshipList;
        return this;
    }

    public CourseKnowledgeGraphClassifyResource addRelevanceKnowledgeGraphRelationshipListItem(
            CourseKnowledgeGraphRelationshipModel relevanceKnowledgeGraphRelationshipListItem) {
        if (this.relevanceKnowledgeGraphRelationshipList == null) {
            this.relevanceKnowledgeGraphRelationshipList = new ArrayList<CourseKnowledgeGraphRelationshipModel>();
        }
        this.relevanceKnowledgeGraphRelationshipList.add(relevanceKnowledgeGraphRelationshipListItem);
        return this;
    }

    /**
     * 关联知识节点关系列表
     *
     * @return relevanceKnowledgeGraphRelationshipList
     **/
    @ApiModelProperty(value = "关联知识节点关系列表")

    @Valid

    public List<CourseKnowledgeGraphRelationshipModel> getRelevanceKnowledgeGraphRelationshipList() {
        return relevanceKnowledgeGraphRelationshipList;
    }

    public void setRelevanceKnowledgeGraphRelationshipList(
            List<CourseKnowledgeGraphRelationshipModel> relevanceKnowledgeGraphRelationshipList) {
        this.relevanceKnowledgeGraphRelationshipList = relevanceKnowledgeGraphRelationshipList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphClassifyResource courseKnowledgeGraphClassifyResource = (CourseKnowledgeGraphClassifyResource) o;
        return Objects.equals(this.courseKnowledgeGraphNodeList, courseKnowledgeGraphClassifyResource.courseKnowledgeGraphNodeList) &&
                Objects.equals(this.parentChildKnowledgeGraphRelationshipList,
                        courseKnowledgeGraphClassifyResource.parentChildKnowledgeGraphRelationshipList) &&
                Objects.equals(this.frontRearKnowledgeGraphRelationshipList,
                        courseKnowledgeGraphClassifyResource.frontRearKnowledgeGraphRelationshipList) &&
                Objects.equals(this.relevanceKnowledgeGraphRelationshipList,
                        courseKnowledgeGraphClassifyResource.relevanceKnowledgeGraphRelationshipList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseKnowledgeGraphNodeList, parentChildKnowledgeGraphRelationshipList,
                frontRearKnowledgeGraphRelationshipList, relevanceKnowledgeGraphRelationshipList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphClassifyResource {\n");

        sb.append("    courseKnowledgeGraphNodeList: ").append(toIndentedString(courseKnowledgeGraphNodeList)).append("\n");
        sb.append("    parentChildKnowledgeGraphRelationshipList: ").append(toIndentedString(parentChildKnowledgeGraphRelationshipList)).append("\n");
        sb.append("    frontRearKnowledgeGraphRelationshipList: ").append(toIndentedString(frontRearKnowledgeGraphRelationshipList)).append("\n");
        sb.append("    relevanceKnowledgeGraphRelationshipList: ").append(toIndentedString(relevanceKnowledgeGraphRelationshipList)).append("\n");
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


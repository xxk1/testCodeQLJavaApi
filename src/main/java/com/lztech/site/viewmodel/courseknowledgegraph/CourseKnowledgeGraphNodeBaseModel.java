package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeBaseModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-31T03:06:00.478Z")


public class CourseKnowledgeGraphNodeBaseModel {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("knowledgeNodeName")
    private String knowledgeNodeName = null;

    @JsonProperty("knowledgeNodeLevelIndex")
    private Integer knowledgeNodeLevelIndex = null;

    @JsonProperty("knowledgeNodeLevelName")
    private String knowledgeNodeLevelName = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("sort")
    private Integer sort = null;

    public CourseKnowledgeGraphNodeBaseModel id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 知识节点id
     *
     * @return id
     **/
    @ApiModelProperty(value = "知识节点id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseKnowledgeGraphNodeBaseModel knowledgeNodeName(String knowledgeNodeName) {
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

    public CourseKnowledgeGraphNodeBaseModel knowledgeNodeLevelIndex(Integer knowledgeNodeLevelIndex) {
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

    public CourseKnowledgeGraphNodeBaseModel knowledgeNodeLevelName(String knowledgeNodeLevelName) {
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

    public CourseKnowledgeGraphNodeBaseModel courseKnowledgeGraphId(String courseKnowledgeGraphId) {
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

    public CourseKnowledgeGraphNodeBaseModel sort(Integer sort) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeBaseModel courseKnowledgeGraphNodeBaseModel = (CourseKnowledgeGraphNodeBaseModel) o;
        return Objects.equals(this.id, courseKnowledgeGraphNodeBaseModel.id) &&
                Objects.equals(this.knowledgeNodeName, courseKnowledgeGraphNodeBaseModel.knowledgeNodeName) &&
                Objects.equals(this.knowledgeNodeLevelIndex, courseKnowledgeGraphNodeBaseModel.knowledgeNodeLevelIndex) &&
                Objects.equals(this.knowledgeNodeLevelName, courseKnowledgeGraphNodeBaseModel.knowledgeNodeLevelName) &&
                Objects.equals(this.courseKnowledgeGraphId, courseKnowledgeGraphNodeBaseModel.courseKnowledgeGraphId) &&
                Objects.equals(this.sort, courseKnowledgeGraphNodeBaseModel.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, knowledgeNodeName, knowledgeNodeLevelIndex, knowledgeNodeLevelName,courseKnowledgeGraphId, sort);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeBaseModel {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    knowledgeNodeName: ").append(toIndentedString(knowledgeNodeName)).append("\n");
        sb.append("    knowledgeNodeLevelIndex: ").append(toIndentedString(knowledgeNodeLevelIndex)).append("\n");
        sb.append("    knowledgeNodeLevelName: ").append(toIndentedString(knowledgeNodeLevelName)).append("\n");
        sb.append("    courseKnowledgeGraphId: ").append(toIndentedString(courseKnowledgeGraphId)).append("\n");
        sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
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


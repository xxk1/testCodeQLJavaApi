package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeTreeModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-24T07:25:45.794Z")


public class CourseKnowledgeGraphNodeTreeModel {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("knowledgeNodeName")
    private String knowledgeNodeName = null;
    @JsonProperty("knowledgeNodeLevelIndex")
    private Integer knowledgeNodeLevelIndex = null;

    @JsonProperty("knowledgeNodeLevelName")
    private String knowledgeNodeLevelName = null;

    @JsonProperty("sort")
    private Integer sort = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId  = null;

    @JsonProperty("parentId")
    private String parentId;

    @JsonProperty("subsetCourseKnowledgeGraphNodeTreeList")
    @Valid
    private List<CourseKnowledgeGraphNodeTreeModel> subsetCourseKnowledgeGraphNodeTreeList = new ArrayList<>();

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public CourseKnowledgeGraphNodeTreeModel id(String id) {
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

    public CourseKnowledgeGraphNodeTreeModel knowledgeNodeName(String knowledgeNodeName) {
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

    public CourseKnowledgeGraphNodeTreeModel knowledgeNodeLevelIndex(Integer knowledgeNodeLevelIndex) {
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

    public CourseKnowledgeGraphNodeTreeModel knowledgeNodeLevelName(String knowledgeNodeLevelName) {
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

    public CourseKnowledgeGraphNodeTreeModel sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    /**
     * @return sort
     **/
    @ApiModelProperty(value = "")


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
        return this;
    }

    /**
     * 知识图谱id
     *
     * @return courseKnowledgeGraphId
     **/
    @ApiModelProperty(value = "知识图谱id")


    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }


    public CourseKnowledgeGraphNodeTreeModel subsetCourseKnowledgeGraphNodeTreeList(
            List<CourseKnowledgeGraphNodeTreeModel> subsetCourseKnowledgeGraphNodeTreeList) {
        this.subsetCourseKnowledgeGraphNodeTreeList = subsetCourseKnowledgeGraphNodeTreeList;
        return this;
    }

    public CourseKnowledgeGraphNodeTreeModel addSubsetCourseKnowledgeGraphNodeTreeListItem(
            CourseKnowledgeGraphNodeTreeModel subsetCourseKnowledgeGraphNodeTreeListItem) {
        if (this.subsetCourseKnowledgeGraphNodeTreeList == null) {
            this.subsetCourseKnowledgeGraphNodeTreeList = new ArrayList<CourseKnowledgeGraphNodeTreeModel>();
        }
        this.subsetCourseKnowledgeGraphNodeTreeList.add(subsetCourseKnowledgeGraphNodeTreeListItem);
        return this;
    }

    /**
     * Get subsetCourseKnowledgeGraphNodeTreeList
     *
     * @return subsetCourseKnowledgeGraphNodeTreeList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CourseKnowledgeGraphNodeTreeModel> getSubsetCourseKnowledgeGraphNodeTreeList() {
        return subsetCourseKnowledgeGraphNodeTreeList;
    }

    public void setSubsetCourseKnowledgeGraphNodeTreeList(
            List<CourseKnowledgeGraphNodeTreeModel> subsetCourseKnowledgeGraphNodeTreeList) {
        this.subsetCourseKnowledgeGraphNodeTreeList = subsetCourseKnowledgeGraphNodeTreeList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel = (CourseKnowledgeGraphNodeTreeModel) o;
        return Objects.equals(this.id, courseKnowledgeGraphNodeTreeModel.id) &&
                Objects.equals(this.knowledgeNodeName, courseKnowledgeGraphNodeTreeModel.knowledgeNodeName) &&
                Objects.equals(this.knowledgeNodeLevelIndex, courseKnowledgeGraphNodeTreeModel.knowledgeNodeLevelIndex) &&
                Objects.equals(this.knowledgeNodeLevelName, courseKnowledgeGraphNodeTreeModel.knowledgeNodeLevelName) &&
                Objects.equals(this.sort, courseKnowledgeGraphNodeTreeModel.sort) &&
                Objects.equals(this.courseKnowledgeGraphId, courseKnowledgeGraphNodeTreeModel.courseKnowledgeGraphId) &&
                Objects.equals(this.subsetCourseKnowledgeGraphNodeTreeList, courseKnowledgeGraphNodeTreeModel.subsetCourseKnowledgeGraphNodeTreeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, knowledgeNodeName, knowledgeNodeLevelIndex,
                knowledgeNodeLevelName, sort,courseKnowledgeGraphId, subsetCourseKnowledgeGraphNodeTreeList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeTreeModel {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    knowledgeNodeName: ").append(toIndentedString(knowledgeNodeName)).append("\n");
        sb.append("    knowledgeNodeLevelIndex: ").append(toIndentedString(knowledgeNodeLevelIndex)).append("\n");
        sb.append("    knowledgeNodeLevelName: ").append(toIndentedString(knowledgeNodeLevelName)).append("\n");
        sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
        sb.append("    courseKnowledgeGraphId: ").append(toIndentedString(courseKnowledgeGraphId)).append("\n");
        sb.append("    subsetCourseKnowledgeGraphNodeTreeList: ").append(toIndentedString(subsetCourseKnowledgeGraphNodeTreeList)).append("\n");
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

    public int getLevelCount() {
        if (subsetCourseKnowledgeGraphNodeTreeList.isEmpty()) {
            return 1; // 如果没有子节点，当前节点为叶子节点，层级为1
        } else {
            int maxChildLevel = 0;
            for (CourseKnowledgeGraphNodeTreeModel child : subsetCourseKnowledgeGraphNodeTreeList) {
                int childLevel = child.getLevelCount();
                if (childLevel > maxChildLevel) {
                    maxChildLevel = childLevel;
                }
            }
            return maxChildLevel + 1; // 当前节点的层级为子节点的最大层级加1
        }
    }
}


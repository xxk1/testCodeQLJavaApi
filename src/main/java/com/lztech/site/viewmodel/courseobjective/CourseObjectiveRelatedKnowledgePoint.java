package com.lztech.site.viewmodel.courseobjective;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseObjectiveRelatedKnowledgePoint
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-12-01T10:31:59.126+08:00")

public class CourseObjectiveRelatedKnowledgePoint {
    @JsonProperty("knowledgePointId")
    private String knowledgePointId = null;

    @JsonProperty("knowledgePointName")
    private String knowledgePointName = null;

    @JsonProperty("createTime")
    private String createTime = null;

    @JsonProperty("knowledgeNodeLevelIndex")
    private Integer knowledgeNodeLevelIndex = null;

    @JsonProperty("knowledgeNodeLevelName")
    private String knowledgeNodeLevelName = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("parentId")
    private String parentId = null;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }

    public Integer getKnowledgeNodeLevelIndex() {
        return knowledgeNodeLevelIndex;
    }

    public void setKnowledgeNodeLevelIndex(Integer knowledgeNodeLevelIndex) {
        this.knowledgeNodeLevelIndex = knowledgeNodeLevelIndex;
    }

    public String getKnowledgeNodeLevelName() {
        return knowledgeNodeLevelName;
    }

    public void setKnowledgeNodeLevelName(String knowledgeNodeLevelName) {
        this.knowledgeNodeLevelName = knowledgeNodeLevelName;
    }

    public CourseObjectiveRelatedKnowledgePoint knowledgePointId(String knowledgePointId) {
        this.knowledgePointId = knowledgePointId;
        return this;
    }

    /**
     * 知识点ID
     *
     * @return knowledgePointId
     **/
    @ApiModelProperty(value = "知识点ID")


    public String getKnowledgePointId() {
        return knowledgePointId;
    }

    public void setKnowledgePointId(String knowledgePointId) {
        this.knowledgePointId = knowledgePointId;
    }

    public CourseObjectiveRelatedKnowledgePoint knowledgePointName(String knowledgePointName) {
        this.knowledgePointName = knowledgePointName;
        return this;
    }

    /**
     * 知识点名称
     *
     * @return knowledgePointName
     **/
    @ApiModelProperty(value = "知识点名称")


    public String getKnowledgePointName() {
        return knowledgePointName;
    }

    public void setKnowledgePointName(String knowledgePointName) {
        this.knowledgePointName = knowledgePointName;
    }

    public CourseObjectiveRelatedKnowledgePoint createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 创建时间
     *
     * @return createTime
     **/
    @ApiModelProperty(value = "创建时间")


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseObjectiveRelatedKnowledgePoint courseObjectiveRelatedKnowledgePoint = (CourseObjectiveRelatedKnowledgePoint) o;
        return Objects.equals(this.knowledgePointId, courseObjectiveRelatedKnowledgePoint.knowledgePointId) &&
                Objects.equals(this.knowledgePointName, courseObjectiveRelatedKnowledgePoint.knowledgePointName) &&
                Objects.equals(this.createTime, courseObjectiveRelatedKnowledgePoint.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knowledgePointId, knowledgePointName, createTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseObjectiveRelatedKnowledgePoint {\n");

        sb.append("    knowledgePointId: ").append(toIndentedString(knowledgePointId)).append("\n");
        sb.append("    knowledgePointName: ").append(toIndentedString(knowledgePointName)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
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


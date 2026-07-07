package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-25T06:10:03.466Z")


public class CourseKnowledgeGraphNodeData {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("accessUserId")
    private String accessUserId = null;

    @JsonProperty("accessUserName")
    private String accessUserName = null;

    @JsonProperty("parentCourseKnowledgeGraphNodeId")
    private String parentCourseKnowledgeGraphNodeId = null;

    @JsonProperty("knowledgeNodeName")
    private String knowledgeNodeName = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    public CourseKnowledgeGraphNodeData courseId(String courseId) {
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

    public CourseKnowledgeGraphNodeData accessUserId(String accessUserId) {
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

    public CourseKnowledgeGraphNodeData accessUserName(String accessUserName) {
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

    public CourseKnowledgeGraphNodeData parentCourseKnowledgeGraphNodeId(String parentCourseKnowledgeGraphNodeId) {
        this.parentCourseKnowledgeGraphNodeId = parentCourseKnowledgeGraphNodeId;
        return this;
    }

    /**
     * 父课程知识图谱节点id
     *
     * @return parentCourseKnowledgeGraphNodeId
     **/
    @ApiModelProperty(value = "父课程知识图谱节点id")


    public String getParentCourseKnowledgeGraphNodeId() {
        return parentCourseKnowledgeGraphNodeId;
    }

    public void setParentCourseKnowledgeGraphNodeId(String parentCourseKnowledgeGraphNodeId) {
        this.parentCourseKnowledgeGraphNodeId = parentCourseKnowledgeGraphNodeId;
    }

    public CourseKnowledgeGraphNodeData knowledgeNodeName(String knowledgeNodeName) {
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

    public CourseKnowledgeGraphNodeData courseKnowledgeGraphId(String courseKnowledgeGraphId) {
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
        CourseKnowledgeGraphNodeData courseKnowledgeGraphNodeData = (CourseKnowledgeGraphNodeData) o;
        return Objects.equals(this.courseId, courseKnowledgeGraphNodeData.courseId) &&
                Objects.equals(this.accessUserId, courseKnowledgeGraphNodeData.accessUserId) &&
                Objects.equals(this.accessUserName, courseKnowledgeGraphNodeData.accessUserName) &&
                Objects.equals(this.parentCourseKnowledgeGraphNodeId, courseKnowledgeGraphNodeData.parentCourseKnowledgeGraphNodeId) &&
                Objects.equals(this.knowledgeNodeName, courseKnowledgeGraphNodeData.knowledgeNodeName) &&
                Objects.equals(this.courseKnowledgeGraphId, courseKnowledgeGraphNodeData.courseKnowledgeGraphId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, accessUserId, accessUserName, parentCourseKnowledgeGraphNodeId, knowledgeNodeName,courseKnowledgeGraphId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeData {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    accessUserId: ").append(toIndentedString(accessUserId)).append("\n");
        sb.append("    accessUserName: ").append(toIndentedString(accessUserName)).append("\n");
        sb.append("    parentCourseKnowledgeGraphNodeId: ").append(toIndentedString(parentCourseKnowledgeGraphNodeId)).append("\n");
        sb.append("    knowledgeNodeName: ").append(toIndentedString(knowledgeNodeName)).append("\n");
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


package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-26T07:33:02.038Z")


public class CourseKnowledgeGraphNodeParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseKnowledgeGraphNodeId")
    private String courseKnowledgeGraphNodeId = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("accessUserId")
    private String accessUserId = null;

    @JsonProperty("accessUserName")
    private String accessUserName = null;

    @JsonProperty("knowledgeNodeName")
    private String knowledgeNodeName = null;

    @JsonProperty("levelTypeIndex")
    private Integer levelTypeIndex = null;

    @JsonProperty("contentDetail")
    private String contentDetail = null;

    @JsonProperty("contentGenerationModeIndex")
    private Integer contentGenerationModeIndex = null;

    public CourseKnowledgeGraphNodeParam contentGenerationModeIndex(Integer contentGenerationModeIndex) {
        this.contentGenerationModeIndex = contentGenerationModeIndex;
        return this;
    }

    /**
     * 内容生成模式编号 0:手动录入 1:AI生成
     *
     * @return contentGenerationModeIndex
     **/
    @ApiModelProperty(value = "内容生成模式编号 0:手动录入 1:AI生成")


    public Integer getContentGenerationModeIndex() {
        return contentGenerationModeIndex;
    }

    public void setContentGenerationModeIndex(Integer contentGenerationModeIndex) {
        this.contentGenerationModeIndex = contentGenerationModeIndex;
    }


    public CourseKnowledgeGraphNodeParam courseId(String courseId) {
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

    public CourseKnowledgeGraphNodeParam courseKnowledgeGraphNodeId(String courseKnowledgeGraphNodeId) {
        this.courseKnowledgeGraphNodeId = courseKnowledgeGraphNodeId;
        return this;
    }

    /**
     * 课程知识图谱节点id
     *
     * @return courseKnowledgeGraphNodeId
     **/
    @ApiModelProperty(value = "课程知识图谱节点id")


    public String getCourseKnowledgeGraphNodeId() {
        return courseKnowledgeGraphNodeId;
    }

    public CourseKnowledgeGraphNodeParam courseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
        return this;
    }

    /**
     * 课程知识图谱d
     *
     * @return courseKnowledgeGraphId
     **/
    @ApiModelProperty(value = "课程知识图谱d")


    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphNodeId(String courseKnowledgeGraphNodeId) {
        this.courseKnowledgeGraphNodeId = courseKnowledgeGraphNodeId;
    }

    public CourseKnowledgeGraphNodeParam accessUserId(String accessUserId) {
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

    public CourseKnowledgeGraphNodeParam accessUserName(String accessUserName) {
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

    public CourseKnowledgeGraphNodeParam knowledgeNodeName(String knowledgeNodeName) {
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

    public CourseKnowledgeGraphNodeParam levelTypeIndex(Integer levelTypeIndex) {
        this.levelTypeIndex = levelTypeIndex;
        return this;
    }

    /**
     * 级别类型编号
     *
     * @return levelTypeIndex
     **/
    @ApiModelProperty(value = "级别类型编号")


    public Integer getLevelTypeIndex() {
        return levelTypeIndex;
    }

    public void setLevelTypeIndex(Integer levelTypeIndex) {
        this.levelTypeIndex = levelTypeIndex;
    }

    public CourseKnowledgeGraphNodeParam contentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
        return this;
    }

    /**
     * 内容详情
     *
     * @return contentDetail
     **/
    @ApiModelProperty(value = "内容详情")


    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeParam courseKnowledgeGraphNodeParam = (CourseKnowledgeGraphNodeParam) o;
        return Objects.equals(this.courseId, courseKnowledgeGraphNodeParam.courseId) &&
                Objects.equals(this.courseKnowledgeGraphNodeId, courseKnowledgeGraphNodeParam.courseKnowledgeGraphNodeId) &&
                Objects.equals(this.accessUserId, courseKnowledgeGraphNodeParam.accessUserId) &&
                Objects.equals(this.accessUserName, courseKnowledgeGraphNodeParam.accessUserName) &&
                Objects.equals(this.knowledgeNodeName, courseKnowledgeGraphNodeParam.knowledgeNodeName) &&
                Objects.equals(this.levelTypeIndex, courseKnowledgeGraphNodeParam.levelTypeIndex) &&
                Objects.equals(this.contentDetail, courseKnowledgeGraphNodeParam.contentDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseKnowledgeGraphNodeId, accessUserId, accessUserName, knowledgeNodeName, levelTypeIndex, contentDetail);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseKnowledgeGraphNodeId: ").append(toIndentedString(courseKnowledgeGraphNodeId)).append("\n");
        sb.append("    accessUserId: ").append(toIndentedString(accessUserId)).append("\n");
        sb.append("    accessUserName: ").append(toIndentedString(accessUserName)).append("\n");
        sb.append("    knowledgeNodeName: ").append(toIndentedString(knowledgeNodeName)).append("\n");
        sb.append("    levelTypeIndex: ").append(toIndentedString(levelTypeIndex)).append("\n");
        sb.append("    contentDetail: ").append(toIndentedString(contentDetail)).append("\n");
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


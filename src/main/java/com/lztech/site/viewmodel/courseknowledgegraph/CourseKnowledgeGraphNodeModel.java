package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseKnowledgeGraphNodeModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-26T06:02:40.105Z")


public class CourseKnowledgeGraphNodeModel {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("knowledgeNodeName")
    private String knowledgeNodeName = null;
    @JsonProperty("contentDetail")
    private String contentDetail = null;
    @JsonProperty("levelTypeList")
    @Valid
    private List<LevelTypeModel> levelTypeList = null;

    @JsonProperty("contentGenerationModeIndex")
    private Integer contentGenerationModeIndex = null;

    public CourseKnowledgeGraphNodeModel contentGenerationModeIndex(Integer contentGenerationModeIndex) {
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


    public CourseKnowledgeGraphNodeModel id(String id) {
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

    public CourseKnowledgeGraphNodeModel knowledgeNodeName(String knowledgeNodeName) {
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
    public CourseKnowledgeGraphNodeModel contentDetail(String contentDetail) {
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

    public CourseKnowledgeGraphNodeModel levelTypeList(List<LevelTypeModel> levelTypeList) {
        this.levelTypeList = levelTypeList;
        return this;
    }

    public CourseKnowledgeGraphNodeModel addLevelTypeListItem(LevelTypeModel levelTypeListItem) {
        if (this.levelTypeList == null) {
            this.levelTypeList = new ArrayList<LevelTypeModel>();
        }
        this.levelTypeList.add(levelTypeListItem);
        return this;
    }

    /**
     * 级别类型列表
     *
     * @return levelTypeList
     **/
    @ApiModelProperty(value = "级别类型列表")

    @Valid

    public List<LevelTypeModel> getLevelTypeList() {
        return levelTypeList;
    }

    public void setLevelTypeList(List<LevelTypeModel> levelTypeList) {
        this.levelTypeList = levelTypeList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseKnowledgeGraphNodeModel courseKnowledgeGraphNodeModel = (CourseKnowledgeGraphNodeModel) o;
        return Objects.equals(this.id, courseKnowledgeGraphNodeModel.id) &&
                Objects.equals(this.knowledgeNodeName, courseKnowledgeGraphNodeModel.knowledgeNodeName) &&
                Objects.equals(this.contentDetail,courseKnowledgeGraphNodeModel.contentDetail) &&
                Objects.equals(this.levelTypeList, courseKnowledgeGraphNodeModel.levelTypeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, knowledgeNodeName,contentDetail, levelTypeList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseKnowledgeGraphNodeModel {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    knowledgeNodeName: ").append(toIndentedString(knowledgeNodeName)).append("\n");
        sb.append("    contentDetail: ").append(toIndentedString(contentDetail)).append("\n");
        sb.append("    levelTypeList: ").append(toIndentedString(levelTypeList)).append("\n");
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


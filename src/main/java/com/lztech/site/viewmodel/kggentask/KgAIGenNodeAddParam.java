package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenNodeAddParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-04T09:25:23.340+08:00")

public class KgAIGenNodeAddParam {
    @JsonProperty("masterTaskId")
    private String masterTaskId = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("subTaskId")
    private String subTaskId = null;

    @JsonProperty("nodeName")
    private String nodeName = null;

    @JsonProperty("parentNodeId")
    private String parentNodeId = null;

    @JsonProperty("chapterContentId")
    private String chapterContentId = null;

    @JsonProperty("sort")
    private Integer sort = null;

    @JsonProperty("level")
    private Integer level = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public KgAIGenNodeAddParam masterTaskId(String masterTaskId) {
        this.masterTaskId = masterTaskId;
        return this;
    }

    /**
     * 主任务 id
     * @return masterTaskId
     **/
    @ApiModelProperty(value = "主任务 id")


    public String getMasterTaskId() {
        return masterTaskId;
    }

    public void setMasterTaskId(String masterTaskId) {
        this.masterTaskId = masterTaskId;
    }

    public KgAIGenNodeAddParam subTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
        return this;
    }

    /**
     * 子任务 id
     * @return subTaskId
     **/
    @ApiModelProperty(value = "子任务 id")


    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public KgAIGenNodeAddParam nodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    /**
     * 节点名称
     * @return nodeName
     **/
    @ApiModelProperty(value = "节点名称")


    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public KgAIGenNodeAddParam parentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
        return this;
    }

    /**
     * 父节点id
     * @return parentNodeId
     **/
    @ApiModelProperty(value = "父节点id")


    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public KgAIGenNodeAddParam chapterContentId(String chapterContentId) {
        this.chapterContentId = chapterContentId;
        return this;
    }

    /**
     * 章节内容id
     * @return chapterContentId
     **/
    @ApiModelProperty(value = "章节内容id")


    public String getChapterContentId() {
        return chapterContentId;
    }

    public void setChapterContentId(String chapterContentId) {
        this.chapterContentId = chapterContentId;
    }

    public KgAIGenNodeAddParam sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    /**
     * 排序
     * @return sort
     **/
    @ApiModelProperty(value = "排序")


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public KgAIGenNodeAddParam operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人id
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public KgAIGenNodeAddParam operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人名称
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人名称")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public KgAIGenNodeAddParam operatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
        return this;
    }

    /**
     * 操作人编号
     * @return operatorNo
     **/
    @ApiModelProperty(value = "操作人编号")


    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenNodeAddParam kgAIGenNodeAddParam = (KgAIGenNodeAddParam) o;
        return Objects.equals(this.masterTaskId, kgAIGenNodeAddParam.masterTaskId) &&
                Objects.equals(this.subTaskId, kgAIGenNodeAddParam.subTaskId) &&
                Objects.equals(this.nodeName, kgAIGenNodeAddParam.nodeName) &&
                Objects.equals(this.parentNodeId, kgAIGenNodeAddParam.parentNodeId) &&
                Objects.equals(this.chapterContentId, kgAIGenNodeAddParam.chapterContentId) &&
                Objects.equals(this.sort, kgAIGenNodeAddParam.sort) &&
                Objects.equals(this.operatorId, kgAIGenNodeAddParam.operatorId) &&
                Objects.equals(this.operatorName, kgAIGenNodeAddParam.operatorName) &&
                Objects.equals(this.operatorNo, kgAIGenNodeAddParam.operatorNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterTaskId, subTaskId, nodeName, parentNodeId, chapterContentId, sort, operatorId, operatorName, operatorNo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenNodeAddParam {\n");

        sb.append("    masterTaskId: ").append(toIndentedString(masterTaskId)).append("\n");
        sb.append("    subTaskId: ").append(toIndentedString(subTaskId)).append("\n");
        sb.append("    nodeName: ").append(toIndentedString(nodeName)).append("\n");
        sb.append("    parentNodeId: ").append(toIndentedString(parentNodeId)).append("\n");
        sb.append("    chapterContentId: ").append(toIndentedString(chapterContentId)).append("\n");
        sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


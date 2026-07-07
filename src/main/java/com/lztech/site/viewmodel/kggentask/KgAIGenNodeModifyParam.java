package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenNodeModifyParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-04T09:25:23.340+08:00")

public class KgAIGenNodeModifyParam {
    @JsonProperty("nodeId")
    private String nodeId = null;

    @JsonProperty("modifyType")
    private Integer modifyType = null;

    @JsonProperty("nodeContent")
    private String nodeContent = null;

    @JsonProperty("nodeName")
    private String nodeName = null;

    @JsonProperty("importantTag")
    private Integer importantTag = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    public KgAIGenNodeModifyParam nodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * 节点id
     *
     * @return nodeId
     **/
    @ApiModelProperty(value = "节点id")


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public KgAIGenNodeModifyParam modifyType(Integer modifyType) {
        this.modifyType = modifyType;
        return this;
    }

    /**
     * 修改类型(0:修改名称;1:修改描述;2:全部修改)
     *
     * @return modifyType
     **/
    @ApiModelProperty(value = "修改类型(0:修改名称;1:修改描述;2:修改重要层级)")


    public Integer getModifyType() {
        return modifyType;
    }

    public void setModifyType(Integer modifyType) {
        this.modifyType = modifyType;
    }

    public KgAIGenNodeModifyParam nodeContent(String nodeContent) {
        this.nodeContent = nodeContent;
        return this;
    }

    /**
     * 修改内容
     *
     * @return nodeContent
     **/
    @ApiModelProperty(value = "修改内容")


    public String getNodeContent() {
        return nodeContent;
    }

    public void setNodeContent(String nodeContent) {
        this.nodeContent = nodeContent;
    }

    public KgAIGenNodeModifyParam nodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    /**
     * 修改名称
     *
     * @return nodeName
     **/
    @ApiModelProperty(value = "修改名称")


    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public KgAIGenNodeModifyParam importantTag(Integer importantTag) {
        this.importantTag = importantTag;
        return this;
    }

    /**
     * 重要标签： 0：次要；1：一般；2：重要;3：极其重要
     *
     * @return importantTag
     **/
    @ApiModelProperty(value = "重要标签： 0：次要；1：一般；2：重要;3：极其重要")


    public Integer getImportantTag() {
        return importantTag;
    }

    public void setImportantTag(Integer importantTag) {
        this.importantTag = importantTag;
    }

    public KgAIGenNodeModifyParam operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人id
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public KgAIGenNodeModifyParam operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人名称
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人名称")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public KgAIGenNodeModifyParam operatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
        return this;
    }

    /**
     * 操作人编号
     *
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenNodeModifyParam kgAIGenNodeModifyParam = (KgAIGenNodeModifyParam) o;
        return Objects.equals(this.nodeId, kgAIGenNodeModifyParam.nodeId) &&
                Objects.equals(this.modifyType, kgAIGenNodeModifyParam.modifyType) &&
                Objects.equals(this.nodeContent, kgAIGenNodeModifyParam.nodeContent) &&
                Objects.equals(this.nodeName, kgAIGenNodeModifyParam.nodeName) &&
                Objects.equals(this.importantTag, kgAIGenNodeModifyParam.importantTag) &&
                Objects.equals(this.operatorId, kgAIGenNodeModifyParam.operatorId) &&
                Objects.equals(this.operatorName, kgAIGenNodeModifyParam.operatorName) &&
                Objects.equals(this.operatorNo, kgAIGenNodeModifyParam.operatorNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, modifyType, nodeContent, nodeName, importantTag, operatorId, operatorName, operatorNo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenNodeModifyParam {\n");

        sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
        sb.append("    modifyType: ").append(toIndentedString(modifyType)).append("\n");
        sb.append("    nodeContent: ").append(toIndentedString(nodeContent)).append("\n");
        sb.append("    nodeName: ").append(toIndentedString(nodeName)).append("\n");
        sb.append("    importantTag: ").append(toIndentedString(importantTag)).append("\n");
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
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


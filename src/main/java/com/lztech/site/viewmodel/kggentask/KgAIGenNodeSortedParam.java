package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * KgAIGenNodeSortedParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-04T09:25:23.340+08:00")

public class KgAIGenNodeSortedParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    @JsonProperty("nodeList")
    @Valid
    private List<KgAIGenNodeSortedParamNode> nodeList = null;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public KgAIGenNodeSortedParam operatorId(String operatorId) {
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

    public KgAIGenNodeSortedParam operatorName(String operatorName) {
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

    public KgAIGenNodeSortedParam operatorNo(String operatorNo) {
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

    public KgAIGenNodeSortedParam nodeList(List<KgAIGenNodeSortedParamNode> nodeList) {
        this.nodeList = nodeList;
        return this;
    }

    public KgAIGenNodeSortedParam addNodeListItem(KgAIGenNodeSortedParamNode nodeListItem) {
        if (this.nodeList == null) {
            this.nodeList = new ArrayList<KgAIGenNodeSortedParamNode>();
        }
        this.nodeList.add(nodeListItem);
        return this;
    }

    /**
     * Get nodeList
     *
     * @return nodeList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<KgAIGenNodeSortedParamNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<KgAIGenNodeSortedParamNode> nodeList) {
        this.nodeList = nodeList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenNodeSortedParam kgAIGenNodeSortedParam = (KgAIGenNodeSortedParam) o;
        return Objects.equals(this.operatorId, kgAIGenNodeSortedParam.operatorId) &&
                Objects.equals(this.operatorName, kgAIGenNodeSortedParam.operatorName) &&
                Objects.equals(this.operatorNo, kgAIGenNodeSortedParam.operatorNo) &&
                Objects.equals(this.nodeList, kgAIGenNodeSortedParam.nodeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatorId, operatorName, operatorNo, nodeList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenNodeSortedParam {\n");

        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("    nodeList: ").append(toIndentedString(nodeList)).append("\n");
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


package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenImportParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-01T13:55:59.230+08:00")

public class KgAIGenImportParam {
    @JsonProperty("masterTaskId")
    private String masterTaskId = null;

    @JsonProperty("subTaskId")
    private String subTaskId = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    public KgAIGenImportParam masterTaskId(String masterTaskId) {
        this.masterTaskId = masterTaskId;
        return this;
    }

    /**
     * 主任务 id
     *
     * @return masterTaskId
     **/
    @ApiModelProperty(value = "主任务 id")


    public String getMasterTaskId() {
        return masterTaskId;
    }

    public void setMasterTaskId(String masterTaskId) {
        this.masterTaskId = masterTaskId;
    }

    public KgAIGenImportParam subTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
        return this;
    }

    /**
     * 子任务 id
     *
     * @return subTaskId
     **/
    @ApiModelProperty(value = "子任务 id")


    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public KgAIGenImportParam courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程 id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程 id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public KgAIGenImportParam operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人 id
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人 id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public KgAIGenImportParam operatorName(String operatorName) {
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

    public KgAIGenImportParam operatorNo(String operatorNo) {
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
        KgAIGenImportParam kgAIGenImportParam = (KgAIGenImportParam) o;
        return Objects.equals(this.masterTaskId, kgAIGenImportParam.masterTaskId) &&
                Objects.equals(this.subTaskId, kgAIGenImportParam.subTaskId) &&
                Objects.equals(this.courseId, kgAIGenImportParam.courseId) &&
                Objects.equals(this.operatorId, kgAIGenImportParam.operatorId) &&
                Objects.equals(this.operatorName, kgAIGenImportParam.operatorName) &&
                Objects.equals(this.operatorNo, kgAIGenImportParam.operatorNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterTaskId, subTaskId, courseId, operatorId, operatorName, operatorNo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenImportParam {\n");

        sb.append("    masterTaskId: ").append(toIndentedString(masterTaskId)).append("\n");
        sb.append("    subTaskId: ").append(toIndentedString(subTaskId)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
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


package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenTeachingMaterialStatusModifyParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-24T15:17:22.858+08:00")

public class KgAIGenTeachingMaterialStatusModifyParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    @JsonProperty("masterTaskId")
    private String masterTaskId = null;

    @JsonProperty("subTaskId")
    private String subTaskId = null;

    @JsonProperty("operationType")
    private Integer operationType = null;

    public KgAIGenTeachingMaterialStatusModifyParam courseId(String courseId) {
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

    public KgAIGenTeachingMaterialStatusModifyParam operatorId(String operatorId) {
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

    public KgAIGenTeachingMaterialStatusModifyParam operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public KgAIGenTeachingMaterialStatusModifyParam operatorNo(String operatorNo) {
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

    public KgAIGenTeachingMaterialStatusModifyParam masterTaskId(String masterTaskId) {
        this.masterTaskId = masterTaskId;
        return this;
    }

    /**
     * 任务id
     *
     * @return masterTaskId
     **/
    @ApiModelProperty(value = "任务id")


    public String getMasterTaskId() {
        return masterTaskId;
    }

    public void setMasterTaskId(String masterTaskId) {
        this.masterTaskId = masterTaskId;
    }

    public KgAIGenTeachingMaterialStatusModifyParam subTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
        return this;
    }

    /**
     * 子任务id
     *
     * @return subTaskId
     **/
    @ApiModelProperty(value = "子任务id")


    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public KgAIGenTeachingMaterialStatusModifyParam operationType(Integer operationType) {
        this.operationType = operationType;
        return this;
    }

    /**
     * 操作类型 0:用户取消 1:用户跳过 2:用户放弃;3:重新生成
     *
     * @return operationType
     **/
    @ApiModelProperty(value = "操作类型 0:用户取消 1:用户跳过 2:用户放弃;3:重新生成")


    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenTeachingMaterialStatusModifyParam kgAIGenTeachingMaterialStatusModifyParam = (KgAIGenTeachingMaterialStatusModifyParam) o;
        return Objects.equals(this.courseId, kgAIGenTeachingMaterialStatusModifyParam.courseId) &&
                Objects.equals(this.operatorId, kgAIGenTeachingMaterialStatusModifyParam.operatorId) &&
                Objects.equals(this.operatorName, kgAIGenTeachingMaterialStatusModifyParam.operatorName) &&
                Objects.equals(this.operatorNo, kgAIGenTeachingMaterialStatusModifyParam.operatorNo) &&
                Objects.equals(this.masterTaskId, kgAIGenTeachingMaterialStatusModifyParam.masterTaskId) &&
                Objects.equals(this.subTaskId, kgAIGenTeachingMaterialStatusModifyParam.subTaskId) &&
                Objects.equals(this.operationType, kgAIGenTeachingMaterialStatusModifyParam.operationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, operatorId, operatorName, operatorNo, masterTaskId, subTaskId, operationType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenTeachingMaterialStatusModifyParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("    masterTaskId: ").append(toIndentedString(masterTaskId)).append("\n");
        sb.append("    subTaskId: ").append(toIndentedString(subTaskId)).append("\n");
        sb.append("    operationType: ").append(toIndentedString(operationType)).append("\n");
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


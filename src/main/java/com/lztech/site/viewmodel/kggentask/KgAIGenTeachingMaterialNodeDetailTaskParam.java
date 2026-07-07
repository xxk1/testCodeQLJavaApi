package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenTeachingMaterialNodeDetailTaskParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-25T10:47:52.748+08:00")

public class KgAIGenTeachingMaterialNodeDetailTaskParam {

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("masterTaskId")
    private String masterTaskId = null;

    @JsonProperty("stepCode")
    private Integer stepCode = null;

    @JsonProperty("operationType")
    private Integer operationType = null;

    @JsonProperty("operatorId")
    private String operatorId;

    @JsonProperty("operatorNo")
    private String operatorNo;

    @JsonProperty("operatorName")
    private String operatorName;


    public KgAIGenTeachingMaterialNodeDetailTaskParam courseId(String courseId) {
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

    public KgAIGenTeachingMaterialNodeDetailTaskParam masterTaskId(String masterTaskId) {
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


    public KgAIGenTeachingMaterialNodeDetailTaskParam stepCode(Integer stepCode) {
        this.stepCode = stepCode;
        return this;
    }

    /**
     * 步骤编号
     *
     * @return stepCode
     **/
    @ApiModelProperty(value = "步骤编号(0:节点生成;1:节点详情生成;2:资源关联;3:导入)")

    public Integer getStepCode() {
        return stepCode;
    }

    public void setStepCode(Integer stepCode) {
        this.stepCode = stepCode;
    }

    public KgAIGenTeachingMaterialNodeDetailTaskParam operationType(Integer operationType) {
        this.operationType = operationType;
        return this;
    }

    /**
     * 操作类型 0:用户跳过 1:生成
     *
     * @return operationType
     **/
    @ApiModelProperty(value = "操作类型 0:用户跳过 1:生成")


    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }


    public KgAIGenTeachingMaterialNodeDetailTaskParam operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }
    /**
     * 操作员ID
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作员ID")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public KgAIGenTeachingMaterialNodeDetailTaskParam operatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
        return this;
    }
    /**
     * 操作员工号
     * @return operatorNo
     **/
    @ApiModelProperty(value = "操作员工号")

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public KgAIGenTeachingMaterialNodeDetailTaskParam operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }
    /**
     * 操作员名称
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作员名称")

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenTeachingMaterialNodeDetailTaskParam kgAIGenTeachingMaterialNodeDetailTaskParam =
                (KgAIGenTeachingMaterialNodeDetailTaskParam) o;
        return Objects.equals(this.courseId, kgAIGenTeachingMaterialNodeDetailTaskParam.courseId) &&
                Objects.equals(this.masterTaskId, kgAIGenTeachingMaterialNodeDetailTaskParam.masterTaskId) &&
                Objects.equals(this.stepCode, kgAIGenTeachingMaterialNodeDetailTaskParam.stepCode) &&
                Objects.equals(this.operationType, kgAIGenTeachingMaterialNodeDetailTaskParam.operationType) &&
                Objects.equals(this.operatorId, kgAIGenTeachingMaterialNodeDetailTaskParam.operatorId) &&
                Objects.equals(this.operatorNo, kgAIGenTeachingMaterialNodeDetailTaskParam.operatorNo) &&
                Objects.equals(this.operatorName, kgAIGenTeachingMaterialNodeDetailTaskParam.operatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, masterTaskId,stepCode, operationType, operatorId,operatorNo,operatorName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenTeachingMaterialNodeDetailTaskParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    masterTaskId: ").append(toIndentedString(masterTaskId)).append("\n");
        sb.append("    stepCode: ").append(toIndentedString(stepCode)).append("\n");
        sb.append("    operationType: ").append(toIndentedString(operationType)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
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


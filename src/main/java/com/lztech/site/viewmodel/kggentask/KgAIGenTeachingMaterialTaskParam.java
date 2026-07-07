package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenTeachingMaterialTaskParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-31T14:50:52.722+08:00")

public class KgAIGenTeachingMaterialTaskParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("masterTaskId")
    private String masterTaskId = null;

    @JsonProperty("operationType")
    private Integer operationType = null;

    @JsonProperty("stepCode")
    private Integer stepCode = null;

    public KgAIGenTeachingMaterialTaskParam courseId(String courseId) {
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

    public KgAIGenTeachingMaterialTaskParam masterTaskId(String masterTaskId) {
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

    public KgAIGenTeachingMaterialTaskParam operationType(Integer operationType) {
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

    public KgAIGenTeachingMaterialTaskParam stepCode(Integer stepCode) {
        this.stepCode = stepCode;
        return this;
    }

    /**
     * 步骤类型 0:节点生成；1：节点详情生成；2：资源关联;3: 图谱导入
     *
     * @return stepCode
     **/
    @ApiModelProperty(value = "步骤类型 0:节点生成；1：节点详情生成；2：资源关联;3: 图谱导入")


    public Integer getStepCode() {
        return stepCode;
    }

    public void setStepCode(Integer stepCode) {
        this.stepCode = stepCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenTeachingMaterialTaskParam kgAIGenTeachingMaterialTaskParam = (KgAIGenTeachingMaterialTaskParam) o;
        return Objects.equals(this.courseId, kgAIGenTeachingMaterialTaskParam.courseId) &&
                Objects.equals(this.masterTaskId, kgAIGenTeachingMaterialTaskParam.masterTaskId) &&
                Objects.equals(this.operationType, kgAIGenTeachingMaterialTaskParam.operationType) &&
                Objects.equals(this.stepCode, kgAIGenTeachingMaterialTaskParam.stepCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, masterTaskId, operationType, stepCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenTeachingMaterialTaskParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    masterTaskId: ").append(toIndentedString(masterTaskId)).append("\n");
        sb.append("    operationType: ").append(toIndentedString(operationType)).append("\n");
        sb.append("    stepCode: ").append(toIndentedString(stepCode)).append("\n");
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


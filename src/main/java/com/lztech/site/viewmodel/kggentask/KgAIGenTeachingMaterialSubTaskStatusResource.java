package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * KgAIGenTeachingMaterialSubTaskStatusResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-23T14:38:09.736+08:00")

public class KgAIGenTeachingMaterialSubTaskStatusResource {
    @JsonProperty("subTaskId")
    private String subTaskId = null;

    @JsonProperty("kgGenTaskStepCode")
    private Integer kgGenTaskStepCode = null;

    @JsonProperty("subTaskStatus")
    private Integer subTaskStatus = null;

    @JsonProperty("remark")
    private String remark = null;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public KgAIGenTeachingMaterialSubTaskStatusResource subTaskId(String subTaskId) {
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

    public KgAIGenTeachingMaterialSubTaskStatusResource kgGenTaskStepCode(Integer kgGenTaskStepCode) {
        this.kgGenTaskStepCode = kgGenTaskStepCode;
        return this;
    }

    /**
     * 步骤类型 0:节点生成；1：节点详情生成；2：资源关联;3: 图谱导入
     *
     * @return kgGenTaskStepCode
     **/
    @ApiModelProperty(value = "步骤类型 0:节点生成；1：节点详情生成；2：资源关联;3: 图谱导入")


    public Integer getKgGenTaskStepCode() {
        return kgGenTaskStepCode;
    }

    public void setKgGenTaskStepCode(Integer kgGenTaskStepCode) {
        this.kgGenTaskStepCode = kgGenTaskStepCode;
    }

    public KgAIGenTeachingMaterialSubTaskStatusResource subTaskStatus(Integer subTaskStatus) {
        this.subTaskStatus = subTaskStatus;
        return this;
    }

    /**
     * 子任务状态 0:未开始 1:进行中 2:已完成 3:失败;4:部分成功;5: 用户放弃；6:用户跳过
     *
     * @return subTaskStatus
     **/
    @ApiModelProperty(value = "子任务状态 0:未开始 1:进行中 2:已完成 3:失败;4:部分成功;5: 用户放弃；6:用户跳过")


    public Integer getSubTaskStatus() {
        return subTaskStatus;
    }

    public void setSubTaskStatus(Integer subTaskStatus) {
        this.subTaskStatus = subTaskStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenTeachingMaterialSubTaskStatusResource kgAIGenTeachingMaterialSubTaskStatusResource = (KgAIGenTeachingMaterialSubTaskStatusResource) o;
        return Objects.equals(this.subTaskId, kgAIGenTeachingMaterialSubTaskStatusResource.subTaskId) &&
                Objects.equals(this.kgGenTaskStepCode, kgAIGenTeachingMaterialSubTaskStatusResource.kgGenTaskStepCode) &&
                Objects.equals(this.subTaskStatus, kgAIGenTeachingMaterialSubTaskStatusResource.subTaskStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subTaskId, kgGenTaskStepCode, subTaskStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenTeachingMaterialSubTaskStatusResource {\n");

        sb.append("    subTaskId: ").append(toIndentedString(subTaskId)).append("\n");
        sb.append("    kgGenTaskStepCode: ").append(toIndentedString(kgGenTaskStepCode)).append("\n");
        sb.append("    subTaskStatus: ").append(toIndentedString(subTaskStatus)).append("\n");
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


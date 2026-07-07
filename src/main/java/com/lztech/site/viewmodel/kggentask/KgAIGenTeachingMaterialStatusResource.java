package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * KgAIGenTeachingMaterialStatusResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-23T14:03:09.153+08:00")

public class KgAIGenTeachingMaterialStatusResource {
    @JsonProperty("masterTaskId")
    private String masterTaskId = null;

    @JsonProperty("masterTaskStatus")
    private Integer masterTaskStatus = null;

    @JsonProperty("subTaskStatusList")
    @Valid
    private List<KgAIGenTeachingMaterialSubTaskStatusResource> subTaskStatusList = null;

    public KgAIGenTeachingMaterialStatusResource masterTaskId(String masterTaskId) {
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

    public KgAIGenTeachingMaterialStatusResource masterTaskStatus(Integer masterTaskStatus) {
        this.masterTaskStatus = masterTaskStatus;
        return this;
    }

    /**
     * 任务状态 0:未开始 1:进行中 2:已完成 3:失败;4:部分成功；5：用户取消
     *
     * @return masterTaskStatus
     **/
    @ApiModelProperty(value = "任务状态 0:未开始 1:进行中 2:已完成 3:失败;4:部分成功；5：用户取消")


    public Integer getMasterTaskStatus() {
        return masterTaskStatus;
    }

    public void setMasterTaskStatus(Integer masterTaskStatus) {
        this.masterTaskStatus = masterTaskStatus;
    }

    public KgAIGenTeachingMaterialStatusResource subTaskStatusList(List<KgAIGenTeachingMaterialSubTaskStatusResource> subTaskStatusList) {
        this.subTaskStatusList = subTaskStatusList;
        return this;
    }

    public KgAIGenTeachingMaterialStatusResource addSubTaskStatusListItem(KgAIGenTeachingMaterialSubTaskStatusResource subTaskStatusListItem) {
        if (this.subTaskStatusList == null) {
            this.subTaskStatusList = new ArrayList<KgAIGenTeachingMaterialSubTaskStatusResource>();
        }
        this.subTaskStatusList.add(subTaskStatusListItem);
        return this;
    }

    /**
     * Get subTaskStatusList
     *
     * @return subTaskStatusList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<KgAIGenTeachingMaterialSubTaskStatusResource> getSubTaskStatusList() {
        return subTaskStatusList;
    }

    public void setSubTaskStatusList(List<KgAIGenTeachingMaterialSubTaskStatusResource> subTaskStatusList) {
        this.subTaskStatusList = subTaskStatusList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenTeachingMaterialStatusResource kgAIGenTeachingMaterialStatusResource = (KgAIGenTeachingMaterialStatusResource) o;
        return Objects.equals(this.masterTaskId, kgAIGenTeachingMaterialStatusResource.masterTaskId) &&
                Objects.equals(this.masterTaskStatus, kgAIGenTeachingMaterialStatusResource.masterTaskStatus) &&
                Objects.equals(this.subTaskStatusList, kgAIGenTeachingMaterialStatusResource.subTaskStatusList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterTaskId, masterTaskStatus, subTaskStatusList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenTeachingMaterialStatusResource {\n");

        sb.append("    masterTaskId: ").append(toIndentedString(masterTaskId)).append("\n");
        sb.append("    masterTaskStatus: ").append(toIndentedString(masterTaskStatus)).append("\n");
        sb.append("    subTaskStatusList: ").append(toIndentedString(subTaskStatusList)).append("\n");
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


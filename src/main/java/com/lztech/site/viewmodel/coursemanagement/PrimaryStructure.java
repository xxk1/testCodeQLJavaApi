package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PrimaryStructure
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-20T01:55:07.822Z")


public class PrimaryStructure {
    @JsonProperty("structureId")
    private String structureId = null;

    @JsonProperty("structureName")
    private String structureName = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    @JsonProperty("structureStatus")
    private Integer structureStatus = null;

    public PrimaryStructure structureId(String structureId) {
        this.structureId = structureId;
        return this;
    }

    /**
     * 课程结构id
     *
     * @return structureId
     **/
    @ApiModelProperty(value = "课程结构id")


    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public PrimaryStructure structureName(String structureName) {
        this.structureName = structureName;
        return this;
    }

    /**
     * 课程结构名称
     *
     * @return structureName
     **/
    @ApiModelProperty(value = "课程结构名称")


    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public PrimaryStructure showOrder(Integer showOrder) {
        this.showOrder = showOrder;
        return this;
    }

    /**
     * 排序字段
     *
     * @return showOrder
     **/
    @ApiModelProperty(value = "排序字段")


    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }


    public PrimaryStructure structureStatus(Integer structureStatus) {
        this.structureStatus = structureStatus;
        return this;
    }

    /**
     * 课程结构id
     *
     * @return structureStatus
     **/
    @ApiModelProperty(value = "课程结构状态")


    public Integer getStructureStatus() {
        return structureStatus;
    }

    public void setStructureStatus(Integer structureStatus) {
        this.structureStatus = structureStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrimaryStructure primaryStructure = (PrimaryStructure) o;
        return Objects.equals(this.structureId, primaryStructure.structureId) &&
                Objects.equals(this.structureName, primaryStructure.structureName) &&
                Objects.equals(this.structureStatus, primaryStructure.structureStatus) &&
                Objects.equals(this.showOrder, primaryStructure.showOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId, structureName, structureStatus,showOrder);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PrimaryStructure {\n");

        sb.append("    structureId: ").append(toIndentedString(structureId)).append("\n");
        sb.append("    structureName: ").append(toIndentedString(structureName)).append("\n");
        sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
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


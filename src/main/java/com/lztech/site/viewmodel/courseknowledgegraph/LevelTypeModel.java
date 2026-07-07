package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * LevelTypeModel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-01-26T06:02:40.105Z")


public class LevelTypeModel {
    @JsonProperty("levelTypeIndex")
    private Integer levelTypeIndex = null;

    @JsonProperty("knowledgeNodeName")
    private String knowledgeNodeName = null;



    @JsonProperty("select")
    private Boolean select = null;

    public LevelTypeModel levelTypeIndex(Integer levelTypeIndex) {
        this.levelTypeIndex = levelTypeIndex;
        return this;
    }

    /**
     * 级别类型编号
     *
     * @return levelTypeIndex
     **/
    @ApiModelProperty(value = "级别类型编号")


    public Integer getLevelTypeIndex() {
        return levelTypeIndex;
    }

    public void setLevelTypeIndex(Integer levelTypeIndex) {
        this.levelTypeIndex = levelTypeIndex;
    }

    public LevelTypeModel knowledgeNodeName(String knowledgeNodeName) {
        this.knowledgeNodeName = knowledgeNodeName;
        return this;
    }

    /**
     * 级别类型名称
     *
     * @return knowledgeNodeName
     **/
    @ApiModelProperty(value = "级别类型名称")


    public String getKnowledgeNodeName() {
        return knowledgeNodeName;
    }

    public void setKnowledgeNodeName(String knowledgeNodeName) {
        this.knowledgeNodeName = knowledgeNodeName;
    }


    public LevelTypeModel select(Boolean select) {
        this.select = select;
        return this;
    }

    /**
     * 选中状态
     *
     * @return select
     **/
    @ApiModelProperty(value = "选中状态")


    public Boolean isSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LevelTypeModel levelTypeModel = (LevelTypeModel) o;
        return Objects.equals(this.levelTypeIndex, levelTypeModel.levelTypeIndex) &&
                Objects.equals(this.knowledgeNodeName, levelTypeModel.knowledgeNodeName) &&
                Objects.equals(this.select, levelTypeModel.select);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelTypeIndex, knowledgeNodeName, select);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LevelTypeModel {\n");

        sb.append("    levelTypeIndex: ").append(toIndentedString(levelTypeIndex)).append("\n");
        sb.append("    knowledgeNodeName: ").append(toIndentedString(knowledgeNodeName)).append("\n");
        sb.append("    select: ").append(toIndentedString(select)).append("\n");
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


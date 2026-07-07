package com.lztech.site.viewmodel.classgroupingscheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassGroupingSchemeVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-23T05:58:18.497Z")


public class ClassGroupingSchemeVo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("schemeName")
    private String schemeName = null;

    @JsonProperty("schemePresentation")
    private String schemePresentation = null;

    @JsonProperty("schemeType")
    private Integer schemeType = null;

    @JsonProperty("groupedHeadCount")
    private Integer groupedHeadCount = null;

    @JsonProperty("classGroupingCount")
    private Integer classGroupingCount = null;

    @JsonProperty("ungroupedHeadCount")
    private Integer ungroupedHeadCount = null;

    public ClassGroupingSchemeVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 班级分组方案Id
     * @return id
     **/
    @ApiModelProperty(value = "班级分组方案Id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClassGroupingSchemeVo schemeName(String schemeName) {
        this.schemeName = schemeName;
        return this;
    }

    /**
     * 方案名称
     * @return schemeName
     **/
    @ApiModelProperty(value = "方案名称")


    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public ClassGroupingSchemeVo schemePresentation(String schemePresentation) {
        this.schemePresentation = schemePresentation;
        return this;
    }

    /**
     * 方案描述
     * @return schemePresentation
     **/
    @ApiModelProperty(value = "方案描述")


    public String getSchemePresentation() {
        return schemePresentation;
    }

    public void setSchemePresentation(String schemePresentation) {
        this.schemePresentation = schemePresentation;
    }

    public ClassGroupingSchemeVo schemeType(Integer schemeType) {
        this.schemeType = schemeType;
        return this;
    }

    /**
     * 方案类型 0：普通小组方案；1：实验小组方案
     * @return schemeType
     **/
    @ApiModelProperty(value = "方案类型 0：普通小组方案；1：实验小组方案")


    public Integer getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(Integer schemeType) {
        this.schemeType = schemeType;
    }

    public ClassGroupingSchemeVo groupedHeadCount(Integer groupedHeadCount) {
        this.groupedHeadCount = groupedHeadCount;
        return this;
    }

    /**
     * 已分组总人数
     * @return groupedHeadCount
     **/
    @ApiModelProperty(value = "已分组总人数")


    public Integer getGroupedHeadCount() {
        return groupedHeadCount;
    }

    public void setGroupedHeadCount(Integer groupedHeadCount) {
        this.groupedHeadCount = groupedHeadCount;
    }

    public ClassGroupingSchemeVo classGroupingCount(Integer classGroupingCount) {
        this.classGroupingCount = classGroupingCount;
        return this;
    }

    /**
     * 班级分组总数
     * @return classGroupingCount
     **/
    @ApiModelProperty(value = "班级分组总数")


    public Integer getClassGroupingCount() {
        return classGroupingCount;
    }

    public void setClassGroupingCount(Integer classGroupingCount) {
        this.classGroupingCount = classGroupingCount;
    }

    public ClassGroupingSchemeVo ungroupedHeadCount(Integer ungroupedHeadCount) {
        this.ungroupedHeadCount = ungroupedHeadCount;
        return this;
    }

    /**
     * 未分组总人数
     * @return ungroupedHeadCount
     **/
    @ApiModelProperty(value = "未分组总人数")


    public Integer getUngroupedHeadCount() {
        return ungroupedHeadCount;
    }

    public void setUngroupedHeadCount(Integer ungroupedHeadCount) {
        this.ungroupedHeadCount = ungroupedHeadCount;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassGroupingSchemeVo classGroupingSchemeVo = (ClassGroupingSchemeVo) o;
        return Objects.equals(this.id, classGroupingSchemeVo.id) &&
                Objects.equals(this.schemeName, classGroupingSchemeVo.schemeName) &&
                Objects.equals(this.schemePresentation, classGroupingSchemeVo.schemePresentation) &&
                Objects.equals(this.schemeType, classGroupingSchemeVo.schemeType) &&
                Objects.equals(this.groupedHeadCount, classGroupingSchemeVo.groupedHeadCount) &&
                Objects.equals(this.classGroupingCount, classGroupingSchemeVo.classGroupingCount) &&
                Objects.equals(this.ungroupedHeadCount, classGroupingSchemeVo.ungroupedHeadCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schemeName, schemePresentation, schemeType, groupedHeadCount, classGroupingCount, ungroupedHeadCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassGroupingSchemeVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    schemeName: ").append(toIndentedString(schemeName)).append("\n");
        sb.append("    schemePresentation: ").append(toIndentedString(schemePresentation)).append("\n");
        sb.append("    schemeType: ").append(toIndentedString(schemeType)).append("\n");
        sb.append("    groupedHeadCount: ").append(toIndentedString(groupedHeadCount)).append("\n");
        sb.append("    classGroupingCount: ").append(toIndentedString(classGroupingCount)).append("\n");
        sb.append("    ungroupedHeadCount: ").append(toIndentedString(ungroupedHeadCount)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


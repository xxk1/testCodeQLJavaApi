package com.lztech.site.viewmodel.classgroupingscheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassGroupingSchemeResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-23T11:28:22.247Z")


public class ClassGroupingSchemeResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("schemeName")
    private String schemeName = null;

    @JsonProperty("schemeDescription")
    private String schemeDescription = null;

    @JsonProperty("schemaType")
    private Integer schemaType = null;

    @JsonProperty("classGroupingList")
    @Valid
    private List<ClassGroupingResource> classGroupingList = null;

    public ClassGroupingSchemeResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 班级方案id
     * @return id
     **/
    @ApiModelProperty(value = "班级方案id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClassGroupingSchemeResource schemeName(String schemeName) {
        this.schemeName = schemeName;
        return this;
    }

    /**
     * 班级方案名称
     * @return schemeName
     **/
    @ApiModelProperty(value = "班级方案名称")


    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public ClassGroupingSchemeResource schemeDescription(String schemeDescription) {
        this.schemeDescription = schemeDescription;
        return this;
    }

    /**
     * 班级方案描述
     * @return schemeDescription
     **/
    @ApiModelProperty(value = "班级方案描述")


    public String getSchemeDescription() {
        return schemeDescription;
    }

    public void setSchemeDescription(String schemeDescription) {
        this.schemeDescription = schemeDescription;
    }

    public ClassGroupingSchemeResource schemaType(Integer schemaType) {
        this.schemaType = schemaType;
        return this;
    }

    /**
     * 方案类型 0：普通小组方案；1：实验小组方案
     * @return schemaType
     **/
    @ApiModelProperty(value = "方案类型 0：普通小组方案；1：实验小组方案")


    public Integer getSchemaType() {
        return schemaType;
    }

    public void setSchemaType(Integer schemaType) {
        this.schemaType = schemaType;
    }

    public ClassGroupingSchemeResource classGroupingList(List<ClassGroupingResource> classGroupingList) {
        this.classGroupingList = classGroupingList;
        return this;
    }

    public ClassGroupingSchemeResource addClassGroupingListItem(ClassGroupingResource classGroupingListItem) {
        if (this.classGroupingList == null) {
            this.classGroupingList = new ArrayList<ClassGroupingResource>();
        }
        this.classGroupingList.add(classGroupingListItem);
        return this;
    }

    /**
     * Get classGroupingList
     * @return classGroupingList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ClassGroupingResource> getClassGroupingList() {
        return classGroupingList;
    }

    public void setClassGroupingList(List<ClassGroupingResource> classGroupingList) {
        this.classGroupingList = classGroupingList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassGroupingSchemeResource classGroupingSchemeResource = (ClassGroupingSchemeResource) o;
        return Objects.equals(this.id, classGroupingSchemeResource.id) &&
                Objects.equals(this.schemeName, classGroupingSchemeResource.schemeName) &&
                Objects.equals(this.schemeDescription, classGroupingSchemeResource.schemeDescription) &&
                Objects.equals(this.schemaType, classGroupingSchemeResource.schemaType) &&
                Objects.equals(this.classGroupingList, classGroupingSchemeResource.classGroupingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schemeName, schemeDescription, schemaType, classGroupingList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassGroupingSchemeResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    schemeName: ").append(toIndentedString(schemeName)).append("\n");
        sb.append("    schemeDescription: ").append(toIndentedString(schemeDescription)).append("\n");
        sb.append("    schemaType: ").append(toIndentedString(schemaType)).append("\n");
        sb.append("    classGroupingList: ").append(toIndentedString(classGroupingList)).append("\n");
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


package com.lztech.site.viewmodel.classgroupingscheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassGroupingschemeInsertResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-23T06:48:13.056Z")

public class ClassGroupingSchemeInsertResource {

    @JsonProperty("schemeId")
    private String schemeId = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("schemeName")
    private String schemeName = null;

    @JsonProperty("schemeDescription")
    private String schemeDescription = null;

    @JsonProperty("schemaType")
    private Integer schemaType = null;

    @JsonProperty("classGroupList")
    @Valid
    private List<ClassGroupingInsertResource> classGroupList = new ArrayList<>();

    public Integer getSchemaType() {
        return schemaType;
    }

    public void setSchemaType(Integer schemaType) {
        this.schemaType = schemaType;
    }

    public ClassGroupingSchemeInsertResource schemeId(String schemeId) {
        this.schemeId = schemeId;
        return this;
    }

    /**
     * 方案id
     *
     * @return schemeId
     **/
    @ApiModelProperty(value = "方案id")


    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public ClassGroupingSchemeInsertResource operatorId(String operatorId) {
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

    public ClassGroupingSchemeInsertResource operatorName(String operatorName) {
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

    public ClassGroupingSchemeInsertResource schemeName(String schemeName) {
        this.schemeName = schemeName;
        return this;
    }

    /**
     * 方案名称
     *
     * @return schemeName
     **/
    @ApiModelProperty(value = "方案名称")


    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public ClassGroupingSchemeInsertResource schemeDescription(String schemeDescription) {
        this.schemeDescription = schemeDescription;
        return this;
    }

    /**
     * 方案描述
     *
     * @return schemeDescription
     **/
    @ApiModelProperty(value = "方案描述")


    public String getSchemeDescription() {
        return schemeDescription;
    }

    public void setSchemeDescription(String schemeDescription) {
        this.schemeDescription = schemeDescription;
    }

    public ClassGroupingSchemeInsertResource classGroupList(List<ClassGroupingInsertResource> classGroupList) {
        this.classGroupList = classGroupList;
        return this;
    }

    public ClassGroupingSchemeInsertResource addClassGroupListItem(ClassGroupingInsertResource classGroupListItem) {
        if (this.classGroupList == null) {
            this.classGroupList = new ArrayList<ClassGroupingInsertResource>();
        }
        this.classGroupList.add(classGroupListItem);
        return this;
    }

    /**
     * Get classGroupList
     *
     * @return classGroupList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ClassGroupingInsertResource> getClassGroupList() {
        return classGroupList;
    }

    public void setClassGroupList(List<ClassGroupingInsertResource> classGroupList) {
        this.classGroupList = classGroupList;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassGroupingSchemeInsertResource classGroupingSchemeInsertResource = (ClassGroupingSchemeInsertResource) o;
        return Objects.equals(this.schemeId, classGroupingSchemeInsertResource.schemeId) &&
                Objects.equals(this.operatorId, classGroupingSchemeInsertResource.operatorId) &&
                Objects.equals(this.operatorName, classGroupingSchemeInsertResource.operatorName) &&
                Objects.equals(this.schemeName, classGroupingSchemeInsertResource.schemeName) &&
                Objects.equals(this.schemeDescription, classGroupingSchemeInsertResource.schemeDescription) &&
                Objects.equals(this.classGroupList, classGroupingSchemeInsertResource.classGroupList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeId, operatorId, operatorName,
                schemeName, schemeDescription, classGroupList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClassGroupingSchemeInsertResource {\n");

        sb.append("    schemeId: ").append(toIndentedString(schemeId)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    schemeName: ").append(toIndentedString(schemeName)).append("\n");
        sb.append("    schemeDescription: ").append(toIndentedString(schemeDescription)).append("\n");
        sb.append("    classGroupList: ").append(toIndentedString(classGroupList)).append("\n");
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


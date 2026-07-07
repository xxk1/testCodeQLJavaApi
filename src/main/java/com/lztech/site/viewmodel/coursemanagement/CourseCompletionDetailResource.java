package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseCompletionDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-28T11:01:40.572Z")


public class CourseCompletionDetailResource {
    @JsonProperty("contentTypeValue")
    private String contentTypeValue = null;

    @JsonProperty("contentTypeName")
    private String contentTypeName = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    @JsonProperty("typeParentName")
    private String typeParentName = null;

    @JsonProperty("isCompleted")
    private Boolean isCompleted = null;

    public CourseCompletionDetailResource contentTypeValue(String contentTypeValue) {
        this.contentTypeValue = contentTypeValue;
        return this;
    }

    /**
     * 完成度类型值
     *
     * @return contentTypeValue
     **/
    @ApiModelProperty(value = "完成度类型值")


    public String getContentTypeValue() {
        return contentTypeValue;
    }

    public void setContentTypeValue(String contentTypeValue) {
        this.contentTypeValue = contentTypeValue;
    }

    public CourseCompletionDetailResource contentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
        return this;
    }

    /**
     * 完成度类型名称
     *
     * @return contentTypeName
     **/
    @ApiModelProperty(value = "完成度类型名称")


    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public CourseCompletionDetailResource showOrder(Integer showOrder) {
        this.showOrder = showOrder;
        return this;
    }

    /**
     * 排序
     *
     * @return showOrder
     **/
    @ApiModelProperty(value = "排序")


    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public CourseCompletionDetailResource typeParentName(String typeParentName) {
        this.typeParentName = typeParentName;
        return this;
    }

    /**
     * 类型父级名称
     *
     * @return typeParentName
     **/
    @ApiModelProperty(value = "类型父级名称")


    public String getTypeParentName() {
        return typeParentName;
    }

    public void setTypeParentName(String typeParentName) {
        this.typeParentName = typeParentName;
    }

    public CourseCompletionDetailResource isCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
        return this;
    }

    /**
     * 是否完成
     *
     * @return isCompleted
     **/
    @ApiModelProperty(value = "是否完成")


    public Boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseCompletionDetailResource courseCompletionDetailResource = (CourseCompletionDetailResource) o;
        return Objects.equals(this.contentTypeValue, courseCompletionDetailResource.contentTypeValue) &&
                Objects.equals(this.contentTypeName, courseCompletionDetailResource.contentTypeName) &&
                Objects.equals(this.showOrder, courseCompletionDetailResource.showOrder) &&
                Objects.equals(this.typeParentName, courseCompletionDetailResource.typeParentName) &&
                Objects.equals(this.isCompleted, courseCompletionDetailResource.isCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentTypeValue, contentTypeName, showOrder, typeParentName, isCompleted);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseCompletionDetailResource {\n");

        sb.append("    contentTypeValue: ").append(toIndentedString(contentTypeValue)).append("\n");
        sb.append("    contentTypeName: ").append(toIndentedString(contentTypeName)).append("\n");
        sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
        sb.append("    typeParentName: ").append(toIndentedString(typeParentName)).append("\n");
        sb.append("    isCompleted: ").append(toIndentedString(isCompleted)).append("\n");
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


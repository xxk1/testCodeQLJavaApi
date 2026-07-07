package com.lztech.site.viewmodel.courseconstruction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseChapterGoalTypeResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-24T09:15:35.278Z")


public class CourseChapterGoalTypeResource {
    @JsonProperty("typeName")
    private String typeName = null;

    @JsonProperty("typeValue")
    private Integer typeValue = null;

    @JsonProperty("parentName")
    private String parentName = null;

    @JsonProperty("isShow")
    private Boolean isShow = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    public CourseChapterGoalTypeResource typeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    /**
     * 类型名称
     *
     * @return typeName
     **/
    @ApiModelProperty(value = "类型名称")


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public CourseChapterGoalTypeResource typeValue(Integer typeValue) {
        this.typeValue = typeValue;
        return this;
    }

    /**
     * 类型值
     *
     * @return typeValue
     **/
    @ApiModelProperty(value = "类型值")


    public Integer getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(Integer typeValue) {
        this.typeValue = typeValue;
    }

    public CourseChapterGoalTypeResource parentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    /**
     * 父级类型名称
     *
     * @return parentName
     **/
    @ApiModelProperty(value = "父级类型名称")


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public CourseChapterGoalTypeResource isShow(Boolean isShow) {
        this.isShow = isShow;
        return this;
    }

    /**
     * 是否展示
     *
     * @return isShow
     **/
    @ApiModelProperty(value = "是否展示")


    public Boolean isIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public CourseChapterGoalTypeResource showOrder(Integer showOrder) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseChapterGoalTypeResource courseChapterGoalTypeResource = (CourseChapterGoalTypeResource) o;
        return Objects.equals(this.typeName, courseChapterGoalTypeResource.typeName) &&
                Objects.equals(this.typeValue, courseChapterGoalTypeResource.typeValue) &&
                Objects.equals(this.parentName, courseChapterGoalTypeResource.parentName) &&
                Objects.equals(this.isShow, courseChapterGoalTypeResource.isShow) &&
                Objects.equals(this.showOrder, courseChapterGoalTypeResource.showOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, typeValue, parentName, isShow, showOrder);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseChapterGoalTypeResource {\n");

        sb.append("    typeName: ").append(toIndentedString(typeName)).append("\n");
        sb.append("    typeValue: ").append(toIndentedString(typeValue)).append("\n");
        sb.append("    parentName: ").append(toIndentedString(parentName)).append("\n");
        sb.append("    isShow: ").append(toIndentedString(isShow)).append("\n");
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


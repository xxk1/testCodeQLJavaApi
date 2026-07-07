package com.lztech.site.viewmodel.coursechaptergoal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseChapterGoalResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-25T09:10:03.832Z")


public class CourseChapterGoalResource {
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

    @JsonProperty("content")
    private String content = null;

    public CourseChapterGoalResource typeName(String typeName) {
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

    public CourseChapterGoalResource typeValue(Integer typeValue) {
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

    public CourseChapterGoalResource parentName(String parentName) {
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

    public CourseChapterGoalResource isShow(Boolean isShow) {
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

    public CourseChapterGoalResource showOrder(Integer showOrder) {
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

    public CourseChapterGoalResource content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 目标内容
     *
     * @return content
     **/
    @ApiModelProperty(value = "目标内容")


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseChapterGoalResource courseChapterGoalResource = (CourseChapterGoalResource) o;
        return Objects.equals(this.typeName, courseChapterGoalResource.typeName) &&
                Objects.equals(this.typeValue, courseChapterGoalResource.typeValue) &&
                Objects.equals(this.parentName, courseChapterGoalResource.parentName) &&
                Objects.equals(this.isShow, courseChapterGoalResource.isShow) &&
                Objects.equals(this.showOrder, courseChapterGoalResource.showOrder) &&
                Objects.equals(this.content, courseChapterGoalResource.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, typeValue, parentName, isShow, showOrder, content);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseChapterGoalResource {\n");

        sb.append("    typeName: ").append(toIndentedString(typeName)).append("\n");
        sb.append("    typeValue: ").append(toIndentedString(typeValue)).append("\n");
        sb.append("    parentName: ").append(toIndentedString(parentName)).append("\n");
        sb.append("    isShow: ").append(toIndentedString(isShow)).append("\n");
        sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
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


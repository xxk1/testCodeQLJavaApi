package com.lztech.site.viewmodel.coursetheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseThemeDetailResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-07T07:12:16.723Z")


public class CourseThemeDetailResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("showOrder")
    private Integer showOrder = null;

    public CourseThemeDetailResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 主题详情id
     *
     * @return id
     **/
    @ApiModelProperty(value = "主题详情id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseThemeDetailResource content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 主题详情内容
     *
     * @return content
     **/
    @ApiModelProperty(value = "主题详情内容")


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CourseThemeDetailResource showOrder(Integer showOrder) {
        this.showOrder = showOrder;
        return this;
    }

    /**
     * 主题详情排序
     *
     * @return showOrder
     **/
    @ApiModelProperty(value = "主题详情排序")


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
        CourseThemeDetailResource courseThemeDetailResource = (CourseThemeDetailResource) o;
        return Objects.equals(this.id, courseThemeDetailResource.id) &&
                Objects.equals(this.content, courseThemeDetailResource.content) &&
                Objects.equals(this.showOrder, courseThemeDetailResource.showOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, showOrder);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseThemeDetailResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
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


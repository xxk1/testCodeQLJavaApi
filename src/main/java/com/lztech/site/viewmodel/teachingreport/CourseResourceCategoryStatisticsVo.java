package com.lztech.site.viewmodel.teachingreport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceCategoryStatisticsVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-12-28T03:34:46.082Z")


public class CourseResourceCategoryStatisticsVo {
    @JsonProperty("categoryId")
    private String categoryId = null;

    @JsonProperty("categoryName")
    private String categoryName = null;

    @JsonProperty("resourceNumber")
    private Integer resourceNumber = null;

    public CourseResourceCategoryStatisticsVo categoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    /**
     * 分类id
     *
     * @return categoryId
     **/
    @ApiModelProperty(value = "分类id")


    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public CourseResourceCategoryStatisticsVo categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    /**
     * 分类名称
     *
     * @return categoryName
     **/
    @ApiModelProperty(value = "分类名称")


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CourseResourceCategoryStatisticsVo resourceNumber(Integer resourceNumber) {
        this.resourceNumber = resourceNumber;
        return this;
    }

    /**
     * 分类资源总数
     *
     * @return resourceNumber
     **/
    @ApiModelProperty(value = "分类资源总数")


    public Integer getResourceNumber() {
        return resourceNumber;
    }

    public void setResourceNumber(Integer resourceNumber) {
        this.resourceNumber = resourceNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceCategoryStatisticsVo courseResourceCategoryStatisticsVo = (CourseResourceCategoryStatisticsVo) o;
        return Objects.equals(this.categoryId, courseResourceCategoryStatisticsVo.categoryId) &&
                Objects.equals(this.categoryName, courseResourceCategoryStatisticsVo.categoryName) &&
                Objects.equals(this.resourceNumber, courseResourceCategoryStatisticsVo.resourceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, resourceNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceCategoryStatisticsVo {\n");

        sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
        sb.append("    categoryName: ").append(toIndentedString(categoryName)).append("\n");
        sb.append("    resourceNumber: ").append(toIndentedString(resourceNumber)).append("\n");
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

